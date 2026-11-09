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

/**
 * Chrome DevTools Protocol Storage domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Storage.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Storage {
    private Storage() {}
    private static final CdpField<Network.TimeSinceEpoch> ACCESS_TIME = CdpField.of("accessTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<java.util.List<String>> ASSOCIATED_SITES = CdpField.of("associatedSites", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<Storage.InterestGroupAuctionId>> AUCTIONS = CdpField.of("auctions", value -> CdpObject.requireList(value, element0 -> new Storage.InterestGroupAuctionId((String) element0)));
    private static final CdpField<Browser.BrowserContextID> BROWSER_CONTEXT_ID = CdpField.of("browserContextId", value -> new Browser.BrowserContextID((String) value));
    private static final CdpField<Storage.StorageBucket> BUCKET = CdpField.of("bucket", value -> java.util.Objects.requireNonNull(Storage.StorageBucket.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Storage.StorageBucketInfo> BUCKET_INFO = CdpField.of("bucketInfo", value -> java.util.Objects.requireNonNull(Storage.StorageBucketInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.CookieParam>> COOKIES = CdpField.of("cookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.CookieParam.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.Cookie>> COOKIES_2 = CdpField.of("cookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.TimeSinceEpoch> CREATION_TIME = CdpField.of("creationTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<java.util.List<String>> DELETED_SITES = CdpField.of("deletedSites", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.Map<String, Object>> DETAILS = CdpField.of("details", value -> CdpObject.requireObjectMap(value));
    private static final CdpField<Boolean> DID_DELETE_TOKENS = CdpField.of("didDeleteTokens", value -> (Boolean) value);
    private static final CdpField<Storage.StorageBucketsDurability> DURABILITY = CdpField.of("durability", value -> Storage.StorageBucketsDurability.of((String) value));
    private static final CdpField<java.util.List<Storage.SharedStorageEntry>> ENTRIES = CdpField.of("entries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.TimeSinceEpoch> EVENT_TIME = CdpField.of("eventTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.TimeSinceEpoch> EXPIRATION = CdpField.of("expiration", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.TimeSinceEpoch> FINISHED_TIME = CdpField.of("finishedTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<java.util.List<String>> HASHES = CdpField.of("hashes", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Page.FrameId> MAIN_FRAME_ID = CdpField.of("mainFrameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Storage.SharedStorageMetadata> METADATA = CdpField.of("metadata", value -> java.util.Objects.requireNonNull(Storage.SharedStorageMetadata.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Storage.SharedStorageAccessMethod> METHOD = CdpField.of("method", value -> Storage.SharedStorageAccessMethod.of((String) value));
    private static final CdpField<Storage.SharedStorageAccessParams> PARAMS = CdpField.of("params", value -> java.util.Objects.requireNonNull(Storage.SharedStorageAccessParams.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Storage.InterestGroupAuctionId> PARENT_AUCTION_ID = CdpField.of("parentAuctionId", value -> new Storage.InterestGroupAuctionId((String) value));
    private static final CdpField<java.util.List<String>> PRIMARY_SITES = CdpField.of("primarySites", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Storage.SharedStoragePrivateAggregationConfig> PRIVATE_AGGREGATION_CONFIG = CdpField.of("privateAggregationConfig", value -> java.util.Objects.requireNonNull(Storage.SharedStoragePrivateAggregationConfig.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Storage.SharedStorageReportingMetadata>> REPORTING_METADATA = CdpField.of("reportingMetadata", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageReportingMetadata.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.RequestId> REQUEST_ID = CdpField.of("requestId", value -> new Network.RequestId((String) value));
    private static final CdpField<Storage.SharedStorageAccessScope> SCOPE = CdpField.of("scope", value -> Storage.SharedStorageAccessScope.of((String) value));
    private static final CdpField<java.util.List<String>> SERVICE_SITES = CdpField.of("serviceSites", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<Storage.RelatedWebsiteSet>> SETS = CdpField.of("sets", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Storage.RelatedWebsiteSet.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Storage.SerializedStorageKey> STORAGE_KEY = CdpField.of("storageKey", value -> new Storage.SerializedStorageKey((String) value));
    private static final CdpField<Storage.StorageType> STORAGE_TYPE = CdpField.of("storageType", value -> Storage.StorageType.of((String) value));
    private static final CdpField<java.util.List<Storage.TrustTokens>> TOKENS = CdpField.of("tokens", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Storage.TrustTokens.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Storage.InterestGroupAccessType> TYPE = CdpField.of("type", value -> Storage.InterestGroupAccessType.of((String) value));
    private static final CdpField<Storage.InterestGroupAuctionEventType> TYPE_2 = CdpField.of("type", value -> Storage.InterestGroupAuctionEventType.of((String) value));
    private static final CdpField<Storage.InterestGroupAuctionFetchType> TYPE_3 = CdpField.of("type", value -> Storage.InterestGroupAuctionFetchType.of((String) value));
    private static final CdpField<Storage.InterestGroupAuctionId> UNIQUE_AUCTION_ID = CdpField.of("uniqueAuctionId", value -> new Storage.InterestGroupAuctionId((String) value));
    private static final CdpField<java.util.List<Storage.SharedStorageUrlWithMetadata>> URLS_WITH_METADATA = CdpField.of("urlsWithMetadata", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageUrlWithMetadata.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Storage.UsageForType>> USAGE_BREAKDOWN = CdpField.of("usageBreakdown", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Storage.UsageForType.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Target.TargetID> WORKLET_TARGET_ID = CdpField.of("workletTargetId", value -> new Target.TargetID((String) value));
    /**
     * Tagged String wire value for SerializedStorageKey.
     */
    public static final class SerializedStorageKey implements CdpValue<String> {
        public final String value;
        public SerializedStorageKey(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof SerializedStorageKey)) return false;
            return value.equals(((SerializedStorageKey) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "SerializedStorageKey(" + value + ")"; }
    }
    /**
     * Enum of possible storage types.
     */
    public enum StorageType implements CdpValue<String> {
        COOKIES("cookies"),
        FILE_SYSTEMS("file_systems"),
        INDEXEDDB("indexeddb"),
        LOCAL_STORAGE("local_storage"),
        SHADER_CACHE("shader_cache"),
        WEBSQL("websql"),
        SERVICE_WORKERS("service_workers"),
        CACHE_STORAGE("cache_storage"),
        INTEREST_GROUPS("interest_groups"),
        SHARED_STORAGE("shared_storage"),
        STORAGE_BUCKETS("storage_buckets"),
        ALL("all"),
        OTHER("other");
        public final String value;
        StorageType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StorageType of(String value) {
            for (StorageType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StorageType value: " + value);
        }
    }
    /**
     * Usage for a storage type.
     */
    public static final class UsageForType extends CdpObject {
        public UsageForType() {}
        private UsageForType(Map<String, Object> values) { super(values); }
        public static UsageForType fromMap(Map<String, Object> values) {
            return new UsageForType(values);
        }
        /**
         * Name of storage type.
         * @return the protocol field value
         */
        public Storage.StorageType storageType() {
            return require(STORAGE_TYPE);
        }
        /**
         * Storage usage (bytes).
         * @return the protocol field value
         */
        public double usage() {
            return requireDouble("usage");
        }
        /**
         * Name of storage type.
         * @param storageType field value
         * @return this model
         */
        public UsageForType storageType(Storage.StorageType storageType) {
            set("storageType", storageType);
            return this;
        }
        /**
         * Storage usage (bytes).
         * @param usage field value
         * @return this model
         */
        public UsageForType usage(double usage) {
            set("usage", usage);
            return this;
        }
    }
    /**
     * Pair of issuer origin and number of available (signed, but not used) Trust Tokens from that issuer.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokens extends CdpObject {
        public TrustTokens() {}
        private TrustTokens(Map<String, Object> values) { super(values); }
        public static TrustTokens fromMap(Map<String, Object> values) {
            return new TrustTokens(values);
        }
        /**
         * Returns the issuerOrigin field.
         * @return the protocol field value
         */
        public String issuerOrigin() {
            return requireString("issuerOrigin");
        }
        /**
         * Returns the count field.
         * @return the protocol field value
         */
        public double count() {
            return requireDouble("count");
        }
        /**
         * Sets the issuerOrigin field.
         * @param issuerOrigin field value
         * @return this model
         */
        public TrustTokens issuerOrigin(String issuerOrigin) {
            set("issuerOrigin", issuerOrigin);
            return this;
        }
        /**
         * Sets the count field.
         * @param count field value
         * @return this model
         */
        public TrustTokens count(double count) {
            set("count", count);
            return this;
        }
    }
    /**
     * Protected audience interest group auction identifier.
     */
    public static final class InterestGroupAuctionId implements CdpValue<String> {
        public final String value;
        public InterestGroupAuctionId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof InterestGroupAuctionId)) return false;
            return value.equals(((InterestGroupAuctionId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "InterestGroupAuctionId(" + value + ")"; }
    }
    /**
     * Enum of interest group access types.
     */
    public enum InterestGroupAccessType implements CdpValue<String> {
        JOIN("join"),
        LEAVE("leave"),
        UPDATE("update"),
        LOADED("loaded"),
        BID("bid"),
        WIN("win"),
        ADDITIONALBID("additionalBid"),
        ADDITIONALBIDWIN("additionalBidWin"),
        TOPLEVELBID("topLevelBid"),
        TOPLEVELADDITIONALBID("topLevelAdditionalBid"),
        CLEAR("clear");
        public final String value;
        InterestGroupAccessType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InterestGroupAccessType of(String value) {
            for (InterestGroupAccessType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InterestGroupAccessType value: " + value);
        }
    }
    /**
     * Enum of auction events.
     */
    public enum InterestGroupAuctionEventType implements CdpValue<String> {
        STARTED("started"),
        CONFIGRESOLVED("configResolved");
        public final String value;
        InterestGroupAuctionEventType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InterestGroupAuctionEventType of(String value) {
            for (InterestGroupAuctionEventType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InterestGroupAuctionEventType value: " + value);
        }
    }
    /**
     * Enum of network fetches auctions can do.
     */
    public enum InterestGroupAuctionFetchType implements CdpValue<String> {
        BIDDERJS("bidderJs"),
        BIDDERWASM("bidderWasm"),
        SELLERJS("sellerJs"),
        BIDDERTRUSTEDSIGNALS("bidderTrustedSignals"),
        SELLERTRUSTEDSIGNALS("sellerTrustedSignals");
        public final String value;
        InterestGroupAuctionFetchType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InterestGroupAuctionFetchType of(String value) {
            for (InterestGroupAuctionFetchType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InterestGroupAuctionFetchType value: " + value);
        }
    }
    /**
     * Enum of shared storage access scopes.
     */
    public enum SharedStorageAccessScope implements CdpValue<String> {
        WINDOW("window"),
        SHAREDSTORAGEWORKLET("sharedStorageWorklet"),
        PROTECTEDAUDIENCEWORKLET("protectedAudienceWorklet"),
        HEADER("header");
        public final String value;
        SharedStorageAccessScope(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SharedStorageAccessScope of(String value) {
            for (SharedStorageAccessScope constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SharedStorageAccessScope value: " + value);
        }
    }
    /**
     * Enum of shared storage access methods.
     */
    public enum SharedStorageAccessMethod implements CdpValue<String> {
        ADDMODULE("addModule"),
        CREATEWORKLET("createWorklet"),
        SELECTURL("selectURL"),
        RUN("run"),
        BATCHUPDATE("batchUpdate"),
        SET("set"),
        APPEND("append"),
        DELETE("delete"),
        CLEAR("clear"),
        GET("get"),
        KEYS("keys"),
        VALUES("values"),
        ENTRIES("entries"),
        LENGTH("length"),
        REMAININGBUDGET("remainingBudget");
        public final String value;
        SharedStorageAccessMethod(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SharedStorageAccessMethod of(String value) {
            for (SharedStorageAccessMethod constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SharedStorageAccessMethod value: " + value);
        }
    }
    /**
     * Struct for a single key-value pair in an origin&#x27;s shared storage.
     */
    public static final class SharedStorageEntry extends CdpObject {
        public SharedStorageEntry() {}
        private SharedStorageEntry(Map<String, Object> values) { super(values); }
        public static SharedStorageEntry fromMap(Map<String, Object> values) {
            return new SharedStorageEntry(values);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public SharedStorageEntry key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public SharedStorageEntry value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Details for an origin&#x27;s shared storage.
     */
    public static final class SharedStorageMetadata extends CdpObject {
        public SharedStorageMetadata() {}
        private SharedStorageMetadata(Map<String, Object> values) { super(values); }
        public static SharedStorageMetadata fromMap(Map<String, Object> values) {
            return new SharedStorageMetadata(values);
        }
        /**
         * Time when the origin&#x27;s shared storage was last created.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch creationTime() {
            return require(CREATION_TIME);
        }
        /**
         * Number of key-value pairs stored in origin&#x27;s shared storage.
         * @return the protocol field value
         */
        public long length() {
            return requireLong("length");
        }
        /**
         * Current amount of bits of entropy remaining in the navigation budget.
         * @return the protocol field value
         */
        public double remainingBudget() {
            return requireDouble("remainingBudget");
        }
        /**
         * Total number of bytes stored as key-value pairs in origin&#x27;s shared storage.
         * @return the protocol field value
         */
        public long bytesUsed() {
            return requireLong("bytesUsed");
        }
        /**
         * Time when the origin&#x27;s shared storage was last created.
         * @param creationTime field value
         * @return this model
         */
        public SharedStorageMetadata creationTime(Network.TimeSinceEpoch creationTime) {
            set("creationTime", creationTime);
            return this;
        }
        /**
         * Number of key-value pairs stored in origin&#x27;s shared storage.
         * @param length field value
         * @return this model
         */
        public SharedStorageMetadata length(long length) {
            set("length", length);
            return this;
        }
        /**
         * Current amount of bits of entropy remaining in the navigation budget.
         * @param remainingBudget field value
         * @return this model
         */
        public SharedStorageMetadata remainingBudget(double remainingBudget) {
            set("remainingBudget", remainingBudget);
            return this;
        }
        /**
         * Total number of bytes stored as key-value pairs in origin&#x27;s shared storage.
         * @param bytesUsed field value
         * @return this model
         */
        public SharedStorageMetadata bytesUsed(long bytesUsed) {
            set("bytesUsed", bytesUsed);
            return this;
        }
    }
    /**
     * Represents a dictionary object passed in as privateAggregationConfig to run or selectURL.
     */
    public static final class SharedStoragePrivateAggregationConfig extends CdpObject {
        public SharedStoragePrivateAggregationConfig() {}
        private SharedStoragePrivateAggregationConfig(Map<String, Object> values) { super(values); }
        public static SharedStoragePrivateAggregationConfig fromMap(Map<String, Object> values) {
            return new SharedStoragePrivateAggregationConfig(values);
        }
        /**
         * The chosen aggregation service deployment.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> aggregationCoordinatorOrigin() {
            return optionalString("aggregationCoordinatorOrigin");
        }
        /**
         * The context ID provided.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> contextId() {
            return optionalString("contextId");
        }
        /**
         * Configures the maximum size allowed for filtering IDs.
         * @return the protocol field value
         */
        public long filteringIdMaxBytes() {
            return requireLong("filteringIdMaxBytes");
        }
        /**
         * The limit on the number of contributions in the final report.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxContributions() {
            return optionalLong("maxContributions");
        }
        /**
         * The chosen aggregation service deployment.
         * @param aggregationCoordinatorOrigin field value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig aggregationCoordinatorOrigin(String aggregationCoordinatorOrigin) {
            set("aggregationCoordinatorOrigin", aggregationCoordinatorOrigin);
            return this;
        }
        /**
         * The context ID provided.
         * @param contextId field value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig contextId(String contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Configures the maximum size allowed for filtering IDs.
         * @param filteringIdMaxBytes field value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig filteringIdMaxBytes(long filteringIdMaxBytes) {
            set("filteringIdMaxBytes", filteringIdMaxBytes);
            return this;
        }
        /**
         * The limit on the number of contributions in the final report.
         * @param maxContributions field value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig maxContributions(long maxContributions) {
            set("maxContributions", maxContributions);
            return this;
        }
    }
    /**
     * Pair of reporting metadata details for a candidate URL for {@code selectURL()}.
     */
    public static final class SharedStorageReportingMetadata extends CdpObject {
        public SharedStorageReportingMetadata() {}
        private SharedStorageReportingMetadata(Map<String, Object> values) { super(values); }
        public static SharedStorageReportingMetadata fromMap(Map<String, Object> values) {
            return new SharedStorageReportingMetadata(values);
        }
        /**
         * Returns the eventType field.
         * @return the protocol field value
         */
        public String eventType() {
            return requireString("eventType");
        }
        /**
         * Returns the reportingUrl field.
         * @return the protocol field value
         */
        public String reportingUrl() {
            return requireString("reportingUrl");
        }
        /**
         * Sets the eventType field.
         * @param eventType field value
         * @return this model
         */
        public SharedStorageReportingMetadata eventType(String eventType) {
            set("eventType", eventType);
            return this;
        }
        /**
         * Sets the reportingUrl field.
         * @param reportingUrl field value
         * @return this model
         */
        public SharedStorageReportingMetadata reportingUrl(String reportingUrl) {
            set("reportingUrl", reportingUrl);
            return this;
        }
    }
    /**
     * Bundles a candidate URL with its reporting metadata.
     */
    public static final class SharedStorageUrlWithMetadata extends CdpObject {
        public SharedStorageUrlWithMetadata() {}
        private SharedStorageUrlWithMetadata(Map<String, Object> values) { super(values); }
        public static SharedStorageUrlWithMetadata fromMap(Map<String, Object> values) {
            return new SharedStorageUrlWithMetadata(values);
        }
        /**
         * Spec of candidate URL.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Any associated reporting metadata.
         * @return the protocol field value
         */
        public java.util.List<Storage.SharedStorageReportingMetadata> reportingMetadata() {
            return require(REPORTING_METADATA);
        }
        /**
         * Spec of candidate URL.
         * @param url field value
         * @return this model
         */
        public SharedStorageUrlWithMetadata url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Any associated reporting metadata.
         * @param reportingMetadata field value
         * @return this model
         */
        public SharedStorageUrlWithMetadata reportingMetadata(java.util.List<Storage.SharedStorageReportingMetadata> reportingMetadata) {
            set("reportingMetadata", reportingMetadata);
            return this;
        }
    }
    /**
     * Bundles the parameters for shared storage access events whose presence/absence can vary according to SharedStorageAccessType.
     */
    public static final class SharedStorageAccessParams extends CdpObject {
        public SharedStorageAccessParams() {}
        private SharedStorageAccessParams(Map<String, Object> values) { super(values); }
        public static SharedStorageAccessParams fromMap(Map<String, Object> values) {
            return new SharedStorageAccessParams(values);
        }
        /**
         * Spec of the module script URL. Present only for SharedStorageAccessMethods: addModule and createWorklet.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> scriptSourceUrl() {
            return optionalString("scriptSourceUrl");
        }
        /**
         * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> dataOrigin() {
            return optionalString("dataOrigin");
        }
        /**
         * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> operationName() {
            return optionalString("operationName");
        }
        /**
         * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> operationId() {
            return optionalString("operationId");
        }
        /**
         * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> keepAlive() {
            return optionalBoolean("keepAlive");
        }
        /**
         * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.SharedStoragePrivateAggregationConfig> privateAggregationConfig() {
            return optional(PRIVATE_AGGREGATION_CONFIG);
        }
        /**
         * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> serializedData() {
            return optionalString("serializedData");
        }
        /**
         * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Storage.SharedStorageUrlWithMetadata>> urlsWithMetadata() {
            return optional(URLS_WITH_METADATA);
        }
        /**
         * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urnUuid() {
            return optionalString("urnUuid");
        }
        /**
         * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> key() {
            return optionalString("key");
        }
        /**
         * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> value() {
            return optionalString("value");
        }
        /**
         * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignoreIfPresent() {
            return optionalBoolean("ignoreIfPresent");
        }
        /**
         * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong workletOrdinal() {
            return optionalLong("workletOrdinal");
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> workletTargetId() {
            return optional(WORKLET_TARGET_ID);
        }
        /**
         * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> withLock() {
            return optionalString("withLock");
        }
        /**
         * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> batchUpdateId() {
            return optionalString("batchUpdateId");
        }
        /**
         * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong batchSize() {
            return optionalLong("batchSize");
        }
        /**
         * Spec of the module script URL. Present only for SharedStorageAccessMethods: addModule and createWorklet.
         * @param scriptSourceUrl field value
         * @return this model
         */
        public SharedStorageAccessParams scriptSourceUrl(String scriptSourceUrl) {
            set("scriptSourceUrl", scriptSourceUrl);
            return this;
        }
        /**
         * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
         * @param dataOrigin field value
         * @return this model
         */
        public SharedStorageAccessParams dataOrigin(String dataOrigin) {
            set("dataOrigin", dataOrigin);
            return this;
        }
        /**
         * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param operationName field value
         * @return this model
         */
        public SharedStorageAccessParams operationName(String operationName) {
            set("operationName", operationName);
            return this;
        }
        /**
         * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param operationId field value
         * @return this model
         */
        public SharedStorageAccessParams operationId(String operationId) {
            set("operationId", operationId);
            return this;
        }
        /**
         * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param keepAlive field value
         * @return this model
         */
        public SharedStorageAccessParams keepAlive(boolean keepAlive) {
            set("keepAlive", keepAlive);
            return this;
        }
        /**
         * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param privateAggregationConfig field value
         * @return this model
         */
        public SharedStorageAccessParams privateAggregationConfig(Storage.SharedStoragePrivateAggregationConfig privateAggregationConfig) {
            set("privateAggregationConfig", privateAggregationConfig);
            return this;
        }
        /**
         * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
         * @param serializedData field value
         * @return this model
         */
        public SharedStorageAccessParams serializedData(String serializedData) {
            set("serializedData", serializedData);
            return this;
        }
        /**
         * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
         * @param urlsWithMetadata field value
         * @return this model
         */
        public SharedStorageAccessParams urlsWithMetadata(java.util.List<Storage.SharedStorageUrlWithMetadata> urlsWithMetadata) {
            set("urlsWithMetadata", urlsWithMetadata);
            return this;
        }
        /**
         * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
         * @param urnUuid field value
         * @return this model
         */
        public SharedStorageAccessParams urnUuid(String urnUuid) {
            set("urnUuid", urnUuid);
            return this;
        }
        /**
         * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
         * @param key field value
         * @return this model
         */
        public SharedStorageAccessParams key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
         * @param value field value
         * @return this model
         */
        public SharedStorageAccessParams value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
         * @param ignoreIfPresent field value
         * @return this model
         */
        public SharedStorageAccessParams ignoreIfPresent(boolean ignoreIfPresent) {
            set("ignoreIfPresent", ignoreIfPresent);
            return this;
        }
        /**
         * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
         * @param workletOrdinal field value
         * @return this model
         */
        public SharedStorageAccessParams workletOrdinal(long workletOrdinal) {
            set("workletOrdinal", workletOrdinal);
            return this;
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
         * @param workletTargetId field value
         * @return this model
         */
        public SharedStorageAccessParams workletTargetId(Target.TargetID workletTargetId) {
            set("workletTargetId", workletTargetId);
            return this;
        }
        /**
         * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
         * @param withLock field value
         * @return this model
         */
        public SharedStorageAccessParams withLock(String withLock) {
            set("withLock", withLock);
            return this;
        }
        /**
         * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
         * @param batchUpdateId field value
         * @return this model
         */
        public SharedStorageAccessParams batchUpdateId(String batchUpdateId) {
            set("batchUpdateId", batchUpdateId);
            return this;
        }
        /**
         * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
         * @param batchSize field value
         * @return this model
         */
        public SharedStorageAccessParams batchSize(long batchSize) {
            set("batchSize", batchSize);
            return this;
        }
    }
    /**
     * Wire values for StorageBucketsDurability.
     */
    public enum StorageBucketsDurability implements CdpValue<String> {
        RELAXED("relaxed"),
        STRICT("strict");
        public final String value;
        StorageBucketsDurability(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StorageBucketsDurability of(String value) {
            for (StorageBucketsDurability constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StorageBucketsDurability value: " + value);
        }
    }
    /**
     */
    public static final class StorageBucket extends CdpObject {
        public StorageBucket() {}
        private StorageBucket(Map<String, Object> values) { super(values); }
        public static StorageBucket fromMap(Map<String, Object> values) {
            return new StorageBucket(values);
        }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        public Storage.SerializedStorageKey storageKey() {
            return require(STORAGE_KEY);
        }
        /**
         * If not specified, it is the default bucket of the storageKey.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
        }
        /**
         * Sets the storageKey field.
         * @param storageKey field value
         * @return this model
         */
        public StorageBucket storageKey(Storage.SerializedStorageKey storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * If not specified, it is the default bucket of the storageKey.
         * @param name field value
         * @return this model
         */
        public StorageBucket name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     */
    public static final class StorageBucketInfo extends CdpObject {
        public StorageBucketInfo() {}
        private StorageBucketInfo(Map<String, Object> values) { super(values); }
        public static StorageBucketInfo fromMap(Map<String, Object> values) {
            return new StorageBucketInfo(values);
        }
        /**
         * Returns the bucket field.
         * @return the protocol field value
         */
        public Storage.StorageBucket bucket() {
            return require(BUCKET);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Returns the expiration field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch expiration() {
            return require(EXPIRATION);
        }
        /**
         * Storage quota (bytes).
         * @return the protocol field value
         */
        public double quota() {
            return requireDouble("quota");
        }
        /**
         * Returns the persistent field.
         * @return the protocol field value
         */
        public boolean persistent() {
            return requireBoolean("persistent");
        }
        /**
         * Returns the durability field.
         * @return the protocol field value
         */
        public Storage.StorageBucketsDurability durability() {
            return require(DURABILITY);
        }
        /**
         * Sets the bucket field.
         * @param bucket field value
         * @return this model
         */
        public StorageBucketInfo bucket(Storage.StorageBucket bucket) {
            set("bucket", bucket);
            return this;
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public StorageBucketInfo id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Sets the expiration field.
         * @param expiration field value
         * @return this model
         */
        public StorageBucketInfo expiration(Network.TimeSinceEpoch expiration) {
            set("expiration", expiration);
            return this;
        }
        /**
         * Storage quota (bytes).
         * @param quota field value
         * @return this model
         */
        public StorageBucketInfo quota(double quota) {
            set("quota", quota);
            return this;
        }
        /**
         * Sets the persistent field.
         * @param persistent field value
         * @return this model
         */
        public StorageBucketInfo persistent(boolean persistent) {
            set("persistent", persistent);
            return this;
        }
        /**
         * Sets the durability field.
         * @param durability field value
         * @return this model
         */
        public StorageBucketInfo durability(Storage.StorageBucketsDurability durability) {
            set("durability", durability);
            return this;
        }
    }
    /**
     * A single Related Website Set object.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RelatedWebsiteSet extends CdpObject {
        public RelatedWebsiteSet() {}
        private RelatedWebsiteSet(Map<String, Object> values) { super(values); }
        public static RelatedWebsiteSet fromMap(Map<String, Object> values) {
            return new RelatedWebsiteSet(values);
        }
        /**
         * The primary site of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        public java.util.List<String> primarySites() {
            return require(PRIMARY_SITES);
        }
        /**
         * The associated sites of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        public java.util.List<String> associatedSites() {
            return require(ASSOCIATED_SITES);
        }
        /**
         * The service sites of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        public java.util.List<String> serviceSites() {
            return require(SERVICE_SITES);
        }
        /**
         * The primary site of this set, along with the ccTLDs if there is any.
         * @param primarySites field value
         * @return this model
         */
        public RelatedWebsiteSet primarySites(java.util.List<String> primarySites) {
            set("primarySites", primarySites);
            return this;
        }
        /**
         * The associated sites of this set, along with the ccTLDs if there is any.
         * @param associatedSites field value
         * @return this model
         */
        public RelatedWebsiteSet associatedSites(java.util.List<String> associatedSites) {
            set("associatedSites", associatedSites);
            return this;
        }
        /**
         * The service sites of this set, along with the ccTLDs if there is any.
         * @param serviceSites field value
         * @return this model
         */
        public RelatedWebsiteSet serviceSites(java.util.List<String> serviceSites) {
            set("serviceSites", serviceSites);
            return this;
        }
    }
    /**
     * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetStorageKeyForFrameRequest extends CdpObject {
        public GetStorageKeyForFrameRequest() {}
        public static GetStorageKeyForFrameRequest fromMap(Map<String, Object> values) {
            final GetStorageKeyForFrameRequest instance_ = new GetStorageKeyForFrameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GetStorageKeyForFrameRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetStorageKeyRequest extends CdpObject {
        public GetStorageKeyRequest() {}
        public static GetStorageKeyRequest fromMap(Map<String, Object> values) {
            final GetStorageKeyRequest instance_ = new GetStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GetStorageKeyRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Clears storage for origin.
     */
    public static final class ClearDataForOriginRequest extends CdpObject {
        public ClearDataForOriginRequest() {}
        public static ClearDataForOriginRequest fromMap(Map<String, Object> values) {
            final ClearDataForOriginRequest instance_ = new ClearDataForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Comma separated list of StorageType to clear.
         * @return the protocol field value
         */
        public String storageTypes() {
            return requireString("storageTypes");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public ClearDataForOriginRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Comma separated list of StorageType to clear.
         * @param storageTypes field value
         * @return this model
         */
        public ClearDataForOriginRequest storageTypes(String storageTypes) {
            set("storageTypes", storageTypes);
            return this;
        }
    }
    /**
     * Clears storage for storage key.
     */
    public static final class ClearDataForStorageKeyRequest extends CdpObject {
        public ClearDataForStorageKeyRequest() {}
        public static ClearDataForStorageKeyRequest fromMap(Map<String, Object> values) {
            final ClearDataForStorageKeyRequest instance_ = new ClearDataForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Comma separated list of StorageType to clear.
         * @return the protocol field value
         */
        public String storageTypes() {
            return requireString("storageTypes");
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public ClearDataForStorageKeyRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Comma separated list of StorageType to clear.
         * @param storageTypes field value
         * @return this model
         */
        public ClearDataForStorageKeyRequest storageTypes(String storageTypes) {
            set("storageTypes", storageTypes);
            return this;
        }
    }
    /**
     * Returns all browser cookies.
     */
    public static final class GetCookiesRequest extends CdpObject {
        public GetCookiesRequest() {}
        public static GetCookiesRequest fromMap(Map<String, Object> values) {
            final GetCookiesRequest instance_ = new GetCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value
         * @return this model
         */
        public GetCookiesRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
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
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
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
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value
         * @return this model
         */
        public SetCookiesRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Clears cookies.
     */
    public static final class ClearCookiesRequest extends CdpObject {
        public ClearCookiesRequest() {}
        public static ClearCookiesRequest fromMap(Map<String, Object> values) {
            final ClearCookiesRequest instance_ = new ClearCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value
         * @return this model
         */
        public ClearCookiesRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Returns usage and quota in bytes.
     */
    public static final class GetUsageAndQuotaRequest extends CdpObject {
        public GetUsageAndQuotaRequest() {}
        public static GetUsageAndQuotaRequest fromMap(Map<String, Object> values) {
            final GetUsageAndQuotaRequest instance_ = new GetUsageAndQuotaRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public GetUsageAndQuotaRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Override quota for the specified origin
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OverrideQuotaForOriginRequest extends CdpObject {
        public OverrideQuotaForOriginRequest() {}
        public static OverrideQuotaForOriginRequest fromMap(Map<String, Object> values) {
            final OverrideQuotaForOriginRequest instance_ = new OverrideQuotaForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * The quota size (in bytes) to override the original quota with. If this is called multiple times, the overridden quota will be equal to the quotaSize provided in the final call. If this is called without specifying a quotaSize, the quota will be reset to the default value for the specified origin. If this is called multiple times with different origins, the override will be maintained for each origin until it is disabled (called without a quotaSize).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble quotaSize() {
            return optionalDouble("quotaSize");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public OverrideQuotaForOriginRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * The quota size (in bytes) to override the original quota with. If this is called multiple times, the overridden quota will be equal to the quotaSize provided in the final call. If this is called without specifying a quotaSize, the quota will be reset to the default value for the specified origin. If this is called multiple times with different origins, the override will be maintained for each origin until it is disabled (called without a quotaSize).
         * @param quotaSize field value
         * @return this model
         */
        public OverrideQuotaForOriginRequest quotaSize(double quotaSize) {
            set("quotaSize", quotaSize);
            return this;
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForOriginRequest extends CdpObject {
        public TrackCacheStorageForOriginRequest() {}
        public static TrackCacheStorageForOriginRequest fromMap(Map<String, Object> values) {
            final TrackCacheStorageForOriginRequest instance_ = new TrackCacheStorageForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public TrackCacheStorageForOriginRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Registers storage key to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForStorageKeyRequest extends CdpObject {
        public TrackCacheStorageForStorageKeyRequest() {}
        public static TrackCacheStorageForStorageKeyRequest fromMap(Map<String, Object> values) {
            final TrackCacheStorageForStorageKeyRequest instance_ = new TrackCacheStorageForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public TrackCacheStorageForStorageKeyRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its IndexedDB.
     */
    public static final class TrackIndexedDBForOriginRequest extends CdpObject {
        public TrackIndexedDBForOriginRequest() {}
        public static TrackIndexedDBForOriginRequest fromMap(Map<String, Object> values) {
            final TrackIndexedDBForOriginRequest instance_ = new TrackIndexedDBForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public TrackIndexedDBForOriginRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Registers storage key to be notified when an update occurs to its IndexedDB.
     */
    public static final class TrackIndexedDBForStorageKeyRequest extends CdpObject {
        public TrackIndexedDBForStorageKeyRequest() {}
        public static TrackIndexedDBForStorageKeyRequest fromMap(Map<String, Object> values) {
            final TrackIndexedDBForStorageKeyRequest instance_ = new TrackIndexedDBForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public TrackIndexedDBForStorageKeyRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
    }
    /**
     * Unregisters origin from receiving notifications for cache storage.
     */
    public static final class UntrackCacheStorageForOriginRequest extends CdpObject {
        public UntrackCacheStorageForOriginRequest() {}
        public static UntrackCacheStorageForOriginRequest fromMap(Map<String, Object> values) {
            final UntrackCacheStorageForOriginRequest instance_ = new UntrackCacheStorageForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public UntrackCacheStorageForOriginRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Unregisters storage key from receiving notifications for cache storage.
     */
    public static final class UntrackCacheStorageForStorageKeyRequest extends CdpObject {
        public UntrackCacheStorageForStorageKeyRequest() {}
        public static UntrackCacheStorageForStorageKeyRequest fromMap(Map<String, Object> values) {
            final UntrackCacheStorageForStorageKeyRequest instance_ = new UntrackCacheStorageForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public UntrackCacheStorageForStorageKeyRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
    }
    /**
     * Unregisters origin from receiving notifications for IndexedDB.
     */
    public static final class UntrackIndexedDBForOriginRequest extends CdpObject {
        public UntrackIndexedDBForOriginRequest() {}
        public static UntrackIndexedDBForOriginRequest fromMap(Map<String, Object> values) {
            final UntrackIndexedDBForOriginRequest instance_ = new UntrackIndexedDBForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Security origin.
         * @param origin field value
         * @return this model
         */
        public UntrackIndexedDBForOriginRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Unregisters storage key from receiving notifications for IndexedDB.
     */
    public static final class UntrackIndexedDBForStorageKeyRequest extends CdpObject {
        public UntrackIndexedDBForStorageKeyRequest() {}
        public static UntrackIndexedDBForStorageKeyRequest fromMap(Map<String, Object> values) {
            final UntrackIndexedDBForStorageKeyRequest instance_ = new UntrackIndexedDBForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public UntrackIndexedDBForStorageKeyRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
    }
    /**
     * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearTrustTokensRequest extends CdpObject {
        public ClearTrustTokensRequest() {}
        public static ClearTrustTokensRequest fromMap(Map<String, Object> values) {
            final ClearTrustTokensRequest instance_ = new ClearTrustTokensRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the issuerOrigin field.
         * @return the protocol field value
         */
        public String issuerOrigin() {
            return requireString("issuerOrigin");
        }
        /**
         * Sets the issuerOrigin field.
         * @param issuerOrigin field value
         * @return this model
         */
        public ClearTrustTokensRequest issuerOrigin(String issuerOrigin) {
            set("issuerOrigin", issuerOrigin);
            return this;
        }
    }
    /**
     * Gets details for a named interest group.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetInterestGroupDetailsRequest extends CdpObject {
        public GetInterestGroupDetailsRequest() {}
        public static GetInterestGroupDetailsRequest fromMap(Map<String, Object> values) {
            final GetInterestGroupDetailsRequest instance_ = new GetInterestGroupDetailsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public GetInterestGroupDetailsRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public GetInterestGroupDetailsRequest name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Enables/Disables issuing of interestGroupAccessed events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterestGroupTrackingRequest extends CdpObject {
        public SetInterestGroupTrackingRequest() {}
        public static SetInterestGroupTrackingRequest fromMap(Map<String, Object> values) {
            final SetInterestGroupTrackingRequest instance_ = new SetInterestGroupTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
        }
        /**
         * Sets the enable field.
         * @param enable field value
         * @return this model
         */
        public SetInterestGroupTrackingRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterestGroupAuctionTrackingRequest extends CdpObject {
        public SetInterestGroupAuctionTrackingRequest() {}
        public static SetInterestGroupAuctionTrackingRequest fromMap(Map<String, Object> values) {
            final SetInterestGroupAuctionTrackingRequest instance_ = new SetInterestGroupAuctionTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
        }
        /**
         * Sets the enable field.
         * @param enable field value
         * @return this model
         */
        public SetInterestGroupAuctionTrackingRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Gets metadata for an origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSharedStorageMetadataRequest extends CdpObject {
        public GetSharedStorageMetadataRequest() {}
        public static GetSharedStorageMetadataRequest fromMap(Map<String, Object> values) {
            final GetSharedStorageMetadataRequest instance_ = new GetSharedStorageMetadataRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public GetSharedStorageMetadataRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
    }
    /**
     * Gets the entries in an given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSharedStorageEntriesRequest extends CdpObject {
        public GetSharedStorageEntriesRequest() {}
        public static GetSharedStorageEntriesRequest fromMap(Map<String, Object> values) {
            final GetSharedStorageEntriesRequest instance_ = new GetSharedStorageEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public GetSharedStorageEntriesRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
    }
    /**
     * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSharedStorageEntryRequest extends CdpObject {
        public SetSharedStorageEntryRequest() {}
        public static SetSharedStorageEntryRequest fromMap(Map<String, Object> values) {
            final SetSharedStorageEntryRequest instance_ = new SetSharedStorageEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * If {@code ignoreIfPresent} is included and true, then only sets the entry if {@code key} doesn&#x27;t already exist.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignoreIfPresent() {
            return optionalBoolean("ignoreIfPresent");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public SetSharedStorageEntryRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public SetSharedStorageEntryRequest key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public SetSharedStorageEntryRequest value(String value) {
            set("value", value);
            return this;
        }
        /**
         * If {@code ignoreIfPresent} is included and true, then only sets the entry if {@code key} doesn&#x27;t already exist.
         * @param ignoreIfPresent field value
         * @return this model
         */
        public SetSharedStorageEntryRequest ignoreIfPresent(boolean ignoreIfPresent) {
            set("ignoreIfPresent", ignoreIfPresent);
            return this;
        }
    }
    /**
     * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteSharedStorageEntryRequest extends CdpObject {
        public DeleteSharedStorageEntryRequest() {}
        public static DeleteSharedStorageEntryRequest fromMap(Map<String, Object> values) {
            final DeleteSharedStorageEntryRequest instance_ = new DeleteSharedStorageEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public DeleteSharedStorageEntryRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public DeleteSharedStorageEntryRequest key(String key) {
            set("key", key);
            return this;
        }
    }
    /**
     * Clears all entries for a given origin&#x27;s shared storage.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearSharedStorageEntriesRequest extends CdpObject {
        public ClearSharedStorageEntriesRequest() {}
        public static ClearSharedStorageEntriesRequest fromMap(Map<String, Object> values) {
            final ClearSharedStorageEntriesRequest instance_ = new ClearSharedStorageEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public ClearSharedStorageEntriesRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
    }
    /**
     * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResetSharedStorageBudgetRequest extends CdpObject {
        public ResetSharedStorageBudgetRequest() {}
        public static ResetSharedStorageBudgetRequest fromMap(Map<String, Object> values) {
            final ResetSharedStorageBudgetRequest instance_ = new ResetSharedStorageBudgetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public ResetSharedStorageBudgetRequest ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
    }
    /**
     * Enables/disables issuing of sharedStorageAccessed events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSharedStorageTrackingRequest extends CdpObject {
        public SetSharedStorageTrackingRequest() {}
        public static SetSharedStorageTrackingRequest fromMap(Map<String, Object> values) {
            final SetSharedStorageTrackingRequest instance_ = new SetSharedStorageTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
        }
        /**
         * Sets the enable field.
         * @param enable field value
         * @return this model
         */
        public SetSharedStorageTrackingRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Set tracking for a storage key&#x27;s buckets.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetStorageBucketTrackingRequest extends CdpObject {
        public SetStorageBucketTrackingRequest() {}
        public static SetStorageBucketTrackingRequest fromMap(Map<String, Object> values) {
            final SetStorageBucketTrackingRequest instance_ = new SetStorageBucketTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
        }
        /**
         * Sets the storageKey field.
         * @param storageKey field value
         * @return this model
         */
        public SetStorageBucketTrackingRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Sets the enable field.
         * @param enable field value
         * @return this model
         */
        public SetStorageBucketTrackingRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Deletes the Storage Bucket with the given storage key and bucket name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteStorageBucketRequest extends CdpObject {
        public DeleteStorageBucketRequest() {}
        public static DeleteStorageBucketRequest fromMap(Map<String, Object> values) {
            final DeleteStorageBucketRequest instance_ = new DeleteStorageBucketRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the bucket field.
         * @return the protocol field value
         */
        public Storage.StorageBucket bucket() {
            return require(BUCKET);
        }
        /**
         * Sets the bucket field.
         * @param bucket field value
         * @return this model
         */
        public DeleteStorageBucketRequest bucket(Storage.StorageBucket bucket) {
            set("bucket", bucket);
            return this;
        }
    }
    /**
     * Request parameters for Storage.setProtectedAudienceKAnonymity.
     */
    public static final class SetProtectedAudienceKAnonymityRequest extends CdpObject {
        public SetProtectedAudienceKAnonymityRequest() {}
        public static SetProtectedAudienceKAnonymityRequest fromMap(Map<String, Object> values) {
            final SetProtectedAudienceKAnonymityRequest instance_ = new SetProtectedAudienceKAnonymityRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the owner field.
         * @return the protocol field value
         */
        public String owner() {
            return requireString("owner");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Returns the hashes field.
         * @return the protocol field value
         */
        public java.util.List<String> hashes() {
            return require(HASHES);
        }
        /**
         * Sets the owner field.
         * @param owner field value
         * @return this model
         */
        public SetProtectedAudienceKAnonymityRequest owner(String owner) {
            set("owner", owner);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public SetProtectedAudienceKAnonymityRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the hashes field.
         * @param hashes field value
         * @return this model
         */
        public SetProtectedAudienceKAnonymityRequest hashes(java.util.List<String> hashes) {
            set("hashes", hashes);
            return this;
        }
    }
    /**
     * Returns usage and quota in bytes.
     */
    public static final class GetUsageAndQuotaResult extends CdpObject {
        public GetUsageAndQuotaResult() {}
        private GetUsageAndQuotaResult(Map<String, Object> values) { super(values); }
        public static GetUsageAndQuotaResult fromMap(Map<String, Object> values) {
            return new GetUsageAndQuotaResult(values);
        }
        /**
         * Storage usage (bytes).
         * @return the protocol field value
         */
        public double usage() {
            return requireDouble("usage");
        }
        /**
         * Storage quota (bytes).
         * @return the protocol field value
         */
        public double quota() {
            return requireDouble("quota");
        }
        /**
         * Whether or not the origin has an active storage quota override
         * @return the protocol field value
         */
        public boolean overrideActive() {
            return requireBoolean("overrideActive");
        }
        /**
         * Storage usage per type (bytes).
         * @return the protocol field value
         */
        public java.util.List<Storage.UsageForType> usageBreakdown() {
            return require(USAGE_BREAKDOWN);
        }
        /**
         * Storage usage (bytes).
         * @param usage field value
         * @return this model
         */
        public GetUsageAndQuotaResult usage(double usage) {
            set("usage", usage);
            return this;
        }
        /**
         * Storage quota (bytes).
         * @param quota field value
         * @return this model
         */
        public GetUsageAndQuotaResult quota(double quota) {
            set("quota", quota);
            return this;
        }
        /**
         * Whether or not the origin has an active storage quota override
         * @param overrideActive field value
         * @return this model
         */
        public GetUsageAndQuotaResult overrideActive(boolean overrideActive) {
            set("overrideActive", overrideActive);
            return this;
        }
        /**
         * Storage usage per type (bytes).
         * @param usageBreakdown field value
         * @return this model
         */
        public GetUsageAndQuotaResult usageBreakdown(java.util.List<Storage.UsageForType> usageBreakdown) {
            set("usageBreakdown", usageBreakdown);
            return this;
        }
    }
    /**
     * A cache&#x27;s contents have been modified.
     */
    public static final class CacheStorageContentUpdatedEvent extends CdpObject {
        public CacheStorageContentUpdatedEvent() {}
        private CacheStorageContentUpdatedEvent(Map<String, Object> values) { super(values); }
        public static CacheStorageContentUpdatedEvent fromMap(Map<String, Object> values) {
            return new CacheStorageContentUpdatedEvent(values);
        }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return requireString("bucketId");
        }
        /**
         * Name of cache in origin.
         * @return the protocol field value
         */
        public String cacheName() {
            return requireString("cacheName");
        }
        /**
         * Origin to update.
         * @param origin field value
         * @return this model
         */
        public CacheStorageContentUpdatedEvent origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Storage key to update.
         * @param storageKey field value
         * @return this model
         */
        public CacheStorageContentUpdatedEvent storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket to update.
         * @param bucketId field value
         * @return this model
         */
        public CacheStorageContentUpdatedEvent bucketId(String bucketId) {
            set("bucketId", bucketId);
            return this;
        }
        /**
         * Name of cache in origin.
         * @param cacheName field value
         * @return this model
         */
        public CacheStorageContentUpdatedEvent cacheName(String cacheName) {
            set("cacheName", cacheName);
            return this;
        }
    }
    /**
     * A cache has been added/deleted.
     */
    public static final class CacheStorageListUpdatedEvent extends CdpObject {
        public CacheStorageListUpdatedEvent() {}
        private CacheStorageListUpdatedEvent(Map<String, Object> values) { super(values); }
        public static CacheStorageListUpdatedEvent fromMap(Map<String, Object> values) {
            return new CacheStorageListUpdatedEvent(values);
        }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return requireString("bucketId");
        }
        /**
         * Origin to update.
         * @param origin field value
         * @return this model
         */
        public CacheStorageListUpdatedEvent origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Storage key to update.
         * @param storageKey field value
         * @return this model
         */
        public CacheStorageListUpdatedEvent storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket to update.
         * @param bucketId field value
         * @return this model
         */
        public CacheStorageListUpdatedEvent bucketId(String bucketId) {
            set("bucketId", bucketId);
            return this;
        }
    }
    /**
     * The origin&#x27;s IndexedDB object store has been modified.
     */
    public static final class IndexedDBContentUpdatedEvent extends CdpObject {
        public IndexedDBContentUpdatedEvent() {}
        private IndexedDBContentUpdatedEvent(Map<String, Object> values) { super(values); }
        public static IndexedDBContentUpdatedEvent fromMap(Map<String, Object> values) {
            return new IndexedDBContentUpdatedEvent(values);
        }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return requireString("bucketId");
        }
        /**
         * Database to update.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
        }
        /**
         * ObjectStore to update.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return requireString("objectStoreName");
        }
        /**
         * Origin to update.
         * @param origin field value
         * @return this model
         */
        public IndexedDBContentUpdatedEvent origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Storage key to update.
         * @param storageKey field value
         * @return this model
         */
        public IndexedDBContentUpdatedEvent storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket to update.
         * @param bucketId field value
         * @return this model
         */
        public IndexedDBContentUpdatedEvent bucketId(String bucketId) {
            set("bucketId", bucketId);
            return this;
        }
        /**
         * Database to update.
         * @param databaseName field value
         * @return this model
         */
        public IndexedDBContentUpdatedEvent databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
        /**
         * ObjectStore to update.
         * @param objectStoreName field value
         * @return this model
         */
        public IndexedDBContentUpdatedEvent objectStoreName(String objectStoreName) {
            set("objectStoreName", objectStoreName);
            return this;
        }
    }
    /**
     * The origin&#x27;s IndexedDB database list has been modified.
     */
    public static final class IndexedDBListUpdatedEvent extends CdpObject {
        public IndexedDBListUpdatedEvent() {}
        private IndexedDBListUpdatedEvent(Map<String, Object> values) { super(values); }
        public static IndexedDBListUpdatedEvent fromMap(Map<String, Object> values) {
            return new IndexedDBListUpdatedEvent(values);
        }
        /**
         * Origin to update.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return requireString("bucketId");
        }
        /**
         * Origin to update.
         * @param origin field value
         * @return this model
         */
        public IndexedDBListUpdatedEvent origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Storage key to update.
         * @param storageKey field value
         * @return this model
         */
        public IndexedDBListUpdatedEvent storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket to update.
         * @param bucketId field value
         * @return this model
         */
        public IndexedDBListUpdatedEvent bucketId(String bucketId) {
            set("bucketId", bucketId);
            return this;
        }
    }
    /**
     * One of the interest groups was accessed. Note that these events are global to all targets sharing an interest group store.
     */
    public static final class InterestGroupAccessedEvent extends CdpObject {
        public InterestGroupAccessedEvent() {}
        private InterestGroupAccessedEvent(Map<String, Object> values) { super(values); }
        public static InterestGroupAccessedEvent fromMap(Map<String, Object> values) {
            return new InterestGroupAccessedEvent(values);
        }
        /**
         * Returns the accessTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch accessTime() {
            return require(ACCESS_TIME);
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAccessType type() {
            return require(TYPE);
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin
         * @return the protocol field value, empty when absent
         */
        public Optional<String> componentSellerOrigin() {
            return optionalString("componentSellerOrigin");
        }
        /**
         * For bid or somethingBid event, if done locally and not on a server.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble bid() {
            return optionalDouble("bid");
        }
        /**
         * Returns the bidCurrency field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bidCurrency() {
            return optionalString("bidCurrency");
        }
        /**
         * For non-global events --- links to interestGroupAuctionEvent
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.InterestGroupAuctionId> uniqueAuctionId() {
            return optional(UNIQUE_AUCTION_ID);
        }
        /**
         * Sets the accessTime field.
         * @param accessTime field value
         * @return this model
         */
        public InterestGroupAccessedEvent accessTime(Network.TimeSinceEpoch accessTime) {
            set("accessTime", accessTime);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public InterestGroupAccessedEvent type(Storage.InterestGroupAccessType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the ownerOrigin field.
         * @param ownerOrigin field value
         * @return this model
         */
        public InterestGroupAccessedEvent ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public InterestGroupAccessedEvent name(String name) {
            set("name", name);
            return this;
        }
        /**
         * For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin
         * @param componentSellerOrigin field value
         * @return this model
         */
        public InterestGroupAccessedEvent componentSellerOrigin(String componentSellerOrigin) {
            set("componentSellerOrigin", componentSellerOrigin);
            return this;
        }
        /**
         * For bid or somethingBid event, if done locally and not on a server.
         * @param bid field value
         * @return this model
         */
        public InterestGroupAccessedEvent bid(double bid) {
            set("bid", bid);
            return this;
        }
        /**
         * Sets the bidCurrency field.
         * @param bidCurrency field value
         * @return this model
         */
        public InterestGroupAccessedEvent bidCurrency(String bidCurrency) {
            set("bidCurrency", bidCurrency);
            return this;
        }
        /**
         * For non-global events --- links to interestGroupAuctionEvent
         * @param uniqueAuctionId field value
         * @return this model
         */
        public InterestGroupAccessedEvent uniqueAuctionId(Storage.InterestGroupAuctionId uniqueAuctionId) {
            set("uniqueAuctionId", uniqueAuctionId);
            return this;
        }
    }
    /**
     * An auction involving interest groups is taking place. These events are target-specific.
     */
    public static final class InterestGroupAuctionEventOccurredEvent extends CdpObject {
        public InterestGroupAuctionEventOccurredEvent() {}
        private InterestGroupAuctionEventOccurredEvent(Map<String, Object> values) { super(values); }
        public static InterestGroupAuctionEventOccurredEvent fromMap(Map<String, Object> values) {
            return new InterestGroupAuctionEventOccurredEvent(values);
        }
        /**
         * Returns the eventTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch eventTime() {
            return require(EVENT_TIME);
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAuctionEventType type() {
            return require(TYPE_2);
        }
        /**
         * Returns the uniqueAuctionId field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAuctionId uniqueAuctionId() {
            return require(UNIQUE_AUCTION_ID);
        }
        /**
         * Set for child auctions.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.InterestGroupAuctionId> parentAuctionId() {
            return optional(PARENT_AUCTION_ID);
        }
        /**
         * Set for started and configResolved
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> auctionConfig() {
            return optionalObject("auctionConfig");
        }
        /**
         * Sets the eventTime field.
         * @param eventTime field value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent eventTime(Network.TimeSinceEpoch eventTime) {
            set("eventTime", eventTime);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent type(Storage.InterestGroupAuctionEventType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the uniqueAuctionId field.
         * @param uniqueAuctionId field value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent uniqueAuctionId(Storage.InterestGroupAuctionId uniqueAuctionId) {
            set("uniqueAuctionId", uniqueAuctionId);
            return this;
        }
        /**
         * Set for child auctions.
         * @param parentAuctionId field value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent parentAuctionId(Storage.InterestGroupAuctionId parentAuctionId) {
            set("parentAuctionId", parentAuctionId);
            return this;
        }
        /**
         * Set for started and configResolved
         * @param auctionConfig field value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent auctionConfig(java.util.Map<String, Object> auctionConfig) {
            set("auctionConfig", auctionConfig);
            return this;
        }
    }
    /**
     * Specifies which auctions a particular network fetch may be related to, and in what role. Note that it is not ordered with respect to Network.requestWillBeSent (but will happen before loadingFinished loadingFailed).
     */
    public static final class InterestGroupAuctionNetworkRequestCreatedEvent extends CdpObject {
        public InterestGroupAuctionNetworkRequestCreatedEvent() {}
        private InterestGroupAuctionNetworkRequestCreatedEvent(Map<String, Object> values) { super(values); }
        public static InterestGroupAuctionNetworkRequestCreatedEvent fromMap(Map<String, Object> values) {
            return new InterestGroupAuctionNetworkRequestCreatedEvent(values);
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAuctionFetchType type() {
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
         * This is the set of the auctions using the worklet that issued this request. In the case of trusted signals, it&#x27;s possible that only some of them actually care about the keys being queried.
         * @return the protocol field value
         */
        public java.util.List<Storage.InterestGroupAuctionId> auctions() {
            return require(AUCTIONS);
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public InterestGroupAuctionNetworkRequestCreatedEvent type(Storage.InterestGroupAuctionFetchType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public InterestGroupAuctionNetworkRequestCreatedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * This is the set of the auctions using the worklet that issued this request. In the case of trusted signals, it&#x27;s possible that only some of them actually care about the keys being queried.
         * @param auctions field value
         * @return this model
         */
        public InterestGroupAuctionNetworkRequestCreatedEvent auctions(java.util.List<Storage.InterestGroupAuctionId> auctions) {
            set("auctions", auctions);
            return this;
        }
    }
    /**
     * Shared storage was accessed by the associated page. The following parameters are included in all events.
     */
    public static final class SharedStorageAccessedEvent extends CdpObject {
        public SharedStorageAccessedEvent() {}
        private SharedStorageAccessedEvent(Map<String, Object> values) { super(values); }
        public static SharedStorageAccessedEvent fromMap(Map<String, Object> values) {
            return new SharedStorageAccessedEvent(values);
        }
        /**
         * Time of the access.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch accessTime() {
            return require(ACCESS_TIME);
        }
        /**
         * Enum value indicating the access scope.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessScope scope() {
            return require(SCOPE);
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessMethod method() {
            return require(METHOD);
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @return the protocol field value
         */
        public Page.FrameId mainFrameId() {
            return require(MAIN_FRAME_ID);
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Serialization of the site owning the Shared Storage data.
         * @return the protocol field value
         */
        public String ownerSite() {
            return requireString("ownerSite");
        }
        /**
         * The sub-parameters wrapped by {@code params} are all optional and their presence/absence depends on {@code type}.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessParams params() {
            return require(PARAMS);
        }
        /**
         * Time of the access.
         * @param accessTime field value
         * @return this model
         */
        public SharedStorageAccessedEvent accessTime(Network.TimeSinceEpoch accessTime) {
            set("accessTime", accessTime);
            return this;
        }
        /**
         * Enum value indicating the access scope.
         * @param scope field value
         * @return this model
         */
        public SharedStorageAccessedEvent scope(Storage.SharedStorageAccessScope scope) {
            set("scope", scope);
            return this;
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @param method field value
         * @return this model
         */
        public SharedStorageAccessedEvent method(Storage.SharedStorageAccessMethod method) {
            set("method", method);
            return this;
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @param mainFrameId field value
         * @return this model
         */
        public SharedStorageAccessedEvent mainFrameId(Page.FrameId mainFrameId) {
            set("mainFrameId", mainFrameId);
            return this;
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @param ownerOrigin field value
         * @return this model
         */
        public SharedStorageAccessedEvent ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
        /**
         * Serialization of the site owning the Shared Storage data.
         * @param ownerSite field value
         * @return this model
         */
        public SharedStorageAccessedEvent ownerSite(String ownerSite) {
            set("ownerSite", ownerSite);
            return this;
        }
        /**
         * The sub-parameters wrapped by {@code params} are all optional and their presence/absence depends on {@code type}.
         * @param params field value
         * @return this model
         */
        public SharedStorageAccessedEvent params(Storage.SharedStorageAccessParams params) {
            set("params", params);
            return this;
        }
    }
    /**
     * A shared storage run or selectURL operation finished its execution. The following parameters are included in all events.
     */
    public static final class SharedStorageWorkletOperationExecutionFinishedEvent extends CdpObject {
        public SharedStorageWorkletOperationExecutionFinishedEvent() {}
        private SharedStorageWorkletOperationExecutionFinishedEvent(Map<String, Object> values) { super(values); }
        public static SharedStorageWorkletOperationExecutionFinishedEvent fromMap(Map<String, Object> values) {
            return new SharedStorageWorkletOperationExecutionFinishedEvent(values);
        }
        /**
         * Time that the operation finished.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch finishedTime() {
            return require(FINISHED_TIME);
        }
        /**
         * Time, in microseconds, from start of shared storage JS API call until end of operation execution in the worklet.
         * @return the protocol field value
         */
        public long executionTime() {
            return requireLong("executionTime");
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessMethod method() {
            return require(METHOD);
        }
        /**
         * ID of the operation call.
         * @return the protocol field value
         */
        public String operationId() {
            return requireString("operationId");
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet.
         * @return the protocol field value
         */
        public Target.TargetID workletTargetId() {
            return require(WORKLET_TARGET_ID);
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @return the protocol field value
         */
        public Page.FrameId mainFrameId() {
            return require(MAIN_FRAME_ID);
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return requireString("ownerOrigin");
        }
        /**
         * Time that the operation finished.
         * @param finishedTime field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent finishedTime(Network.TimeSinceEpoch finishedTime) {
            set("finishedTime", finishedTime);
            return this;
        }
        /**
         * Time, in microseconds, from start of shared storage JS API call until end of operation execution in the worklet.
         * @param executionTime field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent executionTime(long executionTime) {
            set("executionTime", executionTime);
            return this;
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @param method field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent method(Storage.SharedStorageAccessMethod method) {
            set("method", method);
            return this;
        }
        /**
         * ID of the operation call.
         * @param operationId field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent operationId(String operationId) {
            set("operationId", operationId);
            return this;
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet.
         * @param workletTargetId field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent workletTargetId(Target.TargetID workletTargetId) {
            set("workletTargetId", workletTargetId);
            return this;
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @param mainFrameId field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent mainFrameId(Page.FrameId mainFrameId) {
            set("mainFrameId", mainFrameId);
            return this;
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @param ownerOrigin field value
         * @return this model
         */
        public SharedStorageWorkletOperationExecutionFinishedEvent ownerOrigin(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
            return this;
        }
    }
    /**
     * Payload of the Storage.storageBucketCreatedOrUpdated event.
     */
    public static final class StorageBucketCreatedOrUpdatedEvent extends CdpObject {
        public StorageBucketCreatedOrUpdatedEvent() {}
        private StorageBucketCreatedOrUpdatedEvent(Map<String, Object> values) { super(values); }
        public static StorageBucketCreatedOrUpdatedEvent fromMap(Map<String, Object> values) {
            return new StorageBucketCreatedOrUpdatedEvent(values);
        }
        /**
         * Returns the bucketInfo field.
         * @return the protocol field value
         */
        public Storage.StorageBucketInfo bucketInfo() {
            return require(BUCKET_INFO);
        }
        /**
         * Sets the bucketInfo field.
         * @param bucketInfo field value
         * @return this model
         */
        public StorageBucketCreatedOrUpdatedEvent bucketInfo(Storage.StorageBucketInfo bucketInfo) {
            set("bucketInfo", bucketInfo);
            return this;
        }
    }
    /**
     * Payload of the Storage.storageBucketDeleted event.
     */
    public static final class StorageBucketDeletedEvent extends CdpObject {
        public StorageBucketDeletedEvent() {}
        private StorageBucketDeletedEvent(Map<String, Object> values) { super(values); }
        public static StorageBucketDeletedEvent fromMap(Map<String, Object> values) {
            return new StorageBucketDeletedEvent(values);
        }
        /**
         * Returns the bucketId field.
         * @return the protocol field value
         */
        public String bucketId() {
            return requireString("bucketId");
        }
        /**
         * Sets the bucketId field.
         * @param bucketId field value
         * @return this model
         */
        public StorageBucketDeletedEvent bucketId(String bucketId) {
            set("bucketId", bucketId);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
         * @param frameId protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Storage.SerializedStorageKey> getStorageKeyForFrame(Page.FrameId frameId) {
            return call("Storage.getStorageKeyForFrame", params("frameId", frameId), STORAGE_KEY::require);
        }
        /**
         * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Storage.SerializedStorageKey> getStorageKeyForFrame(GetStorageKeyForFrameRequest request) {
            return call("Storage.getStorageKeyForFrame", request, STORAGE_KEY::require);
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SerializedStorageKey> getStorageKey(Page.FrameId frameId) {
            return call("Storage.getStorageKey", params("frameId", frameId), STORAGE_KEY::require);
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SerializedStorageKey> getStorageKey() {
            return call("Storage.getStorageKey", STORAGE_KEY::require);
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SerializedStorageKey> getStorageKey(GetStorageKeyRequest request) {
            return call("Storage.getStorageKey", request, STORAGE_KEY::require);
        }
        /**
         * Clears storage for origin.
         * @param origin protocol value
         * @param storageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForOrigin(String origin, String storageTypes) {
            return call("Storage.clearDataForOrigin", params("origin", origin, "storageTypes", storageTypes));
        }
        /**
         * Clears storage for origin.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForOrigin(ClearDataForOriginRequest request) {
            return call("Storage.clearDataForOrigin", request);
        }
        /**
         * Clears storage for storage key.
         * @param storageKey protocol value
         * @param storageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForStorageKey(String storageKey, String storageTypes) {
            return call("Storage.clearDataForStorageKey", params("storageKey", storageKey, "storageTypes", storageTypes));
        }
        /**
         * Clears storage for storage key.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForStorageKey(ClearDataForStorageKeyRequest request) {
            return call("Storage.clearDataForStorageKey", request);
        }
        /**
         * Returns all browser cookies.
         * @param browserContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(Browser.BrowserContextID browserContextId) {
            return call("Storage.getCookies", params("browserContextId", browserContextId), COOKIES_2::require);
        }
        /**
         * Returns all browser cookies.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies() {
            return call("Storage.getCookies", COOKIES_2::require);
        }
        /**
         * Returns all browser cookies.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(GetCookiesRequest request) {
            return call("Storage.getCookies", request, COOKIES_2::require);
        }
        /**
         * Sets given cookies.
         * @param cookies protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(java.util.List<Network.CookieParam> cookies, Browser.BrowserContextID browserContextId) {
            return call("Storage.setCookies", params("cookies", cookies, "browserContextId", browserContextId));
        }
        /**
         * Sets given cookies.
         * @param cookies protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(java.util.List<Network.CookieParam> cookies) {
            return call("Storage.setCookies", params("cookies", cookies));
        }
        /**
         * Sets given cookies.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(SetCookiesRequest request) {
            return call("Storage.setCookies", request);
        }
        /**
         * Clears cookies.
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCookies(Browser.BrowserContextID browserContextId) {
            return call("Storage.clearCookies", params("browserContextId", browserContextId));
        }
        /**
         * Clears cookies.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCookies() {
            return call("Storage.clearCookies");
        }
        /**
         * Clears cookies.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCookies(ClearCookiesRequest request) {
            return call("Storage.clearCookies", request);
        }
        /**
         * Returns usage and quota in bytes.
         * @param origin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetUsageAndQuotaResult> getUsageAndQuota(String origin) {
            return call("Storage.getUsageAndQuota", params("origin", origin), GetUsageAndQuotaResult::fromMap);
        }
        /**
         * Returns usage and quota in bytes.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetUsageAndQuotaResult> getUsageAndQuota(GetUsageAndQuotaRequest request) {
            return call("Storage.getUsageAndQuota", request, GetUsageAndQuotaResult::fromMap);
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @param quotaSize protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideQuotaForOrigin(String origin, double quotaSize) {
            return call("Storage.overrideQuotaForOrigin", params("origin", origin, "quotaSize", quotaSize));
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideQuotaForOrigin(String origin) {
            return call("Storage.overrideQuotaForOrigin", params("origin", origin));
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideQuotaForOrigin(OverrideQuotaForOriginRequest request) {
            return call("Storage.overrideQuotaForOrigin", request);
        }
        /**
         * Registers origin to be notified when an update occurs to its cache storage list.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForOrigin(String origin) {
            return call("Storage.trackCacheStorageForOrigin", params("origin", origin));
        }
        /**
         * Registers origin to be notified when an update occurs to its cache storage list.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForOrigin(TrackCacheStorageForOriginRequest request) {
            return call("Storage.trackCacheStorageForOrigin", request);
        }
        /**
         * Registers storage key to be notified when an update occurs to its cache storage list.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForStorageKey(String storageKey) {
            return call("Storage.trackCacheStorageForStorageKey", params("storageKey", storageKey));
        }
        /**
         * Registers storage key to be notified when an update occurs to its cache storage list.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForStorageKey(TrackCacheStorageForStorageKeyRequest request) {
            return call("Storage.trackCacheStorageForStorageKey", request);
        }
        /**
         * Registers origin to be notified when an update occurs to its IndexedDB.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForOrigin(String origin) {
            return call("Storage.trackIndexedDBForOrigin", params("origin", origin));
        }
        /**
         * Registers origin to be notified when an update occurs to its IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForOrigin(TrackIndexedDBForOriginRequest request) {
            return call("Storage.trackIndexedDBForOrigin", request);
        }
        /**
         * Registers storage key to be notified when an update occurs to its IndexedDB.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForStorageKey(String storageKey) {
            return call("Storage.trackIndexedDBForStorageKey", params("storageKey", storageKey));
        }
        /**
         * Registers storage key to be notified when an update occurs to its IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForStorageKey(TrackIndexedDBForStorageKeyRequest request) {
            return call("Storage.trackIndexedDBForStorageKey", request);
        }
        /**
         * Unregisters origin from receiving notifications for cache storage.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForOrigin(String origin) {
            return call("Storage.untrackCacheStorageForOrigin", params("origin", origin));
        }
        /**
         * Unregisters origin from receiving notifications for cache storage.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForOrigin(UntrackCacheStorageForOriginRequest request) {
            return call("Storage.untrackCacheStorageForOrigin", request);
        }
        /**
         * Unregisters storage key from receiving notifications for cache storage.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForStorageKey(String storageKey) {
            return call("Storage.untrackCacheStorageForStorageKey", params("storageKey", storageKey));
        }
        /**
         * Unregisters storage key from receiving notifications for cache storage.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForStorageKey(UntrackCacheStorageForStorageKeyRequest request) {
            return call("Storage.untrackCacheStorageForStorageKey", request);
        }
        /**
         * Unregisters origin from receiving notifications for IndexedDB.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForOrigin(String origin) {
            return call("Storage.untrackIndexedDBForOrigin", params("origin", origin));
        }
        /**
         * Unregisters origin from receiving notifications for IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForOrigin(UntrackIndexedDBForOriginRequest request) {
            return call("Storage.untrackIndexedDBForOrigin", request);
        }
        /**
         * Unregisters storage key from receiving notifications for IndexedDB.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForStorageKey(String storageKey) {
            return call("Storage.untrackIndexedDBForStorageKey", params("storageKey", storageKey));
        }
        /**
         * Unregisters storage key from receiving notifications for IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForStorageKey(UntrackIndexedDBForStorageKeyRequest request) {
            return call("Storage.untrackIndexedDBForStorageKey", request);
        }
        /**
         * Returns the number of stored Trust Tokens per issuer for the current browsing context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.TrustTokens>> getTrustTokens() {
            return call("Storage.getTrustTokens", TOKENS::require);
        }
        /**
         * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param issuerOrigin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> clearTrustTokens(String issuerOrigin) {
            return call("Storage.clearTrustTokens", params("issuerOrigin", issuerOrigin), DID_DELETE_TOKENS::require);
        }
        /**
         * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> clearTrustTokens(ClearTrustTokensRequest request) {
            return call("Storage.clearTrustTokens", request, DID_DELETE_TOKENS::require);
        }
        /**
         * Gets details for a named interest group.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param name protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getInterestGroupDetails(String ownerOrigin, String name) {
            return call("Storage.getInterestGroupDetails", params("ownerOrigin", ownerOrigin, "name", name), DETAILS::require);
        }
        /**
         * Gets details for a named interest group.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getInterestGroupDetails(GetInterestGroupDetailsRequest request) {
            return call("Storage.getInterestGroupDetails", request, DETAILS::require);
        }
        /**
         * Enables/Disables issuing of interestGroupAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupTracking(boolean enable) {
            return call("Storage.setInterestGroupTracking", params("enable", enable));
        }
        /**
         * Enables/Disables issuing of interestGroupAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupTracking(SetInterestGroupTrackingRequest request) {
            return call("Storage.setInterestGroupTracking", request);
        }
        /**
         * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupAuctionTracking(boolean enable) {
            return call("Storage.setInterestGroupAuctionTracking", params("enable", enable));
        }
        /**
         * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupAuctionTracking(SetInterestGroupAuctionTrackingRequest request) {
            return call("Storage.setInterestGroupAuctionTracking", request);
        }
        /**
         * Gets metadata for an origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SharedStorageMetadata> getSharedStorageMetadata(String ownerOrigin) {
            return call("Storage.getSharedStorageMetadata", params("ownerOrigin", ownerOrigin), METADATA::require);
        }
        /**
         * Gets metadata for an origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SharedStorageMetadata> getSharedStorageMetadata(GetSharedStorageMetadataRequest request) {
            return call("Storage.getSharedStorageMetadata", request, METADATA::require);
        }
        /**
         * Gets the entries in an given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.SharedStorageEntry>> getSharedStorageEntries(String ownerOrigin) {
            return call("Storage.getSharedStorageEntries", params("ownerOrigin", ownerOrigin), ENTRIES::require);
        }
        /**
         * Gets the entries in an given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.SharedStorageEntry>> getSharedStorageEntries(GetSharedStorageEntriesRequest request) {
            return call("Storage.getSharedStorageEntries", request, ENTRIES::require);
        }
        /**
         * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param key protocol value
         * @param value protocol value
         * @param ignoreIfPresent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageEntry(String ownerOrigin, String key, String value, boolean ignoreIfPresent) {
            return call("Storage.setSharedStorageEntry", params("ownerOrigin", ownerOrigin, "key", key, "value", value, "ignoreIfPresent", ignoreIfPresent));
        }
        /**
         * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param key protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageEntry(String ownerOrigin, String key, String value) {
            return call("Storage.setSharedStorageEntry", params("ownerOrigin", ownerOrigin, "key", key, "value", value));
        }
        /**
         * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageEntry(SetSharedStorageEntryRequest request) {
            return call("Storage.setSharedStorageEntry", request);
        }
        /**
         * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param key protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteSharedStorageEntry(String ownerOrigin, String key) {
            return call("Storage.deleteSharedStorageEntry", params("ownerOrigin", ownerOrigin, "key", key));
        }
        /**
         * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteSharedStorageEntry(DeleteSharedStorageEntryRequest request) {
            return call("Storage.deleteSharedStorageEntry", request);
        }
        /**
         * Clears all entries for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearSharedStorageEntries(String ownerOrigin) {
            return call("Storage.clearSharedStorageEntries", params("ownerOrigin", ownerOrigin));
        }
        /**
         * Clears all entries for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearSharedStorageEntries(ClearSharedStorageEntriesRequest request) {
            return call("Storage.clearSharedStorageEntries", request);
        }
        /**
         * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetSharedStorageBudget(String ownerOrigin) {
            return call("Storage.resetSharedStorageBudget", params("ownerOrigin", ownerOrigin));
        }
        /**
         * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetSharedStorageBudget(ResetSharedStorageBudgetRequest request) {
            return call("Storage.resetSharedStorageBudget", request);
        }
        /**
         * Enables/disables issuing of sharedStorageAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageTracking(boolean enable) {
            return call("Storage.setSharedStorageTracking", params("enable", enable));
        }
        /**
         * Enables/disables issuing of sharedStorageAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageTracking(SetSharedStorageTrackingRequest request) {
            return call("Storage.setSharedStorageTracking", request);
        }
        /**
         * Set tracking for a storage key&#x27;s buckets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param storageKey protocol value
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageBucketTracking(String storageKey, boolean enable) {
            return call("Storage.setStorageBucketTracking", params("storageKey", storageKey, "enable", enable));
        }
        /**
         * Set tracking for a storage key&#x27;s buckets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageBucketTracking(SetStorageBucketTrackingRequest request) {
            return call("Storage.setStorageBucketTracking", request);
        }
        /**
         * Deletes the Storage Bucket with the given storage key and bucket name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param bucket protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteStorageBucket(Storage.StorageBucket bucket) {
            return call("Storage.deleteStorageBucket", params("bucket", bucket));
        }
        /**
         * Deletes the Storage Bucket with the given storage key and bucket name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteStorageBucket(DeleteStorageBucketRequest request) {
            return call("Storage.deleteStorageBucket", request);
        }
        /**
         * Deletes state for sites identified as potential bounce trackers, immediately.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> runBounceTrackingMitigations() {
            return call("Storage.runBounceTrackingMitigations", DELETED_SITES::require);
        }
        /**
         * Returns the effective Related Website Sets in use by this profile for the browser session. The effective Related Website Sets will not change during a browser session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.RelatedWebsiteSet>> getRelatedWebsiteSets() {
            return call("Storage.getRelatedWebsiteSets", SETS::require);
        }
        /**
         * Invokes Storage.setProtectedAudienceKAnonymity.
         * @param owner protocol value
         * @param name protocol value
         * @param hashes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setProtectedAudienceKAnonymity(String owner, String name, java.util.List<String> hashes) {
            return call("Storage.setProtectedAudienceKAnonymity", params("owner", owner, "name", name, "hashes", hashes));
        }
        /**
         * Invokes Storage.setProtectedAudienceKAnonymity with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setProtectedAudienceKAnonymity(SetProtectedAudienceKAnonymityRequest request) {
            return call("Storage.setProtectedAudienceKAnonymity", request);
        }
        /**
         * A cache&#x27;s contents have been modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCacheStorageContentUpdated(Consumer<CacheStorageContentUpdatedEvent> handler) {
            return on("Storage.cacheStorageContentUpdated", CacheStorageContentUpdatedEvent::fromMap, handler);
        }
        /**
         * A cache has been added/deleted.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCacheStorageListUpdated(Consumer<CacheStorageListUpdatedEvent> handler) {
            return on("Storage.cacheStorageListUpdated", CacheStorageListUpdatedEvent::fromMap, handler);
        }
        /**
         * The origin&#x27;s IndexedDB object store has been modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIndexedDBContentUpdated(Consumer<IndexedDBContentUpdatedEvent> handler) {
            return on("Storage.indexedDBContentUpdated", IndexedDBContentUpdatedEvent::fromMap, handler);
        }
        /**
         * The origin&#x27;s IndexedDB database list has been modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIndexedDBListUpdated(Consumer<IndexedDBListUpdatedEvent> handler) {
            return on("Storage.indexedDBListUpdated", IndexedDBListUpdatedEvent::fromMap, handler);
        }
        /**
         * One of the interest groups was accessed. Note that these events are global to all targets sharing an interest group store.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterestGroupAccessed(Consumer<InterestGroupAccessedEvent> handler) {
            return on("Storage.interestGroupAccessed", InterestGroupAccessedEvent::fromMap, handler);
        }
        /**
         * An auction involving interest groups is taking place. These events are target-specific.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterestGroupAuctionEventOccurred(Consumer<InterestGroupAuctionEventOccurredEvent> handler) {
            return on("Storage.interestGroupAuctionEventOccurred", InterestGroupAuctionEventOccurredEvent::fromMap, handler);
        }
        /**
         * Specifies which auctions a particular network fetch may be related to, and in what role. Note that it is not ordered with respect to Network.requestWillBeSent (but will happen before loadingFinished loadingFailed).
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterestGroupAuctionNetworkRequestCreated(Consumer<InterestGroupAuctionNetworkRequestCreatedEvent> handler) {
            return on("Storage.interestGroupAuctionNetworkRequestCreated", InterestGroupAuctionNetworkRequestCreatedEvent::fromMap, handler);
        }
        /**
         * Shared storage was accessed by the associated page. The following parameters are included in all events.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSharedStorageAccessed(Consumer<SharedStorageAccessedEvent> handler) {
            return on("Storage.sharedStorageAccessed", SharedStorageAccessedEvent::fromMap, handler);
        }
        /**
         * A shared storage run or selectURL operation finished its execution. The following parameters are included in all events.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSharedStorageWorkletOperationExecutionFinished(Consumer<SharedStorageWorkletOperationExecutionFinishedEvent> handler) {
            return on("Storage.sharedStorageWorkletOperationExecutionFinished", SharedStorageWorkletOperationExecutionFinishedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.storageBucketCreatedOrUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStorageBucketCreatedOrUpdated(Consumer<StorageBucketCreatedOrUpdatedEvent> handler) {
            return on("Storage.storageBucketCreatedOrUpdated", StorageBucketCreatedOrUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Storage.storageBucketDeleted.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStorageBucketDeleted(Consumer<StorageBucketDeletedEvent> handler) {
            return on("Storage.storageBucketDeleted", StorageBucketDeletedEvent::fromMap, handler);
        }
    }
}
