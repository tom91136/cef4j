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
 * The Browser domain defines methods and events for browser managing.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Browser.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Browser {
    private Browser() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * The state of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WindowState {
        private WindowState() {}
        public static final String NORMAL = "normal";
        public static final String MINIMIZED = "minimized";
        public static final String MAXIMIZED = "maximized";
        public static final String FULLSCREEN = "fullscreen";
    }
    /**
     * Browser window bounds information
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Bounds extends CdpObject {
        private Bounds(Map<String, Object> values) { super(values); }
        @Nullable public static Bounds fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Bounds(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The offset from the left edge of the screen to the window in pixels.
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * The offset from the top edge of the screen to the window in pixels.
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * The window width in pixels.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * The window height in pixels.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * The window state. Default to normal.
         * @return the protocol field value
         */
        @Nullable public String windowState() {
            return (String) value("windowState");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The offset from the left edge of the screen to the window in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * The offset from the top edge of the screen to the window in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * The window width in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * The window height in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * The window state. Default to normal.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowState(@Nullable String value) {
                if (value == null) values.remove("windowState");
                else values.put("windowState", jsonValue(value));
                return this;
            }
            public Bounds build() {
                return new Bounds(values);
            }
        }
    }
    /**
     * Wire values for PermissionType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionType {
        private PermissionType() {}
        public static final String AR = "ar";
        public static final String AUDIOCAPTURE = "audioCapture";
        public static final String AUTOMATICFULLSCREEN = "automaticFullscreen";
        public static final String BACKGROUNDFETCH = "backgroundFetch";
        public static final String BACKGROUNDSYNC = "backgroundSync";
        public static final String CAMERAPANTILTZOOM = "cameraPanTiltZoom";
        public static final String CAPTUREDSURFACECONTROL = "capturedSurfaceControl";
        public static final String CLIPBOARDREADWRITE = "clipboardReadWrite";
        public static final String CLIPBOARDSANITIZEDWRITE = "clipboardSanitizedWrite";
        public static final String DISPLAYCAPTURE = "displayCapture";
        public static final String DURABLESTORAGE = "durableStorage";
        public static final String GEOLOCATION = "geolocation";
        public static final String HANDTRACKING = "handTracking";
        public static final String IDLEDETECTION = "idleDetection";
        public static final String KEYBOARDLOCK = "keyboardLock";
        public static final String LOCALFONTS = "localFonts";
        public static final String LOCALNETWORK = "localNetwork";
        public static final String LOCALNETWORKACCESS = "localNetworkAccess";
        public static final String LOOPBACKNETWORK = "loopbackNetwork";
        public static final String MIDI = "midi";
        public static final String MIDISYSEX = "midiSysex";
        public static final String NFC = "nfc";
        public static final String NOTIFICATIONS = "notifications";
        public static final String PAYMENTHANDLER = "paymentHandler";
        public static final String PERIODICBACKGROUNDSYNC = "periodicBackgroundSync";
        public static final String POINTERLOCK = "pointerLock";
        public static final String PROTECTEDMEDIAIDENTIFIER = "protectedMediaIdentifier";
        public static final String SENSORS = "sensors";
        public static final String SMARTCARD = "smartCard";
        public static final String SPEAKERSELECTION = "speakerSelection";
        public static final String STORAGEACCESS = "storageAccess";
        public static final String TOPLEVELSTORAGEACCESS = "topLevelStorageAccess";
        public static final String VIDEOCAPTURE = "videoCapture";
        public static final String VR = "vr";
        public static final String WAKELOCKSCREEN = "wakeLockScreen";
        public static final String WAKELOCKSYSTEM = "wakeLockSystem";
        public static final String WEBAPPINSTALLATION = "webAppInstallation";
        public static final String WEBPRINTING = "webPrinting";
        public static final String WINDOWMANAGEMENT = "windowManagement";
    }
    /**
     * Wire values for PermissionSetting.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionSetting {
        private PermissionSetting() {}
        public static final String GRANTED = "granted";
        public static final String DENIED = "denied";
        public static final String PROMPT = "prompt";
    }
    /**
     * Definition of PermissionDescriptor defined in the Permissions API: https://w3c.github.io/permissions/#dom-permissiondescriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionDescriptor extends CdpObject {
        private PermissionDescriptor(Map<String, Object> values) { super(values); }
        @Nullable public static PermissionDescriptor fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PermissionDescriptor(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of permission. See https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl for valid permission names.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * For &quot;midi&quot; permission, may also specify sysex control.
         * @return the protocol field value
         */
        @Nullable public Boolean sysex() {
            return (Boolean) value("sysex");
        }
        /**
         * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
         * @return the protocol field value
         */
        @Nullable public Boolean userVisibleOnly() {
            return (Boolean) value("userVisibleOnly");
        }
        /**
         * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
         * @return the protocol field value
         */
        @Nullable public Boolean allowWithoutSanitization() {
            return (Boolean) value("allowWithoutSanitization");
        }
        /**
         * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
         * @return the protocol field value
         */
        @Nullable public Boolean allowWithoutGesture() {
            return (Boolean) value("allowWithoutGesture");
        }
        /**
         * For &quot;camera&quot; permission, may specify panTiltZoom.
         * @return the protocol field value
         */
        @Nullable public Boolean panTiltZoom() {
            return (Boolean) value("panTiltZoom");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of permission. See https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl for valid permission names.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * For &quot;midi&quot; permission, may also specify sysex control.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sysex(@Nullable Boolean value) {
                if (value == null) values.remove("sysex");
                else values.put("sysex", jsonValue(value));
                return this;
            }
            /**
             * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userVisibleOnly(@Nullable Boolean value) {
                if (value == null) values.remove("userVisibleOnly");
                else values.put("userVisibleOnly", jsonValue(value));
                return this;
            }
            /**
             * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowWithoutSanitization(@Nullable Boolean value) {
                if (value == null) values.remove("allowWithoutSanitization");
                else values.put("allowWithoutSanitization", jsonValue(value));
                return this;
            }
            /**
             * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowWithoutGesture(@Nullable Boolean value) {
                if (value == null) values.remove("allowWithoutGesture");
                else values.put("allowWithoutGesture", jsonValue(value));
                return this;
            }
            /**
             * For &quot;camera&quot; permission, may specify panTiltZoom.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder panTiltZoom(@Nullable Boolean value) {
                if (value == null) values.remove("panTiltZoom");
                else values.put("panTiltZoom", jsonValue(value));
                return this;
            }
            public PermissionDescriptor build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new PermissionDescriptor(values);
            }
        }
    }
    /**
     * Browser command ids used by executeBrowserCommand.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BrowserCommandId {
        private BrowserCommandId() {}
        public static final String OPENTABSEARCH = "openTabSearch";
        public static final String CLOSETABSEARCH = "closeTabSearch";
        public static final String OPENGLIC = "openGlic";
    }
    /**
     * Chrome histogram bucket.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Bucket extends CdpObject {
        private Bucket(Map<String, Object> values) { super(values); }
        @Nullable public static Bucket fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Bucket(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Minimum value (inclusive).
         * @return the protocol field value
         */
        @Nullable public Long low() {
            return numberAsLong(value("low"));
        }
        /**
         * Maximum value (exclusive).
         * @return the protocol field value
         */
        @Nullable public Long high() {
            return numberAsLong(value("high"));
        }
        /**
         * Number of samples.
         * @return the protocol field value
         */
        @Nullable public Long count() {
            return numberAsLong(value("count"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Minimum value (inclusive).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder low(@Nullable Long value) {
                if (value == null) values.remove("low");
                else values.put("low", jsonValue(value));
                return this;
            }
            /**
             * Maximum value (exclusive).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder high(@Nullable Long value) {
                if (value == null) values.remove("high");
                else values.put("high", jsonValue(value));
                return this;
            }
            /**
             * Number of samples.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder count(@Nullable Long value) {
                if (value == null) values.remove("count");
                else values.put("count", jsonValue(value));
                return this;
            }
            public Bucket build() {
                if (!values.containsKey("low")) throw new IllegalStateException("Missing required CDP field: low");
                if (!values.containsKey("high")) throw new IllegalStateException("Missing required CDP field: high");
                if (!values.containsKey("count")) throw new IllegalStateException("Missing required CDP field: count");
                return new Bucket(values);
            }
        }
    }
    /**
     * Chrome histogram.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Histogram extends CdpObject {
        private Histogram(Map<String, Object> values) { super(values); }
        @Nullable public static Histogram fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Histogram(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Sum of sample values.
         * @return the protocol field value
         */
        @Nullable public Long sum() {
            return numberAsLong(value("sum"));
        }
        /**
         * Total number of samples.
         * @return the protocol field value
         */
        @Nullable public Long count() {
            return numberAsLong(value("count"));
        }
        /**
         * Buckets.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Browser.Bucket> buckets() {
            return list(value("buckets"), element0 -> Browser.Bucket.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Sum of sample values.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sum(@Nullable Long value) {
                if (value == null) values.remove("sum");
                else values.put("sum", jsonValue(value));
                return this;
            }
            /**
             * Total number of samples.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder count(@Nullable Long value) {
                if (value == null) values.remove("count");
                else values.put("count", jsonValue(value));
                return this;
            }
            /**
             * Buckets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder buckets(@Nullable java.util.List<Browser.Bucket> value) {
                if (value == null) values.remove("buckets");
                else values.put("buckets", jsonValue(value));
                return this;
            }
            public Histogram build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("sum")) throw new IllegalStateException("Missing required CDP field: sum");
                if (!values.containsKey("count")) throw new IllegalStateException("Missing required CDP field: count");
                if (!values.containsKey("buckets")) throw new IllegalStateException("Missing required CDP field: buckets");
                return new Histogram(values);
            }
        }
    }
    /**
     * Wire values for PrivacySandboxAPI.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PrivacySandboxAPI {
        private PrivacySandboxAPI() {}
        public static final String BIDDINGANDAUCTIONSERVICES = "BiddingAndAuctionServices";
        public static final String TRUSTEDKEYVALUE = "TrustedKeyValue";
    }
    /**
     * Set permission settings for given embedding and embedded origins.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPermissionParams extends CdpObject {
        private SetPermissionParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPermissionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPermissionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Descriptor of permission to override.
         * @return the protocol field value
         */
        @Nullable public Browser.PermissionDescriptor permission() {
            return Browser.PermissionDescriptor.fromMap(objectMap(value("permission")));
        }
        /**
         * Setting of the permission.
         * @return the protocol field value
         */
        @Nullable public String setting() {
            return (String) value("setting");
        }
        /**
         * Embedding origin the permission applies to, all origins if not specified.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
         * @return the protocol field value
         */
        @Nullable public String embeddedOrigin() {
            return (String) value("embeddedOrigin");
        }
        /**
         * Context to override. When omitted, default browser context is used.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Descriptor of permission to override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder permission(@Nullable Browser.PermissionDescriptor value) {
                if (value == null) values.remove("permission");
                else values.put("permission", jsonValue(value));
                return this;
            }
            /**
             * Setting of the permission.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder setting(@Nullable String value) {
                if (value == null) values.remove("setting");
                else values.put("setting", jsonValue(value));
                return this;
            }
            /**
             * Embedding origin the permission applies to, all origins if not specified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder embeddedOrigin(@Nullable String value) {
                if (value == null) values.remove("embeddedOrigin");
                else values.put("embeddedOrigin", jsonValue(value));
                return this;
            }
            /**
             * Context to override. When omitted, default browser context is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public SetPermissionParams build() {
                if (!values.containsKey("permission")) throw new IllegalStateException("Missing required CDP field: permission");
                if (!values.containsKey("setting")) throw new IllegalStateException("Missing required CDP field: setting");
                return new SetPermissionParams(values);
            }
        }
    }
    /**
     * Set permission settings for given embedding and embedded origins.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPermissionResult extends CdpObject {
        private SetPermissionResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPermissionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPermissionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPermissionResult build() {
                return new SetPermissionResult(values);
            }
        }
    }
    /**
     * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GrantPermissionsParams extends CdpObject {
        private GrantPermissionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GrantPermissionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GrantPermissionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the permissions field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> permissions() {
            return list(value("permissions"), element0 -> (String) element0);
        }
        /**
         * Origin the permission applies to, all origins if not specified.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * BrowserContext to override permissions. When omitted, default browser context is used.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the permissions field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder permissions(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("permissions");
                else values.put("permissions", jsonValue(value));
                return this;
            }
            /**
             * Origin the permission applies to, all origins if not specified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * BrowserContext to override permissions. When omitted, default browser context is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public GrantPermissionsParams build() {
                if (!values.containsKey("permissions")) throw new IllegalStateException("Missing required CDP field: permissions");
                return new GrantPermissionsParams(values);
            }
        }
    }
    /**
     * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GrantPermissionsResult extends CdpObject {
        private GrantPermissionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GrantPermissionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GrantPermissionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GrantPermissionsResult build() {
                return new GrantPermissionsResult(values);
            }
        }
    }
    /**
     * Reset all permission management for all origins.
     */
    public static final class ResetPermissionsParams extends CdpObject {
        private ResetPermissionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResetPermissionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetPermissionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * BrowserContext to reset permissions. When omitted, default browser context is used.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * BrowserContext to reset permissions. When omitted, default browser context is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public ResetPermissionsParams build() {
                return new ResetPermissionsParams(values);
            }
        }
    }
    /**
     * Reset all permission management for all origins.
     */
    public static final class ResetPermissionsResult extends CdpObject {
        private ResetPermissionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResetPermissionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetPermissionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetPermissionsResult build() {
                return new ResetPermissionsResult(values);
            }
        }
    }
    /**
     * Set the behavior when downloading a file.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDownloadBehaviorParams extends CdpObject {
        private SetDownloadBehaviorParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDownloadBehaviorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDownloadBehaviorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
         * @return the protocol field value
         */
        @Nullable public String behavior() {
            return (String) value("behavior");
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
         */
        public static final class BehaviorValues {
            private BehaviorValues() {}
            public static final String DENY = "deny";
            public static final String ALLOW = "allow";
            public static final String ALLOWANDNAME = "allowAndName";
            public static final String DEFAULT = "default";
        }
        /**
         * BrowserContext to set download behavior. When omitted, default browser context is used.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
         * @return the protocol field value
         */
        @Nullable public String downloadPath() {
            return (String) value("downloadPath");
        }
        /**
         * Whether to emit download events (defaults to false).
         * @return the protocol field value
         */
        @Nullable public Boolean eventsEnabled() {
            return (Boolean) value("eventsEnabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder behavior(@Nullable String value) {
                if (value == null) values.remove("behavior");
                else values.put("behavior", jsonValue(value));
                return this;
            }
            /**
             * BrowserContext to set download behavior. When omitted, default browser context is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            /**
             * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder downloadPath(@Nullable String value) {
                if (value == null) values.remove("downloadPath");
                else values.put("downloadPath", jsonValue(value));
                return this;
            }
            /**
             * Whether to emit download events (defaults to false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventsEnabled(@Nullable Boolean value) {
                if (value == null) values.remove("eventsEnabled");
                else values.put("eventsEnabled", jsonValue(value));
                return this;
            }
            public SetDownloadBehaviorParams build() {
                if (!values.containsKey("behavior")) throw new IllegalStateException("Missing required CDP field: behavior");
                return new SetDownloadBehaviorParams(values);
            }
        }
    }
    /**
     * Set the behavior when downloading a file.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDownloadBehaviorResult extends CdpObject {
        private SetDownloadBehaviorResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDownloadBehaviorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDownloadBehaviorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDownloadBehaviorResult build() {
                return new SetDownloadBehaviorResult(values);
            }
        }
    }
    /**
     * Cancel a download if in progress
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CancelDownloadParams extends CdpObject {
        private CancelDownloadParams(Map<String, Object> values) { super(values); }
        @Nullable public static CancelDownloadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelDownloadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        @Nullable public String guid() {
            return (String) value("guid");
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Global unique identifier of the download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder guid(@Nullable String value) {
                if (value == null) values.remove("guid");
                else values.put("guid", jsonValue(value));
                return this;
            }
            /**
             * BrowserContext to perform the action in. When omitted, default browser context is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public CancelDownloadParams build() {
                if (!values.containsKey("guid")) throw new IllegalStateException("Missing required CDP field: guid");
                return new CancelDownloadParams(values);
            }
        }
    }
    /**
     * Cancel a download if in progress
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CancelDownloadResult extends CdpObject {
        private CancelDownloadResult(Map<String, Object> values) { super(values); }
        @Nullable public static CancelDownloadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelDownloadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CancelDownloadResult build() {
                return new CancelDownloadResult(values);
            }
        }
    }
    /**
     * Close browser gracefully.
     */
    public static final class CloseParams extends CdpObject {
        private CloseParams(Map<String, Object> values) { super(values); }
        @Nullable public static CloseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CloseParams build() {
                return new CloseParams(values);
            }
        }
    }
    /**
     * Close browser gracefully.
     */
    public static final class CloseResult extends CdpObject {
        private CloseResult(Map<String, Object> values) { super(values); }
        @Nullable public static CloseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CloseResult build() {
                return new CloseResult(values);
            }
        }
    }
    /**
     * Crashes browser on the main thread.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrashParams extends CdpObject {
        private CrashParams(Map<String, Object> values) { super(values); }
        @Nullable public static CrashParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CrashParams build() {
                return new CrashParams(values);
            }
        }
    }
    /**
     * Crashes browser on the main thread.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrashResult extends CdpObject {
        private CrashResult(Map<String, Object> values) { super(values); }
        @Nullable public static CrashResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CrashResult build() {
                return new CrashResult(values);
            }
        }
    }
    /**
     * Crashes GPU process.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrashGpuProcessParams extends CdpObject {
        private CrashGpuProcessParams(Map<String, Object> values) { super(values); }
        @Nullable public static CrashGpuProcessParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashGpuProcessParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CrashGpuProcessParams build() {
                return new CrashGpuProcessParams(values);
            }
        }
    }
    /**
     * Crashes GPU process.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrashGpuProcessResult extends CdpObject {
        private CrashGpuProcessResult(Map<String, Object> values) { super(values); }
        @Nullable public static CrashGpuProcessResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashGpuProcessResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CrashGpuProcessResult build() {
                return new CrashGpuProcessResult(values);
            }
        }
    }
    /**
     * Returns version information.
     */
    public static final class GetVersionParams extends CdpObject {
        private GetVersionParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetVersionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetVersionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetVersionParams build() {
                return new GetVersionParams(values);
            }
        }
    }
    /**
     * Returns version information.
     */
    public static final class GetVersionResult extends CdpObject {
        private GetVersionResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetVersionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetVersionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Protocol version.
         * @return the protocol field value
         */
        @Nullable public String protocolVersion() {
            return (String) value("protocolVersion");
        }
        /**
         * Product name.
         * @return the protocol field value
         */
        @Nullable public String product() {
            return (String) value("product");
        }
        /**
         * Product revision.
         * @return the protocol field value
         */
        @Nullable public String revision() {
            return (String) value("revision");
        }
        /**
         * User-Agent.
         * @return the protocol field value
         */
        @Nullable public String userAgent() {
            return (String) value("userAgent");
        }
        /**
         * V8 version.
         * @return the protocol field value
         */
        @Nullable public String jsVersion() {
            return (String) value("jsVersion");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Protocol version.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocolVersion(@Nullable String value) {
                if (value == null) values.remove("protocolVersion");
                else values.put("protocolVersion", jsonValue(value));
                return this;
            }
            /**
             * Product name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder product(@Nullable String value) {
                if (value == null) values.remove("product");
                else values.put("product", jsonValue(value));
                return this;
            }
            /**
             * Product revision.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder revision(@Nullable String value) {
                if (value == null) values.remove("revision");
                else values.put("revision", jsonValue(value));
                return this;
            }
            /**
             * User-Agent.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userAgent(@Nullable String value) {
                if (value == null) values.remove("userAgent");
                else values.put("userAgent", jsonValue(value));
                return this;
            }
            /**
             * V8 version.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder jsVersion(@Nullable String value) {
                if (value == null) values.remove("jsVersion");
                else values.put("jsVersion", jsonValue(value));
                return this;
            }
            public GetVersionResult build() {
                if (!values.containsKey("protocolVersion")) throw new IllegalStateException("Missing required CDP field: protocolVersion");
                if (!values.containsKey("product")) throw new IllegalStateException("Missing required CDP field: product");
                if (!values.containsKey("revision")) throw new IllegalStateException("Missing required CDP field: revision");
                if (!values.containsKey("userAgent")) throw new IllegalStateException("Missing required CDP field: userAgent");
                if (!values.containsKey("jsVersion")) throw new IllegalStateException("Missing required CDP field: jsVersion");
                return new GetVersionResult(values);
            }
        }
    }
    /**
     * Returns the command line switches for the browser process if, and only if --enable-automation is on the commandline.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetBrowserCommandLineParams extends CdpObject {
        private GetBrowserCommandLineParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetBrowserCommandLineParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBrowserCommandLineParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetBrowserCommandLineParams build() {
                return new GetBrowserCommandLineParams(values);
            }
        }
    }
    /**
     * Returns the command line switches for the browser process if, and only if --enable-automation is on the commandline.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetBrowserCommandLineResult extends CdpObject {
        private GetBrowserCommandLineResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetBrowserCommandLineResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBrowserCommandLineResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Commandline parameters
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> arguments() {
            return list(value("arguments"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Commandline parameters
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder arguments(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("arguments");
                else values.put("arguments", jsonValue(value));
                return this;
            }
            public GetBrowserCommandLineResult build() {
                if (!values.containsKey("arguments")) throw new IllegalStateException("Missing required CDP field: arguments");
                return new GetBrowserCommandLineResult(values);
            }
        }
    }
    /**
     * Get Chrome histograms.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHistogramsParams extends CdpObject {
        private GetHistogramsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetHistogramsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHistogramsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
         * @return the protocol field value
         */
        @Nullable public String query() {
            return (String) value("query");
        }
        /**
         * If true, retrieve delta since last delta call.
         * @return the protocol field value
         */
        @Nullable public Boolean delta() {
            return (Boolean) value("delta");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder query(@Nullable String value) {
                if (value == null) values.remove("query");
                else values.put("query", jsonValue(value));
                return this;
            }
            /**
             * If true, retrieve delta since last delta call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder delta(@Nullable Boolean value) {
                if (value == null) values.remove("delta");
                else values.put("delta", jsonValue(value));
                return this;
            }
            public GetHistogramsParams build() {
                return new GetHistogramsParams(values);
            }
        }
    }
    /**
     * Get Chrome histograms.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHistogramsResult extends CdpObject {
        private GetHistogramsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetHistogramsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHistogramsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Histograms.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Browser.Histogram> histograms() {
            return list(value("histograms"), element0 -> Browser.Histogram.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Histograms.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder histograms(@Nullable java.util.List<Browser.Histogram> value) {
                if (value == null) values.remove("histograms");
                else values.put("histograms", jsonValue(value));
                return this;
            }
            public GetHistogramsResult build() {
                if (!values.containsKey("histograms")) throw new IllegalStateException("Missing required CDP field: histograms");
                return new GetHistogramsResult(values);
            }
        }
    }
    /**
     * Get a Chrome histogram by name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHistogramParams extends CdpObject {
        private GetHistogramParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetHistogramParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHistogramParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Requested histogram name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * If true, retrieve delta since last delta call.
         * @return the protocol field value
         */
        @Nullable public Boolean delta() {
            return (Boolean) value("delta");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Requested histogram name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * If true, retrieve delta since last delta call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder delta(@Nullable Boolean value) {
                if (value == null) values.remove("delta");
                else values.put("delta", jsonValue(value));
                return this;
            }
            public GetHistogramParams build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new GetHistogramParams(values);
            }
        }
    }
    /**
     * Get a Chrome histogram by name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHistogramResult extends CdpObject {
        private GetHistogramResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetHistogramResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHistogramResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Histogram.
         * @return the protocol field value
         */
        @Nullable public Browser.Histogram histogram() {
            return Browser.Histogram.fromMap(objectMap(value("histogram")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Histogram.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder histogram(@Nullable Browser.Histogram value) {
                if (value == null) values.remove("histogram");
                else values.put("histogram", jsonValue(value));
                return this;
            }
            public GetHistogramResult build() {
                if (!values.containsKey("histogram")) throw new IllegalStateException("Missing required CDP field: histogram");
                return new GetHistogramResult(values);
            }
        }
    }
    /**
     * Get position and size of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowBoundsParams extends CdpObject {
        private GetWindowBoundsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetWindowBoundsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetWindowBoundsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        @Nullable public Long windowId() {
            return numberAsLong(value("windowId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Browser window id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowId(@Nullable Long value) {
                if (value == null) values.remove("windowId");
                else values.put("windowId", jsonValue(value));
                return this;
            }
            public GetWindowBoundsParams build() {
                if (!values.containsKey("windowId")) throw new IllegalStateException("Missing required CDP field: windowId");
                return new GetWindowBoundsParams(values);
            }
        }
    }
    /**
     * Get position and size of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowBoundsResult extends CdpObject {
        private GetWindowBoundsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetWindowBoundsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetWindowBoundsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
         * @return the protocol field value
         */
        @Nullable public Browser.Bounds bounds() {
            return Browser.Bounds.fromMap(objectMap(value("bounds")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounds(@Nullable Browser.Bounds value) {
                if (value == null) values.remove("bounds");
                else values.put("bounds", jsonValue(value));
                return this;
            }
            public GetWindowBoundsResult build() {
                if (!values.containsKey("bounds")) throw new IllegalStateException("Missing required CDP field: bounds");
                return new GetWindowBoundsResult(values);
            }
        }
    }
    /**
     * Get the browser window that contains the devtools target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowForTargetParams extends CdpObject {
        private GetWindowForTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetWindowForTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetWindowForTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Devtools agent host id. If called as a part of the session, associated targetId is used.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Devtools agent host id. If called as a part of the session, associated targetId is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public GetWindowForTargetParams build() {
                return new GetWindowForTargetParams(values);
            }
        }
    }
    /**
     * Get the browser window that contains the devtools target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowForTargetResult extends CdpObject {
        private GetWindowForTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetWindowForTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetWindowForTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        @Nullable public Long windowId() {
            return numberAsLong(value("windowId"));
        }
        /**
         * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
         * @return the protocol field value
         */
        @Nullable public Browser.Bounds bounds() {
            return Browser.Bounds.fromMap(objectMap(value("bounds")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Browser window id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowId(@Nullable Long value) {
                if (value == null) values.remove("windowId");
                else values.put("windowId", jsonValue(value));
                return this;
            }
            /**
             * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounds(@Nullable Browser.Bounds value) {
                if (value == null) values.remove("bounds");
                else values.put("bounds", jsonValue(value));
                return this;
            }
            public GetWindowForTargetResult build() {
                if (!values.containsKey("windowId")) throw new IllegalStateException("Missing required CDP field: windowId");
                if (!values.containsKey("bounds")) throw new IllegalStateException("Missing required CDP field: bounds");
                return new GetWindowForTargetResult(values);
            }
        }
    }
    /**
     * Set position and/or size of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetWindowBoundsParams extends CdpObject {
        private SetWindowBoundsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetWindowBoundsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetWindowBoundsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        @Nullable public Long windowId() {
            return numberAsLong(value("windowId"));
        }
        /**
         * New window bounds. The &#x27;minimized&#x27;, &#x27;maximized&#x27; and &#x27;fullscreen&#x27; states cannot be combined with &#x27;left&#x27;, &#x27;top&#x27;, &#x27;width&#x27; or &#x27;height&#x27;. Leaves unspecified fields unchanged.
         * @return the protocol field value
         */
        @Nullable public Browser.Bounds bounds() {
            return Browser.Bounds.fromMap(objectMap(value("bounds")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Browser window id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowId(@Nullable Long value) {
                if (value == null) values.remove("windowId");
                else values.put("windowId", jsonValue(value));
                return this;
            }
            /**
             * New window bounds. The &#x27;minimized&#x27;, &#x27;maximized&#x27; and &#x27;fullscreen&#x27; states cannot be combined with &#x27;left&#x27;, &#x27;top&#x27;, &#x27;width&#x27; or &#x27;height&#x27;. Leaves unspecified fields unchanged.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounds(@Nullable Browser.Bounds value) {
                if (value == null) values.remove("bounds");
                else values.put("bounds", jsonValue(value));
                return this;
            }
            public SetWindowBoundsParams build() {
                if (!values.containsKey("windowId")) throw new IllegalStateException("Missing required CDP field: windowId");
                if (!values.containsKey("bounds")) throw new IllegalStateException("Missing required CDP field: bounds");
                return new SetWindowBoundsParams(values);
            }
        }
    }
    /**
     * Set position and/or size of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetWindowBoundsResult extends CdpObject {
        private SetWindowBoundsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetWindowBoundsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetWindowBoundsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetWindowBoundsResult build() {
                return new SetWindowBoundsResult(values);
            }
        }
    }
    /**
     * Set size of the browser contents resizing browser window as necessary.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetContentsSizeParams extends CdpObject {
        private SetContentsSizeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetContentsSizeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetContentsSizeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        @Nullable public Long windowId() {
            return numberAsLong(value("windowId"));
        }
        /**
         * The window contents width in DIP. Assumes current width if omitted. Must be specified if &#x27;height&#x27; is omitted.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Browser window id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowId(@Nullable Long value) {
                if (value == null) values.remove("windowId");
                else values.put("windowId", jsonValue(value));
                return this;
            }
            /**
             * The window contents width in DIP. Assumes current width if omitted. Must be specified if &#x27;height&#x27; is omitted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            public SetContentsSizeParams build() {
                if (!values.containsKey("windowId")) throw new IllegalStateException("Missing required CDP field: windowId");
                return new SetContentsSizeParams(values);
            }
        }
    }
    /**
     * Set size of the browser contents resizing browser window as necessary.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetContentsSizeResult extends CdpObject {
        private SetContentsSizeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetContentsSizeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetContentsSizeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetContentsSizeResult build() {
                return new SetContentsSizeResult(values);
            }
        }
    }
    /**
     * Set dock tile details, platform-specific.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDockTileParams extends CdpObject {
        private SetDockTileParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDockTileParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDockTileParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the badgeLabel field.
         * @return the protocol field value
         */
        @Nullable public String badgeLabel() {
            return (String) value("badgeLabel");
        }
        /**
         * Png encoded image. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String image() {
            return (String) value("image");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the badgeLabel field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder badgeLabel(@Nullable String value) {
                if (value == null) values.remove("badgeLabel");
                else values.put("badgeLabel", jsonValue(value));
                return this;
            }
            /**
             * Png encoded image. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder image(@Nullable String value) {
                if (value == null) values.remove("image");
                else values.put("image", jsonValue(value));
                return this;
            }
            public SetDockTileParams build() {
                return new SetDockTileParams(values);
            }
        }
    }
    /**
     * Set dock tile details, platform-specific.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDockTileResult extends CdpObject {
        private SetDockTileResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDockTileResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDockTileResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDockTileResult build() {
                return new SetDockTileResult(values);
            }
        }
    }
    /**
     * Invoke custom browser commands used by telemetry.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExecuteBrowserCommandParams extends CdpObject {
        private ExecuteBrowserCommandParams(Map<String, Object> values) { super(values); }
        @Nullable public static ExecuteBrowserCommandParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExecuteBrowserCommandParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the commandId field.
         * @return the protocol field value
         */
        @Nullable public String commandId() {
            return (String) value("commandId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the commandId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder commandId(@Nullable String value) {
                if (value == null) values.remove("commandId");
                else values.put("commandId", jsonValue(value));
                return this;
            }
            public ExecuteBrowserCommandParams build() {
                if (!values.containsKey("commandId")) throw new IllegalStateException("Missing required CDP field: commandId");
                return new ExecuteBrowserCommandParams(values);
            }
        }
    }
    /**
     * Invoke custom browser commands used by telemetry.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExecuteBrowserCommandResult extends CdpObject {
        private ExecuteBrowserCommandResult(Map<String, Object> values) { super(values); }
        @Nullable public static ExecuteBrowserCommandResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExecuteBrowserCommandResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ExecuteBrowserCommandResult build() {
                return new ExecuteBrowserCommandResult(values);
            }
        }
    }
    /**
     * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
     */
    public static final class AddPrivacySandboxEnrollmentOverrideParams extends CdpObject {
        private AddPrivacySandboxEnrollmentOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddPrivacySandboxEnrollmentOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddPrivacySandboxEnrollmentOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
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
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public AddPrivacySandboxEnrollmentOverrideParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new AddPrivacySandboxEnrollmentOverrideParams(values);
            }
        }
    }
    /**
     * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
     */
    public static final class AddPrivacySandboxEnrollmentOverrideResult extends CdpObject {
        private AddPrivacySandboxEnrollmentOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddPrivacySandboxEnrollmentOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddPrivacySandboxEnrollmentOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AddPrivacySandboxEnrollmentOverrideResult build() {
                return new AddPrivacySandboxEnrollmentOverrideResult(values);
            }
        }
    }
    /**
     * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
     */
    public static final class AddPrivacySandboxCoordinatorKeyConfigParams extends CdpObject {
        private AddPrivacySandboxCoordinatorKeyConfigParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddPrivacySandboxCoordinatorKeyConfigParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddPrivacySandboxCoordinatorKeyConfigParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the api field.
         * @return the protocol field value
         */
        @Nullable public String api() {
            return (String) value("api");
        }
        /**
         * Returns the coordinatorOrigin field.
         * @return the protocol field value
         */
        @Nullable public String coordinatorOrigin() {
            return (String) value("coordinatorOrigin");
        }
        /**
         * Returns the keyConfig field.
         * @return the protocol field value
         */
        @Nullable public String keyConfig() {
            return (String) value("keyConfig");
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the api field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder api(@Nullable String value) {
                if (value == null) values.remove("api");
                else values.put("api", jsonValue(value));
                return this;
            }
            /**
             * Sets the coordinatorOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder coordinatorOrigin(@Nullable String value) {
                if (value == null) values.remove("coordinatorOrigin");
                else values.put("coordinatorOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the keyConfig field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyConfig(@Nullable String value) {
                if (value == null) values.remove("keyConfig");
                else values.put("keyConfig", jsonValue(value));
                return this;
            }
            /**
             * BrowserContext to perform the action in. When omitted, default browser context is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public AddPrivacySandboxCoordinatorKeyConfigParams build() {
                if (!values.containsKey("api")) throw new IllegalStateException("Missing required CDP field: api");
                if (!values.containsKey("coordinatorOrigin")) throw new IllegalStateException("Missing required CDP field: coordinatorOrigin");
                if (!values.containsKey("keyConfig")) throw new IllegalStateException("Missing required CDP field: keyConfig");
                return new AddPrivacySandboxCoordinatorKeyConfigParams(values);
            }
        }
    }
    /**
     * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
     */
    public static final class AddPrivacySandboxCoordinatorKeyConfigResult extends CdpObject {
        private AddPrivacySandboxCoordinatorKeyConfigResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddPrivacySandboxCoordinatorKeyConfigResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddPrivacySandboxCoordinatorKeyConfigResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AddPrivacySandboxCoordinatorKeyConfigResult build() {
                return new AddPrivacySandboxCoordinatorKeyConfigResult(values);
            }
        }
    }
    /**
     * Fired when page is about to start a download.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DownloadWillBeginEvent extends CdpObject {
        private DownloadWillBeginEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DownloadWillBeginEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DownloadWillBeginEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that caused the download to begin.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        @Nullable public String guid() {
            return (String) value("guid");
        }
        /**
         * URL of the resource being downloaded.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @return the protocol field value
         */
        @Nullable public String suggestedFilename() {
            return (String) value("suggestedFilename");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that caused the download to begin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Global unique identifier of the download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder guid(@Nullable String value) {
                if (value == null) values.remove("guid");
                else values.put("guid", jsonValue(value));
                return this;
            }
            /**
             * URL of the resource being downloaded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Suggested file name of the resource (the actual name of the file saved on disk may differ).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder suggestedFilename(@Nullable String value) {
                if (value == null) values.remove("suggestedFilename");
                else values.put("suggestedFilename", jsonValue(value));
                return this;
            }
            public DownloadWillBeginEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("guid")) throw new IllegalStateException("Missing required CDP field: guid");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("suggestedFilename")) throw new IllegalStateException("Missing required CDP field: suggestedFilename");
                return new DownloadWillBeginEvent(values);
            }
        }
    }
    /**
     * Fired when download makes progress. Last call has |done| == true.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DownloadProgressEvent extends CdpObject {
        private DownloadProgressEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DownloadProgressEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DownloadProgressEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        @Nullable public String guid() {
            return (String) value("guid");
        }
        /**
         * Total expected bytes to download.
         * @return the protocol field value
         */
        @Nullable public Double totalBytes() {
            return numberAsDouble(value("totalBytes"));
        }
        /**
         * Total bytes received.
         * @return the protocol field value
         */
        @Nullable public Double receivedBytes() {
            return numberAsDouble(value("receivedBytes"));
        }
        /**
         * Download status.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        /**
         * Download status.
         */
        public static final class StateValues {
            private StateValues() {}
            public static final String INPROGRESS = "inProgress";
            public static final String COMPLETED = "completed";
            public static final String CANCELED = "canceled";
        }
        /**
         * If download is &quot;completed&quot;, provides the path of the downloaded file. Depending on the platform, it is not guaranteed to be set, nor the file is guaranteed to exist.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String filePath() {
            return (String) value("filePath");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Global unique identifier of the download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder guid(@Nullable String value) {
                if (value == null) values.remove("guid");
                else values.put("guid", jsonValue(value));
                return this;
            }
            /**
             * Total expected bytes to download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder totalBytes(@Nullable Double value) {
                if (value == null) values.remove("totalBytes");
                else values.put("totalBytes", jsonValue(value));
                return this;
            }
            /**
             * Total bytes received.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder receivedBytes(@Nullable Double value) {
                if (value == null) values.remove("receivedBytes");
                else values.put("receivedBytes", jsonValue(value));
                return this;
            }
            /**
             * Download status.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            /**
             * If download is &quot;completed&quot;, provides the path of the downloaded file. Depending on the platform, it is not guaranteed to be set, nor the file is guaranteed to exist.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filePath(@Nullable String value) {
                if (value == null) values.remove("filePath");
                else values.put("filePath", jsonValue(value));
                return this;
            }
            public DownloadProgressEvent build() {
                if (!values.containsKey("guid")) throw new IllegalStateException("Missing required CDP field: guid");
                if (!values.containsKey("totalBytes")) throw new IllegalStateException("Missing required CDP field: totalBytes");
                if (!values.containsKey("receivedBytes")) throw new IllegalStateException("Missing required CDP field: receivedBytes");
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                return new DownloadProgressEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPermissionResult> setPermission(SetPermissionParams params) {
            return client.call("Browser.setPermission", params, SetPermissionResult::fromMap);
        }
        /**
         * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GrantPermissionsResult> grantPermissions(GrantPermissionsParams params) {
            return client.call("Browser.grantPermissions", params, GrantPermissionsResult::fromMap);
        }
        /**
         * Reset all permission management for all origins.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResetPermissionsResult> resetPermissions(ResetPermissionsParams params) {
            return client.call("Browser.resetPermissions", params, ResetPermissionsResult::fromMap);
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDownloadBehaviorResult> setDownloadBehavior(SetDownloadBehaviorParams params) {
            return client.call("Browser.setDownloadBehavior", params, SetDownloadBehaviorResult::fromMap);
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CancelDownloadResult> cancelDownload(CancelDownloadParams params) {
            return client.call("Browser.cancelDownload", params, CancelDownloadResult::fromMap);
        }
        /**
         * Close browser gracefully.
         * @return a stage completing with the command result
         */
        public CompletionStage<CloseResult> close() {
            return client.call("Browser.close", null, CloseResult::fromMap);
        }
        /**
         * Crashes browser on the main thread.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<CrashResult> crash() {
            return client.call("Browser.crash", null, CrashResult::fromMap);
        }
        /**
         * Crashes GPU process.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<CrashGpuProcessResult> crashGpuProcess() {
            return client.call("Browser.crashGpuProcess", null, CrashGpuProcessResult::fromMap);
        }
        /**
         * Returns version information.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetVersionResult> getVersion() {
            return client.call("Browser.getVersion", null, GetVersionResult::fromMap);
        }
        /**
         * Returns the command line switches for the browser process if, and only if --enable-automation is on the commandline.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBrowserCommandLineResult> getBrowserCommandLine() {
            return client.call("Browser.getBrowserCommandLine", null, GetBrowserCommandLineResult::fromMap);
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetHistogramsResult> getHistograms(GetHistogramsParams params) {
            return client.call("Browser.getHistograms", params, GetHistogramsResult::fromMap);
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetHistogramResult> getHistogram(GetHistogramParams params) {
            return client.call("Browser.getHistogram", params, GetHistogramResult::fromMap);
        }
        /**
         * Get position and size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowBoundsResult> getWindowBounds(GetWindowBoundsParams params) {
            return client.call("Browser.getWindowBounds", params, GetWindowBoundsResult::fromMap);
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget(GetWindowForTargetParams params) {
            return client.call("Browser.getWindowForTarget", params, GetWindowForTargetResult::fromMap);
        }
        /**
         * Set position and/or size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetWindowBoundsResult> setWindowBounds(SetWindowBoundsParams params) {
            return client.call("Browser.setWindowBounds", params, SetWindowBoundsResult::fromMap);
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetContentsSizeResult> setContentsSize(SetContentsSizeParams params) {
            return client.call("Browser.setContentsSize", params, SetContentsSizeResult::fromMap);
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDockTileResult> setDockTile(SetDockTileParams params) {
            return client.call("Browser.setDockTile", params, SetDockTileResult::fromMap);
        }
        /**
         * Invoke custom browser commands used by telemetry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ExecuteBrowserCommandResult> executeBrowserCommand(ExecuteBrowserCommandParams params) {
            return client.call("Browser.executeBrowserCommand", params, ExecuteBrowserCommandResult::fromMap);
        }
        /**
         * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddPrivacySandboxEnrollmentOverrideResult> addPrivacySandboxEnrollmentOverride(AddPrivacySandboxEnrollmentOverrideParams params) {
            return client.call("Browser.addPrivacySandboxEnrollmentOverride", params, AddPrivacySandboxEnrollmentOverrideResult::fromMap);
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddPrivacySandboxCoordinatorKeyConfigResult> addPrivacySandboxCoordinatorKeyConfig(AddPrivacySandboxCoordinatorKeyConfigParams params) {
            return client.call("Browser.addPrivacySandboxCoordinatorKeyConfig", params, AddPrivacySandboxCoordinatorKeyConfigResult::fromMap);
        }
        /**
         * Fired when page is about to start a download.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDownloadWillBegin(Consumer<DownloadWillBeginEvent> handler) {
            return client.on("Browser.downloadWillBegin", DownloadWillBeginEvent::fromMap, handler);
        }
        /**
         * Fired when download makes progress. Last call has |done| == true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDownloadProgress(Consumer<DownloadProgressEvent> handler) {
            return client.on("Browser.downloadProgress", DownloadProgressEvent::fromMap, handler);
        }
    }
}
