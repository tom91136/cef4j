// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
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
 * Query and modify DOM storage.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMStorage.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class DOMStorage {
    private DOMStorage() {}
    private static final CdpField<java.util.List<java.util.List<String>>> ENTRIES = CdpField.of("entries", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> (String) element1)));
    private static final CdpField<DOMStorage.StorageId> STORAGE_ID = CdpField.of("storageId", value -> java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMStorage.SerializedStorageKey> STORAGE_KEY = CdpField.of("storageKey", value -> new DOMStorage.SerializedStorageKey((String) value));
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
     * DOM Storage identifier.
     */
    public static final class StorageId extends CdpObject {
        public StorageId() {}
        private StorageId(Map<String, Object> values) { super(values); }
        public static StorageId fromMap(Map<String, Object> values) {
            return new StorageId(values);
        }
        /**
         * Security origin for the storage.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> securityOrigin() {
            return optionalString("securityOrigin");
        }
        /**
         * Represents a key by which DOM Storage keys its CachedStorageAreas
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMStorage.SerializedStorageKey> storageKey() {
            return optional(STORAGE_KEY);
        }
        /**
         * Whether the storage is local storage (not session storage).
         * @return the protocol field value
         */
        public boolean isLocalStorage() {
            return requireBoolean("isLocalStorage");
        }
        /**
         * Security origin for the storage.
         * @param securityOrigin field value
         * @return this model
         */
        public StorageId securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Represents a key by which DOM Storage keys its CachedStorageAreas
         * @param storageKey field value
         * @return this model
         */
        public StorageId storageKey(DOMStorage.SerializedStorageKey storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Whether the storage is local storage (not session storage).
         * @param isLocalStorage field value
         * @return this model
         */
        public StorageId isLocalStorage(boolean isLocalStorage) {
            set("isLocalStorage", isLocalStorage);
            return this;
        }
    }
    /**
     * Request parameters for DOMStorage.clear.
     */
    public static final class ClearRequest extends CdpObject {
        public ClearRequest() {}
        public static ClearRequest fromMap(Map<String, Object> values) {
            final ClearRequest instance_ = new ClearRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public ClearRequest storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
    }
    /**
     * Request parameters for DOMStorage.getDOMStorageItems.
     */
    public static final class GetDOMStorageItemsRequest extends CdpObject {
        public GetDOMStorageItemsRequest() {}
        public static GetDOMStorageItemsRequest fromMap(Map<String, Object> values) {
            final GetDOMStorageItemsRequest instance_ = new GetDOMStorageItemsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public GetDOMStorageItemsRequest storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
    }
    /**
     * Request parameters for DOMStorage.removeDOMStorageItem.
     */
    public static final class RemoveDOMStorageItemRequest extends CdpObject {
        public RemoveDOMStorageItemRequest() {}
        public static RemoveDOMStorageItemRequest fromMap(Map<String, Object> values) {
            final RemoveDOMStorageItemRequest instance_ = new RemoveDOMStorageItemRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public RemoveDOMStorageItemRequest storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public RemoveDOMStorageItemRequest key(String key) {
            set("key", key);
            return this;
        }
    }
    /**
     * Request parameters for DOMStorage.setDOMStorageItem.
     */
    public static final class SetDOMStorageItemRequest extends CdpObject {
        public SetDOMStorageItemRequest() {}
        public static SetDOMStorageItemRequest fromMap(Map<String, Object> values) {
            final SetDOMStorageItemRequest instance_ = new SetDOMStorageItemRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
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
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public SetDOMStorageItemRequest storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public SetDOMStorageItemRequest key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public SetDOMStorageItemRequest value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemAdded event.
     */
    public static final class DomStorageItemAddedEvent extends CdpObject {
        public DomStorageItemAddedEvent() {}
        private DomStorageItemAddedEvent(Map<String, Object> values) { super(values); }
        public static DomStorageItemAddedEvent fromMap(Map<String, Object> values) {
            return new DomStorageItemAddedEvent(values);
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Returns the newValue field.
         * @return the protocol field value
         */
        public String newValue() {
            return requireString("newValue");
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public DomStorageItemAddedEvent storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public DomStorageItemAddedEvent key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the newValue field.
         * @param newValue field value
         * @return this model
         */
        public DomStorageItemAddedEvent newValue(String newValue) {
            set("newValue", newValue);
            return this;
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemRemoved event.
     */
    public static final class DomStorageItemRemovedEvent extends CdpObject {
        public DomStorageItemRemovedEvent() {}
        private DomStorageItemRemovedEvent(Map<String, Object> values) { super(values); }
        public static DomStorageItemRemovedEvent fromMap(Map<String, Object> values) {
            return new DomStorageItemRemovedEvent(values);
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public DomStorageItemRemovedEvent storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public DomStorageItemRemovedEvent key(String key) {
            set("key", key);
            return this;
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemUpdated event.
     */
    public static final class DomStorageItemUpdatedEvent extends CdpObject {
        public DomStorageItemUpdatedEvent() {}
        private DomStorageItemUpdatedEvent(Map<String, Object> values) { super(values); }
        public static DomStorageItemUpdatedEvent fromMap(Map<String, Object> values) {
            return new DomStorageItemUpdatedEvent(values);
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Returns the oldValue field.
         * @return the protocol field value
         */
        public String oldValue() {
            return requireString("oldValue");
        }
        /**
         * Returns the newValue field.
         * @return the protocol field value
         */
        public String newValue() {
            return requireString("newValue");
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public DomStorageItemUpdatedEvent storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public DomStorageItemUpdatedEvent key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the oldValue field.
         * @param oldValue field value
         * @return this model
         */
        public DomStorageItemUpdatedEvent oldValue(String oldValue) {
            set("oldValue", oldValue);
            return this;
        }
        /**
         * Sets the newValue field.
         * @param newValue field value
         * @return this model
         */
        public DomStorageItemUpdatedEvent newValue(String newValue) {
            set("newValue", newValue);
            return this;
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemsCleared event.
     */
    public static final class DomStorageItemsClearedEvent extends CdpObject {
        public DomStorageItemsClearedEvent() {}
        private DomStorageItemsClearedEvent(Map<String, Object> values) { super(values); }
        public static DomStorageItemsClearedEvent fromMap(Map<String, Object> values) {
            return new DomStorageItemsClearedEvent(values);
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return require(STORAGE_ID);
        }
        /**
         * Sets the storageId field.
         * @param storageId field value
         * @return this model
         */
        public DomStorageItemsClearedEvent storageId(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Invokes DOMStorage.clear.
         * @param storageId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clear(DOMStorage.StorageId storageId) {
            return call("DOMStorage.clear", params("storageId", storageId));
        }
        /**
         * Invokes DOMStorage.clear with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clear(ClearRequest request) {
            return call("DOMStorage.clear", request);
        }
        /**
         * Disables storage tracking, prevents storage events from being sent to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("DOMStorage.disable");
        }
        /**
         * Enables storage tracking, storage events will now be delivered to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("DOMStorage.enable");
        }
        /**
         * Invokes DOMStorage.getDOMStorageItems.
         * @param storageId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<String>>> getDOMStorageItems(DOMStorage.StorageId storageId) {
            return call("DOMStorage.getDOMStorageItems", params("storageId", storageId), ENTRIES::require);
        }
        /**
         * Invokes DOMStorage.getDOMStorageItems with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<String>>> getDOMStorageItems(GetDOMStorageItemsRequest request) {
            return call("DOMStorage.getDOMStorageItems", request, ENTRIES::require);
        }
        /**
         * Invokes DOMStorage.removeDOMStorageItem.
         * @param storageId protocol value
         * @param key protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMStorageItem(DOMStorage.StorageId storageId, String key) {
            return call("DOMStorage.removeDOMStorageItem", params("storageId", storageId, "key", key));
        }
        /**
         * Invokes DOMStorage.removeDOMStorageItem with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMStorageItem(RemoveDOMStorageItemRequest request) {
            return call("DOMStorage.removeDOMStorageItem", request);
        }
        /**
         * Invokes DOMStorage.setDOMStorageItem.
         * @param storageId protocol value
         * @param key protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMStorageItem(DOMStorage.StorageId storageId, String key, String value) {
            return call("DOMStorage.setDOMStorageItem", params("storageId", storageId, "key", key, "value", value));
        }
        /**
         * Invokes DOMStorage.setDOMStorageItem with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMStorageItem(SetDOMStorageItemRequest request) {
            return call("DOMStorage.setDOMStorageItem", request);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemAdded.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemAdded(Consumer<DomStorageItemAddedEvent> handler) {
            return on("DOMStorage.domStorageItemAdded", DomStorageItemAddedEvent::fromMap, handler);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemRemoved.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemRemoved(Consumer<DomStorageItemRemovedEvent> handler) {
            return on("DOMStorage.domStorageItemRemoved", DomStorageItemRemovedEvent::fromMap, handler);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemUpdated(Consumer<DomStorageItemUpdatedEvent> handler) {
            return on("DOMStorage.domStorageItemUpdated", DomStorageItemUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemsCleared.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemsCleared(Consumer<DomStorageItemsClearedEvent> handler) {
            return on("DOMStorage.domStorageItemsCleared", DomStorageItemsClearedEvent::fromMap, handler);
        }
    }
}
