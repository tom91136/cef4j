// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * Chrome DevTools Protocol CacheStorage domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CacheStorage.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class CacheStorage {
    private CacheStorage() {}
    /**
     * Unique identifier of the Cache object.
     */
    public static final class CacheId implements CdpValue<String> {
        public final String value;
        public CacheId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static CachedResponseType of(@Nonnull String value) {
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
            return (String) require("requestURL");
        }
        /**
         * Request method.
         * @return the protocol field value
         */
        public String requestMethod() {
            return (String) require("requestMethod");
        }
        /**
         * Request headers
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.Header> requestHeaders() {
            return CdpObject.requireList(require("requestHeaders"), element0 -> java.util.Objects.requireNonNull(CacheStorage.Header.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Number of seconds since epoch.
         * @return the protocol field value
         */
        public double responseTime() {
            return ((Number) require("responseTime")).doubleValue();
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        public long responseStatus() {
            return ((Number) require("responseStatus")).longValue();
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        public String responseStatusText() {
            return (String) require("responseStatusText");
        }
        /**
         * HTTP response type
         * @return the protocol field value
         */
        public CacheStorage.CachedResponseType responseType() {
            return CacheStorage.CachedResponseType.of((String) require("responseType"));
        }
        /**
         * Response headers
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.Header> responseHeaders() {
            return CdpObject.requireList(require("responseHeaders"), element0 -> java.util.Objects.requireNonNull(CacheStorage.Header.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return new CacheStorage.CacheId((String) require("cacheId"));
        }
        /**
         * Security origin of the cache.
         * @return the protocol field value
         */
        public String securityOrigin() {
            return (String) require("securityOrigin");
        }
        /**
         * Storage key of the cache.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Storage bucket of the cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return Optional.ofNullable(raw("storageBucket") == null ? null : Storage.StorageBucket.fromMap(java.util.Objects.requireNonNull(objectMap(raw("storageBucket")))));
        }
        /**
         * The name of the cache.
         * @return the protocol field value
         */
        public String cacheName() {
            return (String) require("cacheName");
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
         * @param storageBucket field value; empty omits the value
         * @return this model
         */
        public Cache storageBucket(Optional<Storage.StorageBucket> storageBucket) {
            set("storageBucket", storageBucket.orElse(null));
            return this;
        }
        /**
         * Storage bucket of the cache.
         * @param storageBucket field value; null removes the value
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
            return (String) require("body");
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
        /**
         * Deletes a cache.
         * @param cacheId protocol value
         */
        public DeleteCacheRequest(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
        }
        public static DeleteCacheRequest fromMap(Map<String, Object> values) {
            DeleteCacheRequest instance_ = new DeleteCacheRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of cache for deletion.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return new CacheStorage.CacheId((String) require("cacheId"));
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
        /**
         * Deletes a cache entry.
         * @param cacheId protocol value
         * @param request protocol value
         */
        public DeleteEntryRequest(CacheStorage.CacheId cacheId, String request) {
            set("cacheId", cacheId);
            set("request", request);
        }
        public static DeleteEntryRequest fromMap(Map<String, Object> values) {
            DeleteEntryRequest instance_ = new DeleteEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of cache where the entry will be deleted.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return new CacheStorage.CacheId((String) require("cacheId"));
        }
        /**
         * URL spec of the request.
         * @return the protocol field value
         */
        public String request() {
            return (String) require("request");
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
            RequestCacheNamesRequest instance_ = new RequestCacheNamesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return Optional.ofNullable((String) raw("securityOrigin"));
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return Optional.ofNullable((String) raw("storageKey"));
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return Optional.ofNullable(raw("storageBucket") == null ? null : Storage.StorageBucket.fromMap(java.util.Objects.requireNonNull(objectMap(raw("storageBucket")))));
        }
        /**
         * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
         * @param securityOrigin field value; empty omits the value
         * @return this model
         */
        public RequestCacheNamesRequest securityOrigin(Optional<String> securityOrigin) {
            set("securityOrigin", securityOrigin.orElse(null));
            return this;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
         * @param securityOrigin field value; null removes the value
         * @return this model
         */
        public RequestCacheNamesRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value; empty omits the value
         * @return this model
         */
        public RequestCacheNamesRequest storageKey(Optional<String> storageKey) {
            set("storageKey", storageKey.orElse(null));
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value; null removes the value
         * @return this model
         */
        public RequestCacheNamesRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value; empty omits the value
         * @return this model
         */
        public RequestCacheNamesRequest storageBucket(Optional<Storage.StorageBucket> storageBucket) {
            set("storageBucket", storageBucket.orElse(null));
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value; null removes the value
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
        /**
         * Fetches cache entry.
         * @param cacheId protocol value
         * @param requestURL protocol value
         * @param requestHeaders protocol value
         */
        public RequestCachedResponseRequest(CacheStorage.CacheId cacheId, String requestURL, java.util.List<CacheStorage.Header> requestHeaders) {
            set("cacheId", cacheId);
            set("requestURL", requestURL);
            set("requestHeaders", requestHeaders);
        }
        public static RequestCachedResponseRequest fromMap(Map<String, Object> values) {
            RequestCachedResponseRequest instance_ = new RequestCachedResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of cache that contains the entry.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return new CacheStorage.CacheId((String) require("cacheId"));
        }
        /**
         * URL spec of the request.
         * @return the protocol field value
         */
        public String requestURL() {
            return (String) require("requestURL");
        }
        /**
         * headers of the request.
         * @return the protocol field value
         */
        public java.util.List<CacheStorage.Header> requestHeaders() {
            return CdpObject.requireList(require("requestHeaders"), element0 -> java.util.Objects.requireNonNull(CacheStorage.Header.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
        /**
         * Requests data from cache.
         * @param cacheId protocol value
         */
        public RequestEntriesRequest(CacheStorage.CacheId cacheId) {
            set("cacheId", cacheId);
        }
        public static RequestEntriesRequest fromMap(Map<String, Object> values) {
            RequestEntriesRequest instance_ = new RequestEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ID of cache to get entries from.
         * @return the protocol field value
         */
        public CacheStorage.CacheId cacheId() {
            return new CacheStorage.CacheId((String) require("cacheId"));
        }
        /**
         * Number of records to skip.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong skipCount() {
            Long value = CdpObject.numberAsLong(raw("skipCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Number of records to fetch.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong pageSize() {
            Long value = CdpObject.numberAsLong(raw("pageSize"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * If present, only return the entries containing this substring in the path
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pathFilter() {
            return Optional.ofNullable((String) raw("pathFilter"));
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
         * @param skipCount field value; empty omits the value
         * @return this model
         */
        public RequestEntriesRequest skipCount(OptionalLong skipCount) {
            set("skipCount", skipCount.isPresent() ? skipCount.getAsLong() : null);
            return this;
        }
        /**
         * Number of records to skip.
         * @param skipCount field value; null removes the value
         * @return this model
         */
        public RequestEntriesRequest skipCount(Long skipCount) {
            set("skipCount", skipCount);
            return this;
        }
        /**
         * Number of records to fetch.
         * @param pageSize field value; empty omits the value
         * @return this model
         */
        public RequestEntriesRequest pageSize(OptionalLong pageSize) {
            set("pageSize", pageSize.isPresent() ? pageSize.getAsLong() : null);
            return this;
        }
        /**
         * Number of records to fetch.
         * @param pageSize field value; null removes the value
         * @return this model
         */
        public RequestEntriesRequest pageSize(Long pageSize) {
            set("pageSize", pageSize);
            return this;
        }
        /**
         * If present, only return the entries containing this substring in the path
         * @param pathFilter field value; empty omits the value
         * @return this model
         */
        public RequestEntriesRequest pathFilter(Optional<String> pathFilter) {
            set("pathFilter", pathFilter.orElse(null));
            return this;
        }
        /**
         * If present, only return the entries containing this substring in the path
         * @param pathFilter field value; null removes the value
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
            return CdpObject.requireList(require("cacheDataEntries"), element0 -> java.util.Objects.requireNonNull(CacheStorage.DataEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Count of returned entries from this storage. If pathFilter is empty, it is the count of all entries from this storage.
         * @return the protocol field value
         */
        public double returnCount() {
            return ((Number) require("returnCount")).doubleValue();
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Deletes a cache.
         * @param cacheId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCache(CacheStorage.CacheId cacheId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("cacheId", CdpObject.json(cacheId));
            return client.call("CacheStorage.deleteCache", params, result_ -> null);
        }
        /**
         * Deletes a cache.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCache(DeleteCacheRequest request) {
            return client.call("CacheStorage.deleteCache", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Deletes a cache entry.
         * @param cacheId protocol value
         * @param request protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteEntry(CacheStorage.CacheId cacheId, String request) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("cacheId", CdpObject.json(cacheId));
            params.put("request", CdpObject.json(request));
            return client.call("CacheStorage.deleteEntry", params, result_ -> null);
        }
        /**
         * Deletes a cache entry.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteEntry(DeleteEntryRequest request) {
            return client.call("CacheStorage.deleteEntry", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests cache names.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CacheStorage.Cache>> requestCacheNames(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            return client.call("CacheStorage.requestCacheNames", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("caches")), element0 -> java.util.Objects.requireNonNull(CacheStorage.Cache.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Requests cache names.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CacheStorage.Cache>> requestCacheNames() {
            return requestCacheNames(Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Requests cache names.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CacheStorage.Cache>> requestCacheNames(RequestCacheNamesRequest request) {
            return client.call("CacheStorage.requestCacheNames", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("caches")), element0 -> java.util.Objects.requireNonNull(CacheStorage.Cache.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Fetches cache entry.
         * @param cacheId protocol value
         * @param requestURL protocol value
         * @param requestHeaders protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CacheStorage.CachedResponse> requestCachedResponse(CacheStorage.CacheId cacheId, String requestURL, java.util.List<CacheStorage.Header> requestHeaders) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("cacheId", CdpObject.json(cacheId));
            params.put("requestURL", CdpObject.json(requestURL));
            params.put("requestHeaders", CdpObject.json(requestHeaders));
            return client.call("CacheStorage.requestCachedResponse", params, result_ -> java.util.Objects.requireNonNull(CacheStorage.CachedResponse.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("response")))))));
        }
        /**
         * Fetches cache entry.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CacheStorage.CachedResponse> requestCachedResponse(RequestCachedResponseRequest request) {
            return client.call("CacheStorage.requestCachedResponse", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(CacheStorage.CachedResponse.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("response")))))));
        }
        /**
         * Requests data from cache.
         * @param cacheId protocol value
         * @param skipCount protocol value
         * @param pageSize protocol value
         * @param pathFilter protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(CacheStorage.CacheId cacheId, OptionalLong skipCount, OptionalLong pageSize, Optional<String> pathFilter) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("cacheId", CdpObject.json(cacheId));
            skipCount.ifPresent(value_ -> params.put("skipCount", value_));
            pageSize.ifPresent(value_ -> params.put("pageSize", value_));
            pathFilter.ifPresent(value_ -> params.put("pathFilter", CdpObject.json(value_)));
            return client.call("CacheStorage.requestEntries", params, result_ -> new RequestEntriesResult(result_));
        }
        /**
         * Requests data from cache.
         * @param cacheId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(CacheStorage.CacheId cacheId) {
            return requestEntries(cacheId, OptionalLong.empty(), OptionalLong.empty(), Optional.empty());
        }
        /**
         * Requests data from cache.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(RequestEntriesRequest request) {
            return client.call("CacheStorage.requestEntries", request == null ? null : request.toMap(), result_ -> new RequestEntriesResult(result_));
        }
    }
}
