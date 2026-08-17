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
 * Chrome DevTools Protocol IndexedDB domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/IndexedDB.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class IndexedDB {
    private IndexedDB() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Database with an array of object stores.
     */
    public static final class DatabaseWithObjectStores extends CdpObject {
        private DatabaseWithObjectStores(Map<String, Object> values) { super(values); }
        @Nullable public static DatabaseWithObjectStores fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DatabaseWithObjectStores(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Database name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Database version (type is not &#x27;integer&#x27;, as the standard requires the version number to be &#x27;unsigned long long&#x27;)
         * @return the protocol field value
         */
        @Nullable public Double version() {
            return numberAsDouble(value("version"));
        }
        /**
         * Object stores in this database.
         * @return the protocol field value
         */
        @Nullable public java.util.List<IndexedDB.ObjectStore> objectStores() {
            return list(value("objectStores"), element0 -> IndexedDB.ObjectStore.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Database name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Database version (type is not &#x27;integer&#x27;, as the standard requires the version number to be &#x27;unsigned long long&#x27;)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder version(@Nullable Double value) {
                if (value == null) values.remove("version");
                else values.put("version", jsonValue(value));
                return this;
            }
            /**
             * Object stores in this database.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStores(@Nullable java.util.List<IndexedDB.ObjectStore> value) {
                if (value == null) values.remove("objectStores");
                else values.put("objectStores", jsonValue(value));
                return this;
            }
            public DatabaseWithObjectStores build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("version")) throw new IllegalStateException("Missing required CDP field: version");
                if (!values.containsKey("objectStores")) throw new IllegalStateException("Missing required CDP field: objectStores");
                return new DatabaseWithObjectStores(values);
            }
        }
    }
    /**
     * Object store.
     */
    public static final class ObjectStore extends CdpObject {
        private ObjectStore(Map<String, Object> values) { super(values); }
        @Nullable public static ObjectStore fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ObjectStore(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object store name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Object store key path.
         * @return the protocol field value
         */
        @Nullable public IndexedDB.KeyPath keyPath() {
            return IndexedDB.KeyPath.fromMap(objectMap(value("keyPath")));
        }
        /**
         * If true, object store has auto increment flag set.
         * @return the protocol field value
         */
        @Nullable public Boolean autoIncrement() {
            return (Boolean) value("autoIncrement");
        }
        /**
         * Indexes in this object store.
         * @return the protocol field value
         */
        @Nullable public java.util.List<IndexedDB.ObjectStoreIndex> indexes() {
            return list(value("indexes"), element0 -> IndexedDB.ObjectStoreIndex.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object store name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Object store key path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyPath(@Nullable IndexedDB.KeyPath value) {
                if (value == null) values.remove("keyPath");
                else values.put("keyPath", jsonValue(value));
                return this;
            }
            /**
             * If true, object store has auto increment flag set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder autoIncrement(@Nullable Boolean value) {
                if (value == null) values.remove("autoIncrement");
                else values.put("autoIncrement", jsonValue(value));
                return this;
            }
            /**
             * Indexes in this object store.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder indexes(@Nullable java.util.List<IndexedDB.ObjectStoreIndex> value) {
                if (value == null) values.remove("indexes");
                else values.put("indexes", jsonValue(value));
                return this;
            }
            public ObjectStore build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("keyPath")) throw new IllegalStateException("Missing required CDP field: keyPath");
                if (!values.containsKey("autoIncrement")) throw new IllegalStateException("Missing required CDP field: autoIncrement");
                if (!values.containsKey("indexes")) throw new IllegalStateException("Missing required CDP field: indexes");
                return new ObjectStore(values);
            }
        }
    }
    /**
     * Object store index.
     */
    public static final class ObjectStoreIndex extends CdpObject {
        private ObjectStoreIndex(Map<String, Object> values) { super(values); }
        @Nullable public static ObjectStoreIndex fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ObjectStoreIndex(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Index name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Index key path.
         * @return the protocol field value
         */
        @Nullable public IndexedDB.KeyPath keyPath() {
            return IndexedDB.KeyPath.fromMap(objectMap(value("keyPath")));
        }
        /**
         * If true, index is unique.
         * @return the protocol field value
         */
        @Nullable public Boolean unique() {
            return (Boolean) value("unique");
        }
        /**
         * If true, index allows multiple entries for a key.
         * @return the protocol field value
         */
        @Nullable public Boolean multiEntry() {
            return (Boolean) value("multiEntry");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Index name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Index key path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyPath(@Nullable IndexedDB.KeyPath value) {
                if (value == null) values.remove("keyPath");
                else values.put("keyPath", jsonValue(value));
                return this;
            }
            /**
             * If true, index is unique.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unique(@Nullable Boolean value) {
                if (value == null) values.remove("unique");
                else values.put("unique", jsonValue(value));
                return this;
            }
            /**
             * If true, index allows multiple entries for a key.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder multiEntry(@Nullable Boolean value) {
                if (value == null) values.remove("multiEntry");
                else values.put("multiEntry", jsonValue(value));
                return this;
            }
            public ObjectStoreIndex build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("keyPath")) throw new IllegalStateException("Missing required CDP field: keyPath");
                if (!values.containsKey("unique")) throw new IllegalStateException("Missing required CDP field: unique");
                if (!values.containsKey("multiEntry")) throw new IllegalStateException("Missing required CDP field: multiEntry");
                return new ObjectStoreIndex(values);
            }
        }
    }
    /**
     * Key.
     */
    public static final class Key extends CdpObject {
        private Key(Map<String, Object> values) { super(values); }
        @Nullable public static Key fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Key(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Key type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Key type.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String NUMBER = "number";
            public static final String STRING = "string";
            public static final String DATE = "date";
            public static final String ARRAY = "array";
        }
        /**
         * Number value.
         * @return the protocol field value
         */
        @Nullable public Double number() {
            return numberAsDouble(value("number"));
        }
        /**
         * String value.
         * @return the protocol field value
         */
        @Nullable public String string() {
            return (String) value("string");
        }
        /**
         * Date value.
         * @return the protocol field value
         */
        @Nullable public Double date() {
            return numberAsDouble(value("date"));
        }
        /**
         * Array value.
         * @return the protocol field value
         */
        @Nullable public java.util.List<IndexedDB.Key> array() {
            return list(value("array"), element0 -> IndexedDB.Key.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Key type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Number value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder number(@Nullable Double value) {
                if (value == null) values.remove("number");
                else values.put("number", jsonValue(value));
                return this;
            }
            /**
             * String value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder string(@Nullable String value) {
                if (value == null) values.remove("string");
                else values.put("string", jsonValue(value));
                return this;
            }
            /**
             * Date value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder date(@Nullable Double value) {
                if (value == null) values.remove("date");
                else values.put("date", jsonValue(value));
                return this;
            }
            /**
             * Array value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder array(@Nullable java.util.List<IndexedDB.Key> value) {
                if (value == null) values.remove("array");
                else values.put("array", jsonValue(value));
                return this;
            }
            public Key build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new Key(values);
            }
        }
    }
    /**
     * Key range.
     */
    public static final class KeyRange extends CdpObject {
        private KeyRange(Map<String, Object> values) { super(values); }
        @Nullable public static KeyRange fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new KeyRange(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Lower bound.
         * @return the protocol field value
         */
        @Nullable public IndexedDB.Key lower() {
            return IndexedDB.Key.fromMap(objectMap(value("lower")));
        }
        /**
         * Upper bound.
         * @return the protocol field value
         */
        @Nullable public IndexedDB.Key upper() {
            return IndexedDB.Key.fromMap(objectMap(value("upper")));
        }
        /**
         * If true lower bound is open.
         * @return the protocol field value
         */
        @Nullable public Boolean lowerOpen() {
            return (Boolean) value("lowerOpen");
        }
        /**
         * If true upper bound is open.
         * @return the protocol field value
         */
        @Nullable public Boolean upperOpen() {
            return (Boolean) value("upperOpen");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Lower bound.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lower(@Nullable IndexedDB.Key value) {
                if (value == null) values.remove("lower");
                else values.put("lower", jsonValue(value));
                return this;
            }
            /**
             * Upper bound.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder upper(@Nullable IndexedDB.Key value) {
                if (value == null) values.remove("upper");
                else values.put("upper", jsonValue(value));
                return this;
            }
            /**
             * If true lower bound is open.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lowerOpen(@Nullable Boolean value) {
                if (value == null) values.remove("lowerOpen");
                else values.put("lowerOpen", jsonValue(value));
                return this;
            }
            /**
             * If true upper bound is open.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder upperOpen(@Nullable Boolean value) {
                if (value == null) values.remove("upperOpen");
                else values.put("upperOpen", jsonValue(value));
                return this;
            }
            public KeyRange build() {
                if (!values.containsKey("lowerOpen")) throw new IllegalStateException("Missing required CDP field: lowerOpen");
                if (!values.containsKey("upperOpen")) throw new IllegalStateException("Missing required CDP field: upperOpen");
                return new KeyRange(values);
            }
        }
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
         * Key object.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject key() {
            return Runtime.RemoteObject.fromMap(objectMap(value("key")));
        }
        /**
         * Primary key object.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject primaryKey() {
            return Runtime.RemoteObject.fromMap(objectMap(value("primaryKey")));
        }
        /**
         * Value object.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject value() {
            return Runtime.RemoteObject.fromMap(objectMap(value("value")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Key object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Primary key object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder primaryKey(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("primaryKey");
                else values.put("primaryKey", jsonValue(value));
                return this;
            }
            /**
             * Value object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public DataEntry build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("primaryKey")) throw new IllegalStateException("Missing required CDP field: primaryKey");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new DataEntry(values);
            }
        }
    }
    /**
     * Key path.
     */
    public static final class KeyPath extends CdpObject {
        private KeyPath(Map<String, Object> values) { super(values); }
        @Nullable public static KeyPath fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new KeyPath(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Key path type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Key path type.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String NULL = "null";
            public static final String STRING = "string";
            public static final String ARRAY = "array";
        }
        /**
         * String value.
         * @return the protocol field value
         */
        @Nullable public String string() {
            return (String) value("string");
        }
        /**
         * Array value.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> array() {
            return list(value("array"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Key path type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * String value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder string(@Nullable String value) {
                if (value == null) values.remove("string");
                else values.put("string", jsonValue(value));
                return this;
            }
            /**
             * Array value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder array(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("array");
                else values.put("array", jsonValue(value));
                return this;
            }
            public KeyPath build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new KeyPath(values);
            }
        }
    }
    /**
     * Clears all entries from an object store.
     */
    public static final class ClearObjectStoreParams extends CdpObject {
        private ClearObjectStoreParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearObjectStoreParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearObjectStoreParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
        /**
         * Database name.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        @Nullable public String objectStoreName() {
            return (String) value("objectStoreName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            /**
             * Database name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            /**
             * Object store name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStoreName(@Nullable String value) {
                if (value == null) values.remove("objectStoreName");
                else values.put("objectStoreName", jsonValue(value));
                return this;
            }
            public ClearObjectStoreParams build() {
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                if (!values.containsKey("objectStoreName")) throw new IllegalStateException("Missing required CDP field: objectStoreName");
                return new ClearObjectStoreParams(values);
            }
        }
    }
    /**
     * Clears all entries from an object store.
     */
    public static final class ClearObjectStoreResult extends CdpObject {
        private ClearObjectStoreResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearObjectStoreResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearObjectStoreResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearObjectStoreResult build() {
                return new ClearObjectStoreResult(values);
            }
        }
    }
    /**
     * Deletes a database.
     */
    public static final class DeleteDatabaseParams extends CdpObject {
        private DeleteDatabaseParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteDatabaseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteDatabaseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
        /**
         * Database name.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            /**
             * Database name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            public DeleteDatabaseParams build() {
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                return new DeleteDatabaseParams(values);
            }
        }
    }
    /**
     * Deletes a database.
     */
    public static final class DeleteDatabaseResult extends CdpObject {
        private DeleteDatabaseResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteDatabaseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteDatabaseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteDatabaseResult build() {
                return new DeleteDatabaseResult(values);
            }
        }
    }
    /**
     * Delete a range of entries from an object store
     */
    public static final class DeleteObjectStoreEntriesParams extends CdpObject {
        private DeleteObjectStoreEntriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteObjectStoreEntriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteObjectStoreEntriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
        /**
         * Returns the databaseName field.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        /**
         * Returns the objectStoreName field.
         * @return the protocol field value
         */
        @Nullable public String objectStoreName() {
            return (String) value("objectStoreName");
        }
        /**
         * Range of entry keys to delete
         * @return the protocol field value
         */
        @Nullable public IndexedDB.KeyRange keyRange() {
            return IndexedDB.KeyRange.fromMap(objectMap(value("keyRange")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            /**
             * Sets the databaseName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            /**
             * Sets the objectStoreName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStoreName(@Nullable String value) {
                if (value == null) values.remove("objectStoreName");
                else values.put("objectStoreName", jsonValue(value));
                return this;
            }
            /**
             * Range of entry keys to delete
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyRange(@Nullable IndexedDB.KeyRange value) {
                if (value == null) values.remove("keyRange");
                else values.put("keyRange", jsonValue(value));
                return this;
            }
            public DeleteObjectStoreEntriesParams build() {
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                if (!values.containsKey("objectStoreName")) throw new IllegalStateException("Missing required CDP field: objectStoreName");
                if (!values.containsKey("keyRange")) throw new IllegalStateException("Missing required CDP field: keyRange");
                return new DeleteObjectStoreEntriesParams(values);
            }
        }
    }
    /**
     * Delete a range of entries from an object store
     */
    public static final class DeleteObjectStoreEntriesResult extends CdpObject {
        private DeleteObjectStoreEntriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteObjectStoreEntriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteObjectStoreEntriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteObjectStoreEntriesResult build() {
                return new DeleteObjectStoreEntriesResult(values);
            }
        }
    }
    /**
     * Disables events from backend.
     */
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Disables events from backend.
     */
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Enables events from backend.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables events from backend.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    /**
     * Requests data from object store or index.
     */
    public static final class RequestDataParams extends CdpObject {
        private RequestDataParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestDataParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestDataParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
        /**
         * Database name.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        @Nullable public String objectStoreName() {
            return (String) value("objectStoreName");
        }
        /**
         * Index name. If not specified, it performs an object store data request.
         * @return the protocol field value
         */
        @Nullable public String indexName() {
            return (String) value("indexName");
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
         * Key range.
         * @return the protocol field value
         */
        @Nullable public IndexedDB.KeyRange keyRange() {
            return IndexedDB.KeyRange.fromMap(objectMap(value("keyRange")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            /**
             * Database name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            /**
             * Object store name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStoreName(@Nullable String value) {
                if (value == null) values.remove("objectStoreName");
                else values.put("objectStoreName", jsonValue(value));
                return this;
            }
            /**
             * Index name. If not specified, it performs an object store data request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder indexName(@Nullable String value) {
                if (value == null) values.remove("indexName");
                else values.put("indexName", jsonValue(value));
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
             * Key range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyRange(@Nullable IndexedDB.KeyRange value) {
                if (value == null) values.remove("keyRange");
                else values.put("keyRange", jsonValue(value));
                return this;
            }
            public RequestDataParams build() {
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                if (!values.containsKey("objectStoreName")) throw new IllegalStateException("Missing required CDP field: objectStoreName");
                if (!values.containsKey("skipCount")) throw new IllegalStateException("Missing required CDP field: skipCount");
                if (!values.containsKey("pageSize")) throw new IllegalStateException("Missing required CDP field: pageSize");
                return new RequestDataParams(values);
            }
        }
    }
    /**
     * Requests data from object store or index.
     */
    public static final class RequestDataResult extends CdpObject {
        private RequestDataResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestDataResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestDataResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of object store data entries.
         * @return the protocol field value
         */
        @Nullable public java.util.List<IndexedDB.DataEntry> objectStoreDataEntries() {
            return list(value("objectStoreDataEntries"), element0 -> IndexedDB.DataEntry.fromMap(objectMap(element0)));
        }
        /**
         * If true, there are more entries to fetch in the given range.
         * @return the protocol field value
         */
        @Nullable public Boolean hasMore() {
            return (Boolean) value("hasMore");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of object store data entries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStoreDataEntries(@Nullable java.util.List<IndexedDB.DataEntry> value) {
                if (value == null) values.remove("objectStoreDataEntries");
                else values.put("objectStoreDataEntries", jsonValue(value));
                return this;
            }
            /**
             * If true, there are more entries to fetch in the given range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasMore(@Nullable Boolean value) {
                if (value == null) values.remove("hasMore");
                else values.put("hasMore", jsonValue(value));
                return this;
            }
            public RequestDataResult build() {
                if (!values.containsKey("objectStoreDataEntries")) throw new IllegalStateException("Missing required CDP field: objectStoreDataEntries");
                if (!values.containsKey("hasMore")) throw new IllegalStateException("Missing required CDP field: hasMore");
                return new RequestDataResult(values);
            }
        }
    }
    /**
     * Gets metadata of an object store.
     */
    public static final class GetMetadataParams extends CdpObject {
        private GetMetadataParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetMetadataParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMetadataParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
        /**
         * Database name.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        /**
         * Object store name.
         * @return the protocol field value
         */
        @Nullable public String objectStoreName() {
            return (String) value("objectStoreName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            /**
             * Database name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            /**
             * Object store name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectStoreName(@Nullable String value) {
                if (value == null) values.remove("objectStoreName");
                else values.put("objectStoreName", jsonValue(value));
                return this;
            }
            public GetMetadataParams build() {
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                if (!values.containsKey("objectStoreName")) throw new IllegalStateException("Missing required CDP field: objectStoreName");
                return new GetMetadataParams(values);
            }
        }
    }
    /**
     * Gets metadata of an object store.
     */
    public static final class GetMetadataResult extends CdpObject {
        private GetMetadataResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetMetadataResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMetadataResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * the entries count
         * @return the protocol field value
         */
        @Nullable public Double entriesCount() {
            return numberAsDouble(value("entriesCount"));
        }
        /**
         * the current value of key generator, to become the next inserted key into the object store. Valid if objectStore.autoIncrement is true.
         * @return the protocol field value
         */
        @Nullable public Double keyGeneratorValue() {
            return numberAsDouble(value("keyGeneratorValue"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * the entries count
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entriesCount(@Nullable Double value) {
                if (value == null) values.remove("entriesCount");
                else values.put("entriesCount", jsonValue(value));
                return this;
            }
            /**
             * the current value of key generator, to become the next inserted key into the object store. Valid if objectStore.autoIncrement is true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyGeneratorValue(@Nullable Double value) {
                if (value == null) values.remove("keyGeneratorValue");
                else values.put("keyGeneratorValue", jsonValue(value));
                return this;
            }
            public GetMetadataResult build() {
                if (!values.containsKey("entriesCount")) throw new IllegalStateException("Missing required CDP field: entriesCount");
                if (!values.containsKey("keyGeneratorValue")) throw new IllegalStateException("Missing required CDP field: keyGeneratorValue");
                return new GetMetadataResult(values);
            }
        }
    }
    /**
     * Requests database with given name in given frame.
     */
    public static final class RequestDatabaseParams extends CdpObject {
        private RequestDatabaseParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestDatabaseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestDatabaseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
        /**
         * Database name.
         * @return the protocol field value
         */
        @Nullable public String databaseName() {
            return (String) value("databaseName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            /**
             * Database name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseName(@Nullable String value) {
                if (value == null) values.remove("databaseName");
                else values.put("databaseName", jsonValue(value));
                return this;
            }
            public RequestDatabaseParams build() {
                if (!values.containsKey("databaseName")) throw new IllegalStateException("Missing required CDP field: databaseName");
                return new RequestDatabaseParams(values);
            }
        }
    }
    /**
     * Requests database with given name in given frame.
     */
    public static final class RequestDatabaseResult extends CdpObject {
        private RequestDatabaseResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestDatabaseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestDatabaseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Database with an array of object stores.
         * @return the protocol field value
         */
        @Nullable public IndexedDB.DatabaseWithObjectStores databaseWithObjectStores() {
            return IndexedDB.DatabaseWithObjectStores.fromMap(objectMap(value("databaseWithObjectStores")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Database with an array of object stores.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseWithObjectStores(@Nullable IndexedDB.DatabaseWithObjectStores value) {
                if (value == null) values.remove("databaseWithObjectStores");
                else values.put("databaseWithObjectStores", jsonValue(value));
                return this;
            }
            public RequestDatabaseResult build() {
                if (!values.containsKey("databaseWithObjectStores")) throw new IllegalStateException("Missing required CDP field: databaseWithObjectStores");
                return new RequestDatabaseResult(values);
            }
        }
    }
    /**
     * Requests database names for given security origin.
     */
    public static final class RequestDatabaseNamesParams extends CdpObject {
        private RequestDatabaseNamesParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestDatabaseNamesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestDatabaseNamesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
             * At least and at most one of securityOrigin, storageKey, or storageBucket must be specified. Security origin.
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
            public RequestDatabaseNamesParams build() {
                return new RequestDatabaseNamesParams(values);
            }
        }
    }
    /**
     * Requests database names for given security origin.
     */
    public static final class RequestDatabaseNamesResult extends CdpObject {
        private RequestDatabaseNamesResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestDatabaseNamesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestDatabaseNamesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Database names for origin.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> databaseNames() {
            return list(value("databaseNames"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Database names for origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder databaseNames(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("databaseNames");
                else values.put("databaseNames", jsonValue(value));
                return this;
            }
            public RequestDatabaseNamesResult build() {
                if (!values.containsKey("databaseNames")) throw new IllegalStateException("Missing required CDP field: databaseNames");
                return new RequestDatabaseNamesResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Clears all entries from an object store.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearObjectStoreResult> clearObjectStore(ClearObjectStoreParams params) {
            return client.call("IndexedDB.clearObjectStore", params, ClearObjectStoreResult::fromMap);
        }
        /**
         * Deletes a database.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteDatabaseResult> deleteDatabase(DeleteDatabaseParams params) {
            return client.call("IndexedDB.deleteDatabase", params, DeleteDatabaseResult::fromMap);
        }
        /**
         * Delete a range of entries from an object store
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteObjectStoreEntriesResult> deleteObjectStoreEntries(DeleteObjectStoreEntriesParams params) {
            return client.call("IndexedDB.deleteObjectStoreEntries", params, DeleteObjectStoreEntriesResult::fromMap);
        }
        /**
         * Disables events from backend.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("IndexedDB.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables events from backend.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("IndexedDB.enable", null, EnableResult::fromMap);
        }
        /**
         * Requests data from object store or index.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDataResult> requestData(RequestDataParams params) {
            return client.call("IndexedDB.requestData", params, RequestDataResult::fromMap);
        }
        /**
         * Gets metadata of an object store.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetadataResult> getMetadata(GetMetadataParams params) {
            return client.call("IndexedDB.getMetadata", params, GetMetadataResult::fromMap);
        }
        /**
         * Requests database with given name in given frame.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDatabaseResult> requestDatabase(RequestDatabaseParams params) {
            return client.call("IndexedDB.requestDatabase", params, RequestDatabaseResult::fromMap);
        }
        /**
         * Requests database names for given security origin.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestDatabaseNamesResult> requestDatabaseNames(RequestDatabaseNamesParams params) {
            return client.call("IndexedDB.requestDatabaseNames", params, RequestDatabaseNamesResult::fromMap);
        }
    }
}
