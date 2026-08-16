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
 * This domain allows interacting with the browser to control PWAs.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/PWA.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class PWA {
    private PWA() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * The following types are the replica of https://crsrc.org/c/chrome/browser/web_applications/proto/web_app_os_integration_state.proto;drc=9910d3be894c8f142c977ba1023f30a656bc13fc;l=67
     */
    public static final class FileHandlerAccept extends CdpObject {
        private FileHandlerAccept(Map<String, Object> values) { super(values); }
        @Nullable public static FileHandlerAccept fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FileHandlerAccept(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New name of the mimetype according to https://www.iana.org/assignments/media-types/media-types.xhtml
         * @return the protocol field value
         */
        @Nullable public String mediaType() {
            return (String) value("mediaType");
        }
        /**
         * Returns the fileExtensions field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> fileExtensions() {
            return list(value("fileExtensions"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New name of the mimetype according to https://www.iana.org/assignments/media-types/media-types.xhtml
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mediaType(@Nullable String value) {
                if (value == null) values.remove("mediaType");
                else values.put("mediaType", jsonValue(value));
                return this;
            }
            /**
             * Sets the fileExtensions field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fileExtensions(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("fileExtensions");
                else values.put("fileExtensions", jsonValue(value));
                return this;
            }
            public FileHandlerAccept build() {
                if (!values.containsKey("mediaType")) throw new IllegalStateException("Missing required CDP field: mediaType");
                if (!values.containsKey("fileExtensions")) throw new IllegalStateException("Missing required CDP field: fileExtensions");
                return new FileHandlerAccept(values);
            }
        }
    }
    /**
     */
    public static final class FileHandler extends CdpObject {
        private FileHandler(Map<String, Object> values) { super(values); }
        @Nullable public static FileHandler fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FileHandler(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        @Nullable public String action() {
            return (String) value("action");
        }
        /**
         * Returns the accepts field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<PWA.FileHandlerAccept> accepts() {
            return list(value("accepts"), element0 -> PWA.FileHandlerAccept.fromMap(objectMap(element0)));
        }
        /**
         * Returns the displayName field.
         * @return the protocol field value
         */
        @Nullable public String displayName() {
            return (String) value("displayName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the action field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder action(@Nullable String value) {
                if (value == null) values.remove("action");
                else values.put("action", jsonValue(value));
                return this;
            }
            /**
             * Sets the accepts field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accepts(@Nullable java.util.List<PWA.FileHandlerAccept> value) {
                if (value == null) values.remove("accepts");
                else values.put("accepts", jsonValue(value));
                return this;
            }
            /**
             * Sets the displayName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayName(@Nullable String value) {
                if (value == null) values.remove("displayName");
                else values.put("displayName", jsonValue(value));
                return this;
            }
            public FileHandler build() {
                if (!values.containsKey("action")) throw new IllegalStateException("Missing required CDP field: action");
                if (!values.containsKey("accepts")) throw new IllegalStateException("Missing required CDP field: accepts");
                if (!values.containsKey("displayName")) throw new IllegalStateException("Missing required CDP field: displayName");
                return new FileHandler(values);
            }
        }
    }
    /**
     * If user prefers opening the app in browser or an app window.
     */
    public static final class DisplayMode {
        private DisplayMode() {}
        public static final String STANDALONE = "standalone";
        public static final String BROWSER = "browser";
    }
    /**
     * Returns the following OS state for the given manifest id.
     */
    public static final class GetOsAppStateParams extends CdpObject {
        private GetOsAppStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetOsAppStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOsAppStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id from the webapp&#x27;s manifest file, commonly it&#x27;s the url of the site installing the webapp. See https://web.dev/learn/pwa/web-app-manifest.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id from the webapp&#x27;s manifest file, commonly it&#x27;s the url of the site installing the webapp. See https://web.dev/learn/pwa/web-app-manifest.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            public GetOsAppStateParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                return new GetOsAppStateParams(values);
            }
        }
    }
    /**
     * Returns the following OS state for the given manifest id.
     */
    public static final class GetOsAppStateResult extends CdpObject {
        private GetOsAppStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetOsAppStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOsAppStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the badgeCount field.
         * @return the protocol field value
         */
        @Nullable public Long badgeCount() {
            return numberAsLong(value("badgeCount"));
        }
        /**
         * Returns the fileHandlers field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<PWA.FileHandler> fileHandlers() {
            return list(value("fileHandlers"), element0 -> PWA.FileHandler.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the badgeCount field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder badgeCount(@Nullable Long value) {
                if (value == null) values.remove("badgeCount");
                else values.put("badgeCount", jsonValue(value));
                return this;
            }
            /**
             * Sets the fileHandlers field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fileHandlers(@Nullable java.util.List<PWA.FileHandler> value) {
                if (value == null) values.remove("fileHandlers");
                else values.put("fileHandlers", jsonValue(value));
                return this;
            }
            public GetOsAppStateResult build() {
                if (!values.containsKey("badgeCount")) throw new IllegalStateException("Missing required CDP field: badgeCount");
                if (!values.containsKey("fileHandlers")) throw new IllegalStateException("Missing required CDP field: fileHandlers");
                return new GetOsAppStateResult(values);
            }
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
    public static final class InstallParams extends CdpObject {
        private InstallParams(Map<String, Object> values) { super(values); }
        @Nullable public static InstallParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InstallParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        /**
         * The location of the app or bundle overriding the one derived from the manifestId.
         * @return the protocol field value
         */
        @Nullable public String installUrlOrBundleUrl() {
            return (String) value("installUrlOrBundleUrl");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            /**
             * The location of the app or bundle overriding the one derived from the manifestId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder installUrlOrBundleUrl(@Nullable String value) {
                if (value == null) values.remove("installUrlOrBundleUrl");
                else values.put("installUrlOrBundleUrl", jsonValue(value));
                return this;
            }
            public InstallParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                return new InstallParams(values);
            }
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
    public static final class InstallResult extends CdpObject {
        private InstallResult(Map<String, Object> values) { super(values); }
        @Nullable public static InstallResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InstallResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public InstallResult build() {
                return new InstallResult(values);
            }
        }
    }
    /**
     * Uninstalls the given manifest_id and closes any opened app windows.
     */
    public static final class UninstallParams extends CdpObject {
        private UninstallParams(Map<String, Object> values) { super(values); }
        @Nullable public static UninstallParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UninstallParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            public UninstallParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                return new UninstallParams(values);
            }
        }
    }
    /**
     * Uninstalls the given manifest_id and closes any opened app windows.
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
     * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
     */
    public static final class LaunchParams extends CdpObject {
        private LaunchParams(Map<String, Object> values) { super(values); }
        @Nullable public static LaunchParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LaunchParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public LaunchParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                return new LaunchParams(values);
            }
        }
    }
    /**
     * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
     */
    public static final class LaunchResult extends CdpObject {
        private LaunchResult(Map<String, Object> values) { super(values); }
        @Nullable public static LaunchResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LaunchResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of the tab target created as a result.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of the tab target created as a result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public LaunchResult build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new LaunchResult(values);
            }
        }
    }
    /**
     * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
     * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
     * <p>TODO(crbug.com/339454034): Check the existences of the input files.
     */
    public static final class LaunchFilesInAppParams extends CdpObject {
        private LaunchFilesInAppParams(Map<String, Object> values) { super(values); }
        @Nullable public static LaunchFilesInAppParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LaunchFilesInAppParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        /**
         * Returns the files field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> files() {
            return list(value("files"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the files field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder files(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("files");
                else values.put("files", jsonValue(value));
                return this;
            }
            public LaunchFilesInAppParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                if (!values.containsKey("files")) throw new IllegalStateException("Missing required CDP field: files");
                return new LaunchFilesInAppParams(values);
            }
        }
    }
    /**
     * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
     * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
     * <p>TODO(crbug.com/339454034): Check the existences of the input files.
     */
    public static final class LaunchFilesInAppResult extends CdpObject {
        private LaunchFilesInAppResult(Map<String, Object> values) { super(values); }
        @Nullable public static LaunchFilesInAppResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LaunchFilesInAppResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * IDs of the tab targets created as the result.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> targetIds() {
            return list(value("targetIds"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * IDs of the tab targets created as the result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("targetIds");
                else values.put("targetIds", jsonValue(value));
                return this;
            }
            public LaunchFilesInAppResult build() {
                if (!values.containsKey("targetIds")) throw new IllegalStateException("Missing required CDP field: targetIds");
                return new LaunchFilesInAppResult(values);
            }
        }
    }
    /**
     * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
     */
    public static final class OpenCurrentPageInAppParams extends CdpObject {
        private OpenCurrentPageInAppParams(Map<String, Object> values) { super(values); }
        @Nullable public static OpenCurrentPageInAppParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OpenCurrentPageInAppParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            public OpenCurrentPageInAppParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                return new OpenCurrentPageInAppParams(values);
            }
        }
    }
    /**
     * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
     */
    public static final class OpenCurrentPageInAppResult extends CdpObject {
        private OpenCurrentPageInAppResult(Map<String, Object> values) { super(values); }
        @Nullable public static OpenCurrentPageInAppResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OpenCurrentPageInAppResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public OpenCurrentPageInAppResult build() {
                return new OpenCurrentPageInAppResult(values);
            }
        }
    }
    /**
     * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
     * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
     * <p>See the comment of each parameter.
     */
    public static final class ChangeAppUserSettingsParams extends CdpObject {
        private ChangeAppUserSettingsParams(Map<String, Object> values) { super(values); }
        @Nullable public static ChangeAppUserSettingsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ChangeAppUserSettingsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        /**
         * If user allows the links clicked on by the user in the app&#x27;s scope, or extended scope if the manifest has scope extensions and the flags {@code DesktopPWAsLinkCapturingWithScopeExtensions} and {@code WebAppEnableScopeExtensions} are enabled.
         * <p>Note, the API does not support resetting the linkCapturing to the initial value, uninstalling and installing the web app again will reset it.
         * <p>TODO(crbug.com/339453269): Setting this value on ChromeOS is not supported yet.
         * @return the protocol field value
         */
        @Nullable public Boolean linkCapturing() {
            return (Boolean) value("linkCapturing");
        }
        /**
         * Returns the displayMode field.
         * @return the protocol field value
         */
        @Nullable public String displayMode() {
            return (String) value("displayMode");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            /**
             * If user allows the links clicked on by the user in the app&#x27;s scope, or extended scope if the manifest has scope extensions and the flags {@code DesktopPWAsLinkCapturingWithScopeExtensions} and {@code WebAppEnableScopeExtensions} are enabled.
             * <p>Note, the API does not support resetting the linkCapturing to the initial value, uninstalling and installing the web app again will reset it.
             * <p>TODO(crbug.com/339453269): Setting this value on ChromeOS is not supported yet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder linkCapturing(@Nullable Boolean value) {
                if (value == null) values.remove("linkCapturing");
                else values.put("linkCapturing", jsonValue(value));
                return this;
            }
            /**
             * Sets the displayMode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayMode(@Nullable String value) {
                if (value == null) values.remove("displayMode");
                else values.put("displayMode", jsonValue(value));
                return this;
            }
            public ChangeAppUserSettingsParams build() {
                if (!values.containsKey("manifestId")) throw new IllegalStateException("Missing required CDP field: manifestId");
                return new ChangeAppUserSettingsParams(values);
            }
        }
    }
    /**
     * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
     * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
     * <p>See the comment of each parameter.
     */
    public static final class ChangeAppUserSettingsResult extends CdpObject {
        private ChangeAppUserSettingsResult(Map<String, Object> values) { super(values); }
        @Nullable public static ChangeAppUserSettingsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ChangeAppUserSettingsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ChangeAppUserSettingsResult build() {
                return new ChangeAppUserSettingsResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns the following OS state for the given manifest id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetOsAppStateResult> getOsAppState(GetOsAppStateParams params) {
            return client.call("PWA.getOsAppState", params, GetOsAppStateResult::fromMap);
        }
        /**
         * Installs the given manifest identity, optionally using the given installUrlOrBundleUrl
         * <p>IWA-specific install description: manifestId corresponds to isolated-app:// + web_package::SignedWebBundleId
         * <p>File installation mode: The installUrlOrBundleUrl can be either file:// or http(s):// pointing to a signed web bundle (.swbn). In this case SignedWebBundleId must correspond to The .swbn file&#x27;s signing key.
         * <p>Dev proxy installation mode: installUrlOrBundleUrl must be http(s):// that serves dev mode IWA. web_package::SignedWebBundleId must be of type dev proxy.
         * <p>The advantage of dev proxy mode is that all changes to IWA automatically will be reflected in the running app without reinstallation.
         * <p>To generate bundle id for proxy mode: 1. Generate 32 random bytes. 2. Add a specific suffix at the end following the documentation https://github.com/WICG/isolated-web-apps/blob/main/Scheme.md#suffix 3. Encode the entire sequence using Base32 without padding.
         * <p>If Chrome is not in IWA dev mode, the installation will fail, regardless of the state of the allowlist.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<InstallResult> install(InstallParams params) {
            return client.call("PWA.install", params, InstallResult::fromMap);
        }
        /**
         * Uninstalls the given manifest_id and closes any opened app windows.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UninstallResult> uninstall(UninstallParams params) {
            return client.call("PWA.uninstall", params, UninstallResult::fromMap);
        }
        /**
         * Launches the installed web app, or an url in the same web app instead of the default start url if it is provided. Returns a page Target.TargetID which can be used to attach to via Target.attachToTarget or similar APIs.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LaunchResult> launch(LaunchParams params) {
            return client.call("PWA.launch", params, LaunchResult::fromMap);
        }
        /**
         * Opens one or more local files from an installed web app identified by its manifestId. The web app needs to have file handlers registered to process the files. The API returns one or more page Target.TargetIDs which can be used to attach to via Target.attachToTarget or similar APIs. If some files in the parameters cannot be handled by the web app, they will be ignored. If none of the files can be handled, this API returns an error. If no files are provided as the parameter, this API also returns an error.
         * <p>According to the definition of the file handlers in the manifest file, one Target.TargetID may represent a page handling one or more files. The order of the returned Target.TargetIDs is not guaranteed.
         * <p>TODO(crbug.com/339454034): Check the existences of the input files.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LaunchFilesInAppResult> launchFilesInApp(LaunchFilesInAppParams params) {
            return client.call("PWA.launchFilesInApp", params, LaunchFilesInAppResult::fromMap);
        }
        /**
         * Opens the current page in its web app identified by the manifest id, needs to be called on a page target. This function returns immediately without waiting for the app to finish loading.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<OpenCurrentPageInAppResult> openCurrentPageInApp(OpenCurrentPageInAppParams params) {
            return client.call("PWA.openCurrentPageInApp", params, OpenCurrentPageInAppResult::fromMap);
        }
        /**
         * Changes user settings of the web app identified by its manifestId. If the app was not installed, this command returns an error. Unset parameters will be ignored; unrecognized values will cause an error.
         * <p>Unlike the ones defined in the manifest files of the web apps, these settings are provided by the browser and controlled by the users, they impact the way the browser handling the web apps.
         * <p>See the comment of each parameter.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ChangeAppUserSettingsResult> changeAppUserSettings(ChangeAppUserSettingsParams params) {
            return client.call("PWA.changeAppUserSettings", params, ChangeAppUserSettingsResult::fromMap);
        }
    }
}
