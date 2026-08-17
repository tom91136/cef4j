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
 * Actions and events related to the inspected page belong to the page domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Page.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Page {
    private Page() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Indicates whether a frame has been identified as an ad.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdFrameType {
        private AdFrameType() {}
        public static final String NONE = "none";
        public static final String CHILD = "child";
        public static final String ROOT = "root";
    }
    /**
     * Wire values for AdFrameExplanation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdFrameExplanation {
        private AdFrameExplanation() {}
        public static final String PARENTISAD = "ParentIsAd";
        public static final String CREATEDBYADSCRIPT = "CreatedByAdScript";
        public static final String MATCHEDBLOCKINGRULE = "MatchedBlockingRule";
    }
    /**
     * Indicates whether a frame has been identified as an ad and why.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdFrameStatus extends CdpObject {
        private AdFrameStatus(Map<String, Object> values) { super(values); }
        @Nullable public static AdFrameStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AdFrameStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the adFrameType field.
         * @return the protocol field value
         */
        @Nullable public String adFrameType() {
            return (String) value("adFrameType");
        }
        /**
         * Returns the explanations field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> explanations() {
            return list(value("explanations"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the adFrameType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder adFrameType(@Nullable String value) {
                if (value == null) values.remove("adFrameType");
                else values.put("adFrameType", jsonValue(value));
                return this;
            }
            /**
             * Sets the explanations field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder explanations(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("explanations");
                else values.put("explanations", jsonValue(value));
                return this;
            }
            public AdFrameStatus build() {
                if (!values.containsKey("adFrameType")) throw new IllegalStateException("Missing required CDP field: adFrameType");
                return new AdFrameStatus(values);
            }
        }
    }
    /**
     * Indicates whether the frame is a secure context and why it is the case.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SecureContextType {
        private SecureContextType() {}
        public static final String SECURE = "Secure";
        public static final String SECURELOCALHOST = "SecureLocalhost";
        public static final String INSECURESCHEME = "InsecureScheme";
        public static final String INSECUREANCESTOR = "InsecureAncestor";
    }
    /**
     * Indicates whether the frame is cross-origin isolated and why it is the case.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginIsolatedContextType {
        private CrossOriginIsolatedContextType() {}
        public static final String ISOLATED = "Isolated";
        public static final String NOTISOLATED = "NotIsolated";
        public static final String NOTISOLATEDFEATUREDISABLED = "NotIsolatedFeatureDisabled";
    }
    /**
     * Wire values for GatedAPIFeatures.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GatedAPIFeatures {
        private GatedAPIFeatures() {}
        public static final String SHAREDARRAYBUFFERS = "SharedArrayBuffers";
        public static final String SHAREDARRAYBUFFERSTRANSFERALLOWED = "SharedArrayBuffersTransferAllowed";
        public static final String PERFORMANCEMEASUREMEMORY = "PerformanceMeasureMemory";
        public static final String PERFORMANCEPROFILE = "PerformanceProfile";
    }
    /**
     * All Permissions Policy features. This enum should match the one defined in services/network/public/cpp/permissions_policy/permissions_policy_features.json5. LINT.IfChange(PermissionsPolicyFeature)
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionsPolicyFeature {
        private PermissionsPolicyFeature() {}
        public static final String ACCELEROMETER = "accelerometer";
        public static final String ALL_SCREENS_CAPTURE = "all-screens-capture";
        public static final String AMBIENT_LIGHT_SENSOR = "ambient-light-sensor";
        public static final String ARIA_NOTIFY = "aria-notify";
        public static final String ATTRIBUTION_REPORTING = "attribution-reporting";
        public static final String AUTOFILL = "autofill";
        public static final String AUTOPLAY = "autoplay";
        public static final String BLUETOOTH = "bluetooth";
        public static final String BROWSING_TOPICS = "browsing-topics";
        public static final String CAMERA = "camera";
        public static final String CAPTURED_SURFACE_CONTROL = "captured-surface-control";
        public static final String CH_DPR = "ch-dpr";
        public static final String CH_DEVICE_MEMORY = "ch-device-memory";
        public static final String CH_DOWNLINK = "ch-downlink";
        public static final String CH_ECT = "ch-ect";
        public static final String CH_PREFERS_COLOR_SCHEME = "ch-prefers-color-scheme";
        public static final String CH_PREFERS_REDUCED_MOTION = "ch-prefers-reduced-motion";
        public static final String CH_PREFERS_REDUCED_TRANSPARENCY = "ch-prefers-reduced-transparency";
        public static final String CH_RTT = "ch-rtt";
        public static final String CH_SAVE_DATA = "ch-save-data";
        public static final String CH_UA = "ch-ua";
        public static final String CH_UA_ARCH = "ch-ua-arch";
        public static final String CH_UA_BITNESS = "ch-ua-bitness";
        public static final String CH_UA_HIGH_ENTROPY_VALUES = "ch-ua-high-entropy-values";
        public static final String CH_UA_PLATFORM = "ch-ua-platform";
        public static final String CH_UA_MODEL = "ch-ua-model";
        public static final String CH_UA_MOBILE = "ch-ua-mobile";
        public static final String CH_UA_FORM_FACTORS = "ch-ua-form-factors";
        public static final String CH_UA_FULL_VERSION = "ch-ua-full-version";
        public static final String CH_UA_FULL_VERSION_LIST = "ch-ua-full-version-list";
        public static final String CH_UA_PLATFORM_VERSION = "ch-ua-platform-version";
        public static final String CH_UA_WOW64 = "ch-ua-wow64";
        public static final String CH_VIEWPORT_HEIGHT = "ch-viewport-height";
        public static final String CH_VIEWPORT_WIDTH = "ch-viewport-width";
        public static final String CH_WIDTH = "ch-width";
        public static final String CLIPBOARD_READ = "clipboard-read";
        public static final String CLIPBOARD_WRITE = "clipboard-write";
        public static final String COMPUTE_PRESSURE = "compute-pressure";
        public static final String CONTROLLED_FRAME = "controlled-frame";
        public static final String CROSS_ORIGIN_ISOLATED = "cross-origin-isolated";
        public static final String DEFERRED_FETCH = "deferred-fetch";
        public static final String DEFERRED_FETCH_MINIMAL = "deferred-fetch-minimal";
        public static final String DEVICE_ATTRIBUTES = "device-attributes";
        public static final String DIGITAL_CREDENTIALS_CREATE = "digital-credentials-create";
        public static final String DIGITAL_CREDENTIALS_GET = "digital-credentials-get";
        public static final String DIRECT_SOCKETS = "direct-sockets";
        public static final String DIRECT_SOCKETS_MULTICAST = "direct-sockets-multicast";
        public static final String DIRECT_SOCKETS_PRIVATE = "direct-sockets-private";
        public static final String DISPLAY_CAPTURE = "display-capture";
        public static final String DOCUMENT_DOMAIN = "document-domain";
        public static final String ENCRYPTED_MEDIA = "encrypted-media";
        public static final String EXECUTION_WHILE_OUT_OF_VIEWPORT = "execution-while-out-of-viewport";
        public static final String EXECUTION_WHILE_NOT_RENDERED = "execution-while-not-rendered";
        public static final String FOCUS_WITHOUT_USER_ACTIVATION = "focus-without-user-activation";
        public static final String FULLSCREEN = "fullscreen";
        public static final String FROBULATE = "frobulate";
        public static final String GAMEPAD = "gamepad";
        public static final String GEOLOCATION = "geolocation";
        public static final String GYROSCOPE = "gyroscope";
        public static final String HID = "hid";
        public static final String IDENTITY_CREDENTIALS_GET = "identity-credentials-get";
        public static final String IDLE_DETECTION = "idle-detection";
        public static final String INTEREST_COHORT = "interest-cohort";
        public static final String JOIN_AD_INTEREST_GROUP = "join-ad-interest-group";
        public static final String KEYBOARD_MAP = "keyboard-map";
        public static final String LANGUAGE_DETECTOR = "language-detector";
        public static final String LANGUAGE_MODEL = "language-model";
        public static final String LOCAL_FONTS = "local-fonts";
        public static final String LOCAL_NETWORK = "local-network";
        public static final String LOCAL_NETWORK_ACCESS = "local-network-access";
        public static final String LOOPBACK_NETWORK = "loopback-network";
        public static final String MAGNETOMETER = "magnetometer";
        public static final String MANUAL_TEXT = "manual-text";
        public static final String MEDIA_PLAYBACK_WHILE_NOT_VISIBLE = "media-playback-while-not-visible";
        public static final String MICROPHONE = "microphone";
        public static final String MIDI = "midi";
        public static final String ON_DEVICE_SPEECH_RECOGNITION = "on-device-speech-recognition";
        public static final String OTP_CREDENTIALS = "otp-credentials";
        public static final String PAYMENT = "payment";
        public static final String PICTURE_IN_PICTURE = "picture-in-picture";
        public static final String PRIVATE_AGGREGATION = "private-aggregation";
        public static final String PRIVATE_STATE_TOKEN_ISSUANCE = "private-state-token-issuance";
        public static final String PRIVATE_STATE_TOKEN_REDEMPTION = "private-state-token-redemption";
        public static final String PUBLICKEY_CREDENTIALS_CREATE = "publickey-credentials-create";
        public static final String PUBLICKEY_CREDENTIALS_GET = "publickey-credentials-get";
        public static final String RECORD_AD_AUCTION_EVENTS = "record-ad-auction-events";
        public static final String REWRITER = "rewriter";
        public static final String RUN_AD_AUCTION = "run-ad-auction";
        public static final String SCREEN_WAKE_LOCK = "screen-wake-lock";
        public static final String SERIAL = "serial";
        public static final String SHARED_STORAGE = "shared-storage";
        public static final String SHARED_STORAGE_SELECT_URL = "shared-storage-select-url";
        public static final String SMART_CARD = "smart-card";
        public static final String SPEAKER_SELECTION = "speaker-selection";
        public static final String STORAGE_ACCESS = "storage-access";
        public static final String SUB_APPS = "sub-apps";
        public static final String SUMMARIZER = "summarizer";
        public static final String SYNC_XHR = "sync-xhr";
        public static final String TOOLS = "tools";
        public static final String TRANSLATOR = "translator";
        public static final String UNLOAD = "unload";
        public static final String USB = "usb";
        public static final String USB_UNRESTRICTED = "usb-unrestricted";
        public static final String VERTICAL_SCROLL = "vertical-scroll";
        public static final String WEB_APP_INSTALLATION = "web-app-installation";
        public static final String WEB_PRINTING = "web-printing";
        public static final String WEB_SHARE = "web-share";
        public static final String WINDOW_MANAGEMENT = "window-management";
        public static final String WRITER = "writer";
        public static final String XR_SPATIAL_TRACKING = "xr-spatial-tracking";
    }
    /**
     * Reason for a permissions policy feature to be disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionsPolicyBlockReason {
        private PermissionsPolicyBlockReason() {}
        public static final String HEADER = "Header";
        public static final String IFRAMEATTRIBUTE = "IframeAttribute";
        public static final String INFENCEDFRAMETREE = "InFencedFrameTree";
        public static final String INISOLATEDAPP = "InIsolatedApp";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionsPolicyBlockLocator extends CdpObject {
        private PermissionsPolicyBlockLocator(Map<String, Object> values) { super(values); }
        @Nullable public static PermissionsPolicyBlockLocator fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PermissionsPolicyBlockLocator(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Returns the blockReason field.
         * @return the protocol field value
         */
        @Nullable public String blockReason() {
            return (String) value("blockReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Sets the blockReason field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockReason(@Nullable String value) {
                if (value == null) values.remove("blockReason");
                else values.put("blockReason", jsonValue(value));
                return this;
            }
            public PermissionsPolicyBlockLocator build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("blockReason")) throw new IllegalStateException("Missing required CDP field: blockReason");
                return new PermissionsPolicyBlockLocator(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionsPolicyFeatureState extends CdpObject {
        private PermissionsPolicyFeatureState(Map<String, Object> values) { super(values); }
        @Nullable public static PermissionsPolicyFeatureState fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PermissionsPolicyFeatureState(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the feature field.
         * @return the protocol field value
         */
        @Nullable public String feature() {
            return (String) value("feature");
        }
        /**
         * Returns the allowed field.
         * @return the protocol field value
         */
        @Nullable public Boolean allowed() {
            return (Boolean) value("allowed");
        }
        /**
         * Returns the locator field.
         * @return the protocol field value
         */
        @Nullable public Page.PermissionsPolicyBlockLocator locator() {
            return Page.PermissionsPolicyBlockLocator.fromMap(objectMap(value("locator")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the feature field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder feature(@Nullable String value) {
                if (value == null) values.remove("feature");
                else values.put("feature", jsonValue(value));
                return this;
            }
            /**
             * Sets the allowed field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowed(@Nullable Boolean value) {
                if (value == null) values.remove("allowed");
                else values.put("allowed", jsonValue(value));
                return this;
            }
            /**
             * Sets the locator field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder locator(@Nullable Page.PermissionsPolicyBlockLocator value) {
                if (value == null) values.remove("locator");
                else values.put("locator", jsonValue(value));
                return this;
            }
            public PermissionsPolicyFeatureState build() {
                if (!values.containsKey("feature")) throw new IllegalStateException("Missing required CDP field: feature");
                if (!values.containsKey("allowed")) throw new IllegalStateException("Missing required CDP field: allowed");
                return new PermissionsPolicyFeatureState(values);
            }
        }
    }
    /**
     * Origin Trial(https://www.chromium.org/blink/origin-trials) support. Status for an Origin Trial token.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialTokenStatus {
        private OriginTrialTokenStatus() {}
        public static final String SUCCESS = "Success";
        public static final String NOTSUPPORTED = "NotSupported";
        public static final String INSECURE = "Insecure";
        public static final String EXPIRED = "Expired";
        public static final String WRONGORIGIN = "WrongOrigin";
        public static final String INVALIDSIGNATURE = "InvalidSignature";
        public static final String MALFORMED = "Malformed";
        public static final String WRONGVERSION = "WrongVersion";
        public static final String FEATUREDISABLED = "FeatureDisabled";
        public static final String TOKENDISABLED = "TokenDisabled";
        public static final String FEATUREDISABLEDFORUSER = "FeatureDisabledForUser";
        public static final String UNKNOWNTRIAL = "UnknownTrial";
    }
    /**
     * Status for an Origin Trial.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialStatus {
        private OriginTrialStatus() {}
        public static final String ENABLED = "Enabled";
        public static final String VALIDTOKENNOTPROVIDED = "ValidTokenNotProvided";
        public static final String OSNOTSUPPORTED = "OSNotSupported";
        public static final String TRIALNOTALLOWED = "TrialNotAllowed";
    }
    /**
     * Wire values for OriginTrialUsageRestriction.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialUsageRestriction {
        private OriginTrialUsageRestriction() {}
        public static final String NONE = "None";
        public static final String SUBSET = "Subset";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialToken extends CdpObject {
        private OriginTrialToken(Map<String, Object> values) { super(values); }
        @Nullable public static OriginTrialToken fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OriginTrialToken(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Returns the matchSubDomains field.
         * @return the protocol field value
         */
        @Nullable public Boolean matchSubDomains() {
            return (Boolean) value("matchSubDomains");
        }
        /**
         * Returns the trialName field.
         * @return the protocol field value
         */
        @Nullable public String trialName() {
            return (String) value("trialName");
        }
        /**
         * Returns the expiryTime field.
         * @return the protocol field value
         */
        @Nullable public Double expiryTime() {
            return numberAsDouble(value("expiryTime"));
        }
        /**
         * Returns the isThirdParty field.
         * @return the protocol field value
         */
        @Nullable public Boolean isThirdParty() {
            return (Boolean) value("isThirdParty");
        }
        /**
         * Returns the usageRestriction field.
         * @return the protocol field value
         */
        @Nullable public String usageRestriction() {
            return (String) value("usageRestriction");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the origin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Sets the matchSubDomains field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchSubDomains(@Nullable Boolean value) {
                if (value == null) values.remove("matchSubDomains");
                else values.put("matchSubDomains", jsonValue(value));
                return this;
            }
            /**
             * Sets the trialName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder trialName(@Nullable String value) {
                if (value == null) values.remove("trialName");
                else values.put("trialName", jsonValue(value));
                return this;
            }
            /**
             * Sets the expiryTime field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expiryTime(@Nullable Double value) {
                if (value == null) values.remove("expiryTime");
                else values.put("expiryTime", jsonValue(value));
                return this;
            }
            /**
             * Sets the isThirdParty field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isThirdParty(@Nullable Boolean value) {
                if (value == null) values.remove("isThirdParty");
                else values.put("isThirdParty", jsonValue(value));
                return this;
            }
            /**
             * Sets the usageRestriction field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder usageRestriction(@Nullable String value) {
                if (value == null) values.remove("usageRestriction");
                else values.put("usageRestriction", jsonValue(value));
                return this;
            }
            public OriginTrialToken build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("matchSubDomains")) throw new IllegalStateException("Missing required CDP field: matchSubDomains");
                if (!values.containsKey("trialName")) throw new IllegalStateException("Missing required CDP field: trialName");
                if (!values.containsKey("expiryTime")) throw new IllegalStateException("Missing required CDP field: expiryTime");
                if (!values.containsKey("isThirdParty")) throw new IllegalStateException("Missing required CDP field: isThirdParty");
                if (!values.containsKey("usageRestriction")) throw new IllegalStateException("Missing required CDP field: usageRestriction");
                return new OriginTrialToken(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialTokenWithStatus extends CdpObject {
        private OriginTrialTokenWithStatus(Map<String, Object> values) { super(values); }
        @Nullable public static OriginTrialTokenWithStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OriginTrialTokenWithStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the rawTokenText field.
         * @return the protocol field value
         */
        @Nullable public String rawTokenText() {
            return (String) value("rawTokenText");
        }
        /**
         * {@code parsedToken} is present only when the token is extractable and parsable.
         * @return the protocol field value
         */
        @Nullable public Page.OriginTrialToken parsedToken() {
            return Page.OriginTrialToken.fromMap(objectMap(value("parsedToken")));
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the rawTokenText field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rawTokenText(@Nullable String value) {
                if (value == null) values.remove("rawTokenText");
                else values.put("rawTokenText", jsonValue(value));
                return this;
            }
            /**
             * {@code parsedToken} is present only when the token is extractable and parsable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parsedToken(@Nullable Page.OriginTrialToken value) {
                if (value == null) values.remove("parsedToken");
                else values.put("parsedToken", jsonValue(value));
                return this;
            }
            /**
             * Sets the status field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            public OriginTrialTokenWithStatus build() {
                if (!values.containsKey("rawTokenText")) throw new IllegalStateException("Missing required CDP field: rawTokenText");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new OriginTrialTokenWithStatus(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrial extends CdpObject {
        private OriginTrial(Map<String, Object> values) { super(values); }
        @Nullable public static OriginTrial fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OriginTrial(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the trialName field.
         * @return the protocol field value
         */
        @Nullable public String trialName() {
            return (String) value("trialName");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Returns the tokensWithStatus field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.OriginTrialTokenWithStatus> tokensWithStatus() {
            return list(value("tokensWithStatus"), element0 -> Page.OriginTrialTokenWithStatus.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the trialName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder trialName(@Nullable String value) {
                if (value == null) values.remove("trialName");
                else values.put("trialName", jsonValue(value));
                return this;
            }
            /**
             * Sets the status field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Sets the tokensWithStatus field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tokensWithStatus(@Nullable java.util.List<Page.OriginTrialTokenWithStatus> value) {
                if (value == null) values.remove("tokensWithStatus");
                else values.put("tokensWithStatus", jsonValue(value));
                return this;
            }
            public OriginTrial build() {
                if (!values.containsKey("trialName")) throw new IllegalStateException("Missing required CDP field: trialName");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("tokensWithStatus")) throw new IllegalStateException("Missing required CDP field: tokensWithStatus");
                return new OriginTrial(values);
            }
        }
    }
    /**
     * Additional information about the frame document&#x27;s security origin.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SecurityOriginDetails extends CdpObject {
        private SecurityOriginDetails(Map<String, Object> values) { super(values); }
        @Nullable public static SecurityOriginDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SecurityOriginDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Indicates whether the frame document&#x27;s security origin is one of the local hostnames (e.g. &quot;localhost&quot;) or IP addresses (IPv4 127.0.0.0/8 or IPv6 ::1).
         * @return the protocol field value
         */
        @Nullable public Boolean isLocalhost() {
            return (Boolean) value("isLocalhost");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Indicates whether the frame document&#x27;s security origin is one of the local hostnames (e.g. &quot;localhost&quot;) or IP addresses (IPv4 127.0.0.0/8 or IPv6 ::1).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isLocalhost(@Nullable Boolean value) {
                if (value == null) values.remove("isLocalhost");
                else values.put("isLocalhost", jsonValue(value));
                return this;
            }
            public SecurityOriginDetails build() {
                if (!values.containsKey("isLocalhost")) throw new IllegalStateException("Missing required CDP field: isLocalhost");
                return new SecurityOriginDetails(values);
            }
        }
    }
    /**
     * Information about the Frame on the page.
     */
    public static final class Frame extends CdpObject {
        private Frame(Map<String, Object> values) { super(values); }
        @Nullable public static Frame fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Frame(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame unique identifier.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Parent frame identifier.
         * @return the protocol field value
         */
        @Nullable public String parentId() {
            return (String) value("parentId");
        }
        /**
         * Identifier of the loader associated with this frame.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Frame&#x27;s name as specified in the tag.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Frame document&#x27;s URL without fragment.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Frame document&#x27;s URL fragment including the &#x27;#&#x27;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String urlFragment() {
            return (String) value("urlFragment");
        }
        /**
         * Frame document&#x27;s registered domain, taking the public suffixes list into account. Extracted from the Frame&#x27;s url. Example URLs: http://www.google.com/file.html -&gt; &quot;google.com&quot; http://a.b.co.uk/file.html -&gt; &quot;b.co.uk&quot;
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String domainAndRegistry() {
            return (String) value("domainAndRegistry");
        }
        /**
         * Frame document&#x27;s security origin.
         * @return the protocol field value
         */
        @Nullable public String securityOrigin() {
            return (String) value("securityOrigin");
        }
        /**
         * Additional details about the frame document&#x27;s security origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Page.SecurityOriginDetails securityOriginDetails() {
            return Page.SecurityOriginDetails.fromMap(objectMap(value("securityOriginDetails")));
        }
        /**
         * Frame document&#x27;s mimeType as determined by the browser.
         * @return the protocol field value
         */
        @Nullable public String mimeType() {
            return (String) value("mimeType");
        }
        /**
         * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike url above, this URL may contain a fragment.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String unreachableUrl() {
            return (String) value("unreachableUrl");
        }
        /**
         * Indicates whether this frame was tagged as an ad and why.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Page.AdFrameStatus adFrameStatus() {
            return Page.AdFrameStatus.fromMap(objectMap(value("adFrameStatus")));
        }
        /**
         * Indicates whether the main document is a secure context and explains why that is the case.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String secureContextType() {
            return (String) value("secureContextType");
        }
        /**
         * Indicates whether this is a cross origin isolated context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String crossOriginIsolatedContextType() {
            return (String) value("crossOriginIsolatedContextType");
        }
        /**
         * Indicated which gated APIs / features are available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> gatedAPIFeatures() {
            return list(value("gatedAPIFeatures"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame unique identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Parent frame identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable String value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the loader associated with this frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * Frame&#x27;s name as specified in the tag.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Frame document&#x27;s URL without fragment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Frame document&#x27;s URL fragment including the &#x27;#&#x27;.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlFragment(@Nullable String value) {
                if (value == null) values.remove("urlFragment");
                else values.put("urlFragment", jsonValue(value));
                return this;
            }
            /**
             * Frame document&#x27;s registered domain, taking the public suffixes list into account. Extracted from the Frame&#x27;s url. Example URLs: http://www.google.com/file.html -&gt; &quot;google.com&quot; http://a.b.co.uk/file.html -&gt; &quot;b.co.uk&quot;
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domainAndRegistry(@Nullable String value) {
                if (value == null) values.remove("domainAndRegistry");
                else values.put("domainAndRegistry", jsonValue(value));
                return this;
            }
            /**
             * Frame document&#x27;s security origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityOrigin(@Nullable String value) {
                if (value == null) values.remove("securityOrigin");
                else values.put("securityOrigin", jsonValue(value));
                return this;
            }
            /**
             * Additional details about the frame document&#x27;s security origin.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityOriginDetails(@Nullable Page.SecurityOriginDetails value) {
                if (value == null) values.remove("securityOriginDetails");
                else values.put("securityOriginDetails", jsonValue(value));
                return this;
            }
            /**
             * Frame document&#x27;s mimeType as determined by the browser.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mimeType(@Nullable String value) {
                if (value == null) values.remove("mimeType");
                else values.put("mimeType", jsonValue(value));
                return this;
            }
            /**
             * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike url above, this URL may contain a fragment.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unreachableUrl(@Nullable String value) {
                if (value == null) values.remove("unreachableUrl");
                else values.put("unreachableUrl", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether this frame was tagged as an ad and why.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder adFrameStatus(@Nullable Page.AdFrameStatus value) {
                if (value == null) values.remove("adFrameStatus");
                else values.put("adFrameStatus", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the main document is a secure context and explains why that is the case.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder secureContextType(@Nullable String value) {
                if (value == null) values.remove("secureContextType");
                else values.put("secureContextType", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether this is a cross origin isolated context.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder crossOriginIsolatedContextType(@Nullable String value) {
                if (value == null) values.remove("crossOriginIsolatedContextType");
                else values.put("crossOriginIsolatedContextType", jsonValue(value));
                return this;
            }
            /**
             * Indicated which gated APIs / features are available.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gatedAPIFeatures(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("gatedAPIFeatures");
                else values.put("gatedAPIFeatures", jsonValue(value));
                return this;
            }
            public Frame build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("domainAndRegistry")) throw new IllegalStateException("Missing required CDP field: domainAndRegistry");
                if (!values.containsKey("securityOrigin")) throw new IllegalStateException("Missing required CDP field: securityOrigin");
                if (!values.containsKey("mimeType")) throw new IllegalStateException("Missing required CDP field: mimeType");
                if (!values.containsKey("secureContextType")) throw new IllegalStateException("Missing required CDP field: secureContextType");
                if (!values.containsKey("crossOriginIsolatedContextType")) throw new IllegalStateException("Missing required CDP field: crossOriginIsolatedContextType");
                if (!values.containsKey("gatedAPIFeatures")) throw new IllegalStateException("Missing required CDP field: gatedAPIFeatures");
                return new Frame(values);
            }
        }
    }
    /**
     * Information about the Resource on the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameResource extends CdpObject {
        private FrameResource(Map<String, Object> values) { super(values); }
        @Nullable public static FrameResource fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameResource(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resource URL.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Type of this resource.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Resource mimeType as determined by the browser.
         * @return the protocol field value
         */
        @Nullable public String mimeType() {
            return (String) value("mimeType");
        }
        /**
         * last-modified timestamp as reported by server.
         * @return the protocol field value
         */
        @Nullable public Double lastModified() {
            return numberAsDouble(value("lastModified"));
        }
        /**
         * Resource content size.
         * @return the protocol field value
         */
        @Nullable public Double contentSize() {
            return numberAsDouble(value("contentSize"));
        }
        /**
         * True if the resource failed to load.
         * @return the protocol field value
         */
        @Nullable public Boolean failed() {
            return (Boolean) value("failed");
        }
        /**
         * True if the resource was canceled during loading.
         * @return the protocol field value
         */
        @Nullable public Boolean canceled() {
            return (Boolean) value("canceled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resource URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Type of this resource.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Resource mimeType as determined by the browser.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mimeType(@Nullable String value) {
                if (value == null) values.remove("mimeType");
                else values.put("mimeType", jsonValue(value));
                return this;
            }
            /**
             * last-modified timestamp as reported by server.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lastModified(@Nullable Double value) {
                if (value == null) values.remove("lastModified");
                else values.put("lastModified", jsonValue(value));
                return this;
            }
            /**
             * Resource content size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentSize(@Nullable Double value) {
                if (value == null) values.remove("contentSize");
                else values.put("contentSize", jsonValue(value));
                return this;
            }
            /**
             * True if the resource failed to load.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder failed(@Nullable Boolean value) {
                if (value == null) values.remove("failed");
                else values.put("failed", jsonValue(value));
                return this;
            }
            /**
             * True if the resource was canceled during loading.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder canceled(@Nullable Boolean value) {
                if (value == null) values.remove("canceled");
                else values.put("canceled", jsonValue(value));
                return this;
            }
            public FrameResource build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("mimeType")) throw new IllegalStateException("Missing required CDP field: mimeType");
                return new FrameResource(values);
            }
        }
    }
    /**
     * Information about the Frame hierarchy along with their cached resources.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameResourceTree extends CdpObject {
        private FrameResourceTree(Map<String, Object> values) { super(values); }
        @Nullable public static FrameResourceTree fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameResourceTree(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame information for this tree item.
         * @return the protocol field value
         */
        @Nullable public Page.Frame frame() {
            return Page.Frame.fromMap(objectMap(value("frame")));
        }
        /**
         * Child frames.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.FrameResourceTree> childFrames() {
            return list(value("childFrames"), element0 -> Page.FrameResourceTree.fromMap(objectMap(element0)));
        }
        /**
         * Information about frame resources.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.FrameResource> resources() {
            return list(value("resources"), element0 -> Page.FrameResource.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame information for this tree item.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frame(@Nullable Page.Frame value) {
                if (value == null) values.remove("frame");
                else values.put("frame", jsonValue(value));
                return this;
            }
            /**
             * Child frames.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childFrames(@Nullable java.util.List<Page.FrameResourceTree> value) {
                if (value == null) values.remove("childFrames");
                else values.put("childFrames", jsonValue(value));
                return this;
            }
            /**
             * Information about frame resources.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resources(@Nullable java.util.List<Page.FrameResource> value) {
                if (value == null) values.remove("resources");
                else values.put("resources", jsonValue(value));
                return this;
            }
            public FrameResourceTree build() {
                if (!values.containsKey("frame")) throw new IllegalStateException("Missing required CDP field: frame");
                if (!values.containsKey("resources")) throw new IllegalStateException("Missing required CDP field: resources");
                return new FrameResourceTree(values);
            }
        }
    }
    /**
     * Information about the Frame hierarchy.
     */
    public static final class FrameTree extends CdpObject {
        private FrameTree(Map<String, Object> values) { super(values); }
        @Nullable public static FrameTree fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameTree(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame information for this tree item.
         * @return the protocol field value
         */
        @Nullable public Page.Frame frame() {
            return Page.Frame.fromMap(objectMap(value("frame")));
        }
        /**
         * Child frames.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.FrameTree> childFrames() {
            return list(value("childFrames"), element0 -> Page.FrameTree.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame information for this tree item.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frame(@Nullable Page.Frame value) {
                if (value == null) values.remove("frame");
                else values.put("frame", jsonValue(value));
                return this;
            }
            /**
             * Child frames.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childFrames(@Nullable java.util.List<Page.FrameTree> value) {
                if (value == null) values.remove("childFrames");
                else values.put("childFrames", jsonValue(value));
                return this;
            }
            public FrameTree build() {
                if (!values.containsKey("frame")) throw new IllegalStateException("Missing required CDP field: frame");
                return new FrameTree(values);
            }
        }
    }
    /**
     * Transition type.
     */
    public static final class TransitionType {
        private TransitionType() {}
        public static final String LINK = "link";
        public static final String TYPED = "typed";
        public static final String ADDRESS_BAR = "address_bar";
        public static final String AUTO_BOOKMARK = "auto_bookmark";
        public static final String AUTO_SUBFRAME = "auto_subframe";
        public static final String MANUAL_SUBFRAME = "manual_subframe";
        public static final String GENERATED = "generated";
        public static final String AUTO_TOPLEVEL = "auto_toplevel";
        public static final String FORM_SUBMIT = "form_submit";
        public static final String RELOAD = "reload";
        public static final String KEYWORD = "keyword";
        public static final String KEYWORD_GENERATED = "keyword_generated";
        public static final String OTHER = "other";
    }
    /**
     * Navigation history entry.
     */
    public static final class NavigationEntry extends CdpObject {
        private NavigationEntry(Map<String, Object> values) { super(values); }
        @Nullable public static NavigationEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigationEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique id of the navigation history entry.
         * @return the protocol field value
         */
        @Nullable public Long id() {
            return numberAsLong(value("id"));
        }
        /**
         * URL of the navigation history entry.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * URL that the user typed in the url bar.
         * @return the protocol field value
         */
        @Nullable public String userTypedURL() {
            return (String) value("userTypedURL");
        }
        /**
         * Title of the navigation history entry.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * Transition type.
         * @return the protocol field value
         */
        @Nullable public String transitionType() {
            return (String) value("transitionType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique id of the navigation history entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable Long value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * URL of the navigation history entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * URL that the user typed in the url bar.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userTypedURL(@Nullable String value) {
                if (value == null) values.remove("userTypedURL");
                else values.put("userTypedURL", jsonValue(value));
                return this;
            }
            /**
             * Title of the navigation history entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Transition type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transitionType(@Nullable String value) {
                if (value == null) values.remove("transitionType");
                else values.put("transitionType", jsonValue(value));
                return this;
            }
            public NavigationEntry build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("userTypedURL")) throw new IllegalStateException("Missing required CDP field: userTypedURL");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                if (!values.containsKey("transitionType")) throw new IllegalStateException("Missing required CDP field: transitionType");
                return new NavigationEntry(values);
            }
        }
    }
    /**
     * Screencast frame metadata.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameMetadata extends CdpObject {
        private ScreencastFrameMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static ScreencastFrameMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreencastFrameMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Top offset in DIP.
         * @return the protocol field value
         */
        @Nullable public Double offsetTop() {
            return numberAsDouble(value("offsetTop"));
        }
        /**
         * Page scale factor.
         * @return the protocol field value
         */
        @Nullable public Double pageScaleFactor() {
            return numberAsDouble(value("pageScaleFactor"));
        }
        /**
         * Device screen width in DIP.
         * @return the protocol field value
         */
        @Nullable public Double deviceWidth() {
            return numberAsDouble(value("deviceWidth"));
        }
        /**
         * Device screen height in DIP.
         * @return the protocol field value
         */
        @Nullable public Double deviceHeight() {
            return numberAsDouble(value("deviceHeight"));
        }
        /**
         * Position of horizontal scroll in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double scrollOffsetX() {
            return numberAsDouble(value("scrollOffsetX"));
        }
        /**
         * Position of vertical scroll in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double scrollOffsetY() {
            return numberAsDouble(value("scrollOffsetY"));
        }
        /**
         * Frame swap timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Top offset in DIP.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offsetTop(@Nullable Double value) {
                if (value == null) values.remove("offsetTop");
                else values.put("offsetTop", jsonValue(value));
                return this;
            }
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
            /**
             * Device screen width in DIP.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceWidth(@Nullable Double value) {
                if (value == null) values.remove("deviceWidth");
                else values.put("deviceWidth", jsonValue(value));
                return this;
            }
            /**
             * Device screen height in DIP.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceHeight(@Nullable Double value) {
                if (value == null) values.remove("deviceHeight");
                else values.put("deviceHeight", jsonValue(value));
                return this;
            }
            /**
             * Position of horizontal scroll in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollOffsetX(@Nullable Double value) {
                if (value == null) values.remove("scrollOffsetX");
                else values.put("scrollOffsetX", jsonValue(value));
                return this;
            }
            /**
             * Position of vertical scroll in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollOffsetY(@Nullable Double value) {
                if (value == null) values.remove("scrollOffsetY");
                else values.put("scrollOffsetY", jsonValue(value));
                return this;
            }
            /**
             * Frame swap timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public ScreencastFrameMetadata build() {
                if (!values.containsKey("offsetTop")) throw new IllegalStateException("Missing required CDP field: offsetTop");
                if (!values.containsKey("pageScaleFactor")) throw new IllegalStateException("Missing required CDP field: pageScaleFactor");
                if (!values.containsKey("deviceWidth")) throw new IllegalStateException("Missing required CDP field: deviceWidth");
                if (!values.containsKey("deviceHeight")) throw new IllegalStateException("Missing required CDP field: deviceHeight");
                if (!values.containsKey("scrollOffsetX")) throw new IllegalStateException("Missing required CDP field: scrollOffsetX");
                if (!values.containsKey("scrollOffsetY")) throw new IllegalStateException("Missing required CDP field: scrollOffsetY");
                return new ScreencastFrameMetadata(values);
            }
        }
    }
    /**
     * Javascript dialog type.
     */
    public static final class DialogType {
        private DialogType() {}
        public static final String ALERT = "alert";
        public static final String CONFIRM = "confirm";
        public static final String PROMPT = "prompt";
        public static final String BEFOREUNLOAD = "beforeunload";
    }
    /**
     * Error while paring app manifest.
     */
    public static final class AppManifestError extends CdpObject {
        private AppManifestError(Map<String, Object> values) { super(values); }
        @Nullable public static AppManifestError fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AppManifestError(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Error message.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        /**
         * If critical, this is a non-recoverable parse error.
         * @return the protocol field value
         */
        @Nullable public Long critical() {
            return numberAsLong(value("critical"));
        }
        /**
         * Error line.
         * @return the protocol field value
         */
        @Nullable public Long line() {
            return numberAsLong(value("line"));
        }
        /**
         * Error column.
         * @return the protocol field value
         */
        @Nullable public Long column() {
            return numberAsLong(value("column"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Error message.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * If critical, this is a non-recoverable parse error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder critical(@Nullable Long value) {
                if (value == null) values.remove("critical");
                else values.put("critical", jsonValue(value));
                return this;
            }
            /**
             * Error line.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder line(@Nullable Long value) {
                if (value == null) values.remove("line");
                else values.put("line", jsonValue(value));
                return this;
            }
            /**
             * Error column.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder column(@Nullable Long value) {
                if (value == null) values.remove("column");
                else values.put("column", jsonValue(value));
                return this;
            }
            public AppManifestError build() {
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                if (!values.containsKey("critical")) throw new IllegalStateException("Missing required CDP field: critical");
                if (!values.containsKey("line")) throw new IllegalStateException("Missing required CDP field: line");
                if (!values.containsKey("column")) throw new IllegalStateException("Missing required CDP field: column");
                return new AppManifestError(values);
            }
        }
    }
    /**
     * Parsed app manifest properties.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AppManifestParsedProperties extends CdpObject {
        private AppManifestParsedProperties(Map<String, Object> values) { super(values); }
        @Nullable public static AppManifestParsedProperties fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AppManifestParsedProperties(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Computed scope value
         * @return the protocol field value
         */
        @Nullable public String scope() {
            return (String) value("scope");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Computed scope value
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scope(@Nullable String value) {
                if (value == null) values.remove("scope");
                else values.put("scope", jsonValue(value));
                return this;
            }
            public AppManifestParsedProperties build() {
                if (!values.containsKey("scope")) throw new IllegalStateException("Missing required CDP field: scope");
                return new AppManifestParsedProperties(values);
            }
        }
    }
    /**
     * Layout viewport position and dimensions.
     */
    public static final class LayoutViewport extends CdpObject {
        private LayoutViewport(Map<String, Object> values) { super(values); }
        @Nullable public static LayoutViewport fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayoutViewport(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Horizontal offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        @Nullable public Long pageX() {
            return numberAsLong(value("pageX"));
        }
        /**
         * Vertical offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        @Nullable public Long pageY() {
            return numberAsLong(value("pageY"));
        }
        /**
         * Width (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        @Nullable public Long clientWidth() {
            return numberAsLong(value("clientWidth"));
        }
        /**
         * Height (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        @Nullable public Long clientHeight() {
            return numberAsLong(value("clientHeight"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Horizontal offset relative to the document (CSS pixels).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageX(@Nullable Long value) {
                if (value == null) values.remove("pageX");
                else values.put("pageX", jsonValue(value));
                return this;
            }
            /**
             * Vertical offset relative to the document (CSS pixels).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageY(@Nullable Long value) {
                if (value == null) values.remove("pageY");
                else values.put("pageY", jsonValue(value));
                return this;
            }
            /**
             * Width (CSS pixels), excludes scrollbar if present.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientWidth(@Nullable Long value) {
                if (value == null) values.remove("clientWidth");
                else values.put("clientWidth", jsonValue(value));
                return this;
            }
            /**
             * Height (CSS pixels), excludes scrollbar if present.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientHeight(@Nullable Long value) {
                if (value == null) values.remove("clientHeight");
                else values.put("clientHeight", jsonValue(value));
                return this;
            }
            public LayoutViewport build() {
                if (!values.containsKey("pageX")) throw new IllegalStateException("Missing required CDP field: pageX");
                if (!values.containsKey("pageY")) throw new IllegalStateException("Missing required CDP field: pageY");
                if (!values.containsKey("clientWidth")) throw new IllegalStateException("Missing required CDP field: clientWidth");
                if (!values.containsKey("clientHeight")) throw new IllegalStateException("Missing required CDP field: clientHeight");
                return new LayoutViewport(values);
            }
        }
    }
    /**
     * Visual viewport position, dimensions, and scale.
     */
    public static final class VisualViewport extends CdpObject {
        private VisualViewport(Map<String, Object> values) { super(values); }
        @Nullable public static VisualViewport fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VisualViewport(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Horizontal offset relative to the layout viewport (CSS pixels).
         * @return the protocol field value
         */
        @Nullable public Double offsetX() {
            return numberAsDouble(value("offsetX"));
        }
        /**
         * Vertical offset relative to the layout viewport (CSS pixels).
         * @return the protocol field value
         */
        @Nullable public Double offsetY() {
            return numberAsDouble(value("offsetY"));
        }
        /**
         * Horizontal offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        @Nullable public Double pageX() {
            return numberAsDouble(value("pageX"));
        }
        /**
         * Vertical offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        @Nullable public Double pageY() {
            return numberAsDouble(value("pageY"));
        }
        /**
         * Width (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        @Nullable public Double clientWidth() {
            return numberAsDouble(value("clientWidth"));
        }
        /**
         * Height (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        @Nullable public Double clientHeight() {
            return numberAsDouble(value("clientHeight"));
        }
        /**
         * Scale relative to the ideal viewport (size at width=device-width).
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        /**
         * Page zoom factor (CSS to device independent pixels ratio).
         * @return the protocol field value
         */
        @Nullable public Double zoom() {
            return numberAsDouble(value("zoom"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Horizontal offset relative to the layout viewport (CSS pixels).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offsetX(@Nullable Double value) {
                if (value == null) values.remove("offsetX");
                else values.put("offsetX", jsonValue(value));
                return this;
            }
            /**
             * Vertical offset relative to the layout viewport (CSS pixels).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offsetY(@Nullable Double value) {
                if (value == null) values.remove("offsetY");
                else values.put("offsetY", jsonValue(value));
                return this;
            }
            /**
             * Horizontal offset relative to the document (CSS pixels).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageX(@Nullable Double value) {
                if (value == null) values.remove("pageX");
                else values.put("pageX", jsonValue(value));
                return this;
            }
            /**
             * Vertical offset relative to the document (CSS pixels).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageY(@Nullable Double value) {
                if (value == null) values.remove("pageY");
                else values.put("pageY", jsonValue(value));
                return this;
            }
            /**
             * Width (CSS pixels), excludes scrollbar if present.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientWidth(@Nullable Double value) {
                if (value == null) values.remove("clientWidth");
                else values.put("clientWidth", jsonValue(value));
                return this;
            }
            /**
             * Height (CSS pixels), excludes scrollbar if present.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientHeight(@Nullable Double value) {
                if (value == null) values.remove("clientHeight");
                else values.put("clientHeight", jsonValue(value));
                return this;
            }
            /**
             * Scale relative to the ideal viewport (size at width=device-width).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scale(@Nullable Double value) {
                if (value == null) values.remove("scale");
                else values.put("scale", jsonValue(value));
                return this;
            }
            /**
             * Page zoom factor (CSS to device independent pixels ratio).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder zoom(@Nullable Double value) {
                if (value == null) values.remove("zoom");
                else values.put("zoom", jsonValue(value));
                return this;
            }
            public VisualViewport build() {
                if (!values.containsKey("offsetX")) throw new IllegalStateException("Missing required CDP field: offsetX");
                if (!values.containsKey("offsetY")) throw new IllegalStateException("Missing required CDP field: offsetY");
                if (!values.containsKey("pageX")) throw new IllegalStateException("Missing required CDP field: pageX");
                if (!values.containsKey("pageY")) throw new IllegalStateException("Missing required CDP field: pageY");
                if (!values.containsKey("clientWidth")) throw new IllegalStateException("Missing required CDP field: clientWidth");
                if (!values.containsKey("clientHeight")) throw new IllegalStateException("Missing required CDP field: clientHeight");
                if (!values.containsKey("scale")) throw new IllegalStateException("Missing required CDP field: scale");
                return new VisualViewport(values);
            }
        }
    }
    /**
     * Viewport for capturing screenshot.
     */
    public static final class Viewport extends CdpObject {
        private Viewport(Map<String, Object> values) { super(values); }
        @Nullable public static Viewport fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Viewport(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X offset in device independent pixels (dip).
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y offset in device independent pixels (dip).
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Rectangle width in device independent pixels (dip).
         * @return the protocol field value
         */
        @Nullable public Double width() {
            return numberAsDouble(value("width"));
        }
        /**
         * Rectangle height in device independent pixels (dip).
         * @return the protocol field value
         */
        @Nullable public Double height() {
            return numberAsDouble(value("height"));
        }
        /**
         * Page scale factor.
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X offset in device independent pixels (dip).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y offset in device independent pixels (dip).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Rectangle width in device independent pixels (dip).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Double value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Rectangle height in device independent pixels (dip).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Double value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Page scale factor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scale(@Nullable Double value) {
                if (value == null) values.remove("scale");
                else values.put("scale", jsonValue(value));
                return this;
            }
            public Viewport build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                if (!values.containsKey("scale")) throw new IllegalStateException("Missing required CDP field: scale");
                return new Viewport(values);
            }
        }
    }
    /**
     * Generic font families collection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FontFamilies extends CdpObject {
        private FontFamilies(Map<String, Object> values) { super(values); }
        @Nullable public static FontFamilies fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FontFamilies(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The standard font-family.
         * @return the protocol field value
         */
        @Nullable public String standard() {
            return (String) value("standard");
        }
        /**
         * The fixed font-family.
         * @return the protocol field value
         */
        @Nullable public String fixed() {
            return (String) value("fixed");
        }
        /**
         * The serif font-family.
         * @return the protocol field value
         */
        @Nullable public String serif() {
            return (String) value("serif");
        }
        /**
         * The sansSerif font-family.
         * @return the protocol field value
         */
        @Nullable public String sansSerif() {
            return (String) value("sansSerif");
        }
        /**
         * The cursive font-family.
         * @return the protocol field value
         */
        @Nullable public String cursive() {
            return (String) value("cursive");
        }
        /**
         * The fantasy font-family.
         * @return the protocol field value
         */
        @Nullable public String fantasy() {
            return (String) value("fantasy");
        }
        /**
         * The math font-family.
         * @return the protocol field value
         */
        @Nullable public String math() {
            return (String) value("math");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The standard font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder standard(@Nullable String value) {
                if (value == null) values.remove("standard");
                else values.put("standard", jsonValue(value));
                return this;
            }
            /**
             * The fixed font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fixed(@Nullable String value) {
                if (value == null) values.remove("fixed");
                else values.put("fixed", jsonValue(value));
                return this;
            }
            /**
             * The serif font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serif(@Nullable String value) {
                if (value == null) values.remove("serif");
                else values.put("serif", jsonValue(value));
                return this;
            }
            /**
             * The sansSerif font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sansSerif(@Nullable String value) {
                if (value == null) values.remove("sansSerif");
                else values.put("sansSerif", jsonValue(value));
                return this;
            }
            /**
             * The cursive font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cursive(@Nullable String value) {
                if (value == null) values.remove("cursive");
                else values.put("cursive", jsonValue(value));
                return this;
            }
            /**
             * The fantasy font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fantasy(@Nullable String value) {
                if (value == null) values.remove("fantasy");
                else values.put("fantasy", jsonValue(value));
                return this;
            }
            /**
             * The math font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder math(@Nullable String value) {
                if (value == null) values.remove("math");
                else values.put("math", jsonValue(value));
                return this;
            }
            public FontFamilies build() {
                return new FontFamilies(values);
            }
        }
    }
    /**
     * Font families collection for a script.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScriptFontFamilies extends CdpObject {
        private ScriptFontFamilies(Map<String, Object> values) { super(values); }
        @Nullable public static ScriptFontFamilies fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScriptFontFamilies(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of the script which these font families are defined for.
         * @return the protocol field value
         */
        @Nullable public String script() {
            return (String) value("script");
        }
        /**
         * Generic font families collection for the script.
         * @return the protocol field value
         */
        @Nullable public Page.FontFamilies fontFamilies() {
            return Page.FontFamilies.fromMap(objectMap(value("fontFamilies")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of the script which these font families are defined for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder script(@Nullable String value) {
                if (value == null) values.remove("script");
                else values.put("script", jsonValue(value));
                return this;
            }
            /**
             * Generic font families collection for the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontFamilies(@Nullable Page.FontFamilies value) {
                if (value == null) values.remove("fontFamilies");
                else values.put("fontFamilies", jsonValue(value));
                return this;
            }
            public ScriptFontFamilies build() {
                if (!values.containsKey("script")) throw new IllegalStateException("Missing required CDP field: script");
                if (!values.containsKey("fontFamilies")) throw new IllegalStateException("Missing required CDP field: fontFamilies");
                return new ScriptFontFamilies(values);
            }
        }
    }
    /**
     * Default font sizes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FontSizes extends CdpObject {
        private FontSizes(Map<String, Object> values) { super(values); }
        @Nullable public static FontSizes fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FontSizes(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Default standard font size.
         * @return the protocol field value
         */
        @Nullable public Long standard() {
            return numberAsLong(value("standard"));
        }
        /**
         * Default fixed font size.
         * @return the protocol field value
         */
        @Nullable public Long fixed() {
            return numberAsLong(value("fixed"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Default standard font size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder standard(@Nullable Long value) {
                if (value == null) values.remove("standard");
                else values.put("standard", jsonValue(value));
                return this;
            }
            /**
             * Default fixed font size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fixed(@Nullable Long value) {
                if (value == null) values.remove("fixed");
                else values.put("fixed", jsonValue(value));
                return this;
            }
            public FontSizes build() {
                return new FontSizes(values);
            }
        }
    }
    /**
     * Wire values for ClientNavigationReason.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClientNavigationReason {
        private ClientNavigationReason() {}
        public static final String ANCHORCLICK = "anchorClick";
        public static final String FORMSUBMISSIONGET = "formSubmissionGet";
        public static final String FORMSUBMISSIONPOST = "formSubmissionPost";
        public static final String HTTPHEADERREFRESH = "httpHeaderRefresh";
        public static final String INITIALFRAMENAVIGATION = "initialFrameNavigation";
        public static final String METATAGREFRESH = "metaTagRefresh";
        public static final String OTHER = "other";
        public static final String PAGEBLOCKINTERSTITIAL = "pageBlockInterstitial";
        public static final String RELOAD = "reload";
        public static final String SCRIPTINITIATED = "scriptInitiated";
    }
    /**
     * Wire values for ClientNavigationDisposition.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClientNavigationDisposition {
        private ClientNavigationDisposition() {}
        public static final String CURRENTTAB = "currentTab";
        public static final String NEWTAB = "newTab";
        public static final String NEWWINDOW = "newWindow";
        public static final String DOWNLOAD = "download";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InstallabilityErrorArgument extends CdpObject {
        private InstallabilityErrorArgument(Map<String, Object> values) { super(values); }
        @Nullable public static InstallabilityErrorArgument fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InstallabilityErrorArgument(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Argument name (e.g. name:&#x27;minimum-icon-size-in-pixels&#x27;).
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Argument value (e.g. value:&#x27;64&#x27;).
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Argument name (e.g. name:&#x27;minimum-icon-size-in-pixels&#x27;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Argument value (e.g. value:&#x27;64&#x27;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public InstallabilityErrorArgument build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new InstallabilityErrorArgument(values);
            }
        }
    }
    /**
     * The installability error
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InstallabilityError extends CdpObject {
        private InstallabilityError(Map<String, Object> values) { super(values); }
        @Nullable public static InstallabilityError fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InstallabilityError(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The error id (e.g. &#x27;manifest-missing-suitable-icon&#x27;).
         * @return the protocol field value
         */
        @Nullable public String errorId() {
            return (String) value("errorId");
        }
        /**
         * The list of error arguments (e.g. {name:&#x27;minimum-icon-size-in-pixels&#x27;, value:&#x27;64&#x27;}).
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.InstallabilityErrorArgument> errorArguments() {
            return list(value("errorArguments"), element0 -> Page.InstallabilityErrorArgument.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The error id (e.g. &#x27;manifest-missing-suitable-icon&#x27;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorId(@Nullable String value) {
                if (value == null) values.remove("errorId");
                else values.put("errorId", jsonValue(value));
                return this;
            }
            /**
             * The list of error arguments (e.g. {name:&#x27;minimum-icon-size-in-pixels&#x27;, value:&#x27;64&#x27;}).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorArguments(@Nullable java.util.List<Page.InstallabilityErrorArgument> value) {
                if (value == null) values.remove("errorArguments");
                else values.put("errorArguments", jsonValue(value));
                return this;
            }
            public InstallabilityError build() {
                if (!values.containsKey("errorId")) throw new IllegalStateException("Missing required CDP field: errorId");
                if (!values.containsKey("errorArguments")) throw new IllegalStateException("Missing required CDP field: errorArguments");
                return new InstallabilityError(values);
            }
        }
    }
    /**
     * The referring-policy used for the navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReferrerPolicy {
        private ReferrerPolicy() {}
        public static final String NOREFERRER = "noReferrer";
        public static final String NOREFERRERWHENDOWNGRADE = "noReferrerWhenDowngrade";
        public static final String ORIGIN = "origin";
        public static final String ORIGINWHENCROSSORIGIN = "originWhenCrossOrigin";
        public static final String SAMEORIGIN = "sameOrigin";
        public static final String STRICTORIGIN = "strictOrigin";
        public static final String STRICTORIGINWHENCROSSORIGIN = "strictOriginWhenCrossOrigin";
        public static final String UNSAFEURL = "unsafeUrl";
    }
    /**
     * Per-script compilation cache parameters for {@code Page.produceCompilationCache}
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CompilationCacheParams extends CdpObject {
        private CompilationCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static CompilationCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CompilationCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The URL of the script to produce a compilation cache entry for.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * A hint to the backend whether eager compilation is recommended. (the actual compilation mode used is upon backend discretion).
         * @return the protocol field value
         */
        @Nullable public Boolean eager() {
            return (Boolean) value("eager");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The URL of the script to produce a compilation cache entry for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * A hint to the backend whether eager compilation is recommended. (the actual compilation mode used is upon backend discretion).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eager(@Nullable Boolean value) {
                if (value == null) values.remove("eager");
                else values.put("eager", jsonValue(value));
                return this;
            }
            public CompilationCacheParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new CompilationCacheParams(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FileFilter extends CdpObject {
        private FileFilter(Map<String, Object> values) { super(values); }
        @Nullable public static FileFilter fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FileFilter(values);
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
         * Returns the accepts field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> accepts() {
            return list(value("accepts"), element0 -> (String) element0);
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
             * Sets the accepts field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accepts(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("accepts");
                else values.put("accepts", jsonValue(value));
                return this;
            }
            public FileFilter build() {
                return new FileFilter(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FileHandler extends CdpObject {
        private FileHandler(Map<String, Object> values) { super(values); }
        @Nullable public static FileHandler fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FileHandler(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        @Nullable public String action() {
            return (String) value("action");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the icons field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.ImageResource> icons() {
            return list(value("icons"), element0 -> Page.ImageResource.fromMap(objectMap(element0)));
        }
        /**
         * Mimic a map, name is the key, accepts is the value.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.FileFilter> accepts() {
            return list(value("accepts"), element0 -> Page.FileFilter.fromMap(objectMap(element0)));
        }
        /**
         * Won&#x27;t repeat the enums, using string for easy comparison. Same as the other enums below.
         * @return the protocol field value
         */
        @Nullable public String launchType() {
            return (String) value("launchType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the action field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder action(@Nullable String value) {
                if (value == null) values.remove("action");
                else values.put("action", jsonValue(value));
                return this;
            }
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
             * Sets the icons field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder icons(@Nullable java.util.List<Page.ImageResource> value) {
                if (value == null) values.remove("icons");
                else values.put("icons", jsonValue(value));
                return this;
            }
            /**
             * Mimic a map, name is the key, accepts is the value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accepts(@Nullable java.util.List<Page.FileFilter> value) {
                if (value == null) values.remove("accepts");
                else values.put("accepts", jsonValue(value));
                return this;
            }
            /**
             * Won&#x27;t repeat the enums, using string for easy comparison. Same as the other enums below.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder launchType(@Nullable String value) {
                if (value == null) values.remove("launchType");
                else values.put("launchType", jsonValue(value));
                return this;
            }
            public FileHandler build() {
                if (!values.containsKey("action")) throw new IllegalStateException("Missing required CDP field: action");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("launchType")) throw new IllegalStateException("Missing required CDP field: launchType");
                return new FileHandler(values);
            }
        }
    }
    /**
     * The image definition used in both icon and screenshot.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ImageResource extends CdpObject {
        private ImageResource(Map<String, Object> values) { super(values); }
        @Nullable public static ImageResource fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ImageResource(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The src field in the definition, but changing to url in favor of consistency.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the sizes field.
         * @return the protocol field value
         */
        @Nullable public String sizes() {
            return (String) value("sizes");
        }
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
             * The src field in the definition, but changing to url in favor of consistency.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the sizes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sizes(@Nullable String value) {
                if (value == null) values.remove("sizes");
                else values.put("sizes", jsonValue(value));
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
            public ImageResource build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new ImageResource(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LaunchHandler extends CdpObject {
        private LaunchHandler(Map<String, Object> values) { super(values); }
        @Nullable public static LaunchHandler fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LaunchHandler(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the clientMode field.
         * @return the protocol field value
         */
        @Nullable public String clientMode() {
            return (String) value("clientMode");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the clientMode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientMode(@Nullable String value) {
                if (value == null) values.remove("clientMode");
                else values.put("clientMode", jsonValue(value));
                return this;
            }
            public LaunchHandler build() {
                if (!values.containsKey("clientMode")) throw new IllegalStateException("Missing required CDP field: clientMode");
                return new LaunchHandler(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ProtocolHandler extends CdpObject {
        private ProtocolHandler(Map<String, Object> values) { super(values); }
        @Nullable public static ProtocolHandler fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProtocolHandler(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the protocol field.
         * @return the protocol field value
         */
        @Nullable public String protocol() {
            return (String) value("protocol");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the protocol field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocol(@Nullable String value) {
                if (value == null) values.remove("protocol");
                else values.put("protocol", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public ProtocolHandler build() {
                if (!values.containsKey("protocol")) throw new IllegalStateException("Missing required CDP field: protocol");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new ProtocolHandler(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RelatedApplication extends CdpObject {
        private RelatedApplication(Map<String, Object> values) { super(values); }
        @Nullable public static RelatedApplication fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RelatedApplication(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public RelatedApplication build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new RelatedApplication(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScopeExtension extends CdpObject {
        private ScopeExtension(Map<String, Object> values) { super(values); }
        @Nullable public static ScopeExtension fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScopeExtension(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Instead of using tuple, this field always returns the serialized string for easy understanding and comparison.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Returns the hasOriginWildcard field.
         * @return the protocol field value
         */
        @Nullable public Boolean hasOriginWildcard() {
            return (Boolean) value("hasOriginWildcard");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Instead of using tuple, this field always returns the serialized string for easy understanding and comparison.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Sets the hasOriginWildcard field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasOriginWildcard(@Nullable Boolean value) {
                if (value == null) values.remove("hasOriginWildcard");
                else values.put("hasOriginWildcard", jsonValue(value));
                return this;
            }
            public ScopeExtension build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("hasOriginWildcard")) throw new IllegalStateException("Missing required CDP field: hasOriginWildcard");
                return new ScopeExtension(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Screenshot extends CdpObject {
        private Screenshot(Map<String, Object> values) { super(values); }
        @Nullable public static Screenshot fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Screenshot(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the image field.
         * @return the protocol field value
         */
        @Nullable public Page.ImageResource image() {
            return Page.ImageResource.fromMap(objectMap(value("image")));
        }
        /**
         * Returns the formFactor field.
         * @return the protocol field value
         */
        @Nullable public String formFactor() {
            return (String) value("formFactor");
        }
        /**
         * Returns the label field.
         * @return the protocol field value
         */
        @Nullable public String label() {
            return (String) value("label");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the image field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder image(@Nullable Page.ImageResource value) {
                if (value == null) values.remove("image");
                else values.put("image", jsonValue(value));
                return this;
            }
            /**
             * Sets the formFactor field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder formFactor(@Nullable String value) {
                if (value == null) values.remove("formFactor");
                else values.put("formFactor", jsonValue(value));
                return this;
            }
            /**
             * Sets the label field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder label(@Nullable String value) {
                if (value == null) values.remove("label");
                else values.put("label", jsonValue(value));
                return this;
            }
            public Screenshot build() {
                if (!values.containsKey("image")) throw new IllegalStateException("Missing required CDP field: image");
                if (!values.containsKey("formFactor")) throw new IllegalStateException("Missing required CDP field: formFactor");
                return new Screenshot(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ShareTarget extends CdpObject {
        private ShareTarget(Map<String, Object> values) { super(values); }
        @Nullable public static ShareTarget fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ShareTarget(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        @Nullable public String action() {
            return (String) value("action");
        }
        /**
         * Returns the method field.
         * @return the protocol field value
         */
        @Nullable public String method() {
            return (String) value("method");
        }
        /**
         * Returns the enctype field.
         * @return the protocol field value
         */
        @Nullable public String enctype() {
            return (String) value("enctype");
        }
        /**
         * Embed the ShareTargetParams
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the files field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.FileFilter> files() {
            return list(value("files"), element0 -> Page.FileFilter.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the action field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder action(@Nullable String value) {
                if (value == null) values.remove("action");
                else values.put("action", jsonValue(value));
                return this;
            }
            /**
             * Sets the method field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder method(@Nullable String value) {
                if (value == null) values.remove("method");
                else values.put("method", jsonValue(value));
                return this;
            }
            /**
             * Sets the enctype field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enctype(@Nullable String value) {
                if (value == null) values.remove("enctype");
                else values.put("enctype", jsonValue(value));
                return this;
            }
            /**
             * Embed the ShareTargetParams
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the files field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder files(@Nullable java.util.List<Page.FileFilter> value) {
                if (value == null) values.remove("files");
                else values.put("files", jsonValue(value));
                return this;
            }
            public ShareTarget build() {
                if (!values.containsKey("action")) throw new IllegalStateException("Missing required CDP field: action");
                if (!values.containsKey("method")) throw new IllegalStateException("Missing required CDP field: method");
                if (!values.containsKey("enctype")) throw new IllegalStateException("Missing required CDP field: enctype");
                return new ShareTarget(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Shortcut extends CdpObject {
        private Shortcut(Map<String, Object> values) { super(values); }
        @Nullable public static Shortcut fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Shortcut(values);
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
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
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
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public Shortcut build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new Shortcut(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WebAppManifest extends CdpObject {
        private WebAppManifest(Map<String, Object> values) { super(values); }
        @Nullable public static WebAppManifest fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebAppManifest(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the backgroundColor field.
         * @return the protocol field value
         */
        @Nullable public String backgroundColor() {
            return (String) value("backgroundColor");
        }
        /**
         * The extra description provided by the manifest.
         * @return the protocol field value
         */
        @Nullable public String description() {
            return (String) value("description");
        }
        /**
         * Returns the dir field.
         * @return the protocol field value
         */
        @Nullable public String dir() {
            return (String) value("dir");
        }
        /**
         * Returns the display field.
         * @return the protocol field value
         */
        @Nullable public String display() {
            return (String) value("display");
        }
        /**
         * The overrided display mode controlled by the user.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> displayOverrides() {
            return list(value("displayOverrides"), element0 -> (String) element0);
        }
        /**
         * The handlers to open files.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.FileHandler> fileHandlers() {
            return list(value("fileHandlers"), element0 -> Page.FileHandler.fromMap(objectMap(element0)));
        }
        /**
         * Returns the icons field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.ImageResource> icons() {
            return list(value("icons"), element0 -> Page.ImageResource.fromMap(objectMap(element0)));
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Returns the lang field.
         * @return the protocol field value
         */
        @Nullable public String lang() {
            return (String) value("lang");
        }
        /**
         * TODO(crbug.com/1231886): This field is non-standard and part of a Chrome experiment. See: https://github.com/WICG/web-app-launch/blob/main/launch_handler.md
         * @return the protocol field value
         */
        @Nullable public Page.LaunchHandler launchHandler() {
            return Page.LaunchHandler.fromMap(objectMap(value("launchHandler")));
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the orientation field.
         * @return the protocol field value
         */
        @Nullable public String orientation() {
            return (String) value("orientation");
        }
        /**
         * Returns the preferRelatedApplications field.
         * @return the protocol field value
         */
        @Nullable public Boolean preferRelatedApplications() {
            return (Boolean) value("preferRelatedApplications");
        }
        /**
         * The handlers to open protocols.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.ProtocolHandler> protocolHandlers() {
            return list(value("protocolHandlers"), element0 -> Page.ProtocolHandler.fromMap(objectMap(element0)));
        }
        /**
         * Returns the relatedApplications field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.RelatedApplication> relatedApplications() {
            return list(value("relatedApplications"), element0 -> Page.RelatedApplication.fromMap(objectMap(element0)));
        }
        /**
         * Returns the scope field.
         * @return the protocol field value
         */
        @Nullable public String scope() {
            return (String) value("scope");
        }
        /**
         * Non-standard, see https://github.com/WICG/manifest-incubations/blob/gh-pages/scope_extensions-explainer.md
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.ScopeExtension> scopeExtensions() {
            return list(value("scopeExtensions"), element0 -> Page.ScopeExtension.fromMap(objectMap(element0)));
        }
        /**
         * The screenshots used by chromium.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.Screenshot> screenshots() {
            return list(value("screenshots"), element0 -> Page.Screenshot.fromMap(objectMap(element0)));
        }
        /**
         * Returns the shareTarget field.
         * @return the protocol field value
         */
        @Nullable public Page.ShareTarget shareTarget() {
            return Page.ShareTarget.fromMap(objectMap(value("shareTarget")));
        }
        /**
         * Returns the shortName field.
         * @return the protocol field value
         */
        @Nullable public String shortName() {
            return (String) value("shortName");
        }
        /**
         * Returns the shortcuts field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.Shortcut> shortcuts() {
            return list(value("shortcuts"), element0 -> Page.Shortcut.fromMap(objectMap(element0)));
        }
        /**
         * Returns the startUrl field.
         * @return the protocol field value
         */
        @Nullable public String startUrl() {
            return (String) value("startUrl");
        }
        /**
         * Returns the themeColor field.
         * @return the protocol field value
         */
        @Nullable public String themeColor() {
            return (String) value("themeColor");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the backgroundColor field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backgroundColor(@Nullable String value) {
                if (value == null) values.remove("backgroundColor");
                else values.put("backgroundColor", jsonValue(value));
                return this;
            }
            /**
             * The extra description provided by the manifest.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable String value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * Sets the dir field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dir(@Nullable String value) {
                if (value == null) values.remove("dir");
                else values.put("dir", jsonValue(value));
                return this;
            }
            /**
             * Sets the display field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder display(@Nullable String value) {
                if (value == null) values.remove("display");
                else values.put("display", jsonValue(value));
                return this;
            }
            /**
             * The overrided display mode controlled by the user.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayOverrides(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("displayOverrides");
                else values.put("displayOverrides", jsonValue(value));
                return this;
            }
            /**
             * The handlers to open files.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fileHandlers(@Nullable java.util.List<Page.FileHandler> value) {
                if (value == null) values.remove("fileHandlers");
                else values.put("fileHandlers", jsonValue(value));
                return this;
            }
            /**
             * Sets the icons field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder icons(@Nullable java.util.List<Page.ImageResource> value) {
                if (value == null) values.remove("icons");
                else values.put("icons", jsonValue(value));
                return this;
            }
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Sets the lang field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lang(@Nullable String value) {
                if (value == null) values.remove("lang");
                else values.put("lang", jsonValue(value));
                return this;
            }
            /**
             * TODO(crbug.com/1231886): This field is non-standard and part of a Chrome experiment. See: https://github.com/WICG/web-app-launch/blob/main/launch_handler.md
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder launchHandler(@Nullable Page.LaunchHandler value) {
                if (value == null) values.remove("launchHandler");
                else values.put("launchHandler", jsonValue(value));
                return this;
            }
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
             * Sets the orientation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder orientation(@Nullable String value) {
                if (value == null) values.remove("orientation");
                else values.put("orientation", jsonValue(value));
                return this;
            }
            /**
             * Sets the preferRelatedApplications field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder preferRelatedApplications(@Nullable Boolean value) {
                if (value == null) values.remove("preferRelatedApplications");
                else values.put("preferRelatedApplications", jsonValue(value));
                return this;
            }
            /**
             * The handlers to open protocols.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocolHandlers(@Nullable java.util.List<Page.ProtocolHandler> value) {
                if (value == null) values.remove("protocolHandlers");
                else values.put("protocolHandlers", jsonValue(value));
                return this;
            }
            /**
             * Sets the relatedApplications field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder relatedApplications(@Nullable java.util.List<Page.RelatedApplication> value) {
                if (value == null) values.remove("relatedApplications");
                else values.put("relatedApplications", jsonValue(value));
                return this;
            }
            /**
             * Sets the scope field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scope(@Nullable String value) {
                if (value == null) values.remove("scope");
                else values.put("scope", jsonValue(value));
                return this;
            }
            /**
             * Non-standard, see https://github.com/WICG/manifest-incubations/blob/gh-pages/scope_extensions-explainer.md
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeExtensions(@Nullable java.util.List<Page.ScopeExtension> value) {
                if (value == null) values.remove("scopeExtensions");
                else values.put("scopeExtensions", jsonValue(value));
                return this;
            }
            /**
             * The screenshots used by chromium.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder screenshots(@Nullable java.util.List<Page.Screenshot> value) {
                if (value == null) values.remove("screenshots");
                else values.put("screenshots", jsonValue(value));
                return this;
            }
            /**
             * Sets the shareTarget field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shareTarget(@Nullable Page.ShareTarget value) {
                if (value == null) values.remove("shareTarget");
                else values.put("shareTarget", jsonValue(value));
                return this;
            }
            /**
             * Sets the shortName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shortName(@Nullable String value) {
                if (value == null) values.remove("shortName");
                else values.put("shortName", jsonValue(value));
                return this;
            }
            /**
             * Sets the shortcuts field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shortcuts(@Nullable java.util.List<Page.Shortcut> value) {
                if (value == null) values.remove("shortcuts");
                else values.put("shortcuts", jsonValue(value));
                return this;
            }
            /**
             * Sets the startUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startUrl(@Nullable String value) {
                if (value == null) values.remove("startUrl");
                else values.put("startUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the themeColor field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder themeColor(@Nullable String value) {
                if (value == null) values.remove("themeColor");
                else values.put("themeColor", jsonValue(value));
                return this;
            }
            public WebAppManifest build() {
                return new WebAppManifest(values);
            }
        }
    }
    /**
     * The type of a frameNavigated event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NavigationType {
        private NavigationType() {}
        public static final String NAVIGATION = "Navigation";
        public static final String BACKFORWARDCACHERESTORE = "BackForwardCacheRestore";
    }
    /**
     * List of not restored reasons for back-forward cache.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotRestoredReason {
        private BackForwardCacheNotRestoredReason() {}
        public static final String NOTPRIMARYMAINFRAME = "NotPrimaryMainFrame";
        public static final String BACKFORWARDCACHEDISABLED = "BackForwardCacheDisabled";
        public static final String RELATEDACTIVECONTENTSEXIST = "RelatedActiveContentsExist";
        public static final String HTTPSTATUSNOTOK = "HTTPStatusNotOK";
        public static final String SCHEMENOTHTTPORHTTPS = "SchemeNotHTTPOrHTTPS";
        public static final String LOADING = "Loading";
        public static final String WASGRANTEDMEDIAACCESS = "WasGrantedMediaAccess";
        public static final String DISABLEFORRENDERFRAMEHOSTCALLED = "DisableForRenderFrameHostCalled";
        public static final String DOMAINNOTALLOWED = "DomainNotAllowed";
        public static final String HTTPMETHODNOTGET = "HTTPMethodNotGET";
        public static final String SUBFRAMEISNAVIGATING = "SubframeIsNavigating";
        public static final String TIMEOUT = "Timeout";
        public static final String CACHELIMIT = "CacheLimit";
        public static final String JAVASCRIPTEXECUTION = "JavaScriptExecution";
        public static final String RENDERERPROCESSKILLED = "RendererProcessKilled";
        public static final String RENDERERPROCESSCRASHED = "RendererProcessCrashed";
        public static final String SCHEDULERTRACKEDFEATUREUSED = "SchedulerTrackedFeatureUsed";
        public static final String CONFLICTINGBROWSINGINSTANCE = "ConflictingBrowsingInstance";
        public static final String CACHEFLUSHED = "CacheFlushed";
        public static final String SERVICEWORKERVERSIONACTIVATION = "ServiceWorkerVersionActivation";
        public static final String SESSIONRESTORED = "SessionRestored";
        public static final String SERVICEWORKERPOSTMESSAGE = "ServiceWorkerPostMessage";
        public static final String ENTEREDBACKFORWARDCACHEBEFORESERVICEWORKERHOSTADDED = "EnteredBackForwardCacheBeforeServiceWorkerHostAdded";
        public static final String RENDERFRAMEHOSTREUSED_SAMESITE = "RenderFrameHostReused_SameSite";
        public static final String RENDERFRAMEHOSTREUSED_CROSSSITE = "RenderFrameHostReused_CrossSite";
        public static final String SERVICEWORKERCLAIM = "ServiceWorkerClaim";
        public static final String IGNOREEVENTANDEVICT = "IgnoreEventAndEvict";
        public static final String HAVEINNERCONTENTS = "HaveInnerContents";
        public static final String TIMEOUTPUTTINGINCACHE = "TimeoutPuttingInCache";
        public static final String BACKFORWARDCACHEDISABLEDBYLOWMEMORY = "BackForwardCacheDisabledByLowMemory";
        public static final String BACKFORWARDCACHEDISABLEDBYCOMMANDLINE = "BackForwardCacheDisabledByCommandLine";
        public static final String NETWORKREQUESTDATAPIPEDRAINEDASBYTESCONSUMER = "NetworkRequestDatapipeDrainedAsBytesConsumer";
        public static final String NETWORKREQUESTREDIRECTED = "NetworkRequestRedirected";
        public static final String NETWORKREQUESTTIMEOUT = "NetworkRequestTimeout";
        public static final String NETWORKEXCEEDSBUFFERLIMIT = "NetworkExceedsBufferLimit";
        public static final String NAVIGATIONCANCELLEDWHILERESTORING = "NavigationCancelledWhileRestoring";
        public static final String NOTMOSTRECENTNAVIGATIONENTRY = "NotMostRecentNavigationEntry";
        public static final String BACKFORWARDCACHEDISABLEDFORPRERENDER = "BackForwardCacheDisabledForPrerender";
        public static final String USERAGENTOVERRIDEDIFFERS = "UserAgentOverrideDiffers";
        public static final String FOREGROUNDCACHELIMIT = "ForegroundCacheLimit";
        public static final String FORWARDCACHEDISABLED = "ForwardCacheDisabled";
        public static final String BROWSINGINSTANCENOTSWAPPED = "BrowsingInstanceNotSwapped";
        public static final String BACKFORWARDCACHEDISABLEDFORDELEGATE = "BackForwardCacheDisabledForDelegate";
        public static final String UNLOADHANDLEREXISTSINMAINFRAME = "UnloadHandlerExistsInMainFrame";
        public static final String UNLOADHANDLEREXISTSINSUBFRAME = "UnloadHandlerExistsInSubFrame";
        public static final String SERVICEWORKERUNREGISTRATION = "ServiceWorkerUnregistration";
        public static final String CACHECONTROLNOSTORE = "CacheControlNoStore";
        public static final String CACHECONTROLNOSTORECOOKIEMODIFIED = "CacheControlNoStoreCookieModified";
        public static final String CACHECONTROLNOSTOREHTTPONLYCOOKIEMODIFIED = "CacheControlNoStoreHTTPOnlyCookieModified";
        public static final String NORESPONSEHEAD = "NoResponseHead";
        public static final String UNKNOWN = "Unknown";
        public static final String ACTIVATIONNAVIGATIONSDISALLOWEDFORBUG1234857 = "ActivationNavigationsDisallowedForBug1234857";
        public static final String ERRORDOCUMENT = "ErrorDocument";
        public static final String FENCEDFRAMESEMBEDDER = "FencedFramesEmbedder";
        public static final String COOKIEDISABLED = "CookieDisabled";
        public static final String HTTPAUTHREQUIRED = "HTTPAuthRequired";
        public static final String COOKIEFLUSHED = "CookieFlushed";
        public static final String BROADCASTCHANNELONMESSAGE = "BroadcastChannelOnMessage";
        public static final String WEBVIEWSETTINGSCHANGED = "WebViewSettingsChanged";
        public static final String WEBVIEWJAVASCRIPTOBJECTCHANGED = "WebViewJavaScriptObjectChanged";
        public static final String WEBVIEWMESSAGELISTENERINJECTED = "WebViewMessageListenerInjected";
        public static final String WEBVIEWSAFEBROWSINGALLOWLISTCHANGED = "WebViewSafeBrowsingAllowlistChanged";
        public static final String WEBVIEWDOCUMENTSTARTJAVASCRIPTCHANGED = "WebViewDocumentStartJavascriptChanged";
        public static final String WEBSOCKET = "WebSocket";
        public static final String WEBTRANSPORT = "WebTransport";
        public static final String WEBRTC = "WebRTC";
        public static final String MAINRESOURCEHASCACHECONTROLNOSTORE = "MainResourceHasCacheControlNoStore";
        public static final String MAINRESOURCEHASCACHECONTROLNOCACHE = "MainResourceHasCacheControlNoCache";
        public static final String SUBRESOURCEHASCACHECONTROLNOSTORE = "SubresourceHasCacheControlNoStore";
        public static final String SUBRESOURCEHASCACHECONTROLNOCACHE = "SubresourceHasCacheControlNoCache";
        public static final String CONTAINSPLUGINS = "ContainsPlugins";
        public static final String DOCUMENTLOADED = "DocumentLoaded";
        public static final String OUTSTANDINGNETWORKREQUESTOTHERS = "OutstandingNetworkRequestOthers";
        public static final String REQUESTEDMIDIPERMISSION = "RequestedMIDIPermission";
        public static final String REQUESTEDAUDIOCAPTUREPERMISSION = "RequestedAudioCapturePermission";
        public static final String REQUESTEDVIDEOCAPTUREPERMISSION = "RequestedVideoCapturePermission";
        public static final String REQUESTEDBACKFORWARDCACHEBLOCKEDSENSORS = "RequestedBackForwardCacheBlockedSensors";
        public static final String REQUESTEDBACKGROUNDWORKPERMISSION = "RequestedBackgroundWorkPermission";
        public static final String BROADCASTCHANNEL = "BroadcastChannel";
        public static final String WEBXR = "WebXR";
        public static final String SHAREDWORKER = "SharedWorker";
        public static final String SHAREDWORKERMESSAGE = "SharedWorkerMessage";
        public static final String SHAREDWORKERWITHNOACTIVECLIENT = "SharedWorkerWithNoActiveClient";
        public static final String WEBLOCKS = "WebLocks";
        public static final String WEBLOCKSCONTENTION = "WebLocksContention";
        public static final String WEBHID = "WebHID";
        public static final String WEBBLUETOOTH = "WebBluetooth";
        public static final String WEBSHARE = "WebShare";
        public static final String REQUESTEDSTORAGEACCESSGRANT = "RequestedStorageAccessGrant";
        public static final String WEBNFC = "WebNfc";
        public static final String OUTSTANDINGNETWORKREQUESTFETCH = "OutstandingNetworkRequestFetch";
        public static final String OUTSTANDINGNETWORKREQUESTXHR = "OutstandingNetworkRequestXHR";
        public static final String APPBANNER = "AppBanner";
        public static final String PRINTING = "Printing";
        public static final String WEBDATABASE = "WebDatabase";
        public static final String PICTUREINPICTURE = "PictureInPicture";
        public static final String SPEECHRECOGNIZER = "SpeechRecognizer";
        public static final String IDLEMANAGER = "IdleManager";
        public static final String PAYMENTMANAGER = "PaymentManager";
        public static final String SPEECHSYNTHESIS = "SpeechSynthesis";
        public static final String KEYBOARDLOCK = "KeyboardLock";
        public static final String WEBOTPSERVICE = "WebOTPService";
        public static final String OUTSTANDINGNETWORKREQUESTDIRECTSOCKET = "OutstandingNetworkRequestDirectSocket";
        public static final String INJECTEDJAVASCRIPT = "InjectedJavascript";
        public static final String INJECTEDSTYLESHEET = "InjectedStyleSheet";
        public static final String KEEPALIVEREQUEST = "KeepaliveRequest";
        public static final String INDEXEDDBEVENT = "IndexedDBEvent";
        public static final String DUMMY = "Dummy";
        public static final String JSNETWORKREQUESTRECEIVEDCACHECONTROLNOSTORERESOURCE = "JsNetworkRequestReceivedCacheControlNoStoreResource";
        public static final String WEBRTCUSEDWITHCCNS = "WebRTCUsedWithCCNS";
        public static final String WEBTRANSPORTUSEDWITHCCNS = "WebTransportUsedWithCCNS";
        public static final String WEBSOCKETUSEDWITHCCNS = "WebSocketUsedWithCCNS";
        public static final String SMARTCARD = "SmartCard";
        public static final String LIVEMEDIASTREAMTRACK = "LiveMediaStreamTrack";
        public static final String UNLOADHANDLER = "UnloadHandler";
        public static final String PARSERABORTED = "ParserAborted";
        public static final String CONTENTSECURITYHANDLER = "ContentSecurityHandler";
        public static final String CONTENTWEBAUTHENTICATIONAPI = "ContentWebAuthenticationAPI";
        public static final String CONTENTFILECHOOSER = "ContentFileChooser";
        public static final String CONTENTSERIAL = "ContentSerial";
        public static final String CONTENTFILESYSTEMACCESS = "ContentFileSystemAccess";
        public static final String CONTENTMEDIADEVICESDISPATCHERHOST = "ContentMediaDevicesDispatcherHost";
        public static final String CONTENTWEBBLUETOOTH = "ContentWebBluetooth";
        public static final String CONTENTWEBUSB = "ContentWebUSB";
        public static final String CONTENTMEDIASESSIONSERVICE = "ContentMediaSessionService";
        public static final String CONTENTSCREENREADER = "ContentScreenReader";
        public static final String CONTENTDISCARDED = "ContentDiscarded";
        public static final String EMBEDDERPOPUPBLOCKERTABHELPER = "EmbedderPopupBlockerTabHelper";
        public static final String EMBEDDERSAFEBROWSINGTRIGGEREDPOPUPBLOCKER = "EmbedderSafeBrowsingTriggeredPopupBlocker";
        public static final String EMBEDDERSAFEBROWSINGTHREATDETAILS = "EmbedderSafeBrowsingThreatDetails";
        public static final String EMBEDDERAPPBANNERMANAGER = "EmbedderAppBannerManager";
        public static final String EMBEDDERDOMDISTILLERVIEWERSOURCE = "EmbedderDomDistillerViewerSource";
        public static final String EMBEDDERDOMDISTILLERSELFDELETINGREQUESTDELEGATE = "EmbedderDomDistillerSelfDeletingRequestDelegate";
        public static final String EMBEDDEROOMINTERVENTIONTABHELPER = "EmbedderOomInterventionTabHelper";
        public static final String EMBEDDEROFFLINEPAGE = "EmbedderOfflinePage";
        public static final String EMBEDDERCHROMEPASSWORDMANAGERCLIENTBINDCREDENTIALMANAGER = "EmbedderChromePasswordManagerClientBindCredentialManager";
        public static final String EMBEDDERPERMISSIONREQUESTMANAGER = "EmbedderPermissionRequestManager";
        public static final String EMBEDDERMODALDIALOG = "EmbedderModalDialog";
        public static final String EMBEDDEREXTENSIONS = "EmbedderExtensions";
        public static final String EMBEDDEREXTENSIONMESSAGING = "EmbedderExtensionMessaging";
        public static final String EMBEDDEREXTENSIONMESSAGINGFOROPENPORT = "EmbedderExtensionMessagingForOpenPort";
        public static final String EMBEDDEREXTENSIONSENTMESSAGETOCACHEDFRAME = "EmbedderExtensionSentMessageToCachedFrame";
        public static final String EMBEDDEREXTENSIONFRAME = "EmbedderExtensionFrame";
        public static final String REQUESTEDBYWEBVIEWCLIENT = "RequestedByWebViewClient";
        public static final String POSTMESSAGEBYWEBVIEWCLIENT = "PostMessageByWebViewClient";
        public static final String CACHECONTROLNOSTOREDEVICEBOUNDSESSIONTERMINATED = "CacheControlNoStoreDeviceBoundSessionTerminated";
        public static final String CACHELIMITPRUNEDONMODERATEMEMORYPRESSURE = "CacheLimitPrunedOnModerateMemoryPressure";
        public static final String CACHELIMITPRUNEDONCRITICALMEMORYPRESSURE = "CacheLimitPrunedOnCriticalMemoryPressure";
    }
    /**
     * Types of not restored reasons for back-forward cache.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotRestoredReasonType {
        private BackForwardCacheNotRestoredReasonType() {}
        public static final String SUPPORTPENDING = "SupportPending";
        public static final String PAGESUPPORTNEEDED = "PageSupportNeeded";
        public static final String CIRCUMSTANTIAL = "Circumstantial";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheBlockingDetails extends CdpObject {
        private BackForwardCacheBlockingDetails(Map<String, Object> values) { super(values); }
        @Nullable public static BackForwardCacheBlockingDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackForwardCacheBlockingDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Url of the file where blockage happened. Optional because of tests.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Function name where blockage happened. Optional because of anonymous functions and tests.
         * @return the protocol field value
         */
        @Nullable public String function() {
            return (String) value("function");
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Url of the file where blockage happened. Optional because of tests.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Function name where blockage happened. Optional because of anonymous functions and tests.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder function(@Nullable String value) {
                if (value == null) values.remove("function");
                else values.put("function", jsonValue(value));
                return this;
            }
            /**
             * Line number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Column number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            public BackForwardCacheBlockingDetails build() {
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new BackForwardCacheBlockingDetails(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotRestoredExplanation extends CdpObject {
        private BackForwardCacheNotRestoredExplanation(Map<String, Object> values) { super(values); }
        @Nullable public static BackForwardCacheNotRestoredExplanation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackForwardCacheNotRestoredExplanation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the reason
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Not restored reason
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * Context associated with the reason. The meaning of this context is dependent on the reason: - EmbedderExtensionSentMessageToCachedFrame: the extension ID.
         * @return the protocol field value
         */
        @Nullable public String context() {
            return (String) value("context");
        }
        /**
         * Returns the details field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.BackForwardCacheBlockingDetails> details() {
            return list(value("details"), element0 -> Page.BackForwardCacheBlockingDetails.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the reason
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Not restored reason
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            /**
             * Context associated with the reason. The meaning of this context is dependent on the reason: - EmbedderExtensionSentMessageToCachedFrame: the extension ID.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder context(@Nullable String value) {
                if (value == null) values.remove("context");
                else values.put("context", jsonValue(value));
                return this;
            }
            /**
             * Sets the details field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder details(@Nullable java.util.List<Page.BackForwardCacheBlockingDetails> value) {
                if (value == null) values.remove("details");
                else values.put("details", jsonValue(value));
                return this;
            }
            public BackForwardCacheNotRestoredExplanation build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                return new BackForwardCacheNotRestoredExplanation(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotRestoredExplanationTree extends CdpObject {
        private BackForwardCacheNotRestoredExplanationTree(Map<String, Object> values) { super(values); }
        @Nullable public static BackForwardCacheNotRestoredExplanationTree fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackForwardCacheNotRestoredExplanationTree(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * URL of each frame
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Not restored reasons of each frame
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.BackForwardCacheNotRestoredExplanation> explanations() {
            return list(value("explanations"), element0 -> Page.BackForwardCacheNotRestoredExplanation.fromMap(objectMap(element0)));
        }
        /**
         * Array of children frame
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.BackForwardCacheNotRestoredExplanationTree> children() {
            return list(value("children"), element0 -> Page.BackForwardCacheNotRestoredExplanationTree.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * URL of each frame
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Not restored reasons of each frame
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder explanations(@Nullable java.util.List<Page.BackForwardCacheNotRestoredExplanation> value) {
                if (value == null) values.remove("explanations");
                else values.put("explanations", jsonValue(value));
                return this;
            }
            /**
             * Array of children frame
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder children(@Nullable java.util.List<Page.BackForwardCacheNotRestoredExplanationTree> value) {
                if (value == null) values.remove("children");
                else values.put("children", jsonValue(value));
                return this;
            }
            public BackForwardCacheNotRestoredExplanationTree build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("explanations")) throw new IllegalStateException("Missing required CDP field: explanations");
                if (!values.containsKey("children")) throw new IllegalStateException("Missing required CDP field: children");
                return new BackForwardCacheNotRestoredExplanationTree(values);
            }
        }
    }
    /**
     * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class AddScriptToEvaluateOnLoadParams extends CdpObject {
        private AddScriptToEvaluateOnLoadParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddScriptToEvaluateOnLoadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddScriptToEvaluateOnLoadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scriptSource field.
         * @return the protocol field value
         */
        @Nullable public String scriptSource() {
            return (String) value("scriptSource");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scriptSource field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptSource(@Nullable String value) {
                if (value == null) values.remove("scriptSource");
                else values.put("scriptSource", jsonValue(value));
                return this;
            }
            public AddScriptToEvaluateOnLoadParams build() {
                if (!values.containsKey("scriptSource")) throw new IllegalStateException("Missing required CDP field: scriptSource");
                return new AddScriptToEvaluateOnLoadParams(values);
            }
        }
    }
    /**
     * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class AddScriptToEvaluateOnLoadResult extends CdpObject {
        private AddScriptToEvaluateOnLoadResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddScriptToEvaluateOnLoadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddScriptToEvaluateOnLoadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the added script.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the added script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            public AddScriptToEvaluateOnLoadResult build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                return new AddScriptToEvaluateOnLoadResult(values);
            }
        }
    }
    /**
     * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
     */
    public static final class AddScriptToEvaluateOnNewDocumentParams extends CdpObject {
        private AddScriptToEvaluateOnNewDocumentParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddScriptToEvaluateOnNewDocumentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddScriptToEvaluateOnNewDocumentParams(values);
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
         * If specified, creates an isolated world with the given name and evaluates given script in it. This world name will be used as the ExecutionContextDescription::name when the corresponding event is emitted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String worldName() {
            return (String) value("worldName");
        }
        /**
         * Specifies whether command line API should be available to the script, defaults to false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean includeCommandLineAPI() {
            return (Boolean) value("includeCommandLineAPI");
        }
        /**
         * If true, runs the script immediately on existing execution contexts or worlds. Default: false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean runImmediately() {
            return (Boolean) value("runImmediately");
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
             * If specified, creates an isolated world with the given name and evaluates given script in it. This world name will be used as the ExecutionContextDescription::name when the corresponding event is emitted.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder worldName(@Nullable String value) {
                if (value == null) values.remove("worldName");
                else values.put("worldName", jsonValue(value));
                return this;
            }
            /**
             * Specifies whether command line API should be available to the script, defaults to false.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeCommandLineAPI(@Nullable Boolean value) {
                if (value == null) values.remove("includeCommandLineAPI");
                else values.put("includeCommandLineAPI", jsonValue(value));
                return this;
            }
            /**
             * If true, runs the script immediately on existing execution contexts or worlds. Default: false.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder runImmediately(@Nullable Boolean value) {
                if (value == null) values.remove("runImmediately");
                else values.put("runImmediately", jsonValue(value));
                return this;
            }
            public AddScriptToEvaluateOnNewDocumentParams build() {
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                return new AddScriptToEvaluateOnNewDocumentParams(values);
            }
        }
    }
    /**
     * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
     */
    public static final class AddScriptToEvaluateOnNewDocumentResult extends CdpObject {
        private AddScriptToEvaluateOnNewDocumentResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddScriptToEvaluateOnNewDocumentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddScriptToEvaluateOnNewDocumentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the added script.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the added script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            public AddScriptToEvaluateOnNewDocumentResult build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                return new AddScriptToEvaluateOnNewDocumentResult(values);
            }
        }
    }
    /**
     * Brings page to front (activates tab).
     */
    public static final class BringToFrontParams extends CdpObject {
        private BringToFrontParams(Map<String, Object> values) { super(values); }
        @Nullable public static BringToFrontParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BringToFrontParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public BringToFrontParams build() {
                return new BringToFrontParams(values);
            }
        }
    }
    /**
     * Brings page to front (activates tab).
     */
    public static final class BringToFrontResult extends CdpObject {
        private BringToFrontResult(Map<String, Object> values) { super(values); }
        @Nullable public static BringToFrontResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BringToFrontResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public BringToFrontResult build() {
                return new BringToFrontResult(values);
            }
        }
    }
    /**
     * Capture page screenshot.
     */
    public static final class CaptureScreenshotParams extends CdpObject {
        private CaptureScreenshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static CaptureScreenshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CaptureScreenshotParams(values);
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
         * Compression quality from range [0..100] (jpeg only).
         * @return the protocol field value
         */
        @Nullable public Long quality() {
            return numberAsLong(value("quality"));
        }
        /**
         * Capture the screenshot of a given region only.
         * @return the protocol field value
         */
        @Nullable public Page.Viewport clip() {
            return Page.Viewport.fromMap(objectMap(value("clip")));
        }
        /**
         * Capture the screenshot from the surface, rather than the view. Defaults to true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean fromSurface() {
            return (Boolean) value("fromSurface");
        }
        /**
         * Capture the screenshot beyond the viewport. Defaults to false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean captureBeyondViewport() {
            return (Boolean) value("captureBeyondViewport");
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
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
             * Compression quality from range [0..100] (jpeg only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quality(@Nullable Long value) {
                if (value == null) values.remove("quality");
                else values.put("quality", jsonValue(value));
                return this;
            }
            /**
             * Capture the screenshot of a given region only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clip(@Nullable Page.Viewport value) {
                if (value == null) values.remove("clip");
                else values.put("clip", jsonValue(value));
                return this;
            }
            /**
             * Capture the screenshot from the surface, rather than the view. Defaults to true.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromSurface(@Nullable Boolean value) {
                if (value == null) values.remove("fromSurface");
                else values.put("fromSurface", jsonValue(value));
                return this;
            }
            /**
             * Capture the screenshot beyond the viewport. Defaults to false.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder captureBeyondViewport(@Nullable Boolean value) {
                if (value == null) values.remove("captureBeyondViewport");
                else values.put("captureBeyondViewport", jsonValue(value));
                return this;
            }
            /**
             * Optimize image encoding for speed, not for resulting size (defaults to false)
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder optimizeForSpeed(@Nullable Boolean value) {
                if (value == null) values.remove("optimizeForSpeed");
                else values.put("optimizeForSpeed", jsonValue(value));
                return this;
            }
            public CaptureScreenshotParams build() {
                return new CaptureScreenshotParams(values);
            }
        }
    }
    /**
     * Capture page screenshot.
     */
    public static final class CaptureScreenshotResult extends CdpObject {
        private CaptureScreenshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static CaptureScreenshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CaptureScreenshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Base64-encoded image data. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Base64-encoded image data. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public CaptureScreenshotResult build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new CaptureScreenshotResult(values);
            }
        }
    }
    /**
     * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CaptureSnapshotParams extends CdpObject {
        private CaptureSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static CaptureSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CaptureSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Format (defaults to mhtml).
         * @return the protocol field value
         */
        @Nullable public String format() {
            return (String) value("format");
        }
        /**
         * Format (defaults to mhtml).
         */
        public static final class FormatValues {
            private FormatValues() {}
            public static final String MHTML = "mhtml";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Format (defaults to mhtml).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder format(@Nullable String value) {
                if (value == null) values.remove("format");
                else values.put("format", jsonValue(value));
                return this;
            }
            public CaptureSnapshotParams build() {
                return new CaptureSnapshotParams(values);
            }
        }
    }
    /**
     * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CaptureSnapshotResult extends CdpObject {
        private CaptureSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static CaptureSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CaptureSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Serialized page data.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Serialized page data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public CaptureSnapshotResult build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new CaptureSnapshotResult(values);
            }
        }
    }
    /**
     * Clears the overridden device metrics.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * Clears the overridden Device Orientation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class ClearDeviceOrientationOverrideParams extends CdpObject {
        private ClearDeviceOrientationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDeviceOrientationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDeviceOrientationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDeviceOrientationOverrideParams build() {
                return new ClearDeviceOrientationOverrideParams(values);
            }
        }
    }
    /**
     * Clears the overridden Device Orientation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class ClearDeviceOrientationOverrideResult extends CdpObject {
        private ClearDeviceOrientationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDeviceOrientationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDeviceOrientationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDeviceOrientationOverrideResult build() {
                return new ClearDeviceOrientationOverrideResult(values);
            }
        }
    }
    /**
     * Clears the overridden Geolocation Position and Error.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * Creates an isolated world for the given frame.
     */
    public static final class CreateIsolatedWorldParams extends CdpObject {
        private CreateIsolatedWorldParams(Map<String, Object> values) { super(values); }
        @Nullable public static CreateIsolatedWorldParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateIsolatedWorldParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame in which the isolated world should be created.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * An optional name which is reported in the Execution Context.
         * @return the protocol field value
         */
        @Nullable public String worldName() {
            return (String) value("worldName");
        }
        /**
         * Whether or not universal access should be granted to the isolated world. This is a powerful option, use with caution.
         * @return the protocol field value
         */
        @Nullable public Boolean grantUniveralAccess() {
            return (Boolean) value("grantUniveralAccess");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame in which the isolated world should be created.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * An optional name which is reported in the Execution Context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder worldName(@Nullable String value) {
                if (value == null) values.remove("worldName");
                else values.put("worldName", jsonValue(value));
                return this;
            }
            /**
             * Whether or not universal access should be granted to the isolated world. This is a powerful option, use with caution.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder grantUniveralAccess(@Nullable Boolean value) {
                if (value == null) values.remove("grantUniveralAccess");
                else values.put("grantUniveralAccess", jsonValue(value));
                return this;
            }
            public CreateIsolatedWorldParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new CreateIsolatedWorldParams(values);
            }
        }
    }
    /**
     * Creates an isolated world for the given frame.
     */
    public static final class CreateIsolatedWorldResult extends CdpObject {
        private CreateIsolatedWorldResult(Map<String, Object> values) { super(values); }
        @Nullable public static CreateIsolatedWorldResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateIsolatedWorldResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Execution context of the isolated world.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Execution context of the isolated world.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            public CreateIsolatedWorldResult build() {
                if (!values.containsKey("executionContextId")) throw new IllegalStateException("Missing required CDP field: executionContextId");
                return new CreateIsolatedWorldResult(values);
            }
        }
    }
    /**
     * Deletes browser cookie with given name, domain and path.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DeleteCookieParams extends CdpObject {
        private DeleteCookieParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteCookieParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteCookieParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of the cookie to remove.
         * @return the protocol field value
         */
        @Nullable public String cookieName() {
            return (String) value("cookieName");
        }
        /**
         * URL to match cooke domain and path.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of the cookie to remove.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieName(@Nullable String value) {
                if (value == null) values.remove("cookieName");
                else values.put("cookieName", jsonValue(value));
                return this;
            }
            /**
             * URL to match cooke domain and path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public DeleteCookieParams build() {
                if (!values.containsKey("cookieName")) throw new IllegalStateException("Missing required CDP field: cookieName");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new DeleteCookieParams(values);
            }
        }
    }
    /**
     * Deletes browser cookie with given name, domain and path.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DeleteCookieResult extends CdpObject {
        private DeleteCookieResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteCookieResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteCookieResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteCookieResult build() {
                return new DeleteCookieResult(values);
            }
        }
    }
    /**
     * Disables page domain notifications.
     */
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
     * Disables page domain notifications.
     */
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
     * Enables page domain notifications.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true, the {@code Page.fileChooserOpened} event will be emitted regardless of the state set by {@code Page.setInterceptFileChooserDialog} command (default: false).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean enableFileChooserOpenedEvent() {
            return (Boolean) value("enableFileChooserOpenedEvent");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true, the {@code Page.fileChooserOpened} event will be emitted regardless of the state set by {@code Page.setInterceptFileChooserDialog} command (default: false).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableFileChooserOpenedEvent(@Nullable Boolean value) {
                if (value == null) values.remove("enableFileChooserOpenedEvent");
                else values.put("enableFileChooserOpenedEvent", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables page domain notifications.
     */
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
    /**
     * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
     */
    public static final class GetAppManifestParams extends CdpObject {
        private GetAppManifestParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAppManifestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAppManifestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the manifestId field.
         * @return the protocol field value
         */
        @Nullable public String manifestId() {
            return (String) value("manifestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the manifestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifestId(@Nullable String value) {
                if (value == null) values.remove("manifestId");
                else values.put("manifestId", jsonValue(value));
                return this;
            }
            public GetAppManifestParams build() {
                return new GetAppManifestParams(values);
            }
        }
    }
    /**
     * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
     */
    public static final class GetAppManifestResult extends CdpObject {
        private GetAppManifestResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAppManifestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAppManifestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Manifest location.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the errors field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.AppManifestError> errors() {
            return list(value("errors"), element0 -> Page.AppManifestError.fromMap(objectMap(element0)));
        }
        /**
         * Manifest content.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Parsed manifest properties. Deprecated, use manifest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Page.AppManifestParsedProperties parsed() {
            return Page.AppManifestParsedProperties.fromMap(objectMap(value("parsed")));
        }
        /**
         * Returns the manifest field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Page.WebAppManifest manifest() {
            return Page.WebAppManifest.fromMap(objectMap(value("manifest")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Manifest location.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the errors field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errors(@Nullable java.util.List<Page.AppManifestError> value) {
                if (value == null) values.remove("errors");
                else values.put("errors", jsonValue(value));
                return this;
            }
            /**
             * Manifest content.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Parsed manifest properties. Deprecated, use manifest instead.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder parsed(@Nullable Page.AppManifestParsedProperties value) {
                if (value == null) values.remove("parsed");
                else values.put("parsed", jsonValue(value));
                return this;
            }
            /**
             * Sets the manifest field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manifest(@Nullable Page.WebAppManifest value) {
                if (value == null) values.remove("manifest");
                else values.put("manifest", jsonValue(value));
                return this;
            }
            public GetAppManifestResult build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("errors")) throw new IllegalStateException("Missing required CDP field: errors");
                if (!values.containsKey("manifest")) throw new IllegalStateException("Missing required CDP field: manifest");
                return new GetAppManifestResult(values);
            }
        }
    }
    /**
     * Parameters for Page.getInstallabilityErrors.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetInstallabilityErrorsParams extends CdpObject {
        private GetInstallabilityErrorsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetInstallabilityErrorsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInstallabilityErrorsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetInstallabilityErrorsParams build() {
                return new GetInstallabilityErrorsParams(values);
            }
        }
    }
    /**
     * Result of Page.getInstallabilityErrors.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetInstallabilityErrorsResult extends CdpObject {
        private GetInstallabilityErrorsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetInstallabilityErrorsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInstallabilityErrorsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the installabilityErrors field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.InstallabilityError> installabilityErrors() {
            return list(value("installabilityErrors"), element0 -> Page.InstallabilityError.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the installabilityErrors field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder installabilityErrors(@Nullable java.util.List<Page.InstallabilityError> value) {
                if (value == null) values.remove("installabilityErrors");
                else values.put("installabilityErrors", jsonValue(value));
                return this;
            }
            public GetInstallabilityErrorsResult build() {
                if (!values.containsKey("installabilityErrors")) throw new IllegalStateException("Missing required CDP field: installabilityErrors");
                return new GetInstallabilityErrorsResult(values);
            }
        }
    }
    /**
     * Deprecated because it&#x27;s not guaranteed that the returned icon is in fact the one used for PWA installation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetManifestIconsParams extends CdpObject {
        private GetManifestIconsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetManifestIconsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetManifestIconsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetManifestIconsParams build() {
                return new GetManifestIconsParams(values);
            }
        }
    }
    /**
     * Deprecated because it&#x27;s not guaranteed that the returned icon is in fact the one used for PWA installation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetManifestIconsResult extends CdpObject {
        private GetManifestIconsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetManifestIconsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetManifestIconsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the primaryIcon field.
         * @return the protocol field value
         */
        @Nullable public String primaryIcon() {
            return (String) value("primaryIcon");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the primaryIcon field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder primaryIcon(@Nullable String value) {
                if (value == null) values.remove("primaryIcon");
                else values.put("primaryIcon", jsonValue(value));
                return this;
            }
            public GetManifestIconsResult build() {
                return new GetManifestIconsResult(values);
            }
        }
    }
    /**
     * Returns the unique (PWA) app id. Only returns values if the feature flag &#x27;WebAppEnableManifestId&#x27; is enabled
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAppIdParams extends CdpObject {
        private GetAppIdParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAppIdParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAppIdParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetAppIdParams build() {
                return new GetAppIdParams(values);
            }
        }
    }
    /**
     * Returns the unique (PWA) app id. Only returns values if the feature flag &#x27;WebAppEnableManifestId&#x27; is enabled
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAppIdResult extends CdpObject {
        private GetAppIdResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAppIdResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAppIdResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * App id, either from manifest&#x27;s id attribute or computed from start_url
         * @return the protocol field value
         */
        @Nullable public String appId() {
            return (String) value("appId");
        }
        /**
         * Recommendation for manifest&#x27;s id attribute to match current id computed from start_url
         * @return the protocol field value
         */
        @Nullable public String recommendedId() {
            return (String) value("recommendedId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * App id, either from manifest&#x27;s id attribute or computed from start_url
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder appId(@Nullable String value) {
                if (value == null) values.remove("appId");
                else values.put("appId", jsonValue(value));
                return this;
            }
            /**
             * Recommendation for manifest&#x27;s id attribute to match current id computed from start_url
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder recommendedId(@Nullable String value) {
                if (value == null) values.remove("recommendedId");
                else values.put("recommendedId", jsonValue(value));
                return this;
            }
            public GetAppIdResult build() {
                return new GetAppIdResult(values);
            }
        }
    }
    /**
     * Parameters for Page.getAdScriptAncestry.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAdScriptAncestryParams extends CdpObject {
        private GetAdScriptAncestryParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAdScriptAncestryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAdScriptAncestryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetAdScriptAncestryParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new GetAdScriptAncestryParams(values);
            }
        }
    }
    /**
     * Result of Page.getAdScriptAncestry.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAdScriptAncestryResult extends CdpObject {
        private GetAdScriptAncestryResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAdScriptAncestryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAdScriptAncestryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The ancestry chain of ad script identifiers leading to this frame&#x27;s creation, along with the root script&#x27;s filterlist rule. The ancestry chain is ordered from the most immediate script (in the frame creation stack) to more distant ancestors (that created the immediately preceding script). Only sent if frame is labelled as an ad and ids are available.
         * @return the protocol field value
         */
        @Nullable public Network.AdAncestry adScriptAncestry() {
            return Network.AdAncestry.fromMap(objectMap(value("adScriptAncestry")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The ancestry chain of ad script identifiers leading to this frame&#x27;s creation, along with the root script&#x27;s filterlist rule. The ancestry chain is ordered from the most immediate script (in the frame creation stack) to more distant ancestors (that created the immediately preceding script). Only sent if frame is labelled as an ad and ids are available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder adScriptAncestry(@Nullable Network.AdAncestry value) {
                if (value == null) values.remove("adScriptAncestry");
                else values.put("adScriptAncestry", jsonValue(value));
                return this;
            }
            public GetAdScriptAncestryResult build() {
                return new GetAdScriptAncestryResult(values);
            }
        }
    }
    /**
     * Returns present frame tree structure.
     */
    public static final class GetFrameTreeParams extends CdpObject {
        private GetFrameTreeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetFrameTreeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFrameTreeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetFrameTreeParams build() {
                return new GetFrameTreeParams(values);
            }
        }
    }
    /**
     * Returns present frame tree structure.
     */
    public static final class GetFrameTreeResult extends CdpObject {
        private GetFrameTreeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetFrameTreeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFrameTreeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Present frame tree structure.
         * @return the protocol field value
         */
        @Nullable public Page.FrameTree frameTree() {
            return Page.FrameTree.fromMap(objectMap(value("frameTree")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Present frame tree structure.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameTree(@Nullable Page.FrameTree value) {
                if (value == null) values.remove("frameTree");
                else values.put("frameTree", jsonValue(value));
                return this;
            }
            public GetFrameTreeResult build() {
                if (!values.containsKey("frameTree")) throw new IllegalStateException("Missing required CDP field: frameTree");
                return new GetFrameTreeResult(values);
            }
        }
    }
    /**
     * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
     */
    public static final class GetLayoutMetricsParams extends CdpObject {
        private GetLayoutMetricsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetLayoutMetricsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLayoutMetricsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetLayoutMetricsParams build() {
                return new GetLayoutMetricsParams(values);
            }
        }
    }
    /**
     * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
     */
    public static final class GetLayoutMetricsResult extends CdpObject {
        private GetLayoutMetricsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetLayoutMetricsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLayoutMetricsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Deprecated metrics relating to the layout viewport. Is in device pixels. Use {@code cssLayoutViewport} instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Page.LayoutViewport layoutViewport() {
            return Page.LayoutViewport.fromMap(objectMap(value("layoutViewport")));
        }
        /**
         * Deprecated metrics relating to the visual viewport. Is in device pixels. Use {@code cssVisualViewport} instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Page.VisualViewport visualViewport() {
            return Page.VisualViewport.fromMap(objectMap(value("visualViewport")));
        }
        /**
         * Deprecated size of scrollable area. Is in DP. Use {@code cssContentSize} instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public DOM.Rect contentSize() {
            return DOM.Rect.fromMap(objectMap(value("contentSize")));
        }
        /**
         * Metrics relating to the layout viewport in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Page.LayoutViewport cssLayoutViewport() {
            return Page.LayoutViewport.fromMap(objectMap(value("cssLayoutViewport")));
        }
        /**
         * Metrics relating to the visual viewport in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Page.VisualViewport cssVisualViewport() {
            return Page.VisualViewport.fromMap(objectMap(value("cssVisualViewport")));
        }
        /**
         * Size of scrollable area in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect cssContentSize() {
            return DOM.Rect.fromMap(objectMap(value("cssContentSize")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Deprecated metrics relating to the layout viewport. Is in device pixels. Use {@code cssLayoutViewport} instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder layoutViewport(@Nullable Page.LayoutViewport value) {
                if (value == null) values.remove("layoutViewport");
                else values.put("layoutViewport", jsonValue(value));
                return this;
            }
            /**
             * Deprecated metrics relating to the visual viewport. Is in device pixels. Use {@code cssVisualViewport} instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder visualViewport(@Nullable Page.VisualViewport value) {
                if (value == null) values.remove("visualViewport");
                else values.put("visualViewport", jsonValue(value));
                return this;
            }
            /**
             * Deprecated size of scrollable area. Is in DP. Use {@code cssContentSize} instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder contentSize(@Nullable DOM.Rect value) {
                if (value == null) values.remove("contentSize");
                else values.put("contentSize", jsonValue(value));
                return this;
            }
            /**
             * Metrics relating to the layout viewport in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssLayoutViewport(@Nullable Page.LayoutViewport value) {
                if (value == null) values.remove("cssLayoutViewport");
                else values.put("cssLayoutViewport", jsonValue(value));
                return this;
            }
            /**
             * Metrics relating to the visual viewport in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssVisualViewport(@Nullable Page.VisualViewport value) {
                if (value == null) values.remove("cssVisualViewport");
                else values.put("cssVisualViewport", jsonValue(value));
                return this;
            }
            /**
             * Size of scrollable area in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssContentSize(@Nullable DOM.Rect value) {
                if (value == null) values.remove("cssContentSize");
                else values.put("cssContentSize", jsonValue(value));
                return this;
            }
            public GetLayoutMetricsResult build() {
                if (!values.containsKey("layoutViewport")) throw new IllegalStateException("Missing required CDP field: layoutViewport");
                if (!values.containsKey("visualViewport")) throw new IllegalStateException("Missing required CDP field: visualViewport");
                if (!values.containsKey("contentSize")) throw new IllegalStateException("Missing required CDP field: contentSize");
                if (!values.containsKey("cssLayoutViewport")) throw new IllegalStateException("Missing required CDP field: cssLayoutViewport");
                if (!values.containsKey("cssVisualViewport")) throw new IllegalStateException("Missing required CDP field: cssVisualViewport");
                if (!values.containsKey("cssContentSize")) throw new IllegalStateException("Missing required CDP field: cssContentSize");
                return new GetLayoutMetricsResult(values);
            }
        }
    }
    /**
     * Returns navigation history for the current page.
     */
    public static final class GetNavigationHistoryParams extends CdpObject {
        private GetNavigationHistoryParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetNavigationHistoryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNavigationHistoryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetNavigationHistoryParams build() {
                return new GetNavigationHistoryParams(values);
            }
        }
    }
    /**
     * Returns navigation history for the current page.
     */
    public static final class GetNavigationHistoryResult extends CdpObject {
        private GetNavigationHistoryResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetNavigationHistoryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNavigationHistoryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Index of the current navigation history entry.
         * @return the protocol field value
         */
        @Nullable public Long currentIndex() {
            return numberAsLong(value("currentIndex"));
        }
        /**
         * Array of navigation history entries.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.NavigationEntry> entries() {
            return list(value("entries"), element0 -> Page.NavigationEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Index of the current navigation history entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentIndex(@Nullable Long value) {
                if (value == null) values.remove("currentIndex");
                else values.put("currentIndex", jsonValue(value));
                return this;
            }
            /**
             * Array of navigation history entries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entries(@Nullable java.util.List<Page.NavigationEntry> value) {
                if (value == null) values.remove("entries");
                else values.put("entries", jsonValue(value));
                return this;
            }
            public GetNavigationHistoryResult build() {
                if (!values.containsKey("currentIndex")) throw new IllegalStateException("Missing required CDP field: currentIndex");
                if (!values.containsKey("entries")) throw new IllegalStateException("Missing required CDP field: entries");
                return new GetNavigationHistoryResult(values);
            }
        }
    }
    /**
     * Resets navigation history for the current page.
     */
    public static final class ResetNavigationHistoryParams extends CdpObject {
        private ResetNavigationHistoryParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResetNavigationHistoryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetNavigationHistoryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetNavigationHistoryParams build() {
                return new ResetNavigationHistoryParams(values);
            }
        }
    }
    /**
     * Resets navigation history for the current page.
     */
    public static final class ResetNavigationHistoryResult extends CdpObject {
        private ResetNavigationHistoryResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResetNavigationHistoryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetNavigationHistoryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetNavigationHistoryResult build() {
                return new ResetNavigationHistoryResult(values);
            }
        }
    }
    /**
     * Returns content of the given resource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResourceContentParams extends CdpObject {
        private GetResourceContentParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetResourceContentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResourceContentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame id to get resource for.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * URL of the resource to get content for.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame id to get resource for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * URL of the resource to get content for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public GetResourceContentParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new GetResourceContentParams(values);
            }
        }
    }
    /**
     * Returns content of the given resource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResourceContentResult extends CdpObject {
        private GetResourceContentResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetResourceContentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResourceContentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resource content.
         * @return the protocol field value
         */
        @Nullable public String content() {
            return (String) value("content");
        }
        /**
         * True, if content was served as base64.
         * @return the protocol field value
         */
        @Nullable public Boolean base64Encoded() {
            return (Boolean) value("base64Encoded");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resource content.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder content(@Nullable String value) {
                if (value == null) values.remove("content");
                else values.put("content", jsonValue(value));
                return this;
            }
            /**
             * True, if content was served as base64.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder base64Encoded(@Nullable Boolean value) {
                if (value == null) values.remove("base64Encoded");
                else values.put("base64Encoded", jsonValue(value));
                return this;
            }
            public GetResourceContentResult build() {
                if (!values.containsKey("content")) throw new IllegalStateException("Missing required CDP field: content");
                if (!values.containsKey("base64Encoded")) throw new IllegalStateException("Missing required CDP field: base64Encoded");
                return new GetResourceContentResult(values);
            }
        }
    }
    /**
     * Returns present frame / resource tree structure.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResourceTreeParams extends CdpObject {
        private GetResourceTreeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetResourceTreeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResourceTreeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetResourceTreeParams build() {
                return new GetResourceTreeParams(values);
            }
        }
    }
    /**
     * Returns present frame / resource tree structure.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResourceTreeResult extends CdpObject {
        private GetResourceTreeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetResourceTreeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResourceTreeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Present frame / resource tree structure.
         * @return the protocol field value
         */
        @Nullable public Page.FrameResourceTree frameTree() {
            return Page.FrameResourceTree.fromMap(objectMap(value("frameTree")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Present frame / resource tree structure.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameTree(@Nullable Page.FrameResourceTree value) {
                if (value == null) values.remove("frameTree");
                else values.put("frameTree", jsonValue(value));
                return this;
            }
            public GetResourceTreeResult build() {
                if (!values.containsKey("frameTree")) throw new IllegalStateException("Missing required CDP field: frameTree");
                return new GetResourceTreeResult(values);
            }
        }
    }
    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     */
    public static final class HandleJavaScriptDialogParams extends CdpObject {
        private HandleJavaScriptDialogParams(Map<String, Object> values) { super(values); }
        @Nullable public static HandleJavaScriptDialogParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HandleJavaScriptDialogParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to accept or dismiss the dialog.
         * @return the protocol field value
         */
        @Nullable public Boolean accept() {
            return (Boolean) value("accept");
        }
        /**
         * The text to enter into the dialog prompt before accepting. Used only if this is a prompt dialog.
         * @return the protocol field value
         */
        @Nullable public String promptText() {
            return (String) value("promptText");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to accept or dismiss the dialog.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accept(@Nullable Boolean value) {
                if (value == null) values.remove("accept");
                else values.put("accept", jsonValue(value));
                return this;
            }
            /**
             * The text to enter into the dialog prompt before accepting. Used only if this is a prompt dialog.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder promptText(@Nullable String value) {
                if (value == null) values.remove("promptText");
                else values.put("promptText", jsonValue(value));
                return this;
            }
            public HandleJavaScriptDialogParams build() {
                if (!values.containsKey("accept")) throw new IllegalStateException("Missing required CDP field: accept");
                return new HandleJavaScriptDialogParams(values);
            }
        }
    }
    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     */
    public static final class HandleJavaScriptDialogResult extends CdpObject {
        private HandleJavaScriptDialogResult(Map<String, Object> values) { super(values); }
        @Nullable public static HandleJavaScriptDialogResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HandleJavaScriptDialogResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HandleJavaScriptDialogResult build() {
                return new HandleJavaScriptDialogResult(values);
            }
        }
    }
    /**
     * Navigates current page to the given URL.
     */
    public static final class NavigateParams extends CdpObject {
        private NavigateParams(Map<String, Object> values) { super(values); }
        @Nullable public static NavigateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * URL to navigate the page to.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Referrer URL.
         * @return the protocol field value
         */
        @Nullable public String referrer() {
            return (String) value("referrer");
        }
        /**
         * Intended transition type.
         * @return the protocol field value
         */
        @Nullable public String transitionType() {
            return (String) value("transitionType");
        }
        /**
         * Frame id to navigate, if not specified navigates the top frame.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Referrer-policy used for the navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String referrerPolicy() {
            return (String) value("referrerPolicy");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * URL to navigate the page to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Referrer URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder referrer(@Nullable String value) {
                if (value == null) values.remove("referrer");
                else values.put("referrer", jsonValue(value));
                return this;
            }
            /**
             * Intended transition type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transitionType(@Nullable String value) {
                if (value == null) values.remove("transitionType");
                else values.put("transitionType", jsonValue(value));
                return this;
            }
            /**
             * Frame id to navigate, if not specified navigates the top frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Referrer-policy used for the navigation.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder referrerPolicy(@Nullable String value) {
                if (value == null) values.remove("referrerPolicy");
                else values.put("referrerPolicy", jsonValue(value));
                return this;
            }
            public NavigateParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new NavigateParams(values);
            }
        }
    }
    /**
     * Navigates current page to the given URL.
     */
    public static final class NavigateResult extends CdpObject {
        private NavigateResult(Map<String, Object> values) { super(values); }
        @Nullable public static NavigateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame id that has navigated (or failed to navigate)
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Loader identifier. This is omitted in case of same-document navigation, as the previously committed loaderId would not change.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * User friendly error message, present if and only if navigation has failed.
         * @return the protocol field value
         */
        @Nullable public String errorText() {
            return (String) value("errorText");
        }
        /**
         * Whether the navigation resulted in a download.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean isDownload() {
            return (Boolean) value("isDownload");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame id that has navigated (or failed to navigate)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Loader identifier. This is omitted in case of same-document navigation, as the previously committed loaderId would not change.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * User friendly error message, present if and only if navigation has failed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorText(@Nullable String value) {
                if (value == null) values.remove("errorText");
                else values.put("errorText", jsonValue(value));
                return this;
            }
            /**
             * Whether the navigation resulted in a download.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isDownload(@Nullable Boolean value) {
                if (value == null) values.remove("isDownload");
                else values.put("isDownload", jsonValue(value));
                return this;
            }
            public NavigateResult build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new NavigateResult(values);
            }
        }
    }
    /**
     * Navigates current page to the given history entry.
     */
    public static final class NavigateToHistoryEntryParams extends CdpObject {
        private NavigateToHistoryEntryParams(Map<String, Object> values) { super(values); }
        @Nullable public static NavigateToHistoryEntryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigateToHistoryEntryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique id of the entry to navigate to.
         * @return the protocol field value
         */
        @Nullable public Long entryId() {
            return numberAsLong(value("entryId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique id of the entry to navigate to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entryId(@Nullable Long value) {
                if (value == null) values.remove("entryId");
                else values.put("entryId", jsonValue(value));
                return this;
            }
            public NavigateToHistoryEntryParams build() {
                if (!values.containsKey("entryId")) throw new IllegalStateException("Missing required CDP field: entryId");
                return new NavigateToHistoryEntryParams(values);
            }
        }
    }
    /**
     * Navigates current page to the given history entry.
     */
    public static final class NavigateToHistoryEntryResult extends CdpObject {
        private NavigateToHistoryEntryResult(Map<String, Object> values) { super(values); }
        @Nullable public static NavigateToHistoryEntryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigateToHistoryEntryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public NavigateToHistoryEntryResult build() {
                return new NavigateToHistoryEntryResult(values);
            }
        }
    }
    /**
     * Print page as PDF.
     */
    public static final class PrintToPDFParams extends CdpObject {
        private PrintToPDFParams(Map<String, Object> values) { super(values); }
        @Nullable public static PrintToPDFParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrintToPDFParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Paper orientation. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean landscape() {
            return (Boolean) value("landscape");
        }
        /**
         * Display header and footer. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean displayHeaderFooter() {
            return (Boolean) value("displayHeaderFooter");
        }
        /**
         * Print background graphics. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean printBackground() {
            return (Boolean) value("printBackground");
        }
        /**
         * Scale of the webpage rendering. Defaults to 1.
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        /**
         * Paper width in inches. Defaults to 8.5 inches.
         * @return the protocol field value
         */
        @Nullable public Double paperWidth() {
            return numberAsDouble(value("paperWidth"));
        }
        /**
         * Paper height in inches. Defaults to 11 inches.
         * @return the protocol field value
         */
        @Nullable public Double paperHeight() {
            return numberAsDouble(value("paperHeight"));
        }
        /**
         * Top margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value
         */
        @Nullable public Double marginTop() {
            return numberAsDouble(value("marginTop"));
        }
        /**
         * Bottom margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value
         */
        @Nullable public Double marginBottom() {
            return numberAsDouble(value("marginBottom"));
        }
        /**
         * Left margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value
         */
        @Nullable public Double marginLeft() {
            return numberAsDouble(value("marginLeft"));
        }
        /**
         * Right margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value
         */
        @Nullable public Double marginRight() {
            return numberAsDouble(value("marginRight"));
        }
        /**
         * Paper ranges to print, one based, e.g., &#x27;1-5, 8, 11-13&#x27;. Pages are printed in the document order, not in the order specified, and no more than once. Defaults to empty string, which implies the entire document is printed. The page numbers are quietly capped to actual page count of the document, and ranges beyond the end of the document are ignored. If this results in no pages to print, an error is reported. It is an error to specify a range with start greater than end.
         * @return the protocol field value
         */
        @Nullable public String pageRanges() {
            return (String) value("pageRanges");
        }
        /**
         * HTML template for the print header. Should be valid HTML markup with following classes used to inject printing values into them: - {@code date}: formatted print date - {@code title}: document title - {@code url}: document location - {@code pageNumber}: current page number - {@code totalPages}: total pages in the document
         * <p>For example, {@code &lt;span class=title&gt;&lt;/span&gt;} would generate span containing the title.
         * @return the protocol field value
         */
        @Nullable public String headerTemplate() {
            return (String) value("headerTemplate");
        }
        /**
         * HTML template for the print footer. Should use the same format as the {@code headerTemplate}.
         * @return the protocol field value
         */
        @Nullable public String footerTemplate() {
            return (String) value("footerTemplate");
        }
        /**
         * Whether or not to prefer page size as defined by css. Defaults to false, in which case the content will be scaled to fit the paper size.
         * @return the protocol field value
         */
        @Nullable public Boolean preferCSSPageSize() {
            return (Boolean) value("preferCSSPageSize");
        }
        /**
         * return as stream
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String transferMode() {
            return (String) value("transferMode");
        }
        /**
         * return as stream
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class TransferModeValues {
            private TransferModeValues() {}
            public static final String RETURNASBASE64 = "ReturnAsBase64";
            public static final String RETURNASSTREAM = "ReturnAsStream";
        }
        /**
         * Whether or not to generate tagged (accessible) PDF. Defaults to embedder choice.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean generateTaggedPDF() {
            return (Boolean) value("generateTaggedPDF");
        }
        /**
         * Whether or not to embed the document outline into the PDF.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean generateDocumentOutline() {
            return (Boolean) value("generateDocumentOutline");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Paper orientation. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder landscape(@Nullable Boolean value) {
                if (value == null) values.remove("landscape");
                else values.put("landscape", jsonValue(value));
                return this;
            }
            /**
             * Display header and footer. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayHeaderFooter(@Nullable Boolean value) {
                if (value == null) values.remove("displayHeaderFooter");
                else values.put("displayHeaderFooter", jsonValue(value));
                return this;
            }
            /**
             * Print background graphics. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder printBackground(@Nullable Boolean value) {
                if (value == null) values.remove("printBackground");
                else values.put("printBackground", jsonValue(value));
                return this;
            }
            /**
             * Scale of the webpage rendering. Defaults to 1.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scale(@Nullable Double value) {
                if (value == null) values.remove("scale");
                else values.put("scale", jsonValue(value));
                return this;
            }
            /**
             * Paper width in inches. Defaults to 8.5 inches.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paperWidth(@Nullable Double value) {
                if (value == null) values.remove("paperWidth");
                else values.put("paperWidth", jsonValue(value));
                return this;
            }
            /**
             * Paper height in inches. Defaults to 11 inches.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paperHeight(@Nullable Double value) {
                if (value == null) values.remove("paperHeight");
                else values.put("paperHeight", jsonValue(value));
                return this;
            }
            /**
             * Top margin in inches. Defaults to 1cm (~0.4 inches).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder marginTop(@Nullable Double value) {
                if (value == null) values.remove("marginTop");
                else values.put("marginTop", jsonValue(value));
                return this;
            }
            /**
             * Bottom margin in inches. Defaults to 1cm (~0.4 inches).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder marginBottom(@Nullable Double value) {
                if (value == null) values.remove("marginBottom");
                else values.put("marginBottom", jsonValue(value));
                return this;
            }
            /**
             * Left margin in inches. Defaults to 1cm (~0.4 inches).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder marginLeft(@Nullable Double value) {
                if (value == null) values.remove("marginLeft");
                else values.put("marginLeft", jsonValue(value));
                return this;
            }
            /**
             * Right margin in inches. Defaults to 1cm (~0.4 inches).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder marginRight(@Nullable Double value) {
                if (value == null) values.remove("marginRight");
                else values.put("marginRight", jsonValue(value));
                return this;
            }
            /**
             * Paper ranges to print, one based, e.g., &#x27;1-5, 8, 11-13&#x27;. Pages are printed in the document order, not in the order specified, and no more than once. Defaults to empty string, which implies the entire document is printed. The page numbers are quietly capped to actual page count of the document, and ranges beyond the end of the document are ignored. If this results in no pages to print, an error is reported. It is an error to specify a range with start greater than end.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pageRanges(@Nullable String value) {
                if (value == null) values.remove("pageRanges");
                else values.put("pageRanges", jsonValue(value));
                return this;
            }
            /**
             * HTML template for the print header. Should be valid HTML markup with following classes used to inject printing values into them: - {@code date}: formatted print date - {@code title}: document title - {@code url}: document location - {@code pageNumber}: current page number - {@code totalPages}: total pages in the document
             * <p>For example, {@code &lt;span class=title&gt;&lt;/span&gt;} would generate span containing the title.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headerTemplate(@Nullable String value) {
                if (value == null) values.remove("headerTemplate");
                else values.put("headerTemplate", jsonValue(value));
                return this;
            }
            /**
             * HTML template for the print footer. Should use the same format as the {@code headerTemplate}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder footerTemplate(@Nullable String value) {
                if (value == null) values.remove("footerTemplate");
                else values.put("footerTemplate", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to prefer page size as defined by css. Defaults to false, in which case the content will be scaled to fit the paper size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder preferCSSPageSize(@Nullable Boolean value) {
                if (value == null) values.remove("preferCSSPageSize");
                else values.put("preferCSSPageSize", jsonValue(value));
                return this;
            }
            /**
             * return as stream
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transferMode(@Nullable String value) {
                if (value == null) values.remove("transferMode");
                else values.put("transferMode", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to generate tagged (accessible) PDF. Defaults to embedder choice.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generateTaggedPDF(@Nullable Boolean value) {
                if (value == null) values.remove("generateTaggedPDF");
                else values.put("generateTaggedPDF", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to embed the document outline into the PDF.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generateDocumentOutline(@Nullable Boolean value) {
                if (value == null) values.remove("generateDocumentOutline");
                else values.put("generateDocumentOutline", jsonValue(value));
                return this;
            }
            public PrintToPDFParams build() {
                return new PrintToPDFParams(values);
            }
        }
    }
    /**
     * Print page as PDF.
     */
    public static final class PrintToPDFResult extends CdpObject {
        private PrintToPDFResult(Map<String, Object> values) { super(values); }
        @Nullable public static PrintToPDFResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrintToPDFResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Base64-encoded pdf data. Empty if |returnAsStream| is specified. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * A handle of the stream that holds resulting PDF data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String stream() {
            return (String) value("stream");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Base64-encoded pdf data. Empty if |returnAsStream| is specified. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * A handle of the stream that holds resulting PDF data.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stream(@Nullable String value) {
                if (value == null) values.remove("stream");
                else values.put("stream", jsonValue(value));
                return this;
            }
            public PrintToPDFResult build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new PrintToPDFResult(values);
            }
        }
    }
    /**
     * Reloads given page optionally ignoring the cache.
     */
    public static final class ReloadParams extends CdpObject {
        private ReloadParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReloadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReloadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true, browser cache is ignored (as if the user pressed Shift+refresh).
         * @return the protocol field value
         */
        @Nullable public Boolean ignoreCache() {
            return (Boolean) value("ignoreCache");
        }
        /**
         * If set, the script will be injected into all frames of the inspected page after reload. Argument will be ignored if reloading dataURL origin.
         * @return the protocol field value
         */
        @Nullable public String scriptToEvaluateOnLoad() {
            return (String) value("scriptToEvaluateOnLoad");
        }
        /**
         * If set, an error will be thrown if the target page&#x27;s main frame&#x27;s loader id does not match the provided id. This prevents accidentally reloading an unintended target in case there&#x27;s a racing navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true, browser cache is ignored (as if the user pressed Shift+refresh).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignoreCache(@Nullable Boolean value) {
                if (value == null) values.remove("ignoreCache");
                else values.put("ignoreCache", jsonValue(value));
                return this;
            }
            /**
             * If set, the script will be injected into all frames of the inspected page after reload. Argument will be ignored if reloading dataURL origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptToEvaluateOnLoad(@Nullable String value) {
                if (value == null) values.remove("scriptToEvaluateOnLoad");
                else values.put("scriptToEvaluateOnLoad", jsonValue(value));
                return this;
            }
            /**
             * If set, an error will be thrown if the target page&#x27;s main frame&#x27;s loader id does not match the provided id. This prevents accidentally reloading an unintended target in case there&#x27;s a racing navigation.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            public ReloadParams build() {
                return new ReloadParams(values);
            }
        }
    }
    /**
     * Reloads given page optionally ignoring the cache.
     */
    public static final class ReloadResult extends CdpObject {
        private ReloadResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReloadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReloadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReloadResult build() {
                return new ReloadResult(values);
            }
        }
    }
    /**
     * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RemoveScriptToEvaluateOnLoadParams extends CdpObject {
        private RemoveScriptToEvaluateOnLoadParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveScriptToEvaluateOnLoadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveScriptToEvaluateOnLoadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            public RemoveScriptToEvaluateOnLoadParams build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                return new RemoveScriptToEvaluateOnLoadParams(values);
            }
        }
    }
    /**
     * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RemoveScriptToEvaluateOnLoadResult extends CdpObject {
        private RemoveScriptToEvaluateOnLoadResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveScriptToEvaluateOnLoadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveScriptToEvaluateOnLoadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveScriptToEvaluateOnLoadResult build() {
                return new RemoveScriptToEvaluateOnLoadResult(values);
            }
        }
    }
    /**
     * Removes given script from the list.
     */
    public static final class RemoveScriptToEvaluateOnNewDocumentParams extends CdpObject {
        private RemoveScriptToEvaluateOnNewDocumentParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveScriptToEvaluateOnNewDocumentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveScriptToEvaluateOnNewDocumentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            public RemoveScriptToEvaluateOnNewDocumentParams build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                return new RemoveScriptToEvaluateOnNewDocumentParams(values);
            }
        }
    }
    /**
     * Removes given script from the list.
     */
    public static final class RemoveScriptToEvaluateOnNewDocumentResult extends CdpObject {
        private RemoveScriptToEvaluateOnNewDocumentResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveScriptToEvaluateOnNewDocumentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveScriptToEvaluateOnNewDocumentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveScriptToEvaluateOnNewDocumentResult build() {
                return new RemoveScriptToEvaluateOnNewDocumentResult(values);
            }
        }
    }
    /**
     * Acknowledges that a screencast frame has been received by the frontend.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameAckParams extends CdpObject {
        private ScreencastFrameAckParams(Map<String, Object> values) { super(values); }
        @Nullable public static ScreencastFrameAckParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreencastFrameAckParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame number.
         * @return the protocol field value
         */
        @Nullable public Long sessionId() {
            return numberAsLong(value("sessionId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame number.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable Long value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            public ScreencastFrameAckParams build() {
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                return new ScreencastFrameAckParams(values);
            }
        }
    }
    /**
     * Acknowledges that a screencast frame has been received by the frontend.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameAckResult extends CdpObject {
        private ScreencastFrameAckResult(Map<String, Object> values) { super(values); }
        @Nullable public static ScreencastFrameAckResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreencastFrameAckResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ScreencastFrameAckResult build() {
                return new ScreencastFrameAckResult(values);
            }
        }
    }
    /**
     * Searches for given string in resource content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SearchInResourceParams extends CdpObject {
        private SearchInResourceParams(Map<String, Object> values) { super(values); }
        @Nullable public static SearchInResourceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchInResourceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame id for resource to search in.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * URL of the resource to search in.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        @Nullable public String query() {
            return (String) value("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value
         */
        @Nullable public Boolean caseSensitive() {
            return (Boolean) value("caseSensitive");
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value
         */
        @Nullable public Boolean isRegex() {
            return (Boolean) value("isRegex");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame id for resource to search in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * URL of the resource to search in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * String to search for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder query(@Nullable String value) {
                if (value == null) values.remove("query");
                else values.put("query", jsonValue(value));
                return this;
            }
            /**
             * If true, search is case sensitive.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder caseSensitive(@Nullable Boolean value) {
                if (value == null) values.remove("caseSensitive");
                else values.put("caseSensitive", jsonValue(value));
                return this;
            }
            /**
             * If true, treats string parameter as regex.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isRegex(@Nullable Boolean value) {
                if (value == null) values.remove("isRegex");
                else values.put("isRegex", jsonValue(value));
                return this;
            }
            public SearchInResourceParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("query")) throw new IllegalStateException("Missing required CDP field: query");
                return new SearchInResourceParams(values);
            }
        }
    }
    /**
     * Searches for given string in resource content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SearchInResourceResult extends CdpObject {
        private SearchInResourceResult(Map<String, Object> values) { super(values); }
        @Nullable public static SearchInResourceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchInResourceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of search matches.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.SearchMatch> result() {
            return list(value("result"), element0 -> Debugger.SearchMatch.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of search matches.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Debugger.SearchMatch> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public SearchInResourceResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new SearchInResourceResult(values);
            }
        }
    }
    /**
     * Enable Chrome&#x27;s experimental ad filter on all sites.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAdBlockingEnabledParams extends CdpObject {
        private SetAdBlockingEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAdBlockingEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAdBlockingEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to block ads.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to block ads.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetAdBlockingEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetAdBlockingEnabledParams(values);
            }
        }
    }
    /**
     * Enable Chrome&#x27;s experimental ad filter on all sites.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAdBlockingEnabledResult extends CdpObject {
        private SetAdBlockingEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAdBlockingEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAdBlockingEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAdBlockingEnabledResult build() {
                return new SetAdBlockingEnabledResult(values);
            }
        }
    }
    /**
     * Enable page Content Security Policy by-passing.
     */
    public static final class SetBypassCSPParams extends CdpObject {
        private SetBypassCSPParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBypassCSPParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBypassCSPParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to bypass page CSP.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to bypass page CSP.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetBypassCSPParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetBypassCSPParams(values);
            }
        }
    }
    /**
     * Enable page Content Security Policy by-passing.
     */
    public static final class SetBypassCSPResult extends CdpObject {
        private SetBypassCSPResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBypassCSPResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBypassCSPResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBypassCSPResult build() {
                return new SetBypassCSPResult(values);
            }
        }
    }
    /**
     * Get Permissions Policy state on given frame.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetPermissionsPolicyStateParams extends CdpObject {
        private GetPermissionsPolicyStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetPermissionsPolicyStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPermissionsPolicyStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetPermissionsPolicyStateParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new GetPermissionsPolicyStateParams(values);
            }
        }
    }
    /**
     * Get Permissions Policy state on given frame.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetPermissionsPolicyStateResult extends CdpObject {
        private GetPermissionsPolicyStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetPermissionsPolicyStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPermissionsPolicyStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the states field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.PermissionsPolicyFeatureState> states() {
            return list(value("states"), element0 -> Page.PermissionsPolicyFeatureState.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the states field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder states(@Nullable java.util.List<Page.PermissionsPolicyFeatureState> value) {
                if (value == null) values.remove("states");
                else values.put("states", jsonValue(value));
                return this;
            }
            public GetPermissionsPolicyStateResult build() {
                if (!values.containsKey("states")) throw new IllegalStateException("Missing required CDP field: states");
                return new GetPermissionsPolicyStateResult(values);
            }
        }
    }
    /**
     * Get Origin Trials on given frame.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetOriginTrialsParams extends CdpObject {
        private GetOriginTrialsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetOriginTrialsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOriginTrialsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetOriginTrialsParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new GetOriginTrialsParams(values);
            }
        }
    }
    /**
     * Get Origin Trials on given frame.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetOriginTrialsResult extends CdpObject {
        private GetOriginTrialsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetOriginTrialsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOriginTrialsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the originTrials field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.OriginTrial> originTrials() {
            return list(value("originTrials"), element0 -> Page.OriginTrial.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the originTrials field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originTrials(@Nullable java.util.List<Page.OriginTrial> value) {
                if (value == null) values.remove("originTrials");
                else values.put("originTrials", jsonValue(value));
                return this;
            }
            public GetOriginTrialsResult build() {
                if (!values.containsKey("originTrials")) throw new IllegalStateException("Missing required CDP field: originTrials");
                return new GetOriginTrialsResult(values);
            }
        }
    }
    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value
         */
        @Nullable public Long screenWidth() {
            return numberAsLong(value("screenWidth"));
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value
         */
        @Nullable public Long screenHeight() {
            return numberAsLong(value("screenHeight"));
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value
         */
        @Nullable public Long positionX() {
            return numberAsLong(value("positionX"));
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value
         */
        @Nullable public Long positionY() {
            return numberAsLong(value("positionY"));
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
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
         * The viewport dimensions and scale. If not set, the override is cleared.
         * @return the protocol field value
         */
        @Nullable public Page.Viewport viewport() {
            return Page.Viewport.fromMap(objectMap(value("viewport")));
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
             * The viewport dimensions and scale. If not set, the override is cleared.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder viewport(@Nullable Page.Viewport value) {
                if (value == null) values.remove("viewport");
                else values.put("viewport", jsonValue(value));
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
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * Overrides the Device Orientation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDeviceOrientationOverrideParams extends CdpObject {
        private SetDeviceOrientationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDeviceOrientationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDeviceOrientationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Mock alpha
         * @return the protocol field value
         */
        @Nullable public Double alpha() {
            return numberAsDouble(value("alpha"));
        }
        /**
         * Mock beta
         * @return the protocol field value
         */
        @Nullable public Double beta() {
            return numberAsDouble(value("beta"));
        }
        /**
         * Mock gamma
         * @return the protocol field value
         */
        @Nullable public Double gamma() {
            return numberAsDouble(value("gamma"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Mock alpha
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder alpha(@Nullable Double value) {
                if (value == null) values.remove("alpha");
                else values.put("alpha", jsonValue(value));
                return this;
            }
            /**
             * Mock beta
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder beta(@Nullable Double value) {
                if (value == null) values.remove("beta");
                else values.put("beta", jsonValue(value));
                return this;
            }
            /**
             * Mock gamma
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gamma(@Nullable Double value) {
                if (value == null) values.remove("gamma");
                else values.put("gamma", jsonValue(value));
                return this;
            }
            public SetDeviceOrientationOverrideParams build() {
                if (!values.containsKey("alpha")) throw new IllegalStateException("Missing required CDP field: alpha");
                if (!values.containsKey("beta")) throw new IllegalStateException("Missing required CDP field: beta");
                if (!values.containsKey("gamma")) throw new IllegalStateException("Missing required CDP field: gamma");
                return new SetDeviceOrientationOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the Device Orientation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDeviceOrientationOverrideResult extends CdpObject {
        private SetDeviceOrientationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDeviceOrientationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDeviceOrientationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDeviceOrientationOverrideResult build() {
                return new SetDeviceOrientationOverrideResult(values);
            }
        }
    }
    /**
     * Set generic font families.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFontFamiliesParams extends CdpObject {
        private SetFontFamiliesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetFontFamiliesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFontFamiliesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Specifies font families to set. If a font family is not specified, it won&#x27;t be changed.
         * @return the protocol field value
         */
        @Nullable public Page.FontFamilies fontFamilies() {
            return Page.FontFamilies.fromMap(objectMap(value("fontFamilies")));
        }
        /**
         * Specifies font families to set for individual scripts.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.ScriptFontFamilies> forScripts() {
            return list(value("forScripts"), element0 -> Page.ScriptFontFamilies.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Specifies font families to set. If a font family is not specified, it won&#x27;t be changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontFamilies(@Nullable Page.FontFamilies value) {
                if (value == null) values.remove("fontFamilies");
                else values.put("fontFamilies", jsonValue(value));
                return this;
            }
            /**
             * Specifies font families to set for individual scripts.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder forScripts(@Nullable java.util.List<Page.ScriptFontFamilies> value) {
                if (value == null) values.remove("forScripts");
                else values.put("forScripts", jsonValue(value));
                return this;
            }
            public SetFontFamiliesParams build() {
                if (!values.containsKey("fontFamilies")) throw new IllegalStateException("Missing required CDP field: fontFamilies");
                return new SetFontFamiliesParams(values);
            }
        }
    }
    /**
     * Set generic font families.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFontFamiliesResult extends CdpObject {
        private SetFontFamiliesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetFontFamiliesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFontFamiliesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetFontFamiliesResult build() {
                return new SetFontFamiliesResult(values);
            }
        }
    }
    /**
     * Set default font sizes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFontSizesParams extends CdpObject {
        private SetFontSizesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetFontSizesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFontSizesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Specifies font sizes to set. If a font size is not specified, it won&#x27;t be changed.
         * @return the protocol field value
         */
        @Nullable public Page.FontSizes fontSizes() {
            return Page.FontSizes.fromMap(objectMap(value("fontSizes")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Specifies font sizes to set. If a font size is not specified, it won&#x27;t be changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontSizes(@Nullable Page.FontSizes value) {
                if (value == null) values.remove("fontSizes");
                else values.put("fontSizes", jsonValue(value));
                return this;
            }
            public SetFontSizesParams build() {
                if (!values.containsKey("fontSizes")) throw new IllegalStateException("Missing required CDP field: fontSizes");
                return new SetFontSizesParams(values);
            }
        }
    }
    /**
     * Set default font sizes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFontSizesResult extends CdpObject {
        private SetFontSizesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetFontSizesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFontSizesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetFontSizesResult build() {
                return new SetFontSizesResult(values);
            }
        }
    }
    /**
     * Sets given markup as the document&#x27;s HTML.
     */
    public static final class SetDocumentContentParams extends CdpObject {
        private SetDocumentContentParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDocumentContentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDocumentContentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame id to set HTML for.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * HTML content to set.
         * @return the protocol field value
         */
        @Nullable public String html() {
            return (String) value("html");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame id to set HTML for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * HTML content to set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder html(@Nullable String value) {
                if (value == null) values.remove("html");
                else values.put("html", jsonValue(value));
                return this;
            }
            public SetDocumentContentParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("html")) throw new IllegalStateException("Missing required CDP field: html");
                return new SetDocumentContentParams(values);
            }
        }
    }
    /**
     * Sets given markup as the document&#x27;s HTML.
     */
    public static final class SetDocumentContentResult extends CdpObject {
        private SetDocumentContentResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDocumentContentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDocumentContentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDocumentContentResult build() {
                return new SetDocumentContentResult(values);
            }
        }
    }
    /**
     * Set the behavior when downloading a file.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDownloadBehaviorParams extends CdpObject {
        private SetDownloadBehaviorParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDownloadBehaviorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDownloadBehaviorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
         * @return the protocol field value
         */
        @Nullable public String behavior() {
            return (String) value("behavior");
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
         */
        public static final class BehaviorValues {
            private BehaviorValues() {}
            public static final String DENY = "deny";
            public static final String ALLOW = "allow";
            public static final String DEFAULT = "default";
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27;
         * @return the protocol field value
         */
        @Nullable public String downloadPath() {
            return (String) value("downloadPath");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder behavior(@Nullable String value) {
                if (value == null) values.remove("behavior");
                else values.put("behavior", jsonValue(value));
                return this;
            }
            /**
             * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder downloadPath(@Nullable String value) {
                if (value == null) values.remove("downloadPath");
                else values.put("downloadPath", jsonValue(value));
                return this;
            }
            public SetDownloadBehaviorParams build() {
                if (!values.containsKey("behavior")) throw new IllegalStateException("Missing required CDP field: behavior");
                return new SetDownloadBehaviorParams(values);
            }
        }
    }
    /**
     * Set the behavior when downloading a file.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDownloadBehaviorResult extends CdpObject {
        private SetDownloadBehaviorResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDownloadBehaviorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDownloadBehaviorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDownloadBehaviorResult build() {
                return new SetDownloadBehaviorResult(values);
            }
        }
    }
    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
            public SetGeolocationOverrideParams build() {
                return new SetGeolocationOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * Controls whether page will emit lifecycle events.
     */
    public static final class SetLifecycleEventsEnabledParams extends CdpObject {
        private SetLifecycleEventsEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetLifecycleEventsEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetLifecycleEventsEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true, starts emitting lifecycle events.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true, starts emitting lifecycle events.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetLifecycleEventsEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetLifecycleEventsEnabledParams(values);
            }
        }
    }
    /**
     * Controls whether page will emit lifecycle events.
     */
    public static final class SetLifecycleEventsEnabledResult extends CdpObject {
        private SetLifecycleEventsEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetLifecycleEventsEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetLifecycleEventsEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetLifecycleEventsEnabledResult build() {
                return new SetLifecycleEventsEnabledResult(values);
            }
        }
    }
    /**
     * Toggles mouse event-based touch event emulation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
             * Touch/gesture events configuration. Default: current platform.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder configuration(@Nullable String value) {
                if (value == null) values.remove("configuration");
                else values.put("configuration", jsonValue(value));
                return this;
            }
            public SetTouchEmulationEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetTouchEmulationEnabledParams(values);
            }
        }
    }
    /**
     * Toggles mouse event-based touch event emulation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
     * Starts sending each frame using the {@code screencastFrame} event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StartScreencastParams extends CdpObject {
        private StartScreencastParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartScreencastParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartScreencastParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Image compression format.
         * @return the protocol field value
         */
        @Nullable public String format() {
            return (String) value("format");
        }
        /**
         * Image compression format.
         */
        public static final class FormatValues {
            private FormatValues() {}
            public static final String JPEG = "jpeg";
            public static final String PNG = "png";
        }
        /**
         * Compression quality from range [0..100].
         * @return the protocol field value
         */
        @Nullable public Long quality() {
            return numberAsLong(value("quality"));
        }
        /**
         * Maximum screenshot width.
         * @return the protocol field value
         */
        @Nullable public Long maxWidth() {
            return numberAsLong(value("maxWidth"));
        }
        /**
         * Maximum screenshot height.
         * @return the protocol field value
         */
        @Nullable public Long maxHeight() {
            return numberAsLong(value("maxHeight"));
        }
        /**
         * Send every n-th frame.
         * @return the protocol field value
         */
        @Nullable public Long everyNthFrame() {
            return numberAsLong(value("everyNthFrame"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Image compression format.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder format(@Nullable String value) {
                if (value == null) values.remove("format");
                else values.put("format", jsonValue(value));
                return this;
            }
            /**
             * Compression quality from range [0..100].
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quality(@Nullable Long value) {
                if (value == null) values.remove("quality");
                else values.put("quality", jsonValue(value));
                return this;
            }
            /**
             * Maximum screenshot width.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxWidth(@Nullable Long value) {
                if (value == null) values.remove("maxWidth");
                else values.put("maxWidth", jsonValue(value));
                return this;
            }
            /**
             * Maximum screenshot height.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxHeight(@Nullable Long value) {
                if (value == null) values.remove("maxHeight");
                else values.put("maxHeight", jsonValue(value));
                return this;
            }
            /**
             * Send every n-th frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder everyNthFrame(@Nullable Long value) {
                if (value == null) values.remove("everyNthFrame");
                else values.put("everyNthFrame", jsonValue(value));
                return this;
            }
            public StartScreencastParams build() {
                return new StartScreencastParams(values);
            }
        }
    }
    /**
     * Starts sending each frame using the {@code screencastFrame} event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StartScreencastResult extends CdpObject {
        private StartScreencastResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartScreencastResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartScreencastResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartScreencastResult build() {
                return new StartScreencastResult(values);
            }
        }
    }
    /**
     * Force the page stop all navigations and pending resource fetches.
     */
    public static final class StopLoadingParams extends CdpObject {
        private StopLoadingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopLoadingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopLoadingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopLoadingParams build() {
                return new StopLoadingParams(values);
            }
        }
    }
    /**
     * Force the page stop all navigations and pending resource fetches.
     */
    public static final class StopLoadingResult extends CdpObject {
        private StopLoadingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopLoadingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopLoadingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopLoadingResult build() {
                return new StopLoadingResult(values);
            }
        }
    }
    /**
     * Crashes renderer on the IO thread, generates minidumps.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrashParams extends CdpObject {
        private CrashParams(Map<String, Object> values) { super(values); }
        @Nullable public static CrashParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CrashParams build() {
                return new CrashParams(values);
            }
        }
    }
    /**
     * Crashes renderer on the IO thread, generates minidumps.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrashResult extends CdpObject {
        private CrashResult(Map<String, Object> values) { super(values); }
        @Nullable public static CrashResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CrashResult build() {
                return new CrashResult(values);
            }
        }
    }
    /**
     * Tries to close page, running its beforeunload hooks, if any.
     */
    public static final class CloseParams extends CdpObject {
        private CloseParams(Map<String, Object> values) { super(values); }
        @Nullable public static CloseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CloseParams build() {
                return new CloseParams(values);
            }
        }
    }
    /**
     * Tries to close page, running its beforeunload hooks, if any.
     */
    public static final class CloseResult extends CdpObject {
        private CloseResult(Map<String, Object> values) { super(values); }
        @Nullable public static CloseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CloseResult build() {
                return new CloseResult(values);
            }
        }
    }
    /**
     * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetWebLifecycleStateParams extends CdpObject {
        private SetWebLifecycleStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetWebLifecycleStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetWebLifecycleStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Target lifecycle state
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        /**
         * Target lifecycle state
         */
        public static final class StateValues {
            private StateValues() {}
            public static final String FROZEN = "frozen";
            public static final String ACTIVE = "active";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Target lifecycle state
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            public SetWebLifecycleStateParams build() {
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                return new SetWebLifecycleStateParams(values);
            }
        }
    }
    /**
     * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetWebLifecycleStateResult extends CdpObject {
        private SetWebLifecycleStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetWebLifecycleStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetWebLifecycleStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetWebLifecycleStateResult build() {
                return new SetWebLifecycleStateResult(values);
            }
        }
    }
    /**
     * Stops sending each frame in the {@code screencastFrame}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StopScreencastParams extends CdpObject {
        private StopScreencastParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopScreencastParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopScreencastParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopScreencastParams build() {
                return new StopScreencastParams(values);
            }
        }
    }
    /**
     * Stops sending each frame in the {@code screencastFrame}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StopScreencastResult extends CdpObject {
        private StopScreencastResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopScreencastResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopScreencastResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopScreencastResult build() {
                return new StopScreencastResult(values);
            }
        }
    }
    /**
     * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ProduceCompilationCacheParams extends CdpObject {
        private ProduceCompilationCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static ProduceCompilationCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProduceCompilationCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scripts field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.CompilationCacheParams> scripts() {
            return list(value("scripts"), element0 -> Page.CompilationCacheParams.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scripts field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scripts(@Nullable java.util.List<Page.CompilationCacheParams> value) {
                if (value == null) values.remove("scripts");
                else values.put("scripts", jsonValue(value));
                return this;
            }
            public ProduceCompilationCacheParams build() {
                if (!values.containsKey("scripts")) throw new IllegalStateException("Missing required CDP field: scripts");
                return new ProduceCompilationCacheParams(values);
            }
        }
    }
    /**
     * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ProduceCompilationCacheResult extends CdpObject {
        private ProduceCompilationCacheResult(Map<String, Object> values) { super(values); }
        @Nullable public static ProduceCompilationCacheResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProduceCompilationCacheResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ProduceCompilationCacheResult build() {
                return new ProduceCompilationCacheResult(values);
            }
        }
    }
    /**
     * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AddCompilationCacheParams extends CdpObject {
        private AddCompilationCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddCompilationCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddCompilationCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Base64-encoded data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Base64-encoded data (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public AddCompilationCacheParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new AddCompilationCacheParams(values);
            }
        }
    }
    /**
     * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AddCompilationCacheResult extends CdpObject {
        private AddCompilationCacheResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddCompilationCacheResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddCompilationCacheResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AddCompilationCacheResult build() {
                return new AddCompilationCacheResult(values);
            }
        }
    }
    /**
     * Clears seeded compilation cache.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearCompilationCacheParams extends CdpObject {
        private ClearCompilationCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearCompilationCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearCompilationCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearCompilationCacheParams build() {
                return new ClearCompilationCacheParams(values);
            }
        }
    }
    /**
     * Clears seeded compilation cache.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearCompilationCacheResult extends CdpObject {
        private ClearCompilationCacheResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearCompilationCacheResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearCompilationCacheResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearCompilationCacheResult build() {
                return new ClearCompilationCacheResult(values);
            }
        }
    }
    /**
     * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSPCTransactionModeParams extends CdpObject {
        private SetSPCTransactionModeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSPCTransactionModeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSPCTransactionModeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the mode field.
         * @return the protocol field value
         */
        @Nullable public String mode() {
            return (String) value("mode");
        }
        /**
         * Wire values for ModeValues.
         */
        public static final class ModeValues {
            private ModeValues() {}
            public static final String NONE = "none";
            public static final String AUTOACCEPT = "autoAccept";
            public static final String AUTOCHOOSETOAUTHANOTHERWAY = "autoChooseToAuthAnotherWay";
            public static final String AUTOREJECT = "autoReject";
            public static final String AUTOOPTOUT = "autoOptOut";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the mode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mode(@Nullable String value) {
                if (value == null) values.remove("mode");
                else values.put("mode", jsonValue(value));
                return this;
            }
            public SetSPCTransactionModeParams build() {
                if (!values.containsKey("mode")) throw new IllegalStateException("Missing required CDP field: mode");
                return new SetSPCTransactionModeParams(values);
            }
        }
    }
    /**
     * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSPCTransactionModeResult extends CdpObject {
        private SetSPCTransactionModeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSPCTransactionModeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSPCTransactionModeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSPCTransactionModeResult build() {
                return new SetSPCTransactionModeResult(values);
            }
        }
    }
    /**
     * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetRPHRegistrationModeParams extends CdpObject {
        private SetRPHRegistrationModeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetRPHRegistrationModeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRPHRegistrationModeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the mode field.
         * @return the protocol field value
         */
        @Nullable public String mode() {
            return (String) value("mode");
        }
        /**
         * Wire values for ModeValues.
         */
        public static final class ModeValues {
            private ModeValues() {}
            public static final String NONE = "none";
            public static final String AUTOACCEPT = "autoAccept";
            public static final String AUTOREJECT = "autoReject";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the mode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mode(@Nullable String value) {
                if (value == null) values.remove("mode");
                else values.put("mode", jsonValue(value));
                return this;
            }
            public SetRPHRegistrationModeParams build() {
                if (!values.containsKey("mode")) throw new IllegalStateException("Missing required CDP field: mode");
                return new SetRPHRegistrationModeParams(values);
            }
        }
    }
    /**
     * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetRPHRegistrationModeResult extends CdpObject {
        private SetRPHRegistrationModeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetRPHRegistrationModeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRPHRegistrationModeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetRPHRegistrationModeResult build() {
                return new SetRPHRegistrationModeResult(values);
            }
        }
    }
    /**
     * Generates a report for testing.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GenerateTestReportParams extends CdpObject {
        private GenerateTestReportParams(Map<String, Object> values) { super(values); }
        @Nullable public static GenerateTestReportParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GenerateTestReportParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Message to be displayed in the report.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        /**
         * Specifies the endpoint group to deliver the report to.
         * @return the protocol field value
         */
        @Nullable public String group() {
            return (String) value("group");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Message to be displayed in the report.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * Specifies the endpoint group to deliver the report to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder group(@Nullable String value) {
                if (value == null) values.remove("group");
                else values.put("group", jsonValue(value));
                return this;
            }
            public GenerateTestReportParams build() {
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                return new GenerateTestReportParams(values);
            }
        }
    }
    /**
     * Generates a report for testing.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GenerateTestReportResult extends CdpObject {
        private GenerateTestReportResult(Map<String, Object> values) { super(values); }
        @Nullable public static GenerateTestReportResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GenerateTestReportResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GenerateTestReportResult build() {
                return new GenerateTestReportResult(values);
            }
        }
    }
    /**
     * Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WaitForDebuggerParams extends CdpObject {
        private WaitForDebuggerParams(Map<String, Object> values) { super(values); }
        @Nullable public static WaitForDebuggerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WaitForDebuggerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public WaitForDebuggerParams build() {
                return new WaitForDebuggerParams(values);
            }
        }
    }
    /**
     * Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WaitForDebuggerResult extends CdpObject {
        private WaitForDebuggerResult(Map<String, Object> values) { super(values); }
        @Nullable public static WaitForDebuggerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WaitForDebuggerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public WaitForDebuggerResult build() {
                return new WaitForDebuggerResult(values);
            }
        }
    }
    /**
     * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
     */
    public static final class SetInterceptFileChooserDialogParams extends CdpObject {
        private SetInterceptFileChooserDialogParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterceptFileChooserDialogParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterceptFileChooserDialogParams(values);
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
         * If true, cancels the dialog by emitting relevant events (if any) in addition to not showing it if the interception is enabled (default: false).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean cancel() {
            return (Boolean) value("cancel");
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
             * If true, cancels the dialog by emitting relevant events (if any) in addition to not showing it if the interception is enabled (default: false).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cancel(@Nullable Boolean value) {
                if (value == null) values.remove("cancel");
                else values.put("cancel", jsonValue(value));
                return this;
            }
            public SetInterceptFileChooserDialogParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetInterceptFileChooserDialogParams(values);
            }
        }
    }
    /**
     * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
     */
    public static final class SetInterceptFileChooserDialogResult extends CdpObject {
        private SetInterceptFileChooserDialogResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterceptFileChooserDialogResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterceptFileChooserDialogResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInterceptFileChooserDialogResult build() {
                return new SetInterceptFileChooserDialogResult(values);
            }
        }
    }
    /**
     * Enable/disable prerendering manually.
     * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
     * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPrerenderingAllowedParams extends CdpObject {
        private SetPrerenderingAllowedParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPrerenderingAllowedParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPrerenderingAllowedParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the isAllowed field.
         * @return the protocol field value
         */
        @Nullable public Boolean isAllowed() {
            return (Boolean) value("isAllowed");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the isAllowed field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isAllowed(@Nullable Boolean value) {
                if (value == null) values.remove("isAllowed");
                else values.put("isAllowed", jsonValue(value));
                return this;
            }
            public SetPrerenderingAllowedParams build() {
                if (!values.containsKey("isAllowed")) throw new IllegalStateException("Missing required CDP field: isAllowed");
                return new SetPrerenderingAllowedParams(values);
            }
        }
    }
    /**
     * Enable/disable prerendering manually.
     * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
     * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPrerenderingAllowedResult extends CdpObject {
        private SetPrerenderingAllowedResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPrerenderingAllowedResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPrerenderingAllowedResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPrerenderingAllowedResult build() {
                return new SetPrerenderingAllowedResult(values);
            }
        }
    }
    /**
     * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnnotatedPageContentParams extends CdpObject {
        private GetAnnotatedPageContentParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAnnotatedPageContentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAnnotatedPageContentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to include actionable information. Defaults to true.
         * @return the protocol field value
         */
        @Nullable public Boolean includeActionableInformation() {
            return (Boolean) value("includeActionableInformation");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to include actionable information. Defaults to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeActionableInformation(@Nullable Boolean value) {
                if (value == null) values.remove("includeActionableInformation");
                else values.put("includeActionableInformation", jsonValue(value));
                return this;
            }
            public GetAnnotatedPageContentParams build() {
                return new GetAnnotatedPageContentParams(values);
            }
        }
    }
    /**
     * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnnotatedPageContentResult extends CdpObject {
        private GetAnnotatedPageContentResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAnnotatedPageContentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAnnotatedPageContentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The annotated page content as a base64 encoded protobuf. The format is defined by the {@code AnnotatedPageContent} message in components/optimization_guide/proto/features/common_quality_data.proto (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String content() {
            return (String) value("content");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The annotated page content as a base64 encoded protobuf. The format is defined by the {@code AnnotatedPageContent} message in components/optimization_guide/proto/features/common_quality_data.proto (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder content(@Nullable String value) {
                if (value == null) values.remove("content");
                else values.put("content", jsonValue(value));
                return this;
            }
            public GetAnnotatedPageContentResult build() {
                if (!values.containsKey("content")) throw new IllegalStateException("Missing required CDP field: content");
                return new GetAnnotatedPageContentResult(values);
            }
        }
    }
    /**
     * Payload of the Page.domContentEventFired event.
     */
    public static final class DomContentEventFiredEvent extends CdpObject {
        private DomContentEventFiredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DomContentEventFiredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DomContentEventFiredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DomContentEventFiredEvent build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DomContentEventFiredEvent(values);
            }
        }
    }
    /**
     * Emitted only when {@code page.interceptFileChooser} is enabled.
     */
    public static final class FileChooserOpenedEvent extends CdpObject {
        private FileChooserOpenedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FileChooserOpenedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FileChooserOpenedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame containing input node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Input mode.
         * @return the protocol field value
         */
        @Nullable public String mode() {
            return (String) value("mode");
        }
        /**
         * Input mode.
         */
        public static final class ModeValues {
            private ModeValues() {}
            public static final String SELECTSINGLE = "selectSingle";
            public static final String SELECTMULTIPLE = "selectMultiple";
        }
        /**
         * Input node id. Only present for file choosers opened via an {@code &lt;input type=&quot;file&quot;&gt;} element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame containing input node.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Input mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mode(@Nullable String value) {
                if (value == null) values.remove("mode");
                else values.put("mode", jsonValue(value));
                return this;
            }
            /**
             * Input node id. Only present for file choosers opened via an {@code &lt;input type=&quot;file&quot;&gt;} element.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public FileChooserOpenedEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("mode")) throw new IllegalStateException("Missing required CDP field: mode");
                return new FileChooserOpenedEvent(values);
            }
        }
    }
    /**
     * Fired when frame has been attached to its parent.
     */
    public static final class FrameAttachedEvent extends CdpObject {
        private FrameAttachedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameAttachedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameAttachedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that has been attached.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Parent frame identifier.
         * @return the protocol field value
         */
        @Nullable public String parentFrameId() {
            return (String) value("parentFrameId");
        }
        /**
         * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stack() {
            return Runtime.StackTrace.fromMap(objectMap(value("stack")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that has been attached.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Parent frame identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentFrameId(@Nullable String value) {
                if (value == null) values.remove("parentFrameId");
                else values.put("parentFrameId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stack(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stack");
                else values.put("stack", jsonValue(value));
                return this;
            }
            public FrameAttachedEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("parentFrameId")) throw new IllegalStateException("Missing required CDP field: parentFrameId");
                return new FrameAttachedEvent(values);
            }
        }
    }
    /**
     * Fired when frame no longer has a scheduled navigation.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class FrameClearedScheduledNavigationEvent extends CdpObject {
        private FrameClearedScheduledNavigationEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameClearedScheduledNavigationEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameClearedScheduledNavigationEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that has cleared its scheduled navigation.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that has cleared its scheduled navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public FrameClearedScheduledNavigationEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new FrameClearedScheduledNavigationEvent(values);
            }
        }
    }
    /**
     * Fired when frame has been detached from its parent.
     */
    public static final class FrameDetachedEvent extends CdpObject {
        private FrameDetachedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameDetachedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameDetachedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that has been detached.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Returns the reason field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * Wire values for ReasonValues.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class ReasonValues {
            private ReasonValues() {}
            public static final String REMOVE = "remove";
            public static final String SWAP = "swap";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that has been detached.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Sets the reason field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            public FrameDetachedEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                return new FrameDetachedEvent(values);
            }
        }
    }
    /**
     * Fired before frame subtree is detached. Emitted before any frame of the subtree is actually detached.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameSubtreeWillBeDetachedEvent extends CdpObject {
        private FrameSubtreeWillBeDetachedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameSubtreeWillBeDetachedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameSubtreeWillBeDetachedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that is the root of the subtree that will be detached.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that is the root of the subtree that will be detached.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public FrameSubtreeWillBeDetachedEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new FrameSubtreeWillBeDetachedEvent(values);
            }
        }
    }
    /**
     * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
     */
    public static final class FrameNavigatedEvent extends CdpObject {
        private FrameNavigatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameNavigatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameNavigatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame object.
         * @return the protocol field value
         */
        @Nullable public Page.Frame frame() {
            return Page.Frame.fromMap(objectMap(value("frame")));
        }
        /**
         * Returns the type field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frame(@Nullable Page.Frame value) {
                if (value == null) values.remove("frame");
                else values.put("frame", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public FrameNavigatedEvent build() {
                if (!values.containsKey("frame")) throw new IllegalStateException("Missing required CDP field: frame");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new FrameNavigatedEvent(values);
            }
        }
    }
    /**
     * Fired when opening document to write to.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DocumentOpenedEvent extends CdpObject {
        private DocumentOpenedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DocumentOpenedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DocumentOpenedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame object.
         * @return the protocol field value
         */
        @Nullable public Page.Frame frame() {
            return Page.Frame.fromMap(objectMap(value("frame")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frame(@Nullable Page.Frame value) {
                if (value == null) values.remove("frame");
                else values.put("frame", jsonValue(value));
                return this;
            }
            public DocumentOpenedEvent build() {
                if (!values.containsKey("frame")) throw new IllegalStateException("Missing required CDP field: frame");
                return new DocumentOpenedEvent(values);
            }
        }
    }
    /**
     * Payload of the Page.frameResized event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameResizedEvent extends CdpObject {
        private FrameResizedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameResizedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameResizedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public FrameResizedEvent build() {
                return new FrameResizedEvent(values);
            }
        }
    }
    /**
     * Fired when a navigation starts. This event is fired for both renderer-initiated and browser-initiated navigations. For renderer-initiated navigations, the event is fired after {@code frameRequestedNavigation}. Navigation may still be cancelled after the event is issued. Multiple events can be fired for a single navigation, for example, when a same-document navigation becomes a cross-document navigation (such as in the case of a frameset).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameStartedNavigatingEvent extends CdpObject {
        private FrameStartedNavigatingEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameStartedNavigatingEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameStartedNavigatingEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of the frame that is being navigated.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * The URL the navigation started with. The final URL can be different.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Loader identifier. Even though it is present in case of same-document navigation, the previously committed loaderId would not change unless the navigation changes from a same-document to a cross-document navigation.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Returns the navigationType field.
         * @return the protocol field value
         */
        @Nullable public String navigationType() {
            return (String) value("navigationType");
        }
        /**
         * Wire values for NavigationTypeValues.
         */
        public static final class NavigationTypeValues {
            private NavigationTypeValues() {}
            public static final String RELOAD = "reload";
            public static final String RELOADBYPASSINGCACHE = "reloadBypassingCache";
            public static final String RESTORE = "restore";
            public static final String RESTOREWITHPOST = "restoreWithPost";
            public static final String HISTORYSAMEDOCUMENT = "historySameDocument";
            public static final String HISTORYDIFFERENTDOCUMENT = "historyDifferentDocument";
            public static final String SAMEDOCUMENT = "sameDocument";
            public static final String DIFFERENTDOCUMENT = "differentDocument";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of the frame that is being navigated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * The URL the navigation started with. The final URL can be different.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Loader identifier. Even though it is present in case of same-document navigation, the previously committed loaderId would not change unless the navigation changes from a same-document to a cross-document navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * Sets the navigationType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder navigationType(@Nullable String value) {
                if (value == null) values.remove("navigationType");
                else values.put("navigationType", jsonValue(value));
                return this;
            }
            public FrameStartedNavigatingEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("navigationType")) throw new IllegalStateException("Missing required CDP field: navigationType");
                return new FrameStartedNavigatingEvent(values);
            }
        }
    }
    /**
     * Fired when a renderer-initiated navigation is requested. Navigation may still be cancelled after the event is issued.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameRequestedNavigationEvent extends CdpObject {
        private FrameRequestedNavigationEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameRequestedNavigationEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameRequestedNavigationEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that is being navigated.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * The reason for the navigation.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * The destination URL for the requested navigation.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * The disposition for the navigation.
         * @return the protocol field value
         */
        @Nullable public String disposition() {
            return (String) value("disposition");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that is being navigated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * The reason for the navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            /**
             * The destination URL for the requested navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * The disposition for the navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disposition(@Nullable String value) {
                if (value == null) values.remove("disposition");
                else values.put("disposition", jsonValue(value));
                return this;
            }
            public FrameRequestedNavigationEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("disposition")) throw new IllegalStateException("Missing required CDP field: disposition");
                return new FrameRequestedNavigationEvent(values);
            }
        }
    }
    /**
     * Fired when frame schedules a potential navigation.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class FrameScheduledNavigationEvent extends CdpObject {
        private FrameScheduledNavigationEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameScheduledNavigationEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameScheduledNavigationEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that has scheduled a navigation.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
         * @return the protocol field value
         */
        @Nullable public Double delay() {
            return numberAsDouble(value("delay"));
        }
        /**
         * The reason for the navigation.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * The destination URL for the scheduled navigation.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that has scheduled a navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder delay(@Nullable Double value) {
                if (value == null) values.remove("delay");
                else values.put("delay", jsonValue(value));
                return this;
            }
            /**
             * The reason for the navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            /**
             * The destination URL for the scheduled navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public FrameScheduledNavigationEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("delay")) throw new IllegalStateException("Missing required CDP field: delay");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new FrameScheduledNavigationEvent(values);
            }
        }
    }
    /**
     * Fired when frame has started loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameStartedLoadingEvent extends CdpObject {
        private FrameStartedLoadingEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameStartedLoadingEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameStartedLoadingEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that has started loading.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that has started loading.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public FrameStartedLoadingEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new FrameStartedLoadingEvent(values);
            }
        }
    }
    /**
     * Fired when frame has stopped loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameStoppedLoadingEvent extends CdpObject {
        private FrameStoppedLoadingEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FrameStoppedLoadingEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FrameStoppedLoadingEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that has stopped loading.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that has stopped loading.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public FrameStoppedLoadingEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new FrameStoppedLoadingEvent(values);
            }
        }
    }
    /**
     * Fired when page is about to start a download. Deprecated. Use Browser.downloadWillBegin instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DownloadWillBeginEvent extends CdpObject {
        private DownloadWillBeginEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DownloadWillBeginEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DownloadWillBeginEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame that caused download to begin.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        @Nullable public String guid() {
            return (String) value("guid");
        }
        /**
         * URL of the resource being downloaded.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @return the protocol field value
         */
        @Nullable public String suggestedFilename() {
            return (String) value("suggestedFilename");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame that caused download to begin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Global unique identifier of the download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder guid(@Nullable String value) {
                if (value == null) values.remove("guid");
                else values.put("guid", jsonValue(value));
                return this;
            }
            /**
             * URL of the resource being downloaded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Suggested file name of the resource (the actual name of the file saved on disk may differ).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder suggestedFilename(@Nullable String value) {
                if (value == null) values.remove("suggestedFilename");
                else values.put("suggestedFilename", jsonValue(value));
                return this;
            }
            public DownloadWillBeginEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("guid")) throw new IllegalStateException("Missing required CDP field: guid");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("suggestedFilename")) throw new IllegalStateException("Missing required CDP field: suggestedFilename");
                return new DownloadWillBeginEvent(values);
            }
        }
    }
    /**
     * Fired when download makes progress. Last call has |done| == true. Deprecated. Use Browser.downloadProgress instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DownloadProgressEvent extends CdpObject {
        private DownloadProgressEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DownloadProgressEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DownloadProgressEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        @Nullable public String guid() {
            return (String) value("guid");
        }
        /**
         * Total expected bytes to download.
         * @return the protocol field value
         */
        @Nullable public Double totalBytes() {
            return numberAsDouble(value("totalBytes"));
        }
        /**
         * Total bytes received.
         * @return the protocol field value
         */
        @Nullable public Double receivedBytes() {
            return numberAsDouble(value("receivedBytes"));
        }
        /**
         * Download status.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        /**
         * Download status.
         */
        public static final class StateValues {
            private StateValues() {}
            public static final String INPROGRESS = "inProgress";
            public static final String COMPLETED = "completed";
            public static final String CANCELED = "canceled";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Global unique identifier of the download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder guid(@Nullable String value) {
                if (value == null) values.remove("guid");
                else values.put("guid", jsonValue(value));
                return this;
            }
            /**
             * Total expected bytes to download.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder totalBytes(@Nullable Double value) {
                if (value == null) values.remove("totalBytes");
                else values.put("totalBytes", jsonValue(value));
                return this;
            }
            /**
             * Total bytes received.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder receivedBytes(@Nullable Double value) {
                if (value == null) values.remove("receivedBytes");
                else values.put("receivedBytes", jsonValue(value));
                return this;
            }
            /**
             * Download status.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            public DownloadProgressEvent build() {
                if (!values.containsKey("guid")) throw new IllegalStateException("Missing required CDP field: guid");
                if (!values.containsKey("totalBytes")) throw new IllegalStateException("Missing required CDP field: totalBytes");
                if (!values.containsKey("receivedBytes")) throw new IllegalStateException("Missing required CDP field: receivedBytes");
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                return new DownloadProgressEvent(values);
            }
        }
    }
    /**
     * Fired when interstitial page was hidden
     */
    public static final class InterstitialHiddenEvent extends CdpObject {
        private InterstitialHiddenEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InterstitialHiddenEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InterstitialHiddenEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public InterstitialHiddenEvent build() {
                return new InterstitialHiddenEvent(values);
            }
        }
    }
    /**
     * Fired when interstitial page was shown
     */
    public static final class InterstitialShownEvent extends CdpObject {
        private InterstitialShownEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InterstitialShownEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InterstitialShownEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public InterstitialShownEvent build() {
                return new InterstitialShownEvent(values);
            }
        }
    }
    /**
     * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been closed.
     */
    public static final class JavascriptDialogClosedEvent extends CdpObject {
        private JavascriptDialogClosedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static JavascriptDialogClosedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new JavascriptDialogClosedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Whether dialog was confirmed.
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        /**
         * User input in case of prompt.
         * @return the protocol field value
         */
        @Nullable public String userInput() {
            return (String) value("userInput");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame id.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Whether dialog was confirmed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * User input in case of prompt.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userInput(@Nullable String value) {
                if (value == null) values.remove("userInput");
                else values.put("userInput", jsonValue(value));
                return this;
            }
            public JavascriptDialogClosedEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                if (!values.containsKey("userInput")) throw new IllegalStateException("Missing required CDP field: userInput");
                return new JavascriptDialogClosedEvent(values);
            }
        }
    }
    /**
     * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to open.
     */
    public static final class JavascriptDialogOpeningEvent extends CdpObject {
        private JavascriptDialogOpeningEvent(Map<String, Object> values) { super(values); }
        @Nullable public static JavascriptDialogOpeningEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new JavascriptDialogOpeningEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame url.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Frame id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Message that will be displayed by the dialog.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        /**
         * Dialog type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * True iff browser is capable showing or acting on the given dialog. When browser has no dialog handler for given target, calling alert while Page domain is engaged will stall the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
         * @return the protocol field value
         */
        @Nullable public Boolean hasBrowserHandler() {
            return (Boolean) value("hasBrowserHandler");
        }
        /**
         * Default dialog prompt.
         * @return the protocol field value
         */
        @Nullable public String defaultPrompt() {
            return (String) value("defaultPrompt");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Frame id.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Message that will be displayed by the dialog.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * Dialog type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * True iff browser is capable showing or acting on the given dialog. When browser has no dialog handler for given target, calling alert while Page domain is engaged will stall the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasBrowserHandler(@Nullable Boolean value) {
                if (value == null) values.remove("hasBrowserHandler");
                else values.put("hasBrowserHandler", jsonValue(value));
                return this;
            }
            /**
             * Default dialog prompt.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder defaultPrompt(@Nullable String value) {
                if (value == null) values.remove("defaultPrompt");
                else values.put("defaultPrompt", jsonValue(value));
                return this;
            }
            public JavascriptDialogOpeningEvent build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("hasBrowserHandler")) throw new IllegalStateException("Missing required CDP field: hasBrowserHandler");
                return new JavascriptDialogOpeningEvent(values);
            }
        }
    }
    /**
     * Fired for lifecycle events (navigation, load, paint, etc) in the current target (including local frames).
     */
    public static final class LifecycleEventEvent extends CdpObject {
        private LifecycleEventEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LifecycleEventEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LifecycleEventEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Loader identifier. Empty string if the request is fetched from worker.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
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
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public LifecycleEventEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new LifecycleEventEvent(values);
            }
        }
    }
    /**
     * Fired for failed bfcache history navigations if BackForwardCache feature is enabled. Do not assume any ordering with the Page.frameNavigated event. This event is fired only for main-frame history navigation where the document changes (non-same-document navigations), when bfcache navigation fails.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotUsedEvent extends CdpObject {
        private BackForwardCacheNotUsedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BackForwardCacheNotUsedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackForwardCacheNotUsedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The loader id for the associated navigation.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * The frame id of the associated frame.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Array of reasons why the page could not be cached. This must not be empty.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Page.BackForwardCacheNotRestoredExplanation> notRestoredExplanations() {
            return list(value("notRestoredExplanations"), element0 -> Page.BackForwardCacheNotRestoredExplanation.fromMap(objectMap(element0)));
        }
        /**
         * Tree structure of reasons why the page could not be cached for each frame.
         * @return the protocol field value
         */
        @Nullable public Page.BackForwardCacheNotRestoredExplanationTree notRestoredExplanationsTree() {
            return Page.BackForwardCacheNotRestoredExplanationTree.fromMap(objectMap(value("notRestoredExplanationsTree")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The loader id for the associated navigation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * The frame id of the associated frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Array of reasons why the page could not be cached. This must not be empty.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder notRestoredExplanations(@Nullable java.util.List<Page.BackForwardCacheNotRestoredExplanation> value) {
                if (value == null) values.remove("notRestoredExplanations");
                else values.put("notRestoredExplanations", jsonValue(value));
                return this;
            }
            /**
             * Tree structure of reasons why the page could not be cached for each frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder notRestoredExplanationsTree(@Nullable Page.BackForwardCacheNotRestoredExplanationTree value) {
                if (value == null) values.remove("notRestoredExplanationsTree");
                else values.put("notRestoredExplanationsTree", jsonValue(value));
                return this;
            }
            public BackForwardCacheNotUsedEvent build() {
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("notRestoredExplanations")) throw new IllegalStateException("Missing required CDP field: notRestoredExplanations");
                return new BackForwardCacheNotUsedEvent(values);
            }
        }
    }
    /**
     * Payload of the Page.loadEventFired event.
     */
    public static final class LoadEventFiredEvent extends CdpObject {
        private LoadEventFiredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LoadEventFiredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadEventFiredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public LoadEventFiredEvent build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new LoadEventFiredEvent(values);
            }
        }
    }
    /**
     * Fired when same-document navigation happens, e.g. due to history API usage or anchor navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NavigatedWithinDocumentEvent extends CdpObject {
        private NavigatedWithinDocumentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NavigatedWithinDocumentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigatedWithinDocumentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the frame.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Frame&#x27;s new url.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Navigation type
         * @return the protocol field value
         */
        @Nullable public String navigationType() {
            return (String) value("navigationType");
        }
        /**
         * Navigation type
         */
        public static final class NavigationTypeValues {
            private NavigationTypeValues() {}
            public static final String FRAGMENT = "fragment";
            public static final String HISTORYAPI = "historyApi";
            public static final String OTHER = "other";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Frame&#x27;s new url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Navigation type
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder navigationType(@Nullable String value) {
                if (value == null) values.remove("navigationType");
                else values.put("navigationType", jsonValue(value));
                return this;
            }
            public NavigatedWithinDocumentEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("navigationType")) throw new IllegalStateException("Missing required CDP field: navigationType");
                return new NavigatedWithinDocumentEvent(values);
            }
        }
    }
    /**
     * Compressed image data requested by the {@code startScreencast}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameEvent extends CdpObject {
        private ScreencastFrameEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScreencastFrameEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreencastFrameEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Base64-encoded compressed image. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Screencast frame metadata.
         * @return the protocol field value
         */
        @Nullable public Page.ScreencastFrameMetadata metadata() {
            return Page.ScreencastFrameMetadata.fromMap(objectMap(value("metadata")));
        }
        /**
         * Frame number.
         * @return the protocol field value
         */
        @Nullable public Long sessionId() {
            return numberAsLong(value("sessionId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Base64-encoded compressed image. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Screencast frame metadata.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metadata(@Nullable Page.ScreencastFrameMetadata value) {
                if (value == null) values.remove("metadata");
                else values.put("metadata", jsonValue(value));
                return this;
            }
            /**
             * Frame number.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable Long value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            public ScreencastFrameEvent build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                if (!values.containsKey("metadata")) throw new IllegalStateException("Missing required CDP field: metadata");
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                return new ScreencastFrameEvent(values);
            }
        }
    }
    /**
     * Fired when the page with currently enabled screencast was shown or hidden `.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastVisibilityChangedEvent extends CdpObject {
        private ScreencastVisibilityChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScreencastVisibilityChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreencastVisibilityChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True if the page is visible.
         * @return the protocol field value
         */
        @Nullable public Boolean visible() {
            return (Boolean) value("visible");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True if the page is visible.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder visible(@Nullable Boolean value) {
                if (value == null) values.remove("visible");
                else values.put("visible", jsonValue(value));
                return this;
            }
            public ScreencastVisibilityChangedEvent build() {
                if (!values.containsKey("visible")) throw new IllegalStateException("Missing required CDP field: visible");
                return new ScreencastVisibilityChangedEvent(values);
            }
        }
    }
    /**
     * Fired when a new window is going to be opened, via window.open(), link click, form submission, etc.
     */
    public static final class WindowOpenEvent extends CdpObject {
        private WindowOpenEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WindowOpenEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WindowOpenEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The URL for the new window.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Window name.
         * @return the protocol field value
         */
        @Nullable public String windowName() {
            return (String) value("windowName");
        }
        /**
         * An array of enabled window features.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> windowFeatures() {
            return list(value("windowFeatures"), element0 -> (String) element0);
        }
        /**
         * Whether or not it was triggered by user gesture.
         * @return the protocol field value
         */
        @Nullable public Boolean userGesture() {
            return (Boolean) value("userGesture");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The URL for the new window.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Window name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowName(@Nullable String value) {
                if (value == null) values.remove("windowName");
                else values.put("windowName", jsonValue(value));
                return this;
            }
            /**
             * An array of enabled window features.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowFeatures(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("windowFeatures");
                else values.put("windowFeatures", jsonValue(value));
                return this;
            }
            /**
             * Whether or not it was triggered by user gesture.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userGesture(@Nullable Boolean value) {
                if (value == null) values.remove("userGesture");
                else values.put("userGesture", jsonValue(value));
                return this;
            }
            public WindowOpenEvent build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("windowName")) throw new IllegalStateException("Missing required CDP field: windowName");
                if (!values.containsKey("windowFeatures")) throw new IllegalStateException("Missing required CDP field: windowFeatures");
                if (!values.containsKey("userGesture")) throw new IllegalStateException("Missing required CDP field: userGesture");
                return new WindowOpenEvent(values);
            }
        }
    }
    /**
     * Issued for every compilation cache generated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CompilationCacheProducedEvent extends CdpObject {
        private CompilationCacheProducedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CompilationCacheProducedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CompilationCacheProducedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Base64-encoded data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Base64-encoded data (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public CompilationCacheProducedEvent build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new CompilationCacheProducedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<AddScriptToEvaluateOnLoadResult> addScriptToEvaluateOnLoad(AddScriptToEvaluateOnLoadParams params) {
            return client.call("Page.addScriptToEvaluateOnLoad", params, AddScriptToEvaluateOnLoadResult::fromMap);
        }
        /**
         * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddScriptToEvaluateOnNewDocumentResult> addScriptToEvaluateOnNewDocument(AddScriptToEvaluateOnNewDocumentParams params) {
            return client.call("Page.addScriptToEvaluateOnNewDocument", params, AddScriptToEvaluateOnNewDocumentResult::fromMap);
        }
        /**
         * Brings page to front (activates tab).
         * @return a stage completing with the command result
         */
        public CompletionStage<BringToFrontResult> bringToFront() {
            return client.call("Page.bringToFront", null, BringToFrontResult::fromMap);
        }
        /**
         * Capture page screenshot.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureScreenshotResult> captureScreenshot(CaptureScreenshotParams params) {
            return client.call("Page.captureScreenshot", params, CaptureScreenshotResult::fromMap);
        }
        /**
         * Capture page screenshot.
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureScreenshotResult> captureScreenshot() {
            return captureScreenshot(CaptureScreenshotParams.builder().build());
        }
        /**
         * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(CaptureSnapshotParams params) {
            return client.call("Page.captureSnapshot", params, CaptureSnapshotResult::fromMap);
        }
        /**
         * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot() {
            return captureSnapshot(CaptureSnapshotParams.builder().build());
        }
        /**
         * Clears the overridden device metrics.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<ClearDeviceMetricsOverrideResult> clearDeviceMetricsOverride() {
            return client.call("Page.clearDeviceMetricsOverride", null, ClearDeviceMetricsOverrideResult::fromMap);
        }
        /**
         * Clears the overridden Device Orientation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<ClearDeviceOrientationOverrideResult> clearDeviceOrientationOverride() {
            return client.call("Page.clearDeviceOrientationOverride", null, ClearDeviceOrientationOverrideResult::fromMap);
        }
        /**
         * Clears the overridden Geolocation Position and Error.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<ClearGeolocationOverrideResult> clearGeolocationOverride() {
            return client.call("Page.clearGeolocationOverride", null, ClearGeolocationOverrideResult::fromMap);
        }
        /**
         * Creates an isolated world for the given frame.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CreateIsolatedWorldResult> createIsolatedWorld(CreateIsolatedWorldParams params) {
            return client.call("Page.createIsolatedWorld", params, CreateIsolatedWorldResult::fromMap);
        }
        /**
         * Deletes browser cookie with given name, domain and path.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<DeleteCookieResult> deleteCookie(DeleteCookieParams params) {
            return client.call("Page.deleteCookie", params, DeleteCookieResult::fromMap);
        }
        /**
         * Disables page domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Page.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables page domain notifications.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("Page.enable", params, EnableResult::fromMap);
        }
        /**
         * Enables page domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return enable(EnableParams.builder().build());
        }
        /**
         * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppManifestResult> getAppManifest(GetAppManifestParams params) {
            return client.call("Page.getAppManifest", params, GetAppManifestResult::fromMap);
        }
        /**
         * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppManifestResult> getAppManifest() {
            return getAppManifest(GetAppManifestParams.builder().build());
        }
        /**
         * Invokes Page.getInstallabilityErrors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInstallabilityErrorsResult> getInstallabilityErrors() {
            return client.call("Page.getInstallabilityErrors", null, GetInstallabilityErrorsResult::fromMap);
        }
        /**
         * Deprecated because it&#x27;s not guaranteed that the returned icon is in fact the one used for PWA installation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetManifestIconsResult> getManifestIcons() {
            return client.call("Page.getManifestIcons", null, GetManifestIconsResult::fromMap);
        }
        /**
         * Returns the unique (PWA) app id. Only returns values if the feature flag &#x27;WebAppEnableManifestId&#x27; is enabled
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppIdResult> getAppId() {
            return client.call("Page.getAppId", null, GetAppIdResult::fromMap);
        }
        /**
         * Invokes Page.getAdScriptAncestry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAdScriptAncestryResult> getAdScriptAncestry(GetAdScriptAncestryParams params) {
            return client.call("Page.getAdScriptAncestry", params, GetAdScriptAncestryResult::fromMap);
        }
        /**
         * Returns present frame tree structure.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFrameTreeResult> getFrameTree() {
            return client.call("Page.getFrameTree", null, GetFrameTreeResult::fromMap);
        }
        /**
         * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetLayoutMetricsResult> getLayoutMetrics() {
            return client.call("Page.getLayoutMetrics", null, GetLayoutMetricsResult::fromMap);
        }
        /**
         * Returns navigation history for the current page.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNavigationHistoryResult> getNavigationHistory() {
            return client.call("Page.getNavigationHistory", null, GetNavigationHistoryResult::fromMap);
        }
        /**
         * Resets navigation history for the current page.
         * @return a stage completing with the command result
         */
        public CompletionStage<ResetNavigationHistoryResult> resetNavigationHistory() {
            return client.call("Page.resetNavigationHistory", null, ResetNavigationHistoryResult::fromMap);
        }
        /**
         * Returns content of the given resource.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResourceContentResult> getResourceContent(GetResourceContentParams params) {
            return client.call("Page.getResourceContent", params, GetResourceContentResult::fromMap);
        }
        /**
         * Returns present frame / resource tree structure.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResourceTreeResult> getResourceTree() {
            return client.call("Page.getResourceTree", null, GetResourceTreeResult::fromMap);
        }
        /**
         * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<HandleJavaScriptDialogResult> handleJavaScriptDialog(HandleJavaScriptDialogParams params) {
            return client.call("Page.handleJavaScriptDialog", params, HandleJavaScriptDialogResult::fromMap);
        }
        /**
         * Navigates current page to the given URL.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<NavigateResult> navigate(NavigateParams params) {
            return client.call("Page.navigate", params, NavigateResult::fromMap);
        }
        /**
         * Navigates current page to the given history entry.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<NavigateToHistoryEntryResult> navigateToHistoryEntry(NavigateToHistoryEntryParams params) {
            return client.call("Page.navigateToHistoryEntry", params, NavigateToHistoryEntryResult::fromMap);
        }
        /**
         * Print page as PDF.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PrintToPDFResult> printToPDF(PrintToPDFParams params) {
            return client.call("Page.printToPDF", params, PrintToPDFResult::fromMap);
        }
        /**
         * Print page as PDF.
         * @return a stage completing with the command result
         */
        public CompletionStage<PrintToPDFResult> printToPDF() {
            return printToPDF(PrintToPDFParams.builder().build());
        }
        /**
         * Reloads given page optionally ignoring the cache.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReloadResult> reload(ReloadParams params) {
            return client.call("Page.reload", params, ReloadResult::fromMap);
        }
        /**
         * Reloads given page optionally ignoring the cache.
         * @return a stage completing with the command result
         */
        public CompletionStage<ReloadResult> reload() {
            return reload(ReloadParams.builder().build());
        }
        /**
         * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<RemoveScriptToEvaluateOnLoadResult> removeScriptToEvaluateOnLoad(RemoveScriptToEvaluateOnLoadParams params) {
            return client.call("Page.removeScriptToEvaluateOnLoad", params, RemoveScriptToEvaluateOnLoadResult::fromMap);
        }
        /**
         * Removes given script from the list.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveScriptToEvaluateOnNewDocumentResult> removeScriptToEvaluateOnNewDocument(RemoveScriptToEvaluateOnNewDocumentParams params) {
            return client.call("Page.removeScriptToEvaluateOnNewDocument", params, RemoveScriptToEvaluateOnNewDocumentResult::fromMap);
        }
        /**
         * Acknowledges that a screencast frame has been received by the frontend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ScreencastFrameAckResult> screencastFrameAck(ScreencastFrameAckParams params) {
            return client.call("Page.screencastFrameAck", params, ScreencastFrameAckResult::fromMap);
        }
        /**
         * Searches for given string in resource content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SearchInResourceResult> searchInResource(SearchInResourceParams params) {
            return client.call("Page.searchInResource", params, SearchInResourceResult::fromMap);
        }
        /**
         * Enable Chrome&#x27;s experimental ad filter on all sites.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAdBlockingEnabledResult> setAdBlockingEnabled(SetAdBlockingEnabledParams params) {
            return client.call("Page.setAdBlockingEnabled", params, SetAdBlockingEnabledResult::fromMap);
        }
        /**
         * Enable page Content Security Policy by-passing.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBypassCSPResult> setBypassCSP(SetBypassCSPParams params) {
            return client.call("Page.setBypassCSP", params, SetBypassCSPResult::fromMap);
        }
        /**
         * Get Permissions Policy state on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPermissionsPolicyStateResult> getPermissionsPolicyState(GetPermissionsPolicyStateParams params) {
            return client.call("Page.getPermissionsPolicyState", params, GetPermissionsPolicyStateResult::fromMap);
        }
        /**
         * Get Origin Trials on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetOriginTrialsResult> getOriginTrials(GetOriginTrialsParams params) {
            return client.call("Page.getOriginTrials", params, GetOriginTrialsResult::fromMap);
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetDeviceMetricsOverrideResult> setDeviceMetricsOverride(SetDeviceMetricsOverrideParams params) {
            return client.call("Page.setDeviceMetricsOverride", params, SetDeviceMetricsOverrideResult::fromMap);
        }
        /**
         * Overrides the Device Orientation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetDeviceOrientationOverrideResult> setDeviceOrientationOverride(SetDeviceOrientationOverrideParams params) {
            return client.call("Page.setDeviceOrientationOverride", params, SetDeviceOrientationOverrideResult::fromMap);
        }
        /**
         * Set generic font families.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetFontFamiliesResult> setFontFamilies(SetFontFamiliesParams params) {
            return client.call("Page.setFontFamilies", params, SetFontFamiliesResult::fromMap);
        }
        /**
         * Set default font sizes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetFontSizesResult> setFontSizes(SetFontSizesParams params) {
            return client.call("Page.setFontSizes", params, SetFontSizesResult::fromMap);
        }
        /**
         * Sets given markup as the document&#x27;s HTML.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDocumentContentResult> setDocumentContent(SetDocumentContentParams params) {
            return client.call("Page.setDocumentContent", params, SetDocumentContentResult::fromMap);
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetDownloadBehaviorResult> setDownloadBehavior(SetDownloadBehaviorParams params) {
            return client.call("Page.setDownloadBehavior", params, SetDownloadBehaviorResult::fromMap);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetGeolocationOverrideResult> setGeolocationOverride(SetGeolocationOverrideParams params) {
            return client.call("Page.setGeolocationOverride", params, SetGeolocationOverrideResult::fromMap);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetGeolocationOverrideResult> setGeolocationOverride() {
            return setGeolocationOverride(SetGeolocationOverrideParams.builder().build());
        }
        /**
         * Controls whether page will emit lifecycle events.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetLifecycleEventsEnabledResult> setLifecycleEventsEnabled(SetLifecycleEventsEnabledParams params) {
            return client.call("Page.setLifecycleEventsEnabled", params, SetLifecycleEventsEnabledResult::fromMap);
        }
        /**
         * Toggles mouse event-based touch event emulation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetTouchEmulationEnabledResult> setTouchEmulationEnabled(SetTouchEmulationEnabledParams params) {
            return client.call("Page.setTouchEmulationEnabled", params, SetTouchEmulationEnabledResult::fromMap);
        }
        /**
         * Starts sending each frame using the {@code screencastFrame} event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartScreencastResult> startScreencast(StartScreencastParams params) {
            return client.call("Page.startScreencast", params, StartScreencastResult::fromMap);
        }
        /**
         * Starts sending each frame using the {@code screencastFrame} event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<StartScreencastResult> startScreencast() {
            return startScreencast(StartScreencastParams.builder().build());
        }
        /**
         * Force the page stop all navigations and pending resource fetches.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopLoadingResult> stopLoading() {
            return client.call("Page.stopLoading", null, StopLoadingResult::fromMap);
        }
        /**
         * Crashes renderer on the IO thread, generates minidumps.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<CrashResult> crash() {
            return client.call("Page.crash", null, CrashResult::fromMap);
        }
        /**
         * Tries to close page, running its beforeunload hooks, if any.
         * @return a stage completing with the command result
         */
        public CompletionStage<CloseResult> close() {
            return client.call("Page.close", null, CloseResult::fromMap);
        }
        /**
         * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetWebLifecycleStateResult> setWebLifecycleState(SetWebLifecycleStateParams params) {
            return client.call("Page.setWebLifecycleState", params, SetWebLifecycleStateResult::fromMap);
        }
        /**
         * Stops sending each frame in the {@code screencastFrame}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopScreencastResult> stopScreencast() {
            return client.call("Page.stopScreencast", null, StopScreencastResult::fromMap);
        }
        /**
         * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ProduceCompilationCacheResult> produceCompilationCache(ProduceCompilationCacheParams params) {
            return client.call("Page.produceCompilationCache", params, ProduceCompilationCacheResult::fromMap);
        }
        /**
         * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddCompilationCacheResult> addCompilationCache(AddCompilationCacheParams params) {
            return client.call("Page.addCompilationCache", params, AddCompilationCacheResult::fromMap);
        }
        /**
         * Clears seeded compilation cache.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearCompilationCacheResult> clearCompilationCache() {
            return client.call("Page.clearCompilationCache", null, ClearCompilationCacheResult::fromMap);
        }
        /**
         * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSPCTransactionModeResult> setSPCTransactionMode(SetSPCTransactionModeParams params) {
            return client.call("Page.setSPCTransactionMode", params, SetSPCTransactionModeResult::fromMap);
        }
        /**
         * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetRPHRegistrationModeResult> setRPHRegistrationMode(SetRPHRegistrationModeParams params) {
            return client.call("Page.setRPHRegistrationMode", params, SetRPHRegistrationModeResult::fromMap);
        }
        /**
         * Generates a report for testing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GenerateTestReportResult> generateTestReport(GenerateTestReportParams params) {
            return client.call("Page.generateTestReport", params, GenerateTestReportResult::fromMap);
        }
        /**
         * Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<WaitForDebuggerResult> waitForDebugger() {
            return client.call("Page.waitForDebugger", null, WaitForDebuggerResult::fromMap);
        }
        /**
         * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInterceptFileChooserDialogResult> setInterceptFileChooserDialog(SetInterceptFileChooserDialogParams params) {
            return client.call("Page.setInterceptFileChooserDialog", params, SetInterceptFileChooserDialogResult::fromMap);
        }
        /**
         * Enable/disable prerendering manually.
         * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
         * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPrerenderingAllowedResult> setPrerenderingAllowed(SetPrerenderingAllowedParams params) {
            return client.call("Page.setPrerenderingAllowed", params, SetPrerenderingAllowedResult::fromMap);
        }
        /**
         * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnnotatedPageContentResult> getAnnotatedPageContent(GetAnnotatedPageContentParams params) {
            return client.call("Page.getAnnotatedPageContent", params, GetAnnotatedPageContentResult::fromMap);
        }
        /**
         * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnnotatedPageContentResult> getAnnotatedPageContent() {
            return getAnnotatedPageContent(GetAnnotatedPageContentParams.builder().build());
        }
        /**
         * Subscribes to Page.domContentEventFired.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDomContentEventFired(Consumer<DomContentEventFiredEvent> handler) {
            return client.on("Page.domContentEventFired", DomContentEventFiredEvent::fromMap, handler);
        }
        /**
         * Emitted only when {@code page.interceptFileChooser} is enabled.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFileChooserOpened(Consumer<FileChooserOpenedEvent> handler) {
            return client.on("Page.fileChooserOpened", FileChooserOpenedEvent::fromMap, handler);
        }
        /**
         * Fired when frame has been attached to its parent.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameAttached(Consumer<FrameAttachedEvent> handler) {
            return client.on("Page.frameAttached", FrameAttachedEvent::fromMap, handler);
        }
        /**
         * Fired when frame no longer has a scheduled navigation.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onFrameClearedScheduledNavigation(Consumer<FrameClearedScheduledNavigationEvent> handler) {
            return client.on("Page.frameClearedScheduledNavigation", FrameClearedScheduledNavigationEvent::fromMap, handler);
        }
        /**
         * Fired when frame has been detached from its parent.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameDetached(Consumer<FrameDetachedEvent> handler) {
            return client.on("Page.frameDetached", FrameDetachedEvent::fromMap, handler);
        }
        /**
         * Fired before frame subtree is detached. Emitted before any frame of the subtree is actually detached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameSubtreeWillBeDetached(Consumer<FrameSubtreeWillBeDetachedEvent> handler) {
            return client.on("Page.frameSubtreeWillBeDetached", FrameSubtreeWillBeDetachedEvent::fromMap, handler);
        }
        /**
         * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameNavigated(Consumer<FrameNavigatedEvent> handler) {
            return client.on("Page.frameNavigated", FrameNavigatedEvent::fromMap, handler);
        }
        /**
         * Fired when opening document to write to.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDocumentOpened(Consumer<DocumentOpenedEvent> handler) {
            return client.on("Page.documentOpened", DocumentOpenedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Page.frameResized.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameResized(Consumer<FrameResizedEvent> handler) {
            return client.on("Page.frameResized", FrameResizedEvent::fromMap, handler);
        }
        /**
         * Fired when a navigation starts. This event is fired for both renderer-initiated and browser-initiated navigations. For renderer-initiated navigations, the event is fired after {@code frameRequestedNavigation}. Navigation may still be cancelled after the event is issued. Multiple events can be fired for a single navigation, for example, when a same-document navigation becomes a cross-document navigation (such as in the case of a frameset).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameStartedNavigating(Consumer<FrameStartedNavigatingEvent> handler) {
            return client.on("Page.frameStartedNavigating", FrameStartedNavigatingEvent::fromMap, handler);
        }
        /**
         * Fired when a renderer-initiated navigation is requested. Navigation may still be cancelled after the event is issued.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameRequestedNavigation(Consumer<FrameRequestedNavigationEvent> handler) {
            return client.on("Page.frameRequestedNavigation", FrameRequestedNavigationEvent::fromMap, handler);
        }
        /**
         * Fired when frame schedules a potential navigation.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onFrameScheduledNavigation(Consumer<FrameScheduledNavigationEvent> handler) {
            return client.on("Page.frameScheduledNavigation", FrameScheduledNavigationEvent::fromMap, handler);
        }
        /**
         * Fired when frame has started loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameStartedLoading(Consumer<FrameStartedLoadingEvent> handler) {
            return client.on("Page.frameStartedLoading", FrameStartedLoadingEvent::fromMap, handler);
        }
        /**
         * Fired when frame has stopped loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFrameStoppedLoading(Consumer<FrameStoppedLoadingEvent> handler) {
            return client.on("Page.frameStoppedLoading", FrameStoppedLoadingEvent::fromMap, handler);
        }
        /**
         * Fired when page is about to start a download. Deprecated. Use Browser.downloadWillBegin instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onDownloadWillBegin(Consumer<DownloadWillBeginEvent> handler) {
            return client.on("Page.downloadWillBegin", DownloadWillBeginEvent::fromMap, handler);
        }
        /**
         * Fired when download makes progress. Last call has |done| == true. Deprecated. Use Browser.downloadProgress instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onDownloadProgress(Consumer<DownloadProgressEvent> handler) {
            return client.on("Page.downloadProgress", DownloadProgressEvent::fromMap, handler);
        }
        /**
         * Fired when interstitial page was hidden
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterstitialHidden(Consumer<InterstitialHiddenEvent> handler) {
            return client.on("Page.interstitialHidden", InterstitialHiddenEvent::fromMap, handler);
        }
        /**
         * Fired when interstitial page was shown
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInterstitialShown(Consumer<InterstitialShownEvent> handler) {
            return client.on("Page.interstitialShown", InterstitialShownEvent::fromMap, handler);
        }
        /**
         * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been closed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onJavascriptDialogClosed(Consumer<JavascriptDialogClosedEvent> handler) {
            return client.on("Page.javascriptDialogClosed", JavascriptDialogClosedEvent::fromMap, handler);
        }
        /**
         * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to open.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onJavascriptDialogOpening(Consumer<JavascriptDialogOpeningEvent> handler) {
            return client.on("Page.javascriptDialogOpening", JavascriptDialogOpeningEvent::fromMap, handler);
        }
        /**
         * Fired for lifecycle events (navigation, load, paint, etc) in the current target (including local frames).
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLifecycleEvent(Consumer<LifecycleEventEvent> handler) {
            return client.on("Page.lifecycleEvent", LifecycleEventEvent::fromMap, handler);
        }
        /**
         * Fired for failed bfcache history navigations if BackForwardCache feature is enabled. Do not assume any ordering with the Page.frameNavigated event. This event is fired only for main-frame history navigation where the document changes (non-same-document navigations), when bfcache navigation fails.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBackForwardCacheNotUsed(Consumer<BackForwardCacheNotUsedEvent> handler) {
            return client.on("Page.backForwardCacheNotUsed", BackForwardCacheNotUsedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Page.loadEventFired.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLoadEventFired(Consumer<LoadEventFiredEvent> handler) {
            return client.on("Page.loadEventFired", LoadEventFiredEvent::fromMap, handler);
        }
        /**
         * Fired when same-document navigation happens, e.g. due to history API usage or anchor navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNavigatedWithinDocument(Consumer<NavigatedWithinDocumentEvent> handler) {
            return client.on("Page.navigatedWithinDocument", NavigatedWithinDocumentEvent::fromMap, handler);
        }
        /**
         * Compressed image data requested by the {@code startScreencast}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScreencastFrame(Consumer<ScreencastFrameEvent> handler) {
            return client.on("Page.screencastFrame", ScreencastFrameEvent::fromMap, handler);
        }
        /**
         * Fired when the page with currently enabled screencast was shown or hidden `.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScreencastVisibilityChanged(Consumer<ScreencastVisibilityChangedEvent> handler) {
            return client.on("Page.screencastVisibilityChanged", ScreencastVisibilityChangedEvent::fromMap, handler);
        }
        /**
         * Fired when a new window is going to be opened, via window.open(), link click, form submission, etc.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWindowOpen(Consumer<WindowOpenEvent> handler) {
            return client.on("Page.windowOpen", WindowOpenEvent::fromMap, handler);
        }
        /**
         * Issued for every compilation cache generated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCompilationCacheProduced(Consumer<CompilationCacheProducedEvent> handler) {
            return client.on("Page.compilationCacheProduced", CompilationCacheProducedEvent::fromMap, handler);
        }
    }
}
