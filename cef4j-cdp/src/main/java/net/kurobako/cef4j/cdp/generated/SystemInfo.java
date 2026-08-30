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
 * The SystemInfo domain defines methods and events for querying low-level system information.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/SystemInfo.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class SystemInfo {
    private SystemInfo() {}
    /**
     * Describes a single graphics processor (GPU).
     */
    public static final class GPUDevice extends CdpObject {
        public GPUDevice() {}
        private GPUDevice(Map<String, Object> values) { super(values); }
        public static GPUDevice fromMap(Map<String, Object> values) {
            return new GPUDevice(values);
        }
        /**
         * PCI ID of the GPU vendor, if available; 0 otherwise.
         * @return the protocol field value
         */
        public double vendorId() {
            return ((Number) require("vendorId")).doubleValue();
        }
        /**
         * PCI ID of the GPU device, if available; 0 otherwise.
         * @return the protocol field value
         */
        public double deviceId() {
            return ((Number) require("deviceId")).doubleValue();
        }
        /**
         * Sub sys ID of the GPU, only available on Windows.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble subSysId() {
            Double value = CdpObject.numberAsDouble(raw("subSysId"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Revision of the GPU, only available on Windows.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble revision() {
            Double value = CdpObject.numberAsDouble(raw("revision"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * String description of the GPU vendor, if the PCI ID is not available.
         * @return the protocol field value
         */
        public String vendorString() {
            return (String) require("vendorString");
        }
        /**
         * String description of the GPU device, if the PCI ID is not available.
         * @return the protocol field value
         */
        public String deviceString() {
            return (String) require("deviceString");
        }
        /**
         * String description of the GPU driver vendor.
         * @return the protocol field value
         */
        public String driverVendor() {
            return (String) require("driverVendor");
        }
        /**
         * String description of the GPU driver version.
         * @return the protocol field value
         */
        public String driverVersion() {
            return (String) require("driverVersion");
        }
        /**
         * PCI ID of the GPU vendor, if available; 0 otherwise.
         * @param vendorId field value
         * @return this model
         */
        public GPUDevice vendorId(double vendorId) {
            set("vendorId", vendorId);
            return this;
        }
        /**
         * PCI ID of the GPU device, if available; 0 otherwise.
         * @param deviceId field value
         * @return this model
         */
        public GPUDevice deviceId(double deviceId) {
            set("deviceId", deviceId);
            return this;
        }
        /**
         * Sub sys ID of the GPU, only available on Windows.
         * @param subSysId field value; empty omits the value
         * @return this model
         */
        public GPUDevice subSysId(OptionalDouble subSysId) {
            set("subSysId", subSysId.isPresent() ? subSysId.getAsDouble() : null);
            return this;
        }
        /**
         * Sub sys ID of the GPU, only available on Windows.
         * @param subSysId field value; null removes the value
         * @return this model
         */
        public GPUDevice subSysId(Double subSysId) {
            set("subSysId", subSysId);
            return this;
        }
        /**
         * Revision of the GPU, only available on Windows.
         * @param revision field value; empty omits the value
         * @return this model
         */
        public GPUDevice revision(OptionalDouble revision) {
            set("revision", revision.isPresent() ? revision.getAsDouble() : null);
            return this;
        }
        /**
         * Revision of the GPU, only available on Windows.
         * @param revision field value; null removes the value
         * @return this model
         */
        public GPUDevice revision(Double revision) {
            set("revision", revision);
            return this;
        }
        /**
         * String description of the GPU vendor, if the PCI ID is not available.
         * @param vendorString field value
         * @return this model
         */
        public GPUDevice vendorString(String vendorString) {
            set("vendorString", vendorString);
            return this;
        }
        /**
         * String description of the GPU device, if the PCI ID is not available.
         * @param deviceString field value
         * @return this model
         */
        public GPUDevice deviceString(String deviceString) {
            set("deviceString", deviceString);
            return this;
        }
        /**
         * String description of the GPU driver vendor.
         * @param driverVendor field value
         * @return this model
         */
        public GPUDevice driverVendor(String driverVendor) {
            set("driverVendor", driverVendor);
            return this;
        }
        /**
         * String description of the GPU driver version.
         * @param driverVersion field value
         * @return this model
         */
        public GPUDevice driverVersion(String driverVersion) {
            set("driverVersion", driverVersion);
            return this;
        }
    }
    /**
     * Describes the width and height dimensions of an entity.
     */
    public static final class Size extends CdpObject {
        public Size() {}
        private Size(Map<String, Object> values) { super(values); }
        public static Size fromMap(Map<String, Object> values) {
            return new Size(values);
        }
        /**
         * Width in pixels.
         * @return the protocol field value
         */
        public long width() {
            return ((Number) require("width")).longValue();
        }
        /**
         * Height in pixels.
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
        }
        /**
         * Width in pixels.
         * @param width field value
         * @return this model
         */
        public Size width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Height in pixels.
         * @param height field value
         * @return this model
         */
        public Size height(long height) {
            set("height", height);
            return this;
        }
    }
    /**
     * Describes a supported video decoding profile with its associated minimum and maximum resolutions.
     */
    public static final class VideoDecodeAcceleratorCapability extends CdpObject {
        public VideoDecodeAcceleratorCapability() {}
        private VideoDecodeAcceleratorCapability(Map<String, Object> values) { super(values); }
        public static VideoDecodeAcceleratorCapability fromMap(Map<String, Object> values) {
            return new VideoDecodeAcceleratorCapability(values);
        }
        /**
         * Video codec profile that is supported, e.g. VP9 Profile 2.
         * @return the protocol field value
         */
        public String profile() {
            return (String) require("profile");
        }
        /**
         * Maximum video dimensions in pixels supported for this |profile|.
         * @return the protocol field value
         */
        public SystemInfo.Size maxResolution() {
            return java.util.Objects.requireNonNull(SystemInfo.Size.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("maxResolution")))));
        }
        /**
         * Minimum video dimensions in pixels supported for this |profile|.
         * @return the protocol field value
         */
        public SystemInfo.Size minResolution() {
            return java.util.Objects.requireNonNull(SystemInfo.Size.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("minResolution")))));
        }
        /**
         * Video codec profile that is supported, e.g. VP9 Profile 2.
         * @param profile field value
         * @return this model
         */
        public VideoDecodeAcceleratorCapability profile(String profile) {
            set("profile", profile);
            return this;
        }
        /**
         * Maximum video dimensions in pixels supported for this |profile|.
         * @param maxResolution field value
         * @return this model
         */
        public VideoDecodeAcceleratorCapability maxResolution(SystemInfo.Size maxResolution) {
            set("maxResolution", maxResolution);
            return this;
        }
        /**
         * Minimum video dimensions in pixels supported for this |profile|.
         * @param minResolution field value
         * @return this model
         */
        public VideoDecodeAcceleratorCapability minResolution(SystemInfo.Size minResolution) {
            set("minResolution", minResolution);
            return this;
        }
    }
    /**
     * Describes a supported video encoding profile with its associated maximum resolution and maximum framerate.
     */
    public static final class VideoEncodeAcceleratorCapability extends CdpObject {
        public VideoEncodeAcceleratorCapability() {}
        private VideoEncodeAcceleratorCapability(Map<String, Object> values) { super(values); }
        public static VideoEncodeAcceleratorCapability fromMap(Map<String, Object> values) {
            return new VideoEncodeAcceleratorCapability(values);
        }
        /**
         * Video codec profile that is supported, e.g H264 Main.
         * @return the protocol field value
         */
        public String profile() {
            return (String) require("profile");
        }
        /**
         * Maximum video dimensions in pixels supported for this |profile|.
         * @return the protocol field value
         */
        public SystemInfo.Size maxResolution() {
            return java.util.Objects.requireNonNull(SystemInfo.Size.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("maxResolution")))));
        }
        /**
         * Maximum encoding framerate in frames per second supported for this |profile|, as fraction&#x27;s numerator and denominator, e.g. 24/1 fps, 24000/1001 fps, etc.
         * @return the protocol field value
         */
        public long maxFramerateNumerator() {
            return ((Number) require("maxFramerateNumerator")).longValue();
        }
        /**
         * Returns the maxFramerateDenominator field.
         * @return the protocol field value
         */
        public long maxFramerateDenominator() {
            return ((Number) require("maxFramerateDenominator")).longValue();
        }
        /**
         * Video codec profile that is supported, e.g H264 Main.
         * @param profile field value
         * @return this model
         */
        public VideoEncodeAcceleratorCapability profile(String profile) {
            set("profile", profile);
            return this;
        }
        /**
         * Maximum video dimensions in pixels supported for this |profile|.
         * @param maxResolution field value
         * @return this model
         */
        public VideoEncodeAcceleratorCapability maxResolution(SystemInfo.Size maxResolution) {
            set("maxResolution", maxResolution);
            return this;
        }
        /**
         * Maximum encoding framerate in frames per second supported for this |profile|, as fraction&#x27;s numerator and denominator, e.g. 24/1 fps, 24000/1001 fps, etc.
         * @param maxFramerateNumerator field value
         * @return this model
         */
        public VideoEncodeAcceleratorCapability maxFramerateNumerator(long maxFramerateNumerator) {
            set("maxFramerateNumerator", maxFramerateNumerator);
            return this;
        }
        /**
         * Sets the maxFramerateDenominator field.
         * @param maxFramerateDenominator field value
         * @return this model
         */
        public VideoEncodeAcceleratorCapability maxFramerateDenominator(long maxFramerateDenominator) {
            set("maxFramerateDenominator", maxFramerateDenominator);
            return this;
        }
    }
    /**
     * YUV subsampling type of the pixels of a given image.
     */
    public enum SubsamplingFormat implements CdpValue<String> {
        YUV420("yuv420"),
        YUV422("yuv422"),
        YUV444("yuv444");
        public final String value;
        SubsamplingFormat(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static SubsamplingFormat of(@Nonnull String value) {
            for (SubsamplingFormat constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SubsamplingFormat value: " + value);
        }
    }
    /**
     * Image format of a given image.
     */
    public enum ImageType implements CdpValue<String> {
        JPEG("jpeg"),
        WEBP("webp"),
        UNKNOWN("unknown");
        public final String value;
        ImageType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ImageType of(@Nonnull String value) {
            for (ImageType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ImageType value: " + value);
        }
    }
    /**
     * Provides information about the GPU(s) on the system.
     */
    public static final class GPUInfo extends CdpObject {
        public GPUInfo() {}
        private GPUInfo(Map<String, Object> values) { super(values); }
        public static GPUInfo fromMap(Map<String, Object> values) {
            return new GPUInfo(values);
        }
        /**
         * The graphics devices on the system. Element 0 is the primary GPU.
         * @return the protocol field value
         */
        public java.util.List<SystemInfo.GPUDevice> devices() {
            return CdpObject.requireList(require("devices"), element0 -> java.util.Objects.requireNonNull(SystemInfo.GPUDevice.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * An optional dictionary of additional GPU related attributes.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> auxAttributes() {
            return Optional.ofNullable(objectMap(raw("auxAttributes")));
        }
        /**
         * An optional dictionary of graphics features and their status.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> featureStatus() {
            return Optional.ofNullable(objectMap(raw("featureStatus")));
        }
        /**
         * An optional array of GPU driver bug workarounds.
         * @return the protocol field value
         */
        public java.util.List<String> driverBugWorkarounds() {
            return CdpObject.requireList(require("driverBugWorkarounds"), element0 -> (String) element0);
        }
        /**
         * Supported accelerated video decoding capabilities.
         * @return the protocol field value
         */
        public java.util.List<SystemInfo.VideoDecodeAcceleratorCapability> videoDecoding() {
            return CdpObject.requireList(require("videoDecoding"), element0 -> java.util.Objects.requireNonNull(SystemInfo.VideoDecodeAcceleratorCapability.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Supported accelerated video encoding capabilities.
         * @return the protocol field value
         */
        public java.util.List<SystemInfo.VideoEncodeAcceleratorCapability> videoEncoding() {
            return CdpObject.requireList(require("videoEncoding"), element0 -> java.util.Objects.requireNonNull(SystemInfo.VideoEncodeAcceleratorCapability.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * The graphics devices on the system. Element 0 is the primary GPU.
         * @param devices field value
         * @return this model
         */
        public GPUInfo devices(java.util.List<SystemInfo.GPUDevice> devices) {
            set("devices", devices);
            return this;
        }
        /**
         * An optional dictionary of additional GPU related attributes.
         * @param auxAttributes field value; empty omits the value
         * @return this model
         */
        public GPUInfo auxAttributes(Optional<java.util.Map<String, Object>> auxAttributes) {
            set("auxAttributes", auxAttributes.orElse(null));
            return this;
        }
        /**
         * An optional dictionary of additional GPU related attributes.
         * @param auxAttributes field value; null removes the value
         * @return this model
         */
        public GPUInfo auxAttributes(java.util.Map<String, Object> auxAttributes) {
            set("auxAttributes", auxAttributes);
            return this;
        }
        /**
         * An optional dictionary of graphics features and their status.
         * @param featureStatus field value; empty omits the value
         * @return this model
         */
        public GPUInfo featureStatus(Optional<java.util.Map<String, Object>> featureStatus) {
            set("featureStatus", featureStatus.orElse(null));
            return this;
        }
        /**
         * An optional dictionary of graphics features and their status.
         * @param featureStatus field value; null removes the value
         * @return this model
         */
        public GPUInfo featureStatus(java.util.Map<String, Object> featureStatus) {
            set("featureStatus", featureStatus);
            return this;
        }
        /**
         * An optional array of GPU driver bug workarounds.
         * @param driverBugWorkarounds field value
         * @return this model
         */
        public GPUInfo driverBugWorkarounds(java.util.List<String> driverBugWorkarounds) {
            set("driverBugWorkarounds", driverBugWorkarounds);
            return this;
        }
        /**
         * Supported accelerated video decoding capabilities.
         * @param videoDecoding field value
         * @return this model
         */
        public GPUInfo videoDecoding(java.util.List<SystemInfo.VideoDecodeAcceleratorCapability> videoDecoding) {
            set("videoDecoding", videoDecoding);
            return this;
        }
        /**
         * Supported accelerated video encoding capabilities.
         * @param videoEncoding field value
         * @return this model
         */
        public GPUInfo videoEncoding(java.util.List<SystemInfo.VideoEncodeAcceleratorCapability> videoEncoding) {
            set("videoEncoding", videoEncoding);
            return this;
        }
    }
    /**
     * Represents process info.
     */
    public static final class ProcessInfo extends CdpObject {
        public ProcessInfo() {}
        private ProcessInfo(Map<String, Object> values) { super(values); }
        public static ProcessInfo fromMap(Map<String, Object> values) {
            return new ProcessInfo(values);
        }
        /**
         * Specifies process type.
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
        }
        /**
         * Specifies process id.
         * @return the protocol field value
         */
        public long id() {
            return ((Number) require("id")).longValue();
        }
        /**
         * Specifies cumulative CPU usage in seconds across all threads of the process since the process start.
         * @return the protocol field value
         */
        public double cpuTime() {
            return ((Number) require("cpuTime")).doubleValue();
        }
        /**
         * Specifies process type.
         * @param type field value
         * @return this model
         */
        public ProcessInfo type(String type) {
            set("type", type);
            return this;
        }
        /**
         * Specifies process id.
         * @param id field value
         * @return this model
         */
        public ProcessInfo id(long id) {
            set("id", id);
            return this;
        }
        /**
         * Specifies cumulative CPU usage in seconds across all threads of the process since the process start.
         * @param cpuTime field value
         * @return this model
         */
        public ProcessInfo cpuTime(double cpuTime) {
            set("cpuTime", cpuTime);
            return this;
        }
    }
    /**
     * Returns information about the feature state.
     */
    public static final class GetFeatureStateRequest extends CdpObject {
        public GetFeatureStateRequest() {}
        /**
         * Returns information about the feature state.
         * @param featureState protocol value
         */
        public GetFeatureStateRequest(String featureState) {
            set("featureState", featureState);
        }
        public static GetFeatureStateRequest fromMap(Map<String, Object> values) {
            GetFeatureStateRequest instance_ = new GetFeatureStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the featureState field.
         * @return the protocol field value
         */
        public String featureState() {
            return (String) require("featureState");
        }
        /**
         * Sets the featureState field.
         * @param featureState field value
         * @return this model
         */
        public GetFeatureStateRequest featureState(String featureState) {
            set("featureState", featureState);
            return this;
        }
    }
    /**
     * Returns information about the system.
     */
    public static final class GetInfoResult extends CdpObject {
        public GetInfoResult() {}
        private GetInfoResult(Map<String, Object> values) { super(values); }
        public static GetInfoResult fromMap(Map<String, Object> values) {
            return new GetInfoResult(values);
        }
        /**
         * Information about the GPUs on the system.
         * @return the protocol field value
         */
        public SystemInfo.GPUInfo gpu() {
            return java.util.Objects.requireNonNull(SystemInfo.GPUInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("gpu")))));
        }
        /**
         * A platform-dependent description of the model of the machine. On Mac OS, this is, for example, &#x27;MacBookPro&#x27;. Will be the empty string if not supported.
         * @return the protocol field value
         */
        public String modelName() {
            return (String) require("modelName");
        }
        /**
         * A platform-dependent description of the version of the machine. On Mac OS, this is, for example, &#x27;10.1&#x27;. Will be the empty string if not supported.
         * @return the protocol field value
         */
        public String modelVersion() {
            return (String) require("modelVersion");
        }
        /**
         * The command line string used to launch the browser. Will be the empty string if not supported.
         * @return the protocol field value
         */
        public String commandLine() {
            return (String) require("commandLine");
        }
        /**
         * Information about the GPUs on the system.
         * @param gpu field value
         * @return this model
         */
        public GetInfoResult gpu(SystemInfo.GPUInfo gpu) {
            set("gpu", gpu);
            return this;
        }
        /**
         * A platform-dependent description of the model of the machine. On Mac OS, this is, for example, &#x27;MacBookPro&#x27;. Will be the empty string if not supported.
         * @param modelName field value
         * @return this model
         */
        public GetInfoResult modelName(String modelName) {
            set("modelName", modelName);
            return this;
        }
        /**
         * A platform-dependent description of the version of the machine. On Mac OS, this is, for example, &#x27;10.1&#x27;. Will be the empty string if not supported.
         * @param modelVersion field value
         * @return this model
         */
        public GetInfoResult modelVersion(String modelVersion) {
            set("modelVersion", modelVersion);
            return this;
        }
        /**
         * The command line string used to launch the browser. Will be the empty string if not supported.
         * @param commandLine field value
         * @return this model
         */
        public GetInfoResult commandLine(String commandLine) {
            set("commandLine", commandLine);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns information about the system.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInfoResult> getInfo() {
            return client.call("SystemInfo.getInfo", null, result_ -> new GetInfoResult(result_));
        }
        /**
         * Returns information about the feature state.
         * @param featureState protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> getFeatureState(String featureState) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("featureState", CdpObject.json(featureState));
            return client.call("SystemInfo.getFeatureState", params, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("featureEnabled")));
        }
        /**
         * Returns information about the feature state.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> getFeatureState(GetFeatureStateRequest request) {
            return client.call("SystemInfo.getFeatureState", request == null ? null : request.toMap(), result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("featureEnabled")));
        }
        /**
         * Returns information about all running processes.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<SystemInfo.ProcessInfo>> getProcessInfo() {
            return client.call("SystemInfo.getProcessInfo", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("processInfo")), element0 -> java.util.Objects.requireNonNull(SystemInfo.ProcessInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
    }
}
