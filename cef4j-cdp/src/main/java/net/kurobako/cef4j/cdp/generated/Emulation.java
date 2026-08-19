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
 * This domain emulates different environments for the page.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Emulation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
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
            Long value = CdpObject.numberAsLong(raw("top"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong topMax() {
            Long value = CdpObject.numberAsLong(raw("topMax"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-inset-left.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            Long value = CdpObject.numberAsLong(raw("left"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong leftMax() {
            Long value = CdpObject.numberAsLong(raw("leftMax"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottom() {
            Long value = CdpObject.numberAsLong(raw("bottom"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottomMax() {
            Long value = CdpObject.numberAsLong(raw("bottomMax"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-inset-right.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong right() {
            Long value = CdpObject.numberAsLong(raw("right"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rightMax() {
            Long value = CdpObject.numberAsLong(raw("rightMax"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overrides safe-area-inset-top.
         * @param top field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets top(OptionalLong top) {
            set("top", top.isPresent() ? top.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-inset-top.
         * @param top field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets top(Long top) {
            set("top", top);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @param topMax field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets topMax(OptionalLong topMax) {
            set("topMax", topMax.isPresent() ? topMax.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @param topMax field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets topMax(Long topMax) {
            set("topMax", topMax);
            return this;
        }
        /**
         * Overrides safe-area-inset-left.
         * @param left field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets left(OptionalLong left) {
            set("left", left.isPresent() ? left.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-inset-left.
         * @param left field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets left(Long left) {
            set("left", left);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @param leftMax field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets leftMax(OptionalLong leftMax) {
            set("leftMax", leftMax.isPresent() ? leftMax.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @param leftMax field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets leftMax(Long leftMax) {
            set("leftMax", leftMax);
            return this;
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @param bottom field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets bottom(OptionalLong bottom) {
            set("bottom", bottom.isPresent() ? bottom.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @param bottom field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets bottom(Long bottom) {
            set("bottom", bottom);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @param bottomMax field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets bottomMax(OptionalLong bottomMax) {
            set("bottomMax", bottomMax.isPresent() ? bottomMax.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @param bottomMax field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets bottomMax(Long bottomMax) {
            set("bottomMax", bottomMax);
            return this;
        }
        /**
         * Overrides safe-area-inset-right.
         * @param right field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets right(OptionalLong right) {
            set("right", right.isPresent() ? right.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-inset-right.
         * @param right field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets right(Long right) {
            set("right", right);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @param rightMax field value; empty omits the value
         * @return this model
         */
        public SafeAreaInsets rightMax(OptionalLong rightMax) {
            set("rightMax", rightMax.isPresent() ? rightMax.getAsLong() : null);
            return this;
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @param rightMax field value; null removes the value
         * @return this model
         */
        public SafeAreaInsets rightMax(Long rightMax) {
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static OrientationValues of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static VirtualTimePolicy of(@Nonnull String value) {
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
            return Optional.ofNullable(list(raw("brands"), element0 -> java.util.Objects.requireNonNull(Emulation.UserAgentBrandVersion.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.UserAgentBrandVersion>> fullVersionList() {
            return Optional.ofNullable(list(raw("fullVersionList"), element0 -> java.util.Objects.requireNonNull(Emulation.UserAgentBrandVersion.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns the fullVersion field.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> fullVersion() {
            return Optional.ofNullable((String) raw("fullVersion"));
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
            return Optional.ofNullable((String) raw("bitness"));
        }
        /**
         * Returns the wow64 field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> wow64() {
            return Optional.ofNullable((Boolean) raw("wow64"));
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> formFactors() {
            return Optional.ofNullable(list(raw("formFactors"), element0 -> (String) element0));
        }
        /**
         * Brands appearing in Sec-CH-UA.
         * @param brands field value; empty omits the value
         * @return this model
         */
        public UserAgentMetadata brands(Optional<java.util.List<Emulation.UserAgentBrandVersion>> brands) {
            set("brands", brands.orElse(null));
            return this;
        }
        /**
         * Brands appearing in Sec-CH-UA.
         * @param brands field value; null removes the value
         * @return this model
         */
        public UserAgentMetadata brands(java.util.List<Emulation.UserAgentBrandVersion> brands) {
            set("brands", brands);
            return this;
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @param fullVersionList field value; empty omits the value
         * @return this model
         */
        public UserAgentMetadata fullVersionList(Optional<java.util.List<Emulation.UserAgentBrandVersion>> fullVersionList) {
            set("fullVersionList", fullVersionList.orElse(null));
            return this;
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @param fullVersionList field value; null removes the value
         * @return this model
         */
        public UserAgentMetadata fullVersionList(java.util.List<Emulation.UserAgentBrandVersion> fullVersionList) {
            set("fullVersionList", fullVersionList);
            return this;
        }
        /**
         * Sets the fullVersion field.
         * @param fullVersion field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public UserAgentMetadata fullVersion(Optional<String> fullVersion) {
            set("fullVersion", fullVersion.orElse(null));
            return this;
        }
        /**
         * Sets the fullVersion field.
         * @param fullVersion field value; null removes the value
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
         * @param bitness field value; empty omits the value
         * @return this model
         */
        public UserAgentMetadata bitness(Optional<String> bitness) {
            set("bitness", bitness.orElse(null));
            return this;
        }
        /**
         * Sets the bitness field.
         * @param bitness field value; null removes the value
         * @return this model
         */
        public UserAgentMetadata bitness(String bitness) {
            set("bitness", bitness);
            return this;
        }
        /**
         * Sets the wow64 field.
         * @param wow64 field value; empty omits the value
         * @return this model
         */
        public UserAgentMetadata wow64(Optional<Boolean> wow64) {
            set("wow64", wow64.orElse(null));
            return this;
        }
        /**
         * Sets the wow64 field.
         * @param wow64 field value; null removes the value
         * @return this model
         */
        public UserAgentMetadata wow64(Boolean wow64) {
            set("wow64", wow64);
            return this;
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @param formFactors field value; empty omits the value
         * @return this model
         */
        public UserAgentMetadata formFactors(Optional<java.util.List<String>> formFactors) {
            set("formFactors", formFactors.orElse(null));
            return this;
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @param formFactors field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static SensorType of(@Nonnull String value) {
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
            return Optional.ofNullable((Boolean) raw("available"));
        }
        /**
         * Returns the minimumFrequency field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble minimumFrequency() {
            Double value = CdpObject.numberAsDouble(raw("minimumFrequency"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Returns the maximumFrequency field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble maximumFrequency() {
            Double value = CdpObject.numberAsDouble(raw("maximumFrequency"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Sets the available field.
         * @param available field value; empty omits the value
         * @return this model
         */
        public SensorMetadata available(Optional<Boolean> available) {
            set("available", available.orElse(null));
            return this;
        }
        /**
         * Sets the available field.
         * @param available field value; null removes the value
         * @return this model
         */
        public SensorMetadata available(Boolean available) {
            set("available", available);
            return this;
        }
        /**
         * Sets the minimumFrequency field.
         * @param minimumFrequency field value; empty omits the value
         * @return this model
         */
        public SensorMetadata minimumFrequency(OptionalDouble minimumFrequency) {
            set("minimumFrequency", minimumFrequency.isPresent() ? minimumFrequency.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the minimumFrequency field.
         * @param minimumFrequency field value; null removes the value
         * @return this model
         */
        public SensorMetadata minimumFrequency(Double minimumFrequency) {
            set("minimumFrequency", minimumFrequency);
            return this;
        }
        /**
         * Sets the maximumFrequency field.
         * @param maximumFrequency field value; empty omits the value
         * @return this model
         */
        public SensorMetadata maximumFrequency(OptionalDouble maximumFrequency) {
            set("maximumFrequency", maximumFrequency.isPresent() ? maximumFrequency.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the maximumFrequency field.
         * @param maximumFrequency field value; null removes the value
         * @return this model
         */
        public SensorMetadata maximumFrequency(Double maximumFrequency) {
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
            return Optional.ofNullable(raw("single") == null ? null : Emulation.SensorReadingSingle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("single")))));
        }
        /**
         * Returns the xyz field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingXYZ> xyz() {
            return Optional.ofNullable(raw("xyz") == null ? null : Emulation.SensorReadingXYZ.fromMap(java.util.Objects.requireNonNull(objectMap(raw("xyz")))));
        }
        /**
         * Returns the quaternion field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.SensorReadingQuaternion> quaternion() {
            return Optional.ofNullable(raw("quaternion") == null ? null : Emulation.SensorReadingQuaternion.fromMap(java.util.Objects.requireNonNull(objectMap(raw("quaternion")))));
        }
        /**
         * Sets the single field.
         * @param single field value; empty omits the value
         * @return this model
         */
        public SensorReading single(Optional<Emulation.SensorReadingSingle> single) {
            set("single", single.orElse(null));
            return this;
        }
        /**
         * Sets the single field.
         * @param single field value; null removes the value
         * @return this model
         */
        public SensorReading single(Emulation.SensorReadingSingle single) {
            set("single", single);
            return this;
        }
        /**
         * Sets the xyz field.
         * @param xyz field value; empty omits the value
         * @return this model
         */
        public SensorReading xyz(Optional<Emulation.SensorReadingXYZ> xyz) {
            set("xyz", xyz.orElse(null));
            return this;
        }
        /**
         * Sets the xyz field.
         * @param xyz field value; null removes the value
         * @return this model
         */
        public SensorReading xyz(Emulation.SensorReadingXYZ xyz) {
            set("xyz", xyz);
            return this;
        }
        /**
         * Sets the quaternion field.
         * @param quaternion field value; empty omits the value
         * @return this model
         */
        public SensorReading quaternion(Optional<Emulation.SensorReadingQuaternion> quaternion) {
            set("quaternion", quaternion.orElse(null));
            return this;
        }
        /**
         * Sets the quaternion field.
         * @param quaternion field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static PressureSource of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static PressureState of(@Nonnull String value) {
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
            return Optional.ofNullable((Boolean) raw("available"));
        }
        /**
         * Sets the available field.
         * @param available field value; empty omits the value
         * @return this model
         */
        public PressureMetadata available(Optional<Boolean> available) {
            set("available", available.orElse(null));
            return this;
        }
        /**
         * Sets the available field.
         * @param available field value; null removes the value
         * @return this model
         */
        public PressureMetadata available(Boolean available) {
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
            Long value = CdpObject.numberAsLong(raw("top"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            Long value = CdpObject.numberAsLong(raw("left"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong bottom() {
            Long value = CdpObject.numberAsLong(raw("bottom"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong right() {
            Long value = CdpObject.numberAsLong(raw("right"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Work area top inset in pixels. Default is 0;
         * @param top field value; empty omits the value
         * @return this model
         */
        public WorkAreaInsets top(OptionalLong top) {
            set("top", top.isPresent() ? top.getAsLong() : null);
            return this;
        }
        /**
         * Work area top inset in pixels. Default is 0;
         * @param top field value; null removes the value
         * @return this model
         */
        public WorkAreaInsets top(Long top) {
            set("top", top);
            return this;
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @param left field value; empty omits the value
         * @return this model
         */
        public WorkAreaInsets left(OptionalLong left) {
            set("left", left.isPresent() ? left.getAsLong() : null);
            return this;
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @param left field value; null removes the value
         * @return this model
         */
        public WorkAreaInsets left(Long left) {
            set("left", left);
            return this;
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @param bottom field value; empty omits the value
         * @return this model
         */
        public WorkAreaInsets bottom(OptionalLong bottom) {
            set("bottom", bottom.isPresent() ? bottom.getAsLong() : null);
            return this;
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @param bottom field value; null removes the value
         * @return this model
         */
        public WorkAreaInsets bottom(Long bottom) {
            set("bottom", bottom);
            return this;
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @param right field value; empty omits the value
         * @return this model
         */
        public WorkAreaInsets right(OptionalLong right) {
            set("right", right.isPresent() ? right.getAsLong() : null);
            return this;
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @param right field value; null removes the value
         * @return this model
         */
        public WorkAreaInsets right(Long right) {
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
        public ScreenId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            return java.util.Objects.requireNonNull(Emulation.ScreenOrientation.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("orientation")))));
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
        @Nonnull public String value() { return value; }
        public static DisabledImageType of(@Nonnull String value) {
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
            set("enabled", enabled);
        }
        public static SetFocusEmulationEnabledRequest fromMap(Map<String, Object> values) {
            SetFocusEmulationEnabledRequest instance_ = new SetFocusEmulationEnabledRequest();
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
            SetAutoDarkModeOverrideRequest instance_ = new SetAutoDarkModeOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enabled() {
            return Optional.ofNullable((Boolean) raw("enabled"));
        }
        /**
         * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
         * @param enabled field value; empty omits the value
         * @return this model
         */
        public SetAutoDarkModeOverrideRequest enabled(Optional<Boolean> enabled) {
            set("enabled", enabled.orElse(null));
            return this;
        }
        /**
         * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
         * @param enabled field value; null removes the value
         * @return this model
         */
        public SetAutoDarkModeOverrideRequest enabled(Boolean enabled) {
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
            set("rate", rate);
        }
        public static SetCPUThrottlingRateRequest fromMap(Map<String, Object> values) {
            SetCPUThrottlingRateRequest instance_ = new SetCPUThrottlingRateRequest();
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
            SetDefaultBackgroundColorOverrideRequest instance_ = new SetDefaultBackgroundColorOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * RGBA of the default background color. If not specified, any existing override will be cleared.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> color() {
            return Optional.ofNullable(raw("color") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("color")))));
        }
        /**
         * RGBA of the default background color. If not specified, any existing override will be cleared.
         * @param color field value; empty omits the value
         * @return this model
         */
        public SetDefaultBackgroundColorOverrideRequest color(Optional<DOM.RGBA> color) {
            set("color", color.orElse(null));
            return this;
        }
        /**
         * RGBA of the default background color. If not specified, any existing override will be cleared.
         * @param color field value; null removes the value
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
            set("insets", insets);
        }
        public static SetSafeAreaInsetsOverrideRequest fromMap(Map<String, Object> values) {
            SetSafeAreaInsetsOverrideRequest instance_ = new SetSafeAreaInsetsOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the insets field.
         * @return the protocol field value
         */
        public Emulation.SafeAreaInsets insets() {
            return java.util.Objects.requireNonNull(Emulation.SafeAreaInsets.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("insets")))));
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
            set("width", width);
            set("height", height);
            set("deviceScaleFactor", deviceScaleFactor);
            set("mobile", mobile);
        }
        public static SetDeviceMetricsOverrideRequest fromMap(Map<String, Object> values) {
            SetDeviceMetricsOverrideRequest instance_ = new SetDeviceMetricsOverrideRequest();
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
            Double value = CdpObject.numberAsDouble(raw("scale"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenWidth() {
            Long value = CdpObject.numberAsLong(raw("screenWidth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenHeight() {
            Long value = CdpObject.numberAsLong(raw("screenHeight"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionX() {
            Long value = CdpObject.numberAsLong(raw("positionX"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionY() {
            Long value = CdpObject.numberAsLong(raw("positionY"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> dontSetVisibleSize() {
            return Optional.ofNullable((Boolean) raw("dontSetVisibleSize"));
        }
        /**
         * Screen orientation override.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.ScreenOrientation> screenOrientation() {
            return Optional.ofNullable(raw("screenOrientation") == null ? null : Emulation.ScreenOrientation.fromMap(java.util.Objects.requireNonNull(objectMap(raw("screenOrientation")))));
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.Viewport> viewport() {
            return Optional.ofNullable(raw("viewport") == null ? null : Page.Viewport.fromMap(java.util.Objects.requireNonNull(objectMap(raw("viewport")))));
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Emulation.DisplayFeature> displayFeature() {
            return Optional.ofNullable(raw("displayFeature") == null ? null : Emulation.DisplayFeature.fromMap(java.util.Objects.requireNonNull(objectMap(raw("displayFeature")))));
        }
        /**
         * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Emulation.DevicePosture> devicePosture() {
            return Optional.ofNullable(raw("devicePosture") == null ? null : Emulation.DevicePosture.fromMap(java.util.Objects.requireNonNull(objectMap(raw("devicePosture")))));
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<SetDeviceMetricsOverrideScrollbarTypeValues> scrollbarType() {
            return Optional.ofNullable(raw("scrollbarType") == null ? null : SetDeviceMetricsOverrideScrollbarTypeValues.of((String) raw("scrollbarType")));
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> screenOrientationLockEmulation() {
            return Optional.ofNullable((Boolean) raw("screenOrientationLockEmulation"));
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
         * @param scale field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scale(OptionalDouble scale) {
            set("scale", scale.isPresent() ? scale.getAsDouble() : null);
            return this;
        }
        /**
         * Scale to apply to resulting view image.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scale field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scale(Double scale) {
            set("scale", scale);
            return this;
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenWidth field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenWidth(OptionalLong screenWidth) {
            set("screenWidth", screenWidth.isPresent() ? screenWidth.getAsLong() : null);
            return this;
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenWidth field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenWidth(Long screenWidth) {
            set("screenWidth", screenWidth);
            return this;
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenHeight field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenHeight(OptionalLong screenHeight) {
            set("screenHeight", screenHeight.isPresent() ? screenHeight.getAsLong() : null);
            return this;
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenHeight field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenHeight(Long screenHeight) {
            set("screenHeight", screenHeight);
            return this;
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param positionX field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionX(OptionalLong positionX) {
            set("positionX", positionX.isPresent() ? positionX.getAsLong() : null);
            return this;
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param positionX field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionX(Long positionX) {
            set("positionX", positionX);
            return this;
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param positionY field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionY(OptionalLong positionY) {
            set("positionY", positionY.isPresent() ? positionY.getAsLong() : null);
            return this;
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param positionY field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionY(Long positionY) {
            set("positionY", positionY);
            return this;
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param dontSetVisibleSize field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest dontSetVisibleSize(Optional<Boolean> dontSetVisibleSize) {
            set("dontSetVisibleSize", dontSetVisibleSize.orElse(null));
            return this;
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param dontSetVisibleSize field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest dontSetVisibleSize(Boolean dontSetVisibleSize) {
            set("dontSetVisibleSize", dontSetVisibleSize);
            return this;
        }
        /**
         * Screen orientation override.
         * @param screenOrientation field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenOrientation(Optional<Emulation.ScreenOrientation> screenOrientation) {
            set("screenOrientation", screenOrientation.orElse(null));
            return this;
        }
        /**
         * Screen orientation override.
         * @param screenOrientation field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenOrientation(Emulation.ScreenOrientation screenOrientation) {
            set("screenOrientation", screenOrientation);
            return this;
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param viewport field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest viewport(Optional<Page.Viewport> viewport) {
            set("viewport", viewport.orElse(null));
            return this;
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param viewport field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest viewport(Page.Viewport viewport) {
            set("viewport", viewport);
            return this;
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param displayFeature field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetDeviceMetricsOverrideRequest displayFeature(Optional<Emulation.DisplayFeature> displayFeature) {
            set("displayFeature", displayFeature.orElse(null));
            return this;
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param displayFeature field value; null removes the value
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
         * @param devicePosture field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetDeviceMetricsOverrideRequest devicePosture(Optional<Emulation.DevicePosture> devicePosture) {
            set("devicePosture", devicePosture.orElse(null));
            return this;
        }
        /**
         * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param devicePosture field value; null removes the value
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
         * @param scrollbarType field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scrollbarType(Optional<SetDeviceMetricsOverrideScrollbarTypeValues> scrollbarType) {
            set("scrollbarType", scrollbarType.orElse(null));
            return this;
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scrollbarType field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scrollbarType(SetDeviceMetricsOverrideScrollbarTypeValues scrollbarType) {
            set("scrollbarType", scrollbarType);
            return this;
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenOrientationLockEmulation field value; empty omits the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenOrientationLockEmulation(Optional<Boolean> screenOrientationLockEmulation) {
            set("screenOrientationLockEmulation", screenOrientationLockEmulation.orElse(null));
            return this;
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenOrientationLockEmulation field value; null removes the value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenOrientationLockEmulation(Boolean screenOrientationLockEmulation) {
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
            set("posture", posture);
        }
        public static SetDevicePostureOverrideRequest fromMap(Map<String, Object> values) {
            SetDevicePostureOverrideRequest instance_ = new SetDevicePostureOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the posture field.
         * @return the protocol field value
         */
        public Emulation.DevicePosture posture() {
            return java.util.Objects.requireNonNull(Emulation.DevicePosture.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("posture")))));
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
            set("features", features);
        }
        public static SetDisplayFeaturesOverrideRequest fromMap(Map<String, Object> values) {
            SetDisplayFeaturesOverrideRequest instance_ = new SetDisplayFeaturesOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the features field.
         * @return the protocol field value
         */
        public java.util.List<Emulation.DisplayFeature> features() {
            return CdpObject.requireList(require("features"), element0 -> java.util.Objects.requireNonNull(Emulation.DisplayFeature.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            set("hidden", hidden);
        }
        public static SetScrollbarsHiddenRequest fromMap(Map<String, Object> values) {
            SetScrollbarsHiddenRequest instance_ = new SetScrollbarsHiddenRequest();
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
            set("disabled", disabled);
        }
        public static SetDocumentCookieDisabledRequest fromMap(Map<String, Object> values) {
            SetDocumentCookieDisabledRequest instance_ = new SetDocumentCookieDisabledRequest();
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
            set("enabled", enabled);
        }
        public static SetEmitTouchEventsForMouseRequest fromMap(Map<String, Object> values) {
            SetEmitTouchEventsForMouseRequest instance_ = new SetEmitTouchEventsForMouseRequest();
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
            return Optional.ofNullable(raw("configuration") == null ? null : SetEmitTouchEventsForMouseConfigurationValues.of((String) raw("configuration")));
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
         * @param configuration field value; empty omits the value
         * @return this model
         */
        public SetEmitTouchEventsForMouseRequest configuration(Optional<SetEmitTouchEventsForMouseConfigurationValues> configuration) {
            set("configuration", configuration.orElse(null));
            return this;
        }
        /**
         * Touch/gesture events configuration. Default: current platform.
         * @param configuration field value; null removes the value
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
            SetEmulatedMediaRequest instance_ = new SetEmulatedMediaRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Media type to emulate. Empty string disables the override.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> media() {
            return Optional.ofNullable((String) raw("media"));
        }
        /**
         * Media features to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Emulation.MediaFeature>> features() {
            return Optional.ofNullable(list(raw("features"), element0 -> java.util.Objects.requireNonNull(Emulation.MediaFeature.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Media type to emulate. Empty string disables the override.
         * @param media field value; empty omits the value
         * @return this model
         */
        public SetEmulatedMediaRequest media(Optional<String> media) {
            set("media", media.orElse(null));
            return this;
        }
        /**
         * Media type to emulate. Empty string disables the override.
         * @param media field value; null removes the value
         * @return this model
         */
        public SetEmulatedMediaRequest media(String media) {
            set("media", media);
            return this;
        }
        /**
         * Media features to emulate.
         * @param features field value; empty omits the value
         * @return this model
         */
        public SetEmulatedMediaRequest features(Optional<java.util.List<Emulation.MediaFeature>> features) {
            set("features", features.orElse(null));
            return this;
        }
        /**
         * Media features to emulate.
         * @param features field value; null removes the value
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
            set("type", type);
        }
        public static SetEmulatedVisionDeficiencyRequest fromMap(Map<String, Object> values) {
            SetEmulatedVisionDeficiencyRequest instance_ = new SetEmulatedVisionDeficiencyRequest();
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
            SetEmulatedOSTextScaleRequest instance_ = new SetEmulatedOSTextScaleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scale field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            Double value = CdpObject.numberAsDouble(raw("scale"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Sets the scale field.
         * @param scale field value; empty omits the value
         * @return this model
         */
        public SetEmulatedOSTextScaleRequest scale(OptionalDouble scale) {
            set("scale", scale.isPresent() ? scale.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the scale field.
         * @param scale field value; null removes the value
         * @return this model
         */
        public SetEmulatedOSTextScaleRequest scale(Double scale) {
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
            SetGeolocationOverrideRequest instance_ = new SetGeolocationOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Mock latitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble latitude() {
            Double value = CdpObject.numberAsDouble(raw("latitude"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock longitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble longitude() {
            Double value = CdpObject.numberAsDouble(raw("longitude"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock accuracy
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble accuracy() {
            Double value = CdpObject.numberAsDouble(raw("accuracy"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock altitude
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble altitude() {
            Double value = CdpObject.numberAsDouble(raw("altitude"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock altitudeAccuracy
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble altitudeAccuracy() {
            Double value = CdpObject.numberAsDouble(raw("altitudeAccuracy"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock heading
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble heading() {
            Double value = CdpObject.numberAsDouble(raw("heading"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock speed
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble speed() {
            Double value = CdpObject.numberAsDouble(raw("speed"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Mock latitude
         * @param latitude field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest latitude(OptionalDouble latitude) {
            set("latitude", latitude.isPresent() ? latitude.getAsDouble() : null);
            return this;
        }
        /**
         * Mock latitude
         * @param latitude field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest latitude(Double latitude) {
            set("latitude", latitude);
            return this;
        }
        /**
         * Mock longitude
         * @param longitude field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest longitude(OptionalDouble longitude) {
            set("longitude", longitude.isPresent() ? longitude.getAsDouble() : null);
            return this;
        }
        /**
         * Mock longitude
         * @param longitude field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest longitude(Double longitude) {
            set("longitude", longitude);
            return this;
        }
        /**
         * Mock accuracy
         * @param accuracy field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest accuracy(OptionalDouble accuracy) {
            set("accuracy", accuracy.isPresent() ? accuracy.getAsDouble() : null);
            return this;
        }
        /**
         * Mock accuracy
         * @param accuracy field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest accuracy(Double accuracy) {
            set("accuracy", accuracy);
            return this;
        }
        /**
         * Mock altitude
         * @param altitude field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest altitude(OptionalDouble altitude) {
            set("altitude", altitude.isPresent() ? altitude.getAsDouble() : null);
            return this;
        }
        /**
         * Mock altitude
         * @param altitude field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest altitude(Double altitude) {
            set("altitude", altitude);
            return this;
        }
        /**
         * Mock altitudeAccuracy
         * @param altitudeAccuracy field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest altitudeAccuracy(OptionalDouble altitudeAccuracy) {
            set("altitudeAccuracy", altitudeAccuracy.isPresent() ? altitudeAccuracy.getAsDouble() : null);
            return this;
        }
        /**
         * Mock altitudeAccuracy
         * @param altitudeAccuracy field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest altitudeAccuracy(Double altitudeAccuracy) {
            set("altitudeAccuracy", altitudeAccuracy);
            return this;
        }
        /**
         * Mock heading
         * @param heading field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest heading(OptionalDouble heading) {
            set("heading", heading.isPresent() ? heading.getAsDouble() : null);
            return this;
        }
        /**
         * Mock heading
         * @param heading field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest heading(Double heading) {
            set("heading", heading);
            return this;
        }
        /**
         * Mock speed
         * @param speed field value; empty omits the value
         * @return this model
         */
        public SetGeolocationOverrideRequest speed(OptionalDouble speed) {
            set("speed", speed.isPresent() ? speed.getAsDouble() : null);
            return this;
        }
        /**
         * Mock speed
         * @param speed field value; null removes the value
         * @return this model
         */
        public SetGeolocationOverrideRequest speed(Double speed) {
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
            set("type", type);
        }
        public static GetOverriddenSensorInformationRequest fromMap(Map<String, Object> values) {
            GetOverriddenSensorInformationRequest instance_ = new GetOverriddenSensorInformationRequest();
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
            set("enabled", enabled);
            set("type", type);
        }
        public static SetSensorOverrideEnabledRequest fromMap(Map<String, Object> values) {
            SetSensorOverrideEnabledRequest instance_ = new SetSensorOverrideEnabledRequest();
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
            return Optional.ofNullable(raw("metadata") == null ? null : Emulation.SensorMetadata.fromMap(java.util.Objects.requireNonNull(objectMap(raw("metadata")))));
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
         * @param metadata field value; empty omits the value
         * @return this model
         */
        public SetSensorOverrideEnabledRequest metadata(Optional<Emulation.SensorMetadata> metadata) {
            set("metadata", metadata.orElse(null));
            return this;
        }
        /**
         * Sets the metadata field.
         * @param metadata field value; null removes the value
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
            set("type", type);
            set("reading", reading);
        }
        public static SetSensorOverrideReadingsRequest fromMap(Map<String, Object> values) {
            SetSensorOverrideReadingsRequest instance_ = new SetSensorOverrideReadingsRequest();
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
            return java.util.Objects.requireNonNull(Emulation.SensorReading.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("reading")))));
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
            set("enabled", enabled);
            set("source", source);
        }
        public static SetPressureSourceOverrideEnabledRequest fromMap(Map<String, Object> values) {
            SetPressureSourceOverrideEnabledRequest instance_ = new SetPressureSourceOverrideEnabledRequest();
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
            return Optional.ofNullable(raw("metadata") == null ? null : Emulation.PressureMetadata.fromMap(java.util.Objects.requireNonNull(objectMap(raw("metadata")))));
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
         * @param metadata field value; empty omits the value
         * @return this model
         */
        public SetPressureSourceOverrideEnabledRequest metadata(Optional<Emulation.PressureMetadata> metadata) {
            set("metadata", metadata.orElse(null));
            return this;
        }
        /**
         * Sets the metadata field.
         * @param metadata field value; null removes the value
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
            set("source", source);
            set("state", state);
        }
        public static SetPressureStateOverrideRequest fromMap(Map<String, Object> values) {
            SetPressureStateOverrideRequest instance_ = new SetPressureStateOverrideRequest();
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
            set("isUserActive", isUserActive);
            set("isScreenUnlocked", isScreenUnlocked);
        }
        public static SetIdleOverrideRequest fromMap(Map<String, Object> values) {
            SetIdleOverrideRequest instance_ = new SetIdleOverrideRequest();
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
            set("platform", platform);
        }
        public static SetNavigatorOverridesRequest fromMap(Map<String, Object> values) {
            SetNavigatorOverridesRequest instance_ = new SetNavigatorOverridesRequest();
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
            set("pageScaleFactor", pageScaleFactor);
        }
        public static SetPageScaleFactorRequest fromMap(Map<String, Object> values) {
            SetPageScaleFactorRequest instance_ = new SetPageScaleFactorRequest();
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
            set("value", value);
        }
        public static SetScriptExecutionDisabledRequest fromMap(Map<String, Object> values) {
            SetScriptExecutionDisabledRequest instance_ = new SetScriptExecutionDisabledRequest();
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
            set("enabled", enabled);
        }
        public static SetTouchEmulationEnabledRequest fromMap(Map<String, Object> values) {
            SetTouchEmulationEnabledRequest instance_ = new SetTouchEmulationEnabledRequest();
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
            Long value = CdpObject.numberAsLong(raw("maxTouchPoints"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
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
         * @param maxTouchPoints field value; empty omits the value
         * @return this model
         */
        public SetTouchEmulationEnabledRequest maxTouchPoints(OptionalLong maxTouchPoints) {
            set("maxTouchPoints", maxTouchPoints.isPresent() ? maxTouchPoints.getAsLong() : null);
            return this;
        }
        /**
         * Maximum touch points supported. Defaults to one.
         * @param maxTouchPoints field value; null removes the value
         * @return this model
         */
        public SetTouchEmulationEnabledRequest maxTouchPoints(Long maxTouchPoints) {
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
            set("policy", policy);
        }
        public static SetVirtualTimePolicyRequest fromMap(Map<String, Object> values) {
            SetVirtualTimePolicyRequest instance_ = new SetVirtualTimePolicyRequest();
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
            Double value = CdpObject.numberAsDouble(raw("budget"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxVirtualTimeTaskStarvationCount() {
            Long value = CdpObject.numberAsLong(raw("maxVirtualTimeTaskStarvationCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> initialVirtualTime() {
            return Optional.ofNullable(raw("initialVirtualTime") == null ? null : new Network.TimeSinceEpoch(((Number) raw("initialVirtualTime")).doubleValue()));
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
         * @param budget field value; empty omits the value
         * @return this model
         */
        public SetVirtualTimePolicyRequest budget(OptionalDouble budget) {
            set("budget", budget.isPresent() ? budget.getAsDouble() : null);
            return this;
        }
        /**
         * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
         * @param budget field value; null removes the value
         * @return this model
         */
        public SetVirtualTimePolicyRequest budget(Double budget) {
            set("budget", budget);
            return this;
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @param maxVirtualTimeTaskStarvationCount field value; empty omits the value
         * @return this model
         */
        public SetVirtualTimePolicyRequest maxVirtualTimeTaskStarvationCount(OptionalLong maxVirtualTimeTaskStarvationCount) {
            set("maxVirtualTimeTaskStarvationCount", maxVirtualTimeTaskStarvationCount.isPresent() ? maxVirtualTimeTaskStarvationCount.getAsLong() : null);
            return this;
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @param maxVirtualTimeTaskStarvationCount field value; null removes the value
         * @return this model
         */
        public SetVirtualTimePolicyRequest maxVirtualTimeTaskStarvationCount(Long maxVirtualTimeTaskStarvationCount) {
            set("maxVirtualTimeTaskStarvationCount", maxVirtualTimeTaskStarvationCount);
            return this;
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @param initialVirtualTime field value; empty omits the value
         * @return this model
         */
        public SetVirtualTimePolicyRequest initialVirtualTime(Optional<Network.TimeSinceEpoch> initialVirtualTime) {
            set("initialVirtualTime", initialVirtualTime.orElse(null));
            return this;
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @param initialVirtualTime field value; null removes the value
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
            SetLocaleOverrideRequest instance_ = new SetLocaleOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> locale() {
            return Optional.ofNullable((String) raw("locale"));
        }
        /**
         * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
         * @param locale field value; empty omits the value
         * @return this model
         */
        public SetLocaleOverrideRequest locale(Optional<String> locale) {
            set("locale", locale.orElse(null));
            return this;
        }
        /**
         * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
         * @param locale field value; null removes the value
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
            set("timezoneId", timezoneId);
        }
        public static SetTimezoneOverrideRequest fromMap(Map<String, Object> values) {
            SetTimezoneOverrideRequest instance_ = new SetTimezoneOverrideRequest();
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
            set("width", width);
            set("height", height);
        }
        public static SetVisibleSizeRequest fromMap(Map<String, Object> values) {
            SetVisibleSizeRequest instance_ = new SetVisibleSizeRequest();
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
            set("imageTypes", imageTypes);
        }
        public static SetDisabledImageTypesRequest fromMap(Map<String, Object> values) {
            SetDisabledImageTypesRequest instance_ = new SetDisabledImageTypesRequest();
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
            SetDataSaverOverrideRequest instance_ = new SetDataSaverOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Override value. Omitting the parameter disables the override.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> dataSaverEnabled() {
            return Optional.ofNullable((Boolean) raw("dataSaverEnabled"));
        }
        /**
         * Override value. Omitting the parameter disables the override.
         * @param dataSaverEnabled field value; empty omits the value
         * @return this model
         */
        public SetDataSaverOverrideRequest dataSaverEnabled(Optional<Boolean> dataSaverEnabled) {
            set("dataSaverEnabled", dataSaverEnabled.orElse(null));
            return this;
        }
        /**
         * Override value. Omitting the parameter disables the override.
         * @param dataSaverEnabled field value; null removes the value
         * @return this model
         */
        public SetDataSaverOverrideRequest dataSaverEnabled(Boolean dataSaverEnabled) {
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
            set("hardwareConcurrency", hardwareConcurrency);
        }
        public static SetHardwareConcurrencyOverrideRequest fromMap(Map<String, Object> values) {
            SetHardwareConcurrencyOverrideRequest instance_ = new SetHardwareConcurrencyOverrideRequest();
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
            set("userAgent", userAgent);
        }
        public static SetUserAgentOverrideRequest fromMap(Map<String, Object> values) {
            SetUserAgentOverrideRequest instance_ = new SetUserAgentOverrideRequest();
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
            return Optional.ofNullable((String) raw("acceptLanguage"));
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> platform() {
            return Optional.ofNullable((String) raw("platform"));
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.UserAgentMetadata> userAgentMetadata() {
            return Optional.ofNullable(raw("userAgentMetadata") == null ? null : Emulation.UserAgentMetadata.fromMap(java.util.Objects.requireNonNull(objectMap(raw("userAgentMetadata")))));
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
         * @param acceptLanguage field value; empty omits the value
         * @return this model
         */
        public SetUserAgentOverrideRequest acceptLanguage(Optional<String> acceptLanguage) {
            set("acceptLanguage", acceptLanguage.orElse(null));
            return this;
        }
        /**
         * Browser language to emulate.
         * @param acceptLanguage field value; null removes the value
         * @return this model
         */
        public SetUserAgentOverrideRequest acceptLanguage(String acceptLanguage) {
            set("acceptLanguage", acceptLanguage);
            return this;
        }
        /**
         * The platform navigator.platform should return.
         * @param platform field value; empty omits the value
         * @return this model
         */
        public SetUserAgentOverrideRequest platform(Optional<String> platform) {
            set("platform", platform.orElse(null));
            return this;
        }
        /**
         * The platform navigator.platform should return.
         * @param platform field value; null removes the value
         * @return this model
         */
        public SetUserAgentOverrideRequest platform(String platform) {
            set("platform", platform);
            return this;
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param userAgentMetadata field value; empty omits the value
         * @return this model
         */
        public SetUserAgentOverrideRequest userAgentMetadata(Optional<Emulation.UserAgentMetadata> userAgentMetadata) {
            set("userAgentMetadata", userAgentMetadata.orElse(null));
            return this;
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param userAgentMetadata field value; null removes the value
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
            set("enabled", enabled);
        }
        public static SetAutomationOverrideRequest fromMap(Map<String, Object> values) {
            SetAutomationOverrideRequest instance_ = new SetAutomationOverrideRequest();
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
            set("difference", difference);
        }
        public static SetSmallViewportHeightDifferenceOverrideRequest fromMap(Map<String, Object> values) {
            SetSmallViewportHeightDifferenceOverrideRequest instance_ = new SetSmallViewportHeightDifferenceOverrideRequest();
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
            set("left", left);
            set("top", top);
            set("width", width);
            set("height", height);
        }
        public static AddScreenRequest fromMap(Map<String, Object> values) {
            AddScreenRequest instance_ = new AddScreenRequest();
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
            return Optional.ofNullable(raw("workAreaInsets") == null ? null : Emulation.WorkAreaInsets.fromMap(java.util.Objects.requireNonNull(objectMap(raw("workAreaInsets")))));
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble devicePixelRatio() {
            Double value = CdpObject.numberAsDouble(raw("devicePixelRatio"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rotation() {
            Long value = CdpObject.numberAsLong(raw("rotation"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong colorDepth() {
            Long value = CdpObject.numberAsLong(raw("colorDepth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return Optional.ofNullable((String) raw("label"));
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isInternal() {
            return Optional.ofNullable((Boolean) raw("isInternal"));
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
         * @param workAreaInsets field value; empty omits the value
         * @return this model
         */
        public AddScreenRequest workAreaInsets(Optional<Emulation.WorkAreaInsets> workAreaInsets) {
            set("workAreaInsets", workAreaInsets.orElse(null));
            return this;
        }
        /**
         * Specifies the screen&#x27;s work area. Default is entire screen.
         * @param workAreaInsets field value; null removes the value
         * @return this model
         */
        public AddScreenRequest workAreaInsets(Emulation.WorkAreaInsets workAreaInsets) {
            set("workAreaInsets", workAreaInsets);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @param devicePixelRatio field value; empty omits the value
         * @return this model
         */
        public AddScreenRequest devicePixelRatio(OptionalDouble devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio.isPresent() ? devicePixelRatio.getAsDouble() : null);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @param devicePixelRatio field value; null removes the value
         * @return this model
         */
        public AddScreenRequest devicePixelRatio(Double devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio);
            return this;
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @param rotation field value; empty omits the value
         * @return this model
         */
        public AddScreenRequest rotation(OptionalLong rotation) {
            set("rotation", rotation.isPresent() ? rotation.getAsLong() : null);
            return this;
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @param rotation field value; null removes the value
         * @return this model
         */
        public AddScreenRequest rotation(Long rotation) {
            set("rotation", rotation);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @param colorDepth field value; empty omits the value
         * @return this model
         */
        public AddScreenRequest colorDepth(OptionalLong colorDepth) {
            set("colorDepth", colorDepth.isPresent() ? colorDepth.getAsLong() : null);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @param colorDepth field value; null removes the value
         * @return this model
         */
        public AddScreenRequest colorDepth(Long colorDepth) {
            set("colorDepth", colorDepth);
            return this;
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @param label field value; empty omits the value
         * @return this model
         */
        public AddScreenRequest label(Optional<String> label) {
            set("label", label.orElse(null));
            return this;
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @param label field value; null removes the value
         * @return this model
         */
        public AddScreenRequest label(String label) {
            set("label", label);
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @param isInternal field value; empty omits the value
         * @return this model
         */
        public AddScreenRequest isInternal(Optional<Boolean> isInternal) {
            set("isInternal", isInternal.orElse(null));
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @param isInternal field value; null removes the value
         * @return this model
         */
        public AddScreenRequest isInternal(Boolean isInternal) {
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
            set("screenId", screenId);
        }
        public static UpdateScreenRequest fromMap(Map<String, Object> values) {
            UpdateScreenRequest instance_ = new UpdateScreenRequest();
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
            Long value = CdpObject.numberAsLong(raw("left"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            Long value = CdpObject.numberAsLong(raw("top"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            Long value = CdpObject.numberAsLong(raw("width"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            Long value = CdpObject.numberAsLong(raw("height"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.WorkAreaInsets> workAreaInsets() {
            return Optional.ofNullable(raw("workAreaInsets") == null ? null : Emulation.WorkAreaInsets.fromMap(java.util.Objects.requireNonNull(objectMap(raw("workAreaInsets")))));
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble devicePixelRatio() {
            Double value = CdpObject.numberAsDouble(raw("devicePixelRatio"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong rotation() {
            Long value = CdpObject.numberAsLong(raw("rotation"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong colorDepth() {
            Long value = CdpObject.numberAsLong(raw("colorDepth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return Optional.ofNullable((String) raw("label"));
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isInternal() {
            return Optional.ofNullable((Boolean) raw("isInternal"));
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
         * @param left field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest left(OptionalLong left) {
            set("left", left.isPresent() ? left.getAsLong() : null);
            return this;
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @param left field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest left(Long left) {
            set("left", left);
            return this;
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @param top field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest top(OptionalLong top) {
            set("top", top.isPresent() ? top.getAsLong() : null);
            return this;
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @param top field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest top(Long top) {
            set("top", top);
            return this;
        }
        /**
         * The width of the screen in pixels.
         * @param width field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest width(OptionalLong width) {
            set("width", width.isPresent() ? width.getAsLong() : null);
            return this;
        }
        /**
         * The width of the screen in pixels.
         * @param width field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest width(Long width) {
            set("width", width);
            return this;
        }
        /**
         * The height of the screen in pixels.
         * @param height field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest height(OptionalLong height) {
            set("height", height.isPresent() ? height.getAsLong() : null);
            return this;
        }
        /**
         * The height of the screen in pixels.
         * @param height field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest height(Long height) {
            set("height", height);
            return this;
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @param workAreaInsets field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest workAreaInsets(Optional<Emulation.WorkAreaInsets> workAreaInsets) {
            set("workAreaInsets", workAreaInsets.orElse(null));
            return this;
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @param workAreaInsets field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest workAreaInsets(Emulation.WorkAreaInsets workAreaInsets) {
            set("workAreaInsets", workAreaInsets);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @param devicePixelRatio field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest devicePixelRatio(OptionalDouble devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio.isPresent() ? devicePixelRatio.getAsDouble() : null);
            return this;
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @param devicePixelRatio field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest devicePixelRatio(Double devicePixelRatio) {
            set("devicePixelRatio", devicePixelRatio);
            return this;
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @param rotation field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest rotation(OptionalLong rotation) {
            set("rotation", rotation.isPresent() ? rotation.getAsLong() : null);
            return this;
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @param rotation field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest rotation(Long rotation) {
            set("rotation", rotation);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @param colorDepth field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest colorDepth(OptionalLong colorDepth) {
            set("colorDepth", colorDepth.isPresent() ? colorDepth.getAsLong() : null);
            return this;
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @param colorDepth field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest colorDepth(Long colorDepth) {
            set("colorDepth", colorDepth);
            return this;
        }
        /**
         * Specifies the descriptive label for the screen.
         * @param label field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest label(Optional<String> label) {
            set("label", label.orElse(null));
            return this;
        }
        /**
         * Specifies the descriptive label for the screen.
         * @param label field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest label(String label) {
            set("label", label);
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @param isInternal field value; empty omits the value
         * @return this model
         */
        public UpdateScreenRequest isInternal(Optional<Boolean> isInternal) {
            set("isInternal", isInternal.orElse(null));
            return this;
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @param isInternal field value; null removes the value
         * @return this model
         */
        public UpdateScreenRequest isInternal(Boolean isInternal) {
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
            set("screenId", screenId);
        }
        public static RemoveScreenRequest fromMap(Map<String, Object> values) {
            RemoveScreenRequest instance_ = new RemoveScreenRequest();
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
            set("screenId", screenId);
        }
        public static SetPrimaryScreenRequest fromMap(Map<String, Object> values) {
            SetPrimaryScreenRequest instance_ = new SetPrimaryScreenRequest();
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
            return Optional.ofNullable(raw("orientation") == null ? null : Emulation.ScreenOrientation.fromMap(java.util.Objects.requireNonNull(objectMap(raw("orientation")))));
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
         * @param orientation field value; empty omits the value
         * @return this model
         */
        public ScreenOrientationLockChangedEvent orientation(Optional<Emulation.ScreenOrientation> orientation) {
            set("orientation", orientation.orElse(null));
            return this;
        }
        /**
         * The orientation lock type requested by the page. Only set when locked is true.
         * @param orientation field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static SetDeviceMetricsOverrideScrollbarTypeValues of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static SetEmitTouchEventsForMouseConfigurationValues of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static SetEmulatedVisionDeficiencyTypeValues of(@Nonnull String value) {
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
            return client.call("Emulation.clearDeviceMetricsOverride", null, result_ -> null);
        }
        /**
         * Clears the overridden Geolocation Position and Error.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearGeolocationOverride() {
            return client.call("Emulation.clearGeolocationOverride", null, result_ -> null);
        }
        /**
         * Requests that page scale factor is reset to initial values.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetPageScaleFactor() {
            return client.call("Emulation.resetPageScaleFactor", null, result_ -> null);
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFocusEmulationEnabled(boolean enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setFocusEmulationEnabled", params, result_ -> null);
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFocusEmulationEnabled(SetFocusEmulationEnabledRequest request) {
            return client.call("Emulation.setFocusEmulationEnabled", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride(Optional<Boolean> enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            enabled.ifPresent(value_ -> params.put("enabled", value_));
            return client.call("Emulation.setAutoDarkModeOverride", params, result_ -> null);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride() {
            return setAutoDarkModeOverride(Optional.empty());
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoDarkModeOverride(SetAutoDarkModeOverrideRequest request) {
            return client.call("Emulation.setAutoDarkModeOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param rate protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCPUThrottlingRate(double rate) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("rate", CdpObject.json(rate));
            return client.call("Emulation.setCPUThrottlingRate", params, result_ -> null);
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCPUThrottlingRate(SetCPUThrottlingRateRequest request) {
            return client.call("Emulation.setCPUThrottlingRate", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param color protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride(Optional<DOM.RGBA> color) {
            Map<String, Object> params = new LinkedHashMap<>();
            color.ifPresent(value_ -> params.put("color", CdpObject.json(value_)));
            return client.call("Emulation.setDefaultBackgroundColorOverride", params, result_ -> null);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride() {
            return setDefaultBackgroundColorOverride(Optional.empty());
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDefaultBackgroundColorOverride(SetDefaultBackgroundColorOverrideRequest request) {
            return client.call("Emulation.setDefaultBackgroundColorOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param insets protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSafeAreaInsetsOverride(Emulation.SafeAreaInsets insets) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("insets", CdpObject.json(insets));
            return client.call("Emulation.setSafeAreaInsetsOverride", params, result_ -> null);
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSafeAreaInsetsOverride(SetSafeAreaInsetsOverrideRequest request) {
            return client.call("Emulation.setSafeAreaInsetsOverride", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Void> setDeviceMetricsOverride(long width, long height, double deviceScaleFactor, boolean mobile, OptionalDouble scale, OptionalLong screenWidth, OptionalLong screenHeight, OptionalLong positionX, OptionalLong positionY, Optional<Boolean> dontSetVisibleSize, Optional<Emulation.ScreenOrientation> screenOrientation, Optional<Page.Viewport> viewport, Optional<Emulation.DisplayFeature> displayFeature, Optional<Emulation.DevicePosture> devicePosture, Optional<SetDeviceMetricsOverrideScrollbarTypeValues> scrollbarType, Optional<Boolean> screenOrientationLockEmulation) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            params.put("deviceScaleFactor", CdpObject.json(deviceScaleFactor));
            params.put("mobile", CdpObject.json(mobile));
            scale.ifPresent(value_ -> params.put("scale", value_));
            screenWidth.ifPresent(value_ -> params.put("screenWidth", value_));
            screenHeight.ifPresent(value_ -> params.put("screenHeight", value_));
            positionX.ifPresent(value_ -> params.put("positionX", value_));
            positionY.ifPresent(value_ -> params.put("positionY", value_));
            dontSetVisibleSize.ifPresent(value_ -> params.put("dontSetVisibleSize", value_));
            screenOrientation.ifPresent(value_ -> params.put("screenOrientation", CdpObject.json(value_)));
            viewport.ifPresent(value_ -> params.put("viewport", CdpObject.json(value_)));
            displayFeature.ifPresent(value_ -> params.put("displayFeature", CdpObject.json(value_)));
            devicePosture.ifPresent(value_ -> params.put("devicePosture", CdpObject.json(value_)));
            scrollbarType.ifPresent(value_ -> params.put("scrollbarType", CdpObject.json(value_)));
            screenOrientationLockEmulation.ifPresent(value_ -> params.put("screenOrientationLockEmulation", value_));
            return client.call("Emulation.setDeviceMetricsOverride", params, result_ -> null);
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
            return setDeviceMetricsOverride(width, height, deviceScaleFactor, mobile, OptionalDouble.empty(), OptionalLong.empty(), OptionalLong.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceMetricsOverride(SetDeviceMetricsOverrideRequest request) {
            return client.call("Emulation.setDeviceMetricsOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param posture protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDevicePostureOverride(Emulation.DevicePosture posture) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("posture", CdpObject.json(posture));
            return client.call("Emulation.setDevicePostureOverride", params, result_ -> null);
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDevicePostureOverride(SetDevicePostureOverrideRequest request) {
            return client.call("Emulation.setDevicePostureOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears a device posture override set with either setDeviceMetricsOverride() or setDevicePostureOverride() and starts using posture information from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDevicePostureOverride() {
            return client.call("Emulation.clearDevicePostureOverride", null, result_ -> null);
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param features protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisplayFeaturesOverride(java.util.List<Emulation.DisplayFeature> features) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("features", CdpObject.json(features));
            return client.call("Emulation.setDisplayFeaturesOverride", params, result_ -> null);
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisplayFeaturesOverride(SetDisplayFeaturesOverrideRequest request) {
            return client.call("Emulation.setDisplayFeaturesOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears the display features override set with either setDeviceMetricsOverride() or setDisplayFeaturesOverride() and starts using display features from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDisplayFeaturesOverride() {
            return client.call("Emulation.clearDisplayFeaturesOverride", null, result_ -> null);
        }
        /**
         * Invokes Emulation.setScrollbarsHidden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hidden protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScrollbarsHidden(boolean hidden) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("hidden", CdpObject.json(hidden));
            return client.call("Emulation.setScrollbarsHidden", params, result_ -> null);
        }
        /**
         * Invokes Emulation.setScrollbarsHidden with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScrollbarsHidden(SetScrollbarsHiddenRequest request) {
            return client.call("Emulation.setScrollbarsHidden", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param disabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentCookieDisabled(boolean disabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("disabled", CdpObject.json(disabled));
            return client.call("Emulation.setDocumentCookieDisabled", params, result_ -> null);
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentCookieDisabled(SetDocumentCookieDisabledRequest request) {
            return client.call("Emulation.setDocumentCookieDisabled", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param configuration protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(boolean enabled, Optional<SetEmitTouchEventsForMouseConfigurationValues> configuration) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            configuration.ifPresent(value_ -> params.put("configuration", CdpObject.json(value_)));
            return client.call("Emulation.setEmitTouchEventsForMouse", params, result_ -> null);
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse with the required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(boolean enabled) {
            return setEmitTouchEventsForMouse(enabled, Optional.empty());
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmitTouchEventsForMouse(SetEmitTouchEventsForMouseRequest request) {
            return client.call("Emulation.setEmitTouchEventsForMouse", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param media protocol value
         * @param features protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia(Optional<String> media, Optional<java.util.List<Emulation.MediaFeature>> features) {
            Map<String, Object> params = new LinkedHashMap<>();
            media.ifPresent(value_ -> params.put("media", CdpObject.json(value_)));
            features.ifPresent(value_ -> params.put("features", CdpObject.json(value_)));
            return client.call("Emulation.setEmulatedMedia", params, result_ -> null);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia() {
            return setEmulatedMedia(Optional.empty(), Optional.empty());
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedMedia(SetEmulatedMediaRequest request) {
            return client.call("Emulation.setEmulatedMedia", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Emulates the given vision deficiency.
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyTypeValues type) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            return client.call("Emulation.setEmulatedVisionDeficiency", params, result_ -> null);
        }
        /**
         * Emulates the given vision deficiency.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyRequest request) {
            return client.call("Emulation.setEmulatedVisionDeficiency", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Emulates the given OS text scale.
         * @param scale protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale(OptionalDouble scale) {
            Map<String, Object> params = new LinkedHashMap<>();
            scale.ifPresent(value_ -> params.put("scale", value_));
            return client.call("Emulation.setEmulatedOSTextScale", params, result_ -> null);
        }
        /**
         * Emulates the given OS text scale.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale() {
            return setEmulatedOSTextScale(OptionalDouble.empty());
        }
        /**
         * Emulates the given OS text scale.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEmulatedOSTextScale(SetEmulatedOSTextScaleRequest request) {
            return client.call("Emulation.setEmulatedOSTextScale", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Void> setGeolocationOverride(OptionalDouble latitude, OptionalDouble longitude, OptionalDouble accuracy, OptionalDouble altitude, OptionalDouble altitudeAccuracy, OptionalDouble heading, OptionalDouble speed) {
            Map<String, Object> params = new LinkedHashMap<>();
            latitude.ifPresent(value_ -> params.put("latitude", value_));
            longitude.ifPresent(value_ -> params.put("longitude", value_));
            accuracy.ifPresent(value_ -> params.put("accuracy", value_));
            altitude.ifPresent(value_ -> params.put("altitude", value_));
            altitudeAccuracy.ifPresent(value_ -> params.put("altitudeAccuracy", value_));
            heading.ifPresent(value_ -> params.put("heading", value_));
            speed.ifPresent(value_ -> params.put("speed", value_));
            return client.call("Emulation.setGeolocationOverride", params, result_ -> null);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride() {
            return setGeolocationOverride(OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty());
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setGeolocationOverride(SetGeolocationOverrideRequest request) {
            return client.call("Emulation.setGeolocationOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Emulation.getOverriddenSensorInformation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getOverriddenSensorInformation(Emulation.SensorType type) {
            Map<String, Object> params = new LinkedHashMap<>();
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
        public CompletionStage<Void> setSensorOverrideEnabled(boolean enabled, Emulation.SensorType type, Optional<Emulation.SensorMetadata> metadata) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("type", CdpObject.json(type));
            metadata.ifPresent(value_ -> params.put("metadata", CdpObject.json(value_)));
            return client.call("Emulation.setSensorOverrideEnabled", params, result_ -> null);
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(boolean enabled, Emulation.SensorType type) {
            return setSensorOverrideEnabled(enabled, type, Optional.empty());
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideEnabled(SetSensorOverrideEnabledRequest request) {
            return client.call("Emulation.setSensorOverrideEnabled", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param reading protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideReadings(Emulation.SensorType type, Emulation.SensorReading reading) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            params.put("reading", CdpObject.json(reading));
            return client.call("Emulation.setSensorOverrideReadings", params, result_ -> null);
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSensorOverrideReadings(SetSensorOverrideReadingsRequest request) {
            return client.call("Emulation.setSensorOverrideReadings", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param source protocol value
         * @param metadata protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(boolean enabled, Emulation.PressureSource source, Optional<Emulation.PressureMetadata> metadata) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("source", CdpObject.json(source));
            metadata.ifPresent(value_ -> params.put("metadata", CdpObject.json(value_)));
            return client.call("Emulation.setPressureSourceOverrideEnabled", params, result_ -> null);
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param source protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(boolean enabled, Emulation.PressureSource source) {
            return setPressureSourceOverrideEnabled(enabled, source, Optional.empty());
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureSourceOverrideEnabled(SetPressureSourceOverrideEnabledRequest request) {
            return client.call("Emulation.setPressureSourceOverrideEnabled", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param source protocol value
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureStateOverride(Emulation.PressureSource source, Emulation.PressureState state) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("source", CdpObject.json(source));
            params.put("state", CdpObject.json(state));
            return client.call("Emulation.setPressureStateOverride", params, result_ -> null);
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureStateOverride(SetPressureStateOverrideRequest request) {
            return client.call("Emulation.setPressureStateOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Overrides the Idle state.
         * @param isUserActive protocol value
         * @param isScreenUnlocked protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIdleOverride(boolean isUserActive, boolean isScreenUnlocked) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("isUserActive", CdpObject.json(isUserActive));
            params.put("isScreenUnlocked", CdpObject.json(isScreenUnlocked));
            return client.call("Emulation.setIdleOverride", params, result_ -> null);
        }
        /**
         * Overrides the Idle state.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIdleOverride(SetIdleOverrideRequest request) {
            return client.call("Emulation.setIdleOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears Idle state overrides.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearIdleOverride() {
            return client.call("Emulation.clearIdleOverride", null, result_ -> null);
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("platform", CdpObject.json(platform));
            return client.call("Emulation.setNavigatorOverrides", params, result_ -> null);
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
            return client.call("Emulation.setNavigatorOverrides", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param pageScaleFactor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPageScaleFactor(double pageScaleFactor) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("pageScaleFactor", CdpObject.json(pageScaleFactor));
            return client.call("Emulation.setPageScaleFactor", params, result_ -> null);
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPageScaleFactor(SetPageScaleFactorRequest request) {
            return client.call("Emulation.setPageScaleFactor", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Switches script execution in the page.
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScriptExecutionDisabled(boolean value) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("value", CdpObject.json(value));
            return client.call("Emulation.setScriptExecutionDisabled", params, result_ -> null);
        }
        /**
         * Switches script execution in the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setScriptExecutionDisabled(SetScriptExecutionDisabledRequest request) {
            return client.call("Emulation.setScriptExecutionDisabled", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         * @param maxTouchPoints protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled, OptionalLong maxTouchPoints) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            maxTouchPoints.ifPresent(value_ -> params.put("maxTouchPoints", value_));
            return client.call("Emulation.setTouchEmulationEnabled", params, result_ -> null);
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled) {
            return setTouchEmulationEnabled(enabled, OptionalLong.empty());
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTouchEmulationEnabled(SetTouchEmulationEnabledRequest request) {
            return client.call("Emulation.setTouchEmulationEnabled", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Double> setVirtualTimePolicy(Emulation.VirtualTimePolicy policy, OptionalDouble budget, OptionalLong maxVirtualTimeTaskStarvationCount, Optional<Network.TimeSinceEpoch> initialVirtualTime) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("policy", CdpObject.json(policy));
            budget.ifPresent(value_ -> params.put("budget", value_));
            maxVirtualTimeTaskStarvationCount.ifPresent(value_ -> params.put("maxVirtualTimeTaskStarvationCount", value_));
            initialVirtualTime.ifPresent(value_ -> params.put("initialVirtualTime", CdpObject.json(value_)));
            return client.call("Emulation.setVirtualTimePolicy", params, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("virtualTimeTicksBase"))).doubleValue());
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param policy protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> setVirtualTimePolicy(Emulation.VirtualTimePolicy policy) {
            return setVirtualTimePolicy(policy, OptionalDouble.empty(), OptionalLong.empty(), Optional.empty());
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
        public CompletionStage<Void> setLocaleOverride(Optional<String> locale) {
            Map<String, Object> params = new LinkedHashMap<>();
            locale.ifPresent(value_ -> params.put("locale", CdpObject.json(value_)));
            return client.call("Emulation.setLocaleOverride", params, result_ -> null);
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride() {
            return setLocaleOverride(Optional.empty());
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocaleOverride(SetLocaleOverrideRequest request) {
            return client.call("Emulation.setLocaleOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param timezoneId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTimezoneOverride(String timezoneId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("timezoneId", CdpObject.json(timezoneId));
            return client.call("Emulation.setTimezoneOverride", params, result_ -> null);
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTimezoneOverride(SetTimezoneOverrideRequest request) {
            return client.call("Emulation.setTimezoneOverride", request == null ? null : request.toMap(), result_ -> null);
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            return client.call("Emulation.setVisibleSize", params, result_ -> null);
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
            return client.call("Emulation.setVisibleSize", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Emulation.setDisabledImageTypes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param imageTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisabledImageTypes(java.util.List<Emulation.DisabledImageType> imageTypes) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("imageTypes", CdpObject.json(imageTypes));
            return client.call("Emulation.setDisabledImageTypes", params, result_ -> null);
        }
        /**
         * Invokes Emulation.setDisabledImageTypes with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDisabledImageTypes(SetDisabledImageTypesRequest request) {
            return client.call("Emulation.setDisabledImageTypes", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param dataSaverEnabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride(Optional<Boolean> dataSaverEnabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            dataSaverEnabled.ifPresent(value_ -> params.put("dataSaverEnabled", value_));
            return client.call("Emulation.setDataSaverOverride", params, result_ -> null);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride() {
            return setDataSaverOverride(Optional.empty());
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDataSaverOverride(SetDataSaverOverrideRequest request) {
            return client.call("Emulation.setDataSaverOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hardwareConcurrency protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setHardwareConcurrencyOverride(long hardwareConcurrency) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("hardwareConcurrency", CdpObject.json(hardwareConcurrency));
            return client.call("Emulation.setHardwareConcurrencyOverride", params, result_ -> null);
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setHardwareConcurrencyOverride(SetHardwareConcurrencyOverrideRequest request) {
            return client.call("Emulation.setHardwareConcurrencyOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param userAgent protocol value
         * @param acceptLanguage protocol value
         * @param platform protocol value
         * @param userAgentMetadata protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent, Optional<String> acceptLanguage, Optional<String> platform, Optional<Emulation.UserAgentMetadata> userAgentMetadata) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("userAgent", CdpObject.json(userAgent));
            acceptLanguage.ifPresent(value_ -> params.put("acceptLanguage", CdpObject.json(value_)));
            platform.ifPresent(value_ -> params.put("platform", CdpObject.json(value_)));
            userAgentMetadata.ifPresent(value_ -> params.put("userAgentMetadata", CdpObject.json(value_)));
            return client.call("Emulation.setUserAgentOverride", params, result_ -> null);
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param userAgent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent) {
            return setUserAgentOverride(userAgent, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(SetUserAgentOverrideRequest request) {
            return client.call("Emulation.setUserAgentOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomationOverride(boolean enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Emulation.setAutomationOverride", params, result_ -> null);
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomationOverride(SetAutomationOverrideRequest request) {
            return client.call("Emulation.setAutomationOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param difference protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSmallViewportHeightDifferenceOverride(long difference) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("difference", CdpObject.json(difference));
            return client.call("Emulation.setSmallViewportHeightDifferenceOverride", params, result_ -> null);
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSmallViewportHeightDifferenceOverride(SetSmallViewportHeightDifferenceOverrideRequest request) {
            return client.call("Emulation.setSmallViewportHeightDifferenceOverride", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Returns device&#x27;s screen configuration. In headful mode, the physical screens configuration is returned, whereas in headless mode, a virtual headless screen configuration is provided instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Emulation.ScreenInfo>> getScreenInfos() {
            return client.call("Emulation.getScreenInfos", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("screenInfos")), element0 -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
        public CompletionStage<Emulation.ScreenInfo> addScreen(long left, long top, long width, long height, Optional<Emulation.WorkAreaInsets> workAreaInsets, OptionalDouble devicePixelRatio, OptionalLong rotation, OptionalLong colorDepth, Optional<String> label, Optional<Boolean> isInternal) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("left", CdpObject.json(left));
            params.put("top", CdpObject.json(top));
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            workAreaInsets.ifPresent(value_ -> params.put("workAreaInsets", CdpObject.json(value_)));
            devicePixelRatio.ifPresent(value_ -> params.put("devicePixelRatio", value_));
            rotation.ifPresent(value_ -> params.put("rotation", value_));
            colorDepth.ifPresent(value_ -> params.put("colorDepth", value_));
            label.ifPresent(value_ -> params.put("label", CdpObject.json(value_)));
            isInternal.ifPresent(value_ -> params.put("isInternal", value_));
            return client.call("Emulation.addScreen", params, result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("screenInfo")))))));
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
            return addScreen(left, top, width, height, Optional.empty(), OptionalDouble.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> addScreen(AddScreenRequest request) {
            return client.call("Emulation.addScreen", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("screenInfo")))))));
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
        public CompletionStage<Emulation.ScreenInfo> updateScreen(Emulation.ScreenId screenId, OptionalLong left, OptionalLong top, OptionalLong width, OptionalLong height, Optional<Emulation.WorkAreaInsets> workAreaInsets, OptionalDouble devicePixelRatio, OptionalLong rotation, OptionalLong colorDepth, Optional<String> label, Optional<Boolean> isInternal) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            left.ifPresent(value_ -> params.put("left", value_));
            top.ifPresent(value_ -> params.put("top", value_));
            width.ifPresent(value_ -> params.put("width", value_));
            height.ifPresent(value_ -> params.put("height", value_));
            workAreaInsets.ifPresent(value_ -> params.put("workAreaInsets", CdpObject.json(value_)));
            devicePixelRatio.ifPresent(value_ -> params.put("devicePixelRatio", value_));
            rotation.ifPresent(value_ -> params.put("rotation", value_));
            colorDepth.ifPresent(value_ -> params.put("colorDepth", value_));
            label.ifPresent(value_ -> params.put("label", CdpObject.json(value_)));
            isInternal.ifPresent(value_ -> params.put("isInternal", value_));
            return client.call("Emulation.updateScreen", params, result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("screenInfo")))))));
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(Emulation.ScreenId screenId) {
            return updateScreen(screenId, OptionalLong.empty(), OptionalLong.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty(), OptionalDouble.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Emulation.ScreenInfo> updateScreen(UpdateScreenRequest request) {
            return client.call("Emulation.updateScreen", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Emulation.ScreenInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("screenInfo")))))));
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScreen(Emulation.ScreenId screenId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            return client.call("Emulation.removeScreen", params, result_ -> null);
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScreen(RemoveScreenRequest request) {
            return client.call("Emulation.removeScreen", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param screenId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrimaryScreen(Emulation.ScreenId screenId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("screenId", CdpObject.json(screenId));
            return client.call("Emulation.setPrimaryScreen", params, result_ -> null);
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrimaryScreen(SetPrimaryScreenRequest request) {
            return client.call("Emulation.setPrimaryScreen", request == null ? null : request.toMap(), result_ -> null);
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
