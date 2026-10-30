// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * This domain provides experimental commands only supported in headless mode.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/HeadlessExperimental.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
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
            @Override public String value() { return value; }
            public static FormatValues of(String value) {
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
            return raw("format").map(value -> ScreenshotParams.FormatValues.of((String) value));
        }
        /**
         * Compression quality from range [0..100] (jpeg and webp only).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong quality() {
            return raw("quality").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> optimizeForSpeed() {
            return raw("optimizeForSpeed").map(value -> (Boolean) value);
        }
        /**
         * Image compression format (defaults to png).
         * @param format field value
         * @return this model
         */
        public ScreenshotParams format(ScreenshotParams.FormatValues format) {
            set("format", format);
            return this;
        }
        /**
         * Compression quality from range [0..100] (jpeg and webp only).
         * @param quality field value
         * @return this model
         */
        public ScreenshotParams quality(long quality) {
            set("quality", quality);
            return this;
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * @param optimizeForSpeed field value
         * @return this model
         */
        public ScreenshotParams optimizeForSpeed(boolean optimizeForSpeed) {
            set("optimizeForSpeed", optimizeForSpeed);
            return this;
        }
    }
    /**
     * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
     */
    public static final class BeginFrameRequest extends CdpObject {
        public BeginFrameRequest() {}
        public static BeginFrameRequest fromMap(Map<String, Object> values) {
            final BeginFrameRequest instance_ = new BeginFrameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Timestamp of this BeginFrame in Renderer TimeTicks (milliseconds of uptime). If not set, the current time will be used.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble frameTimeTicks() {
            return raw("frameTimeTicks").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * The interval between BeginFrames that is reported to the compositor, in milliseconds. Defaults to a 60 frames/second interval, i.e. about 16.666 milliseconds.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble interval() {
            return raw("interval").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Whether updates should not be committed and drawn onto the display. False by default. If true, only side effects of the BeginFrame will be run, such as layout and animations, but any visual updates may not be visible on the display or in screenshots.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> noDisplayUpdates() {
            return raw("noDisplayUpdates").map(value -> (Boolean) value);
        }
        /**
         * If set, a screenshot of the frame will be captured and returned in the response. Otherwise, no screenshot will be captured. Note that capturing a screenshot can fail, for example, during renderer initialization. In such a case, no screenshot data will be returned.
         * @return the protocol field value, empty when absent
         */
        public Optional<HeadlessExperimental.ScreenshotParams> screenshot() {
            return raw("screenshot").flatMap(CdpObject::objectMap).map(HeadlessExperimental.ScreenshotParams::fromMap);
        }
        /**
         * Timestamp of this BeginFrame in Renderer TimeTicks (milliseconds of uptime). If not set, the current time will be used.
         * @param frameTimeTicks field value
         * @return this model
         */
        public BeginFrameRequest frameTimeTicks(double frameTimeTicks) {
            set("frameTimeTicks", frameTimeTicks);
            return this;
        }
        /**
         * The interval between BeginFrames that is reported to the compositor, in milliseconds. Defaults to a 60 frames/second interval, i.e. about 16.666 milliseconds.
         * @param interval field value
         * @return this model
         */
        public BeginFrameRequest interval(double interval) {
            set("interval", interval);
            return this;
        }
        /**
         * Whether updates should not be committed and drawn onto the display. False by default. If true, only side effects of the BeginFrame will be run, such as layout and animations, but any visual updates may not be visible on the display or in screenshots.
         * @param noDisplayUpdates field value
         * @return this model
         */
        public BeginFrameRequest noDisplayUpdates(boolean noDisplayUpdates) {
            set("noDisplayUpdates", noDisplayUpdates);
            return this;
        }
        /**
         * If set, a screenshot of the frame will be captured and returned in the response. Otherwise, no screenshot will be captured. Note that capturing a screenshot can fail, for example, during renderer initialization. In such a case, no screenshot data will be returned.
         * @param screenshot field value
         * @return this model
         */
        public BeginFrameRequest screenshot(HeadlessExperimental.ScreenshotParams screenshot) {
            set("screenshot", screenshot);
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
            return raw("screenshotData").map(value -> (String) value);
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
         * @param screenshotData field value
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
        public CompletionStage<BeginFrameResult> beginFrame(@Nullable Double frameTimeTicks, @Nullable Double interval, @Nullable Boolean noDisplayUpdates, HeadlessExperimental.@Nullable ScreenshotParams screenshot) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (frameTimeTicks != null) params.put("frameTimeTicks", CdpObject.json(frameTimeTicks));
            if (interval != null) params.put("interval", CdpObject.json(interval));
            if (noDisplayUpdates != null) params.put("noDisplayUpdates", CdpObject.json(noDisplayUpdates));
            if (screenshot != null) params.put("screenshot", CdpObject.json(screenshot));
            return client.call("HeadlessExperimental.beginFrame", params, result_ -> new BeginFrameResult(result_));
        }
        /**
         * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
         * @return a stage completing with the command result
         */
        public CompletionStage<BeginFrameResult> beginFrame() {
            return client.call("HeadlessExperimental.beginFrame", null, result_ -> new BeginFrameResult(result_));
        }
        /**
         * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<BeginFrameResult> beginFrame(BeginFrameRequest request) {
            return client.call("HeadlessExperimental.beginFrame", request == null ? null : request.toMap(), result_ -> new BeginFrameResult(result_));
        }
        /**
         * Disables headless events for the target.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> disable() {
            return client.call("HeadlessExperimental.disable", null);
        }
        /**
         * Enables headless events for the target.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> enable() {
            return client.call("HeadlessExperimental.enable", null);
        }
    }
}
