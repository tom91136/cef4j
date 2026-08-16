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
 * This domain provides experimental commands only supported in headless mode.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/HeadlessExperimental.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class HeadlessExperimental {
    private HeadlessExperimental() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Encoding options for a screenshot.
     */
    public static final class ScreenshotParams extends CdpObject {
        private ScreenshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static ScreenshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreenshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Image compression format (defaults to png).
         * @return the protocol field value
         */
        @Nullable public String format() {
            return (String) value("format");
        }
        /**
         * Image compression format (defaults to png).
         */
        public static final class FormatValues {
            private FormatValues() {}
            public static final String JPEG = "jpeg";
            public static final String PNG = "png";
            public static final String WEBP = "webp";
        }
        /**
         * Compression quality from range [0..100] (jpeg and webp only).
         * @return the protocol field value
         */
        @Nullable public Long quality() {
            return numberAsLong(value("quality"));
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * @return the protocol field value
         */
        @Nullable public Boolean optimizeForSpeed() {
            return (Boolean) value("optimizeForSpeed");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Image compression format (defaults to png).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder format(@Nullable String value) {
                if (value == null) values.remove("format");
                else values.put("format", jsonValue(value));
                return this;
            }
            /**
             * Compression quality from range [0..100] (jpeg and webp only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quality(@Nullable Long value) {
                if (value == null) values.remove("quality");
                else values.put("quality", jsonValue(value));
                return this;
            }
            /**
             * Optimize image encoding for speed, not for resulting size (defaults to false)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder optimizeForSpeed(@Nullable Boolean value) {
                if (value == null) values.remove("optimizeForSpeed");
                else values.put("optimizeForSpeed", jsonValue(value));
                return this;
            }
            public ScreenshotParams build() {
                return new ScreenshotParams(values);
            }
        }
    }
    /**
     * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
     */
    public static final class BeginFrameParams extends CdpObject {
        private BeginFrameParams(Map<String, Object> values) { super(values); }
        @Nullable public static BeginFrameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BeginFrameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Timestamp of this BeginFrame in Renderer TimeTicks (milliseconds of uptime). If not set, the current time will be used.
         * @return the protocol field value
         */
        @Nullable public Double frameTimeTicks() {
            return numberAsDouble(value("frameTimeTicks"));
        }
        /**
         * The interval between BeginFrames that is reported to the compositor, in milliseconds. Defaults to a 60 frames/second interval, i.e. about 16.666 milliseconds.
         * @return the protocol field value
         */
        @Nullable public Double interval() {
            return numberAsDouble(value("interval"));
        }
        /**
         * Whether updates should not be committed and drawn onto the display. False by default. If true, only side effects of the BeginFrame will be run, such as layout and animations, but any visual updates may not be visible on the display or in screenshots.
         * @return the protocol field value
         */
        @Nullable public Boolean noDisplayUpdates() {
            return (Boolean) value("noDisplayUpdates");
        }
        /**
         * If set, a screenshot of the frame will be captured and returned in the response. Otherwise, no screenshot will be captured. Note that capturing a screenshot can fail, for example, during renderer initialization. In such a case, no screenshot data will be returned.
         * @return the protocol field value
         */
        @Nullable public HeadlessExperimental.ScreenshotParams screenshot() {
            return HeadlessExperimental.ScreenshotParams.fromMap(objectMap(value("screenshot")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Timestamp of this BeginFrame in Renderer TimeTicks (milliseconds of uptime). If not set, the current time will be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameTimeTicks(@Nullable Double value) {
                if (value == null) values.remove("frameTimeTicks");
                else values.put("frameTimeTicks", jsonValue(value));
                return this;
            }
            /**
             * The interval between BeginFrames that is reported to the compositor, in milliseconds. Defaults to a 60 frames/second interval, i.e. about 16.666 milliseconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interval(@Nullable Double value) {
                if (value == null) values.remove("interval");
                else values.put("interval", jsonValue(value));
                return this;
            }
            /**
             * Whether updates should not be committed and drawn onto the display. False by default. If true, only side effects of the BeginFrame will be run, such as layout and animations, but any visual updates may not be visible on the display or in screenshots.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder noDisplayUpdates(@Nullable Boolean value) {
                if (value == null) values.remove("noDisplayUpdates");
                else values.put("noDisplayUpdates", jsonValue(value));
                return this;
            }
            /**
             * If set, a screenshot of the frame will be captured and returned in the response. Otherwise, no screenshot will be captured. Note that capturing a screenshot can fail, for example, during renderer initialization. In such a case, no screenshot data will be returned.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenshot(@Nullable HeadlessExperimental.ScreenshotParams value) {
                if (value == null) values.remove("screenshot");
                else values.put("screenshot", jsonValue(value));
                return this;
            }
            public BeginFrameParams build() {
                return new BeginFrameParams(values);
            }
        }
    }
    /**
     * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
     */
    public static final class BeginFrameResult extends CdpObject {
        private BeginFrameResult(Map<String, Object> values) { super(values); }
        @Nullable public static BeginFrameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BeginFrameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the BeginFrame resulted in damage and, thus, a new frame was committed to the display. Reported for diagnostic uses, may be removed in the future.
         * @return the protocol field value
         */
        @Nullable public Boolean hasDamage() {
            return (Boolean) value("hasDamage");
        }
        /**
         * Base64-encoded image data of the screenshot, if one was requested and successfully taken. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String screenshotData() {
            return (String) value("screenshotData");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the BeginFrame resulted in damage and, thus, a new frame was committed to the display. Reported for diagnostic uses, may be removed in the future.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasDamage(@Nullable Boolean value) {
                if (value == null) values.remove("hasDamage");
                else values.put("hasDamage", jsonValue(value));
                return this;
            }
            /**
             * Base64-encoded image data of the screenshot, if one was requested and successfully taken. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenshotData(@Nullable String value) {
                if (value == null) values.remove("screenshotData");
                else values.put("screenshotData", jsonValue(value));
                return this;
            }
            public BeginFrameResult build() {
                if (!values.containsKey("hasDamage")) throw new IllegalStateException("Missing required CDP field: hasDamage");
                return new BeginFrameResult(values);
            }
        }
    }
    /**
     * Disables headless events for the target.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Disables headless events for the target.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Enables headless events for the target.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables headless events for the target.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a screenshot from the resulting frame. Requires that the target was created with enabled BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also https://goo.gle/chrome-headless-rendering for more background.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<BeginFrameResult> beginFrame(BeginFrameParams params) {
            return client.call("HeadlessExperimental.beginFrame", params, BeginFrameResult::fromMap);
        }
        /**
         * Disables headless events for the target.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<DisableResult> disable() {
            return client.call("HeadlessExperimental.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables headless events for the target.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<EnableResult> enable() {
            return client.call("HeadlessExperimental.enable", null, EnableResult::fromMap);
        }
    }
}
