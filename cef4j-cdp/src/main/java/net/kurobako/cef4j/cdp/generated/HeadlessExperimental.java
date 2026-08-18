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
 * This domain provides experimental commands only supported in headless mode.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/HeadlessExperimental.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class HeadlessExperimental {
    private HeadlessExperimental() {}
    /**
     * Encoding options for a screenshot.
     */
    public static final class ScreenshotParams extends CdpObject {
        public ScreenshotParams() {}
        private ScreenshotParams(Map<String, Object> values) { super(values); }
        public static ScreenshotParams fromMap(Map<String, Object> values) {
            return new ScreenshotParams(values);
        }
        /**
         * Image compression format (defaults to png).
         */
        public enum FormatValues implements CdpValue<String> {
            JPEG("jpeg"),
            PNG("png"),
            WEBP("webp");
            public final String value;
            FormatValues(String value) { this.value = value; }
            @Nonnull public String value() { return value; }
            public static FormatValues of(@Nonnull String value) {
                for (FormatValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown FormatValues value: " + value);
            }
        }
        /**
         * Image compression format (defaults to png).
         * @return the protocol field value, empty when absent
         */
        public Optional<ScreenshotParams.FormatValues> format() {
            return Optional.ofNullable(raw("format") == null ? null : ScreenshotParams.FormatValues.of((String) raw("format")));
        }
        /**
         * Compression quality from range [0..100] (jpeg and webp only).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong quality() {
            Long value = CdpObject.numberAsLong(raw("quality"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> optimizeForSpeed() {
            return Optional.ofNullable((Boolean) raw("optimizeForSpeed"));
        }
        /**
         * Image compression format (defaults to png).
         * @param format field value; empty omits the value
         * @return this model
         */
        public ScreenshotParams format(Optional<ScreenshotParams.FormatValues> format) {
            set("format", format.orElse(null));
            return this;
        }
        /**
         * Image compression format (defaults to png).
         * @param format field value; null removes the value
         * @return this model
         */
        public ScreenshotParams format(ScreenshotParams.FormatValues format) {
            set("format", format);
            return this;
        }
        /**
         * Compression quality from range [0..100] (jpeg and webp only).
         * @param quality field value; empty omits the value
         * @return this model
         */
        public ScreenshotParams quality(OptionalLong quality) {
            set("quality", quality.isPresent() ? quality.getAsLong() : null);
            return this;
        }
        /**
         * Compression quality from range [0..100] (jpeg and webp only).
         * @param quality field value; null removes the value
         * @return this model
         */
        public ScreenshotParams quality(Long quality) {
            set("quality", quality);
            return this;
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * @param optimizeForSpeed field value; empty omits the value
         * @return this model
         */
        public ScreenshotParams optimizeForSpeed(Optional<Boolean> optimizeForSpeed) {
            set("optimizeForSpeed", optimizeForSpeed.orElse(null));
            return this;
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * @param optimizeForSpeed field value; null removes the value
         * @return this model
         */
        public ScreenshotParams optimizeForSpeed(Boolean optimizeForSpeed) {
            set("optimizeForSpeed", optimizeForSpeed);
            return this;
        }
    }
    /**
     * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
     */
    public static final class BeginFrameResult extends CdpObject {
        public BeginFrameResult() {}
        private BeginFrameResult(Map<String, Object> values) { super(values); }
        public static BeginFrameResult fromMap(Map<String, Object> values) {
            return new BeginFrameResult(values);
        }
        /**
         * Whether the BeginFrame resulted in damage and, thus, a new frame was committed to the display. Reported for diagnostic uses, may be removed in the future.
         * @return the protocol field value
         */
        public boolean hasDamage() {
            return (Boolean) require("hasDamage");
        }
        /**
         * Base64-encoded image data of the screenshot, if one was requested and successfully taken. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> screenshotData() {
            return Optional.ofNullable((String) raw("screenshotData"));
        }
        /**
         * Whether the BeginFrame resulted in damage and, thus, a new frame was committed to the display. Reported for diagnostic uses, may be removed in the future.
         * @param hasDamage field value
         * @return this model
         */
        public BeginFrameResult hasDamage(boolean hasDamage) {
            set("hasDamage", hasDamage);
            return this;
        }
        /**
         * Base64-encoded image data of the screenshot, if one was requested and successfully taken. (Encoded as a base64 string when passed over JSON)
         * @param screenshotData field value; empty omits the value
         * @return this model
         */
        public BeginFrameResult screenshotData(Optional<String> screenshotData) {
            set("screenshotData", screenshotData.orElse(null));
            return this;
        }
        /**
         * Base64-encoded image data of the screenshot, if one was requested and successfully taken. (Encoded as a base64 string when passed over JSON)
         * @param screenshotData field value; null removes the value
         * @return this model
         */
        public BeginFrameResult screenshotData(String screenshotData) {
            set("screenshotData", screenshotData);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
         * @param frameTimeTicks protocol value
         * @param interval protocol value
         * @param noDisplayUpdates protocol value
         * @param screenshot protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<BeginFrameResult> beginFrame(OptionalDouble frameTimeTicks, OptionalDouble interval, Optional<Boolean> noDisplayUpdates, Optional<HeadlessExperimental.ScreenshotParams> screenshot) {
            Map<String, Object> params = new LinkedHashMap<>();
            frameTimeTicks.ifPresent(value_ -> params.put("frameTimeTicks", value_));
            interval.ifPresent(value_ -> params.put("interval", value_));
            noDisplayUpdates.ifPresent(value_ -> params.put("noDisplayUpdates", value_));
            screenshot.ifPresent(value_ -> params.put("screenshot", CdpObject.json(value_)));
            return client.call("HeadlessExperimental.beginFrame", params, result_ -> new BeginFrameResult(result_));
        }
        /**
         * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
         * @return a stage completing with the command result
         */
        public CompletionStage<BeginFrameResult> beginFrame() {
            return beginFrame(OptionalDouble.empty(), OptionalDouble.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Disables headless events for the target.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> disable() {
            return client.call("HeadlessExperimental.disable", null, result_ -> null);
        }
        /**
         * Enables headless events for the target.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> enable() {
            return client.call("HeadlessExperimental.enable", null, result_ -> null);
        }
    }
}
