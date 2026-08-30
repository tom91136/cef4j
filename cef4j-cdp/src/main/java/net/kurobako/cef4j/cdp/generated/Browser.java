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
 * The Browser domain defines methods and events for browser managing.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Browser.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Browser {
    private Browser() {}
    /**
     * Tagged String wire value for BrowserContextID.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BrowserContextID implements CdpValue<String> {
        public final String value;
        public BrowserContextID(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public Long value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static WindowState of(@Nonnull String value) {
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
            Long value = CdpObject.numberAsLong(raw("left"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The offset from the top edge of the screen to the window in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            Long value = CdpObject.numberAsLong(raw("top"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The window width in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            Long value = CdpObject.numberAsLong(raw("width"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The window height in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            Long value = CdpObject.numberAsLong(raw("height"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The window state. Default to normal.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.WindowState> windowState() {
            return Optional.ofNullable(raw("windowState") == null ? null : Browser.WindowState.of((String) raw("windowState")));
        }
        /**
         * The offset from the left edge of the screen to the window in pixels.
         * @param left field value; empty omits the value
         * @return this model
         */
        public Bounds left(OptionalLong left) {
            set("left", left.isPresent() ? left.getAsLong() : null);
            return this;
        }
        /**
         * The offset from the left edge of the screen to the window in pixels.
         * @param left field value; null removes the value
         * @return this model
         */
        public Bounds left(Long left) {
            set("left", left);
            return this;
        }
        /**
         * The offset from the top edge of the screen to the window in pixels.
         * @param top field value; empty omits the value
         * @return this model
         */
        public Bounds top(OptionalLong top) {
            set("top", top.isPresent() ? top.getAsLong() : null);
            return this;
        }
        /**
         * The offset from the top edge of the screen to the window in pixels.
         * @param top field value; null removes the value
         * @return this model
         */
        public Bounds top(Long top) {
            set("top", top);
            return this;
        }
        /**
         * The window width in pixels.
         * @param width field value; empty omits the value
         * @return this model
         */
        public Bounds width(OptionalLong width) {
            set("width", width.isPresent() ? width.getAsLong() : null);
            return this;
        }
        /**
         * The window width in pixels.
         * @param width field value; null removes the value
         * @return this model
         */
        public Bounds width(Long width) {
            set("width", width);
            return this;
        }
        /**
         * The window height in pixels.
         * @param height field value; empty omits the value
         * @return this model
         */
        public Bounds height(OptionalLong height) {
            set("height", height.isPresent() ? height.getAsLong() : null);
            return this;
        }
        /**
         * The window height in pixels.
         * @param height field value; null removes the value
         * @return this model
         */
        public Bounds height(Long height) {
            set("height", height);
            return this;
        }
        /**
         * The window state. Default to normal.
         * @param windowState field value; empty omits the value
         * @return this model
         */
        public Bounds windowState(Optional<Browser.WindowState> windowState) {
            set("windowState", windowState.orElse(null));
            return this;
        }
        /**
         * The window state. Default to normal.
         * @param windowState field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static PermissionType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static PermissionSetting of(@Nonnull String value) {
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
            return (String) require("name");
        }
        /**
         * For &quot;midi&quot; permission, may also specify sysex control.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> sysex() {
            return Optional.ofNullable((Boolean) raw("sysex"));
        }
        /**
         * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> userVisibleOnly() {
            return Optional.ofNullable((Boolean) raw("userVisibleOnly"));
        }
        /**
         * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowWithoutSanitization() {
            return Optional.ofNullable((Boolean) raw("allowWithoutSanitization"));
        }
        /**
         * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowWithoutGesture() {
            return Optional.ofNullable((Boolean) raw("allowWithoutGesture"));
        }
        /**
         * For &quot;camera&quot; permission, may specify panTiltZoom.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> panTiltZoom() {
            return Optional.ofNullable((Boolean) raw("panTiltZoom"));
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
         * @param sysex field value; empty omits the value
         * @return this model
         */
        public PermissionDescriptor sysex(Optional<Boolean> sysex) {
            set("sysex", sysex.orElse(null));
            return this;
        }
        /**
         * For &quot;midi&quot; permission, may also specify sysex control.
         * @param sysex field value; null removes the value
         * @return this model
         */
        public PermissionDescriptor sysex(Boolean sysex) {
            set("sysex", sysex);
            return this;
        }
        /**
         * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
         * @param userVisibleOnly field value; empty omits the value
         * @return this model
         */
        public PermissionDescriptor userVisibleOnly(Optional<Boolean> userVisibleOnly) {
            set("userVisibleOnly", userVisibleOnly.orElse(null));
            return this;
        }
        /**
         * For &quot;push&quot; permission, may specify userVisibleOnly. Note that userVisibleOnly = true is the only currently supported type.
         * @param userVisibleOnly field value; null removes the value
         * @return this model
         */
        public PermissionDescriptor userVisibleOnly(Boolean userVisibleOnly) {
            set("userVisibleOnly", userVisibleOnly);
            return this;
        }
        /**
         * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
         * @param allowWithoutSanitization field value; empty omits the value
         * @return this model
         */
        public PermissionDescriptor allowWithoutSanitization(Optional<Boolean> allowWithoutSanitization) {
            set("allowWithoutSanitization", allowWithoutSanitization.orElse(null));
            return this;
        }
        /**
         * For &quot;clipboard&quot; permission, may specify allowWithoutSanitization.
         * @param allowWithoutSanitization field value; null removes the value
         * @return this model
         */
        public PermissionDescriptor allowWithoutSanitization(Boolean allowWithoutSanitization) {
            set("allowWithoutSanitization", allowWithoutSanitization);
            return this;
        }
        /**
         * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
         * @param allowWithoutGesture field value; empty omits the value
         * @return this model
         */
        public PermissionDescriptor allowWithoutGesture(Optional<Boolean> allowWithoutGesture) {
            set("allowWithoutGesture", allowWithoutGesture.orElse(null));
            return this;
        }
        /**
         * For &quot;fullscreen&quot; permission, must specify allowWithoutGesture:true.
         * @param allowWithoutGesture field value; null removes the value
         * @return this model
         */
        public PermissionDescriptor allowWithoutGesture(Boolean allowWithoutGesture) {
            set("allowWithoutGesture", allowWithoutGesture);
            return this;
        }
        /**
         * For &quot;camera&quot; permission, may specify panTiltZoom.
         * @param panTiltZoom field value; empty omits the value
         * @return this model
         */
        public PermissionDescriptor panTiltZoom(Optional<Boolean> panTiltZoom) {
            set("panTiltZoom", panTiltZoom.orElse(null));
            return this;
        }
        /**
         * For &quot;camera&quot; permission, may specify panTiltZoom.
         * @param panTiltZoom field value; null removes the value
         * @return this model
         */
        public PermissionDescriptor panTiltZoom(Boolean panTiltZoom) {
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
        @Nonnull public String value() { return value; }
        public static BrowserCommandId of(@Nonnull String value) {
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
            return ((Number) require("low")).longValue();
        }
        /**
         * Maximum value (exclusive).
         * @return the protocol field value
         */
        public long high() {
            return ((Number) require("high")).longValue();
        }
        /**
         * Number of samples.
         * @return the protocol field value
         */
        public long count() {
            return ((Number) require("count")).longValue();
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
            return (String) require("name");
        }
        /**
         * Sum of sample values.
         * @return the protocol field value
         */
        public long sum() {
            return ((Number) require("sum")).longValue();
        }
        /**
         * Total number of samples.
         * @return the protocol field value
         */
        public long count() {
            return ((Number) require("count")).longValue();
        }
        /**
         * Buckets.
         * @return the protocol field value
         */
        public java.util.List<Browser.Bucket> buckets() {
            return CdpObject.requireList(require("buckets"), element0 -> java.util.Objects.requireNonNull(Browser.Bucket.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
        @Nonnull public String value() { return value; }
        public static PrivacySandboxAPI of(@Nonnull String value) {
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
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permission protocol value
         * @param setting protocol value
         */
        public SetPermissionRequest(Browser.PermissionDescriptor permission, Browser.PermissionSetting setting) {
            set("permission", permission);
            set("setting", setting);
        }
        public static SetPermissionRequest fromMap(Map<String, Object> values) {
            SetPermissionRequest instance_ = new SetPermissionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Descriptor of permission to override.
         * @return the protocol field value
         */
        public Browser.PermissionDescriptor permission() {
            return java.util.Objects.requireNonNull(Browser.PermissionDescriptor.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("permission")))));
        }
        /**
         * Setting of the permission.
         * @return the protocol field value
         */
        public Browser.PermissionSetting setting() {
            return Browser.PermissionSetting.of((String) require("setting"));
        }
        /**
         * Embedding origin the permission applies to, all origins if not specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> origin() {
            return Optional.ofNullable((String) raw("origin"));
        }
        /**
         * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> embeddedOrigin() {
            return Optional.ofNullable((String) raw("embeddedOrigin"));
        }
        /**
         * Context to override. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
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
         * @param origin field value; empty omits the value
         * @return this model
         */
        public SetPermissionRequest origin(Optional<String> origin) {
            set("origin", origin.orElse(null));
            return this;
        }
        /**
         * Embedding origin the permission applies to, all origins if not specified.
         * @param origin field value; null removes the value
         * @return this model
         */
        public SetPermissionRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
         * @param embeddedOrigin field value; empty omits the value
         * @return this model
         */
        public SetPermissionRequest embeddedOrigin(Optional<String> embeddedOrigin) {
            set("embeddedOrigin", embeddedOrigin.orElse(null));
            return this;
        }
        /**
         * Embedded origin the permission applies to. It is ignored unless the embedding origin is present and valid. If the embedding origin is provided but the embedded origin isn&#x27;t, the embedding origin is used as the embedded origin.
         * @param embeddedOrigin field value; null removes the value
         * @return this model
         */
        public SetPermissionRequest embeddedOrigin(String embeddedOrigin) {
            set("embeddedOrigin", embeddedOrigin);
            return this;
        }
        /**
         * Context to override. When omitted, default browser context is used.
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public SetPermissionRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * Context to override. When omitted, default browser context is used.
         * @param browserContextId field value; null removes the value
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
        /**
         * Grant specific permissions to the given origin and reject all others. Deprecated. Use setPermission instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permissions protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GrantPermissionsRequest(java.util.List<Browser.PermissionType> permissions) {
            set("permissions", permissions);
        }
        public static GrantPermissionsRequest fromMap(Map<String, Object> values) {
            GrantPermissionsRequest instance_ = new GrantPermissionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the permissions field.
         * @return the protocol field value
         */
        public java.util.List<Browser.PermissionType> permissions() {
            return CdpObject.requireList(require("permissions"), element0 -> Browser.PermissionType.of((String) element0));
        }
        /**
         * Origin the permission applies to, all origins if not specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> origin() {
            return Optional.ofNullable((String) raw("origin"));
        }
        /**
         * BrowserContext to override permissions. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
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
         * @param origin field value; empty omits the value
         * @return this model
         */
        public GrantPermissionsRequest origin(Optional<String> origin) {
            set("origin", origin.orElse(null));
            return this;
        }
        /**
         * Origin the permission applies to, all origins if not specified.
         * @param origin field value; null removes the value
         * @return this model
         */
        public GrantPermissionsRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * BrowserContext to override permissions. When omitted, default browser context is used.
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public GrantPermissionsRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * BrowserContext to override permissions. When omitted, default browser context is used.
         * @param browserContextId field value; null removes the value
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
            ResetPermissionsRequest instance_ = new ResetPermissionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * BrowserContext to reset permissions. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
        }
        /**
         * BrowserContext to reset permissions. When omitted, default browser context is used.
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public ResetPermissionsRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * BrowserContext to reset permissions. When omitted, default browser context is used.
         * @param browserContextId field value; null removes the value
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
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         */
        public SetDownloadBehaviorRequest(SetDownloadBehaviorBehaviorValues behavior) {
            set("behavior", behavior);
        }
        public static SetDownloadBehaviorRequest fromMap(Map<String, Object> values) {
            SetDownloadBehaviorRequest instance_ = new SetDownloadBehaviorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny). |allowAndName| allows download and names files according to their download guids.
         * @return the protocol field value
         */
        public SetDownloadBehaviorBehaviorValues behavior() {
            return SetDownloadBehaviorBehaviorValues.of((String) require("behavior"));
        }
        /**
         * BrowserContext to set download behavior. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> downloadPath() {
            return Optional.ofNullable((String) raw("downloadPath"));
        }
        /**
         * Whether to emit download events (defaults to false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> eventsEnabled() {
            return Optional.ofNullable((Boolean) raw("eventsEnabled"));
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
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public SetDownloadBehaviorRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * BrowserContext to set download behavior. When omitted, default browser context is used.
         * @param browserContextId field value; null removes the value
         * @return this model
         */
        public SetDownloadBehaviorRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
         * @param downloadPath field value; empty omits the value
         * @return this model
         */
        public SetDownloadBehaviorRequest downloadPath(Optional<String> downloadPath) {
            set("downloadPath", downloadPath.orElse(null));
            return this;
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27; or &#x27;allowAndName&#x27;.
         * @param downloadPath field value; null removes the value
         * @return this model
         */
        public SetDownloadBehaviorRequest downloadPath(String downloadPath) {
            set("downloadPath", downloadPath);
            return this;
        }
        /**
         * Whether to emit download events (defaults to false).
         * @param eventsEnabled field value; empty omits the value
         * @return this model
         */
        public SetDownloadBehaviorRequest eventsEnabled(Optional<Boolean> eventsEnabled) {
            set("eventsEnabled", eventsEnabled.orElse(null));
            return this;
        }
        /**
         * Whether to emit download events (defaults to false).
         * @param eventsEnabled field value; null removes the value
         * @return this model
         */
        public SetDownloadBehaviorRequest eventsEnabled(Boolean eventsEnabled) {
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
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param guid protocol value
         */
        public CancelDownloadRequest(String guid) {
            set("guid", guid);
        }
        public static CancelDownloadRequest fromMap(Map<String, Object> values) {
            CancelDownloadRequest instance_ = new CancelDownloadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return (String) require("guid");
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
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
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public CancelDownloadRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @param browserContextId field value; null removes the value
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
            GetHistogramsRequest instance_ = new GetHistogramsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> query() {
            return Optional.ofNullable((String) raw("query"));
        }
        /**
         * If true, retrieve delta since last delta call.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> delta() {
            return Optional.ofNullable((Boolean) raw("delta"));
        }
        /**
         * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
         * @param query field value; empty omits the value
         * @return this model
         */
        public GetHistogramsRequest query(Optional<String> query) {
            set("query", query.orElse(null));
            return this;
        }
        /**
         * Requested substring in name. Only histograms which have query as a substring in their name are extracted. An empty or absent query returns all histograms.
         * @param query field value; null removes the value
         * @return this model
         */
        public GetHistogramsRequest query(String query) {
            set("query", query);
            return this;
        }
        /**
         * If true, retrieve delta since last delta call.
         * @param delta field value; empty omits the value
         * @return this model
         */
        public GetHistogramsRequest delta(Optional<Boolean> delta) {
            set("delta", delta.orElse(null));
            return this;
        }
        /**
         * If true, retrieve delta since last delta call.
         * @param delta field value; null removes the value
         * @return this model
         */
        public GetHistogramsRequest delta(Boolean delta) {
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
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param name protocol value
         */
        public GetHistogramRequest(String name) {
            set("name", name);
        }
        public static GetHistogramRequest fromMap(Map<String, Object> values) {
            GetHistogramRequest instance_ = new GetHistogramRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Requested histogram name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * If true, retrieve delta since last delta call.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> delta() {
            return Optional.ofNullable((Boolean) raw("delta"));
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
         * @param delta field value; empty omits the value
         * @return this model
         */
        public GetHistogramRequest delta(Optional<Boolean> delta) {
            set("delta", delta.orElse(null));
            return this;
        }
        /**
         * If true, retrieve delta since last delta call.
         * @param delta field value; null removes the value
         * @return this model
         */
        public GetHistogramRequest delta(Boolean delta) {
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
        /**
         * Get position and size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         */
        public GetWindowBoundsRequest(Browser.WindowID windowId) {
            set("windowId", windowId);
        }
        public static GetWindowBoundsRequest fromMap(Map<String, Object> values) {
            GetWindowBoundsRequest instance_ = new GetWindowBoundsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return new Browser.WindowID(((Number) require("windowId")).longValue());
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
            GetWindowForTargetRequest instance_ = new GetWindowForTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Devtools agent host id. If called as a part of the session, associated targetId is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> targetId() {
            return Optional.ofNullable(raw("targetId") == null ? null : new Target.TargetID((String) raw("targetId")));
        }
        /**
         * Devtools agent host id. If called as a part of the session, associated targetId is used.
         * @param targetId field value; empty omits the value
         * @return this model
         */
        public GetWindowForTargetRequest targetId(Optional<Target.TargetID> targetId) {
            set("targetId", targetId.orElse(null));
            return this;
        }
        /**
         * Devtools agent host id. If called as a part of the session, associated targetId is used.
         * @param targetId field value; null removes the value
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
        /**
         * Set position and/or size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @param bounds protocol value
         */
        public SetWindowBoundsRequest(Browser.WindowID windowId, Browser.Bounds bounds) {
            set("windowId", windowId);
            set("bounds", bounds);
        }
        public static SetWindowBoundsRequest fromMap(Map<String, Object> values) {
            SetWindowBoundsRequest instance_ = new SetWindowBoundsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return new Browser.WindowID(((Number) require("windowId")).longValue());
        }
        /**
         * New window bounds. The &#x27;minimized&#x27;, &#x27;maximized&#x27; and &#x27;fullscreen&#x27; states cannot be combined with &#x27;left&#x27;, &#x27;top&#x27;, &#x27;width&#x27; or &#x27;height&#x27;. Leaves unspecified fields unchanged.
         * @return the protocol field value
         */
        public Browser.Bounds bounds() {
            return java.util.Objects.requireNonNull(Browser.Bounds.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("bounds")))));
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
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         */
        public SetContentsSizeRequest(Browser.WindowID windowId) {
            set("windowId", windowId);
        }
        public static SetContentsSizeRequest fromMap(Map<String, Object> values) {
            SetContentsSizeRequest instance_ = new SetContentsSizeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Browser window id.
         * @return the protocol field value
         */
        public Browser.WindowID windowId() {
            return new Browser.WindowID(((Number) require("windowId")).longValue());
        }
        /**
         * The window contents width in DIP. Assumes current width if omitted. Must be specified if &#x27;height&#x27; is omitted.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            Long value = CdpObject.numberAsLong(raw("width"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            Long value = CdpObject.numberAsLong(raw("height"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
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
         * @param width field value; empty omits the value
         * @return this model
         */
        public SetContentsSizeRequest width(OptionalLong width) {
            set("width", width.isPresent() ? width.getAsLong() : null);
            return this;
        }
        /**
         * The window contents width in DIP. Assumes current width if omitted. Must be specified if &#x27;height&#x27; is omitted.
         * @param width field value; null removes the value
         * @return this model
         */
        public SetContentsSizeRequest width(Long width) {
            set("width", width);
            return this;
        }
        /**
         * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
         * @param height field value; empty omits the value
         * @return this model
         */
        public SetContentsSizeRequest height(OptionalLong height) {
            set("height", height.isPresent() ? height.getAsLong() : null);
            return this;
        }
        /**
         * The window contents height in DIP. Assumes current height if omitted. Must be specified if &#x27;width&#x27; is omitted.
         * @param height field value; null removes the value
         * @return this model
         */
        public SetContentsSizeRequest height(Long height) {
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
            SetDockTileRequest instance_ = new SetDockTileRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the badgeLabel field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> badgeLabel() {
            return Optional.ofNullable((String) raw("badgeLabel"));
        }
        /**
         * Png encoded image. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> image() {
            return Optional.ofNullable((String) raw("image"));
        }
        /**
         * Sets the badgeLabel field.
         * @param badgeLabel field value; empty omits the value
         * @return this model
         */
        public SetDockTileRequest badgeLabel(Optional<String> badgeLabel) {
            set("badgeLabel", badgeLabel.orElse(null));
            return this;
        }
        /**
         * Sets the badgeLabel field.
         * @param badgeLabel field value; null removes the value
         * @return this model
         */
        public SetDockTileRequest badgeLabel(String badgeLabel) {
            set("badgeLabel", badgeLabel);
            return this;
        }
        /**
         * Png encoded image. (Encoded as a base64 string when passed over JSON)
         * @param image field value; empty omits the value
         * @return this model
         */
        public SetDockTileRequest image(Optional<String> image) {
            set("image", image.orElse(null));
            return this;
        }
        /**
         * Png encoded image. (Encoded as a base64 string when passed over JSON)
         * @param image field value; null removes the value
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
        /**
         * Invoke custom browser commands used by telemetry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param commandId protocol value
         */
        public ExecuteBrowserCommandRequest(Browser.BrowserCommandId commandId) {
            set("commandId", commandId);
        }
        public static ExecuteBrowserCommandRequest fromMap(Map<String, Object> values) {
            ExecuteBrowserCommandRequest instance_ = new ExecuteBrowserCommandRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the commandId field.
         * @return the protocol field value
         */
        public Browser.BrowserCommandId commandId() {
            return Browser.BrowserCommandId.of((String) require("commandId"));
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
        /**
         * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
         * @param url protocol value
         */
        public AddPrivacySandboxEnrollmentOverrideRequest(String url) {
            set("url", url);
        }
        public static AddPrivacySandboxEnrollmentOverrideRequest fromMap(Map<String, Object> values) {
            AddPrivacySandboxEnrollmentOverrideRequest instance_ = new AddPrivacySandboxEnrollmentOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
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
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param api protocol value
         * @param coordinatorOrigin protocol value
         * @param keyConfig protocol value
         */
        public AddPrivacySandboxCoordinatorKeyConfigRequest(Browser.PrivacySandboxAPI api, String coordinatorOrigin, String keyConfig) {
            set("api", api);
            set("coordinatorOrigin", coordinatorOrigin);
            set("keyConfig", keyConfig);
        }
        public static AddPrivacySandboxCoordinatorKeyConfigRequest fromMap(Map<String, Object> values) {
            AddPrivacySandboxCoordinatorKeyConfigRequest instance_ = new AddPrivacySandboxCoordinatorKeyConfigRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the api field.
         * @return the protocol field value
         */
        public Browser.PrivacySandboxAPI api() {
            return Browser.PrivacySandboxAPI.of((String) require("api"));
        }
        /**
         * Returns the coordinatorOrigin field.
         * @return the protocol field value
         */
        public String coordinatorOrigin() {
            return (String) require("coordinatorOrigin");
        }
        /**
         * Returns the keyConfig field.
         * @return the protocol field value
         */
        public String keyConfig() {
            return (String) require("keyConfig");
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
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
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public AddPrivacySandboxCoordinatorKeyConfigRequest browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * BrowserContext to perform the action in. When omitted, default browser context is used.
         * @param browserContextId field value; null removes the value
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
            return (String) require("protocolVersion");
        }
        /**
         * Product name.
         * @return the protocol field value
         */
        public String product() {
            return (String) require("product");
        }
        /**
         * Product revision.
         * @return the protocol field value
         */
        public String revision() {
            return (String) require("revision");
        }
        /**
         * User-Agent.
         * @return the protocol field value
         */
        public String userAgent() {
            return (String) require("userAgent");
        }
        /**
         * V8 version.
         * @return the protocol field value
         */
        public String jsVersion() {
            return (String) require("jsVersion");
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
            return new Browser.WindowID(((Number) require("windowId")).longValue());
        }
        /**
         * Bounds information of the window. When window state is &#x27;minimized&#x27;, the restored window position and size are returned.
         * @return the protocol field value
         */
        public Browser.Bounds bounds() {
            return java.util.Objects.requireNonNull(Browser.Bounds.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("bounds")))));
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
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return (String) require("guid");
        }
        /**
         * URL of the resource being downloaded.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @return the protocol field value
         */
        public String suggestedFilename() {
            return (String) require("suggestedFilename");
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
            @Nonnull public String value() { return value; }
            public static StateValues of(@Nonnull String value) {
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
            return (String) require("guid");
        }
        /**
         * Total expected bytes to download.
         * @return the protocol field value
         */
        public double totalBytes() {
            return ((Number) require("totalBytes")).doubleValue();
        }
        /**
         * Total bytes received.
         * @return the protocol field value
         */
        public double receivedBytes() {
            return ((Number) require("receivedBytes")).doubleValue();
        }
        /**
         * Download status.
         * @return the protocol field value
         */
        public DownloadProgressEvent.StateValues state() {
            return DownloadProgressEvent.StateValues.of((String) require("state"));
        }
        /**
         * If download is &quot;completed&quot;, provides the path of the downloaded file. Depending on the platform, it is not guaranteed to be set, nor the file is guaranteed to exist.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> filePath() {
            return Optional.ofNullable((String) raw("filePath"));
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
         * @param filePath field value; empty omits the value
         * @return this model
         */
        public DownloadProgressEvent filePath(Optional<String> filePath) {
            set("filePath", filePath.orElse(null));
            return this;
        }
        /**
         * If download is &quot;completed&quot;, provides the path of the downloaded file. Depending on the platform, it is not guaranteed to be set, nor the file is guaranteed to exist.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param filePath field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static SetDownloadBehaviorBehaviorValues of(@Nonnull String value) {
            for (SetDownloadBehaviorBehaviorValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetDownloadBehaviorBehaviorValues value: " + value);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
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
        public CompletionStage<Void> setPermission(Browser.PermissionDescriptor permission, Browser.PermissionSetting setting, Optional<String> origin, Optional<String> embeddedOrigin, Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("permission", CdpObject.json(permission));
            params.put("setting", CdpObject.json(setting));
            origin.ifPresent(value_ -> params.put("origin", CdpObject.json(value_)));
            embeddedOrigin.ifPresent(value_ -> params.put("embeddedOrigin", CdpObject.json(value_)));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Browser.setPermission", params, result_ -> null);
        }
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param permission protocol value
         * @param setting protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPermission(Browser.PermissionDescriptor permission, Browser.PermissionSetting setting) {
            return setPermission(permission, setting, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Set permission settings for given embedding and embedded origins.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPermission(SetPermissionRequest request) {
            return client.call("Browser.setPermission", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Void> grantPermissions(java.util.List<Browser.PermissionType> permissions, Optional<String> origin, Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("permissions", CdpObject.json(permissions));
            origin.ifPresent(value_ -> params.put("origin", CdpObject.json(value_)));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Browser.grantPermissions", params, result_ -> null);
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
            return grantPermissions(permissions, Optional.empty(), Optional.empty());
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
            return client.call("Browser.grantPermissions", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Reset all permission management for all origins.
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPermissions(Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Browser.resetPermissions", params, result_ -> null);
        }
        /**
         * Reset all permission management for all origins.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPermissions() {
            return resetPermissions(Optional.empty());
        }
        /**
         * Reset all permission management for all origins.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPermissions(ResetPermissionsRequest request) {
            return client.call("Browser.resetPermissions", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorBehaviorValues behavior, Optional<Browser.BrowserContextID> browserContextId, Optional<String> downloadPath, Optional<Boolean> eventsEnabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("behavior", CdpObject.json(behavior));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            downloadPath.ifPresent(value_ -> params.put("downloadPath", CdpObject.json(value_)));
            eventsEnabled.ifPresent(value_ -> params.put("eventsEnabled", value_));
            return client.call("Browser.setDownloadBehavior", params, result_ -> null);
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorBehaviorValues behavior) {
            return setDownloadBehavior(behavior, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorRequest request) {
            return client.call("Browser.setDownloadBehavior", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param guid protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDownload(String guid, Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("guid", CdpObject.json(guid));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Browser.cancelDownload", params, result_ -> null);
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param guid protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDownload(String guid) {
            return cancelDownload(guid, Optional.empty());
        }
        /**
         * Cancel a download if in progress
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDownload(CancelDownloadRequest request) {
            return client.call("Browser.cancelDownload", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Close browser gracefully.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close() {
            return client.call("Browser.close", null, result_ -> null);
        }
        /**
         * Crashes browser on the main thread.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> crash() {
            return client.call("Browser.crash", null, result_ -> null);
        }
        /**
         * Crashes GPU process.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> crashGpuProcess() {
            return client.call("Browser.crashGpuProcess", null, result_ -> null);
        }
        /**
         * Returns version information.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetVersionResult> getVersion() {
            return client.call("Browser.getVersion", null, result_ -> new GetVersionResult(result_));
        }
        /**
         * Returns the command line switches for the browser process if, and only if --enable-automation is on the commandline.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getBrowserCommandLine() {
            return client.call("Browser.getBrowserCommandLine", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("arguments")), element0 -> (String) element0));
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         * @param delta protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Browser.Histogram>> getHistograms(Optional<String> query, Optional<Boolean> delta) {
            Map<String, Object> params = new LinkedHashMap<>();
            query.ifPresent(value_ -> params.put("query", CdpObject.json(value_)));
            delta.ifPresent(value_ -> params.put("delta", value_));
            return client.call("Browser.getHistograms", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("histograms")), element0 -> java.util.Objects.requireNonNull(Browser.Histogram.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Browser.Histogram>> getHistograms() {
            return getHistograms(Optional.empty(), Optional.empty());
        }
        /**
         * Get Chrome histograms.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Browser.Histogram>> getHistograms(GetHistogramsRequest request) {
            return client.call("Browser.getHistograms", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("histograms")), element0 -> java.util.Objects.requireNonNull(Browser.Histogram.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param name protocol value
         * @param delta protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Histogram> getHistogram(String name, Optional<Boolean> delta) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            delta.ifPresent(value_ -> params.put("delta", value_));
            return client.call("Browser.getHistogram", params, result_ -> java.util.Objects.requireNonNull(Browser.Histogram.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("histogram")))))));
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param name protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Histogram> getHistogram(String name) {
            return getHistogram(name, Optional.empty());
        }
        /**
         * Get a Chrome histogram by name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Histogram> getHistogram(GetHistogramRequest request) {
            return client.call("Browser.getHistogram", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Browser.Histogram.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("histogram")))))));
        }
        /**
         * Get position and size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Bounds> getWindowBounds(Browser.WindowID windowId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("windowId", CdpObject.json(windowId));
            return client.call("Browser.getWindowBounds", params, result_ -> java.util.Objects.requireNonNull(Browser.Bounds.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("bounds")))))));
        }
        /**
         * Get position and size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.Bounds> getWindowBounds(GetWindowBoundsRequest request) {
            return client.call("Browser.getWindowBounds", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Browser.Bounds.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("bounds")))))));
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget(Optional<Target.TargetID> targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            targetId.ifPresent(value_ -> params.put("targetId", CdpObject.json(value_)));
            return client.call("Browser.getWindowForTarget", params, result_ -> new GetWindowForTargetResult(result_));
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget() {
            return getWindowForTarget(Optional.empty());
        }
        /**
         * Get the browser window that contains the devtools target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetWindowForTargetResult> getWindowForTarget(GetWindowForTargetRequest request) {
            return client.call("Browser.getWindowForTarget", request == null ? null : request.toMap(), result_ -> new GetWindowForTargetResult(result_));
        }
        /**
         * Set position and/or size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @param bounds protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setWindowBounds(Browser.WindowID windowId, Browser.Bounds bounds) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("windowId", CdpObject.json(windowId));
            params.put("bounds", CdpObject.json(bounds));
            return client.call("Browser.setWindowBounds", params, result_ -> null);
        }
        /**
         * Set position and/or size of the browser window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setWindowBounds(SetWindowBoundsRequest request) {
            return client.call("Browser.setWindowBounds", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @param width protocol value
         * @param height protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setContentsSize(Browser.WindowID windowId, OptionalLong width, OptionalLong height) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("windowId", CdpObject.json(windowId));
            width.ifPresent(value_ -> params.put("width", value_));
            height.ifPresent(value_ -> params.put("height", value_));
            return client.call("Browser.setContentsSize", params, result_ -> null);
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param windowId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setContentsSize(Browser.WindowID windowId) {
            return setContentsSize(windowId, OptionalLong.empty(), OptionalLong.empty());
        }
        /**
         * Set size of the browser contents resizing browser window as necessary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setContentsSize(SetContentsSizeRequest request) {
            return client.call("Browser.setContentsSize", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param badgeLabel protocol value
         * @param image protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDockTile(Optional<String> badgeLabel, Optional<String> image) {
            Map<String, Object> params = new LinkedHashMap<>();
            badgeLabel.ifPresent(value_ -> params.put("badgeLabel", CdpObject.json(value_)));
            image.ifPresent(value_ -> params.put("image", CdpObject.json(value_)));
            return client.call("Browser.setDockTile", params, result_ -> null);
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDockTile() {
            return setDockTile(Optional.empty(), Optional.empty());
        }
        /**
         * Set dock tile details, platform-specific.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDockTile(SetDockTileRequest request) {
            return client.call("Browser.setDockTile", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invoke custom browser commands used by telemetry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param commandId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> executeBrowserCommand(Browser.BrowserCommandId commandId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("commandId", CdpObject.json(commandId));
            return client.call("Browser.executeBrowserCommand", params, result_ -> null);
        }
        /**
         * Invoke custom browser commands used by telemetry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> executeBrowserCommand(ExecuteBrowserCommandRequest request) {
            return client.call("Browser.executeBrowserCommand", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
         * @param url protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxEnrollmentOverride(String url) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            return client.call("Browser.addPrivacySandboxEnrollmentOverride", params, result_ -> null);
        }
        /**
         * Allows a site to use privacy sandbox features that require enrollment without the site actually being enrolled. Only supported on page targets.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxEnrollmentOverride(AddPrivacySandboxEnrollmentOverrideRequest request) {
            return client.call("Browser.addPrivacySandboxEnrollmentOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param api protocol value
         * @param coordinatorOrigin protocol value
         * @param keyConfig protocol value
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxCoordinatorKeyConfig(Browser.PrivacySandboxAPI api, String coordinatorOrigin, String keyConfig, Optional<Browser.BrowserContextID> browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("api", CdpObject.json(api));
            params.put("coordinatorOrigin", CdpObject.json(coordinatorOrigin));
            params.put("keyConfig", CdpObject.json(keyConfig));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            return client.call("Browser.addPrivacySandboxCoordinatorKeyConfig", params, result_ -> null);
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param api protocol value
         * @param coordinatorOrigin protocol value
         * @param keyConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxCoordinatorKeyConfig(Browser.PrivacySandboxAPI api, String coordinatorOrigin, String keyConfig) {
            return addPrivacySandboxCoordinatorKeyConfig(api, coordinatorOrigin, keyConfig, Optional.empty());
        }
        /**
         * Configures encryption keys used with a given privacy sandbox API to talk to a trusted coordinator. Since this is intended for test automation only, coordinatorOrigin must be a .test domain. No existing coordinator configuration for the origin may exist.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addPrivacySandboxCoordinatorKeyConfig(AddPrivacySandboxCoordinatorKeyConfigRequest request) {
            return client.call("Browser.addPrivacySandboxCoordinatorKeyConfig", request == null ? null : request.toMap(), result_ -> null);
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
