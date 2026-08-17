// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Query and modify DOM storage.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMStorage.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class DOMStorage {
    private DOMStorage() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * DOM Storage identifier.
     */
    public static final class StorageId extends CdpObject {
        private StorageId(Map<String, Object> values) { super(values); }
        @Nullable public static StorageId fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StorageId(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security origin for the storage.
         * @return the protocol field value
         */
        @Nullable public String securityOrigin() {
            return (String) value("securityOrigin");
        }
        /**
         * Represents a key by which DOM Storage keys its CachedStorageAreas
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Whether the storage is local storage (not session storage).
         * @return the protocol field value
         */
        @Nullable public Boolean isLocalStorage() {
            return (Boolean) value("isLocalStorage");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security origin for the storage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityOrigin(@Nullable String value) {
                if (value == null) values.remove("securityOrigin");
                else values.put("securityOrigin", jsonValue(value));
                return this;
            }
            /**
             * Represents a key by which DOM Storage keys its CachedStorageAreas
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Whether the storage is local storage (not session storage).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isLocalStorage(@Nullable Boolean value) {
                if (value == null) values.remove("isLocalStorage");
                else values.put("isLocalStorage", jsonValue(value));
                return this;
            }
            public StorageId build() {
                if (!values.containsKey("isLocalStorage")) throw new IllegalStateException("Missing required CDP field: isLocalStorage");
                return new StorageId(values);
            }
        }
    }
    /**
     * Parameters for DOMStorage.clear.
     */
    public static final class ClearParams extends CdpObject {
        private ClearParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
                return this;
            }
            public ClearParams build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                return new ClearParams(values);
            }
        }
    }
    /**
     * Result of DOMStorage.clear.
     */
    public static final class ClearResult extends CdpObject {
        private ClearResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearResult build() {
                return new ClearResult(values);
            }
        }
    }
    /**
     * Disables storage tracking, prevents storage events from being sent to the client.
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
     * Disables storage tracking, prevents storage events from being sent to the client.
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
     * Enables storage tracking, storage events will now be delivered to the client.
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
     * Enables storage tracking, storage events will now be delivered to the client.
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
     * Parameters for DOMStorage.getDOMStorageItems.
     */
    public static final class GetDOMStorageItemsParams extends CdpObject {
        private GetDOMStorageItemsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDOMStorageItemsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDOMStorageItemsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
                return this;
            }
            public GetDOMStorageItemsParams build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                return new GetDOMStorageItemsParams(values);
            }
        }
    }
    /**
     * Result of DOMStorage.getDOMStorageItems.
     */
    public static final class GetDOMStorageItemsResult extends CdpObject {
        private GetDOMStorageItemsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDOMStorageItemsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDOMStorageItemsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the entries field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<String>> entries() {
            return list(value("entries"), element0 -> list(element0, element1 -> (String) element1));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the entries field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entries(@Nullable java.util.List<java.util.List<String>> value) {
                if (value == null) values.remove("entries");
                else values.put("entries", jsonValue(value));
                return this;
            }
            public GetDOMStorageItemsResult build() {
                if (!values.containsKey("entries")) throw new IllegalStateException("Missing required CDP field: entries");
                return new GetDOMStorageItemsResult(values);
            }
        }
    }
    /**
     * Parameters for DOMStorage.removeDOMStorageItem.
     */
    public static final class RemoveDOMStorageItemParams extends CdpObject {
        private RemoveDOMStorageItemParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveDOMStorageItemParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveDOMStorageItemParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
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
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
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
            public RemoveDOMStorageItemParams build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                return new RemoveDOMStorageItemParams(values);
            }
        }
    }
    /**
     * Result of DOMStorage.removeDOMStorageItem.
     */
    public static final class RemoveDOMStorageItemResult extends CdpObject {
        private RemoveDOMStorageItemResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveDOMStorageItemResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveDOMStorageItemResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveDOMStorageItemResult build() {
                return new RemoveDOMStorageItemResult(values);
            }
        }
    }
    /**
     * Parameters for DOMStorage.setDOMStorageItem.
     */
    public static final class SetDOMStorageItemParams extends CdpObject {
        private SetDOMStorageItemParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDOMStorageItemParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDOMStorageItemParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
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
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
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
            public SetDOMStorageItemParams build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetDOMStorageItemParams(values);
            }
        }
    }
    /**
     * Result of DOMStorage.setDOMStorageItem.
     */
    public static final class SetDOMStorageItemResult extends CdpObject {
        private SetDOMStorageItemResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDOMStorageItemResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDOMStorageItemResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDOMStorageItemResult build() {
                return new SetDOMStorageItemResult(values);
            }
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemAdded event.
     */
    public static final class DomStorageItemAddedEvent extends CdpObject {
        private DomStorageItemAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DomStorageItemAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DomStorageItemAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Returns the newValue field.
         * @return the protocol field value
         */
        @Nullable public String newValue() {
            return (String) value("newValue");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
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
             * Sets the newValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newValue(@Nullable String value) {
                if (value == null) values.remove("newValue");
                else values.put("newValue", jsonValue(value));
                return this;
            }
            public DomStorageItemAddedEvent build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("newValue")) throw new IllegalStateException("Missing required CDP field: newValue");
                return new DomStorageItemAddedEvent(values);
            }
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemRemoved event.
     */
    public static final class DomStorageItemRemovedEvent extends CdpObject {
        private DomStorageItemRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DomStorageItemRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DomStorageItemRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
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
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
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
            public DomStorageItemRemovedEvent build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                return new DomStorageItemRemovedEvent(values);
            }
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemUpdated event.
     */
    public static final class DomStorageItemUpdatedEvent extends CdpObject {
        private DomStorageItemUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DomStorageItemUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DomStorageItemUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Returns the oldValue field.
         * @return the protocol field value
         */
        @Nullable public String oldValue() {
            return (String) value("oldValue");
        }
        /**
         * Returns the newValue field.
         * @return the protocol field value
         */
        @Nullable public String newValue() {
            return (String) value("newValue");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
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
             * Sets the oldValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder oldValue(@Nullable String value) {
                if (value == null) values.remove("oldValue");
                else values.put("oldValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the newValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newValue(@Nullable String value) {
                if (value == null) values.remove("newValue");
                else values.put("newValue", jsonValue(value));
                return this;
            }
            public DomStorageItemUpdatedEvent build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("oldValue")) throw new IllegalStateException("Missing required CDP field: oldValue");
                if (!values.containsKey("newValue")) throw new IllegalStateException("Missing required CDP field: newValue");
                return new DomStorageItemUpdatedEvent(values);
            }
        }
    }
    /**
     * Payload of the DOMStorage.domStorageItemsCleared event.
     */
    public static final class DomStorageItemsClearedEvent extends CdpObject {
        private DomStorageItemsClearedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DomStorageItemsClearedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DomStorageItemsClearedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the storageId field.
         * @return the protocol field value
         */
        @Nullable public DOMStorage.StorageId storageId() {
            return DOMStorage.StorageId.fromMap(objectMap(value("storageId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the storageId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageId(@Nullable DOMStorage.StorageId value) {
                if (value == null) values.remove("storageId");
                else values.put("storageId", jsonValue(value));
                return this;
            }
            public DomStorageItemsClearedEvent build() {
                if (!values.containsKey("storageId")) throw new IllegalStateException("Missing required CDP field: storageId");
                return new DomStorageItemsClearedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes DOMStorage.clear.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearResult> clear(ClearParams params) {
            return client.call("DOMStorage.clear", params, ClearResult::fromMap);
        }
        /**
         * Disables storage tracking, prevents storage events from being sent to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("DOMStorage.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables storage tracking, storage events will now be delivered to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("DOMStorage.enable", null, EnableResult::fromMap);
        }
        /**
         * Invokes DOMStorage.getDOMStorageItems.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDOMStorageItemsResult> getDOMStorageItems(GetDOMStorageItemsParams params) {
            return client.call("DOMStorage.getDOMStorageItems", params, GetDOMStorageItemsResult::fromMap);
        }
        /**
         * Invokes DOMStorage.removeDOMStorageItem.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveDOMStorageItemResult> removeDOMStorageItem(RemoveDOMStorageItemParams params) {
            return client.call("DOMStorage.removeDOMStorageItem", params, RemoveDOMStorageItemResult::fromMap);
        }
        /**
         * Invokes DOMStorage.setDOMStorageItem.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDOMStorageItemResult> setDOMStorageItem(SetDOMStorageItemParams params) {
            return client.call("DOMStorage.setDOMStorageItem", params, SetDOMStorageItemResult::fromMap);
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
