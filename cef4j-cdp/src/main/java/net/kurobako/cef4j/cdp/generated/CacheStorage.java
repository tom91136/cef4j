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
 * Chrome DevTools Protocol CacheStorage domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/CacheStorage.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class CacheStorage {
    private CacheStorage() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * type of HTTP response cached
     */
    public static final class CachedResponseType {
        private CachedResponseType() {}
        public static final String BASIC = "basic";
        public static final String CORS = "cors";
        public static final String DEFAULT = "default";
        public static final String ERROR = "error";
        public static final String OPAQUERESPONSE = "opaqueResponse";
        public static final String OPAQUEREDIRECT = "opaqueRedirect";
    }
    /**
     * Data entry.
     */
    public static final class DataEntry extends CdpObject {
        private DataEntry(Map<String, Object> values) { super(values); }
        @Nullable public static DataEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DataEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request URL.
         * @return the protocol field value
         */
        @Nullable public String requestURL() {
            return (String) value("requestURL");
        }
        /**
         * Request method.
         * @return the protocol field value
         */
        @Nullable public String requestMethod() {
            return (String) value("requestMethod");
        }
        /**
         * Request headers
         * @return the protocol field value
         */
        @Nullable public java.util.List<CacheStorage.Header> requestHeaders() {
            return list(value("requestHeaders"), element0 -> CacheStorage.Header.fromMap(objectMap(element0)));
        }
        /**
         * Number of seconds since epoch.
         * @return the protocol field value
         */
        @Nullable public Double responseTime() {
            return numberAsDouble(value("responseTime"));
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        @Nullable public Long responseStatus() {
            return numberAsLong(value("responseStatus"));
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        @Nullable public String responseStatusText() {
            return (String) value("responseStatusText");
        }
        /**
         * HTTP response type
         * @return the protocol field value
         */
        @Nullable public String responseType() {
            return (String) value("responseType");
        }
        /**
         * Response headers
         * @return the protocol field value
         */
        @Nullable public java.util.List<CacheStorage.Header> responseHeaders() {
            return list(value("responseHeaders"), element0 -> CacheStorage.Header.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestURL(@Nullable String value) {
                if (value == null) values.remove("requestURL");
                else values.put("requestURL", jsonValue(value));
                return this;
            }
            /**
             * Request method.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestMethod(@Nullable String value) {
                if (value == null) values.remove("requestMethod");
                else values.put("requestMethod", jsonValue(value));
                return this;
            }
            /**
             * Request headers
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestHeaders(@Nullable java.util.List<CacheStorage.Header> value) {
                if (value == null) values.remove("requestHeaders");
                else values.put("requestHeaders", jsonValue(value));
                return this;
            }
            /**
             * Number of seconds since epoch.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseTime(@Nullable Double value) {
                if (value == null) values.remove("responseTime");
                else values.put("responseTime", jsonValue(value));
                return this;
            }
            /**
             * HTTP response status code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseStatus(@Nullable Long value) {
                if (value == null) values.remove("responseStatus");
                else values.put("responseStatus", jsonValue(value));
                return this;
            }
            /**
             * HTTP response status text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseStatusText(@Nullable String value) {
                if (value == null) values.remove("responseStatusText");
                else values.put("responseStatusText", jsonValue(value));
                return this;
            }
            /**
             * HTTP response type
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseType(@Nullable String value) {
                if (value == null) values.remove("responseType");
                else values.put("responseType", jsonValue(value));
                return this;
            }
            /**
             * Response headers
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseHeaders(@Nullable java.util.List<CacheStorage.Header> value) {
                if (value == null) values.remove("responseHeaders");
                else values.put("responseHeaders", jsonValue(value));
                return this;
            }
            public DataEntry build() {
                if (!values.containsKey("requestURL")) throw new IllegalStateException("Missing required CDP field: requestURL");
                if (!values.containsKey("requestMethod")) throw new IllegalStateException("Missing required CDP field: requestMethod");
                if (!values.containsKey("requestHeaders")) throw new IllegalStateException("Missing required CDP field: requestHeaders");
                if (!values.containsKey("responseTime")) throw new IllegalStateException("Missing required CDP field: responseTime");
                if (!values.containsKey("responseStatus")) throw new IllegalStateException("Missing required CDP field: responseStatus");
                if (!values.containsKey("responseStatusText")) throw new IllegalStateException("Missing required CDP field: responseStatusText");
                if (!values.containsKey("responseType")) throw new IllegalStateException("Missing required CDP field: responseType");
                if (!values.containsKey("responseHeaders")) throw new IllegalStateException("Missing required CDP field: responseHeaders");
                return new DataEntry(values);
            }
        }
    }
    /**
     * Cache identifier.
     */
    public static final class Cache extends CdpObject {
        private Cache(Map<String, Object> values) { super(values); }
        @Nullable public static Cache fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Cache(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An opaque unique id of the cache.
         * @return the protocol field value
         */
        @Nullable public String cacheId() {
            return (String) value("cacheId");
        }
        /**
         * Security origin of the cache.
         * @return the protocol field value
         */
        @Nullable public String securityOrigin() {
            return (String) value("securityOrigin");
        }
        /**
         * Storage key of the cache.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Storage bucket of the cache.
         * @return the protocol field value
         */
        @Nullable public Storage.StorageBucket storageBucket() {
            return Storage.StorageBucket.fromMap(objectMap(value("storageBucket")));
        }
        /**
         * The name of the cache.
         * @return the protocol field value
         */
        @Nullable public String cacheName() {
            return (String) value("cacheName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An opaque unique id of the cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheId(@Nullable String value) {
                if (value == null) values.remove("cacheId");
                else values.put("cacheId", jsonValue(value));
                return this;
            }
            /**
             * Security origin of the cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityOrigin(@Nullable String value) {
                if (value == null) values.remove("securityOrigin");
                else values.put("securityOrigin", jsonValue(value));
                return this;
            }
            /**
             * Storage key of the cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Storage bucket of the cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageBucket(@Nullable Storage.StorageBucket value) {
                if (value == null) values.remove("storageBucket");
                else values.put("storageBucket", jsonValue(value));
                return this;
            }
            /**
             * The name of the cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheName(@Nullable String value) {
                if (value == null) values.remove("cacheName");
                else values.put("cacheName", jsonValue(value));
                return this;
            }
            public Cache build() {
                if (!values.containsKey("cacheId")) throw new IllegalStateException("Missing required CDP field: cacheId");
                if (!values.containsKey("securityOrigin")) throw new IllegalStateException("Missing required CDP field: securityOrigin");
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("cacheName")) throw new IllegalStateException("Missing required CDP field: cacheName");
                return new Cache(values);
            }
        }
    }
    /**
     */
    public static final class Header extends CdpObject {
        private Header(Map<String, Object> values) { super(values); }
        @Nullable public static Header fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Header(values);
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
            public Header build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new Header(values);
            }
        }
    }
    /**
     * Cached response
     */
    public static final class CachedResponse extends CdpObject {
        private CachedResponse(Map<String, Object> values) { super(values); }
        @Nullable public static CachedResponse fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CachedResponse(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Entry content, base64-encoded. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String body() {
            return (String) value("body");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Entry content, base64-encoded. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder body(@Nullable String value) {
                if (value == null) values.remove("body");
                else values.put("body", jsonValue(value));
                return this;
            }
            public CachedResponse build() {
                if (!values.containsKey("body")) throw new IllegalStateException("Missing required CDP field: body");
                return new CachedResponse(values);
            }
        }
    }
    /**
     * Deletes a cache.
     */
    public static final class DeleteCacheParams extends CdpObject {
        private DeleteCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of cache for deletion.
         * @return the protocol field value
         */
        @Nullable public String cacheId() {
            return (String) value("cacheId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of cache for deletion.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheId(@Nullable String value) {
                if (value == null) values.remove("cacheId");
                else values.put("cacheId", jsonValue(value));
                return this;
            }
            public DeleteCacheParams build() {
                if (!values.containsKey("cacheId")) throw new IllegalStateException("Missing required CDP field: cacheId");
                return new DeleteCacheParams(values);
            }
        }
    }
    /**
     * Deletes a cache.
     */
    public static final class DeleteCacheResult extends CdpObject {
        private DeleteCacheResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteCacheResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteCacheResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteCacheResult build() {
                return new DeleteCacheResult(values);
            }
        }
    }
    /**
     * Deletes a cache entry.
     */
    public static final class DeleteEntryParams extends CdpObject {
        private DeleteEntryParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteEntryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteEntryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of cache where the entry will be deleted.
         * @return the protocol field value
         */
        @Nullable public String cacheId() {
            return (String) value("cacheId");
        }
        /**
         * URL spec of the request.
         * @return the protocol field value
         */
        @Nullable public String request() {
            return (String) value("request");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of cache where the entry will be deleted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheId(@Nullable String value) {
                if (value == null) values.remove("cacheId");
                else values.put("cacheId", jsonValue(value));
                return this;
            }
            /**
             * URL spec of the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable String value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public DeleteEntryParams build() {
                if (!values.containsKey("cacheId")) throw new IllegalStateException("Missing required CDP field: cacheId");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new DeleteEntryParams(values);
            }
        }
    }
    /**
     * Deletes a cache entry.
     */
    public static final class DeleteEntryResult extends CdpObject {
        private DeleteEntryResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteEntryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteEntryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteEntryResult build() {
                return new DeleteEntryResult(values);
            }
        }
    }
    /**
     * Requests cache names.
     */
    public static final class RequestCacheNamesParams extends CdpObject {
        private RequestCacheNamesParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestCacheNamesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestCacheNamesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
         * @return the protocol field value
         */
        @Nullable public String securityOrigin() {
            return (String) value("securityOrigin");
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value
         */
        @Nullable public Storage.StorageBucket storageBucket() {
            return Storage.StorageBucket.fromMap(objectMap(value("storageBucket")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, storageBucket must be specified. Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityOrigin(@Nullable String value) {
                if (value == null) values.remove("securityOrigin");
                else values.put("securityOrigin", jsonValue(value));
                return this;
            }
            /**
             * Storage key.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Storage bucket. If not specified, it uses the default bucket.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageBucket(@Nullable Storage.StorageBucket value) {
                if (value == null) values.remove("storageBucket");
                else values.put("storageBucket", jsonValue(value));
                return this;
            }
            public RequestCacheNamesParams build() {
                return new RequestCacheNamesParams(values);
            }
        }
    }
    /**
     * Requests cache names.
     */
    public static final class RequestCacheNamesResult extends CdpObject {
        private RequestCacheNamesResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestCacheNamesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestCacheNamesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Caches for the security origin.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CacheStorage.Cache> caches() {
            return list(value("caches"), element0 -> CacheStorage.Cache.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Caches for the security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder caches(@Nullable java.util.List<CacheStorage.Cache> value) {
                if (value == null) values.remove("caches");
                else values.put("caches", jsonValue(value));
                return this;
            }
            public RequestCacheNamesResult build() {
                if (!values.containsKey("caches")) throw new IllegalStateException("Missing required CDP field: caches");
                return new RequestCacheNamesResult(values);
            }
        }
    }
    /**
     * Fetches cache entry.
     */
    public static final class RequestCachedResponseParams extends CdpObject {
        private RequestCachedResponseParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestCachedResponseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestCachedResponseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of cache that contains the entry.
         * @return the protocol field value
         */
        @Nullable public String cacheId() {
            return (String) value("cacheId");
        }
        /**
         * URL spec of the request.
         * @return the protocol field value
         */
        @Nullable public String requestURL() {
            return (String) value("requestURL");
        }
        /**
         * headers of the request.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CacheStorage.Header> requestHeaders() {
            return list(value("requestHeaders"), element0 -> CacheStorage.Header.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of cache that contains the entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheId(@Nullable String value) {
                if (value == null) values.remove("cacheId");
                else values.put("cacheId", jsonValue(value));
                return this;
            }
            /**
             * URL spec of the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestURL(@Nullable String value) {
                if (value == null) values.remove("requestURL");
                else values.put("requestURL", jsonValue(value));
                return this;
            }
            /**
             * headers of the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestHeaders(@Nullable java.util.List<CacheStorage.Header> value) {
                if (value == null) values.remove("requestHeaders");
                else values.put("requestHeaders", jsonValue(value));
                return this;
            }
            public RequestCachedResponseParams build() {
                if (!values.containsKey("cacheId")) throw new IllegalStateException("Missing required CDP field: cacheId");
                if (!values.containsKey("requestURL")) throw new IllegalStateException("Missing required CDP field: requestURL");
                if (!values.containsKey("requestHeaders")) throw new IllegalStateException("Missing required CDP field: requestHeaders");
                return new RequestCachedResponseParams(values);
            }
        }
    }
    /**
     * Fetches cache entry.
     */
    public static final class RequestCachedResponseResult extends CdpObject {
        private RequestCachedResponseResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestCachedResponseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestCachedResponseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Response read from the cache.
         * @return the protocol field value
         */
        @Nullable public CacheStorage.CachedResponse response() {
            return CacheStorage.CachedResponse.fromMap(objectMap(value("response")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Response read from the cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable CacheStorage.CachedResponse value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            public RequestCachedResponseResult build() {
                if (!values.containsKey("response")) throw new IllegalStateException("Missing required CDP field: response");
                return new RequestCachedResponseResult(values);
            }
        }
    }
    /**
     * Requests data from cache.
     */
    public static final class RequestEntriesParams extends CdpObject {
        private RequestEntriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestEntriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestEntriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of cache to get entries from.
         * @return the protocol field value
         */
        @Nullable public String cacheId() {
            return (String) value("cacheId");
        }
        /**
         * Number of records to skip.
         * @return the protocol field value
         */
        @Nullable public Long skipCount() {
            return numberAsLong(value("skipCount"));
        }
        /**
         * Number of records to fetch.
         * @return the protocol field value
         */
        @Nullable public Long pageSize() {
            return numberAsLong(value("pageSize"));
        }
        /**
         * If present, only return the entries containing this substring in the path
         * @return the protocol field value
         */
        @Nullable public String pathFilter() {
            return (String) value("pathFilter");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of cache to get entries from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheId(@Nullable String value) {
                if (value == null) values.remove("cacheId");
                else values.put("cacheId", jsonValue(value));
                return this;
            }
            /**
             * Number of records to skip.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder skipCount(@Nullable Long value) {
                if (value == null) values.remove("skipCount");
                else values.put("skipCount", jsonValue(value));
                return this;
            }
            /**
             * Number of records to fetch.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageSize(@Nullable Long value) {
                if (value == null) values.remove("pageSize");
                else values.put("pageSize", jsonValue(value));
                return this;
            }
            /**
             * If present, only return the entries containing this substring in the path
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pathFilter(@Nullable String value) {
                if (value == null) values.remove("pathFilter");
                else values.put("pathFilter", jsonValue(value));
                return this;
            }
            public RequestEntriesParams build() {
                if (!values.containsKey("cacheId")) throw new IllegalStateException("Missing required CDP field: cacheId");
                return new RequestEntriesParams(values);
            }
        }
    }
    /**
     * Requests data from cache.
     */
    public static final class RequestEntriesResult extends CdpObject {
        private RequestEntriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestEntriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestEntriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of object store data entries.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CacheStorage.DataEntry> cacheDataEntries() {
            return list(value("cacheDataEntries"), element0 -> CacheStorage.DataEntry.fromMap(objectMap(element0)));
        }
        /**
         * Count of returned entries from this storage. If pathFilter is empty, it is the count of all entries from this storage.
         * @return the protocol field value
         */
        @Nullable public Double returnCount() {
            return numberAsDouble(value("returnCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of object store data entries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheDataEntries(@Nullable java.util.List<CacheStorage.DataEntry> value) {
                if (value == null) values.remove("cacheDataEntries");
                else values.put("cacheDataEntries", jsonValue(value));
                return this;
            }
            /**
             * Count of returned entries from this storage. If pathFilter is empty, it is the count of all entries from this storage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnCount(@Nullable Double value) {
                if (value == null) values.remove("returnCount");
                else values.put("returnCount", jsonValue(value));
                return this;
            }
            public RequestEntriesResult build() {
                if (!values.containsKey("cacheDataEntries")) throw new IllegalStateException("Missing required CDP field: cacheDataEntries");
                if (!values.containsKey("returnCount")) throw new IllegalStateException("Missing required CDP field: returnCount");
                return new RequestEntriesResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Deletes a cache.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteCacheResult> deleteCache(DeleteCacheParams params) {
            return client.call("CacheStorage.deleteCache", params, DeleteCacheResult::fromMap);
        }
        /**
         * Deletes a cache entry.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteEntryResult> deleteEntry(DeleteEntryParams params) {
            return client.call("CacheStorage.deleteEntry", params, DeleteEntryResult::fromMap);
        }
        /**
         * Requests cache names.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestCacheNamesResult> requestCacheNames(RequestCacheNamesParams params) {
            return client.call("CacheStorage.requestCacheNames", params, RequestCacheNamesResult::fromMap);
        }
        /**
         * Fetches cache entry.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestCachedResponseResult> requestCachedResponse(RequestCachedResponseParams params) {
            return client.call("CacheStorage.requestCachedResponse", params, RequestCachedResponseResult::fromMap);
        }
        /**
         * Requests data from cache.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestEntriesResult> requestEntries(RequestEntriesParams params) {
            return client.call("CacheStorage.requestEntries", params, RequestEntriesResult::fromMap);
        }
    }
}
