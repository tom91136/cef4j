// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Defines commands and events for browser extensions.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Extensions.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Extensions {
    private Extensions() {}
    private static final CdpField<java.util.Map<String, Object>> DATA = CdpField.of("data", value -> CdpObject.requireObjectMap(value));
    private static final CdpField<java.util.List<Extensions.ExtensionInfo>> EXTENSIONS = CdpField.of("extensions", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Extensions.ExtensionInfo.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<String> ID = CdpField.of("id", value -> (String) value);
    private static final CdpField<java.util.List<String>> KEYS = CdpField.of("keys", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Extensions.StorageArea> STORAGE_AREA = CdpField.of("storageArea", value -> Extensions.StorageArea.of((String) value));
    /**
     * Storage areas.
     */
    public enum StorageArea implements CdpValue<String> {
        SESSION("session"),
        LOCAL("local"),
        SYNC("sync"),
        MANAGED("managed");
        public final String value;
        StorageArea(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StorageArea of(String value) {
            for (StorageArea constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StorageArea value: " + value);
        }
    }
    /**
     * Detailed information about an extension.
     */
    public static final class ExtensionInfo extends CdpObject {
        public ExtensionInfo() {}
        private ExtensionInfo(Map<String, Object> values) { super(values); }
        public static ExtensionInfo fromMap(Map<String, Object> values) {
            return new ExtensionInfo(values);
        }
        /**
         * Extension id.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Extension name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Extension version.
         * @return the protocol field value
         */
        public String version() {
            return requireString("version");
        }
        /**
         * The path from which the extension was loaded.
         * @return the protocol field value
         */
        public String path() {
            return requireString("path");
        }
        /**
         * Extension enabled status.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Extension id.
         * @param id field value
         * @return this model
         */
        public ExtensionInfo id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Extension name.
         * @param name field value
         * @return this model
         */
        public ExtensionInfo name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Extension version.
         * @param version field value
         * @return this model
         */
        public ExtensionInfo version(String version) {
            set("version", version);
            return this;
        }
        /**
         * The path from which the extension was loaded.
         * @param path field value
         * @return this model
         */
        public ExtensionInfo path(String path) {
            set("path", path);
            return this;
        }
        /**
         * Extension enabled status.
         * @param enabled field value
         * @return this model
         */
        public ExtensionInfo enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Runs an extension default action.
     */
    public static final class TriggerActionRequest extends CdpObject {
        public TriggerActionRequest() {}
        public static TriggerActionRequest fromMap(Map<String, Object> values) {
            final TriggerActionRequest instance_ = new TriggerActionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Extension id.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * A tab target ID to trigger the default extension action on.
         * @return the protocol field value
         */
        public String targetId() {
            return requireString("targetId");
        }
        /**
         * Extension id.
         * @param id field value
         * @return this model
         */
        public TriggerActionRequest id(String id) {
            set("id", id);
            return this;
        }
        /**
         * A tab target ID to trigger the default extension action on.
         * @param targetId field value
         * @return this model
         */
        public TriggerActionRequest targetId(String targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
     */
    public static final class LoadUnpackedRequest extends CdpObject {
        public LoadUnpackedRequest() {}
        public static LoadUnpackedRequest fromMap(Map<String, Object> values) {
            final LoadUnpackedRequest instance_ = new LoadUnpackedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Absolute file path.
         * @return the protocol field value
         */
        public String path() {
            return requireString("path");
        }
        /**
         * Enable the extension in incognito
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableInIncognito() {
            return optionalBoolean("enableInIncognito");
        }
        /**
         * Absolute file path.
         * @param path field value
         * @return this model
         */
        public LoadUnpackedRequest path(String path) {
            set("path", path);
            return this;
        }
        /**
         * Enable the extension in incognito
         * @param enableInIncognito field value
         * @return this model
         */
        public LoadUnpackedRequest enableInIncognito(boolean enableInIncognito) {
            set("enableInIncognito", enableInIncognito);
            return this;
        }
    }
    /**
     * Uninstalls an unpacked extension (others not supported) from the profile.
     */
    public static final class UninstallRequest extends CdpObject {
        public UninstallRequest() {}
        public static UninstallRequest fromMap(Map<String, Object> values) {
            final UninstallRequest instance_ = new UninstallRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Extension id.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Extension id.
         * @param id field value
         * @return this model
         */
        public UninstallRequest id(String id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
     */
    public static final class GetStorageItemsRequest extends CdpObject {
        public GetStorageItemsRequest() {}
        public static GetStorageItemsRequest fromMap(Map<String, Object> values) {
            final GetStorageItemsRequest instance_ = new GetStorageItemsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * StorageArea to retrieve data from.
         * @return the protocol field value
         */
        public Extensions.StorageArea storageArea() {
            return require(STORAGE_AREA);
        }
        /**
         * Keys to retrieve.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> keys() {
            return optional(KEYS);
        }
        /**
         * ID of extension.
         * @param id field value
         * @return this model
         */
        public GetStorageItemsRequest id(String id) {
            set("id", id);
            return this;
        }
        /**
         * StorageArea to retrieve data from.
         * @param storageArea field value
         * @return this model
         */
        public GetStorageItemsRequest storageArea(Extensions.StorageArea storageArea) {
            set("storageArea", storageArea);
            return this;
        }
        /**
         * Keys to retrieve.
         * @param keys field value
         * @return this model
         */
        public GetStorageItemsRequest keys(java.util.List<String> keys) {
            set("keys", keys);
            return this;
        }
    }
    /**
     * Removes {@code keys} from extension storage in the given {@code storageArea}.
     */
    public static final class RemoveStorageItemsRequest extends CdpObject {
        public RemoveStorageItemsRequest() {}
        public static RemoveStorageItemsRequest fromMap(Map<String, Object> values) {
            final RemoveStorageItemsRequest instance_ = new RemoveStorageItemsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * StorageArea to remove data from.
         * @return the protocol field value
         */
        public Extensions.StorageArea storageArea() {
            return require(STORAGE_AREA);
        }
        /**
         * Keys to remove.
         * @return the protocol field value
         */
        public java.util.List<String> keys() {
            return require(KEYS);
        }
        /**
         * ID of extension.
         * @param id field value
         * @return this model
         */
        public RemoveStorageItemsRequest id(String id) {
            set("id", id);
            return this;
        }
        /**
         * StorageArea to remove data from.
         * @param storageArea field value
         * @return this model
         */
        public RemoveStorageItemsRequest storageArea(Extensions.StorageArea storageArea) {
            set("storageArea", storageArea);
            return this;
        }
        /**
         * Keys to remove.
         * @param keys field value
         * @return this model
         */
        public RemoveStorageItemsRequest keys(java.util.List<String> keys) {
            set("keys", keys);
            return this;
        }
    }
    /**
     * Clears extension storage in the given {@code storageArea}.
     */
    public static final class ClearStorageItemsRequest extends CdpObject {
        public ClearStorageItemsRequest() {}
        public static ClearStorageItemsRequest fromMap(Map<String, Object> values) {
            final ClearStorageItemsRequest instance_ = new ClearStorageItemsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * StorageArea to remove data from.
         * @return the protocol field value
         */
        public Extensions.StorageArea storageArea() {
            return require(STORAGE_AREA);
        }
        /**
         * ID of extension.
         * @param id field value
         * @return this model
         */
        public ClearStorageItemsRequest id(String id) {
            set("id", id);
            return this;
        }
        /**
         * StorageArea to remove data from.
         * @param storageArea field value
         * @return this model
         */
        public ClearStorageItemsRequest storageArea(Extensions.StorageArea storageArea) {
            set("storageArea", storageArea);
            return this;
        }
    }
    /**
     * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
     */
    public static final class SetStorageItemsRequest extends CdpObject {
        public SetStorageItemsRequest() {}
        public static SetStorageItemsRequest fromMap(Map<String, Object> values) {
            final SetStorageItemsRequest instance_ = new SetStorageItemsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ID of extension.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * StorageArea to set data in.
         * @return the protocol field value
         */
        public Extensions.StorageArea storageArea() {
            return require(STORAGE_AREA);
        }
        /**
         * Values to set.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> values() {
            return requireObject("values");
        }
        /**
         * ID of extension.
         * @param id field value
         * @return this model
         */
        public SetStorageItemsRequest id(String id) {
            set("id", id);
            return this;
        }
        /**
         * StorageArea to set data in.
         * @param storageArea field value
         * @return this model
         */
        public SetStorageItemsRequest storageArea(Extensions.StorageArea storageArea) {
            set("storageArea", storageArea);
            return this;
        }
        /**
         * Values to set.
         * @param values field value
         * @return this model
         */
        public SetStorageItemsRequest values(java.util.Map<String, Object> values) {
            set("values", values);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Runs an extension default action.
         * @param id protocol value
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> triggerAction(String id, String targetId) {
            return call("Extensions.triggerAction", params("id", id, "targetId", targetId));
        }
        /**
         * Runs an extension default action.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> triggerAction(TriggerActionRequest request) {
            return call("Extensions.triggerAction", request);
        }
        /**
         * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
         * @param path protocol value
         * @param enableInIncognito protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> loadUnpacked(String path, boolean enableInIncognito) {
            return call("Extensions.loadUnpacked", params("path", path, "enableInIncognito", enableInIncognito), ID::require);
        }
        /**
         * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
         * @param path protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> loadUnpacked(String path) {
            return call("Extensions.loadUnpacked", params("path", path), ID::require);
        }
        /**
         * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> loadUnpacked(LoadUnpackedRequest request) {
            return call("Extensions.loadUnpacked", request, ID::require);
        }
        /**
         * Gets a list of all unpacked extensions.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Extensions.ExtensionInfo>> getExtensions() {
            return call("Extensions.getExtensions", EXTENSIONS::require);
        }
        /**
         * Uninstalls an unpacked extension (others not supported) from the profile.
         * @param id protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> uninstall(String id) {
            return call("Extensions.uninstall", params("id", id));
        }
        /**
         * Uninstalls an unpacked extension (others not supported) from the profile.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> uninstall(UninstallRequest request) {
            return call("Extensions.uninstall", request);
        }
        /**
         * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
         * @param id protocol value
         * @param storageArea protocol value
         * @param keys protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getStorageItems(String id, Extensions.StorageArea storageArea, java.util.List<String> keys) {
            return call("Extensions.getStorageItems", params("id", id, "storageArea", storageArea, "keys", keys), DATA::require);
        }
        /**
         * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
         * @param id protocol value
         * @param storageArea protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getStorageItems(String id, Extensions.StorageArea storageArea) {
            return call("Extensions.getStorageItems", params("id", id, "storageArea", storageArea), DATA::require);
        }
        /**
         * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getStorageItems(GetStorageItemsRequest request) {
            return call("Extensions.getStorageItems", request, DATA::require);
        }
        /**
         * Removes {@code keys} from extension storage in the given {@code storageArea}.
         * @param id protocol value
         * @param storageArea protocol value
         * @param keys protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeStorageItems(String id, Extensions.StorageArea storageArea, java.util.List<String> keys) {
            return call("Extensions.removeStorageItems", params("id", id, "storageArea", storageArea, "keys", keys));
        }
        /**
         * Removes {@code keys} from extension storage in the given {@code storageArea}.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeStorageItems(RemoveStorageItemsRequest request) {
            return call("Extensions.removeStorageItems", request);
        }
        /**
         * Clears extension storage in the given {@code storageArea}.
         * @param id protocol value
         * @param storageArea protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearStorageItems(String id, Extensions.StorageArea storageArea) {
            return call("Extensions.clearStorageItems", params("id", id, "storageArea", storageArea));
        }
        /**
         * Clears extension storage in the given {@code storageArea}.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearStorageItems(ClearStorageItemsRequest request) {
            return call("Extensions.clearStorageItems", request);
        }
        /**
         * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
         * @param id protocol value
         * @param storageArea protocol value
         * @param values protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageItems(String id, Extensions.StorageArea storageArea, java.util.Map<String, Object> values) {
            return call("Extensions.setStorageItems", params("id", id, "storageArea", storageArea, "values", values));
        }
        /**
         * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageItems(SetStorageItemsRequest request) {
            return call("Extensions.setStorageItems", request);
        }
    }
}
