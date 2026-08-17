// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * This domain emulates different environments for the page.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Emulation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Emulation {
    private Emulation() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SafeAreaInsets extends CdpObject {
        private SafeAreaInsets(Map<String, Object> values) { super(values); }
        @Nullable public static SafeAreaInsets fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SafeAreaInsets(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Overrides safe-area-inset-top.
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * Overrides safe-area-max-inset-top.
         * @return the protocol field value
         */
        @Nullable public Long topMax() {
            return numberAsLong(value("topMax"));
        }
        /**
         * Overrides safe-area-inset-left.
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * Overrides safe-area-max-inset-left.
         * @return the protocol field value
         */
        @Nullable public Long leftMax() {
            return numberAsLong(value("leftMax"));
        }
        /**
         * Overrides safe-area-inset-bottom.
         * @return the protocol field value
         */
        @Nullable public Long bottom() {
            return numberAsLong(value("bottom"));
        }
        /**
         * Overrides safe-area-max-inset-bottom.
         * @return the protocol field value
         */
        @Nullable public Long bottomMax() {
            return numberAsLong(value("bottomMax"));
        }
        /**
         * Overrides safe-area-inset-right.
         * @return the protocol field value
         */
        @Nullable public Long right() {
            return numberAsLong(value("right"));
        }
        /**
         * Overrides safe-area-max-inset-right.
         * @return the protocol field value
         */
        @Nullable public Long rightMax() {
            return numberAsLong(value("rightMax"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Overrides safe-area-inset-top.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-max-inset-top.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder topMax(@Nullable Long value) {
                if (value == null) values.remove("topMax");
                else values.put("topMax", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-inset-left.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-max-inset-left.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder leftMax(@Nullable Long value) {
                if (value == null) values.remove("leftMax");
                else values.put("leftMax", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-inset-bottom.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bottom(@Nullable Long value) {
                if (value == null) values.remove("bottom");
                else values.put("bottom", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-max-inset-bottom.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bottomMax(@Nullable Long value) {
                if (value == null) values.remove("bottomMax");
                else values.put("bottomMax", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-inset-right.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder right(@Nullable Long value) {
                if (value == null) values.remove("right");
                else values.put("right", jsonValue(value));
                return this;
            }
            /**
             * Overrides safe-area-max-inset-right.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rightMax(@Nullable Long value) {
                if (value == null) values.remove("rightMax");
                else values.put("rightMax", jsonValue(value));
                return this;
            }
            public SafeAreaInsets build() {
                return new SafeAreaInsets(values);
            }
        }
    }
    /**
     * Screen orientation.
     */
    public static final class ScreenOrientation extends CdpObject {
        private ScreenOrientation(Map<String, Object> values) { super(values); }
        @Nullable public static ScreenOrientation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreenOrientation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Orientation type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Orientation type.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String PORTRAITPRIMARY = "portraitPrimary";
            public static final String PORTRAITSECONDARY = "portraitSecondary";
            public static final String LANDSCAPEPRIMARY = "landscapePrimary";
            public static final String LANDSCAPESECONDARY = "landscapeSecondary";
        }
        /**
         * Orientation angle.
         * @return the protocol field value
         */
        @Nullable public Long angle() {
            return numberAsLong(value("angle"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Orientation type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Orientation angle.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder angle(@Nullable Long value) {
                if (value == null) values.remove("angle");
                else values.put("angle", jsonValue(value));
                return this;
            }
            public ScreenOrientation build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("angle")) throw new IllegalStateException("Missing required CDP field: angle");
                return new ScreenOrientation(values);
            }
        }
    }
    /**
     */
    public static final class DisplayFeature extends CdpObject {
        private DisplayFeature(Map<String, Object> values) { super(values); }
        @Nullable public static DisplayFeature fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisplayFeature(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Orientation of a display feature in relation to screen
         * @return the protocol field value
         */
        @Nullable public String orientation() {
            return (String) value("orientation");
        }
        /**
         * Orientation of a display feature in relation to screen
         */
        public static final class OrientationValues {
            private OrientationValues() {}
            public static final String VERTICAL = "vertical";
            public static final String HORIZONTAL = "horizontal";
        }
        /**
         * The offset from the screen origin in either the x (for vertical orientation) or y (for horizontal orientation) direction.
         * @return the protocol field value
         */
        @Nullable public Long offset() {
            return numberAsLong(value("offset"));
        }
        /**
         * A display feature may mask content such that it is not physically displayed - this length along with the offset describes this area. A display feature that only splits content will have a 0 mask_length.
         * @return the protocol field value
         */
        @Nullable public Long maskLength() {
            return numberAsLong(value("maskLength"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Orientation of a display feature in relation to screen
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder orientation(@Nullable String value) {
                if (value == null) values.remove("orientation");
                else values.put("orientation", jsonValue(value));
                return this;
            }
            /**
             * The offset from the screen origin in either the x (for vertical orientation) or y (for horizontal orientation) direction.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offset(@Nullable Long value) {
                if (value == null) values.remove("offset");
                else values.put("offset", jsonValue(value));
                return this;
            }
            /**
             * A display feature may mask content such that it is not physically displayed - this length along with the offset describes this area. A display feature that only splits content will have a 0 mask_length.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maskLength(@Nullable Long value) {
                if (value == null) values.remove("maskLength");
                else values.put("maskLength", jsonValue(value));
                return this;
            }
            public DisplayFeature build() {
                if (!values.containsKey("orientation")) throw new IllegalStateException("Missing required CDP field: orientation");
                if (!values.containsKey("offset")) throw new IllegalStateException("Missing required CDP field: offset");
                if (!values.containsKey("maskLength")) throw new IllegalStateException("Missing required CDP field: maskLength");
                return new DisplayFeature(values);
            }
        }
    }
    /**
     */
    public static final class DevicePosture extends CdpObject {
        private DevicePosture(Map<String, Object> values) { super(values); }
        @Nullable public static DevicePosture fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DevicePosture(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Current posture of the device
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Current posture of the device
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String CONTINUOUS = "continuous";
            public static final String FOLDED = "folded";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Current posture of the device
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public DevicePosture build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new DevicePosture(values);
            }
        }
    }
    /**
     */
    public static final class MediaFeature extends CdpObject {
        private MediaFeature(Map<String, Object> values) { super(values); }
        @Nullable public static MediaFeature fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MediaFeature(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the name field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public MediaFeature build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new MediaFeature(values);
            }
        }
    }
    /**
     * advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to allow the next delayed task (if any) to run; pause: The virtual time base may not advance; pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending resource fetches.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VirtualTimePolicy {
        private VirtualTimePolicy() {}
        public static final String ADVANCE = "advance";
        public static final String PAUSE = "pause";
        public static final String PAUSEIFNETWORKFETCHESPENDING = "pauseIfNetworkFetchesPending";
    }
    /**
     * Used to specify User Agent Client Hints to emulate. See https://wicg.github.io/ua-client-hints
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UserAgentBrandVersion extends CdpObject {
        private UserAgentBrandVersion(Map<String, Object> values) { super(values); }
        @Nullable public static UserAgentBrandVersion fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UserAgentBrandVersion(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the brand field.
         * @return the protocol field value
         */
        @Nullable public String brand() {
            return (String) value("brand");
        }
        /**
         * Returns the version field.
         * @return the protocol field value
         */
        @Nullable public String version() {
            return (String) value("version");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the brand field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder brand(@Nullable String value) {
                if (value == null) values.remove("brand");
                else values.put("brand", jsonValue(value));
                return this;
            }
            /**
             * Sets the version field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder version(@Nullable String value) {
                if (value == null) values.remove("version");
                else values.put("version", jsonValue(value));
                return this;
            }
            public UserAgentBrandVersion build() {
                if (!values.containsKey("brand")) throw new IllegalStateException("Missing required CDP field: brand");
                if (!values.containsKey("version")) throw new IllegalStateException("Missing required CDP field: version");
                return new UserAgentBrandVersion(values);
            }
        }
    }
    /**
     * Used to specify User Agent Client Hints to emulate. See https://wicg.github.io/ua-client-hints Missing optional values will be filled in by the target with what it would normally use.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UserAgentMetadata extends CdpObject {
        private UserAgentMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static UserAgentMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UserAgentMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Brands appearing in Sec-CH-UA.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Emulation.UserAgentBrandVersion> brands() {
            return list(value("brands"), element0 -> Emulation.UserAgentBrandVersion.fromMap(objectMap(element0)));
        }
        /**
         * Brands appearing in Sec-CH-UA-Full-Version-List.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Emulation.UserAgentBrandVersion> fullVersionList() {
            return list(value("fullVersionList"), element0 -> Emulation.UserAgentBrandVersion.fromMap(objectMap(element0)));
        }
        /**
         * Returns the fullVersion field.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String fullVersion() {
            return (String) value("fullVersion");
        }
        /**
         * Returns the platform field.
         * @return the protocol field value
         */
        @Nullable public String platform() {
            return (String) value("platform");
        }
        /**
         * Returns the platformVersion field.
         * @return the protocol field value
         */
        @Nullable public String platformVersion() {
            return (String) value("platformVersion");
        }
        /**
         * Returns the architecture field.
         * @return the protocol field value
         */
        @Nullable public String architecture() {
            return (String) value("architecture");
        }
        /**
         * Returns the model field.
         * @return the protocol field value
         */
        @Nullable public String model() {
            return (String) value("model");
        }
        /**
         * Returns the mobile field.
         * @return the protocol field value
         */
        @Nullable public Boolean mobile() {
            return (Boolean) value("mobile");
        }
        /**
         * Returns the bitness field.
         * @return the protocol field value
         */
        @Nullable public String bitness() {
            return (String) value("bitness");
        }
        /**
         * Returns the wow64 field.
         * @return the protocol field value
         */
        @Nullable public Boolean wow64() {
            return (Boolean) value("wow64");
        }
        /**
         * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> formFactors() {
            return list(value("formFactors"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Brands appearing in Sec-CH-UA.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder brands(@Nullable java.util.List<Emulation.UserAgentBrandVersion> value) {
                if (value == null) values.remove("brands");
                else values.put("brands", jsonValue(value));
                return this;
            }
            /**
             * Brands appearing in Sec-CH-UA-Full-Version-List.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fullVersionList(@Nullable java.util.List<Emulation.UserAgentBrandVersion> value) {
                if (value == null) values.remove("fullVersionList");
                else values.put("fullVersionList", jsonValue(value));
                return this;
            }
            /**
             * Sets the fullVersion field.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder fullVersion(@Nullable String value) {
                if (value == null) values.remove("fullVersion");
                else values.put("fullVersion", jsonValue(value));
                return this;
            }
            /**
             * Sets the platform field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder platform(@Nullable String value) {
                if (value == null) values.remove("platform");
                else values.put("platform", jsonValue(value));
                return this;
            }
            /**
             * Sets the platformVersion field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder platformVersion(@Nullable String value) {
                if (value == null) values.remove("platformVersion");
                else values.put("platformVersion", jsonValue(value));
                return this;
            }
            /**
             * Sets the architecture field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder architecture(@Nullable String value) {
                if (value == null) values.remove("architecture");
                else values.put("architecture", jsonValue(value));
                return this;
            }
            /**
             * Sets the model field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder model(@Nullable String value) {
                if (value == null) values.remove("model");
                else values.put("model", jsonValue(value));
                return this;
            }
            /**
             * Sets the mobile field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mobile(@Nullable Boolean value) {
                if (value == null) values.remove("mobile");
                else values.put("mobile", jsonValue(value));
                return this;
            }
            /**
             * Sets the bitness field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bitness(@Nullable String value) {
                if (value == null) values.remove("bitness");
                else values.put("bitness", jsonValue(value));
                return this;
            }
            /**
             * Sets the wow64 field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder wow64(@Nullable Boolean value) {
                if (value == null) values.remove("wow64");
                else values.put("wow64", jsonValue(value));
                return this;
            }
            /**
             * Used to specify User Agent form-factor values. See https://wicg.github.io/ua-client-hints/#sec-ch-ua-form-factors
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder formFactors(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("formFactors");
                else values.put("formFactors", jsonValue(value));
                return this;
            }
            public UserAgentMetadata build() {
                if (!values.containsKey("platform")) throw new IllegalStateException("Missing required CDP field: platform");
                if (!values.containsKey("platformVersion")) throw new IllegalStateException("Missing required CDP field: platformVersion");
                if (!values.containsKey("architecture")) throw new IllegalStateException("Missing required CDP field: architecture");
                if (!values.containsKey("model")) throw new IllegalStateException("Missing required CDP field: model");
                if (!values.containsKey("mobile")) throw new IllegalStateException("Missing required CDP field: mobile");
                return new UserAgentMetadata(values);
            }
        }
    }
    /**
     * Used to specify sensor types to emulate. See https://w3c.github.io/sensors/#automation for more information.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorType {
        private SensorType() {}
        public static final String ABSOLUTE_ORIENTATION = "absolute-orientation";
        public static final String ACCELEROMETER = "accelerometer";
        public static final String AMBIENT_LIGHT = "ambient-light";
        public static final String GRAVITY = "gravity";
        public static final String GYROSCOPE = "gyroscope";
        public static final String LINEAR_ACCELERATION = "linear-acceleration";
        public static final String MAGNETOMETER = "magnetometer";
        public static final String RELATIVE_ORIENTATION = "relative-orientation";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorMetadata extends CdpObject {
        private SensorMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static SensorMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SensorMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the available field.
         * @return the protocol field value
         */
        @Nullable public Boolean available() {
            return (Boolean) value("available");
        }
        /**
         * Returns the minimumFrequency field.
         * @return the protocol field value
         */
        @Nullable public Double minimumFrequency() {
            return numberAsDouble(value("minimumFrequency"));
        }
        /**
         * Returns the maximumFrequency field.
         * @return the protocol field value
         */
        @Nullable public Double maximumFrequency() {
            return numberAsDouble(value("maximumFrequency"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the available field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder available(@Nullable Boolean value) {
                if (value == null) values.remove("available");
                else values.put("available", jsonValue(value));
                return this;
            }
            /**
             * Sets the minimumFrequency field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder minimumFrequency(@Nullable Double value) {
                if (value == null) values.remove("minimumFrequency");
                else values.put("minimumFrequency", jsonValue(value));
                return this;
            }
            /**
             * Sets the maximumFrequency field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maximumFrequency(@Nullable Double value) {
                if (value == null) values.remove("maximumFrequency");
                else values.put("maximumFrequency", jsonValue(value));
                return this;
            }
            public SensorMetadata build() {
                return new SensorMetadata(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReadingSingle extends CdpObject {
        private SensorReadingSingle(Map<String, Object> values) { super(values); }
        @Nullable public static SensorReadingSingle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SensorReadingSingle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public SensorReadingSingle build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SensorReadingSingle(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReadingXYZ extends CdpObject {
        private SensorReadingXYZ(Map<String, Object> values) { super(values); }
        @Nullable public static SensorReadingXYZ fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SensorReadingXYZ(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the x field.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Returns the y field.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Returns the z field.
         * @return the protocol field value
         */
        @Nullable public Double z() {
            return numberAsDouble(value("z"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the x field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Sets the y field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Sets the z field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder z(@Nullable Double value) {
                if (value == null) values.remove("z");
                else values.put("z", jsonValue(value));
                return this;
            }
            public SensorReadingXYZ build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("z")) throw new IllegalStateException("Missing required CDP field: z");
                return new SensorReadingXYZ(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReadingQuaternion extends CdpObject {
        private SensorReadingQuaternion(Map<String, Object> values) { super(values); }
        @Nullable public static SensorReadingQuaternion fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SensorReadingQuaternion(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the x field.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Returns the y field.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Returns the z field.
         * @return the protocol field value
         */
        @Nullable public Double z() {
            return numberAsDouble(value("z"));
        }
        /**
         * Returns the w field.
         * @return the protocol field value
         */
        @Nullable public Double w() {
            return numberAsDouble(value("w"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the x field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Sets the y field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Sets the z field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder z(@Nullable Double value) {
                if (value == null) values.remove("z");
                else values.put("z", jsonValue(value));
                return this;
            }
            /**
             * Sets the w field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder w(@Nullable Double value) {
                if (value == null) values.remove("w");
                else values.put("w", jsonValue(value));
                return this;
            }
            public SensorReadingQuaternion build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("z")) throw new IllegalStateException("Missing required CDP field: z");
                if (!values.containsKey("w")) throw new IllegalStateException("Missing required CDP field: w");
                return new SensorReadingQuaternion(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SensorReading extends CdpObject {
        private SensorReading(Map<String, Object> values) { super(values); }
        @Nullable public static SensorReading fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SensorReading(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the single field.
         * @return the protocol field value
         */
        @Nullable public Emulation.SensorReadingSingle single() {
            return Emulation.SensorReadingSingle.fromMap(objectMap(value("single")));
        }
        /**
         * Returns the xyz field.
         * @return the protocol field value
         */
        @Nullable public Emulation.SensorReadingXYZ xyz() {
            return Emulation.SensorReadingXYZ.fromMap(objectMap(value("xyz")));
        }
        /**
         * Returns the quaternion field.
         * @return the protocol field value
         */
        @Nullable public Emulation.SensorReadingQuaternion quaternion() {
            return Emulation.SensorReadingQuaternion.fromMap(objectMap(value("quaternion")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the single field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder single(@Nullable Emulation.SensorReadingSingle value) {
                if (value == null) values.remove("single");
                else values.put("single", jsonValue(value));
                return this;
            }
            /**
             * Sets the xyz field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder xyz(@Nullable Emulation.SensorReadingXYZ value) {
                if (value == null) values.remove("xyz");
                else values.put("xyz", jsonValue(value));
                return this;
            }
            /**
             * Sets the quaternion field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quaternion(@Nullable Emulation.SensorReadingQuaternion value) {
                if (value == null) values.remove("quaternion");
                else values.put("quaternion", jsonValue(value));
                return this;
            }
            public SensorReading build() {
                return new SensorReading(values);
            }
        }
    }
    /**
     * Wire values for PressureSource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PressureSource {
        private PressureSource() {}
        public static final String CPU = "cpu";
    }
    /**
     * Wire values for PressureState.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PressureState {
        private PressureState() {}
        public static final String NOMINAL = "nominal";
        public static final String FAIR = "fair";
        public static final String SERIOUS = "serious";
        public static final String CRITICAL = "critical";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PressureMetadata extends CdpObject {
        private PressureMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static PressureMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PressureMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the available field.
         * @return the protocol field value
         */
        @Nullable public Boolean available() {
            return (Boolean) value("available");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the available field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder available(@Nullable Boolean value) {
                if (value == null) values.remove("available");
                else values.put("available", jsonValue(value));
                return this;
            }
            public PressureMetadata build() {
                return new PressureMetadata(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WorkAreaInsets extends CdpObject {
        private WorkAreaInsets(Map<String, Object> values) { super(values); }
        @Nullable public static WorkAreaInsets fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WorkAreaInsets(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Work area top inset in pixels. Default is 0;
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * Work area left inset in pixels. Default is 0;
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * Work area bottom inset in pixels. Default is 0;
         * @return the protocol field value
         */
        @Nullable public Long bottom() {
            return numberAsLong(value("bottom"));
        }
        /**
         * Work area right inset in pixels. Default is 0;
         * @return the protocol field value
         */
        @Nullable public Long right() {
            return numberAsLong(value("right"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Work area top inset in pixels. Default is 0;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * Work area left inset in pixels. Default is 0;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * Work area bottom inset in pixels. Default is 0;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bottom(@Nullable Long value) {
                if (value == null) values.remove("bottom");
                else values.put("bottom", jsonValue(value));
                return this;
            }
            /**
             * Work area right inset in pixels. Default is 0;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder right(@Nullable Long value) {
                if (value == null) values.remove("right");
                else values.put("right", jsonValue(value));
                return this;
            }
            public WorkAreaInsets build() {
                return new WorkAreaInsets(values);
            }
        }
    }
    /**
     * Screen information similar to the one returned by window.getScreenDetails() method, see https://w3c.github.io/window-management/#screendetailed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreenInfo extends CdpObject {
        private ScreenInfo(Map<String, Object> values) { super(values); }
        @Nullable public static ScreenInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreenInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Offset of the left edge of the screen.
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * Offset of the top edge of the screen.
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * Width of the screen.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Height of the screen.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * Offset of the left edge of the available screen area.
         * @return the protocol field value
         */
        @Nullable public Long availLeft() {
            return numberAsLong(value("availLeft"));
        }
        /**
         * Offset of the top edge of the available screen area.
         * @return the protocol field value
         */
        @Nullable public Long availTop() {
            return numberAsLong(value("availTop"));
        }
        /**
         * Width of the available screen area.
         * @return the protocol field value
         */
        @Nullable public Long availWidth() {
            return numberAsLong(value("availWidth"));
        }
        /**
         * Height of the available screen area.
         * @return the protocol field value
         */
        @Nullable public Long availHeight() {
            return numberAsLong(value("availHeight"));
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value
         */
        @Nullable public Double devicePixelRatio() {
            return numberAsDouble(value("devicePixelRatio"));
        }
        /**
         * Specifies the screen&#x27;s orientation.
         * @return the protocol field value
         */
        @Nullable public Emulation.ScreenOrientation orientation() {
            return Emulation.ScreenOrientation.fromMap(objectMap(value("orientation")));
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value
         */
        @Nullable public Long colorDepth() {
            return numberAsLong(value("colorDepth"));
        }
        /**
         * Indicates whether the device has multiple screens.
         * @return the protocol field value
         */
        @Nullable public Boolean isExtended() {
            return (Boolean) value("isExtended");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device.
         * @return the protocol field value
         */
        @Nullable public Boolean isInternal() {
            return (Boolean) value("isInternal");
        }
        /**
         * Indicates whether the screen is set as the the operating system primary screen.
         * @return the protocol field value
         */
        @Nullable public Boolean isPrimary() {
            return (Boolean) value("isPrimary");
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value
         */
        @Nullable public String label() {
            return (String) value("label");
        }
        /**
         * Specifies the unique identifier of the screen.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Offset of the left edge of the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * Offset of the top edge of the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * Width of the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Height of the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Offset of the left edge of the available screen area.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder availLeft(@Nullable Long value) {
                if (value == null) values.remove("availLeft");
                else values.put("availLeft", jsonValue(value));
                return this;
            }
            /**
             * Offset of the top edge of the available screen area.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder availTop(@Nullable Long value) {
                if (value == null) values.remove("availTop");
                else values.put("availTop", jsonValue(value));
                return this;
            }
            /**
             * Width of the available screen area.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder availWidth(@Nullable Long value) {
                if (value == null) values.remove("availWidth");
                else values.put("availWidth", jsonValue(value));
                return this;
            }
            /**
             * Height of the available screen area.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder availHeight(@Nullable Long value) {
                if (value == null) values.remove("availHeight");
                else values.put("availHeight", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s device pixel ratio.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder devicePixelRatio(@Nullable Double value) {
                if (value == null) values.remove("devicePixelRatio");
                else values.put("devicePixelRatio", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s orientation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder orientation(@Nullable Emulation.ScreenOrientation value) {
                if (value == null) values.remove("orientation");
                else values.put("orientation", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s color depth in bits.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder colorDepth(@Nullable Long value) {
                if (value == null) values.remove("colorDepth");
                else values.put("colorDepth", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the device has multiple screens.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isExtended(@Nullable Boolean value) {
                if (value == null) values.remove("isExtended");
                else values.put("isExtended", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the screen is internal to the device or external, attached to the device.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isInternal(@Nullable Boolean value) {
                if (value == null) values.remove("isInternal");
                else values.put("isInternal", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the screen is set as the the operating system primary screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isPrimary(@Nullable Boolean value) {
                if (value == null) values.remove("isPrimary");
                else values.put("isPrimary", jsonValue(value));
                return this;
            }
            /**
             * Specifies the descriptive label for the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder label(@Nullable String value) {
                if (value == null) values.remove("label");
                else values.put("label", jsonValue(value));
                return this;
            }
            /**
             * Specifies the unique identifier of the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public ScreenInfo build() {
                if (!values.containsKey("left")) throw new IllegalStateException("Missing required CDP field: left");
                if (!values.containsKey("top")) throw new IllegalStateException("Missing required CDP field: top");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                if (!values.containsKey("availLeft")) throw new IllegalStateException("Missing required CDP field: availLeft");
                if (!values.containsKey("availTop")) throw new IllegalStateException("Missing required CDP field: availTop");
                if (!values.containsKey("availWidth")) throw new IllegalStateException("Missing required CDP field: availWidth");
                if (!values.containsKey("availHeight")) throw new IllegalStateException("Missing required CDP field: availHeight");
                if (!values.containsKey("devicePixelRatio")) throw new IllegalStateException("Missing required CDP field: devicePixelRatio");
                if (!values.containsKey("orientation")) throw new IllegalStateException("Missing required CDP field: orientation");
                if (!values.containsKey("colorDepth")) throw new IllegalStateException("Missing required CDP field: colorDepth");
                if (!values.containsKey("isExtended")) throw new IllegalStateException("Missing required CDP field: isExtended");
                if (!values.containsKey("isInternal")) throw new IllegalStateException("Missing required CDP field: isInternal");
                if (!values.containsKey("isPrimary")) throw new IllegalStateException("Missing required CDP field: isPrimary");
                if (!values.containsKey("label")) throw new IllegalStateException("Missing required CDP field: label");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new ScreenInfo(values);
            }
        }
    }
    /**
     * Enum of image types that can be disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DisabledImageType {
        private DisabledImageType() {}
        public static final String AVIF = "avif";
        public static final String JXL = "jxl";
        public static final String WEBP = "webp";
    }
    /**
     * Tells whether emulation is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanEmulateParams extends CdpObject {
        private CanEmulateParams(Map<String, Object> values) { super(values); }
        @Nullable public static CanEmulateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanEmulateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CanEmulateParams build() {
                return new CanEmulateParams(values);
            }
        }
    }
    /**
     * Tells whether emulation is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanEmulateResult extends CdpObject {
        private CanEmulateResult(Map<String, Object> values) { super(values); }
        @Nullable public static CanEmulateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanEmulateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True if emulation is supported.
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True if emulation is supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public CanEmulateResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new CanEmulateResult(values);
            }
        }
    }
    /**
     * Clears the overridden device metrics.
     */
    public static final class ClearDeviceMetricsOverrideParams extends CdpObject {
        private ClearDeviceMetricsOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDeviceMetricsOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDeviceMetricsOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDeviceMetricsOverrideParams build() {
                return new ClearDeviceMetricsOverrideParams(values);
            }
        }
    }
    /**
     * Clears the overridden device metrics.
     */
    public static final class ClearDeviceMetricsOverrideResult extends CdpObject {
        private ClearDeviceMetricsOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDeviceMetricsOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDeviceMetricsOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDeviceMetricsOverrideResult build() {
                return new ClearDeviceMetricsOverrideResult(values);
            }
        }
    }
    /**
     * Clears the overridden Geolocation Position and Error.
     */
    public static final class ClearGeolocationOverrideParams extends CdpObject {
        private ClearGeolocationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearGeolocationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearGeolocationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearGeolocationOverrideParams build() {
                return new ClearGeolocationOverrideParams(values);
            }
        }
    }
    /**
     * Clears the overridden Geolocation Position and Error.
     */
    public static final class ClearGeolocationOverrideResult extends CdpObject {
        private ClearGeolocationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearGeolocationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearGeolocationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearGeolocationOverrideResult build() {
                return new ClearGeolocationOverrideResult(values);
            }
        }
    }
    /**
     * Requests that page scale factor is reset to initial values.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResetPageScaleFactorParams extends CdpObject {
        private ResetPageScaleFactorParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResetPageScaleFactorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetPageScaleFactorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetPageScaleFactorParams build() {
                return new ResetPageScaleFactorParams(values);
            }
        }
    }
    /**
     * Requests that page scale factor is reset to initial values.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResetPageScaleFactorResult extends CdpObject {
        private ResetPageScaleFactorResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResetPageScaleFactorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetPageScaleFactorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetPageScaleFactorResult build() {
                return new ResetPageScaleFactorResult(values);
            }
        }
    }
    /**
     * Enables or disables simulating a focused and active page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFocusEmulationEnabledParams extends CdpObject {
        private SetFocusEmulationEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetFocusEmulationEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFocusEmulationEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to enable to disable focus emulation.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to enable to disable focus emulation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetFocusEmulationEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetFocusEmulationEnabledParams(values);
            }
        }
    }
    /**
     * Enables or disables simulating a focused and active page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFocusEmulationEnabledResult extends CdpObject {
        private SetFocusEmulationEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetFocusEmulationEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFocusEmulationEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetFocusEmulationEnabledResult build() {
                return new SetFocusEmulationEnabledResult(values);
            }
        }
    }
    /**
     * Automatically render all web contents using a dark theme.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAutoDarkModeOverrideParams extends CdpObject {
        private SetAutoDarkModeOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutoDarkModeOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutoDarkModeOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to enable or disable automatic dark mode. If not specified, any existing override will be cleared.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetAutoDarkModeOverrideParams build() {
                return new SetAutoDarkModeOverrideParams(values);
            }
        }
    }
    /**
     * Automatically render all web contents using a dark theme.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAutoDarkModeOverrideResult extends CdpObject {
        private SetAutoDarkModeOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutoDarkModeOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutoDarkModeOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAutoDarkModeOverrideResult build() {
                return new SetAutoDarkModeOverrideResult(values);
            }
        }
    }
    /**
     * Enables CPU throttling to emulate slow CPUs.
     */
    public static final class SetCPUThrottlingRateParams extends CdpObject {
        private SetCPUThrottlingRateParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCPUThrottlingRateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCPUThrottlingRateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
         * @return the protocol field value
         */
        @Nullable public Double rate() {
            return numberAsDouble(value("rate"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rate(@Nullable Double value) {
                if (value == null) values.remove("rate");
                else values.put("rate", jsonValue(value));
                return this;
            }
            public SetCPUThrottlingRateParams build() {
                if (!values.containsKey("rate")) throw new IllegalStateException("Missing required CDP field: rate");
                return new SetCPUThrottlingRateParams(values);
            }
        }
    }
    /**
     * Enables CPU throttling to emulate slow CPUs.
     */
    public static final class SetCPUThrottlingRateResult extends CdpObject {
        private SetCPUThrottlingRateResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCPUThrottlingRateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCPUThrottlingRateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetCPUThrottlingRateResult build() {
                return new SetCPUThrottlingRateResult(values);
            }
        }
    }
    /**
     * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
     */
    public static final class SetDefaultBackgroundColorOverrideParams extends CdpObject {
        private SetDefaultBackgroundColorOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDefaultBackgroundColorOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDefaultBackgroundColorOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * RGBA of the default background color. If not specified, any existing override will be cleared.
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA color() {
            return DOM.RGBA.fromMap(objectMap(value("color")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * RGBA of the default background color. If not specified, any existing override will be cleared.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder color(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("color");
                else values.put("color", jsonValue(value));
                return this;
            }
            public SetDefaultBackgroundColorOverrideParams build() {
                return new SetDefaultBackgroundColorOverrideParams(values);
            }
        }
    }
    /**
     * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
     */
    public static final class SetDefaultBackgroundColorOverrideResult extends CdpObject {
        private SetDefaultBackgroundColorOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDefaultBackgroundColorOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDefaultBackgroundColorOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDefaultBackgroundColorOverrideResult build() {
                return new SetDefaultBackgroundColorOverrideResult(values);
            }
        }
    }
    /**
     * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSafeAreaInsetsOverrideParams extends CdpObject {
        private SetSafeAreaInsetsOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSafeAreaInsetsOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSafeAreaInsetsOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the insets field.
         * @return the protocol field value
         */
        @Nullable public Emulation.SafeAreaInsets insets() {
            return Emulation.SafeAreaInsets.fromMap(objectMap(value("insets")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the insets field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder insets(@Nullable Emulation.SafeAreaInsets value) {
                if (value == null) values.remove("insets");
                else values.put("insets", jsonValue(value));
                return this;
            }
            public SetSafeAreaInsetsOverrideParams build() {
                if (!values.containsKey("insets")) throw new IllegalStateException("Missing required CDP field: insets");
                return new SetSafeAreaInsetsOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSafeAreaInsetsOverrideResult extends CdpObject {
        private SetSafeAreaInsetsOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSafeAreaInsetsOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSafeAreaInsetsOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSafeAreaInsetsOverrideResult build() {
                return new SetSafeAreaInsetsOverrideResult(values);
            }
        }
    }
    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
     */
    public static final class SetDeviceMetricsOverrideParams extends CdpObject {
        private SetDeviceMetricsOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDeviceMetricsOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDeviceMetricsOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * Overriding device scale factor value. 0 disables the override.
         * @return the protocol field value
         */
        @Nullable public Double deviceScaleFactor() {
            return numberAsDouble(value("deviceScaleFactor"));
        }
        /**
         * Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
         * @return the protocol field value
         */
        @Nullable public Boolean mobile() {
            return (Boolean) value("mobile");
        }
        /**
         * Scale to apply to resulting view image.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long screenWidth() {
            return numberAsLong(value("screenWidth"));
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long screenHeight() {
            return numberAsLong(value("screenHeight"));
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long positionX() {
            return numberAsLong(value("positionX"));
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long positionY() {
            return numberAsLong(value("positionY"));
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean dontSetVisibleSize() {
            return (Boolean) value("dontSetVisibleSize");
        }
        /**
         * Screen orientation override.
         * @return the protocol field value
         */
        @Nullable public Emulation.ScreenOrientation screenOrientation() {
            return Emulation.ScreenOrientation.fromMap(objectMap(value("screenOrientation")));
        }
        /**
         * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Page.Viewport viewport() {
            return Page.Viewport.fromMap(objectMap(value("viewport")));
        }
        /**
         * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Emulation.DisplayFeature displayFeature() {
            return Emulation.DisplayFeature.fromMap(objectMap(value("displayFeature")));
        }
        /**
         * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Emulation.DevicePosture devicePosture() {
            return Emulation.DevicePosture.fromMap(objectMap(value("devicePosture")));
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String scrollbarType() {
            return (String) value("scrollbarType");
        }
        /**
         * Scrollbar type. Default: {@code default}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class ScrollbarTypeValues {
            private ScrollbarTypeValues() {}
            public static final String OVERLAY = "overlay";
            public static final String DEFAULT = "default";
        }
        /**
         * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean screenOrientationLockEmulation() {
            return (Boolean) value("screenOrientationLockEmulation");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Overriding device scale factor value. 0 disables the override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceScaleFactor(@Nullable Double value) {
                if (value == null) values.remove("deviceScaleFactor");
                else values.put("deviceScaleFactor", jsonValue(value));
                return this;
            }
            /**
             * Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mobile(@Nullable Boolean value) {
                if (value == null) values.remove("mobile");
                else values.put("mobile", jsonValue(value));
                return this;
            }
            /**
             * Scale to apply to resulting view image.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scale(@Nullable Double value) {
                if (value == null) values.remove("scale");
                else values.put("scale", jsonValue(value));
                return this;
            }
            /**
             * Overriding screen width value in pixels (minimum 0, maximum 10000000).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenWidth(@Nullable Long value) {
                if (value == null) values.remove("screenWidth");
                else values.put("screenWidth", jsonValue(value));
                return this;
            }
            /**
             * Overriding screen height value in pixels (minimum 0, maximum 10000000).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenHeight(@Nullable Long value) {
                if (value == null) values.remove("screenHeight");
                else values.put("screenHeight", jsonValue(value));
                return this;
            }
            /**
             * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder positionX(@Nullable Long value) {
                if (value == null) values.remove("positionX");
                else values.put("positionX", jsonValue(value));
                return this;
            }
            /**
             * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder positionY(@Nullable Long value) {
                if (value == null) values.remove("positionY");
                else values.put("positionY", jsonValue(value));
                return this;
            }
            /**
             * Do not set visible view size, rely upon explicit setVisibleSize call.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dontSetVisibleSize(@Nullable Boolean value) {
                if (value == null) values.remove("dontSetVisibleSize");
                else values.put("dontSetVisibleSize", jsonValue(value));
                return this;
            }
            /**
             * Screen orientation override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenOrientation(@Nullable Emulation.ScreenOrientation value) {
                if (value == null) values.remove("screenOrientation");
                else values.put("screenOrientation", jsonValue(value));
                return this;
            }
            /**
             * If set, the visible area of the page will be overridden to this viewport. This viewport change is not observed by the page, e.g. viewport-relative elements do not change positions.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder viewport(@Nullable Page.Viewport value) {
                if (value == null) values.remove("viewport");
                else values.put("viewport", jsonValue(value));
                return this;
            }
            /**
             * If set, the display feature of a multi-segment screen. If not set, multi-segment support is turned-off. Deprecated, use Emulation.setDisplayFeaturesOverride.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder displayFeature(@Nullable Emulation.DisplayFeature value) {
                if (value == null) values.remove("displayFeature");
                else values.put("displayFeature", jsonValue(value));
                return this;
            }
            /**
             * If set, the posture of a foldable device. If not set the posture is set to continuous. Deprecated, use Emulation.setDevicePostureOverride.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder devicePosture(@Nullable Emulation.DevicePosture value) {
                if (value == null) values.remove("devicePosture");
                else values.put("devicePosture", jsonValue(value));
                return this;
            }
            /**
             * Scrollbar type. Default: {@code default}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollbarType(@Nullable String value) {
                if (value == null) values.remove("scrollbarType");
                else values.put("scrollbarType", jsonValue(value));
                return this;
            }
            /**
             * If set to true, enables screen orientation lock emulation, which intercepts screen.orientation.lock() calls from the page and reports orientation changes via screenOrientationLockChanged events. This is useful for emulating mobile device orientation lock behavior in responsive design mode.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenOrientationLockEmulation(@Nullable Boolean value) {
                if (value == null) values.remove("screenOrientationLockEmulation");
                else values.put("screenOrientationLockEmulation", jsonValue(value));
                return this;
            }
            public SetDeviceMetricsOverrideParams build() {
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                if (!values.containsKey("deviceScaleFactor")) throw new IllegalStateException("Missing required CDP field: deviceScaleFactor");
                if (!values.containsKey("mobile")) throw new IllegalStateException("Missing required CDP field: mobile");
                return new SetDeviceMetricsOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
     */
    public static final class SetDeviceMetricsOverrideResult extends CdpObject {
        private SetDeviceMetricsOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDeviceMetricsOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDeviceMetricsOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDeviceMetricsOverrideResult build() {
                return new SetDeviceMetricsOverrideResult(values);
            }
        }
    }
    /**
     * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDevicePostureOverrideParams extends CdpObject {
        private SetDevicePostureOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDevicePostureOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDevicePostureOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the posture field.
         * @return the protocol field value
         */
        @Nullable public Emulation.DevicePosture posture() {
            return Emulation.DevicePosture.fromMap(objectMap(value("posture")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the posture field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder posture(@Nullable Emulation.DevicePosture value) {
                if (value == null) values.remove("posture");
                else values.put("posture", jsonValue(value));
                return this;
            }
            public SetDevicePostureOverrideParams build() {
                if (!values.containsKey("posture")) throw new IllegalStateException("Missing required CDP field: posture");
                return new SetDevicePostureOverrideParams(values);
            }
        }
    }
    /**
     * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDevicePostureOverrideResult extends CdpObject {
        private SetDevicePostureOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDevicePostureOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDevicePostureOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDevicePostureOverrideResult build() {
                return new SetDevicePostureOverrideResult(values);
            }
        }
    }
    /**
     * Clears a device posture override set with either setDeviceMetricsOverride() or setDevicePostureOverride() and starts using posture information from the platform again. Does nothing if no override is set.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearDevicePostureOverrideParams extends CdpObject {
        private ClearDevicePostureOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDevicePostureOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDevicePostureOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDevicePostureOverrideParams build() {
                return new ClearDevicePostureOverrideParams(values);
            }
        }
    }
    /**
     * Clears a device posture override set with either setDeviceMetricsOverride() or setDevicePostureOverride() and starts using posture information from the platform again. Does nothing if no override is set.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearDevicePostureOverrideResult extends CdpObject {
        private ClearDevicePostureOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDevicePostureOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDevicePostureOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDevicePostureOverrideResult build() {
                return new ClearDevicePostureOverrideResult(values);
            }
        }
    }
    /**
     * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDisplayFeaturesOverrideParams extends CdpObject {
        private SetDisplayFeaturesOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDisplayFeaturesOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDisplayFeaturesOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the features field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Emulation.DisplayFeature> features() {
            return list(value("features"), element0 -> Emulation.DisplayFeature.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the features field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder features(@Nullable java.util.List<Emulation.DisplayFeature> value) {
                if (value == null) values.remove("features");
                else values.put("features", jsonValue(value));
                return this;
            }
            public SetDisplayFeaturesOverrideParams build() {
                if (!values.containsKey("features")) throw new IllegalStateException("Missing required CDP field: features");
                return new SetDisplayFeaturesOverrideParams(values);
            }
        }
    }
    /**
     * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDisplayFeaturesOverrideResult extends CdpObject {
        private SetDisplayFeaturesOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDisplayFeaturesOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDisplayFeaturesOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDisplayFeaturesOverrideResult build() {
                return new SetDisplayFeaturesOverrideResult(values);
            }
        }
    }
    /**
     * Clears the display features override set with either setDeviceMetricsOverride() or setDisplayFeaturesOverride() and starts using display features from the platform again. Does nothing if no override is set.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearDisplayFeaturesOverrideParams extends CdpObject {
        private ClearDisplayFeaturesOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDisplayFeaturesOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDisplayFeaturesOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDisplayFeaturesOverrideParams build() {
                return new ClearDisplayFeaturesOverrideParams(values);
            }
        }
    }
    /**
     * Clears the display features override set with either setDeviceMetricsOverride() or setDisplayFeaturesOverride() and starts using display features from the platform again. Does nothing if no override is set.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearDisplayFeaturesOverrideResult extends CdpObject {
        private ClearDisplayFeaturesOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDisplayFeaturesOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDisplayFeaturesOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDisplayFeaturesOverrideResult build() {
                return new ClearDisplayFeaturesOverrideResult(values);
            }
        }
    }
    /**
     * Parameters for Emulation.setScrollbarsHidden.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetScrollbarsHiddenParams extends CdpObject {
        private SetScrollbarsHiddenParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetScrollbarsHiddenParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScrollbarsHiddenParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether scrollbars should be always hidden.
         * @return the protocol field value
         */
        @Nullable public Boolean hidden() {
            return (Boolean) value("hidden");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether scrollbars should be always hidden.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hidden(@Nullable Boolean value) {
                if (value == null) values.remove("hidden");
                else values.put("hidden", jsonValue(value));
                return this;
            }
            public SetScrollbarsHiddenParams build() {
                if (!values.containsKey("hidden")) throw new IllegalStateException("Missing required CDP field: hidden");
                return new SetScrollbarsHiddenParams(values);
            }
        }
    }
    /**
     * Result of Emulation.setScrollbarsHidden.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetScrollbarsHiddenResult extends CdpObject {
        private SetScrollbarsHiddenResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetScrollbarsHiddenResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScrollbarsHiddenResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetScrollbarsHiddenResult build() {
                return new SetScrollbarsHiddenResult(values);
            }
        }
    }
    /**
     * Parameters for Emulation.setDocumentCookieDisabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDocumentCookieDisabledParams extends CdpObject {
        private SetDocumentCookieDisabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDocumentCookieDisabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDocumentCookieDisabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether document.coookie API should be disabled.
         * @return the protocol field value
         */
        @Nullable public Boolean disabled() {
            return (Boolean) value("disabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether document.coookie API should be disabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabled(@Nullable Boolean value) {
                if (value == null) values.remove("disabled");
                else values.put("disabled", jsonValue(value));
                return this;
            }
            public SetDocumentCookieDisabledParams build() {
                if (!values.containsKey("disabled")) throw new IllegalStateException("Missing required CDP field: disabled");
                return new SetDocumentCookieDisabledParams(values);
            }
        }
    }
    /**
     * Result of Emulation.setDocumentCookieDisabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDocumentCookieDisabledResult extends CdpObject {
        private SetDocumentCookieDisabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDocumentCookieDisabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDocumentCookieDisabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDocumentCookieDisabledResult build() {
                return new SetDocumentCookieDisabledResult(values);
            }
        }
    }
    /**
     * Parameters for Emulation.setEmitTouchEventsForMouse.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetEmitTouchEventsForMouseParams extends CdpObject {
        private SetEmitTouchEventsForMouseParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmitTouchEventsForMouseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmitTouchEventsForMouseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether touch emulation based on mouse input should be enabled.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        /**
         * Touch/gesture events configuration. Default: current platform.
         * @return the protocol field value
         */
        @Nullable public String configuration() {
            return (String) value("configuration");
        }
        /**
         * Touch/gesture events configuration. Default: current platform.
         */
        public static final class ConfigurationValues {
            private ConfigurationValues() {}
            public static final String MOBILE = "mobile";
            public static final String DESKTOP = "desktop";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether touch emulation based on mouse input should be enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            /**
             * Touch/gesture events configuration. Default: current platform.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder configuration(@Nullable String value) {
                if (value == null) values.remove("configuration");
                else values.put("configuration", jsonValue(value));
                return this;
            }
            public SetEmitTouchEventsForMouseParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetEmitTouchEventsForMouseParams(values);
            }
        }
    }
    /**
     * Result of Emulation.setEmitTouchEventsForMouse.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetEmitTouchEventsForMouseResult extends CdpObject {
        private SetEmitTouchEventsForMouseResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmitTouchEventsForMouseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmitTouchEventsForMouseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetEmitTouchEventsForMouseResult build() {
                return new SetEmitTouchEventsForMouseResult(values);
            }
        }
    }
    /**
     * Emulates the given media type or media feature for CSS media queries.
     */
    public static final class SetEmulatedMediaParams extends CdpObject {
        private SetEmulatedMediaParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmulatedMediaParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmulatedMediaParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Media type to emulate. Empty string disables the override.
         * @return the protocol field value
         */
        @Nullable public String media() {
            return (String) value("media");
        }
        /**
         * Media features to emulate.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Emulation.MediaFeature> features() {
            return list(value("features"), element0 -> Emulation.MediaFeature.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Media type to emulate. Empty string disables the override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder media(@Nullable String value) {
                if (value == null) values.remove("media");
                else values.put("media", jsonValue(value));
                return this;
            }
            /**
             * Media features to emulate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder features(@Nullable java.util.List<Emulation.MediaFeature> value) {
                if (value == null) values.remove("features");
                else values.put("features", jsonValue(value));
                return this;
            }
            public SetEmulatedMediaParams build() {
                return new SetEmulatedMediaParams(values);
            }
        }
    }
    /**
     * Emulates the given media type or media feature for CSS media queries.
     */
    public static final class SetEmulatedMediaResult extends CdpObject {
        private SetEmulatedMediaResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmulatedMediaResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmulatedMediaResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetEmulatedMediaResult build() {
                return new SetEmulatedMediaResult(values);
            }
        }
    }
    /**
     * Emulates the given vision deficiency.
     */
    public static final class SetEmulatedVisionDeficiencyParams extends CdpObject {
        private SetEmulatedVisionDeficiencyParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmulatedVisionDeficiencyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmulatedVisionDeficiencyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Vision deficiency to emulate. Order: best-effort emulations come first, followed by any physiologically accurate emulations for medically recognized color vision deficiencies.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Vision deficiency to emulate. Order: best-effort emulations come first, followed by any physiologically accurate emulations for medically recognized color vision deficiencies.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String NONE = "none";
            public static final String BLURREDVISION = "blurredVision";
            public static final String REDUCEDCONTRAST = "reducedContrast";
            public static final String ACHROMATOPSIA = "achromatopsia";
            public static final String DEUTERANOPIA = "deuteranopia";
            public static final String PROTANOPIA = "protanopia";
            public static final String TRITANOPIA = "tritanopia";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Vision deficiency to emulate. Order: best-effort emulations come first, followed by any physiologically accurate emulations for medically recognized color vision deficiencies.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public SetEmulatedVisionDeficiencyParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new SetEmulatedVisionDeficiencyParams(values);
            }
        }
    }
    /**
     * Emulates the given vision deficiency.
     */
    public static final class SetEmulatedVisionDeficiencyResult extends CdpObject {
        private SetEmulatedVisionDeficiencyResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmulatedVisionDeficiencyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmulatedVisionDeficiencyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetEmulatedVisionDeficiencyResult build() {
                return new SetEmulatedVisionDeficiencyResult(values);
            }
        }
    }
    /**
     * Emulates the given OS text scale.
     */
    public static final class SetEmulatedOSTextScaleParams extends CdpObject {
        private SetEmulatedOSTextScaleParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmulatedOSTextScaleParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmulatedOSTextScaleParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scale field.
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scale field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scale(@Nullable Double value) {
                if (value == null) values.remove("scale");
                else values.put("scale", jsonValue(value));
                return this;
            }
            public SetEmulatedOSTextScaleParams build() {
                return new SetEmulatedOSTextScaleParams(values);
            }
        }
    }
    /**
     * Emulates the given OS text scale.
     */
    public static final class SetEmulatedOSTextScaleResult extends CdpObject {
        private SetEmulatedOSTextScaleResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetEmulatedOSTextScaleResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEmulatedOSTextScaleResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetEmulatedOSTextScaleResult build() {
                return new SetEmulatedOSTextScaleResult(values);
            }
        }
    }
    /**
     * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
     */
    public static final class SetGeolocationOverrideParams extends CdpObject {
        private SetGeolocationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetGeolocationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetGeolocationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Mock latitude
         * @return the protocol field value
         */
        @Nullable public Double latitude() {
            return numberAsDouble(value("latitude"));
        }
        /**
         * Mock longitude
         * @return the protocol field value
         */
        @Nullable public Double longitude() {
            return numberAsDouble(value("longitude"));
        }
        /**
         * Mock accuracy
         * @return the protocol field value
         */
        @Nullable public Double accuracy() {
            return numberAsDouble(value("accuracy"));
        }
        /**
         * Mock altitude
         * @return the protocol field value
         */
        @Nullable public Double altitude() {
            return numberAsDouble(value("altitude"));
        }
        /**
         * Mock altitudeAccuracy
         * @return the protocol field value
         */
        @Nullable public Double altitudeAccuracy() {
            return numberAsDouble(value("altitudeAccuracy"));
        }
        /**
         * Mock heading
         * @return the protocol field value
         */
        @Nullable public Double heading() {
            return numberAsDouble(value("heading"));
        }
        /**
         * Mock speed
         * @return the protocol field value
         */
        @Nullable public Double speed() {
            return numberAsDouble(value("speed"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Mock latitude
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder latitude(@Nullable Double value) {
                if (value == null) values.remove("latitude");
                else values.put("latitude", jsonValue(value));
                return this;
            }
            /**
             * Mock longitude
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder longitude(@Nullable Double value) {
                if (value == null) values.remove("longitude");
                else values.put("longitude", jsonValue(value));
                return this;
            }
            /**
             * Mock accuracy
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accuracy(@Nullable Double value) {
                if (value == null) values.remove("accuracy");
                else values.put("accuracy", jsonValue(value));
                return this;
            }
            /**
             * Mock altitude
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder altitude(@Nullable Double value) {
                if (value == null) values.remove("altitude");
                else values.put("altitude", jsonValue(value));
                return this;
            }
            /**
             * Mock altitudeAccuracy
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder altitudeAccuracy(@Nullable Double value) {
                if (value == null) values.remove("altitudeAccuracy");
                else values.put("altitudeAccuracy", jsonValue(value));
                return this;
            }
            /**
             * Mock heading
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder heading(@Nullable Double value) {
                if (value == null) values.remove("heading");
                else values.put("heading", jsonValue(value));
                return this;
            }
            /**
             * Mock speed
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder speed(@Nullable Double value) {
                if (value == null) values.remove("speed");
                else values.put("speed", jsonValue(value));
                return this;
            }
            public SetGeolocationOverrideParams build() {
                return new SetGeolocationOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
     */
    public static final class SetGeolocationOverrideResult extends CdpObject {
        private SetGeolocationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetGeolocationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetGeolocationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetGeolocationOverrideResult build() {
                return new SetGeolocationOverrideResult(values);
            }
        }
    }
    /**
     * Parameters for Emulation.getOverriddenSensorInformation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetOverriddenSensorInformationParams extends CdpObject {
        private GetOverriddenSensorInformationParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetOverriddenSensorInformationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOverriddenSensorInformationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public GetOverriddenSensorInformationParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new GetOverriddenSensorInformationParams(values);
            }
        }
    }
    /**
     * Result of Emulation.getOverriddenSensorInformation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetOverriddenSensorInformationResult extends CdpObject {
        private GetOverriddenSensorInformationResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetOverriddenSensorInformationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOverriddenSensorInformationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestedSamplingFrequency field.
         * @return the protocol field value
         */
        @Nullable public Double requestedSamplingFrequency() {
            return numberAsDouble(value("requestedSamplingFrequency"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestedSamplingFrequency field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestedSamplingFrequency(@Nullable Double value) {
                if (value == null) values.remove("requestedSamplingFrequency");
                else values.put("requestedSamplingFrequency", jsonValue(value));
                return this;
            }
            public GetOverriddenSensorInformationResult build() {
                if (!values.containsKey("requestedSamplingFrequency")) throw new IllegalStateException("Missing required CDP field: requestedSamplingFrequency");
                return new GetOverriddenSensorInformationResult(values);
            }
        }
    }
    /**
     * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSensorOverrideEnabledParams extends CdpObject {
        private SetSensorOverrideEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSensorOverrideEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSensorOverrideEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the metadata field.
         * @return the protocol field value
         */
        @Nullable public Emulation.SensorMetadata metadata() {
            return Emulation.SensorMetadata.fromMap(objectMap(value("metadata")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enabled field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the metadata field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metadata(@Nullable Emulation.SensorMetadata value) {
                if (value == null) values.remove("metadata");
                else values.put("metadata", jsonValue(value));
                return this;
            }
            public SetSensorOverrideEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new SetSensorOverrideEnabledParams(values);
            }
        }
    }
    /**
     * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSensorOverrideEnabledResult extends CdpObject {
        private SetSensorOverrideEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSensorOverrideEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSensorOverrideEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSensorOverrideEnabledResult build() {
                return new SetSensorOverrideEnabledResult(values);
            }
        }
    }
    /**
     * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSensorOverrideReadingsParams extends CdpObject {
        private SetSensorOverrideReadingsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSensorOverrideReadingsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSensorOverrideReadingsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the reading field.
         * @return the protocol field value
         */
        @Nullable public Emulation.SensorReading reading() {
            return Emulation.SensorReading.fromMap(objectMap(value("reading")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the reading field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reading(@Nullable Emulation.SensorReading value) {
                if (value == null) values.remove("reading");
                else values.put("reading", jsonValue(value));
                return this;
            }
            public SetSensorOverrideReadingsParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("reading")) throw new IllegalStateException("Missing required CDP field: reading");
                return new SetSensorOverrideReadingsParams(values);
            }
        }
    }
    /**
     * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSensorOverrideReadingsResult extends CdpObject {
        private SetSensorOverrideReadingsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSensorOverrideReadingsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSensorOverrideReadingsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSensorOverrideReadingsResult build() {
                return new SetSensorOverrideReadingsResult(values);
            }
        }
    }
    /**
     * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPressureSourceOverrideEnabledParams extends CdpObject {
        private SetPressureSourceOverrideEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPressureSourceOverrideEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPressureSourceOverrideEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        /**
         * Returns the metadata field.
         * @return the protocol field value
         */
        @Nullable public Emulation.PressureMetadata metadata() {
            return Emulation.PressureMetadata.fromMap(objectMap(value("metadata")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enabled field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            /**
             * Sets the source field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * Sets the metadata field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metadata(@Nullable Emulation.PressureMetadata value) {
                if (value == null) values.remove("metadata");
                else values.put("metadata", jsonValue(value));
                return this;
            }
            public SetPressureSourceOverrideEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                return new SetPressureSourceOverrideEnabledParams(values);
            }
        }
    }
    /**
     * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPressureSourceOverrideEnabledResult extends CdpObject {
        private SetPressureSourceOverrideEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPressureSourceOverrideEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPressureSourceOverrideEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPressureSourceOverrideEnabledResult build() {
                return new SetPressureSourceOverrideEnabledResult(values);
            }
        }
    }
    /**
     * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPressureStateOverrideParams extends CdpObject {
        private SetPressureStateOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPressureStateOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPressureStateOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        /**
         * Returns the state field.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the source field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * Sets the state field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            public SetPressureStateOverrideParams build() {
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                return new SetPressureStateOverrideParams(values);
            }
        }
    }
    /**
     * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPressureStateOverrideResult extends CdpObject {
        private SetPressureStateOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPressureStateOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPressureStateOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPressureStateOverrideResult build() {
                return new SetPressureStateOverrideResult(values);
            }
        }
    }
    /**
     * Overrides the Idle state.
     */
    public static final class SetIdleOverrideParams extends CdpObject {
        private SetIdleOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetIdleOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetIdleOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Mock isUserActive
         * @return the protocol field value
         */
        @Nullable public Boolean isUserActive() {
            return (Boolean) value("isUserActive");
        }
        /**
         * Mock isScreenUnlocked
         * @return the protocol field value
         */
        @Nullable public Boolean isScreenUnlocked() {
            return (Boolean) value("isScreenUnlocked");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Mock isUserActive
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isUserActive(@Nullable Boolean value) {
                if (value == null) values.remove("isUserActive");
                else values.put("isUserActive", jsonValue(value));
                return this;
            }
            /**
             * Mock isScreenUnlocked
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isScreenUnlocked(@Nullable Boolean value) {
                if (value == null) values.remove("isScreenUnlocked");
                else values.put("isScreenUnlocked", jsonValue(value));
                return this;
            }
            public SetIdleOverrideParams build() {
                if (!values.containsKey("isUserActive")) throw new IllegalStateException("Missing required CDP field: isUserActive");
                if (!values.containsKey("isScreenUnlocked")) throw new IllegalStateException("Missing required CDP field: isScreenUnlocked");
                return new SetIdleOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the Idle state.
     */
    public static final class SetIdleOverrideResult extends CdpObject {
        private SetIdleOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetIdleOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetIdleOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetIdleOverrideResult build() {
                return new SetIdleOverrideResult(values);
            }
        }
    }
    /**
     * Clears Idle state overrides.
     */
    public static final class ClearIdleOverrideParams extends CdpObject {
        private ClearIdleOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearIdleOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearIdleOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearIdleOverrideParams build() {
                return new ClearIdleOverrideParams(values);
            }
        }
    }
    /**
     * Clears Idle state overrides.
     */
    public static final class ClearIdleOverrideResult extends CdpObject {
        private ClearIdleOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearIdleOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearIdleOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearIdleOverrideResult build() {
                return new ClearIdleOverrideResult(values);
            }
        }
    }
    /**
     * Overrides value returned by the javascript navigator object.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetNavigatorOverridesParams extends CdpObject {
        private SetNavigatorOverridesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetNavigatorOverridesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNavigatorOverridesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value
         */
        @Nullable public String platform() {
            return (String) value("platform");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The platform navigator.platform should return.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder platform(@Nullable String value) {
                if (value == null) values.remove("platform");
                else values.put("platform", jsonValue(value));
                return this;
            }
            public SetNavigatorOverridesParams build() {
                if (!values.containsKey("platform")) throw new IllegalStateException("Missing required CDP field: platform");
                return new SetNavigatorOverridesParams(values);
            }
        }
    }
    /**
     * Overrides value returned by the javascript navigator object.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetNavigatorOverridesResult extends CdpObject {
        private SetNavigatorOverridesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetNavigatorOverridesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNavigatorOverridesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetNavigatorOverridesResult build() {
                return new SetNavigatorOverridesResult(values);
            }
        }
    }
    /**
     * Sets a specified page scale factor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPageScaleFactorParams extends CdpObject {
        private SetPageScaleFactorParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPageScaleFactorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPageScaleFactorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Page scale factor.
         * @return the protocol field value
         */
        @Nullable public Double pageScaleFactor() {
            return numberAsDouble(value("pageScaleFactor"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Page scale factor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageScaleFactor(@Nullable Double value) {
                if (value == null) values.remove("pageScaleFactor");
                else values.put("pageScaleFactor", jsonValue(value));
                return this;
            }
            public SetPageScaleFactorParams build() {
                if (!values.containsKey("pageScaleFactor")) throw new IllegalStateException("Missing required CDP field: pageScaleFactor");
                return new SetPageScaleFactorParams(values);
            }
        }
    }
    /**
     * Sets a specified page scale factor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPageScaleFactorResult extends CdpObject {
        private SetPageScaleFactorResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPageScaleFactorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPageScaleFactorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPageScaleFactorResult build() {
                return new SetPageScaleFactorResult(values);
            }
        }
    }
    /**
     * Switches script execution in the page.
     */
    public static final class SetScriptExecutionDisabledParams extends CdpObject {
        private SetScriptExecutionDisabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetScriptExecutionDisabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScriptExecutionDisabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether script execution should be disabled in the page.
         * @return the protocol field value
         */
        @Nullable public Boolean value() {
            return (Boolean) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether script execution should be disabled in the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Boolean value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public SetScriptExecutionDisabledParams build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetScriptExecutionDisabledParams(values);
            }
        }
    }
    /**
     * Switches script execution in the page.
     */
    public static final class SetScriptExecutionDisabledResult extends CdpObject {
        private SetScriptExecutionDisabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetScriptExecutionDisabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScriptExecutionDisabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetScriptExecutionDisabledResult build() {
                return new SetScriptExecutionDisabledResult(values);
            }
        }
    }
    /**
     * Enables touch on platforms which do not support them.
     */
    public static final class SetTouchEmulationEnabledParams extends CdpObject {
        private SetTouchEmulationEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetTouchEmulationEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTouchEmulationEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the touch event emulation should be enabled.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        /**
         * Maximum touch points supported. Defaults to one.
         * @return the protocol field value
         */
        @Nullable public Long maxTouchPoints() {
            return numberAsLong(value("maxTouchPoints"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the touch event emulation should be enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            /**
             * Maximum touch points supported. Defaults to one.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxTouchPoints(@Nullable Long value) {
                if (value == null) values.remove("maxTouchPoints");
                else values.put("maxTouchPoints", jsonValue(value));
                return this;
            }
            public SetTouchEmulationEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetTouchEmulationEnabledParams(values);
            }
        }
    }
    /**
     * Enables touch on platforms which do not support them.
     */
    public static final class SetTouchEmulationEnabledResult extends CdpObject {
        private SetTouchEmulationEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetTouchEmulationEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTouchEmulationEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetTouchEmulationEnabledResult build() {
                return new SetTouchEmulationEnabledResult(values);
            }
        }
    }
    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetVirtualTimePolicyParams extends CdpObject {
        private SetVirtualTimePolicyParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetVirtualTimePolicyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetVirtualTimePolicyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the policy field.
         * @return the protocol field value
         */
        @Nullable public String policy() {
            return (String) value("policy");
        }
        /**
         * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
         * @return the protocol field value
         */
        @Nullable public Double budget() {
            return numberAsDouble(value("budget"));
        }
        /**
         * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
         * @return the protocol field value
         */
        @Nullable public Long maxVirtualTimeTaskStarvationCount() {
            return numberAsLong(value("maxVirtualTimeTaskStarvationCount"));
        }
        /**
         * If set, base::Time::Now will be overridden to initially return this value.
         * @return the protocol field value
         */
        @Nullable public Double initialVirtualTime() {
            return numberAsDouble(value("initialVirtualTime"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the policy field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder policy(@Nullable String value) {
                if (value == null) values.remove("policy");
                else values.put("policy", jsonValue(value));
                return this;
            }
            /**
             * If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder budget(@Nullable Double value) {
                if (value == null) values.remove("budget");
                else values.put("budget", jsonValue(value));
                return this;
            }
            /**
             * If set this specifies the maximum number of tasks that can be run before virtual is forced forwards to prevent deadlock.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxVirtualTimeTaskStarvationCount(@Nullable Long value) {
                if (value == null) values.remove("maxVirtualTimeTaskStarvationCount");
                else values.put("maxVirtualTimeTaskStarvationCount", jsonValue(value));
                return this;
            }
            /**
             * If set, base::Time::Now will be overridden to initially return this value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initialVirtualTime(@Nullable Double value) {
                if (value == null) values.remove("initialVirtualTime");
                else values.put("initialVirtualTime", jsonValue(value));
                return this;
            }
            public SetVirtualTimePolicyParams build() {
                if (!values.containsKey("policy")) throw new IllegalStateException("Missing required CDP field: policy");
                return new SetVirtualTimePolicyParams(values);
            }
        }
    }
    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetVirtualTimePolicyResult extends CdpObject {
        private SetVirtualTimePolicyResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetVirtualTimePolicyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetVirtualTimePolicyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Absolute timestamp at which virtual time was first enabled (up time in milliseconds).
         * @return the protocol field value
         */
        @Nullable public Double virtualTimeTicksBase() {
            return numberAsDouble(value("virtualTimeTicksBase"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Absolute timestamp at which virtual time was first enabled (up time in milliseconds).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder virtualTimeTicksBase(@Nullable Double value) {
                if (value == null) values.remove("virtualTimeTicksBase");
                else values.put("virtualTimeTicksBase", jsonValue(value));
                return this;
            }
            public SetVirtualTimePolicyResult build() {
                if (!values.containsKey("virtualTimeTicksBase")) throw new IllegalStateException("Missing required CDP field: virtualTimeTicksBase");
                return new SetVirtualTimePolicyResult(values);
            }
        }
    }
    /**
     * Overrides default host system locale with the specified one.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetLocaleOverrideParams extends CdpObject {
        private SetLocaleOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetLocaleOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetLocaleOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
         * @return the protocol field value
         */
        @Nullable public String locale() {
            return (String) value("locale");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ICU style C locale (e.g. &quot;en_US&quot;). If not specified or empty, disables the override and restores default host system locale.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder locale(@Nullable String value) {
                if (value == null) values.remove("locale");
                else values.put("locale", jsonValue(value));
                return this;
            }
            public SetLocaleOverrideParams build() {
                return new SetLocaleOverrideParams(values);
            }
        }
    }
    /**
     * Overrides default host system locale with the specified one.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetLocaleOverrideResult extends CdpObject {
        private SetLocaleOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetLocaleOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetLocaleOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetLocaleOverrideResult build() {
                return new SetLocaleOverrideResult(values);
            }
        }
    }
    /**
     * Overrides default host system timezone with the specified one.
     */
    public static final class SetTimezoneOverrideParams extends CdpObject {
        private SetTimezoneOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetTimezoneOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTimezoneOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The timezone identifier. List of supported timezones: https://source.chromium.org/chromium/chromium/deps/icu.git/+/faee8bc70570192d82d2978a71e2a615788597d1:source/data/misc/metaZones.txt If empty, disables the override and restores default host system timezone.
         * @return the protocol field value
         */
        @Nullable public String timezoneId() {
            return (String) value("timezoneId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The timezone identifier. List of supported timezones: https://source.chromium.org/chromium/chromium/deps/icu.git/+/faee8bc70570192d82d2978a71e2a615788597d1:source/data/misc/metaZones.txt If empty, disables the override and restores default host system timezone.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timezoneId(@Nullable String value) {
                if (value == null) values.remove("timezoneId");
                else values.put("timezoneId", jsonValue(value));
                return this;
            }
            public SetTimezoneOverrideParams build() {
                if (!values.containsKey("timezoneId")) throw new IllegalStateException("Missing required CDP field: timezoneId");
                return new SetTimezoneOverrideParams(values);
            }
        }
    }
    /**
     * Overrides default host system timezone with the specified one.
     */
    public static final class SetTimezoneOverrideResult extends CdpObject {
        private SetTimezoneOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetTimezoneOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTimezoneOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetTimezoneOverrideResult build() {
                return new SetTimezoneOverrideResult(values);
            }
        }
    }
    /**
     * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetVisibleSizeParams extends CdpObject {
        private SetVisibleSizeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetVisibleSizeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetVisibleSizeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame width (DIP).
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Frame height (DIP).
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame width (DIP).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Frame height (DIP).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            public SetVisibleSizeParams build() {
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                return new SetVisibleSizeParams(values);
            }
        }
    }
    /**
     * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetVisibleSizeResult extends CdpObject {
        private SetVisibleSizeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetVisibleSizeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetVisibleSizeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetVisibleSizeResult build() {
                return new SetVisibleSizeResult(values);
            }
        }
    }
    /**
     * Parameters for Emulation.setDisabledImageTypes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDisabledImageTypesParams extends CdpObject {
        private SetDisabledImageTypesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDisabledImageTypesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDisabledImageTypesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Image types to disable.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> imageTypes() {
            return list(value("imageTypes"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Image types to disable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder imageTypes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("imageTypes");
                else values.put("imageTypes", jsonValue(value));
                return this;
            }
            public SetDisabledImageTypesParams build() {
                if (!values.containsKey("imageTypes")) throw new IllegalStateException("Missing required CDP field: imageTypes");
                return new SetDisabledImageTypesParams(values);
            }
        }
    }
    /**
     * Result of Emulation.setDisabledImageTypes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDisabledImageTypesResult extends CdpObject {
        private SetDisabledImageTypesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDisabledImageTypesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDisabledImageTypesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDisabledImageTypesResult build() {
                return new SetDisabledImageTypesResult(values);
            }
        }
    }
    /**
     * Override the value of navigator.connection.saveData
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDataSaverOverrideParams extends CdpObject {
        private SetDataSaverOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDataSaverOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDataSaverOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Override value. Omitting the parameter disables the override.
         * @return the protocol field value
         */
        @Nullable public Boolean dataSaverEnabled() {
            return (Boolean) value("dataSaverEnabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Override value. Omitting the parameter disables the override.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dataSaverEnabled(@Nullable Boolean value) {
                if (value == null) values.remove("dataSaverEnabled");
                else values.put("dataSaverEnabled", jsonValue(value));
                return this;
            }
            public SetDataSaverOverrideParams build() {
                return new SetDataSaverOverrideParams(values);
            }
        }
    }
    /**
     * Override the value of navigator.connection.saveData
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetDataSaverOverrideResult extends CdpObject {
        private SetDataSaverOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDataSaverOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDataSaverOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDataSaverOverrideResult build() {
                return new SetDataSaverOverrideResult(values);
            }
        }
    }
    /**
     * Parameters for Emulation.setHardwareConcurrencyOverride.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetHardwareConcurrencyOverrideParams extends CdpObject {
        private SetHardwareConcurrencyOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetHardwareConcurrencyOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetHardwareConcurrencyOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Hardware concurrency to report
         * @return the protocol field value
         */
        @Nullable public Long hardwareConcurrency() {
            return numberAsLong(value("hardwareConcurrency"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Hardware concurrency to report
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hardwareConcurrency(@Nullable Long value) {
                if (value == null) values.remove("hardwareConcurrency");
                else values.put("hardwareConcurrency", jsonValue(value));
                return this;
            }
            public SetHardwareConcurrencyOverrideParams build() {
                if (!values.containsKey("hardwareConcurrency")) throw new IllegalStateException("Missing required CDP field: hardwareConcurrency");
                return new SetHardwareConcurrencyOverrideParams(values);
            }
        }
    }
    /**
     * Result of Emulation.setHardwareConcurrencyOverride.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetHardwareConcurrencyOverrideResult extends CdpObject {
        private SetHardwareConcurrencyOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetHardwareConcurrencyOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetHardwareConcurrencyOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetHardwareConcurrencyOverrideResult build() {
                return new SetHardwareConcurrencyOverrideResult(values);
            }
        }
    }
    /**
     * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
     */
    public static final class SetUserAgentOverrideParams extends CdpObject {
        private SetUserAgentOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetUserAgentOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetUserAgentOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * User agent to use.
         * @return the protocol field value
         */
        @Nullable public String userAgent() {
            return (String) value("userAgent");
        }
        /**
         * Browser language to emulate.
         * @return the protocol field value
         */
        @Nullable public String acceptLanguage() {
            return (String) value("acceptLanguage");
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value
         */
        @Nullable public String platform() {
            return (String) value("platform");
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Emulation.UserAgentMetadata userAgentMetadata() {
            return Emulation.UserAgentMetadata.fromMap(objectMap(value("userAgentMetadata")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * User agent to use.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userAgent(@Nullable String value) {
                if (value == null) values.remove("userAgent");
                else values.put("userAgent", jsonValue(value));
                return this;
            }
            /**
             * Browser language to emulate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder acceptLanguage(@Nullable String value) {
                if (value == null) values.remove("acceptLanguage");
                else values.put("acceptLanguage", jsonValue(value));
                return this;
            }
            /**
             * The platform navigator.platform should return.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder platform(@Nullable String value) {
                if (value == null) values.remove("platform");
                else values.put("platform", jsonValue(value));
                return this;
            }
            /**
             * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userAgentMetadata(@Nullable Emulation.UserAgentMetadata value) {
                if (value == null) values.remove("userAgentMetadata");
                else values.put("userAgentMetadata", jsonValue(value));
                return this;
            }
            public SetUserAgentOverrideParams build() {
                if (!values.containsKey("userAgent")) throw new IllegalStateException("Missing required CDP field: userAgent");
                return new SetUserAgentOverrideParams(values);
            }
        }
    }
    /**
     * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
     */
    public static final class SetUserAgentOverrideResult extends CdpObject {
        private SetUserAgentOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetUserAgentOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetUserAgentOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetUserAgentOverrideResult build() {
                return new SetUserAgentOverrideResult(values);
            }
        }
    }
    /**
     * Allows overriding the automation flag.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAutomationOverrideParams extends CdpObject {
        private SetAutomationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutomationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutomationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the override should be enabled.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the override should be enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetAutomationOverrideParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetAutomationOverrideParams(values);
            }
        }
    }
    /**
     * Allows overriding the automation flag.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAutomationOverrideResult extends CdpObject {
        private SetAutomationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutomationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutomationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAutomationOverrideResult build() {
                return new SetAutomationOverrideResult(values);
            }
        }
    }
    /**
     * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSmallViewportHeightDifferenceOverrideParams extends CdpObject {
        private SetSmallViewportHeightDifferenceOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSmallViewportHeightDifferenceOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSmallViewportHeightDifferenceOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * This will cause an element of size 100svh to be {@code difference} pixels smaller than an element of size 100lvh.
         * @return the protocol field value
         */
        @Nullable public Long difference() {
            return numberAsLong(value("difference"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * This will cause an element of size 100svh to be {@code difference} pixels smaller than an element of size 100lvh.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder difference(@Nullable Long value) {
                if (value == null) values.remove("difference");
                else values.put("difference", jsonValue(value));
                return this;
            }
            public SetSmallViewportHeightDifferenceOverrideParams build() {
                if (!values.containsKey("difference")) throw new IllegalStateException("Missing required CDP field: difference");
                return new SetSmallViewportHeightDifferenceOverrideParams(values);
            }
        }
    }
    /**
     * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSmallViewportHeightDifferenceOverrideResult extends CdpObject {
        private SetSmallViewportHeightDifferenceOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSmallViewportHeightDifferenceOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSmallViewportHeightDifferenceOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSmallViewportHeightDifferenceOverrideResult build() {
                return new SetSmallViewportHeightDifferenceOverrideResult(values);
            }
        }
    }
    /**
     * Returns device&#x27;s screen configuration. In headful mode, the physical screens configuration is returned, whereas in headless mode, a virtual headless screen configuration is provided instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetScreenInfosParams extends CdpObject {
        private GetScreenInfosParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetScreenInfosParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetScreenInfosParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetScreenInfosParams build() {
                return new GetScreenInfosParams(values);
            }
        }
    }
    /**
     * Returns device&#x27;s screen configuration. In headful mode, the physical screens configuration is returned, whereas in headless mode, a virtual headless screen configuration is provided instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetScreenInfosResult extends CdpObject {
        private GetScreenInfosResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetScreenInfosResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetScreenInfosResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the screenInfos field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Emulation.ScreenInfo> screenInfos() {
            return list(value("screenInfos"), element0 -> Emulation.ScreenInfo.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the screenInfos field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenInfos(@Nullable java.util.List<Emulation.ScreenInfo> value) {
                if (value == null) values.remove("screenInfos");
                else values.put("screenInfos", jsonValue(value));
                return this;
            }
            public GetScreenInfosResult build() {
                if (!values.containsKey("screenInfos")) throw new IllegalStateException("Missing required CDP field: screenInfos");
                return new GetScreenInfosResult(values);
            }
        }
    }
    /**
     * Add a new screen to the device. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AddScreenParams extends CdpObject {
        private AddScreenParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddScreenParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddScreenParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Offset of the left edge of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * Specifies the screen&#x27;s work area. Default is entire screen.
         * @return the protocol field value
         */
        @Nullable public Emulation.WorkAreaInsets workAreaInsets() {
            return Emulation.WorkAreaInsets.fromMap(objectMap(value("workAreaInsets")));
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio. Default is 1.
         * @return the protocol field value
         */
        @Nullable public Double devicePixelRatio() {
            return numberAsDouble(value("devicePixelRatio"));
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
         * @return the protocol field value
         */
        @Nullable public Long rotation() {
            return numberAsLong(value("rotation"));
        }
        /**
         * Specifies the screen&#x27;s color depth in bits. Default is 24.
         * @return the protocol field value
         */
        @Nullable public Long colorDepth() {
            return numberAsLong(value("colorDepth"));
        }
        /**
         * Specifies the descriptive label for the screen. Default is none.
         * @return the protocol field value
         */
        @Nullable public String label() {
            return (String) value("label");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value
         */
        @Nullable public Boolean isInternal() {
            return (Boolean) value("isInternal");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Offset of the left edge of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * Offset of the top edge of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * The width of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * The height of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s work area. Default is entire screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workAreaInsets(@Nullable Emulation.WorkAreaInsets value) {
                if (value == null) values.remove("workAreaInsets");
                else values.put("workAreaInsets", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s device pixel ratio. Default is 1.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder devicePixelRatio(@Nullable Double value) {
                if (value == null) values.remove("devicePixelRatio");
                else values.put("devicePixelRatio", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270. Default is 0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rotation(@Nullable Long value) {
                if (value == null) values.remove("rotation");
                else values.put("rotation", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s color depth in bits. Default is 24.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder colorDepth(@Nullable Long value) {
                if (value == null) values.remove("colorDepth");
                else values.put("colorDepth", jsonValue(value));
                return this;
            }
            /**
             * Specifies the descriptive label for the screen. Default is none.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder label(@Nullable String value) {
                if (value == null) values.remove("label");
                else values.put("label", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isInternal(@Nullable Boolean value) {
                if (value == null) values.remove("isInternal");
                else values.put("isInternal", jsonValue(value));
                return this;
            }
            public AddScreenParams build() {
                if (!values.containsKey("left")) throw new IllegalStateException("Missing required CDP field: left");
                if (!values.containsKey("top")) throw new IllegalStateException("Missing required CDP field: top");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                return new AddScreenParams(values);
            }
        }
    }
    /**
     * Add a new screen to the device. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AddScreenResult extends CdpObject {
        private AddScreenResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddScreenResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddScreenResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the screenInfo field.
         * @return the protocol field value
         */
        @Nullable public Emulation.ScreenInfo screenInfo() {
            return Emulation.ScreenInfo.fromMap(objectMap(value("screenInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the screenInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenInfo(@Nullable Emulation.ScreenInfo value) {
                if (value == null) values.remove("screenInfo");
                else values.put("screenInfo", jsonValue(value));
                return this;
            }
            public AddScreenResult build() {
                if (!values.containsKey("screenInfo")) throw new IllegalStateException("Missing required CDP field: screenInfo");
                return new AddScreenResult(values);
            }
        }
    }
    /**
     * Updates specified screen parameters. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UpdateScreenParams extends CdpObject {
        private UpdateScreenParams(Map<String, Object> values) { super(values); }
        @Nullable public static UpdateScreenParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UpdateScreenParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Target screen identifier.
         * @return the protocol field value
         */
        @Nullable public String screenId() {
            return (String) value("screenId");
        }
        /**
         * Offset of the left edge of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * Offset of the top edge of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * The width of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * The height of the screen in pixels.
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * Specifies the screen&#x27;s work area.
         * @return the protocol field value
         */
        @Nullable public Emulation.WorkAreaInsets workAreaInsets() {
            return Emulation.WorkAreaInsets.fromMap(objectMap(value("workAreaInsets")));
        }
        /**
         * Specifies the screen&#x27;s device pixel ratio.
         * @return the protocol field value
         */
        @Nullable public Double devicePixelRatio() {
            return numberAsDouble(value("devicePixelRatio"));
        }
        /**
         * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
         * @return the protocol field value
         */
        @Nullable public Long rotation() {
            return numberAsLong(value("rotation"));
        }
        /**
         * Specifies the screen&#x27;s color depth in bits.
         * @return the protocol field value
         */
        @Nullable public Long colorDepth() {
            return numberAsLong(value("colorDepth"));
        }
        /**
         * Specifies the descriptive label for the screen.
         * @return the protocol field value
         */
        @Nullable public String label() {
            return (String) value("label");
        }
        /**
         * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
         * @return the protocol field value
         */
        @Nullable public Boolean isInternal() {
            return (Boolean) value("isInternal");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Target screen identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenId(@Nullable String value) {
                if (value == null) values.remove("screenId");
                else values.put("screenId", jsonValue(value));
                return this;
            }
            /**
             * Offset of the left edge of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * Offset of the top edge of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * The width of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * The height of the screen in pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s work area.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workAreaInsets(@Nullable Emulation.WorkAreaInsets value) {
                if (value == null) values.remove("workAreaInsets");
                else values.put("workAreaInsets", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s device pixel ratio.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder devicePixelRatio(@Nullable Double value) {
                if (value == null) values.remove("devicePixelRatio");
                else values.put("devicePixelRatio", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s rotation angle. Available values are 0, 90, 180 and 270.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rotation(@Nullable Long value) {
                if (value == null) values.remove("rotation");
                else values.put("rotation", jsonValue(value));
                return this;
            }
            /**
             * Specifies the screen&#x27;s color depth in bits.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder colorDepth(@Nullable Long value) {
                if (value == null) values.remove("colorDepth");
                else values.put("colorDepth", jsonValue(value));
                return this;
            }
            /**
             * Specifies the descriptive label for the screen.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder label(@Nullable String value) {
                if (value == null) values.remove("label");
                else values.put("label", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the screen is internal to the device or external, attached to the device. Default is false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isInternal(@Nullable Boolean value) {
                if (value == null) values.remove("isInternal");
                else values.put("isInternal", jsonValue(value));
                return this;
            }
            public UpdateScreenParams build() {
                if (!values.containsKey("screenId")) throw new IllegalStateException("Missing required CDP field: screenId");
                return new UpdateScreenParams(values);
            }
        }
    }
    /**
     * Updates specified screen parameters. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UpdateScreenResult extends CdpObject {
        private UpdateScreenResult(Map<String, Object> values) { super(values); }
        @Nullable public static UpdateScreenResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UpdateScreenResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the screenInfo field.
         * @return the protocol field value
         */
        @Nullable public Emulation.ScreenInfo screenInfo() {
            return Emulation.ScreenInfo.fromMap(objectMap(value("screenInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the screenInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenInfo(@Nullable Emulation.ScreenInfo value) {
                if (value == null) values.remove("screenInfo");
                else values.put("screenInfo", jsonValue(value));
                return this;
            }
            public UpdateScreenResult build() {
                if (!values.containsKey("screenInfo")) throw new IllegalStateException("Missing required CDP field: screenInfo");
                return new UpdateScreenResult(values);
            }
        }
    }
    /**
     * Remove screen from the device. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RemoveScreenParams extends CdpObject {
        private RemoveScreenParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveScreenParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveScreenParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the screenId field.
         * @return the protocol field value
         */
        @Nullable public String screenId() {
            return (String) value("screenId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the screenId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenId(@Nullable String value) {
                if (value == null) values.remove("screenId");
                else values.put("screenId", jsonValue(value));
                return this;
            }
            public RemoveScreenParams build() {
                if (!values.containsKey("screenId")) throw new IllegalStateException("Missing required CDP field: screenId");
                return new RemoveScreenParams(values);
            }
        }
    }
    /**
     * Remove screen from the device. Only supported in headless mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RemoveScreenResult extends CdpObject {
        private RemoveScreenResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveScreenResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveScreenResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveScreenResult build() {
                return new RemoveScreenResult(values);
            }
        }
    }
    /**
     * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPrimaryScreenParams extends CdpObject {
        private SetPrimaryScreenParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPrimaryScreenParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPrimaryScreenParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the screenId field.
         * @return the protocol field value
         */
        @Nullable public String screenId() {
            return (String) value("screenId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the screenId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenId(@Nullable String value) {
                if (value == null) values.remove("screenId");
                else values.put("screenId", jsonValue(value));
                return this;
            }
            public SetPrimaryScreenParams build() {
                if (!values.containsKey("screenId")) throw new IllegalStateException("Missing required CDP field: screenId");
                return new SetPrimaryScreenParams(values);
            }
        }
    }
    /**
     * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPrimaryScreenResult extends CdpObject {
        private SetPrimaryScreenResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPrimaryScreenResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPrimaryScreenResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPrimaryScreenResult build() {
                return new SetPrimaryScreenResult(values);
            }
        }
    }
    /**
     * Notification sent after the virtual time budget for the current VirtualTimePolicy has run out.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VirtualTimeBudgetExpiredEvent extends CdpObject {
        private VirtualTimeBudgetExpiredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static VirtualTimeBudgetExpiredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VirtualTimeBudgetExpiredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public VirtualTimeBudgetExpiredEvent build() {
                return new VirtualTimeBudgetExpiredEvent(values);
            }
        }
    }
    /**
     * Fired when a page calls screen.orientation.lock() or screen.orientation.unlock() while device emulation is enabled. This allows the DevTools frontend to update the emulated device orientation accordingly.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreenOrientationLockChangedEvent extends CdpObject {
        private ScreenOrientationLockChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScreenOrientationLockChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreenOrientationLockChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the screen orientation is currently locked.
         * @return the protocol field value
         */
        @Nullable public Boolean locked() {
            return (Boolean) value("locked");
        }
        /**
         * The orientation lock type requested by the page. Only set when locked is true.
         * @return the protocol field value
         */
        @Nullable public Emulation.ScreenOrientation orientation() {
            return Emulation.ScreenOrientation.fromMap(objectMap(value("orientation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the screen orientation is currently locked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder locked(@Nullable Boolean value) {
                if (value == null) values.remove("locked");
                else values.put("locked", jsonValue(value));
                return this;
            }
            /**
             * The orientation lock type requested by the page. Only set when locked is true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder orientation(@Nullable Emulation.ScreenOrientation value) {
                if (value == null) values.remove("orientation");
                else values.put("orientation", jsonValue(value));
                return this;
            }
            public ScreenOrientationLockChangedEvent build() {
                if (!values.containsKey("locked")) throw new IllegalStateException("Missing required CDP field: locked");
                return new ScreenOrientationLockChangedEvent(values);
            }
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
        public CompletionStage<CanEmulateResult> canEmulate() {
            return client.call("Emulation.canEmulate", null, CanEmulateResult::fromMap);
        }
        /**
         * Clears the overridden device metrics.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearDeviceMetricsOverrideResult> clearDeviceMetricsOverride() {
            return client.call("Emulation.clearDeviceMetricsOverride", null, ClearDeviceMetricsOverrideResult::fromMap);
        }
        /**
         * Clears the overridden Geolocation Position and Error.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearGeolocationOverrideResult> clearGeolocationOverride() {
            return client.call("Emulation.clearGeolocationOverride", null, ClearGeolocationOverrideResult::fromMap);
        }
        /**
         * Requests that page scale factor is reset to initial values.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<ResetPageScaleFactorResult> resetPageScaleFactor() {
            return client.call("Emulation.resetPageScaleFactor", null, ResetPageScaleFactorResult::fromMap);
        }
        /**
         * Enables or disables simulating a focused and active page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetFocusEmulationEnabledResult> setFocusEmulationEnabled(SetFocusEmulationEnabledParams params) {
            return client.call("Emulation.setFocusEmulationEnabled", params, SetFocusEmulationEnabledResult::fromMap);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAutoDarkModeOverrideResult> setAutoDarkModeOverride(SetAutoDarkModeOverrideParams params) {
            return client.call("Emulation.setAutoDarkModeOverride", params, SetAutoDarkModeOverrideResult::fromMap);
        }
        /**
         * Automatically render all web contents using a dark theme.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAutoDarkModeOverrideResult> setAutoDarkModeOverride() {
            return setAutoDarkModeOverride(SetAutoDarkModeOverrideParams.builder().build());
        }
        /**
         * Enables CPU throttling to emulate slow CPUs.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCPUThrottlingRateResult> setCPUThrottlingRate(SetCPUThrottlingRateParams params) {
            return client.call("Emulation.setCPUThrottlingRate", params, SetCPUThrottlingRateResult::fromMap);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDefaultBackgroundColorOverrideResult> setDefaultBackgroundColorOverride(SetDefaultBackgroundColorOverrideParams params) {
            return client.call("Emulation.setDefaultBackgroundColorOverride", params, SetDefaultBackgroundColorOverrideResult::fromMap);
        }
        /**
         * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDefaultBackgroundColorOverrideResult> setDefaultBackgroundColorOverride() {
            return setDefaultBackgroundColorOverride(SetDefaultBackgroundColorOverrideParams.builder().build());
        }
        /**
         * Overrides the values for env(safe-area-inset-*) and env(safe-area-max-inset-*). Unset values will cause the respective variables to be undefined, even if previously overridden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSafeAreaInsetsOverrideResult> setSafeAreaInsetsOverride(SetSafeAreaInsetsOverrideParams params) {
            return client.call("Emulation.setSafeAreaInsetsOverride", params, SetSafeAreaInsetsOverrideResult::fromMap);
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDeviceMetricsOverrideResult> setDeviceMetricsOverride(SetDeviceMetricsOverrideParams params) {
            return client.call("Emulation.setDeviceMetricsOverride", params, SetDeviceMetricsOverrideResult::fromMap);
        }
        /**
         * Start reporting the given posture value to the Device Posture API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDevicePostureOverrideResult> setDevicePostureOverride(SetDevicePostureOverrideParams params) {
            return client.call("Emulation.setDevicePostureOverride", params, SetDevicePostureOverrideResult::fromMap);
        }
        /**
         * Clears a device posture override set with either setDeviceMetricsOverride() or setDevicePostureOverride() and starts using posture information from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearDevicePostureOverrideResult> clearDevicePostureOverride() {
            return client.call("Emulation.clearDevicePostureOverride", null, ClearDevicePostureOverrideResult::fromMap);
        }
        /**
         * Start using the given display features to pupulate the Viewport Segments API. This override can also be set in setDeviceMetricsOverride().
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDisplayFeaturesOverrideResult> setDisplayFeaturesOverride(SetDisplayFeaturesOverrideParams params) {
            return client.call("Emulation.setDisplayFeaturesOverride", params, SetDisplayFeaturesOverrideResult::fromMap);
        }
        /**
         * Clears the display features override set with either setDeviceMetricsOverride() or setDisplayFeaturesOverride() and starts using display features from the platform again. Does nothing if no override is set.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearDisplayFeaturesOverrideResult> clearDisplayFeaturesOverride() {
            return client.call("Emulation.clearDisplayFeaturesOverride", null, ClearDisplayFeaturesOverrideResult::fromMap);
        }
        /**
         * Invokes Emulation.setScrollbarsHidden.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScrollbarsHiddenResult> setScrollbarsHidden(SetScrollbarsHiddenParams params) {
            return client.call("Emulation.setScrollbarsHidden", params, SetScrollbarsHiddenResult::fromMap);
        }
        /**
         * Invokes Emulation.setDocumentCookieDisabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDocumentCookieDisabledResult> setDocumentCookieDisabled(SetDocumentCookieDisabledParams params) {
            return client.call("Emulation.setDocumentCookieDisabled", params, SetDocumentCookieDisabledResult::fromMap);
        }
        /**
         * Invokes Emulation.setEmitTouchEventsForMouse.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEmitTouchEventsForMouseResult> setEmitTouchEventsForMouse(SetEmitTouchEventsForMouseParams params) {
            return client.call("Emulation.setEmitTouchEventsForMouse", params, SetEmitTouchEventsForMouseResult::fromMap);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEmulatedMediaResult> setEmulatedMedia(SetEmulatedMediaParams params) {
            return client.call("Emulation.setEmulatedMedia", params, SetEmulatedMediaResult::fromMap);
        }
        /**
         * Emulates the given media type or media feature for CSS media queries.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEmulatedMediaResult> setEmulatedMedia() {
            return setEmulatedMedia(SetEmulatedMediaParams.builder().build());
        }
        /**
         * Emulates the given vision deficiency.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEmulatedVisionDeficiencyResult> setEmulatedVisionDeficiency(SetEmulatedVisionDeficiencyParams params) {
            return client.call("Emulation.setEmulatedVisionDeficiency", params, SetEmulatedVisionDeficiencyResult::fromMap);
        }
        /**
         * Emulates the given OS text scale.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEmulatedOSTextScaleResult> setEmulatedOSTextScale(SetEmulatedOSTextScaleParams params) {
            return client.call("Emulation.setEmulatedOSTextScale", params, SetEmulatedOSTextScaleResult::fromMap);
        }
        /**
         * Emulates the given OS text scale.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEmulatedOSTextScaleResult> setEmulatedOSTextScale() {
            return setEmulatedOSTextScale(SetEmulatedOSTextScaleParams.builder().build());
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetGeolocationOverrideResult> setGeolocationOverride(SetGeolocationOverrideParams params) {
            return client.call("Emulation.setGeolocationOverride", params, SetGeolocationOverrideResult::fromMap);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting latitude, longitude or accuracy emulates position unavailable.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetGeolocationOverrideResult> setGeolocationOverride() {
            return setGeolocationOverride(SetGeolocationOverrideParams.builder().build());
        }
        /**
         * Invokes Emulation.getOverriddenSensorInformation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetOverriddenSensorInformationResult> getOverriddenSensorInformation(GetOverriddenSensorInformationParams params) {
            return client.call("Emulation.getOverriddenSensorInformation", params, GetOverriddenSensorInformationResult::fromMap);
        }
        /**
         * Overrides a platform sensor of a given type. If |enabled| is true, calls to Sensor.start() will use a virtual sensor as backend rather than fetching data from a real hardware sensor. Otherwise, existing virtual sensor-backend Sensor objects will fire an error event and new calls to Sensor.start() will attempt to use a real sensor instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSensorOverrideEnabledResult> setSensorOverrideEnabled(SetSensorOverrideEnabledParams params) {
            return client.call("Emulation.setSensorOverrideEnabled", params, SetSensorOverrideEnabledResult::fromMap);
        }
        /**
         * Updates the sensor readings reported by a sensor type previously overridden by setSensorOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSensorOverrideReadingsResult> setSensorOverrideReadings(SetSensorOverrideReadingsParams params) {
            return client.call("Emulation.setSensorOverrideReadings", params, SetSensorOverrideReadingsResult::fromMap);
        }
        /**
         * Overrides a pressure source of a given type, as used by the Compute Pressure API, so that updates to PressureObserver.observe() are provided via setPressureStateOverride instead of being retrieved from platform-provided telemetry data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPressureSourceOverrideEnabledResult> setPressureSourceOverrideEnabled(SetPressureSourceOverrideEnabledParams params) {
            return client.call("Emulation.setPressureSourceOverrideEnabled", params, SetPressureSourceOverrideEnabledResult::fromMap);
        }
        /**
         * Provides a given pressure state that will be processed and eventually be delivered to PressureObserver users. |source| must have been previously overridden by setPressureSourceOverrideEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPressureStateOverrideResult> setPressureStateOverride(SetPressureStateOverrideParams params) {
            return client.call("Emulation.setPressureStateOverride", params, SetPressureStateOverrideResult::fromMap);
        }
        /**
         * Overrides the Idle state.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetIdleOverrideResult> setIdleOverride(SetIdleOverrideParams params) {
            return client.call("Emulation.setIdleOverride", params, SetIdleOverrideResult::fromMap);
        }
        /**
         * Clears Idle state overrides.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearIdleOverrideResult> clearIdleOverride() {
            return client.call("Emulation.clearIdleOverride", null, ClearIdleOverrideResult::fromMap);
        }
        /**
         * Overrides value returned by the javascript navigator object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetNavigatorOverridesResult> setNavigatorOverrides(SetNavigatorOverridesParams params) {
            return client.call("Emulation.setNavigatorOverrides", params, SetNavigatorOverridesResult::fromMap);
        }
        /**
         * Sets a specified page scale factor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPageScaleFactorResult> setPageScaleFactor(SetPageScaleFactorParams params) {
            return client.call("Emulation.setPageScaleFactor", params, SetPageScaleFactorResult::fromMap);
        }
        /**
         * Switches script execution in the page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScriptExecutionDisabledResult> setScriptExecutionDisabled(SetScriptExecutionDisabledParams params) {
            return client.call("Emulation.setScriptExecutionDisabled", params, SetScriptExecutionDisabledResult::fromMap);
        }
        /**
         * Enables touch on platforms which do not support them.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetTouchEmulationEnabledResult> setTouchEmulationEnabled(SetTouchEmulationEnabledParams params) {
            return client.call("Emulation.setTouchEmulationEnabled", params, SetTouchEmulationEnabledResult::fromMap);
        }
        /**
         * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy. Note this supersedes any previous time budget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetVirtualTimePolicyResult> setVirtualTimePolicy(SetVirtualTimePolicyParams params) {
            return client.call("Emulation.setVirtualTimePolicy", params, SetVirtualTimePolicyResult::fromMap);
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetLocaleOverrideResult> setLocaleOverride(SetLocaleOverrideParams params) {
            return client.call("Emulation.setLocaleOverride", params, SetLocaleOverrideResult::fromMap);
        }
        /**
         * Overrides default host system locale with the specified one.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetLocaleOverrideResult> setLocaleOverride() {
            return setLocaleOverride(SetLocaleOverrideParams.builder().build());
        }
        /**
         * Overrides default host system timezone with the specified one.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetTimezoneOverrideResult> setTimezoneOverride(SetTimezoneOverrideParams params) {
            return client.call("Emulation.setTimezoneOverride", params, SetTimezoneOverrideResult::fromMap);
        }
        /**
         * Resizes the frame/viewport of the page. Note that this does not affect the frame&#x27;s container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetVisibleSizeResult> setVisibleSize(SetVisibleSizeParams params) {
            return client.call("Emulation.setVisibleSize", params, SetVisibleSizeResult::fromMap);
        }
        /**
         * Invokes Emulation.setDisabledImageTypes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDisabledImageTypesResult> setDisabledImageTypes(SetDisabledImageTypesParams params) {
            return client.call("Emulation.setDisabledImageTypes", params, SetDisabledImageTypesResult::fromMap);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDataSaverOverrideResult> setDataSaverOverride(SetDataSaverOverrideParams params) {
            return client.call("Emulation.setDataSaverOverride", params, SetDataSaverOverrideResult::fromMap);
        }
        /**
         * Override the value of navigator.connection.saveData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDataSaverOverrideResult> setDataSaverOverride() {
            return setDataSaverOverride(SetDataSaverOverrideParams.builder().build());
        }
        /**
         * Invokes Emulation.setHardwareConcurrencyOverride.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetHardwareConcurrencyOverrideResult> setHardwareConcurrencyOverride(SetHardwareConcurrencyOverrideParams params) {
            return client.call("Emulation.setHardwareConcurrencyOverride", params, SetHardwareConcurrencyOverrideResult::fromMap);
        }
        /**
         * Allows overriding user agent with the given string. {@code userAgentMetadata} must be set for Client Hint headers to be sent.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetUserAgentOverrideResult> setUserAgentOverride(SetUserAgentOverrideParams params) {
            return client.call("Emulation.setUserAgentOverride", params, SetUserAgentOverrideResult::fromMap);
        }
        /**
         * Allows overriding the automation flag.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAutomationOverrideResult> setAutomationOverride(SetAutomationOverrideParams params) {
            return client.call("Emulation.setAutomationOverride", params, SetAutomationOverrideResult::fromMap);
        }
        /**
         * Allows overriding the difference between the small and large viewport sizes, which determine the value of the {@code svh} and {@code lvh} unit, respectively. Only supported for top-level frames.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSmallViewportHeightDifferenceOverrideResult> setSmallViewportHeightDifferenceOverride(SetSmallViewportHeightDifferenceOverrideParams params) {
            return client.call("Emulation.setSmallViewportHeightDifferenceOverride", params, SetSmallViewportHeightDifferenceOverrideResult::fromMap);
        }
        /**
         * Returns device&#x27;s screen configuration. In headful mode, the physical screens configuration is returned, whereas in headless mode, a virtual headless screen configuration is provided instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetScreenInfosResult> getScreenInfos() {
            return client.call("Emulation.getScreenInfos", null, GetScreenInfosResult::fromMap);
        }
        /**
         * Add a new screen to the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddScreenResult> addScreen(AddScreenParams params) {
            return client.call("Emulation.addScreen", params, AddScreenResult::fromMap);
        }
        /**
         * Updates specified screen parameters. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UpdateScreenResult> updateScreen(UpdateScreenParams params) {
            return client.call("Emulation.updateScreen", params, UpdateScreenResult::fromMap);
        }
        /**
         * Remove screen from the device. Only supported in headless mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveScreenResult> removeScreen(RemoveScreenParams params) {
            return client.call("Emulation.removeScreen", params, RemoveScreenResult::fromMap);
        }
        /**
         * Set primary screen. Only supported in headless mode. Note that this changes the coordinate system origin to the top-left of the new primary screen, updating the bounds and work areas of all existing screens accordingly.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPrimaryScreenResult> setPrimaryScreen(SetPrimaryScreenParams params) {
            return client.call("Emulation.setPrimaryScreen", params, SetPrimaryScreenResult::fromMap);
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
