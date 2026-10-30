// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
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
            return raw("top").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong topMax() {
            return raw("topMax").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-inset-left.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return raw("left").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong leftMax() {
            return raw("leftMax").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottom() {
            return raw("bottom").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottomMax() {
            return raw("bottomMax").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-inset-right.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong right() {
            return raw("right").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rightMax() {
            return raw("rightMax").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
            return ScreenOrientation.TypeValues.of((String) require("type"));
        }
        /**
         * Orientation angle.
         * @return the protocol field value
         */
        public long angle() {
            return ((Number) require("angle")).longValue();
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
            return DisplayFeature.OrientationValues.of((String) require("orientation"));
        }
        /**
         * The offset from the screen origin in either the x (for vertical orientation) or y (for horizontal orientation) direction.
         * @return the protocol field value
         */
        public long offset() {
            return ((Number) require("offset")).longValue();
        }
        /**
         * A display feature may mask content such that it is not physically displayed - this length along with the offset describes this area. A display feature that only splits content will have a 0 mask_length.
         * @return the protocol field value
         */
        public long maskLength() {
            return ((Number) require("maskLength")).longValue();
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
            return DevicePosture.TypeValues.of((String) require("type"));
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
            return (String) require("name");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
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
            return (String) require("brand");
        }
        /**
         * Returns the version field.
         * @return the protocol field value
         */
        public String version() {
            return (String) require("version");
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
            return raw("brands").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Emulation.UserAgentBrandVersion.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.UserAgentBrandVersion>> fullVersionList() {
            return raw("fullVersionList").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Emulation.UserAgentBrandVersion.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the fullVersion field.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> fullVersion() {
            return raw("fullVersion").map(value -> (String) value);
        }
        /**
         * Returns the platform field.
         * @return the protocol field value
         */
        public String platform() {
            return (String) require("platform");
        }
        /**
         * Returns the platformVersion field.
         * @return the protocol field value
         */
        public String platformVersion() {
            return (String) require("platformVersion");
        }
        /**
         * Returns the architecture field.
         * @return the protocol field value
         */
        public String architecture() {
            return (String) require("architecture");
        }
        /**
         * Returns the model field.
         * @return the protocol field value
         */
        public String model() {
            return (String) require("model");
        }
        /**
         * Returns the mobile field.
         * @return the protocol field value
         */
        public boolean mobile() {
            return (Boolean) require("mobile");
        }
        /**
         * Returns the bitness field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bitness() {
            return raw("bitness").map(value -> (String) value);
        }
        /**
         * Returns the wow64 field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> wow64() {
            return raw("wow64").map(value -> (Boolean) value);
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> formFactors() {
            return raw("formFactors").flatMap(value -> list(value, element0 -> (String) element0));
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
            return raw("available").map(value -> (Boolean) value);
        }
        /**
         * Returns the minimumFrequency field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble minimumFrequency() {
            return raw("minimumFrequency").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Returns the maximumFrequency field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble maximumFrequency() {
            return raw("maximumFrequency").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
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
            return ((Number) require("value")).doubleValue();
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
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Returns the y field.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Returns the z field.
         * @return the protocol field value
         */
        public double z() {
            return ((Number) require("z")).doubleValue();
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
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Returns the y field.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Returns the z field.
         * @return the protocol field value
         */
        public double z() {
            return ((Number) require("z")).doubleValue();
        }
        /**
         * Returns the w field.
         * @return the protocol field value
         */
        public double w() {
            return ((Number) require("w")).doubleValue();
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
            return raw("single").flatMap(CdpObject::objectMap).map(Emulation.SensorReadingSingle::fromMap);
        }
        /**
         * Returns the xyz field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingXYZ> xyz() {
            return raw("xyz").flatMap(CdpObject::objectMap).map(Emulation.SensorReadingXYZ::fromMap);
        }
        /**
         * Returns the quaternion field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingQuaternion> quaternion() {
            return raw("quaternion").flatMap(CdpObject::objectMap).map(Emulation.SensorReadingQuaternion::fromMap);
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
            return raw("available").map(value -> (Boolean) value);
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
            return raw("top").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return raw("left").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottom() {
            return raw("bottom").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong right() {
            return raw("right").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
            return ((Number) require("left")).longValue();
        }
        /**
         * Offset of the top edge of the screen.
         * @return the protocol field value
         */
        public long top() {
            return ((Number) require("top")).longValue();
        }
        /**
         * Width of the screen.
         * @return the protocol field value
         */
        public long width() {
            return ((Number) require("width")).longValue();
        }
        /**
         * Height of the screen.
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
        }
        /**
         * Offset of the left edge of the available screen area.
         * @return the protocol field value
         */
        public long availLeft() {
            return ((Number) require("availLeft")).longValue();
        }
        /**
         * Offset of the top edge of the available screen area.
         * @return the protocol field value
         */
        public long availTop() {
            return ((Number) require("availTop")).longValue();
        }
        /**
         * Width of the available screen area.
         * @return the protocol field value
         */
        public long availWidth() {
            return ((Number) require("availWidth")).longValue();
        }
        /**
         * Height of the available screen area.
         * @return the protocol field value
         */
        public long availHeight() {
            return ((Number) require("availHeight")).longValue();
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value
         */
        public double devicePixelRatio() {
            return ((Number) require("devicePixelRatio")).doubleValue();
        }
        /**
         * Specifies the screen&#x27;s orientation.
         * @return the protocol field value
         */
        public Emulation.ScreenOrientation orientation() {
            return java.util.Objects.requireNonNull(Emulation.ScreenOrientation.fromMap(CdpObject.requireObjectMap(require("orientation"))));
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value
         */
        public long colorDepth() {
            return ((Number) require("colorDepth")).longValue();
        }
        /**
         * Indicates whether the device has multiple screens.
         * @return the protocol field value
         */
        public boolean isExtended() {
            return (Boolean) require("isExtended");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device.
         * @return the protocol field value
         */
        public boolean isInternal() {
            return (Boolean) require("isInternal");
        }
        /**
         * Indicates whether the screen is set as the the operating system primary screen.
         * @return the protocol field value
         */
        public boolean isPrimary() {
            return (Boolean) require("isPrimary");
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value
         */
        public String label() {
            return (String) require("label");
        }
        /**
         * Specifies the unique identifier of the screen.
         * @return the protocol field value
         */
        public Emulation.ScreenId id() {
            return new Emulation.ScreenId((String) require("id"));
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
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetFocusEmulationEnabledRequest(boolean enabled) {
            enabled(enabled);
        }
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
            return (Boolean) require("enabled");
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
            return raw("enabled").map(value -> (Boolean) value);
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
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param rate protocol value
         */
        public SetCPUThrottlingRateRequest(double rate) {
            rate(rate);
        }
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
            return ((Number) require("rate")).doubleValue();
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
            return raw("color").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
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
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param insets protocol value
         */
        public SetSafeAreaInsetsOverrideRequest(Emulation.SafeAreaInsets insets) {
            insets(insets);
        }
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
            return java.util.Objects.requireNonNull(Emulation.SafeAreaInsets.fromMap(CdpObject.requireObjectMap(require("insets"))));
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
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param width protocol value
         * @param height protocol value
         * @param deviceScaleFactor protocol value
         * @param mobile protocol value
         */
        public SetDeviceMetricsOverrideRequest(long width, long height, double deviceScaleFactor, boolean mobile) {
            width(width).height(height).deviceScaleFactor(deviceScaleFactor).mobile(mobile);
        }
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
            return ((Number) require("width")).longValue();
        }
        /**
         * Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
        }
        /**
         * Overriding device scale factor value. 0 disables the override.
         * @return the protocol field value
         */
        public double deviceScaleFactor() {
            return ((Number) require("deviceScaleFactor")).doubleValue();
        }
        /**
         * Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
         * @return the protocol field value
         */
        public boolean mobile() {
            return (Boolean) require("mobile");
        }
        /**
         * Scale to apply to resulting view image.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            return raw("scale").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenWidth() {
            return raw("screenWidth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenHeight() {
            return raw("screenHeight").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionX() {
            return raw("positionX").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionY() {
            return raw("positionY").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> dontSetVisibleSize() {
            return raw("dontSetVisibleSize").map(value -> (Boolean) value);
        }
        /**
         * Screen orientation override.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.ScreenOrientation> screenOrientation() {
            return raw("screenOrientation").flatMap(CdpObject::objectMap).map(Emulation.ScreenOrientation::fromMap);
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.Viewport> viewport() {
            return raw("viewport").flatMap(CdpObject::objectMap).map(Page.Viewport::fromMap);
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Emulation.DisplayFeature> displayFeature() {
            return raw("displayFeature").flatMap(CdpObject::objectMap).map(Emulation.DisplayFeature::fromMap);
        }
        /**
         * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Emulation.DevicePosture> devicePosture() {
            return raw("devicePosture").flatMap(CdpObject::objectMap).map(Emulation.DevicePosture::fromMap);
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<SetDeviceMetricsOverrideScrollbarTypeValues> scrollbarType() {
            return raw("scrollbarType").map(value -> SetDeviceMetricsOverrideScrollbarTypeValues.of((String) value));
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> screenOrientationLockEmulation() {
            return raw("screenOrientationLockEmulation").map(value -> (Boolean) value);
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
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param posture protocol value
         */
        public SetDevicePostureOverrideRequest(Emulation.DevicePosture posture) {
            posture(posture);
        }
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
            return java.util.Objects.requireNonNull(Emulation.DevicePosture.fromMap(CdpObject.requireObjectMap(require("posture"))));
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
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param features protocol value
         */
        public SetDisplayFeaturesOverrideRequest(java.util.List<Emulation.DisplayFeature> features) {
            features(features);
        }
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
            return CdpObject.requireList(require("features"), element0 -> java.util.Objects.requireNonNull(Emulation.DisplayFeature.fromMap(CdpObject.requireObjectMap(element0))));
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
        /**
         * Creates a new SetScrollbarsHiddenRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hidden protocol value
         */
        public SetScrollbarsHiddenRequest(boolean hidden) {
            hidden(hidden);
        }
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
            return (Boolean) require("hidden");
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
        /**
         * Creates a new SetDocumentCookieDisabledRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param disabled protocol value
         */
        public SetDocumentCookieDisabledRequest(boolean disabled) {
            disabled(disabled);
        }
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
            return (Boolean) require("disabled");
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
        /**
         * Creates a new SetEmitTouchEventsForMouseRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetEmitTouchEventsForMouseRequest(boolean enabled) {
            enabled(enabled);
        }
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
            return (Boolean) require("enabled");
        }
        /**
         * Touch/gesture events configuration. Default: current platform.
         * @return the protocol field value, empty when absent
         */
        public Optional<SetEmitTouchEventsForMouseConfigurationValues> configuration() {
            return raw("configuration").map(value -> SetEmitTouchEventsForMouseConfigurationValues.of((String) value));
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
            return raw("media").map(value -> (String) value);
        }
        /**
         * Media features to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.MediaFeature>> features() {
            return raw("features").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Emulation.MediaFeature.fromMap(CdpObject.requireObjectMap(element0)))));
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
        /**
         * Emulates the given vision deficiency.
         * @param type protocol value
         */
        public SetEmulatedVisionDeficiencyRequest(SetEmulatedVisionDeficiencyTypeValues type) {
            type(type);
        }
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
            return SetEmulatedVisionDeficiencyTypeValues.of((String) require("type"));
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
            return raw("scale").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
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
            return raw("latitude").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Mock longitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble longitude() {
            return raw("longitude").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Mock accuracy
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble accuracy() {
            return raw("accuracy").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Mock altitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble altitude() {
            return raw("altitude").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Mock altitudeAccuracy
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble altitudeAccuracy() {
            return raw("altitudeAccuracy").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Mock heading
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble heading() {
            return raw("heading").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Mock speed
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble speed() {
            return raw("speed").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
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
        /**
         * Creates a new GetOverriddenSensorInformationRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         */
        public GetOverriddenSensorInformationRequest(Emulation.SensorType type) {
            type(type);
        }
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
            return Emulation.SensorType.of((String) require("type"));
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
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param type protocol value
         */
        public SetSensorOverrideEnabledRequest(boolean enabled, Emulation.SensorType type) {
            enabled(enabled).type(type);
        }
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
            return (Boolean) require("enabled");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Emulation.SensorType type() {
            return Emulation.SensorType.of((String) require("type"));
        }
        /**
         * Returns the metadata field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorMetadata> metadata() {
            return raw("metadata").flatMap(CdpObject::objectMap).map(Emulation.SensorMetadata::fromMap);
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
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param reading protocol value
         */
        public SetSensorOverrideReadingsRequest(Emulation.SensorType type, Emulation.SensorReading reading) {
            type(type).reading(reading);
        }
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
            return Emulation.SensorType.of((String) require("type"));
        }
        /**
         * Returns the reading field.
         * @return the protocol field value
         */
        public Emulation.SensorReading reading() {
            return java.util.Objects.requireNonNull(Emulation.SensorReading.fromMap(CdpObject.requireObjectMap(require("reading"))));
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
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param source protocol value
         */
        public SetPressureSourceOverrideEnabledRequest(boolean enabled, Emulation.PressureSource source) {
            enabled(enabled).source(source);
        }
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
            return (Boolean) require("enabled");
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        public Emulation.PressureSource source() {
            return Emulation.PressureSource.of((String) require("source"));
        }
        /**
         * Returns the metadata field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.PressureMetadata> metadata() {
            return raw("metadata").flatMap(CdpObject::objectMap).map(Emulation.PressureMetadata::fromMap);
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
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param source protocol value
         * @param state protocol value
         */
        public SetPressureStateOverrideRequest(Emulation.PressureSource source, Emulation.PressureState state) {
            source(source).state(state);
        }
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
            return Emulation.PressureSource.of((String) require("source"));
        }
        /**
         * Returns the state field.
         * @return the protocol field value
         */
        public Emulation.PressureState state() {
            return Emulation.PressureState.of((String) require("state"));
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
        /**
         * Overrides the Idle state.
         * @param isUserActive protocol value
         * @param isScreenUnlocked protocol value
         */
        public SetIdleOverrideRequest(boolean isUserActive, boolean isScreenUnlocked) {
            isUserActive(isUserActive).isScreenUnlocked(isScreenUnlocked);
        }
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
            return (Boolean) require("isUserActive");
        }
        /**
         * Mock isScreenUnlocked
         * @return the protocol field value
         */
        public boolean isScreenUnlocked() {
            return (Boolean) require("isScreenUnlocked");
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
        /**
         * Overrides value returned by the javascript navigator object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param platform protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetNavigatorOverridesRequest(String platform) {
            platform(platform);
        }
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
            return (String) require("platform");
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
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param pageScaleFactor protocol value
         */
        public SetPageScaleFactorRequest(double pageScaleFactor) {
            pageScaleFactor(pageScaleFactor);
        }
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
            return ((Number) require("pageScaleFactor")).doubleValue();
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
        /**
         * Switches script execution in the page.
         * @param value protocol value
         */
        public SetScriptExecutionDisabledRequest(boolean value) {
            value(value);
        }
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
            return (Boolean) require("value");
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
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         */
        public SetTouchEmulationEnabledRequest(boolean enabled) {
            enabled(enabled);
        }
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
            return (Boolean) require("enabled");
        }
        /**
         * Maximum touch points supported. Defaults to one.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxTouchPoints() {
            return raw("maxTouchPoints").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
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
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param policy protocol value
         */
        public SetVirtualTimePolicyRequest(Emulation.VirtualTimePolicy policy) {
            policy(policy);
        }
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
            return Emulation.VirtualTimePolicy.of((String) require("policy"));
        }
        /**
         * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble budget() {
            return raw("budget").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxVirtualTimeTaskStarvationCount() {
            return raw("maxVirtualTimeTaskStarvationCount").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> initialVirtualTime() {
            return raw("initialVirtualTime").map(value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
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
            return raw("locale").map(value -> (String) value);
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
        /**
         * Overrides default host system timezone with the specified one.
         * @param timezoneId protocol value
         */
        public SetTimezoneOverrideRequest(String timezoneId) {
            timezoneId(timezoneId);
        }
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
            return (String) require("timezoneId");
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
        /**
         * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param width protocol value
         * @param height protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetVisibleSizeRequest(long width, long height) {
            width(width).height(height);
        }
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
            return ((Number) require("width")).longValue();
        }
        /**
         * Frame height (DIP).
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
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
        /**
         * Creates a new SetDisabledImageTypesRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param imageTypes protocol value
         */
        public SetDisabledImageTypesRequest(java.util.List<Emulation.DisabledImageType> imageTypes) {
            imageTypes(imageTypes);
        }
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
            return CdpObject.requireList(require("imageTypes"), element0 -> Emulation.DisabledImageType.of((String) element0));
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
            return raw("dataSaverEnabled").map(value -> (Boolean) value);
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
        /**
         * Creates a new SetHardwareConcurrencyOverrideRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hardwareConcurrency protocol value
         */
        public SetHardwareConcurrencyOverrideRequest(long hardwareConcurrency) {
            hardwareConcurrency(hardwareConcurrency);
        }
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
            return ((Number) require("hardwareConcurrency")).longValue();
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
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param userAgent protocol value
         */
        public SetUserAgentOverrideRequest(String userAgent) {
            userAgent(userAgent);
        }
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
            return (String) require("userAgent");
        }
        /**
         * Browser language to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> acceptLanguage() {
            return raw("acceptLanguage").map(value -> (String) value);
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> platform() {
            return raw("platform").map(value -> (String) value);
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.UserAgentMetadata> userAgentMetadata() {
            return raw("userAgentMetadata").flatMap(CdpObject::objectMap).map(Emulation.UserAgentMetadata::fromMap);
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
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetAutomationOverrideRequest(boolean enabled) {
            enabled(enabled);
        }
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
            return (Boolean) require("enabled");
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
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param difference protocol value
         */
        public SetSmallViewportHeightDifferenceOverrideRequest(long difference) {
            difference(difference);
        }
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
            return ((Number) require("difference")).longValue();
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
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param left protocol value
         * @param top protocol value
         * @param width protocol value
         * @param height protocol value
         */
        public AddScreenRequest(long left, long top, long width, long height) {
            left(left).top(top).width(width).height(height);
        }
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
            return ((Number) require("left")).longValue();
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value
         */
        public long top() {
            return ((Number) require("top")).longValue();
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value
         */
        public long width() {
            return ((Number) require("width")).longValue();
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
        }
        /**
         * Specifies the screen&#x27;s work area. Default is entire screen.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.WorkAreaInsets> workAreaInsets() {
            return raw("workAreaInsets").flatMap(CdpObject::objectMap).map(Emulation.WorkAreaInsets::fromMap);
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble devicePixelRatio() {
            return raw("devicePixelRatio").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rotation() {
            return raw("rotation").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong colorDepth() {
            return raw("colorDepth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return raw("label").map(value -> (String) value);
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isInternal() {
            return raw("isInternal").map(value -> (Boolean) value);
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
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         */
        public UpdateScreenRequest(Emulation.ScreenId screenId) {
            screenId(screenId);
        }
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
            return new Emulation.ScreenId((String) require("screenId"));
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return raw("left").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            return raw("top").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            return raw("width").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            return raw("height").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.WorkAreaInsets> workAreaInsets() {
            return raw("workAreaInsets").flatMap(CdpObject::objectMap).map(Emulation.WorkAreaInsets::fromMap);
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble devicePixelRatio() {
            return raw("devicePixelRatio").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rotation() {
            return raw("rotation").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong colorDepth() {
            return raw("colorDepth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return raw("label").map(value -> (String) value);
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isInternal() {
            return raw("isInternal").map(value -> (Boolean) value);
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
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         */
        public RemoveScreenRequest(Emulation.ScreenId screenId) {
            screenId(screenId);
        }
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
            return new Emulation.ScreenId((String) require("screenId"));
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
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         */
        public SetPrimaryScreenRequest(Emulation.ScreenId screenId) {
            screenId(screenId);
        }
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
            return new Emulation.ScreenId((String) require("screenId"));
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
            return (Boolean) require("locked");
        }
        /**
         * The orientation lock type requested by the page. Only set when locked is true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.ScreenOrientation> orientation() {
            return raw("orientation").flatMap(CdpObject::objectMap).map(Emulation.ScreenOrientation::fromMap);
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Tells whether emulation is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canEmulate() {
            return client.call("Emulation.canEmulate", null, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("result")));
        }
        /**
         * Clears the overridden device metrics.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDeviceMetricsOverride() {
            return client.call("Emulation.clearDeviceMetricsOverride", null);
        }
        /**
         * Clears the overridden Geolocation Position and Error.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearGeolocationOverride() {
            return client.call("Emulation.clearGeolocationOverride", null);
        }
        /**
         * Requests that page scale factor is reset to initial values.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPageScaleFactor() {
            return client.call("Emulation.resetPageScaleFactor", null);
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFocusEmulationEnabled(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setFocusEmulationEnabled", params);
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFocusEmulationEnabled(SetFocusEmulationEnabledRequest request) {
            return client.call("Emulation.setFocusEmulationEnabled", request == null ? null : request.toMap());
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setAutoDarkModeOverride", params);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride() {
            return client.call("Emulation.setAutoDarkModeOverride", null);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride(SetAutoDarkModeOverrideRequest request) {
            return client.call("Emulation.setAutoDarkModeOverride", request == null ? null : request.toMap());
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param rate protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCPUThrottlingRate(double rate) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("rate", CdpObject.json(rate));
            return client.call("Emulation.setCPUThrottlingRate", params);
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCPUThrottlingRate(SetCPUThrottlingRateRequest request) {
            return client.call("Emulation.setCPUThrottlingRate", request == null ? null : request.toMap());
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param color protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride(DOM.RGBA color) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("color", CdpObject.json(color));
            return client.call("Emulation.setDefaultBackgroundColorOverride", params);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride() {
            return client.call("Emulation.setDefaultBackgroundColorOverride", null);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride(SetDefaultBackgroundColorOverrideRequest request) {
            return client.call("Emulation.setDefaultBackgroundColorOverride", request == null ? null : request.toMap());
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param insets protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSafeAreaInsetsOverride(Emulation.SafeAreaInsets insets) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("insets", CdpObject.json(insets));
            return client.call("Emulation.setSafeAreaInsetsOverride", params);
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSafeAreaInsetsOverride(SetSafeAreaInsetsOverrideRequest request) {
            return client.call("Emulation.setSafeAreaInsetsOverride", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            params.put("deviceScaleFactor", CdpObject.json(deviceScaleFactor));
            params.put("mobile", CdpObject.json(mobile));
            if (scale != null) params.put("scale", CdpObject.json(scale));
            if (screenWidth != null) params.put("screenWidth", CdpObject.json(screenWidth));
            if (screenHeight != null) params.put("screenHeight", CdpObject.json(screenHeight));
            if (positionX != null) params.put("positionX", CdpObject.json(positionX));
            if (positionY != null) params.put("positionY", CdpObject.json(positionY));
            if (dontSetVisibleSize != null) params.put("dontSetVisibleSize", CdpObject.json(dontSetVisibleSize));
            if (screenOrientation != null) params.put("screenOrientation", CdpObject.json(screenOrientation));
            if (viewport != null) params.put("viewport", CdpObject.json(viewport));
            if (displayFeature != null) params.put("displayFeature", CdpObject.json(displayFeature));
            if (devicePosture != null) params.put("devicePosture", CdpObject.json(devicePosture));
            if (scrollbarType != null) params.put("scrollbarType", CdpObject.json(scrollbarType));
            if (screenOrientationLockEmulation != null) params.put("screenOrientationLockEmulation", CdpObject.json(screenOrientationLockEmulation));
            return client.call("Emulation.setDeviceMetricsOverride", params);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            params.put("deviceScaleFactor", CdpObject.json(deviceScaleFactor));
            params.put("mobile", CdpObject.json(mobile));
            return client.call("Emulation.setDeviceMetricsOverride", params);
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceMetricsOverride(SetDeviceMetricsOverrideRequest request) {
            return client.call("Emulation.setDeviceMetricsOverride", request == null ? null : request.toMap());
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param posture protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDevicePostureOverride(Emulation.DevicePosture posture) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("posture", CdpObject.json(posture));
            return client.call("Emulation.setDevicePostureOverride", params);
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDevicePostureOverride(SetDevicePostureOverrideRequest request) {
            return client.call("Emulation.setDevicePostureOverride", request == null ? null : request.toMap());
        }
        /**
         * Clears a device posture override set with either setDeviceMetricsOverride() or setDevicePostureOverride() and starts using posture information from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDevicePostureOverride() {
            return client.call("Emulation.clearDevicePostureOverride", null);
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param features protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisplayFeaturesOverride(java.util.List<Emulation.DisplayFeature> features) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("features", CdpObject.json(features));
            return client.call("Emulation.setDisplayFeaturesOverride", params);
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisplayFeaturesOverride(SetDisplayFeaturesOverrideRequest request) {
            return client.call("Emulation.setDisplayFeaturesOverride", request == null ? null : request.toMap());
        }
        /**
         * Clears the display features override set with either setDeviceMetricsOverride() or setDisplayFeaturesOverride() and starts using display features from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDisplayFeaturesOverride() {
            return client.call("Emulation.clearDisplayFeaturesOverride", null);
        }
        /**
         * Invokes Emulation.setScrollbarsHidden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hidden protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScrollbarsHidden(boolean hidden) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("hidden", CdpObject.json(hidden));
            return client.call("Emulation.setScrollbarsHidden", params);
        }
        /**
         * Invokes Emulation.setScrollbarsHidden with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScrollbarsHidden(SetScrollbarsHiddenRequest request) {
            return client.call("Emulation.setScrollbarsHidden", request == null ? null : request.toMap());
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param disabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentCookieDisabled(boolean disabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("disabled", CdpObject.json(disabled));
            return client.call("Emulation.setDocumentCookieDisabled", params);
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentCookieDisabled(SetDocumentCookieDisabledRequest request) {
            return client.call("Emulation.setDocumentCookieDisabled", request == null ? null : request.toMap());
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param configuration protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(boolean enabled, SetEmitTouchEventsForMouseConfigurationValues configuration) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("configuration", CdpObject.json(configuration));
            return client.call("Emulation.setEmitTouchEventsForMouse", params);
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse with the required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setEmitTouchEventsForMouse", params);
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(SetEmitTouchEventsForMouseRequest request) {
            return client.call("Emulation.setEmitTouchEventsForMouse", request == null ? null : request.toMap());
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param media protocol value
         * @param features protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia(@Nullable String media, java.util.@Nullable List<Emulation.MediaFeature> features) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (media != null) params.put("media", CdpObject.json(media));
            if (features != null) params.put("features", CdpObject.json(features));
            return client.call("Emulation.setEmulatedMedia", params);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia() {
            return client.call("Emulation.setEmulatedMedia", null);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia(SetEmulatedMediaRequest request) {
            return client.call("Emulation.setEmulatedMedia", request == null ? null : request.toMap());
        }
        /**
         * Emulates the given vision deficiency.
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyTypeValues type) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            return client.call("Emulation.setEmulatedVisionDeficiency", params);
        }
        /**
         * Emulates the given vision deficiency.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyRequest request) {
            return client.call("Emulation.setEmulatedVisionDeficiency", request == null ? null : request.toMap());
        }
        /**
         * Emulates the given OS text scale.
         * @param scale protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale(double scale) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scale", CdpObject.json(scale));
            return client.call("Emulation.setEmulatedOSTextScale", params);
        }
        /**
         * Emulates the given OS text scale.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale() {
            return client.call("Emulation.setEmulatedOSTextScale", null);
        }
        /**
         * Emulates the given OS text scale.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale(SetEmulatedOSTextScaleRequest request) {
            return client.call("Emulation.setEmulatedOSTextScale", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            if (latitude != null) params.put("latitude", CdpObject.json(latitude));
            if (longitude != null) params.put("longitude", CdpObject.json(longitude));
            if (accuracy != null) params.put("accuracy", CdpObject.json(accuracy));
            if (altitude != null) params.put("altitude", CdpObject.json(altitude));
            if (altitudeAccuracy != null) params.put("altitudeAccuracy", CdpObject.json(altitudeAccuracy));
            if (heading != null) params.put("heading", CdpObject.json(heading));
            if (speed != null) params.put("speed", CdpObject.json(speed));
            return client.call("Emulation.setGeolocationOverride", params);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride() {
            return client.call("Emulation.setGeolocationOverride", null);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride(SetGeolocationOverrideRequest request) {
            return client.call("Emulation.setGeolocationOverride", request == null ? null : request.toMap());
        }
        /**
         * Invokes Emulation.getOverriddenSensorInformation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getOverriddenSensorInformation(Emulation.SensorType type) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            return client.call("Emulation.getOverriddenSensorInformation", params, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("requestedSamplingFrequency"))).doubleValue());
        }
        /**
         * Invokes Emulation.getOverriddenSensorInformation with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getOverriddenSensorInformation(GetOverriddenSensorInformationRequest request) {
            return client.call("Emulation.getOverriddenSensorInformation", request == null ? null : request.toMap(), result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("requestedSamplingFrequency"))).doubleValue());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("type", CdpObject.json(type));
            params.put("metadata", CdpObject.json(metadata));
            return client.call("Emulation.setSensorOverrideEnabled", params);
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(boolean enabled, Emulation.SensorType type) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("type", CdpObject.json(type));
            return client.call("Emulation.setSensorOverrideEnabled", params);
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(SetSensorOverrideEnabledRequest request) {
            return client.call("Emulation.setSensorOverrideEnabled", request == null ? null : request.toMap());
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param reading protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideReadings(Emulation.SensorType type, Emulation.SensorReading reading) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            params.put("reading", CdpObject.json(reading));
            return client.call("Emulation.setSensorOverrideReadings", params);
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideReadings(SetSensorOverrideReadingsRequest request) {
            return client.call("Emulation.setSensorOverrideReadings", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("source", CdpObject.json(source));
            params.put("metadata", CdpObject.json(metadata));
            return client.call("Emulation.setPressureSourceOverrideEnabled", params);
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param source protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(boolean enabled, Emulation.PressureSource source) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("source", CdpObject.json(source));
            return client.call("Emulation.setPressureSourceOverrideEnabled", params);
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(SetPressureSourceOverrideEnabledRequest request) {
            return client.call("Emulation.setPressureSourceOverrideEnabled", request == null ? null : request.toMap());
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param source protocol value
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureStateOverride(Emulation.PressureSource source, Emulation.PressureState state) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("source", CdpObject.json(source));
            params.put("state", CdpObject.json(state));
            return client.call("Emulation.setPressureStateOverride", params);
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureStateOverride(SetPressureStateOverrideRequest request) {
            return client.call("Emulation.setPressureStateOverride", request == null ? null : request.toMap());
        }
        /**
         * Overrides the Idle state.
         * @param isUserActive protocol value
         * @param isScreenUnlocked protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIdleOverride(boolean isUserActive, boolean isScreenUnlocked) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("isUserActive", CdpObject.json(isUserActive));
            params.put("isScreenUnlocked", CdpObject.json(isScreenUnlocked));
            return client.call("Emulation.setIdleOverride", params);
        }
        /**
         * Overrides the Idle state.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIdleOverride(SetIdleOverrideRequest request) {
            return client.call("Emulation.setIdleOverride", request == null ? null : request.toMap());
        }
        /**
         * Clears Idle state overrides.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearIdleOverride() {
            return client.call("Emulation.clearIdleOverride", null);
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("platform", CdpObject.json(platform));
            return client.call("Emulation.setNavigatorOverrides", params);
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
            return client.call("Emulation.setNavigatorOverrides", request == null ? null : request.toMap());
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param pageScaleFactor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPageScaleFactor(double pageScaleFactor) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("pageScaleFactor", CdpObject.json(pageScaleFactor));
            return client.call("Emulation.setPageScaleFactor", params);
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPageScaleFactor(SetPageScaleFactorRequest request) {
            return client.call("Emulation.setPageScaleFactor", request == null ? null : request.toMap());
        }
        /**
         * Switches script execution in the page.
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScriptExecutionDisabled(boolean value) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("value", CdpObject.json(value));
            return client.call("Emulation.setScriptExecutionDisabled", params);
        }
        /**
         * Switches script execution in the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScriptExecutionDisabled(SetScriptExecutionDisabledRequest request) {
            return client.call("Emulation.setScriptExecutionDisabled", request == null ? null : request.toMap());
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         * @param maxTouchPoints protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled, long maxTouchPoints) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("maxTouchPoints", CdpObject.json(maxTouchPoints));
            return client.call("Emulation.setTouchEmulationEnabled", params);
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setTouchEmulationEnabled", params);
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(SetTouchEmulationEnabledRequest request) {
            return client.call("Emulation.setTouchEmulationEnabled", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("policy", CdpObject.json(policy));
            if (budget != null) params.put("budget", CdpObject.json(budget));
            if (maxVirtualTimeTaskStarvationCount != null) params.put("maxVirtualTimeTaskStarvationCount", CdpObject.json(maxVirtualTimeTaskStarvationCount));
            if (initialVirtualTime != null) params.put("initialVirtualTime", CdpObject.json(initialVirtualTime));
            return client.call("Emulation.setVirtualTimePolicy", params, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("virtualTimeTicksBase"))).doubleValue());
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param policy protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> setVirtualTimePolicy(Emulation.VirtualTimePolicy policy) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("policy", CdpObject.json(policy));
            return client.call("Emulation.setVirtualTimePolicy", params, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("virtualTimeTicksBase"))).doubleValue());
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> setVirtualTimePolicy(SetVirtualTimePolicyRequest request) {
            return client.call("Emulation.setVirtualTimePolicy", request == null ? null : request.toMap(), result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("virtualTimeTicksBase"))).doubleValue());
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param locale protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride(String locale) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("locale", CdpObject.json(locale));
            return client.call("Emulation.setLocaleOverride", params);
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride() {
            return client.call("Emulation.setLocaleOverride", null);
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride(SetLocaleOverrideRequest request) {
            return client.call("Emulation.setLocaleOverride", request == null ? null : request.toMap());
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param timezoneId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTimezoneOverride(String timezoneId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("timezoneId", CdpObject.json(timezoneId));
            return client.call("Emulation.setTimezoneOverride", params);
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTimezoneOverride(SetTimezoneOverrideRequest request) {
            return client.call("Emulation.setTimezoneOverride", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            return client.call("Emulation.setVisibleSize", params);
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
            return client.call("Emulation.setVisibleSize", request == null ? null : request.toMap());
        }
        /**
         * Invokes Emulation.setDisabledImageTypes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param imageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisabledImageTypes(java.util.List<Emulation.DisabledImageType> imageTypes) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("imageTypes", CdpObject.json(imageTypes));
            return client.call("Emulation.setDisabledImageTypes", params);
        }
        /**
         * Invokes Emulation.setDisabledImageTypes with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisabledImageTypes(SetDisabledImageTypesRequest request) {
            return client.call("Emulation.setDisabledImageTypes", request == null ? null : request.toMap());
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param dataSaverEnabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride(boolean dataSaverEnabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("dataSaverEnabled", CdpObject.json(dataSaverEnabled));
            return client.call("Emulation.setDataSaverOverride", params);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride() {
            return client.call("Emulation.setDataSaverOverride", null);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride(SetDataSaverOverrideRequest request) {
            return client.call("Emulation.setDataSaverOverride", request == null ? null : request.toMap());
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hardwareConcurrency protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setHardwareConcurrencyOverride(long hardwareConcurrency) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("hardwareConcurrency", CdpObject.json(hardwareConcurrency));
            return client.call("Emulation.setHardwareConcurrencyOverride", params);
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setHardwareConcurrencyOverride(SetHardwareConcurrencyOverrideRequest request) {
            return client.call("Emulation.setHardwareConcurrencyOverride", request == null ? null : request.toMap());
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("userAgent", CdpObject.json(userAgent));
            if (acceptLanguage != null) params.put("acceptLanguage", CdpObject.json(acceptLanguage));
            if (platform != null) params.put("platform", CdpObject.json(platform));
            if (userAgentMetadata != null) params.put("userAgentMetadata", CdpObject.json(userAgentMetadata));
            return client.call("Emulation.setUserAgentOverride", params);
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param userAgent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("userAgent", CdpObject.json(userAgent));
            return client.call("Emulation.setUserAgentOverride", params);
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(SetUserAgentOverrideRequest request) {
            return client.call("Emulation.setUserAgentOverride", request == null ? null : request.toMap());
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomationOverride(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setAutomationOverride", params);
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomationOverride(SetAutomationOverrideRequest request) {
            return client.call("Emulation.setAutomationOverride", request == null ? null : request.toMap());
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param difference protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSmallViewportHeightDifferenceOverride(long difference) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("difference", CdpObject.json(difference));
            return client.call("Emulation.setSmallViewportHeightDifferenceOverride", params);
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSmallViewportHeightDifferenceOverride(SetSmallViewportHeightDifferenceOverrideRequest request) {
            return client.call("Emulation.setSmallViewportHeightDifferenceOverride", request == null ? null : request.toMap());
        }
        /**
         * Returns device&#x27;s screen configuration. In headful mode, the physical screens configuration is returned, whereas in headless mode, a virtual headless screen configuration is provided instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Emulation.ScreenInfo>> getScreenInfos() {
            return client.call("Emulation.getScreenInfos", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("screenInfos")), element0 -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(element0)))));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("left", CdpObject.json(left));
            params.put("top", CdpObject.json(top));
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            if (workAreaInsets != null) params.put("workAreaInsets", CdpObject.json(workAreaInsets));
            if (devicePixelRatio != null) params.put("devicePixelRatio", CdpObject.json(devicePixelRatio));
            if (rotation != null) params.put("rotation", CdpObject.json(rotation));
            if (colorDepth != null) params.put("colorDepth", CdpObject.json(colorDepth));
            if (label != null) params.put("label", CdpObject.json(label));
            if (isInternal != null) params.put("isInternal", CdpObject.json(isInternal));
            return client.call("Emulation.addScreen", params, result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("screenInfo"))))));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("left", CdpObject.json(left));
            params.put("top", CdpObject.json(top));
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            return client.call("Emulation.addScreen", params, result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("screenInfo"))))));
        }
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> addScreen(AddScreenRequest request) {
            return client.call("Emulation.addScreen", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("screenInfo"))))));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            if (left != null) params.put("left", CdpObject.json(left));
            if (top != null) params.put("top", CdpObject.json(top));
            if (width != null) params.put("width", CdpObject.json(width));
            if (height != null) params.put("height", CdpObject.json(height));
            if (workAreaInsets != null) params.put("workAreaInsets", CdpObject.json(workAreaInsets));
            if (devicePixelRatio != null) params.put("devicePixelRatio", CdpObject.json(devicePixelRatio));
            if (rotation != null) params.put("rotation", CdpObject.json(rotation));
            if (colorDepth != null) params.put("colorDepth", CdpObject.json(colorDepth));
            if (label != null) params.put("label", CdpObject.json(label));
            if (isInternal != null) params.put("isInternal", CdpObject.json(isInternal));
            return client.call("Emulation.updateScreen", params, result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("screenInfo"))))));
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(Emulation.ScreenId screenId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            return client.call("Emulation.updateScreen", params, result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("screenInfo"))))));
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(UpdateScreenRequest request) {
            return client.call("Emulation.updateScreen", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("screenInfo"))))));
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScreen(Emulation.ScreenId screenId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            return client.call("Emulation.removeScreen", params);
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScreen(RemoveScreenRequest request) {
            return client.call("Emulation.removeScreen", request == null ? null : request.toMap());
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrimaryScreen(Emulation.ScreenId screenId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            return client.call("Emulation.setPrimaryScreen", params);
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrimaryScreen(SetPrimaryScreenRequest request) {
            return client.call("Emulation.setPrimaryScreen", request == null ? null : request.toMap());
        }
        /**
         * Notification sent after the virtual time budget for the current VirtualTimePolicy has run out.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onVirtualTimeBudgetExpired(Consumer<VirtualTimeBudgetExpiredEvent> handler) {
            return client.on("Emulation.virtualTimeBudgetExpired", VirtualTimeBudgetExpiredEvent::fromMap, handler);
        }
        /**
         * Fired when a page calls screen.orientation.lock() or screen.orientation.unlock() while device emulation is enabled. This allows the DevTools frontend to update the emulated device orientation accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScreenOrientationLockChanged(Consumer<ScreenOrientationLockChangedEvent> handler) {
            return client.on("Emulation.screenOrientationLockChanged", ScreenOrientationLockChangedEvent::fromMap, handler);
        }
    }
}
