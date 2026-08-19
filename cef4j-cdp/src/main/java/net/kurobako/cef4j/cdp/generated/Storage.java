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
 * Chrome DevTools Protocol Storage domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Storage.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Storage {
    private Storage() {}
    /**
     * Tagged String wire value for SerializedStorageKey.
     */
    public static final class SerializedStorageKey implements CdpValue<String> {
        public final String value;
        public SerializedStorageKey(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static StorageType of(@Nonnull String value) {
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
            return Storage.StorageType.of((String) require("storageType"));
        }
        /**
         * Storage usage (bytes).
         * @return the protocol field value
         */
        public double usage() {
            return ((Number) require("usage")).doubleValue();
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
            return (String) require("issuerOrigin");
        }
        /**
         * Returns the count field.
         * @return the protocol field value
         */
        public double count() {
            return ((Number) require("count")).doubleValue();
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
        public InterestGroupAuctionId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static InterestGroupAccessType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static InterestGroupAuctionEventType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static InterestGroupAuctionFetchType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static SharedStorageAccessScope of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static SharedStorageAccessMethod of(@Nonnull String value) {
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
            return (String) require("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
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
            return new Network.TimeSinceEpoch(((Number) require("creationTime")).doubleValue());
        }
        /**
         * Number of key-value pairs stored in origin&#x27;s shared storage.
         * @return the protocol field value
         */
        public long length() {
            return ((Number) require("length")).longValue();
        }
        /**
         * Current amount of bits of entropy remaining in the navigation budget.
         * @return the protocol field value
         */
        public double remainingBudget() {
            return ((Number) require("remainingBudget")).doubleValue();
        }
        /**
         * Total number of bytes stored as key-value pairs in origin&#x27;s shared storage.
         * @return the protocol field value
         */
        public long bytesUsed() {
            return ((Number) require("bytesUsed")).longValue();
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
            return Optional.ofNullable((String) raw("aggregationCoordinatorOrigin"));
        }
        /**
         * The context ID provided.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> contextId() {
            return Optional.ofNullable((String) raw("contextId"));
        }
        /**
         * Configures the maximum size allowed for filtering IDs.
         * @return the protocol field value
         */
        public long filteringIdMaxBytes() {
            return ((Number) require("filteringIdMaxBytes")).longValue();
        }
        /**
         * The limit on the number of contributions in the final report.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxContributions() {
            Long value = CdpObject.numberAsLong(raw("maxContributions"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The chosen aggregation service deployment.
         * @param aggregationCoordinatorOrigin field value; empty omits the value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig aggregationCoordinatorOrigin(Optional<String> aggregationCoordinatorOrigin) {
            set("aggregationCoordinatorOrigin", aggregationCoordinatorOrigin.orElse(null));
            return this;
        }
        /**
         * The chosen aggregation service deployment.
         * @param aggregationCoordinatorOrigin field value; null removes the value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig aggregationCoordinatorOrigin(String aggregationCoordinatorOrigin) {
            set("aggregationCoordinatorOrigin", aggregationCoordinatorOrigin);
            return this;
        }
        /**
         * The context ID provided.
         * @param contextId field value; empty omits the value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig contextId(Optional<String> contextId) {
            set("contextId", contextId.orElse(null));
            return this;
        }
        /**
         * The context ID provided.
         * @param contextId field value; null removes the value
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
         * @param maxContributions field value; empty omits the value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig maxContributions(OptionalLong maxContributions) {
            set("maxContributions", maxContributions.isPresent() ? maxContributions.getAsLong() : null);
            return this;
        }
        /**
         * The limit on the number of contributions in the final report.
         * @param maxContributions field value; null removes the value
         * @return this model
         */
        public SharedStoragePrivateAggregationConfig maxContributions(Long maxContributions) {
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
            return (String) require("eventType");
        }
        /**
         * Returns the reportingUrl field.
         * @return the protocol field value
         */
        public String reportingUrl() {
            return (String) require("reportingUrl");
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
            return (String) require("url");
        }
        /**
         * Any associated reporting metadata.
         * @return the protocol field value
         */
        public java.util.List<Storage.SharedStorageReportingMetadata> reportingMetadata() {
            return CdpObject.requireList(require("reportingMetadata"), element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageReportingMetadata.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return Optional.ofNullable((String) raw("scriptSourceUrl"));
        }
        /**
         * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> dataOrigin() {
            return Optional.ofNullable((String) raw("dataOrigin"));
        }
        /**
         * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> operationName() {
            return Optional.ofNullable((String) raw("operationName"));
        }
        /**
         * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> operationId() {
            return Optional.ofNullable((String) raw("operationId"));
        }
        /**
         * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> keepAlive() {
            return Optional.ofNullable((Boolean) raw("keepAlive"));
        }
        /**
         * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.SharedStoragePrivateAggregationConfig> privateAggregationConfig() {
            return Optional.ofNullable(raw("privateAggregationConfig") == null ? null : Storage.SharedStoragePrivateAggregationConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("privateAggregationConfig")))));
        }
        /**
         * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> serializedData() {
            return Optional.ofNullable((String) raw("serializedData"));
        }
        /**
         * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Storage.SharedStorageUrlWithMetadata>> urlsWithMetadata() {
            return Optional.ofNullable(list(raw("urlsWithMetadata"), element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageUrlWithMetadata.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urnUuid() {
            return Optional.ofNullable((String) raw("urnUuid"));
        }
        /**
         * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> key() {
            return Optional.ofNullable((String) raw("key"));
        }
        /**
         * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> value() {
            return Optional.ofNullable((String) raw("value"));
        }
        /**
         * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignoreIfPresent() {
            return Optional.ofNullable((Boolean) raw("ignoreIfPresent"));
        }
        /**
         * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong workletOrdinal() {
            Long value = CdpObject.numberAsLong(raw("workletOrdinal"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> workletTargetId() {
            return Optional.ofNullable(raw("workletTargetId") == null ? null : new Target.TargetID((String) raw("workletTargetId")));
        }
        /**
         * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> withLock() {
            return Optional.ofNullable((String) raw("withLock"));
        }
        /**
         * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> batchUpdateId() {
            return Optional.ofNullable((String) raw("batchUpdateId"));
        }
        /**
         * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong batchSize() {
            Long value = CdpObject.numberAsLong(raw("batchSize"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Spec of the module script URL. Present only for SharedStorageAccessMethods: addModule and createWorklet.
         * @param scriptSourceUrl field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams scriptSourceUrl(Optional<String> scriptSourceUrl) {
            set("scriptSourceUrl", scriptSourceUrl.orElse(null));
            return this;
        }
        /**
         * Spec of the module script URL. Present only for SharedStorageAccessMethods: addModule and createWorklet.
         * @param scriptSourceUrl field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams scriptSourceUrl(String scriptSourceUrl) {
            set("scriptSourceUrl", scriptSourceUrl);
            return this;
        }
        /**
         * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
         * @param dataOrigin field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams dataOrigin(Optional<String> dataOrigin) {
            set("dataOrigin", dataOrigin.orElse(null));
            return this;
        }
        /**
         * String denoting &quot;context-origin&quot;, &quot;script-origin&quot;, or a custom origin to be used as the worklet&#x27;s data origin. Present only for SharedStorageAccessMethod: createWorklet.
         * @param dataOrigin field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams dataOrigin(String dataOrigin) {
            set("dataOrigin", dataOrigin);
            return this;
        }
        /**
         * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param operationName field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams operationName(Optional<String> operationName) {
            set("operationName", operationName.orElse(null));
            return this;
        }
        /**
         * Name of the registered operation to be run. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param operationName field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams operationName(String operationName) {
            set("operationName", operationName);
            return this;
        }
        /**
         * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param operationId field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams operationId(Optional<String> operationId) {
            set("operationId", operationId.orElse(null));
            return this;
        }
        /**
         * ID of the operation call. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param operationId field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams operationId(String operationId) {
            set("operationId", operationId);
            return this;
        }
        /**
         * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param keepAlive field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams keepAlive(Optional<Boolean> keepAlive) {
            set("keepAlive", keepAlive.orElse(null));
            return this;
        }
        /**
         * Whether or not to keep the worket alive for future run or selectURL calls. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param keepAlive field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams keepAlive(Boolean keepAlive) {
            set("keepAlive", keepAlive);
            return this;
        }
        /**
         * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param privateAggregationConfig field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams privateAggregationConfig(Optional<Storage.SharedStoragePrivateAggregationConfig> privateAggregationConfig) {
            set("privateAggregationConfig", privateAggregationConfig.orElse(null));
            return this;
        }
        /**
         * Configures the private aggregation options. Present only for SharedStorageAccessMethods: run and selectURL.
         * @param privateAggregationConfig field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams privateAggregationConfig(Storage.SharedStoragePrivateAggregationConfig privateAggregationConfig) {
            set("privateAggregationConfig", privateAggregationConfig);
            return this;
        }
        /**
         * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
         * @param serializedData field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams serializedData(Optional<String> serializedData) {
            set("serializedData", serializedData.orElse(null));
            return this;
        }
        /**
         * The operation&#x27;s serialized data in bytes (converted to a string). Present only for SharedStorageAccessMethods: run and selectURL. TODO(crbug.com/401011862): Consider updating this parameter to binary.
         * @param serializedData field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams serializedData(String serializedData) {
            set("serializedData", serializedData);
            return this;
        }
        /**
         * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
         * @param urlsWithMetadata field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams urlsWithMetadata(Optional<java.util.List<Storage.SharedStorageUrlWithMetadata>> urlsWithMetadata) {
            set("urlsWithMetadata", urlsWithMetadata.orElse(null));
            return this;
        }
        /**
         * Array of candidate URLs&#x27; specs, along with any associated metadata. Present only for SharedStorageAccessMethod: selectURL.
         * @param urlsWithMetadata field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams urlsWithMetadata(java.util.List<Storage.SharedStorageUrlWithMetadata> urlsWithMetadata) {
            set("urlsWithMetadata", urlsWithMetadata);
            return this;
        }
        /**
         * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
         * @param urnUuid field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams urnUuid(Optional<String> urnUuid) {
            set("urnUuid", urnUuid.orElse(null));
            return this;
        }
        /**
         * Spec of the URN:UUID generated for a selectURL call. Present only for SharedStorageAccessMethod: selectURL.
         * @param urnUuid field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams urnUuid(String urnUuid) {
            set("urnUuid", urnUuid);
            return this;
        }
        /**
         * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
         * @param key field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams key(Optional<String> key) {
            set("key", key.orElse(null));
            return this;
        }
        /**
         * Key for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set, append, delete, and get.
         * @param key field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
         * @param value field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams value(Optional<String> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * Value for a specific entry in an origin&#x27;s shared storage. Present only for SharedStorageAccessMethods: set and append.
         * @param value field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
         * @param ignoreIfPresent field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams ignoreIfPresent(Optional<Boolean> ignoreIfPresent) {
            set("ignoreIfPresent", ignoreIfPresent.orElse(null));
            return this;
        }
        /**
         * Whether or not to set an entry for a key if that key is already present. Present only for SharedStorageAccessMethod: set.
         * @param ignoreIfPresent field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams ignoreIfPresent(Boolean ignoreIfPresent) {
            set("ignoreIfPresent", ignoreIfPresent);
            return this;
        }
        /**
         * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
         * @param workletOrdinal field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams workletOrdinal(OptionalLong workletOrdinal) {
            set("workletOrdinal", workletOrdinal.isPresent() ? workletOrdinal.getAsLong() : null);
            return this;
        }
        /**
         * A number denoting the (0-based) order of the worklet&#x27;s creation relative to all other shared storage worklets created by documents using the current storage partition. Present only for SharedStorageAccessMethods: addModule, createWorklet.
         * @param workletOrdinal field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams workletOrdinal(Long workletOrdinal) {
            set("workletOrdinal", workletOrdinal);
            return this;
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
         * @param workletTargetId field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams workletTargetId(Optional<Target.TargetID> workletTargetId) {
            set("workletTargetId", workletTargetId.orElse(null));
            return this;
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet. Present only for SharedStorageAccessMethods: addModule, createWorklet, run, selectURL, and any other SharedStorageAccessMethod when the SharedStorageAccessScope is sharedStorageWorklet.
         * @param workletTargetId field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams workletTargetId(Target.TargetID workletTargetId) {
            set("workletTargetId", workletTargetId);
            return this;
        }
        /**
         * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
         * @param withLock field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams withLock(Optional<String> withLock) {
            set("withLock", withLock.orElse(null));
            return this;
        }
        /**
         * Name of the lock to be acquired, if present. Optionally present only for SharedStorageAccessMethods: batchUpdate, set, append, delete, and clear.
         * @param withLock field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams withLock(String withLock) {
            set("withLock", withLock);
            return this;
        }
        /**
         * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
         * @param batchUpdateId field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams batchUpdateId(Optional<String> batchUpdateId) {
            set("batchUpdateId", batchUpdateId.orElse(null));
            return this;
        }
        /**
         * If the method has been called as part of a batchUpdate, then this number identifies the batch to which it belongs. Optionally present only for SharedStorageAccessMethods: batchUpdate (required), set, append, delete, and clear.
         * @param batchUpdateId field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams batchUpdateId(String batchUpdateId) {
            set("batchUpdateId", batchUpdateId);
            return this;
        }
        /**
         * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
         * @param batchSize field value; empty omits the value
         * @return this model
         */
        public SharedStorageAccessParams batchSize(OptionalLong batchSize) {
            set("batchSize", batchSize.isPresent() ? batchSize.getAsLong() : null);
            return this;
        }
        /**
         * Number of modifier methods sent in batch. Present only for SharedStorageAccessMethod: batchUpdate.
         * @param batchSize field value; null removes the value
         * @return this model
         */
        public SharedStorageAccessParams batchSize(Long batchSize) {
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
        @Nonnull public String value() { return value; }
        public static StorageBucketsDurability of(@Nonnull String value) {
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
            return new Storage.SerializedStorageKey((String) require("storageKey"));
        }
        /**
         * If not specified, it is the default bucket of the storageKey.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return Optional.ofNullable((String) raw("name"));
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
         * @param name field value; empty omits the value
         * @return this model
         */
        public StorageBucket name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * If not specified, it is the default bucket of the storageKey.
         * @param name field value; null removes the value
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
            return java.util.Objects.requireNonNull(Storage.StorageBucket.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("bucket")))));
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public String id() {
            return (String) require("id");
        }
        /**
         * Returns the expiration field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch expiration() {
            return new Network.TimeSinceEpoch(((Number) require("expiration")).doubleValue());
        }
        /**
         * Storage quota (bytes).
         * @return the protocol field value
         */
        public double quota() {
            return ((Number) require("quota")).doubleValue();
        }
        /**
         * Returns the persistent field.
         * @return the protocol field value
         */
        public boolean persistent() {
            return (Boolean) require("persistent");
        }
        /**
         * Returns the durability field.
         * @return the protocol field value
         */
        public Storage.StorageBucketsDurability durability() {
            return Storage.StorageBucketsDurability.of((String) require("durability"));
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
            return CdpObject.requireList(require("primarySites"), element0 -> (String) element0);
        }
        /**
         * The associated sites of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        public java.util.List<String> associatedSites() {
            return CdpObject.requireList(require("associatedSites"), element0 -> (String) element0);
        }
        /**
         * The service sites of this set, along with the ccTLDs if there is any.
         * @return the protocol field value
         */
        public java.util.List<String> serviceSites() {
            return CdpObject.requireList(require("serviceSites"), element0 -> (String) element0);
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
        /**
         * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
         * @param frameId protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetStorageKeyForFrameRequest(Page.FrameId frameId) {
            set("frameId", frameId);
        }
        public static GetStorageKeyForFrameRequest fromMap(Map<String, Object> values) {
            GetStorageKeyForFrameRequest instance_ = new GetStorageKeyForFrameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
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
            GetStorageKeyRequest instance_ = new GetStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return Optional.ofNullable(raw("frameId") == null ? null : new Page.FrameId((String) raw("frameId")));
        }
        /**
         * Sets the frameId field.
         * @param frameId field value; empty omits the value
         * @return this model
         */
        public GetStorageKeyRequest frameId(Optional<Page.FrameId> frameId) {
            set("frameId", frameId.orElse(null));
            return this;
        }
        /**
         * Sets the frameId field.
         * @param frameId field value; null removes the value
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
        /**
         * Clears storage for origin.
         * @param origin protocol value
         * @param storageTypes protocol value
         */
        public ClearDataForOriginRequest(String origin, String storageTypes) {
            set("origin", origin);
            set("storageTypes", storageTypes);
        }
        public static ClearDataForOriginRequest fromMap(Map<String, Object> values) {
            ClearDataForOriginRequest instance_ = new ClearDataForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Comma separated list of StorageType to clear.
         * @return the protocol field value
         */
        public String storageTypes() {
            return (String) require("storageTypes");
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
        /**
         * Clears storage for storage key.
         * @param storageKey protocol value
         * @param storageTypes protocol value
         */
        public ClearDataForStorageKeyRequest(String storageKey, String storageTypes) {
            set("storageKey", storageKey);
            set("storageTypes", storageTypes);
        }
        public static ClearDataForStorageKeyRequest fromMap(Map<String, Object> values) {
            ClearDataForStorageKeyRequest instance_ = new ClearDataForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Comma separated list of StorageType to clear.
         * @return the protocol field value
         */
        public String storageTypes() {
            return (String) require("storageTypes");
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
            GetCookiesRequest instance_ = new GetCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public GetCookiesRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value; null removes the value
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
        /**
         * Sets given cookies.
         * @param cookies protocol value
         */
        public SetCookiesRequest(java.util.List<Network.CookieParam> cookies) {
            set("cookies", cookies);
        }
        public static SetCookiesRequest fromMap(Map<String, Object> values) {
            SetCookiesRequest instance_ = new SetCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Cookies to be set.
         * @return the protocol field value
         */
        public java.util.List<Network.CookieParam> cookies() {
            return CdpObject.requireList(require("cookies"), element0 -> java.util.Objects.requireNonNull(Network.CookieParam.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
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
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public SetCookiesRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value; null removes the value
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
            ClearCookiesRequest instance_ = new ClearCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public ClearCookiesRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * Browser context to use when called on the browser endpoint.
         * @param browserContextId field value; null removes the value
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
        /**
         * Returns usage and quota in bytes.
         * @param origin protocol value
         */
        public GetUsageAndQuotaRequest(String origin) {
            set("origin", origin);
        }
        public static GetUsageAndQuotaRequest fromMap(Map<String, Object> values) {
            GetUsageAndQuotaRequest instance_ = new GetUsageAndQuotaRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
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
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         */
        public OverrideQuotaForOriginRequest(String origin) {
            set("origin", origin);
        }
        public static OverrideQuotaForOriginRequest fromMap(Map<String, Object> values) {
            OverrideQuotaForOriginRequest instance_ = new OverrideQuotaForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * The quota size (in bytes) to override the original quota with. If this is called multiple times, the overridden quota will be equal to the quotaSize provided in the final call. If this is called without specifying a quotaSize, the quota will be reset to the default value for the specified origin. If this is called multiple times with different origins, the override will be maintained for each origin until it is disabled (called without a quotaSize).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble quotaSize() {
            Double value = CdpObject.numberAsDouble(raw("quotaSize"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
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
         * @param quotaSize field value; empty omits the value
         * @return this model
         */
        public OverrideQuotaForOriginRequest quotaSize(OptionalDouble quotaSize) {
            set("quotaSize", quotaSize.isPresent() ? quotaSize.getAsDouble() : null);
            return this;
        }
        /**
         * The quota size (in bytes) to override the original quota with. If this is called multiple times, the overridden quota will be equal to the quotaSize provided in the final call. If this is called without specifying a quotaSize, the quota will be reset to the default value for the specified origin. If this is called multiple times with different origins, the override will be maintained for each origin until it is disabled (called without a quotaSize).
         * @param quotaSize field value; null removes the value
         * @return this model
         */
        public OverrideQuotaForOriginRequest quotaSize(Double quotaSize) {
            set("quotaSize", quotaSize);
            return this;
        }
    }
    /**
     * Registers origin to be notified when an update occurs to its cache storage list.
     */
    public static final class TrackCacheStorageForOriginRequest extends CdpObject {
        public TrackCacheStorageForOriginRequest() {}
        /**
         * Registers origin to be notified when an update occurs to its cache storage list.
         * @param origin protocol value
         */
        public TrackCacheStorageForOriginRequest(String origin) {
            set("origin", origin);
        }
        public static TrackCacheStorageForOriginRequest fromMap(Map<String, Object> values) {
            TrackCacheStorageForOriginRequest instance_ = new TrackCacheStorageForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
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
        /**
         * Registers storage key to be notified when an update occurs to its cache storage list.
         * @param storageKey protocol value
         */
        public TrackCacheStorageForStorageKeyRequest(String storageKey) {
            set("storageKey", storageKey);
        }
        public static TrackCacheStorageForStorageKeyRequest fromMap(Map<String, Object> values) {
            TrackCacheStorageForStorageKeyRequest instance_ = new TrackCacheStorageForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
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
        /**
         * Registers origin to be notified when an update occurs to its IndexedDB.
         * @param origin protocol value
         */
        public TrackIndexedDBForOriginRequest(String origin) {
            set("origin", origin);
        }
        public static TrackIndexedDBForOriginRequest fromMap(Map<String, Object> values) {
            TrackIndexedDBForOriginRequest instance_ = new TrackIndexedDBForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
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
        /**
         * Registers storage key to be notified when an update occurs to its IndexedDB.
         * @param storageKey protocol value
         */
        public TrackIndexedDBForStorageKeyRequest(String storageKey) {
            set("storageKey", storageKey);
        }
        public static TrackIndexedDBForStorageKeyRequest fromMap(Map<String, Object> values) {
            TrackIndexedDBForStorageKeyRequest instance_ = new TrackIndexedDBForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
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
        /**
         * Unregisters origin from receiving notifications for cache storage.
         * @param origin protocol value
         */
        public UntrackCacheStorageForOriginRequest(String origin) {
            set("origin", origin);
        }
        public static UntrackCacheStorageForOriginRequest fromMap(Map<String, Object> values) {
            UntrackCacheStorageForOriginRequest instance_ = new UntrackCacheStorageForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
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
        /**
         * Unregisters storage key from receiving notifications for cache storage.
         * @param storageKey protocol value
         */
        public UntrackCacheStorageForStorageKeyRequest(String storageKey) {
            set("storageKey", storageKey);
        }
        public static UntrackCacheStorageForStorageKeyRequest fromMap(Map<String, Object> values) {
            UntrackCacheStorageForStorageKeyRequest instance_ = new UntrackCacheStorageForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
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
        /**
         * Unregisters origin from receiving notifications for IndexedDB.
         * @param origin protocol value
         */
        public UntrackIndexedDBForOriginRequest(String origin) {
            set("origin", origin);
        }
        public static UntrackIndexedDBForOriginRequest fromMap(Map<String, Object> values) {
            UntrackIndexedDBForOriginRequest instance_ = new UntrackIndexedDBForOriginRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Security origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
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
        /**
         * Unregisters storage key from receiving notifications for IndexedDB.
         * @param storageKey protocol value
         */
        public UntrackIndexedDBForStorageKeyRequest(String storageKey) {
            set("storageKey", storageKey);
        }
        public static UntrackIndexedDBForStorageKeyRequest fromMap(Map<String, Object> values) {
            UntrackIndexedDBForStorageKeyRequest instance_ = new UntrackIndexedDBForStorageKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Storage key.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
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
        /**
         * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param issuerOrigin protocol value
         */
        public ClearTrustTokensRequest(String issuerOrigin) {
            set("issuerOrigin", issuerOrigin);
        }
        public static ClearTrustTokensRequest fromMap(Map<String, Object> values) {
            ClearTrustTokensRequest instance_ = new ClearTrustTokensRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the issuerOrigin field.
         * @return the protocol field value
         */
        public String issuerOrigin() {
            return (String) require("issuerOrigin");
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
        /**
         * Gets details for a named interest group.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param name protocol value
         */
        public GetInterestGroupDetailsRequest(String ownerOrigin, String name) {
            set("ownerOrigin", ownerOrigin);
            set("name", name);
        }
        public static GetInterestGroupDetailsRequest fromMap(Map<String, Object> values) {
            GetInterestGroupDetailsRequest instance_ = new GetInterestGroupDetailsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
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
        /**
         * Enables/Disables issuing of interestGroupAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         */
        public SetInterestGroupTrackingRequest(boolean enable) {
            set("enable", enable);
        }
        public static SetInterestGroupTrackingRequest fromMap(Map<String, Object> values) {
            SetInterestGroupTrackingRequest instance_ = new SetInterestGroupTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return (Boolean) require("enable");
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
        /**
         * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         */
        public SetInterestGroupAuctionTrackingRequest(boolean enable) {
            set("enable", enable);
        }
        public static SetInterestGroupAuctionTrackingRequest fromMap(Map<String, Object> values) {
            SetInterestGroupAuctionTrackingRequest instance_ = new SetInterestGroupAuctionTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return (Boolean) require("enable");
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
        /**
         * Gets metadata for an origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         */
        public GetSharedStorageMetadataRequest(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
        }
        public static GetSharedStorageMetadataRequest fromMap(Map<String, Object> values) {
            GetSharedStorageMetadataRequest instance_ = new GetSharedStorageMetadataRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
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
        /**
         * Gets the entries in an given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         */
        public GetSharedStorageEntriesRequest(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
        }
        public static GetSharedStorageEntriesRequest fromMap(Map<String, Object> values) {
            GetSharedStorageEntriesRequest instance_ = new GetSharedStorageEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
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
        /**
         * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param key protocol value
         * @param value protocol value
         */
        public SetSharedStorageEntryRequest(String ownerOrigin, String key, String value) {
            set("ownerOrigin", ownerOrigin);
            set("key", key);
            set("value", value);
        }
        public static SetSharedStorageEntryRequest fromMap(Map<String, Object> values) {
            SetSharedStorageEntryRequest instance_ = new SetSharedStorageEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return (String) require("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * If {@code ignoreIfPresent} is included and true, then only sets the entry if {@code key} doesn&#x27;t already exist.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignoreIfPresent() {
            return Optional.ofNullable((Boolean) raw("ignoreIfPresent"));
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
         * @param ignoreIfPresent field value; empty omits the value
         * @return this model
         */
        public SetSharedStorageEntryRequest ignoreIfPresent(Optional<Boolean> ignoreIfPresent) {
            set("ignoreIfPresent", ignoreIfPresent.orElse(null));
            return this;
        }
        /**
         * If {@code ignoreIfPresent} is included and true, then only sets the entry if {@code key} doesn&#x27;t already exist.
         * @param ignoreIfPresent field value; null removes the value
         * @return this model
         */
        public SetSharedStorageEntryRequest ignoreIfPresent(Boolean ignoreIfPresent) {
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
        /**
         * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param key protocol value
         */
        public DeleteSharedStorageEntryRequest(String ownerOrigin, String key) {
            set("ownerOrigin", ownerOrigin);
            set("key", key);
        }
        public static DeleteSharedStorageEntryRequest fromMap(Map<String, Object> values) {
            DeleteSharedStorageEntryRequest instance_ = new DeleteSharedStorageEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return (String) require("key");
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
        /**
         * Clears all entries for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         */
        public ClearSharedStorageEntriesRequest(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
        }
        public static ClearSharedStorageEntriesRequest fromMap(Map<String, Object> values) {
            ClearSharedStorageEntriesRequest instance_ = new ClearSharedStorageEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
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
        /**
         * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         */
        public ResetSharedStorageBudgetRequest(String ownerOrigin) {
            set("ownerOrigin", ownerOrigin);
        }
        public static ResetSharedStorageBudgetRequest fromMap(Map<String, Object> values) {
            ResetSharedStorageBudgetRequest instance_ = new ResetSharedStorageBudgetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
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
        /**
         * Enables/disables issuing of sharedStorageAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         */
        public SetSharedStorageTrackingRequest(boolean enable) {
            set("enable", enable);
        }
        public static SetSharedStorageTrackingRequest fromMap(Map<String, Object> values) {
            SetSharedStorageTrackingRequest instance_ = new SetSharedStorageTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return (Boolean) require("enable");
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
        /**
         * Set tracking for a storage key&#x27;s buckets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param storageKey protocol value
         * @param enable protocol value
         */
        public SetStorageBucketTrackingRequest(String storageKey, boolean enable) {
            set("storageKey", storageKey);
            set("enable", enable);
        }
        public static SetStorageBucketTrackingRequest fromMap(Map<String, Object> values) {
            SetStorageBucketTrackingRequest instance_ = new SetStorageBucketTrackingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageKey field.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Returns the enable field.
         * @return the protocol field value
         */
        public boolean enable() {
            return (Boolean) require("enable");
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
        /**
         * Deletes the Storage Bucket with the given storage key and bucket name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param bucket protocol value
         */
        public DeleteStorageBucketRequest(Storage.StorageBucket bucket) {
            set("bucket", bucket);
        }
        public static DeleteStorageBucketRequest fromMap(Map<String, Object> values) {
            DeleteStorageBucketRequest instance_ = new DeleteStorageBucketRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the bucket field.
         * @return the protocol field value
         */
        public Storage.StorageBucket bucket() {
            return java.util.Objects.requireNonNull(Storage.StorageBucket.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("bucket")))));
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
        /**
         * Creates a new SetProtectedAudienceKAnonymityRequest with all required parameters.
         * @param owner protocol value
         * @param name protocol value
         * @param hashes protocol value
         */
        public SetProtectedAudienceKAnonymityRequest(String owner, String name, java.util.List<String> hashes) {
            set("owner", owner);
            set("name", name);
            set("hashes", hashes);
        }
        public static SetProtectedAudienceKAnonymityRequest fromMap(Map<String, Object> values) {
            SetProtectedAudienceKAnonymityRequest instance_ = new SetProtectedAudienceKAnonymityRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the owner field.
         * @return the protocol field value
         */
        public String owner() {
            return (String) require("owner");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the hashes field.
         * @return the protocol field value
         */
        public java.util.List<String> hashes() {
            return CdpObject.requireList(require("hashes"), element0 -> (String) element0);
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
            return ((Number) require("usage")).doubleValue();
        }
        /**
         * Storage quota (bytes).
         * @return the protocol field value
         */
        public double quota() {
            return ((Number) require("quota")).doubleValue();
        }
        /**
         * Whether or not the origin has an active storage quota override
         * @return the protocol field value
         */
        public boolean overrideActive() {
            return (Boolean) require("overrideActive");
        }
        /**
         * Storage usage per type (bytes).
         * @return the protocol field value
         */
        public java.util.List<Storage.UsageForType> usageBreakdown() {
            return CdpObject.requireList(require("usageBreakdown"), element0 -> java.util.Objects.requireNonNull(Storage.UsageForType.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return (String) require("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return (String) require("bucketId");
        }
        /**
         * Name of cache in origin.
         * @return the protocol field value
         */
        public String cacheName() {
            return (String) require("cacheName");
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
            return (String) require("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return (String) require("bucketId");
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
            return (String) require("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return (String) require("bucketId");
        }
        /**
         * Database to update.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * ObjectStore to update.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return (String) require("objectStoreName");
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
            return (String) require("origin");
        }
        /**
         * Storage key to update.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
        }
        /**
         * Storage bucket to update.
         * @return the protocol field value
         */
        public String bucketId() {
            return (String) require("bucketId");
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
            return new Network.TimeSinceEpoch(((Number) require("accessTime")).doubleValue());
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAccessType type() {
            return Storage.InterestGroupAccessType.of((String) require("type"));
        }
        /**
         * Returns the ownerOrigin field.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin
         * @return the protocol field value, empty when absent
         */
        public Optional<String> componentSellerOrigin() {
            return Optional.ofNullable((String) raw("componentSellerOrigin"));
        }
        /**
         * For bid or somethingBid event, if done locally and not on a server.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble bid() {
            Double value = CdpObject.numberAsDouble(raw("bid"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Returns the bidCurrency field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bidCurrency() {
            return Optional.ofNullable((String) raw("bidCurrency"));
        }
        /**
         * For non-global events --- links to interestGroupAuctionEvent
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.InterestGroupAuctionId> uniqueAuctionId() {
            return Optional.ofNullable(raw("uniqueAuctionId") == null ? null : new Storage.InterestGroupAuctionId((String) raw("uniqueAuctionId")));
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
         * @param componentSellerOrigin field value; empty omits the value
         * @return this model
         */
        public InterestGroupAccessedEvent componentSellerOrigin(Optional<String> componentSellerOrigin) {
            set("componentSellerOrigin", componentSellerOrigin.orElse(null));
            return this;
        }
        /**
         * For topLevelBid/topLevelAdditionalBid, and when appropriate, win and additionalBidWin
         * @param componentSellerOrigin field value; null removes the value
         * @return this model
         */
        public InterestGroupAccessedEvent componentSellerOrigin(String componentSellerOrigin) {
            set("componentSellerOrigin", componentSellerOrigin);
            return this;
        }
        /**
         * For bid or somethingBid event, if done locally and not on a server.
         * @param bid field value; empty omits the value
         * @return this model
         */
        public InterestGroupAccessedEvent bid(OptionalDouble bid) {
            set("bid", bid.isPresent() ? bid.getAsDouble() : null);
            return this;
        }
        /**
         * For bid or somethingBid event, if done locally and not on a server.
         * @param bid field value; null removes the value
         * @return this model
         */
        public InterestGroupAccessedEvent bid(Double bid) {
            set("bid", bid);
            return this;
        }
        /**
         * Sets the bidCurrency field.
         * @param bidCurrency field value; empty omits the value
         * @return this model
         */
        public InterestGroupAccessedEvent bidCurrency(Optional<String> bidCurrency) {
            set("bidCurrency", bidCurrency.orElse(null));
            return this;
        }
        /**
         * Sets the bidCurrency field.
         * @param bidCurrency field value; null removes the value
         * @return this model
         */
        public InterestGroupAccessedEvent bidCurrency(String bidCurrency) {
            set("bidCurrency", bidCurrency);
            return this;
        }
        /**
         * For non-global events --- links to interestGroupAuctionEvent
         * @param uniqueAuctionId field value; empty omits the value
         * @return this model
         */
        public InterestGroupAccessedEvent uniqueAuctionId(Optional<Storage.InterestGroupAuctionId> uniqueAuctionId) {
            set("uniqueAuctionId", uniqueAuctionId.orElse(null));
            return this;
        }
        /**
         * For non-global events --- links to interestGroupAuctionEvent
         * @param uniqueAuctionId field value; null removes the value
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
            return new Network.TimeSinceEpoch(((Number) require("eventTime")).doubleValue());
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAuctionEventType type() {
            return Storage.InterestGroupAuctionEventType.of((String) require("type"));
        }
        /**
         * Returns the uniqueAuctionId field.
         * @return the protocol field value
         */
        public Storage.InterestGroupAuctionId uniqueAuctionId() {
            return new Storage.InterestGroupAuctionId((String) require("uniqueAuctionId"));
        }
        /**
         * Set for child auctions.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.InterestGroupAuctionId> parentAuctionId() {
            return Optional.ofNullable(raw("parentAuctionId") == null ? null : new Storage.InterestGroupAuctionId((String) raw("parentAuctionId")));
        }
        /**
         * Set for started and configResolved
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> auctionConfig() {
            return Optional.ofNullable(objectMap(raw("auctionConfig")));
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
         * @param parentAuctionId field value; empty omits the value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent parentAuctionId(Optional<Storage.InterestGroupAuctionId> parentAuctionId) {
            set("parentAuctionId", parentAuctionId.orElse(null));
            return this;
        }
        /**
         * Set for child auctions.
         * @param parentAuctionId field value; null removes the value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent parentAuctionId(Storage.InterestGroupAuctionId parentAuctionId) {
            set("parentAuctionId", parentAuctionId);
            return this;
        }
        /**
         * Set for started and configResolved
         * @param auctionConfig field value; empty omits the value
         * @return this model
         */
        public InterestGroupAuctionEventOccurredEvent auctionConfig(Optional<java.util.Map<String, Object>> auctionConfig) {
            set("auctionConfig", auctionConfig.orElse(null));
            return this;
        }
        /**
         * Set for started and configResolved
         * @param auctionConfig field value; null removes the value
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
            return Storage.InterestGroupAuctionFetchType.of((String) require("type"));
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return new Network.RequestId((String) require("requestId"));
        }
        /**
         * This is the set of the auctions using the worklet that issued this request. In the case of trusted signals, it&#x27;s possible that only some of them actually care about the keys being queried.
         * @return the protocol field value
         */
        public java.util.List<Storage.InterestGroupAuctionId> auctions() {
            return CdpObject.requireList(require("auctions"), element0 -> new Storage.InterestGroupAuctionId((String) element0));
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
            return new Network.TimeSinceEpoch(((Number) require("accessTime")).doubleValue());
        }
        /**
         * Enum value indicating the access scope.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessScope scope() {
            return Storage.SharedStorageAccessScope.of((String) require("scope"));
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessMethod method() {
            return Storage.SharedStorageAccessMethod.of((String) require("method"));
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @return the protocol field value
         */
        public Page.FrameId mainFrameId() {
            return new Page.FrameId((String) require("mainFrameId"));
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
        }
        /**
         * Serialization of the site owning the Shared Storage data.
         * @return the protocol field value
         */
        public String ownerSite() {
            return (String) require("ownerSite");
        }
        /**
         * The sub-parameters wrapped by {@code params} are all optional and their presence/absence depends on {@code type}.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessParams params() {
            return java.util.Objects.requireNonNull(Storage.SharedStorageAccessParams.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("params")))));
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
            return new Network.TimeSinceEpoch(((Number) require("finishedTime")).doubleValue());
        }
        /**
         * Time, in microseconds, from start of shared storage JS API call until end of operation execution in the worklet.
         * @return the protocol field value
         */
        public long executionTime() {
            return ((Number) require("executionTime")).longValue();
        }
        /**
         * Enum value indicating the Shared Storage API method invoked.
         * @return the protocol field value
         */
        public Storage.SharedStorageAccessMethod method() {
            return Storage.SharedStorageAccessMethod.of((String) require("method"));
        }
        /**
         * ID of the operation call.
         * @return the protocol field value
         */
        public String operationId() {
            return (String) require("operationId");
        }
        /**
         * Hex representation of the DevTools token used as the TargetID for the associated shared storage worklet.
         * @return the protocol field value
         */
        public Target.TargetID workletTargetId() {
            return new Target.TargetID((String) require("workletTargetId"));
        }
        /**
         * DevTools Frame Token for the primary frame tree&#x27;s root.
         * @return the protocol field value
         */
        public Page.FrameId mainFrameId() {
            return new Page.FrameId((String) require("mainFrameId"));
        }
        /**
         * Serialization of the origin owning the Shared Storage data.
         * @return the protocol field value
         */
        public String ownerOrigin() {
            return (String) require("ownerOrigin");
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
            return java.util.Objects.requireNonNull(Storage.StorageBucketInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("bucketInfo")))));
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
            return (String) require("bucketId");
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
         * @param frameId protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Storage.SerializedStorageKey> getStorageKeyForFrame(Page.FrameId frameId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Storage.getStorageKeyForFrame", params, result_ -> new Storage.SerializedStorageKey((String) java.util.Objects.requireNonNull(result_.get("storageKey"))));
        }
        /**
         * Returns a storage key given a frame id. Deprecated. Please use Storage.getStorageKey instead.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Storage.SerializedStorageKey> getStorageKeyForFrame(GetStorageKeyForFrameRequest request) {
            return client.call("Storage.getStorageKeyForFrame", request == null ? null : request.toMap(), result_ -> new Storage.SerializedStorageKey((String) java.util.Objects.requireNonNull(result_.get("storageKey"))));
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SerializedStorageKey> getStorageKey(Optional<Page.FrameId> frameId) {
            Map<String, Object> params = new LinkedHashMap<>();
            frameId.ifPresent(value_ -> params.put("frameId", CdpObject.json(value_)));
            return client.call("Storage.getStorageKey", params, result_ -> new Storage.SerializedStorageKey((String) java.util.Objects.requireNonNull(result_.get("storageKey"))));
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SerializedStorageKey> getStorageKey() {
            return getStorageKey(Optional.empty());
        }
        /**
         * Returns storage key for the given frame. If no frame ID is provided, the storage key of the target executing this command is returned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SerializedStorageKey> getStorageKey(GetStorageKeyRequest request) {
            return client.call("Storage.getStorageKey", request == null ? null : request.toMap(), result_ -> new Storage.SerializedStorageKey((String) java.util.Objects.requireNonNull(result_.get("storageKey"))));
        }
        /**
         * Clears storage for origin.
         * @param origin protocol value
         * @param storageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForOrigin(String origin, String storageTypes) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            params.put("storageTypes", CdpObject.json(storageTypes));
            return client.call("Storage.clearDataForOrigin", params, result_ -> null);
        }
        /**
         * Clears storage for origin.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForOrigin(ClearDataForOriginRequest request) {
            return client.call("Storage.clearDataForOrigin", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears storage for storage key.
         * @param storageKey protocol value
         * @param storageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForStorageKey(String storageKey, String storageTypes) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageKey", CdpObject.json(storageKey));
            params.put("storageTypes", CdpObject.json(storageTypes));
            return client.call("Storage.clearDataForStorageKey", params, result_ -> null);
        }
        /**
         * Clears storage for storage key.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDataForStorageKey(ClearDataForStorageKeyRequest request) {
            return client.call("Storage.clearDataForStorageKey", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Returns all browser cookies.
         * @param browserContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Storage.getCookies", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("cookies")), element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns all browser cookies.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies() {
            return getCookies(Optional.empty());
        }
        /**
         * Returns all browser cookies.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(GetCookiesRequest request) {
            return client.call("Storage.getCookies", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("cookies")), element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Sets given cookies.
         * @param cookies protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(java.util.List<Network.CookieParam> cookies, Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("cookies", CdpObject.json(cookies));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Storage.setCookies", params, result_ -> null);
        }
        /**
         * Sets given cookies.
         * @param cookies protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(java.util.List<Network.CookieParam> cookies) {
            return setCookies(cookies, Optional.empty());
        }
        /**
         * Sets given cookies.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(SetCookiesRequest request) {
            return client.call("Storage.setCookies", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears cookies.
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCookies(Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Storage.clearCookies", params, result_ -> null);
        }
        /**
         * Clears cookies.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCookies() {
            return clearCookies(Optional.empty());
        }
        /**
         * Clears cookies.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCookies(ClearCookiesRequest request) {
            return client.call("Storage.clearCookies", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Returns usage and quota in bytes.
         * @param origin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetUsageAndQuotaResult> getUsageAndQuota(String origin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Storage.getUsageAndQuota", params, result_ -> new GetUsageAndQuotaResult(result_));
        }
        /**
         * Returns usage and quota in bytes.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetUsageAndQuotaResult> getUsageAndQuota(GetUsageAndQuotaRequest request) {
            return client.call("Storage.getUsageAndQuota", request == null ? null : request.toMap(), result_ -> new GetUsageAndQuotaResult(result_));
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @param quotaSize protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideQuotaForOrigin(String origin, OptionalDouble quotaSize) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            quotaSize.ifPresent(value_ -> params.put("quotaSize", value_));
            return client.call("Storage.overrideQuotaForOrigin", params, result_ -> null);
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideQuotaForOrigin(String origin) {
            return overrideQuotaForOrigin(origin, OptionalDouble.empty());
        }
        /**
         * Override quota for the specified origin
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideQuotaForOrigin(OverrideQuotaForOriginRequest request) {
            return client.call("Storage.overrideQuotaForOrigin", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Registers origin to be notified when an update occurs to its cache storage list.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForOrigin(String origin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Storage.trackCacheStorageForOrigin", params, result_ -> null);
        }
        /**
         * Registers origin to be notified when an update occurs to its cache storage list.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForOrigin(TrackCacheStorageForOriginRequest request) {
            return client.call("Storage.trackCacheStorageForOrigin", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Registers storage key to be notified when an update occurs to its cache storage list.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForStorageKey(String storageKey) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageKey", CdpObject.json(storageKey));
            return client.call("Storage.trackCacheStorageForStorageKey", params, result_ -> null);
        }
        /**
         * Registers storage key to be notified when an update occurs to its cache storage list.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackCacheStorageForStorageKey(TrackCacheStorageForStorageKeyRequest request) {
            return client.call("Storage.trackCacheStorageForStorageKey", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Registers origin to be notified when an update occurs to its IndexedDB.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForOrigin(String origin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Storage.trackIndexedDBForOrigin", params, result_ -> null);
        }
        /**
         * Registers origin to be notified when an update occurs to its IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForOrigin(TrackIndexedDBForOriginRequest request) {
            return client.call("Storage.trackIndexedDBForOrigin", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Registers storage key to be notified when an update occurs to its IndexedDB.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForStorageKey(String storageKey) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageKey", CdpObject.json(storageKey));
            return client.call("Storage.trackIndexedDBForStorageKey", params, result_ -> null);
        }
        /**
         * Registers storage key to be notified when an update occurs to its IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackIndexedDBForStorageKey(TrackIndexedDBForStorageKeyRequest request) {
            return client.call("Storage.trackIndexedDBForStorageKey", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Unregisters origin from receiving notifications for cache storage.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForOrigin(String origin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Storage.untrackCacheStorageForOrigin", params, result_ -> null);
        }
        /**
         * Unregisters origin from receiving notifications for cache storage.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForOrigin(UntrackCacheStorageForOriginRequest request) {
            return client.call("Storage.untrackCacheStorageForOrigin", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Unregisters storage key from receiving notifications for cache storage.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForStorageKey(String storageKey) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageKey", CdpObject.json(storageKey));
            return client.call("Storage.untrackCacheStorageForStorageKey", params, result_ -> null);
        }
        /**
         * Unregisters storage key from receiving notifications for cache storage.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackCacheStorageForStorageKey(UntrackCacheStorageForStorageKeyRequest request) {
            return client.call("Storage.untrackCacheStorageForStorageKey", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Unregisters origin from receiving notifications for IndexedDB.
         * @param origin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForOrigin(String origin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            return client.call("Storage.untrackIndexedDBForOrigin", params, result_ -> null);
        }
        /**
         * Unregisters origin from receiving notifications for IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForOrigin(UntrackIndexedDBForOriginRequest request) {
            return client.call("Storage.untrackIndexedDBForOrigin", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Unregisters storage key from receiving notifications for IndexedDB.
         * @param storageKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForStorageKey(String storageKey) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageKey", CdpObject.json(storageKey));
            return client.call("Storage.untrackIndexedDBForStorageKey", params, result_ -> null);
        }
        /**
         * Unregisters storage key from receiving notifications for IndexedDB.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> untrackIndexedDBForStorageKey(UntrackIndexedDBForStorageKeyRequest request) {
            return client.call("Storage.untrackIndexedDBForStorageKey", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Returns the number of stored Trust Tokens per issuer for the current browsing context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.TrustTokens>> getTrustTokens() {
            return client.call("Storage.getTrustTokens", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("tokens")), element0 -> java.util.Objects.requireNonNull(Storage.TrustTokens.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param issuerOrigin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> clearTrustTokens(String issuerOrigin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("issuerOrigin", CdpObject.json(issuerOrigin));
            return client.call("Storage.clearTrustTokens", params, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("didDeleteTokens")));
        }
        /**
         * Removes all Trust Tokens issued by the provided issuerOrigin. Leaves other stored data, including the issuer&#x27;s Redemption Records, intact.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> clearTrustTokens(ClearTrustTokensRequest request) {
            return client.call("Storage.clearTrustTokens", request == null ? null : request.toMap(), result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("didDeleteTokens")));
        }
        /**
         * Gets details for a named interest group.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param name protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getInterestGroupDetails(String ownerOrigin, String name) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            params.put("name", CdpObject.json(name));
            return client.call("Storage.getInterestGroupDetails", params, result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("details")))));
        }
        /**
         * Gets details for a named interest group.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getInterestGroupDetails(GetInterestGroupDetailsRequest request) {
            return client.call("Storage.getInterestGroupDetails", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("details")))));
        }
        /**
         * Enables/Disables issuing of interestGroupAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupTracking(boolean enable) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enable", CdpObject.json(enable));
            return client.call("Storage.setInterestGroupTracking", params, result_ -> null);
        }
        /**
         * Enables/Disables issuing of interestGroupAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupTracking(SetInterestGroupTrackingRequest request) {
            return client.call("Storage.setInterestGroupTracking", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupAuctionTracking(boolean enable) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enable", CdpObject.json(enable));
            return client.call("Storage.setInterestGroupAuctionTracking", params, result_ -> null);
        }
        /**
         * Enables/Disables issuing of interestGroupAuctionEventOccurred and interestGroupAuctionNetworkRequestCreated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterestGroupAuctionTracking(SetInterestGroupAuctionTrackingRequest request) {
            return client.call("Storage.setInterestGroupAuctionTracking", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Gets metadata for an origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SharedStorageMetadata> getSharedStorageMetadata(String ownerOrigin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            return client.call("Storage.getSharedStorageMetadata", params, result_ -> java.util.Objects.requireNonNull(Storage.SharedStorageMetadata.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("metadata")))))));
        }
        /**
         * Gets metadata for an origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Storage.SharedStorageMetadata> getSharedStorageMetadata(GetSharedStorageMetadataRequest request) {
            return client.call("Storage.getSharedStorageMetadata", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Storage.SharedStorageMetadata.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("metadata")))))));
        }
        /**
         * Gets the entries in an given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.SharedStorageEntry>> getSharedStorageEntries(String ownerOrigin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            return client.call("Storage.getSharedStorageEntries", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("entries")), element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Gets the entries in an given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.SharedStorageEntry>> getSharedStorageEntries(GetSharedStorageEntriesRequest request) {
            return client.call("Storage.getSharedStorageEntries", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("entries")), element0 -> java.util.Objects.requireNonNull(Storage.SharedStorageEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
        public CompletionStage<Void> setSharedStorageEntry(String ownerOrigin, String key, String value, Optional<Boolean> ignoreIfPresent) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            params.put("key", CdpObject.json(key));
            params.put("value", CdpObject.json(value));
            ignoreIfPresent.ifPresent(value_ -> params.put("ignoreIfPresent", value_));
            return client.call("Storage.setSharedStorageEntry", params, result_ -> null);
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
            return setSharedStorageEntry(ownerOrigin, key, value, Optional.empty());
        }
        /**
         * Sets entry with {@code key} and {@code value} for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageEntry(SetSharedStorageEntryRequest request) {
            return client.call("Storage.setSharedStorageEntry", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @param key protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteSharedStorageEntry(String ownerOrigin, String key) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            params.put("key", CdpObject.json(key));
            return client.call("Storage.deleteSharedStorageEntry", params, result_ -> null);
        }
        /**
         * Deletes entry for {@code key} (if it exists) for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteSharedStorageEntry(DeleteSharedStorageEntryRequest request) {
            return client.call("Storage.deleteSharedStorageEntry", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears all entries for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearSharedStorageEntries(String ownerOrigin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            return client.call("Storage.clearSharedStorageEntries", params, result_ -> null);
        }
        /**
         * Clears all entries for a given origin&#x27;s shared storage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearSharedStorageEntries(ClearSharedStorageEntriesRequest request) {
            return client.call("Storage.clearSharedStorageEntries", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ownerOrigin protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetSharedStorageBudget(String ownerOrigin) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ownerOrigin", CdpObject.json(ownerOrigin));
            return client.call("Storage.resetSharedStorageBudget", params, result_ -> null);
        }
        /**
         * Resets the budget for {@code ownerOrigin} by clearing all budget withdrawals.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetSharedStorageBudget(ResetSharedStorageBudgetRequest request) {
            return client.call("Storage.resetSharedStorageBudget", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Enables/disables issuing of sharedStorageAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageTracking(boolean enable) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enable", CdpObject.json(enable));
            return client.call("Storage.setSharedStorageTracking", params, result_ -> null);
        }
        /**
         * Enables/disables issuing of sharedStorageAccessed events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSharedStorageTracking(SetSharedStorageTrackingRequest request) {
            return client.call("Storage.setSharedStorageTracking", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Set tracking for a storage key&#x27;s buckets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param storageKey protocol value
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageBucketTracking(String storageKey, boolean enable) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageKey", CdpObject.json(storageKey));
            params.put("enable", CdpObject.json(enable));
            return client.call("Storage.setStorageBucketTracking", params, result_ -> null);
        }
        /**
         * Set tracking for a storage key&#x27;s buckets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageBucketTracking(SetStorageBucketTrackingRequest request) {
            return client.call("Storage.setStorageBucketTracking", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Deletes the Storage Bucket with the given storage key and bucket name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param bucket protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteStorageBucket(Storage.StorageBucket bucket) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("bucket", CdpObject.json(bucket));
            return client.call("Storage.deleteStorageBucket", params, result_ -> null);
        }
        /**
         * Deletes the Storage Bucket with the given storage key and bucket name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteStorageBucket(DeleteStorageBucketRequest request) {
            return client.call("Storage.deleteStorageBucket", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Deletes state for sites identified as potential bounce trackers, immediately.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> runBounceTrackingMitigations() {
            return client.call("Storage.runBounceTrackingMitigations", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("deletedSites")), element0 -> (String) element0));
        }
        /**
         * Returns the effective Related Website Sets in use by this profile for the browser session. The effective Related Website Sets will not change during a browser session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Storage.RelatedWebsiteSet>> getRelatedWebsiteSets() {
            return client.call("Storage.getRelatedWebsiteSets", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("sets")), element0 -> java.util.Objects.requireNonNull(Storage.RelatedWebsiteSet.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Invokes Storage.setProtectedAudienceKAnonymity.
         * @param owner protocol value
         * @param name protocol value
         * @param hashes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setProtectedAudienceKAnonymity(String owner, String name, java.util.List<String> hashes) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("owner", CdpObject.json(owner));
            params.put("name", CdpObject.json(name));
            params.put("hashes", CdpObject.json(hashes));
            return client.call("Storage.setProtectedAudienceKAnonymity", params, result_ -> null);
        }
        /**
         * Invokes Storage.setProtectedAudienceKAnonymity with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setProtectedAudienceKAnonymity(SetProtectedAudienceKAnonymityRequest request) {
            return client.call("Storage.setProtectedAudienceKAnonymity", request == null ? null : request.toMap(), result_ -> null);
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
    }
}
