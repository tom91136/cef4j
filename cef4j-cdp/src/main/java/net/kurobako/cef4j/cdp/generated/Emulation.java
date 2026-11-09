// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
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
 * This domain emulates different environments for the page.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Emulation.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Emulation {
    private Emulation() {}
    private static final CdpField<java.util.List<Emulation.UserAgentBrandVersion>> BRANDS = CdpField.of("brands", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Emulation.UserAgentBrandVersion.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.RGBA> COLOR = CdpField.of("color", value -> java.util.Objects.requireNonNull(DOM.RGBA.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<SetEmitTouchEventsForMouseConfigurationValues> CONFIGURATION = CdpField.of("configuration", value -> SetEmitTouchEventsForMouseConfigurationValues.of((String) value));
    private static final CdpField<Emulation.DevicePosture> DEVICE_POSTURE = CdpField.of("devicePosture", value -> java.util.Objects.requireNonNull(Emulation.DevicePosture.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.DisplayFeature> DISPLAY_FEATURE = CdpField.of("displayFeature", value -> java.util.Objects.requireNonNull(Emulation.DisplayFeature.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Emulation.DisplayFeature>> FEATURES = CdpField.of("features", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Emulation.DisplayFeature.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Emulation.MediaFeature>> FEATURES_2 = CdpField.of("features", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Emulation.MediaFeature.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> FORM_FACTORS = CdpField.of("formFactors", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<Emulation.UserAgentBrandVersion>> FULL_VERSION_LIST = CdpField.of("fullVersionList", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Emulation.UserAgentBrandVersion.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Emulation.ScreenId> ID = CdpField.of("id", value -> new Emulation.ScreenId((String) value));
    private static final CdpField<java.util.List<Emulation.DisabledImageType>> IMAGE_TYPES = CdpField.of("imageTypes", value -> CdpObject.requireList(value, element0 -> Emulation.DisabledImageType.of((String) element0)));
    private static final CdpField<Network.TimeSinceEpoch> INITIAL_VIRTUAL_TIME = CdpField.of("initialVirtualTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Emulation.SafeAreaInsets> INSETS = CdpField.of("insets", value -> java.util.Objects.requireNonNull(Emulation.SafeAreaInsets.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.SensorMetadata> METADATA = CdpField.of("metadata", value -> java.util.Objects.requireNonNull(Emulation.SensorMetadata.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.PressureMetadata> METADATA_2 = CdpField.of("metadata", value -> java.util.Objects.requireNonNull(Emulation.PressureMetadata.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DisplayFeature.OrientationValues> ORIENTATION = CdpField.of("orientation", value -> DisplayFeature.OrientationValues.of((String) value));
    private static final CdpField<Emulation.ScreenOrientation> ORIENTATION_2 = CdpField.of("orientation", value -> java.util.Objects.requireNonNull(Emulation.ScreenOrientation.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.VirtualTimePolicy> POLICY = CdpField.of("policy", value -> Emulation.VirtualTimePolicy.of((String) value));
    private static final CdpField<Emulation.DevicePosture> POSTURE = CdpField.of("posture", value -> java.util.Objects.requireNonNull(Emulation.DevicePosture.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.SensorReadingQuaternion> QUATERNION = CdpField.of("quaternion", value -> java.util.Objects.requireNonNull(Emulation.SensorReadingQuaternion.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.SensorReading> READING = CdpField.of("reading", value -> java.util.Objects.requireNonNull(Emulation.SensorReading.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Double> REQUESTED_SAMPLING_FREQUENCY = CdpField.of("requestedSamplingFrequency", value -> ((Number) value).doubleValue());
    private static final CdpField<Boolean> RESULT = CdpField.of("result", value -> (Boolean) value);
    private static final CdpField<Emulation.ScreenId> SCREEN_ID = CdpField.of("screenId", value -> new Emulation.ScreenId((String) value));
    private static final CdpField<Emulation.ScreenInfo> SCREEN_INFO = CdpField.of("screenInfo", value -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Emulation.ScreenInfo>> SCREEN_INFOS = CdpField.of("screenInfos", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Emulation.ScreenOrientation> SCREEN_ORIENTATION = CdpField.of("screenOrientation", value -> java.util.Objects.requireNonNull(Emulation.ScreenOrientation.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<SetDeviceMetricsOverrideScrollbarTypeValues> SCROLLBAR_TYPE = CdpField.of("scrollbarType", value -> SetDeviceMetricsOverrideScrollbarTypeValues.of((String) value));
    private static final CdpField<Emulation.SensorReadingSingle> SINGLE = CdpField.of("single", value -> java.util.Objects.requireNonNull(Emulation.SensorReadingSingle.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.PressureSource> SOURCE = CdpField.of("source", value -> Emulation.PressureSource.of((String) value));
    private static final CdpField<Emulation.PressureState> STATE = CdpField.of("state", value -> Emulation.PressureState.of((String) value));
    private static final CdpField<ScreenOrientation.TypeValues> TYPE = CdpField.of("type", value -> ScreenOrientation.TypeValues.of((String) value));
    private static final CdpField<DevicePosture.TypeValues> TYPE_2 = CdpField.of("type", value -> DevicePosture.TypeValues.of((String) value));
    private static final CdpField<SetEmulatedVisionDeficiencyTypeValues> TYPE_3 = CdpField.of("type", value -> SetEmulatedVisionDeficiencyTypeValues.of((String) value));
    private static final CdpField<Emulation.SensorType> TYPE_4 = CdpField.of("type", value -> Emulation.SensorType.of((String) value));
    private static final CdpField<Emulation.UserAgentMetadata> USER_AGENT_METADATA = CdpField.of("userAgentMetadata", value -> java.util.Objects.requireNonNull(Emulation.UserAgentMetadata.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Page.Viewport> VIEWPORT = CdpField.of("viewport", value -> java.util.Objects.requireNonNull(Page.Viewport.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Double> VIRTUAL_TIME_TICKS_BASE = CdpField.of("virtualTimeTicksBase", value -> ((Number) value).doubleValue());
    private static final CdpField<Emulation.WorkAreaInsets> WORK_AREA_INSETS = CdpField.of("workAreaInsets", value -> java.util.Objects.requireNonNull(Emulation.WorkAreaInsets.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Emulation.SensorReadingXYZ> XYZ = CdpField.of("xyz", value -> java.util.Objects.requireNonNull(Emulation.SensorReadingXYZ.fromMap(CdpObject.requireObjectMap(value))));
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SafeAreaInsets extends CdpObject {
        public SafeAreaInsets() {}
        private SafeAreaInsets(Map<String, Object> values) { super(values); }
        public static SafeAreaInsets fromMap(Map<String, Object> values) {
            return new SafeAreaInsets(values);
        }
        /**
         * Overrides safe-area-inset-top.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            return optionalLong("top");
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong topMax() {
            return optionalLong("topMax");
        }
        /**
         * Overrides safe-area-inset-left.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return optionalLong("left");
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong leftMax() {
            return optionalLong("leftMax");
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottom() {
            return optionalLong("bottom");
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottomMax() {
            return optionalLong("bottomMax");
        }
        /**
         * Overrides safe-area-inset-right.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong right() {
            return optionalLong("right");
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rightMax() {
            return optionalLong("rightMax");
        }
        /**
         * Overrides safe-area-inset-top.
         * @param top field value
         * @return this model
         */
        public SafeAreaInsets top(long top) {
            set("top", top);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @param topMax field value
         * @return this model
         */
        public SafeAreaInsets topMax(long topMax) {
            set("topMax", topMax);
            return this;
        }
        /**
         * Overrides safe-area-inset-left.
         * @param left field value
         * @return this model
         */
        public SafeAreaInsets left(long left) {
            set("left", left);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @param leftMax field value
         * @return this model
         */
        public SafeAreaInsets leftMax(long leftMax) {
            set("leftMax", leftMax);
            return this;
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @param bottom field value
         * @return this model
         */
        public SafeAreaInsets bottom(long bottom) {
            set("bottom", bottom);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @param bottomMax field value
         * @return this model
         */
        public SafeAreaInsets bottomMax(long bottomMax) {
            set("bottomMax", bottomMax);
            return this;
        }
        /**
         * Overrides safe-area-inset-right.
         * @param right field value
         * @return this model
         */
        public SafeAreaInsets right(long right) {
            set("right", right);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @param rightMax field value
         * @return this model
         */
        public SafeAreaInsets rightMax(long rightMax) {
            set("rightMax", rightMax);
            return this;
        }
    }
    /**
     * Screen orientation.
     */
    public static final class ScreenOrientation extends CdpObject {
        public ScreenOrientation() {}
        private ScreenOrientation(Map<String, Object> values) { super(values); }
        public static ScreenOrientation fromMap(Map<String, Object> values) {
            return new ScreenOrientation(values);
        }
        /**
         * Orientation type.
         */
        public enum TypeValues implements CdpValue<String> {
            PORTRAITPRIMARY("portraitPrimary"),
            PORTRAITSECONDARY("portraitSecondary"),
            LANDSCAPEPRIMARY("landscapePrimary"),
            LANDSCAPESECONDARY("landscapeSecondary");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Orientation type.
         * @return the protocol field value
         */
        public ScreenOrientation.TypeValues type() {
            return require(TYPE);
        }
        /**
         * Orientation angle.
         * @return the protocol field value
         */
        public long angle() {
            return requireLong("angle");
        }
        /**
         * Orientation type.
         * @param type field value
         * @return this model
         */
        public ScreenOrientation type(ScreenOrientation.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Orientation angle.
         * @param angle field value
         * @return this model
         */
        public ScreenOrientation angle(long angle) {
            set("angle", angle);
            return this;
        }
    }
    /**
     */
    public static final class DisplayFeature extends CdpObject {
        public DisplayFeature() {}
        private DisplayFeature(Map<String, Object> values) { super(values); }
        public static DisplayFeature fromMap(Map<String, Object> values) {
            return new DisplayFeature(values);
        }
        /**
         * Orientation of a display feature in relation to screen
         */
        public enum OrientationValues implements CdpValue<String> {
            VERTICAL("vertical"),
            HORIZONTAL("horizontal");
            public final String value;
            OrientationValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static OrientationValues of(String value) {
                for (OrientationValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown OrientationValues value: " + value);
            }
        }
        /**
         * Orientation of a display feature in relation to screen
         * @return the protocol field value
         */
        public DisplayFeature.OrientationValues orientation() {
            return require(ORIENTATION);
        }
        /**
         * The offset from the screen origin in either the x (for vertical orientation) or y (for horizontal orientation) direction.
         * @return the protocol field value
         */
        public long offset() {
            return requireLong("offset");
        }
        /**
         * A display feature may mask content such that it is not physically displayed - this length along with the offset describes this area. A display feature that only splits content will have a 0 mask_length.
         * @return the protocol field value
         */
        public long maskLength() {
            return requireLong("maskLength");
        }
        /**
         * Orientation of a display feature in relation to screen
         * @param orientation field value
         * @return this model
         */
        public DisplayFeature orientation(DisplayFeature.OrientationValues orientation) {
            set("orientation", orientation);
            return this;
        }
        /**
         * The offset from the screen origin in either the x (for vertical orientation) or y (for horizontal orientation) direction.
         * @param offset field value
         * @return this model
         */
        public DisplayFeature offset(long offset) {
            set("offset", offset);
            return this;
        }
        /**
         * A display feature may mask content such that it is not physically displayed - this length along with the offset describes this area. A display feature that only splits content will have a 0 mask_length.
         * @param maskLength field value
         * @return this model
         */
        public DisplayFeature maskLength(long maskLength) {
            set("maskLength", maskLength);
            return this;
        }
    }
    /**
     */
    public static final class DevicePosture extends CdpObject {
        public DevicePosture() {}
        private DevicePosture(Map<String, Object> values) { super(values); }
        public static DevicePosture fromMap(Map<String, Object> values) {
            return new DevicePosture(values);
        }
        /**
         * Current posture of the device
         */
        public enum TypeValues implements CdpValue<String> {
            CONTINUOUS("continuous"),
            FOLDED("folded");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Current posture of the device
         * @return the protocol field value
         */
        public DevicePosture.TypeValues type() {
            return require(TYPE_2);
        }
        /**
         * Current posture of the device
         * @param type field value
         * @return this model
         */
        public DevicePosture type(DevicePosture.TypeValues type) {
            set("type", type);
            return this;
        }
    }
    /**
     */
    public static final class MediaFeature extends CdpObject {
        public MediaFeature() {}
        private MediaFeature(Map<String, Object> values) { super(values); }
        public static MediaFeature fromMap(Map<String, Object> values) {
            return new MediaFeature(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public MediaFeature name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public MediaFeature value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to allow the next delayed task (if any) to run; pause: The virtual time base may not advance; pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending resource fetches.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum VirtualTimePolicy implements CdpValue<String> {
        ADVANCE("advance"),
        PAUSE("pause"),
        PAUSEIFNETWORKFETCHESPENDING("pauseIfNetworkFetchesPending");
        public final String value;
        VirtualTimePolicy(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static VirtualTimePolicy of(String value) {
            for (VirtualTimePolicy constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown VirtualTimePolicy value: " + value);
        }
    }
    /**
     * Used to specify User Agent Client Hints to emulate. See https://wicg.github.io/ua-client-hints
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UserAgentBrandVersion extends CdpObject {
        public UserAgentBrandVersion() {}
        private UserAgentBrandVersion(Map<String, Object> values) { super(values); }
        public static UserAgentBrandVersion fromMap(Map<String, Object> values) {
            return new UserAgentBrandVersion(values);
        }
        /**
         * Returns the brand field.
         * @return the protocol field value
         */
        public String brand() {
            return requireString("brand");
        }
        /**
         * Returns the version field.
         * @return the protocol field value
         */
        public String version() {
            return requireString("version");
        }
        /**
         * Sets the brand field.
         * @param brand field value
         * @return this model
         */
        public UserAgentBrandVersion brand(String brand) {
            set("brand", brand);
            return this;
        }
        /**
         * Sets the version field.
         * @param version field value
         * @return this model
         */
        public UserAgentBrandVersion version(String version) {
            set("version", version);
            return this;
        }
    }
    /**
     * Used to specify User Agent Client Hints to emulate. See https://wicg.github.io/ua-client-hints Missing optional values will be filled in by the target with what it would normally use.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UserAgentMetadata extends CdpObject {
        public UserAgentMetadata() {}
        private UserAgentMetadata(Map<String, Object> values) { super(values); }
        public static UserAgentMetadata fromMap(Map<String, Object> values) {
            return new UserAgentMetadata(values);
        }
        /**
         * Brands appearing in Sec-CH-UA.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.UserAgentBrandVersion>> brands() {
            return optional(BRANDS);
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.UserAgentBrandVersion>> fullVersionList() {
            return optional(FULL_VERSION_LIST);
        }
        /**
         * Returns the fullVersion field.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> fullVersion() {
            return optionalString("fullVersion");
        }
        /**
         * Returns the platform field.
         * @return the protocol field value
         */
        public String platform() {
            return requireString("platform");
        }
        /**
         * Returns the platformVersion field.
         * @return the protocol field value
         */
        public String platformVersion() {
            return requireString("platformVersion");
        }
        /**
         * Returns the architecture field.
         * @return the protocol field value
         */
        public String architecture() {
            return requireString("architecture");
        }
        /**
         * Returns the model field.
         * @return the protocol field value
         */
        public String model() {
            return requireString("model");
        }
        /**
         * Returns the mobile field.
         * @return the protocol field value
         */
        public boolean mobile() {
            return requireBoolean("mobile");
        }
        /**
         * Returns the bitness field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bitness() {
            return optionalString("bitness");
        }
        /**
         * Returns the wow64 field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> wow64() {
            return optionalBoolean("wow64");
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> formFactors() {
            return optional(FORM_FACTORS);
        }
        /**
         * Brands appearing in Sec-CH-UA.
         * @param brands field value
         * @return this model
         */
        public UserAgentMetadata brands(java.util.List<Emulation.UserAgentBrandVersion> brands) {
            set("brands", brands);
            return this;
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @param fullVersionList field value
         * @return this model
         */
        public UserAgentMetadata fullVersionList(java.util.List<Emulation.UserAgentBrandVersion> fullVersionList) {
            set("fullVersionList", fullVersionList);
            return this;
        }
        /**
         * Sets the fullVersion field.
         * @param fullVersion field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public UserAgentMetadata fullVersion(String fullVersion) {
            set("fullVersion", fullVersion);
            return this;
        }
        /**
         * Sets the platform field.
         * @param platform field value
         * @return this model
         */
        public UserAgentMetadata platform(String platform) {
            set("platform", platform);
            return this;
        }
        /**
         * Sets the platformVersion field.
         * @param platformVersion field value
         * @return this model
         */
        public UserAgentMetadata platformVersion(String platformVersion) {
            set("platformVersion", platformVersion);
            return this;
        }
        /**
         * Sets the architecture field.
         * @param architecture field value
         * @return this model
         */
        public UserAgentMetadata architecture(String architecture) {
            set("architecture", architecture);
            return this;
        }
        /**
         * Sets the model field.
         * @param model field value
         * @return this model
         */
        public UserAgentMetadata model(String model) {
            set("model", model);
            return this;
        }
        /**
         * Sets the mobile field.
         * @param mobile field value
         * @return this model
         */
        public UserAgentMetadata mobile(boolean mobile) {
            set("mobile", mobile);
            return this;
        }
        /**
         * Sets the bitness field.
         * @param bitness field value
         * @return this model
         */
        public UserAgentMetadata bitness(String bitness) {
            set("bitness", bitness);
            return this;
        }
        /**
         * Sets the wow64 field.
         * @param wow64 field value
         * @return this model
         */
        public UserAgentMetadata wow64(boolean wow64) {
            set("wow64", wow64);
            return this;
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @param formFactors field value
         * @return this model
         */
        public UserAgentMetadata formFactors(java.util.List<String> formFactors) {
            set("formFactors", formFactors);
            return this;
        }
    }
    /**
     * Used to specify sensor types to emulate. See https://w3c.github.io/sensors/#automation for more information.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum SensorType implements CdpValue<String> {
        ABSOLUTE_ORIENTATION("absolute-orientation"),
        ACCELEROMETER("accelerometer"),
        AMBIENT_LIGHT("ambient-light"),
        GRAVITY("gravity"),
        GYROSCOPE("gyroscope"),
        LINEAR_ACCELERATION("linear-acceleration"),
        MAGNETOMETER("magnetometer"),
        RELATIVE_ORIENTATION("relative-orientation");
        public final String value;
        SensorType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SensorType of(String value) {
            for (SensorType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SensorType value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorMetadata extends CdpObject {
        public SensorMetadata() {}
        private SensorMetadata(Map<String, Object> values) { super(values); }
        public static SensorMetadata fromMap(Map<String, Object> values) {
            return new SensorMetadata(values);
        }
        /**
         * Returns the available field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> available() {
            return optionalBoolean("available");
        }
        /**
         * Returns the minimumFrequency field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble minimumFrequency() {
            return optionalDouble("minimumFrequency");
        }
        /**
         * Returns the maximumFrequency field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble maximumFrequency() {
            return optionalDouble("maximumFrequency");
        }
        /**
         * Sets the available field.
         * @param available field value
         * @return this model
         */
        public SensorMetadata available(boolean available) {
            set("available", available);
            return this;
        }
        /**
         * Sets the minimumFrequency field.
         * @param minimumFrequency field value
         * @return this model
         */
        public SensorMetadata minimumFrequency(double minimumFrequency) {
            set("minimumFrequency", minimumFrequency);
            return this;
        }
        /**
         * Sets the maximumFrequency field.
         * @param maximumFrequency field value
         * @return this model
         */
        public SensorMetadata maximumFrequency(double maximumFrequency) {
            set("maximumFrequency", maximumFrequency);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReadingSingle extends CdpObject {
        public SensorReadingSingle() {}
        private SensorReadingSingle(Map<String, Object> values) { super(values); }
        public static SensorReadingSingle fromMap(Map<String, Object> values) {
            return new SensorReadingSingle(values);
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public double value() {
            return requireDouble("value");
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public SensorReadingSingle value(double value) {
            set("value", value);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReadingXYZ extends CdpObject {
        public SensorReadingXYZ() {}
        private SensorReadingXYZ(Map<String, Object> values) { super(values); }
        public static SensorReadingXYZ fromMap(Map<String, Object> values) {
            return new SensorReadingXYZ(values);
        }
        /**
         * Returns the x field.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Returns the y field.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Returns the z field.
         * @return the protocol field value
         */
        public double z() {
            return requireDouble("z");
        }
        /**
         * Sets the x field.
         * @param x field value
         * @return this model
         */
        public SensorReadingXYZ x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Sets the y field.
         * @param y field value
         * @return this model
         */
        public SensorReadingXYZ y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Sets the z field.
         * @param z field value
         * @return this model
         */
        public SensorReadingXYZ z(double z) {
            set("z", z);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReadingQuaternion extends CdpObject {
        public SensorReadingQuaternion() {}
        private SensorReadingQuaternion(Map<String, Object> values) { super(values); }
        public static SensorReadingQuaternion fromMap(Map<String, Object> values) {
            return new SensorReadingQuaternion(values);
        }
        /**
         * Returns the x field.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Returns the y field.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Returns the z field.
         * @return the protocol field value
         */
        public double z() {
            return requireDouble("z");
        }
        /**
         * Returns the w field.
         * @return the protocol field value
         */
        public double w() {
            return requireDouble("w");
        }
        /**
         * Sets the x field.
         * @param x field value
         * @return this model
         */
        public SensorReadingQuaternion x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Sets the y field.
         * @param y field value
         * @return this model
         */
        public SensorReadingQuaternion y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Sets the z field.
         * @param z field value
         * @return this model
         */
        public SensorReadingQuaternion z(double z) {
            set("z", z);
            return this;
        }
        /**
         * Sets the w field.
         * @param w field value
         * @return this model
         */
        public SensorReadingQuaternion w(double w) {
            set("w", w);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReading extends CdpObject {
        public SensorReading() {}
        private SensorReading(Map<String, Object> values) { super(values); }
        public static SensorReading fromMap(Map<String, Object> values) {
            return new SensorReading(values);
        }
        /**
         * Returns the single field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingSingle> single() {
            return optional(SINGLE);
        }
        /**
         * Returns the xyz field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingXYZ> xyz() {
            return optional(XYZ);
        }
        /**
         * Returns the quaternion field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingQuaternion> quaternion() {
            return optional(QUATERNION);
        }
        /**
         * Sets the single field.
         * @param single field value
         * @return this model
         */
        public SensorReading single(Emulation.SensorReadingSingle single) {
            set("single", single);
            return this;
        }
        /**
         * Sets the xyz field.
         * @param xyz field value
         * @return this model
         */
        public SensorReading xyz(Emulation.SensorReadingXYZ xyz) {
            set("xyz", xyz);
            return this;
        }
        /**
         * Sets the quaternion field.
         * @param quaternion field value
         * @return this model
         */
        public SensorReading quaternion(Emulation.SensorReadingQuaternion quaternion) {
            set("quaternion", quaternion);
            return this;
        }
    }
    /**
     * Wire values for PressureSource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PressureSource implements CdpValue<String> {
        CPU("cpu");
        public final String value;
        PressureSource(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PressureSource of(String value) {
            for (PressureSource constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PressureSource value: " + value);
        }
    }
    /**
     * Wire values for PressureState.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PressureState implements CdpValue<String> {
        NOMINAL("nominal"),
        FAIR("fair"),
        SERIOUS("serious"),
        CRITICAL("critical");
        public final String value;
        PressureState(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PressureState of(String value) {
            for (PressureState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PressureState value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PressureMetadata extends CdpObject {
        public PressureMetadata() {}
        private PressureMetadata(Map<String, Object> values) { super(values); }
        public static PressureMetadata fromMap(Map<String, Object> values) {
            return new PressureMetadata(values);
        }
        /**
         * Returns the available field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> available() {
            return optionalBoolean("available");
        }
        /**
         * Sets the available field.
         * @param available field value
         * @return this model
         */
        public PressureMetadata available(boolean available) {
            set("available", available);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WorkAreaInsets extends CdpObject {
        public WorkAreaInsets() {}
        private WorkAreaInsets(Map<String, Object> values) { super(values); }
        public static WorkAreaInsets fromMap(Map<String, Object> values) {
            return new WorkAreaInsets(values);
        }
        /**
         * Work area top inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            return optionalLong("top");
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return optionalLong("left");
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottom() {
            return optionalLong("bottom");
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong right() {
            return optionalLong("right");
        }
        /**
         * Work area top inset in pixels. Default is 0;
         * @param top field value
         * @return this model
         */
        public WorkAreaInsets top(long top) {
            set("top", top);
            return this;
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @param left field value
         * @return this model
         */
        public WorkAreaInsets left(long left) {
            set("left", left);
            return this;
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @param bottom field value
         * @return this model
         */
        public WorkAreaInsets bottom(long bottom) {
            set("bottom", bottom);
            return this;
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @param right field value
         * @return this model
         */
        public WorkAreaInsets right(long right) {
            set("right", right);
            return this;
        }
    }
    /**
     * Tagged String wire value for ScreenId.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreenId implements CdpValue<String> {
        public final String value;
        public ScreenId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof ScreenId)) return false;
            return value.equals(((ScreenId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "ScreenId(" + value + ")"; }
    }
    /**
     * Screen information similar to the one returned by window.getScreenDetails() method, see https://w3c.github.io/window-management/#screendetailed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreenInfo extends CdpObject {
        public ScreenInfo() {}
        private ScreenInfo(Map<String, Object> values) { super(values); }
        public static ScreenInfo fromMap(Map<String, Object> values) {
            return new ScreenInfo(values);
        }
        /**
         * Offset of the left edge of the screen.
         * @return the protocol field value
         */
        public long left() {
            return requireLong("left");
        }
        /**
         * Offset of the top edge of the screen.
         * @return the protocol field value
         */
        public long top() {
            return requireLong("top");
        }
        /**
         * Width of the screen.
         * @return the protocol field value
         */
        public long width() {
            return requireLong("width");
        }
        /**
         * Height of the screen.
         * @return the protocol field value
         */
        public long height() {
            return requireLong("height");
        }
        /**
         * Offset of the left edge of the available screen area.
         * @return the protocol field value
         */
        public long availLeft() {
            return requireLong("availLeft");
        }
        /**
         * Offset of the top edge of the available screen area.
         * @return the protocol field value
         */
        public long availTop() {
            return requireLong("availTop");
        }
        /**
         * Width of the available screen area.
         * @return the protocol field value
         */
        public long availWidth() {
            return requireLong("availWidth");
        }
        /**
         * Height of the available screen area.
         * @return the protocol field value
         */
        public long availHeight() {
            return requireLong("availHeight");
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value
         */
        public double devicePixelRatio() {
            return requireDouble("devicePixelRatio");
        }
        /**
         * Specifies the screen&#x27;s orientation.
         * @return the protocol field value
         */
        public Emulation.ScreenOrientation orientation() {
            return require(ORIENTATION_2);
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value
         */
        public long colorDepth() {
            return requireLong("colorDepth");
        }
        /**
         * Indicates whether the device has multiple screens.
         * @return the protocol field value
         */
        public boolean isExtended() {
            return requireBoolean("isExtended");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device.
         * @return the protocol field value
         */
        public boolean isInternal() {
            return requireBoolean("isInternal");
        }
        /**
         * Indicates whether the screen is set as the the operating system primary screen.
         * @return the protocol field value
         */
        public boolean isPrimary() {
            return requireBoolean("isPrimary");
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value
         */
        public String label() {
            return requireString("label");
        }
        /**
         * Specifies the unique identifier of the screen.
         * @return the protocol field value
         */
        public Emulation.ScreenId id() {
            return require(ID);
        }
        /**
         * Offset of the left edge of the screen.
         * @param left field value
         * @return this model
         */
        public ScreenInfo left(long left) {
            set("left", left);
            return this;
        }
        /**
         * Offset of the top edge of the screen.
         * @param top field value
         * @return this model
         */
        public ScreenInfo top(long top) {
            set("top", top);
            return this;
        }
        /**
         * Width of the screen.
         * @param width field value
         * @return this model
         */
        public ScreenInfo width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Height of the screen.
         * @param height field value
         * @return this model
         */
        public ScreenInfo height(long height) {
            set("height", height);
            return this;
        }
        /**
         * Offset of the left edge of the available screen area.
         * @param availLeft field value
         * @return this model
         */
        public ScreenInfo availLeft(long availLeft) {
            set("availLeft", availLeft);
            return this;
        }
        /**
         * Offset of the top edge of the available screen area.
         * @param availTop field value
         * @return this model
         */
        public ScreenInfo availTop(long availTop) {
            set("availTop", availTop);
            return this;
        }
        /**
         * Width of the available screen area.
         * @param availWidth field value
         * @return this model
         */
        public ScreenInfo availWidth(long availWidth) {
            set("availWidth", availWidth);
            return this;
        }
        /**
         * Height of the available screen area.
         * @param availHeight field value
         * @return this model
         */
        public ScreenInfo availHeight(long availHeight) {
            set("availHeight", availHeight);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @param devicePixelRatio field value
         * @return this model
         */
        public ScreenInfo devicePixelRatio(double devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio);
            return this;
        }
        /**
         * Specifies the screen&#x27;s orientation.
         * @param orientation field value
         * @return this model
         */
        public ScreenInfo orientation(Emulation.ScreenOrientation orientation) {
            set("orientation", orientation);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @param colorDepth field value
         * @return this model
         */
        public ScreenInfo colorDepth(long colorDepth) {
            set("colorDepth", colorDepth);
            return this;
        }
        /**
         * Indicates whether the device has multiple screens.
         * @param isExtended field value
         * @return this model
         */
        public ScreenInfo isExtended(boolean isExtended) {
            set("isExtended", isExtended);
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device.
         * @param isInternal field value
         * @return this model
         */
        public ScreenInfo isInternal(boolean isInternal) {
            set("isInternal", isInternal);
            return this;
        }
        /**
         * Indicates whether the screen is set as the the operating system primary screen.
         * @param isPrimary field value
         * @return this model
         */
        public ScreenInfo isPrimary(boolean isPrimary) {
            set("isPrimary", isPrimary);
            return this;
        }
        /**
         * Specifies the descriptive label for the screen.
         * @param label field value
         * @return this model
         */
        public ScreenInfo label(String label) {
            set("label", label);
            return this;
        }
        /**
         * Specifies the unique identifier of the screen.
         * @param id field value
         * @return this model
         */
        public ScreenInfo id(Emulation.ScreenId id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Enum of image types that can be disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum DisabledImageType implements CdpValue<String> {
        AVIF("avif"),
        JXL("jxl"),
        WEBP("webp");
        public final String value;
        DisabledImageType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static DisabledImageType of(String value) {
            for (DisabledImageType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DisabledImageType value: " + value);
        }
    }
    /**
     * Enables or disables simulating a focused and active page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFocusEmulationEnabledRequest extends CdpObject {
        public SetFocusEmulationEnabledRequest() {}
        public static SetFocusEmulationEnabledRequest fromMap(Map<String, Object> values) {
            final SetFocusEmulationEnabledRequest instance_ = new SetFocusEmulationEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to enable to disable focus emulation.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Whether to enable to disable focus emulation.
         * @param enabled field value
         * @return this model
         */
        public SetFocusEmulationEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Automatically render all web contents using a dark theme.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAutoDarkModeOverrideRequest extends CdpObject {
        public SetAutoDarkModeOverrideRequest() {}
        public static SetAutoDarkModeOverrideRequest fromMap(Map<String, Object> values) {
            final SetAutoDarkModeOverrideRequest instance_ = new SetAutoDarkModeOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enabled() {
            return optionalBoolean("enabled");
        }
        /**
         * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
         * @param enabled field value
         * @return this model
         */
        public SetAutoDarkModeOverrideRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Enables CPU throttling to emulate slow CPUs.
     */
    public static final class SetCPUThrottlingRateRequest extends CdpObject {
        public SetCPUThrottlingRateRequest() {}
        public static SetCPUThrottlingRateRequest fromMap(Map<String, Object> values) {
            final SetCPUThrottlingRateRequest instance_ = new SetCPUThrottlingRateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
         * @return the protocol field value
         */
        public double rate() {
            return requireDouble("rate");
        }
        /**
         * Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
         * @param rate field value
         * @return this model
         */
        public SetCPUThrottlingRateRequest rate(double rate) {
            set("rate", rate);
            return this;
        }
    }
    /**
     * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
     */
    public static final class SetDefaultBackgroundColorOverrideRequest extends CdpObject {
        public SetDefaultBackgroundColorOverrideRequest() {}
        public static SetDefaultBackgroundColorOverrideRequest fromMap(Map<String, Object> values) {
            final SetDefaultBackgroundColorOverrideRequest instance_ = new SetDefaultBackgroundColorOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * RGBA of the default background color. If not specified, any existing override will be cleared.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> color() {
            return optional(COLOR);
        }
        /**
         * RGBA of the default background color. If not specified, any existing override will be cleared.
         * @param color field value
         * @return this model
         */
        public SetDefaultBackgroundColorOverrideRequest color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
    }
    /**
     * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSafeAreaInsetsOverrideRequest extends CdpObject {
        public SetSafeAreaInsetsOverrideRequest() {}
        public static SetSafeAreaInsetsOverrideRequest fromMap(Map<String, Object> values) {
            final SetSafeAreaInsetsOverrideRequest instance_ = new SetSafeAreaInsetsOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the insets field.
         * @return the protocol field value
         */
        public Emulation.SafeAreaInsets insets() {
            return require(INSETS);
        }
        /**
         * Sets the insets field.
         * @param insets field value
         * @return this model
         */
        public SetSafeAreaInsetsOverrideRequest insets(Emulation.SafeAreaInsets insets) {
            set("insets", insets);
            return this;
        }
    }
    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
     */
    public static final class SetDeviceMetricsOverrideRequest extends CdpObject {
        public SetDeviceMetricsOverrideRequest() {}
        public static SetDeviceMetricsOverrideRequest fromMap(Map<String, Object> values) {
            final SetDeviceMetricsOverrideRequest instance_ = new SetDeviceMetricsOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @return the protocol field value
         */
        public long width() {
            return requireLong("width");
        }
        /**
         * Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @return the protocol field value
         */
        public long height() {
            return requireLong("height");
        }
        /**
         * Overriding device scale factor value. 0 disables the override.
         * @return the protocol field value
         */
        public double deviceScaleFactor() {
            return requireDouble("deviceScaleFactor");
        }
        /**
         * Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
         * @return the protocol field value
         */
        public boolean mobile() {
            return requireBoolean("mobile");
        }
        /**
         * Scale to apply to resulting view image.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            return optionalDouble("scale");
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenWidth() {
            return optionalLong("screenWidth");
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenHeight() {
            return optionalLong("screenHeight");
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionX() {
            return optionalLong("positionX");
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionY() {
            return optionalLong("positionY");
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> dontSetVisibleSize() {
            return optionalBoolean("dontSetVisibleSize");
        }
        /**
         * Screen orientation override.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.ScreenOrientation> screenOrientation() {
            return optional(SCREEN_ORIENTATION);
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.Viewport> viewport() {
            return optional(VIEWPORT);
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Emulation.DisplayFeature> displayFeature() {
            return optional(DISPLAY_FEATURE);
        }
        /**
         * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Emulation.DevicePosture> devicePosture() {
            return optional(DEVICE_POSTURE);
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<SetDeviceMetricsOverrideScrollbarTypeValues> scrollbarType() {
            return optional(SCROLLBAR_TYPE);
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> screenOrientationLockEmulation() {
            return optionalBoolean("screenOrientationLockEmulation");
        }
        /**
         * Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @param width field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @param height field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest height(long height) {
            set("height", height);
            return this;
        }
        /**
         * Overriding device scale factor value. 0 disables the override.
         * @param deviceScaleFactor field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest deviceScaleFactor(double deviceScaleFactor) {
            set("deviceScaleFactor", deviceScaleFactor);
            return this;
        }
        /**
         * Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
         * @param mobile field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest mobile(boolean mobile) {
            set("mobile", mobile);
            return this;
        }
        /**
         * Scale to apply to resulting view image.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scale field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scale(double scale) {
            set("scale", scale);
            return this;
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenWidth field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenWidth(long screenWidth) {
            set("screenWidth", screenWidth);
            return this;
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenHeight field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenHeight(long screenHeight) {
            set("screenHeight", screenHeight);
            return this;
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param positionX field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionX(long positionX) {
            set("positionX", positionX);
            return this;
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param positionY field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionY(long positionY) {
            set("positionY", positionY);
            return this;
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param dontSetVisibleSize field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest dontSetVisibleSize(boolean dontSetVisibleSize) {
            set("dontSetVisibleSize", dontSetVisibleSize);
            return this;
        }
        /**
         * Screen orientation override.
         * @param screenOrientation field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenOrientation(Emulation.ScreenOrientation screenOrientation) {
            set("screenOrientation", screenOrientation);
            return this;
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param viewport field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest viewport(Page.Viewport viewport) {
            set("viewport", viewport);
            return this;
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param displayFeature field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetDeviceMetricsOverrideRequest displayFeature(Emulation.DisplayFeature displayFeature) {
            set("displayFeature", displayFeature);
            return this;
        }
        /**
         * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param devicePosture field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetDeviceMetricsOverrideRequest devicePosture(Emulation.DevicePosture devicePosture) {
            set("devicePosture", devicePosture);
            return this;
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scrollbarType field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scrollbarType(SetDeviceMetricsOverrideScrollbarTypeValues scrollbarType) {
            set("scrollbarType", scrollbarType);
            return this;
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenOrientationLockEmulation field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenOrientationLockEmulation(boolean screenOrientationLockEmulation) {
            set("screenOrientationLockEmulation", screenOrientationLockEmulation);
            return this;
        }
    }
    /**
     * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDevicePostureOverrideRequest extends CdpObject {
        public SetDevicePostureOverrideRequest() {}
        public static SetDevicePostureOverrideRequest fromMap(Map<String, Object> values) {
            final SetDevicePostureOverrideRequest instance_ = new SetDevicePostureOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the posture field.
         * @return the protocol field value
         */
        public Emulation.DevicePosture posture() {
            return require(POSTURE);
        }
        /**
         * Sets the posture field.
         * @param posture field value
         * @return this model
         */
        public SetDevicePostureOverrideRequest posture(Emulation.DevicePosture posture) {
            set("posture", posture);
            return this;
        }
    }
    /**
     * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDisplayFeaturesOverrideRequest extends CdpObject {
        public SetDisplayFeaturesOverrideRequest() {}
        public static SetDisplayFeaturesOverrideRequest fromMap(Map<String, Object> values) {
            final SetDisplayFeaturesOverrideRequest instance_ = new SetDisplayFeaturesOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the features field.
         * @return the protocol field value
         */
        public java.util.List<Emulation.DisplayFeature> features() {
            return require(FEATURES);
        }
        /**
         * Sets the features field.
         * @param features field value
         * @return this model
         */
        public SetDisplayFeaturesOverrideRequest features(java.util.List<Emulation.DisplayFeature> features) {
            set("features", features);
            return this;
        }
    }
    /**
     * Request parameters for Emulation.setScrollbarsHidden.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetScrollbarsHiddenRequest extends CdpObject {
        public SetScrollbarsHiddenRequest() {}
        public static SetScrollbarsHiddenRequest fromMap(Map<String, Object> values) {
            final SetScrollbarsHiddenRequest instance_ = new SetScrollbarsHiddenRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether scrollbars should be always hidden.
         * @return the protocol field value
         */
        public boolean hidden() {
            return requireBoolean("hidden");
        }
        /**
         * Whether scrollbars should be always hidden.
         * @param hidden field value
         * @return this model
         */
        public SetScrollbarsHiddenRequest hidden(boolean hidden) {
            set("hidden", hidden);
            return this;
        }
    }
    /**
     * Request parameters for Emulation.setDocumentCookieDisabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDocumentCookieDisabledRequest extends CdpObject {
        public SetDocumentCookieDisabledRequest() {}
        public static SetDocumentCookieDisabledRequest fromMap(Map<String, Object> values) {
            final SetDocumentCookieDisabledRequest instance_ = new SetDocumentCookieDisabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether document.coookie API should be disabled.
         * @return the protocol field value
         */
        public boolean disabled() {
            return requireBoolean("disabled");
        }
        /**
         * Whether document.coookie API should be disabled.
         * @param disabled field value
         * @return this model
         */
        public SetDocumentCookieDisabledRequest disabled(boolean disabled) {
            set("disabled", disabled);
            return this;
        }
    }
    /**
     * Request parameters for Emulation.setEmitTouchEventsForMouse.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetEmitTouchEventsForMouseRequest extends CdpObject {
        public SetEmitTouchEventsForMouseRequest() {}
        public static SetEmitTouchEventsForMouseRequest fromMap(Map<String, Object> values) {
            final SetEmitTouchEventsForMouseRequest instance_ = new SetEmitTouchEventsForMouseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether touch emulation based on mouse input should be enabled.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Touch/gesture events configuration. Default: current platform.
         * @return the protocol field value, empty when absent
         */
        public Optional<SetEmitTouchEventsForMouseConfigurationValues> configuration() {
            return optional(CONFIGURATION);
        }
        /**
         * Whether touch emulation based on mouse input should be enabled.
         * @param enabled field value
         * @return this model
         */
        public SetEmitTouchEventsForMouseRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
        /**
         * Touch/gesture events configuration. Default: current platform.
         * @param configuration field value
         * @return this model
         */
        public SetEmitTouchEventsForMouseRequest configuration(SetEmitTouchEventsForMouseConfigurationValues configuration) {
            set("configuration", configuration);
            return this;
        }
    }
    /**
     * Emulates the given media type or media feature for CSS media queries.
     */
    public static final class SetEmulatedMediaRequest extends CdpObject {
        public SetEmulatedMediaRequest() {}
        public static SetEmulatedMediaRequest fromMap(Map<String, Object> values) {
            final SetEmulatedMediaRequest instance_ = new SetEmulatedMediaRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Media type to emulate. Empty string disables the override.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> media() {
            return optionalString("media");
        }
        /**
         * Media features to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.MediaFeature>> features() {
            return optional(FEATURES_2);
        }
        /**
         * Media type to emulate. Empty string disables the override.
         * @param media field value
         * @return this model
         */
        public SetEmulatedMediaRequest media(String media) {
            set("media", media);
            return this;
        }
        /**
         * Media features to emulate.
         * @param features field value
         * @return this model
         */
        public SetEmulatedMediaRequest features(java.util.List<Emulation.MediaFeature> features) {
            set("features", features);
            return this;
        }
    }
    /**
     * Emulates the given vision deficiency.
     */
    public static final class SetEmulatedVisionDeficiencyRequest extends CdpObject {
        public SetEmulatedVisionDeficiencyRequest() {}
        public static SetEmulatedVisionDeficiencyRequest fromMap(Map<String, Object> values) {
            final SetEmulatedVisionDeficiencyRequest instance_ = new SetEmulatedVisionDeficiencyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Vision deficiency to emulate. Order: best-effort emulations come first, followed by any physiologically accurate emulations for medically recognized color vision deficiencies.
         * @return the protocol field value
         */
        public SetEmulatedVisionDeficiencyTypeValues type() {
            return require(TYPE_3);
        }
        /**
         * Vision deficiency to emulate. Order: best-effort emulations come first, followed by any physiologically accurate emulations for medically recognized color vision deficiencies.
         * @param type field value
         * @return this model
         */
        public SetEmulatedVisionDeficiencyRequest type(SetEmulatedVisionDeficiencyTypeValues type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Emulates the given OS text scale.
     */
    public static final class SetEmulatedOSTextScaleRequest extends CdpObject {
        public SetEmulatedOSTextScaleRequest() {}
        public static SetEmulatedOSTextScaleRequest fromMap(Map<String, Object> values) {
            final SetEmulatedOSTextScaleRequest instance_ = new SetEmulatedOSTextScaleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scale field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            return optionalDouble("scale");
        }
        /**
         * Sets the scale field.
         * @param scale field value
         * @return this model
         */
        public SetEmulatedOSTextScaleRequest scale(double scale) {
            set("scale", scale);
            return this;
        }
    }
    /**
     * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
     */
    public static final class SetGeolocationOverrideRequest extends CdpObject {
        public SetGeolocationOverrideRequest() {}
        public static SetGeolocationOverrideRequest fromMap(Map<String, Object> values) {
            final SetGeolocationOverrideRequest instance_ = new SetGeolocationOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Mock latitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble latitude() {
            return optionalDouble("latitude");
        }
        /**
         * Mock longitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble longitude() {
            return optionalDouble("longitude");
        }
        /**
         * Mock accuracy
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble accuracy() {
            return optionalDouble("accuracy");
        }
        /**
         * Mock altitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble altitude() {
            return optionalDouble("altitude");
        }
        /**
         * Mock altitudeAccuracy
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble altitudeAccuracy() {
            return optionalDouble("altitudeAccuracy");
        }
        /**
         * Mock heading
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble heading() {
            return optionalDouble("heading");
        }
        /**
         * Mock speed
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble speed() {
            return optionalDouble("speed");
        }
        /**
         * Mock latitude
         * @param latitude field value
         * @return this model
         */
        public SetGeolocationOverrideRequest latitude(double latitude) {
            set("latitude", latitude);
            return this;
        }
        /**
         * Mock longitude
         * @param longitude field value
         * @return this model
         */
        public SetGeolocationOverrideRequest longitude(double longitude) {
            set("longitude", longitude);
            return this;
        }
        /**
         * Mock accuracy
         * @param accuracy field value
         * @return this model
         */
        public SetGeolocationOverrideRequest accuracy(double accuracy) {
            set("accuracy", accuracy);
            return this;
        }
        /**
         * Mock altitude
         * @param altitude field value
         * @return this model
         */
        public SetGeolocationOverrideRequest altitude(double altitude) {
            set("altitude", altitude);
            return this;
        }
        /**
         * Mock altitudeAccuracy
         * @param altitudeAccuracy field value
         * @return this model
         */
        public SetGeolocationOverrideRequest altitudeAccuracy(double altitudeAccuracy) {
            set("altitudeAccuracy", altitudeAccuracy);
            return this;
        }
        /**
         * Mock heading
         * @param heading field value
         * @return this model
         */
        public SetGeolocationOverrideRequest heading(double heading) {
            set("heading", heading);
            return this;
        }
        /**
         * Mock speed
         * @param speed field value
         * @return this model
         */
        public SetGeolocationOverrideRequest speed(double speed) {
            set("speed", speed);
            return this;
        }
    }
    /**
     * Request parameters for Emulation.getOverriddenSensorInformation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetOverriddenSensorInformationRequest extends CdpObject {
        public GetOverriddenSensorInformationRequest() {}
        public static GetOverriddenSensorInformationRequest fromMap(Map<String, Object> values) {
            final GetOverriddenSensorInformationRequest instance_ = new GetOverriddenSensorInformationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Emulation.SensorType type() {
            return require(TYPE_4);
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public GetOverriddenSensorInformationRequest type(Emulation.SensorType type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSensorOverrideEnabledRequest extends CdpObject {
        public SetSensorOverrideEnabledRequest() {}
        public static SetSensorOverrideEnabledRequest fromMap(Map<String, Object> values) {
            final SetSensorOverrideEnabledRequest instance_ = new SetSensorOverrideEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Emulation.SensorType type() {
            return require(TYPE_4);
        }
        /**
         * Returns the metadata field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorMetadata> metadata() {
            return optional(METADATA);
        }
        /**
         * Sets the enabled field.
         * @param enabled field value
         * @return this model
         */
        public SetSensorOverrideEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public SetSensorOverrideEnabledRequest type(Emulation.SensorType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the metadata field.
         * @param metadata field value
         * @return this model
         */
        public SetSensorOverrideEnabledRequest metadata(Emulation.SensorMetadata metadata) {
            set("metadata", metadata);
            return this;
        }
    }
    /**
     * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSensorOverrideReadingsRequest extends CdpObject {
        public SetSensorOverrideReadingsRequest() {}
        public static SetSensorOverrideReadingsRequest fromMap(Map<String, Object> values) {
            final SetSensorOverrideReadingsRequest instance_ = new SetSensorOverrideReadingsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Emulation.SensorType type() {
            return require(TYPE_4);
        }
        /**
         * Returns the reading field.
         * @return the protocol field value
         */
        public Emulation.SensorReading reading() {
            return require(READING);
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public SetSensorOverrideReadingsRequest type(Emulation.SensorType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the reading field.
         * @param reading field value
         * @return this model
         */
        public SetSensorOverrideReadingsRequest reading(Emulation.SensorReading reading) {
            set("reading", reading);
            return this;
        }
    }
    /**
     * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPressureSourceOverrideEnabledRequest extends CdpObject {
        public SetPressureSourceOverrideEnabledRequest() {}
        public static SetPressureSourceOverrideEnabledRequest fromMap(Map<String, Object> values) {
            final SetPressureSourceOverrideEnabledRequest instance_ = new SetPressureSourceOverrideEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        public Emulation.PressureSource source() {
            return require(SOURCE);
        }
        /**
         * Returns the metadata field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.PressureMetadata> metadata() {
            return optional(METADATA_2);
        }
        /**
         * Sets the enabled field.
         * @param enabled field value
         * @return this model
         */
        public SetPressureSourceOverrideEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
        /**
         * Sets the source field.
         * @param source field value
         * @return this model
         */
        public SetPressureSourceOverrideEnabledRequest source(Emulation.PressureSource source) {
            set("source", source);
            return this;
        }
        /**
         * Sets the metadata field.
         * @param metadata field value
         * @return this model
         */
        public SetPressureSourceOverrideEnabledRequest metadata(Emulation.PressureMetadata metadata) {
            set("metadata", metadata);
            return this;
        }
    }
    /**
     * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPressureStateOverrideRequest extends CdpObject {
        public SetPressureStateOverrideRequest() {}
        public static SetPressureStateOverrideRequest fromMap(Map<String, Object> values) {
            final SetPressureStateOverrideRequest instance_ = new SetPressureStateOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        public Emulation.PressureSource source() {
            return require(SOURCE);
        }
        /**
         * Returns the state field.
         * @return the protocol field value
         */
        public Emulation.PressureState state() {
            return require(STATE);
        }
        /**
         * Sets the source field.
         * @param source field value
         * @return this model
         */
        public SetPressureStateOverrideRequest source(Emulation.PressureSource source) {
            set("source", source);
            return this;
        }
        /**
         * Sets the state field.
         * @param state field value
         * @return this model
         */
        public SetPressureStateOverrideRequest state(Emulation.PressureState state) {
            set("state", state);
            return this;
        }
    }
    /**
     * Overrides the Idle state.
     */
    public static final class SetIdleOverrideRequest extends CdpObject {
        public SetIdleOverrideRequest() {}
        public static SetIdleOverrideRequest fromMap(Map<String, Object> values) {
            final SetIdleOverrideRequest instance_ = new SetIdleOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Mock isUserActive
         * @return the protocol field value
         */
        public boolean isUserActive() {
            return requireBoolean("isUserActive");
        }
        /**
         * Mock isScreenUnlocked
         * @return the protocol field value
         */
        public boolean isScreenUnlocked() {
            return requireBoolean("isScreenUnlocked");
        }
        /**
         * Mock isUserActive
         * @param isUserActive field value
         * @return this model
         */
        public SetIdleOverrideRequest isUserActive(boolean isUserActive) {
            set("isUserActive", isUserActive);
            return this;
        }
        /**
         * Mock isScreenUnlocked
         * @param isScreenUnlocked field value
         * @return this model
         */
        public SetIdleOverrideRequest isScreenUnlocked(boolean isScreenUnlocked) {
            set("isScreenUnlocked", isScreenUnlocked);
            return this;
        }
    }
    /**
     * Overrides value returned by the javascript navigator object.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetNavigatorOverridesRequest extends CdpObject {
        public SetNavigatorOverridesRequest() {}
        public static SetNavigatorOverridesRequest fromMap(Map<String, Object> values) {
            final SetNavigatorOverridesRequest instance_ = new SetNavigatorOverridesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value
         */
        public String platform() {
            return requireString("platform");
        }
        /**
         * The platform navigator.platform should return.
         * @param platform field value
         * @return this model
         */
        public SetNavigatorOverridesRequest platform(String platform) {
            set("platform", platform);
            return this;
        }
    }
    /**
     * Sets a specified page scale factor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPageScaleFactorRequest extends CdpObject {
        public SetPageScaleFactorRequest() {}
        public static SetPageScaleFactorRequest fromMap(Map<String, Object> values) {
            final SetPageScaleFactorRequest instance_ = new SetPageScaleFactorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Page scale factor.
         * @return the protocol field value
         */
        public double pageScaleFactor() {
            return requireDouble("pageScaleFactor");
        }
        /**
         * Page scale factor.
         * @param pageScaleFactor field value
         * @return this model
         */
        public SetPageScaleFactorRequest pageScaleFactor(double pageScaleFactor) {
            set("pageScaleFactor", pageScaleFactor);
            return this;
        }
    }
    /**
     * Switches script execution in the page.
     */
    public static final class SetScriptExecutionDisabledRequest extends CdpObject {
        public SetScriptExecutionDisabledRequest() {}
        public static SetScriptExecutionDisabledRequest fromMap(Map<String, Object> values) {
            final SetScriptExecutionDisabledRequest instance_ = new SetScriptExecutionDisabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether script execution should be disabled in the page.
         * @return the protocol field value
         */
        public boolean value() {
            return requireBoolean("value");
        }
        /**
         * Whether script execution should be disabled in the page.
         * @param value field value
         * @return this model
         */
        public SetScriptExecutionDisabledRequest value(boolean value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Enables touch on platforms which do not support them.
     */
    public static final class SetTouchEmulationEnabledRequest extends CdpObject {
        public SetTouchEmulationEnabledRequest() {}
        public static SetTouchEmulationEnabledRequest fromMap(Map<String, Object> values) {
            final SetTouchEmulationEnabledRequest instance_ = new SetTouchEmulationEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether the touch event emulation should be enabled.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Maximum touch points supported. Defaults to one.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxTouchPoints() {
            return optionalLong("maxTouchPoints");
        }
        /**
         * Whether the touch event emulation should be enabled.
         * @param enabled field value
         * @return this model
         */
        public SetTouchEmulationEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
        /**
         * Maximum touch points supported. Defaults to one.
         * @param maxTouchPoints field value
         * @return this model
         */
        public SetTouchEmulationEnabledRequest maxTouchPoints(long maxTouchPoints) {
            set("maxTouchPoints", maxTouchPoints);
            return this;
        }
    }
    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetVirtualTimePolicyRequest extends CdpObject {
        public SetVirtualTimePolicyRequest() {}
        public static SetVirtualTimePolicyRequest fromMap(Map<String, Object> values) {
            final SetVirtualTimePolicyRequest instance_ = new SetVirtualTimePolicyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the policy field.
         * @return the protocol field value
         */
        public Emulation.VirtualTimePolicy policy() {
            return require(POLICY);
        }
        /**
         * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble budget() {
            return optionalDouble("budget");
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxVirtualTimeTaskStarvationCount() {
            return optionalLong("maxVirtualTimeTaskStarvationCount");
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> initialVirtualTime() {
            return optional(INITIAL_VIRTUAL_TIME);
        }
        /**
         * Sets the policy field.
         * @param policy field value
         * @return this model
         */
        public SetVirtualTimePolicyRequest policy(Emulation.VirtualTimePolicy policy) {
            set("policy", policy);
            return this;
        }
        /**
         * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
         * @param budget field value
         * @return this model
         */
        public SetVirtualTimePolicyRequest budget(double budget) {
            set("budget", budget);
            return this;
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @param maxVirtualTimeTaskStarvationCount field value
         * @return this model
         */
        public SetVirtualTimePolicyRequest maxVirtualTimeTaskStarvationCount(long maxVirtualTimeTaskStarvationCount) {
            set("maxVirtualTimeTaskStarvationCount", maxVirtualTimeTaskStarvationCount);
            return this;
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @param initialVirtualTime field value
         * @return this model
         */
        public SetVirtualTimePolicyRequest initialVirtualTime(Network.TimeSinceEpoch initialVirtualTime) {
            set("initialVirtualTime", initialVirtualTime);
            return this;
        }
    }
    /**
     * Overrides default host system locale with the specified one.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetLocaleOverrideRequest extends CdpObject {
        public SetLocaleOverrideRequest() {}
        public static SetLocaleOverrideRequest fromMap(Map<String, Object> values) {
            final SetLocaleOverrideRequest instance_ = new SetLocaleOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> locale() {
            return optionalString("locale");
        }
        /**
         * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
         * @param locale field value
         * @return this model
         */
        public SetLocaleOverrideRequest locale(String locale) {
            set("locale", locale);
            return this;
        }
    }
    /**
     * Overrides default host system timezone with the specified one.
     */
    public static final class SetTimezoneOverrideRequest extends CdpObject {
        public SetTimezoneOverrideRequest() {}
        public static SetTimezoneOverrideRequest fromMap(Map<String, Object> values) {
            final SetTimezoneOverrideRequest instance_ = new SetTimezoneOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The timezone identifier. List of supported timezones: https://source.chromium.org/chromium/chromium/deps/icu.git/+/faee8bc70570192d82d2978a71e2a615788597d1:source/data/misc/metaZones.txt If empty, disables the override and restores default host system timezone.
         * @return the protocol field value
         */
        public String timezoneId() {
            return requireString("timezoneId");
        }
        /**
         * The timezone identifier. List of supported timezones: https://source.chromium.org/chromium/chromium/deps/icu.git/+/faee8bc70570192d82d2978a71e2a615788597d1:source/data/misc/metaZones.txt If empty, disables the override and restores default host system timezone.
         * @param timezoneId field value
         * @return this model
         */
        public SetTimezoneOverrideRequest timezoneId(String timezoneId) {
            set("timezoneId", timezoneId);
            return this;
        }
    }
    /**
     * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetVisibleSizeRequest extends CdpObject {
        public SetVisibleSizeRequest() {}
        public static SetVisibleSizeRequest fromMap(Map<String, Object> values) {
            final SetVisibleSizeRequest instance_ = new SetVisibleSizeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame width (DIP).
         * @return the protocol field value
         */
        public long width() {
            return requireLong("width");
        }
        /**
         * Frame height (DIP).
         * @return the protocol field value
         */
        public long height() {
            return requireLong("height");
        }
        /**
         * Frame width (DIP).
         * @param width field value
         * @return this model
         */
        public SetVisibleSizeRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Frame height (DIP).
         * @param height field value
         * @return this model
         */
        public SetVisibleSizeRequest height(long height) {
            set("height", height);
            return this;
        }
    }
    /**
     * Request parameters for Emulation.setDisabledImageTypes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDisabledImageTypesRequest extends CdpObject {
        public SetDisabledImageTypesRequest() {}
        public static SetDisabledImageTypesRequest fromMap(Map<String, Object> values) {
            final SetDisabledImageTypesRequest instance_ = new SetDisabledImageTypesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Image types to disable.
         * @return the protocol field value
         */
        public java.util.List<Emulation.DisabledImageType> imageTypes() {
            return require(IMAGE_TYPES);
        }
        /**
         * Image types to disable.
         * @param imageTypes field value
         * @return this model
         */
        public SetDisabledImageTypesRequest imageTypes(java.util.List<Emulation.DisabledImageType> imageTypes) {
            set("imageTypes", imageTypes);
            return this;
        }
    }
    /**
     * Override the value of navigator.connection.saveData
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDataSaverOverrideRequest extends CdpObject {
        public SetDataSaverOverrideRequest() {}
        public static SetDataSaverOverrideRequest fromMap(Map<String, Object> values) {
            final SetDataSaverOverrideRequest instance_ = new SetDataSaverOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Override value. Omitting the parameter disables the override.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> dataSaverEnabled() {
            return optionalBoolean("dataSaverEnabled");
        }
        /**
         * Override value. Omitting the parameter disables the override.
         * @param dataSaverEnabled field value
         * @return this model
         */
        public SetDataSaverOverrideRequest dataSaverEnabled(boolean dataSaverEnabled) {
            set("dataSaverEnabled", dataSaverEnabled);
            return this;
        }
    }
    /**
     * Request parameters for Emulation.setHardwareConcurrencyOverride.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetHardwareConcurrencyOverrideRequest extends CdpObject {
        public SetHardwareConcurrencyOverrideRequest() {}
        public static SetHardwareConcurrencyOverrideRequest fromMap(Map<String, Object> values) {
            final SetHardwareConcurrencyOverrideRequest instance_ = new SetHardwareConcurrencyOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Hardware concurrency to report
         * @return the protocol field value
         */
        public long hardwareConcurrency() {
            return requireLong("hardwareConcurrency");
        }
        /**
         * Hardware concurrency to report
         * @param hardwareConcurrency field value
         * @return this model
         */
        public SetHardwareConcurrencyOverrideRequest hardwareConcurrency(long hardwareConcurrency) {
            set("hardwareConcurrency", hardwareConcurrency);
            return this;
        }
    }
    /**
     * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
     */
    public static final class SetUserAgentOverrideRequest extends CdpObject {
        public SetUserAgentOverrideRequest() {}
        public static SetUserAgentOverrideRequest fromMap(Map<String, Object> values) {
            final SetUserAgentOverrideRequest instance_ = new SetUserAgentOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * User agent to use.
         * @return the protocol field value
         */
        public String userAgent() {
            return requireString("userAgent");
        }
        /**
         * Browser language to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> acceptLanguage() {
            return optionalString("acceptLanguage");
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> platform() {
            return optionalString("platform");
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.UserAgentMetadata> userAgentMetadata() {
            return optional(USER_AGENT_METADATA);
        }
        /**
         * User agent to use.
         * @param userAgent field value
         * @return this model
         */
        public SetUserAgentOverrideRequest userAgent(String userAgent) {
            set("userAgent", userAgent);
            return this;
        }
        /**
         * Browser language to emulate.
         * @param acceptLanguage field value
         * @return this model
         */
        public SetUserAgentOverrideRequest acceptLanguage(String acceptLanguage) {
            set("acceptLanguage", acceptLanguage);
            return this;
        }
        /**
         * The platform navigator.platform should return.
         * @param platform field value
         * @return this model
         */
        public SetUserAgentOverrideRequest platform(String platform) {
            set("platform", platform);
            return this;
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param userAgentMetadata field value
         * @return this model
         */
        public SetUserAgentOverrideRequest userAgentMetadata(Emulation.UserAgentMetadata userAgentMetadata) {
            set("userAgentMetadata", userAgentMetadata);
            return this;
        }
    }
    /**
     * Allows overriding the automation flag.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAutomationOverrideRequest extends CdpObject {
        public SetAutomationOverrideRequest() {}
        public static SetAutomationOverrideRequest fromMap(Map<String, Object> values) {
            final SetAutomationOverrideRequest instance_ = new SetAutomationOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether the override should be enabled.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Whether the override should be enabled.
         * @param enabled field value
         * @return this model
         */
        public SetAutomationOverrideRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSmallViewportHeightDifferenceOverrideRequest extends CdpObject {
        public SetSmallViewportHeightDifferenceOverrideRequest() {}
        public static SetSmallViewportHeightDifferenceOverrideRequest fromMap(Map<String, Object> values) {
            final SetSmallViewportHeightDifferenceOverrideRequest instance_ = new SetSmallViewportHeightDifferenceOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * This will cause an element of size 100svh to be {@code difference} pixels smaller than an element of size 100lvh.
         * @return the protocol field value
         */
        public long difference() {
            return requireLong("difference");
        }
        /**
         * This will cause an element of size 100svh to be {@code difference} pixels smaller than an element of size 100lvh.
         * @param difference field value
         * @return this model
         */
        public SetSmallViewportHeightDifferenceOverrideRequest difference(long difference) {
            set("difference", difference);
            return this;
        }
    }
    /**
     * Add a new screen to the device. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AddScreenRequest extends CdpObject {
        public AddScreenRequest() {}
        public static AddScreenRequest fromMap(Map<String, Object> values) {
            final AddScreenRequest instance_ = new AddScreenRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @return the protocol field value
         */
        public long left() {
            return requireLong("left");
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value
         */
        public long top() {
            return requireLong("top");
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value
         */
        public long width() {
            return requireLong("width");
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value
         */
        public long height() {
            return requireLong("height");
        }
        /**
         * Specifies the screen&#x27;s work area. Default is entire screen.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.WorkAreaInsets> workAreaInsets() {
            return optional(WORK_AREA_INSETS);
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble devicePixelRatio() {
            return optionalDouble("devicePixelRatio");
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rotation() {
            return optionalLong("rotation");
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong colorDepth() {
            return optionalLong("colorDepth");
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return optionalString("label");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isInternal() {
            return optionalBoolean("isInternal");
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @param left field value
         * @return this model
         */
        public AddScreenRequest left(long left) {
            set("left", left);
            return this;
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @param top field value
         * @return this model
         */
        public AddScreenRequest top(long top) {
            set("top", top);
            return this;
        }
        /**
         * The width of the screen in pixels.
         * @param width field value
         * @return this model
         */
        public AddScreenRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * The height of the screen in pixels.
         * @param height field value
         * @return this model
         */
        public AddScreenRequest height(long height) {
            set("height", height);
            return this;
        }
        /**
         * Specifies the screen&#x27;s work area. Default is entire screen.
         * @param workAreaInsets field value
         * @return this model
         */
        public AddScreenRequest workAreaInsets(Emulation.WorkAreaInsets workAreaInsets) {
            set("workAreaInsets", workAreaInsets);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @param devicePixelRatio field value
         * @return this model
         */
        public AddScreenRequest devicePixelRatio(double devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio);
            return this;
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @param rotation field value
         * @return this model
         */
        public AddScreenRequest rotation(long rotation) {
            set("rotation", rotation);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @param colorDepth field value
         * @return this model
         */
        public AddScreenRequest colorDepth(long colorDepth) {
            set("colorDepth", colorDepth);
            return this;
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @param label field value
         * @return this model
         */
        public AddScreenRequest label(String label) {
            set("label", label);
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @param isInternal field value
         * @return this model
         */
        public AddScreenRequest isInternal(boolean isInternal) {
            set("isInternal", isInternal);
            return this;
        }
    }
    /**
     * Updates specified screen parameters. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UpdateScreenRequest extends CdpObject {
        public UpdateScreenRequest() {}
        public static UpdateScreenRequest fromMap(Map<String, Object> values) {
            final UpdateScreenRequest instance_ = new UpdateScreenRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Target screen identifier.
         * @return the protocol field value
         */
        public Emulation.ScreenId screenId() {
            return require(SCREEN_ID);
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return optionalLong("left");
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            return optionalLong("top");
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            return optionalLong("width");
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            return optionalLong("height");
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.WorkAreaInsets> workAreaInsets() {
            return optional(WORK_AREA_INSETS);
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble devicePixelRatio() {
            return optionalDouble("devicePixelRatio");
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rotation() {
            return optionalLong("rotation");
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong colorDepth() {
            return optionalLong("colorDepth");
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return optionalString("label");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isInternal() {
            return optionalBoolean("isInternal");
        }
        /**
         * Target screen identifier.
         * @param screenId field value
         * @return this model
         */
        public UpdateScreenRequest screenId(Emulation.ScreenId screenId) {
            set("screenId", screenId);
            return this;
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @param left field value
         * @return this model
         */
        public UpdateScreenRequest left(long left) {
            set("left", left);
            return this;
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @param top field value
         * @return this model
         */
        public UpdateScreenRequest top(long top) {
            set("top", top);
            return this;
        }
        /**
         * The width of the screen in pixels.
         * @param width field value
         * @return this model
         */
        public UpdateScreenRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * The height of the screen in pixels.
         * @param height field value
         * @return this model
         */
        public UpdateScreenRequest height(long height) {
            set("height", height);
            return this;
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @param workAreaInsets field value
         * @return this model
         */
        public UpdateScreenRequest workAreaInsets(Emulation.WorkAreaInsets workAreaInsets) {
            set("workAreaInsets", workAreaInsets);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @param devicePixelRatio field value
         * @return this model
         */
        public UpdateScreenRequest devicePixelRatio(double devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio);
            return this;
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @param rotation field value
         * @return this model
         */
        public UpdateScreenRequest rotation(long rotation) {
            set("rotation", rotation);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @param colorDepth field value
         * @return this model
         */
        public UpdateScreenRequest colorDepth(long colorDepth) {
            set("colorDepth", colorDepth);
            return this;
        }
        /**
         * Specifies the descriptive label for the screen.
         * @param label field value
         * @return this model
         */
        public UpdateScreenRequest label(String label) {
            set("label", label);
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @param isInternal field value
         * @return this model
         */
        public UpdateScreenRequest isInternal(boolean isInternal) {
            set("isInternal", isInternal);
            return this;
        }
    }
    /**
     * Remove screen from the device. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RemoveScreenRequest extends CdpObject {
        public RemoveScreenRequest() {}
        public static RemoveScreenRequest fromMap(Map<String, Object> values) {
            final RemoveScreenRequest instance_ = new RemoveScreenRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the screenId field.
         * @return the protocol field value
         */
        public Emulation.ScreenId screenId() {
            return require(SCREEN_ID);
        }
        /**
         * Sets the screenId field.
         * @param screenId field value
         * @return this model
         */
        public RemoveScreenRequest screenId(Emulation.ScreenId screenId) {
            set("screenId", screenId);
            return this;
        }
    }
    /**
     * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPrimaryScreenRequest extends CdpObject {
        public SetPrimaryScreenRequest() {}
        public static SetPrimaryScreenRequest fromMap(Map<String, Object> values) {
            final SetPrimaryScreenRequest instance_ = new SetPrimaryScreenRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the screenId field.
         * @return the protocol field value
         */
        public Emulation.ScreenId screenId() {
            return require(SCREEN_ID);
        }
        /**
         * Sets the screenId field.
         * @param screenId field value
         * @return this model
         */
        public SetPrimaryScreenRequest screenId(Emulation.ScreenId screenId) {
            set("screenId", screenId);
            return this;
        }
    }
    /**
     * Notification sent after the virtual time budget for the current VirtualTimePolicy has run out.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VirtualTimeBudgetExpiredEvent extends CdpObject {
        public VirtualTimeBudgetExpiredEvent() {}
        private VirtualTimeBudgetExpiredEvent(Map<String, Object> values) { super(values); }
        public static VirtualTimeBudgetExpiredEvent fromMap(Map<String, Object> values) {
            return new VirtualTimeBudgetExpiredEvent(values);
        }
    }
    /**
     * Fired when a page calls screen.orientation.lock() or screen.orientation.unlock() while device emulation is enabled. This allows the DevTools frontend to update the emulated device orientation accordingly.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreenOrientationLockChangedEvent extends CdpObject {
        public ScreenOrientationLockChangedEvent() {}
        private ScreenOrientationLockChangedEvent(Map<String, Object> values) { super(values); }
        public static ScreenOrientationLockChangedEvent fromMap(Map<String, Object> values) {
            return new ScreenOrientationLockChangedEvent(values);
        }
        /**
         * Whether the screen orientation is currently locked.
         * @return the protocol field value
         */
        public boolean locked() {
            return requireBoolean("locked");
        }
        /**
         * The orientation lock type requested by the page. Only set when locked is true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.ScreenOrientation> orientation() {
            return optional(ORIENTATION_2);
        }
        /**
         * Whether the screen orientation is currently locked.
         * @param locked field value
         * @return this model
         */
        public ScreenOrientationLockChangedEvent locked(boolean locked) {
            set("locked", locked);
            return this;
        }
        /**
         * The orientation lock type requested by the page. Only set when locked is true.
         * @param orientation field value
         * @return this model
         */
        public ScreenOrientationLockChangedEvent orientation(Emulation.ScreenOrientation orientation) {
            set("orientation", orientation);
            return this;
        }
    }
    /**
     * Scrollbar type. Default: {@code default}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum SetDeviceMetricsOverrideScrollbarTypeValues implements CdpValue<String> {
        OVERLAY("overlay"),
        DEFAULT("default");
        public final String value;
        SetDeviceMetricsOverrideScrollbarTypeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetDeviceMetricsOverrideScrollbarTypeValues of(String value) {
            for (SetDeviceMetricsOverrideScrollbarTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetDeviceMetricsOverrideScrollbarTypeValues value: " + value);
        }
    }
    /**
     * Touch/gesture events configuration. Default: current platform.
     */
    public enum SetEmitTouchEventsForMouseConfigurationValues implements CdpValue<String> {
        MOBILE("mobile"),
        DESKTOP("desktop");
        public final String value;
        SetEmitTouchEventsForMouseConfigurationValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetEmitTouchEventsForMouseConfigurationValues of(String value) {
            for (SetEmitTouchEventsForMouseConfigurationValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetEmitTouchEventsForMouseConfigurationValues value: " + value);
        }
    }
    /**
     * Vision deficiency to emulate. Order: best-effort emulations come first, followed by any physiologically accurate emulations for medically recognized color vision deficiencies.
     */
    public enum SetEmulatedVisionDeficiencyTypeValues implements CdpValue<String> {
        NONE("none"),
        BLURREDVISION("blurredVision"),
        REDUCEDCONTRAST("reducedContrast"),
        ACHROMATOPSIA("achromatopsia"),
        DEUTERANOPIA("deuteranopia"),
        PROTANOPIA("protanopia"),
        TRITANOPIA("tritanopia");
        public final String value;
        SetEmulatedVisionDeficiencyTypeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetEmulatedVisionDeficiencyTypeValues of(String value) {
            for (SetEmulatedVisionDeficiencyTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetEmulatedVisionDeficiencyTypeValues value: " + value);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Tells whether emulation is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canEmulate() {
            return call("Emulation.canEmulate", RESULT::require);
        }
        /**
         * Clears the overridden device metrics.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDeviceMetricsOverride() {
            return call("Emulation.clearDeviceMetricsOverride");
        }
        /**
         * Clears the overridden Geolocation Position and Error.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearGeolocationOverride() {
            return call("Emulation.clearGeolocationOverride");
        }
        /**
         * Requests that page scale factor is reset to initial values.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPageScaleFactor() {
            return call("Emulation.resetPageScaleFactor");
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFocusEmulationEnabled(boolean enabled) {
            return call("Emulation.setFocusEmulationEnabled", params("enabled", enabled));
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFocusEmulationEnabled(SetFocusEmulationEnabledRequest request) {
            return call("Emulation.setFocusEmulationEnabled", request);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride(boolean enabled) {
            return call("Emulation.setAutoDarkModeOverride", params("enabled", enabled));
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride() {
            return call("Emulation.setAutoDarkModeOverride");
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride(SetAutoDarkModeOverrideRequest request) {
            return call("Emulation.setAutoDarkModeOverride", request);
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param rate protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCPUThrottlingRate(double rate) {
            return call("Emulation.setCPUThrottlingRate", params("rate", rate));
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCPUThrottlingRate(SetCPUThrottlingRateRequest request) {
            return call("Emulation.setCPUThrottlingRate", request);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param color protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride(DOM.RGBA color) {
            return call("Emulation.setDefaultBackgroundColorOverride", params("color", color));
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride() {
            return call("Emulation.setDefaultBackgroundColorOverride");
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride(SetDefaultBackgroundColorOverrideRequest request) {
            return call("Emulation.setDefaultBackgroundColorOverride", request);
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param insets protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSafeAreaInsetsOverride(Emulation.SafeAreaInsets insets) {
            return call("Emulation.setSafeAreaInsetsOverride", params("insets", insets));
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSafeAreaInsetsOverride(SetSafeAreaInsetsOverrideRequest request) {
            return call("Emulation.setSafeAreaInsetsOverride", request);
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param width protocol value
         * @param height protocol value
         * @param deviceScaleFactor protocol value
         * @param mobile protocol value
         * @param scale protocol value
         * @param screenWidth protocol value
         * @param screenHeight protocol value
         * @param positionX protocol value
         * @param positionY protocol value
         * @param dontSetVisibleSize protocol value
         * @param screenOrientation protocol value
         * @param viewport protocol value
         * @param displayFeature protocol value
         * @param devicePosture protocol value
         * @param scrollbarType protocol value
         * @param screenOrientationLockEmulation protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceMetricsOverride(long width, long height, double deviceScaleFactor, boolean mobile, @Nullable Double scale, @Nullable Long screenWidth, @Nullable Long screenHeight, @Nullable Long positionX, @Nullable Long positionY, @Nullable Boolean dontSetVisibleSize, Emulation.@Nullable ScreenOrientation screenOrientation, Page.@Nullable Viewport viewport, Emulation.@Nullable DisplayFeature displayFeature, Emulation.@Nullable DevicePosture devicePosture, @Nullable SetDeviceMetricsOverrideScrollbarTypeValues scrollbarType, @Nullable Boolean screenOrientationLockEmulation) {
            return call("Emulation.setDeviceMetricsOverride", params("width", width, "height", height, "deviceScaleFactor", deviceScaleFactor, "mobile", mobile, "scale", scale, "screenWidth", screenWidth, "screenHeight", screenHeight, "positionX", positionX, "positionY", positionY, "dontSetVisibleSize", dontSetVisibleSize, "screenOrientation", screenOrientation, "viewport", viewport, "displayFeature", displayFeature, "devicePosture", devicePosture, "scrollbarType", scrollbarType, "screenOrientationLockEmulation", screenOrientationLockEmulation));
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param width protocol value
         * @param height protocol value
         * @param deviceScaleFactor protocol value
         * @param mobile protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceMetricsOverride(long width, long height, double deviceScaleFactor, boolean mobile) {
            return call("Emulation.setDeviceMetricsOverride", params("width", width, "height", height, "deviceScaleFactor", deviceScaleFactor, "mobile", mobile));
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceMetricsOverride(SetDeviceMetricsOverrideRequest request) {
            return call("Emulation.setDeviceMetricsOverride", request);
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param posture protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDevicePostureOverride(Emulation.DevicePosture posture) {
            return call("Emulation.setDevicePostureOverride", params("posture", posture));
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDevicePostureOverride(SetDevicePostureOverrideRequest request) {
            return call("Emulation.setDevicePostureOverride", request);
        }
        /**
         * Clears a device posture override set with either setDeviceMetricsOverride() or setDevicePostureOverride() and starts using posture information from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDevicePostureOverride() {
            return call("Emulation.clearDevicePostureOverride");
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param features protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisplayFeaturesOverride(java.util.List<Emulation.DisplayFeature> features) {
            return call("Emulation.setDisplayFeaturesOverride", params("features", features));
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisplayFeaturesOverride(SetDisplayFeaturesOverrideRequest request) {
            return call("Emulation.setDisplayFeaturesOverride", request);
        }
        /**
         * Clears the display features override set with either setDeviceMetricsOverride() or setDisplayFeaturesOverride() and starts using display features from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDisplayFeaturesOverride() {
            return call("Emulation.clearDisplayFeaturesOverride");
        }
        /**
         * Invokes Emulation.setScrollbarsHidden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hidden protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScrollbarsHidden(boolean hidden) {
            return call("Emulation.setScrollbarsHidden", params("hidden", hidden));
        }
        /**
         * Invokes Emulation.setScrollbarsHidden with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScrollbarsHidden(SetScrollbarsHiddenRequest request) {
            return call("Emulation.setScrollbarsHidden", request);
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param disabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentCookieDisabled(boolean disabled) {
            return call("Emulation.setDocumentCookieDisabled", params("disabled", disabled));
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentCookieDisabled(SetDocumentCookieDisabledRequest request) {
            return call("Emulation.setDocumentCookieDisabled", request);
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param configuration protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(boolean enabled, SetEmitTouchEventsForMouseConfigurationValues configuration) {
            return call("Emulation.setEmitTouchEventsForMouse", params("enabled", enabled, "configuration", configuration));
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse with the required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(boolean enabled) {
            return call("Emulation.setEmitTouchEventsForMouse", params("enabled", enabled));
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(SetEmitTouchEventsForMouseRequest request) {
            return call("Emulation.setEmitTouchEventsForMouse", request);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param media protocol value
         * @param features protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia(@Nullable String media, java.util.@Nullable List<Emulation.MediaFeature> features) {
            return call("Emulation.setEmulatedMedia", params("media", media, "features", features));
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia() {
            return call("Emulation.setEmulatedMedia");
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia(SetEmulatedMediaRequest request) {
            return call("Emulation.setEmulatedMedia", request);
        }
        /**
         * Emulates the given vision deficiency.
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyTypeValues type) {
            return call("Emulation.setEmulatedVisionDeficiency", params("type", type));
        }
        /**
         * Emulates the given vision deficiency.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyRequest request) {
            return call("Emulation.setEmulatedVisionDeficiency", request);
        }
        /**
         * Emulates the given OS text scale.
         * @param scale protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale(double scale) {
            return call("Emulation.setEmulatedOSTextScale", params("scale", scale));
        }
        /**
         * Emulates the given OS text scale.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale() {
            return call("Emulation.setEmulatedOSTextScale");
        }
        /**
         * Emulates the given OS text scale.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale(SetEmulatedOSTextScaleRequest request) {
            return call("Emulation.setEmulatedOSTextScale", request);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @param latitude protocol value
         * @param longitude protocol value
         * @param accuracy protocol value
         * @param altitude protocol value
         * @param altitudeAccuracy protocol value
         * @param heading protocol value
         * @param speed protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride(@Nullable Double latitude, @Nullable Double longitude, @Nullable Double accuracy, @Nullable Double altitude, @Nullable Double altitudeAccuracy, @Nullable Double heading, @Nullable Double speed) {
            return call("Emulation.setGeolocationOverride", params("latitude", latitude, "longitude", longitude, "accuracy", accuracy, "altitude", altitude, "altitudeAccuracy", altitudeAccuracy, "heading", heading, "speed", speed));
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride() {
            return call("Emulation.setGeolocationOverride");
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride(SetGeolocationOverrideRequest request) {
            return call("Emulation.setGeolocationOverride", request);
        }
        /**
         * Invokes Emulation.getOverriddenSensorInformation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getOverriddenSensorInformation(Emulation.SensorType type) {
            return call("Emulation.getOverriddenSensorInformation", params("type", type), REQUESTED_SAMPLING_FREQUENCY::require);
        }
        /**
         * Invokes Emulation.getOverriddenSensorInformation with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getOverriddenSensorInformation(GetOverriddenSensorInformationRequest request) {
            return call("Emulation.getOverriddenSensorInformation", request, REQUESTED_SAMPLING_FREQUENCY::require);
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param type protocol value
         * @param metadata protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(boolean enabled, Emulation.SensorType type, Emulation.SensorMetadata metadata) {
            return call("Emulation.setSensorOverrideEnabled", params("enabled", enabled, "type", type, "metadata", metadata));
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(boolean enabled, Emulation.SensorType type) {
            return call("Emulation.setSensorOverrideEnabled", params("enabled", enabled, "type", type));
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(SetSensorOverrideEnabledRequest request) {
            return call("Emulation.setSensorOverrideEnabled", request);
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param reading protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideReadings(Emulation.SensorType type, Emulation.SensorReading reading) {
            return call("Emulation.setSensorOverrideReadings", params("type", type, "reading", reading));
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideReadings(SetSensorOverrideReadingsRequest request) {
            return call("Emulation.setSensorOverrideReadings", request);
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param source protocol value
         * @param metadata protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(boolean enabled, Emulation.PressureSource source, Emulation.PressureMetadata metadata) {
            return call("Emulation.setPressureSourceOverrideEnabled", params("enabled", enabled, "source", source, "metadata", metadata));
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param source protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(boolean enabled, Emulation.PressureSource source) {
            return call("Emulation.setPressureSourceOverrideEnabled", params("enabled", enabled, "source", source));
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(SetPressureSourceOverrideEnabledRequest request) {
            return call("Emulation.setPressureSourceOverrideEnabled", request);
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param source protocol value
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureStateOverride(Emulation.PressureSource source, Emulation.PressureState state) {
            return call("Emulation.setPressureStateOverride", params("source", source, "state", state));
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureStateOverride(SetPressureStateOverrideRequest request) {
            return call("Emulation.setPressureStateOverride", request);
        }
        /**
         * Overrides the Idle state.
         * @param isUserActive protocol value
         * @param isScreenUnlocked protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIdleOverride(boolean isUserActive, boolean isScreenUnlocked) {
            return call("Emulation.setIdleOverride", params("isUserActive", isUserActive, "isScreenUnlocked", isScreenUnlocked));
        }
        /**
         * Overrides the Idle state.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIdleOverride(SetIdleOverrideRequest request) {
            return call("Emulation.setIdleOverride", request);
        }
        /**
         * Clears Idle state overrides.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearIdleOverride() {
            return call("Emulation.clearIdleOverride");
        }
        /**
         * Overrides value returned by the javascript navigator object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param platform protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setNavigatorOverrides(String platform) {
            return call("Emulation.setNavigatorOverrides", params("platform", platform));
        }
        /**
         * Overrides value returned by the javascript navigator object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setNavigatorOverrides(SetNavigatorOverridesRequest request) {
            return call("Emulation.setNavigatorOverrides", request);
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param pageScaleFactor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPageScaleFactor(double pageScaleFactor) {
            return call("Emulation.setPageScaleFactor", params("pageScaleFactor", pageScaleFactor));
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPageScaleFactor(SetPageScaleFactorRequest request) {
            return call("Emulation.setPageScaleFactor", request);
        }
        /**
         * Switches script execution in the page.
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScriptExecutionDisabled(boolean value) {
            return call("Emulation.setScriptExecutionDisabled", params("value", value));
        }
        /**
         * Switches script execution in the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScriptExecutionDisabled(SetScriptExecutionDisabledRequest request) {
            return call("Emulation.setScriptExecutionDisabled", request);
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         * @param maxTouchPoints protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled, long maxTouchPoints) {
            return call("Emulation.setTouchEmulationEnabled", params("enabled", enabled, "maxTouchPoints", maxTouchPoints));
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled) {
            return call("Emulation.setTouchEmulationEnabled", params("enabled", enabled));
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(SetTouchEmulationEnabledRequest request) {
            return call("Emulation.setTouchEmulationEnabled", request);
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param policy protocol value
         * @param budget protocol value
         * @param maxVirtualTimeTaskStarvationCount protocol value
         * @param initialVirtualTime protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> setVirtualTimePolicy(Emulation.VirtualTimePolicy policy, @Nullable Double budget, @Nullable Long maxVirtualTimeTaskStarvationCount, Network.@Nullable TimeSinceEpoch initialVirtualTime) {
            return call("Emulation.setVirtualTimePolicy", params("policy", policy, "budget", budget, "maxVirtualTimeTaskStarvationCount", maxVirtualTimeTaskStarvationCount, "initialVirtualTime", initialVirtualTime), VIRTUAL_TIME_TICKS_BASE::require);
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param policy protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> setVirtualTimePolicy(Emulation.VirtualTimePolicy policy) {
            return call("Emulation.setVirtualTimePolicy", params("policy", policy), VIRTUAL_TIME_TICKS_BASE::require);
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> setVirtualTimePolicy(SetVirtualTimePolicyRequest request) {
            return call("Emulation.setVirtualTimePolicy", request, VIRTUAL_TIME_TICKS_BASE::require);
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param locale protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride(String locale) {
            return call("Emulation.setLocaleOverride", params("locale", locale));
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride() {
            return call("Emulation.setLocaleOverride");
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride(SetLocaleOverrideRequest request) {
            return call("Emulation.setLocaleOverride", request);
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param timezoneId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTimezoneOverride(String timezoneId) {
            return call("Emulation.setTimezoneOverride", params("timezoneId", timezoneId));
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTimezoneOverride(SetTimezoneOverrideRequest request) {
            return call("Emulation.setTimezoneOverride", request);
        }
        /**
         * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param width protocol value
         * @param height protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setVisibleSize(long width, long height) {
            return call("Emulation.setVisibleSize", params("width", width, "height", height));
        }
        /**
         * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setVisibleSize(SetVisibleSizeRequest request) {
            return call("Emulation.setVisibleSize", request);
        }
        /**
         * Invokes Emulation.setDisabledImageTypes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param imageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisabledImageTypes(java.util.List<Emulation.DisabledImageType> imageTypes) {
            return call("Emulation.setDisabledImageTypes", params("imageTypes", imageTypes));
        }
        /**
         * Invokes Emulation.setDisabledImageTypes with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisabledImageTypes(SetDisabledImageTypesRequest request) {
            return call("Emulation.setDisabledImageTypes", request);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param dataSaverEnabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride(boolean dataSaverEnabled) {
            return call("Emulation.setDataSaverOverride", params("dataSaverEnabled", dataSaverEnabled));
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride() {
            return call("Emulation.setDataSaverOverride");
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride(SetDataSaverOverrideRequest request) {
            return call("Emulation.setDataSaverOverride", request);
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hardwareConcurrency protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setHardwareConcurrencyOverride(long hardwareConcurrency) {
            return call("Emulation.setHardwareConcurrencyOverride", params("hardwareConcurrency", hardwareConcurrency));
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setHardwareConcurrencyOverride(SetHardwareConcurrencyOverrideRequest request) {
            return call("Emulation.setHardwareConcurrencyOverride", request);
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param userAgent protocol value
         * @param acceptLanguage protocol value
         * @param platform protocol value
         * @param userAgentMetadata protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent, @Nullable String acceptLanguage, @Nullable String platform, Emulation.@Nullable UserAgentMetadata userAgentMetadata) {
            return call("Emulation.setUserAgentOverride", params("userAgent", userAgent, "acceptLanguage", acceptLanguage, "platform", platform, "userAgentMetadata", userAgentMetadata));
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param userAgent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent) {
            return call("Emulation.setUserAgentOverride", params("userAgent", userAgent));
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(SetUserAgentOverrideRequest request) {
            return call("Emulation.setUserAgentOverride", request);
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomationOverride(boolean enabled) {
            return call("Emulation.setAutomationOverride", params("enabled", enabled));
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomationOverride(SetAutomationOverrideRequest request) {
            return call("Emulation.setAutomationOverride", request);
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param difference protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSmallViewportHeightDifferenceOverride(long difference) {
            return call("Emulation.setSmallViewportHeightDifferenceOverride", params("difference", difference));
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSmallViewportHeightDifferenceOverride(SetSmallViewportHeightDifferenceOverrideRequest request) {
            return call("Emulation.setSmallViewportHeightDifferenceOverride", request);
        }
        /**
         * Returns device&#x27;s screen configuration. In headful mode, the physical screens configuration is returned, whereas in headless mode, a virtual headless screen configuration is provided instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Emulation.ScreenInfo>> getScreenInfos() {
            return call("Emulation.getScreenInfos", SCREEN_INFOS::require);
        }
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param left protocol value
         * @param top protocol value
         * @param width protocol value
         * @param height protocol value
         * @param workAreaInsets protocol value
         * @param devicePixelRatio protocol value
         * @param rotation protocol value
         * @param colorDepth protocol value
         * @param label protocol value
         * @param isInternal protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> addScreen(long left, long top, long width, long height, Emulation.@Nullable WorkAreaInsets workAreaInsets, @Nullable Double devicePixelRatio, @Nullable Long rotation, @Nullable Long colorDepth, @Nullable String label, @Nullable Boolean isInternal) {
            return call("Emulation.addScreen", params("left", left, "top", top, "width", width, "height", height, "workAreaInsets", workAreaInsets, "devicePixelRatio", devicePixelRatio, "rotation", rotation, "colorDepth", colorDepth, "label", label, "isInternal", isInternal), SCREEN_INFO::require);
        }
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param left protocol value
         * @param top protocol value
         * @param width protocol value
         * @param height protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> addScreen(long left, long top, long width, long height) {
            return call("Emulation.addScreen", params("left", left, "top", top, "width", width, "height", height), SCREEN_INFO::require);
        }
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> addScreen(AddScreenRequest request) {
            return call("Emulation.addScreen", request, SCREEN_INFO::require);
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @param left protocol value
         * @param top protocol value
         * @param width protocol value
         * @param height protocol value
         * @param workAreaInsets protocol value
         * @param devicePixelRatio protocol value
         * @param rotation protocol value
         * @param colorDepth protocol value
         * @param label protocol value
         * @param isInternal protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(Emulation.ScreenId screenId, @Nullable Long left, @Nullable Long top, @Nullable Long width, @Nullable Long height, Emulation.@Nullable WorkAreaInsets workAreaInsets, @Nullable Double devicePixelRatio, @Nullable Long rotation, @Nullable Long colorDepth, @Nullable String label, @Nullable Boolean isInternal) {
            return call("Emulation.updateScreen", params("screenId", screenId, "left", left, "top", top, "width", width, "height", height, "workAreaInsets", workAreaInsets, "devicePixelRatio", devicePixelRatio, "rotation", rotation, "colorDepth", colorDepth, "label", label, "isInternal", isInternal), SCREEN_INFO::require);
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(Emulation.ScreenId screenId) {
            return call("Emulation.updateScreen", params("screenId", screenId), SCREEN_INFO::require);
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(UpdateScreenRequest request) {
            return call("Emulation.updateScreen", request, SCREEN_INFO::require);
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScreen(Emulation.ScreenId screenId) {
            return call("Emulation.removeScreen", params("screenId", screenId));
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScreen(RemoveScreenRequest request) {
            return call("Emulation.removeScreen", request);
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrimaryScreen(Emulation.ScreenId screenId) {
            return call("Emulation.setPrimaryScreen", params("screenId", screenId));
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrimaryScreen(SetPrimaryScreenRequest request) {
            return call("Emulation.setPrimaryScreen", request);
        }
        /**
         * Notification sent after the virtual time budget for the current VirtualTimePolicy has run out.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onVirtualTimeBudgetExpired(Consumer<VirtualTimeBudgetExpiredEvent> handler) {
            return on("Emulation.virtualTimeBudgetExpired", VirtualTimeBudgetExpiredEvent::fromMap, handler);
        }
        /**
         * Fired when a page calls screen.orientation.lock() or screen.orientation.unlock() while device emulation is enabled. This allows the DevTools frontend to update the emulated device orientation accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScreenOrientationLockChanged(Consumer<ScreenOrientationLockChangedEvent> handler) {
            return on("Emulation.screenOrientationLockChanged", ScreenOrientationLockChangedEvent::fromMap, handler);
        }
    }
}
