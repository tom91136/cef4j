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
 * Query and modify DOM storage.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMStorage.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class DOMStorage {
    private DOMStorage() {}
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
            return Optional.ofNullable((String) raw("securityOrigin"));
        }
        /**
         * Represents a key by which DOM Storage keys its CachedStorageAreas
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMStorage.SerializedStorageKey> storageKey() {
            return Optional.ofNullable(raw("storageKey") == null ? null : new DOMStorage.SerializedStorageKey((String) raw("storageKey")));
        }
        /**
         * Whether the storage is local storage (not session storage).
         * @return the protocol field value
         */
        public boolean isLocalStorage() {
            return (Boolean) require("isLocalStorage");
        }
        /**
         * Security origin for the storage.
         * @param securityOrigin field value; empty omits the value
         * @return this model
         */
        public StorageId securityOrigin(Optional<String> securityOrigin) {
            set("securityOrigin", securityOrigin.orElse(null));
            return this;
        }
        /**
         * Security origin for the storage.
         * @param securityOrigin field value; null removes the value
         * @return this model
         */
        public StorageId securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Represents a key by which DOM Storage keys its CachedStorageAreas
         * @param storageKey field value; empty omits the value
         * @return this model
         */
        public StorageId storageKey(Optional<DOMStorage.SerializedStorageKey> storageKey) {
            set("storageKey", storageKey.orElse(null));
            return this;
        }
        /**
         * Represents a key by which DOM Storage keys its CachedStorageAreas
         * @param storageKey field value; null removes the value
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
        /**
         * Creates a new ClearRequest with all required parameters.
         * @param storageId protocol value
         */
        public ClearRequest(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
        }
        public static ClearRequest fromMap(Map<String, Object> values) {
            ClearRequest instance_ = new ClearRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
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
        /**
         * Creates a new GetDOMStorageItemsRequest with all required parameters.
         * @param storageId protocol value
         */
        public GetDOMStorageItemsRequest(DOMStorage.StorageId storageId) {
            set("storageId", storageId);
        }
        public static GetDOMStorageItemsRequest fromMap(Map<String, Object> values) {
            GetDOMStorageItemsRequest instance_ = new GetDOMStorageItemsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
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
        /**
         * Creates a new RemoveDOMStorageItemRequest with all required parameters.
         * @param storageId protocol value
         * @param key protocol value
         */
        public RemoveDOMStorageItemRequest(DOMStorage.StorageId storageId, String key) {
            set("storageId", storageId);
            set("key", key);
        }
        public static RemoveDOMStorageItemRequest fromMap(Map<String, Object> values) {
            RemoveDOMStorageItemRequest instance_ = new RemoveDOMStorageItemRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return (String) require("key");
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
        /**
         * Creates a new SetDOMStorageItemRequest with all required parameters.
         * @param storageId protocol value
         * @param key protocol value
         * @param value protocol value
         */
        public SetDOMStorageItemRequest(DOMStorage.StorageId storageId, String key, String value) {
            set("storageId", storageId);
            set("key", key);
            set("value", value);
        }
        public static SetDOMStorageItemRequest fromMap(Map<String, Object> values) {
            SetDOMStorageItemRequest instance_ = new SetDOMStorageItemRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        public DOMStorage.StorageId storageId() {
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
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
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return (String) require("key");
        }
        /**
         * Returns the newValue field.
         * @return the protocol field value
         */
        public String newValue() {
            return (String) require("newValue");
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
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return (String) require("key");
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
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return (String) require("key");
        }
        /**
         * Returns the oldValue field.
         * @return the protocol field value
         */
        public String oldValue() {
            return (String) require("oldValue");
        }
        /**
         * Returns the newValue field.
         * @return the protocol field value
         */
        public String newValue() {
            return (String) require("newValue");
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
            return java.util.Objects.requireNonNull(DOMStorage.StorageId.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("storageId")))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes DOMStorage.clear.
         * @param storageId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clear(DOMStorage.StorageId storageId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageId", CdpObject.json(storageId));
            return client.call("DOMStorage.clear", params, result_ -> null);
        }
        /**
         * Invokes DOMStorage.clear with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clear(ClearRequest request) {
            return client.call("DOMStorage.clear", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Disables storage tracking, prevents storage events from being sent to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("DOMStorage.disable", null, result_ -> null);
        }
        /**
         * Enables storage tracking, storage events will now be delivered to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("DOMStorage.enable", null, result_ -> null);
        }
        /**
         * Invokes DOMStorage.getDOMStorageItems.
         * @param storageId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<String>>> getDOMStorageItems(DOMStorage.StorageId storageId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageId", CdpObject.json(storageId));
            return client.call("DOMStorage.getDOMStorageItems", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("entries")), element0 -> CdpObject.requireList(element0, element1 -> (String) element1)));
        }
        /**
         * Invokes DOMStorage.getDOMStorageItems with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<String>>> getDOMStorageItems(GetDOMStorageItemsRequest request) {
            return client.call("DOMStorage.getDOMStorageItems", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("entries")), element0 -> CdpObject.requireList(element0, element1 -> (String) element1)));
        }
        /**
         * Invokes DOMStorage.removeDOMStorageItem.
         * @param storageId protocol value
         * @param key protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMStorageItem(DOMStorage.StorageId storageId, String key) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageId", CdpObject.json(storageId));
            params.put("key", CdpObject.json(key));
            return client.call("DOMStorage.removeDOMStorageItem", params, result_ -> null);
        }
        /**
         * Invokes DOMStorage.removeDOMStorageItem with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMStorageItem(RemoveDOMStorageItemRequest request) {
            return client.call("DOMStorage.removeDOMStorageItem", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes DOMStorage.setDOMStorageItem.
         * @param storageId protocol value
         * @param key protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMStorageItem(DOMStorage.StorageId storageId, String key, String value) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("storageId", CdpObject.json(storageId));
            params.put("key", CdpObject.json(key));
            params.put("value", CdpObject.json(value));
            return client.call("DOMStorage.setDOMStorageItem", params, result_ -> null);
        }
        /**
         * Invokes DOMStorage.setDOMStorageItem with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMStorageItem(SetDOMStorageItemRequest request) {
            return client.call("DOMStorage.setDOMStorageItem", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemAdded.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemAdded(Consumer<DomStorageItemAddedEvent> handler) {
            return client.on("DOMStorage.domStorageItemAdded", DomStorageItemAddedEvent::fromMap, handler);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemRemoved.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemRemoved(Consumer<DomStorageItemRemovedEvent> handler) {
            return client.on("DOMStorage.domStorageItemRemoved", DomStorageItemRemovedEvent::fromMap, handler);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemUpdated(Consumer<DomStorageItemUpdatedEvent> handler) {
            return client.on("DOMStorage.domStorageItemUpdated", DomStorageItemUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to DOMStorage.domStorageItemsCleared.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomStorageItemsCleared(Consumer<DomStorageItemsClearedEvent> handler) {
            return client.on("DOMStorage.domStorageItemsCleared", DomStorageItemsClearedEvent::fromMap, handler);
        }
    }
}
