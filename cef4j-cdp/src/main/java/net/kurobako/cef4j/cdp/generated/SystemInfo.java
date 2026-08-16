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
 * The SystemInfo domain defines methods and events for querying low-level system information.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/SystemInfo.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class SystemInfo {
    private SystemInfo() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Describes a single graphics processor (GPU).
     */
    public static final class GPUDevice extends CdpObject {
        private GPUDevice(Map<String, Object> values) { super(values); }
        @Nullable public static GPUDevice fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GPUDevice(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * PCI ID of the GPU vendor, if available; 0 otherwise.
         * @return the protocol field value
         */
        @Nullable public Double vendorId() {
            return numberAsDouble(value("vendorId"));
        }
        /**
         * PCI ID of the GPU device, if available; 0 otherwise.
         * @return the protocol field value
         */
        @Nullable public Double deviceId() {
            return numberAsDouble(value("deviceId"));
        }
        /**
         * Sub sys ID of the GPU, only available on Windows.
         * @return the protocol field value
         */
        @Nullable public Double subSysId() {
            return numberAsDouble(value("subSysId"));
        }
        /**
         * Revision of the GPU, only available on Windows.
         * @return the protocol field value
         */
        @Nullable public Double revision() {
            return numberAsDouble(value("revision"));
        }
        /**
         * String description of the GPU vendor, if the PCI ID is not available.
         * @return the protocol field value
         */
        @Nullable public String vendorString() {
            return (String) value("vendorString");
        }
        /**
         * String description of the GPU device, if the PCI ID is not available.
         * @return the protocol field value
         */
        @Nullable public String deviceString() {
            return (String) value("deviceString");
        }
        /**
         * String description of the GPU driver vendor.
         * @return the protocol field value
         */
        @Nullable public String driverVendor() {
            return (String) value("driverVendor");
        }
        /**
         * String description of the GPU driver version.
         * @return the protocol field value
         */
        @Nullable public String driverVersion() {
            return (String) value("driverVersion");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * PCI ID of the GPU vendor, if available; 0 otherwise.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder vendorId(@Nullable Double value) {
                if (value == null) values.remove("vendorId");
                else values.put("vendorId", jsonValue(value));
                return this;
            }
            /**
             * PCI ID of the GPU device, if available; 0 otherwise.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceId(@Nullable Double value) {
                if (value == null) values.remove("deviceId");
                else values.put("deviceId", jsonValue(value));
                return this;
            }
            /**
             * Sub sys ID of the GPU, only available on Windows.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subSysId(@Nullable Double value) {
                if (value == null) values.remove("subSysId");
                else values.put("subSysId", jsonValue(value));
                return this;
            }
            /**
             * Revision of the GPU, only available on Windows.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder revision(@Nullable Double value) {
                if (value == null) values.remove("revision");
                else values.put("revision", jsonValue(value));
                return this;
            }
            /**
             * String description of the GPU vendor, if the PCI ID is not available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder vendorString(@Nullable String value) {
                if (value == null) values.remove("vendorString");
                else values.put("vendorString", jsonValue(value));
                return this;
            }
            /**
             * String description of the GPU device, if the PCI ID is not available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceString(@Nullable String value) {
                if (value == null) values.remove("deviceString");
                else values.put("deviceString", jsonValue(value));
                return this;
            }
            /**
             * String description of the GPU driver vendor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder driverVendor(@Nullable String value) {
                if (value == null) values.remove("driverVendor");
                else values.put("driverVendor", jsonValue(value));
                return this;
            }
            /**
             * String description of the GPU driver version.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder driverVersion(@Nullable String value) {
                if (value == null) values.remove("driverVersion");
                else values.put("driverVersion", jsonValue(value));
                return this;
            }
            public GPUDevice build() {
                if (!values.containsKey("vendorId")) throw new IllegalStateException("Missing required CDP field: vendorId");
                if (!values.containsKey("deviceId")) throw new IllegalStateException("Missing required CDP field: deviceId");
                if (!values.containsKey("vendorString")) throw new IllegalStateException("Missing required CDP field: vendorString");
                if (!values.containsKey("deviceString")) throw new IllegalStateException("Missing required CDP field: deviceString");
                if (!values.containsKey("driverVendor")) throw new IllegalStateException("Missing required CDP field: driverVendor");
                if (!values.containsKey("driverVersion")) throw new IllegalStateException("Missing required CDP field: driverVersion");
                return new GPUDevice(values);
            }
        }
    }
    /**
     * Describes the width and height dimensions of an entity.
     */
    public static final class Size extends CdpObject {
        private Size(Map<String, Object> values) { super(values); }
        @Nullable public static Size fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Size(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Width in pixels.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Height in pixels.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Width in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Height in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            public Size build() {
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                return new Size(values);
            }
        }
    }
    /**
     * Describes a supported video decoding profile with its associated minimum and maximum resolutions.
     */
    public static final class VideoDecodeAcceleratorCapability extends CdpObject {
        private VideoDecodeAcceleratorCapability(Map<String, Object> values) { super(values); }
        @Nullable public static VideoDecodeAcceleratorCapability fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VideoDecodeAcceleratorCapability(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Video codec profile that is supported, e.g. VP9 Profile 2.
         * @return the protocol field value
         */
        @Nullable public String profile() {
            return (String) value("profile");
        }
        /**
         * Maximum video dimensions in pixels supported for this |profile|.
         * @return the protocol field value
         */
        @Nullable public SystemInfo.Size maxResolution() {
            return SystemInfo.Size.fromMap(objectMap(value("maxResolution")));
        }
        /**
         * Minimum video dimensions in pixels supported for this |profile|.
         * @return the protocol field value
         */
        @Nullable public SystemInfo.Size minResolution() {
            return SystemInfo.Size.fromMap(objectMap(value("minResolution")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Video codec profile that is supported, e.g. VP9 Profile 2.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable String value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            /**
             * Maximum video dimensions in pixels supported for this |profile|.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxResolution(@Nullable SystemInfo.Size value) {
                if (value == null) values.remove("maxResolution");
                else values.put("maxResolution", jsonValue(value));
                return this;
            }
            /**
             * Minimum video dimensions in pixels supported for this |profile|.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder minResolution(@Nullable SystemInfo.Size value) {
                if (value == null) values.remove("minResolution");
                else values.put("minResolution", jsonValue(value));
                return this;
            }
            public VideoDecodeAcceleratorCapability build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                if (!values.containsKey("maxResolution")) throw new IllegalStateException("Missing required CDP field: maxResolution");
                if (!values.containsKey("minResolution")) throw new IllegalStateException("Missing required CDP field: minResolution");
                return new VideoDecodeAcceleratorCapability(values);
            }
        }
    }
    /**
     * Describes a supported video encoding profile with its associated maximum resolution and maximum framerate.
     */
    public static final class VideoEncodeAcceleratorCapability extends CdpObject {
        private VideoEncodeAcceleratorCapability(Map<String, Object> values) { super(values); }
        @Nullable public static VideoEncodeAcceleratorCapability fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VideoEncodeAcceleratorCapability(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Video codec profile that is supported, e.g H264 Main.
         * @return the protocol field value
         */
        @Nullable public String profile() {
            return (String) value("profile");
        }
        /**
         * Maximum video dimensions in pixels supported for this |profile|.
         * @return the protocol field value
         */
        @Nullable public SystemInfo.Size maxResolution() {
            return SystemInfo.Size.fromMap(objectMap(value("maxResolution")));
        }
        /**
         * Maximum encoding framerate in frames per second supported for this |profile|, as fraction&#x27;s numerator and denominator, e.g. 24/1 fps, 24000/1001 fps, etc.
         * @return the protocol field value
         */
        @Nullable public Long maxFramerateNumerator() {
            return numberAsLong(value("maxFramerateNumerator"));
        }
        /**
         * Returns the maxFramerateDenominator field.
         * @return the protocol field value
         */
        @Nullable public Long maxFramerateDenominator() {
            return numberAsLong(value("maxFramerateDenominator"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Video codec profile that is supported, e.g H264 Main.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable String value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            /**
             * Maximum video dimensions in pixels supported for this |profile|.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxResolution(@Nullable SystemInfo.Size value) {
                if (value == null) values.remove("maxResolution");
                else values.put("maxResolution", jsonValue(value));
                return this;
            }
            /**
             * Maximum encoding framerate in frames per second supported for this |profile|, as fraction&#x27;s numerator and denominator, e.g. 24/1 fps, 24000/1001 fps, etc.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxFramerateNumerator(@Nullable Long value) {
                if (value == null) values.remove("maxFramerateNumerator");
                else values.put("maxFramerateNumerator", jsonValue(value));
                return this;
            }
            /**
             * Sets the maxFramerateDenominator field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxFramerateDenominator(@Nullable Long value) {
                if (value == null) values.remove("maxFramerateDenominator");
                else values.put("maxFramerateDenominator", jsonValue(value));
                return this;
            }
            public VideoEncodeAcceleratorCapability build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                if (!values.containsKey("maxResolution")) throw new IllegalStateException("Missing required CDP field: maxResolution");
                if (!values.containsKey("maxFramerateNumerator")) throw new IllegalStateException("Missing required CDP field: maxFramerateNumerator");
                if (!values.containsKey("maxFramerateDenominator")) throw new IllegalStateException("Missing required CDP field: maxFramerateDenominator");
                return new VideoEncodeAcceleratorCapability(values);
            }
        }
    }
    /**
     * YUV subsampling type of the pixels of a given image.
     */
    public static final class SubsamplingFormat {
        private SubsamplingFormat() {}
        public static final String YUV420 = "yuv420";
        public static final String YUV422 = "yuv422";
        public static final String YUV444 = "yuv444";
    }
    /**
     * Image format of a given image.
     */
    public static final class ImageType {
        private ImageType() {}
        public static final String JPEG = "jpeg";
        public static final String WEBP = "webp";
        public static final String UNKNOWN = "unknown";
    }
    /**
     * Provides information about the GPU(s) on the system.
     */
    public static final class GPUInfo extends CdpObject {
        private GPUInfo(Map<String, Object> values) { super(values); }
        @Nullable public static GPUInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GPUInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The graphics devices on the system. Element 0 is the primary GPU.
         * @return the protocol field value
         */
        @Nullable public java.util.List<SystemInfo.GPUDevice> devices() {
            return list(value("devices"), element0 -> SystemInfo.GPUDevice.fromMap(objectMap(element0)));
        }
        /**
         * An optional dictionary of additional GPU related attributes.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> auxAttributes() {
            return objectMap(value("auxAttributes"));
        }
        /**
         * An optional dictionary of graphics features and their status.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> featureStatus() {
            return objectMap(value("featureStatus"));
        }
        /**
         * An optional array of GPU driver bug workarounds.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> driverBugWorkarounds() {
            return list(value("driverBugWorkarounds"), element0 -> (String) element0);
        }
        /**
         * Supported accelerated video decoding capabilities.
         * @return the protocol field value
         */
        @Nullable public java.util.List<SystemInfo.VideoDecodeAcceleratorCapability> videoDecoding() {
            return list(value("videoDecoding"), element0 -> SystemInfo.VideoDecodeAcceleratorCapability.fromMap(objectMap(element0)));
        }
        /**
         * Supported accelerated video encoding capabilities.
         * @return the protocol field value
         */
        @Nullable public java.util.List<SystemInfo.VideoEncodeAcceleratorCapability> videoEncoding() {
            return list(value("videoEncoding"), element0 -> SystemInfo.VideoEncodeAcceleratorCapability.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The graphics devices on the system. Element 0 is the primary GPU.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder devices(@Nullable java.util.List<SystemInfo.GPUDevice> value) {
                if (value == null) values.remove("devices");
                else values.put("devices", jsonValue(value));
                return this;
            }
            /**
             * An optional dictionary of additional GPU related attributes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder auxAttributes(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("auxAttributes");
                else values.put("auxAttributes", jsonValue(value));
                return this;
            }
            /**
             * An optional dictionary of graphics features and their status.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder featureStatus(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("featureStatus");
                else values.put("featureStatus", jsonValue(value));
                return this;
            }
            /**
             * An optional array of GPU driver bug workarounds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder driverBugWorkarounds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("driverBugWorkarounds");
                else values.put("driverBugWorkarounds", jsonValue(value));
                return this;
            }
            /**
             * Supported accelerated video decoding capabilities.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder videoDecoding(@Nullable java.util.List<SystemInfo.VideoDecodeAcceleratorCapability> value) {
                if (value == null) values.remove("videoDecoding");
                else values.put("videoDecoding", jsonValue(value));
                return this;
            }
            /**
             * Supported accelerated video encoding capabilities.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder videoEncoding(@Nullable java.util.List<SystemInfo.VideoEncodeAcceleratorCapability> value) {
                if (value == null) values.remove("videoEncoding");
                else values.put("videoEncoding", jsonValue(value));
                return this;
            }
            public GPUInfo build() {
                if (!values.containsKey("devices")) throw new IllegalStateException("Missing required CDP field: devices");
                if (!values.containsKey("driverBugWorkarounds")) throw new IllegalStateException("Missing required CDP field: driverBugWorkarounds");
                if (!values.containsKey("videoDecoding")) throw new IllegalStateException("Missing required CDP field: videoDecoding");
                if (!values.containsKey("videoEncoding")) throw new IllegalStateException("Missing required CDP field: videoEncoding");
                return new GPUInfo(values);
            }
        }
    }
    /**
     * Represents process info.
     */
    public static final class ProcessInfo extends CdpObject {
        private ProcessInfo(Map<String, Object> values) { super(values); }
        @Nullable public static ProcessInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProcessInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Specifies process type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Specifies process id.
         * @return the protocol field value
         */
        @Nullable public Long id() {
            return numberAsLong(value("id"));
        }
        /**
         * Specifies cumulative CPU usage in seconds across all threads of the process since the process start.
         * @return the protocol field value
         */
        @Nullable public Double cpuTime() {
            return numberAsDouble(value("cpuTime"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Specifies process type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Specifies process id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable Long value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Specifies cumulative CPU usage in seconds across all threads of the process since the process start.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cpuTime(@Nullable Double value) {
                if (value == null) values.remove("cpuTime");
                else values.put("cpuTime", jsonValue(value));
                return this;
            }
            public ProcessInfo build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("cpuTime")) throw new IllegalStateException("Missing required CDP field: cpuTime");
                return new ProcessInfo(values);
            }
        }
    }
    /**
     * Returns information about the system.
     */
    public static final class GetInfoParams extends CdpObject {
        private GetInfoParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetInfoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInfoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetInfoParams build() {
                return new GetInfoParams(values);
            }
        }
    }
    /**
     * Returns information about the system.
     */
    public static final class GetInfoResult extends CdpObject {
        private GetInfoResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetInfoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInfoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Information about the GPUs on the system.
         * @return the protocol field value
         */
        @Nullable public SystemInfo.GPUInfo gpu() {
            return SystemInfo.GPUInfo.fromMap(objectMap(value("gpu")));
        }
        /**
         * A platform-dependent description of the model of the machine. On Mac OS, this is, for example, &#x27;MacBookPro&#x27;. Will be the empty string if not supported.
         * @return the protocol field value
         */
        @Nullable public String modelName() {
            return (String) value("modelName");
        }
        /**
         * A platform-dependent description of the version of the machine. On Mac OS, this is, for example, &#x27;10.1&#x27;. Will be the empty string if not supported.
         * @return the protocol field value
         */
        @Nullable public String modelVersion() {
            return (String) value("modelVersion");
        }
        /**
         * The command line string used to launch the browser. Will be the empty string if not supported.
         * @return the protocol field value
         */
        @Nullable public String commandLine() {
            return (String) value("commandLine");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Information about the GPUs on the system.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gpu(@Nullable SystemInfo.GPUInfo value) {
                if (value == null) values.remove("gpu");
                else values.put("gpu", jsonValue(value));
                return this;
            }
            /**
             * A platform-dependent description of the model of the machine. On Mac OS, this is, for example, &#x27;MacBookPro&#x27;. Will be the empty string if not supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modelName(@Nullable String value) {
                if (value == null) values.remove("modelName");
                else values.put("modelName", jsonValue(value));
                return this;
            }
            /**
             * A platform-dependent description of the version of the machine. On Mac OS, this is, for example, &#x27;10.1&#x27;. Will be the empty string if not supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modelVersion(@Nullable String value) {
                if (value == null) values.remove("modelVersion");
                else values.put("modelVersion", jsonValue(value));
                return this;
            }
            /**
             * The command line string used to launch the browser. Will be the empty string if not supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder commandLine(@Nullable String value) {
                if (value == null) values.remove("commandLine");
                else values.put("commandLine", jsonValue(value));
                return this;
            }
            public GetInfoResult build() {
                if (!values.containsKey("gpu")) throw new IllegalStateException("Missing required CDP field: gpu");
                if (!values.containsKey("modelName")) throw new IllegalStateException("Missing required CDP field: modelName");
                if (!values.containsKey("modelVersion")) throw new IllegalStateException("Missing required CDP field: modelVersion");
                if (!values.containsKey("commandLine")) throw new IllegalStateException("Missing required CDP field: commandLine");
                return new GetInfoResult(values);
            }
        }
    }
    /**
     * Returns information about the feature state.
     */
    public static final class GetFeatureStateParams extends CdpObject {
        private GetFeatureStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetFeatureStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFeatureStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the featureState field.
         * @return the protocol field value
         */
        @Nullable public String featureState() {
            return (String) value("featureState");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the featureState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder featureState(@Nullable String value) {
                if (value == null) values.remove("featureState");
                else values.put("featureState", jsonValue(value));
                return this;
            }
            public GetFeatureStateParams build() {
                if (!values.containsKey("featureState")) throw new IllegalStateException("Missing required CDP field: featureState");
                return new GetFeatureStateParams(values);
            }
        }
    }
    /**
     * Returns information about the feature state.
     */
    public static final class GetFeatureStateResult extends CdpObject {
        private GetFeatureStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetFeatureStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFeatureStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the featureEnabled field.
         * @return the protocol field value
         */
        @Nullable public Boolean featureEnabled() {
            return (Boolean) value("featureEnabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the featureEnabled field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder featureEnabled(@Nullable Boolean value) {
                if (value == null) values.remove("featureEnabled");
                else values.put("featureEnabled", jsonValue(value));
                return this;
            }
            public GetFeatureStateResult build() {
                if (!values.containsKey("featureEnabled")) throw new IllegalStateException("Missing required CDP field: featureEnabled");
                return new GetFeatureStateResult(values);
            }
        }
    }
    /**
     * Returns information about all running processes.
     */
    public static final class GetProcessInfoParams extends CdpObject {
        private GetProcessInfoParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetProcessInfoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetProcessInfoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetProcessInfoParams build() {
                return new GetProcessInfoParams(values);
            }
        }
    }
    /**
     * Returns information about all running processes.
     */
    public static final class GetProcessInfoResult extends CdpObject {
        private GetProcessInfoResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetProcessInfoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetProcessInfoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of process info blocks.
         * @return the protocol field value
         */
        @Nullable public java.util.List<SystemInfo.ProcessInfo> processInfo() {
            return list(value("processInfo"), element0 -> SystemInfo.ProcessInfo.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of process info blocks.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder processInfo(@Nullable java.util.List<SystemInfo.ProcessInfo> value) {
                if (value == null) values.remove("processInfo");
                else values.put("processInfo", jsonValue(value));
                return this;
            }
            public GetProcessInfoResult build() {
                if (!values.containsKey("processInfo")) throw new IllegalStateException("Missing required CDP field: processInfo");
                return new GetProcessInfoResult(values);
            }
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
            return client.call("SystemInfo.getInfo", null, GetInfoResult::fromMap);
        }
        /**
         * Returns information about the feature state.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFeatureStateResult> getFeatureState(GetFeatureStateParams params) {
            return client.call("SystemInfo.getFeatureState", params, GetFeatureStateResult::fromMap);
        }
        /**
         * Returns information about all running processes.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetProcessInfoResult> getProcessInfo() {
            return client.call("SystemInfo.getProcessInfo", null, GetProcessInfoResult::fromMap);
        }
    }
}
