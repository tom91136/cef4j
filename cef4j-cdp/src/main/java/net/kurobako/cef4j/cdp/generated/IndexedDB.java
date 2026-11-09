// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
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
 * Chrome DevTools Protocol IndexedDB domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/IndexedDB.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class IndexedDB {
    private IndexedDB() {}
    private static final CdpField<java.util.List<IndexedDB.Key>> ARRAY = CdpField.of("array", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(IndexedDB.Key.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> ARRAY_2 = CdpField.of("array", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<String>> DATABASE_NAMES = CdpField.of("databaseNames", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<IndexedDB.DatabaseWithObjectStores> DATABASE_WITH_OBJECT_STORES = CdpField.of("databaseWithObjectStores", value -> java.util.Objects.requireNonNull(IndexedDB.DatabaseWithObjectStores.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<IndexedDB.ObjectStoreIndex>> INDEXES = CdpField.of("indexes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(IndexedDB.ObjectStoreIndex.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Runtime.RemoteObject> KEY = CdpField.of("key", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<IndexedDB.KeyPath> KEY_PATH = CdpField.of("keyPath", value -> java.util.Objects.requireNonNull(IndexedDB.KeyPath.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<IndexedDB.KeyRange> KEY_RANGE = CdpField.of("keyRange", value -> java.util.Objects.requireNonNull(IndexedDB.KeyRange.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<IndexedDB.Key> LOWER = CdpField.of("lower", value -> java.util.Objects.requireNonNull(IndexedDB.Key.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<IndexedDB.ObjectStore>> OBJECT_STORES = CdpField.of("objectStores", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(IndexedDB.ObjectStore.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<IndexedDB.DataEntry>> OBJECT_STORE_DATA_ENTRIES = CdpField.of("objectStoreDataEntries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(IndexedDB.DataEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Runtime.RemoteObject> PRIMARY_KEY = CdpField.of("primaryKey", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Storage.StorageBucket> STORAGE_BUCKET = CdpField.of("storageBucket", value -> java.util.Objects.requireNonNull(Storage.StorageBucket.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Key.TypeValues> TYPE = CdpField.of("type", value -> Key.TypeValues.of((String) value));
    private static final CdpField<KeyPath.TypeValues> TYPE_2 = CdpField.of("type", value -> KeyPath.TypeValues.of((String) value));
    private static final CdpField<IndexedDB.Key> UPPER = CdpField.of("upper", value -> java.util.Objects.requireNonNull(IndexedDB.Key.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Runtime.RemoteObject> VALUE = CdpField.of("value", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
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
            return requireString("name");
        }
        /**
         * Database version (type is not &#x27;integer&#x27;, as the standard requires the version number to be &#x27;unsigned long long&#x27;)
         * @return the protocol field value
         */
        public double version() {
            return requireDouble("version");
        }
        /**
         * Object stores in this database.
         * @return the protocol field value
         */
        public java.util.List<IndexedDB.ObjectStore> objectStores() {
            return require(OBJECT_STORES);
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
            return requireString("name");
        }
        /**
         * Object store key path.
         * @return the protocol field value
         */
        public IndexedDB.KeyPath keyPath() {
            return require(KEY_PATH);
        }
        /**
         * If true, object store has auto increment flag set.
         * @return the protocol field value
         */
        public boolean autoIncrement() {
            return requireBoolean("autoIncrement");
        }
        /**
         * Indexes in this object store.
         * @return the protocol field value
         */
        public java.util.List<IndexedDB.ObjectStoreIndex> indexes() {
            return require(INDEXES);
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
            return requireString("name");
        }
        /**
         * Index key path.
         * @return the protocol field value
         */
        public IndexedDB.KeyPath keyPath() {
            return require(KEY_PATH);
        }
        /**
         * If true, index is unique.
         * @return the protocol field value
         */
        public boolean unique() {
            return requireBoolean("unique");
        }
        /**
         * If true, index allows multiple entries for a key.
         * @return the protocol field value
         */
        public boolean multiEntry() {
            return requireBoolean("multiEntry");
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
            return require(TYPE);
        }
        /**
         * Number value.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble number() {
            return optionalDouble("number");
        }
        /**
         * String value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> string() {
            return optionalString("string");
        }
        /**
         * Date value.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble date() {
            return optionalDouble("date");
        }
        /**
         * Array value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<IndexedDB.Key>> array() {
            return optional(ARRAY);
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
            return optional(LOWER);
        }
        /**
         * Upper bound.
         * @return the protocol field value, empty when absent
         */
        public Optional<IndexedDB.Key> upper() {
            return optional(UPPER);
        }
        /**
         * If true lower bound is open.
         * @return the protocol field value
         */
        public boolean lowerOpen() {
            return requireBoolean("lowerOpen");
        }
        /**
         * If true upper bound is open.
         * @return the protocol field value
         */
        public boolean upperOpen() {
            return requireBoolean("upperOpen");
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
            return require(KEY);
        }
        /**
         * Primary key object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject primaryKey() {
            return require(PRIMARY_KEY);
        }
        /**
         * Value object.
         * @return the protocol field value
         */
        public Runtime.RemoteObject value() {
            return require(VALUE);
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
            return require(TYPE_2);
        }
        /**
         * String value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> string() {
            return optionalString("string");
        }
        /**
         * Array value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> array() {
            return optional(ARRAY_2);
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
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return requireString("objectStoreName");
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
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
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
         * Returns the databaseName field.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
        }
        /**
         * Returns the objectStoreName field.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return requireString("objectStoreName");
        }
        /**
         * Range of entry keys to delete
         * @return the protocol field value
         */
        public IndexedDB.KeyRange keyRange() {
            return require(KEY_RANGE);
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
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return requireString("objectStoreName");
        }
        /**
         * Index name. If not specified, it performs an object store data request.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> indexName() {
            return optionalString("indexName");
        }
        /**
         * Number of records to skip.
         * @return the protocol field value
         */
        public long skipCount() {
            return requireLong("skipCount");
        }
        /**
         * Number of records to fetch.
         * @return the protocol field value
         */
        public long pageSize() {
            return requireLong("pageSize");
        }
        /**
         * Key range.
         * @return the protocol field value, empty when absent
         */
        public Optional<IndexedDB.KeyRange> keyRange() {
            return optional(KEY_RANGE);
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
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        public String objectStoreName() {
            return requireString("objectStoreName");
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
         * Database name.
         * @return the protocol field value
         */
        public String databaseName() {
            return requireString("databaseName");
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
            return require(OBJECT_STORE_DATA_ENTRIES);
        }
        /**
         * If true, there are more entries to fetch in the given range.
         * @return the protocol field value
         */
        public boolean hasMore() {
            return requireBoolean("hasMore");
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
            return requireDouble("entriesCount");
        }
        /**
         * the current value of key generator, to become the next inserted key into the object store. Valid if objectStore.autoIncrement is true.
         * @return the protocol field value
         */
        public double keyGeneratorValue() {
            return requireDouble("keyGeneratorValue");
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
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
            return call("IndexedDB.clearObjectStore", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket, "databaseName", databaseName, "objectStoreName", objectStoreName));
        }
        /**
         * Clears all entries from an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearObjectStore(String databaseName, String objectStoreName) {
            return call("IndexedDB.clearObjectStore", params("databaseName", databaseName, "objectStoreName", objectStoreName));
        }
        /**
         * Clears all entries from an object store.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearObjectStore(ClearObjectStoreRequest request) {
            return call("IndexedDB.clearObjectStore", request);
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
            return call("IndexedDB.deleteDatabase", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket, "databaseName", databaseName));
        }
        /**
         * Deletes a database.
         * @param databaseName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(String databaseName) {
            return call("IndexedDB.deleteDatabase", params("databaseName", databaseName));
        }
        /**
         * Deletes a database.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDatabase(DeleteDatabaseRequest request) {
            return call("IndexedDB.deleteDatabase", request);
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
            return call("IndexedDB.deleteObjectStoreEntries", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket, "databaseName", databaseName, "objectStoreName", objectStoreName, "keyRange", keyRange));
        }
        /**
         * Delete a range of entries from an object store
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @param keyRange protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteObjectStoreEntries(String databaseName, String objectStoreName, IndexedDB.KeyRange keyRange) {
            return call("IndexedDB.deleteObjectStoreEntries", params("databaseName", databaseName, "objectStoreName", objectStoreName, "keyRange", keyRange));
        }
        /**
         * Delete a range of entries from an object store
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteObjectStoreEntries(DeleteObjectStoreEntriesRequest request) {
            return call("IndexedDB.deleteObjectStoreEntries", request);
        }
        /**
         * Disables events from backend.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("IndexedDB.disable");
        }
        /**
         * Enables events from backend.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("IndexedDB.enable");
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
            return call("IndexedDB.requestData", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket, "databaseName", databaseName, "objectStoreName", objectStoreName, "indexName", indexName, "skipCount", skipCount, "pageSize", pageSize, "keyRange", keyRange), RequestDataResult::fromMap);
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
            return call("IndexedDB.requestData", params("databaseName", databaseName, "objectStoreName", objectStoreName, "skipCount", skipCount, "pageSize", pageSize), RequestDataResult::fromMap);
        }
        /**
         * Requests data from object store or index.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDataResult> requestData(RequestDataRequest request) {
            return call("IndexedDB.requestData", request, RequestDataResult::fromMap);
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
            return call("IndexedDB.getMetadata", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket, "databaseName", databaseName, "objectStoreName", objectStoreName), GetMetadataResult::fromMap);
        }
        /**
         * Gets metadata of an object store.
         * @param databaseName protocol value
         * @param objectStoreName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetadataResult> getMetadata(String databaseName, String objectStoreName) {
            return call("IndexedDB.getMetadata", params("databaseName", databaseName, "objectStoreName", objectStoreName), GetMetadataResult::fromMap);
        }
        /**
         * Gets metadata of an object store.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetadataResult> getMetadata(GetMetadataRequest request) {
            return call("IndexedDB.getMetadata", request, GetMetadataResult::fromMap);
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
            return call("IndexedDB.requestDatabase", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket, "databaseName", databaseName), DATABASE_WITH_OBJECT_STORES::require);
        }
        /**
         * Requests database with given name in given frame.
         * @param databaseName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(String databaseName) {
            return call("IndexedDB.requestDatabase", params("databaseName", databaseName), DATABASE_WITH_OBJECT_STORES::require);
        }
        /**
         * Requests database with given name in given frame.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<IndexedDB.DatabaseWithObjectStores> requestDatabase(RequestDatabaseRequest request) {
            return call("IndexedDB.requestDatabase", request, DATABASE_WITH_OBJECT_STORES::require);
        }
        /**
         * Requests database names for given security origin.
         * @param securityOrigin protocol value
         * @param storageKey protocol value
         * @param storageBucket protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames(@Nullable String securityOrigin, @Nullable String storageKey, Storage.@Nullable StorageBucket storageBucket) {
            return call("IndexedDB.requestDatabaseNames", params("securityOrigin", securityOrigin, "storageKey", storageKey, "storageBucket", storageBucket), DATABASE_NAMES::require);
        }
        /**
         * Requests database names for given security origin.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames() {
            return call("IndexedDB.requestDatabaseNames", DATABASE_NAMES::require);
        }
        /**
         * Requests database names for given security origin.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> requestDatabaseNames(RequestDatabaseNamesRequest request) {
            return call("IndexedDB.requestDatabaseNames", request, DATABASE_NAMES::require);
        }
    }
}
