// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * This domain allows interacting with the browser to control PWAs.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/PWA.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class PWA {
    private PWA() {}
    /**
     * The following types are the replica of https://crsrc.org/c/chrome/browser/web_applications/proto/web_app_os_integration_state.proto;drc=9910d3be894c8f142c977ba1023f30a656bc13fc;l=67
     */
    public static final class FileHandlerAccept extends CdpObject {
        public FileHandlerAccept() {}
        private FileHandlerAccept(Map<String, Object> values) { super(values); }
        public static FileHandlerAccept fromMap(Map<String, Object> values) {
            return new FileHandlerAccept(values);
        }
        /**
         * New name of the mimetype according to https://www.iana.org/assignments/media-types/media-types.xhtml
         * @return the protocol field value
         */
        public String mediaType() {
            return (String) require("mediaType");
        }
        /**
         * Returns the fileExtensions field.
         * @return the protocol field value
         */
        public java.util.List<String> fileExtensions() {
            return CdpObject.requireList(require("fileExtensions"), element0 -> (String) element0);
        }
        /**
         * New name of the mimetype according to https://www.iana.org/assignments/media-types/media-types.xhtml
         * @param mediaType field value
         * @return this model
         */
        public FileHandlerAccept mediaType(String mediaType) {
            set("mediaType", mediaType);
            return this;
        }
        /**
         * Sets the fileExtensions field.
         * @param fileExtensions field value
         * @return this model
         */
        public FileHandlerAccept fileExtensions(java.util.List<String> fileExtensions) {
            set("fileExtensions", fileExtensions);
            return this;
        }
    }
    /**
     */
    public static final class FileHandler extends CdpObject {
        public FileHandler() {}
        private FileHandler(Map<String, Object> values) { super(values); }
        public static FileHandler fromMap(Map<String, Object> values) {
            return new FileHandler(values);
        }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        public String action() {
            return (String) require("action");
        }
        /**
         * Returns the accepts field.
         * @return the protocol field value
         */
        public java.util.List<PWA.FileHandlerAccept> accepts() {
            return CdpObject.requireList(require("accepts"), element0 -> java.util.Objects.requireNonNull(PWA.FileHandlerAccept.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Returns the displayName field.
         * @return the protocol field value
         */
        public String displayName() {
            return (String) require("displayName");
        }
        /**
         * Sets the action field.
         * @param action field value
         * @return this model
         */
        public FileHandler action(String action) {
            set("action", action);
            return this;
        }
        /**
         * Sets the accepts field.
         * @param accepts field value
         * @return this model
         */
        public FileHandler accepts(java.util.List<PWA.FileHandlerAccept> accepts) {
            set("accepts", accepts);
            return this;
        }
        /**
         * Sets the displayName field.
         * @param displayName field value
         * @return this model
         */
        public FileHandler displayName(String displayName) {
            set("displayName", displayName);
            return this;
        }
    }
    /**
     * If user prefers opening the app in browser or an app window.
     */
    public enum DisplayMode implements CdpValue<String> {
        STANDALONE("standalone"),
        BROWSER("browser");
        public final String value;
        DisplayMode(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DisplayMode of(@Nonnull String value) {
            for (DisplayMode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DisplayMode value: " + value);
        }
    }
    /**
     * Returns the following OS state for the given manifest id.
     */
    public static final class GetOsAppStateRequest extends CdpObject {
        public GetOsAppStateRequest() {}
        /**
         * Returns the following OS state for the given manifest id.
         * @param manifestId protocol value
         */
        public GetOsAppStateRequest(String manifestId) {
            set("manifestId", manifestId);
        }
        public static GetOsAppStateRequest fromMap(Map<String, Object> values) {
            GetOsAppStateRequest instance_ = new GetOsAppStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id from the webapp&#x27;s manifest file, commonly it&#x27;s the url of the site installing the webapp. See https://web.dev/learn/pwa/web-app-manifest.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * The id from the webapp&#x27;s manifest file, commonly it&#x27;s the url of the site installing the webapp. See https://web.dev/learn/pwa/web-app-manifest.
         * @param manifestId field value
         * @return this model
         */
        public GetOsAppStateRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
    }
    /**
     * Installs the given manifest identity, optionally using the given installUrlOrBundleUrl
     * <p>IWA-specific install description: manifestId corresponds to isolated-app:// + web_package::SignedWebBundleId
     * <p>File installation mode: The installUrlOrBundleUrl can be either file:// or http(s):// pointing to a signed web bundle (.swbn). In this case SignedWebBundleId must correspond to The .swbn file&#x27;s signing key.
     * <p>Dev proxy installation mode: installUrlOrBundleUrl must be http(s):// that serves dev mode IWA. web_package::SignedWebBundleId must be of type dev proxy.
     * <p>The advantage of dev proxy mode is that all changes to IWA automatically will be reflected in the running app without reinstallation.
     * <p>To generate bundle id for proxy mode: 1. Generate 32 random bytes. 2. Add a specific suffix at the end following the documentation https://github.com/WICG/isolated-web-apps/blob/main/Scheme.md#suffix 3. Encode the entire sequence using Base32 without padding.
     * <p>If Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the allowlist.
     */
    public static final class InstallRequest extends CdpObject {
        public InstallRequest() {}
        /**
         * Installs the given manifest identity, optionally using the given installUrlOrBundleUrl
         * <p>IWA-specific install description: manifestId corresponds to isolated-app:// + web_package::SignedWebBundleId
         * <p>File installation mode: The installUrlOrBundleUrl can be either file:// or http(s):// pointing to a signed web bundle (.swbn). In this case SignedWebBundleId must correspond to The .swbn file&#x27;s signing key.
         * <p>Dev proxy installation mode: installUrlOrBundleUrl must be http(s):// that serves dev mode IWA. web_package::SignedWebBundleId must be of type dev proxy.
         * <p>The advantage of dev proxy mode is that all changes to IWA automatically will be reflected in the running app without reinstallation.
         * <p>To generate bundle id for proxy mode: 1. Generate 32 random bytes. 2. Add a specific suffix at the end following the documentation https://github.com/WICG/isolated-web-apps/blob/main/Scheme.md#suffix 3. Encode the entire sequence using Base32 without padding.
         * <p>If Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the allowlist.
         * @param manifestId protocol value
         */
        public InstallRequest(String manifestId) {
            set("manifestId", manifestId);
        }
        public static InstallRequest fromMap(Map<String, Object> values) {
            InstallRequest instance_ = new InstallRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * The location of the app or bundle overriding the one derived from the manifestId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> installUrlOrBundleUrl() {
            return Optional.ofNullable((String) raw("installUrlOrBundleUrl"));
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public InstallRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
        /**
         * The location of the app or bundle overriding the one derived from the manifestId.
         * @param installUrlOrBundleUrl field value; empty omits the value
         * @return this model
         */
        public InstallRequest installUrlOrBundleUrl(Optional<String> installUrlOrBundleUrl) {
            set("installUrlOrBundleUrl", installUrlOrBundleUrl.orElse(null));
            return this;
        }
        /**
         * The location of the app or bundle overriding the one derived from the manifestId.
         * @param installUrlOrBundleUrl field value; null removes the value
         * @return this model
         */
        public InstallRequest installUrlOrBundleUrl(String installUrlOrBundleUrl) {
            set("installUrlOrBundleUrl", installUrlOrBundleUrl);
            return this;
        }
    }
    /**
     * Uninstalls the given manifest_id and closes any opened app windows.
     */
    public static final class UninstallRequest extends CdpObject {
        public UninstallRequest() {}
        /**
         * Uninstalls the given manifest_id and closes any opened app windows.
         * @param manifestId protocol value
         */
        public UninstallRequest(String manifestId) {
            set("manifestId", manifestId);
        }
        public static UninstallRequest fromMap(Map<String, Object> values) {
            UninstallRequest instance_ = new UninstallRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public UninstallRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
    }
    /**
     * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
     */
    public static final class LaunchRequest extends CdpObject {
        public LaunchRequest() {}
        /**
         * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
         * @param manifestId protocol value
         */
        public LaunchRequest(String manifestId) {
            set("manifestId", manifestId);
        }
        public static LaunchRequest fromMap(Map<String, Object> values) {
            LaunchRequest instance_ = new LaunchRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * Returns the url field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return Optional.ofNullable((String) raw("url"));
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public LaunchRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value; empty omits the value
         * @return this model
         */
        public LaunchRequest url(Optional<String> url) {
            set("url", url.orElse(null));
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value; null removes the value
         * @return this model
         */
        public LaunchRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
     * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
     * <p>TODO(crbug.com/339454034): Check the existences of the input files.
     */
    public static final class LaunchFilesInAppRequest extends CdpObject {
        public LaunchFilesInAppRequest() {}
        /**
         * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
         * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
         * <p>TODO(crbug.com/339454034): Check the existences of the input files.
         * @param manifestId protocol value
         * @param files protocol value
         */
        public LaunchFilesInAppRequest(String manifestId, java.util.List<String> files) {
            set("manifestId", manifestId);
            set("files", files);
        }
        public static LaunchFilesInAppRequest fromMap(Map<String, Object> values) {
            LaunchFilesInAppRequest instance_ = new LaunchFilesInAppRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * Returns the files field.
         * @return the protocol field value
         */
        public java.util.List<String> files() {
            return CdpObject.requireList(require("files"), element0 -> (String) element0);
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public LaunchFilesInAppRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
        /**
         * Sets the files field.
         * @param files field value
         * @return this model
         */
        public LaunchFilesInAppRequest files(java.util.List<String> files) {
            set("files", files);
            return this;
        }
    }
    /**
     * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
     */
    public static final class OpenCurrentPageInAppRequest extends CdpObject {
        public OpenCurrentPageInAppRequest() {}
        /**
         * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
         * @param manifestId protocol value
         */
        public OpenCurrentPageInAppRequest(String manifestId) {
            set("manifestId", manifestId);
        }
        public static OpenCurrentPageInAppRequest fromMap(Map<String, Object> values) {
            OpenCurrentPageInAppRequest instance_ = new OpenCurrentPageInAppRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public OpenCurrentPageInAppRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
    }
    /**
     * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
     * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
     * <p>See the comment of each parameter.
     */
    public static final class ChangeAppUserSettingsRequest extends CdpObject {
        public ChangeAppUserSettingsRequest() {}
        /**
         * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
         * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
         * <p>See the comment of each parameter.
         * @param manifestId protocol value
         */
        public ChangeAppUserSettingsRequest(String manifestId) {
            set("manifestId", manifestId);
        }
        public static ChangeAppUserSettingsRequest fromMap(Map<String, Object> values) {
            ChangeAppUserSettingsRequest instance_ = new ChangeAppUserSettingsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        public String manifestId() {
            return (String) require("manifestId");
        }
        /**
         * If user allows the links clicked on by the user in the app&#x27;s scope, or extended scope if the manifest has scope extensions and the flags {@code DesktopPWAsLinkCapturingWithScopeExtensions} and {@code WebAppEnableScopeExtensions} are enabled.
         * <p>Note, the API does not support resetting the linkCapturing to the initial value, uninstalling and installing the web app again will reset it.
         * <p>TODO(crbug.com/339453269): Setting this value on ChromeOS is not supported yet.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> linkCapturing() {
            return Optional.ofNullable((Boolean) raw("linkCapturing"));
        }
        /**
         * Returns the displayMode field.
         * @return the protocol field value, empty when absent
         */
        public Optional<PWA.DisplayMode> displayMode() {
            return Optional.ofNullable(raw("displayMode") == null ? null : PWA.DisplayMode.of((String) raw("displayMode")));
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public ChangeAppUserSettingsRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
        /**
         * If user allows the links clicked on by the user in the app&#x27;s scope, or extended scope if the manifest has scope extensions and the flags {@code DesktopPWAsLinkCapturingWithScopeExtensions} and {@code WebAppEnableScopeExtensions} are enabled.
         * <p>Note, the API does not support resetting the linkCapturing to the initial value, uninstalling and installing the web app again will reset it.
         * <p>TODO(crbug.com/339453269): Setting this value on ChromeOS is not supported yet.
         * @param linkCapturing field value; empty omits the value
         * @return this model
         */
        public ChangeAppUserSettingsRequest linkCapturing(Optional<Boolean> linkCapturing) {
            set("linkCapturing", linkCapturing.orElse(null));
            return this;
        }
        /**
         * If user allows the links clicked on by the user in the app&#x27;s scope, or extended scope if the manifest has scope extensions and the flags {@code DesktopPWAsLinkCapturingWithScopeExtensions} and {@code WebAppEnableScopeExtensions} are enabled.
         * <p>Note, the API does not support resetting the linkCapturing to the initial value, uninstalling and installing the web app again will reset it.
         * <p>TODO(crbug.com/339453269): Setting this value on ChromeOS is not supported yet.
         * @param linkCapturing field value; null removes the value
         * @return this model
         */
        public ChangeAppUserSettingsRequest linkCapturing(Boolean linkCapturing) {
            set("linkCapturing", linkCapturing);
            return this;
        }
        /**
         * Sets the displayMode field.
         * @param displayMode field value; empty omits the value
         * @return this model
         */
        public ChangeAppUserSettingsRequest displayMode(Optional<PWA.DisplayMode> displayMode) {
            set("displayMode", displayMode.orElse(null));
            return this;
        }
        /**
         * Sets the displayMode field.
         * @param displayMode field value; null removes the value
         * @return this model
         */
        public ChangeAppUserSettingsRequest displayMode(PWA.DisplayMode displayMode) {
            set("displayMode", displayMode);
            return this;
        }
    }
    /**
     * Returns the following OS state for the given manifest id.
     */
    public static final class GetOsAppStateResult extends CdpObject {
        public GetOsAppStateResult() {}
        private GetOsAppStateResult(Map<String, Object> values) { super(values); }
        public static GetOsAppStateResult fromMap(Map<String, Object> values) {
            return new GetOsAppStateResult(values);
        }
        /**
         * Returns the badgeCount field.
         * @return the protocol field value
         */
        public long badgeCount() {
            return ((Number) require("badgeCount")).longValue();
        }
        /**
         * Returns the fileHandlers field.
         * @return the protocol field value
         */
        public java.util.List<PWA.FileHandler> fileHandlers() {
            return CdpObject.requireList(require("fileHandlers"), element0 -> java.util.Objects.requireNonNull(PWA.FileHandler.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the badgeCount field.
         * @param badgeCount field value
         * @return this model
         */
        public GetOsAppStateResult badgeCount(long badgeCount) {
            set("badgeCount", badgeCount);
            return this;
        }
        /**
         * Sets the fileHandlers field.
         * @param fileHandlers field value
         * @return this model
         */
        public GetOsAppStateResult fileHandlers(java.util.List<PWA.FileHandler> fileHandlers) {
            set("fileHandlers", fileHandlers);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns the following OS state for the given manifest id.
         * @param manifestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetOsAppStateResult> getOsAppState(String manifestId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            return client.call("PWA.getOsAppState", params, result_ -> new GetOsAppStateResult(result_));
        }
        /**
         * Returns the following OS state for the given manifest id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetOsAppStateResult> getOsAppState(GetOsAppStateRequest request) {
            return client.call("PWA.getOsAppState", request == null ? null : request.toMap(), result_ -> new GetOsAppStateResult(result_));
        }
        /**
         * Installs the given manifest identity, optionally using the given installUrlOrBundleUrl
         * <p>IWA-specific install description: manifestId corresponds to isolated-app:// + web_package::SignedWebBundleId
         * <p>File installation mode: The installUrlOrBundleUrl can be either file:// or http(s):// pointing to a signed web bundle (.swbn). In this case SignedWebBundleId must correspond to The .swbn file&#x27;s signing key.
         * <p>Dev proxy installation mode: installUrlOrBundleUrl must be http(s):// that serves dev mode IWA. web_package::SignedWebBundleId must be of type dev proxy.
         * <p>The advantage of dev proxy mode is that all changes to IWA automatically will be reflected in the running app without reinstallation.
         * <p>To generate bundle id for proxy mode: 1. Generate 32 random bytes. 2. Add a specific suffix at the end following the documentation https://github.com/WICG/isolated-web-apps/blob/main/Scheme.md#suffix 3. Encode the entire sequence using Base32 without padding.
         * <p>If Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the allowlist.
         * @param manifestId protocol value
         * @param installUrlOrBundleUrl protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> install(String manifestId, Optional<String> installUrlOrBundleUrl) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            installUrlOrBundleUrl.ifPresent(value_ -> params.put("installUrlOrBundleUrl", CdpObject.json(value_)));
            return client.call("PWA.install", params, result_ -> null);
        }
        /**
         * Installs the given manifest identity, optionally using the given installUrlOrBundleUrl
         * <p>IWA-specific install description: manifestId corresponds to isolated-app:// + web_package::SignedWebBundleId
         * <p>File installation mode: The installUrlOrBundleUrl can be either file:// or http(s):// pointing to a signed web bundle (.swbn). In this case SignedWebBundleId must correspond to The .swbn file&#x27;s signing key.
         * <p>Dev proxy installation mode: installUrlOrBundleUrl must be http(s):// that serves dev mode IWA. web_package::SignedWebBundleId must be of type dev proxy.
         * <p>The advantage of dev proxy mode is that all changes to IWA automatically will be reflected in the running app without reinstallation.
         * <p>To generate bundle id for proxy mode: 1. Generate 32 random bytes. 2. Add a specific suffix at the end following the documentation https://github.com/WICG/isolated-web-apps/blob/main/Scheme.md#suffix 3. Encode the entire sequence using Base32 without padding.
         * <p>If Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the allowlist.
         * @param manifestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> install(String manifestId) {
            return install(manifestId, Optional.empty());
        }
        /**
         * Installs the given manifest identity, optionally using the given installUrlOrBundleUrl
         * <p>IWA-specific install description: manifestId corresponds to isolated-app:// + web_package::SignedWebBundleId
         * <p>File installation mode: The installUrlOrBundleUrl can be either file:// or http(s):// pointing to a signed web bundle (.swbn). In this case SignedWebBundleId must correspond to The .swbn file&#x27;s signing key.
         * <p>Dev proxy installation mode: installUrlOrBundleUrl must be http(s):// that serves dev mode IWA. web_package::SignedWebBundleId must be of type dev proxy.
         * <p>The advantage of dev proxy mode is that all changes to IWA automatically will be reflected in the running app without reinstallation.
         * <p>To generate bundle id for proxy mode: 1. Generate 32 random bytes. 2. Add a specific suffix at the end following the documentation https://github.com/WICG/isolated-web-apps/blob/main/Scheme.md#suffix 3. Encode the entire sequence using Base32 without padding.
         * <p>If Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the allowlist.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> install(InstallRequest request) {
            return client.call("PWA.install", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Uninstalls the given manifest_id and closes any opened app windows.
         * @param manifestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> uninstall(String manifestId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            return client.call("PWA.uninstall", params, result_ -> null);
        }
        /**
         * Uninstalls the given manifest_id and closes any opened app windows.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> uninstall(UninstallRequest request) {
            return client.call("PWA.uninstall", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
         * @param manifestId protocol value
         * @param url protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> launch(String manifestId, Optional<String> url) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            url.ifPresent(value_ -> params.put("url", CdpObject.json(value_)));
            return client.call("PWA.launch", params, result_ -> new Target.TargetID((String) java.util.Objects.requireNonNull(result_.get("targetId"))));
        }
        /**
         * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
         * @param manifestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> launch(String manifestId) {
            return launch(manifestId, Optional.empty());
        }
        /**
         * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> launch(LaunchRequest request) {
            return client.call("PWA.launch", request == null ? null : request.toMap(), result_ -> new Target.TargetID((String) java.util.Objects.requireNonNull(result_.get("targetId"))));
        }
        /**
         * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
         * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
         * <p>TODO(crbug.com/339454034): Check the existences of the input files.
         * @param manifestId protocol value
         * @param files protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetID>> launchFilesInApp(String manifestId, java.util.List<String> files) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            params.put("files", CdpObject.json(files));
            return client.call("PWA.launchFilesInApp", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("targetIds")), element0 -> new Target.TargetID((String) element0)));
        }
        /**
         * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
         * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
         * <p>TODO(crbug.com/339454034): Check the existences of the input files.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetID>> launchFilesInApp(LaunchFilesInAppRequest request) {
            return client.call("PWA.launchFilesInApp", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("targetIds")), element0 -> new Target.TargetID((String) element0)));
        }
        /**
         * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
         * @param manifestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> openCurrentPageInApp(String manifestId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            return client.call("PWA.openCurrentPageInApp", params, result_ -> null);
        }
        /**
         * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> openCurrentPageInApp(OpenCurrentPageInAppRequest request) {
            return client.call("PWA.openCurrentPageInApp", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
         * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
         * <p>See the comment of each parameter.
         * @param manifestId protocol value
         * @param linkCapturing protocol value
         * @param displayMode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> changeAppUserSettings(String manifestId, Optional<Boolean> linkCapturing, Optional<PWA.DisplayMode> displayMode) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            linkCapturing.ifPresent(value_ -> params.put("linkCapturing", value_));
            displayMode.ifPresent(value_ -> params.put("displayMode", CdpObject.json(value_)));
            return client.call("PWA.changeAppUserSettings", params, result_ -> null);
        }
        /**
         * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
         * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
         * <p>See the comment of each parameter.
         * @param manifestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> changeAppUserSettings(String manifestId) {
            return changeAppUserSettings(manifestId, Optional.empty(), Optional.empty());
        }
        /**
         * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
         * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
         * <p>See the comment of each parameter.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> changeAppUserSettings(ChangeAppUserSettingsRequest request) {
            return client.call("PWA.changeAppUserSettings", request == null ? null : request.toMap(), result_ -> null);
        }
    }
}
