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
 * The Browser domain defines methods and events for browser managing.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Browser.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
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
