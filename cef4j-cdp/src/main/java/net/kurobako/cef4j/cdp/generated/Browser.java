// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
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
import org.jspecify.annotations.Nullable;

/**
 * The Browser domain defines methods and events for browser managing.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Browser.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Browser {
    private Browser() {}
    private static final CdpField<Browser.PrivacySandboxAPI> API = CdpField.of("api", value -> Browser.PrivacySandboxAPI.of((String) value));
    private static final CdpField<java.util.List<String>> ARGUMENTS = CdpField.of("arguments", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<SetDownloadBehaviorBehaviorValues> BEHAVIOR = CdpField.of("behavior", value -> SetDownloadBehaviorBehaviorValues.of((String) value));
    private static final CdpField<Browser.Bounds> BOUNDS = CdpField.of("bounds", value -> java.util.Objects.requireNonNull(Browser.Bounds.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Browser.BrowserContextID> BROWSER_CONTEXT_ID = CdpField.of("browserContextId", value -> new Browser.BrowserContextID((String) value));
    private static final CdpField<java.util.List<Browser.Bucket>> BUCKETS = CdpField.of("buckets", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Browser.Bucket.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Browser.BrowserCommandId> COMMAND_ID = CdpField.of("commandId", value -> Browser.BrowserCommandId.of((String) value));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Browser.Histogram> HISTOGRAM = CdpField.of("histogram", value -> java.util.Objects.requireNonNull(Browser.Histogram.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Browser.Histogram>> HISTOGRAMS = CdpField.of("histograms", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Browser.Histogram.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Browser.PermissionDescriptor> PERMISSION = CdpField.of("permission", value -> java.util.Objects.requireNonNull(Browser.PermissionDescriptor.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Browser.PermissionType>> PERMISSIONS = CdpField.of("permissions", value -> CdpObject.requireList(value, element0 -> Browser.PermissionType.of((String) element0)));
    private static final CdpField<Browser.PermissionSetting> SETTING = CdpField.of("setting", value -> Browser.PermissionSetting.of((String) value));
    private static final CdpField<DownloadProgressEvent.StateValues> STATE = CdpField.of("state", value -> DownloadProgressEvent.StateValues.of((String) value));
    private static final CdpField<Target.TargetID> TARGET_ID = CdpField.of("targetId", value -> new Target.TargetID((String) value));
    private static final CdpField<Browser.WindowID> WINDOW_ID = CdpField.of("windowId", value -> new Browser.WindowID(((Number) value).longValue()));
    private static final CdpField<Browser.WindowState> WINDOW_STATE = CdpField.of("windowState", value -> Browser.WindowState.of((String) value));
    /**
     * Tagged String wire value for BrowserContextID.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BrowserContextID implements CdpValue<String> {
        public final String value;
        public BrowserContextID(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof BrowserContextID)) return false;
            return value.equals(((BrowserContextID) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "BrowserContextID(" + value + ")"; }
    }
    /**
     * Tagged long wire value for WindowID.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WindowID implements CdpValue<Long> {
        public final long value;
        public WindowID(long value) { this.value = value; }
        @Override public Long value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof WindowID)) return false;
            return value == ((WindowID) other).value;
        }
        @Override public int hashCode() { return Long.hashCode(value); }
        @Override public String toString() { return "WindowID(" + value + ")"; }
    }
    /**
     * The state of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum WindowState implements CdpValue<String> {
        NORMAL("normal"),
        MINIMIZED("minimized"),
        MAXIMIZED("maximized"),
        FULLSCREEN("fullscreen");
        public final String value;
        WindowState(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static WindowState of(String value) {
            for (WindowState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown WindowState value: " + value);
        }
    }
    /**
     * Browser window bounds information
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Bounds extends CdpObject {
        public Bounds() {}
        private Bounds(Map<String, Object> values) { super(values); }
        public static Bounds fromMap(Map<String, Object> values) {
            return new Bounds(values);
        }
        /**
         * The offset from the left edge of the screen to the window in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return optionalLong("left");
        }
        /**
         * The offset from the top edge of the screen to the window in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            return optionalLong("top");
        }
        /**
         * The window width in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            return optionalLong("width");
        }
        /**
         * The window height in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            return optionalLong("height");
        }
        /**
         * The window state. Default to normal.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.WindowState> windowState() {
            return optional(WINDOW_STATE);
        }
        /**
         * The offset from the left edge of the screen to the window in pixels.
         * @param left field value
         * @return this model
         */
        public Bounds left(long left) {
            set("left", left);
            return this;
        }
        /**
         * The offset from the top edge of the screen to the window in pixels.
         * @param top field value
         * @return this model
         */
        public Bounds top(long top) {
            set("top", top);
            return this;
        }
        /**
         * The window width in pixels.
         * @param width field value
         * @return this model
         */
        public Bounds width(long width) {
            set("width", width);
            return this;
        }
        /**
         * The window height in pixels.
         * @param height field value
         * @return this model
         */
        public Bounds height(long height) {
            set("height", height);
            return this;
        }
        /**
         * The window state. Default to normal.
         * @param windowState field value
         * @return this model
         */
        public Bounds windowState(Browser.WindowState windowState) {
            set("windowState", windowState);
            return this;
        }
    }
    /**
     * Wire values for PermissionType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PermissionType implements CdpValue<String> {
        AR("ar"),
        AUDIOCAPTURE("audioCapture"),
        AUTOMATICFULLSCREEN("automaticFullscreen"),
        BACKGROUNDFETCH("backgroundFetch"),
        BACKGROUNDSYNC("backgroundSync"),
        CAMERAPANTILTZOOM("cameraPanTiltZoom"),
        CAPTUREDSURFACECONTROL("capturedSurfaceControl"),
        CLIPBOARDREADWRITE("clipboardReadWrite"),
        CLIPBOARDSANITIZEDWRITE("clipboardSanitizedWrite"),
        DISPLAYCAPTURE("displayCapture"),
        DURABLESTORAGE("durableStorage"),
        GEOLOCATION("geolocation"),
        HANDTRACKING("handTracking"),
        IDLEDETECTION("idleDetection"),
        KEYBOARDLOCK("keyboardLock"),
        LOCALFONTS("localFonts"),
        LOCALNETWORK("localNetwork"),
        LOCALNETWORKACCESS("localNetworkAccess"),
        LOOPBACKNETWORK("loopbackNetwork"),
        MIDI("midi"),
        MIDISYSEX("midiSysex"),
        NFC("nfc"),
        NOTIFICATIONS("notifications"),
        PAYMENTHANDLER("paymentHandler"),
        PERIODICBACKGROUNDSYNC("periodicBackgroundSync"),
        POINTERLOCK("pointerLock"),
        PROTECTEDMEDIAIDENTIFIER("protectedMediaIdentifier"),
        SENSORS("sensors"),
        SMARTCARD("smartCard"),
        SPEAKERSELECTION("speakerSelection"),
        STORAGEACCESS("storageAccess"),
        TOPLEVELSTORAGEACCESS("topLevelStorageAccess"),
        VIDEOCAPTURE("videoCapture"),
        VR("vr"),
        WAKELOCKSCREEN("wakeLockScreen"),
        WAKELOCKSYSTEM("wakeLockSystem"),
        WEBAPPINSTALLATION("webAppInstallation"),
        WEBPRINTING("webPrinting"),
        WINDOWMANAGEMENT("windowManagement");
        public final String value;
        PermissionType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PermissionType of(String value) {
            for (PermissionType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PermissionType value: " + value);
        }
    }
    /**
     * Wire values for PermissionSetting.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PermissionSetting implements CdpValue<String> {
        GRANTED("granted"),
        DENIED("denied"),
        PROMPT("prompt");
        public final String value;
        PermissionSetting(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PermissionSetting of(String value) {
            for (PermissionSetting constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PermissionSetting value: " + value);
        }
    }
    /**
     * Definition of PermissionDescriptor defined in the Permissions API: https://w3c.github.io/permissions/#dom-permissiondescriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionDescriptor extends CdpObject {
        public PermissionDescriptor() {}
        private PermissionDescriptor(Map<String, Object> values) { super(values); }
        public static PermissionDescriptor fromMap(Map<String, Object> values) {
            return new PermissionDescriptor(values);
        }
        /**
         * Name of permission. See https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl for valid permission names.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * For &quot;midi&quot; permission, may also specify sysex control.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> sysex() {
            return optionalBoolean("sysex");
        }
        /**
         * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> userVisibleOnly() {
            return optionalBoolean("userVisibleOnly");
        }
        /**
         * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowWithoutSanitization() {
            return optionalBoolean("allowWithoutSanitization");
        }
        /**
         * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowWithoutGesture() {
            return optionalBoolean("allowWithoutGesture");
        }
        /**
         * For &quot;camera&quot; permission, may specify panTiltZoom.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> panTiltZoom() {
            return optionalBoolean("panTiltZoom");
        }
        /**
         * Name of permission. See https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl for valid permission names.
         * @param name field value
         * @return this model
         */
        public PermissionDescriptor name(String name) {
            set("name", name);
            return this;
        }
        /**
         * For &quot;midi&quot; permission, may also specify sysex control.
         * @param sysex field value
         * @return this model
         */
        public PermissionDescriptor sysex(boolean sysex) {
            set("sysex", sysex);
            return this;
        }
        /**
         * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
         * @param userVisibleOnly field value
         * @return this model
         */
        public PermissionDescriptor userVisibleOnly(boolean userVisibleOnly) {
            set("userVisibleOnly", userVisibleOnly);
            return this;
        }
        /**
         * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
         * @param allowWithoutSanitization field value
         * @return this model
         */
        public PermissionDescriptor allowWithoutSanitization(boolean allowWithoutSanitization) {
            set("allowWithoutSanitization", allowWithoutSanitization);
            return this;
        }
        /**
         * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
         * @param allowWithoutGesture field value
         * @return this model
         */
        public PermissionDescriptor allowWithoutGesture(boolean allowWithoutGesture) {
            set("allowWithoutGesture", allowWithoutGesture);
            return this;
        }
        /**
         * For &quot;camera&quot; permission, may specify panTiltZoom.
         * @param panTiltZoom field value
         * @return this model
         */
        public PermissionDescriptor panTiltZoom(boolean panTiltZoom) {
            set("panTiltZoom", panTiltZoom);
            return this;
        }
    }
    /**
     * Browser command ids used by executeBrowserCommand.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum BrowserCommandId implements CdpValue<String> {
        OPENTABSEARCH("openTabSearch"),
        CLOSETABSEARCH("closeTabSearch"),
        OPENGLIC("openGlic");
        public final String value;
        BrowserCommandId(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static BrowserCommandId of(String value) {
            for (BrowserCommandId constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown BrowserCommandId value: " + value);
        }
    }
    /**
     * Chrome histogram bucket.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Bucket extends CdpObject {
        public Bucket() {}
        private Bucket(Map<String, Object> values) { super(values); }
        public static Bucket fromMap(Map<String, Object> values) {
            return new Bucket(values);
        }
        /**
         * Minimum value (inclusive).
         * @return the protocol field value
         */
        public long low() {
            return requireLong("low");
        }
        /**
         * Maximum value (exclusive).
         * @return the protocol field value
         */
        public long high() {
            return requireLong("high");
        }
        /**
         * Number of samples.
         * @return the protocol field value
         */
        public long count() {
            return requireLong("count");
        }
        /**
         * Minimum value (inclusive).
         * @param low field value
         * @return this model
         */
        public Bucket low(long low) {
            set("low", low);
            return this;
        }
        /**
         * Maximum value (exclusive).
         * @param high field value
         * @return this model
         */
        public Bucket high(long high) {
            set("high", high);
            return this;
        }
        /**
         * Number of samples.
         * @param count field value
         * @return this model
         */
        public Bucket count(long count) {
            set("count", count);
            return this;
        }
    }
    /**
     * Chrome histogram.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Histogram extends CdpObject {
        public Histogram() {}
        private Histogram(Map<String, Object> values) { super(values); }
        public static Histogram fromMap(Map<String, Object> values) {
            return new Histogram(values);
        }
        /**
         * Name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Sum of sample values.
         * @return the protocol field value
         */
        public long sum() {
            return requireLong("sum");
        }
        /**
         * Total number of samples.
         * @return the protocol field value
         */
        public long count() {
            return requireLong("count");
        }
        /**
         * Buckets.
         * @return the protocol field value
         */
        public java.util.List<Browser.Bucket> buckets() {
            return require(BUCKETS);
        }
        /**
         * Name.
         * @param name field value
         * @return this model
         */
        public Histogram name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sum of sample values.
         * @param sum field value
         * @return this model
         */
        public Histogram sum(long sum) {
            set("sum", sum);
            return this;
        }
        /**
         * Total number of samples.
         * @param count field value
         * @return this model
         */
        public Histogram count(long count) {
            set("count", count);
            return this;
        }
        /**
         * Buckets.
         * @param buckets field value
         * @return this model
         */
        public Histogram buckets(java.util.List<Browser.Bucket> buckets) {
            set("buckets", buckets);
            return this;
        }
    }
    /**
     * Wire values for PrivacySandboxAPI.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PrivacySandboxAPI implements CdpValue<String> {
        BIDDINGANDAUCTIONSERVICES("BiddingAndAuctionServices"),
        TRUSTEDKEYVALUE("TrustedKeyValue");
        public final String value;
        PrivacySandboxAPI(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PrivacySandboxAPI of(String value) {
            for (PrivacySandboxAPI constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PrivacySandboxAPI value: " + value);
        }
    }
    /**
     * Set permission settings for given embedding and embedded origins.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPermissionRequest extends CdpObject {
        public SetPermissionRequest() {}
        public static SetPermissionRequest fromMap(Map<String, Object> values) {
            final SetPermissionRequest instance_ = new SetPermissionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Descriptor of permission to override.
         * @return the protocol field value
         */
        public Browser.PermissionDescriptor permission() {
            return require(PERMISSION);
        }
        /**
         * Setting of the permission.
         * @return the protocol field value
         */
        public Browser.PermissionSetting setting() {
            return require(SETTING);
        }
        /**
         * Embedding origin the permission applies to, all origins if not specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> origin() {
            return optionalString("origin");
        }
        /**
         * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> embeddedOrigin() {
            return optionalString("embeddedOrigin");
        }
        /**
         * Context to override. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Descriptor of permission to override.
         * @param permission field value
         * @return this model
         */
        public SetPermissionRequest permission(Browser.PermissionDescriptor permission) {
            set("permission", permission);
            return this;
        }
        /**
         * Setting of the permission.
         * @param setting field value
         * @return this model
         */
        public SetPermissionRequest setting(Browser.PermissionSetting setting) {
            set("setting", setting);
            return this;
        }
        /**
         * Embedding origin the permission applies to, all origins if not specified.
         * @param origin field value
         * @return this model
         */
        public SetPermissionRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
         * @param embeddedOrigin field value
         * @return this model
         */
        public SetPermissionRequest embeddedOrigin(String embeddedOrigin) {
            set("embeddedOrigin", embeddedOrigin);
            return this;
        }
        /**
         * Context to override. When omitted, default browser context is used.
         * @param browserContextId field value
         * @return this model
         */
        public SetPermissionRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GrantPermissionsRequest extends CdpObject {
        public GrantPermissionsRequest() {}
        public static GrantPermissionsRequest fromMap(Map<String, Object> values) {
            final GrantPermissionsRequest instance_ = new GrantPermissionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the permissions field.
         * @return the protocol field value
         */
        public java.util.List<Browser.PermissionType> permissions() {
            return require(PERMISSIONS);
        }
        /**
         * Origin the permission applies to, all origins if not specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> origin() {
            return optionalString("origin");
        }
        /**
         * BrowserContext to override permissions. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Sets the permissions field.
         * @param permissions field value
         * @return this model
         */
        public GrantPermissionsRequest permissions(java.util.List<Browser.PermissionType> permissions) {
            set("permissions", permissions);
            return this;
        }
        /**
         * Origin the permission applies to, all origins if not specified.
         * @param origin field value
         * @return this model
         */
        public GrantPermissionsRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * BrowserContext to override permissions. When omitted, default browser context is used.
         * @param browserContextId field value
         * @return this model
         */
        public GrantPermissionsRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Reset all permission management for all origins.
     */
    public static final class ResetPermissionsRequest extends CdpObject {
        public ResetPermissionsRequest() {}
        public static ResetPermissionsRequest fromMap(Map<String, Object> values) {
            final ResetPermissionsRequest instance_ = new ResetPermissionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * BrowserContext to reset permissions. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * BrowserContext to reset permissions. When omitted, default browser context is used.
         * @param browserContextId field value
         * @return this model
         */
        public ResetPermissionsRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Set the behavior when downloading a file.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDownloadBehaviorRequest extends CdpObject {
        public SetDownloadBehaviorRequest() {}
        public static SetDownloadBehaviorRequest fromMap(Map<String, Object> values) {
            final SetDownloadBehaviorRequest instance_ = new SetDownloadBehaviorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
         * @return the protocol field value
         */
        public SetDownloadBehaviorBehaviorValues behavior() {
            return require(BEHAVIOR);
        }
        /**
         * BrowserContext to set download behavior. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> downloadPath() {
            return optionalString("downloadPath");
        }
        /**
         * Whether to emit download events (defaults to false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> eventsEnabled() {
            return optionalBoolean("eventsEnabled");
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
         * @param behavior field value
         * @return this model
         */
        public SetDownloadBehaviorRequest behavior(SetDownloadBehaviorBehaviorValues behavior) {
            set("behavior", behavior);
            return this;
        }
        /**
         * BrowserContext to set download behavior. When omitted, default browser context is used.
         * @param browserContextId field value
         * @return this model
         */
        public SetDownloadBehaviorRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
         * @param downloadPath field value
         * @return this model
         */
        public SetDownloadBehaviorRequest downloadPath(String downloadPath) {
            set("downloadPath", downloadPath);
            return this;
        }
        /**
         * Whether to emit download events (defaults to false).
         * @param eventsEnabled field value
         * @return this model
         */
        public SetDownloadBehaviorRequest eventsEnabled(boolean eventsEnabled) {
            set("eventsEnabled", eventsEnabled);
            return this;
        }
    }
    /**
     * Cancel a download if in progress
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CancelDownloadRequest extends CdpObject {
        public CancelDownloadRequest() {}
        public static CancelDownloadRequest fromMap(Map<String, Object> values) {
            final CancelDownloadRequest instance_ = new CancelDownloadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return requireString("guid");
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Global unique identifier of the download.
         * @param guid field value
         * @return this model
         */
        public CancelDownloadRequest guid(String guid) {
            set("guid", guid);
            return this;
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @param browserContextId field value
         * @return this model
         */
        public CancelDownloadRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Get Chrome histograms.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHistogramsRequest extends CdpObject {
        public GetHistogramsRequest() {}
        public static GetHistogramsRequest fromMap(Map<String, Object> values) {
            final GetHistogramsRequest instance_ = new GetHistogramsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> query() {
            return optionalString("query");
        }
        /**
         * If true, retrieve delta since last delta call.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> delta() {
            return optionalBoolean("delta");
        }
        /**
         * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
         * @param query field value
         * @return this model
         */
        public GetHistogramsRequest query(String query) {
            set("query", query);
            return this;
        }
        /**
         * If true, retrieve delta since last delta call.
         * @param delta field value
         * @return this model
         */
        public GetHistogramsRequest delta(boolean delta) {
            set("delta", delta);
            return this;
        }
    }
    /**
     * Get a Chrome histogram by name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHistogramRequest extends CdpObject {
        public GetHistogramRequest() {}
        public static GetHistogramRequest fromMap(Map<String, Object> values) {
            final GetHistogramRequest instance_ = new GetHistogramRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Requested histogram name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * If true, retrieve delta since last delta call.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> delta() {
            return optionalBoolean("delta");
        }
        /**
         * Requested histogram name.
         * @param name field value
         * @return this model
         */
        public GetHistogramRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * If true, retrieve delta since last delta call.
         * @param delta field value
         * @return this model
         */
        public GetHistogramRequest delta(boolean delta) {
            set("delta", delta);
            return this;
        }
    }
    /**
     * Get position and size of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowBoundsRequest extends CdpObject {
        public GetWindowBoundsRequest() {}
        public static GetWindowBoundsRequest fromMap(Map<String, Object> values) {
            final GetWindowBoundsRequest instance_ = new GetWindowBoundsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return require(WINDOW_ID);
        }
        /**
         * Browser window id.
         * @param windowId field value
         * @return this model
         */
        public GetWindowBoundsRequest windowId(Browser.WindowID windowId) {
            set("windowId", windowId);
            return this;
        }
    }
    /**
     * Get the browser window that contains the devtools target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowForTargetRequest extends CdpObject {
        public GetWindowForTargetRequest() {}
        public static GetWindowForTargetRequest fromMap(Map<String, Object> values) {
            final GetWindowForTargetRequest instance_ = new GetWindowForTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Devtools agent host id. If called as a part of the session, associated targetId is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Devtools agent host id. If called as a part of the session, associated targetId is used.
         * @param targetId field value
         * @return this model
         */
        public GetWindowForTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Set position and/or size of the browser window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetWindowBoundsRequest extends CdpObject {
        public SetWindowBoundsRequest() {}
        public static SetWindowBoundsRequest fromMap(Map<String, Object> values) {
            final SetWindowBoundsRequest instance_ = new SetWindowBoundsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return require(WINDOW_ID);
        }
        /**
         * New window bounds. The &#x27;minimized&#x27;, &#x27;maximized&#x27; and &#x27;fullscreen&#x27; states cannot be combined with &#x27;left&#x27;, &#x27;top&#x27;, &#x27;width&#x27; or &#x27;height&#x27;. Leaves unspecified fields unchanged.
         * @return the protocol field value
         */
        public Browser.Bounds bounds() {
            return require(BOUNDS);
        }
        /**
         * Browser window id.
         * @param windowId field value
         * @return this model
         */
        public SetWindowBoundsRequest windowId(Browser.WindowID windowId) {
            set("windowId", windowId);
            return this;
        }
        /**
         * New window bounds. The &#x27;minimized&#x27;, &#x27;maximized&#x27; and &#x27;fullscreen&#x27; states cannot be combined with &#x27;left&#x27;, &#x27;top&#x27;, &#x27;width&#x27; or &#x27;height&#x27;. Leaves unspecified fields unchanged.
         * @param bounds field value
         * @return this model
         */
        public SetWindowBoundsRequest bounds(Browser.Bounds bounds) {
            set("bounds", bounds);
            return this;
        }
    }
    /**
     * Set size of the browser contents resizing browser window as necessary.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetContentsSizeRequest extends CdpObject {
        public SetContentsSizeRequest() {}
        public static SetContentsSizeRequest fromMap(Map<String, Object> values) {
            final SetContentsSizeRequest instance_ = new SetContentsSizeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return require(WINDOW_ID);
        }
        /**
         * The window contents width in DIP. Assumes current width if omitted. Must be specified if &#x27;height&#x27; is omitted.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            return optionalLong("width");
        }
        /**
         * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            return optionalLong("height");
        }
        /**
         * Browser window id.
         * @param windowId field value
         * @return this model
         */
        public SetContentsSizeRequest windowId(Browser.WindowID windowId) {
            set("windowId", windowId);
            return this;
        }
        /**
         * The window contents width in DIP. Assumes current width if omitted. Must be specified if &#x27;height&#x27; is omitted.
         * @param width field value
         * @return this model
         */
        public SetContentsSizeRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
         * @param height field value
         * @return this model
         */
        public SetContentsSizeRequest height(long height) {
            set("height", height);
            return this;
        }
    }
    /**
     * Set dock tile details, platform-specific.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDockTileRequest extends CdpObject {
        public SetDockTileRequest() {}
        public static SetDockTileRequest fromMap(Map<String, Object> values) {
            final SetDockTileRequest instance_ = new SetDockTileRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the badgeLabel field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> badgeLabel() {
            return optionalString("badgeLabel");
        }
        /**
         * Png encoded image. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> image() {
            return optionalString("image");
        }
        /**
         * Sets the badgeLabel field.
         * @param badgeLabel field value
         * @return this model
         */
        public SetDockTileRequest badgeLabel(String badgeLabel) {
            set("badgeLabel", badgeLabel);
            return this;
        }
        /**
         * Png encoded image. (Encoded as a base64 string when passed over JSON)
         * @param image field value
         * @return this model
         */
        public SetDockTileRequest image(String image) {
            set("image", image);
            return this;
        }
    }
    /**
     * Invoke custom browser commands used by telemetry.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExecuteBrowserCommandRequest extends CdpObject {
        public ExecuteBrowserCommandRequest() {}
        public static ExecuteBrowserCommandRequest fromMap(Map<String, Object> values) {
            final ExecuteBrowserCommandRequest instance_ = new ExecuteBrowserCommandRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the commandId field.
         * @return the protocol field value
         */
        public Browser.BrowserCommandId commandId() {
            return require(COMMAND_ID);
        }
        /**
         * Sets the commandId field.
         * @param commandId field value
         * @return this model
         */
        public ExecuteBrowserCommandRequest commandId(Browser.BrowserCommandId commandId) {
            set("commandId", commandId);
            return this;
        }
    }
    /**
     * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
     */
    public static final class AddPrivacySandboxEnrollmentOverrideRequest extends CdpObject {
        public AddPrivacySandboxEnrollmentOverrideRequest() {}
        public static AddPrivacySandboxEnrollmentOverrideRequest fromMap(Map<String, Object> values) {
            final AddPrivacySandboxEnrollmentOverrideRequest instance_ = new AddPrivacySandboxEnrollmentOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public AddPrivacySandboxEnrollmentOverrideRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
     */
    public static final class AddPrivacySandboxCoordinatorKeyConfigRequest extends CdpObject {
        public AddPrivacySandboxCoordinatorKeyConfigRequest() {}
        public static AddPrivacySandboxCoordinatorKeyConfigRequest fromMap(Map<String, Object> values) {
            final AddPrivacySandboxCoordinatorKeyConfigRequest instance_ = new AddPrivacySandboxCoordinatorKeyConfigRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the api field.
         * @return the protocol field value
         */
        public Browser.PrivacySandboxAPI api() {
            return require(API);
        }
        /**
         * Returns the coordinatorOrigin field.
         * @return the protocol field value
         */
        public String coordinatorOrigin() {
            return requireString("coordinatorOrigin");
        }
        /**
         * Returns the keyConfig field.
         * @return the protocol field value
         */
        public String keyConfig() {
            return requireString("keyConfig");
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Sets the api field.
         * @param api field value
         * @return this model
         */
        public AddPrivacySandboxCoordinatorKeyConfigRequest api(Browser.PrivacySandboxAPI api) {
            set("api", api);
            return this;
        }
        /**
         * Sets the coordinatorOrigin field.
         * @param coordinatorOrigin field value
         * @return this model
         */
        public AddPrivacySandboxCoordinatorKeyConfigRequest coordinatorOrigin(String coordinatorOrigin) {
            set("coordinatorOrigin", coordinatorOrigin);
            return this;
        }
        /**
         * Sets the keyConfig field.
         * @param keyConfig field value
         * @return this model
         */
        public AddPrivacySandboxCoordinatorKeyConfigRequest keyConfig(String keyConfig) {
            set("keyConfig", keyConfig);
            return this;
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @param browserContextId field value
         * @return this model
         */
        public AddPrivacySandboxCoordinatorKeyConfigRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Returns version information.
     */
    public static final class GetVersionResult extends CdpObject {
        public GetVersionResult() {}
        private GetVersionResult(Map<String, Object> values) { super(values); }
        public static GetVersionResult fromMap(Map<String, Object> values) {
            return new GetVersionResult(values);
        }
        /**
         * Protocol version.
         * @return the protocol field value
         */
        public String protocolVersion() {
            return requireString("protocolVersion");
        }
        /**
         * Product name.
         * @return the protocol field value
         */
        public String product() {
            return requireString("product");
        }
        /**
         * Product revision.
         * @return the protocol field value
         */
        public String revision() {
            return requireString("revision");
        }
        /**
         * User-Agent.
         * @return the protocol field value
         */
        public String userAgent() {
            return requireString("userAgent");
        }
        /**
         * V8 version.
         * @return the protocol field value
         */
        public String jsVersion() {
            return requireString("jsVersion");
        }
        /**
         * Protocol version.
         * @param protocolVersion field value
         * @return this model
         */
        public GetVersionResult protocolVersion(String protocolVersion) {
            set("protocolVersion", protocolVersion);
            return this;
        }
        /**
         * Product name.
         * @param product field value
         * @return this model
         */
        public GetVersionResult product(String product) {
            set("product", product);
            return this;
        }
        /**
         * Product revision.
         * @param revision field value
         * @return this model
         */
        public GetVersionResult revision(String revision) {
            set("revision", revision);
            return this;
        }
        /**
         * User-Agent.
         * @param userAgent field value
         * @return this model
         */
        public GetVersionResult userAgent(String userAgent) {
            set("userAgent", userAgent);
            return this;
        }
        /**
         * V8 version.
         * @param jsVersion field value
         * @return this model
         */
        public GetVersionResult jsVersion(String jsVersion) {
            set("jsVersion", jsVersion);
            return this;
        }
    }
    /**
     * Get the browser window that contains the devtools target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetWindowForTargetResult extends CdpObject {
        public GetWindowForTargetResult() {}
        private GetWindowForTargetResult(Map<String, Object> values) { super(values); }
        public static GetWindowForTargetResult fromMap(Map<String, Object> values) {
            return new GetWindowForTargetResult(values);
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return require(WINDOW_ID);
        }
        /**
         * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
         * @return the protocol field value
         */
        public Browser.Bounds bounds() {
            return require(BOUNDS);
        }
        /**
         * Browser window id.
         * @param windowId field value
         * @return this model
         */
        public GetWindowForTargetResult windowId(Browser.WindowID windowId) {
            set("windowId", windowId);
            return this;
        }
        /**
         * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
         * @param bounds field value
         * @return this model
         */
        public GetWindowForTargetResult bounds(Browser.Bounds bounds) {
            set("bounds", bounds);
            return this;
        }
    }
    /**
     * Fired when page is about to start a download.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DownloadWillBeginEvent extends CdpObject {
        public DownloadWillBeginEvent() {}
        private DownloadWillBeginEvent(Map<String, Object> values) { super(values); }
        public static DownloadWillBeginEvent fromMap(Map<String, Object> values) {
            return new DownloadWillBeginEvent(values);
        }
        /**
         * Id of the frame that caused the download to begin.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return requireString("guid");
        }
        /**
         * URL of the resource being downloaded.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @return the protocol field value
         */
        public String suggestedFilename() {
            return requireString("suggestedFilename");
        }
        /**
         * Id of the frame that caused the download to begin.
         * @param frameId field value
         * @return this model
         */
        public DownloadWillBeginEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Global unique identifier of the download.
         * @param guid field value
         * @return this model
         */
        public DownloadWillBeginEvent guid(String guid) {
            set("guid", guid);
            return this;
        }
        /**
         * URL of the resource being downloaded.
         * @param url field value
         * @return this model
         */
        public DownloadWillBeginEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @param suggestedFilename field value
         * @return this model
         */
        public DownloadWillBeginEvent suggestedFilename(String suggestedFilename) {
            set("suggestedFilename", suggestedFilename);
            return this;
        }
    }
    /**
     * Fired when download makes progress. Last call has |done| == true.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DownloadProgressEvent extends CdpObject {
        public DownloadProgressEvent() {}
        private DownloadProgressEvent(Map<String, Object> values) { super(values); }
        public static DownloadProgressEvent fromMap(Map<String, Object> values) {
            return new DownloadProgressEvent(values);
        }
        /**
         * Download status.
         */
        public enum StateValues implements CdpValue<String> {
            INPROGRESS("inProgress"),
            COMPLETED("completed"),
            CANCELED("canceled");
            public final String value;
            StateValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static StateValues of(String value) {
                for (StateValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown StateValues value: " + value);
            }
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return requireString("guid");
        }
        /**
         * Total expected bytes to download.
         * @return the protocol field value
         */
        public double totalBytes() {
            return requireDouble("totalBytes");
        }
        /**
         * Total bytes received.
         * @return the protocol field value
         */
        public double receivedBytes() {
            return requireDouble("receivedBytes");
        }
        /**
         * Download status.
         * @return the protocol field value
         */
        public DownloadProgressEvent.StateValues state() {
            return require(STATE);
        }
        /**
         * If download is &quot;completed&quot;, provides the path of the downloaded file. Depending on the platform, it is not guaranteed to be set, nor the file is guaranteed to exist.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> filePath() {
            return optionalString("filePath");
        }
        /**
         * Global unique identifier of the download.
         * @param guid field value
         * @return this model
         */
        public DownloadProgressEvent guid(String guid) {
            set("guid", guid);
            return this;
        }
        /**
         * Total expected bytes to download.
         * @param totalBytes field value
         * @return this model
         */
        public DownloadProgressEvent totalBytes(double totalBytes) {
            set("totalBytes", totalBytes);
            return this;
        }
        /**
         * Total bytes received.
         * @param receivedBytes field value
         * @return this model
         */
        public DownloadProgressEvent receivedBytes(double receivedBytes) {
            set("receivedBytes", receivedBytes);
            return this;
        }
        /**
         * Download status.
         * @param state field value
         * @return this model
         */
        public DownloadProgressEvent state(DownloadProgressEvent.StateValues state) {
            set("state", state);
            return this;
        }
        /**
         * If download is &quot;completed&quot;, provides the path of the downloaded file. Depending on the platform, it is not guaranteed to be set, nor the file is guaranteed to exist.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param filePath field value
         * @return this model
         */
        public DownloadProgressEvent filePath(String filePath) {
            set("filePath", filePath);
            return this;
        }
    }
    /**
     * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
     */
    public enum SetDownloadBehaviorBehaviorValues implements CdpValue<String> {
        DENY("deny"),
        ALLOW("allow"),
        ALLOWANDNAME("allowAndName"),
        DEFAULT("default");
        public final String value;
        SetDownloadBehaviorBehaviorValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetDownloadBehaviorBehaviorValues of(String value) {
            for (SetDownloadBehaviorBehaviorValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetDownloadBehaviorBehaviorValues value: " + value);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permission protocol value
         * @param setting protocol value
         * @param origin protocol value
         * @param embeddedOrigin protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPermission(Browser.PermissionDescriptor permission, Browser.PermissionSetting setting, @Nullable String origin, @Nullable String embeddedOrigin, Browser.@Nullable BrowserContextID browserContextId) {
            return call("Browser.setPermission", params("permission", permission, "setting", setting, "origin", origin, "embeddedOrigin", embeddedOrigin, "browserContextId", browserContextId));
        }
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permission protocol value
         * @param setting protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPermission(Browser.PermissionDescriptor permission, Browser.PermissionSetting setting) {
            return call("Browser.setPermission", params("permission", permission, "setting", setting));
        }
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPermission(SetPermissionRequest request) {
            return call("Browser.setPermission", request);
        }
        /**
         * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permissions protocol value
         * @param origin protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> grantPermissions(java.util.List<Browser.PermissionType> permissions, @Nullable String origin, Browser.@Nullable BrowserContextID browserContextId) {
            return call("Browser.grantPermissions", params("permissions", permissions, "origin", origin, "browserContextId", browserContextId));
        }
        /**
         * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permissions protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> grantPermissions(java.util.List<Browser.PermissionType> permissions) {
            return call("Browser.grantPermissions", params("permissions", permissions));
        }
        /**
         * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> grantPermissions(GrantPermissionsRequest request) {
            return call("Browser.grantPermissions", request);
        }
        /**
         * Reset all permission management for all origins.
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPermissions(Browser.BrowserContextID browserContextId) {
            return call("Browser.resetPermissions", params("browserContextId", browserContextId));
        }
        /**
         * Reset all permission management for all origins.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPermissions() {
            return call("Browser.resetPermissions");
        }
        /**
         * Reset all permission management for all origins.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPermissions(ResetPermissionsRequest request) {
            return call("Browser.resetPermissions", request);
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         * @param browserContextId protocol value
         * @param downloadPath protocol value
         * @param eventsEnabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorBehaviorValues behavior, Browser.@Nullable BrowserContextID browserContextId, @Nullable String downloadPath, @Nullable Boolean eventsEnabled) {
            return call("Browser.setDownloadBehavior", params("behavior", behavior, "browserContextId", browserContextId, "downloadPath", downloadPath, "eventsEnabled", eventsEnabled));
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorBehaviorValues behavior) {
            return call("Browser.setDownloadBehavior", params("behavior", behavior));
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorRequest request) {
            return call("Browser.setDownloadBehavior", request);
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param guid protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDownload(String guid, Browser.BrowserContextID browserContextId) {
            return call("Browser.cancelDownload", params("guid", guid, "browserContextId", browserContextId));
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param guid protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDownload(String guid) {
            return call("Browser.cancelDownload", params("guid", guid));
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDownload(CancelDownloadRequest request) {
            return call("Browser.cancelDownload", request);
        }
        /**
         * Close browser gracefully.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close() {
            return call("Browser.close");
        }
        /**
         * Crashes browser on the main thread.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> crash() {
            return call("Browser.crash");
        }
        /**
         * Crashes GPU process.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> crashGpuProcess() {
            return call("Browser.crashGpuProcess");
        }
        /**
         * Returns version information.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetVersionResult> getVersion() {
            return call("Browser.getVersion", GetVersionResult::fromMap);
        }
        /**
         * Returns the command line switches for the browser process if, and only if --enable-automation is on the commandline.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getBrowserCommandLine() {
            return call("Browser.getBrowserCommandLine", ARGUMENTS::require);
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         * @param delta protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Browser.Histogram>> getHistograms(@Nullable String query, @Nullable Boolean delta) {
            return call("Browser.getHistograms", params("query", query, "delta", delta), HISTOGRAMS::require);
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Browser.Histogram>> getHistograms() {
            return call("Browser.getHistograms", HISTOGRAMS::require);
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Browser.Histogram>> getHistograms(GetHistogramsRequest request) {
            return call("Browser.getHistograms", request, HISTOGRAMS::require);
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param name protocol value
         * @param delta protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Histogram> getHistogram(String name, boolean delta) {
            return call("Browser.getHistogram", params("name", name, "delta", delta), HISTOGRAM::require);
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param name protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Histogram> getHistogram(String name) {
            return call("Browser.getHistogram", params("name", name), HISTOGRAM::require);
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Histogram> getHistogram(GetHistogramRequest request) {
            return call("Browser.getHistogram", request, HISTOGRAM::require);
        }
        /**
         * Get position and size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Bounds> getWindowBounds(Browser.WindowID windowId) {
            return call("Browser.getWindowBounds", params("windowId", windowId), BOUNDS::require);
        }
        /**
         * Get position and size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Bounds> getWindowBounds(GetWindowBoundsRequest request) {
            return call("Browser.getWindowBounds", request, BOUNDS::require);
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget(Target.TargetID targetId) {
            return call("Browser.getWindowForTarget", params("targetId", targetId), GetWindowForTargetResult::fromMap);
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget() {
            return call("Browser.getWindowForTarget", GetWindowForTargetResult::fromMap);
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget(GetWindowForTargetRequest request) {
            return call("Browser.getWindowForTarget", request, GetWindowForTargetResult::fromMap);
        }
        /**
         * Set position and/or size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @param bounds protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setWindowBounds(Browser.WindowID windowId, Browser.Bounds bounds) {
            return call("Browser.setWindowBounds", params("windowId", windowId, "bounds", bounds));
        }
        /**
         * Set position and/or size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setWindowBounds(SetWindowBoundsRequest request) {
            return call("Browser.setWindowBounds", request);
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @param width protocol value
         * @param height protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setContentsSize(Browser.WindowID windowId, @Nullable Long width, @Nullable Long height) {
            return call("Browser.setContentsSize", params("windowId", windowId, "width", width, "height", height));
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setContentsSize(Browser.WindowID windowId) {
            return call("Browser.setContentsSize", params("windowId", windowId));
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setContentsSize(SetContentsSizeRequest request) {
            return call("Browser.setContentsSize", request);
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param badgeLabel protocol value
         * @param image protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDockTile(@Nullable String badgeLabel, @Nullable String image) {
            return call("Browser.setDockTile", params("badgeLabel", badgeLabel, "image", image));
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDockTile() {
            return call("Browser.setDockTile");
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDockTile(SetDockTileRequest request) {
            return call("Browser.setDockTile", request);
        }
        /**
         * Invoke custom browser commands used by telemetry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param commandId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> executeBrowserCommand(Browser.BrowserCommandId commandId) {
            return call("Browser.executeBrowserCommand", params("commandId", commandId));
        }
        /**
         * Invoke custom browser commands used by telemetry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> executeBrowserCommand(ExecuteBrowserCommandRequest request) {
            return call("Browser.executeBrowserCommand", request);
        }
        /**
         * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
         * @param url protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxEnrollmentOverride(String url) {
            return call("Browser.addPrivacySandboxEnrollmentOverride", params("url", url));
        }
        /**
         * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxEnrollmentOverride(AddPrivacySandboxEnrollmentOverrideRequest request) {
            return call("Browser.addPrivacySandboxEnrollmentOverride", request);
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param api protocol value
         * @param coordinatorOrigin protocol value
         * @param keyConfig protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxCoordinatorKeyConfig(Browser.PrivacySandboxAPI api, String coordinatorOrigin, String keyConfig, Browser.BrowserContextID browserContextId) {
            return call("Browser.addPrivacySandboxCoordinatorKeyConfig", params("api", api, "coordinatorOrigin", coordinatorOrigin, "keyConfig", keyConfig, "browserContextId", browserContextId));
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param api protocol value
         * @param coordinatorOrigin protocol value
         * @param keyConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxCoordinatorKeyConfig(Browser.PrivacySandboxAPI api, String coordinatorOrigin, String keyConfig) {
            return call("Browser.addPrivacySandboxCoordinatorKeyConfig", params("api", api, "coordinatorOrigin", coordinatorOrigin, "keyConfig", keyConfig));
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxCoordinatorKeyConfig(AddPrivacySandboxCoordinatorKeyConfigRequest request) {
            return call("Browser.addPrivacySandboxCoordinatorKeyConfig", request);
        }
        /**
         * Fired when page is about to start a download.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDownloadWillBegin(Consumer<DownloadWillBeginEvent> handler) {
            return on("Browser.downloadWillBegin", DownloadWillBeginEvent::fromMap, handler);
        }
        /**
         * Fired when download makes progress. Last call has |done| == true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDownloadProgress(Consumer<DownloadProgressEvent> handler) {
            return on("Browser.downloadProgress", DownloadProgressEvent::fromMap, handler);
        }
    }
}
