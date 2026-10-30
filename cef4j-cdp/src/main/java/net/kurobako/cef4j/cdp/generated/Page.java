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
 * Actions and events related to the inspected page belong to the page domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Page.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Page {
    private Page() {}
    /**
     * Unique frame identifier.
     */
    public static final class FrameId implements CdpValue<String> {
        public final String value;
        public FrameId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof FrameId)) return false;
            return value.equals(((FrameId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "FrameId(" + value + ")"; }
    }
    /**
     * Indicates whether a frame has been identified as an ad.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum AdFrameType implements CdpValue<String> {
        NONE("none"),
        CHILD("child"),
        ROOT("root");
        public final String value;
        AdFrameType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AdFrameType of(String value) {
            for (AdFrameType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AdFrameType value: " + value);
        }
    }
    /**
     * Wire values for AdFrameExplanation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum AdFrameExplanation implements CdpValue<String> {
        PARENTISAD("ParentIsAd"),
        CREATEDBYADSCRIPT("CreatedByAdScript"),
        MATCHEDBLOCKINGRULE("MatchedBlockingRule");
        public final String value;
        AdFrameExplanation(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AdFrameExplanation of(String value) {
            for (AdFrameExplanation constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AdFrameExplanation value: " + value);
        }
    }
    /**
     * Indicates whether a frame has been identified as an ad and why.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdFrameStatus extends CdpObject {
        public AdFrameStatus() {}
        private AdFrameStatus(Map<String, Object> values) { super(values); }
        public static AdFrameStatus fromMap(Map<String, Object> values) {
            return new AdFrameStatus(values);
        }
        /**
         * Returns the adFrameType field.
         * @return the protocol field value
         */
        public Page.AdFrameType adFrameType() {
            return Page.AdFrameType.of((String) require("adFrameType"));
        }
        /**
         * Returns the explanations field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.AdFrameExplanation>> explanations() {
            return raw("explanations").flatMap(value -> list(value, element0 -> Page.AdFrameExplanation.of((String) element0)));
        }
        /**
         * Sets the adFrameType field.
         * @param adFrameType field value
         * @return this model
         */
        public AdFrameStatus adFrameType(Page.AdFrameType adFrameType) {
            set("adFrameType", adFrameType);
            return this;
        }
        /**
         * Sets the explanations field.
         * @param explanations field value
         * @return this model
         */
        public AdFrameStatus explanations(java.util.List<Page.AdFrameExplanation> explanations) {
            set("explanations", explanations);
            return this;
        }
    }
    /**
     * Indicates whether the frame is a secure context and why it is the case.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum SecureContextType implements CdpValue<String> {
        SECURE("Secure"),
        SECURELOCALHOST("SecureLocalhost"),
        INSECURESCHEME("InsecureScheme"),
        INSECUREANCESTOR("InsecureAncestor");
        public final String value;
        SecureContextType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SecureContextType of(String value) {
            for (SecureContextType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SecureContextType value: " + value);
        }
    }
    /**
     * Indicates whether the frame is cross-origin isolated and why it is the case.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CrossOriginIsolatedContextType implements CdpValue<String> {
        ISOLATED("Isolated"),
        NOTISOLATED("NotIsolated"),
        NOTISOLATEDFEATUREDISABLED("NotIsolatedFeatureDisabled");
        public final String value;
        CrossOriginIsolatedContextType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CrossOriginIsolatedContextType of(String value) {
            for (CrossOriginIsolatedContextType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CrossOriginIsolatedContextType value: " + value);
        }
    }
    /**
     * Wire values for GatedAPIFeatures.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum GatedAPIFeatures implements CdpValue<String> {
        SHAREDARRAYBUFFERS("SharedArrayBuffers"),
        SHAREDARRAYBUFFERSTRANSFERALLOWED("SharedArrayBuffersTransferAllowed"),
        PERFORMANCEMEASUREMEMORY("PerformanceMeasureMemory"),
        PERFORMANCEPROFILE("PerformanceProfile");
        public final String value;
        GatedAPIFeatures(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static GatedAPIFeatures of(String value) {
            for (GatedAPIFeatures constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GatedAPIFeatures value: " + value);
        }
    }
    /**
     * All Permissions Policy features. This enum should match the one defined in services/network/public/cpp/permissions_policy/permissions_policy_features.json5. LINT.IfChange(PermissionsPolicyFeature)
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PermissionsPolicyFeature implements CdpValue<String> {
        ACCELEROMETER("accelerometer"),
        ALL_SCREENS_CAPTURE("all-screens-capture"),
        AMBIENT_LIGHT_SENSOR("ambient-light-sensor"),
        ARIA_NOTIFY("aria-notify"),
        ATTRIBUTION_REPORTING("attribution-reporting"),
        AUTOFILL("autofill"),
        AUTOPLAY("autoplay"),
        BLUETOOTH("bluetooth"),
        BROWSING_TOPICS("browsing-topics"),
        CAMERA("camera"),
        CAPTURED_SURFACE_CONTROL("captured-surface-control"),
        CH_DPR("ch-dpr"),
        CH_DEVICE_MEMORY("ch-device-memory"),
        CH_DOWNLINK("ch-downlink"),
        CH_ECT("ch-ect"),
        CH_PREFERS_COLOR_SCHEME("ch-prefers-color-scheme"),
        CH_PREFERS_REDUCED_MOTION("ch-prefers-reduced-motion"),
        CH_PREFERS_REDUCED_TRANSPARENCY("ch-prefers-reduced-transparency"),
        CH_RTT("ch-rtt"),
        CH_SAVE_DATA("ch-save-data"),
        CH_UA("ch-ua"),
        CH_UA_ARCH("ch-ua-arch"),
        CH_UA_BITNESS("ch-ua-bitness"),
        CH_UA_HIGH_ENTROPY_VALUES("ch-ua-high-entropy-values"),
        CH_UA_PLATFORM("ch-ua-platform"),
        CH_UA_MODEL("ch-ua-model"),
        CH_UA_MOBILE("ch-ua-mobile"),
        CH_UA_FORM_FACTORS("ch-ua-form-factors"),
        CH_UA_FULL_VERSION("ch-ua-full-version"),
        CH_UA_FULL_VERSION_LIST("ch-ua-full-version-list"),
        CH_UA_PLATFORM_VERSION("ch-ua-platform-version"),
        CH_UA_WOW64("ch-ua-wow64"),
        CH_VIEWPORT_HEIGHT("ch-viewport-height"),
        CH_VIEWPORT_WIDTH("ch-viewport-width"),
        CH_WIDTH("ch-width"),
        CLIPBOARD_READ("clipboard-read"),
        CLIPBOARD_WRITE("clipboard-write"),
        COMPUTE_PRESSURE("compute-pressure"),
        CONTROLLED_FRAME("controlled-frame"),
        CROSS_ORIGIN_ISOLATED("cross-origin-isolated"),
        DEFERRED_FETCH("deferred-fetch"),
        DEFERRED_FETCH_MINIMAL("deferred-fetch-minimal"),
        DEVICE_ATTRIBUTES("device-attributes"),
        DIGITAL_CREDENTIALS_CREATE("digital-credentials-create"),
        DIGITAL_CREDENTIALS_GET("digital-credentials-get"),
        DIRECT_SOCKETS("direct-sockets"),
        DIRECT_SOCKETS_MULTICAST("direct-sockets-multicast"),
        DIRECT_SOCKETS_PRIVATE("direct-sockets-private"),
        DISPLAY_CAPTURE("display-capture"),
        DOCUMENT_DOMAIN("document-domain"),
        ENCRYPTED_MEDIA("encrypted-media"),
        EXECUTION_WHILE_OUT_OF_VIEWPORT("execution-while-out-of-viewport"),
        EXECUTION_WHILE_NOT_RENDERED("execution-while-not-rendered"),
        FOCUS_WITHOUT_USER_ACTIVATION("focus-without-user-activation"),
        FULLSCREEN("fullscreen"),
        FROBULATE("frobulate"),
        GAMEPAD("gamepad"),
        GEOLOCATION("geolocation"),
        GYROSCOPE("gyroscope"),
        HID("hid"),
        IDENTITY_CREDENTIALS_GET("identity-credentials-get"),
        IDLE_DETECTION("idle-detection"),
        INTEREST_COHORT("interest-cohort"),
        JOIN_AD_INTEREST_GROUP("join-ad-interest-group"),
        KEYBOARD_MAP("keyboard-map"),
        LANGUAGE_DETECTOR("language-detector"),
        LANGUAGE_MODEL("language-model"),
        LOCAL_FONTS("local-fonts"),
        LOCAL_NETWORK("local-network"),
        LOCAL_NETWORK_ACCESS("local-network-access"),
        LOOPBACK_NETWORK("loopback-network"),
        MAGNETOMETER("magnetometer"),
        MANUAL_TEXT("manual-text"),
        MEDIA_PLAYBACK_WHILE_NOT_VISIBLE("media-playback-while-not-visible"),
        MICROPHONE("microphone"),
        MIDI("midi"),
        ON_DEVICE_SPEECH_RECOGNITION("on-device-speech-recognition"),
        OTP_CREDENTIALS("otp-credentials"),
        PAYMENT("payment"),
        PICTURE_IN_PICTURE("picture-in-picture"),
        PRIVATE_AGGREGATION("private-aggregation"),
        PRIVATE_STATE_TOKEN_ISSUANCE("private-state-token-issuance"),
        PRIVATE_STATE_TOKEN_REDEMPTION("private-state-token-redemption"),
        PUBLICKEY_CREDENTIALS_CREATE("publickey-credentials-create"),
        PUBLICKEY_CREDENTIALS_GET("publickey-credentials-get"),
        RECORD_AD_AUCTION_EVENTS("record-ad-auction-events"),
        REWRITER("rewriter"),
        RUN_AD_AUCTION("run-ad-auction"),
        SCREEN_WAKE_LOCK("screen-wake-lock"),
        SERIAL("serial"),
        SHARED_STORAGE("shared-storage"),
        SHARED_STORAGE_SELECT_URL("shared-storage-select-url"),
        SMART_CARD("smart-card"),
        SPEAKER_SELECTION("speaker-selection"),
        STORAGE_ACCESS("storage-access"),
        SUB_APPS("sub-apps"),
        SUMMARIZER("summarizer"),
        SYNC_XHR("sync-xhr"),
        TOOLS("tools"),
        TRANSLATOR("translator"),
        UNLOAD("unload"),
        USB("usb"),
        USB_UNRESTRICTED("usb-unrestricted"),
        VERTICAL_SCROLL("vertical-scroll"),
        WEB_APP_INSTALLATION("web-app-installation"),
        WEB_PRINTING("web-printing"),
        WEB_SHARE("web-share"),
        WINDOW_MANAGEMENT("window-management"),
        WRITER("writer"),
        XR_SPATIAL_TRACKING("xr-spatial-tracking");
        public final String value;
        PermissionsPolicyFeature(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PermissionsPolicyFeature of(String value) {
            for (PermissionsPolicyFeature constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PermissionsPolicyFeature value: " + value);
        }
    }
    /**
     * Reason for a permissions policy feature to be disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PermissionsPolicyBlockReason implements CdpValue<String> {
        HEADER("Header"),
        IFRAMEATTRIBUTE("IframeAttribute"),
        INFENCEDFRAMETREE("InFencedFrameTree"),
        INISOLATEDAPP("InIsolatedApp");
        public final String value;
        PermissionsPolicyBlockReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PermissionsPolicyBlockReason of(String value) {
            for (PermissionsPolicyBlockReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PermissionsPolicyBlockReason value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionsPolicyBlockLocator extends CdpObject {
        public PermissionsPolicyBlockLocator() {}
        private PermissionsPolicyBlockLocator(Map<String, Object> values) { super(values); }
        public static PermissionsPolicyBlockLocator fromMap(Map<String, Object> values) {
            return new PermissionsPolicyBlockLocator(values);
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Returns the blockReason field.
         * @return the protocol field value
         */
        public Page.PermissionsPolicyBlockReason blockReason() {
            return Page.PermissionsPolicyBlockReason.of((String) require("blockReason"));
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public PermissionsPolicyBlockLocator frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Sets the blockReason field.
         * @param blockReason field value
         * @return this model
         */
        public PermissionsPolicyBlockLocator blockReason(Page.PermissionsPolicyBlockReason blockReason) {
            set("blockReason", blockReason);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PermissionsPolicyFeatureState extends CdpObject {
        public PermissionsPolicyFeatureState() {}
        private PermissionsPolicyFeatureState(Map<String, Object> values) { super(values); }
        public static PermissionsPolicyFeatureState fromMap(Map<String, Object> values) {
            return new PermissionsPolicyFeatureState(values);
        }
        /**
         * Returns the feature field.
         * @return the protocol field value
         */
        public Page.PermissionsPolicyFeature feature() {
            return Page.PermissionsPolicyFeature.of((String) require("feature"));
        }
        /**
         * Returns the allowed field.
         * @return the protocol field value
         */
        public boolean allowed() {
            return (Boolean) require("allowed");
        }
        /**
         * Returns the locator field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.PermissionsPolicyBlockLocator> locator() {
            return raw("locator").flatMap(CdpObject::objectMap).map(Page.PermissionsPolicyBlockLocator::fromMap);
        }
        /**
         * Sets the feature field.
         * @param feature field value
         * @return this model
         */
        public PermissionsPolicyFeatureState feature(Page.PermissionsPolicyFeature feature) {
            set("feature", feature);
            return this;
        }
        /**
         * Sets the allowed field.
         * @param allowed field value
         * @return this model
         */
        public PermissionsPolicyFeatureState allowed(boolean allowed) {
            set("allowed", allowed);
            return this;
        }
        /**
         * Sets the locator field.
         * @param locator field value
         * @return this model
         */
        public PermissionsPolicyFeatureState locator(Page.PermissionsPolicyBlockLocator locator) {
            set("locator", locator);
            return this;
        }
    }
    /**
     * Origin Trial(https://www.chromium.org/blink/origin-trials) support. Status for an Origin Trial token.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum OriginTrialTokenStatus implements CdpValue<String> {
        SUCCESS("Success"),
        NOTSUPPORTED("NotSupported"),
        INSECURE("Insecure"),
        EXPIRED("Expired"),
        WRONGORIGIN("WrongOrigin"),
        INVALIDSIGNATURE("InvalidSignature"),
        MALFORMED("Malformed"),
        WRONGVERSION("WrongVersion"),
        FEATUREDISABLED("FeatureDisabled"),
        TOKENDISABLED("TokenDisabled"),
        FEATUREDISABLEDFORUSER("FeatureDisabledForUser"),
        UNKNOWNTRIAL("UnknownTrial");
        public final String value;
        OriginTrialTokenStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static OriginTrialTokenStatus of(String value) {
            for (OriginTrialTokenStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown OriginTrialTokenStatus value: " + value);
        }
    }
    /**
     * Status for an Origin Trial.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum OriginTrialStatus implements CdpValue<String> {
        ENABLED("Enabled"),
        VALIDTOKENNOTPROVIDED("ValidTokenNotProvided"),
        OSNOTSUPPORTED("OSNotSupported"),
        TRIALNOTALLOWED("TrialNotAllowed");
        public final String value;
        OriginTrialStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static OriginTrialStatus of(String value) {
            for (OriginTrialStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown OriginTrialStatus value: " + value);
        }
    }
    /**
     * Wire values for OriginTrialUsageRestriction.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum OriginTrialUsageRestriction implements CdpValue<String> {
        NONE("None"),
        SUBSET("Subset");
        public final String value;
        OriginTrialUsageRestriction(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static OriginTrialUsageRestriction of(String value) {
            for (OriginTrialUsageRestriction constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown OriginTrialUsageRestriction value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialToken extends CdpObject {
        public OriginTrialToken() {}
        private OriginTrialToken(Map<String, Object> values) { super(values); }
        public static OriginTrialToken fromMap(Map<String, Object> values) {
            return new OriginTrialToken(values);
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Returns the matchSubDomains field.
         * @return the protocol field value
         */
        public boolean matchSubDomains() {
            return (Boolean) require("matchSubDomains");
        }
        /**
         * Returns the trialName field.
         * @return the protocol field value
         */
        public String trialName() {
            return (String) require("trialName");
        }
        /**
         * Returns the expiryTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch expiryTime() {
            return new Network.TimeSinceEpoch(((Number) require("expiryTime")).doubleValue());
        }
        /**
         * Returns the isThirdParty field.
         * @return the protocol field value
         */
        public boolean isThirdParty() {
            return (Boolean) require("isThirdParty");
        }
        /**
         * Returns the usageRestriction field.
         * @return the protocol field value
         */
        public Page.OriginTrialUsageRestriction usageRestriction() {
            return Page.OriginTrialUsageRestriction.of((String) require("usageRestriction"));
        }
        /**
         * Sets the origin field.
         * @param origin field value
         * @return this model
         */
        public OriginTrialToken origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Sets the matchSubDomains field.
         * @param matchSubDomains field value
         * @return this model
         */
        public OriginTrialToken matchSubDomains(boolean matchSubDomains) {
            set("matchSubDomains", matchSubDomains);
            return this;
        }
        /**
         * Sets the trialName field.
         * @param trialName field value
         * @return this model
         */
        public OriginTrialToken trialName(String trialName) {
            set("trialName", trialName);
            return this;
        }
        /**
         * Sets the expiryTime field.
         * @param expiryTime field value
         * @return this model
         */
        public OriginTrialToken expiryTime(Network.TimeSinceEpoch expiryTime) {
            set("expiryTime", expiryTime);
            return this;
        }
        /**
         * Sets the isThirdParty field.
         * @param isThirdParty field value
         * @return this model
         */
        public OriginTrialToken isThirdParty(boolean isThirdParty) {
            set("isThirdParty", isThirdParty);
            return this;
        }
        /**
         * Sets the usageRestriction field.
         * @param usageRestriction field value
         * @return this model
         */
        public OriginTrialToken usageRestriction(Page.OriginTrialUsageRestriction usageRestriction) {
            set("usageRestriction", usageRestriction);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrialTokenWithStatus extends CdpObject {
        public OriginTrialTokenWithStatus() {}
        private OriginTrialTokenWithStatus(Map<String, Object> values) { super(values); }
        public static OriginTrialTokenWithStatus fromMap(Map<String, Object> values) {
            return new OriginTrialTokenWithStatus(values);
        }
        /**
         * Returns the rawTokenText field.
         * @return the protocol field value
         */
        public String rawTokenText() {
            return (String) require("rawTokenText");
        }
        /**
         * {@code parsedToken} is present only when the token is extractable and parsable.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.OriginTrialToken> parsedToken() {
            return raw("parsedToken").flatMap(CdpObject::objectMap).map(Page.OriginTrialToken::fromMap);
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public Page.OriginTrialTokenStatus status() {
            return Page.OriginTrialTokenStatus.of((String) require("status"));
        }
        /**
         * Sets the rawTokenText field.
         * @param rawTokenText field value
         * @return this model
         */
        public OriginTrialTokenWithStatus rawTokenText(String rawTokenText) {
            set("rawTokenText", rawTokenText);
            return this;
        }
        /**
         * {@code parsedToken} is present only when the token is extractable and parsable.
         * @param parsedToken field value
         * @return this model
         */
        public OriginTrialTokenWithStatus parsedToken(Page.OriginTrialToken parsedToken) {
            set("parsedToken", parsedToken);
            return this;
        }
        /**
         * Sets the status field.
         * @param status field value
         * @return this model
         */
        public OriginTrialTokenWithStatus status(Page.OriginTrialTokenStatus status) {
            set("status", status);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OriginTrial extends CdpObject {
        public OriginTrial() {}
        private OriginTrial(Map<String, Object> values) { super(values); }
        public static OriginTrial fromMap(Map<String, Object> values) {
            return new OriginTrial(values);
        }
        /**
         * Returns the trialName field.
         * @return the protocol field value
         */
        public String trialName() {
            return (String) require("trialName");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public Page.OriginTrialStatus status() {
            return Page.OriginTrialStatus.of((String) require("status"));
        }
        /**
         * Returns the tokensWithStatus field.
         * @return the protocol field value
         */
        public java.util.List<Page.OriginTrialTokenWithStatus> tokensWithStatus() {
            return CdpObject.requireList(require("tokensWithStatus"), element0 -> java.util.Objects.requireNonNull(Page.OriginTrialTokenWithStatus.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Sets the trialName field.
         * @param trialName field value
         * @return this model
         */
        public OriginTrial trialName(String trialName) {
            set("trialName", trialName);
            return this;
        }
        /**
         * Sets the status field.
         * @param status field value
         * @return this model
         */
        public OriginTrial status(Page.OriginTrialStatus status) {
            set("status", status);
            return this;
        }
        /**
         * Sets the tokensWithStatus field.
         * @param tokensWithStatus field value
         * @return this model
         */
        public OriginTrial tokensWithStatus(java.util.List<Page.OriginTrialTokenWithStatus> tokensWithStatus) {
            set("tokensWithStatus", tokensWithStatus);
            return this;
        }
    }
    /**
     * Additional information about the frame document&#x27;s security origin.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SecurityOriginDetails extends CdpObject {
        public SecurityOriginDetails() {}
        private SecurityOriginDetails(Map<String, Object> values) { super(values); }
        public static SecurityOriginDetails fromMap(Map<String, Object> values) {
            return new SecurityOriginDetails(values);
        }
        /**
         * Indicates whether the frame document&#x27;s security origin is one of the local hostnames (e.g. &quot;localhost&quot;) or IP addresses (IPv4 127.0.0.0/8 or IPv6 ::1).
         * @return the protocol field value
         */
        public boolean isLocalhost() {
            return (Boolean) require("isLocalhost");
        }
        /**
         * Indicates whether the frame document&#x27;s security origin is one of the local hostnames (e.g. &quot;localhost&quot;) or IP addresses (IPv4 127.0.0.0/8 or IPv6 ::1).
         * @param isLocalhost field value
         * @return this model
         */
        public SecurityOriginDetails isLocalhost(boolean isLocalhost) {
            set("isLocalhost", isLocalhost);
            return this;
        }
    }
    /**
     * Information about the Frame on the page.
     */
    public static final class Frame extends CdpObject {
        public Frame() {}
        private Frame(Map<String, Object> values) { super(values); }
        public static Frame fromMap(Map<String, Object> values) {
            return new Frame(values);
        }
        /**
         * Frame unique identifier.
         * @return the protocol field value
         */
        public Page.FrameId id() {
            return new Page.FrameId((String) require("id"));
        }
        /**
         * Parent frame identifier.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> parentId() {
            return raw("parentId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * Identifier of the loader associated with this frame.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return new Network.LoaderId((String) require("loaderId"));
        }
        /**
         * Frame&#x27;s name as specified in the tag.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return raw("name").map(value -> (String) value);
        }
        /**
         * Frame document&#x27;s URL without fragment.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Frame document&#x27;s URL fragment including the &#x27;#&#x27;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urlFragment() {
            return raw("urlFragment").map(value -> (String) value);
        }
        /**
         * Frame document&#x27;s registered domain, taking the public suffixes list into account. Extracted from the Frame&#x27;s url. Example URLs: http://www.google.com/file.html -&gt; &quot;google.com&quot; http://a.b.co.uk/file.html -&gt; &quot;b.co.uk&quot;
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public String domainAndRegistry() {
            return (String) require("domainAndRegistry");
        }
        /**
         * Frame document&#x27;s security origin.
         * @return the protocol field value
         */
        public String securityOrigin() {
            return (String) require("securityOrigin");
        }
        /**
         * Additional details about the frame document&#x27;s security origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.SecurityOriginDetails> securityOriginDetails() {
            return raw("securityOriginDetails").flatMap(CdpObject::objectMap).map(Page.SecurityOriginDetails::fromMap);
        }
        /**
         * Frame document&#x27;s mimeType as determined by the browser.
         * @return the protocol field value
         */
        public String mimeType() {
            return (String) require("mimeType");
        }
        /**
         * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike url above, this URL may contain a fragment.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> unreachableUrl() {
            return raw("unreachableUrl").map(value -> (String) value);
        }
        /**
         * Indicates whether this frame was tagged as an ad and why.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.AdFrameStatus> adFrameStatus() {
            return raw("adFrameStatus").flatMap(CdpObject::objectMap).map(Page.AdFrameStatus::fromMap);
        }
        /**
         * Indicates whether the main document is a secure context and explains why that is the case.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.SecureContextType secureContextType() {
            return Page.SecureContextType.of((String) require("secureContextType"));
        }
        /**
         * Indicates whether this is a cross origin isolated context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.CrossOriginIsolatedContextType crossOriginIsolatedContextType() {
            return Page.CrossOriginIsolatedContextType.of((String) require("crossOriginIsolatedContextType"));
        }
        /**
         * Indicated which gated APIs / features are available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public java.util.List<Page.GatedAPIFeatures> gatedAPIFeatures() {
            return CdpObject.requireList(require("gatedAPIFeatures"), element0 -> Page.GatedAPIFeatures.of((String) element0));
        }
        /**
         * Frame unique identifier.
         * @param id field value
         * @return this model
         */
        public Frame id(Page.FrameId id) {
            set("id", id);
            return this;
        }
        /**
         * Parent frame identifier.
         * @param parentId field value
         * @return this model
         */
        public Frame parentId(Page.FrameId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * Identifier of the loader associated with this frame.
         * @param loaderId field value
         * @return this model
         */
        public Frame loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Frame&#x27;s name as specified in the tag.
         * @param name field value
         * @return this model
         */
        public Frame name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Frame document&#x27;s URL without fragment.
         * @param url field value
         * @return this model
         */
        public Frame url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Frame document&#x27;s URL fragment including the &#x27;#&#x27;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param urlFragment field value
         * @return this model
         */
        public Frame urlFragment(String urlFragment) {
            set("urlFragment", urlFragment);
            return this;
        }
        /**
         * Frame document&#x27;s registered domain, taking the public suffixes list into account. Extracted from the Frame&#x27;s url. Example URLs: http://www.google.com/file.html -&gt; &quot;google.com&quot; http://a.b.co.uk/file.html -&gt; &quot;b.co.uk&quot;
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param domainAndRegistry field value
         * @return this model
         */
        public Frame domainAndRegistry(String domainAndRegistry) {
            set("domainAndRegistry", domainAndRegistry);
            return this;
        }
        /**
         * Frame document&#x27;s security origin.
         * @param securityOrigin field value
         * @return this model
         */
        public Frame securityOrigin(String securityOrigin) {
            set("securityOrigin", securityOrigin);
            return this;
        }
        /**
         * Additional details about the frame document&#x27;s security origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param securityOriginDetails field value
         * @return this model
         */
        public Frame securityOriginDetails(Page.SecurityOriginDetails securityOriginDetails) {
            set("securityOriginDetails", securityOriginDetails);
            return this;
        }
        /**
         * Frame document&#x27;s mimeType as determined by the browser.
         * @param mimeType field value
         * @return this model
         */
        public Frame mimeType(String mimeType) {
            set("mimeType", mimeType);
            return this;
        }
        /**
         * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike url above, this URL may contain a fragment.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param unreachableUrl field value
         * @return this model
         */
        public Frame unreachableUrl(String unreachableUrl) {
            set("unreachableUrl", unreachableUrl);
            return this;
        }
        /**
         * Indicates whether this frame was tagged as an ad and why.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adFrameStatus field value
         * @return this model
         */
        public Frame adFrameStatus(Page.AdFrameStatus adFrameStatus) {
            set("adFrameStatus", adFrameStatus);
            return this;
        }
        /**
         * Indicates whether the main document is a secure context and explains why that is the case.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param secureContextType field value
         * @return this model
         */
        public Frame secureContextType(Page.SecureContextType secureContextType) {
            set("secureContextType", secureContextType);
            return this;
        }
        /**
         * Indicates whether this is a cross origin isolated context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param crossOriginIsolatedContextType field value
         * @return this model
         */
        public Frame crossOriginIsolatedContextType(Page.CrossOriginIsolatedContextType crossOriginIsolatedContextType) {
            set("crossOriginIsolatedContextType", crossOriginIsolatedContextType);
            return this;
        }
        /**
         * Indicated which gated APIs / features are available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param gatedAPIFeatures field value
         * @return this model
         */
        public Frame gatedAPIFeatures(java.util.List<Page.GatedAPIFeatures> gatedAPIFeatures) {
            set("gatedAPIFeatures", gatedAPIFeatures);
            return this;
        }
    }
    /**
     * Information about the Resource on the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameResource extends CdpObject {
        public FrameResource() {}
        private FrameResource(Map<String, Object> values) { super(values); }
        public static FrameResource fromMap(Map<String, Object> values) {
            return new FrameResource(values);
        }
        /**
         * Resource URL.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Type of this resource.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return Network.ResourceType.of((String) require("type"));
        }
        /**
         * Resource mimeType as determined by the browser.
         * @return the protocol field value
         */
        public String mimeType() {
            return (String) require("mimeType");
        }
        /**
         * last-modified timestamp as reported by server.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> lastModified() {
            return raw("lastModified").map(value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
        }
        /**
         * Resource content size.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble contentSize() {
            return raw("contentSize").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * True if the resource failed to load.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> failed() {
            return raw("failed").map(value -> (Boolean) value);
        }
        /**
         * True if the resource was canceled during loading.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> canceled() {
            return raw("canceled").map(value -> (Boolean) value);
        }
        /**
         * Resource URL.
         * @param url field value
         * @return this model
         */
        public FrameResource url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Type of this resource.
         * @param type field value
         * @return this model
         */
        public FrameResource type(Network.ResourceType type) {
            set("type", type);
            return this;
        }
        /**
         * Resource mimeType as determined by the browser.
         * @param mimeType field value
         * @return this model
         */
        public FrameResource mimeType(String mimeType) {
            set("mimeType", mimeType);
            return this;
        }
        /**
         * last-modified timestamp as reported by server.
         * @param lastModified field value
         * @return this model
         */
        public FrameResource lastModified(Network.TimeSinceEpoch lastModified) {
            set("lastModified", lastModified);
            return this;
        }
        /**
         * Resource content size.
         * @param contentSize field value
         * @return this model
         */
        public FrameResource contentSize(double contentSize) {
            set("contentSize", contentSize);
            return this;
        }
        /**
         * True if the resource failed to load.
         * @param failed field value
         * @return this model
         */
        public FrameResource failed(boolean failed) {
            set("failed", failed);
            return this;
        }
        /**
         * True if the resource was canceled during loading.
         * @param canceled field value
         * @return this model
         */
        public FrameResource canceled(boolean canceled) {
            set("canceled", canceled);
            return this;
        }
    }
    /**
     * Information about the Frame hierarchy along with their cached resources.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameResourceTree extends CdpObject {
        public FrameResourceTree() {}
        private FrameResourceTree(Map<String, Object> values) { super(values); }
        public static FrameResourceTree fromMap(Map<String, Object> values) {
            return new FrameResourceTree(values);
        }
        /**
         * Frame information for this tree item.
         * @return the protocol field value
         */
        public Page.Frame frame() {
            return java.util.Objects.requireNonNull(Page.Frame.fromMap(CdpObject.requireObjectMap(require("frame"))));
        }
        /**
         * Child frames.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.FrameResourceTree>> childFrames() {
            return raw("childFrames").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.FrameResourceTree.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Information about frame resources.
         * @return the protocol field value
         */
        public java.util.List<Page.FrameResource> resources() {
            return CdpObject.requireList(require("resources"), element0 -> java.util.Objects.requireNonNull(Page.FrameResource.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Frame information for this tree item.
         * @param frame field value
         * @return this model
         */
        public FrameResourceTree frame(Page.Frame frame) {
            set("frame", frame);
            return this;
        }
        /**
         * Child frames.
         * @param childFrames field value
         * @return this model
         */
        public FrameResourceTree childFrames(java.util.List<Page.FrameResourceTree> childFrames) {
            set("childFrames", childFrames);
            return this;
        }
        /**
         * Information about frame resources.
         * @param resources field value
         * @return this model
         */
        public FrameResourceTree resources(java.util.List<Page.FrameResource> resources) {
            set("resources", resources);
            return this;
        }
    }
    /**
     * Information about the Frame hierarchy.
     */
    public static final class FrameTree extends CdpObject {
        public FrameTree() {}
        private FrameTree(Map<String, Object> values) { super(values); }
        public static FrameTree fromMap(Map<String, Object> values) {
            return new FrameTree(values);
        }
        /**
         * Frame information for this tree item.
         * @return the protocol field value
         */
        public Page.Frame frame() {
            return java.util.Objects.requireNonNull(Page.Frame.fromMap(CdpObject.requireObjectMap(require("frame"))));
        }
        /**
         * Child frames.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.FrameTree>> childFrames() {
            return raw("childFrames").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.FrameTree.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Frame information for this tree item.
         * @param frame field value
         * @return this model
         */
        public FrameTree frame(Page.Frame frame) {
            set("frame", frame);
            return this;
        }
        /**
         * Child frames.
         * @param childFrames field value
         * @return this model
         */
        public FrameTree childFrames(java.util.List<Page.FrameTree> childFrames) {
            set("childFrames", childFrames);
            return this;
        }
    }
    /**
     * Unique script identifier.
     */
    public static final class ScriptIdentifier implements CdpValue<String> {
        public final String value;
        public ScriptIdentifier(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof ScriptIdentifier)) return false;
            return value.equals(((ScriptIdentifier) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "ScriptIdentifier(" + value + ")"; }
    }
    /**
     * Transition type.
     */
    public enum TransitionType implements CdpValue<String> {
        LINK("link"),
        TYPED("typed"),
        ADDRESS_BAR("address_bar"),
        AUTO_BOOKMARK("auto_bookmark"),
        AUTO_SUBFRAME("auto_subframe"),
        MANUAL_SUBFRAME("manual_subframe"),
        GENERATED("generated"),
        AUTO_TOPLEVEL("auto_toplevel"),
        FORM_SUBMIT("form_submit"),
        RELOAD("reload"),
        KEYWORD("keyword"),
        KEYWORD_GENERATED("keyword_generated"),
        OTHER("other");
        public final String value;
        TransitionType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static TransitionType of(String value) {
            for (TransitionType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown TransitionType value: " + value);
        }
    }
    /**
     * Navigation history entry.
     */
    public static final class NavigationEntry extends CdpObject {
        public NavigationEntry() {}
        private NavigationEntry(Map<String, Object> values) { super(values); }
        public static NavigationEntry fromMap(Map<String, Object> values) {
            return new NavigationEntry(values);
        }
        /**
         * Unique id of the navigation history entry.
         * @return the protocol field value
         */
        public long id() {
            return ((Number) require("id")).longValue();
        }
        /**
         * URL of the navigation history entry.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * URL that the user typed in the url bar.
         * @return the protocol field value
         */
        public String userTypedURL() {
            return (String) require("userTypedURL");
        }
        /**
         * Title of the navigation history entry.
         * @return the protocol field value
         */
        public String title() {
            return (String) require("title");
        }
        /**
         * Transition type.
         * @return the protocol field value
         */
        public Page.TransitionType transitionType() {
            return Page.TransitionType.of((String) require("transitionType"));
        }
        /**
         * Unique id of the navigation history entry.
         * @param id field value
         * @return this model
         */
        public NavigationEntry id(long id) {
            set("id", id);
            return this;
        }
        /**
         * URL of the navigation history entry.
         * @param url field value
         * @return this model
         */
        public NavigationEntry url(String url) {
            set("url", url);
            return this;
        }
        /**
         * URL that the user typed in the url bar.
         * @param userTypedURL field value
         * @return this model
         */
        public NavigationEntry userTypedURL(String userTypedURL) {
            set("userTypedURL", userTypedURL);
            return this;
        }
        /**
         * Title of the navigation history entry.
         * @param title field value
         * @return this model
         */
        public NavigationEntry title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Transition type.
         * @param transitionType field value
         * @return this model
         */
        public NavigationEntry transitionType(Page.TransitionType transitionType) {
            set("transitionType", transitionType);
            return this;
        }
    }
    /**
     * Screencast frame metadata.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameMetadata extends CdpObject {
        public ScreencastFrameMetadata() {}
        private ScreencastFrameMetadata(Map<String, Object> values) { super(values); }
        public static ScreencastFrameMetadata fromMap(Map<String, Object> values) {
            return new ScreencastFrameMetadata(values);
        }
        /**
         * Top offset in DIP.
         * @return the protocol field value
         */
        public double offsetTop() {
            return ((Number) require("offsetTop")).doubleValue();
        }
        /**
         * Page scale factor.
         * @return the protocol field value
         */
        public double pageScaleFactor() {
            return ((Number) require("pageScaleFactor")).doubleValue();
        }
        /**
         * Device screen width in DIP.
         * @return the protocol field value
         */
        public double deviceWidth() {
            return ((Number) require("deviceWidth")).doubleValue();
        }
        /**
         * Device screen height in DIP.
         * @return the protocol field value
         */
        public double deviceHeight() {
            return ((Number) require("deviceHeight")).doubleValue();
        }
        /**
         * Position of horizontal scroll in CSS pixels.
         * @return the protocol field value
         */
        public double scrollOffsetX() {
            return ((Number) require("scrollOffsetX")).doubleValue();
        }
        /**
         * Position of vertical scroll in CSS pixels.
         * @return the protocol field value
         */
        public double scrollOffsetY() {
            return ((Number) require("scrollOffsetY")).doubleValue();
        }
        /**
         * Frame swap timestamp.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> timestamp() {
            return raw("timestamp").map(value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
        }
        /**
         * Top offset in DIP.
         * @param offsetTop field value
         * @return this model
         */
        public ScreencastFrameMetadata offsetTop(double offsetTop) {
            set("offsetTop", offsetTop);
            return this;
        }
        /**
         * Page scale factor.
         * @param pageScaleFactor field value
         * @return this model
         */
        public ScreencastFrameMetadata pageScaleFactor(double pageScaleFactor) {
            set("pageScaleFactor", pageScaleFactor);
            return this;
        }
        /**
         * Device screen width in DIP.
         * @param deviceWidth field value
         * @return this model
         */
        public ScreencastFrameMetadata deviceWidth(double deviceWidth) {
            set("deviceWidth", deviceWidth);
            return this;
        }
        /**
         * Device screen height in DIP.
         * @param deviceHeight field value
         * @return this model
         */
        public ScreencastFrameMetadata deviceHeight(double deviceHeight) {
            set("deviceHeight", deviceHeight);
            return this;
        }
        /**
         * Position of horizontal scroll in CSS pixels.
         * @param scrollOffsetX field value
         * @return this model
         */
        public ScreencastFrameMetadata scrollOffsetX(double scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX);
            return this;
        }
        /**
         * Position of vertical scroll in CSS pixels.
         * @param scrollOffsetY field value
         * @return this model
         */
        public ScreencastFrameMetadata scrollOffsetY(double scrollOffsetY) {
            set("scrollOffsetY", scrollOffsetY);
            return this;
        }
        /**
         * Frame swap timestamp.
         * @param timestamp field value
         * @return this model
         */
        public ScreencastFrameMetadata timestamp(Network.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Javascript dialog type.
     */
    public enum DialogType implements CdpValue<String> {
        ALERT("alert"),
        CONFIRM("confirm"),
        PROMPT("prompt"),
        BEFOREUNLOAD("beforeunload");
        public final String value;
        DialogType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static DialogType of(String value) {
            for (DialogType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DialogType value: " + value);
        }
    }
    /**
     * Error while paring app manifest.
     */
    public static final class AppManifestError extends CdpObject {
        public AppManifestError() {}
        private AppManifestError(Map<String, Object> values) { super(values); }
        public static AppManifestError fromMap(Map<String, Object> values) {
            return new AppManifestError(values);
        }
        /**
         * Error message.
         * @return the protocol field value
         */
        public String message() {
            return (String) require("message");
        }
        /**
         * If critical, this is a non-recoverable parse error.
         * @return the protocol field value
         */
        public long critical() {
            return ((Number) require("critical")).longValue();
        }
        /**
         * Error line.
         * @return the protocol field value
         */
        public long line() {
            return ((Number) require("line")).longValue();
        }
        /**
         * Error column.
         * @return the protocol field value
         */
        public long column() {
            return ((Number) require("column")).longValue();
        }
        /**
         * Error message.
         * @param message field value
         * @return this model
         */
        public AppManifestError message(String message) {
            set("message", message);
            return this;
        }
        /**
         * If critical, this is a non-recoverable parse error.
         * @param critical field value
         * @return this model
         */
        public AppManifestError critical(long critical) {
            set("critical", critical);
            return this;
        }
        /**
         * Error line.
         * @param line field value
         * @return this model
         */
        public AppManifestError line(long line) {
            set("line", line);
            return this;
        }
        /**
         * Error column.
         * @param column field value
         * @return this model
         */
        public AppManifestError column(long column) {
            set("column", column);
            return this;
        }
    }
    /**
     * Parsed app manifest properties.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AppManifestParsedProperties extends CdpObject {
        public AppManifestParsedProperties() {}
        private AppManifestParsedProperties(Map<String, Object> values) { super(values); }
        public static AppManifestParsedProperties fromMap(Map<String, Object> values) {
            return new AppManifestParsedProperties(values);
        }
        /**
         * Computed scope value
         * @return the protocol field value
         */
        public String scope() {
            return (String) require("scope");
        }
        /**
         * Computed scope value
         * @param scope field value
         * @return this model
         */
        public AppManifestParsedProperties scope(String scope) {
            set("scope", scope);
            return this;
        }
    }
    /**
     * Layout viewport position and dimensions.
     */
    public static final class LayoutViewport extends CdpObject {
        public LayoutViewport() {}
        private LayoutViewport(Map<String, Object> values) { super(values); }
        public static LayoutViewport fromMap(Map<String, Object> values) {
            return new LayoutViewport(values);
        }
        /**
         * Horizontal offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        public long pageX() {
            return ((Number) require("pageX")).longValue();
        }
        /**
         * Vertical offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        public long pageY() {
            return ((Number) require("pageY")).longValue();
        }
        /**
         * Width (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        public long clientWidth() {
            return ((Number) require("clientWidth")).longValue();
        }
        /**
         * Height (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        public long clientHeight() {
            return ((Number) require("clientHeight")).longValue();
        }
        /**
         * Horizontal offset relative to the document (CSS pixels).
         * @param pageX field value
         * @return this model
         */
        public LayoutViewport pageX(long pageX) {
            set("pageX", pageX);
            return this;
        }
        /**
         * Vertical offset relative to the document (CSS pixels).
         * @param pageY field value
         * @return this model
         */
        public LayoutViewport pageY(long pageY) {
            set("pageY", pageY);
            return this;
        }
        /**
         * Width (CSS pixels), excludes scrollbar if present.
         * @param clientWidth field value
         * @return this model
         */
        public LayoutViewport clientWidth(long clientWidth) {
            set("clientWidth", clientWidth);
            return this;
        }
        /**
         * Height (CSS pixels), excludes scrollbar if present.
         * @param clientHeight field value
         * @return this model
         */
        public LayoutViewport clientHeight(long clientHeight) {
            set("clientHeight", clientHeight);
            return this;
        }
    }
    /**
     * Visual viewport position, dimensions, and scale.
     */
    public static final class VisualViewport extends CdpObject {
        public VisualViewport() {}
        private VisualViewport(Map<String, Object> values) { super(values); }
        public static VisualViewport fromMap(Map<String, Object> values) {
            return new VisualViewport(values);
        }
        /**
         * Horizontal offset relative to the layout viewport (CSS pixels).
         * @return the protocol field value
         */
        public double offsetX() {
            return ((Number) require("offsetX")).doubleValue();
        }
        /**
         * Vertical offset relative to the layout viewport (CSS pixels).
         * @return the protocol field value
         */
        public double offsetY() {
            return ((Number) require("offsetY")).doubleValue();
        }
        /**
         * Horizontal offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        public double pageX() {
            return ((Number) require("pageX")).doubleValue();
        }
        /**
         * Vertical offset relative to the document (CSS pixels).
         * @return the protocol field value
         */
        public double pageY() {
            return ((Number) require("pageY")).doubleValue();
        }
        /**
         * Width (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        public double clientWidth() {
            return ((Number) require("clientWidth")).doubleValue();
        }
        /**
         * Height (CSS pixels), excludes scrollbar if present.
         * @return the protocol field value
         */
        public double clientHeight() {
            return ((Number) require("clientHeight")).doubleValue();
        }
        /**
         * Scale relative to the ideal viewport (size at width=device-width).
         * @return the protocol field value
         */
        public double scale() {
            return ((Number) require("scale")).doubleValue();
        }
        /**
         * Page zoom factor (CSS to device independent pixels ratio).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble zoom() {
            return raw("zoom").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Horizontal offset relative to the layout viewport (CSS pixels).
         * @param offsetX field value
         * @return this model
         */
        public VisualViewport offsetX(double offsetX) {
            set("offsetX", offsetX);
            return this;
        }
        /**
         * Vertical offset relative to the layout viewport (CSS pixels).
         * @param offsetY field value
         * @return this model
         */
        public VisualViewport offsetY(double offsetY) {
            set("offsetY", offsetY);
            return this;
        }
        /**
         * Horizontal offset relative to the document (CSS pixels).
         * @param pageX field value
         * @return this model
         */
        public VisualViewport pageX(double pageX) {
            set("pageX", pageX);
            return this;
        }
        /**
         * Vertical offset relative to the document (CSS pixels).
         * @param pageY field value
         * @return this model
         */
        public VisualViewport pageY(double pageY) {
            set("pageY", pageY);
            return this;
        }
        /**
         * Width (CSS pixels), excludes scrollbar if present.
         * @param clientWidth field value
         * @return this model
         */
        public VisualViewport clientWidth(double clientWidth) {
            set("clientWidth", clientWidth);
            return this;
        }
        /**
         * Height (CSS pixels), excludes scrollbar if present.
         * @param clientHeight field value
         * @return this model
         */
        public VisualViewport clientHeight(double clientHeight) {
            set("clientHeight", clientHeight);
            return this;
        }
        /**
         * Scale relative to the ideal viewport (size at width=device-width).
         * @param scale field value
         * @return this model
         */
        public VisualViewport scale(double scale) {
            set("scale", scale);
            return this;
        }
        /**
         * Page zoom factor (CSS to device independent pixels ratio).
         * @param zoom field value
         * @return this model
         */
        public VisualViewport zoom(double zoom) {
            set("zoom", zoom);
            return this;
        }
    }
    /**
     * Viewport for capturing screenshot.
     */
    public static final class Viewport extends CdpObject {
        public Viewport() {}
        private Viewport(Map<String, Object> values) { super(values); }
        public static Viewport fromMap(Map<String, Object> values) {
            return new Viewport(values);
        }
        /**
         * X offset in device independent pixels (dip).
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y offset in device independent pixels (dip).
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Rectangle width in device independent pixels (dip).
         * @return the protocol field value
         */
        public double width() {
            return ((Number) require("width")).doubleValue();
        }
        /**
         * Rectangle height in device independent pixels (dip).
         * @return the protocol field value
         */
        public double height() {
            return ((Number) require("height")).doubleValue();
        }
        /**
         * Page scale factor.
         * @return the protocol field value
         */
        public double scale() {
            return ((Number) require("scale")).doubleValue();
        }
        /**
         * X offset in device independent pixels (dip).
         * @param x field value
         * @return this model
         */
        public Viewport x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y offset in device independent pixels (dip).
         * @param y field value
         * @return this model
         */
        public Viewport y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Rectangle width in device independent pixels (dip).
         * @param width field value
         * @return this model
         */
        public Viewport width(double width) {
            set("width", width);
            return this;
        }
        /**
         * Rectangle height in device independent pixels (dip).
         * @param height field value
         * @return this model
         */
        public Viewport height(double height) {
            set("height", height);
            return this;
        }
        /**
         * Page scale factor.
         * @param scale field value
         * @return this model
         */
        public Viewport scale(double scale) {
            set("scale", scale);
            return this;
        }
    }
    /**
     * Generic font families collection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FontFamilies extends CdpObject {
        public FontFamilies() {}
        private FontFamilies(Map<String, Object> values) { super(values); }
        public static FontFamilies fromMap(Map<String, Object> values) {
            return new FontFamilies(values);
        }
        /**
         * The standard font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> standard() {
            return raw("standard").map(value -> (String) value);
        }
        /**
         * The fixed font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> fixed() {
            return raw("fixed").map(value -> (String) value);
        }
        /**
         * The serif font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> serif() {
            return raw("serif").map(value -> (String) value);
        }
        /**
         * The sansSerif font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sansSerif() {
            return raw("sansSerif").map(value -> (String) value);
        }
        /**
         * The cursive font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cursive() {
            return raw("cursive").map(value -> (String) value);
        }
        /**
         * The fantasy font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> fantasy() {
            return raw("fantasy").map(value -> (String) value);
        }
        /**
         * The math font-family.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> math() {
            return raw("math").map(value -> (String) value);
        }
        /**
         * The standard font-family.
         * @param standard field value
         * @return this model
         */
        public FontFamilies standard(String standard) {
            set("standard", standard);
            return this;
        }
        /**
         * The fixed font-family.
         * @param fixed field value
         * @return this model
         */
        public FontFamilies fixed(String fixed) {
            set("fixed", fixed);
            return this;
        }
        /**
         * The serif font-family.
         * @param serif field value
         * @return this model
         */
        public FontFamilies serif(String serif) {
            set("serif", serif);
            return this;
        }
        /**
         * The sansSerif font-family.
         * @param sansSerif field value
         * @return this model
         */
        public FontFamilies sansSerif(String sansSerif) {
            set("sansSerif", sansSerif);
            return this;
        }
        /**
         * The cursive font-family.
         * @param cursive field value
         * @return this model
         */
        public FontFamilies cursive(String cursive) {
            set("cursive", cursive);
            return this;
        }
        /**
         * The fantasy font-family.
         * @param fantasy field value
         * @return this model
         */
        public FontFamilies fantasy(String fantasy) {
            set("fantasy", fantasy);
            return this;
        }
        /**
         * The math font-family.
         * @param math field value
         * @return this model
         */
        public FontFamilies math(String math) {
            set("math", math);
            return this;
        }
    }
    /**
     * Font families collection for a script.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScriptFontFamilies extends CdpObject {
        public ScriptFontFamilies() {}
        private ScriptFontFamilies(Map<String, Object> values) { super(values); }
        public static ScriptFontFamilies fromMap(Map<String, Object> values) {
            return new ScriptFontFamilies(values);
        }
        /**
         * Name of the script which these font families are defined for.
         * @return the protocol field value
         */
        public String script() {
            return (String) require("script");
        }
        /**
         * Generic font families collection for the script.
         * @return the protocol field value
         */
        public Page.FontFamilies fontFamilies() {
            return java.util.Objects.requireNonNull(Page.FontFamilies.fromMap(CdpObject.requireObjectMap(require("fontFamilies"))));
        }
        /**
         * Name of the script which these font families are defined for.
         * @param script field value
         * @return this model
         */
        public ScriptFontFamilies script(String script) {
            set("script", script);
            return this;
        }
        /**
         * Generic font families collection for the script.
         * @param fontFamilies field value
         * @return this model
         */
        public ScriptFontFamilies fontFamilies(Page.FontFamilies fontFamilies) {
            set("fontFamilies", fontFamilies);
            return this;
        }
    }
    /**
     * Default font sizes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FontSizes extends CdpObject {
        public FontSizes() {}
        private FontSizes(Map<String, Object> values) { super(values); }
        public static FontSizes fromMap(Map<String, Object> values) {
            return new FontSizes(values);
        }
        /**
         * Default standard font size.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong standard() {
            return raw("standard").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Default fixed font size.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong fixed() {
            return raw("fixed").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Default standard font size.
         * @param standard field value
         * @return this model
         */
        public FontSizes standard(long standard) {
            set("standard", standard);
            return this;
        }
        /**
         * Default fixed font size.
         * @param fixed field value
         * @return this model
         */
        public FontSizes fixed(long fixed) {
            set("fixed", fixed);
            return this;
        }
    }
    /**
     * Wire values for ClientNavigationReason.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum ClientNavigationReason implements CdpValue<String> {
        ANCHORCLICK("anchorClick"),
        FORMSUBMISSIONGET("formSubmissionGet"),
        FORMSUBMISSIONPOST("formSubmissionPost"),
        HTTPHEADERREFRESH("httpHeaderRefresh"),
        INITIALFRAMENAVIGATION("initialFrameNavigation"),
        METATAGREFRESH("metaTagRefresh"),
        OTHER("other"),
        PAGEBLOCKINTERSTITIAL("pageBlockInterstitial"),
        RELOAD("reload"),
        SCRIPTINITIATED("scriptInitiated");
        public final String value;
        ClientNavigationReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ClientNavigationReason of(String value) {
            for (ClientNavigationReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ClientNavigationReason value: " + value);
        }
    }
    /**
     * Wire values for ClientNavigationDisposition.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum ClientNavigationDisposition implements CdpValue<String> {
        CURRENTTAB("currentTab"),
        NEWTAB("newTab"),
        NEWWINDOW("newWindow"),
        DOWNLOAD("download");
        public final String value;
        ClientNavigationDisposition(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ClientNavigationDisposition of(String value) {
            for (ClientNavigationDisposition constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ClientNavigationDisposition value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InstallabilityErrorArgument extends CdpObject {
        public InstallabilityErrorArgument() {}
        private InstallabilityErrorArgument(Map<String, Object> values) { super(values); }
        public static InstallabilityErrorArgument fromMap(Map<String, Object> values) {
            return new InstallabilityErrorArgument(values);
        }
        /**
         * Argument name (e.g. name:&#x27;minimum-icon-size-in-pixels&#x27;).
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Argument value (e.g. value:&#x27;64&#x27;).
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Argument name (e.g. name:&#x27;minimum-icon-size-in-pixels&#x27;).
         * @param name field value
         * @return this model
         */
        public InstallabilityErrorArgument name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Argument value (e.g. value:&#x27;64&#x27;).
         * @param value field value
         * @return this model
         */
        public InstallabilityErrorArgument value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * The installability error
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InstallabilityError extends CdpObject {
        public InstallabilityError() {}
        private InstallabilityError(Map<String, Object> values) { super(values); }
        public static InstallabilityError fromMap(Map<String, Object> values) {
            return new InstallabilityError(values);
        }
        /**
         * The error id (e.g. &#x27;manifest-missing-suitable-icon&#x27;).
         * @return the protocol field value
         */
        public String errorId() {
            return (String) require("errorId");
        }
        /**
         * The list of error arguments (e.g. {name:&#x27;minimum-icon-size-in-pixels&#x27;, value:&#x27;64&#x27;}).
         * @return the protocol field value
         */
        public java.util.List<Page.InstallabilityErrorArgument> errorArguments() {
            return CdpObject.requireList(require("errorArguments"), element0 -> java.util.Objects.requireNonNull(Page.InstallabilityErrorArgument.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * The error id (e.g. &#x27;manifest-missing-suitable-icon&#x27;).
         * @param errorId field value
         * @return this model
         */
        public InstallabilityError errorId(String errorId) {
            set("errorId", errorId);
            return this;
        }
        /**
         * The list of error arguments (e.g. {name:&#x27;minimum-icon-size-in-pixels&#x27;, value:&#x27;64&#x27;}).
         * @param errorArguments field value
         * @return this model
         */
        public InstallabilityError errorArguments(java.util.List<Page.InstallabilityErrorArgument> errorArguments) {
            set("errorArguments", errorArguments);
            return this;
        }
    }
    /**
     * The referring-policy used for the navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum ReferrerPolicy implements CdpValue<String> {
        NOREFERRER("noReferrer"),
        NOREFERRERWHENDOWNGRADE("noReferrerWhenDowngrade"),
        ORIGIN("origin"),
        ORIGINWHENCROSSORIGIN("originWhenCrossOrigin"),
        SAMEORIGIN("sameOrigin"),
        STRICTORIGIN("strictOrigin"),
        STRICTORIGINWHENCROSSORIGIN("strictOriginWhenCrossOrigin"),
        UNSAFEURL("unsafeUrl");
        public final String value;
        ReferrerPolicy(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ReferrerPolicy of(String value) {
            for (ReferrerPolicy constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ReferrerPolicy value: " + value);
        }
    }
    /**
     * Per-script compilation cache parameters for {@code Page.produceCompilationCache}
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CompilationCacheParams extends CdpObject {
        public CompilationCacheParams() {}
        private CompilationCacheParams(Map<String, Object> values) { super(values); }
        public static CompilationCacheParams fromMap(Map<String, Object> values) {
            return new CompilationCacheParams(values);
        }
        /**
         * The URL of the script to produce a compilation cache entry for.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * A hint to the backend whether eager compilation is recommended. (the actual compilation mode used is upon backend discretion).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> eager() {
            return raw("eager").map(value -> (Boolean) value);
        }
        /**
         * The URL of the script to produce a compilation cache entry for.
         * @param url field value
         * @return this model
         */
        public CompilationCacheParams url(String url) {
            set("url", url);
            return this;
        }
        /**
         * A hint to the backend whether eager compilation is recommended. (the actual compilation mode used is upon backend discretion).
         * @param eager field value
         * @return this model
         */
        public CompilationCacheParams eager(boolean eager) {
            set("eager", eager);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FileFilter extends CdpObject {
        public FileFilter() {}
        private FileFilter(Map<String, Object> values) { super(values); }
        public static FileFilter fromMap(Map<String, Object> values) {
            return new FileFilter(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return raw("name").map(value -> (String) value);
        }
        /**
         * Returns the accepts field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> accepts() {
            return raw("accepts").flatMap(value -> list(value, element0 -> (String) element0));
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public FileFilter name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the accepts field.
         * @param accepts field value
         * @return this model
         */
        public FileFilter accepts(java.util.List<String> accepts) {
            set("accepts", accepts);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FileHandler extends CdpObject {
        public FileHandler() {}
        private FileHandler(Map<String, Object> values) { super(values); }
        public static FileHandler fromMap(Map<String, Object> values) {
            return new FileHandler(values);
        }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        public String action() {
            return (String) require("action");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the icons field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.ImageResource>> icons() {
            return raw("icons").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.ImageResource.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Mimic a map, name is the key, accepts is the value.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.FileFilter>> accepts() {
            return raw("accepts").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.FileFilter.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Won&#x27;t repeat the enums, using string for easy comparison. Same as the other enums below.
         * @return the protocol field value
         */
        public String launchType() {
            return (String) require("launchType");
        }
        /**
         * Sets the action field.
         * @param action field value
         * @return this model
         */
        public FileHandler action(String action) {
            set("action", action);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public FileHandler name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the icons field.
         * @param icons field value
         * @return this model
         */
        public FileHandler icons(java.util.List<Page.ImageResource> icons) {
            set("icons", icons);
            return this;
        }
        /**
         * Mimic a map, name is the key, accepts is the value.
         * @param accepts field value
         * @return this model
         */
        public FileHandler accepts(java.util.List<Page.FileFilter> accepts) {
            set("accepts", accepts);
            return this;
        }
        /**
         * Won&#x27;t repeat the enums, using string for easy comparison. Same as the other enums below.
         * @param launchType field value
         * @return this model
         */
        public FileHandler launchType(String launchType) {
            set("launchType", launchType);
            return this;
        }
    }
    /**
     * The image definition used in both icon and screenshot.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ImageResource extends CdpObject {
        public ImageResource() {}
        private ImageResource(Map<String, Object> values) { super(values); }
        public static ImageResource fromMap(Map<String, Object> values) {
            return new ImageResource(values);
        }
        /**
         * The src field in the definition, but changing to url in favor of consistency.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Returns the sizes field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sizes() {
            return raw("sizes").map(value -> (String) value);
        }
        /**
         * Returns the type field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> type() {
            return raw("type").map(value -> (String) value);
        }
        /**
         * The src field in the definition, but changing to url in favor of consistency.
         * @param url field value
         * @return this model
         */
        public ImageResource url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the sizes field.
         * @param sizes field value
         * @return this model
         */
        public ImageResource sizes(String sizes) {
            set("sizes", sizes);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public ImageResource type(String type) {
            set("type", type);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LaunchHandler extends CdpObject {
        public LaunchHandler() {}
        private LaunchHandler(Map<String, Object> values) { super(values); }
        public static LaunchHandler fromMap(Map<String, Object> values) {
            return new LaunchHandler(values);
        }
        /**
         * Returns the clientMode field.
         * @return the protocol field value
         */
        public String clientMode() {
            return (String) require("clientMode");
        }
        /**
         * Sets the clientMode field.
         * @param clientMode field value
         * @return this model
         */
        public LaunchHandler clientMode(String clientMode) {
            set("clientMode", clientMode);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ProtocolHandler extends CdpObject {
        public ProtocolHandler() {}
        private ProtocolHandler(Map<String, Object> values) { super(values); }
        public static ProtocolHandler fromMap(Map<String, Object> values) {
            return new ProtocolHandler(values);
        }
        /**
         * Returns the protocol field.
         * @return the protocol field value
         */
        public String protocol() {
            return (String) require("protocol");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Sets the protocol field.
         * @param protocol field value
         * @return this model
         */
        public ProtocolHandler protocol(String protocol) {
            set("protocol", protocol);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public ProtocolHandler url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RelatedApplication extends CdpObject {
        public RelatedApplication() {}
        private RelatedApplication(Map<String, Object> values) { super(values); }
        public static RelatedApplication fromMap(Map<String, Object> values) {
            return new RelatedApplication(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> id() {
            return raw("id").map(value -> (String) value);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public RelatedApplication id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public RelatedApplication url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScopeExtension extends CdpObject {
        public ScopeExtension() {}
        private ScopeExtension(Map<String, Object> values) { super(values); }
        public static ScopeExtension fromMap(Map<String, Object> values) {
            return new ScopeExtension(values);
        }
        /**
         * Instead of using tuple, this field always returns the serialized string for easy understanding and comparison.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Returns the hasOriginWildcard field.
         * @return the protocol field value
         */
        public boolean hasOriginWildcard() {
            return (Boolean) require("hasOriginWildcard");
        }
        /**
         * Instead of using tuple, this field always returns the serialized string for easy understanding and comparison.
         * @param origin field value
         * @return this model
         */
        public ScopeExtension origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Sets the hasOriginWildcard field.
         * @param hasOriginWildcard field value
         * @return this model
         */
        public ScopeExtension hasOriginWildcard(boolean hasOriginWildcard) {
            set("hasOriginWildcard", hasOriginWildcard);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Screenshot extends CdpObject {
        public Screenshot() {}
        private Screenshot(Map<String, Object> values) { super(values); }
        public static Screenshot fromMap(Map<String, Object> values) {
            return new Screenshot(values);
        }
        /**
         * Returns the image field.
         * @return the protocol field value
         */
        public Page.ImageResource image() {
            return java.util.Objects.requireNonNull(Page.ImageResource.fromMap(CdpObject.requireObjectMap(require("image"))));
        }
        /**
         * Returns the formFactor field.
         * @return the protocol field value
         */
        public String formFactor() {
            return (String) require("formFactor");
        }
        /**
         * Returns the label field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> label() {
            return raw("label").map(value -> (String) value);
        }
        /**
         * Sets the image field.
         * @param image field value
         * @return this model
         */
        public Screenshot image(Page.ImageResource image) {
            set("image", image);
            return this;
        }
        /**
         * Sets the formFactor field.
         * @param formFactor field value
         * @return this model
         */
        public Screenshot formFactor(String formFactor) {
            set("formFactor", formFactor);
            return this;
        }
        /**
         * Sets the label field.
         * @param label field value
         * @return this model
         */
        public Screenshot label(String label) {
            set("label", label);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ShareTarget extends CdpObject {
        public ShareTarget() {}
        private ShareTarget(Map<String, Object> values) { super(values); }
        public static ShareTarget fromMap(Map<String, Object> values) {
            return new ShareTarget(values);
        }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        public String action() {
            return (String) require("action");
        }
        /**
         * Returns the method field.
         * @return the protocol field value
         */
        public String method() {
            return (String) require("method");
        }
        /**
         * Returns the enctype field.
         * @return the protocol field value
         */
        public String enctype() {
            return (String) require("enctype");
        }
        /**
         * Embed the ShareTargetParams
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return raw("title").map(value -> (String) value);
        }
        /**
         * Returns the text field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return raw("text").map(value -> (String) value);
        }
        /**
         * Returns the url field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Returns the files field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.FileFilter>> files() {
            return raw("files").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.FileFilter.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Sets the action field.
         * @param action field value
         * @return this model
         */
        public ShareTarget action(String action) {
            set("action", action);
            return this;
        }
        /**
         * Sets the method field.
         * @param method field value
         * @return this model
         */
        public ShareTarget method(String method) {
            set("method", method);
            return this;
        }
        /**
         * Sets the enctype field.
         * @param enctype field value
         * @return this model
         */
        public ShareTarget enctype(String enctype) {
            set("enctype", enctype);
            return this;
        }
        /**
         * Embed the ShareTargetParams
         * @param title field value
         * @return this model
         */
        public ShareTarget title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public ShareTarget text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public ShareTarget url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the files field.
         * @param files field value
         * @return this model
         */
        public ShareTarget files(java.util.List<Page.FileFilter> files) {
            set("files", files);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Shortcut extends CdpObject {
        public Shortcut() {}
        private Shortcut(Map<String, Object> values) { super(values); }
        public static Shortcut fromMap(Map<String, Object> values) {
            return new Shortcut(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public Shortcut name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public Shortcut url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WebAppManifest extends CdpObject {
        public WebAppManifest() {}
        private WebAppManifest(Map<String, Object> values) { super(values); }
        public static WebAppManifest fromMap(Map<String, Object> values) {
            return new WebAppManifest(values);
        }
        /**
         * Returns the backgroundColor field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> backgroundColor() {
            return raw("backgroundColor").map(value -> (String) value);
        }
        /**
         * The extra description provided by the manifest.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> description() {
            return raw("description").map(value -> (String) value);
        }
        /**
         * Returns the dir field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> dir() {
            return raw("dir").map(value -> (String) value);
        }
        /**
         * Returns the display field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> display() {
            return raw("display").map(value -> (String) value);
        }
        /**
         * The overrided display mode controlled by the user.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> displayOverrides() {
            return raw("displayOverrides").flatMap(value -> list(value, element0 -> (String) element0));
        }
        /**
         * The handlers to open files.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.FileHandler>> fileHandlers() {
            return raw("fileHandlers").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.FileHandler.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the icons field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.ImageResource>> icons() {
            return raw("icons").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.ImageResource.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the id field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> id() {
            return raw("id").map(value -> (String) value);
        }
        /**
         * Returns the lang field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> lang() {
            return raw("lang").map(value -> (String) value);
        }
        /**
         * TODO(crbug.com/1231886): This field is non-standard and part of a Chrome experiment. See: https://github.com/WICG/web-app-launch/blob/main/launch_handler.md
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.LaunchHandler> launchHandler() {
            return raw("launchHandler").flatMap(CdpObject::objectMap).map(Page.LaunchHandler::fromMap);
        }
        /**
         * Returns the name field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return raw("name").map(value -> (String) value);
        }
        /**
         * Returns the orientation field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> orientation() {
            return raw("orientation").map(value -> (String) value);
        }
        /**
         * Returns the preferRelatedApplications field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> preferRelatedApplications() {
            return raw("preferRelatedApplications").map(value -> (Boolean) value);
        }
        /**
         * The handlers to open protocols.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.ProtocolHandler>> protocolHandlers() {
            return raw("protocolHandlers").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.ProtocolHandler.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the relatedApplications field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.RelatedApplication>> relatedApplications() {
            return raw("relatedApplications").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.RelatedApplication.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the scope field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> scope() {
            return raw("scope").map(value -> (String) value);
        }
        /**
         * Non-standard, see https://github.com/WICG/manifest-incubations/blob/gh-pages/scope_extensions-explainer.md
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.ScopeExtension>> scopeExtensions() {
            return raw("scopeExtensions").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.ScopeExtension.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The screenshots used by chromium.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.Screenshot>> screenshots() {
            return raw("screenshots").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.Screenshot.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the shareTarget field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.ShareTarget> shareTarget() {
            return raw("shareTarget").flatMap(CdpObject::objectMap).map(Page.ShareTarget::fromMap);
        }
        /**
         * Returns the shortName field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> shortName() {
            return raw("shortName").map(value -> (String) value);
        }
        /**
         * Returns the shortcuts field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.Shortcut>> shortcuts() {
            return raw("shortcuts").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.Shortcut.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns the startUrl field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> startUrl() {
            return raw("startUrl").map(value -> (String) value);
        }
        /**
         * Returns the themeColor field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> themeColor() {
            return raw("themeColor").map(value -> (String) value);
        }
        /**
         * Sets the backgroundColor field.
         * @param backgroundColor field value
         * @return this model
         */
        public WebAppManifest backgroundColor(String backgroundColor) {
            set("backgroundColor", backgroundColor);
            return this;
        }
        /**
         * The extra description provided by the manifest.
         * @param description field value
         * @return this model
         */
        public WebAppManifest description(String description) {
            set("description", description);
            return this;
        }
        /**
         * Sets the dir field.
         * @param dir field value
         * @return this model
         */
        public WebAppManifest dir(String dir) {
            set("dir", dir);
            return this;
        }
        /**
         * Sets the display field.
         * @param display field value
         * @return this model
         */
        public WebAppManifest display(String display) {
            set("display", display);
            return this;
        }
        /**
         * The overrided display mode controlled by the user.
         * @param displayOverrides field value
         * @return this model
         */
        public WebAppManifest displayOverrides(java.util.List<String> displayOverrides) {
            set("displayOverrides", displayOverrides);
            return this;
        }
        /**
         * The handlers to open files.
         * @param fileHandlers field value
         * @return this model
         */
        public WebAppManifest fileHandlers(java.util.List<Page.FileHandler> fileHandlers) {
            set("fileHandlers", fileHandlers);
            return this;
        }
        /**
         * Sets the icons field.
         * @param icons field value
         * @return this model
         */
        public WebAppManifest icons(java.util.List<Page.ImageResource> icons) {
            set("icons", icons);
            return this;
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public WebAppManifest id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Sets the lang field.
         * @param lang field value
         * @return this model
         */
        public WebAppManifest lang(String lang) {
            set("lang", lang);
            return this;
        }
        /**
         * TODO(crbug.com/1231886): This field is non-standard and part of a Chrome experiment. See: https://github.com/WICG/web-app-launch/blob/main/launch_handler.md
         * @param launchHandler field value
         * @return this model
         */
        public WebAppManifest launchHandler(Page.LaunchHandler launchHandler) {
            set("launchHandler", launchHandler);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public WebAppManifest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the orientation field.
         * @param orientation field value
         * @return this model
         */
        public WebAppManifest orientation(String orientation) {
            set("orientation", orientation);
            return this;
        }
        /**
         * Sets the preferRelatedApplications field.
         * @param preferRelatedApplications field value
         * @return this model
         */
        public WebAppManifest preferRelatedApplications(boolean preferRelatedApplications) {
            set("preferRelatedApplications", preferRelatedApplications);
            return this;
        }
        /**
         * The handlers to open protocols.
         * @param protocolHandlers field value
         * @return this model
         */
        public WebAppManifest protocolHandlers(java.util.List<Page.ProtocolHandler> protocolHandlers) {
            set("protocolHandlers", protocolHandlers);
            return this;
        }
        /**
         * Sets the relatedApplications field.
         * @param relatedApplications field value
         * @return this model
         */
        public WebAppManifest relatedApplications(java.util.List<Page.RelatedApplication> relatedApplications) {
            set("relatedApplications", relatedApplications);
            return this;
        }
        /**
         * Sets the scope field.
         * @param scope field value
         * @return this model
         */
        public WebAppManifest scope(String scope) {
            set("scope", scope);
            return this;
        }
        /**
         * Non-standard, see https://github.com/WICG/manifest-incubations/blob/gh-pages/scope_extensions-explainer.md
         * @param scopeExtensions field value
         * @return this model
         */
        public WebAppManifest scopeExtensions(java.util.List<Page.ScopeExtension> scopeExtensions) {
            set("scopeExtensions", scopeExtensions);
            return this;
        }
        /**
         * The screenshots used by chromium.
         * @param screenshots field value
         * @return this model
         */
        public WebAppManifest screenshots(java.util.List<Page.Screenshot> screenshots) {
            set("screenshots", screenshots);
            return this;
        }
        /**
         * Sets the shareTarget field.
         * @param shareTarget field value
         * @return this model
         */
        public WebAppManifest shareTarget(Page.ShareTarget shareTarget) {
            set("shareTarget", shareTarget);
            return this;
        }
        /**
         * Sets the shortName field.
         * @param shortName field value
         * @return this model
         */
        public WebAppManifest shortName(String shortName) {
            set("shortName", shortName);
            return this;
        }
        /**
         * Sets the shortcuts field.
         * @param shortcuts field value
         * @return this model
         */
        public WebAppManifest shortcuts(java.util.List<Page.Shortcut> shortcuts) {
            set("shortcuts", shortcuts);
            return this;
        }
        /**
         * Sets the startUrl field.
         * @param startUrl field value
         * @return this model
         */
        public WebAppManifest startUrl(String startUrl) {
            set("startUrl", startUrl);
            return this;
        }
        /**
         * Sets the themeColor field.
         * @param themeColor field value
         * @return this model
         */
        public WebAppManifest themeColor(String themeColor) {
            set("themeColor", themeColor);
            return this;
        }
    }
    /**
     * The type of a frameNavigated event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum NavigationType implements CdpValue<String> {
        NAVIGATION("Navigation"),
        BACKFORWARDCACHERESTORE("BackForwardCacheRestore");
        public final String value;
        NavigationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static NavigationType of(String value) {
            for (NavigationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown NavigationType value: " + value);
        }
    }
    /**
     * List of not restored reasons for back-forward cache.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum BackForwardCacheNotRestoredReason implements CdpValue<String> {
        NOTPRIMARYMAINFRAME("NotPrimaryMainFrame"),
        BACKFORWARDCACHEDISABLED("BackForwardCacheDisabled"),
        RELATEDACTIVECONTENTSEXIST("RelatedActiveContentsExist"),
        HTTPSTATUSNOTOK("HTTPStatusNotOK"),
        SCHEMENOTHTTPORHTTPS("SchemeNotHTTPOrHTTPS"),
        LOADING("Loading"),
        WASGRANTEDMEDIAACCESS("WasGrantedMediaAccess"),
        DISABLEFORRENDERFRAMEHOSTCALLED("DisableForRenderFrameHostCalled"),
        DOMAINNOTALLOWED("DomainNotAllowed"),
        HTTPMETHODNOTGET("HTTPMethodNotGET"),
        SUBFRAMEISNAVIGATING("SubframeIsNavigating"),
        TIMEOUT("Timeout"),
        CACHELIMIT("CacheLimit"),
        JAVASCRIPTEXECUTION("JavaScriptExecution"),
        RENDERERPROCESSKILLED("RendererProcessKilled"),
        RENDERERPROCESSCRASHED("RendererProcessCrashed"),
        SCHEDULERTRACKEDFEATUREUSED("SchedulerTrackedFeatureUsed"),
        CONFLICTINGBROWSINGINSTANCE("ConflictingBrowsingInstance"),
        CACHEFLUSHED("CacheFlushed"),
        SERVICEWORKERVERSIONACTIVATION("ServiceWorkerVersionActivation"),
        SESSIONRESTORED("SessionRestored"),
        SERVICEWORKERPOSTMESSAGE("ServiceWorkerPostMessage"),
        ENTEREDBACKFORWARDCACHEBEFORESERVICEWORKERHOSTADDED("EnteredBackForwardCacheBeforeServiceWorkerHostAdded"),
        RENDERFRAMEHOSTREUSED_SAMESITE("RenderFrameHostReused_SameSite"),
        RENDERFRAMEHOSTREUSED_CROSSSITE("RenderFrameHostReused_CrossSite"),
        SERVICEWORKERCLAIM("ServiceWorkerClaim"),
        IGNOREEVENTANDEVICT("IgnoreEventAndEvict"),
        HAVEINNERCONTENTS("HaveInnerContents"),
        TIMEOUTPUTTINGINCACHE("TimeoutPuttingInCache"),
        BACKFORWARDCACHEDISABLEDBYLOWMEMORY("BackForwardCacheDisabledByLowMemory"),
        BACKFORWARDCACHEDISABLEDBYCOMMANDLINE("BackForwardCacheDisabledByCommandLine"),
        NETWORKREQUESTDATAPIPEDRAINEDASBYTESCONSUMER("NetworkRequestDatapipeDrainedAsBytesConsumer"),
        NETWORKREQUESTREDIRECTED("NetworkRequestRedirected"),
        NETWORKREQUESTTIMEOUT("NetworkRequestTimeout"),
        NETWORKEXCEEDSBUFFERLIMIT("NetworkExceedsBufferLimit"),
        NAVIGATIONCANCELLEDWHILERESTORING("NavigationCancelledWhileRestoring"),
        NOTMOSTRECENTNAVIGATIONENTRY("NotMostRecentNavigationEntry"),
        BACKFORWARDCACHEDISABLEDFORPRERENDER("BackForwardCacheDisabledForPrerender"),
        USERAGENTOVERRIDEDIFFERS("UserAgentOverrideDiffers"),
        FOREGROUNDCACHELIMIT("ForegroundCacheLimit"),
        FORWARDCACHEDISABLED("ForwardCacheDisabled"),
        BROWSINGINSTANCENOTSWAPPED("BrowsingInstanceNotSwapped"),
        BACKFORWARDCACHEDISABLEDFORDELEGATE("BackForwardCacheDisabledForDelegate"),
        UNLOADHANDLEREXISTSINMAINFRAME("UnloadHandlerExistsInMainFrame"),
        UNLOADHANDLEREXISTSINSUBFRAME("UnloadHandlerExistsInSubFrame"),
        SERVICEWORKERUNREGISTRATION("ServiceWorkerUnregistration"),
        CACHECONTROLNOSTORE("CacheControlNoStore"),
        CACHECONTROLNOSTORECOOKIEMODIFIED("CacheControlNoStoreCookieModified"),
        CACHECONTROLNOSTOREHTTPONLYCOOKIEMODIFIED("CacheControlNoStoreHTTPOnlyCookieModified"),
        NORESPONSEHEAD("NoResponseHead"),
        UNKNOWN("Unknown"),
        ACTIVATIONNAVIGATIONSDISALLOWEDFORBUG1234857("ActivationNavigationsDisallowedForBug1234857"),
        ERRORDOCUMENT("ErrorDocument"),
        FENCEDFRAMESEMBEDDER("FencedFramesEmbedder"),
        COOKIEDISABLED("CookieDisabled"),
        HTTPAUTHREQUIRED("HTTPAuthRequired"),
        COOKIEFLUSHED("CookieFlushed"),
        BROADCASTCHANNELONMESSAGE("BroadcastChannelOnMessage"),
        WEBVIEWSETTINGSCHANGED("WebViewSettingsChanged"),
        WEBVIEWJAVASCRIPTOBJECTCHANGED("WebViewJavaScriptObjectChanged"),
        WEBVIEWMESSAGELISTENERINJECTED("WebViewMessageListenerInjected"),
        WEBVIEWSAFEBROWSINGALLOWLISTCHANGED("WebViewSafeBrowsingAllowlistChanged"),
        WEBVIEWDOCUMENTSTARTJAVASCRIPTCHANGED("WebViewDocumentStartJavascriptChanged"),
        WEBSOCKET("WebSocket"),
        WEBTRANSPORT("WebTransport"),
        WEBRTC("WebRTC"),
        MAINRESOURCEHASCACHECONTROLNOSTORE("MainResourceHasCacheControlNoStore"),
        MAINRESOURCEHASCACHECONTROLNOCACHE("MainResourceHasCacheControlNoCache"),
        SUBRESOURCEHASCACHECONTROLNOSTORE("SubresourceHasCacheControlNoStore"),
        SUBRESOURCEHASCACHECONTROLNOCACHE("SubresourceHasCacheControlNoCache"),
        CONTAINSPLUGINS("ContainsPlugins"),
        DOCUMENTLOADED("DocumentLoaded"),
        OUTSTANDINGNETWORKREQUESTOTHERS("OutstandingNetworkRequestOthers"),
        REQUESTEDMIDIPERMISSION("RequestedMIDIPermission"),
        REQUESTEDAUDIOCAPTUREPERMISSION("RequestedAudioCapturePermission"),
        REQUESTEDVIDEOCAPTUREPERMISSION("RequestedVideoCapturePermission"),
        REQUESTEDBACKFORWARDCACHEBLOCKEDSENSORS("RequestedBackForwardCacheBlockedSensors"),
        REQUESTEDBACKGROUNDWORKPERMISSION("RequestedBackgroundWorkPermission"),
        BROADCASTCHANNEL("BroadcastChannel"),
        WEBXR("WebXR"),
        SHAREDWORKER("SharedWorker"),
        SHAREDWORKERMESSAGE("SharedWorkerMessage"),
        SHAREDWORKERWITHNOACTIVECLIENT("SharedWorkerWithNoActiveClient"),
        WEBLOCKS("WebLocks"),
        WEBLOCKSCONTENTION("WebLocksContention"),
        WEBHID("WebHID"),
        WEBBLUETOOTH("WebBluetooth"),
        WEBSHARE("WebShare"),
        REQUESTEDSTORAGEACCESSGRANT("RequestedStorageAccessGrant"),
        WEBNFC("WebNfc"),
        OUTSTANDINGNETWORKREQUESTFETCH("OutstandingNetworkRequestFetch"),
        OUTSTANDINGNETWORKREQUESTXHR("OutstandingNetworkRequestXHR"),
        APPBANNER("AppBanner"),
        PRINTING("Printing"),
        WEBDATABASE("WebDatabase"),
        PICTUREINPICTURE("PictureInPicture"),
        SPEECHRECOGNIZER("SpeechRecognizer"),
        IDLEMANAGER("IdleManager"),
        PAYMENTMANAGER("PaymentManager"),
        SPEECHSYNTHESIS("SpeechSynthesis"),
        KEYBOARDLOCK("KeyboardLock"),
        WEBOTPSERVICE("WebOTPService"),
        OUTSTANDINGNETWORKREQUESTDIRECTSOCKET("OutstandingNetworkRequestDirectSocket"),
        INJECTEDJAVASCRIPT("InjectedJavascript"),
        INJECTEDSTYLESHEET("InjectedStyleSheet"),
        KEEPALIVEREQUEST("KeepaliveRequest"),
        INDEXEDDBEVENT("IndexedDBEvent"),
        DUMMY("Dummy"),
        JSNETWORKREQUESTRECEIVEDCACHECONTROLNOSTORERESOURCE("JsNetworkRequestReceivedCacheControlNoStoreResource"),
        WEBRTCUSEDWITHCCNS("WebRTCUsedWithCCNS"),
        WEBTRANSPORTUSEDWITHCCNS("WebTransportUsedWithCCNS"),
        WEBSOCKETUSEDWITHCCNS("WebSocketUsedWithCCNS"),
        SMARTCARD("SmartCard"),
        LIVEMEDIASTREAMTRACK("LiveMediaStreamTrack"),
        UNLOADHANDLER("UnloadHandler"),
        PARSERABORTED("ParserAborted"),
        CONTENTSECURITYHANDLER("ContentSecurityHandler"),
        CONTENTWEBAUTHENTICATIONAPI("ContentWebAuthenticationAPI"),
        CONTENTFILECHOOSER("ContentFileChooser"),
        CONTENTSERIAL("ContentSerial"),
        CONTENTFILESYSTEMACCESS("ContentFileSystemAccess"),
        CONTENTMEDIADEVICESDISPATCHERHOST("ContentMediaDevicesDispatcherHost"),
        CONTENTWEBBLUETOOTH("ContentWebBluetooth"),
        CONTENTWEBUSB("ContentWebUSB"),
        CONTENTMEDIASESSIONSERVICE("ContentMediaSessionService"),
        CONTENTSCREENREADER("ContentScreenReader"),
        CONTENTDISCARDED("ContentDiscarded"),
        EMBEDDERPOPUPBLOCKERTABHELPER("EmbedderPopupBlockerTabHelper"),
        EMBEDDERSAFEBROWSINGTRIGGEREDPOPUPBLOCKER("EmbedderSafeBrowsingTriggeredPopupBlocker"),
        EMBEDDERSAFEBROWSINGTHREATDETAILS("EmbedderSafeBrowsingThreatDetails"),
        EMBEDDERAPPBANNERMANAGER("EmbedderAppBannerManager"),
        EMBEDDERDOMDISTILLERVIEWERSOURCE("EmbedderDomDistillerViewerSource"),
        EMBEDDERDOMDISTILLERSELFDELETINGREQUESTDELEGATE("EmbedderDomDistillerSelfDeletingRequestDelegate"),
        EMBEDDEROOMINTERVENTIONTABHELPER("EmbedderOomInterventionTabHelper"),
        EMBEDDEROFFLINEPAGE("EmbedderOfflinePage"),
        EMBEDDERCHROMEPASSWORDMANAGERCLIENTBINDCREDENTIALMANAGER("EmbedderChromePasswordManagerClientBindCredentialManager"),
        EMBEDDERPERMISSIONREQUESTMANAGER("EmbedderPermissionRequestManager"),
        EMBEDDERMODALDIALOG("EmbedderModalDialog"),
        EMBEDDEREXTENSIONS("EmbedderExtensions"),
        EMBEDDEREXTENSIONMESSAGING("EmbedderExtensionMessaging"),
        EMBEDDEREXTENSIONMESSAGINGFOROPENPORT("EmbedderExtensionMessagingForOpenPort"),
        EMBEDDEREXTENSIONSENTMESSAGETOCACHEDFRAME("EmbedderExtensionSentMessageToCachedFrame"),
        EMBEDDEREXTENSIONFRAME("EmbedderExtensionFrame"),
        REQUESTEDBYWEBVIEWCLIENT("RequestedByWebViewClient"),
        POSTMESSAGEBYWEBVIEWCLIENT("PostMessageByWebViewClient"),
        CACHECONTROLNOSTOREDEVICEBOUNDSESSIONTERMINATED("CacheControlNoStoreDeviceBoundSessionTerminated"),
        CACHELIMITPRUNEDONMODERATEMEMORYPRESSURE("CacheLimitPrunedOnModerateMemoryPressure"),
        CACHELIMITPRUNEDONCRITICALMEMORYPRESSURE("CacheLimitPrunedOnCriticalMemoryPressure");
        public final String value;
        BackForwardCacheNotRestoredReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static BackForwardCacheNotRestoredReason of(String value) {
            for (BackForwardCacheNotRestoredReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown BackForwardCacheNotRestoredReason value: " + value);
        }
    }
    /**
     * Types of not restored reasons for back-forward cache.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum BackForwardCacheNotRestoredReasonType implements CdpValue<String> {
        SUPPORTPENDING("SupportPending"),
        PAGESUPPORTNEEDED("PageSupportNeeded"),
        CIRCUMSTANTIAL("Circumstantial");
        public final String value;
        BackForwardCacheNotRestoredReasonType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static BackForwardCacheNotRestoredReasonType of(String value) {
            for (BackForwardCacheNotRestoredReasonType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown BackForwardCacheNotRestoredReasonType value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheBlockingDetails extends CdpObject {
        public BackForwardCacheBlockingDetails() {}
        private BackForwardCacheBlockingDetails(Map<String, Object> values) { super(values); }
        public static BackForwardCacheBlockingDetails fromMap(Map<String, Object> values) {
            return new BackForwardCacheBlockingDetails(values);
        }
        /**
         * Url of the file where blockage happened. Optional because of tests.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Function name where blockage happened. Optional because of anonymous functions and tests.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> function() {
            return raw("function").map(value -> (String) value);
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value
         */
        public long columnNumber() {
            return ((Number) require("columnNumber")).longValue();
        }
        /**
         * Url of the file where blockage happened. Optional because of tests.
         * @param url field value
         * @return this model
         */
        public BackForwardCacheBlockingDetails url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Function name where blockage happened. Optional because of anonymous functions and tests.
         * @param function field value
         * @return this model
         */
        public BackForwardCacheBlockingDetails function(String function) {
            set("function", function);
            return this;
        }
        /**
         * Line number in the script (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public BackForwardCacheBlockingDetails lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Column number in the script (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public BackForwardCacheBlockingDetails columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotRestoredExplanation extends CdpObject {
        public BackForwardCacheNotRestoredExplanation() {}
        private BackForwardCacheNotRestoredExplanation(Map<String, Object> values) { super(values); }
        public static BackForwardCacheNotRestoredExplanation fromMap(Map<String, Object> values) {
            return new BackForwardCacheNotRestoredExplanation(values);
        }
        /**
         * Type of the reason
         * @return the protocol field value
         */
        public Page.BackForwardCacheNotRestoredReasonType type() {
            return Page.BackForwardCacheNotRestoredReasonType.of((String) require("type"));
        }
        /**
         * Not restored reason
         * @return the protocol field value
         */
        public Page.BackForwardCacheNotRestoredReason reason() {
            return Page.BackForwardCacheNotRestoredReason.of((String) require("reason"));
        }
        /**
         * Context associated with the reason. The meaning of this context is dependent on the reason: - EmbedderExtensionSentMessageToCachedFrame: the extension ID.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> context() {
            return raw("context").map(value -> (String) value);
        }
        /**
         * Returns the details field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.BackForwardCacheBlockingDetails>> details() {
            return raw("details").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.BackForwardCacheBlockingDetails.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Type of the reason
         * @param type field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanation type(Page.BackForwardCacheNotRestoredReasonType type) {
            set("type", type);
            return this;
        }
        /**
         * Not restored reason
         * @param reason field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanation reason(Page.BackForwardCacheNotRestoredReason reason) {
            set("reason", reason);
            return this;
        }
        /**
         * Context associated with the reason. The meaning of this context is dependent on the reason: - EmbedderExtensionSentMessageToCachedFrame: the extension ID.
         * @param context field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanation context(String context) {
            set("context", context);
            return this;
        }
        /**
         * Sets the details field.
         * @param details field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanation details(java.util.List<Page.BackForwardCacheBlockingDetails> details) {
            set("details", details);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotRestoredExplanationTree extends CdpObject {
        public BackForwardCacheNotRestoredExplanationTree() {}
        private BackForwardCacheNotRestoredExplanationTree(Map<String, Object> values) { super(values); }
        public static BackForwardCacheNotRestoredExplanationTree fromMap(Map<String, Object> values) {
            return new BackForwardCacheNotRestoredExplanationTree(values);
        }
        /**
         * URL of each frame
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Not restored reasons of each frame
         * @return the protocol field value
         */
        public java.util.List<Page.BackForwardCacheNotRestoredExplanation> explanations() {
            return CdpObject.requireList(require("explanations"), element0 -> java.util.Objects.requireNonNull(Page.BackForwardCacheNotRestoredExplanation.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Array of children frame
         * @return the protocol field value
         */
        public java.util.List<Page.BackForwardCacheNotRestoredExplanationTree> children() {
            return CdpObject.requireList(require("children"), element0 -> java.util.Objects.requireNonNull(Page.BackForwardCacheNotRestoredExplanationTree.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * URL of each frame
         * @param url field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanationTree url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Not restored reasons of each frame
         * @param explanations field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanationTree explanations(java.util.List<Page.BackForwardCacheNotRestoredExplanation> explanations) {
            set("explanations", explanations);
            return this;
        }
        /**
         * Array of children frame
         * @param children field value
         * @return this model
         */
        public BackForwardCacheNotRestoredExplanationTree children(java.util.List<Page.BackForwardCacheNotRestoredExplanationTree> children) {
            set("children", children);
            return this;
        }
    }
    /**
     * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class AddScriptToEvaluateOnLoadRequest extends CdpObject {
        public AddScriptToEvaluateOnLoadRequest() {}
        /**
         * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptSource protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public AddScriptToEvaluateOnLoadRequest(String scriptSource) {
            scriptSource(scriptSource);
        }
        public static AddScriptToEvaluateOnLoadRequest fromMap(Map<String, Object> values) {
            final AddScriptToEvaluateOnLoadRequest instance_ = new AddScriptToEvaluateOnLoadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scriptSource field.
         * @return the protocol field value
         */
        public String scriptSource() {
            return (String) require("scriptSource");
        }
        /**
         * Sets the scriptSource field.
         * @param scriptSource field value
         * @return this model
         */
        public AddScriptToEvaluateOnLoadRequest scriptSource(String scriptSource) {
            set("scriptSource", scriptSource);
            return this;
        }
    }
    /**
     * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
     */
    public static final class AddScriptToEvaluateOnNewDocumentRequest extends CdpObject {
        public AddScriptToEvaluateOnNewDocumentRequest() {}
        /**
         * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
         * @param source protocol value
         */
        public AddScriptToEvaluateOnNewDocumentRequest(String source) {
            source(source);
        }
        public static AddScriptToEvaluateOnNewDocumentRequest fromMap(Map<String, Object> values) {
            final AddScriptToEvaluateOnNewDocumentRequest instance_ = new AddScriptToEvaluateOnNewDocumentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        public String source() {
            return (String) require("source");
        }
        /**
         * If specified, creates an isolated world with the given name and evaluates given script in it. This world name will be used as the ExecutionContextDescription::name when the corresponding event is emitted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> worldName() {
            return raw("worldName").map(value -> (String) value);
        }
        /**
         * Specifies whether command line API should be available to the script, defaults to false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeCommandLineAPI() {
            return raw("includeCommandLineAPI").map(value -> (Boolean) value);
        }
        /**
         * If true, runs the script immediately on existing execution contexts or worlds. Default: false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> runImmediately() {
            return raw("runImmediately").map(value -> (Boolean) value);
        }
        /**
         * Sets the source field.
         * @param source field value
         * @return this model
         */
        public AddScriptToEvaluateOnNewDocumentRequest source(String source) {
            set("source", source);
            return this;
        }
        /**
         * If specified, creates an isolated world with the given name and evaluates given script in it. This world name will be used as the ExecutionContextDescription::name when the corresponding event is emitted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param worldName field value
         * @return this model
         */
        public AddScriptToEvaluateOnNewDocumentRequest worldName(String worldName) {
            set("worldName", worldName);
            return this;
        }
        /**
         * Specifies whether command line API should be available to the script, defaults to false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeCommandLineAPI field value
         * @return this model
         */
        public AddScriptToEvaluateOnNewDocumentRequest includeCommandLineAPI(boolean includeCommandLineAPI) {
            set("includeCommandLineAPI", includeCommandLineAPI);
            return this;
        }
        /**
         * If true, runs the script immediately on existing execution contexts or worlds. Default: false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param runImmediately field value
         * @return this model
         */
        public AddScriptToEvaluateOnNewDocumentRequest runImmediately(boolean runImmediately) {
            set("runImmediately", runImmediately);
            return this;
        }
    }
    /**
     * Capture page screenshot.
     */
    public static final class CaptureScreenshotRequest extends CdpObject {
        public CaptureScreenshotRequest() {}
        public static CaptureScreenshotRequest fromMap(Map<String, Object> values) {
            final CaptureScreenshotRequest instance_ = new CaptureScreenshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Image compression format (defaults to png).
         * @return the protocol field value, empty when absent
         */
        public Optional<CaptureScreenshotFormatValues> format() {
            return raw("format").map(value -> CaptureScreenshotFormatValues.of((String) value));
        }
        /**
         * Compression quality from range [0..100] (jpeg only).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong quality() {
            return raw("quality").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Capture the screenshot of a given region only.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.Viewport> clip() {
            return raw("clip").flatMap(CdpObject::objectMap).map(Page.Viewport::fromMap);
        }
        /**
         * Capture the screenshot from the surface, rather than the view. Defaults to true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromSurface() {
            return raw("fromSurface").map(value -> (Boolean) value);
        }
        /**
         * Capture the screenshot beyond the viewport. Defaults to false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> captureBeyondViewport() {
            return raw("captureBeyondViewport").map(value -> (Boolean) value);
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
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
        public CaptureScreenshotRequest format(CaptureScreenshotFormatValues format) {
            set("format", format);
            return this;
        }
        /**
         * Compression quality from range [0..100] (jpeg only).
         * @param quality field value
         * @return this model
         */
        public CaptureScreenshotRequest quality(long quality) {
            set("quality", quality);
            return this;
        }
        /**
         * Capture the screenshot of a given region only.
         * @param clip field value
         * @return this model
         */
        public CaptureScreenshotRequest clip(Page.Viewport clip) {
            set("clip", clip);
            return this;
        }
        /**
         * Capture the screenshot from the surface, rather than the view. Defaults to true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param fromSurface field value
         * @return this model
         */
        public CaptureScreenshotRequest fromSurface(boolean fromSurface) {
            set("fromSurface", fromSurface);
            return this;
        }
        /**
         * Capture the screenshot beyond the viewport. Defaults to false.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param captureBeyondViewport field value
         * @return this model
         */
        public CaptureScreenshotRequest captureBeyondViewport(boolean captureBeyondViewport) {
            set("captureBeyondViewport", captureBeyondViewport);
            return this;
        }
        /**
         * Optimize image encoding for speed, not for resulting size (defaults to false)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param optimizeForSpeed field value
         * @return this model
         */
        public CaptureScreenshotRequest optimizeForSpeed(boolean optimizeForSpeed) {
            set("optimizeForSpeed", optimizeForSpeed);
            return this;
        }
    }
    /**
     * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CaptureSnapshotRequest extends CdpObject {
        public CaptureSnapshotRequest() {}
        public static CaptureSnapshotRequest fromMap(Map<String, Object> values) {
            final CaptureSnapshotRequest instance_ = new CaptureSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Format (defaults to mhtml).
         * @return the protocol field value, empty when absent
         */
        public Optional<CaptureSnapshotFormatValues> format() {
            return raw("format").map(value -> CaptureSnapshotFormatValues.of((String) value));
        }
        /**
         * Format (defaults to mhtml).
         * @param format field value
         * @return this model
         */
        public CaptureSnapshotRequest format(CaptureSnapshotFormatValues format) {
            set("format", format);
            return this;
        }
    }
    /**
     * Creates an isolated world for the given frame.
     */
    public static final class CreateIsolatedWorldRequest extends CdpObject {
        public CreateIsolatedWorldRequest() {}
        /**
         * Creates an isolated world for the given frame.
         * @param frameId protocol value
         */
        public CreateIsolatedWorldRequest(Page.FrameId frameId) {
            frameId(frameId);
        }
        public static CreateIsolatedWorldRequest fromMap(Map<String, Object> values) {
            final CreateIsolatedWorldRequest instance_ = new CreateIsolatedWorldRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the frame in which the isolated world should be created.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * An optional name which is reported in the Execution Context.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> worldName() {
            return raw("worldName").map(value -> (String) value);
        }
        /**
         * Whether or not universal access should be granted to the isolated world. This is a powerful option, use with caution.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> grantUniveralAccess() {
            return raw("grantUniveralAccess").map(value -> (Boolean) value);
        }
        /**
         * Id of the frame in which the isolated world should be created.
         * @param frameId field value
         * @return this model
         */
        public CreateIsolatedWorldRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * An optional name which is reported in the Execution Context.
         * @param worldName field value
         * @return this model
         */
        public CreateIsolatedWorldRequest worldName(String worldName) {
            set("worldName", worldName);
            return this;
        }
        /**
         * Whether or not universal access should be granted to the isolated world. This is a powerful option, use with caution.
         * @param grantUniveralAccess field value
         * @return this model
         */
        public CreateIsolatedWorldRequest grantUniveralAccess(boolean grantUniveralAccess) {
            set("grantUniveralAccess", grantUniveralAccess);
            return this;
        }
    }
    /**
     * Deletes browser cookie with given name, domain and path.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DeleteCookieRequest extends CdpObject {
        public DeleteCookieRequest() {}
        /**
         * Deletes browser cookie with given name, domain and path.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cookieName protocol value
         * @param url protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public DeleteCookieRequest(String cookieName, String url) {
            cookieName(cookieName).url(url);
        }
        public static DeleteCookieRequest fromMap(Map<String, Object> values) {
            final DeleteCookieRequest instance_ = new DeleteCookieRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Name of the cookie to remove.
         * @return the protocol field value
         */
        public String cookieName() {
            return (String) require("cookieName");
        }
        /**
         * URL to match cooke domain and path.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Name of the cookie to remove.
         * @param cookieName field value
         * @return this model
         */
        public DeleteCookieRequest cookieName(String cookieName) {
            set("cookieName", cookieName);
            return this;
        }
        /**
         * URL to match cooke domain and path.
         * @param url field value
         * @return this model
         */
        public DeleteCookieRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Enables page domain notifications.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If true, the {@code Page.fileChooserOpened} event will be emitted regardless of the state set by {@code Page.setInterceptFileChooserDialog} command (default: false).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableFileChooserOpenedEvent() {
            return raw("enableFileChooserOpenedEvent").map(value -> (Boolean) value);
        }
        /**
         * If true, the {@code Page.fileChooserOpened} event will be emitted regardless of the state set by {@code Page.setInterceptFileChooserDialog} command (default: false).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableFileChooserOpenedEvent field value
         * @return this model
         */
        public EnableRequest enableFileChooserOpenedEvent(boolean enableFileChooserOpenedEvent) {
            set("enableFileChooserOpenedEvent", enableFileChooserOpenedEvent);
            return this;
        }
    }
    /**
     * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
     */
    public static final class GetAppManifestRequest extends CdpObject {
        public GetAppManifestRequest() {}
        public static GetAppManifestRequest fromMap(Map<String, Object> values) {
            final GetAppManifestRequest instance_ = new GetAppManifestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the manifestId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> manifestId() {
            return raw("manifestId").map(value -> (String) value);
        }
        /**
         * Sets the manifestId field.
         * @param manifestId field value
         * @return this model
         */
        public GetAppManifestRequest manifestId(String manifestId) {
            set("manifestId", manifestId);
            return this;
        }
    }
    /**
     * Request parameters for Page.getAdScriptAncestry.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAdScriptAncestryRequest extends CdpObject {
        public GetAdScriptAncestryRequest() {}
        /**
         * Creates a new GetAdScriptAncestryRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         */
        public GetAdScriptAncestryRequest(Page.FrameId frameId) {
            frameId(frameId);
        }
        public static GetAdScriptAncestryRequest fromMap(Map<String, Object> values) {
            final GetAdScriptAncestryRequest instance_ = new GetAdScriptAncestryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GetAdScriptAncestryRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Returns content of the given resource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResourceContentRequest extends CdpObject {
        public GetResourceContentRequest() {}
        /**
         * Returns content of the given resource.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @param url protocol value
         */
        public GetResourceContentRequest(Page.FrameId frameId, String url) {
            frameId(frameId).url(url);
        }
        public static GetResourceContentRequest fromMap(Map<String, Object> values) {
            final GetResourceContentRequest instance_ = new GetResourceContentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame id to get resource for.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * URL of the resource to get content for.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Frame id to get resource for.
         * @param frameId field value
         * @return this model
         */
        public GetResourceContentRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * URL of the resource to get content for.
         * @param url field value
         * @return this model
         */
        public GetResourceContentRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     */
    public static final class HandleJavaScriptDialogRequest extends CdpObject {
        public HandleJavaScriptDialogRequest() {}
        /**
         * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
         * @param accept protocol value
         */
        public HandleJavaScriptDialogRequest(boolean accept) {
            accept(accept);
        }
        public static HandleJavaScriptDialogRequest fromMap(Map<String, Object> values) {
            final HandleJavaScriptDialogRequest instance_ = new HandleJavaScriptDialogRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to accept or dismiss the dialog.
         * @return the protocol field value
         */
        public boolean accept() {
            return (Boolean) require("accept");
        }
        /**
         * The text to enter into the dialog prompt before accepting. Used only if this is a prompt dialog.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> promptText() {
            return raw("promptText").map(value -> (String) value);
        }
        /**
         * Whether to accept or dismiss the dialog.
         * @param accept field value
         * @return this model
         */
        public HandleJavaScriptDialogRequest accept(boolean accept) {
            set("accept", accept);
            return this;
        }
        /**
         * The text to enter into the dialog prompt before accepting. Used only if this is a prompt dialog.
         * @param promptText field value
         * @return this model
         */
        public HandleJavaScriptDialogRequest promptText(String promptText) {
            set("promptText", promptText);
            return this;
        }
    }
    /**
     * Navigates current page to the given URL.
     */
    public static final class NavigateRequest extends CdpObject {
        public NavigateRequest() {}
        /**
         * Navigates current page to the given URL.
         * @param url protocol value
         */
        public NavigateRequest(String url) {
            url(url);
        }
        public static NavigateRequest fromMap(Map<String, Object> values) {
            final NavigateRequest instance_ = new NavigateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * URL to navigate the page to.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Referrer URL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> referrer() {
            return raw("referrer").map(value -> (String) value);
        }
        /**
         * Intended transition type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.TransitionType> transitionType() {
            return raw("transitionType").map(value -> Page.TransitionType.of((String) value));
        }
        /**
         * Frame id to navigate, if not specified navigates the top frame.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * Referrer-policy used for the navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.ReferrerPolicy> referrerPolicy() {
            return raw("referrerPolicy").map(value -> Page.ReferrerPolicy.of((String) value));
        }
        /**
         * URL to navigate the page to.
         * @param url field value
         * @return this model
         */
        public NavigateRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Referrer URL.
         * @param referrer field value
         * @return this model
         */
        public NavigateRequest referrer(String referrer) {
            set("referrer", referrer);
            return this;
        }
        /**
         * Intended transition type.
         * @param transitionType field value
         * @return this model
         */
        public NavigateRequest transitionType(Page.TransitionType transitionType) {
            set("transitionType", transitionType);
            return this;
        }
        /**
         * Frame id to navigate, if not specified navigates the top frame.
         * @param frameId field value
         * @return this model
         */
        public NavigateRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Referrer-policy used for the navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param referrerPolicy field value
         * @return this model
         */
        public NavigateRequest referrerPolicy(Page.ReferrerPolicy referrerPolicy) {
            set("referrerPolicy", referrerPolicy);
            return this;
        }
    }
    /**
     * Navigates current page to the given history entry.
     */
    public static final class NavigateToHistoryEntryRequest extends CdpObject {
        public NavigateToHistoryEntryRequest() {}
        /**
         * Navigates current page to the given history entry.
         * @param entryId protocol value
         */
        public NavigateToHistoryEntryRequest(long entryId) {
            entryId(entryId);
        }
        public static NavigateToHistoryEntryRequest fromMap(Map<String, Object> values) {
            final NavigateToHistoryEntryRequest instance_ = new NavigateToHistoryEntryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Unique id of the entry to navigate to.
         * @return the protocol field value
         */
        public long entryId() {
            return ((Number) require("entryId")).longValue();
        }
        /**
         * Unique id of the entry to navigate to.
         * @param entryId field value
         * @return this model
         */
        public NavigateToHistoryEntryRequest entryId(long entryId) {
            set("entryId", entryId);
            return this;
        }
    }
    /**
     * Print page as PDF.
     */
    public static final class PrintToPDFRequest extends CdpObject {
        public PrintToPDFRequest() {}
        public static PrintToPDFRequest fromMap(Map<String, Object> values) {
            final PrintToPDFRequest instance_ = new PrintToPDFRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Paper orientation. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> landscape() {
            return raw("landscape").map(value -> (Boolean) value);
        }
        /**
         * Display header and footer. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> displayHeaderFooter() {
            return raw("displayHeaderFooter").map(value -> (Boolean) value);
        }
        /**
         * Print background graphics. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> printBackground() {
            return raw("printBackground").map(value -> (Boolean) value);
        }
        /**
         * Scale of the webpage rendering. Defaults to 1.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            return raw("scale").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Paper width in inches. Defaults to 8.5 inches.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble paperWidth() {
            return raw("paperWidth").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Paper height in inches. Defaults to 11 inches.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble paperHeight() {
            return raw("paperHeight").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Top margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble marginTop() {
            return raw("marginTop").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Bottom margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble marginBottom() {
            return raw("marginBottom").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Left margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble marginLeft() {
            return raw("marginLeft").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Right margin in inches. Defaults to 1cm (~0.4 inches).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble marginRight() {
            return raw("marginRight").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Paper ranges to print, one based, e.g., &#x27;1-5, 8, 11-13&#x27;. Pages are printed in the document order, not in the order specified, and no more than once. Defaults to empty string, which implies the entire document is printed. The page numbers are quietly capped to actual page count of the document, and ranges beyond the end of the document are ignored. If this results in no pages to print, an error is reported. It is an error to specify a range with start greater than end.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pageRanges() {
            return raw("pageRanges").map(value -> (String) value);
        }
        /**
         * HTML template for the print header. Should be valid HTML markup with following classes used to inject printing values into them: - {@code date}: formatted print date - {@code title}: document title - {@code url}: document location - {@code pageNumber}: current page number - {@code totalPages}: total pages in the document
         * <p>For example, {@code &lt;span class=title&gt;&lt;/span&gt;} would generate span containing the title.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> headerTemplate() {
            return raw("headerTemplate").map(value -> (String) value);
        }
        /**
         * HTML template for the print footer. Should use the same format as the {@code headerTemplate}.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> footerTemplate() {
            return raw("footerTemplate").map(value -> (String) value);
        }
        /**
         * Whether or not to prefer page size as defined by css. Defaults to false, in which case the content will be scaled to fit the paper size.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> preferCSSPageSize() {
            return raw("preferCSSPageSize").map(value -> (Boolean) value);
        }
        /**
         * return as stream
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<PrintToPDFTransferModeValues> transferMode() {
            return raw("transferMode").map(value -> PrintToPDFTransferModeValues.of((String) value));
        }
        /**
         * Whether or not to generate tagged (accessible) PDF. Defaults to embedder choice.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generateTaggedPDF() {
            return raw("generateTaggedPDF").map(value -> (Boolean) value);
        }
        /**
         * Whether or not to embed the document outline into the PDF.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generateDocumentOutline() {
            return raw("generateDocumentOutline").map(value -> (Boolean) value);
        }
        /**
         * Paper orientation. Defaults to false.
         * @param landscape field value
         * @return this model
         */
        public PrintToPDFRequest landscape(boolean landscape) {
            set("landscape", landscape);
            return this;
        }
        /**
         * Display header and footer. Defaults to false.
         * @param displayHeaderFooter field value
         * @return this model
         */
        public PrintToPDFRequest displayHeaderFooter(boolean displayHeaderFooter) {
            set("displayHeaderFooter", displayHeaderFooter);
            return this;
        }
        /**
         * Print background graphics. Defaults to false.
         * @param printBackground field value
         * @return this model
         */
        public PrintToPDFRequest printBackground(boolean printBackground) {
            set("printBackground", printBackground);
            return this;
        }
        /**
         * Scale of the webpage rendering. Defaults to 1.
         * @param scale field value
         * @return this model
         */
        public PrintToPDFRequest scale(double scale) {
            set("scale", scale);
            return this;
        }
        /**
         * Paper width in inches. Defaults to 8.5 inches.
         * @param paperWidth field value
         * @return this model
         */
        public PrintToPDFRequest paperWidth(double paperWidth) {
            set("paperWidth", paperWidth);
            return this;
        }
        /**
         * Paper height in inches. Defaults to 11 inches.
         * @param paperHeight field value
         * @return this model
         */
        public PrintToPDFRequest paperHeight(double paperHeight) {
            set("paperHeight", paperHeight);
            return this;
        }
        /**
         * Top margin in inches. Defaults to 1cm (~0.4 inches).
         * @param marginTop field value
         * @return this model
         */
        public PrintToPDFRequest marginTop(double marginTop) {
            set("marginTop", marginTop);
            return this;
        }
        /**
         * Bottom margin in inches. Defaults to 1cm (~0.4 inches).
         * @param marginBottom field value
         * @return this model
         */
        public PrintToPDFRequest marginBottom(double marginBottom) {
            set("marginBottom", marginBottom);
            return this;
        }
        /**
         * Left margin in inches. Defaults to 1cm (~0.4 inches).
         * @param marginLeft field value
         * @return this model
         */
        public PrintToPDFRequest marginLeft(double marginLeft) {
            set("marginLeft", marginLeft);
            return this;
        }
        /**
         * Right margin in inches. Defaults to 1cm (~0.4 inches).
         * @param marginRight field value
         * @return this model
         */
        public PrintToPDFRequest marginRight(double marginRight) {
            set("marginRight", marginRight);
            return this;
        }
        /**
         * Paper ranges to print, one based, e.g., &#x27;1-5, 8, 11-13&#x27;. Pages are printed in the document order, not in the order specified, and no more than once. Defaults to empty string, which implies the entire document is printed. The page numbers are quietly capped to actual page count of the document, and ranges beyond the end of the document are ignored. If this results in no pages to print, an error is reported. It is an error to specify a range with start greater than end.
         * @param pageRanges field value
         * @return this model
         */
        public PrintToPDFRequest pageRanges(String pageRanges) {
            set("pageRanges", pageRanges);
            return this;
        }
        /**
         * HTML template for the print header. Should be valid HTML markup with following classes used to inject printing values into them: - {@code date}: formatted print date - {@code title}: document title - {@code url}: document location - {@code pageNumber}: current page number - {@code totalPages}: total pages in the document
         * <p>For example, {@code &lt;span class=title&gt;&lt;/span&gt;} would generate span containing the title.
         * @param headerTemplate field value
         * @return this model
         */
        public PrintToPDFRequest headerTemplate(String headerTemplate) {
            set("headerTemplate", headerTemplate);
            return this;
        }
        /**
         * HTML template for the print footer. Should use the same format as the {@code headerTemplate}.
         * @param footerTemplate field value
         * @return this model
         */
        public PrintToPDFRequest footerTemplate(String footerTemplate) {
            set("footerTemplate", footerTemplate);
            return this;
        }
        /**
         * Whether or not to prefer page size as defined by css. Defaults to false, in which case the content will be scaled to fit the paper size.
         * @param preferCSSPageSize field value
         * @return this model
         */
        public PrintToPDFRequest preferCSSPageSize(boolean preferCSSPageSize) {
            set("preferCSSPageSize", preferCSSPageSize);
            return this;
        }
        /**
         * return as stream
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param transferMode field value
         * @return this model
         */
        public PrintToPDFRequest transferMode(PrintToPDFTransferModeValues transferMode) {
            set("transferMode", transferMode);
            return this;
        }
        /**
         * Whether or not to generate tagged (accessible) PDF. Defaults to embedder choice.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param generateTaggedPDF field value
         * @return this model
         */
        public PrintToPDFRequest generateTaggedPDF(boolean generateTaggedPDF) {
            set("generateTaggedPDF", generateTaggedPDF);
            return this;
        }
        /**
         * Whether or not to embed the document outline into the PDF.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param generateDocumentOutline field value
         * @return this model
         */
        public PrintToPDFRequest generateDocumentOutline(boolean generateDocumentOutline) {
            set("generateDocumentOutline", generateDocumentOutline);
            return this;
        }
    }
    /**
     * Reloads given page optionally ignoring the cache.
     */
    public static final class ReloadRequest extends CdpObject {
        public ReloadRequest() {}
        public static ReloadRequest fromMap(Map<String, Object> values) {
            final ReloadRequest instance_ = new ReloadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If true, browser cache is ignored (as if the user pressed Shift+refresh).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignoreCache() {
            return raw("ignoreCache").map(value -> (Boolean) value);
        }
        /**
         * If set, the script will be injected into all frames of the inspected page after reload. Argument will be ignored if reloading dataURL origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> scriptToEvaluateOnLoad() {
            return raw("scriptToEvaluateOnLoad").map(value -> (String) value);
        }
        /**
         * If set, an error will be thrown if the target page&#x27;s main frame&#x27;s loader id does not match the provided id. This prevents accidentally reloading an unintended target in case there&#x27;s a racing navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.LoaderId> loaderId() {
            return raw("loaderId").map(value -> new Network.LoaderId((String) value));
        }
        /**
         * If true, browser cache is ignored (as if the user pressed Shift+refresh).
         * @param ignoreCache field value
         * @return this model
         */
        public ReloadRequest ignoreCache(boolean ignoreCache) {
            set("ignoreCache", ignoreCache);
            return this;
        }
        /**
         * If set, the script will be injected into all frames of the inspected page after reload. Argument will be ignored if reloading dataURL origin.
         * @param scriptToEvaluateOnLoad field value
         * @return this model
         */
        public ReloadRequest scriptToEvaluateOnLoad(String scriptToEvaluateOnLoad) {
            set("scriptToEvaluateOnLoad", scriptToEvaluateOnLoad);
            return this;
        }
        /**
         * If set, an error will be thrown if the target page&#x27;s main frame&#x27;s loader id does not match the provided id. This prevents accidentally reloading an unintended target in case there&#x27;s a racing navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param loaderId field value
         * @return this model
         */
        public ReloadRequest loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
    }
    /**
     * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RemoveScriptToEvaluateOnLoadRequest extends CdpObject {
        public RemoveScriptToEvaluateOnLoadRequest() {}
        /**
         * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param identifier protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RemoveScriptToEvaluateOnLoadRequest(Page.ScriptIdentifier identifier) {
            identifier(identifier);
        }
        public static RemoveScriptToEvaluateOnLoadRequest fromMap(Map<String, Object> values) {
            final RemoveScriptToEvaluateOnLoadRequest instance_ = new RemoveScriptToEvaluateOnLoadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Page.ScriptIdentifier identifier() {
            return new Page.ScriptIdentifier((String) require("identifier"));
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public RemoveScriptToEvaluateOnLoadRequest identifier(Page.ScriptIdentifier identifier) {
            set("identifier", identifier);
            return this;
        }
    }
    /**
     * Removes given script from the list.
     */
    public static final class RemoveScriptToEvaluateOnNewDocumentRequest extends CdpObject {
        public RemoveScriptToEvaluateOnNewDocumentRequest() {}
        /**
         * Removes given script from the list.
         * @param identifier protocol value
         */
        public RemoveScriptToEvaluateOnNewDocumentRequest(Page.ScriptIdentifier identifier) {
            identifier(identifier);
        }
        public static RemoveScriptToEvaluateOnNewDocumentRequest fromMap(Map<String, Object> values) {
            final RemoveScriptToEvaluateOnNewDocumentRequest instance_ = new RemoveScriptToEvaluateOnNewDocumentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Page.ScriptIdentifier identifier() {
            return new Page.ScriptIdentifier((String) require("identifier"));
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public RemoveScriptToEvaluateOnNewDocumentRequest identifier(Page.ScriptIdentifier identifier) {
            set("identifier", identifier);
            return this;
        }
    }
    /**
     * Acknowledges that a screencast frame has been received by the frontend.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameAckRequest extends CdpObject {
        public ScreencastFrameAckRequest() {}
        /**
         * Acknowledges that a screencast frame has been received by the frontend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sessionId protocol value
         */
        public ScreencastFrameAckRequest(long sessionId) {
            sessionId(sessionId);
        }
        public static ScreencastFrameAckRequest fromMap(Map<String, Object> values) {
            final ScreencastFrameAckRequest instance_ = new ScreencastFrameAckRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame number.
         * @return the protocol field value
         */
        public long sessionId() {
            return ((Number) require("sessionId")).longValue();
        }
        /**
         * Frame number.
         * @param sessionId field value
         * @return this model
         */
        public ScreencastFrameAckRequest sessionId(long sessionId) {
            set("sessionId", sessionId);
            return this;
        }
    }
    /**
     * Searches for given string in resource content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SearchInResourceRequest extends CdpObject {
        public SearchInResourceRequest() {}
        /**
         * Searches for given string in resource content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @param url protocol value
         * @param query protocol value
         */
        public SearchInResourceRequest(Page.FrameId frameId, String url, String query) {
            frameId(frameId).url(url).query(query);
        }
        public static SearchInResourceRequest fromMap(Map<String, Object> values) {
            final SearchInResourceRequest instance_ = new SearchInResourceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame id for resource to search in.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * URL of the resource to search in.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        public String query() {
            return (String) require("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> caseSensitive() {
            return raw("caseSensitive").map(value -> (Boolean) value);
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isRegex() {
            return raw("isRegex").map(value -> (Boolean) value);
        }
        /**
         * Frame id for resource to search in.
         * @param frameId field value
         * @return this model
         */
        public SearchInResourceRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * URL of the resource to search in.
         * @param url field value
         * @return this model
         */
        public SearchInResourceRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * String to search for.
         * @param query field value
         * @return this model
         */
        public SearchInResourceRequest query(String query) {
            set("query", query);
            return this;
        }
        /**
         * If true, search is case sensitive.
         * @param caseSensitive field value
         * @return this model
         */
        public SearchInResourceRequest caseSensitive(boolean caseSensitive) {
            set("caseSensitive", caseSensitive);
            return this;
        }
        /**
         * If true, treats string parameter as regex.
         * @param isRegex field value
         * @return this model
         */
        public SearchInResourceRequest isRegex(boolean isRegex) {
            set("isRegex", isRegex);
            return this;
        }
    }
    /**
     * Enable Chrome&#x27;s experimental ad filter on all sites.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAdBlockingEnabledRequest extends CdpObject {
        public SetAdBlockingEnabledRequest() {}
        /**
         * Enable Chrome&#x27;s experimental ad filter on all sites.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetAdBlockingEnabledRequest(boolean enabled) {
            enabled(enabled);
        }
        public static SetAdBlockingEnabledRequest fromMap(Map<String, Object> values) {
            final SetAdBlockingEnabledRequest instance_ = new SetAdBlockingEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to block ads.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
        }
        /**
         * Whether to block ads.
         * @param enabled field value
         * @return this model
         */
        public SetAdBlockingEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Enable page Content Security Policy by-passing.
     */
    public static final class SetBypassCSPRequest extends CdpObject {
        public SetBypassCSPRequest() {}
        /**
         * Enable page Content Security Policy by-passing.
         * @param enabled protocol value
         */
        public SetBypassCSPRequest(boolean enabled) {
            enabled(enabled);
        }
        public static SetBypassCSPRequest fromMap(Map<String, Object> values) {
            final SetBypassCSPRequest instance_ = new SetBypassCSPRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to bypass page CSP.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
        }
        /**
         * Whether to bypass page CSP.
         * @param enabled field value
         * @return this model
         */
        public SetBypassCSPRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Get Permissions Policy state on given frame.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetPermissionsPolicyStateRequest extends CdpObject {
        public GetPermissionsPolicyStateRequest() {}
        /**
         * Get Permissions Policy state on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         */
        public GetPermissionsPolicyStateRequest(Page.FrameId frameId) {
            frameId(frameId);
        }
        public static GetPermissionsPolicyStateRequest fromMap(Map<String, Object> values) {
            final GetPermissionsPolicyStateRequest instance_ = new GetPermissionsPolicyStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GetPermissionsPolicyStateRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Get Origin Trials on given frame.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetOriginTrialsRequest extends CdpObject {
        public GetOriginTrialsRequest() {}
        /**
         * Get Origin Trials on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         */
        public GetOriginTrialsRequest(Page.FrameId frameId) {
            frameId(frameId);
        }
        public static GetOriginTrialsRequest fromMap(Map<String, Object> values) {
            final GetOriginTrialsRequest instance_ = new GetOriginTrialsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GetOriginTrialsRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDeviceMetricsOverrideRequest extends CdpObject {
        public SetDeviceMetricsOverrideRequest() {}
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param width protocol value
         * @param height protocol value
         * @param deviceScaleFactor protocol value
         * @param mobile protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
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
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            return raw("scale").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenWidth() {
            return raw("screenWidth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong screenHeight() {
            return raw("screenHeight").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionX() {
            return raw("positionX").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong positionY() {
            return raw("positionY").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
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
         * The viewport dimensions and scale. If not set, the override is cleared.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.Viewport> viewport() {
            return raw("viewport").flatMap(CdpObject::objectMap).map(Page.Viewport::fromMap);
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
         * @param scale field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest scale(double scale) {
            set("scale", scale);
            return this;
        }
        /**
         * Overriding screen width value in pixels (minimum 0, maximum 10000000).
         * @param screenWidth field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenWidth(long screenWidth) {
            set("screenWidth", screenWidth);
            return this;
        }
        /**
         * Overriding screen height value in pixels (minimum 0, maximum 10000000).
         * @param screenHeight field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest screenHeight(long screenHeight) {
            set("screenHeight", screenHeight);
            return this;
        }
        /**
         * Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
         * @param positionX field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionX(long positionX) {
            set("positionX", positionX);
            return this;
        }
        /**
         * Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
         * @param positionY field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest positionY(long positionY) {
            set("positionY", positionY);
            return this;
        }
        /**
         * Do not set visible view size, rely upon explicit setVisibleSize call.
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
         * The viewport dimensions and scale. If not set, the override is cleared.
         * @param viewport field value
         * @return this model
         */
        public SetDeviceMetricsOverrideRequest viewport(Page.Viewport viewport) {
            set("viewport", viewport);
            return this;
        }
    }
    /**
     * Overrides the Device Orientation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDeviceOrientationOverrideRequest extends CdpObject {
        public SetDeviceOrientationOverrideRequest() {}
        /**
         * Overrides the Device Orientation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param alpha protocol value
         * @param beta protocol value
         * @param gamma protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetDeviceOrientationOverrideRequest(double alpha, double beta, double gamma) {
            alpha(alpha).beta(beta).gamma(gamma);
        }
        public static SetDeviceOrientationOverrideRequest fromMap(Map<String, Object> values) {
            final SetDeviceOrientationOverrideRequest instance_ = new SetDeviceOrientationOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Mock alpha
         * @return the protocol field value
         */
        public double alpha() {
            return ((Number) require("alpha")).doubleValue();
        }
        /**
         * Mock beta
         * @return the protocol field value
         */
        public double beta() {
            return ((Number) require("beta")).doubleValue();
        }
        /**
         * Mock gamma
         * @return the protocol field value
         */
        public double gamma() {
            return ((Number) require("gamma")).doubleValue();
        }
        /**
         * Mock alpha
         * @param alpha field value
         * @return this model
         */
        public SetDeviceOrientationOverrideRequest alpha(double alpha) {
            set("alpha", alpha);
            return this;
        }
        /**
         * Mock beta
         * @param beta field value
         * @return this model
         */
        public SetDeviceOrientationOverrideRequest beta(double beta) {
            set("beta", beta);
            return this;
        }
        /**
         * Mock gamma
         * @param gamma field value
         * @return this model
         */
        public SetDeviceOrientationOverrideRequest gamma(double gamma) {
            set("gamma", gamma);
            return this;
        }
    }
    /**
     * Set generic font families.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFontFamiliesRequest extends CdpObject {
        public SetFontFamiliesRequest() {}
        /**
         * Set generic font families.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param fontFamilies protocol value
         */
        public SetFontFamiliesRequest(Page.FontFamilies fontFamilies) {
            fontFamilies(fontFamilies);
        }
        public static SetFontFamiliesRequest fromMap(Map<String, Object> values) {
            final SetFontFamiliesRequest instance_ = new SetFontFamiliesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Specifies font families to set. If a font family is not specified, it won&#x27;t be changed.
         * @return the protocol field value
         */
        public Page.FontFamilies fontFamilies() {
            return java.util.Objects.requireNonNull(Page.FontFamilies.fromMap(CdpObject.requireObjectMap(require("fontFamilies"))));
        }
        /**
         * Specifies font families to set for individual scripts.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Page.ScriptFontFamilies>> forScripts() {
            return raw("forScripts").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Page.ScriptFontFamilies.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Specifies font families to set. If a font family is not specified, it won&#x27;t be changed.
         * @param fontFamilies field value
         * @return this model
         */
        public SetFontFamiliesRequest fontFamilies(Page.FontFamilies fontFamilies) {
            set("fontFamilies", fontFamilies);
            return this;
        }
        /**
         * Specifies font families to set for individual scripts.
         * @param forScripts field value
         * @return this model
         */
        public SetFontFamiliesRequest forScripts(java.util.List<Page.ScriptFontFamilies> forScripts) {
            set("forScripts", forScripts);
            return this;
        }
    }
    /**
     * Set default font sizes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetFontSizesRequest extends CdpObject {
        public SetFontSizesRequest() {}
        /**
         * Set default font sizes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param fontSizes protocol value
         */
        public SetFontSizesRequest(Page.FontSizes fontSizes) {
            fontSizes(fontSizes);
        }
        public static SetFontSizesRequest fromMap(Map<String, Object> values) {
            final SetFontSizesRequest instance_ = new SetFontSizesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Specifies font sizes to set. If a font size is not specified, it won&#x27;t be changed.
         * @return the protocol field value
         */
        public Page.FontSizes fontSizes() {
            return java.util.Objects.requireNonNull(Page.FontSizes.fromMap(CdpObject.requireObjectMap(require("fontSizes"))));
        }
        /**
         * Specifies font sizes to set. If a font size is not specified, it won&#x27;t be changed.
         * @param fontSizes field value
         * @return this model
         */
        public SetFontSizesRequest fontSizes(Page.FontSizes fontSizes) {
            set("fontSizes", fontSizes);
            return this;
        }
    }
    /**
     * Sets given markup as the document&#x27;s HTML.
     */
    public static final class SetDocumentContentRequest extends CdpObject {
        public SetDocumentContentRequest() {}
        /**
         * Sets given markup as the document&#x27;s HTML.
         * @param frameId protocol value
         * @param html protocol value
         */
        public SetDocumentContentRequest(Page.FrameId frameId, String html) {
            frameId(frameId).html(html);
        }
        public static SetDocumentContentRequest fromMap(Map<String, Object> values) {
            final SetDocumentContentRequest instance_ = new SetDocumentContentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame id to set HTML for.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * HTML content to set.
         * @return the protocol field value
         */
        public String html() {
            return (String) require("html");
        }
        /**
         * Frame id to set HTML for.
         * @param frameId field value
         * @return this model
         */
        public SetDocumentContentRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * HTML content to set.
         * @param html field value
         * @return this model
         */
        public SetDocumentContentRequest html(String html) {
            set("html", html);
            return this;
        }
    }
    /**
     * Set the behavior when downloading a file.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetDownloadBehaviorRequest extends CdpObject {
        public SetDownloadBehaviorRequest() {}
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetDownloadBehaviorRequest(SetDownloadBehaviorBehaviorValues behavior) {
            behavior(behavior);
        }
        public static SetDownloadBehaviorRequest fromMap(Map<String, Object> values) {
            final SetDownloadBehaviorRequest instance_ = new SetDownloadBehaviorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
         * @return the protocol field value
         */
        public SetDownloadBehaviorBehaviorValues behavior() {
            return SetDownloadBehaviorBehaviorValues.of((String) require("behavior"));
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27;
         * @return the protocol field value, empty when absent
         */
        public Optional<String> downloadPath() {
            return raw("downloadPath").map(value -> (String) value);
        }
        /**
         * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
         * @param behavior field value
         * @return this model
         */
        public SetDownloadBehaviorRequest behavior(SetDownloadBehaviorBehaviorValues behavior) {
            set("behavior", behavior);
            return this;
        }
        /**
         * The default path to save downloaded files to. This is required if behavior is set to &#x27;allow&#x27;
         * @param downloadPath field value
         * @return this model
         */
        public SetDownloadBehaviorRequest downloadPath(String downloadPath) {
            set("downloadPath", downloadPath);
            return this;
        }
    }
    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
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
    }
    /**
     * Controls whether page will emit lifecycle events.
     */
    public static final class SetLifecycleEventsEnabledRequest extends CdpObject {
        public SetLifecycleEventsEnabledRequest() {}
        /**
         * Controls whether page will emit lifecycle events.
         * @param enabled protocol value
         */
        public SetLifecycleEventsEnabledRequest(boolean enabled) {
            enabled(enabled);
        }
        public static SetLifecycleEventsEnabledRequest fromMap(Map<String, Object> values) {
            final SetLifecycleEventsEnabledRequest instance_ = new SetLifecycleEventsEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If true, starts emitting lifecycle events.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
        }
        /**
         * If true, starts emitting lifecycle events.
         * @param enabled field value
         * @return this model
         */
        public SetLifecycleEventsEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Toggles mouse event-based touch event emulation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetTouchEmulationEnabledRequest extends CdpObject {
        public SetTouchEmulationEnabledRequest() {}
        /**
         * Toggles mouse event-based touch event emulation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
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
         * Touch/gesture events configuration. Default: current platform.
         * @return the protocol field value, empty when absent
         */
        public Optional<SetTouchEmulationEnabledConfigurationValues> configuration() {
            return raw("configuration").map(value -> SetTouchEmulationEnabledConfigurationValues.of((String) value));
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
         * Touch/gesture events configuration. Default: current platform.
         * @param configuration field value
         * @return this model
         */
        public SetTouchEmulationEnabledRequest configuration(SetTouchEmulationEnabledConfigurationValues configuration) {
            set("configuration", configuration);
            return this;
        }
    }
    /**
     * Starts sending each frame using the {@code screencastFrame} event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StartScreencastRequest extends CdpObject {
        public StartScreencastRequest() {}
        public static StartScreencastRequest fromMap(Map<String, Object> values) {
            final StartScreencastRequest instance_ = new StartScreencastRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Image compression format.
         * @return the protocol field value, empty when absent
         */
        public Optional<StartScreencastFormatValues> format() {
            return raw("format").map(value -> StartScreencastFormatValues.of((String) value));
        }
        /**
         * Compression quality from range [0..100].
         * @return the protocol field value, empty when absent
         */
        public OptionalLong quality() {
            return raw("quality").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Maximum screenshot width.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxWidth() {
            return raw("maxWidth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Maximum screenshot height.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxHeight() {
            return raw("maxHeight").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Send every n-th frame.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong everyNthFrame() {
            return raw("everyNthFrame").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Image compression format.
         * @param format field value
         * @return this model
         */
        public StartScreencastRequest format(StartScreencastFormatValues format) {
            set("format", format);
            return this;
        }
        /**
         * Compression quality from range [0..100].
         * @param quality field value
         * @return this model
         */
        public StartScreencastRequest quality(long quality) {
            set("quality", quality);
            return this;
        }
        /**
         * Maximum screenshot width.
         * @param maxWidth field value
         * @return this model
         */
        public StartScreencastRequest maxWidth(long maxWidth) {
            set("maxWidth", maxWidth);
            return this;
        }
        /**
         * Maximum screenshot height.
         * @param maxHeight field value
         * @return this model
         */
        public StartScreencastRequest maxHeight(long maxHeight) {
            set("maxHeight", maxHeight);
            return this;
        }
        /**
         * Send every n-th frame.
         * @param everyNthFrame field value
         * @return this model
         */
        public StartScreencastRequest everyNthFrame(long everyNthFrame) {
            set("everyNthFrame", everyNthFrame);
            return this;
        }
    }
    /**
     * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetWebLifecycleStateRequest extends CdpObject {
        public SetWebLifecycleStateRequest() {}
        /**
         * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param state protocol value
         */
        public SetWebLifecycleStateRequest(SetWebLifecycleStateStateValues state) {
            state(state);
        }
        public static SetWebLifecycleStateRequest fromMap(Map<String, Object> values) {
            final SetWebLifecycleStateRequest instance_ = new SetWebLifecycleStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Target lifecycle state
         * @return the protocol field value
         */
        public SetWebLifecycleStateStateValues state() {
            return SetWebLifecycleStateStateValues.of((String) require("state"));
        }
        /**
         * Target lifecycle state
         * @param state field value
         * @return this model
         */
        public SetWebLifecycleStateRequest state(SetWebLifecycleStateStateValues state) {
            set("state", state);
            return this;
        }
    }
    /**
     * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ProduceCompilationCacheRequest extends CdpObject {
        public ProduceCompilationCacheRequest() {}
        /**
         * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scripts protocol value
         */
        public ProduceCompilationCacheRequest(java.util.List<Page.CompilationCacheParams> scripts) {
            scripts(scripts);
        }
        public static ProduceCompilationCacheRequest fromMap(Map<String, Object> values) {
            final ProduceCompilationCacheRequest instance_ = new ProduceCompilationCacheRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scripts field.
         * @return the protocol field value
         */
        public java.util.List<Page.CompilationCacheParams> scripts() {
            return CdpObject.requireList(require("scripts"), element0 -> java.util.Objects.requireNonNull(Page.CompilationCacheParams.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Sets the scripts field.
         * @param scripts field value
         * @return this model
         */
        public ProduceCompilationCacheRequest scripts(java.util.List<Page.CompilationCacheParams> scripts) {
            set("scripts", scripts);
            return this;
        }
    }
    /**
     * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AddCompilationCacheRequest extends CdpObject {
        public AddCompilationCacheRequest() {}
        /**
         * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param url protocol value
         * @param data protocol value
         */
        public AddCompilationCacheRequest(String url, String data) {
            url(url).data(data);
        }
        public static AddCompilationCacheRequest fromMap(Map<String, Object> values) {
            final AddCompilationCacheRequest instance_ = new AddCompilationCacheRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Base64-encoded data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public AddCompilationCacheRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Base64-encoded data (Encoded as a base64 string when passed over JSON)
         * @param data field value
         * @return this model
         */
        public AddCompilationCacheRequest data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSPCTransactionModeRequest extends CdpObject {
        public SetSPCTransactionModeRequest() {}
        /**
         * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param mode protocol value
         */
        public SetSPCTransactionModeRequest(SetSPCTransactionModeModeValues mode) {
            mode(mode);
        }
        public static SetSPCTransactionModeRequest fromMap(Map<String, Object> values) {
            final SetSPCTransactionModeRequest instance_ = new SetSPCTransactionModeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the mode field.
         * @return the protocol field value
         */
        public SetSPCTransactionModeModeValues mode() {
            return SetSPCTransactionModeModeValues.of((String) require("mode"));
        }
        /**
         * Sets the mode field.
         * @param mode field value
         * @return this model
         */
        public SetSPCTransactionModeRequest mode(SetSPCTransactionModeModeValues mode) {
            set("mode", mode);
            return this;
        }
    }
    /**
     * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetRPHRegistrationModeRequest extends CdpObject {
        public SetRPHRegistrationModeRequest() {}
        /**
         * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param mode protocol value
         */
        public SetRPHRegistrationModeRequest(SetRPHRegistrationModeModeValues mode) {
            mode(mode);
        }
        public static SetRPHRegistrationModeRequest fromMap(Map<String, Object> values) {
            final SetRPHRegistrationModeRequest instance_ = new SetRPHRegistrationModeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the mode field.
         * @return the protocol field value
         */
        public SetRPHRegistrationModeModeValues mode() {
            return SetRPHRegistrationModeModeValues.of((String) require("mode"));
        }
        /**
         * Sets the mode field.
         * @param mode field value
         * @return this model
         */
        public SetRPHRegistrationModeRequest mode(SetRPHRegistrationModeModeValues mode) {
            set("mode", mode);
            return this;
        }
    }
    /**
     * Generates a report for testing.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GenerateTestReportRequest extends CdpObject {
        public GenerateTestReportRequest() {}
        /**
         * Generates a report for testing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param message protocol value
         */
        public GenerateTestReportRequest(String message) {
            message(message);
        }
        public static GenerateTestReportRequest fromMap(Map<String, Object> values) {
            final GenerateTestReportRequest instance_ = new GenerateTestReportRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Message to be displayed in the report.
         * @return the protocol field value
         */
        public String message() {
            return (String) require("message");
        }
        /**
         * Specifies the endpoint group to deliver the report to.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> group() {
            return raw("group").map(value -> (String) value);
        }
        /**
         * Message to be displayed in the report.
         * @param message field value
         * @return this model
         */
        public GenerateTestReportRequest message(String message) {
            set("message", message);
            return this;
        }
        /**
         * Specifies the endpoint group to deliver the report to.
         * @param group field value
         * @return this model
         */
        public GenerateTestReportRequest group(String group) {
            set("group", group);
            return this;
        }
    }
    /**
     * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
     */
    public static final class SetInterceptFileChooserDialogRequest extends CdpObject {
        public SetInterceptFileChooserDialogRequest() {}
        /**
         * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
         * @param enabled protocol value
         */
        public SetInterceptFileChooserDialogRequest(boolean enabled) {
            enabled(enabled);
        }
        public static SetInterceptFileChooserDialogRequest fromMap(Map<String, Object> values) {
            final SetInterceptFileChooserDialogRequest instance_ = new SetInterceptFileChooserDialogRequest();
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
         * If true, cancels the dialog by emitting relevant events (if any) in addition to not showing it if the interception is enabled (default: false).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> cancel() {
            return raw("cancel").map(value -> (Boolean) value);
        }
        /**
         * Sets the enabled field.
         * @param enabled field value
         * @return this model
         */
        public SetInterceptFileChooserDialogRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
        /**
         * If true, cancels the dialog by emitting relevant events (if any) in addition to not showing it if the interception is enabled (default: false).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cancel field value
         * @return this model
         */
        public SetInterceptFileChooserDialogRequest cancel(boolean cancel) {
            set("cancel", cancel);
            return this;
        }
    }
    /**
     * Enable/disable prerendering manually.
     * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
     * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetPrerenderingAllowedRequest extends CdpObject {
        public SetPrerenderingAllowedRequest() {}
        /**
         * Enable/disable prerendering manually.
         * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
         * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isAllowed protocol value
         */
        public SetPrerenderingAllowedRequest(boolean isAllowed) {
            isAllowed(isAllowed);
        }
        public static SetPrerenderingAllowedRequest fromMap(Map<String, Object> values) {
            final SetPrerenderingAllowedRequest instance_ = new SetPrerenderingAllowedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the isAllowed field.
         * @return the protocol field value
         */
        public boolean isAllowed() {
            return (Boolean) require("isAllowed");
        }
        /**
         * Sets the isAllowed field.
         * @param isAllowed field value
         * @return this model
         */
        public SetPrerenderingAllowedRequest isAllowed(boolean isAllowed) {
            set("isAllowed", isAllowed);
            return this;
        }
    }
    /**
     * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnnotatedPageContentRequest extends CdpObject {
        public GetAnnotatedPageContentRequest() {}
        public static GetAnnotatedPageContentRequest fromMap(Map<String, Object> values) {
            final GetAnnotatedPageContentRequest instance_ = new GetAnnotatedPageContentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to include actionable information. Defaults to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeActionableInformation() {
            return raw("includeActionableInformation").map(value -> (Boolean) value);
        }
        /**
         * Whether to include actionable information. Defaults to true.
         * @param includeActionableInformation field value
         * @return this model
         */
        public GetAnnotatedPageContentRequest includeActionableInformation(boolean includeActionableInformation) {
            set("includeActionableInformation", includeActionableInformation);
            return this;
        }
    }
    /**
     * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
     */
    public static final class GetAppManifestResult extends CdpObject {
        public GetAppManifestResult() {}
        private GetAppManifestResult(Map<String, Object> values) { super(values); }
        public static GetAppManifestResult fromMap(Map<String, Object> values) {
            return new GetAppManifestResult(values);
        }
        /**
         * Manifest location.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Returns the errors field.
         * @return the protocol field value
         */
        public java.util.List<Page.AppManifestError> errors() {
            return CdpObject.requireList(require("errors"), element0 -> java.util.Objects.requireNonNull(Page.AppManifestError.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Manifest content.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return raw("data").map(value -> (String) value);
        }
        /**
         * Parsed manifest properties. Deprecated, use manifest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Page.AppManifestParsedProperties> parsed() {
            return raw("parsed").flatMap(CdpObject::objectMap).map(Page.AppManifestParsedProperties::fromMap);
        }
        /**
         * Returns the manifest field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.WebAppManifest manifest() {
            return java.util.Objects.requireNonNull(Page.WebAppManifest.fromMap(CdpObject.requireObjectMap(require("manifest"))));
        }
        /**
         * Manifest location.
         * @param url field value
         * @return this model
         */
        public GetAppManifestResult url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the errors field.
         * @param errors field value
         * @return this model
         */
        public GetAppManifestResult errors(java.util.List<Page.AppManifestError> errors) {
            set("errors", errors);
            return this;
        }
        /**
         * Manifest content.
         * @param data field value
         * @return this model
         */
        public GetAppManifestResult data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Parsed manifest properties. Deprecated, use manifest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parsed field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetAppManifestResult parsed(Page.AppManifestParsedProperties parsed) {
            set("parsed", parsed);
            return this;
        }
        /**
         * Sets the manifest field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param manifest field value
         * @return this model
         */
        public GetAppManifestResult manifest(Page.WebAppManifest manifest) {
            set("manifest", manifest);
            return this;
        }
    }
    /**
     * Returns the unique (PWA) app id. Only returns values if the feature flag &#x27;WebAppEnableManifestId&#x27; is enabled
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAppIdResult extends CdpObject {
        public GetAppIdResult() {}
        private GetAppIdResult(Map<String, Object> values) { super(values); }
        public static GetAppIdResult fromMap(Map<String, Object> values) {
            return new GetAppIdResult(values);
        }
        /**
         * App id, either from manifest&#x27;s id attribute or computed from start_url
         * @return the protocol field value, empty when absent
         */
        public Optional<String> appId() {
            return raw("appId").map(value -> (String) value);
        }
        /**
         * Recommendation for manifest&#x27;s id attribute to match current id computed from start_url
         * @return the protocol field value, empty when absent
         */
        public Optional<String> recommendedId() {
            return raw("recommendedId").map(value -> (String) value);
        }
        /**
         * App id, either from manifest&#x27;s id attribute or computed from start_url
         * @param appId field value
         * @return this model
         */
        public GetAppIdResult appId(String appId) {
            set("appId", appId);
            return this;
        }
        /**
         * Recommendation for manifest&#x27;s id attribute to match current id computed from start_url
         * @param recommendedId field value
         * @return this model
         */
        public GetAppIdResult recommendedId(String recommendedId) {
            set("recommendedId", recommendedId);
            return this;
        }
    }
    /**
     * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
     */
    public static final class GetLayoutMetricsResult extends CdpObject {
        public GetLayoutMetricsResult() {}
        private GetLayoutMetricsResult(Map<String, Object> values) { super(values); }
        public static GetLayoutMetricsResult fromMap(Map<String, Object> values) {
            return new GetLayoutMetricsResult(values);
        }
        /**
         * Deprecated metrics relating to the layout viewport. Is in device pixels. Use {@code cssLayoutViewport} instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Page.LayoutViewport layoutViewport() {
            return java.util.Objects.requireNonNull(Page.LayoutViewport.fromMap(CdpObject.requireObjectMap(require("layoutViewport"))));
        }
        /**
         * Deprecated metrics relating to the visual viewport. Is in device pixels. Use {@code cssVisualViewport} instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Page.VisualViewport visualViewport() {
            return java.util.Objects.requireNonNull(Page.VisualViewport.fromMap(CdpObject.requireObjectMap(require("visualViewport"))));
        }
        /**
         * Deprecated size of scrollable area. Is in DP. Use {@code cssContentSize} instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public DOM.Rect contentSize() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("contentSize"))));
        }
        /**
         * Metrics relating to the layout viewport in CSS pixels.
         * @return the protocol field value
         */
        public Page.LayoutViewport cssLayoutViewport() {
            return java.util.Objects.requireNonNull(Page.LayoutViewport.fromMap(CdpObject.requireObjectMap(require("cssLayoutViewport"))));
        }
        /**
         * Metrics relating to the visual viewport in CSS pixels.
         * @return the protocol field value
         */
        public Page.VisualViewport cssVisualViewport() {
            return java.util.Objects.requireNonNull(Page.VisualViewport.fromMap(CdpObject.requireObjectMap(require("cssVisualViewport"))));
        }
        /**
         * Size of scrollable area in CSS pixels.
         * @return the protocol field value
         */
        public DOM.Rect cssContentSize() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("cssContentSize"))));
        }
        /**
         * Deprecated metrics relating to the layout viewport. Is in device pixels. Use {@code cssLayoutViewport} instead.
         * @param layoutViewport field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetLayoutMetricsResult layoutViewport(Page.LayoutViewport layoutViewport) {
            set("layoutViewport", layoutViewport);
            return this;
        }
        /**
         * Deprecated metrics relating to the visual viewport. Is in device pixels. Use {@code cssVisualViewport} instead.
         * @param visualViewport field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetLayoutMetricsResult visualViewport(Page.VisualViewport visualViewport) {
            set("visualViewport", visualViewport);
            return this;
        }
        /**
         * Deprecated size of scrollable area. Is in DP. Use {@code cssContentSize} instead.
         * @param contentSize field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetLayoutMetricsResult contentSize(DOM.Rect contentSize) {
            set("contentSize", contentSize);
            return this;
        }
        /**
         * Metrics relating to the layout viewport in CSS pixels.
         * @param cssLayoutViewport field value
         * @return this model
         */
        public GetLayoutMetricsResult cssLayoutViewport(Page.LayoutViewport cssLayoutViewport) {
            set("cssLayoutViewport", cssLayoutViewport);
            return this;
        }
        /**
         * Metrics relating to the visual viewport in CSS pixels.
         * @param cssVisualViewport field value
         * @return this model
         */
        public GetLayoutMetricsResult cssVisualViewport(Page.VisualViewport cssVisualViewport) {
            set("cssVisualViewport", cssVisualViewport);
            return this;
        }
        /**
         * Size of scrollable area in CSS pixels.
         * @param cssContentSize field value
         * @return this model
         */
        public GetLayoutMetricsResult cssContentSize(DOM.Rect cssContentSize) {
            set("cssContentSize", cssContentSize);
            return this;
        }
    }
    /**
     * Returns navigation history for the current page.
     */
    public static final class GetNavigationHistoryResult extends CdpObject {
        public GetNavigationHistoryResult() {}
        private GetNavigationHistoryResult(Map<String, Object> values) { super(values); }
        public static GetNavigationHistoryResult fromMap(Map<String, Object> values) {
            return new GetNavigationHistoryResult(values);
        }
        /**
         * Index of the current navigation history entry.
         * @return the protocol field value
         */
        public long currentIndex() {
            return ((Number) require("currentIndex")).longValue();
        }
        /**
         * Array of navigation history entries.
         * @return the protocol field value
         */
        public java.util.List<Page.NavigationEntry> entries() {
            return CdpObject.requireList(require("entries"), element0 -> java.util.Objects.requireNonNull(Page.NavigationEntry.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Index of the current navigation history entry.
         * @param currentIndex field value
         * @return this model
         */
        public GetNavigationHistoryResult currentIndex(long currentIndex) {
            set("currentIndex", currentIndex);
            return this;
        }
        /**
         * Array of navigation history entries.
         * @param entries field value
         * @return this model
         */
        public GetNavigationHistoryResult entries(java.util.List<Page.NavigationEntry> entries) {
            set("entries", entries);
            return this;
        }
    }
    /**
     * Returns content of the given resource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResourceContentResult extends CdpObject {
        public GetResourceContentResult() {}
        private GetResourceContentResult(Map<String, Object> values) { super(values); }
        public static GetResourceContentResult fromMap(Map<String, Object> values) {
            return new GetResourceContentResult(values);
        }
        /**
         * Resource content.
         * @return the protocol field value
         */
        public String content() {
            return (String) require("content");
        }
        /**
         * True, if content was served as base64.
         * @return the protocol field value
         */
        public boolean base64Encoded() {
            return (Boolean) require("base64Encoded");
        }
        /**
         * Resource content.
         * @param content field value
         * @return this model
         */
        public GetResourceContentResult content(String content) {
            set("content", content);
            return this;
        }
        /**
         * True, if content was served as base64.
         * @param base64Encoded field value
         * @return this model
         */
        public GetResourceContentResult base64Encoded(boolean base64Encoded) {
            set("base64Encoded", base64Encoded);
            return this;
        }
    }
    /**
     * Navigates current page to the given URL.
     */
    public static final class NavigateResult extends CdpObject {
        public NavigateResult() {}
        private NavigateResult(Map<String, Object> values) { super(values); }
        public static NavigateResult fromMap(Map<String, Object> values) {
            return new NavigateResult(values);
        }
        /**
         * Frame id that has navigated (or failed to navigate)
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Loader identifier. This is omitted in case of same-document navigation, as the previously committed loaderId would not change.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.LoaderId> loaderId() {
            return raw("loaderId").map(value -> new Network.LoaderId((String) value));
        }
        /**
         * User friendly error message, present if and only if navigation has failed.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> errorText() {
            return raw("errorText").map(value -> (String) value);
        }
        /**
         * Whether the navigation resulted in a download.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isDownload() {
            return raw("isDownload").map(value -> (Boolean) value);
        }
        /**
         * Frame id that has navigated (or failed to navigate)
         * @param frameId field value
         * @return this model
         */
        public NavigateResult frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Loader identifier. This is omitted in case of same-document navigation, as the previously committed loaderId would not change.
         * @param loaderId field value
         * @return this model
         */
        public NavigateResult loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * User friendly error message, present if and only if navigation has failed.
         * @param errorText field value
         * @return this model
         */
        public NavigateResult errorText(String errorText) {
            set("errorText", errorText);
            return this;
        }
        /**
         * Whether the navigation resulted in a download.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isDownload field value
         * @return this model
         */
        public NavigateResult isDownload(boolean isDownload) {
            set("isDownload", isDownload);
            return this;
        }
    }
    /**
     * Print page as PDF.
     */
    public static final class PrintToPDFResult extends CdpObject {
        public PrintToPDFResult() {}
        private PrintToPDFResult(Map<String, Object> values) { super(values); }
        public static PrintToPDFResult fromMap(Map<String, Object> values) {
            return new PrintToPDFResult(values);
        }
        /**
         * Base64-encoded pdf data. Empty if |returnAsStream| is specified. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * A handle of the stream that holds resulting PDF data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<IO.StreamHandle> stream() {
            return raw("stream").map(value -> new IO.StreamHandle((String) value));
        }
        /**
         * Base64-encoded pdf data. Empty if |returnAsStream| is specified. (Encoded as a base64 string when passed over JSON)
         * @param data field value
         * @return this model
         */
        public PrintToPDFResult data(String data) {
            set("data", data);
            return this;
        }
        /**
         * A handle of the stream that holds resulting PDF data.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stream field value
         * @return this model
         */
        public PrintToPDFResult stream(IO.StreamHandle stream) {
            set("stream", stream);
            return this;
        }
    }
    /**
     * Payload of the Page.domContentEventFired event.
     */
    public static final class DomContentEventFiredEvent extends CdpObject {
        public DomContentEventFiredEvent() {}
        private DomContentEventFiredEvent(Map<String, Object> values) { super(values); }
        public static DomContentEventFiredEvent fromMap(Map<String, Object> values) {
            return new DomContentEventFiredEvent(values);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DomContentEventFiredEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Emitted only when {@code page.interceptFileChooser} is enabled.
     */
    public static final class FileChooserOpenedEvent extends CdpObject {
        public FileChooserOpenedEvent() {}
        private FileChooserOpenedEvent(Map<String, Object> values) { super(values); }
        public static FileChooserOpenedEvent fromMap(Map<String, Object> values) {
            return new FileChooserOpenedEvent(values);
        }
        /**
         * Input mode.
         */
        public enum ModeValues implements CdpValue<String> {
            SELECTSINGLE("selectSingle"),
            SELECTMULTIPLE("selectMultiple");
            public final String value;
            ModeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static ModeValues of(String value) {
                for (ModeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown ModeValues value: " + value);
            }
        }
        /**
         * Id of the frame containing input node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Input mode.
         * @return the protocol field value
         */
        public FileChooserOpenedEvent.ModeValues mode() {
            return FileChooserOpenedEvent.ModeValues.of((String) require("mode"));
        }
        /**
         * Input node id. Only present for file choosers opened via an {@code &lt;input type=&quot;file&quot;&gt;} element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * Id of the frame containing input node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId field value
         * @return this model
         */
        public FileChooserOpenedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Input mode.
         * @param mode field value
         * @return this model
         */
        public FileChooserOpenedEvent mode(FileChooserOpenedEvent.ModeValues mode) {
            set("mode", mode);
            return this;
        }
        /**
         * Input node id. Only present for file choosers opened via an {@code &lt;input type=&quot;file&quot;&gt;} element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param backendNodeId field value
         * @return this model
         */
        public FileChooserOpenedEvent backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * Fired when frame has been attached to its parent.
     */
    public static final class FrameAttachedEvent extends CdpObject {
        public FrameAttachedEvent() {}
        private FrameAttachedEvent(Map<String, Object> values) { super(values); }
        public static FrameAttachedEvent fromMap(Map<String, Object> values) {
            return new FrameAttachedEvent(values);
        }
        /**
         * Id of the frame that has been attached.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Parent frame identifier.
         * @return the protocol field value
         */
        public Page.FrameId parentFrameId() {
            return new Page.FrameId((String) require("parentFrameId"));
        }
        /**
         * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stack() {
            return raw("stack").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Id of the frame that has been attached.
         * @param frameId field value
         * @return this model
         */
        public FrameAttachedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Parent frame identifier.
         * @param parentFrameId field value
         * @return this model
         */
        public FrameAttachedEvent parentFrameId(Page.FrameId parentFrameId) {
            set("parentFrameId", parentFrameId);
            return this;
        }
        /**
         * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
         * @param stack field value
         * @return this model
         */
        public FrameAttachedEvent stack(Runtime.StackTrace stack) {
            set("stack", stack);
            return this;
        }
    }
    /**
     * Fired when frame no longer has a scheduled navigation.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class FrameClearedScheduledNavigationEvent extends CdpObject {
        public FrameClearedScheduledNavigationEvent() {}
        private FrameClearedScheduledNavigationEvent(Map<String, Object> values) { super(values); }
        public static FrameClearedScheduledNavigationEvent fromMap(Map<String, Object> values) {
            return new FrameClearedScheduledNavigationEvent(values);
        }
        /**
         * Id of the frame that has cleared its scheduled navigation.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Id of the frame that has cleared its scheduled navigation.
         * @param frameId field value
         * @return this model
         */
        public FrameClearedScheduledNavigationEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fired when frame has been detached from its parent.
     */
    public static final class FrameDetachedEvent extends CdpObject {
        public FrameDetachedEvent() {}
        private FrameDetachedEvent(Map<String, Object> values) { super(values); }
        public static FrameDetachedEvent fromMap(Map<String, Object> values) {
            return new FrameDetachedEvent(values);
        }
        /**
         * Wire values for ReasonValues.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public enum ReasonValues implements CdpValue<String> {
            REMOVE("remove"),
            SWAP("swap");
            public final String value;
            ReasonValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static ReasonValues of(String value) {
                for (ReasonValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown ReasonValues value: " + value);
            }
        }
        /**
         * Id of the frame that has been detached.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Returns the reason field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public FrameDetachedEvent.ReasonValues reason() {
            return FrameDetachedEvent.ReasonValues.of((String) require("reason"));
        }
        /**
         * Id of the frame that has been detached.
         * @param frameId field value
         * @return this model
         */
        public FrameDetachedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Sets the reason field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param reason field value
         * @return this model
         */
        public FrameDetachedEvent reason(FrameDetachedEvent.ReasonValues reason) {
            set("reason", reason);
            return this;
        }
    }
    /**
     * Fired before frame subtree is detached. Emitted before any frame of the subtree is actually detached.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameSubtreeWillBeDetachedEvent extends CdpObject {
        public FrameSubtreeWillBeDetachedEvent() {}
        private FrameSubtreeWillBeDetachedEvent(Map<String, Object> values) { super(values); }
        public static FrameSubtreeWillBeDetachedEvent fromMap(Map<String, Object> values) {
            return new FrameSubtreeWillBeDetachedEvent(values);
        }
        /**
         * Id of the frame that is the root of the subtree that will be detached.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Id of the frame that is the root of the subtree that will be detached.
         * @param frameId field value
         * @return this model
         */
        public FrameSubtreeWillBeDetachedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fired once navigation of the frame has completed. Frame is now associated with the new loader.
     */
    public static final class FrameNavigatedEvent extends CdpObject {
        public FrameNavigatedEvent() {}
        private FrameNavigatedEvent(Map<String, Object> values) { super(values); }
        public static FrameNavigatedEvent fromMap(Map<String, Object> values) {
            return new FrameNavigatedEvent(values);
        }
        /**
         * Frame object.
         * @return the protocol field value
         */
        public Page.Frame frame() {
            return java.util.Objects.requireNonNull(Page.Frame.fromMap(CdpObject.requireObjectMap(require("frame"))));
        }
        /**
         * Returns the type field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.NavigationType type() {
            return Page.NavigationType.of((String) require("type"));
        }
        /**
         * Frame object.
         * @param frame field value
         * @return this model
         */
        public FrameNavigatedEvent frame(Page.Frame frame) {
            set("frame", frame);
            return this;
        }
        /**
         * Sets the type field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type field value
         * @return this model
         */
        public FrameNavigatedEvent type(Page.NavigationType type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Fired when opening document to write to.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DocumentOpenedEvent extends CdpObject {
        public DocumentOpenedEvent() {}
        private DocumentOpenedEvent(Map<String, Object> values) { super(values); }
        public static DocumentOpenedEvent fromMap(Map<String, Object> values) {
            return new DocumentOpenedEvent(values);
        }
        /**
         * Frame object.
         * @return the protocol field value
         */
        public Page.Frame frame() {
            return java.util.Objects.requireNonNull(Page.Frame.fromMap(CdpObject.requireObjectMap(require("frame"))));
        }
        /**
         * Frame object.
         * @param frame field value
         * @return this model
         */
        public DocumentOpenedEvent frame(Page.Frame frame) {
            set("frame", frame);
            return this;
        }
    }
    /**
     * Payload of the Page.frameResized event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameResizedEvent extends CdpObject {
        public FrameResizedEvent() {}
        private FrameResizedEvent(Map<String, Object> values) { super(values); }
        public static FrameResizedEvent fromMap(Map<String, Object> values) {
            return new FrameResizedEvent(values);
        }
    }
    /**
     * Fired when a navigation starts. This event is fired for both renderer-initiated and browser-initiated navigations. For renderer-initiated navigations, the event is fired after {@code frameRequestedNavigation}. Navigation may still be cancelled after the event is issued. Multiple events can be fired for a single navigation, for example, when a same-document navigation becomes a cross-document navigation (such as in the case of a frameset).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameStartedNavigatingEvent extends CdpObject {
        public FrameStartedNavigatingEvent() {}
        private FrameStartedNavigatingEvent(Map<String, Object> values) { super(values); }
        public static FrameStartedNavigatingEvent fromMap(Map<String, Object> values) {
            return new FrameStartedNavigatingEvent(values);
        }
        /**
         * Wire values for NavigationTypeValues.
         */
        public enum NavigationTypeValues implements CdpValue<String> {
            RELOAD("reload"),
            RELOADBYPASSINGCACHE("reloadBypassingCache"),
            RESTORE("restore"),
            RESTOREWITHPOST("restoreWithPost"),
            HISTORYSAMEDOCUMENT("historySameDocument"),
            HISTORYDIFFERENTDOCUMENT("historyDifferentDocument"),
            SAMEDOCUMENT("sameDocument"),
            DIFFERENTDOCUMENT("differentDocument");
            public final String value;
            NavigationTypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static NavigationTypeValues of(String value) {
                for (NavigationTypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown NavigationTypeValues value: " + value);
            }
        }
        /**
         * ID of the frame that is being navigated.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * The URL the navigation started with. The final URL can be different.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Loader identifier. Even though it is present in case of same-document navigation, the previously committed loaderId would not change unless the navigation changes from a same-document to a cross-document navigation.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return new Network.LoaderId((String) require("loaderId"));
        }
        /**
         * Returns the navigationType field.
         * @return the protocol field value
         */
        public FrameStartedNavigatingEvent.NavigationTypeValues navigationType() {
            return FrameStartedNavigatingEvent.NavigationTypeValues.of((String) require("navigationType"));
        }
        /**
         * ID of the frame that is being navigated.
         * @param frameId field value
         * @return this model
         */
        public FrameStartedNavigatingEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The URL the navigation started with. The final URL can be different.
         * @param url field value
         * @return this model
         */
        public FrameStartedNavigatingEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Loader identifier. Even though it is present in case of same-document navigation, the previously committed loaderId would not change unless the navigation changes from a same-document to a cross-document navigation.
         * @param loaderId field value
         * @return this model
         */
        public FrameStartedNavigatingEvent loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Sets the navigationType field.
         * @param navigationType field value
         * @return this model
         */
        public FrameStartedNavigatingEvent navigationType(FrameStartedNavigatingEvent.NavigationTypeValues navigationType) {
            set("navigationType", navigationType);
            return this;
        }
    }
    /**
     * Fired when a renderer-initiated navigation is requested. Navigation may still be cancelled after the event is issued.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameRequestedNavigationEvent extends CdpObject {
        public FrameRequestedNavigationEvent() {}
        private FrameRequestedNavigationEvent(Map<String, Object> values) { super(values); }
        public static FrameRequestedNavigationEvent fromMap(Map<String, Object> values) {
            return new FrameRequestedNavigationEvent(values);
        }
        /**
         * Id of the frame that is being navigated.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * The reason for the navigation.
         * @return the protocol field value
         */
        public Page.ClientNavigationReason reason() {
            return Page.ClientNavigationReason.of((String) require("reason"));
        }
        /**
         * The destination URL for the requested navigation.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * The disposition for the navigation.
         * @return the protocol field value
         */
        public Page.ClientNavigationDisposition disposition() {
            return Page.ClientNavigationDisposition.of((String) require("disposition"));
        }
        /**
         * Id of the frame that is being navigated.
         * @param frameId field value
         * @return this model
         */
        public FrameRequestedNavigationEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The reason for the navigation.
         * @param reason field value
         * @return this model
         */
        public FrameRequestedNavigationEvent reason(Page.ClientNavigationReason reason) {
            set("reason", reason);
            return this;
        }
        /**
         * The destination URL for the requested navigation.
         * @param url field value
         * @return this model
         */
        public FrameRequestedNavigationEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * The disposition for the navigation.
         * @param disposition field value
         * @return this model
         */
        public FrameRequestedNavigationEvent disposition(Page.ClientNavigationDisposition disposition) {
            set("disposition", disposition);
            return this;
        }
    }
    /**
     * Fired when frame schedules a potential navigation.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class FrameScheduledNavigationEvent extends CdpObject {
        public FrameScheduledNavigationEvent() {}
        private FrameScheduledNavigationEvent(Map<String, Object> values) { super(values); }
        public static FrameScheduledNavigationEvent fromMap(Map<String, Object> values) {
            return new FrameScheduledNavigationEvent(values);
        }
        /**
         * Id of the frame that has scheduled a navigation.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
         * @return the protocol field value
         */
        public double delay() {
            return ((Number) require("delay")).doubleValue();
        }
        /**
         * The reason for the navigation.
         * @return the protocol field value
         */
        public Page.ClientNavigationReason reason() {
            return Page.ClientNavigationReason.of((String) require("reason"));
        }
        /**
         * The destination URL for the scheduled navigation.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Id of the frame that has scheduled a navigation.
         * @param frameId field value
         * @return this model
         */
        public FrameScheduledNavigationEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not guaranteed to start.
         * @param delay field value
         * @return this model
         */
        public FrameScheduledNavigationEvent delay(double delay) {
            set("delay", delay);
            return this;
        }
        /**
         * The reason for the navigation.
         * @param reason field value
         * @return this model
         */
        public FrameScheduledNavigationEvent reason(Page.ClientNavigationReason reason) {
            set("reason", reason);
            return this;
        }
        /**
         * The destination URL for the scheduled navigation.
         * @param url field value
         * @return this model
         */
        public FrameScheduledNavigationEvent url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Fired when frame has started loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameStartedLoadingEvent extends CdpObject {
        public FrameStartedLoadingEvent() {}
        private FrameStartedLoadingEvent(Map<String, Object> values) { super(values); }
        public static FrameStartedLoadingEvent fromMap(Map<String, Object> values) {
            return new FrameStartedLoadingEvent(values);
        }
        /**
         * Id of the frame that has started loading.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Id of the frame that has started loading.
         * @param frameId field value
         * @return this model
         */
        public FrameStartedLoadingEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fired when frame has stopped loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FrameStoppedLoadingEvent extends CdpObject {
        public FrameStoppedLoadingEvent() {}
        private FrameStoppedLoadingEvent(Map<String, Object> values) { super(values); }
        public static FrameStoppedLoadingEvent fromMap(Map<String, Object> values) {
            return new FrameStoppedLoadingEvent(values);
        }
        /**
         * Id of the frame that has stopped loading.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Id of the frame that has stopped loading.
         * @param frameId field value
         * @return this model
         */
        public FrameStoppedLoadingEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fired when page is about to start a download. Deprecated. Use Browser.downloadWillBegin instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DownloadWillBeginEvent extends CdpObject {
        public DownloadWillBeginEvent() {}
        private DownloadWillBeginEvent(Map<String, Object> values) { super(values); }
        public static DownloadWillBeginEvent fromMap(Map<String, Object> values) {
            return new DownloadWillBeginEvent(values);
        }
        /**
         * Id of the frame that caused download to begin.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return (String) require("guid");
        }
        /**
         * URL of the resource being downloaded.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @return the protocol field value
         */
        public String suggestedFilename() {
            return (String) require("suggestedFilename");
        }
        /**
         * Id of the frame that caused download to begin.
         * @param frameId field value
         * @return this model
         */
        public DownloadWillBeginEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Global unique identifier of the download.
         * @param guid field value
         * @return this model
         */
        public DownloadWillBeginEvent guid(String guid) {
            set("guid", guid);
            return this;
        }
        /**
         * URL of the resource being downloaded.
         * @param url field value
         * @return this model
         */
        public DownloadWillBeginEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Suggested file name of the resource (the actual name of the file saved on disk may differ).
         * @param suggestedFilename field value
         * @return this model
         */
        public DownloadWillBeginEvent suggestedFilename(String suggestedFilename) {
            set("suggestedFilename", suggestedFilename);
            return this;
        }
    }
    /**
     * Fired when download makes progress. Last call has |done| == true. Deprecated. Use Browser.downloadProgress instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class DownloadProgressEvent extends CdpObject {
        public DownloadProgressEvent() {}
        private DownloadProgressEvent(Map<String, Object> values) { super(values); }
        public static DownloadProgressEvent fromMap(Map<String, Object> values) {
            return new DownloadProgressEvent(values);
        }
        /**
         * Download status.
         */
        public enum StateValues implements CdpValue<String> {
            INPROGRESS("inProgress"),
            COMPLETED("completed"),
            CANCELED("canceled");
            public final String value;
            StateValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static StateValues of(String value) {
                for (StateValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown StateValues value: " + value);
            }
        }
        /**
         * Global unique identifier of the download.
         * @return the protocol field value
         */
        public String guid() {
            return (String) require("guid");
        }
        /**
         * Total expected bytes to download.
         * @return the protocol field value
         */
        public double totalBytes() {
            return ((Number) require("totalBytes")).doubleValue();
        }
        /**
         * Total bytes received.
         * @return the protocol field value
         */
        public double receivedBytes() {
            return ((Number) require("receivedBytes")).doubleValue();
        }
        /**
         * Download status.
         * @return the protocol field value
         */
        public DownloadProgressEvent.StateValues state() {
            return DownloadProgressEvent.StateValues.of((String) require("state"));
        }
        /**
         * Global unique identifier of the download.
         * @param guid field value
         * @return this model
         */
        public DownloadProgressEvent guid(String guid) {
            set("guid", guid);
            return this;
        }
        /**
         * Total expected bytes to download.
         * @param totalBytes field value
         * @return this model
         */
        public DownloadProgressEvent totalBytes(double totalBytes) {
            set("totalBytes", totalBytes);
            return this;
        }
        /**
         * Total bytes received.
         * @param receivedBytes field value
         * @return this model
         */
        public DownloadProgressEvent receivedBytes(double receivedBytes) {
            set("receivedBytes", receivedBytes);
            return this;
        }
        /**
         * Download status.
         * @param state field value
         * @return this model
         */
        public DownloadProgressEvent state(DownloadProgressEvent.StateValues state) {
            set("state", state);
            return this;
        }
    }
    /**
     * Fired when interstitial page was hidden
     */
    public static final class InterstitialHiddenEvent extends CdpObject {
        public InterstitialHiddenEvent() {}
        private InterstitialHiddenEvent(Map<String, Object> values) { super(values); }
        public static InterstitialHiddenEvent fromMap(Map<String, Object> values) {
            return new InterstitialHiddenEvent(values);
        }
    }
    /**
     * Fired when interstitial page was shown
     */
    public static final class InterstitialShownEvent extends CdpObject {
        public InterstitialShownEvent() {}
        private InterstitialShownEvent(Map<String, Object> values) { super(values); }
        public static InterstitialShownEvent fromMap(Map<String, Object> values) {
            return new InterstitialShownEvent(values);
        }
    }
    /**
     * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been closed.
     */
    public static final class JavascriptDialogClosedEvent extends CdpObject {
        public JavascriptDialogClosedEvent() {}
        private JavascriptDialogClosedEvent(Map<String, Object> values) { super(values); }
        public static JavascriptDialogClosedEvent fromMap(Map<String, Object> values) {
            return new JavascriptDialogClosedEvent(values);
        }
        /**
         * Frame id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Whether dialog was confirmed.
         * @return the protocol field value
         */
        public boolean result() {
            return (Boolean) require("result");
        }
        /**
         * User input in case of prompt.
         * @return the protocol field value
         */
        public String userInput() {
            return (String) require("userInput");
        }
        /**
         * Frame id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId field value
         * @return this model
         */
        public JavascriptDialogClosedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Whether dialog was confirmed.
         * @param result field value
         * @return this model
         */
        public JavascriptDialogClosedEvent result(boolean result) {
            set("result", result);
            return this;
        }
        /**
         * User input in case of prompt.
         * @param userInput field value
         * @return this model
         */
        public JavascriptDialogClosedEvent userInput(String userInput) {
            set("userInput", userInput);
            return this;
        }
    }
    /**
     * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to open.
     */
    public static final class JavascriptDialogOpeningEvent extends CdpObject {
        public JavascriptDialogOpeningEvent() {}
        private JavascriptDialogOpeningEvent(Map<String, Object> values) { super(values); }
        public static JavascriptDialogOpeningEvent fromMap(Map<String, Object> values) {
            return new JavascriptDialogOpeningEvent(values);
        }
        /**
         * Frame url.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Frame id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Message that will be displayed by the dialog.
         * @return the protocol field value
         */
        public String message() {
            return (String) require("message");
        }
        /**
         * Dialog type.
         * @return the protocol field value
         */
        public Page.DialogType type() {
            return Page.DialogType.of((String) require("type"));
        }
        /**
         * True iff browser is capable showing or acting on the given dialog. When browser has no dialog handler for given target, calling alert while Page domain is engaged will stall the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
         * @return the protocol field value
         */
        public boolean hasBrowserHandler() {
            return (Boolean) require("hasBrowserHandler");
        }
        /**
         * Default dialog prompt.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> defaultPrompt() {
            return raw("defaultPrompt").map(value -> (String) value);
        }
        /**
         * Frame url.
         * @param url field value
         * @return this model
         */
        public JavascriptDialogOpeningEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Frame id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId field value
         * @return this model
         */
        public JavascriptDialogOpeningEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Message that will be displayed by the dialog.
         * @param message field value
         * @return this model
         */
        public JavascriptDialogOpeningEvent message(String message) {
            set("message", message);
            return this;
        }
        /**
         * Dialog type.
         * @param type field value
         * @return this model
         */
        public JavascriptDialogOpeningEvent type(Page.DialogType type) {
            set("type", type);
            return this;
        }
        /**
         * True iff browser is capable showing or acting on the given dialog. When browser has no dialog handler for given target, calling alert while Page domain is engaged will stall the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
         * @param hasBrowserHandler field value
         * @return this model
         */
        public JavascriptDialogOpeningEvent hasBrowserHandler(boolean hasBrowserHandler) {
            set("hasBrowserHandler", hasBrowserHandler);
            return this;
        }
        /**
         * Default dialog prompt.
         * @param defaultPrompt field value
         * @return this model
         */
        public JavascriptDialogOpeningEvent defaultPrompt(String defaultPrompt) {
            set("defaultPrompt", defaultPrompt);
            return this;
        }
    }
    /**
     * Fired for lifecycle events (navigation, load, paint, etc) in the current target (including local frames).
     */
    public static final class LifecycleEventEvent extends CdpObject {
        public LifecycleEventEvent() {}
        private LifecycleEventEvent(Map<String, Object> values) { super(values); }
        public static LifecycleEventEvent fromMap(Map<String, Object> values) {
            return new LifecycleEventEvent(values);
        }
        /**
         * Id of the frame.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return new Network.LoaderId((String) require("loaderId"));
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Id of the frame.
         * @param frameId field value
         * @return this model
         */
        public LifecycleEventEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @param loaderId field value
         * @return this model
         */
        public LifecycleEventEvent loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public LifecycleEventEvent name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public LifecycleEventEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired for failed bfcache history navigations if BackForwardCache feature is enabled. Do not assume any ordering with the Page.frameNavigated event. This event is fired only for main-frame history navigation where the document changes (non-same-document navigations), when bfcache navigation fails.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BackForwardCacheNotUsedEvent extends CdpObject {
        public BackForwardCacheNotUsedEvent() {}
        private BackForwardCacheNotUsedEvent(Map<String, Object> values) { super(values); }
        public static BackForwardCacheNotUsedEvent fromMap(Map<String, Object> values) {
            return new BackForwardCacheNotUsedEvent(values);
        }
        /**
         * The loader id for the associated navigation.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return new Network.LoaderId((String) require("loaderId"));
        }
        /**
         * The frame id of the associated frame.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Array of reasons why the page could not be cached. This must not be empty.
         * @return the protocol field value
         */
        public java.util.List<Page.BackForwardCacheNotRestoredExplanation> notRestoredExplanations() {
            return CdpObject.requireList(require("notRestoredExplanations"), element0 -> java.util.Objects.requireNonNull(Page.BackForwardCacheNotRestoredExplanation.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Tree structure of reasons why the page could not be cached for each frame.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.BackForwardCacheNotRestoredExplanationTree> notRestoredExplanationsTree() {
            return raw("notRestoredExplanationsTree").flatMap(CdpObject::objectMap).map(Page.BackForwardCacheNotRestoredExplanationTree::fromMap);
        }
        /**
         * The loader id for the associated navigation.
         * @param loaderId field value
         * @return this model
         */
        public BackForwardCacheNotUsedEvent loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * The frame id of the associated frame.
         * @param frameId field value
         * @return this model
         */
        public BackForwardCacheNotUsedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Array of reasons why the page could not be cached. This must not be empty.
         * @param notRestoredExplanations field value
         * @return this model
         */
        public BackForwardCacheNotUsedEvent notRestoredExplanations(java.util.List<Page.BackForwardCacheNotRestoredExplanation> notRestoredExplanations) {
            set("notRestoredExplanations", notRestoredExplanations);
            return this;
        }
        /**
         * Tree structure of reasons why the page could not be cached for each frame.
         * @param notRestoredExplanationsTree field value
         * @return this model
         */
        public BackForwardCacheNotUsedEvent notRestoredExplanationsTree(Page.BackForwardCacheNotRestoredExplanationTree notRestoredExplanationsTree) {
            set("notRestoredExplanationsTree", notRestoredExplanationsTree);
            return this;
        }
    }
    /**
     * Payload of the Page.loadEventFired event.
     */
    public static final class LoadEventFiredEvent extends CdpObject {
        public LoadEventFiredEvent() {}
        private LoadEventFiredEvent(Map<String, Object> values) { super(values); }
        public static LoadEventFiredEvent fromMap(Map<String, Object> values) {
            return new LoadEventFiredEvent(values);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return new Network.MonotonicTime(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public LoadEventFiredEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when same-document navigation happens, e.g. due to history API usage or anchor navigation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NavigatedWithinDocumentEvent extends CdpObject {
        public NavigatedWithinDocumentEvent() {}
        private NavigatedWithinDocumentEvent(Map<String, Object> values) { super(values); }
        public static NavigatedWithinDocumentEvent fromMap(Map<String, Object> values) {
            return new NavigatedWithinDocumentEvent(values);
        }
        /**
         * Navigation type
         */
        public enum NavigationTypeValues implements CdpValue<String> {
            FRAGMENT("fragment"),
            HISTORYAPI("historyApi"),
            OTHER("other");
            public final String value;
            NavigationTypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static NavigationTypeValues of(String value) {
                for (NavigationTypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown NavigationTypeValues value: " + value);
            }
        }
        /**
         * Id of the frame.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Frame&#x27;s new url.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Navigation type
         * @return the protocol field value
         */
        public NavigatedWithinDocumentEvent.NavigationTypeValues navigationType() {
            return NavigatedWithinDocumentEvent.NavigationTypeValues.of((String) require("navigationType"));
        }
        /**
         * Id of the frame.
         * @param frameId field value
         * @return this model
         */
        public NavigatedWithinDocumentEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Frame&#x27;s new url.
         * @param url field value
         * @return this model
         */
        public NavigatedWithinDocumentEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Navigation type
         * @param navigationType field value
         * @return this model
         */
        public NavigatedWithinDocumentEvent navigationType(NavigatedWithinDocumentEvent.NavigationTypeValues navigationType) {
            set("navigationType", navigationType);
            return this;
        }
    }
    /**
     * Compressed image data requested by the {@code startScreencast}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastFrameEvent extends CdpObject {
        public ScreencastFrameEvent() {}
        private ScreencastFrameEvent(Map<String, Object> values) { super(values); }
        public static ScreencastFrameEvent fromMap(Map<String, Object> values) {
            return new ScreencastFrameEvent(values);
        }
        /**
         * Base64-encoded compressed image. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Screencast frame metadata.
         * @return the protocol field value
         */
        public Page.ScreencastFrameMetadata metadata() {
            return java.util.Objects.requireNonNull(Page.ScreencastFrameMetadata.fromMap(CdpObject.requireObjectMap(require("metadata"))));
        }
        /**
         * Frame number.
         * @return the protocol field value
         */
        public long sessionId() {
            return ((Number) require("sessionId")).longValue();
        }
        /**
         * Base64-encoded compressed image. (Encoded as a base64 string when passed over JSON)
         * @param data field value
         * @return this model
         */
        public ScreencastFrameEvent data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Screencast frame metadata.
         * @param metadata field value
         * @return this model
         */
        public ScreencastFrameEvent metadata(Page.ScreencastFrameMetadata metadata) {
            set("metadata", metadata);
            return this;
        }
        /**
         * Frame number.
         * @param sessionId field value
         * @return this model
         */
        public ScreencastFrameEvent sessionId(long sessionId) {
            set("sessionId", sessionId);
            return this;
        }
    }
    /**
     * Fired when the page with currently enabled screencast was shown or hidden `.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScreencastVisibilityChangedEvent extends CdpObject {
        public ScreencastVisibilityChangedEvent() {}
        private ScreencastVisibilityChangedEvent(Map<String, Object> values) { super(values); }
        public static ScreencastVisibilityChangedEvent fromMap(Map<String, Object> values) {
            return new ScreencastVisibilityChangedEvent(values);
        }
        /**
         * True if the page is visible.
         * @return the protocol field value
         */
        public boolean visible() {
            return (Boolean) require("visible");
        }
        /**
         * True if the page is visible.
         * @param visible field value
         * @return this model
         */
        public ScreencastVisibilityChangedEvent visible(boolean visible) {
            set("visible", visible);
            return this;
        }
    }
    /**
     * Fired when a new window is going to be opened, via window.open(), link click, form submission, etc.
     */
    public static final class WindowOpenEvent extends CdpObject {
        public WindowOpenEvent() {}
        private WindowOpenEvent(Map<String, Object> values) { super(values); }
        public static WindowOpenEvent fromMap(Map<String, Object> values) {
            return new WindowOpenEvent(values);
        }
        /**
         * The URL for the new window.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Window name.
         * @return the protocol field value
         */
        public String windowName() {
            return (String) require("windowName");
        }
        /**
         * An array of enabled window features.
         * @return the protocol field value
         */
        public java.util.List<String> windowFeatures() {
            return CdpObject.requireList(require("windowFeatures"), element0 -> (String) element0);
        }
        /**
         * Whether or not it was triggered by user gesture.
         * @return the protocol field value
         */
        public boolean userGesture() {
            return (Boolean) require("userGesture");
        }
        /**
         * The URL for the new window.
         * @param url field value
         * @return this model
         */
        public WindowOpenEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Window name.
         * @param windowName field value
         * @return this model
         */
        public WindowOpenEvent windowName(String windowName) {
            set("windowName", windowName);
            return this;
        }
        /**
         * An array of enabled window features.
         * @param windowFeatures field value
         * @return this model
         */
        public WindowOpenEvent windowFeatures(java.util.List<String> windowFeatures) {
            set("windowFeatures", windowFeatures);
            return this;
        }
        /**
         * Whether or not it was triggered by user gesture.
         * @param userGesture field value
         * @return this model
         */
        public WindowOpenEvent userGesture(boolean userGesture) {
            set("userGesture", userGesture);
            return this;
        }
    }
    /**
     * Issued for every compilation cache generated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CompilationCacheProducedEvent extends CdpObject {
        public CompilationCacheProducedEvent() {}
        private CompilationCacheProducedEvent(Map<String, Object> values) { super(values); }
        public static CompilationCacheProducedEvent fromMap(Map<String, Object> values) {
            return new CompilationCacheProducedEvent(values);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Base64-encoded data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public CompilationCacheProducedEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Base64-encoded data (Encoded as a base64 string when passed over JSON)
         * @param data field value
         * @return this model
         */
        public CompilationCacheProducedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Image compression format (defaults to png).
     */
    public enum CaptureScreenshotFormatValues implements CdpValue<String> {
        JPEG("jpeg"),
        PNG("png"),
        WEBP("webp");
        public final String value;
        CaptureScreenshotFormatValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CaptureScreenshotFormatValues of(String value) {
            for (CaptureScreenshotFormatValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CaptureScreenshotFormatValues value: " + value);
        }
    }
    /**
     * Format (defaults to mhtml).
     */
    public enum CaptureSnapshotFormatValues implements CdpValue<String> {
        MHTML("mhtml");
        public final String value;
        CaptureSnapshotFormatValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CaptureSnapshotFormatValues of(String value) {
            for (CaptureSnapshotFormatValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CaptureSnapshotFormatValues value: " + value);
        }
    }
    /**
     * return as stream
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum PrintToPDFTransferModeValues implements CdpValue<String> {
        RETURNASBASE64("ReturnAsBase64"),
        RETURNASSTREAM("ReturnAsStream");
        public final String value;
        PrintToPDFTransferModeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PrintToPDFTransferModeValues of(String value) {
            for (PrintToPDFTransferModeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PrintToPDFTransferModeValues value: " + value);
        }
    }
    /**
     * Whether to allow all or deny all download requests, or use default Chrome behavior if available (otherwise deny).
     */
    public enum SetDownloadBehaviorBehaviorValues implements CdpValue<String> {
        DENY("deny"),
        ALLOW("allow"),
        DEFAULT("default");
        public final String value;
        SetDownloadBehaviorBehaviorValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetDownloadBehaviorBehaviorValues of(String value) {
            for (SetDownloadBehaviorBehaviorValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetDownloadBehaviorBehaviorValues value: " + value);
        }
    }
    /**
     * Touch/gesture events configuration. Default: current platform.
     */
    public enum SetTouchEmulationEnabledConfigurationValues implements CdpValue<String> {
        MOBILE("mobile"),
        DESKTOP("desktop");
        public final String value;
        SetTouchEmulationEnabledConfigurationValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetTouchEmulationEnabledConfigurationValues of(String value) {
            for (SetTouchEmulationEnabledConfigurationValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetTouchEmulationEnabledConfigurationValues value: " + value);
        }
    }
    /**
     * Image compression format.
     */
    public enum StartScreencastFormatValues implements CdpValue<String> {
        JPEG("jpeg"),
        PNG("png");
        public final String value;
        StartScreencastFormatValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StartScreencastFormatValues of(String value) {
            for (StartScreencastFormatValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StartScreencastFormatValues value: " + value);
        }
    }
    /**
     * Target lifecycle state
     */
    public enum SetWebLifecycleStateStateValues implements CdpValue<String> {
        FROZEN("frozen"),
        ACTIVE("active");
        public final String value;
        SetWebLifecycleStateStateValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetWebLifecycleStateStateValues of(String value) {
            for (SetWebLifecycleStateStateValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetWebLifecycleStateStateValues value: " + value);
        }
    }
    /**
     * Wire values for SetSPCTransactionModeModeValues.
     */
    public enum SetSPCTransactionModeModeValues implements CdpValue<String> {
        NONE("none"),
        AUTOACCEPT("autoAccept"),
        AUTOCHOOSETOAUTHANOTHERWAY("autoChooseToAuthAnotherWay"),
        AUTOREJECT("autoReject"),
        AUTOOPTOUT("autoOptOut");
        public final String value;
        SetSPCTransactionModeModeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetSPCTransactionModeModeValues of(String value) {
            for (SetSPCTransactionModeModeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetSPCTransactionModeModeValues value: " + value);
        }
    }
    /**
     * Wire values for SetRPHRegistrationModeModeValues.
     */
    public enum SetRPHRegistrationModeModeValues implements CdpValue<String> {
        NONE("none"),
        AUTOACCEPT("autoAccept"),
        AUTOREJECT("autoReject");
        public final String value;
        SetRPHRegistrationModeModeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetRPHRegistrationModeModeValues of(String value) {
            for (SetRPHRegistrationModeModeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetRPHRegistrationModeModeValues value: " + value);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptSource protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Page.ScriptIdentifier> addScriptToEvaluateOnLoad(String scriptSource) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptSource", CdpObject.json(scriptSource));
            return client.call("Page.addScriptToEvaluateOnLoad", params, result_ -> new Page.ScriptIdentifier((String) java.util.Objects.requireNonNull(result_.get("identifier"))));
        }
        /**
         * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Page.ScriptIdentifier> addScriptToEvaluateOnLoad(AddScriptToEvaluateOnLoadRequest request) {
            return client.call("Page.addScriptToEvaluateOnLoad", request == null ? null : request.toMap(), result_ -> new Page.ScriptIdentifier((String) java.util.Objects.requireNonNull(result_.get("identifier"))));
        }
        /**
         * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
         * @param source protocol value
         * @param worldName protocol value
         * @param includeCommandLineAPI protocol value
         * @param runImmediately protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Page.ScriptIdentifier> addScriptToEvaluateOnNewDocument(String source, @Nullable String worldName, @Nullable Boolean includeCommandLineAPI, @Nullable Boolean runImmediately) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("source", CdpObject.json(source));
            if (worldName != null) params.put("worldName", CdpObject.json(worldName));
            if (includeCommandLineAPI != null) params.put("includeCommandLineAPI", CdpObject.json(includeCommandLineAPI));
            if (runImmediately != null) params.put("runImmediately", CdpObject.json(runImmediately));
            return client.call("Page.addScriptToEvaluateOnNewDocument", params, result_ -> new Page.ScriptIdentifier((String) java.util.Objects.requireNonNull(result_.get("identifier"))));
        }
        /**
         * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
         * @param source protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Page.ScriptIdentifier> addScriptToEvaluateOnNewDocument(String source) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("source", CdpObject.json(source));
            return client.call("Page.addScriptToEvaluateOnNewDocument", params, result_ -> new Page.ScriptIdentifier((String) java.util.Objects.requireNonNull(result_.get("identifier"))));
        }
        /**
         * Evaluates given script in every frame upon creation (before loading frame&#x27;s scripts).
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Page.ScriptIdentifier> addScriptToEvaluateOnNewDocument(AddScriptToEvaluateOnNewDocumentRequest request) {
            return client.call("Page.addScriptToEvaluateOnNewDocument", request == null ? null : request.toMap(), result_ -> new Page.ScriptIdentifier((String) java.util.Objects.requireNonNull(result_.get("identifier"))));
        }
        /**
         * Brings page to front (activates tab).
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> bringToFront() {
            return client.call("Page.bringToFront", null);
        }
        /**
         * Capture page screenshot.
         * @param format protocol value
         * @param quality protocol value
         * @param clip protocol value
         * @param fromSurface protocol value
         * @param captureBeyondViewport protocol value
         * @param optimizeForSpeed protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> captureScreenshot(@Nullable CaptureScreenshotFormatValues format, @Nullable Long quality, Page.@Nullable Viewport clip, @Nullable Boolean fromSurface, @Nullable Boolean captureBeyondViewport, @Nullable Boolean optimizeForSpeed) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (format != null) params.put("format", CdpObject.json(format));
            if (quality != null) params.put("quality", CdpObject.json(quality));
            if (clip != null) params.put("clip", CdpObject.json(clip));
            if (fromSurface != null) params.put("fromSurface", CdpObject.json(fromSurface));
            if (captureBeyondViewport != null) params.put("captureBeyondViewport", CdpObject.json(captureBeyondViewport));
            if (optimizeForSpeed != null) params.put("optimizeForSpeed", CdpObject.json(optimizeForSpeed));
            return client.call("Page.captureScreenshot", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("data")));
        }
        /**
         * Capture page screenshot.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> captureScreenshot() {
            return client.call("Page.captureScreenshot", null, result_ -> (String) java.util.Objects.requireNonNull(result_.get("data")));
        }
        /**
         * Capture page screenshot.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> captureScreenshot(CaptureScreenshotRequest request) {
            return client.call("Page.captureScreenshot", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("data")));
        }
        /**
         * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param format protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> captureSnapshot(CaptureSnapshotFormatValues format) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("format", CdpObject.json(format));
            return client.call("Page.captureSnapshot", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("data")));
        }
        /**
         * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> captureSnapshot() {
            return client.call("Page.captureSnapshot", null, result_ -> (String) java.util.Objects.requireNonNull(result_.get("data")));
        }
        /**
         * Returns a snapshot of the page as a string. For MHTML format, the serialization includes iframes, shadow DOM, external resources, and element-inline styles.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> captureSnapshot(CaptureSnapshotRequest request) {
            return client.call("Page.captureSnapshot", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("data")));
        }
        /**
         * Clears the overridden device metrics.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> clearDeviceMetricsOverride() {
            return client.call("Page.clearDeviceMetricsOverride", null);
        }
        /**
         * Clears the overridden Device Orientation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> clearDeviceOrientationOverride() {
            return client.call("Page.clearDeviceOrientationOverride", null);
        }
        /**
         * Clears the overridden Geolocation Position and Error.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> clearGeolocationOverride() {
            return client.call("Page.clearGeolocationOverride", null);
        }
        /**
         * Creates an isolated world for the given frame.
         * @param frameId protocol value
         * @param worldName protocol value
         * @param grantUniveralAccess protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.ExecutionContextId> createIsolatedWorld(Page.FrameId frameId, @Nullable String worldName, @Nullable Boolean grantUniveralAccess) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            if (worldName != null) params.put("worldName", CdpObject.json(worldName));
            if (grantUniveralAccess != null) params.put("grantUniveralAccess", CdpObject.json(grantUniveralAccess));
            return client.call("Page.createIsolatedWorld", params, result_ -> new Runtime.ExecutionContextId(((Number) java.util.Objects.requireNonNull(result_.get("executionContextId"))).longValue()));
        }
        /**
         * Creates an isolated world for the given frame.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.ExecutionContextId> createIsolatedWorld(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Page.createIsolatedWorld", params, result_ -> new Runtime.ExecutionContextId(((Number) java.util.Objects.requireNonNull(result_.get("executionContextId"))).longValue()));
        }
        /**
         * Creates an isolated world for the given frame.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.ExecutionContextId> createIsolatedWorld(CreateIsolatedWorldRequest request) {
            return client.call("Page.createIsolatedWorld", request == null ? null : request.toMap(), result_ -> new Runtime.ExecutionContextId(((Number) java.util.Objects.requireNonNull(result_.get("executionContextId"))).longValue()));
        }
        /**
         * Deletes browser cookie with given name, domain and path.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cookieName protocol value
         * @param url protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> deleteCookie(String cookieName, String url) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("cookieName", CdpObject.json(cookieName));
            params.put("url", CdpObject.json(url));
            return client.call("Page.deleteCookie", params);
        }
        /**
         * Deletes browser cookie with given name, domain and path.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> deleteCookie(DeleteCookieRequest request) {
            return client.call("Page.deleteCookie", request == null ? null : request.toMap());
        }
        /**
         * Disables page domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Page.disable", null);
        }
        /**
         * Enables page domain notifications.
         * @param enableFileChooserOpenedEvent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(boolean enableFileChooserOpenedEvent) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enableFileChooserOpenedEvent", CdpObject.json(enableFileChooserOpenedEvent));
            return client.call("Page.enable", params);
        }
        /**
         * Enables page domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Page.enable", null);
        }
        /**
         * Enables page domain notifications.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("Page.enable", request == null ? null : request.toMap());
        }
        /**
         * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
         * @param manifestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppManifestResult> getAppManifest(String manifestId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("manifestId", CdpObject.json(manifestId));
            return client.call("Page.getAppManifest", params, result_ -> new GetAppManifestResult(result_));
        }
        /**
         * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppManifestResult> getAppManifest() {
            return client.call("Page.getAppManifest", null, result_ -> new GetAppManifestResult(result_));
        }
        /**
         * Gets the processed manifest for this current document. This API always waits for the manifest to be loaded. If manifestId is provided, and it does not match the manifest of the current document, this API errors out. If there is not a loaded page, this API errors out immediately.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppManifestResult> getAppManifest(GetAppManifestRequest request) {
            return client.call("Page.getAppManifest", request == null ? null : request.toMap(), result_ -> new GetAppManifestResult(result_));
        }
        /**
         * Invokes Page.getInstallabilityErrors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Page.InstallabilityError>> getInstallabilityErrors() {
            return client.call("Page.getInstallabilityErrors", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("installabilityErrors")), element0 -> java.util.Objects.requireNonNull(Page.InstallabilityError.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Deprecated because it&#x27;s not guaranteed that the returned icon is in fact the one used for PWA installation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Optional<String>> getManifestIcons() {
            return client.call("Page.getManifestIcons", null, result_ -> Optional.ofNullable(result_.get("primaryIcon")).map(value -> (String) value));
        }
        /**
         * Returns the unique (PWA) app id. Only returns values if the feature flag &#x27;WebAppEnableManifestId&#x27; is enabled
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAppIdResult> getAppId() {
            return client.call("Page.getAppId", null, result_ -> new GetAppIdResult(result_));
        }
        /**
         * Invokes Page.getAdScriptAncestry.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Network.AdAncestry>> getAdScriptAncestry(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Page.getAdScriptAncestry", params, result_ -> Optional.ofNullable(result_.get("adScriptAncestry")).flatMap(CdpObject::objectMap).map(Network.AdAncestry::fromMap));
        }
        /**
         * Invokes Page.getAdScriptAncestry with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Network.AdAncestry>> getAdScriptAncestry(GetAdScriptAncestryRequest request) {
            return client.call("Page.getAdScriptAncestry", request == null ? null : request.toMap(), result_ -> Optional.ofNullable(result_.get("adScriptAncestry")).flatMap(CdpObject::objectMap).map(Network.AdAncestry::fromMap));
        }
        /**
         * Returns present frame tree structure.
         * @return a stage completing with the command result
         */
        public CompletionStage<Page.FrameTree> getFrameTree() {
            return client.call("Page.getFrameTree", null, result_ -> java.util.Objects.requireNonNull(Page.FrameTree.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("frameTree"))))));
        }
        /**
         * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetLayoutMetricsResult> getLayoutMetrics() {
            return client.call("Page.getLayoutMetrics", null, result_ -> new GetLayoutMetricsResult(result_));
        }
        /**
         * Returns navigation history for the current page.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNavigationHistoryResult> getNavigationHistory() {
            return client.call("Page.getNavigationHistory", null, result_ -> new GetNavigationHistoryResult(result_));
        }
        /**
         * Resets navigation history for the current page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetNavigationHistory() {
            return client.call("Page.resetNavigationHistory", null);
        }
        /**
         * Returns content of the given resource.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @param url protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResourceContentResult> getResourceContent(Page.FrameId frameId, String url) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            params.put("url", CdpObject.json(url));
            return client.call("Page.getResourceContent", params, result_ -> new GetResourceContentResult(result_));
        }
        /**
         * Returns content of the given resource.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResourceContentResult> getResourceContent(GetResourceContentRequest request) {
            return client.call("Page.getResourceContent", request == null ? null : request.toMap(), result_ -> new GetResourceContentResult(result_));
        }
        /**
         * Returns present frame / resource tree structure.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Page.FrameResourceTree> getResourceTree() {
            return client.call("Page.getResourceTree", null, result_ -> java.util.Objects.requireNonNull(Page.FrameResourceTree.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("frameTree"))))));
        }
        /**
         * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
         * @param accept protocol value
         * @param promptText protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> handleJavaScriptDialog(boolean accept, String promptText) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("accept", CdpObject.json(accept));
            params.put("promptText", CdpObject.json(promptText));
            return client.call("Page.handleJavaScriptDialog", params);
        }
        /**
         * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
         * @param accept protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> handleJavaScriptDialog(boolean accept) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("accept", CdpObject.json(accept));
            return client.call("Page.handleJavaScriptDialog", params);
        }
        /**
         * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> handleJavaScriptDialog(HandleJavaScriptDialogRequest request) {
            return client.call("Page.handleJavaScriptDialog", request == null ? null : request.toMap());
        }
        /**
         * Navigates current page to the given URL.
         * @param url protocol value
         * @param referrer protocol value
         * @param transitionType protocol value
         * @param frameId protocol value
         * @param referrerPolicy protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<NavigateResult> navigate(String url, @Nullable String referrer, Page.@Nullable TransitionType transitionType, Page.@Nullable FrameId frameId, Page.@Nullable ReferrerPolicy referrerPolicy) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            if (referrer != null) params.put("referrer", CdpObject.json(referrer));
            if (transitionType != null) params.put("transitionType", CdpObject.json(transitionType));
            if (frameId != null) params.put("frameId", CdpObject.json(frameId));
            if (referrerPolicy != null) params.put("referrerPolicy", CdpObject.json(referrerPolicy));
            return client.call("Page.navigate", params, result_ -> new NavigateResult(result_));
        }
        /**
         * Navigates current page to the given URL.
         * @param url protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<NavigateResult> navigate(String url) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            return client.call("Page.navigate", params, result_ -> new NavigateResult(result_));
        }
        /**
         * Navigates current page to the given URL.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<NavigateResult> navigate(NavigateRequest request) {
            return client.call("Page.navigate", request == null ? null : request.toMap(), result_ -> new NavigateResult(result_));
        }
        /**
         * Navigates current page to the given history entry.
         * @param entryId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> navigateToHistoryEntry(long entryId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("entryId", CdpObject.json(entryId));
            return client.call("Page.navigateToHistoryEntry", params);
        }
        /**
         * Navigates current page to the given history entry.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> navigateToHistoryEntry(NavigateToHistoryEntryRequest request) {
            return client.call("Page.navigateToHistoryEntry", request == null ? null : request.toMap());
        }
        /**
         * Print page as PDF.
         * @param landscape protocol value
         * @param displayHeaderFooter protocol value
         * @param printBackground protocol value
         * @param scale protocol value
         * @param paperWidth protocol value
         * @param paperHeight protocol value
         * @param marginTop protocol value
         * @param marginBottom protocol value
         * @param marginLeft protocol value
         * @param marginRight protocol value
         * @param pageRanges protocol value
         * @param headerTemplate protocol value
         * @param footerTemplate protocol value
         * @param preferCSSPageSize protocol value
         * @param transferMode protocol value
         * @param generateTaggedPDF protocol value
         * @param generateDocumentOutline protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<PrintToPDFResult> printToPDF(@Nullable Boolean landscape, @Nullable Boolean displayHeaderFooter, @Nullable Boolean printBackground, @Nullable Double scale, @Nullable Double paperWidth, @Nullable Double paperHeight, @Nullable Double marginTop, @Nullable Double marginBottom, @Nullable Double marginLeft, @Nullable Double marginRight, @Nullable String pageRanges, @Nullable String headerTemplate, @Nullable String footerTemplate, @Nullable Boolean preferCSSPageSize, @Nullable PrintToPDFTransferModeValues transferMode, @Nullable Boolean generateTaggedPDF, @Nullable Boolean generateDocumentOutline) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (landscape != null) params.put("landscape", CdpObject.json(landscape));
            if (displayHeaderFooter != null) params.put("displayHeaderFooter", CdpObject.json(displayHeaderFooter));
            if (printBackground != null) params.put("printBackground", CdpObject.json(printBackground));
            if (scale != null) params.put("scale", CdpObject.json(scale));
            if (paperWidth != null) params.put("paperWidth", CdpObject.json(paperWidth));
            if (paperHeight != null) params.put("paperHeight", CdpObject.json(paperHeight));
            if (marginTop != null) params.put("marginTop", CdpObject.json(marginTop));
            if (marginBottom != null) params.put("marginBottom", CdpObject.json(marginBottom));
            if (marginLeft != null) params.put("marginLeft", CdpObject.json(marginLeft));
            if (marginRight != null) params.put("marginRight", CdpObject.json(marginRight));
            if (pageRanges != null) params.put("pageRanges", CdpObject.json(pageRanges));
            if (headerTemplate != null) params.put("headerTemplate", CdpObject.json(headerTemplate));
            if (footerTemplate != null) params.put("footerTemplate", CdpObject.json(footerTemplate));
            if (preferCSSPageSize != null) params.put("preferCSSPageSize", CdpObject.json(preferCSSPageSize));
            if (transferMode != null) params.put("transferMode", CdpObject.json(transferMode));
            if (generateTaggedPDF != null) params.put("generateTaggedPDF", CdpObject.json(generateTaggedPDF));
            if (generateDocumentOutline != null) params.put("generateDocumentOutline", CdpObject.json(generateDocumentOutline));
            return client.call("Page.printToPDF", params, result_ -> new PrintToPDFResult(result_));
        }
        /**
         * Print page as PDF.
         * @return a stage completing with the command result
         */
        public CompletionStage<PrintToPDFResult> printToPDF() {
            return client.call("Page.printToPDF", null, result_ -> new PrintToPDFResult(result_));
        }
        /**
         * Print page as PDF.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PrintToPDFResult> printToPDF(PrintToPDFRequest request) {
            return client.call("Page.printToPDF", request == null ? null : request.toMap(), result_ -> new PrintToPDFResult(result_));
        }
        /**
         * Reloads given page optionally ignoring the cache.
         * @param ignoreCache protocol value
         * @param scriptToEvaluateOnLoad protocol value
         * @param loaderId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reload(@Nullable Boolean ignoreCache, @Nullable String scriptToEvaluateOnLoad, Network.@Nullable LoaderId loaderId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (ignoreCache != null) params.put("ignoreCache", CdpObject.json(ignoreCache));
            if (scriptToEvaluateOnLoad != null) params.put("scriptToEvaluateOnLoad", CdpObject.json(scriptToEvaluateOnLoad));
            if (loaderId != null) params.put("loaderId", CdpObject.json(loaderId));
            return client.call("Page.reload", params);
        }
        /**
         * Reloads given page optionally ignoring the cache.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reload() {
            return client.call("Page.reload", null);
        }
        /**
         * Reloads given page optionally ignoring the cache.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reload(ReloadRequest request) {
            return client.call("Page.reload", request == null ? null : request.toMap());
        }
        /**
         * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param identifier protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> removeScriptToEvaluateOnLoad(Page.ScriptIdentifier identifier) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("identifier", CdpObject.json(identifier));
            return client.call("Page.removeScriptToEvaluateOnLoad", params);
        }
        /**
         * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> removeScriptToEvaluateOnLoad(RemoveScriptToEvaluateOnLoadRequest request) {
            return client.call("Page.removeScriptToEvaluateOnLoad", request == null ? null : request.toMap());
        }
        /**
         * Removes given script from the list.
         * @param identifier protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScriptToEvaluateOnNewDocument(Page.ScriptIdentifier identifier) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("identifier", CdpObject.json(identifier));
            return client.call("Page.removeScriptToEvaluateOnNewDocument", params);
        }
        /**
         * Removes given script from the list.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeScriptToEvaluateOnNewDocument(RemoveScriptToEvaluateOnNewDocumentRequest request) {
            return client.call("Page.removeScriptToEvaluateOnNewDocument", request == null ? null : request.toMap());
        }
        /**
         * Acknowledges that a screencast frame has been received by the frontend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sessionId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> screencastFrameAck(long sessionId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("sessionId", CdpObject.json(sessionId));
            return client.call("Page.screencastFrameAck", params);
        }
        /**
         * Acknowledges that a screencast frame has been received by the frontend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> screencastFrameAck(ScreencastFrameAckRequest request) {
            return client.call("Page.screencastFrameAck", request == null ? null : request.toMap());
        }
        /**
         * Searches for given string in resource content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @param url protocol value
         * @param query protocol value
         * @param caseSensitive protocol value
         * @param isRegex protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResource(Page.FrameId frameId, String url, String query, @Nullable Boolean caseSensitive, @Nullable Boolean isRegex) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            params.put("url", CdpObject.json(url));
            params.put("query", CdpObject.json(query));
            if (caseSensitive != null) params.put("caseSensitive", CdpObject.json(caseSensitive));
            if (isRegex != null) params.put("isRegex", CdpObject.json(isRegex));
            return client.call("Page.searchInResource", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Searches for given string in resource content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @param url protocol value
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResource(Page.FrameId frameId, String url, String query) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            params.put("url", CdpObject.json(url));
            params.put("query", CdpObject.json(query));
            return client.call("Page.searchInResource", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Searches for given string in resource content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResource(SearchInResourceRequest request) {
            return client.call("Page.searchInResource", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Enable Chrome&#x27;s experimental ad filter on all sites.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAdBlockingEnabled(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Page.setAdBlockingEnabled", params);
        }
        /**
         * Enable Chrome&#x27;s experimental ad filter on all sites.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAdBlockingEnabled(SetAdBlockingEnabledRequest request) {
            return client.call("Page.setAdBlockingEnabled", request == null ? null : request.toMap());
        }
        /**
         * Enable page Content Security Policy by-passing.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBypassCSP(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Page.setBypassCSP", params);
        }
        /**
         * Enable page Content Security Policy by-passing.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBypassCSP(SetBypassCSPRequest request) {
            return client.call("Page.setBypassCSP", request == null ? null : request.toMap());
        }
        /**
         * Get Permissions Policy state on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Page.PermissionsPolicyFeatureState>> getPermissionsPolicyState(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Page.getPermissionsPolicyState", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("states")), element0 -> java.util.Objects.requireNonNull(Page.PermissionsPolicyFeatureState.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Get Permissions Policy state on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Page.PermissionsPolicyFeatureState>> getPermissionsPolicyState(GetPermissionsPolicyStateRequest request) {
            return client.call("Page.getPermissionsPolicyState", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("states")), element0 -> java.util.Objects.requireNonNull(Page.PermissionsPolicyFeatureState.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Get Origin Trials on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Page.OriginTrial>> getOriginTrials(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Page.getOriginTrials", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("originTrials")), element0 -> java.util.Objects.requireNonNull(Page.OriginTrial.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Get Origin Trials on given frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Page.OriginTrial>> getOriginTrials(GetOriginTrialsRequest request) {
            return client.call("Page.getOriginTrials", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("originTrials")), element0 -> java.util.Objects.requireNonNull(Page.OriginTrial.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
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
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDeviceMetricsOverride(long width, long height, double deviceScaleFactor, boolean mobile, @Nullable Double scale, @Nullable Long screenWidth, @Nullable Long screenHeight, @Nullable Long positionX, @Nullable Long positionY, @Nullable Boolean dontSetVisibleSize, Emulation.@Nullable ScreenOrientation screenOrientation, Page.@Nullable Viewport viewport) {
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
            return client.call("Page.setDeviceMetricsOverride", params);
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param width protocol value
         * @param height protocol value
         * @param deviceScaleFactor protocol value
         * @param mobile protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDeviceMetricsOverride(long width, long height, double deviceScaleFactor, boolean mobile) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            params.put("deviceScaleFactor", CdpObject.json(deviceScaleFactor));
            params.put("mobile", CdpObject.json(mobile));
            return client.call("Page.setDeviceMetricsOverride", params);
        }
        /**
         * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and &quot;device-width&quot;/&quot;device-height&quot;-related CSS media query results).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDeviceMetricsOverride(SetDeviceMetricsOverrideRequest request) {
            return client.call("Page.setDeviceMetricsOverride", request == null ? null : request.toMap());
        }
        /**
         * Overrides the Device Orientation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param alpha protocol value
         * @param beta protocol value
         * @param gamma protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDeviceOrientationOverride(double alpha, double beta, double gamma) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("alpha", CdpObject.json(alpha));
            params.put("beta", CdpObject.json(beta));
            params.put("gamma", CdpObject.json(gamma));
            return client.call("Page.setDeviceOrientationOverride", params);
        }
        /**
         * Overrides the Device Orientation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDeviceOrientationOverride(SetDeviceOrientationOverrideRequest request) {
            return client.call("Page.setDeviceOrientationOverride", request == null ? null : request.toMap());
        }
        /**
         * Set generic font families.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param fontFamilies protocol value
         * @param forScripts protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFontFamilies(Page.FontFamilies fontFamilies, java.util.List<Page.ScriptFontFamilies> forScripts) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("fontFamilies", CdpObject.json(fontFamilies));
            params.put("forScripts", CdpObject.json(forScripts));
            return client.call("Page.setFontFamilies", params);
        }
        /**
         * Set generic font families.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param fontFamilies protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFontFamilies(Page.FontFamilies fontFamilies) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("fontFamilies", CdpObject.json(fontFamilies));
            return client.call("Page.setFontFamilies", params);
        }
        /**
         * Set generic font families.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFontFamilies(SetFontFamiliesRequest request) {
            return client.call("Page.setFontFamilies", request == null ? null : request.toMap());
        }
        /**
         * Set default font sizes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param fontSizes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFontSizes(Page.FontSizes fontSizes) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("fontSizes", CdpObject.json(fontSizes));
            return client.call("Page.setFontSizes", params);
        }
        /**
         * Set default font sizes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFontSizes(SetFontSizesRequest request) {
            return client.call("Page.setFontSizes", request == null ? null : request.toMap());
        }
        /**
         * Sets given markup as the document&#x27;s HTML.
         * @param frameId protocol value
         * @param html protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentContent(Page.FrameId frameId, String html) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            params.put("html", CdpObject.json(html));
            return client.call("Page.setDocumentContent", params);
        }
        /**
         * Sets given markup as the document&#x27;s HTML.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDocumentContent(SetDocumentContentRequest request) {
            return client.call("Page.setDocumentContent", request == null ? null : request.toMap());
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         * @param downloadPath protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorBehaviorValues behavior, String downloadPath) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("behavior", CdpObject.json(behavior));
            params.put("downloadPath", CdpObject.json(downloadPath));
            return client.call("Page.setDownloadBehavior", params);
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param behavior protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorBehaviorValues behavior) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("behavior", CdpObject.json(behavior));
            return client.call("Page.setDownloadBehavior", params);
        }
        /**
         * Set the behavior when downloading a file.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setDownloadBehavior(SetDownloadBehaviorRequest request) {
            return client.call("Page.setDownloadBehavior", request == null ? null : request.toMap());
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
         * @param latitude protocol value
         * @param longitude protocol value
         * @param accuracy protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setGeolocationOverride(@Nullable Double latitude, @Nullable Double longitude, @Nullable Double accuracy) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (latitude != null) params.put("latitude", CdpObject.json(latitude));
            if (longitude != null) params.put("longitude", CdpObject.json(longitude));
            if (accuracy != null) params.put("accuracy", CdpObject.json(accuracy));
            return client.call("Page.setGeolocationOverride", params);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setGeolocationOverride() {
            return client.call("Page.setGeolocationOverride", null);
        }
        /**
         * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setGeolocationOverride(SetGeolocationOverrideRequest request) {
            return client.call("Page.setGeolocationOverride", request == null ? null : request.toMap());
        }
        /**
         * Controls whether page will emit lifecycle events.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLifecycleEventsEnabled(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Page.setLifecycleEventsEnabled", params);
        }
        /**
         * Controls whether page will emit lifecycle events.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLifecycleEventsEnabled(SetLifecycleEventsEnabledRequest request) {
            return client.call("Page.setLifecycleEventsEnabled", request == null ? null : request.toMap());
        }
        /**
         * Toggles mouse event-based touch event emulation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @param configuration protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled, SetTouchEmulationEnabledConfigurationValues configuration) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("configuration", CdpObject.json(configuration));
            return client.call("Page.setTouchEmulationEnabled", params);
        }
        /**
         * Toggles mouse event-based touch event emulation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setTouchEmulationEnabled(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Page.setTouchEmulationEnabled", params);
        }
        /**
         * Toggles mouse event-based touch event emulation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setTouchEmulationEnabled(SetTouchEmulationEnabledRequest request) {
            return client.call("Page.setTouchEmulationEnabled", request == null ? null : request.toMap());
        }
        /**
         * Starts sending each frame using the {@code screencastFrame} event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param format protocol value
         * @param quality protocol value
         * @param maxWidth protocol value
         * @param maxHeight protocol value
         * @param everyNthFrame protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startScreencast(@Nullable StartScreencastFormatValues format, @Nullable Long quality, @Nullable Long maxWidth, @Nullable Long maxHeight, @Nullable Long everyNthFrame) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (format != null) params.put("format", CdpObject.json(format));
            if (quality != null) params.put("quality", CdpObject.json(quality));
            if (maxWidth != null) params.put("maxWidth", CdpObject.json(maxWidth));
            if (maxHeight != null) params.put("maxHeight", CdpObject.json(maxHeight));
            if (everyNthFrame != null) params.put("everyNthFrame", CdpObject.json(everyNthFrame));
            return client.call("Page.startScreencast", params);
        }
        /**
         * Starts sending each frame using the {@code screencastFrame} event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startScreencast() {
            return client.call("Page.startScreencast", null);
        }
        /**
         * Starts sending each frame using the {@code screencastFrame} event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startScreencast(StartScreencastRequest request) {
            return client.call("Page.startScreencast", request == null ? null : request.toMap());
        }
        /**
         * Force the page stop all navigations and pending resource fetches.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopLoading() {
            return client.call("Page.stopLoading", null);
        }
        /**
         * Crashes renderer on the IO thread, generates minidumps.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> crash() {
            return client.call("Page.crash", null);
        }
        /**
         * Tries to close page, running its beforeunload hooks, if any.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close() {
            return client.call("Page.close", null);
        }
        /**
         * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setWebLifecycleState(SetWebLifecycleStateStateValues state) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("state", CdpObject.json(state));
            return client.call("Page.setWebLifecycleState", params);
        }
        /**
         * Tries to update the web lifecycle state of the page. It will transition the page to the given state according to: https://github.com/WICG/web-lifecycle/
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setWebLifecycleState(SetWebLifecycleStateRequest request) {
            return client.call("Page.setWebLifecycleState", request == null ? null : request.toMap());
        }
        /**
         * Stops sending each frame in the {@code screencastFrame}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopScreencast() {
            return client.call("Page.stopScreencast", null);
        }
        /**
         * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scripts protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> produceCompilationCache(java.util.List<Page.CompilationCacheParams> scripts) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scripts", CdpObject.json(scripts));
            return client.call("Page.produceCompilationCache", params);
        }
        /**
         * Requests backend to produce compilation cache for the specified scripts. {@code scripts} are appended to the list of scripts for which the cache would be produced. The list may be reset during page navigation. When script with a matching URL is encountered, the cache is optionally produced upon backend discretion, based on internal heuristics. See also: {@code Page.compilationCacheProduced}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> produceCompilationCache(ProduceCompilationCacheRequest request) {
            return client.call("Page.produceCompilationCache", request == null ? null : request.toMap());
        }
        /**
         * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param url protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addCompilationCache(String url, String data) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            params.put("data", CdpObject.json(data));
            return client.call("Page.addCompilationCache", params);
        }
        /**
         * Seeds compilation cache for given url. Compilation cache does not survive cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addCompilationCache(AddCompilationCacheRequest request) {
            return client.call("Page.addCompilationCache", request == null ? null : request.toMap());
        }
        /**
         * Clears seeded compilation cache.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCompilationCache() {
            return client.call("Page.clearCompilationCache", null);
        }
        /**
         * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param mode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSPCTransactionMode(SetSPCTransactionModeModeValues mode) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("mode", CdpObject.json(mode));
            return client.call("Page.setSPCTransactionMode", params);
        }
        /**
         * Sets the Secure Payment Confirmation transaction mode. https://w3c.github.io/secure-payment-confirmation/#sctn-automation-set-spc-transaction-mode
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSPCTransactionMode(SetSPCTransactionModeRequest request) {
            return client.call("Page.setSPCTransactionMode", request == null ? null : request.toMap());
        }
        /**
         * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param mode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRPHRegistrationMode(SetRPHRegistrationModeModeValues mode) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("mode", CdpObject.json(mode));
            return client.call("Page.setRPHRegistrationMode", params);
        }
        /**
         * Extensions for Custom Handlers API: https://html.spec.whatwg.org/multipage/system-state.html#rph-automation
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRPHRegistrationMode(SetRPHRegistrationModeRequest request) {
            return client.call("Page.setRPHRegistrationMode", request == null ? null : request.toMap());
        }
        /**
         * Generates a report for testing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param message protocol value
         * @param group protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> generateTestReport(String message, String group) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("message", CdpObject.json(message));
            params.put("group", CdpObject.json(group));
            return client.call("Page.generateTestReport", params);
        }
        /**
         * Generates a report for testing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param message protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> generateTestReport(String message) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("message", CdpObject.json(message));
            return client.call("Page.generateTestReport", params);
        }
        /**
         * Generates a report for testing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> generateTestReport(GenerateTestReportRequest request) {
            return client.call("Page.generateTestReport", request == null ? null : request.toMap());
        }
        /**
         * Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> waitForDebugger() {
            return client.call("Page.waitForDebugger", null);
        }
        /**
         * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
         * @param enabled protocol value
         * @param cancel protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptFileChooserDialog(boolean enabled, boolean cancel) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            params.put("cancel", CdpObject.json(cancel));
            return client.call("Page.setInterceptFileChooserDialog", params);
        }
        /**
         * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptFileChooserDialog(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Page.setInterceptFileChooserDialog", params);
        }
        /**
         * Intercept file chooser requests and transfer control to protocol clients. When file chooser interception is enabled, native file chooser dialog is not shown. Instead, a protocol event {@code Page.fileChooserOpened} is emitted.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptFileChooserDialog(SetInterceptFileChooserDialogRequest request) {
            return client.call("Page.setInterceptFileChooserDialog", request == null ? null : request.toMap());
        }
        /**
         * Enable/disable prerendering manually.
         * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
         * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isAllowed protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrerenderingAllowed(boolean isAllowed) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("isAllowed", CdpObject.json(isAllowed));
            return client.call("Page.setPrerenderingAllowed", params);
        }
        /**
         * Enable/disable prerendering manually.
         * <p>This command is a short-term solution for https://crbug.com/1440085. See https://docs.google.com/document/d/12HVmFxYj5Jc-eJr5OmWsa2bqTJsbgGLKI6ZIyx0_wpA for more details.
         * <p>TODO(https://crbug.com/1440085): Remove this once Puppeteer supports tab targets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPrerenderingAllowed(SetPrerenderingAllowedRequest request) {
            return client.call("Page.setPrerenderingAllowed", request == null ? null : request.toMap());
        }
        /**
         * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeActionableInformation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getAnnotatedPageContent(boolean includeActionableInformation) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("includeActionableInformation", CdpObject.json(includeActionableInformation));
            return client.call("Page.getAnnotatedPageContent", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("content")));
        }
        /**
         * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getAnnotatedPageContent() {
            return client.call("Page.getAnnotatedPageContent", null, result_ -> (String) java.util.Objects.requireNonNull(result_.get("content")));
        }
        /**
         * Get the annotated page content for the main frame. This is an experimental command that is subject to change.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getAnnotatedPageContent(GetAnnotatedPageContentRequest request) {
            return client.call("Page.getAnnotatedPageContent", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("content")));
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
