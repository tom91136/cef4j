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
 * Defines commands and events for browser extensions.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Extensions.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Extensions {
    private Extensions() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Storage areas.
     */
    public static final class StorageArea {
        private StorageArea() {}
        public static final String SESSION = "session";
        public static final String LOCAL = "local";
        public static final String SYNC = "sync";
        public static final String MANAGED = "managed";
    }
    /**
     * Detailed information about an extension.
     */
    public static final class ExtensionInfo extends CdpObject {
        private ExtensionInfo(Map<String, Object> values) { super(values); }
        @Nullable public static ExtensionInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExtensionInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Extension id.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Extension name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Extension version.
         * @return the protocol field value
         */
        @Nullable public String version() {
            return (String) value("version");
        }
        /**
         * The path from which the extension was loaded.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * Extension enabled status.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Extension id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Extension name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Extension version.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder version(@Nullable String value) {
                if (value == null) values.remove("version");
                else values.put("version", jsonValue(value));
                return this;
            }
            /**
             * The path from which the extension was loaded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * Extension enabled status.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public ExtensionInfo build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("version")) throw new IllegalStateException("Missing required CDP field: version");
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new ExtensionInfo(values);
            }
        }
    }
    /**
     * Runs an extension default action.
     */
    public static final class TriggerActionParams extends CdpObject {
        private TriggerActionParams(Map<String, Object> values) { super(values); }
        @Nullable public static TriggerActionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TriggerActionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Extension id.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * A tab target ID to trigger the default extension action on.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Extension id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * A tab target ID to trigger the default extension action on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public TriggerActionParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new TriggerActionParams(values);
            }
        }
    }
    /**
     * Runs an extension default action.
     */
    public static final class TriggerActionResult extends CdpObject {
        private TriggerActionResult(Map<String, Object> values) { super(values); }
        @Nullable public static TriggerActionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TriggerActionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TriggerActionResult build() {
                return new TriggerActionResult(values);
            }
        }
    }
    /**
     * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
     */
    public static final class LoadUnpackedParams extends CdpObject {
        private LoadUnpackedParams(Map<String, Object> values) { super(values); }
        @Nullable public static LoadUnpackedParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadUnpackedParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Absolute file path.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * Enable the extension in incognito
         * @return the protocol field value
         */
        @Nullable public Boolean enableInIncognito() {
            return (Boolean) value("enableInIncognito");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Absolute file path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * Enable the extension in incognito
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableInIncognito(@Nullable Boolean value) {
                if (value == null) values.remove("enableInIncognito");
                else values.put("enableInIncognito", jsonValue(value));
                return this;
            }
            public LoadUnpackedParams build() {
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                return new LoadUnpackedParams(values);
            }
        }
    }
    /**
     * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
     */
    public static final class LoadUnpackedResult extends CdpObject {
        private LoadUnpackedResult(Map<String, Object> values) { super(values); }
        @Nullable public static LoadUnpackedResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadUnpackedResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Extension id.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Extension id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public LoadUnpackedResult build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new LoadUnpackedResult(values);
            }
        }
    }
    /**
     * Gets a list of all unpacked extensions.
     */
    public static final class GetExtensionsParams extends CdpObject {
        private GetExtensionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetExtensionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetExtensionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetExtensionsParams build() {
                return new GetExtensionsParams(values);
            }
        }
    }
    /**
     * Gets a list of all unpacked extensions.
     */
    public static final class GetExtensionsResult extends CdpObject {
        private GetExtensionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetExtensionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetExtensionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the extensions field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Extensions.ExtensionInfo> extensions() {
            return list(value("extensions"), element0 -> Extensions.ExtensionInfo.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the extensions field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder extensions(@Nullable java.util.List<Extensions.ExtensionInfo> value) {
                if (value == null) values.remove("extensions");
                else values.put("extensions", jsonValue(value));
                return this;
            }
            public GetExtensionsResult build() {
                if (!values.containsKey("extensions")) throw new IllegalStateException("Missing required CDP field: extensions");
                return new GetExtensionsResult(values);
            }
        }
    }
    /**
     * Uninstalls an unpacked extension (others not supported) from the profile.
     */
    public static final class UninstallParams extends CdpObject {
        private UninstallParams(Map<String, Object> values) { super(values); }
        @Nullable public static UninstallParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UninstallParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Extension id.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Extension id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public UninstallParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new UninstallParams(values);
            }
        }
    }
    /**
     * Uninstalls an unpacked extension (others not supported) from the profile.
     */
    public static final class UninstallResult extends CdpObject {
        private UninstallResult(Map<String, Object> values) { super(values); }
        @Nullable public static UninstallResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UninstallResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UninstallResult build() {
                return new UninstallResult(values);
            }
        }
    }
    /**
     * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
     */
    public static final class GetStorageItemsParams extends CdpObject {
        private GetStorageItemsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetStorageItemsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStorageItemsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * StorageArea to retrieve data from.
         * @return the protocol field value
         */
        @Nullable public String storageArea() {
            return (String) value("storageArea");
        }
        /**
         * Keys to retrieve.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> keys() {
            return list(value("keys"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of extension.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * StorageArea to retrieve data from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageArea(@Nullable String value) {
                if (value == null) values.remove("storageArea");
                else values.put("storageArea", jsonValue(value));
                return this;
            }
            /**
             * Keys to retrieve.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keys(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("keys");
                else values.put("keys", jsonValue(value));
                return this;
            }
            public GetStorageItemsParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("storageArea")) throw new IllegalStateException("Missing required CDP field: storageArea");
                return new GetStorageItemsParams(values);
            }
        }
    }
    /**
     * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
     */
    public static final class GetStorageItemsResult extends CdpObject {
        private GetStorageItemsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetStorageItemsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStorageItemsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> data() {
            return objectMap(value("data"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public GetStorageItemsResult build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new GetStorageItemsResult(values);
            }
        }
    }
    /**
     * Removes {@code keys} from extension storage in the given {@code storageArea}.
     */
    public static final class RemoveStorageItemsParams extends CdpObject {
        private RemoveStorageItemsParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveStorageItemsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveStorageItemsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * StorageArea to remove data from.
         * @return the protocol field value
         */
        @Nullable public String storageArea() {
            return (String) value("storageArea");
        }
        /**
         * Keys to remove.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> keys() {
            return list(value("keys"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of extension.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * StorageArea to remove data from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageArea(@Nullable String value) {
                if (value == null) values.remove("storageArea");
                else values.put("storageArea", jsonValue(value));
                return this;
            }
            /**
             * Keys to remove.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keys(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("keys");
                else values.put("keys", jsonValue(value));
                return this;
            }
            public RemoveStorageItemsParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("storageArea")) throw new IllegalStateException("Missing required CDP field: storageArea");
                if (!values.containsKey("keys")) throw new IllegalStateException("Missing required CDP field: keys");
                return new RemoveStorageItemsParams(values);
            }
        }
    }
    /**
     * Removes {@code keys} from extension storage in the given {@code storageArea}.
     */
    public static final class RemoveStorageItemsResult extends CdpObject {
        private RemoveStorageItemsResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveStorageItemsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveStorageItemsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveStorageItemsResult build() {
                return new RemoveStorageItemsResult(values);
            }
        }
    }
    /**
     * Clears extension storage in the given {@code storageArea}.
     */
    public static final class ClearStorageItemsParams extends CdpObject {
        private ClearStorageItemsParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearStorageItemsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearStorageItemsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * StorageArea to remove data from.
         * @return the protocol field value
         */
        @Nullable public String storageArea() {
            return (String) value("storageArea");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of extension.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * StorageArea to remove data from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageArea(@Nullable String value) {
                if (value == null) values.remove("storageArea");
                else values.put("storageArea", jsonValue(value));
                return this;
            }
            public ClearStorageItemsParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("storageArea")) throw new IllegalStateException("Missing required CDP field: storageArea");
                return new ClearStorageItemsParams(values);
            }
        }
    }
    /**
     * Clears extension storage in the given {@code storageArea}.
     */
    public static final class ClearStorageItemsResult extends CdpObject {
        private ClearStorageItemsResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearStorageItemsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearStorageItemsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearStorageItemsResult build() {
                return new ClearStorageItemsResult(values);
            }
        }
    }
    /**
     * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
     */
    public static final class SetStorageItemsParams extends CdpObject {
        private SetStorageItemsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetStorageItemsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStorageItemsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * StorageArea to set data in.
         * @return the protocol field value
         */
        @Nullable public String storageArea() {
            return (String) value("storageArea");
        }
        /**
         * Values to set.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> values() {
            return objectMap(value("values"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of extension.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * StorageArea to set data in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageArea(@Nullable String value) {
                if (value == null) values.remove("storageArea");
                else values.put("storageArea", jsonValue(value));
                return this;
            }
            /**
             * Values to set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder values(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("values");
                else values.put("values", jsonValue(value));
                return this;
            }
            public SetStorageItemsParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("storageArea")) throw new IllegalStateException("Missing required CDP field: storageArea");
                if (!values.containsKey("values")) throw new IllegalStateException("Missing required CDP field: values");
                return new SetStorageItemsParams(values);
            }
        }
    }
    /**
     * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
     */
    public static final class SetStorageItemsResult extends CdpObject {
        private SetStorageItemsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetStorageItemsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStorageItemsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetStorageItemsResult build() {
                return new SetStorageItemsResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Runs an extension default action.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TriggerActionResult> triggerAction(TriggerActionParams params) {
            return client.call("Extensions.triggerAction", params, TriggerActionResult::fromMap);
        }
        /**
         * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LoadUnpackedResult> loadUnpacked(LoadUnpackedParams params) {
            return client.call("Extensions.loadUnpacked", params, LoadUnpackedResult::fromMap);
        }
        /**
         * Gets a list of all unpacked extensions.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetExtensionsResult> getExtensions() {
            return client.call("Extensions.getExtensions", null, GetExtensionsResult::fromMap);
        }
        /**
         * Uninstalls an unpacked extension (others not supported) from the profile.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UninstallResult> uninstall(UninstallParams params) {
            return client.call("Extensions.uninstall", params, UninstallResult::fromMap);
        }
        /**
         * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetStorageItemsResult> getStorageItems(GetStorageItemsParams params) {
            return client.call("Extensions.getStorageItems", params, GetStorageItemsResult::fromMap);
        }
        /**
         * Removes {@code keys} from extension storage in the given {@code storageArea}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveStorageItemsResult> removeStorageItems(RemoveStorageItemsParams params) {
            return client.call("Extensions.removeStorageItems", params, RemoveStorageItemsResult::fromMap);
        }
        /**
         * Clears extension storage in the given {@code storageArea}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearStorageItemsResult> clearStorageItems(ClearStorageItemsParams params) {
            return client.call("Extensions.clearStorageItems", params, ClearStorageItemsResult::fromMap);
        }
        /**
         * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetStorageItemsResult> setStorageItems(SetStorageItemsParams params) {
            return client.call("Extensions.setStorageItems", params, SetStorageItemsResult::fromMap);
        }
    }
}
