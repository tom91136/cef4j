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
 * Chrome DevTools Protocol Storage domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Storage.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Storage {
    private Storage() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Enum of possible storage types.
     */
    public static final class StorageType {
        private StorageType() {}
        public static final String COOKIES = "cookies";
        public static final String FILE_SYSTEMS = "file_systems";
        public static final String INDEXEDDB = "indexeddb";
        public static final String LOCAL_STORAGE = "local_storage";
        public static final String SHADER_CACHE = "shader_cache";
        public static final String WEBSQL = "websql";
        public static final String SERVICE_WORKERS = "service_workers";
        public static final String CACHE_STORAGE = "cache_storage";
        public static final String INTEREST_GROUPS = "interest_groups";
        public static final String SHARED_STORAGE = "shared_storage";
        public static final String STORAGE_BUCKETS = "storage_buckets";
        public static final String ALL = "all";
        public static final String OTHER = "other";
    }
    /**
     * Usage for a storage type.
     */
    public static final class UsageForType extends CdpObject {
        private UsageForType(Map<String, Object> values) { super(values); }
        @Nullable public static UsageForType fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UsageForType(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of storage type.
         * @return the protocol field value
         */
        @Nullable public String storageType() {
            return (String) value("storageType");
        }
        /**
         * Storage usage (bytes).
         * @return the protocol field value
         */
        @Nullable public Double usage() {
            return numberAsDouble(value("usage"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of storage type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageType(@Nullable String value) {
                if (value == null) values.remove("storageType");
                else values.put("storageType", jsonValue(value));
                return this;
            }
            /**
             * Storage usage (bytes).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder usage(@Nullable Double value) {
                if (value == null) values.remove("usage");
                else values.put("usage", jsonValue(value));
                return this;
            }
            public UsageForType build() {
                if (!values.containsKey("storageType")) throw new IllegalStateException("Missing required CDP field: storageType");
                if (!values.containsKey("usage")) throw new IllegalStateException("Missing required CDP field: usage");
                return new UsageForType(values);
            }
        }
    }
    /**
     * Pair of issuer origin and number of available (signed, but not used) Trust Tokens from that issuer.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokens extends CdpObject {
        private TrustTokens(Map<String, Object> values) { super(values); }
        @Nullable public static TrustTokens fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrustTokens(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the issuerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String issuerOrigin() {
            return (String) value("issuerOrigin");
        }
        /**
         * Returns the count field.
         * @return the protocol field value
         */
        @Nullable public Double count() {
            return numberAsDouble(value("count"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the issuerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuerOrigin(@Nullable String value) {
                if (value == null) values.remove("issuerOrigin");
                else values.put("issuerOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the count field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder count(@Nullable Double value) {
                if (value == null) values.remove("count");
                else values.put("count", jsonValue(value));
                return this;
            }
            public TrustTokens build() {
                if (!values.containsKey("issuerOrigin")) throw new IllegalStateException("Missing required CDP field: issuerOrigin");
                if (!values.containsKey("count")) throw new IllegalStateException("Missing required CDP field: count");
                return new TrustTokens(values);
            }
        }
    }
    /**
     * Enum of interest group access types.
     */
    public static final class InterestGroupAccessType {
        private InterestGroupAccessType() {}
        public static final String JOIN = "join";
        public static final String LEAVE = "leave";
        public static final String UPDATE = "update";
        public static final String LOADED = "loaded";
        public static final String BID = "bid";
        public static final String WIN = "win";
        public static final String ADDITIONALBID = "additionalBid";
        public static final String ADDITIONALBIDWIN = "additionalBidWin";
        public static final String TOPLEVELBID = "topLevelBid";
        public static final String TOPLEVELADDITIONALBID = "topLevelAdditionalBid";
        public static final String CLEAR = "clear";
    }
    /**
     * Enum of auction events.
     */
    public static final class InterestGroupAuctionEventType {
        private InterestGroupAuctionEventType() {}
        public static final String STARTED = "started";
        public static final String CONFIGRESOLVED = "configResolved";
    }
    /**
     * Enum of network fetches auctions can do.
     */
    public static final class InterestGroupAuctionFetchType {
        private InterestGroupAuctionFetchType() {}
        public static final String BIDDERJS = "bidderJs";
        public static final String BIDDERWASM = "bidderWasm";
        public static final String SELLERJS = "sellerJs";
        public static final String BIDDERTRUSTEDSIGNALS = "bidderTrustedSignals";
        public static final String SELLERTRUSTEDSIGNALS = "sellerTrustedSignals";
    }
    /**
     * Enum of shared storage access scopes.
     */
    public static final class SharedStorageAccessScope {
        private SharedStorageAccessScope() {}
        public static final String WINDOW = "window";
        public static final String SHAREDSTORAGEWORKLET = "sharedStorageWorklet";
        public static final String PROTECTEDAUDIENCEWORKLET = "protectedAudienceWorklet";
        public static final String HEADER = "header";
    }
    /**
     * Enum of shared storage access methods.
     */
    public static final class SharedStorageAccessMethod {
        private SharedStorageAccessMethod() {}
        public static final String ADDMODULE = "addModule";
        public static final String CREATEWORKLET = "createWorklet";
        public static final String SELECTURL = "selectURL";
        public static final String RUN = "run";
        public static final String BATCHUPDATE = "batchUpdate";
        public static final String SET = "set";
        public static final String APPEND = "append";
        public static final String DELETE = "delete";
        public static final String CLEAR = "clear";
        public static final String GET = "get";
        public static final String KEYS = "keys";
        public static final String VALUES = "values";
        public static final String ENTRIES = "entries";
        public static final String LENGTH = "length";
        public static final String REMAININGBUDGET = "remainingBudget";
    }
    /**
     * Struct for a single key-value pair in an origin&#x27;s shared storage.
     */
    public static final class SharedStorageEntry extends CdpObject {
        private SharedStorageEntry(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
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
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
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
            public SharedStorageEntry build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SharedStorageEntry(values);
            }
        }
    }
    /**
     * Details for an origin&#x27;s shared storage.
     */
    public static final class SharedStorageMetadata extends CdpObject {
        private SharedStorageMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Time when the origin&#x27;s shared storage was last created.
         * @return the protocol field value
         */
        @Nullable public Double creationTime() {
            return numberAsDouble(value("creationTime"));
        }
        /**
         * Number of key-value pairs stored in origin&#x27;s shared storage.
         * @return the protocol field value
         */
        @Nullable public Long length() {
            return numberAsLong(value("length"));
        }
        /**
         * Current amount of bits of entropy remaining in the navigation budget.
         * @return the protocol field value
         */
        @Nullable public Double remainingBudget() {
            return numberAsDouble(value("remainingBudget"));
        }
        /**
         * Total number of bytes stored as key-value pairs in origin&#x27;s shared storage.
         * @return the protocol field value
         */
        @Nullable public Long bytesUsed() {
            return numberAsLong(value("bytesUsed"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Time when the origin&#x27;s shared storage was last created.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder creationTime(@Nullable Double value) {
                if (value == null) values.remove("creationTime");
                else values.put("creationTime", jsonValue(value));
                return this;
            }
            /**
             * Number of key-value pairs stored in origin&#x27;s shared storage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder length(@Nullable Long value) {
                if (value == null) values.remove("length");
                else values.put("length", jsonValue(value));
                return this;
            }
            /**
             * Current amount of bits of entropy remaining in the navigation budget.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remainingBudget(@Nullable Double value) {
                if (value == null) values.remove("remainingBudget");
                else values.put("remainingBudget", jsonValue(value));
                return this;
            }
            /**
             * Total number of bytes stored as key-value pairs in origin&#x27;s shared storage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bytesUsed(@Nullable Long value) {
                if (value == null) values.remove("bytesUsed");
                else values.put("bytesUsed", jsonValue(value));
                return this;
            }
            public SharedStorageMetadata build() {
                if (!values.containsKey("creationTime")) throw new IllegalStateException("Missing required CDP field: creationTime");
                if (!values.containsKey("length")) throw new IllegalStateException("Missing required CDP field: length");
                if (!values.containsKey("remainingBudget")) throw new IllegalStateException("Missing required CDP field: remainingBudget");
                if (!values.containsKey("bytesUsed")) throw new IllegalStateException("Missing required CDP field: bytesUsed");
                return new SharedStorageMetadata(values);
            }
        }
    }
    /**
     * Represents a dictionary object passed in as privateAggregationConfig to run or selectURL.
     */
    public static final class SharedStoragePrivateAggregationConfig extends CdpObject {
        private SharedStoragePrivateAggregationConfig(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStoragePrivateAggregationConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStoragePrivateAggregationConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The chosen aggregation service deployment.
         * @return the protocol field value
         */
        @Nullable public String aggregationCoordinatorOrigin() {
            return (String) value("aggregationCoordinatorOrigin");
        }
        /**
         * The context ID provided.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Configures the maximum size allowed for filtering IDs.
         * @return the protocol field value
         */
        @Nullable public Long filteringIdMaxBytes() {
            return numberAsLong(value("filteringIdMaxBytes"));
        }
        /**
         * The limit on the number of contributions in the final report.
         * @return the protocol field value
         */
        @Nullable public Long maxContributions() {
            return numberAsLong(value("maxContributions"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The chosen aggregation service deployment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregationCoordinatorOrigin(@Nullable String value) {
                if (value == null) values.remove("aggregationCoordinatorOrigin");
                else values.put("aggregationCoordinatorOrigin", jsonValue(value));
                return this;
            }
            /**
             * The context ID provided.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Configures the maximum size allowed for filtering IDs.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filteringIdMaxBytes(@Nullable Long value) {
                if (value == null) values.remove("filteringIdMaxBytes");
                else values.put("filteringIdMaxBytes", jsonValue(value));
                return this;
            }
            /**
             * The limit on the number of contributions in the final report.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxContributions(@Nullable Long value) {
                if (value == null) values.remove("maxContributions");
                else values.put("maxContributions", jsonValue(value));
                return this;
            }
            public SharedStoragePrivateAggregationConfig build() {
                if (!values.containsKey("filteringIdMaxBytes")) throw new IllegalStateException("Missing required CDP field: filteringIdMaxBytes");
                return new SharedStoragePrivateAggregationConfig(values);
            }
        }
    }
    /**
     * Pair of reporting metadata details for a candidate URL for {@code selectURL()}.
     */
    public static final class SharedStorageReportingMetadata extends CdpObject {
        private SharedStorageReportingMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageReportingMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageReportingMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the eventType field.
         * @return the protocol field value
         */
        @Nullable public String eventType() {
            return (String) value("eventType");
        }
        /**
         * Returns the reportingUrl field.
         * @return the protocol field value
         */
        @Nullable public String reportingUrl() {
            return (String) value("reportingUrl");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the eventType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventType(@Nullable String value) {
                if (value == null) values.remove("eventType");
                else values.put("eventType", jsonValue(value));
                return this;
            }
            /**
             * Sets the reportingUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportingUrl(@Nullable String value) {
                if (value == null) values.remove("reportingUrl");
                else values.put("reportingUrl", jsonValue(value));
                return this;
            }
            public SharedStorageReportingMetadata build() {
                if (!values.containsKey("eventType")) throw new IllegalStateException("Missing required CDP field: eventType");
                if (!values.containsKey("reportingUrl")) throw new IllegalStateException("Missing required CDP field: reportingUrl");
                return new SharedStorageReportingMetadata(values);
            }
        }
    }
    /**
     * Bundles a candidate URL with its reporting metadata.
     */
    public static final class SharedStorageUrlWithMetadata extends CdpObject {
        private SharedStorageUrlWithMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageUrlWithMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageUrlWithMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Spec of candidate URL.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Any associated reporting metadata.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.SharedStorageReportingMetadata> reportingMetadata() {
            return list(value("reportingMetadata"), element0 -> Storage.SharedStorageReportingMetadata.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Spec of candidate URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Any associated reporting metadata.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportingMetadata(@Nullable java.util.List<Storage.SharedStorageReportingMetadata> value) {
                if (value == null) values.remove("reportingMetadata");
                else values.put("reportingMetadata", jsonValue(value));
                return this;
            }
            public SharedStorageUrlWithMetadata build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("reportingMetadata")) throw new IllegalStateException("Missing required CDP field: reportingMetadata");
                return new SharedStorageUrlWithMetadata(values);
            }
        }
    }
    /**
     * Bundles the parameters for shared storage access events whose presence/absence can vary according to SharedStorageAccessType.
     */
    public static final class SharedStorageAccessParams extends CdpObject {
        private SharedStorageAccessParams(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageAccessParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageAccessParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Spec of the module script URL. Present only for SharedStorageAccessMethods: addModule and createWorklet.
         * @return the protocol field value
         */
        @Nullable public String scriptSourceUrl() {
            return (String) value("scriptSourceUrl");
        }
        /**
         * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
         * @return the protocol field value
         */
        @Nullable public String dataOrigin() {
            return (String) value("dataOrigin");
        }
        /**
         * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value
         */
        @Nullable public String operationName() {
            return (String) value("operationName");
        }
        /**
         * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value
         */
        @Nullable public String operationId() {
            return (String) value("operationId");
        }
        /**
         * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value
         */
        @Nullable public Boolean keepAlive() {
            return (Boolean) value("keepAlive");
        }
        /**
         * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value
         */
        @Nullable public Storage.SharedStoragePrivateAggregationConfig privateAggregationConfig() {
            return Storage.SharedStoragePrivateAggregationConfig.fromMap(objectMap(value("privateAggregationConfig")));
        }
        /**
         * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
         * @return the protocol field value
         */
        @Nullable public String serializedData() {
            return (String) value("serializedData");
        }
        /**
         * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.SharedStorageUrlWithMetadata> urlsWithMetadata() {
            return list(value("urlsWithMetadata"), element0 -> Storage.SharedStorageUrlWithMetadata.fromMap(objectMap(element0)));
        }
        /**
         * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
         * @return the protocol field value
         */
        @Nullable public String urnUuid() {
            return (String) value("urnUuid");
        }
        /**
         * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
         * @return the protocol field value
         */
        @Nullable public Boolean ignoreIfPresent() {
            return (Boolean) value("ignoreIfPresent");
        }
        /**
         * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
         * @return the protocol field value
         */
        @Nullable public Long workletOrdinal() {
            return numberAsLong(value("workletOrdinal"));
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
         * @return the protocol field value
         */
        @Nullable public String workletTargetId() {
            return (String) value("workletTargetId");
        }
        /**
         * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
         * @return the protocol field value
         */
        @Nullable public String withLock() {
            return (String) value("withLock");
        }
        /**
         * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
         * @return the protocol field value
         */
        @Nullable public String batchUpdateId() {
            return (String) value("batchUpdateId");
        }
        /**
         * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
         * @return the protocol field value
         */
        @Nullable public Long batchSize() {
            return numberAsLong(value("batchSize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Spec of the module script URL. Present only for SharedStorageAccessMethods: addModule and createWorklet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptSourceUrl(@Nullable String value) {
                if (value == null) values.remove("scriptSourceUrl");
                else values.put("scriptSourceUrl", jsonValue(value));
                return this;
            }
            /**
             * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dataOrigin(@Nullable String value) {
                if (value == null) values.remove("dataOrigin");
                else values.put("dataOrigin", jsonValue(value));
                return this;
            }
            /**
             * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder operationName(@Nullable String value) {
                if (value == null) values.remove("operationName");
                else values.put("operationName", jsonValue(value));
                return this;
            }
            /**
             * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder operationId(@Nullable String value) {
                if (value == null) values.remove("operationId");
                else values.put("operationId", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keepAlive(@Nullable Boolean value) {
                if (value == null) values.remove("keepAlive");
                else values.put("keepAlive", jsonValue(value));
                return this;
            }
            /**
             * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder privateAggregationConfig(@Nullable Storage.SharedStoragePrivateAggregationConfig value) {
                if (value == null) values.remove("privateAggregationConfig");
                else values.put("privateAggregationConfig", jsonValue(value));
                return this;
            }
            /**
             * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serializedData(@Nullable String value) {
                if (value == null) values.remove("serializedData");
                else values.put("serializedData", jsonValue(value));
                return this;
            }
            /**
             * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlsWithMetadata(@Nullable java.util.List<Storage.SharedStorageUrlWithMetadata> value) {
                if (value == null) values.remove("urlsWithMetadata");
                else values.put("urlsWithMetadata", jsonValue(value));
                return this;
            }
            /**
             * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urnUuid(@Nullable String value) {
                if (value == null) values.remove("urnUuid");
                else values.put("urnUuid", jsonValue(value));
                return this;
            }
            /**
             * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignoreIfPresent(@Nullable Boolean value) {
                if (value == null) values.remove("ignoreIfPresent");
                else values.put("ignoreIfPresent", jsonValue(value));
                return this;
            }
            /**
             * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workletOrdinal(@Nullable Long value) {
                if (value == null) values.remove("workletOrdinal");
                else values.put("workletOrdinal", jsonValue(value));
                return this;
            }
            /**
             * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workletTargetId(@Nullable String value) {
                if (value == null) values.remove("workletTargetId");
                else values.put("workletTargetId", jsonValue(value));
                return this;
            }
            /**
             * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder withLock(@Nullable String value) {
                if (value == null) values.remove("withLock");
                else values.put("withLock", jsonValue(value));
                return this;
            }
            /**
             * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder batchUpdateId(@Nullable String value) {
                if (value == null) values.remove("batchUpdateId");
                else values.put("batchUpdateId", jsonValue(value));
                return this;
            }
            /**
             * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder batchSize(@Nullable Long value) {
                if (value == null) values.remove("batchSize");
                else values.put("batchSize", jsonValue(value));
                return this;
            }
            public SharedStorageAccessParams build() {
                return new SharedStorageAccessParams(values);
            }
        }
    }
    /**
     * Wire values for StorageBucketsDurability.
     */
    public static final class StorageBucketsDurability {
        private StorageBucketsDurability() {}
        public static final String RELAXED = "relaxed";
        public static final String STRICT = "strict";
    }
    /**
     */
    public static final class StorageBucket extends CdpObject {
        private StorageBucket(Map<String, Object> values) { super(values); }
        @Nullable public static StorageBucket fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StorageBucket(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * If not specified, it is the default bucket of the storageKey.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * If not specified, it is the default bucket of the storageKey.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public StorageBucket build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new StorageBucket(values);
            }
        }
    }
    /**
     */
    public static final class StorageBucketInfo extends CdpObject {
        private StorageBucketInfo(Map<String, Object> values) { super(values); }
        @Nullable public static StorageBucketInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StorageBucketInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the bucket field.
         * @return the protocol field value
         */
        @Nullable public Storage.StorageBucket bucket() {
            return Storage.StorageBucket.fromMap(objectMap(value("bucket")));
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Returns the expiration field.
         * @return the protocol field value
         */
        @Nullable public Double expiration() {
            return numberAsDouble(value("expiration"));
        }
        /**
         * Storage quota (bytes).
         * @return the protocol field value
         */
        @Nullable public Double quota() {
            return numberAsDouble(value("quota"));
        }
        /**
         * Returns the persistent field.
         * @return the protocol field value
         */
        @Nullable public Boolean persistent() {
            return (Boolean) value("persistent");
        }
        /**
         * Returns the durability field.
         * @return the protocol field value
         */
        @Nullable public String durability() {
            return (String) value("durability");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the bucket field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucket(@Nullable Storage.StorageBucket value) {
                if (value == null) values.remove("bucket");
                else values.put("bucket", jsonValue(value));
                return this;
            }
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
             * Sets the expiration field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expiration(@Nullable Double value) {
                if (value == null) values.remove("expiration");
                else values.put("expiration", jsonValue(value));
                return this;
            }
            /**
             * Storage quota (bytes).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quota(@Nullable Double value) {
                if (value == null) values.remove("quota");
                else values.put("quota", jsonValue(value));
                return this;
            }
            /**
             * Sets the persistent field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder persistent(@Nullable Boolean value) {
                if (value == null) values.remove("persistent");
                else values.put("persistent", jsonValue(value));
                return this;
            }
            /**
             * Sets the durability field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder durability(@Nullable String value) {
                if (value == null) values.remove("durability");
                else values.put("durability", jsonValue(value));
                return this;
            }
            public StorageBucketInfo build() {
                if (!values.containsKey("bucket")) throw new IllegalStateException("Missing required CDP field: bucket");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("expiration")) throw new IllegalStateException("Missing required CDP field: expiration");
                if (!values.containsKey("quota")) throw new IllegalStateException("Missing required CDP field: quota");
                if (!values.containsKey("persistent")) throw new IllegalStateException("Missing required CDP field: persistent");
                if (!values.containsKey("durability")) throw new IllegalStateException("Missing required CDP field: durability");
                return new StorageBucketInfo(values);
            }
        }
    }
    /**
     * Wire values for AttributionReportingSourceType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingSourceType {
        private AttributionReportingSourceType() {}
        public static final String NAVIGATION = "navigation";
        public static final String EVENT = "event";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingFilterDataEntry extends CdpObject {
        private AttributionReportingFilterDataEntry(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingFilterDataEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingFilterDataEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Returns the values field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> values() {
            return list(value("values"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Sets the values field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder values(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("values");
                else values.put("values", jsonValue(value));
                return this;
            }
            public AttributionReportingFilterDataEntry build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("values")) throw new IllegalStateException("Missing required CDP field: values");
                return new AttributionReportingFilterDataEntry(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingFilterConfig extends CdpObject {
        private AttributionReportingFilterConfig(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingFilterConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingFilterConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the filterValues field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingFilterDataEntry> filterValues() {
            return list(value("filterValues"), element0 -> Storage.AttributionReportingFilterDataEntry.fromMap(objectMap(element0)));
        }
        /**
         * duration in seconds
         * @return the protocol field value
         */
        @Nullable public Long lookbackWindow() {
            return numberAsLong(value("lookbackWindow"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the filterValues field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filterValues(@Nullable java.util.List<Storage.AttributionReportingFilterDataEntry> value) {
                if (value == null) values.remove("filterValues");
                else values.put("filterValues", jsonValue(value));
                return this;
            }
            /**
             * duration in seconds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lookbackWindow(@Nullable Long value) {
                if (value == null) values.remove("lookbackWindow");
                else values.put("lookbackWindow", jsonValue(value));
                return this;
            }
            public AttributionReportingFilterConfig build() {
                if (!values.containsKey("filterValues")) throw new IllegalStateException("Missing required CDP field: filterValues");
                return new AttributionReportingFilterConfig(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingFilterPair extends CdpObject {
        private AttributionReportingFilterPair(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingFilterPair fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingFilterPair(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingFilterConfig> filters() {
            return list(value("filters"), element0 -> Storage.AttributionReportingFilterConfig.fromMap(objectMap(element0)));
        }
        /**
         * Returns the notFilters field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingFilterConfig> notFilters() {
            return list(value("notFilters"), element0 -> Storage.AttributionReportingFilterConfig.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable java.util.List<Storage.AttributionReportingFilterConfig> value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            /**
             * Sets the notFilters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder notFilters(@Nullable java.util.List<Storage.AttributionReportingFilterConfig> value) {
                if (value == null) values.remove("notFilters");
                else values.put("notFilters", jsonValue(value));
                return this;
            }
            public AttributionReportingFilterPair build() {
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                if (!values.containsKey("notFilters")) throw new IllegalStateException("Missing required CDP field: notFilters");
                return new AttributionReportingFilterPair(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregationKeysEntry extends CdpObject {
        private AttributionReportingAggregationKeysEntry(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregationKeysEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregationKeysEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
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
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
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
            public AttributionReportingAggregationKeysEntry build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new AttributionReportingAggregationKeysEntry(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingEventReportWindows extends CdpObject {
        private AttributionReportingEventReportWindows(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingEventReportWindows fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingEventReportWindows(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * duration in seconds
         * @return the protocol field value
         */
        @Nullable public Long start() {
            return numberAsLong(value("start"));
        }
        /**
         * duration in seconds
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> ends() {
            return list(value("ends"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * duration in seconds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder start(@Nullable Long value) {
                if (value == null) values.remove("start");
                else values.put("start", jsonValue(value));
                return this;
            }
            /**
             * duration in seconds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ends(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("ends");
                else values.put("ends", jsonValue(value));
                return this;
            }
            public AttributionReportingEventReportWindows build() {
                if (!values.containsKey("start")) throw new IllegalStateException("Missing required CDP field: start");
                if (!values.containsKey("ends")) throw new IllegalStateException("Missing required CDP field: ends");
                return new AttributionReportingEventReportWindows(values);
            }
        }
    }
    /**
     * Wire values for AttributionReportingTriggerDataMatching.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingTriggerDataMatching {
        private AttributionReportingTriggerDataMatching() {}
        public static final String EXACT = "exact";
        public static final String MODULUS = "modulus";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableDebugReportingData extends CdpObject {
        private AttributionReportingAggregatableDebugReportingData(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregatableDebugReportingData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregatableDebugReportingData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the keyPiece field.
         * @return the protocol field value
         */
        @Nullable public String keyPiece() {
            return (String) value("keyPiece");
        }
        /**
         * number instead of integer because not all uint32 can be represented by int
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        /**
         * Returns the types field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> types() {
            return list(value("types"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the keyPiece field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyPiece(@Nullable String value) {
                if (value == null) values.remove("keyPiece");
                else values.put("keyPiece", jsonValue(value));
                return this;
            }
            /**
             * number instead of integer because not all uint32 can be represented by int
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Sets the types field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder types(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("types");
                else values.put("types", jsonValue(value));
                return this;
            }
            public AttributionReportingAggregatableDebugReportingData build() {
                if (!values.containsKey("keyPiece")) throw new IllegalStateException("Missing required CDP field: keyPiece");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("types")) throw new IllegalStateException("Missing required CDP field: types");
                return new AttributionReportingAggregatableDebugReportingData(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableDebugReportingConfig extends CdpObject {
        private AttributionReportingAggregatableDebugReportingConfig(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregatableDebugReportingConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregatableDebugReportingConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * number instead of integer because not all uint32 can be represented by int, only present for source registrations
         * @return the protocol field value
         */
        @Nullable public Double budget() {
            return numberAsDouble(value("budget"));
        }
        /**
         * Returns the keyPiece field.
         * @return the protocol field value
         */
        @Nullable public String keyPiece() {
            return (String) value("keyPiece");
        }
        /**
         * Returns the debugData field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingAggregatableDebugReportingData> debugData() {
            return list(value("debugData"), element0 -> Storage.AttributionReportingAggregatableDebugReportingData.fromMap(objectMap(element0)));
        }
        /**
         * Returns the aggregationCoordinatorOrigin field.
         * @return the protocol field value
         */
        @Nullable public String aggregationCoordinatorOrigin() {
            return (String) value("aggregationCoordinatorOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * number instead of integer because not all uint32 can be represented by int, only present for source registrations
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder budget(@Nullable Double value) {
                if (value == null) values.remove("budget");
                else values.put("budget", jsonValue(value));
                return this;
            }
            /**
             * Sets the keyPiece field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyPiece(@Nullable String value) {
                if (value == null) values.remove("keyPiece");
                else values.put("keyPiece", jsonValue(value));
                return this;
            }
            /**
             * Sets the debugData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debugData(@Nullable java.util.List<Storage.AttributionReportingAggregatableDebugReportingData> value) {
                if (value == null) values.remove("debugData");
                else values.put("debugData", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregationCoordinatorOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregationCoordinatorOrigin(@Nullable String value) {
                if (value == null) values.remove("aggregationCoordinatorOrigin");
                else values.put("aggregationCoordinatorOrigin", jsonValue(value));
                return this;
            }
            public AttributionReportingAggregatableDebugReportingConfig build() {
                if (!values.containsKey("keyPiece")) throw new IllegalStateException("Missing required CDP field: keyPiece");
                if (!values.containsKey("debugData")) throw new IllegalStateException("Missing required CDP field: debugData");
                return new AttributionReportingAggregatableDebugReportingConfig(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionScopesData extends CdpObject {
        private AttributionScopesData(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionScopesData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionScopesData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the values field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> values() {
            return list(value("values"), element0 -> (String) element0);
        }
        /**
         * number instead of integer because not all uint32 can be represented by int
         * @return the protocol field value
         */
        @Nullable public Double limit() {
            return numberAsDouble(value("limit"));
        }
        /**
         * Returns the maxEventStates field.
         * @return the protocol field value
         */
        @Nullable public Double maxEventStates() {
            return numberAsDouble(value("maxEventStates"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the values field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder values(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("values");
                else values.put("values", jsonValue(value));
                return this;
            }
            /**
             * number instead of integer because not all uint32 can be represented by int
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder limit(@Nullable Double value) {
                if (value == null) values.remove("limit");
                else values.put("limit", jsonValue(value));
                return this;
            }
            /**
             * Sets the maxEventStates field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxEventStates(@Nullable Double value) {
                if (value == null) values.remove("maxEventStates");
                else values.put("maxEventStates", jsonValue(value));
                return this;
            }
            public AttributionScopesData build() {
                if (!values.containsKey("values")) throw new IllegalStateException("Missing required CDP field: values");
                if (!values.containsKey("limit")) throw new IllegalStateException("Missing required CDP field: limit");
                if (!values.containsKey("maxEventStates")) throw new IllegalStateException("Missing required CDP field: maxEventStates");
                return new AttributionScopesData(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingNamedBudgetDef extends CdpObject {
        private AttributionReportingNamedBudgetDef(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingNamedBudgetDef fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingNamedBudgetDef(values);
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
         * Returns the budget field.
         * @return the protocol field value
         */
        @Nullable public Long budget() {
            return numberAsLong(value("budget"));
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
             * Sets the budget field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder budget(@Nullable Long value) {
                if (value == null) values.remove("budget");
                else values.put("budget", jsonValue(value));
                return this;
            }
            public AttributionReportingNamedBudgetDef build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("budget")) throw new IllegalStateException("Missing required CDP field: budget");
                return new AttributionReportingNamedBudgetDef(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingSourceRegistration extends CdpObject {
        private AttributionReportingSourceRegistration(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingSourceRegistration fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingSourceRegistration(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the time field.
         * @return the protocol field value
         */
        @Nullable public Double time() {
            return numberAsDouble(value("time"));
        }
        /**
         * duration in seconds
         * @return the protocol field value
         */
        @Nullable public Long expiry() {
            return numberAsLong(value("expiry"));
        }
        /**
         * number instead of integer because not all uint32 can be represented by int
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> triggerData() {
            return list(value("triggerData"), element0 -> numberAsDouble(element0));
        }
        /**
         * Returns the eventReportWindows field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingEventReportWindows eventReportWindows() {
            return Storage.AttributionReportingEventReportWindows.fromMap(objectMap(value("eventReportWindows")));
        }
        /**
         * duration in seconds
         * @return the protocol field value
         */
        @Nullable public Long aggregatableReportWindow() {
            return numberAsLong(value("aggregatableReportWindow"));
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the sourceOrigin field.
         * @return the protocol field value
         */
        @Nullable public String sourceOrigin() {
            return (String) value("sourceOrigin");
        }
        /**
         * Returns the reportingOrigin field.
         * @return the protocol field value
         */
        @Nullable public String reportingOrigin() {
            return (String) value("reportingOrigin");
        }
        /**
         * Returns the destinationSites field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> destinationSites() {
            return list(value("destinationSites"), element0 -> (String) element0);
        }
        /**
         * Returns the eventId field.
         * @return the protocol field value
         */
        @Nullable public String eventId() {
            return (String) value("eventId");
        }
        /**
         * Returns the priority field.
         * @return the protocol field value
         */
        @Nullable public String priority() {
            return (String) value("priority");
        }
        /**
         * Returns the filterData field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingFilterDataEntry> filterData() {
            return list(value("filterData"), element0 -> Storage.AttributionReportingFilterDataEntry.fromMap(objectMap(element0)));
        }
        /**
         * Returns the aggregationKeys field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingAggregationKeysEntry> aggregationKeys() {
            return list(value("aggregationKeys"), element0 -> Storage.AttributionReportingAggregationKeysEntry.fromMap(objectMap(element0)));
        }
        /**
         * Returns the debugKey field.
         * @return the protocol field value
         */
        @Nullable public String debugKey() {
            return (String) value("debugKey");
        }
        /**
         * Returns the triggerDataMatching field.
         * @return the protocol field value
         */
        @Nullable public String triggerDataMatching() {
            return (String) value("triggerDataMatching");
        }
        /**
         * Returns the destinationLimitPriority field.
         * @return the protocol field value
         */
        @Nullable public String destinationLimitPriority() {
            return (String) value("destinationLimitPriority");
        }
        /**
         * Returns the aggregatableDebugReportingConfig field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingAggregatableDebugReportingConfig aggregatableDebugReportingConfig() {
            return Storage.AttributionReportingAggregatableDebugReportingConfig.fromMap(objectMap(value("aggregatableDebugReportingConfig")));
        }
        /**
         * Returns the scopesData field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionScopesData scopesData() {
            return Storage.AttributionScopesData.fromMap(objectMap(value("scopesData")));
        }
        /**
         * Returns the maxEventLevelReports field.
         * @return the protocol field value
         */
        @Nullable public Long maxEventLevelReports() {
            return numberAsLong(value("maxEventLevelReports"));
        }
        /**
         * Returns the namedBudgets field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingNamedBudgetDef> namedBudgets() {
            return list(value("namedBudgets"), element0 -> Storage.AttributionReportingNamedBudgetDef.fromMap(objectMap(element0)));
        }
        /**
         * Returns the debugReporting field.
         * @return the protocol field value
         */
        @Nullable public Boolean debugReporting() {
            return (Boolean) value("debugReporting");
        }
        /**
         * Returns the eventLevelEpsilon field.
         * @return the protocol field value
         */
        @Nullable public Double eventLevelEpsilon() {
            return numberAsDouble(value("eventLevelEpsilon"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the time field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder time(@Nullable Double value) {
                if (value == null) values.remove("time");
                else values.put("time", jsonValue(value));
                return this;
            }
            /**
             * duration in seconds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expiry(@Nullable Long value) {
                if (value == null) values.remove("expiry");
                else values.put("expiry", jsonValue(value));
                return this;
            }
            /**
             * number instead of integer because not all uint32 can be represented by int
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder triggerData(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("triggerData");
                else values.put("triggerData", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventReportWindows field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventReportWindows(@Nullable Storage.AttributionReportingEventReportWindows value) {
                if (value == null) values.remove("eventReportWindows");
                else values.put("eventReportWindows", jsonValue(value));
                return this;
            }
            /**
             * duration in seconds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableReportWindow(@Nullable Long value) {
                if (value == null) values.remove("aggregatableReportWindow");
                else values.put("aggregatableReportWindow", jsonValue(value));
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
             * Sets the sourceOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceOrigin(@Nullable String value) {
                if (value == null) values.remove("sourceOrigin");
                else values.put("sourceOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the reportingOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportingOrigin(@Nullable String value) {
                if (value == null) values.remove("reportingOrigin");
                else values.put("reportingOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationSites field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationSites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("destinationSites");
                else values.put("destinationSites", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventId(@Nullable String value) {
                if (value == null) values.remove("eventId");
                else values.put("eventId", jsonValue(value));
                return this;
            }
            /**
             * Sets the priority field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder priority(@Nullable String value) {
                if (value == null) values.remove("priority");
                else values.put("priority", jsonValue(value));
                return this;
            }
            /**
             * Sets the filterData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filterData(@Nullable java.util.List<Storage.AttributionReportingFilterDataEntry> value) {
                if (value == null) values.remove("filterData");
                else values.put("filterData", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregationKeys field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregationKeys(@Nullable java.util.List<Storage.AttributionReportingAggregationKeysEntry> value) {
                if (value == null) values.remove("aggregationKeys");
                else values.put("aggregationKeys", jsonValue(value));
                return this;
            }
            /**
             * Sets the debugKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debugKey(@Nullable String value) {
                if (value == null) values.remove("debugKey");
                else values.put("debugKey", jsonValue(value));
                return this;
            }
            /**
             * Sets the triggerDataMatching field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder triggerDataMatching(@Nullable String value) {
                if (value == null) values.remove("triggerDataMatching");
                else values.put("triggerDataMatching", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationLimitPriority field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationLimitPriority(@Nullable String value) {
                if (value == null) values.remove("destinationLimitPriority");
                else values.put("destinationLimitPriority", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatableDebugReportingConfig field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableDebugReportingConfig(@Nullable Storage.AttributionReportingAggregatableDebugReportingConfig value) {
                if (value == null) values.remove("aggregatableDebugReportingConfig");
                else values.put("aggregatableDebugReportingConfig", jsonValue(value));
                return this;
            }
            /**
             * Sets the scopesData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopesData(@Nullable Storage.AttributionScopesData value) {
                if (value == null) values.remove("scopesData");
                else values.put("scopesData", jsonValue(value));
                return this;
            }
            /**
             * Sets the maxEventLevelReports field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxEventLevelReports(@Nullable Long value) {
                if (value == null) values.remove("maxEventLevelReports");
                else values.put("maxEventLevelReports", jsonValue(value));
                return this;
            }
            /**
             * Sets the namedBudgets field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder namedBudgets(@Nullable java.util.List<Storage.AttributionReportingNamedBudgetDef> value) {
                if (value == null) values.remove("namedBudgets");
                else values.put("namedBudgets", jsonValue(value));
                return this;
            }
            /**
             * Sets the debugReporting field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debugReporting(@Nullable Boolean value) {
                if (value == null) values.remove("debugReporting");
                else values.put("debugReporting", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventLevelEpsilon field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventLevelEpsilon(@Nullable Double value) {
                if (value == null) values.remove("eventLevelEpsilon");
                else values.put("eventLevelEpsilon", jsonValue(value));
                return this;
            }
            public AttributionReportingSourceRegistration build() {
                if (!values.containsKey("time")) throw new IllegalStateException("Missing required CDP field: time");
                if (!values.containsKey("expiry")) throw new IllegalStateException("Missing required CDP field: expiry");
                if (!values.containsKey("triggerData")) throw new IllegalStateException("Missing required CDP field: triggerData");
                if (!values.containsKey("eventReportWindows")) throw new IllegalStateException("Missing required CDP field: eventReportWindows");
                if (!values.containsKey("aggregatableReportWindow")) throw new IllegalStateException("Missing required CDP field: aggregatableReportWindow");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("sourceOrigin")) throw new IllegalStateException("Missing required CDP field: sourceOrigin");
                if (!values.containsKey("reportingOrigin")) throw new IllegalStateException("Missing required CDP field: reportingOrigin");
                if (!values.containsKey("destinationSites")) throw new IllegalStateException("Missing required CDP field: destinationSites");
                if (!values.containsKey("eventId")) throw new IllegalStateException("Missing required CDP field: eventId");
                if (!values.containsKey("priority")) throw new IllegalStateException("Missing required CDP field: priority");
                if (!values.containsKey("filterData")) throw new IllegalStateException("Missing required CDP field: filterData");
                if (!values.containsKey("aggregationKeys")) throw new IllegalStateException("Missing required CDP field: aggregationKeys");
                if (!values.containsKey("triggerDataMatching")) throw new IllegalStateException("Missing required CDP field: triggerDataMatching");
                if (!values.containsKey("destinationLimitPriority")) throw new IllegalStateException("Missing required CDP field: destinationLimitPriority");
                if (!values.containsKey("aggregatableDebugReportingConfig")) throw new IllegalStateException("Missing required CDP field: aggregatableDebugReportingConfig");
                if (!values.containsKey("maxEventLevelReports")) throw new IllegalStateException("Missing required CDP field: maxEventLevelReports");
                if (!values.containsKey("namedBudgets")) throw new IllegalStateException("Missing required CDP field: namedBudgets");
                if (!values.containsKey("debugReporting")) throw new IllegalStateException("Missing required CDP field: debugReporting");
                if (!values.containsKey("eventLevelEpsilon")) throw new IllegalStateException("Missing required CDP field: eventLevelEpsilon");
                return new AttributionReportingSourceRegistration(values);
            }
        }
    }
    /**
     * Wire values for AttributionReportingSourceRegistrationResult.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingSourceRegistrationResult {
        private AttributionReportingSourceRegistrationResult() {}
        public static final String SUCCESS = "success";
        public static final String INTERNALERROR = "internalError";
        public static final String INSUFFICIENTSOURCECAPACITY = "insufficientSourceCapacity";
        public static final String INSUFFICIENTUNIQUEDESTINATIONCAPACITY = "insufficientUniqueDestinationCapacity";
        public static final String EXCESSIVEREPORTINGORIGINS = "excessiveReportingOrigins";
        public static final String PROHIBITEDBYBROWSERPOLICY = "prohibitedByBrowserPolicy";
        public static final String SUCCESSNOISED = "successNoised";
        public static final String DESTINATIONREPORTINGLIMITREACHED = "destinationReportingLimitReached";
        public static final String DESTINATIONGLOBALLIMITREACHED = "destinationGlobalLimitReached";
        public static final String DESTINATIONBOTHLIMITSREACHED = "destinationBothLimitsReached";
        public static final String REPORTINGORIGINSPERSITELIMITREACHED = "reportingOriginsPerSiteLimitReached";
        public static final String EXCEEDSMAXCHANNELCAPACITY = "exceedsMaxChannelCapacity";
        public static final String EXCEEDSMAXSCOPESCHANNELCAPACITY = "exceedsMaxScopesChannelCapacity";
        public static final String EXCEEDSMAXTRIGGERSTATECARDINALITY = "exceedsMaxTriggerStateCardinality";
        public static final String EXCEEDSMAXEVENTSTATESLIMIT = "exceedsMaxEventStatesLimit";
        public static final String DESTINATIONPERDAYREPORTINGLIMITREACHED = "destinationPerDayReportingLimitReached";
    }
    /**
     * Wire values for AttributionReportingSourceRegistrationTimeConfig.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingSourceRegistrationTimeConfig {
        private AttributionReportingSourceRegistrationTimeConfig() {}
        public static final String INCLUDE = "include";
        public static final String EXCLUDE = "exclude";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableValueDictEntry extends CdpObject {
        private AttributionReportingAggregatableValueDictEntry(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregatableValueDictEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregatableValueDictEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * number instead of integer because not all uint32 can be represented by int
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        /**
         * Returns the filteringId field.
         * @return the protocol field value
         */
        @Nullable public String filteringId() {
            return (String) value("filteringId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * number instead of integer because not all uint32 can be represented by int
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Sets the filteringId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filteringId(@Nullable String value) {
                if (value == null) values.remove("filteringId");
                else values.put("filteringId", jsonValue(value));
                return this;
            }
            public AttributionReportingAggregatableValueDictEntry build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("filteringId")) throw new IllegalStateException("Missing required CDP field: filteringId");
                return new AttributionReportingAggregatableValueDictEntry(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableValueEntry extends CdpObject {
        private AttributionReportingAggregatableValueEntry(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregatableValueEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregatableValueEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the values field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingAggregatableValueDictEntry> values() {
            return list(value("values"), element0 -> Storage.AttributionReportingAggregatableValueDictEntry.fromMap(objectMap(element0)));
        }
        /**
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingFilterPair filters() {
            return Storage.AttributionReportingFilterPair.fromMap(objectMap(value("filters")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the values field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder values(@Nullable java.util.List<Storage.AttributionReportingAggregatableValueDictEntry> value) {
                if (value == null) values.remove("values");
                else values.put("values", jsonValue(value));
                return this;
            }
            /**
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable Storage.AttributionReportingFilterPair value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            public AttributionReportingAggregatableValueEntry build() {
                if (!values.containsKey("values")) throw new IllegalStateException("Missing required CDP field: values");
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                return new AttributionReportingAggregatableValueEntry(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingEventTriggerData extends CdpObject {
        private AttributionReportingEventTriggerData(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingEventTriggerData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingEventTriggerData(values);
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
         * Returns the priority field.
         * @return the protocol field value
         */
        @Nullable public String priority() {
            return (String) value("priority");
        }
        /**
         * Returns the dedupKey field.
         * @return the protocol field value
         */
        @Nullable public String dedupKey() {
            return (String) value("dedupKey");
        }
        /**
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingFilterPair filters() {
            return Storage.AttributionReportingFilterPair.fromMap(objectMap(value("filters")));
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
             * Sets the priority field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder priority(@Nullable String value) {
                if (value == null) values.remove("priority");
                else values.put("priority", jsonValue(value));
                return this;
            }
            /**
             * Sets the dedupKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dedupKey(@Nullable String value) {
                if (value == null) values.remove("dedupKey");
                else values.put("dedupKey", jsonValue(value));
                return this;
            }
            /**
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable Storage.AttributionReportingFilterPair value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            public AttributionReportingEventTriggerData build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                if (!values.containsKey("priority")) throw new IllegalStateException("Missing required CDP field: priority");
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                return new AttributionReportingEventTriggerData(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableTriggerData extends CdpObject {
        private AttributionReportingAggregatableTriggerData(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregatableTriggerData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregatableTriggerData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the keyPiece field.
         * @return the protocol field value
         */
        @Nullable public String keyPiece() {
            return (String) value("keyPiece");
        }
        /**
         * Returns the sourceKeys field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> sourceKeys() {
            return list(value("sourceKeys"), element0 -> (String) element0);
        }
        /**
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingFilterPair filters() {
            return Storage.AttributionReportingFilterPair.fromMap(objectMap(value("filters")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the keyPiece field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyPiece(@Nullable String value) {
                if (value == null) values.remove("keyPiece");
                else values.put("keyPiece", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceKeys field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceKeys(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("sourceKeys");
                else values.put("sourceKeys", jsonValue(value));
                return this;
            }
            /**
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable Storage.AttributionReportingFilterPair value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            public AttributionReportingAggregatableTriggerData build() {
                if (!values.containsKey("keyPiece")) throw new IllegalStateException("Missing required CDP field: keyPiece");
                if (!values.containsKey("sourceKeys")) throw new IllegalStateException("Missing required CDP field: sourceKeys");
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                return new AttributionReportingAggregatableTriggerData(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableDedupKey extends CdpObject {
        private AttributionReportingAggregatableDedupKey(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingAggregatableDedupKey fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingAggregatableDedupKey(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dedupKey field.
         * @return the protocol field value
         */
        @Nullable public String dedupKey() {
            return (String) value("dedupKey");
        }
        /**
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingFilterPair filters() {
            return Storage.AttributionReportingFilterPair.fromMap(objectMap(value("filters")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dedupKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dedupKey(@Nullable String value) {
                if (value == null) values.remove("dedupKey");
                else values.put("dedupKey", jsonValue(value));
                return this;
            }
            /**
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable Storage.AttributionReportingFilterPair value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            public AttributionReportingAggregatableDedupKey build() {
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                return new AttributionReportingAggregatableDedupKey(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingNamedBudgetCandidate extends CdpObject {
        private AttributionReportingNamedBudgetCandidate(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingNamedBudgetCandidate fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingNamedBudgetCandidate(values);
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
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingFilterPair filters() {
            return Storage.AttributionReportingFilterPair.fromMap(objectMap(value("filters")));
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
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable Storage.AttributionReportingFilterPair value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            public AttributionReportingNamedBudgetCandidate build() {
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                return new AttributionReportingNamedBudgetCandidate(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingTriggerRegistration extends CdpObject {
        private AttributionReportingTriggerRegistration(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingTriggerRegistration fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingTriggerRegistration(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the filters field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingFilterPair filters() {
            return Storage.AttributionReportingFilterPair.fromMap(objectMap(value("filters")));
        }
        /**
         * Returns the debugKey field.
         * @return the protocol field value
         */
        @Nullable public String debugKey() {
            return (String) value("debugKey");
        }
        /**
         * Returns the aggregatableDedupKeys field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingAggregatableDedupKey> aggregatableDedupKeys() {
            return list(value("aggregatableDedupKeys"), element0 -> Storage.AttributionReportingAggregatableDedupKey.fromMap(objectMap(element0)));
        }
        /**
         * Returns the eventTriggerData field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingEventTriggerData> eventTriggerData() {
            return list(value("eventTriggerData"), element0 -> Storage.AttributionReportingEventTriggerData.fromMap(objectMap(element0)));
        }
        /**
         * Returns the aggregatableTriggerData field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingAggregatableTriggerData> aggregatableTriggerData() {
            return list(value("aggregatableTriggerData"), element0 -> Storage.AttributionReportingAggregatableTriggerData.fromMap(objectMap(element0)));
        }
        /**
         * Returns the aggregatableValues field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingAggregatableValueEntry> aggregatableValues() {
            return list(value("aggregatableValues"), element0 -> Storage.AttributionReportingAggregatableValueEntry.fromMap(objectMap(element0)));
        }
        /**
         * Returns the aggregatableFilteringIdMaxBytes field.
         * @return the protocol field value
         */
        @Nullable public Long aggregatableFilteringIdMaxBytes() {
            return numberAsLong(value("aggregatableFilteringIdMaxBytes"));
        }
        /**
         * Returns the debugReporting field.
         * @return the protocol field value
         */
        @Nullable public Boolean debugReporting() {
            return (Boolean) value("debugReporting");
        }
        /**
         * Returns the aggregationCoordinatorOrigin field.
         * @return the protocol field value
         */
        @Nullable public String aggregationCoordinatorOrigin() {
            return (String) value("aggregationCoordinatorOrigin");
        }
        /**
         * Returns the sourceRegistrationTimeConfig field.
         * @return the protocol field value
         */
        @Nullable public String sourceRegistrationTimeConfig() {
            return (String) value("sourceRegistrationTimeConfig");
        }
        /**
         * Returns the triggerContextId field.
         * @return the protocol field value
         */
        @Nullable public String triggerContextId() {
            return (String) value("triggerContextId");
        }
        /**
         * Returns the aggregatableDebugReportingConfig field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingAggregatableDebugReportingConfig aggregatableDebugReportingConfig() {
            return Storage.AttributionReportingAggregatableDebugReportingConfig.fromMap(objectMap(value("aggregatableDebugReportingConfig")));
        }
        /**
         * Returns the scopes field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> scopes() {
            return list(value("scopes"), element0 -> (String) element0);
        }
        /**
         * Returns the namedBudgets field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.AttributionReportingNamedBudgetCandidate> namedBudgets() {
            return list(value("namedBudgets"), element0 -> Storage.AttributionReportingNamedBudgetCandidate.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the filters field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filters(@Nullable Storage.AttributionReportingFilterPair value) {
                if (value == null) values.remove("filters");
                else values.put("filters", jsonValue(value));
                return this;
            }
            /**
             * Sets the debugKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debugKey(@Nullable String value) {
                if (value == null) values.remove("debugKey");
                else values.put("debugKey", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatableDedupKeys field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableDedupKeys(@Nullable java.util.List<Storage.AttributionReportingAggregatableDedupKey> value) {
                if (value == null) values.remove("aggregatableDedupKeys");
                else values.put("aggregatableDedupKeys", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventTriggerData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventTriggerData(@Nullable java.util.List<Storage.AttributionReportingEventTriggerData> value) {
                if (value == null) values.remove("eventTriggerData");
                else values.put("eventTriggerData", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatableTriggerData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableTriggerData(@Nullable java.util.List<Storage.AttributionReportingAggregatableTriggerData> value) {
                if (value == null) values.remove("aggregatableTriggerData");
                else values.put("aggregatableTriggerData", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatableValues field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableValues(@Nullable java.util.List<Storage.AttributionReportingAggregatableValueEntry> value) {
                if (value == null) values.remove("aggregatableValues");
                else values.put("aggregatableValues", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatableFilteringIdMaxBytes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableFilteringIdMaxBytes(@Nullable Long value) {
                if (value == null) values.remove("aggregatableFilteringIdMaxBytes");
                else values.put("aggregatableFilteringIdMaxBytes", jsonValue(value));
                return this;
            }
            /**
             * Sets the debugReporting field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debugReporting(@Nullable Boolean value) {
                if (value == null) values.remove("debugReporting");
                else values.put("debugReporting", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregationCoordinatorOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregationCoordinatorOrigin(@Nullable String value) {
                if (value == null) values.remove("aggregationCoordinatorOrigin");
                else values.put("aggregationCoordinatorOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceRegistrationTimeConfig field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceRegistrationTimeConfig(@Nullable String value) {
                if (value == null) values.remove("sourceRegistrationTimeConfig");
                else values.put("sourceRegistrationTimeConfig", jsonValue(value));
                return this;
            }
            /**
             * Sets the triggerContextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder triggerContextId(@Nullable String value) {
                if (value == null) values.remove("triggerContextId");
                else values.put("triggerContextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatableDebugReportingConfig field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatableDebugReportingConfig(@Nullable Storage.AttributionReportingAggregatableDebugReportingConfig value) {
                if (value == null) values.remove("aggregatableDebugReportingConfig");
                else values.put("aggregatableDebugReportingConfig", jsonValue(value));
                return this;
            }
            /**
             * Sets the scopes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("scopes");
                else values.put("scopes", jsonValue(value));
                return this;
            }
            /**
             * Sets the namedBudgets field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder namedBudgets(@Nullable java.util.List<Storage.AttributionReportingNamedBudgetCandidate> value) {
                if (value == null) values.remove("namedBudgets");
                else values.put("namedBudgets", jsonValue(value));
                return this;
            }
            public AttributionReportingTriggerRegistration build() {
                if (!values.containsKey("filters")) throw new IllegalStateException("Missing required CDP field: filters");
                if (!values.containsKey("aggregatableDedupKeys")) throw new IllegalStateException("Missing required CDP field: aggregatableDedupKeys");
                if (!values.containsKey("eventTriggerData")) throw new IllegalStateException("Missing required CDP field: eventTriggerData");
                if (!values.containsKey("aggregatableTriggerData")) throw new IllegalStateException("Missing required CDP field: aggregatableTriggerData");
                if (!values.containsKey("aggregatableValues")) throw new IllegalStateException("Missing required CDP field: aggregatableValues");
                if (!values.containsKey("aggregatableFilteringIdMaxBytes")) throw new IllegalStateException("Missing required CDP field: aggregatableFilteringIdMaxBytes");
                if (!values.containsKey("debugReporting")) throw new IllegalStateException("Missing required CDP field: debugReporting");
                if (!values.containsKey("sourceRegistrationTimeConfig")) throw new IllegalStateException("Missing required CDP field: sourceRegistrationTimeConfig");
                if (!values.containsKey("aggregatableDebugReportingConfig")) throw new IllegalStateException("Missing required CDP field: aggregatableDebugReportingConfig");
                if (!values.containsKey("scopes")) throw new IllegalStateException("Missing required CDP field: scopes");
                if (!values.containsKey("namedBudgets")) throw new IllegalStateException("Missing required CDP field: namedBudgets");
                return new AttributionReportingTriggerRegistration(values);
            }
        }
    }
    /**
     * Wire values for AttributionReportingEventLevelResult.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingEventLevelResult {
        private AttributionReportingEventLevelResult() {}
        public static final String SUCCESS = "success";
        public static final String SUCCESSDROPPEDLOWERPRIORITY = "successDroppedLowerPriority";
        public static final String INTERNALERROR = "internalError";
        public static final String NOCAPACITYFORATTRIBUTIONDESTINATION = "noCapacityForAttributionDestination";
        public static final String NOMATCHINGSOURCES = "noMatchingSources";
        public static final String DEDUPLICATED = "deduplicated";
        public static final String EXCESSIVEATTRIBUTIONS = "excessiveAttributions";
        public static final String PRIORITYTOOLOW = "priorityTooLow";
        public static final String NEVERATTRIBUTEDSOURCE = "neverAttributedSource";
        public static final String EXCESSIVEREPORTINGORIGINS = "excessiveReportingOrigins";
        public static final String NOMATCHINGSOURCEFILTERDATA = "noMatchingSourceFilterData";
        public static final String PROHIBITEDBYBROWSERPOLICY = "prohibitedByBrowserPolicy";
        public static final String NOMATCHINGCONFIGURATIONS = "noMatchingConfigurations";
        public static final String EXCESSIVEREPORTS = "excessiveReports";
        public static final String FALSELYATTRIBUTEDSOURCE = "falselyAttributedSource";
        public static final String REPORTWINDOWPASSED = "reportWindowPassed";
        public static final String NOTREGISTERED = "notRegistered";
        public static final String REPORTWINDOWNOTSTARTED = "reportWindowNotStarted";
        public static final String NOMATCHINGTRIGGERDATA = "noMatchingTriggerData";
    }
    /**
     * Wire values for AttributionReportingAggregatableResult.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingAggregatableResult {
        private AttributionReportingAggregatableResult() {}
        public static final String SUCCESS = "success";
        public static final String INTERNALERROR = "internalError";
        public static final String NOCAPACITYFORATTRIBUTIONDESTINATION = "noCapacityForAttributionDestination";
        public static final String NOMATCHINGSOURCES = "noMatchingSources";
        public static final String EXCESSIVEATTRIBUTIONS = "excessiveAttributions";
        public static final String EXCESSIVEREPORTINGORIGINS = "excessiveReportingOrigins";
        public static final String NOHISTOGRAMS = "noHistograms";
        public static final String INSUFFICIENTBUDGET = "insufficientBudget";
        public static final String INSUFFICIENTNAMEDBUDGET = "insufficientNamedBudget";
        public static final String NOMATCHINGSOURCEFILTERDATA = "noMatchingSourceFilterData";
        public static final String NOTREGISTERED = "notRegistered";
        public static final String PROHIBITEDBYBROWSERPOLICY = "prohibitedByBrowserPolicy";
        public static final String DEDUPLICATED = "deduplicated";
        public static final String REPORTWINDOWPASSED = "reportWindowPassed";
        public static final String EXCESSIVEREPORTS = "excessiveReports";
    }
    /**
     * Wire values for AttributionReportingReportResult.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingReportResult {
        private AttributionReportingReportResult() {}
        public static final String SENT = "sent";
        public static final String PROHIBITED = "prohibited";
        public static final String FAILEDTOASSEMBLE = "failedToAssemble";
        public static final String EXPIRED = "expired";
    }
    /**
     * A single Related Website Set object.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RelatedWebsiteSet extends CdpObject {
        private RelatedWebsiteSet(Map<String, Object> values) { super(values); }
        @Nullable public static RelatedWebsiteSet fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RelatedWebsiteSet(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The primary site of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> primarySites() {
            return list(value("primarySites"), element0 -> (String) element0);
        }
        /**
         * The associated sites of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> associatedSites() {
            return list(value("associatedSites"), element0 -> (String) element0);
        }
        /**
         * The service sites of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> serviceSites() {
            return list(value("serviceSites"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The primary site of this set, along with the ccTLDs if there is any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder primarySites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("primarySites");
                else values.put("primarySites", jsonValue(value));
                return this;
            }
            /**
             * The associated sites of this set, along with the ccTLDs if there is any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder associatedSites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("associatedSites");
                else values.put("associatedSites", jsonValue(value));
                return this;
            }
            /**
             * The service sites of this set, along with the ccTLDs if there is any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceSites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("serviceSites");
                else values.put("serviceSites", jsonValue(value));
                return this;
            }
            public RelatedWebsiteSet build() {
                if (!values.containsKey("primarySites")) throw new IllegalStateException("Missing required CDP field: primarySites");
                if (!values.containsKey("associatedSites")) throw new IllegalStateException("Missing required CDP field: associatedSites");
                if (!values.containsKey("serviceSites")) throw new IllegalStateException("Missing required CDP field: serviceSites");
                return new RelatedWebsiteSet(values);
            }
        }
    }
    /**
     * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetStorageKeyForFrameParams extends CdpObject {
        private GetStorageKeyForFrameParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetStorageKeyForFrameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStorageKeyForFrameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetStorageKeyForFrameParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new GetStorageKeyForFrameParams(values);
            }
        }
    }
    /**
     * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetStorageKeyForFrameResult extends CdpObject {
        private GetStorageKeyForFrameResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetStorageKeyForFrameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStorageKeyForFrameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            public GetStorageKeyForFrameResult build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new GetStorageKeyForFrameResult(values);
            }
        }
    }
    /**
     * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetStorageKeyParams extends CdpObject {
        private GetStorageKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetStorageKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStorageKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetStorageKeyParams build() {
                return new GetStorageKeyParams(values);
            }
        }
    }
    /**
     * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetStorageKeyResult extends CdpObject {
        private GetStorageKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetStorageKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStorageKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            public GetStorageKeyResult build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new GetStorageKeyResult(values);
            }
        }
    }
    /**
     * Clears storage for origin.
     */
    public static final class ClearDataForOriginParams extends CdpObject {
        private ClearDataForOriginParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDataForOriginParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDataForOriginParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Comma separated list of StorageType to clear.
         * @return the protocol field value
         */
        @Nullable public String storageTypes() {
            return (String) value("storageTypes");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Comma separated list of StorageType to clear.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageTypes(@Nullable String value) {
                if (value == null) values.remove("storageTypes");
                else values.put("storageTypes", jsonValue(value));
                return this;
            }
            public ClearDataForOriginParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("storageTypes")) throw new IllegalStateException("Missing required CDP field: storageTypes");
                return new ClearDataForOriginParams(values);
            }
        }
    }
    /**
     * Clears storage for origin.
     */
    public static final class ClearDataForOriginResult extends CdpObject {
        private ClearDataForOriginResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDataForOriginResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDataForOriginResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDataForOriginResult build() {
                return new ClearDataForOriginResult(values);
            }
        }
    }
    /**
     * Clears storage for storage key.
     */
    public static final class ClearDataForStorageKeyParams extends CdpObject {
        private ClearDataForStorageKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDataForStorageKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDataForStorageKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage key.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Comma separated list of StorageType to clear.
         * @return the protocol field value
         */
        @Nullable public String storageTypes() {
            return (String) value("storageTypes");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
             * Comma separated list of StorageType to clear.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageTypes(@Nullable String value) {
                if (value == null) values.remove("storageTypes");
                else values.put("storageTypes", jsonValue(value));
                return this;
            }
            public ClearDataForStorageKeyParams build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("storageTypes")) throw new IllegalStateException("Missing required CDP field: storageTypes");
                return new ClearDataForStorageKeyParams(values);
            }
        }
    }
    /**
     * Clears storage for storage key.
     */
    public static final class ClearDataForStorageKeyResult extends CdpObject {
        private ClearDataForStorageKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDataForStorageKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDataForStorageKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDataForStorageKeyResult build() {
                return new ClearDataForStorageKeyResult(values);
            }
        }
    }
    /**
     * Returns all browser cookies.
     */
    public static final class GetCookiesParams extends CdpObject {
        private GetCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Browser context to use when called on the browser endpoint.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public GetCookiesParams build() {
                return new GetCookiesParams(values);
            }
        }
    }
    /**
     * Returns all browser cookies.
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
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
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
            /**
             * Browser context to use when called on the browser endpoint.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
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
     * Clears cookies.
     */
    public static final class ClearCookiesParams extends CdpObject {
        private ClearCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Browser context to use when called on the browser endpoint.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public ClearCookiesParams build() {
                return new ClearCookiesParams(values);
            }
        }
    }
    /**
     * Clears cookies.
     */
    public static final class ClearCookiesResult extends CdpObject {
        private ClearCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearCookiesResult build() {
                return new ClearCookiesResult(values);
            }
        }
    }
    /**
     * Returns usage and quota in bytes.
     */
    public static final class GetUsageAndQuotaParams extends CdpObject {
        private GetUsageAndQuotaParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetUsageAndQuotaParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetUsageAndQuotaParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public GetUsageAndQuotaParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new GetUsageAndQuotaParams(values);
            }
        }
    }
    /**
     * Returns usage and quota in bytes.
     */
    public static final class GetUsageAndQuotaResult extends CdpObject {
        private GetUsageAndQuotaResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetUsageAndQuotaResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetUsageAndQuotaResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage usage (bytes).
         * @return the protocol field value
         */
        @Nullable public Double usage() {
            return numberAsDouble(value("usage"));
        }
        /**
         * Storage quota (bytes).
         * @return the protocol field value
         */
        @Nullable public Double quota() {
            return numberAsDouble(value("quota"));
        }
        /**
         * Whether or not the origin has an active storage quota override
         * @return the protocol field value
         */
        @Nullable public Boolean overrideActive() {
            return (Boolean) value("overrideActive");
        }
        /**
         * Storage usage per type (bytes).
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.UsageForType> usageBreakdown() {
            return list(value("usageBreakdown"), element0 -> Storage.UsageForType.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Storage usage (bytes).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder usage(@Nullable Double value) {
                if (value == null) values.remove("usage");
                else values.put("usage", jsonValue(value));
                return this;
            }
            /**
             * Storage quota (bytes).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quota(@Nullable Double value) {
                if (value == null) values.remove("quota");
                else values.put("quota", jsonValue(value));
                return this;
            }
            /**
             * Whether or not the origin has an active storage quota override
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder overrideActive(@Nullable Boolean value) {
                if (value == null) values.remove("overrideActive");
                else values.put("overrideActive", jsonValue(value));
                return this;
            }
            /**
             * Storage usage per type (bytes).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder usageBreakdown(@Nullable java.util.List<Storage.UsageForType> value) {
                if (value == null) values.remove("usageBreakdown");
                else values.put("usageBreakdown", jsonValue(value));
                return this;
            }
            public GetUsageAndQuotaResult build() {
                if (!values.containsKey("usage")) throw new IllegalStateException("Missing required CDP field: usage");
                if (!values.containsKey("quota")) throw new IllegalStateException("Missing required CDP field: quota");
                if (!values.containsKey("overrideActive")) throw new IllegalStateException("Missing required CDP field: overrideActive");
                if (!values.containsKey("usageBreakdown")) throw new IllegalStateException("Missing required CDP field: usageBreakdown");
                return new GetUsageAndQuotaResult(values);
            }
        }
    }
    /**
     * Override quota for the specified origin
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OverrideQuotaForOriginParams extends CdpObject {
        private OverrideQuotaForOriginParams(Map<String, Object> values) { super(values); }
        @Nullable public static OverrideQuotaForOriginParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OverrideQuotaForOriginParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * The quota size (in bytes) to override the original quota with. If this is called multiple times, the overridden quota will be equal to the quotaSize provided in the final call. If this is called without specifying a quotaSize, the quota will be reset to the default value for the specified origin. If this is called multiple times with different origins, the override will be maintained for each origin until it is disabled (called without a quotaSize).
         * @return the protocol field value
         */
        @Nullable public Double quotaSize() {
            return numberAsDouble(value("quotaSize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * The quota size (in bytes) to override the original quota with. If this is called multiple times, the overridden quota will be equal to the quotaSize provided in the final call. If this is called without specifying a quotaSize, the quota will be reset to the default value for the specified origin. If this is called multiple times with different origins, the override will be maintained for each origin until it is disabled (called without a quotaSize).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quotaSize(@Nullable Double value) {
                if (value == null) values.remove("quotaSize");
                else values.put("quotaSize", jsonValue(value));
                return this;
            }
            public OverrideQuotaForOriginParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new OverrideQuotaForOriginParams(values);
            }
        }
    }
    /**
     * Override quota for the specified origin
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OverrideQuotaForOriginResult extends CdpObject {
        private OverrideQuotaForOriginResult(Map<String, Object> values) { super(values); }
        @Nullable public static OverrideQuotaForOriginResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OverrideQuotaForOriginResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public OverrideQuotaForOriginResult build() {
                return new OverrideQuotaForOriginResult(values);
            }
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForOriginParams extends CdpObject {
        private TrackCacheStorageForOriginParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrackCacheStorageForOriginParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackCacheStorageForOriginParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public TrackCacheStorageForOriginParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new TrackCacheStorageForOriginParams(values);
            }
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForOriginResult extends CdpObject {
        private TrackCacheStorageForOriginResult(Map<String, Object> values) { super(values); }
        @Nullable public static TrackCacheStorageForOriginResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackCacheStorageForOriginResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TrackCacheStorageForOriginResult build() {
                return new TrackCacheStorageForOriginResult(values);
            }
        }
    }
    /**
     * Registers storage key to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForStorageKeyParams extends CdpObject {
        private TrackCacheStorageForStorageKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrackCacheStorageForStorageKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackCacheStorageForStorageKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage key.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            public TrackCacheStorageForStorageKeyParams build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new TrackCacheStorageForStorageKeyParams(values);
            }
        }
    }
    /**
     * Registers storage key to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForStorageKeyResult extends CdpObject {
        private TrackCacheStorageForStorageKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static TrackCacheStorageForStorageKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackCacheStorageForStorageKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TrackCacheStorageForStorageKeyResult build() {
                return new TrackCacheStorageForStorageKeyResult(values);
            }
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its IndexedDB.
     */
    public static final class TrackIndexedDBForOriginParams extends CdpObject {
        private TrackIndexedDBForOriginParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrackIndexedDBForOriginParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackIndexedDBForOriginParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public TrackIndexedDBForOriginParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new TrackIndexedDBForOriginParams(values);
            }
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its IndexedDB.
     */
    public static final class TrackIndexedDBForOriginResult extends CdpObject {
        private TrackIndexedDBForOriginResult(Map<String, Object> values) { super(values); }
        @Nullable public static TrackIndexedDBForOriginResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackIndexedDBForOriginResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TrackIndexedDBForOriginResult build() {
                return new TrackIndexedDBForOriginResult(values);
            }
        }
    }
    /**
     * Registers storage key to be notified when an update occurs to its IndexedDB.
     */
    public static final class TrackIndexedDBForStorageKeyParams extends CdpObject {
        private TrackIndexedDBForStorageKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrackIndexedDBForStorageKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackIndexedDBForStorageKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage key.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            public TrackIndexedDBForStorageKeyParams build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new TrackIndexedDBForStorageKeyParams(values);
            }
        }
    }
    /**
     * Registers storage key to be notified when an update occurs to its IndexedDB.
     */
    public static final class TrackIndexedDBForStorageKeyResult extends CdpObject {
        private TrackIndexedDBForStorageKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static TrackIndexedDBForStorageKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackIndexedDBForStorageKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TrackIndexedDBForStorageKeyResult build() {
                return new TrackIndexedDBForStorageKeyResult(values);
            }
        }
    }
    /**
     * Unregisters origin from receiving notifications for cache storage.
     */
    public static final class UntrackCacheStorageForOriginParams extends CdpObject {
        private UntrackCacheStorageForOriginParams(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackCacheStorageForOriginParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackCacheStorageForOriginParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public UntrackCacheStorageForOriginParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new UntrackCacheStorageForOriginParams(values);
            }
        }
    }
    /**
     * Unregisters origin from receiving notifications for cache storage.
     */
    public static final class UntrackCacheStorageForOriginResult extends CdpObject {
        private UntrackCacheStorageForOriginResult(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackCacheStorageForOriginResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackCacheStorageForOriginResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UntrackCacheStorageForOriginResult build() {
                return new UntrackCacheStorageForOriginResult(values);
            }
        }
    }
    /**
     * Unregisters storage key from receiving notifications for cache storage.
     */
    public static final class UntrackCacheStorageForStorageKeyParams extends CdpObject {
        private UntrackCacheStorageForStorageKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackCacheStorageForStorageKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackCacheStorageForStorageKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage key.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            public UntrackCacheStorageForStorageKeyParams build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new UntrackCacheStorageForStorageKeyParams(values);
            }
        }
    }
    /**
     * Unregisters storage key from receiving notifications for cache storage.
     */
    public static final class UntrackCacheStorageForStorageKeyResult extends CdpObject {
        private UntrackCacheStorageForStorageKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackCacheStorageForStorageKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackCacheStorageForStorageKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UntrackCacheStorageForStorageKeyResult build() {
                return new UntrackCacheStorageForStorageKeyResult(values);
            }
        }
    }
    /**
     * Unregisters origin from receiving notifications for IndexedDB.
     */
    public static final class UntrackIndexedDBForOriginParams extends CdpObject {
        private UntrackIndexedDBForOriginParams(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackIndexedDBForOriginParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackIndexedDBForOriginParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public UntrackIndexedDBForOriginParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new UntrackIndexedDBForOriginParams(values);
            }
        }
    }
    /**
     * Unregisters origin from receiving notifications for IndexedDB.
     */
    public static final class UntrackIndexedDBForOriginResult extends CdpObject {
        private UntrackIndexedDBForOriginResult(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackIndexedDBForOriginResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackIndexedDBForOriginResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UntrackIndexedDBForOriginResult build() {
                return new UntrackIndexedDBForOriginResult(values);
            }
        }
    }
    /**
     * Unregisters storage key from receiving notifications for IndexedDB.
     */
    public static final class UntrackIndexedDBForStorageKeyParams extends CdpObject {
        private UntrackIndexedDBForStorageKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackIndexedDBForStorageKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackIndexedDBForStorageKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage key.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            public UntrackIndexedDBForStorageKeyParams build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new UntrackIndexedDBForStorageKeyParams(values);
            }
        }
    }
    /**
     * Unregisters storage key from receiving notifications for IndexedDB.
     */
    public static final class UntrackIndexedDBForStorageKeyResult extends CdpObject {
        private UntrackIndexedDBForStorageKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static UntrackIndexedDBForStorageKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UntrackIndexedDBForStorageKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UntrackIndexedDBForStorageKeyResult build() {
                return new UntrackIndexedDBForStorageKeyResult(values);
            }
        }
    }
    /**
     * Returns the number of stored Trust Tokens per issuer for the current browsing context.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTrustTokensParams extends CdpObject {
        private GetTrustTokensParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetTrustTokensParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTrustTokensParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetTrustTokensParams build() {
                return new GetTrustTokensParams(values);
            }
        }
    }
    /**
     * Returns the number of stored Trust Tokens per issuer for the current browsing context.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTrustTokensResult extends CdpObject {
        private GetTrustTokensResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetTrustTokensResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTrustTokensResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the tokens field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.TrustTokens> tokens() {
            return list(value("tokens"), element0 -> Storage.TrustTokens.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the tokens field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tokens(@Nullable java.util.List<Storage.TrustTokens> value) {
                if (value == null) values.remove("tokens");
                else values.put("tokens", jsonValue(value));
                return this;
            }
            public GetTrustTokensResult build() {
                if (!values.containsKey("tokens")) throw new IllegalStateException("Missing required CDP field: tokens");
                return new GetTrustTokensResult(values);
            }
        }
    }
    /**
     * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearTrustTokensParams extends CdpObject {
        private ClearTrustTokensParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearTrustTokensParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearTrustTokensParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the issuerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String issuerOrigin() {
            return (String) value("issuerOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the issuerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuerOrigin(@Nullable String value) {
                if (value == null) values.remove("issuerOrigin");
                else values.put("issuerOrigin", jsonValue(value));
                return this;
            }
            public ClearTrustTokensParams build() {
                if (!values.containsKey("issuerOrigin")) throw new IllegalStateException("Missing required CDP field: issuerOrigin");
                return new ClearTrustTokensParams(values);
            }
        }
    }
    /**
     * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearTrustTokensResult extends CdpObject {
        private ClearTrustTokensResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearTrustTokensResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearTrustTokensResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True if any tokens were deleted, false otherwise.
         * @return the protocol field value
         */
        @Nullable public Boolean didDeleteTokens() {
            return (Boolean) value("didDeleteTokens");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True if any tokens were deleted, false otherwise.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder didDeleteTokens(@Nullable Boolean value) {
                if (value == null) values.remove("didDeleteTokens");
                else values.put("didDeleteTokens", jsonValue(value));
                return this;
            }
            public ClearTrustTokensResult build() {
                if (!values.containsKey("didDeleteTokens")) throw new IllegalStateException("Missing required CDP field: didDeleteTokens");
                return new ClearTrustTokensResult(values);
            }
        }
    }
    /**
     * Gets details for a named interest group.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetInterestGroupDetailsParams extends CdpObject {
        private GetInterestGroupDetailsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetInterestGroupDetailsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInterestGroupDetailsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
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
            public GetInterestGroupDetailsParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new GetInterestGroupDetailsParams(values);
            }
        }
    }
    /**
     * Gets details for a named interest group.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetInterestGroupDetailsResult extends CdpObject {
        private GetInterestGroupDetailsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetInterestGroupDetailsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInterestGroupDetailsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * This largely corresponds to: https://wicg.github.io/turtledove/#dictdef-generatebidinterestgroup but has absolute expirationTime instead of relative lifetimeMs and also adds joiningOrigin.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> details() {
            return objectMap(value("details"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * This largely corresponds to: https://wicg.github.io/turtledove/#dictdef-generatebidinterestgroup but has absolute expirationTime instead of relative lifetimeMs and also adds joiningOrigin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder details(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("details");
                else values.put("details", jsonValue(value));
                return this;
            }
            public GetInterestGroupDetailsResult build() {
                if (!values.containsKey("details")) throw new IllegalStateException("Missing required CDP field: details");
                return new GetInterestGroupDetailsResult(values);
            }
        }
    }
    /**
     * Enables/Disables issuing of interestGroupAccessed events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterestGroupTrackingParams extends CdpObject {
        private SetInterestGroupTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterestGroupTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterestGroupTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public SetInterestGroupTrackingParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new SetInterestGroupTrackingParams(values);
            }
        }
    }
    /**
     * Enables/Disables issuing of interestGroupAccessed events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterestGroupTrackingResult extends CdpObject {
        private SetInterestGroupTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterestGroupTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterestGroupTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInterestGroupTrackingResult build() {
                return new SetInterestGroupTrackingResult(values);
            }
        }
    }
    /**
     * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterestGroupAuctionTrackingParams extends CdpObject {
        private SetInterestGroupAuctionTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterestGroupAuctionTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterestGroupAuctionTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public SetInterestGroupAuctionTrackingParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new SetInterestGroupAuctionTrackingParams(values);
            }
        }
    }
    /**
     * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterestGroupAuctionTrackingResult extends CdpObject {
        private SetInterestGroupAuctionTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterestGroupAuctionTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterestGroupAuctionTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInterestGroupAuctionTrackingResult build() {
                return new SetInterestGroupAuctionTrackingResult(values);
            }
        }
    }
    /**
     * Gets metadata for an origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSharedStorageMetadataParams extends CdpObject {
        private GetSharedStorageMetadataParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSharedStorageMetadataParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSharedStorageMetadataParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            public GetSharedStorageMetadataParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                return new GetSharedStorageMetadataParams(values);
            }
        }
    }
    /**
     * Gets metadata for an origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSharedStorageMetadataResult extends CdpObject {
        private GetSharedStorageMetadataResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSharedStorageMetadataResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSharedStorageMetadataResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the metadata field.
         * @return the protocol field value
         */
        @Nullable public Storage.SharedStorageMetadata metadata() {
            return Storage.SharedStorageMetadata.fromMap(objectMap(value("metadata")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the metadata field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metadata(@Nullable Storage.SharedStorageMetadata value) {
                if (value == null) values.remove("metadata");
                else values.put("metadata", jsonValue(value));
                return this;
            }
            public GetSharedStorageMetadataResult build() {
                if (!values.containsKey("metadata")) throw new IllegalStateException("Missing required CDP field: metadata");
                return new GetSharedStorageMetadataResult(values);
            }
        }
    }
    /**
     * Gets the entries in an given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSharedStorageEntriesParams extends CdpObject {
        private GetSharedStorageEntriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSharedStorageEntriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSharedStorageEntriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            public GetSharedStorageEntriesParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                return new GetSharedStorageEntriesParams(values);
            }
        }
    }
    /**
     * Gets the entries in an given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSharedStorageEntriesResult extends CdpObject {
        private GetSharedStorageEntriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSharedStorageEntriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSharedStorageEntriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the entries field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.SharedStorageEntry> entries() {
            return list(value("entries"), element0 -> Storage.SharedStorageEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the entries field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entries(@Nullable java.util.List<Storage.SharedStorageEntry> value) {
                if (value == null) values.remove("entries");
                else values.put("entries", jsonValue(value));
                return this;
            }
            public GetSharedStorageEntriesResult build() {
                if (!values.containsKey("entries")) throw new IllegalStateException("Missing required CDP field: entries");
                return new GetSharedStorageEntriesResult(values);
            }
        }
    }
    /**
     * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSharedStorageEntryParams extends CdpObject {
        private SetSharedStorageEntryParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSharedStorageEntryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSharedStorageEntryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * If {@code ignoreIfPresent} is included and true, then only sets the entry if {@code key} doesn&#x27;t already exist.
         * @return the protocol field value
         */
        @Nullable public Boolean ignoreIfPresent() {
            return (Boolean) value("ignoreIfPresent");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
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
            /**
             * If {@code ignoreIfPresent} is included and true, then only sets the entry if {@code key} doesn&#x27;t already exist.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignoreIfPresent(@Nullable Boolean value) {
                if (value == null) values.remove("ignoreIfPresent");
                else values.put("ignoreIfPresent", jsonValue(value));
                return this;
            }
            public SetSharedStorageEntryParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetSharedStorageEntryParams(values);
            }
        }
    }
    /**
     * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSharedStorageEntryResult extends CdpObject {
        private SetSharedStorageEntryResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSharedStorageEntryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSharedStorageEntryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSharedStorageEntryResult build() {
                return new SetSharedStorageEntryResult(values);
            }
        }
    }
    /**
     * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteSharedStorageEntryParams extends CdpObject {
        private DeleteSharedStorageEntryParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteSharedStorageEntryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteSharedStorageEntryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            public DeleteSharedStorageEntryParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                return new DeleteSharedStorageEntryParams(values);
            }
        }
    }
    /**
     * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteSharedStorageEntryResult extends CdpObject {
        private DeleteSharedStorageEntryResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteSharedStorageEntryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteSharedStorageEntryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteSharedStorageEntryResult build() {
                return new DeleteSharedStorageEntryResult(values);
            }
        }
    }
    /**
     * Clears all entries for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearSharedStorageEntriesParams extends CdpObject {
        private ClearSharedStorageEntriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearSharedStorageEntriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearSharedStorageEntriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            public ClearSharedStorageEntriesParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                return new ClearSharedStorageEntriesParams(values);
            }
        }
    }
    /**
     * Clears all entries for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearSharedStorageEntriesResult extends CdpObject {
        private ClearSharedStorageEntriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearSharedStorageEntriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearSharedStorageEntriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearSharedStorageEntriesResult build() {
                return new ClearSharedStorageEntriesResult(values);
            }
        }
    }
    /**
     * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResetSharedStorageBudgetParams extends CdpObject {
        private ResetSharedStorageBudgetParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResetSharedStorageBudgetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetSharedStorageBudgetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            public ResetSharedStorageBudgetParams build() {
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                return new ResetSharedStorageBudgetParams(values);
            }
        }
    }
    /**
     * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResetSharedStorageBudgetResult extends CdpObject {
        private ResetSharedStorageBudgetResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResetSharedStorageBudgetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetSharedStorageBudgetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetSharedStorageBudgetResult build() {
                return new ResetSharedStorageBudgetResult(values);
            }
        }
    }
    /**
     * Enables/disables issuing of sharedStorageAccessed events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSharedStorageTrackingParams extends CdpObject {
        private SetSharedStorageTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSharedStorageTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSharedStorageTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public SetSharedStorageTrackingParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new SetSharedStorageTrackingParams(values);
            }
        }
    }
    /**
     * Enables/disables issuing of sharedStorageAccessed events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSharedStorageTrackingResult extends CdpObject {
        private SetSharedStorageTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSharedStorageTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSharedStorageTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSharedStorageTrackingResult build() {
                return new SetSharedStorageTrackingResult(values);
            }
        }
    }
    /**
     * Set tracking for a storage key&#x27;s buckets.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetStorageBucketTrackingParams extends CdpObject {
        private SetStorageBucketTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetStorageBucketTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStorageBucketTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageKey field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Sets the enable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public SetStorageBucketTrackingParams build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new SetStorageBucketTrackingParams(values);
            }
        }
    }
    /**
     * Set tracking for a storage key&#x27;s buckets.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetStorageBucketTrackingResult extends CdpObject {
        private SetStorageBucketTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetStorageBucketTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStorageBucketTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetStorageBucketTrackingResult build() {
                return new SetStorageBucketTrackingResult(values);
            }
        }
    }
    /**
     * Deletes the Storage Bucket with the given storage key and bucket name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteStorageBucketParams extends CdpObject {
        private DeleteStorageBucketParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteStorageBucketParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteStorageBucketParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the bucket field.
         * @return the protocol field value
         */
        @Nullable public Storage.StorageBucket bucket() {
            return Storage.StorageBucket.fromMap(objectMap(value("bucket")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the bucket field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucket(@Nullable Storage.StorageBucket value) {
                if (value == null) values.remove("bucket");
                else values.put("bucket", jsonValue(value));
                return this;
            }
            public DeleteStorageBucketParams build() {
                if (!values.containsKey("bucket")) throw new IllegalStateException("Missing required CDP field: bucket");
                return new DeleteStorageBucketParams(values);
            }
        }
    }
    /**
     * Deletes the Storage Bucket with the given storage key and bucket name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteStorageBucketResult extends CdpObject {
        private DeleteStorageBucketResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteStorageBucketResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteStorageBucketResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteStorageBucketResult build() {
                return new DeleteStorageBucketResult(values);
            }
        }
    }
    /**
     * Deletes state for sites identified as potential bounce trackers, immediately.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RunBounceTrackingMitigationsParams extends CdpObject {
        private RunBounceTrackingMitigationsParams(Map<String, Object> values) { super(values); }
        @Nullable public static RunBounceTrackingMitigationsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RunBounceTrackingMitigationsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RunBounceTrackingMitigationsParams build() {
                return new RunBounceTrackingMitigationsParams(values);
            }
        }
    }
    /**
     * Deletes state for sites identified as potential bounce trackers, immediately.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RunBounceTrackingMitigationsResult extends CdpObject {
        private RunBounceTrackingMitigationsResult(Map<String, Object> values) { super(values); }
        @Nullable public static RunBounceTrackingMitigationsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RunBounceTrackingMitigationsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the deletedSites field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> deletedSites() {
            return list(value("deletedSites"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the deletedSites field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deletedSites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("deletedSites");
                else values.put("deletedSites", jsonValue(value));
                return this;
            }
            public RunBounceTrackingMitigationsResult build() {
                if (!values.containsKey("deletedSites")) throw new IllegalStateException("Missing required CDP field: deletedSites");
                return new RunBounceTrackingMitigationsResult(values);
            }
        }
    }
    /**
     * https://wicg.github.io/attribution-reporting-api/
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttributionReportingLocalTestingModeParams extends CdpObject {
        private SetAttributionReportingLocalTestingModeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributionReportingLocalTestingModeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributionReportingLocalTestingModeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If enabled, noise is suppressed and reports are sent immediately.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If enabled, noise is suppressed and reports are sent immediately.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetAttributionReportingLocalTestingModeParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetAttributionReportingLocalTestingModeParams(values);
            }
        }
    }
    /**
     * https://wicg.github.io/attribution-reporting-api/
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttributionReportingLocalTestingModeResult extends CdpObject {
        private SetAttributionReportingLocalTestingModeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributionReportingLocalTestingModeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributionReportingLocalTestingModeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAttributionReportingLocalTestingModeResult build() {
                return new SetAttributionReportingLocalTestingModeResult(values);
            }
        }
    }
    /**
     * Enables/disables issuing of Attribution Reporting events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttributionReportingTrackingParams extends CdpObject {
        private SetAttributionReportingTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributionReportingTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributionReportingTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public SetAttributionReportingTrackingParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new SetAttributionReportingTrackingParams(values);
            }
        }
    }
    /**
     * Enables/disables issuing of Attribution Reporting events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttributionReportingTrackingResult extends CdpObject {
        private SetAttributionReportingTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributionReportingTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributionReportingTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAttributionReportingTrackingResult build() {
                return new SetAttributionReportingTrackingResult(values);
            }
        }
    }
    /**
     * Sends all pending Attribution Reports immediately, regardless of their scheduled report time.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SendPendingAttributionReportsParams extends CdpObject {
        private SendPendingAttributionReportsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SendPendingAttributionReportsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SendPendingAttributionReportsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SendPendingAttributionReportsParams build() {
                return new SendPendingAttributionReportsParams(values);
            }
        }
    }
    /**
     * Sends all pending Attribution Reports immediately, regardless of their scheduled report time.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SendPendingAttributionReportsResult extends CdpObject {
        private SendPendingAttributionReportsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SendPendingAttributionReportsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SendPendingAttributionReportsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The number of reports that were sent.
         * @return the protocol field value
         */
        @Nullable public Long numSent() {
            return numberAsLong(value("numSent"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The number of reports that were sent.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder numSent(@Nullable Long value) {
                if (value == null) values.remove("numSent");
                else values.put("numSent", jsonValue(value));
                return this;
            }
            public SendPendingAttributionReportsResult build() {
                if (!values.containsKey("numSent")) throw new IllegalStateException("Missing required CDP field: numSent");
                return new SendPendingAttributionReportsResult(values);
            }
        }
    }
    /**
     * Returns the effective Related Website Sets in use by this profile for the browser session. The effective Related Website Sets will not change during a browser session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRelatedWebsiteSetsParams extends CdpObject {
        private GetRelatedWebsiteSetsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetRelatedWebsiteSetsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRelatedWebsiteSetsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetRelatedWebsiteSetsParams build() {
                return new GetRelatedWebsiteSetsParams(values);
            }
        }
    }
    /**
     * Returns the effective Related Website Sets in use by this profile for the browser session. The effective Related Website Sets will not change during a browser session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRelatedWebsiteSetsResult extends CdpObject {
        private GetRelatedWebsiteSetsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetRelatedWebsiteSetsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRelatedWebsiteSetsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sets field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Storage.RelatedWebsiteSet> sets() {
            return list(value("sets"), element0 -> Storage.RelatedWebsiteSet.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sets field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sets(@Nullable java.util.List<Storage.RelatedWebsiteSet> value) {
                if (value == null) values.remove("sets");
                else values.put("sets", jsonValue(value));
                return this;
            }
            public GetRelatedWebsiteSetsResult build() {
                if (!values.containsKey("sets")) throw new IllegalStateException("Missing required CDP field: sets");
                return new GetRelatedWebsiteSetsResult(values);
            }
        }
    }
    /**
     * Returns the list of URLs from a page and its embedded resources that match existing grace period URL pattern rules. https://developers.google.com/privacy-sandbox/cookies/temporary-exceptions/grace-period
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAffectedUrlsForThirdPartyCookieMetadataParams extends CdpObject {
        private GetAffectedUrlsForThirdPartyCookieMetadataParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAffectedUrlsForThirdPartyCookieMetadataParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAffectedUrlsForThirdPartyCookieMetadataParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The URL of the page currently being visited.
         * @return the protocol field value
         */
        @Nullable public String firstPartyUrl() {
            return (String) value("firstPartyUrl");
        }
        /**
         * The list of embedded resource URLs from the page.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> thirdPartyUrls() {
            return list(value("thirdPartyUrls"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The URL of the page currently being visited.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder firstPartyUrl(@Nullable String value) {
                if (value == null) values.remove("firstPartyUrl");
                else values.put("firstPartyUrl", jsonValue(value));
                return this;
            }
            /**
             * The list of embedded resource URLs from the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder thirdPartyUrls(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("thirdPartyUrls");
                else values.put("thirdPartyUrls", jsonValue(value));
                return this;
            }
            public GetAffectedUrlsForThirdPartyCookieMetadataParams build() {
                if (!values.containsKey("firstPartyUrl")) throw new IllegalStateException("Missing required CDP field: firstPartyUrl");
                if (!values.containsKey("thirdPartyUrls")) throw new IllegalStateException("Missing required CDP field: thirdPartyUrls");
                return new GetAffectedUrlsForThirdPartyCookieMetadataParams(values);
            }
        }
    }
    /**
     * Returns the list of URLs from a page and its embedded resources that match existing grace period URL pattern rules. https://developers.google.com/privacy-sandbox/cookies/temporary-exceptions/grace-period
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAffectedUrlsForThirdPartyCookieMetadataResult extends CdpObject {
        private GetAffectedUrlsForThirdPartyCookieMetadataResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAffectedUrlsForThirdPartyCookieMetadataResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAffectedUrlsForThirdPartyCookieMetadataResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of matching URLs. If there is a primary pattern match for the first- party URL, only the first-party URL is returned in the array.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> matchedUrls() {
            return list(value("matchedUrls"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of matching URLs. If there is a primary pattern match for the first- party URL, only the first-party URL is returned in the array.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchedUrls(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("matchedUrls");
                else values.put("matchedUrls", jsonValue(value));
                return this;
            }
            public GetAffectedUrlsForThirdPartyCookieMetadataResult build() {
                if (!values.containsKey("matchedUrls")) throw new IllegalStateException("Missing required CDP field: matchedUrls");
                return new GetAffectedUrlsForThirdPartyCookieMetadataResult(values);
            }
        }
    }
    /**
     * Parameters for Storage.setProtectedAudienceKAnonymity.
     */
    public static final class SetProtectedAudienceKAnonymityParams extends CdpObject {
        private SetProtectedAudienceKAnonymityParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetProtectedAudienceKAnonymityParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetProtectedAudienceKAnonymityParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the owner field.
         * @return the protocol field value
         */
        @Nullable public String owner() {
            return (String) value("owner");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the hashes field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> hashes() {
            return list(value("hashes"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the owner field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder owner(@Nullable String value) {
                if (value == null) values.remove("owner");
                else values.put("owner", jsonValue(value));
                return this;
            }
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
             * Sets the hashes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hashes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("hashes");
                else values.put("hashes", jsonValue(value));
                return this;
            }
            public SetProtectedAudienceKAnonymityParams build() {
                if (!values.containsKey("owner")) throw new IllegalStateException("Missing required CDP field: owner");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("hashes")) throw new IllegalStateException("Missing required CDP field: hashes");
                return new SetProtectedAudienceKAnonymityParams(values);
            }
        }
    }
    /**
     * Result of Storage.setProtectedAudienceKAnonymity.
     */
    public static final class SetProtectedAudienceKAnonymityResult extends CdpObject {
        private SetProtectedAudienceKAnonymityResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetProtectedAudienceKAnonymityResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetProtectedAudienceKAnonymityResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetProtectedAudienceKAnonymityResult build() {
                return new SetProtectedAudienceKAnonymityResult(values);
            }
        }
    }
    /**
     * A cache&#x27;s contents have been modified.
     */
    public static final class CacheStorageContentUpdatedEvent extends CdpObject {
        private CacheStorageContentUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CacheStorageContentUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CacheStorageContentUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        @Nullable public String bucketId() {
            return (String) value("bucketId");
        }
        /**
         * Name of cache in origin.
         * @return the protocol field value
         */
        @Nullable public String cacheName() {
            return (String) value("cacheName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Origin to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Storage key to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Storage bucket to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketId(@Nullable String value) {
                if (value == null) values.remove("bucketId");
                else values.put("bucketId", jsonValue(value));
                return this;
            }
            /**
             * Name of cache in origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheName(@Nullable String value) {
                if (value == null) values.remove("cacheName");
                else values.put("cacheName", jsonValue(value));
                return this;
            }
            public CacheStorageContentUpdatedEvent build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("bucketId")) throw new IllegalStateException("Missing required CDP field: bucketId");
                if (!values.containsKey("cacheName")) throw new IllegalStateException("Missing required CDP field: cacheName");
                return new CacheStorageContentUpdatedEvent(values);
            }
        }
    }
    /**
     * A cache has been added/deleted.
     */
    public static final class CacheStorageListUpdatedEvent extends CdpObject {
        private CacheStorageListUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CacheStorageListUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CacheStorageListUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        @Nullable public String bucketId() {
            return (String) value("bucketId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Origin to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Storage key to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Storage bucket to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketId(@Nullable String value) {
                if (value == null) values.remove("bucketId");
                else values.put("bucketId", jsonValue(value));
                return this;
            }
            public CacheStorageListUpdatedEvent build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("bucketId")) throw new IllegalStateException("Missing required CDP field: bucketId");
                return new CacheStorageListUpdatedEvent(values);
            }
        }
    }
    /**
     * The origin&#x27;s IndexedDB object store has been modified.
     */
    public static final class IndexedDBContentUpdatedEvent extends CdpObject {
        private IndexedDBContentUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static IndexedDBContentUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new IndexedDBContentUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        @Nullable public String bucketId() {
            return (String) value("bucketId");
        }
        /**
         * Database to update.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        /**
         * ObjectStore to update.
         * @return the protocol field value
         */
        @Nullable public String objectStoreName() {
            return (String) value("objectStoreName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Origin to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Storage key to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Storage bucket to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketId(@Nullable String value) {
                if (value == null) values.remove("bucketId");
                else values.put("bucketId", jsonValue(value));
                return this;
            }
            /**
             * Database to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            /**
             * ObjectStore to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStoreName(@Nullable String value) {
                if (value == null) values.remove("objectStoreName");
                else values.put("objectStoreName", jsonValue(value));
                return this;
            }
            public IndexedDBContentUpdatedEvent build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("bucketId")) throw new IllegalStateException("Missing required CDP field: bucketId");
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                if (!values.containsKey("objectStoreName")) throw new IllegalStateException("Missing required CDP field: objectStoreName");
                return new IndexedDBContentUpdatedEvent(values);
            }
        }
    }
    /**
     * The origin&#x27;s IndexedDB database list has been modified.
     */
    public static final class IndexedDBListUpdatedEvent extends CdpObject {
        private IndexedDBListUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static IndexedDBListUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new IndexedDBListUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        @Nullable public String bucketId() {
            return (String) value("bucketId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Origin to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Storage key to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Storage bucket to update.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketId(@Nullable String value) {
                if (value == null) values.remove("bucketId");
                else values.put("bucketId", jsonValue(value));
                return this;
            }
            public IndexedDBListUpdatedEvent build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("bucketId")) throw new IllegalStateException("Missing required CDP field: bucketId");
                return new IndexedDBListUpdatedEvent(values);
            }
        }
    }
    /**
     * One of the interest groups was accessed. Note that these events are global to all targets sharing an interest group store.
     */
    public static final class InterestGroupAccessedEvent extends CdpObject {
        private InterestGroupAccessedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InterestGroupAccessedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InterestGroupAccessedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the accessTime field.
         * @return the protocol field value
         */
        @Nullable public Double accessTime() {
            return numberAsDouble(value("accessTime"));
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin
         * @return the protocol field value
         */
        @Nullable public String componentSellerOrigin() {
            return (String) value("componentSellerOrigin");
        }
        /**
         * For bid or somethingBid event, if done locally and not on a server.
         * @return the protocol field value
         */
        @Nullable public Double bid() {
            return numberAsDouble(value("bid"));
        }
        /**
         * Returns the bidCurrency field.
         * @return the protocol field value
         */
        @Nullable public String bidCurrency() {
            return (String) value("bidCurrency");
        }
        /**
         * For non-global events --- links to interestGroupAuctionEvent
         * @return the protocol field value
         */
        @Nullable public String uniqueAuctionId() {
            return (String) value("uniqueAuctionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the accessTime field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accessTime(@Nullable Double value) {
                if (value == null) values.remove("accessTime");
                else values.put("accessTime", jsonValue(value));
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
             * Sets the ownerOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
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
             * For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder componentSellerOrigin(@Nullable String value) {
                if (value == null) values.remove("componentSellerOrigin");
                else values.put("componentSellerOrigin", jsonValue(value));
                return this;
            }
            /**
             * For bid or somethingBid event, if done locally and not on a server.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bid(@Nullable Double value) {
                if (value == null) values.remove("bid");
                else values.put("bid", jsonValue(value));
                return this;
            }
            /**
             * Sets the bidCurrency field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bidCurrency(@Nullable String value) {
                if (value == null) values.remove("bidCurrency");
                else values.put("bidCurrency", jsonValue(value));
                return this;
            }
            /**
             * For non-global events --- links to interestGroupAuctionEvent
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uniqueAuctionId(@Nullable String value) {
                if (value == null) values.remove("uniqueAuctionId");
                else values.put("uniqueAuctionId", jsonValue(value));
                return this;
            }
            public InterestGroupAccessedEvent build() {
                if (!values.containsKey("accessTime")) throw new IllegalStateException("Missing required CDP field: accessTime");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new InterestGroupAccessedEvent(values);
            }
        }
    }
    /**
     * An auction involving interest groups is taking place. These events are target-specific.
     */
    public static final class InterestGroupAuctionEventOccurredEvent extends CdpObject {
        private InterestGroupAuctionEventOccurredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InterestGroupAuctionEventOccurredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InterestGroupAuctionEventOccurredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the eventTime field.
         * @return the protocol field value
         */
        @Nullable public Double eventTime() {
            return numberAsDouble(value("eventTime"));
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the uniqueAuctionId field.
         * @return the protocol field value
         */
        @Nullable public String uniqueAuctionId() {
            return (String) value("uniqueAuctionId");
        }
        /**
         * Set for child auctions.
         * @return the protocol field value
         */
        @Nullable public String parentAuctionId() {
            return (String) value("parentAuctionId");
        }
        /**
         * Set for started and configResolved
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> auctionConfig() {
            return objectMap(value("auctionConfig"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the eventTime field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventTime(@Nullable Double value) {
                if (value == null) values.remove("eventTime");
                else values.put("eventTime", jsonValue(value));
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
             * Sets the uniqueAuctionId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uniqueAuctionId(@Nullable String value) {
                if (value == null) values.remove("uniqueAuctionId");
                else values.put("uniqueAuctionId", jsonValue(value));
                return this;
            }
            /**
             * Set for child auctions.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentAuctionId(@Nullable String value) {
                if (value == null) values.remove("parentAuctionId");
                else values.put("parentAuctionId", jsonValue(value));
                return this;
            }
            /**
             * Set for started and configResolved
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder auctionConfig(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("auctionConfig");
                else values.put("auctionConfig", jsonValue(value));
                return this;
            }
            public InterestGroupAuctionEventOccurredEvent build() {
                if (!values.containsKey("eventTime")) throw new IllegalStateException("Missing required CDP field: eventTime");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("uniqueAuctionId")) throw new IllegalStateException("Missing required CDP field: uniqueAuctionId");
                return new InterestGroupAuctionEventOccurredEvent(values);
            }
        }
    }
    /**
     * Specifies which auctions a particular network fetch may be related to, and in what role. Note that it is not ordered with respect to Network.requestWillBeSent (but will happen before loadingFinished loadingFailed).
     */
    public static final class InterestGroupAuctionNetworkRequestCreatedEvent extends CdpObject {
        private InterestGroupAuctionNetworkRequestCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InterestGroupAuctionNetworkRequestCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InterestGroupAuctionNetworkRequestCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
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
         * This is the set of the auctions using the worklet that issued this request. In the case of trusted signals, it&#x27;s possible that only some of them actually care about the keys being queried.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> auctions() {
            return list(value("auctions"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
             * This is the set of the auctions using the worklet that issued this request. In the case of trusted signals, it&#x27;s possible that only some of them actually care about the keys being queried.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder auctions(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("auctions");
                else values.put("auctions", jsonValue(value));
                return this;
            }
            public InterestGroupAuctionNetworkRequestCreatedEvent build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("auctions")) throw new IllegalStateException("Missing required CDP field: auctions");
                return new InterestGroupAuctionNetworkRequestCreatedEvent(values);
            }
        }
    }
    /**
     * Shared storage was accessed by the associated page. The following parameters are included in all events.
     */
    public static final class SharedStorageAccessedEvent extends CdpObject {
        private SharedStorageAccessedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageAccessedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageAccessedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Time of the access.
         * @return the protocol field value
         */
        @Nullable public Double accessTime() {
            return numberAsDouble(value("accessTime"));
        }
        /**
         * Enum value indicating the access scope.
         * @return the protocol field value
         */
        @Nullable public String scope() {
            return (String) value("scope");
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @return the protocol field value
         */
        @Nullable public String method() {
            return (String) value("method");
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @return the protocol field value
         */
        @Nullable public String mainFrameId() {
            return (String) value("mainFrameId");
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        /**
         * Serialization of the site owning the Shared Storage data.
         * @return the protocol field value
         */
        @Nullable public String ownerSite() {
            return (String) value("ownerSite");
        }
        /**
         * The sub-parameters wrapped by {@code params} are all optional and their presence/absence depends on {@code type}.
         * @return the protocol field value
         */
        @Nullable public Storage.SharedStorageAccessParams params() {
            return Storage.SharedStorageAccessParams.fromMap(objectMap(value("params")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Time of the access.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accessTime(@Nullable Double value) {
                if (value == null) values.remove("accessTime");
                else values.put("accessTime", jsonValue(value));
                return this;
            }
            /**
             * Enum value indicating the access scope.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scope(@Nullable String value) {
                if (value == null) values.remove("scope");
                else values.put("scope", jsonValue(value));
                return this;
            }
            /**
             * Enum value indicating the Shared Storage API method invoked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder method(@Nullable String value) {
                if (value == null) values.remove("method");
                else values.put("method", jsonValue(value));
                return this;
            }
            /**
             * DevTools Frame Token for the primary frame tree&#x27;s root.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mainFrameId(@Nullable String value) {
                if (value == null) values.remove("mainFrameId");
                else values.put("mainFrameId", jsonValue(value));
                return this;
            }
            /**
             * Serialization of the origin owning the Shared Storage data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            /**
             * Serialization of the site owning the Shared Storage data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerSite(@Nullable String value) {
                if (value == null) values.remove("ownerSite");
                else values.put("ownerSite", jsonValue(value));
                return this;
            }
            /**
             * The sub-parameters wrapped by {@code params} are all optional and their presence/absence depends on {@code type}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder params(@Nullable Storage.SharedStorageAccessParams value) {
                if (value == null) values.remove("params");
                else values.put("params", jsonValue(value));
                return this;
            }
            public SharedStorageAccessedEvent build() {
                if (!values.containsKey("accessTime")) throw new IllegalStateException("Missing required CDP field: accessTime");
                if (!values.containsKey("scope")) throw new IllegalStateException("Missing required CDP field: scope");
                if (!values.containsKey("method")) throw new IllegalStateException("Missing required CDP field: method");
                if (!values.containsKey("mainFrameId")) throw new IllegalStateException("Missing required CDP field: mainFrameId");
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                if (!values.containsKey("ownerSite")) throw new IllegalStateException("Missing required CDP field: ownerSite");
                if (!values.containsKey("params")) throw new IllegalStateException("Missing required CDP field: params");
                return new SharedStorageAccessedEvent(values);
            }
        }
    }
    /**
     * A shared storage run or selectURL operation finished its execution. The following parameters are included in all events.
     */
    public static final class SharedStorageWorkletOperationExecutionFinishedEvent extends CdpObject {
        private SharedStorageWorkletOperationExecutionFinishedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SharedStorageWorkletOperationExecutionFinishedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedStorageWorkletOperationExecutionFinishedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Time that the operation finished.
         * @return the protocol field value
         */
        @Nullable public Double finishedTime() {
            return numberAsDouble(value("finishedTime"));
        }
        /**
         * Time, in microseconds, from start of shared storage JS API call until end of operation execution in the worklet.
         * @return the protocol field value
         */
        @Nullable public Long executionTime() {
            return numberAsLong(value("executionTime"));
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @return the protocol field value
         */
        @Nullable public String method() {
            return (String) value("method");
        }
        /**
         * ID of the operation call.
         * @return the protocol field value
         */
        @Nullable public String operationId() {
            return (String) value("operationId");
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet.
         * @return the protocol field value
         */
        @Nullable public String workletTargetId() {
            return (String) value("workletTargetId");
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @return the protocol field value
         */
        @Nullable public String mainFrameId() {
            return (String) value("mainFrameId");
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @return the protocol field value
         */
        @Nullable public String ownerOrigin() {
            return (String) value("ownerOrigin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Time that the operation finished.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder finishedTime(@Nullable Double value) {
                if (value == null) values.remove("finishedTime");
                else values.put("finishedTime", jsonValue(value));
                return this;
            }
            /**
             * Time, in microseconds, from start of shared storage JS API call until end of operation execution in the worklet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionTime(@Nullable Long value) {
                if (value == null) values.remove("executionTime");
                else values.put("executionTime", jsonValue(value));
                return this;
            }
            /**
             * Enum value indicating the Shared Storage API method invoked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder method(@Nullable String value) {
                if (value == null) values.remove("method");
                else values.put("method", jsonValue(value));
                return this;
            }
            /**
             * ID of the operation call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder operationId(@Nullable String value) {
                if (value == null) values.remove("operationId");
                else values.put("operationId", jsonValue(value));
                return this;
            }
            /**
             * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workletTargetId(@Nullable String value) {
                if (value == null) values.remove("workletTargetId");
                else values.put("workletTargetId", jsonValue(value));
                return this;
            }
            /**
             * DevTools Frame Token for the primary frame tree&#x27;s root.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mainFrameId(@Nullable String value) {
                if (value == null) values.remove("mainFrameId");
                else values.put("mainFrameId", jsonValue(value));
                return this;
            }
            /**
             * Serialization of the origin owning the Shared Storage data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerOrigin(@Nullable String value) {
                if (value == null) values.remove("ownerOrigin");
                else values.put("ownerOrigin", jsonValue(value));
                return this;
            }
            public SharedStorageWorkletOperationExecutionFinishedEvent build() {
                if (!values.containsKey("finishedTime")) throw new IllegalStateException("Missing required CDP field: finishedTime");
                if (!values.containsKey("executionTime")) throw new IllegalStateException("Missing required CDP field: executionTime");
                if (!values.containsKey("method")) throw new IllegalStateException("Missing required CDP field: method");
                if (!values.containsKey("operationId")) throw new IllegalStateException("Missing required CDP field: operationId");
                if (!values.containsKey("workletTargetId")) throw new IllegalStateException("Missing required CDP field: workletTargetId");
                if (!values.containsKey("mainFrameId")) throw new IllegalStateException("Missing required CDP field: mainFrameId");
                if (!values.containsKey("ownerOrigin")) throw new IllegalStateException("Missing required CDP field: ownerOrigin");
                return new SharedStorageWorkletOperationExecutionFinishedEvent(values);
            }
        }
    }
    /**
     * Payload of the Storage.storageBucketCreatedOrUpdated event.
     */
    public static final class StorageBucketCreatedOrUpdatedEvent extends CdpObject {
        private StorageBucketCreatedOrUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static StorageBucketCreatedOrUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StorageBucketCreatedOrUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the bucketInfo field.
         * @return the protocol field value
         */
        @Nullable public Storage.StorageBucketInfo bucketInfo() {
            return Storage.StorageBucketInfo.fromMap(objectMap(value("bucketInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the bucketInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketInfo(@Nullable Storage.StorageBucketInfo value) {
                if (value == null) values.remove("bucketInfo");
                else values.put("bucketInfo", jsonValue(value));
                return this;
            }
            public StorageBucketCreatedOrUpdatedEvent build() {
                if (!values.containsKey("bucketInfo")) throw new IllegalStateException("Missing required CDP field: bucketInfo");
                return new StorageBucketCreatedOrUpdatedEvent(values);
            }
        }
    }
    /**
     * Payload of the Storage.storageBucketDeleted event.
     */
    public static final class StorageBucketDeletedEvent extends CdpObject {
        private StorageBucketDeletedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static StorageBucketDeletedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StorageBucketDeletedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the bucketId field.
         * @return the protocol field value
         */
        @Nullable public String bucketId() {
            return (String) value("bucketId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the bucketId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketId(@Nullable String value) {
                if (value == null) values.remove("bucketId");
                else values.put("bucketId", jsonValue(value));
                return this;
            }
            public StorageBucketDeletedEvent build() {
                if (!values.containsKey("bucketId")) throw new IllegalStateException("Missing required CDP field: bucketId");
                return new StorageBucketDeletedEvent(values);
            }
        }
    }
    /**
     * Payload of the Storage.attributionReportingSourceRegistered event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingSourceRegisteredEvent extends CdpObject {
        private AttributionReportingSourceRegisteredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingSourceRegisteredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingSourceRegisteredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the registration field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingSourceRegistration registration() {
            return Storage.AttributionReportingSourceRegistration.fromMap(objectMap(value("registration")));
        }
        /**
         * Returns the result field.
         * @return the protocol field value
         */
        @Nullable public String result() {
            return (String) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the registration field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registration(@Nullable Storage.AttributionReportingSourceRegistration value) {
                if (value == null) values.remove("registration");
                else values.put("registration", jsonValue(value));
                return this;
            }
            /**
             * Sets the result field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable String value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public AttributionReportingSourceRegisteredEvent build() {
                if (!values.containsKey("registration")) throw new IllegalStateException("Missing required CDP field: registration");
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new AttributionReportingSourceRegisteredEvent(values);
            }
        }
    }
    /**
     * Payload of the Storage.attributionReportingTriggerRegistered event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingTriggerRegisteredEvent extends CdpObject {
        private AttributionReportingTriggerRegisteredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingTriggerRegisteredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingTriggerRegisteredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the registration field.
         * @return the protocol field value
         */
        @Nullable public Storage.AttributionReportingTriggerRegistration registration() {
            return Storage.AttributionReportingTriggerRegistration.fromMap(objectMap(value("registration")));
        }
        /**
         * Returns the eventLevel field.
         * @return the protocol field value
         */
        @Nullable public String eventLevel() {
            return (String) value("eventLevel");
        }
        /**
         * Returns the aggregatable field.
         * @return the protocol field value
         */
        @Nullable public String aggregatable() {
            return (String) value("aggregatable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the registration field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registration(@Nullable Storage.AttributionReportingTriggerRegistration value) {
                if (value == null) values.remove("registration");
                else values.put("registration", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventLevel field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventLevel(@Nullable String value) {
                if (value == null) values.remove("eventLevel");
                else values.put("eventLevel", jsonValue(value));
                return this;
            }
            /**
             * Sets the aggregatable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder aggregatable(@Nullable String value) {
                if (value == null) values.remove("aggregatable");
                else values.put("aggregatable", jsonValue(value));
                return this;
            }
            public AttributionReportingTriggerRegisteredEvent build() {
                if (!values.containsKey("registration")) throw new IllegalStateException("Missing required CDP field: registration");
                if (!values.containsKey("eventLevel")) throw new IllegalStateException("Missing required CDP field: eventLevel");
                if (!values.containsKey("aggregatable")) throw new IllegalStateException("Missing required CDP field: aggregatable");
                return new AttributionReportingTriggerRegisteredEvent(values);
            }
        }
    }
    /**
     * Payload of the Storage.attributionReportingReportSent event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingReportSentEvent extends CdpObject {
        private AttributionReportingReportSentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingReportSentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingReportSentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the body field.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> body() {
            return objectMap(value("body"));
        }
        /**
         * Returns the result field.
         * @return the protocol field value
         */
        @Nullable public String result() {
            return (String) value("result");
        }
        /**
         * If result is {@code sent}, populated with net/HTTP status.
         * @return the protocol field value
         */
        @Nullable public Long netError() {
            return numberAsLong(value("netError"));
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
        @Nullable public Long httpStatusCode() {
            return numberAsLong(value("httpStatusCode"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
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
             * Sets the result field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable String value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * If result is {@code sent}, populated with net/HTTP status.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder netError(@Nullable Long value) {
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
            public Builder httpStatusCode(@Nullable Long value) {
                if (value == null) values.remove("httpStatusCode");
                else values.put("httpStatusCode", jsonValue(value));
                return this;
            }
            public AttributionReportingReportSentEvent build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("body")) throw new IllegalStateException("Missing required CDP field: body");
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new AttributionReportingReportSentEvent(values);
            }
        }
    }
    /**
     * Payload of the Storage.attributionReportingVerboseDebugReportSent event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttributionReportingVerboseDebugReportSentEvent extends CdpObject {
        private AttributionReportingVerboseDebugReportSentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingVerboseDebugReportSentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingVerboseDebugReportSentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the body field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.Map<String, Object>> body() {
            return list(value("body"), element0 -> objectMap(element0));
        }
        /**
         * Returns the netError field.
         * @return the protocol field value
         */
        @Nullable public Long netError() {
            return numberAsLong(value("netError"));
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
        @Nullable public Long httpStatusCode() {
            return numberAsLong(value("httpStatusCode"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the body field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder body(@Nullable java.util.List<java.util.Map<String, Object>> value) {
                if (value == null) values.remove("body");
                else values.put("body", jsonValue(value));
                return this;
            }
            /**
             * Sets the netError field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder netError(@Nullable Long value) {
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
            public Builder httpStatusCode(@Nullable Long value) {
                if (value == null) values.remove("httpStatusCode");
                else values.put("httpStatusCode", jsonValue(value));
                return this;
            }
            public AttributionReportingVerboseDebugReportSentEvent build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new AttributionReportingVerboseDebugReportSentEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetStorageKeyForFrameResult> getStorageKeyForFrame(GetStorageKeyForFrameParams params) {
            return client.call("Storage.getStorageKeyForFrame", params, GetStorageKeyForFrameResult::fromMap);
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetStorageKeyResult> getStorageKey(GetStorageKeyParams params) {
            return client.call("Storage.getStorageKey", params, GetStorageKeyResult::fromMap);
        }
        /**
         * Clears storage for origin.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearDataForOriginResult> clearDataForOrigin(ClearDataForOriginParams params) {
            return client.call("Storage.clearDataForOrigin", params, ClearDataForOriginResult::fromMap);
        }
        /**
         * Clears storage for storage key.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearDataForStorageKeyResult> clearDataForStorageKey(ClearDataForStorageKeyParams params) {
            return client.call("Storage.clearDataForStorageKey", params, ClearDataForStorageKeyResult::fromMap);
        }
        /**
         * Returns all browser cookies.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCookiesResult> getCookies(GetCookiesParams params) {
            return client.call("Storage.getCookies", params, GetCookiesResult::fromMap);
        }
        /**
         * Sets given cookies.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCookiesResult> setCookies(SetCookiesParams params) {
            return client.call("Storage.setCookies", params, SetCookiesResult::fromMap);
        }
        /**
         * Clears cookies.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearCookiesResult> clearCookies(ClearCookiesParams params) {
            return client.call("Storage.clearCookies", params, ClearCookiesResult::fromMap);
        }
        /**
         * Returns usage and quota in bytes.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetUsageAndQuotaResult> getUsageAndQuota(GetUsageAndQuotaParams params) {
            return client.call("Storage.getUsageAndQuota", params, GetUsageAndQuotaResult::fromMap);
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<OverrideQuotaForOriginResult> overrideQuotaForOrigin(OverrideQuotaForOriginParams params) {
            return client.call("Storage.overrideQuotaForOrigin", params, OverrideQuotaForOriginResult::fromMap);
        }
        /**
         * Registers origin to be notified when an update occurs to its cache storage list.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackCacheStorageForOriginResult> trackCacheStorageForOrigin(TrackCacheStorageForOriginParams params) {
            return client.call("Storage.trackCacheStorageForOrigin", params, TrackCacheStorageForOriginResult::fromMap);
        }
        /**
         * Registers storage key to be notified when an update occurs to its cache storage list.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackCacheStorageForStorageKeyResult> trackCacheStorageForStorageKey(TrackCacheStorageForStorageKeyParams params) {
            return client.call("Storage.trackCacheStorageForStorageKey", params, TrackCacheStorageForStorageKeyResult::fromMap);
        }
        /**
         * Registers origin to be notified when an update occurs to its IndexedDB.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackIndexedDBForOriginResult> trackIndexedDBForOrigin(TrackIndexedDBForOriginParams params) {
            return client.call("Storage.trackIndexedDBForOrigin", params, TrackIndexedDBForOriginResult::fromMap);
        }
        /**
         * Registers storage key to be notified when an update occurs to its IndexedDB.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackIndexedDBForStorageKeyResult> trackIndexedDBForStorageKey(TrackIndexedDBForStorageKeyParams params) {
            return client.call("Storage.trackIndexedDBForStorageKey", params, TrackIndexedDBForStorageKeyResult::fromMap);
        }
        /**
         * Unregisters origin from receiving notifications for cache storage.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UntrackCacheStorageForOriginResult> untrackCacheStorageForOrigin(UntrackCacheStorageForOriginParams params) {
            return client.call("Storage.untrackCacheStorageForOrigin", params, UntrackCacheStorageForOriginResult::fromMap);
        }
        /**
         * Unregisters storage key from receiving notifications for cache storage.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UntrackCacheStorageForStorageKeyResult> untrackCacheStorageForStorageKey(UntrackCacheStorageForStorageKeyParams params) {
            return client.call("Storage.untrackCacheStorageForStorageKey", params, UntrackCacheStorageForStorageKeyResult::fromMap);
        }
        /**
         * Unregisters origin from receiving notifications for IndexedDB.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UntrackIndexedDBForOriginResult> untrackIndexedDBForOrigin(UntrackIndexedDBForOriginParams params) {
            return client.call("Storage.untrackIndexedDBForOrigin", params, UntrackIndexedDBForOriginResult::fromMap);
        }
        /**
         * Unregisters storage key from receiving notifications for IndexedDB.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UntrackIndexedDBForStorageKeyResult> untrackIndexedDBForStorageKey(UntrackIndexedDBForStorageKeyParams params) {
            return client.call("Storage.untrackIndexedDBForStorageKey", params, UntrackIndexedDBForStorageKeyResult::fromMap);
        }
        /**
         * Returns the number of stored Trust Tokens per issuer for the current browsing context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetTrustTokensResult> getTrustTokens() {
            return client.call("Storage.getTrustTokens", null, GetTrustTokensResult::fromMap);
        }
        /**
         * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearTrustTokensResult> clearTrustTokens(ClearTrustTokensParams params) {
            return client.call("Storage.clearTrustTokens", params, ClearTrustTokensResult::fromMap);
        }
        /**
         * Gets details for a named interest group.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInterestGroupDetailsResult> getInterestGroupDetails(GetInterestGroupDetailsParams params) {
            return client.call("Storage.getInterestGroupDetails", params, GetInterestGroupDetailsResult::fromMap);
        }
        /**
         * Enables/Disables issuing of interestGroupAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInterestGroupTrackingResult> setInterestGroupTracking(SetInterestGroupTrackingParams params) {
            return client.call("Storage.setInterestGroupTracking", params, SetInterestGroupTrackingResult::fromMap);
        }
        /**
         * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInterestGroupAuctionTrackingResult> setInterestGroupAuctionTracking(SetInterestGroupAuctionTrackingParams params) {
            return client.call("Storage.setInterestGroupAuctionTracking", params, SetInterestGroupAuctionTrackingResult::fromMap);
        }
        /**
         * Gets metadata for an origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSharedStorageMetadataResult> getSharedStorageMetadata(GetSharedStorageMetadataParams params) {
            return client.call("Storage.getSharedStorageMetadata", params, GetSharedStorageMetadataResult::fromMap);
        }
        /**
         * Gets the entries in an given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSharedStorageEntriesResult> getSharedStorageEntries(GetSharedStorageEntriesParams params) {
            return client.call("Storage.getSharedStorageEntries", params, GetSharedStorageEntriesResult::fromMap);
        }
        /**
         * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSharedStorageEntryResult> setSharedStorageEntry(SetSharedStorageEntryParams params) {
            return client.call("Storage.setSharedStorageEntry", params, SetSharedStorageEntryResult::fromMap);
        }
        /**
         * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteSharedStorageEntryResult> deleteSharedStorageEntry(DeleteSharedStorageEntryParams params) {
            return client.call("Storage.deleteSharedStorageEntry", params, DeleteSharedStorageEntryResult::fromMap);
        }
        /**
         * Clears all entries for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearSharedStorageEntriesResult> clearSharedStorageEntries(ClearSharedStorageEntriesParams params) {
            return client.call("Storage.clearSharedStorageEntries", params, ClearSharedStorageEntriesResult::fromMap);
        }
        /**
         * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResetSharedStorageBudgetResult> resetSharedStorageBudget(ResetSharedStorageBudgetParams params) {
            return client.call("Storage.resetSharedStorageBudget", params, ResetSharedStorageBudgetResult::fromMap);
        }
        /**
         * Enables/disables issuing of sharedStorageAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSharedStorageTrackingResult> setSharedStorageTracking(SetSharedStorageTrackingParams params) {
            return client.call("Storage.setSharedStorageTracking", params, SetSharedStorageTrackingResult::fromMap);
        }
        /**
         * Set tracking for a storage key&#x27;s buckets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetStorageBucketTrackingResult> setStorageBucketTracking(SetStorageBucketTrackingParams params) {
            return client.call("Storage.setStorageBucketTracking", params, SetStorageBucketTrackingResult::fromMap);
        }
        /**
         * Deletes the Storage Bucket with the given storage key and bucket name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteStorageBucketResult> deleteStorageBucket(DeleteStorageBucketParams params) {
            return client.call("Storage.deleteStorageBucket", params, DeleteStorageBucketResult::fromMap);
        }
        /**
         * Deletes state for sites identified as potential bounce trackers, immediately.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<RunBounceTrackingMitigationsResult> runBounceTrackingMitigations() {
            return client.call("Storage.runBounceTrackingMitigations", null, RunBounceTrackingMitigationsResult::fromMap);
        }
        /**
         * https://wicg.github.io/attribution-reporting-api/
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAttributionReportingLocalTestingModeResult> setAttributionReportingLocalTestingMode(SetAttributionReportingLocalTestingModeParams params) {
            return client.call("Storage.setAttributionReportingLocalTestingMode", params, SetAttributionReportingLocalTestingModeResult::fromMap);
        }
        /**
         * Enables/disables issuing of Attribution Reporting events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAttributionReportingTrackingResult> setAttributionReportingTracking(SetAttributionReportingTrackingParams params) {
            return client.call("Storage.setAttributionReportingTracking", params, SetAttributionReportingTrackingResult::fromMap);
        }
        /**
         * Sends all pending Attribution Reports immediately, regardless of their scheduled report time.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<SendPendingAttributionReportsResult> sendPendingAttributionReports() {
            return client.call("Storage.sendPendingAttributionReports", null, SendPendingAttributionReportsResult::fromMap);
        }
        /**
         * Returns the effective Related Website Sets in use by this profile for the browser session. The effective Related Website Sets will not change during a browser session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRelatedWebsiteSetsResult> getRelatedWebsiteSets() {
            return client.call("Storage.getRelatedWebsiteSets", null, GetRelatedWebsiteSetsResult::fromMap);
        }
        /**
         * Returns the list of URLs from a page and its embedded resources that match existing grace period URL pattern rules. https://developers.google.com/privacy-sandbox/cookies/temporary-exceptions/grace-period
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAffectedUrlsForThirdPartyCookieMetadataResult> getAffectedUrlsForThirdPartyCookieMetadata(GetAffectedUrlsForThirdPartyCookieMetadataParams params) {
            return client.call("Storage.getAffectedUrlsForThirdPartyCookieMetadata", params, GetAffectedUrlsForThirdPartyCookieMetadataResult::fromMap);
        }
        /**
         * Invokes Storage.setProtectedAudienceKAnonymity.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetProtectedAudienceKAnonymityResult> setProtectedAudienceKAnonymity(SetProtectedAudienceKAnonymityParams params) {
            return client.call("Storage.setProtectedAudienceKAnonymity", params, SetProtectedAudienceKAnonymityResult::fromMap);
        }
        /**
         * A cache&#x27;s contents have been modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCacheStorageContentUpdated(Consumer<CacheStorageContentUpdatedEvent> handler) {
            return client.on("Storage.cacheStorageContentUpdated", CacheStorageContentUpdatedEvent::fromMap, handler);
        }
        /**
         * A cache has been added/deleted.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCacheStorageListUpdated(Consumer<CacheStorageListUpdatedEvent> handler) {
            return client.on("Storage.cacheStorageListUpdated", CacheStorageListUpdatedEvent::fromMap, handler);
        }
        /**
         * The origin&#x27;s IndexedDB object store has been modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIndexedDBContentUpdated(Consumer<IndexedDBContentUpdatedEvent> handler) {
            return client.on("Storage.indexedDBContentUpdated", IndexedDBContentUpdatedEvent::fromMap, handler);
        }
        /**
         * The origin&#x27;s IndexedDB database list has been modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIndexedDBListUpdated(Consumer<IndexedDBListUpdatedEvent> handler) {
            return client.on("Storage.indexedDBListUpdated", IndexedDBListUpdatedEvent::fromMap, handler);
        }
        /**
         * One of the interest groups was accessed. Note that these events are global to all targets sharing an interest group store.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterestGroupAccessed(Consumer<InterestGroupAccessedEvent> handler) {
            return client.on("Storage.interestGroupAccessed", InterestGroupAccessedEvent::fromMap, handler);
        }
        /**
         * An auction involving interest groups is taking place. These events are target-specific.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterestGroupAuctionEventOccurred(Consumer<InterestGroupAuctionEventOccurredEvent> handler) {
            return client.on("Storage.interestGroupAuctionEventOccurred", InterestGroupAuctionEventOccurredEvent::fromMap, handler);
        }
        /**
         * Specifies which auctions a particular network fetch may be related to, and in what role. Note that it is not ordered with respect to Network.requestWillBeSent (but will happen before loadingFinished loadingFailed).
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterestGroupAuctionNetworkRequestCreated(Consumer<InterestGroupAuctionNetworkRequestCreatedEvent> handler) {
            return client.on("Storage.interestGroupAuctionNetworkRequestCreated", InterestGroupAuctionNetworkRequestCreatedEvent::fromMap, handler);
        }
        /**
         * Shared storage was accessed by the associated page. The following parameters are included in all events.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSharedStorageAccessed(Consumer<SharedStorageAccessedEvent> handler) {
            return client.on("Storage.sharedStorageAccessed", SharedStorageAccessedEvent::fromMap, handler);
        }
        /**
         * A shared storage run or selectURL operation finished its execution. The following parameters are included in all events.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSharedStorageWorkletOperationExecutionFinished(Consumer<SharedStorageWorkletOperationExecutionFinishedEvent> handler) {
            return client.on("Storage.sharedStorageWorkletOperationExecutionFinished", SharedStorageWorkletOperationExecutionFinishedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.storageBucketCreatedOrUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStorageBucketCreatedOrUpdated(Consumer<StorageBucketCreatedOrUpdatedEvent> handler) {
            return client.on("Storage.storageBucketCreatedOrUpdated", StorageBucketCreatedOrUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.storageBucketDeleted.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStorageBucketDeleted(Consumer<StorageBucketDeletedEvent> handler) {
            return client.on("Storage.storageBucketDeleted", StorageBucketDeletedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.attributionReportingSourceRegistered.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributionReportingSourceRegistered(Consumer<AttributionReportingSourceRegisteredEvent> handler) {
            return client.on("Storage.attributionReportingSourceRegistered", AttributionReportingSourceRegisteredEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.attributionReportingTriggerRegistered.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributionReportingTriggerRegistered(Consumer<AttributionReportingTriggerRegisteredEvent> handler) {
            return client.on("Storage.attributionReportingTriggerRegistered", AttributionReportingTriggerRegisteredEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.attributionReportingReportSent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributionReportingReportSent(Consumer<AttributionReportingReportSentEvent> handler) {
            return client.on("Storage.attributionReportingReportSent", AttributionReportingReportSentEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.attributionReportingVerboseDebugReportSent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributionReportingVerboseDebugReportSent(Consumer<AttributionReportingVerboseDebugReportSentEvent> handler) {
            return client.on("Storage.attributionReportingVerboseDebugReportSent", AttributionReportingVerboseDebugReportSentEvent::fromMap, handler);
        }
    }
}
