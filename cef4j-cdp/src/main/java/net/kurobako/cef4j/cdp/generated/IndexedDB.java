// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol IndexedDB domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/IndexedDB.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
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
            return CdpObject.requireList(require("objectStores"), element0 -> java.util.Objects.requireNonNull(IndexedDB.ObjectStore.fromMap(CdpObject.requireObjectMap(element0))));
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
            return java.util.Objects.requireNonNull(IndexedDB.KeyPath.fromMap(CdpObject.requireObjectMap(require("keyPath"))));
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
            return CdpObject.requireList(require("indexes"), element0 -> java.util.Objects.requireNonNull(IndexedDB.ObjectStoreIndex.fromMap(CdpObject.requireObjectMap(element0))));
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
            return java.util.Objects.requireNonNull(IndexedDB.KeyPath.fromMap(CdpObject.requireObjectMap(require("keyPath"))));
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
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
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
            return raw("number").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * String value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> string() {
            return raw("string").map(value -> (String) value);
        }
        /**
         * Date value.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble date() {
            return raw("date").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Array value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<IndexedDB.Key>> array() {
            return raw("array").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(IndexedDB.Key.fromMap(CdpObject.requireObjectMap(element0)))));
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
         * @param number field value
         * @return this model
         */
        public Key number(double number) {
            set("number", number);
            return this;
        }
        /**
         * String value.
         * @param string field value
         * @return this model
         */
        public Key string(String string) {
            set("string", string);
            return this;
        }
        /**
         * Date value.
         * @param date field value
         * @return this model
         */
        public Key date(double date) {
            set("date", date);
            return this;
        }
        /**
         * Array value.
         * @param array field value
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
            return raw("lower").flatMap(CdpObject::objectMap).map(IndexedDB.Key::fromMap);
        }
        /**
         * Upper bound.
         * @return the protocol field value, empty when absent
         */
        public Optional<IndexedDB.Key> upper() {
            return raw("upper").flatMap(CdpObject::objectMap).map(IndexedDB.Key::fromMap);
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
         * @param lower field value
         * @return this model
         */
        public KeyRange lower(IndexedDB.Key lower) {
            set("lower", lower);
            return this;
        }
        /**
         * Upper bound.
         * @param upper field value
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("key"))));
        }
        /**
         * Primary key object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject primaryKey() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("primaryKey"))));
        }
        /**
         * Value object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject value() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("value"))));
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
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
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
            return raw("string").map(value -> (String) value);
        }
        /**
         * Array value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> array() {
            return raw("array").flatMap(value -> list(value, element0 -> (String) element0));
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
         * @param string field value
         * @return this model
         */
        public KeyPath string(String string) {
            set("string", string);
            return this;
        }
        /**
         * Array value.
         * @param array field value
         * @return this model
         */
        public KeyPath array(java.util.List<String> array) {
            set("array", array);
            return this;
        }
    }
    /**
     * Clears all entries from an object store.
     */
    public static final class ClearObjectStoreRequest extends CdpObject {
        public ClearObjectStoreRequest() {}
        /**
         * Clears all entries from an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         */
        public ClearObjectStoreRequest(String databaseName, String objectStoreName) {
            databaseName(databaseName).objectStoreName(objectStoreName);
        }
        public static ClearObjectStoreRequest fromMap(Map<String, Object> values) {
            final ClearObjectStoreRequest instance_ = new ClearObjectStoreRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return (String) require("objectStoreName");
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public ClearObjectStoreRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public ClearObjectStoreRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public ClearObjectStoreRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * Database name.
         * @param databaseName field value
         * @return this model
         */
        public ClearObjectStoreRequest databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
        /**
         * Object store name.
         * @param objectStoreName field value
         * @return this model
         */
        public ClearObjectStoreRequest objectStoreName(String objectStoreName) {
            set("objectStoreName", objectStoreName);
            return this;
        }
    }
    /**
     * Deletes a database.
     */
    public static final class DeleteDatabaseRequest extends CdpObject {
        public DeleteDatabaseRequest() {}
        /**
         * Deletes a database.
         * @param databaseName protocol value
         */
        public DeleteDatabaseRequest(String databaseName) {
            databaseName(databaseName);
        }
        public static DeleteDatabaseRequest fromMap(Map<String, Object> values) {
            final DeleteDatabaseRequest instance_ = new DeleteDatabaseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public DeleteDatabaseRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public DeleteDatabaseRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public DeleteDatabaseRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * Database name.
         * @param databaseName field value
         * @return this model
         */
        public DeleteDatabaseRequest databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
    }
    /**
     * Delete a range of entries from an object store
     */
    public static final class DeleteObjectStoreEntriesRequest extends CdpObject {
        public DeleteObjectStoreEntriesRequest() {}
        /**
         * Delete a range of entries from an object store
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param keyRange protocol value
         */
        public DeleteObjectStoreEntriesRequest(String databaseName, String objectStoreName, IndexedDB.KeyRange keyRange) {
            databaseName(databaseName).objectStoreName(objectStoreName).keyRange(keyRange);
        }
        public static DeleteObjectStoreEntriesRequest fromMap(Map<String, Object> values) {
            final DeleteObjectStoreEntriesRequest instance_ = new DeleteObjectStoreEntriesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * Returns the databaseName field.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * Returns the objectStoreName field.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return (String) require("objectStoreName");
        }
        /**
         * Range of entry keys to delete
         * @return the protocol field value
         */
        public IndexedDB.KeyRange keyRange() {
            return java.util.Objects.requireNonNull(IndexedDB.KeyRange.fromMap(CdpObject.requireObjectMap(require("keyRange"))));
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public DeleteObjectStoreEntriesRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public DeleteObjectStoreEntriesRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public DeleteObjectStoreEntriesRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * Sets the databaseName field.
         * @param databaseName field value
         * @return this model
         */
        public DeleteObjectStoreEntriesRequest databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
        /**
         * Sets the objectStoreName field.
         * @param objectStoreName field value
         * @return this model
         */
        public DeleteObjectStoreEntriesRequest objectStoreName(String objectStoreName) {
            set("objectStoreName", objectStoreName);
            return this;
        }
        /**
         * Range of entry keys to delete
         * @param keyRange field value
         * @return this model
         */
        public DeleteObjectStoreEntriesRequest keyRange(IndexedDB.KeyRange keyRange) {
            set("keyRange", keyRange);
            return this;
        }
    }
    /**
     * Requests data from object store or index.
     */
    public static final class RequestDataRequest extends CdpObject {
        public RequestDataRequest() {}
        /**
         * Requests data from object store or index.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param skipCount protocol value
         * @param pageSize protocol value
         */
        public RequestDataRequest(String databaseName, String objectStoreName, long skipCount, long pageSize) {
            databaseName(databaseName).objectStoreName(objectStoreName).skipCount(skipCount).pageSize(pageSize);
        }
        public static RequestDataRequest fromMap(Map<String, Object> values) {
            final RequestDataRequest instance_ = new RequestDataRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return (String) require("objectStoreName");
        }
        /**
         * Index name. If not specified, it performs an object store data request.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> indexName() {
            return raw("indexName").map(value -> (String) value);
        }
        /**
         * Number of records to skip.
         * @return the protocol field value
         */
        public long skipCount() {
            return ((Number) require("skipCount")).longValue();
        }
        /**
         * Number of records to fetch.
         * @return the protocol field value
         */
        public long pageSize() {
            return ((Number) require("pageSize")).longValue();
        }
        /**
         * Key range.
         * @return the protocol field value, empty when absent
         */
        public Optional<IndexedDB.KeyRange> keyRange() {
            return raw("keyRange").flatMap(CdpObject::objectMap).map(IndexedDB.KeyRange::fromMap);
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public RequestDataRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public RequestDataRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public RequestDataRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * Database name.
         * @param databaseName field value
         * @return this model
         */
        public RequestDataRequest databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
        /**
         * Object store name.
         * @param objectStoreName field value
         * @return this model
         */
        public RequestDataRequest objectStoreName(String objectStoreName) {
            set("objectStoreName", objectStoreName);
            return this;
        }
        /**
         * Index name. If not specified, it performs an object store data request.
         * @param indexName field value
         * @return this model
         */
        public RequestDataRequest indexName(String indexName) {
            set("indexName", indexName);
            return this;
        }
        /**
         * Number of records to skip.
         * @param skipCount field value
         * @return this model
         */
        public RequestDataRequest skipCount(long skipCount) {
            set("skipCount", skipCount);
            return this;
        }
        /**
         * Number of records to fetch.
         * @param pageSize field value
         * @return this model
         */
        public RequestDataRequest pageSize(long pageSize) {
            set("pageSize", pageSize);
            return this;
        }
        /**
         * Key range.
         * @param keyRange field value
         * @return this model
         */
        public RequestDataRequest keyRange(IndexedDB.KeyRange keyRange) {
            set("keyRange", keyRange);
            return this;
        }
    }
    /**
     * Gets metadata of an object store.
     */
    public static final class GetMetadataRequest extends CdpObject {
        public GetMetadataRequest() {}
        /**
         * Gets metadata of an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         */
        public GetMetadataRequest(String databaseName, String objectStoreName) {
            databaseName(databaseName).objectStoreName(objectStoreName);
        }
        public static GetMetadataRequest fromMap(Map<String, Object> values) {
            final GetMetadataRequest instance_ = new GetMetadataRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return (String) require("objectStoreName");
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public GetMetadataRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public GetMetadataRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public GetMetadataRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * Database name.
         * @param databaseName field value
         * @return this model
         */
        public GetMetadataRequest databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
        /**
         * Object store name.
         * @param objectStoreName field value
         * @return this model
         */
        public GetMetadataRequest objectStoreName(String objectStoreName) {
            set("objectStoreName", objectStoreName);
            return this;
        }
    }
    /**
     * Requests database with given name in given frame.
     */
    public static final class RequestDatabaseRequest extends CdpObject {
        public RequestDatabaseRequest() {}
        /**
         * Requests database with given name in given frame.
         * @param databaseName protocol value
         */
        public RequestDatabaseRequest(String databaseName) {
            databaseName(databaseName);
        }
        public static RequestDatabaseRequest fromMap(Map<String, Object> values) {
            final RequestDatabaseRequest instance_ = new RequestDatabaseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return (String) require("databaseName");
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public RequestDatabaseRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public RequestDatabaseRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public RequestDatabaseRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
            return this;
        }
        /**
         * Database name.
         * @param databaseName field value
         * @return this model
         */
        public RequestDatabaseRequest databaseName(String databaseName) {
            set("databaseName", databaseName);
            return this;
        }
    }
    /**
     * Requests database names for given security origin.
     */
    public static final class RequestDatabaseNamesRequest extends CdpObject {
        public RequestDatabaseNamesRequest() {}
        public static RequestDatabaseNamesRequest fromMap(Map<String, Object> values) {
            final RequestDatabaseNamesRequest instance_ = new RequestDatabaseNamesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return raw("securityOrigin").map(value -> (String) value);
        }
        /**
         * Storage key.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> storageKey() {
            return raw("storageKey").map(value -> (String) value);
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @return the protocol field value, empty when absent
         */
        public Optional<Storage.StorageBucket> storageBucket() {
            return raw("storageBucket").flatMap(CdpObject::objectMap).map(Storage.StorageBucket::fromMap);
        }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public RequestDatabaseNamesRequest securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Storage key.
         * @param storageKey field value
         * @return this model
         */
        public RequestDatabaseNamesRequest storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Storage bucket. If not specified, it uses the default bucket.
         * @param storageBucket field value
         * @return this model
         */
        public RequestDatabaseNamesRequest storageBucket(Storage.StorageBucket storageBucket) {
            set("storageBucket", storageBucket);
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
            return CdpObject.requireList(require("objectStoreDataEntries"), element0 -> java.util.Objects.requireNonNull(IndexedDB.DataEntry.fromMap(CdpObject.requireObjectMap(element0))));
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
        public CompletionStage<Void> clearObjectStore(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket, String databaseName, String objectStoreName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            return client.call("IndexedDB.clearObjectStore", params);
        }
        /**
         * Clears all entries from an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearObjectStore(String databaseName, String objectStoreName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            return client.call("IndexedDB.clearObjectStore", params);
        }
        /**
         * Clears all entries from an object store.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearObjectStore(ClearObjectStoreRequest request) {
            return client.call("IndexedDB.clearObjectStore", request == null ? null : request.toMap());
        }
        /**
         * Deletes a database.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket, String databaseName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
            params.put("databaseName", CdpObject.json(databaseName));
            return client.call("IndexedDB.deleteDatabase", params);
        }
        /**
         * Deletes a database.
         * @param databaseName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(String databaseName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("databaseName", CdpObject.json(databaseName));
            return client.call("IndexedDB.deleteDatabase", params);
        }
        /**
         * Deletes a database.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(DeleteDatabaseRequest request) {
            return client.call("IndexedDB.deleteDatabase", request == null ? null : request.toMap());
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
        public CompletionStage<Void> deleteObjectStoreEntries(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket, String databaseName, String objectStoreName, IndexedDB.KeyRange keyRange) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            params.put("keyRange", CdpObject.json(keyRange));
            return client.call("IndexedDB.deleteObjectStoreEntries", params);
        }
        /**
         * Delete a range of entries from an object store
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param keyRange protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteObjectStoreEntries(String databaseName, String objectStoreName, IndexedDB.KeyRange keyRange) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            params.put("keyRange", CdpObject.json(keyRange));
            return client.call("IndexedDB.deleteObjectStoreEntries", params);
        }
        /**
         * Delete a range of entries from an object store
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteObjectStoreEntries(DeleteObjectStoreEntriesRequest request) {
            return client.call("IndexedDB.deleteObjectStoreEntries", request == null ? null : request.toMap());
        }
        /**
         * Disables events from backend.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("IndexedDB.disable", null);
        }
        /**
         * Enables events from backend.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("IndexedDB.enable", null);
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
        public CompletionStage<RequestDataResult> requestData(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket, String databaseName, String objectStoreName, @Nullable String indexName, long skipCount, long pageSize, IndexedDB.@Nullable KeyRange keyRange) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            if (indexName != null) params.put("indexName", CdpObject.json(indexName));
            params.put("skipCount", CdpObject.json(skipCount));
            params.put("pageSize", CdpObject.json(pageSize));
            if (keyRange != null) params.put("keyRange", CdpObject.json(keyRange));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            params.put("skipCount", CdpObject.json(skipCount));
            params.put("pageSize", CdpObject.json(pageSize));
            return client.call("IndexedDB.requestData", params, result_ -> new RequestDataResult(result_));
        }
        /**
         * Requests data from object store or index.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDataResult> requestData(RequestDataRequest request) {
            return client.call("IndexedDB.requestData", request == null ? null : request.toMap(), result_ -> new RequestDataResult(result_));
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
        public CompletionStage<GetMetadataResult> getMetadata(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket, String databaseName, String objectStoreName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("databaseName", CdpObject.json(databaseName));
            params.put("objectStoreName", CdpObject.json(objectStoreName));
            return client.call("IndexedDB.getMetadata", params, result_ -> new GetMetadataResult(result_));
        }
        /**
         * Gets metadata of an object store.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetadataResult> getMetadata(GetMetadataRequest request) {
            return client.call("IndexedDB.getMetadata", request == null ? null : request.toMap(), result_ -> new GetMetadataResult(result_));
        }
        /**
         * Requests database with given name in given frame.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @param databaseName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket, String databaseName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
            params.put("databaseName", CdpObject.json(databaseName));
            return client.call("IndexedDB.requestDatabase", params, result_ -> java.util.Objects.requireNonNull(IndexedDB.DatabaseWithObjectStores.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("databaseWithObjectStores"))))));
        }
        /**
         * Requests database with given name in given frame.
         * @param databaseName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(String databaseName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("databaseName", CdpObject.json(databaseName));
            return client.call("IndexedDB.requestDatabase", params, result_ -> java.util.Objects.requireNonNull(IndexedDB.DatabaseWithObjectStores.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("databaseWithObjectStores"))))));
        }
        /**
         * Requests database with given name in given frame.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(RequestDatabaseRequest request) {
            return client.call("IndexedDB.requestDatabase", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(IndexedDB.DatabaseWithObjectStores.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("databaseWithObjectStores"))))));
        }
        /**
         * Requests database names for given security origin.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (securityOrigin != null) params.put("securityOrigin", CdpObject.json(securityOrigin));
            if (storageKey != null) params.put("storageKey", CdpObject.json(storageKey));
            if (storageBucket != null) params.put("storageBucket", CdpObject.json(storageBucket));
            return client.call("IndexedDB.requestDatabaseNames", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("databaseNames")), element0 -> (String) element0));
        }
        /**
         * Requests database names for given security origin.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames() {
            return client.call("IndexedDB.requestDatabaseNames", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("databaseNames")), element0 -> (String) element0));
        }
        /**
         * Requests database names for given security origin.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames(RequestDatabaseNamesRequest request) {
            return client.call("IndexedDB.requestDatabaseNames", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("databaseNames")), element0 -> (String) element0));
        }
    }
}
