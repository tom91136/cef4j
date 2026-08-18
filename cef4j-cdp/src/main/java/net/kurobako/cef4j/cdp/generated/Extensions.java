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
 * Defines commands and events for browser extensions.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Extensions.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Extensions {
    private Extensions() {}
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
        @Nonnull public String value() { return value; }
        public static StorageArea of(@Nonnull String value) {
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
            return (String) require("id");
        }
        /**
         * Extension name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Extension version.
         * @return the protocol field value
         */
        public String version() {
            return (String) require("version");
        }
        /**
         * The path from which the extension was loaded.
         * @return the protocol field value
         */
        public String path() {
            return (String) require("path");
        }
        /**
         * Extension enabled status.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Runs an extension default action.
         * @param id protocol value
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> triggerAction(String id, String targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            params.put("targetId", CdpObject.json(targetId));
            return client.call("Extensions.triggerAction", params, result_ -> null);
        }
        /**
         * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
         * @param path protocol value
         * @param enableInIncognito protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> loadUnpacked(String path, Optional<Boolean> enableInIncognito) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("path", CdpObject.json(path));
            enableInIncognito.ifPresent(value_ -> params.put("enableInIncognito", value_));
            return client.call("Extensions.loadUnpacked", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("id")));
        }
        /**
         * Installs an unpacked extension from the filesystem similar to --load-extension CLI flags. Returns extension ID once the extension has been installed.
         * @param path protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> loadUnpacked(String path) {
            return loadUnpacked(path, Optional.empty());
        }
        /**
         * Gets a list of all unpacked extensions.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Extensions.ExtensionInfo>> getExtensions() {
            return client.call("Extensions.getExtensions", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("extensions")), element0 -> java.util.Objects.requireNonNull(Extensions.ExtensionInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Uninstalls an unpacked extension (others not supported) from the profile.
         * @param id protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> uninstall(String id) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            return client.call("Extensions.uninstall", params, result_ -> null);
        }
        /**
         * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
         * @param id protocol value
         * @param storageArea protocol value
         * @param keys protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getStorageItems(String id, Extensions.StorageArea storageArea, Optional<java.util.List<String>> keys) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            params.put("storageArea", CdpObject.json(storageArea));
            keys.ifPresent(value_ -> params.put("keys", CdpObject.json(value_)));
            return client.call("Extensions.getStorageItems", params, result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("data")))));
        }
        /**
         * Gets data from extension storage in the given {@code storageArea}. If {@code keys} is specified, these are used to filter the result.
         * @param id protocol value
         * @param storageArea protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getStorageItems(String id, Extensions.StorageArea storageArea) {
            return getStorageItems(id, storageArea, Optional.empty());
        }
        /**
         * Removes {@code keys} from extension storage in the given {@code storageArea}.
         * @param id protocol value
         * @param storageArea protocol value
         * @param keys protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeStorageItems(String id, Extensions.StorageArea storageArea, java.util.List<String> keys) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            params.put("storageArea", CdpObject.json(storageArea));
            params.put("keys", CdpObject.json(keys));
            return client.call("Extensions.removeStorageItems", params, result_ -> null);
        }
        /**
         * Clears extension storage in the given {@code storageArea}.
         * @param id protocol value
         * @param storageArea protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearStorageItems(String id, Extensions.StorageArea storageArea) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            params.put("storageArea", CdpObject.json(storageArea));
            return client.call("Extensions.clearStorageItems", params, result_ -> null);
        }
        /**
         * Sets {@code values} in extension storage in the given {@code storageArea}. The provided {@code values} will be merged with existing values in the storage area.
         * @param id protocol value
         * @param storageArea protocol value
         * @param values protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setStorageItems(String id, Extensions.StorageArea storageArea, java.util.Map<String, Object> values) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            params.put("storageArea", CdpObject.json(storageArea));
            params.put("values", CdpObject.json(values));
            return client.call("Extensions.setStorageItems", params, result_ -> null);
        }
    }
}
