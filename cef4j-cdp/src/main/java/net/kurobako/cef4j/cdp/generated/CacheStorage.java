// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol CacheStorage domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CacheStorage.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class CacheStorage {
    private CacheStorage() {}
    private static final CdpField<java.util.List<CacheStorage.Cache>> CACHES = CdpField.of("caches", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CacheStorage.Cache.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CacheStorage.DataEntry>> CACHE_DATA_ENTRIES = CdpField.of("cacheDataEntries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CacheStorage.DataEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CacheStorage.CacheId> CACHE_ID = CdpField.of("cacheId", value -> new CacheStorage.CacheId((String) value));
    private static final CdpField<java.util.List<CacheStorage.Header>> REQUEST_HEADERS = CdpField.of("requestHeaders", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CacheStorage.Header.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CacheStorage.CachedResponse> RESPONSE = CdpField.of("response", value -> java.util.Objects.requireNonNull(CacheStorage.CachedResponse.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CacheStorage.Header>> RESPONSE_HEADERS = CdpField.of("responseHeaders", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CacheStorage.Header.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CacheStorage.CachedResponseType> RESPONSE_TYPE = CdpField.of("responseType", value -> CacheStorage.CachedResponseType.of((String) value));
    private static final CdpField<Storage.StorageBucket> STORAGE_BUCKET = CdpField.of("storageBucket", value -> java.util.Objects.requireNonNull(Storage.StorageBucket.fromMap(CdpObject.requireObjectMap(value))));
    /**
     * Unique identifier of the Cache object.
     */
    public static final class CacheId implements CdpValue<String> {
        public final String value;
        public CacheId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof CacheId)) return false;
            return value.equals(((CacheId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "CacheId(" + value + ")"; }
    }
    /**
     * type of HTTP response cached
     */
    public enum CachedResponseType implements CdpValue<String> {
        BASIC("basic"),
        CORS("cors"),
        DEFAULT("default"),
        ERROR("error"),
        OPAQUERESPONSE("opaqueResponse"),
        OPAQUEREDIRECT("opaqueRedirect");
        public final String value;
        CachedResponseType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CachedResponseType of(String value) {
            for (CachedResponseType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CachedResponseType value: " + value);
        }
    }
    /**
     * Data entry.
     */
    public static final class DataEntry extends CdpObject {
        public DataEntry() {}
        private DataEntry(Map<String, Object> values) { super(values); }
        public static DataEntry fromMap(Map<String, Object> values) {
            return new DataEntry(values);
        }
        /**
         * Request URL.
         * @return the protocol field value
         */
        public String requestURL() {
            return requireString("requestURL");
        }
        /**
         * Request method.
         * @return the protocol field value
         */
        public String requestMethod() {
            return requireString("requestMethod");
        }
        /**
         * Request headers
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.Header> requestHeaders() {
            return require(REQUEST_HEADERS);
        }
        /**
         * Number of seconds since epoch.
         * @return the protocol field value
         */
        public double responseTime() {
            return requireDouble("responseTime");
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        public long responseStatus() {
            return requireLong("responseStatus");
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        public String responseStatusText() {
            return requireString("responseStatusText");
        }
        /**
         * HTTP response type
         * @return the protocol field value
         */
        public CacheStorage.CachedResponseType responseType() {
            return require(RESPONSE_TYPE);
        }
        /**
         * Response headers
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.Header> responseHeaders() {
            return require(RESPONSE_HEADERS);
        }
        /**
         * Request URL.
         * @param requestURL field value
         * @return this model
         */
        public DataEntry requestURL(String requestURL) {
            set("requestURL", requestURL);
            return this;
        }
        /**
         * Request method.
         * @param requestMethod field value
         * @return this model
         */
        public DataEntry requestMethod(String requestMethod) {
            set("requestMethod", requestMethod);
            return this;
        }
        /**
         * Request headers
         * @param requestHeaders field value
         * @return this model
         */
        public DataEntry requestHeaders(java.util.List<CacheStorage.Header> requestHeaders) {
            set("requestHeaders", requestHeaders);
            return this;
        }
        /**
         * Number of seconds since epoch.
         * @param responseTime field value
         * @return this model
         */
        public DataEntry responseTime(double responseTime) {
            set("responseTime", responseTime);
            return this;
        }
        /**
         * HTTP response status code.
         * @param responseStatus field value
         * @return this model
         */
        public DataEntry responseStatus(long responseStatus) {
            set("responseStatus", responseStatus);
            return this;
        }
        /**
         * HTTP response status text.
         * @param responseStatusText field value
         * @return this model
         */
        public DataEntry responseStatusText(String responseStatusText) {
            set("responseStatusText", responseStatusText);
            return this;
        }
        /**
         * HTTP response type
         * @param responseType field value
         * @return this model
         */
        public DataEntry responseType(CacheStorage.CachedResponseType responseType) {
            set("responseType", responseType);
            return this;
        }
        /**
         * Response headers
         * @param responseHeaders field value
         * @return this model
         */
        public DataEntry responseHeaders(java.util.List<CacheStorage.Header> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
    }
    /**
     * Cache identifier.
     */
    public static final class Cache extends CdpObject {
        public Cache() {}
        private Cache(Map<String, Object> values) { super(values); }
        public static Cache fromMap(Map<String, Object> values) {
            return new Cache(values);
        }
        /**
         * An opaque unique id of the cache.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return require(CACHE_ID);
        }
        /**
         * Security origin of the cache.
         * @return the protocol field value
         */
        public String securityOrigin() {
            return requireString("securityOrigin");
        }
        /**
         * Storage key of the cache.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage bucket of the cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return optional(STORAGE_BUCKET);
        }
        /**
         * The name of the cache.
         * @return the protocol field value
         */
        public String cacheName() {
            return requireString("cacheName");
        }
        /**
         * An opaque unique id of the cache.
         * @param cacheId field value
         * @return this model
         */
        public Cache cacheId(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
            return this;
        }
        /**
         * Security origin of the cache.
         * @param securityOrigin field value
         * @return this model
         */
        public Cache securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key of the cache.
         * @param storageKey field value
         * @return this model
         */
        public Cache storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket of the cache.
         * @param storageBucket field value
         * @return this model
         */
        public Cache storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * The name of the cache.
         * @param cacheName field value
         * @return this model
         */
        public Cache cacheName(String cacheName) {
            set("cacheName", cacheName);
            return this;
        }
    }
    /**
     */
    public static final class Header extends CdpObject {
        public Header() {}
        private Header(Map<String, Object> values) { super(values); }
        public static Header fromMap(Map<String, Object> values) {
            return new Header(values);
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
        public Header name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public Header value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Cached response
     */
    public static final class CachedResponse extends CdpObject {
        public CachedResponse() {}
        private CachedResponse(Map<String, Object> values) { super(values); }
        public static CachedResponse fromMap(Map<String, Object> values) {
            return new CachedResponse(values);
        }
        /**
         * Entry content, base64-encoded. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String body() {
            return requireString("body");
        }
        /**
         * Entry content, base64-encoded. (Encoded as a base64 string when passed over JSON)
         * @param body field value
         * @return this model
         */
        public CachedResponse body(String body) {
            set("body", body);
            return this;
        }
    }
    /**
     * Deletes a cache.
     */
    public static final class DeleteCacheRequest extends CdpObject {
        public DeleteCacheRequest() {}
        public static DeleteCacheRequest fromMap(Map<String, Object> values) {
            final DeleteCacheRequest instance_ = new DeleteCacheRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of cache for deletion.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return require(CACHE_ID);
        }
        /**
         * Id of cache for deletion.
         * @param cacheId field value
         * @return this model
         */
        public DeleteCacheRequest cacheId(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
            return this;
        }
    }
    /**
     * Deletes a cache entry.
     */
    public static final class DeleteEntryRequest extends CdpObject {
        public DeleteEntryRequest() {}
        public static DeleteEntryRequest fromMap(Map<String, Object> values) {
            final DeleteEntryRequest instance_ = new DeleteEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of cache where the entry will be deleted.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return require(CACHE_ID);
        }
        /**
         * URL spec of the request.
         * @return the protocol field value
         */
        public String request() {
            return requireString("request");
        }
        /**
         * Id of cache where the entry will be deleted.
         * @param cacheId field value
         * @return this model
         */
        public DeleteEntryRequest cacheId(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
            return this;
        }
        /**
         * URL spec of the request.
         * @param request field value
         * @return this model
         */
        public DeleteEntryRequest request(String request) {
            set("request", request);
            return this;
        }
    }
    /**
     * Requests cache names.
     */
    public static final class RequestCacheNamesRequest extends CdpObject {
        public RequestCacheNamesRequest() {}
        public static RequestCacheNamesRequest fromMap(Map<String, Object> values) {
            final RequestCacheNamesRequest instance_ = new RequestCacheNamesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return optionalString("securityOrigin");
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return optionalString("storageKey");
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return optional(STORAGE_BUCKET);
        }
        /**
         * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public RequestCacheNamesRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public RequestCacheNamesRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public RequestCacheNamesRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
    }
    /**
     * Fetches cache entry.
     */
    public static final class RequestCachedResponseRequest extends CdpObject {
        public RequestCachedResponseRequest() {}
        public static RequestCachedResponseRequest fromMap(Map<String, Object> values) {
            final RequestCachedResponseRequest instance_ = new RequestCachedResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of cache that contains the entry.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return require(CACHE_ID);
        }
        /**
         * URL spec of the request.
         * @return the protocol field value
         */
        public String requestURL() {
            return requireString("requestURL");
        }
        /**
         * headers of the request.
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.Header> requestHeaders() {
            return require(REQUEST_HEADERS);
        }
        /**
         * Id of cache that contains the entry.
         * @param cacheId field value
         * @return this model
         */
        public RequestCachedResponseRequest cacheId(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
            return this;
        }
        /**
         * URL spec of the request.
         * @param requestURL field value
         * @return this model
         */
        public RequestCachedResponseRequest requestURL(String requestURL) {
            set("requestURL", requestURL);
            return this;
        }
        /**
         * headers of the request.
         * @param requestHeaders field value
         * @return this model
         */
        public RequestCachedResponseRequest requestHeaders(java.util.List<CacheStorage.Header> requestHeaders) {
            set("requestHeaders", requestHeaders);
            return this;
        }
    }
    /**
     * Requests data from cache.
     */
    public static final class RequestEntriesRequest extends CdpObject {
        public RequestEntriesRequest() {}
        public static RequestEntriesRequest fromMap(Map<String, Object> values) {
            final RequestEntriesRequest instance_ = new RequestEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ID of cache to get entries from.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return require(CACHE_ID);
        }
        /**
         * Number of records to skip.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong skipCount() {
            return optionalLong("skipCount");
        }
        /**
         * Number of records to fetch.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong pageSize() {
            return optionalLong("pageSize");
        }
        /**
         * If present, only return the entries containing this substring in the path
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pathFilter() {
            return optionalString("pathFilter");
        }
        /**
         * ID of cache to get entries from.
         * @param cacheId field value
         * @return this model
         */
        public RequestEntriesRequest cacheId(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
            return this;
        }
        /**
         * Number of records to skip.
         * @param skipCount field value
         * @return this model
         */
        public RequestEntriesRequest skipCount(long skipCount) {
            set("skipCount", skipCount);
            return this;
        }
        /**
         * Number of records to fetch.
         * @param pageSize field value
         * @return this model
         */
        public RequestEntriesRequest pageSize(long pageSize) {
            set("pageSize", pageSize);
            return this;
        }
        /**
         * If present, only return the entries containing this substring in the path
         * @param pathFilter field value
         * @return this model
         */
        public RequestEntriesRequest pathFilter(String pathFilter) {
            set("pathFilter", pathFilter);
            return this;
        }
    }
    /**
     * Requests data from cache.
     */
    public static final class RequestEntriesResult extends CdpObject {
        public RequestEntriesResult() {}
        private RequestEntriesResult(Map<String, Object> values) { super(values); }
        public static RequestEntriesResult fromMap(Map<String, Object> values) {
            return new RequestEntriesResult(values);
        }
        /**
         * Array of object store data entries.
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.DataEntry> cacheDataEntries() {
            return require(CACHE_DATA_ENTRIES);
        }
        /**
         * Count of returned entries from this storage. If pathFilter is empty, it is the count of all entries from this storage.
         * @return the protocol field value
         */
        public double returnCount() {
            return requireDouble("returnCount");
        }
        /**
         * Array of object store data entries.
         * @param cacheDataEntries field value
         * @return this model
         */
        public RequestEntriesResult cacheDataEntries(java.util.List<CacheStorage.DataEntry> cacheDataEntries) {
            set("cacheDataEntries", cacheDataEntries);
            return this;
        }
        /**
         * Count of returned entries from this storage. If pathFilter is empty, it is the count of all entries from this storage.
         * @param returnCount field value
         * @return this model
         */
        public RequestEntriesResult returnCount(double returnCount) {
            set("returnCount", returnCount);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Deletes a cache.
         * @param cacheId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCache(CacheStorage.CacheId cacheId) {
            return call("CacheStorage.deleteCache", params("cacheId", cacheId));
        }
        /**
         * Deletes a cache.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCache(DeleteCacheRequest request) {
            return call("CacheStorage.deleteCache", request);
        }
        /**
         * Deletes a cache entry.
         * @param cacheId protocol value
         * @param request protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteEntry(CacheStorage.CacheId cacheId, String request) {
            return call("CacheStorage.deleteEntry", params("cacheId", cacheId, "request", request));
        }
        /**
         * Deletes a cache entry.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteEntry(DeleteEntryRequest request) {
            return call("CacheStorage.deleteEntry", request);
        }
        /**
         * Requests cache names.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CacheStorage.Cache>> requestCacheNames(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket) {
            return call("CacheStorage.requestCacheNames", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket), CACHES::require);
        }
        /**
         * Requests cache names.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CacheStorage.Cache>> requestCacheNames() {
            return call("CacheStorage.requestCacheNames", CACHES::require);
        }
        /**
         * Requests cache names.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CacheStorage.Cache>> requestCacheNames(RequestCacheNamesRequest request) {
            return call("CacheStorage.requestCacheNames", request, CACHES::require);
        }
        /**
         * Fetches cache entry.
         * @param cacheId protocol value
         * @param requestURL protocol value
         * @param requestHeaders protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CacheStorage.CachedResponse> requestCachedResponse(CacheStorage.CacheId cacheId, String requestURL, java.util.List<CacheStorage.Header> requestHeaders) {
            return call("CacheStorage.requestCachedResponse", params("cacheId", cacheId, "requestURL", requestURL, "requestHeaders", requestHeaders), RESPONSE::require);
        }
        /**
         * Fetches cache entry.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CacheStorage.CachedResponse> requestCachedResponse(RequestCachedResponseRequest request) {
            return call("CacheStorage.requestCachedResponse", request, RESPONSE::require);
        }
        /**
         * Requests data from cache.
         * @param cacheId protocol value
         * @param skipCount protocol value
         * @param pageSize protocol value
         * @param pathFilter protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(CacheStorage.CacheId cacheId, @Nullable Long skipCount, @Nullable Long pageSize, @Nullable String pathFilter) {
            return call("CacheStorage.requestEntries", params("cacheId", cacheId, "skipCount", skipCount, "pageSize", pageSize, "pathFilter", pathFilter), RequestEntriesResult::fromMap);
        }
        /**
         * Requests data from cache.
         * @param cacheId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(CacheStorage.CacheId cacheId) {
            return call("CacheStorage.requestEntries", params("cacheId", cacheId), RequestEntriesResult::fromMap);
        }
        /**
         * Requests data from cache.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(RequestEntriesRequest request) {
            return call("CacheStorage.requestEntries", request, RequestEntriesResult::fromMap);
        }
    }
}
