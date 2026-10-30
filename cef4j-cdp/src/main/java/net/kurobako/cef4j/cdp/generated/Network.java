// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
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
            return ((Number) require("requestTime")).doubleValue();
        }
        /**
         * Started resolving proxy.
         * @return the protocol field value
         */
        public double proxyStart() {
            return ((Number) require("proxyStart")).doubleValue();
        }
        /**
         * Finished resolving proxy.
         * @return the protocol field value
         */
        public double proxyEnd() {
            return ((Number) require("proxyEnd")).doubleValue();
        }
        /**
         * Started DNS address resolve.
         * @return the protocol field value
         */
        public double dnsStart() {
            return ((Number) require("dnsStart")).doubleValue();
        }
        /**
         * Finished DNS address resolve.
         * @return the protocol field value
         */
        public double dnsEnd() {
            return ((Number) require("dnsEnd")).doubleValue();
        }
        /**
         * Started connecting to the remote host.
         * @return the protocol field value
         */
        public double connectStart() {
            return ((Number) require("connectStart")).doubleValue();
        }
        /**
         * Connected to the remote host.
         * @return the protocol field value
         */
        public double connectEnd() {
            return ((Number) require("connectEnd")).doubleValue();
        }
        /**
         * Started SSL handshake.
         * @return the protocol field value
         */
        public double sslStart() {
            return ((Number) require("sslStart")).doubleValue();
        }
        /**
         * Finished SSL handshake.
         * @return the protocol field value
         */
        public double sslEnd() {
            return ((Number) require("sslEnd")).doubleValue();
        }
        /**
         * Started running ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerStart() {
            return ((Number) require("workerStart")).doubleValue();
        }
        /**
         * Finished Starting ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerReady() {
            return ((Number) require("workerReady")).doubleValue();
        }
        /**
         * Started fetch event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerFetchStart() {
            return ((Number) require("workerFetchStart")).doubleValue();
        }
        /**
         * Settled fetch event respondWith promise.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerRespondWithSettled() {
            return ((Number) require("workerRespondWithSettled")).doubleValue();
        }
        /**
         * Started ServiceWorker static routing source evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble workerRouterEvaluationStart() {
            return raw("workerRouterEvaluationStart").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Started cache lookup when the source was evaluated to {@code cache}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble workerCacheLookupStart() {
            return raw("workerCacheLookupStart").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Started sending request.
         * @return the protocol field value
         */
        public double sendStart() {
            return ((Number) require("sendStart")).doubleValue();
        }
        /**
         * Finished sending request.
         * @return the protocol field value
         */
        public double sendEnd() {
            return ((Number) require("sendEnd")).doubleValue();
        }
        /**
         * Time the server started pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double pushStart() {
            return ((Number) require("pushStart")).doubleValue();
        }
        /**
         * Time the server finished pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double pushEnd() {
            return ((Number) require("pushEnd")).doubleValue();
        }
        /**
         * Started receiving response headers.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double receiveHeadersStart() {
            return ((Number) require("receiveHeadersStart")).doubleValue();
        }
        /**
         * Finished receiving response headers.
         * @return the protocol field value
         */
        public double receiveHeadersEnd() {
            return ((Number) require("receiveHeadersEnd")).doubleValue();
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
            return raw("bytes").map(value -> (String) value);
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
            return (String) require("url");
        }
        /**
         * Fragment of the requested URL starting with hash, if present.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urlFragment() {
            return raw("urlFragment").map(value -> (String) value);
        }
        /**
         * HTTP request method.
         * @return the protocol field value
         */
        public String method() {
            return (String) require("method");
        }
        /**
         * HTTP request headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return CdpObject.requireObjectMap(require("headers"));
        }
        /**
         * HTTP POST request data. Use postDataEntries instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> postData() {
            return raw("postData").map(value -> (String) value);
        }
        /**
         * True when the request has POST data. Note that postData might still be omitted when this flag is true when the data is too long.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasPostData() {
            return raw("hasPostData").map(value -> (Boolean) value);
        }
        /**
         * Request body elements (post data broken into individual entries).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.PostDataEntry>> postDataEntries() {
            return raw("postDataEntries").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Network.PostDataEntry.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The mixed content type of the request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Security.MixedContentType> mixedContentType() {
            return raw("mixedContentType").map(value -> Security.MixedContentType.of((String) value));
        }
        /**
         * Priority of the resource request at the time request is sent.
         * @return the protocol field value
         */
        public Network.ResourcePriority initialPriority() {
            return Network.ResourcePriority.of((String) require("initialPriority"));
        }
        /**
         * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
         * @return the protocol field value
         */
        public Request.ReferrerPolicyValues referrerPolicy() {
            return Request.ReferrerPolicyValues.of((String) require("referrerPolicy"));
        }
        /**
         * Whether is loaded via link preload.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isLinkPreload() {
            return raw("isLinkPreload").map(value -> (Boolean) value);
        }
        /**
         * Set for requests when the TrustToken API is used. Contains the parameters passed by the developer (e.g. via &quot;fetch&quot;) as understood by the backend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TrustTokenParams> trustTokenParams() {
            return raw("trustTokenParams").flatMap(CdpObject::objectMap).map(Network.TrustTokenParams::fromMap);
        }
        /**
         * True if this resource request is considered to be the &#x27;same site&#x27; as the request corresponding to the main frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isSameSite() {
            return raw("isSameSite").map(value -> (Boolean) value);
        }
        /**
         * True when the resource request is ad-related.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isAdRelated() {
            return raw("isAdRelated").map(value -> (Boolean) value);
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
            return (String) require("status");
        }
        /**
         * Origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Log name / description.
         * @return the protocol field value
         */
        public String logDescription() {
            return (String) require("logDescription");
        }
        /**
         * Log ID.
         * @return the protocol field value
         */
        public String logId() {
            return (String) require("logId");
        }
        /**
         * Issuance date. Unlike TimeSinceEpoch, this contains the number of milliseconds since January 1, 1970, UTC, not the number of seconds.
         * @return the protocol field value
         */
        public double timestamp() {
            return ((Number) require("timestamp")).doubleValue();
        }
        /**
         * Hash algorithm.
         * @return the protocol field value
         */
        public String hashAlgorithm() {
            return (String) require("hashAlgorithm");
        }
        /**
         * Signature algorithm.
         * @return the protocol field value
         */
        public String signatureAlgorithm() {
            return (String) require("signatureAlgorithm");
        }
        /**
         * Signature data.
         * @return the protocol field value
         */
        public String signatureData() {
            return (String) require("signatureData");
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
            return (String) require("protocol");
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @return the protocol field value
         */
        public String keyExchange() {
            return (String) require("keyExchange");
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> keyExchangeGroup() {
            return raw("keyExchangeGroup").map(value -> (String) value);
        }
        /**
         * Cipher name.
         * @return the protocol field value
         */
        public String cipher() {
            return (String) require("cipher");
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> mac() {
            return raw("mac").map(value -> (String) value);
        }
        /**
         * Certificate ID value.
         * @return the protocol field value
         */
        public Security.CertificateId certificateId() {
            return new Security.CertificateId(((Number) require("certificateId")).longValue());
        }
        /**
         * Certificate subject name.
         * @return the protocol field value
         */
        public String subjectName() {
            return (String) require("subjectName");
        }
        /**
         * Subject Alternative Name (SAN) DNS names and IP addresses.
         * @return the protocol field value
         */
        public java.util.List<String> sanList() {
            return CdpObject.requireList(require("sanList"), element0 -> (String) element0);
        }
        /**
         * Name of the issuing CA.
         * @return the protocol field value
         */
        public String issuer() {
            return (String) require("issuer");
        }
        /**
         * Certificate valid from date.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch validFrom() {
            return new Network.TimeSinceEpoch(((Number) require("validFrom")).doubleValue());
        }
        /**
         * Certificate valid to (expiration) date
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch validTo() {
            return new Network.TimeSinceEpoch(((Number) require("validTo")).doubleValue());
        }
        /**
         * List of signed certificate timestamps (SCTs).
         * @return the protocol field value
         */
        public java.util.List<Network.SignedCertificateTimestamp> signedCertificateTimestampList() {
            return CdpObject.requireList(require("signedCertificateTimestampList"), element0 -> java.util.Objects.requireNonNull(Network.SignedCertificateTimestamp.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Whether the request complied with Certificate Transparency policy
         * @return the protocol field value
         */
        public Network.CertificateTransparencyCompliance certificateTransparencyCompliance() {
            return Network.CertificateTransparencyCompliance.of((String) require("certificateTransparencyCompliance"));
        }
        /**
         * The signature algorithm used by the server in the TLS server signature, represented as a TLS SignatureScheme code point. Omitted if not applicable or not known.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong serverSignatureAlgorithm() {
            return raw("serverSignatureAlgorithm").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Whether the connection used Encrypted ClientHello
         * @return the protocol field value
         */
        public boolean encryptedClientHello() {
            return (Boolean) require("encryptedClientHello");
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
            return Network.CorsError.of((String) require("corsError"));
        }
        /**
         * Returns the failedParameter field.
         * @return the protocol field value
         */
        public String failedParameter() {
            return (String) require("failedParameter");
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
            return Network.TrustTokenOperationType.of((String) require("operation"));
        }
        /**
         * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
         * @return the protocol field value
         */
        public TrustTokenParams.RefreshPolicyValues refreshPolicy() {
            return TrustTokenParams.RefreshPolicyValues.of((String) require("refreshPolicy"));
        }
        /**
         * Origins of issuers from whom to request tokens or redemption records.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> issuers() {
            return raw("issuers").flatMap(value -> list(value, element0 -> (String) element0));
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
            return raw("ruleIdMatched").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The router source of the matched rule. If there is a matched rule, this field will be set, otherwise no value will be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerRouterSource> matchedSourceType() {
            return raw("matchedSourceType").map(value -> Network.ServiceWorkerRouterSource.of((String) value));
        }
        /**
         * The actual router source used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerRouterSource> actualSourceType() {
            return raw("actualSourceType").map(value -> Network.ServiceWorkerRouterSource.of((String) value));
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
            return (String) require("url");
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        public long status() {
            return ((Number) require("status")).longValue();
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        public String statusText() {
            return (String) require("statusText");
        }
        /**
         * HTTP response headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return CdpObject.requireObjectMap(require("headers"));
        }
        /**
         * HTTP response headers text. This has been replaced by the headers in Network.responseReceivedExtraInfo.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> headersText() {
            return raw("headersText").map(value -> (String) value);
        }
        /**
         * Resource mimeType as determined by the browser.
         * @return the protocol field value
         */
        public String mimeType() {
            return (String) require("mimeType");
        }
        /**
         * Resource charset as determined by the browser (if applicable).
         * @return the protocol field value
         */
        public String charset() {
            return (String) require("charset");
        }
        /**
         * Refined HTTP request headers that were actually transmitted over the network.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> requestHeaders() {
            return raw("requestHeaders").flatMap(CdpObject::objectMap);
        }
        /**
         * HTTP request headers text. This has been replaced by the headers in Network.requestWillBeSentExtraInfo.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> requestHeadersText() {
            return raw("requestHeadersText").map(value -> (String) value);
        }
        /**
         * Specifies whether physical connection was actually reused for this request.
         * @return the protocol field value
         */
        public boolean connectionReused() {
            return (Boolean) require("connectionReused");
        }
        /**
         * Physical connection id that was actually used for this request.
         * @return the protocol field value
         */
        public double connectionId() {
            return ((Number) require("connectionId")).doubleValue();
        }
        /**
         * Remote IP address.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteIPAddress() {
            return raw("remoteIPAddress").map(value -> (String) value);
        }
        /**
         * Remote port.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return raw("remotePort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Specifies that the request was served from the disk cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromDiskCache() {
            return raw("fromDiskCache").map(value -> (Boolean) value);
        }
        /**
         * Specifies that the request was served from the ServiceWorker.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromServiceWorker() {
            return raw("fromServiceWorker").map(value -> (Boolean) value);
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromPrefetchCache() {
            return raw("fromPrefetchCache").map(value -> (Boolean) value);
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromEarlyHints() {
            return raw("fromEarlyHints").map(value -> (Boolean) value);
        }
        /**
         * Information about how ServiceWorker Static Router API was used. If this field is set with {@code matchedSourceType} field, a matching rule is found. If this field is set without {@code matchedSource}, no matching rule is found. Otherwise, the API is not used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerRouterInfo> serviceWorkerRouterInfo() {
            return raw("serviceWorkerRouterInfo").flatMap(CdpObject::objectMap).map(Network.ServiceWorkerRouterInfo::fromMap);
        }
        /**
         * Total number of bytes received for this request so far.
         * @return the protocol field value
         */
        public double encodedDataLength() {
            return ((Number) require("encodedDataLength")).doubleValue();
        }
        /**
         * Timing information for the given request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceTiming> timing() {
            return raw("timing").flatMap(CdpObject::objectMap).map(Network.ResourceTiming::fromMap);
        }
        /**
         * Response source of response from ServiceWorker.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerResponseSource> serviceWorkerResponseSource() {
            return raw("serviceWorkerResponseSource").map(value -> Network.ServiceWorkerResponseSource.of((String) value));
        }
        /**
         * The time at which the returned response was generated.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> responseTime() {
            return raw("responseTime").map(value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
        }
        /**
         * Cache Storage Cache Name.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cacheStorageCacheName() {
            return raw("cacheStorageCacheName").map(value -> (String) value);
        }
        /**
         * Protocol used to fetch this request.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> protocol() {
            return raw("protocol").map(value -> (String) value);
        }
        /**
         * The reason why Chrome uses a specific transport protocol for HTTP semantics.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AlternateProtocolUsage> alternateProtocolUsage() {
            return raw("alternateProtocolUsage").map(value -> Network.AlternateProtocolUsage.of((String) value));
        }
        /**
         * Security state of the request resource.
         * @return the protocol field value
         */
        public Security.SecurityState securityState() {
            return Security.SecurityState.of((String) require("securityState"));
        }
        /**
         * Security details for the request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SecurityDetails> securityDetails() {
            return raw("securityDetails").flatMap(CdpObject::objectMap).map(Network.SecurityDetails::fromMap);
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
            return CdpObject.requireObjectMap(require("headers"));
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
            return ((Number) require("status")).longValue();
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        public String statusText() {
            return (String) require("statusText");
        }
        /**
         * HTTP response headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return CdpObject.requireObjectMap(require("headers"));
        }
        /**
         * HTTP response headers text.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> headersText() {
            return raw("headersText").map(value -> (String) value);
        }
        /**
         * HTTP request headers.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> requestHeaders() {
            return raw("requestHeaders").flatMap(CdpObject::objectMap);
        }
        /**
         * HTTP request headers text.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> requestHeadersText() {
            return raw("requestHeadersText").map(value -> (String) value);
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
            return ((Number) require("opcode")).doubleValue();
        }
        /**
         * WebSocket message mask.
         * @return the protocol field value
         */
        public boolean mask() {
            return (Boolean) require("mask");
        }
        /**
         * WebSocket message payload data. If the opcode is 1, this is a text message and payloadData is a UTF-8 string. If the opcode isn&#x27;t 1, then payloadData is a base64 encoded string representing binary data.
         * @return the protocol field value
         */
        public String payloadData() {
            return (String) require("payloadData");
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
            return (String) require("url");
        }
        /**
         * Type of this resource.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return Network.ResourceType.of((String) require("type"));
        }
        /**
         * Cached response data.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Response> response() {
            return raw("response").flatMap(CdpObject::objectMap).map(Network.Response::fromMap);
        }
        /**
         * Cached response body size.
         * @return the protocol field value
         */
        public double bodySize() {
            return ((Number) require("bodySize")).doubleValue();
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
            return Initiator.TypeValues.of((String) require("type"));
        }
        /**
         * Initiator JavaScript stack trace, set for Script only. Requires the Debugger domain to be enabled.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stack() {
            return raw("stack").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Initiator URL, set for Parser type or for Script type (when script is importing module) or for SignedExchange type.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Initiator line number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble lineNumber() {
            return raw("lineNumber").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Initiator column number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble columnNumber() {
            return raw("columnNumber").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Set if another request triggered this request (e.g. preflight).
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return raw("requestId").map(value -> new Network.RequestId((String) value));
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
            return (String) require("topLevelSite");
        }
        /**
         * Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
         * @return the protocol field value
         */
        public boolean hasCrossSiteAncestor() {
            return (Boolean) require("hasCrossSiteAncestor");
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
            return (String) require("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Cookie domain.
         * @return the protocol field value
         */
        public String domain() {
            return (String) require("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value
         */
        public String path() {
            return (String) require("path");
        }
        /**
         * Cookie expiration date as the number of seconds since the UNIX epoch. The value is set to -1 if the expiry date is not set. The value can be null for values that cannot be represented in JSON (±Inf).
         * @return the protocol field value
         */
        public double expires() {
            return ((Number) require("expires")).doubleValue();
        }
        /**
         * Cookie size.
         * @return the protocol field value
         */
        public long size() {
            return ((Number) require("size")).longValue();
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value
         */
        public boolean httpOnly() {
            return (Boolean) require("httpOnly");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value
         */
        public boolean secure() {
            return (Boolean) require("secure");
        }
        /**
         * True in case of session cookie.
         * @return the protocol field value
         */
        public boolean session() {
            return (Boolean) require("session");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return raw("sameSite").map(value -> Network.CookieSameSite.of((String) value));
        }
        /**
         * Cookie Priority
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Network.CookiePriority priority() {
            return Network.CookiePriority.of((String) require("priority"));
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Network.CookieSourceScheme sourceScheme() {
            return Network.CookieSourceScheme.of((String) require("sourceScheme"));
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public long sourcePort() {
            return ((Number) require("sourcePort")).longValue();
        }
        /**
         * Cookie partition key.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return raw("partitionKey").flatMap(CdpObject::objectMap).map(Network.CookiePartitionKey::fromMap);
        }
        /**
         * True if cookie partition key is opaque.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> partitionKeyOpaque() {
            return raw("partitionKeyOpaque").map(value -> (Boolean) value);
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
            return CdpObject.requireList(require("blockedReasons"), element0 -> Network.SetCookieBlockedReason.of((String) element0));
        }
        /**
         * The string representing this individual cookie as it would appear in the header. This is not the entire &quot;cookie&quot; or &quot;set-cookie&quot; header which could have multiple cookies.
         * @return the protocol field value
         */
        public String cookieLine() {
            return (String) require("cookieLine");
        }
        /**
         * The cookie object which represents the cookie which was not stored. It is optional because sometimes complete cookie information is not available, such as in the case of parsing errors.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Cookie> cookie() {
            return raw("cookie").flatMap(CdpObject::objectMap).map(Network.Cookie::fromMap);
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
            return Network.CookieExemptionReason.of((String) require("exemptionReason"));
        }
        /**
         * The string representing this individual cookie as it would appear in the header.
         * @return the protocol field value
         */
        public String cookieLine() {
            return (String) require("cookieLine");
        }
        /**
         * The cookie object representing the cookie.
         * @return the protocol field value
         */
        public Network.Cookie cookie() {
            return java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(require("cookie"))));
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
            return java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(require("cookie"))));
        }
        /**
         * The reason(s) the cookie was blocked. If empty means the cookie is included.
         * @return the protocol field value
         */
        public java.util.List<Network.CookieBlockedReason> blockedReasons() {
            return CdpObject.requireList(require("blockedReasons"), element0 -> Network.CookieBlockedReason.of((String) element0));
        }
        /**
         * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only have at most one exemption reason.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieExemptionReason> exemptionReason() {
            return raw("exemptionReason").map(value -> Network.CookieExemptionReason.of((String) value));
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
            return (String) require("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Cookie domain.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> domain() {
            return raw("domain").map(value -> (String) value);
        }
        /**
         * Cookie path.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> path() {
            return raw("path").map(value -> (String) value);
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> secure() {
            return raw("secure").map(value -> (Boolean) value);
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> httpOnly() {
            return raw("httpOnly").map(value -> (Boolean) value);
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return raw("sameSite").map(value -> Network.CookieSameSite.of((String) value));
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> expires() {
            return raw("expires").map(value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
        }
        /**
         * Cookie Priority.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePriority> priority() {
            return raw("priority").map(value -> Network.CookiePriority.of((String) value));
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSourceScheme> sourceScheme() {
            return raw("sourceScheme").map(value -> Network.CookieSourceScheme.of((String) value));
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong sourcePort() {
            return raw("sourcePort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return raw("partitionKey").flatMap(CdpObject::objectMap).map(Network.CookiePartitionKey::fromMap);
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
            return raw("source").map(value -> AuthChallenge.SourceValues.of((String) value));
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
            return AuthChallengeResponse.ResponseValues.of((String) require("response"));
        }
        /**
         * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> username() {
            return raw("username").map(value -> (String) value);
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> password() {
            return raw("password").map(value -> (String) value);
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
            return raw("urlPattern").map(value -> (String) value);
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceType> resourceType() {
            return raw("resourceType").map(value -> Network.ResourceType.of((String) value));
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.InterceptionStage> interceptionStage() {
            return raw("interceptionStage").map(value -> Network.InterceptionStage.of((String) value));
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
            return (String) require("label");
        }
        /**
         * The hex string of signed exchange signature.
         * @return the protocol field value
         */
        public String signature() {
            return (String) require("signature");
        }
        /**
         * Signed exchange signature integrity.
         * @return the protocol field value
         */
        public String integrity() {
            return (String) require("integrity");
        }
        /**
         * Signed exchange signature cert Url.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> certUrl() {
            return raw("certUrl").map(value -> (String) value);
        }
        /**
         * The hex string of signed exchange signature cert sha256.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> certSha256() {
            return raw("certSha256").map(value -> (String) value);
        }
        /**
         * Signed exchange signature validity Url.
         * @return the protocol field value
         */
        public String validityUrl() {
            return (String) require("validityUrl");
        }
        /**
         * Signed exchange signature date.
         * @return the protocol field value
         */
        public long date() {
            return ((Number) require("date")).longValue();
        }
        /**
         * Signed exchange signature expires.
         * @return the protocol field value
         */
        public long expires() {
            return ((Number) require("expires")).longValue();
        }
        /**
         * The encoded certificates.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> certificates() {
            return raw("certificates").flatMap(value -> list(value, element0 -> (String) element0));
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
            return (String) require("requestUrl");
        }
        /**
         * Signed exchange response code.
         * @return the protocol field value
         */
        public long responseCode() {
            return ((Number) require("responseCode")).longValue();
        }
        /**
         * Signed exchange response headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> responseHeaders() {
            return CdpObject.requireObjectMap(require("responseHeaders"));
        }
        /**
         * Signed exchange response signature.
         * @return the protocol field value
         */
        public java.util.List<Network.SignedExchangeSignature> signatures() {
            return CdpObject.requireList(require("signatures"), element0 -> java.util.Objects.requireNonNull(Network.SignedExchangeSignature.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Signed exchange header integrity hash in the form of {@code sha256-&lt;base64-hash-value&gt;}.
         * @return the protocol field value
         */
        public String headerIntegrity() {
            return (String) require("headerIntegrity");
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
            return (String) require("message");
        }
        /**
         * The index of the signature which caused the error.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong signatureIndex() {
            return raw("signatureIndex").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The field which caused the error.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SignedExchangeErrorField> errorField() {
            return raw("errorField").map(value -> Network.SignedExchangeErrorField.of((String) value));
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
            return java.util.Objects.requireNonNull(Network.Response.fromMap(CdpObject.requireObjectMap(require("outerResponse"))));
        }
        /**
         * Whether network response for the signed exchange was accompanied by extra headers.
         * @return the protocol field value
         */
        public boolean hasExtraInfo() {
            return (Boolean) require("hasExtraInfo");
        }
        /**
         * Information about the signed exchange header.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SignedExchangeHeader> header() {
            return raw("header").flatMap(CdpObject::objectMap).map(Network.SignedExchangeHeader::fromMap);
        }
        /**
         * Security details for the signed exchange header.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SecurityDetails> securityDetails() {
            return raw("securityDetails").flatMap(CdpObject::objectMap).map(Network.SecurityDetails::fromMap);
        }
        /**
         * Errors occurred while handling the signed exchange.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.SignedExchangeError>> errors() {
            return raw("errors").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Network.SignedExchangeError.fromMap(CdpObject.requireObjectMap(element0)))));
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
            return (String) require("urlPattern");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        public double latency() {
            return ((Number) require("latency")).doubleValue();
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        public double downloadThroughput() {
            return ((Number) require("downloadThroughput")).doubleValue();
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        public double uploadThroughput() {
            return ((Number) require("uploadThroughput")).doubleValue();
        }
        /**
         * Connection type if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ConnectionType> connectionType() {
            return raw("connectionType").map(value -> Network.ConnectionType.of((String) value));
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble packetLoss() {
            return raw("packetLoss").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong packetQueueLength() {
            return raw("packetQueueLength").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * WebRTC packetReordering feature.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> packetReordering() {
            return raw("packetReordering").map(value -> (Boolean) value);
        }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> offline() {
            return raw("offline").map(value -> (Boolean) value);
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
            return (String) require("urlPattern");
        }
        /**
         * Whether or not to block the pattern. If false, a matching request will not be blocked even if it matches a later {@code BlockPattern}.
         * @return the protocol field value
         */
        public boolean block() {
            return (Boolean) require("block");
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
            return (Boolean) require("noDelay");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble keepAliveDelay() {
            return raw("keepAliveDelay").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sendBufferSize() {
            return raw("sendBufferSize").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble receiveBufferSize() {
            return raw("receiveBufferSize").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Returns the dnsQueryType field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DirectSocketDnsQueryType> dnsQueryType() {
            return raw("dnsQueryType").map(value -> Network.DirectSocketDnsQueryType.of((String) value));
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
            return raw("remoteAddr").map(value -> (String) value);
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return raw("remotePort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> localAddr() {
            return raw("localAddr").map(value -> (String) value);
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong localPort() {
            return raw("localPort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Returns the dnsQueryType field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DirectSocketDnsQueryType> dnsQueryType() {
            return raw("dnsQueryType").map(value -> Network.DirectSocketDnsQueryType.of((String) value));
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sendBufferSize() {
            return raw("sendBufferSize").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble receiveBufferSize() {
            return raw("receiveBufferSize").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Returns the multicastLoopback field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> multicastLoopback() {
            return raw("multicastLoopback").map(value -> (Boolean) value);
        }
        /**
         * Unsigned int 8.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong multicastTimeToLive() {
            return raw("multicastTimeToLive").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Returns the multicastAllowAddressSharing field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> multicastAllowAddressSharing() {
            return raw("multicastAllowAddressSharing").map(value -> (Boolean) value);
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
            return (String) require("data");
        }
        /**
         * Null for connected mode.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteAddr() {
            return raw("remoteAddr").map(value -> (String) value);
        }
        /**
         * Null for connected mode. Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return raw("remotePort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
            return ((Number) require("requestTime")).doubleValue();
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
            return (Boolean) require("initiatorIsSecureContext");
        }
        /**
         * Returns the initiatorIPAddressSpace field.
         * @return the protocol field value
         */
        public Network.IPAddressSpace initiatorIPAddressSpace() {
            return Network.IPAddressSpace.of((String) require("initiatorIPAddressSpace"));
        }
        /**
         * Returns the localNetworkAccessRequestPolicy field.
         * @return the protocol field value
         */
        public Network.LocalNetworkAccessRequestPolicy localNetworkAccessRequestPolicy() {
            return Network.LocalNetworkAccessRequestPolicy.of((String) require("localNetworkAccessRequestPolicy"));
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
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * V8&#x27;s debugging ID for the v8::Context.
         * @return the protocol field value
         */
        public Runtime.UniqueDebuggerId debuggerId() {
            return new Runtime.UniqueDebuggerId((String) require("debuggerId"));
        }
        /**
         * The script&#x27;s url (or generated name based on id if inline script).
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
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
            return CdpObject.requireList(require("ancestryChain"), element0 -> java.util.Objects.requireNonNull(Network.AdScriptIdentifier.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * The filter list rule that caused the root (last) script in {@code ancestryChain} to be tagged as an ad.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> rootScriptFilterlistRule() {
            return raw("rootScriptFilterlistRule").map(value -> (String) value);
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
            return raw("filterlistRule").map(value -> (String) value);
        }
        /**
         * The script ancestry that created the ad, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AdAncestry> adScriptAncestry() {
            return raw("adScriptAncestry").flatMap(CdpObject::objectMap).map(Network.AdAncestry::fromMap);
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
            return Network.CrossOriginOpenerPolicyValue.of((String) require("value"));
        }
        /**
         * Returns the reportOnlyValue field.
         * @return the protocol field value
         */
        public Network.CrossOriginOpenerPolicyValue reportOnlyValue() {
            return Network.CrossOriginOpenerPolicyValue.of((String) require("reportOnlyValue"));
        }
        /**
         * Returns the reportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportingEndpoint() {
            return raw("reportingEndpoint").map(value -> (String) value);
        }
        /**
         * Returns the reportOnlyReportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportOnlyReportingEndpoint() {
            return raw("reportOnlyReportingEndpoint").map(value -> (String) value);
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
            return Network.CrossOriginEmbedderPolicyValue.of((String) require("value"));
        }
        /**
         * Returns the reportOnlyValue field.
         * @return the protocol field value
         */
        public Network.CrossOriginEmbedderPolicyValue reportOnlyValue() {
            return Network.CrossOriginEmbedderPolicyValue.of((String) require("reportOnlyValue"));
        }
        /**
         * Returns the reportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportingEndpoint() {
            return raw("reportingEndpoint").map(value -> (String) value);
        }
        /**
         * Returns the reportOnlyReportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportOnlyReportingEndpoint() {
            return raw("reportOnlyReportingEndpoint").map(value -> (String) value);
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
            return (String) require("effectiveDirectives");
        }
        /**
         * Returns the isEnforced field.
         * @return the protocol field value
         */
        public boolean isEnforced() {
            return (Boolean) require("isEnforced");
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        public Network.ContentSecurityPolicySource source() {
            return Network.ContentSecurityPolicySource.of((String) require("source"));
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
            return raw("coop").flatMap(CdpObject::objectMap).map(Network.CrossOriginOpenerPolicyStatus::fromMap);
        }
        /**
         * Returns the coep field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CrossOriginEmbedderPolicyStatus> coep() {
            return raw("coep").flatMap(CdpObject::objectMap).map(Network.CrossOriginEmbedderPolicyStatus::fromMap);
        }
        /**
         * Returns the csp field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.ContentSecurityPolicyStatus>> csp() {
            return raw("csp").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Network.ContentSecurityPolicyStatus.fromMap(CdpObject.requireObjectMap(element0)))));
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
            return new Network.ReportId((String) require("id"));
        }
        /**
         * The URL of the document that triggered the report.
         * @return the protocol field value
         */
        public String initiatorUrl() {
            return (String) require("initiatorUrl");
        }
        /**
         * The name of the endpoint group that should be used to deliver the report.
         * @return the protocol field value
         */
        public String destination() {
            return (String) require("destination");
        }
        /**
         * The type of the report (specifies the set of data that is contained in the report body).
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
        }
        /**
         * When the report was generated.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch timestamp() {
            return new Network.TimeSinceEpoch(((Number) require("timestamp")).doubleValue());
        }
        /**
         * How many uploads deep the related request was.
         * @return the protocol field value
         */
        public long depth() {
            return ((Number) require("depth")).longValue();
        }
        /**
         * The number of delivery attempts made so far, not including an active attempt.
         * @return the protocol field value
         */
        public long completedAttempts() {
            return ((Number) require("completedAttempts")).longValue();
        }
        /**
         * Returns the body field.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> body() {
            return CdpObject.requireObjectMap(require("body"));
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public Network.ReportStatus status() {
            return Network.ReportStatus.of((String) require("status"));
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
            return (String) require("url");
        }
        /**
         * Name of the endpoint group.
         * @return the protocol field value
         */
        public String groupName() {
            return (String) require("groupName");
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
            return (String) require("site");
        }
        /**
         * The id of the session.
         * @return the protocol field value
         */
        public String id() {
            return (String) require("id");
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
            return java.util.Objects.requireNonNull(Network.DeviceBoundSessionKey.fromMap(CdpObject.requireObjectMap(require("sessionKey"))));
        }
        /**
         * How the session was used (or not used).
         * @return the protocol field value
         */
        public DeviceBoundSessionWithUsage.UsageValues usage() {
            return DeviceBoundSessionWithUsage.UsageValues.of((String) require("usage"));
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
            return (String) require("name");
        }
        /**
         * The domain of the craving.
         * @return the protocol field value
         */
        public String domain() {
            return (String) require("domain");
        }
        /**
         * The path of the craving.
         * @return the protocol field value
         */
        public String path() {
            return (String) require("path");
        }
        /**
         * The {@code Secure} attribute of the craving attributes.
         * @return the protocol field value
         */
        public boolean secure() {
            return (Boolean) require("secure");
        }
        /**
         * The {@code HttpOnly} attribute of the craving attributes.
         * @return the protocol field value
         */
        public boolean httpOnly() {
            return (Boolean) require("httpOnly");
        }
        /**
         * The {@code SameSite} attribute of the craving attributes.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return raw("sameSite").map(value -> Network.CookieSameSite.of((String) value));
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
            return DeviceBoundSessionUrlRule.RuleTypeValues.of((String) require("ruleType"));
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::host_pattern}.
         * @return the protocol field value
         */
        public String hostPattern() {
            return (String) require("hostPattern");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::path_prefix}.
         * @return the protocol field value
         */
        public String pathPrefix() {
            return (String) require("pathPrefix");
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
            return (String) require("origin");
        }
        /**
         * Whether the whole site is included. See comments on {@code net::device_bound_sessions::SessionInclusionRules::include_site_} for more details; this boolean is true if that value is populated.
         * @return the protocol field value
         */
        public boolean includeSite() {
            return (Boolean) require("includeSite");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::url_rules_}.
         * @return the protocol field value
         */
        public java.util.List<Network.DeviceBoundSessionUrlRule> urlRules() {
            return CdpObject.requireList(require("urlRules"), element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionUrlRule.fromMap(CdpObject.requireObjectMap(element0))));
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
            return java.util.Objects.requireNonNull(Network.DeviceBoundSessionKey.fromMap(CdpObject.requireObjectMap(require("key"))));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::refresh_url_}.
         * @return the protocol field value
         */
        public String refreshUrl() {
            return (String) require("refreshUrl");
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::inclusion_rules_}.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionInclusionRules inclusionRules() {
            return java.util.Objects.requireNonNull(Network.DeviceBoundSessionInclusionRules.fromMap(CdpObject.requireObjectMap(require("inclusionRules"))));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cookie_cravings_}.
         * @return the protocol field value
         */
        public java.util.List<Network.DeviceBoundSessionCookieCraving> cookieCravings() {
            return CdpObject.requireList(require("cookieCravings"), element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionCookieCraving.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::expiry_date_}.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch expiryDate() {
            return new Network.TimeSinceEpoch(((Number) require("expiryDate")).doubleValue());
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cached_challenge__}.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cachedChallenge() {
            return raw("cachedChallenge").map(value -> (String) value);
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::allowed_refresh_initiators_}.
         * @return the protocol field value
         */
        public java.util.List<String> allowedRefreshInitiators() {
            return CdpObject.requireList(require("allowedRefreshInitiators"), element0 -> (String) element0);
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
            return (String) require("requestUrl");
        }
        /**
         * The net error of the response if it was not OK.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> netError() {
            return raw("netError").map(value -> (String) value);
        }
        /**
         * The response code if the net error was OK and the response code was not 200.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseError() {
            return raw("responseError").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The body of the response if the net error was OK, the response code was not 200, and the response body was not empty.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> responseErrorBody() {
            return raw("responseErrorBody").map(value -> (String) value);
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
            return Network.DeviceBoundSessionFetchResult.of((String) require("fetchResult"));
        }
        /**
         * The session if there was a newly created session. This is populated for all successful creation events.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSession> newSession() {
            return raw("newSession").flatMap(CdpObject::objectMap).map(Network.DeviceBoundSession::fromMap);
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSessionFailedRequest> failedRequest() {
            return raw("failedRequest").flatMap(CdpObject::objectMap).map(Network.DeviceBoundSessionFailedRequest::fromMap);
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
            return RefreshEventDetails.RefreshResultValues.of((String) require("refreshResult"));
        }
        /**
         * If there was a fetch attempt, the result of that.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSessionFetchResult> fetchResult() {
            return raw("fetchResult").map(value -> Network.DeviceBoundSessionFetchResult.of((String) value));
        }
        /**
         * The session display if there was a newly created session. This is populated for any refresh event that modifies the session config.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSession> newSession() {
            return raw("newSession").flatMap(CdpObject::objectMap).map(Network.DeviceBoundSession::fromMap);
        }
        /**
         * See comments on {@code net::device_bound_sessions::RefreshEventResult::was_fully_proactive_refresh}.
         * @return the protocol field value
         */
        public boolean wasFullyProactiveRefresh() {
            return (Boolean) require("wasFullyProactiveRefresh");
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSessionFailedRequest> failedRequest() {
            return raw("failedRequest").flatMap(CdpObject::objectMap).map(Network.DeviceBoundSessionFailedRequest::fromMap);
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
            return TerminationEventDetails.DeletionReasonValues.of((String) require("deletionReason"));
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
            return ChallengeEventDetails.ChallengeResultValues.of((String) require("challengeResult"));
        }
        /**
         * The challenge set.
         * @return the protocol field value
         */
        public String challenge() {
            return (String) require("challenge");
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
            return (Boolean) require("success");
        }
        /**
         * Optional values used for error reporting.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble netError() {
            return raw("netError").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Returns the netErrorName field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> netErrorName() {
            return raw("netErrorName").map(value -> (String) value);
        }
        /**
         * Returns the httpStatusCode field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble httpStatusCode() {
            return raw("httpStatusCode").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * If successful, one of the following two fields holds the result.
         * @return the protocol field value, empty when absent
         */
        public Optional<IO.StreamHandle> stream() {
            return raw("stream").map(value -> new IO.StreamHandle((String) value));
        }
        /**
         * Response headers.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> headers() {
            return raw("headers").flatMap(CdpObject::objectMap);
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
            return (Boolean) require("disableCache");
        }
        /**
         * Returns the includeCredentials field.
         * @return the protocol field value
         */
        public boolean includeCredentials() {
            return (Boolean) require("includeCredentials");
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
        /**
         * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param encodings protocol value
         */
        public SetAcceptedEncodingsRequest(java.util.List<Network.ContentEncoding> encodings) {
            encodings(encodings);
        }
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
            return CdpObject.requireList(require("encodings"), element0 -> Network.ContentEncoding.of((String) element0));
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
        /**
         * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public ContinueInterceptedRequestRequest(Network.InterceptionId interceptionId) {
            interceptionId(interceptionId);
        }
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
            return new Network.InterceptionId((String) require("interceptionId"));
        }
        /**
         * If set this causes the request to fail with the given reason. Passing {@code Aborted} for requests marked with {@code isNavigationRequest} also cancels the navigation. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ErrorReason> errorReason() {
            return raw("errorReason").map(value -> Network.ErrorReason.of((String) value));
        }
        /**
         * If set the requests completes using with the provided base64 encoded raw response, including HTTP status line and headers etc... Must not be set in response to an authChallenge. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> rawResponse() {
            return raw("rawResponse").map(value -> (String) value);
        }
        /**
         * If set the request url will be modified in a way that&#x27;s not observable by page. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * If set this allows the request method to be overridden. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> method() {
            return raw("method").map(value -> (String) value);
        }
        /**
         * If set this allows postData to be set. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> postData() {
            return raw("postData").map(value -> (String) value);
        }
        /**
         * If set this allows the request headers to be changed. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> headers() {
            return raw("headers").flatMap(CdpObject::objectMap);
        }
        /**
         * Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AuthChallengeResponse> authChallengeResponse() {
            return raw("authChallengeResponse").flatMap(CdpObject::objectMap).map(Network.AuthChallengeResponse::fromMap);
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
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param name protocol value
         */
        public DeleteCookiesRequest(String name) {
            name(name);
        }
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
            return (String) require("name");
        }
        /**
         * If specified, deletes all the cookies with the given name where domain and path match provided URL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * If specified, deletes only cookies with the exact domain.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> domain() {
            return raw("domain").map(value -> (String) value);
        }
        /**
         * If specified, deletes only cookies with the exact path.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> path() {
            return raw("path").map(value -> (String) value);
        }
        /**
         * If specified, deletes only cookies with the the given name and partitionKey where all partition key attributes match the cookie partition key attribute.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return raw("partitionKey").flatMap(CdpObject::objectMap).map(Network.CookiePartitionKey::fromMap);
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
        /**
         * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
         * @param offline protocol value
         * @param latency protocol value
         * @param downloadThroughput protocol value
         * @param uploadThroughput protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public EmulateNetworkConditionsRequest(boolean offline, double latency, double downloadThroughput, double uploadThroughput) {
            offline(offline).latency(latency).downloadThroughput(downloadThroughput).uploadThroughput(uploadThroughput);
        }
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
            return (Boolean) require("offline");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        public double latency() {
            return ((Number) require("latency")).doubleValue();
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        public double downloadThroughput() {
            return ((Number) require("downloadThroughput")).doubleValue();
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        public double uploadThroughput() {
            return ((Number) require("uploadThroughput")).doubleValue();
        }
        /**
         * Connection type if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ConnectionType> connectionType() {
            return raw("connectionType").map(value -> Network.ConnectionType.of((String) value));
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble packetLoss() {
            return raw("packetLoss").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong packetQueueLength() {
            return raw("packetQueueLength").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * WebRTC packetReordering feature.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> packetReordering() {
            return raw("packetReordering").map(value -> (Boolean) value);
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
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param matchedNetworkConditions protocol value
         */
        public EmulateNetworkConditionsByRuleRequest(java.util.List<Network.NetworkConditions> matchedNetworkConditions) {
            matchedNetworkConditions(matchedNetworkConditions);
        }
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
            return raw("offline").map(value -> (Boolean) value);
        }
        /**
         * True to emulate offline service worker.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> emulateOfflineServiceWorker() {
            return raw("emulateOfflineServiceWorker").map(value -> (Boolean) value);
        }
        /**
         * Configure conditions for matching requests. If multiple entries match a request, the first entry wins. Global conditions can be configured by leaving the urlPattern for the conditions empty. These global conditions are also applied for throttling of p2p connections.
         * @return the protocol field value
         */
        public java.util.List<Network.NetworkConditions> matchedNetworkConditions() {
            return CdpObject.requireList(require("matchedNetworkConditions"), element0 -> java.util.Objects.requireNonNull(Network.NetworkConditions.fromMap(CdpObject.requireObjectMap(element0))));
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
        /**
         * Override the state of navigator.onLine and navigator.connection.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param offline protocol value
         * @param latency protocol value
         * @param downloadThroughput protocol value
         * @param uploadThroughput protocol value
         */
        public OverrideNetworkStateRequest(boolean offline, double latency, double downloadThroughput, double uploadThroughput) {
            offline(offline).latency(latency).downloadThroughput(downloadThroughput).uploadThroughput(uploadThroughput);
        }
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
            return (Boolean) require("offline");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        public double latency() {
            return ((Number) require("latency")).doubleValue();
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        public double downloadThroughput() {
            return ((Number) require("downloadThroughput")).doubleValue();
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        public double uploadThroughput() {
            return ((Number) require("uploadThroughput")).doubleValue();
        }
        /**
         * Connection type if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ConnectionType> connectionType() {
            return raw("connectionType").map(value -> Network.ConnectionType.of((String) value));
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
            return raw("maxTotalBufferSize").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxResourceBufferSize() {
            return raw("maxResourceBufferSize").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Longest post body size (in bytes) that would be included in requestWillBeSent notification
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxPostDataSize() {
            return raw("maxPostDataSize").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Whether DirectSocket chunk send/receive events should be reported.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> reportDirectSocketTraffic() {
            return raw("reportDirectSocketTraffic").map(value -> (Boolean) value);
        }
        /**
         * Enable storing response bodies outside of renderer, so that these survive a cross-process navigation. Requires maxTotalBufferSize to be set. Currently defaults to false. This field is being deprecated in favor of the dedicated configureDurableMessages command, due to the possibility of deadlocks when awaiting Network.enable before issuing Runtime.runIfWaitingForDebugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableDurableMessages() {
            return raw("enableDurableMessages").map(value -> (Boolean) value);
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
            return raw("maxTotalBufferSize").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxResourceBufferSize() {
            return raw("maxResourceBufferSize").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
        /**
         * Returns the DER-encoded certificate.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         */
        public GetCertificateRequest(String origin) {
            origin(origin);
        }
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
            return (String) require("origin");
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
            return raw("urls").flatMap(value -> list(value, element0 -> (String) element0));
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
        /**
         * Returns content served for the given request.
         * @param requestId protocol value
         */
        public GetResponseBodyRequest(Network.RequestId requestId) {
            requestId(requestId);
        }
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
            return new Network.RequestId((String) require("requestId"));
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
        /**
         * Returns post data sent with the request. Returns an error when no data was sent with the request.
         * @param requestId protocol value
         */
        public GetRequestPostDataRequest(Network.RequestId requestId) {
            requestId(requestId);
        }
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
            return new Network.RequestId((String) require("requestId"));
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
        /**
         * Returns content served for the given currently intercepted request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         */
        public GetResponseBodyForInterceptionRequest(Network.InterceptionId interceptionId) {
            interceptionId(interceptionId);
        }
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
            return new Network.InterceptionId((String) require("interceptionId"));
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
        /**
         * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         */
        public TakeResponseBodyForInterceptionAsStreamRequest(Network.InterceptionId interceptionId) {
            interceptionId(interceptionId);
        }
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
            return new Network.InterceptionId((String) require("interceptionId"));
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
        /**
         * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         */
        public ReplayXHRRequest(Network.RequestId requestId) {
            requestId(requestId);
        }
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
            return new Network.RequestId((String) require("requestId"));
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
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @param query protocol value
         */
        public SearchInResponseBodyRequest(Network.RequestId requestId, String query) {
            requestId(requestId).query(query);
        }
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        public String query() {
            return (String) require("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> caseSensitive() {
            return raw("caseSensitive").map(value -> (Boolean) value);
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isRegex() {
            return raw("isRegex").map(value -> (Boolean) value);
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
            return raw("urlPatterns").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Network.BlockPattern.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * URL patterns to block. Wildcards (&#x27;*&#x27;) are allowed.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<java.util.List<String>> urls() {
            return raw("urls").flatMap(value -> list(value, element0 -> (String) element0));
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
        /**
         * Toggles ignoring of service worker for each request.
         * @param bypass protocol value
         */
        public SetBypassServiceWorkerRequest(boolean bypass) {
            bypass(bypass);
        }
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
            return (Boolean) require("bypass");
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
        /**
         * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
         * @param cacheDisabled protocol value
         */
        public SetCacheDisabledRequest(boolean cacheDisabled) {
            cacheDisabled(cacheDisabled);
        }
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
            return (Boolean) require("cacheDisabled");
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
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param name protocol value
         * @param value protocol value
         */
        public SetCookieRequest(String name, String value) {
            name(name).value(value);
        }
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
            return (String) require("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Cookie domain.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> domain() {
            return raw("domain").map(value -> (String) value);
        }
        /**
         * Cookie path.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> path() {
            return raw("path").map(value -> (String) value);
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> secure() {
            return raw("secure").map(value -> (Boolean) value);
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> httpOnly() {
            return raw("httpOnly").map(value -> (Boolean) value);
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return raw("sameSite").map(value -> Network.CookieSameSite.of((String) value));
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> expires() {
            return raw("expires").map(value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
        }
        /**
         * Cookie Priority type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePriority> priority() {
            return raw("priority").map(value -> Network.CookiePriority.of((String) value));
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSourceScheme> sourceScheme() {
            return raw("sourceScheme").map(value -> Network.CookieSourceScheme.of((String) value));
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong sourcePort() {
            return raw("sourcePort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return raw("partitionKey").flatMap(CdpObject::objectMap).map(Network.CookiePartitionKey::fromMap);
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
        /**
         * Sets given cookies.
         * @param cookies protocol value
         */
        public SetCookiesRequest(java.util.List<Network.CookieParam> cookies) {
            cookies(cookies);
        }
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
            return CdpObject.requireList(require("cookies"), element0 -> java.util.Objects.requireNonNull(Network.CookieParam.fromMap(CdpObject.requireObjectMap(element0))));
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
        /**
         * Specifies whether to always send extra HTTP headers with the requests from this page.
         * @param headers protocol value
         */
        public SetExtraHTTPHeadersRequest(java.util.Map<String, Object> headers) {
            headers(headers);
        }
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
            return CdpObject.requireObjectMap(require("headers"));
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
        /**
         * Specifies whether to attach a page script stack id in requests
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetAttachDebugStackRequest(boolean enabled) {
            enabled(enabled);
        }
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
            return (Boolean) require("enabled");
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
        /**
         * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetRequestInterceptionRequest(java.util.List<Network.RequestPattern> patterns) {
            patterns(patterns);
        }
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
            return CdpObject.requireList(require("patterns"), element0 -> java.util.Objects.requireNonNull(Network.RequestPattern.fromMap(CdpObject.requireObjectMap(element0))));
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
        /**
         * Allows overriding user agent with the given string.
         * @param userAgent protocol value
         */
        public SetUserAgentOverrideRequest(String userAgent) {
            userAgent(userAgent);
        }
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
            return (String) require("userAgent");
        }
        /**
         * Browser language to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> acceptLanguage() {
            return raw("acceptLanguage").map(value -> (String) value);
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> platform() {
            return raw("platform").map(value -> (String) value);
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.UserAgentMetadata> userAgentMetadata() {
            return raw("userAgentMetadata").flatMap(CdpObject::objectMap).map(Emulation.UserAgentMetadata::fromMap);
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
        /**
         * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         */
        public StreamResourceContentRequest(Network.RequestId requestId) {
            requestId(requestId);
        }
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
            return new Network.RequestId((String) require("requestId"));
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
            return raw("frameId").map(value -> new Page.FrameId((String) value));
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
        /**
         * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         */
        public EnableReportingApiRequest(boolean enable) {
            enable(enable);
        }
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
            return (Boolean) require("enable");
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
        /**
         * Sets up tracking device bound sessions and fetching of initial set of sessions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         */
        public EnableDeviceBoundSessionsRequest(boolean enable) {
            enable(enable);
        }
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
            return (Boolean) require("enable");
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
        /**
         * Deletes a device bound session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param key protocol value
         */
        public DeleteDeviceBoundSessionRequest(Network.DeviceBoundSessionKey key) {
            key(key);
        }
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
            return java.util.Objects.requireNonNull(Network.DeviceBoundSessionKey.fromMap(CdpObject.requireObjectMap(require("key"))));
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
        /**
         * Fetches the schemeful site for a specific origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         */
        public FetchSchemefulSiteRequest(String origin) {
            origin(origin);
        }
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
            return (String) require("origin");
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
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param url protocol value
         * @param options protocol value
         */
        public LoadNetworkResourceRequest(String url, Network.LoadNetworkResourceOptions options) {
            url(url).options(options);
        }
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
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * URL of the resource to get content for.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Options for the request.
         * @return the protocol field value
         */
        public Network.LoadNetworkResourceOptions options() {
            return java.util.Objects.requireNonNull(Network.LoadNetworkResourceOptions.fromMap(CdpObject.requireObjectMap(require("options"))));
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
        /**
         * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableThirdPartyCookieRestriction protocol value
         */
        public SetCookieControlsRequest(boolean enableThirdPartyCookieRestriction) {
            enableThirdPartyCookieRestriction(enableThirdPartyCookieRestriction);
        }
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
            return (Boolean) require("enableThirdPartyCookieRestriction");
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
            return (String) require("postData");
        }
        /**
         * True, if content was sent as base64.
         * @return the protocol field value
         */
        public boolean base64Encoded() {
            return (Boolean) require("base64Encoded");
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Data chunk length.
         * @return the protocol field value
         */
        public long dataLength() {
            return ((Number) require("dataLength")).longValue();
        }
        /**
         * Actual bytes received (might be less than dataLength for compressed encodings).
         * @return the protocol field value
         */
        public long encodedDataLength() {
            return ((Number) require("encodedDataLength")).longValue();
        }
        /**
         * Data that was received. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return raw("data").map(value -> (String) value);
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Message type.
         * @return the protocol field value
         */
        public String eventName() {
            return (String) require("eventName");
        }
        /**
         * Message identifier.
         * @return the protocol field value
         */
        public String eventId() {
            return (String) require("eventId");
        }
        /**
         * Message content.
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Resource type.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return Network.ResourceType.of((String) require("type"));
        }
        /**
         * Error message. List of network errors: https://cs.chromium.org/chromium/src/net/base/net_error_list.h
         * @return the protocol field value
         */
        public String errorText() {
            return (String) require("errorText");
        }
        /**
         * True if loading was canceled.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> canceled() {
            return raw("canceled").map(value -> (Boolean) value);
        }
        /**
         * The reason why loading was blocked, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.BlockedReason> blockedReason() {
            return raw("blockedReason").map(value -> Network.BlockedReason.of((String) value));
        }
        /**
         * The reason why loading was blocked by CORS, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CorsErrorStatus> corsErrorStatus() {
            return raw("corsErrorStatus").flatMap(CdpObject::objectMap).map(Network.CorsErrorStatus::fromMap);
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Total number of bytes received for this request.
         * @return the protocol field value
         */
        public double encodedDataLength() {
            return ((Number) require("encodedDataLength")).doubleValue();
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
            return new Network.InterceptionId((String) require("interceptionId"));
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Network.Request request() {
            return java.util.Objects.requireNonNull(Network.Request.fromMap(CdpObject.requireObjectMap(require("request"))));
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
         * Whether this is a navigation request, which can abort the navigation completely.
         * @return the protocol field value
         */
        public boolean isNavigationRequest() {
            return (Boolean) require("isNavigationRequest");
        }
        /**
         * Set if the request is a navigation that will result in a download. Only present after response is received from the server (i.e. HeadersReceived stage).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isDownload() {
            return raw("isDownload").map(value -> (Boolean) value);
        }
        /**
         * Redirect location, only sent if a redirect was intercepted.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> redirectUrl() {
            return raw("redirectUrl").map(value -> (String) value);
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AuthChallenge> authChallenge() {
            return raw("authChallenge").flatMap(CdpObject::objectMap).map(Network.AuthChallenge::fromMap);
        }
        /**
         * Response error if intercepted at response stage or if redirect occurred while intercepting request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ErrorReason> responseErrorReason() {
            return raw("responseErrorReason").map(value -> Network.ErrorReason.of((String) value));
        }
        /**
         * Response code if intercepted at response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseStatusCode() {
            return raw("responseStatusCode").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Response headers if intercepted at the response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> responseHeaders() {
            return raw("responseHeaders").flatMap(CdpObject::objectMap);
        }
        /**
         * If the intercepted request had a corresponding requestWillBeSent event fired for it, then this requestId will be the same as the requestId present in the requestWillBeSent event.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return raw("requestId").map(value -> new Network.RequestId((String) value));
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
            return new Network.RequestId((String) require("requestId"));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return new Network.LoaderId((String) require("loaderId"));
        }
        /**
         * URL of the document this request is loaded for.
         * @return the protocol field value
         */
        public String documentURL() {
            return (String) require("documentURL");
        }
        /**
         * Request data.
         * @return the protocol field value
         */
        public Network.Request request() {
            return java.util.Objects.requireNonNull(Network.Request.fromMap(CdpObject.requireObjectMap(require("request"))));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch wallTime() {
            return new Network.TimeSinceEpoch(((Number) require("wallTime")).doubleValue());
        }
        /**
         * Request initiator.
         * @return the protocol field value
         */
        public Network.Initiator initiator() {
            return java.util.Objects.requireNonNull(Network.Initiator.fromMap(CdpObject.requireObjectMap(require("initiator"))));
        }
        /**
         * In the case that redirectResponse is populated, this flag indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for the request which was just redirected.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public boolean redirectHasExtraInfo() {
            return (Boolean) require("redirectHasExtraInfo");
        }
        /**
         * Redirect response data.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Response> redirectResponse() {
            return raw("redirectResponse").flatMap(CdpObject::objectMap).map(Network.Response::fromMap);
        }
        /**
         * Type of this resource.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceType> type() {
            return raw("type").map(value -> Network.ResourceType.of((String) value));
        }
        /**
         * Frame identifier.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * Whether the request is initiated by a user gesture. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasUserGesture() {
            return raw("hasUserGesture").map(value -> (Boolean) value);
        }
        /**
         * The render-blocking behavior of the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RenderBlockingBehavior> renderBlockingBehavior() {
            return raw("renderBlockingBehavior").map(value -> Network.RenderBlockingBehavior.of((String) value));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * New priority
         * @return the protocol field value
         */
        public Network.ResourcePriority newPriority() {
            return Network.ResourcePriority.of((String) require("newPriority"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Information about the signed exchange response.
         * @return the protocol field value
         */
        public Network.SignedExchangeInfo info() {
            return java.util.Objects.requireNonNull(Network.SignedExchangeInfo.fromMap(CdpObject.requireObjectMap(require("info"))));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return new Network.LoaderId((String) require("loaderId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Resource type.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return Network.ResourceType.of((String) require("type"));
        }
        /**
         * Response data.
         * @return the protocol field value
         */
        public Network.Response response() {
            return java.util.Objects.requireNonNull(Network.Response.fromMap(CdpObject.requireObjectMap(require("response"))));
        }
        /**
         * Indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public boolean hasExtraInfo() {
            return (Boolean) require("hasExtraInfo");
        }
        /**
         * Frame identifier.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * WebSocket request URL.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Request initiator.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return raw("initiator").flatMap(CdpObject::objectMap).map(Network.Initiator::fromMap);
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * WebSocket error message.
         * @return the protocol field value
         */
        public String errorMessage() {
            return (String) require("errorMessage");
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        public Network.WebSocketFrame response() {
            return java.util.Objects.requireNonNull(Network.WebSocketFrame.fromMap(CdpObject.requireObjectMap(require("response"))));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        public Network.WebSocketFrame response() {
            return java.util.Objects.requireNonNull(Network.WebSocketFrame.fromMap(CdpObject.requireObjectMap(require("response"))));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        public Network.WebSocketResponse response() {
            return java.util.Objects.requireNonNull(Network.WebSocketResponse.fromMap(CdpObject.requireObjectMap(require("response"))));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * UTC Timestamp.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch wallTime() {
            return new Network.TimeSinceEpoch(((Number) require("wallTime")).doubleValue());
        }
        /**
         * WebSocket request data.
         * @return the protocol field value
         */
        public Network.WebSocketRequest request() {
            return java.util.Objects.requireNonNull(Network.WebSocketRequest.fromMap(CdpObject.requireObjectMap(require("request"))));
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
            return new Network.RequestId((String) require("transportId"));
        }
        /**
         * WebTransport request URL.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Request initiator.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return raw("initiator").flatMap(CdpObject::objectMap).map(Network.Initiator::fromMap);
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
            return new Network.RequestId((String) require("transportId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("transportId"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        public String remoteAddr() {
            return (String) require("remoteAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value
         */
        public long remotePort() {
            return ((Number) require("remotePort")).longValue();
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        public Network.DirectTCPSocketOptions options() {
            return java.util.Objects.requireNonNull(Network.DirectTCPSocketOptions.fromMap(CdpObject.requireObjectMap(require("options"))));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Returns the initiator field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return raw("initiator").flatMap(CdpObject::objectMap).map(Network.Initiator::fromMap);
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        public String remoteAddr() {
            return (String) require("remoteAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        public long remotePort() {
            return ((Number) require("remotePort")).longValue();
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> localAddr() {
            return raw("localAddr").map(value -> (String) value);
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong localPort() {
            return raw("localPort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        public String errorMessage() {
            return (String) require("errorMessage");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the IPAddress field.
         * @return the protocol field value
         */
        public String IPAddress() {
            return (String) require("IPAddress");
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the IPAddress field.
         * @return the protocol field value
         */
        public String IPAddress() {
            return (String) require("IPAddress");
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        public Network.DirectUDPSocketOptions options() {
            return java.util.Objects.requireNonNull(Network.DirectUDPSocketOptions.fromMap(CdpObject.requireObjectMap(require("options"))));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Returns the initiator field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return raw("initiator").flatMap(CdpObject::objectMap).map(Network.Initiator::fromMap);
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value
         */
        public String localAddr() {
            return (String) require("localAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        public long localPort() {
            return ((Number) require("localPort")).longValue();
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteAddr() {
            return raw("remoteAddr").map(value -> (String) value);
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return raw("remotePort").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        public String errorMessage() {
            return (String) require("errorMessage");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public Network.DirectUDPMessage message() {
            return java.util.Objects.requireNonNull(Network.DirectUDPMessage.fromMap(CdpObject.requireObjectMap(require("message"))));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("identifier"));
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public Network.DirectUDPMessage message() {
            return java.util.Objects.requireNonNull(Network.DirectUDPMessage.fromMap(CdpObject.requireObjectMap(require("message"))));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * A list of cookies potentially associated to the requested URL. This includes both cookies sent with the request and the ones not sent; the latter are distinguished by having blockedReasons field set.
         * @return the protocol field value
         */
        public java.util.List<Network.AssociatedCookie> associatedCookies() {
            return CdpObject.requireList(require("associatedCookies"), element0 -> java.util.Objects.requireNonNull(Network.AssociatedCookie.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Raw request headers as they will be sent over the wire.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return CdpObject.requireObjectMap(require("headers"));
        }
        /**
         * Connection timing information for the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Network.ConnectTiming connectTiming() {
            return java.util.Objects.requireNonNull(Network.ConnectTiming.fromMap(CdpObject.requireObjectMap(require("connectTiming"))));
        }
        /**
         * How the request site&#x27;s device bound sessions were used during this request.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.DeviceBoundSessionWithUsage>> deviceBoundSessionUsages() {
            return raw("deviceBoundSessionUsages").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionWithUsage.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The client security state set for the request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ClientSecurityState> clientSecurityState() {
            return raw("clientSecurityState").flatMap(CdpObject::objectMap).map(Network.ClientSecurityState::fromMap);
        }
        /**
         * Whether the site has partitioned cookies stored in a partition different than the current one.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> siteHasCookieInOtherPartition() {
            return raw("siteHasCookieInOtherPartition").map(value -> (Boolean) value);
        }
        /**
         * The network conditions id if this request was affected by network conditions configured via emulateNetworkConditionsByRule.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> appliedNetworkConditionsId() {
            return raw("appliedNetworkConditionsId").map(value -> (String) value);
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * A list of cookies which were not stored from the response along with the corresponding reasons for blocking. The cookies here may not be valid due to syntax errors, which are represented by the invalid cookie line string instead of a proper cookie.
         * @return the protocol field value
         */
        public java.util.List<Network.BlockedSetCookieWithReason> blockedCookies() {
            return CdpObject.requireList(require("blockedCookies"), element0 -> java.util.Objects.requireNonNull(Network.BlockedSetCookieWithReason.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return CdpObject.requireObjectMap(require("headers"));
        }
        /**
         * The IP address space of the resource. The address space can only be determined once the transport established the connection, so we can&#x27;t send it in {@code requestWillBeSentExtraInfo}.
         * @return the protocol field value
         */
        public Network.IPAddressSpace resourceIPAddressSpace() {
            return Network.IPAddressSpace.of((String) require("resourceIPAddressSpace"));
        }
        /**
         * The status code of the response. This is useful in cases the request failed and no responseReceived event is triggered, which is the case for, e.g., CORS errors. This is also the correct status code for cached requests, where the status in responseReceived is a 200 and this will be 304.
         * @return the protocol field value
         */
        public long statusCode() {
            return ((Number) require("statusCode")).longValue();
        }
        /**
         * Raw response header text as it was received over the wire. The raw text may not always be available, such as in the case of HTTP/2 or QUIC.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> headersText() {
            return raw("headersText").map(value -> (String) value);
        }
        /**
         * The cookie partition key that will be used to store partitioned cookies set in this response. Only sent when partitioned cookies are enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> cookiePartitionKey() {
            return raw("cookiePartitionKey").flatMap(CdpObject::objectMap).map(Network.CookiePartitionKey::fromMap);
        }
        /**
         * True if partitioned cookies are enabled, but the partition key is not serializable to string.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> cookiePartitionKeyOpaque() {
            return raw("cookiePartitionKeyOpaque").map(value -> (Boolean) value);
        }
        /**
         * A list of cookies which should have been blocked by 3PCD but are exempted and stored from the response with the corresponding reason.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.ExemptedSetCookieWithReason>> exemptedCookies() {
            return raw("exemptedCookies").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Network.ExemptedSetCookieWithReason.fromMap(CdpObject.requireObjectMap(element0)))));
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
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return CdpObject.requireObjectMap(require("headers"));
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
            return TrustTokenOperationDoneEvent.StatusValues.of((String) require("status"));
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Network.TrustTokenOperationType type() {
            return Network.TrustTokenOperationType.of((String) require("type"));
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * Top level origin. The context in which the operation was attempted.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> topLevelOrigin() {
            return raw("topLevelOrigin").map(value -> (String) value);
        }
        /**
         * Origin of the issuer in case of a &quot;Issuance&quot; or &quot;Redemption&quot; operation.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> issuerOrigin() {
            return raw("issuerOrigin").map(value -> (String) value);
        }
        /**
         * The number of obtained Trust Tokens on a successful &quot;Issuance&quot; operation.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong issuedTokenCount() {
            return raw("issuedTokenCount").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
            return java.util.Objects.requireNonNull(Network.ReportingApiReport.fromMap(CdpObject.requireObjectMap(require("report"))));
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
            return java.util.Objects.requireNonNull(Network.ReportingApiReport.fromMap(CdpObject.requireObjectMap(require("report"))));
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
            return (String) require("origin");
        }
        /**
         * Returns the endpoints field.
         * @return the protocol field value
         */
        public java.util.List<Network.ReportingApiEndpoint> endpoints() {
            return CdpObject.requireList(require("endpoints"), element0 -> java.util.Objects.requireNonNull(Network.ReportingApiEndpoint.fromMap(CdpObject.requireObjectMap(element0))));
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
            return CdpObject.requireList(require("sessions"), element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSession.fromMap(CdpObject.requireObjectMap(element0))));
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
            return new Network.DeviceBoundSessionEventId((String) require("eventId"));
        }
        /**
         * The site this session event is associated with.
         * @return the protocol field value
         */
        public String site() {
            return (String) require("site");
        }
        /**
         * Whether this event was considered successful.
         * @return the protocol field value
         */
        public boolean succeeded() {
            return (Boolean) require("succeeded");
        }
        /**
         * The session ID this event is associated with. May not be populated for failed events.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sessionId() {
            return raw("sessionId").map(value -> (String) value);
        }
        /**
         * The below are the different session event type details. Exactly one is populated.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CreationEventDetails> creationEventDetails() {
            return raw("creationEventDetails").flatMap(CdpObject::objectMap).map(Network.CreationEventDetails::fromMap);
        }
        /**
         * Returns the refreshEventDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RefreshEventDetails> refreshEventDetails() {
            return raw("refreshEventDetails").flatMap(CdpObject::objectMap).map(Network.RefreshEventDetails::fromMap);
        }
        /**
         * Returns the terminationEventDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TerminationEventDetails> terminationEventDetails() {
            return raw("terminationEventDetails").flatMap(CdpObject::objectMap).map(Network.TerminationEventDetails::fromMap);
        }
        /**
         * Returns the challengeEventDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ChallengeEventDetails> challengeEventDetails() {
            return raw("challengeEventDetails").flatMap(CdpObject::objectMap).map(Network.ChallengeEventDetails::fromMap);
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param encodings protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAcceptedEncodings(java.util.List<Network.ContentEncoding> encodings) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("encodings", CdpObject.json(encodings));
            return client.call("Network.setAcceptedEncodings", params);
        }
        /**
         * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAcceptedEncodings(SetAcceptedEncodingsRequest request) {
            return client.call("Network.setAcceptedEncodings", request == null ? null : request.toMap());
        }
        /**
         * Clears accepted encodings set by setAcceptedEncodings
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearAcceptedEncodingsOverride() {
            return client.call("Network.clearAcceptedEncodingsOverride", null);
        }
        /**
         * Tells whether clearing browser cache is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canClearBrowserCache() {
            return client.call("Network.canClearBrowserCache", null, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("result")));
        }
        /**
         * Tells whether clearing browser cookies is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canClearBrowserCookies() {
            return client.call("Network.canClearBrowserCookies", null, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("result")));
        }
        /**
         * Tells whether emulation of network conditions is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canEmulateNetworkConditions() {
            return client.call("Network.canEmulateNetworkConditions", null, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("result")));
        }
        /**
         * Clears browser cache.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearBrowserCache() {
            return client.call("Network.clearBrowserCache", null);
        }
        /**
         * Clears browser cookies.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearBrowserCookies() {
            return client.call("Network.clearBrowserCookies", null);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("interceptionId", CdpObject.json(interceptionId));
            if (errorReason != null) params.put("errorReason", CdpObject.json(errorReason));
            if (rawResponse != null) params.put("rawResponse", CdpObject.json(rawResponse));
            if (url != null) params.put("url", CdpObject.json(url));
            if (method != null) params.put("method", CdpObject.json(method));
            if (postData != null) params.put("postData", CdpObject.json(postData));
            if (headers != null) params.put("headers", CdpObject.json(headers));
            if (authChallengeResponse != null) params.put("authChallengeResponse", CdpObject.json(authChallengeResponse));
            return client.call("Network.continueInterceptedRequest", params);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("interceptionId", CdpObject.json(interceptionId));
            return client.call("Network.continueInterceptedRequest", params);
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
            return client.call("Network.continueInterceptedRequest", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            if (url != null) params.put("url", CdpObject.json(url));
            if (domain != null) params.put("domain", CdpObject.json(domain));
            if (path != null) params.put("path", CdpObject.json(path));
            if (partitionKey != null) params.put("partitionKey", CdpObject.json(partitionKey));
            return client.call("Network.deleteCookies", params);
        }
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCookies(String name) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            return client.call("Network.deleteCookies", params);
        }
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCookies(DeleteCookiesRequest request) {
            return client.call("Network.deleteCookies", request == null ? null : request.toMap());
        }
        /**
         * Disables network tracking, prevents network events from being sent to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Network.disable", null);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("offline", CdpObject.json(offline));
            params.put("latency", CdpObject.json(latency));
            params.put("downloadThroughput", CdpObject.json(downloadThroughput));
            params.put("uploadThroughput", CdpObject.json(uploadThroughput));
            if (connectionType != null) params.put("connectionType", CdpObject.json(connectionType));
            if (packetLoss != null) params.put("packetLoss", CdpObject.json(packetLoss));
            if (packetQueueLength != null) params.put("packetQueueLength", CdpObject.json(packetQueueLength));
            if (packetReordering != null) params.put("packetReordering", CdpObject.json(packetReordering));
            return client.call("Network.emulateNetworkConditions", params);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("offline", CdpObject.json(offline));
            params.put("latency", CdpObject.json(latency));
            params.put("downloadThroughput", CdpObject.json(downloadThroughput));
            params.put("uploadThroughput", CdpObject.json(uploadThroughput));
            return client.call("Network.emulateNetworkConditions", params);
        }
        /**
         * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> emulateNetworkConditions(EmulateNetworkConditionsRequest request) {
            return client.call("Network.emulateNetworkConditions", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            if (offline != null) params.put("offline", CdpObject.json(offline));
            if (emulateOfflineServiceWorker != null) params.put("emulateOfflineServiceWorker", CdpObject.json(emulateOfflineServiceWorker));
            params.put("matchedNetworkConditions", CdpObject.json(matchedNetworkConditions));
            return client.call("Network.emulateNetworkConditionsByRule", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("ruleIds")), element0 -> (String) element0));
        }
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param matchedNetworkConditions protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> emulateNetworkConditionsByRule(java.util.List<Network.NetworkConditions> matchedNetworkConditions) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("matchedNetworkConditions", CdpObject.json(matchedNetworkConditions));
            return client.call("Network.emulateNetworkConditionsByRule", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("ruleIds")), element0 -> (String) element0));
        }
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> emulateNetworkConditionsByRule(EmulateNetworkConditionsByRuleRequest request) {
            return client.call("Network.emulateNetworkConditionsByRule", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("ruleIds")), element0 -> (String) element0));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("offline", CdpObject.json(offline));
            params.put("latency", CdpObject.json(latency));
            params.put("downloadThroughput", CdpObject.json(downloadThroughput));
            params.put("uploadThroughput", CdpObject.json(uploadThroughput));
            params.put("connectionType", CdpObject.json(connectionType));
            return client.call("Network.overrideNetworkState", params);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("offline", CdpObject.json(offline));
            params.put("latency", CdpObject.json(latency));
            params.put("downloadThroughput", CdpObject.json(downloadThroughput));
            params.put("uploadThroughput", CdpObject.json(uploadThroughput));
            return client.call("Network.overrideNetworkState", params);
        }
        /**
         * Override the state of navigator.onLine and navigator.connection.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideNetworkState(OverrideNetworkStateRequest request) {
            return client.call("Network.overrideNetworkState", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            if (maxTotalBufferSize != null) params.put("maxTotalBufferSize", CdpObject.json(maxTotalBufferSize));
            if (maxResourceBufferSize != null) params.put("maxResourceBufferSize", CdpObject.json(maxResourceBufferSize));
            if (maxPostDataSize != null) params.put("maxPostDataSize", CdpObject.json(maxPostDataSize));
            if (reportDirectSocketTraffic != null) params.put("reportDirectSocketTraffic", CdpObject.json(reportDirectSocketTraffic));
            if (enableDurableMessages != null) params.put("enableDurableMessages", CdpObject.json(enableDurableMessages));
            return client.call("Network.enable", params);
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Network.enable", null);
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("Network.enable", request == null ? null : request.toMap());
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param maxTotalBufferSize protocol value
         * @param maxResourceBufferSize protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> configureDurableMessages(@Nullable Long maxTotalBufferSize, @Nullable Long maxResourceBufferSize) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (maxTotalBufferSize != null) params.put("maxTotalBufferSize", CdpObject.json(maxTotalBufferSize));
            if (maxResourceBufferSize != null) params.put("maxResourceBufferSize", CdpObject.json(maxResourceBufferSize));
            return client.call("Network.configureDurableMessages", params);
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> configureDurableMessages() {
            return client.call("Network.configureDurableMessages", null);
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> configureDurableMessages(ConfigureDurableMessagesRequest request) {
            return client.call("Network.configureDurableMessages", request == null ? null : request.toMap());
        }
        /**
         * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the {@code cookies} field. Deprecated. Use Storage.getCookies instead.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<Network.Cookie>> getAllCookies() {
            return client.call("Network.getAllCookies", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("cookies")), element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the DER-encoded certificate.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getCertificate(String origin) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Network.getCertificate", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("tableNames")), element0 -> (String) element0));
        }
        /**
         * Returns the DER-encoded certificate.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getCertificate(GetCertificateRequest request) {
            return client.call("Network.getCertificate", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("tableNames")), element0 -> (String) element0));
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @param urls protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(java.util.List<String> urls) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("urls", CdpObject.json(urls));
            return client.call("Network.getCookies", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("cookies")), element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies() {
            return client.call("Network.getCookies", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("cookies")), element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(GetCookiesRequest request) {
            return client.call("Network.getCookies", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("cookies")), element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns content served for the given request.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(Network.RequestId requestId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            return client.call("Network.getResponseBody", params, result_ -> new GetResponseBodyResult(result_));
        }
        /**
         * Returns content served for the given request.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(GetResponseBodyRequest request) {
            return client.call("Network.getResponseBody", request == null ? null : request.toMap(), result_ -> new GetResponseBodyResult(result_));
        }
        /**
         * Returns post data sent with the request. Returns an error when no data was sent with the request.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRequestPostDataResult> getRequestPostData(Network.RequestId requestId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            return client.call("Network.getRequestPostData", params, result_ -> new GetRequestPostDataResult(result_));
        }
        /**
         * Returns post data sent with the request. Returns an error when no data was sent with the request.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRequestPostDataResult> getRequestPostData(GetRequestPostDataRequest request) {
            return client.call("Network.getRequestPostData", request == null ? null : request.toMap(), result_ -> new GetRequestPostDataResult(result_));
        }
        /**
         * Returns content served for the given currently intercepted request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyForInterceptionResult> getResponseBodyForInterception(Network.InterceptionId interceptionId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("interceptionId", CdpObject.json(interceptionId));
            return client.call("Network.getResponseBodyForInterception", params, result_ -> new GetResponseBodyForInterceptionResult(result_));
        }
        /**
         * Returns content served for the given currently intercepted request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyForInterceptionResult> getResponseBodyForInterception(GetResponseBodyForInterceptionRequest request) {
            return client.call("Network.getResponseBodyForInterception", request == null ? null : request.toMap(), result_ -> new GetResponseBodyForInterceptionResult(result_));
        }
        /**
         * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyForInterceptionAsStream(Network.InterceptionId interceptionId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("interceptionId", CdpObject.json(interceptionId));
            return client.call("Network.takeResponseBodyForInterceptionAsStream", params, result_ -> new IO.StreamHandle((String) java.util.Objects.requireNonNull(result_.get("stream"))));
        }
        /**
         * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyForInterceptionAsStream(TakeResponseBodyForInterceptionAsStreamRequest request) {
            return client.call("Network.takeResponseBodyForInterceptionAsStream", request == null ? null : request.toMap(), result_ -> new IO.StreamHandle((String) java.util.Objects.requireNonNull(result_.get("stream"))));
        }
        /**
         * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> replayXHR(Network.RequestId requestId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            return client.call("Network.replayXHR", params);
        }
        /**
         * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> replayXHR(ReplayXHRRequest request) {
            return client.call("Network.replayXHR", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            params.put("query", CdpObject.json(query));
            if (caseSensitive != null) params.put("caseSensitive", CdpObject.json(caseSensitive));
            if (isRegex != null) params.put("isRegex", CdpObject.json(isRegex));
            return client.call("Network.searchInResponseBody", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResponseBody(Network.RequestId requestId, String query) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            params.put("query", CdpObject.json(query));
            return client.call("Network.searchInResponseBody", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResponseBody(SearchInResponseBodyRequest request) {
            return client.call("Network.searchInResponseBody", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param urlPatterns protocol value
         * @param urls protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlockedURLs(java.util.@Nullable List<Network.BlockPattern> urlPatterns, java.util.@Nullable List<String> urls) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (urlPatterns != null) params.put("urlPatterns", CdpObject.json(urlPatterns));
            if (urls != null) params.put("urls", CdpObject.json(urls));
            return client.call("Network.setBlockedURLs", params);
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlockedURLs() {
            return client.call("Network.setBlockedURLs", null);
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlockedURLs(SetBlockedURLsRequest request) {
            return client.call("Network.setBlockedURLs", request == null ? null : request.toMap());
        }
        /**
         * Toggles ignoring of service worker for each request.
         * @param bypass protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBypassServiceWorker(boolean bypass) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("bypass", CdpObject.json(bypass));
            return client.call("Network.setBypassServiceWorker", params);
        }
        /**
         * Toggles ignoring of service worker for each request.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBypassServiceWorker(SetBypassServiceWorkerRequest request) {
            return client.call("Network.setBypassServiceWorker", request == null ? null : request.toMap());
        }
        /**
         * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
         * @param cacheDisabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCacheDisabled(boolean cacheDisabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("cacheDisabled", CdpObject.json(cacheDisabled));
            return client.call("Network.setCacheDisabled", params);
        }
        /**
         * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCacheDisabled(SetCacheDisabledRequest request) {
            return client.call("Network.setCacheDisabled", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            params.put("value", CdpObject.json(value));
            if (url != null) params.put("url", CdpObject.json(url));
            if (domain != null) params.put("domain", CdpObject.json(domain));
            if (path != null) params.put("path", CdpObject.json(path));
            if (secure != null) params.put("secure", CdpObject.json(secure));
            if (httpOnly != null) params.put("httpOnly", CdpObject.json(httpOnly));
            if (sameSite != null) params.put("sameSite", CdpObject.json(sameSite));
            if (expires != null) params.put("expires", CdpObject.json(expires));
            if (priority != null) params.put("priority", CdpObject.json(priority));
            if (sourceScheme != null) params.put("sourceScheme", CdpObject.json(sourceScheme));
            if (sourcePort != null) params.put("sourcePort", CdpObject.json(sourcePort));
            if (partitionKey != null) params.put("partitionKey", CdpObject.json(partitionKey));
            return client.call("Network.setCookie", params, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("success")));
        }
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param name protocol value
         * @param value protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> setCookie(String name, String value) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            params.put("value", CdpObject.json(value));
            return client.call("Network.setCookie", params, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("success")));
        }
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> setCookie(SetCookieRequest request) {
            return client.call("Network.setCookie", request == null ? null : request.toMap(), result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("success")));
        }
        /**
         * Sets given cookies.
         * @param cookies protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(java.util.List<Network.CookieParam> cookies) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("cookies", CdpObject.json(cookies));
            return client.call("Network.setCookies", params);
        }
        /**
         * Sets given cookies.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(SetCookiesRequest request) {
            return client.call("Network.setCookies", request == null ? null : request.toMap());
        }
        /**
         * Specifies whether to always send extra HTTP headers with the requests from this page.
         * @param headers protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setExtraHTTPHeaders(java.util.Map<String, Object> headers) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("headers", CdpObject.json(headers));
            return client.call("Network.setExtraHTTPHeaders", params);
        }
        /**
         * Specifies whether to always send extra HTTP headers with the requests from this page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setExtraHTTPHeaders(SetExtraHTTPHeadersRequest request) {
            return client.call("Network.setExtraHTTPHeaders", request == null ? null : request.toMap());
        }
        /**
         * Specifies whether to attach a page script stack id in requests
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttachDebugStack(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Network.setAttachDebugStack", params);
        }
        /**
         * Specifies whether to attach a page script stack id in requests
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttachDebugStack(SetAttachDebugStackRequest request) {
            return client.call("Network.setAttachDebugStack", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("patterns", CdpObject.json(patterns));
            return client.call("Network.setRequestInterception", params);
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
            return client.call("Network.setRequestInterception", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("userAgent", CdpObject.json(userAgent));
            if (acceptLanguage != null) params.put("acceptLanguage", CdpObject.json(acceptLanguage));
            if (platform != null) params.put("platform", CdpObject.json(platform));
            if (userAgentMetadata != null) params.put("userAgentMetadata", CdpObject.json(userAgentMetadata));
            return client.call("Network.setUserAgentOverride", params);
        }
        /**
         * Allows overriding user agent with the given string.
         * @param userAgent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("userAgent", CdpObject.json(userAgent));
            return client.call("Network.setUserAgentOverride", params);
        }
        /**
         * Allows overriding user agent with the given string.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(SetUserAgentOverrideRequest request) {
            return client.call("Network.setUserAgentOverride", request == null ? null : request.toMap());
        }
        /**
         * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> streamResourceContent(Network.RequestId requestId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            return client.call("Network.streamResourceContent", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("bufferedData")));
        }
        /**
         * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> streamResourceContent(StreamResourceContentRequest request) {
            return client.call("Network.streamResourceContent", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("bufferedData")));
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.SecurityIsolationStatus> getSecurityIsolationStatus(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Network.getSecurityIsolationStatus", params, result_ -> java.util.Objects.requireNonNull(Network.SecurityIsolationStatus.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("status"))))));
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.SecurityIsolationStatus> getSecurityIsolationStatus() {
            return client.call("Network.getSecurityIsolationStatus", null, result_ -> java.util.Objects.requireNonNull(Network.SecurityIsolationStatus.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("status"))))));
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.SecurityIsolationStatus> getSecurityIsolationStatus(GetSecurityIsolationStatusRequest request) {
            return client.call("Network.getSecurityIsolationStatus", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Network.SecurityIsolationStatus.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("status"))))));
        }
        /**
         * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableReportingApi(boolean enable) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enable", CdpObject.json(enable));
            return client.call("Network.enableReportingApi", params);
        }
        /**
         * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableReportingApi(EnableReportingApiRequest request) {
            return client.call("Network.enableReportingApi", request == null ? null : request.toMap());
        }
        /**
         * Sets up tracking device bound sessions and fetching of initial set of sessions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableDeviceBoundSessions(boolean enable) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enable", CdpObject.json(enable));
            return client.call("Network.enableDeviceBoundSessions", params);
        }
        /**
         * Sets up tracking device bound sessions and fetching of initial set of sessions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableDeviceBoundSessions(EnableDeviceBoundSessionsRequest request) {
            return client.call("Network.enableDeviceBoundSessions", request == null ? null : request.toMap());
        }
        /**
         * Deletes a device bound session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param key protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDeviceBoundSession(Network.DeviceBoundSessionKey key) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("key", CdpObject.json(key));
            return client.call("Network.deleteDeviceBoundSession", params);
        }
        /**
         * Deletes a device bound session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDeviceBoundSession(DeleteDeviceBoundSessionRequest request) {
            return client.call("Network.deleteDeviceBoundSession", request == null ? null : request.toMap());
        }
        /**
         * Fetches the schemeful site for a specific origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> fetchSchemefulSite(String origin) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Network.fetchSchemefulSite", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("schemefulSite")));
        }
        /**
         * Fetches the schemeful site for a specific origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> fetchSchemefulSite(FetchSchemefulSiteRequest request) {
            return client.call("Network.fetchSchemefulSite", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("schemefulSite")));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            params.put("url", CdpObject.json(url));
            params.put("options", CdpObject.json(options));
            return client.call("Network.loadNetworkResource", params, result_ -> java.util.Objects.requireNonNull(Network.LoadNetworkResourcePageResult.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("resource"))))));
        }
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param url protocol value
         * @param options protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.LoadNetworkResourcePageResult> loadNetworkResource(String url, Network.LoadNetworkResourceOptions options) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            params.put("options", CdpObject.json(options));
            return client.call("Network.loadNetworkResource", params, result_ -> java.util.Objects.requireNonNull(Network.LoadNetworkResourcePageResult.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("resource"))))));
        }
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.LoadNetworkResourcePageResult> loadNetworkResource(LoadNetworkResourceRequest request) {
            return client.call("Network.loadNetworkResource", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Network.LoadNetworkResourcePageResult.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("resource"))))));
        }
        /**
         * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableThirdPartyCookieRestriction protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookieControls(boolean enableThirdPartyCookieRestriction) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enableThirdPartyCookieRestriction", CdpObject.json(enableThirdPartyCookieRestriction));
            return client.call("Network.setCookieControls", params);
        }
        /**
         * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookieControls(SetCookieControlsRequest request) {
            return client.call("Network.setCookieControls", request == null ? null : request.toMap());
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
