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
 * Chrome DevTools Protocol Security domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Security.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Security {
    private Security() {}
    /**
     * An internal certificate ID value.
     */
    public static final class CertificateId implements CdpValue<Long> {
        public final long value;
        public CertificateId(long value) { this.value = value; }
        @Nonnull public Long value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof CertificateId)) return false;
            return value == ((CertificateId) other).value;
        }
        @Override public int hashCode() { return Long.hashCode(value); }
        @Override public String toString() { return "CertificateId(" + value + ")"; }
    }
    /**
     * A description of mixed content (HTTP resources on HTTPS pages), as defined by https://www.w3.org/TR/mixed-content/#categories
     */
    public enum MixedContentType implements CdpValue<String> {
        BLOCKABLE("blockable"),
        OPTIONALLY_BLOCKABLE("optionally-blockable"),
        NONE("none");
        public final String value;
        MixedContentType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static MixedContentType of(@Nonnull String value) {
            for (MixedContentType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown MixedContentType value: " + value);
        }
    }
    /**
     * The security level of a page or resource.
     */
    public enum SecurityState implements CdpValue<String> {
        UNKNOWN("unknown"),
        NEUTRAL("neutral"),
        INSECURE("insecure"),
        SECURE("secure"),
        INFO("info"),
        INSECURE_BROKEN("insecure-broken");
        public final String value;
        SecurityState(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static SecurityState of(@Nonnull String value) {
            for (SecurityState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SecurityState value: " + value);
        }
    }
    /**
     * Details about the security state of the page certificate.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CertificateSecurityState extends CdpObject {
        public CertificateSecurityState() {}
        private CertificateSecurityState(Map<String, Object> values) { super(values); }
        public static CertificateSecurityState fromMap(Map<String, Object> values) {
            return new CertificateSecurityState(values);
        }
        /**
         * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
         * @return the protocol field value
         */
        public String protocol() {
            return (String) require("protocol");
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @return the protocol field value
         */
        public String keyExchange() {
            return (String) require("keyExchange");
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> keyExchangeGroup() {
            return Optional.ofNullable((String) raw("keyExchangeGroup"));
        }
        /**
         * Cipher name.
         * @return the protocol field value
         */
        public String cipher() {
            return (String) require("cipher");
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> mac() {
            return Optional.ofNullable((String) raw("mac"));
        }
        /**
         * Page certificate.
         * @return the protocol field value
         */
        public java.util.List<String> certificate() {
            return CdpObject.requireList(require("certificate"), element0 -> (String) element0);
        }
        /**
         * Certificate subject name.
         * @return the protocol field value
         */
        public String subjectName() {
            return (String) require("subjectName");
        }
        /**
         * Name of the issuing CA.
         * @return the protocol field value
         */
        public String issuer() {
            return (String) require("issuer");
        }
        /**
         * Certificate valid from date.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch validFrom() {
            return new Network.TimeSinceEpoch(((Number) require("validFrom")).doubleValue());
        }
        /**
         * Certificate valid to (expiration) date
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch validTo() {
            return new Network.TimeSinceEpoch(((Number) require("validTo")).doubleValue());
        }
        /**
         * The highest priority network error code, if the certificate has an error.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> certificateNetworkError() {
            return Optional.ofNullable((String) raw("certificateNetworkError"));
        }
        /**
         * True if the certificate uses a weak signature algorithm.
         * @return the protocol field value
         */
        public boolean certificateHasWeakSignature() {
            return (Boolean) require("certificateHasWeakSignature");
        }
        /**
         * True if the certificate has a SHA1 signature in the chain.
         * @return the protocol field value
         */
        public boolean certificateHasSha1Signature() {
            return (Boolean) require("certificateHasSha1Signature");
        }
        /**
         * True if modern SSL
         * @return the protocol field value
         */
        public boolean modernSSL() {
            return (Boolean) require("modernSSL");
        }
        /**
         * True if the connection is using an obsolete SSL protocol.
         * @return the protocol field value
         */
        public boolean obsoleteSslProtocol() {
            return (Boolean) require("obsoleteSslProtocol");
        }
        /**
         * True if the connection is using an obsolete SSL key exchange.
         * @return the protocol field value
         */
        public boolean obsoleteSslKeyExchange() {
            return (Boolean) require("obsoleteSslKeyExchange");
        }
        /**
         * True if the connection is using an obsolete SSL cipher.
         * @return the protocol field value
         */
        public boolean obsoleteSslCipher() {
            return (Boolean) require("obsoleteSslCipher");
        }
        /**
         * True if the connection is using an obsolete SSL signature.
         * @return the protocol field value
         */
        public boolean obsoleteSslSignature() {
            return (Boolean) require("obsoleteSslSignature");
        }
        /**
         * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
         * @param protocol field value
         * @return this model
         */
        public CertificateSecurityState protocol(String protocol) {
            set("protocol", protocol);
            return this;
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @param keyExchange field value
         * @return this model
         */
        public CertificateSecurityState keyExchange(String keyExchange) {
            set("keyExchange", keyExchange);
            return this;
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @param keyExchangeGroup field value; empty omits the value
         * @return this model
         */
        public CertificateSecurityState keyExchangeGroup(Optional<String> keyExchangeGroup) {
            set("keyExchangeGroup", keyExchangeGroup.orElse(null));
            return this;
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @param keyExchangeGroup field value; null removes the value
         * @return this model
         */
        public CertificateSecurityState keyExchangeGroup(String keyExchangeGroup) {
            set("keyExchangeGroup", keyExchangeGroup);
            return this;
        }
        /**
         * Cipher name.
         * @param cipher field value
         * @return this model
         */
        public CertificateSecurityState cipher(String cipher) {
            set("cipher", cipher);
            return this;
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @param mac field value; empty omits the value
         * @return this model
         */
        public CertificateSecurityState mac(Optional<String> mac) {
            set("mac", mac.orElse(null));
            return this;
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @param mac field value; null removes the value
         * @return this model
         */
        public CertificateSecurityState mac(String mac) {
            set("mac", mac);
            return this;
        }
        /**
         * Page certificate.
         * @param certificate field value
         * @return this model
         */
        public CertificateSecurityState certificate(java.util.List<String> certificate) {
            set("certificate", certificate);
            return this;
        }
        /**
         * Certificate subject name.
         * @param subjectName field value
         * @return this model
         */
        public CertificateSecurityState subjectName(String subjectName) {
            set("subjectName", subjectName);
            return this;
        }
        /**
         * Name of the issuing CA.
         * @param issuer field value
         * @return this model
         */
        public CertificateSecurityState issuer(String issuer) {
            set("issuer", issuer);
            return this;
        }
        /**
         * Certificate valid from date.
         * @param validFrom field value
         * @return this model
         */
        public CertificateSecurityState validFrom(Network.TimeSinceEpoch validFrom) {
            set("validFrom", validFrom);
            return this;
        }
        /**
         * Certificate valid to (expiration) date
         * @param validTo field value
         * @return this model
         */
        public CertificateSecurityState validTo(Network.TimeSinceEpoch validTo) {
            set("validTo", validTo);
            return this;
        }
        /**
         * The highest priority network error code, if the certificate has an error.
         * @param certificateNetworkError field value; empty omits the value
         * @return this model
         */
        public CertificateSecurityState certificateNetworkError(Optional<String> certificateNetworkError) {
            set("certificateNetworkError", certificateNetworkError.orElse(null));
            return this;
        }
        /**
         * The highest priority network error code, if the certificate has an error.
         * @param certificateNetworkError field value; null removes the value
         * @return this model
         */
        public CertificateSecurityState certificateNetworkError(String certificateNetworkError) {
            set("certificateNetworkError", certificateNetworkError);
            return this;
        }
        /**
         * True if the certificate uses a weak signature algorithm.
         * @param certificateHasWeakSignature field value
         * @return this model
         */
        public CertificateSecurityState certificateHasWeakSignature(boolean certificateHasWeakSignature) {
            set("certificateHasWeakSignature", certificateHasWeakSignature);
            return this;
        }
        /**
         * True if the certificate has a SHA1 signature in the chain.
         * @param certificateHasSha1Signature field value
         * @return this model
         */
        public CertificateSecurityState certificateHasSha1Signature(boolean certificateHasSha1Signature) {
            set("certificateHasSha1Signature", certificateHasSha1Signature);
            return this;
        }
        /**
         * True if modern SSL
         * @param modernSSL field value
         * @return this model
         */
        public CertificateSecurityState modernSSL(boolean modernSSL) {
            set("modernSSL", modernSSL);
            return this;
        }
        /**
         * True if the connection is using an obsolete SSL protocol.
         * @param obsoleteSslProtocol field value
         * @return this model
         */
        public CertificateSecurityState obsoleteSslProtocol(boolean obsoleteSslProtocol) {
            set("obsoleteSslProtocol", obsoleteSslProtocol);
            return this;
        }
        /**
         * True if the connection is using an obsolete SSL key exchange.
         * @param obsoleteSslKeyExchange field value
         * @return this model
         */
        public CertificateSecurityState obsoleteSslKeyExchange(boolean obsoleteSslKeyExchange) {
            set("obsoleteSslKeyExchange", obsoleteSslKeyExchange);
            return this;
        }
        /**
         * True if the connection is using an obsolete SSL cipher.
         * @param obsoleteSslCipher field value
         * @return this model
         */
        public CertificateSecurityState obsoleteSslCipher(boolean obsoleteSslCipher) {
            set("obsoleteSslCipher", obsoleteSslCipher);
            return this;
        }
        /**
         * True if the connection is using an obsolete SSL signature.
         * @param obsoleteSslSignature field value
         * @return this model
         */
        public CertificateSecurityState obsoleteSslSignature(boolean obsoleteSslSignature) {
            set("obsoleteSslSignature", obsoleteSslSignature);
            return this;
        }
    }
    /**
     * Wire values for SafetyTipStatus.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum SafetyTipStatus implements CdpValue<String> {
        BADREPUTATION("badReputation"),
        LOOKALIKE("lookalike");
        public final String value;
        SafetyTipStatus(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static SafetyTipStatus of(@Nonnull String value) {
            for (SafetyTipStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SafetyTipStatus value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SafetyTipInfo extends CdpObject {
        public SafetyTipInfo() {}
        private SafetyTipInfo(Map<String, Object> values) { super(values); }
        public static SafetyTipInfo fromMap(Map<String, Object> values) {
            return new SafetyTipInfo(values);
        }
        /**
         * Describes whether the page triggers any safety tips or reputation warnings. Default is unknown.
         * @return the protocol field value
         */
        public Security.SafetyTipStatus safetyTipStatus() {
            return Security.SafetyTipStatus.of((String) require("safetyTipStatus"));
        }
        /**
         * The URL the safety tip suggested (&quot;Did you mean?&quot;). Only filled in for lookalike matches.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> safeUrl() {
            return Optional.ofNullable((String) raw("safeUrl"));
        }
        /**
         * Describes whether the page triggers any safety tips or reputation warnings. Default is unknown.
         * @param safetyTipStatus field value
         * @return this model
         */
        public SafetyTipInfo safetyTipStatus(Security.SafetyTipStatus safetyTipStatus) {
            set("safetyTipStatus", safetyTipStatus);
            return this;
        }
        /**
         * The URL the safety tip suggested (&quot;Did you mean?&quot;). Only filled in for lookalike matches.
         * @param safeUrl field value; empty omits the value
         * @return this model
         */
        public SafetyTipInfo safeUrl(Optional<String> safeUrl) {
            set("safeUrl", safeUrl.orElse(null));
            return this;
        }
        /**
         * The URL the safety tip suggested (&quot;Did you mean?&quot;). Only filled in for lookalike matches.
         * @param safeUrl field value; null removes the value
         * @return this model
         */
        public SafetyTipInfo safeUrl(String safeUrl) {
            set("safeUrl", safeUrl);
            return this;
        }
    }
    /**
     * Security state information about the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VisibleSecurityState extends CdpObject {
        public VisibleSecurityState() {}
        private VisibleSecurityState(Map<String, Object> values) { super(values); }
        public static VisibleSecurityState fromMap(Map<String, Object> values) {
            return new VisibleSecurityState(values);
        }
        /**
         * The security level of the page.
         * @return the protocol field value
         */
        public Security.SecurityState securityState() {
            return Security.SecurityState.of((String) require("securityState"));
        }
        /**
         * Security state details about the page certificate.
         * @return the protocol field value, empty when absent
         */
        public Optional<Security.CertificateSecurityState> certificateSecurityState() {
            return Optional.ofNullable(raw("certificateSecurityState") == null ? null : Security.CertificateSecurityState.fromMap(java.util.Objects.requireNonNull(objectMap(raw("certificateSecurityState")))));
        }
        /**
         * The type of Safety Tip triggered on the page. Note that this field will be set even if the Safety Tip UI was not actually shown.
         * @return the protocol field value, empty when absent
         */
        public Optional<Security.SafetyTipInfo> safetyTipInfo() {
            return Optional.ofNullable(raw("safetyTipInfo") == null ? null : Security.SafetyTipInfo.fromMap(java.util.Objects.requireNonNull(objectMap(raw("safetyTipInfo")))));
        }
        /**
         * Array of security state issues ids.
         * @return the protocol field value
         */
        public java.util.List<String> securityStateIssueIds() {
            return CdpObject.requireList(require("securityStateIssueIds"), element0 -> (String) element0);
        }
        /**
         * The security level of the page.
         * @param securityState field value
         * @return this model
         */
        public VisibleSecurityState securityState(Security.SecurityState securityState) {
            set("securityState", securityState);
            return this;
        }
        /**
         * Security state details about the page certificate.
         * @param certificateSecurityState field value; empty omits the value
         * @return this model
         */
        public VisibleSecurityState certificateSecurityState(Optional<Security.CertificateSecurityState> certificateSecurityState) {
            set("certificateSecurityState", certificateSecurityState.orElse(null));
            return this;
        }
        /**
         * Security state details about the page certificate.
         * @param certificateSecurityState field value; null removes the value
         * @return this model
         */
        public VisibleSecurityState certificateSecurityState(Security.CertificateSecurityState certificateSecurityState) {
            set("certificateSecurityState", certificateSecurityState);
            return this;
        }
        /**
         * The type of Safety Tip triggered on the page. Note that this field will be set even if the Safety Tip UI was not actually shown.
         * @param safetyTipInfo field value; empty omits the value
         * @return this model
         */
        public VisibleSecurityState safetyTipInfo(Optional<Security.SafetyTipInfo> safetyTipInfo) {
            set("safetyTipInfo", safetyTipInfo.orElse(null));
            return this;
        }
        /**
         * The type of Safety Tip triggered on the page. Note that this field will be set even if the Safety Tip UI was not actually shown.
         * @param safetyTipInfo field value; null removes the value
         * @return this model
         */
        public VisibleSecurityState safetyTipInfo(Security.SafetyTipInfo safetyTipInfo) {
            set("safetyTipInfo", safetyTipInfo);
            return this;
        }
        /**
         * Array of security state issues ids.
         * @param securityStateIssueIds field value
         * @return this model
         */
        public VisibleSecurityState securityStateIssueIds(java.util.List<String> securityStateIssueIds) {
            set("securityStateIssueIds", securityStateIssueIds);
            return this;
        }
    }
    /**
     * An explanation of an factor contributing to the security state.
     */
    public static final class SecurityStateExplanation extends CdpObject {
        public SecurityStateExplanation() {}
        private SecurityStateExplanation(Map<String, Object> values) { super(values); }
        public static SecurityStateExplanation fromMap(Map<String, Object> values) {
            return new SecurityStateExplanation(values);
        }
        /**
         * Security state representing the severity of the factor being explained.
         * @return the protocol field value
         */
        public Security.SecurityState securityState() {
            return Security.SecurityState.of((String) require("securityState"));
        }
        /**
         * Title describing the type of factor.
         * @return the protocol field value
         */
        public String title() {
            return (String) require("title");
        }
        /**
         * Short phrase describing the type of factor.
         * @return the protocol field value
         */
        public String summary() {
            return (String) require("summary");
        }
        /**
         * Full text explanation of the factor.
         * @return the protocol field value
         */
        public String description() {
            return (String) require("description");
        }
        /**
         * The type of mixed content described by the explanation.
         * @return the protocol field value
         */
        public Security.MixedContentType mixedContentType() {
            return Security.MixedContentType.of((String) require("mixedContentType"));
        }
        /**
         * Page certificate.
         * @return the protocol field value
         */
        public java.util.List<String> certificate() {
            return CdpObject.requireList(require("certificate"), element0 -> (String) element0);
        }
        /**
         * Recommendations to fix any issues.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> recommendations() {
            return Optional.ofNullable(list(raw("recommendations"), element0 -> (String) element0));
        }
        /**
         * Security state representing the severity of the factor being explained.
         * @param securityState field value
         * @return this model
         */
        public SecurityStateExplanation securityState(Security.SecurityState securityState) {
            set("securityState", securityState);
            return this;
        }
        /**
         * Title describing the type of factor.
         * @param title field value
         * @return this model
         */
        public SecurityStateExplanation title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Short phrase describing the type of factor.
         * @param summary field value
         * @return this model
         */
        public SecurityStateExplanation summary(String summary) {
            set("summary", summary);
            return this;
        }
        /**
         * Full text explanation of the factor.
         * @param description field value
         * @return this model
         */
        public SecurityStateExplanation description(String description) {
            set("description", description);
            return this;
        }
        /**
         * The type of mixed content described by the explanation.
         * @param mixedContentType field value
         * @return this model
         */
        public SecurityStateExplanation mixedContentType(Security.MixedContentType mixedContentType) {
            set("mixedContentType", mixedContentType);
            return this;
        }
        /**
         * Page certificate.
         * @param certificate field value
         * @return this model
         */
        public SecurityStateExplanation certificate(java.util.List<String> certificate) {
            set("certificate", certificate);
            return this;
        }
        /**
         * Recommendations to fix any issues.
         * @param recommendations field value; empty omits the value
         * @return this model
         */
        public SecurityStateExplanation recommendations(Optional<java.util.List<String>> recommendations) {
            set("recommendations", recommendations.orElse(null));
            return this;
        }
        /**
         * Recommendations to fix any issues.
         * @param recommendations field value; null removes the value
         * @return this model
         */
        public SecurityStateExplanation recommendations(java.util.List<String> recommendations) {
            set("recommendations", recommendations);
            return this;
        }
    }
    /**
     * Information about insecure content on the page.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class InsecureContentStatus extends CdpObject {
        public InsecureContentStatus() {}
        private InsecureContentStatus(Map<String, Object> values) { super(values); }
        public static InsecureContentStatus fromMap(Map<String, Object> values) {
            return new InsecureContentStatus(values);
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        public boolean ranMixedContent() {
            return (Boolean) require("ranMixedContent");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        public boolean displayedMixedContent() {
            return (Boolean) require("displayedMixedContent");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        public boolean containedMixedForm() {
            return (Boolean) require("containedMixedForm");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        public boolean ranContentWithCertErrors() {
            return (Boolean) require("ranContentWithCertErrors");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        public boolean displayedContentWithCertErrors() {
            return (Boolean) require("displayedContentWithCertErrors");
        }
        /**
         * Always set to unknown.
         * @return the protocol field value
         */
        public Security.SecurityState ranInsecureContentStyle() {
            return Security.SecurityState.of((String) require("ranInsecureContentStyle"));
        }
        /**
         * Always set to unknown.
         * @return the protocol field value
         */
        public Security.SecurityState displayedInsecureContentStyle() {
            return Security.SecurityState.of((String) require("displayedInsecureContentStyle"));
        }
        /**
         * Always false.
         * @param ranMixedContent field value
         * @return this model
         */
        public InsecureContentStatus ranMixedContent(boolean ranMixedContent) {
            set("ranMixedContent", ranMixedContent);
            return this;
        }
        /**
         * Always false.
         * @param displayedMixedContent field value
         * @return this model
         */
        public InsecureContentStatus displayedMixedContent(boolean displayedMixedContent) {
            set("displayedMixedContent", displayedMixedContent);
            return this;
        }
        /**
         * Always false.
         * @param containedMixedForm field value
         * @return this model
         */
        public InsecureContentStatus containedMixedForm(boolean containedMixedForm) {
            set("containedMixedForm", containedMixedForm);
            return this;
        }
        /**
         * Always false.
         * @param ranContentWithCertErrors field value
         * @return this model
         */
        public InsecureContentStatus ranContentWithCertErrors(boolean ranContentWithCertErrors) {
            set("ranContentWithCertErrors", ranContentWithCertErrors);
            return this;
        }
        /**
         * Always false.
         * @param displayedContentWithCertErrors field value
         * @return this model
         */
        public InsecureContentStatus displayedContentWithCertErrors(boolean displayedContentWithCertErrors) {
            set("displayedContentWithCertErrors", displayedContentWithCertErrors);
            return this;
        }
        /**
         * Always set to unknown.
         * @param ranInsecureContentStyle field value
         * @return this model
         */
        public InsecureContentStatus ranInsecureContentStyle(Security.SecurityState ranInsecureContentStyle) {
            set("ranInsecureContentStyle", ranInsecureContentStyle);
            return this;
        }
        /**
         * Always set to unknown.
         * @param displayedInsecureContentStyle field value
         * @return this model
         */
        public InsecureContentStatus displayedInsecureContentStyle(Security.SecurityState displayedInsecureContentStyle) {
            set("displayedInsecureContentStyle", displayedInsecureContentStyle);
            return this;
        }
    }
    /**
     * The action to take when a certificate error occurs. continue will continue processing the request and cancel will cancel the request.
     */
    public enum CertificateErrorAction implements CdpValue<String> {
        CONTINUE("continue"),
        CANCEL("cancel");
        public final String value;
        CertificateErrorAction(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static CertificateErrorAction of(@Nonnull String value) {
            for (CertificateErrorAction constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CertificateErrorAction value: " + value);
        }
    }
    /**
     * There is a certificate error. If overriding certificate errors is enabled, then it should be handled with the {@code handleCertificateError} command. Note: this event does not fire if the certificate error has been allowed internally. Only one client per target should override certificate errors at the same time.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CertificateErrorEvent extends CdpObject {
        public CertificateErrorEvent() {}
        private CertificateErrorEvent(Map<String, Object> values) { super(values); }
        public static CertificateErrorEvent fromMap(Map<String, Object> values) {
            return new CertificateErrorEvent(values);
        }
        /**
         * The ID of the event.
         * @return the protocol field value
         */
        public long eventId() {
            return ((Number) require("eventId")).longValue();
        }
        /**
         * The type of the error.
         * @return the protocol field value
         */
        public String errorType() {
            return (String) require("errorType");
        }
        /**
         * The url that was requested.
         * @return the protocol field value
         */
        public String requestURL() {
            return (String) require("requestURL");
        }
        /**
         * The ID of the event.
         * @param eventId field value
         * @return this model
         */
        public CertificateErrorEvent eventId(long eventId) {
            set("eventId", eventId);
            return this;
        }
        /**
         * The type of the error.
         * @param errorType field value
         * @return this model
         */
        public CertificateErrorEvent errorType(String errorType) {
            set("errorType", errorType);
            return this;
        }
        /**
         * The url that was requested.
         * @param requestURL field value
         * @return this model
         */
        public CertificateErrorEvent requestURL(String requestURL) {
            set("requestURL", requestURL);
            return this;
        }
    }
    /**
     * The security state of the page changed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VisibleSecurityStateChangedEvent extends CdpObject {
        public VisibleSecurityStateChangedEvent() {}
        private VisibleSecurityStateChangedEvent(Map<String, Object> values) { super(values); }
        public static VisibleSecurityStateChangedEvent fromMap(Map<String, Object> values) {
            return new VisibleSecurityStateChangedEvent(values);
        }
        /**
         * Security state information about the page.
         * @return the protocol field value
         */
        public Security.VisibleSecurityState visibleSecurityState() {
            return java.util.Objects.requireNonNull(Security.VisibleSecurityState.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("visibleSecurityState")))));
        }
        /**
         * Security state information about the page.
         * @param visibleSecurityState field value
         * @return this model
         */
        public VisibleSecurityStateChangedEvent visibleSecurityState(Security.VisibleSecurityState visibleSecurityState) {
            set("visibleSecurityState", visibleSecurityState);
            return this;
        }
    }
    /**
     * The security state of the page changed. No longer being sent.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SecurityStateChangedEvent extends CdpObject {
        public SecurityStateChangedEvent() {}
        private SecurityStateChangedEvent(Map<String, Object> values) { super(values); }
        public static SecurityStateChangedEvent fromMap(Map<String, Object> values) {
            return new SecurityStateChangedEvent(values);
        }
        /**
         * Security state.
         * @return the protocol field value
         */
        public Security.SecurityState securityState() {
            return Security.SecurityState.of((String) require("securityState"));
        }
        /**
         * True if the page was loaded over cryptographic transport such as HTTPS.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public boolean schemeIsCryptographic() {
            return (Boolean) require("schemeIsCryptographic");
        }
        /**
         * Previously a list of explanations for the security state. Now always empty.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public java.util.List<Security.SecurityStateExplanation> explanations() {
            return CdpObject.requireList(require("explanations"), element0 -> java.util.Objects.requireNonNull(Security.SecurityStateExplanation.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Information about insecure content on the page.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Security.InsecureContentStatus insecureContentStatus() {
            return java.util.Objects.requireNonNull(Security.InsecureContentStatus.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("insecureContentStatus")))));
        }
        /**
         * Overrides user-visible description of the state. Always omitted.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> summary() {
            return Optional.ofNullable((String) raw("summary"));
        }
        /**
         * Security state.
         * @param securityState field value
         * @return this model
         */
        public SecurityStateChangedEvent securityState(Security.SecurityState securityState) {
            set("securityState", securityState);
            return this;
        }
        /**
         * True if the page was loaded over cryptographic transport such as HTTPS.
         * @param schemeIsCryptographic field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SecurityStateChangedEvent schemeIsCryptographic(boolean schemeIsCryptographic) {
            set("schemeIsCryptographic", schemeIsCryptographic);
            return this;
        }
        /**
         * Previously a list of explanations for the security state. Now always empty.
         * @param explanations field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SecurityStateChangedEvent explanations(java.util.List<Security.SecurityStateExplanation> explanations) {
            set("explanations", explanations);
            return this;
        }
        /**
         * Information about insecure content on the page.
         * @param insecureContentStatus field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SecurityStateChangedEvent insecureContentStatus(Security.InsecureContentStatus insecureContentStatus) {
            set("insecureContentStatus", insecureContentStatus);
            return this;
        }
        /**
         * Overrides user-visible description of the state. Always omitted.
         * @param summary field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SecurityStateChangedEvent summary(Optional<String> summary) {
            set("summary", summary.orElse(null));
            return this;
        }
        /**
         * Overrides user-visible description of the state. Always omitted.
         * @param summary field value; null removes the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SecurityStateChangedEvent summary(String summary) {
            set("summary", summary);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables tracking security state changes.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Security.disable", null, result_ -> null);
        }
        /**
         * Enables tracking security state changes.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Security.enable", null, result_ -> null);
        }
        /**
         * Enable/disable whether all certificate errors should be ignored.
         * @param ignore protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIgnoreCertificateErrors(boolean ignore) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ignore", CdpObject.json(ignore));
            return client.call("Security.setIgnoreCertificateErrors", params, result_ -> null);
        }
        /**
         * Handles a certificate error that fired a certificateError event.
         * @param eventId protocol value
         * @param action protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> handleCertificateError(long eventId, Security.CertificateErrorAction action) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventId", CdpObject.json(eventId));
            params.put("action", CdpObject.json(action));
            return client.call("Security.handleCertificateError", params, result_ -> null);
        }
        /**
         * Enable/disable overriding certificate errors. If enabled, all certificate error events need to be handled by the DevTools client and should be answered with {@code handleCertificateError} commands.
         * @param override protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setOverrideCertificateErrors(boolean override) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("override", CdpObject.json(override));
            return client.call("Security.setOverrideCertificateErrors", params, result_ -> null);
        }
        /**
         * There is a certificate error. If overriding certificate errors is enabled, then it should be handled with the {@code handleCertificateError} command. Note: this event does not fire if the certificate error has been allowed internally. Only one client per target should override certificate errors at the same time.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onCertificateError(Consumer<CertificateErrorEvent> handler) {
            return client.on("Security.certificateError", CertificateErrorEvent::fromMap, handler);
        }
        /**
         * The security state of the page changed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onVisibleSecurityStateChanged(Consumer<VisibleSecurityStateChangedEvent> handler) {
            return client.on("Security.visibleSecurityStateChanged", VisibleSecurityStateChangedEvent::fromMap, handler);
        }
        /**
         * The security state of the page changed. No longer being sent.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onSecurityStateChanged(Consumer<SecurityStateChangedEvent> handler) {
            return client.on("Security.securityStateChanged", SecurityStateChangedEvent::fromMap, handler);
        }
    }
}
