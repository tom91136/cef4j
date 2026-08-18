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
