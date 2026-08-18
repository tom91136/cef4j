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
 * Chrome DevTools Protocol IndexedDB domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/IndexedDB.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class IndexedDB {
    private IndexedDB() {}
    /**
     * Database with an array of object stores.
     */
    public static final class DatabaseWithObjectStores extends CdpObject {
        public DatabaseWithObjectStores() {}
        private DatabaseWithObjectStores(Map<String, Object> values) { super(values); }
        public static DatabaseWithObjectStores fromMap(Map<String, Object> values) {
            return new DatabaseWithObjectStores(values);
        }
        /**
         * Database name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Database version (type is not &#x27;integer&#x27;, as the standard requires the version number to be &#x27;unsigned long long&#x27;)
         * @return the protocol field value
         */
        public double version() {
            return ((Number) require("version")).doubleValue();
        }
        /**
         * Object stores in this database.
         * @return the protocol field value
         */
        public java.util.List<IndexedDB.ObjectStore> objectStores() {
            return CdpObject.requireList(require("objectStores"), element0 -> java.util.Objects.requireNonNull(IndexedDB.ObjectStore.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Database name.
         * @param name field value
         * @return this model
         */
        public DatabaseWithObjectStores name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Database version (type is not &#x27;integer&#x27;, as the standard requires the version number to be &#x27;unsigned long long&#x27;)
         * @param version field value
         * @return this model
         */
        public DatabaseWithObjectStores version(double version) {
            set("version", version);
            return this;
        }
        /**
         * Object stores in this database.
         * @param objectStores field value
         * @return this model
         */
        public DatabaseWithObjectStores objectStores(java.util.List<IndexedDB.ObjectStore> objectStores) {
            set("objectStores", objectStores);
            return this;
        }
    }
    /**
     * Object store.
     */
    public static final class ObjectStore extends CdpObject {
        public ObjectStore() {}
        private ObjectStore(Map<String, Object> values) { super(values); }
        public static ObjectStore fromMap(Map<String, Object> values) {
            return new ObjectStore(values);
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Object store key path.
         * @return the protocol field value
         */
        public IndexedDB.KeyPath keyPath() {
            return java.util.Objects.requireNonNull(IndexedDB.KeyPath.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("keyPath")))));
        }
        /**
         * If true, object store has auto increment flag set.
         * @return the protocol field value
         */
        public boolean autoIncrement() {
            return (Boolean) require("autoIncrement");
        }
        /**
         * Indexes in this object store.
         * @return the protocol field value
         */
        public java.util.List<IndexedDB.ObjectStoreIndex> indexes() {
            return CdpObject.requireList(require("indexes"), element0 -> java.util.Objects.requireNonNull(IndexedDB.ObjectStoreIndex.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Object store name.
         * @param name field value
         * @return this model
         */
        public ObjectStore name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Object store key path.
         * @param keyPath field value
         * @return this model
         */
        public ObjectStore keyPath(IndexedDB.KeyPath keyPath) {
            set("keyPath", keyPath);
            return this;
        }
        /**
         * If true, object store has auto increment flag set.
         * @param autoIncrement field value
         * @return this model
         */
        public ObjectStore autoIncrement(boolean autoIncrement) {
            set("autoIncrement", autoIncrement);
            return this;
        }
        /**
         * Indexes in this object store.
         * @param indexes field value
         * @return this model
         */
        public ObjectStore indexes(java.util.List<IndexedDB.ObjectStoreIndex> indexes) {
            set("indexes", indexes);
            return this;
        }
    }
    /**
     * Object store index.
     */
    public static final class ObjectStoreIndex extends CdpObject {
        public ObjectStoreIndex() {}
        private ObjectStoreIndex(Map<String, Object> values) { super(values); }
        public static ObjectStoreIndex fromMap(Map<String, Object> values) {
            return new ObjectStoreIndex(values);
        }
        /**
         * Index name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Index key path.
         * @return the protocol field value
         */
        public IndexedDB.KeyPath keyPath() {
            return java.util.Objects.requireNonNull(IndexedDB.KeyPath.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("keyPath")))));
        }
        /**
         * If true, index is unique.
         * @return the protocol field value
         */
        public boolean unique() {
            return (Boolean) require("unique");
        }
        /**
         * If true, index allows multiple entries for a key.
         * @return the protocol field value
         */
        public boolean multiEntry() {
            return (Boolean) require("multiEntry");
        }
        /**
         * Index name.
         * @param name field value
         * @return this model
         */
        public ObjectStoreIndex name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Index key path.
         * @param keyPath field value
         * @return this model
         */
        public ObjectStoreIndex keyPath(IndexedDB.KeyPath keyPath) {
            set("keyPath", keyPath);
            return this;
        }
        /**
         * If true, index is unique.
         * @param unique field value
         * @return this model
         */
        public ObjectStoreIndex unique(boolean unique) {
            set("unique", unique);
            return this;
        }
        /**
         * If true, index allows multiple entries for a key.
         * @param multiEntry field value
         * @return this model
         */
        public ObjectStoreIndex multiEntry(boolean multiEntry) {
            set("multiEntry", multiEntry);
            return this;
        }
    }
    /**
     * Key.
     */
    public static final class Key extends CdpObject {
        public Key() {}
        private Key(Map<String, Object> values) { super(values); }
        public static Key fromMap(Map<String, Object> values) {
            return new Key(values);
        }
        /**
         * Key type.
         */
        public enum TypeValues implements CdpValue<String> {
            NUMBER("number"),
            STRING("string"),
            DATE("date"),
            ARRAY("array");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Key type.
         * @return the protocol field value
         */
        public Key.TypeValues type() {
            return Key.TypeValues.of((String) require("type"));
        }
        /**
         * Number value.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble number() {
            Double value = CdpObject.numberAsDouble(raw("number"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * String value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> string() {
            return Optional.ofNullable((String) raw("string"));
        }
        /**
         * Date value.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble date() {
            Double value = CdpObject.numberAsDouble(raw("date"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Array value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<IndexedDB.Key>> array() {
            return Optional.ofNullable(list(raw("array"), element0 -> java.util.Objects.requireNonNull(IndexedDB.Key.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Key type.
         * @param type field value
         * @return this model
         */
        public Key type(Key.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Number value.
         * @param number field value; empty omits the value
         * @return this model
         */
        public Key number(OptionalDouble number) {
            set("number", number.isPresent() ? number.getAsDouble() : null);
            return this;
        }
        /**
         * Number value.
         * @param number field value; null removes the value
         * @return this model
         */
        public Key number(Double number) {
            set("number", number);
            return this;
        }
        /**
         * String value.
         * @param string field value; empty omits the value
         * @return this model
         */
        public Key string(Optional<String> string) {
            set("string", string.orElse(null));
            return this;
        }
        /**
         * String value.
         * @param string field value; null removes the value
         * @return this model
         */
        public Key string(String string) {
            set("string", string);
            return this;
        }
        /**
         * Date value.
         * @param date field value; empty omits the value
         * @return this model
         */
        public Key date(OptionalDouble date) {
            set("date", date.isPresent() ? date.getAsDouble() : null);
            return this;
        }
        /**
         * Date value.
         * @param date field value; null removes the value
         * @return this model
         */
        public Key date(Double date) {
            set("date", date);
            return this;
        }
        /**
         * Array value.
         * @param array field value; empty omits the value
         * @return this model
         */
        public Key array(Optional<java.util.List<IndexedDB.Key>> array) {
            set("array", array.orElse(null));
            return this;
        }
        /**
         * Array value.
         * @param array field value; null removes the value
         * @return this model
         */
        public Key array(java.util.List<IndexedDB.Key> array) {
            set("array", array);
            return this;
        }
    }
    /**
     * Key range.
     */
    public static final class KeyRange extends CdpObject {
        public KeyRange() {}
        private KeyRange(Map<String, Object> values) { super(values); }
        public static KeyRange fromMap(Map<String, Object> values) {
            return new KeyRange(values);
        }
        /**
         * Lower bound.
         * @return the protocol field value, empty when absent
         */
        public Optional<IndexedDB.Key> lower() {
            return Optional.ofNullable(raw("lower") == null ? null : IndexedDB.Key.fromMap(java.util.Objects.requireNonNull(objectMap(raw("lower")))));
        }
        /**
         * Upper bound.
         * @return the protocol field value, empty when absent
         */
        public Optional<IndexedDB.Key> upper() {
            return Optional.ofNullable(raw("upper") == null ? null : IndexedDB.Key.fromMap(java.util.Objects.requireNonNull(objectMap(raw("upper")))));
        }
        /**
         * If true lower bound is open.
         * @return the protocol field value
         */
        public boolean lowerOpen() {
            return (Boolean) require("lowerOpen");
        }
        /**
         * If true upper bound is open.
         * @return the protocol field value
         */
        public boolean upperOpen() {
            return (Boolean) require("upperOpen");
        }
        /**
         * Lower bound.
         * @param lower field value; empty omits the value
         * @return this model
         */
        public KeyRange lower(Optional<IndexedDB.Key> lower) {
            set("lower", lower.orElse(null));
            return this;
        }
        /**
         * Lower bound.
         * @param lower field value; null removes the value
         * @return this model
         */
        public KeyRange lower(IndexedDB.Key lower) {
            set("lower", lower);
            return this;
        }
        /**
         * Upper bound.
         * @param upper field value; empty omits the value
         * @return this model
         */
        public KeyRange upper(Optional<IndexedDB.Key> upper) {
            set("upper", upper.orElse(null));
            return this;
        }
        /**
         * Upper bound.
         * @param upper field value; null removes the value
         * @return this model
         */
        public KeyRange upper(IndexedDB.Key upper) {
            set("upper", upper);
            return this;
        }
        /**
         * If true lower bound is open.
         * @param lowerOpen field value
         * @return this model
         */
        public KeyRange lowerOpen(boolean lowerOpen) {
            set("lowerOpen", lowerOpen);
            return this;
        }
        /**
         * If true upper bound is open.
         * @param upperOpen field value
         * @return this model
         */
        public KeyRange upperOpen(boolean upperOpen) {
            set("upperOpen", upperOpen);
            return this;
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
         * Key object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject key() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("key")))));
        }
        /**
         * Primary key object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject primaryKey() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("primaryKey")))));
        }
        /**
         * Value object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject value() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("value")))));
        }
        /**
         * Key object.
         * @param key field value
         * @return this model
         */
        public DataEntry key(Runtime.RemoteObject key) {
            set("key", key);
            return this;
        }
        /**
         * Primary key object.
         * @param primaryKey field value
         * @return this model
         */
        public DataEntry primaryKey(Runtime.RemoteObject primaryKey) {
            set("primaryKey", primaryKey);
            return this;
        }
        /**
         * Value object.
         * @param value field value
         * @return this model
         */
        public DataEntry value(Runtime.RemoteObject value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Key path.
     */
    public static final class KeyPath extends CdpObject {
        public KeyPath() {}
        private KeyPath(Map<String, Object> values) { super(values); }
        public static KeyPath fromMap(Map<String, Object> values) {
            return new KeyPath(values);
        }
        /**
         * Key path type.
         */
        public enum TypeValues implements CdpValue<String> {
            NULL("null"),
            STRING("string"),
            ARRAY("array");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Key path type.
         * @return the protocol field value
         */
        public KeyPath.TypeValues type() {
            return KeyPath.TypeValues.of((String) require("type"));
        }
        /**
         * String value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> string() {
            return Optional.ofNullable((String) raw("string"));
        }
        /**
         * Array value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> array() {
            return Optional.ofNullable(list(raw("array"), element0 -> (String) element0));
        }
        /**
         * Key path type.
         * @param type field value
         * @return this model
         */
        public KeyPath type(KeyPath.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * String value.
         * @param string field value; empty omits the value
         * @return this model
         */
        public KeyPath string(Optional<String> string) {
            set("string", string.orElse(null));
            return this;
        }
        /**
         * String value.
         * @param string field value; null removes the value
         * @return this model
         */
        public KeyPath string(String string) {
            set("string", string);
            return this;
        }
        /**
         * Array value.
         * @param array field value; empty omits the value
         * @return this model
         */
        public KeyPath array(Optional<java.util.List<String>> array) {
            set("array", array.orElse(null));
            return this;
        }
        /**
         * Array value.
         * @param array field value; null removes the value
         * @return this model
         */
        public KeyPath array(java.util.List<String> array) {
            set("array", array);
            return this;
        }
    }
    /**
     * Requests data from object store or index.
     */
    public static final class RequestDataResult extends CdpObject {
        public RequestDataResult() {}
        private RequestDataResult(Map<String, Object> values) { super(values); }
        public static RequestDataResult fromMap(Map<String, Object> values) {
            return new RequestDataResult(values);
        }
        /**
         * Array of object store data entries.
         * @return the protocol field value
         */
        public java.util.List<IndexedDB.DataEntry> objectStoreDataEntries() {
            return CdpObject.requireList(require("objectStoreDataEntries"), element0 -> java.util.Objects.requireNonNull(IndexedDB.DataEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * If true, there are more entries to fetch in the given range.
         * @return the protocol field value
         */
        public boolean hasMore() {
            return (Boolean) require("hasMore");
        }
        /**
         * Array of object store data entries.
         * @param objectStoreDataEntries field value
         * @return this model
         */
        public RequestDataResult objectStoreDataEntries(java.util.List<IndexedDB.DataEntry> objectStoreDataEntries) {
            set("objectStoreDataEntries", objectStoreDataEntries);
            return this;
        }
        /**
         * If true, there are more entries to fetch in the given range.
         * @param hasMore field value
         * @return this model
         */
        public RequestDataResult hasMore(boolean hasMore) {
            set("hasMore", hasMore);
            return this;
        }
    }
    /**
     * Gets metadata of an object store.
     */
    public static final class GetMetadataResult extends CdpObject {
        public GetMetadataResult() {}
        private GetMetadataResult(Map<String, Object> values) { super(values); }
        public static GetMetadataResult fromMap(Map<String, Object> values) {
            return new GetMetadataResult(values);
        }
        /**
         * the entries count
         * @return the protocol field value
         */
        public double entriesCount() {
            return ((Number) require("entriesCount")).doubleValue();
        }
        /**
         * the current value of key generator, to become the next inserted key into the object store. Valid if objectStore.autoIncrement is true.
         * @return the protocol field value
         */
        public double keyGeneratorValue() {
            return ((Number) require("keyGeneratorValue")).doubleValue();
        }
        /**
         * the entries count
         * @param entriesCount field value
         * @return this model
         */
        public GetMetadataResult entriesCount(double entriesCount) {
            set("entriesCount", entriesCount);
            return this;
        }
        /**
         * the current value of key generator, to become the next inserted key into the object store. Valid if objectStore.autoIncrement is true.
         * @param keyGeneratorValue field value
         * @return this model
         */
        public GetMetadataResult keyGeneratorValue(double keyGeneratorValue) {
            set("keyGeneratorValue", keyGeneratorValue);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Clears all entries from an object store.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearObjectStore(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket, String databaseName, String objectStoreName) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            return client.call("IndexedDB.clearObjectStore", params, result_ -> null);
        }
        /**
         * Clears all entries from an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearObjectStore(String databaseName, String objectStoreName) {
            return clearObjectStore(Optional.empty(), Optional.empty(), Optional.empty(), databaseName, objectStoreName);
        }
        /**
         * Deletes a database.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket, String databaseName) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            params.put("databaseName", CdpObject.json(databaseName));
            return client.call("IndexedDB.deleteDatabase", params, result_ -> null);
        }
        /**
         * Deletes a database.
         * @param databaseName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(String databaseName) {
            return deleteDatabase(Optional.empty(), Optional.empty(), Optional.empty(), databaseName);
        }
        /**
         * Delete a range of entries from an object store
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param keyRange protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteObjectStoreEntries(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket, String databaseName, String objectStoreName, IndexedDB.KeyRange keyRange) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            params.put("keyRange", CdpObject.json(keyRange));
            return client.call("IndexedDB.deleteObjectStoreEntries", params, result_ -> null);
        }
        /**
         * Delete a range of entries from an object store
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param keyRange protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteObjectStoreEntries(String databaseName, String objectStoreName, IndexedDB.KeyRange keyRange) {
            return deleteObjectStoreEntries(Optional.empty(), Optional.empty(), Optional.empty(), databaseName, objectStoreName, keyRange);
        }
        /**
         * Disables events from backend.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("IndexedDB.disable", null, result_ -> null);
        }
        /**
         * Enables events from backend.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("IndexedDB.enable", null, result_ -> null);
        }
        /**
         * Requests data from object store or index.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param indexName protocol value
         * @param skipCount protocol value
         * @param pageSize protocol value
         * @param keyRange protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDataResult> requestData(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket, String databaseName, String objectStoreName, Optional<String> indexName, long skipCount, long pageSize, Optional<IndexedDB.KeyRange> keyRange) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            indexName.ifPresent(value_ -> params.put("indexName", CdpObject.json(value_)));
            params.put("skipCount", CdpObject.json(skipCount));
            params.put("pageSize", CdpObject.json(pageSize));
            keyRange.ifPresent(value_ -> params.put("keyRange", CdpObject.json(value_)));
            return client.call("IndexedDB.requestData", params, result_ -> new RequestDataResult(result_));
        }
        /**
         * Requests data from object store or index.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param skipCount protocol value
         * @param pageSize protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDataResult> requestData(String databaseName, String objectStoreName, long skipCount, long pageSize) {
            return requestData(Optional.empty(), Optional.empty(), Optional.empty(), databaseName, objectStoreName, Optional.empty(), skipCount, pageSize, Optional.empty());
        }
        /**
         * Gets metadata of an object store.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetadataResult> getMetadata(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket, String databaseName, String objectStoreName) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            return client.call("IndexedDB.getMetadata", params, result_ -> new GetMetadataResult(result_));
        }
        /**
         * Gets metadata of an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetadataResult> getMetadata(String databaseName, String objectStoreName) {
            return getMetadata(Optional.empty(), Optional.empty(), Optional.empty(), databaseName, objectStoreName);
        }
        /**
         * Requests database with given name in given frame.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket, String databaseName) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            params.put("databaseName", CdpObject.json(databaseName));
            return client.call("IndexedDB.requestDatabase", params, result_ -> java.util.Objects.requireNonNull(IndexedDB.DatabaseWithObjectStores.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("databaseWithObjectStores")))))));
        }
        /**
         * Requests database with given name in given frame.
         * @param databaseName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(String databaseName) {
            return requestDatabase(Optional.empty(), Optional.empty(), Optional.empty(), databaseName);
        }
        /**
         * Requests database names for given security origin.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames(Optional<String> securityOrigin, Optional<String> storageKey, Optional<Storage.StorageBucket> storageBucket) {
            Map<String, Object> params = new LinkedHashMap<>();
            securityOrigin.ifPresent(value_ -> params.put("securityOrigin", CdpObject.json(value_)));
            storageKey.ifPresent(value_ -> params.put("storageKey", CdpObject.json(value_)));
            storageBucket.ifPresent(value_ -> params.put("storageBucket", CdpObject.json(value_)));
            return client.call("IndexedDB.requestDatabaseNames", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("databaseNames")), element0 -> (String) element0));
        }
        /**
         * Requests database names for given security origin.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames() {
            return requestDatabaseNames(Optional.empty(), Optional.empty(), Optional.empty());
        }
    }
}
