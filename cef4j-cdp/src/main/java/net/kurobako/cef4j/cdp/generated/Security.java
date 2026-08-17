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
 * Chrome DevTools Protocol Security domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Security.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Security {
    private Security() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * A description of mixed content (HTTP resources on HTTPS pages), as defined by https://www.w3.org/TR/mixed-content/#categories
     */
    public static final class MixedContentType {
        private MixedContentType() {}
        public static final String BLOCKABLE = "blockable";
        public static final String OPTIONALLY_BLOCKABLE = "optionally-blockable";
        public static final String NONE = "none";
    }
    /**
     * The security level of a page or resource.
     */
    public static final class SecurityState {
        private SecurityState() {}
        public static final String UNKNOWN = "unknown";
        public static final String NEUTRAL = "neutral";
        public static final String INSECURE = "insecure";
        public static final String SECURE = "secure";
        public static final String INFO = "info";
        public static final String INSECURE_BROKEN = "insecure-broken";
    }
    /**
     * Details about the security state of the page certificate.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CertificateSecurityState extends CdpObject {
        private CertificateSecurityState(Map<String, Object> values) { super(values); }
        @Nullable public static CertificateSecurityState fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CertificateSecurityState(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
         * @return the protocol field value
         */
        @Nullable public String protocol() {
            return (String) value("protocol");
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @return the protocol field value
         */
        @Nullable public String keyExchange() {
            return (String) value("keyExchange");
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @return the protocol field value
         */
        @Nullable public String keyExchangeGroup() {
            return (String) value("keyExchangeGroup");
        }
        /**
         * Cipher name.
         * @return the protocol field value
         */
        @Nullable public String cipher() {
            return (String) value("cipher");
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @return the protocol field value
         */
        @Nullable public String mac() {
            return (String) value("mac");
        }
        /**
         * Page certificate.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> certificate() {
            return list(value("certificate"), element0 -> (String) element0);
        }
        /**
         * Certificate subject name.
         * @return the protocol field value
         */
        @Nullable public String subjectName() {
            return (String) value("subjectName");
        }
        /**
         * Name of the issuing CA.
         * @return the protocol field value
         */
        @Nullable public String issuer() {
            return (String) value("issuer");
        }
        /**
         * Certificate valid from date.
         * @return the protocol field value
         */
        @Nullable public Double validFrom() {
            return numberAsDouble(value("validFrom"));
        }
        /**
         * Certificate valid to (expiration) date
         * @return the protocol field value
         */
        @Nullable public Double validTo() {
            return numberAsDouble(value("validTo"));
        }
        /**
         * The highest priority network error code, if the certificate has an error.
         * @return the protocol field value
         */
        @Nullable public String certificateNetworkError() {
            return (String) value("certificateNetworkError");
        }
        /**
         * True if the certificate uses a weak signature algorithm.
         * @return the protocol field value
         */
        @Nullable public Boolean certificateHasWeakSignature() {
            return (Boolean) value("certificateHasWeakSignature");
        }
        /**
         * True if the certificate has a SHA1 signature in the chain.
         * @return the protocol field value
         */
        @Nullable public Boolean certificateHasSha1Signature() {
            return (Boolean) value("certificateHasSha1Signature");
        }
        /**
         * True if modern SSL
         * @return the protocol field value
         */
        @Nullable public Boolean modernSSL() {
            return (Boolean) value("modernSSL");
        }
        /**
         * True if the connection is using an obsolete SSL protocol.
         * @return the protocol field value
         */
        @Nullable public Boolean obsoleteSslProtocol() {
            return (Boolean) value("obsoleteSslProtocol");
        }
        /**
         * True if the connection is using an obsolete SSL key exchange.
         * @return the protocol field value
         */
        @Nullable public Boolean obsoleteSslKeyExchange() {
            return (Boolean) value("obsoleteSslKeyExchange");
        }
        /**
         * True if the connection is using an obsolete SSL cipher.
         * @return the protocol field value
         */
        @Nullable public Boolean obsoleteSslCipher() {
            return (Boolean) value("obsoleteSslCipher");
        }
        /**
         * True if the connection is using an obsolete SSL signature.
         * @return the protocol field value
         */
        @Nullable public Boolean obsoleteSslSignature() {
            return (Boolean) value("obsoleteSslSignature");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocol(@Nullable String value) {
                if (value == null) values.remove("protocol");
                else values.put("protocol", jsonValue(value));
                return this;
            }
            /**
             * Key Exchange used by the connection, or the empty string if not applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyExchange(@Nullable String value) {
                if (value == null) values.remove("keyExchange");
                else values.put("keyExchange", jsonValue(value));
                return this;
            }
            /**
             * (EC)DH group used by the connection, if applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyExchangeGroup(@Nullable String value) {
                if (value == null) values.remove("keyExchangeGroup");
                else values.put("keyExchangeGroup", jsonValue(value));
                return this;
            }
            /**
             * Cipher name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cipher(@Nullable String value) {
                if (value == null) values.remove("cipher");
                else values.put("cipher", jsonValue(value));
                return this;
            }
            /**
             * TLS MAC. Note that AEAD ciphers do not have separate MACs.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mac(@Nullable String value) {
                if (value == null) values.remove("mac");
                else values.put("mac", jsonValue(value));
                return this;
            }
            /**
             * Page certificate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificate(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("certificate");
                else values.put("certificate", jsonValue(value));
                return this;
            }
            /**
             * Certificate subject name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subjectName(@Nullable String value) {
                if (value == null) values.remove("subjectName");
                else values.put("subjectName", jsonValue(value));
                return this;
            }
            /**
             * Name of the issuing CA.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuer(@Nullable String value) {
                if (value == null) values.remove("issuer");
                else values.put("issuer", jsonValue(value));
                return this;
            }
            /**
             * Certificate valid from date.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder validFrom(@Nullable Double value) {
                if (value == null) values.remove("validFrom");
                else values.put("validFrom", jsonValue(value));
                return this;
            }
            /**
             * Certificate valid to (expiration) date
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder validTo(@Nullable Double value) {
                if (value == null) values.remove("validTo");
                else values.put("validTo", jsonValue(value));
                return this;
            }
            /**
             * The highest priority network error code, if the certificate has an error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificateNetworkError(@Nullable String value) {
                if (value == null) values.remove("certificateNetworkError");
                else values.put("certificateNetworkError", jsonValue(value));
                return this;
            }
            /**
             * True if the certificate uses a weak signature algorithm.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificateHasWeakSignature(@Nullable Boolean value) {
                if (value == null) values.remove("certificateHasWeakSignature");
                else values.put("certificateHasWeakSignature", jsonValue(value));
                return this;
            }
            /**
             * True if the certificate has a SHA1 signature in the chain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificateHasSha1Signature(@Nullable Boolean value) {
                if (value == null) values.remove("certificateHasSha1Signature");
                else values.put("certificateHasSha1Signature", jsonValue(value));
                return this;
            }
            /**
             * True if modern SSL
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modernSSL(@Nullable Boolean value) {
                if (value == null) values.remove("modernSSL");
                else values.put("modernSSL", jsonValue(value));
                return this;
            }
            /**
             * True if the connection is using an obsolete SSL protocol.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder obsoleteSslProtocol(@Nullable Boolean value) {
                if (value == null) values.remove("obsoleteSslProtocol");
                else values.put("obsoleteSslProtocol", jsonValue(value));
                return this;
            }
            /**
             * True if the connection is using an obsolete SSL key exchange.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder obsoleteSslKeyExchange(@Nullable Boolean value) {
                if (value == null) values.remove("obsoleteSslKeyExchange");
                else values.put("obsoleteSslKeyExchange", jsonValue(value));
                return this;
            }
            /**
             * True if the connection is using an obsolete SSL cipher.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder obsoleteSslCipher(@Nullable Boolean value) {
                if (value == null) values.remove("obsoleteSslCipher");
                else values.put("obsoleteSslCipher", jsonValue(value));
                return this;
            }
            /**
             * True if the connection is using an obsolete SSL signature.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder obsoleteSslSignature(@Nullable Boolean value) {
                if (value == null) values.remove("obsoleteSslSignature");
                else values.put("obsoleteSslSignature", jsonValue(value));
                return this;
            }
            public CertificateSecurityState build() {
                if (!values.containsKey("protocol")) throw new IllegalStateException("Missing required CDP field: protocol");
                if (!values.containsKey("keyExchange")) throw new IllegalStateException("Missing required CDP field: keyExchange");
                if (!values.containsKey("cipher")) throw new IllegalStateException("Missing required CDP field: cipher");
                if (!values.containsKey("certificate")) throw new IllegalStateException("Missing required CDP field: certificate");
                if (!values.containsKey("subjectName")) throw new IllegalStateException("Missing required CDP field: subjectName");
                if (!values.containsKey("issuer")) throw new IllegalStateException("Missing required CDP field: issuer");
                if (!values.containsKey("validFrom")) throw new IllegalStateException("Missing required CDP field: validFrom");
                if (!values.containsKey("validTo")) throw new IllegalStateException("Missing required CDP field: validTo");
                if (!values.containsKey("certificateHasWeakSignature")) throw new IllegalStateException("Missing required CDP field: certificateHasWeakSignature");
                if (!values.containsKey("certificateHasSha1Signature")) throw new IllegalStateException("Missing required CDP field: certificateHasSha1Signature");
                if (!values.containsKey("modernSSL")) throw new IllegalStateException("Missing required CDP field: modernSSL");
                if (!values.containsKey("obsoleteSslProtocol")) throw new IllegalStateException("Missing required CDP field: obsoleteSslProtocol");
                if (!values.containsKey("obsoleteSslKeyExchange")) throw new IllegalStateException("Missing required CDP field: obsoleteSslKeyExchange");
                if (!values.containsKey("obsoleteSslCipher")) throw new IllegalStateException("Missing required CDP field: obsoleteSslCipher");
                if (!values.containsKey("obsoleteSslSignature")) throw new IllegalStateException("Missing required CDP field: obsoleteSslSignature");
                return new CertificateSecurityState(values);
            }
        }
    }
    /**
     * Wire values for SafetyTipStatus.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SafetyTipStatus {
        private SafetyTipStatus() {}
        public static final String BADREPUTATION = "badReputation";
        public static final String LOOKALIKE = "lookalike";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SafetyTipInfo extends CdpObject {
        private SafetyTipInfo(Map<String, Object> values) { super(values); }
        @Nullable public static SafetyTipInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SafetyTipInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Describes whether the page triggers any safety tips or reputation warnings. Default is unknown.
         * @return the protocol field value
         */
        @Nullable public String safetyTipStatus() {
            return (String) value("safetyTipStatus");
        }
        /**
         * The URL the safety tip suggested (&quot;Did you mean?&quot;). Only filled in for lookalike matches.
         * @return the protocol field value
         */
        @Nullable public String safeUrl() {
            return (String) value("safeUrl");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Describes whether the page triggers any safety tips or reputation warnings. Default is unknown.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder safetyTipStatus(@Nullable String value) {
                if (value == null) values.remove("safetyTipStatus");
                else values.put("safetyTipStatus", jsonValue(value));
                return this;
            }
            /**
             * The URL the safety tip suggested (&quot;Did you mean?&quot;). Only filled in for lookalike matches.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder safeUrl(@Nullable String value) {
                if (value == null) values.remove("safeUrl");
                else values.put("safeUrl", jsonValue(value));
                return this;
            }
            public SafetyTipInfo build() {
                if (!values.containsKey("safetyTipStatus")) throw new IllegalStateException("Missing required CDP field: safetyTipStatus");
                return new SafetyTipInfo(values);
            }
        }
    }
    /**
     * Security state information about the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VisibleSecurityState extends CdpObject {
        private VisibleSecurityState(Map<String, Object> values) { super(values); }
        @Nullable public static VisibleSecurityState fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VisibleSecurityState(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The security level of the page.
         * @return the protocol field value
         */
        @Nullable public String securityState() {
            return (String) value("securityState");
        }
        /**
         * Security state details about the page certificate.
         * @return the protocol field value
         */
        @Nullable public Security.CertificateSecurityState certificateSecurityState() {
            return Security.CertificateSecurityState.fromMap(objectMap(value("certificateSecurityState")));
        }
        /**
         * The type of Safety Tip triggered on the page. Note that this field will be set even if the Safety Tip UI was not actually shown.
         * @return the protocol field value
         */
        @Nullable public Security.SafetyTipInfo safetyTipInfo() {
            return Security.SafetyTipInfo.fromMap(objectMap(value("safetyTipInfo")));
        }
        /**
         * Array of security state issues ids.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> securityStateIssueIds() {
            return list(value("securityStateIssueIds"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The security level of the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityState(@Nullable String value) {
                if (value == null) values.remove("securityState");
                else values.put("securityState", jsonValue(value));
                return this;
            }
            /**
             * Security state details about the page certificate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificateSecurityState(@Nullable Security.CertificateSecurityState value) {
                if (value == null) values.remove("certificateSecurityState");
                else values.put("certificateSecurityState", jsonValue(value));
                return this;
            }
            /**
             * The type of Safety Tip triggered on the page. Note that this field will be set even if the Safety Tip UI was not actually shown.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder safetyTipInfo(@Nullable Security.SafetyTipInfo value) {
                if (value == null) values.remove("safetyTipInfo");
                else values.put("safetyTipInfo", jsonValue(value));
                return this;
            }
            /**
             * Array of security state issues ids.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityStateIssueIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("securityStateIssueIds");
                else values.put("securityStateIssueIds", jsonValue(value));
                return this;
            }
            public VisibleSecurityState build() {
                if (!values.containsKey("securityState")) throw new IllegalStateException("Missing required CDP field: securityState");
                if (!values.containsKey("securityStateIssueIds")) throw new IllegalStateException("Missing required CDP field: securityStateIssueIds");
                return new VisibleSecurityState(values);
            }
        }
    }
    /**
     * An explanation of an factor contributing to the security state.
     */
    public static final class SecurityStateExplanation extends CdpObject {
        private SecurityStateExplanation(Map<String, Object> values) { super(values); }
        @Nullable public static SecurityStateExplanation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SecurityStateExplanation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security state representing the severity of the factor being explained.
         * @return the protocol field value
         */
        @Nullable public String securityState() {
            return (String) value("securityState");
        }
        /**
         * Title describing the type of factor.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * Short phrase describing the type of factor.
         * @return the protocol field value
         */
        @Nullable public String summary() {
            return (String) value("summary");
        }
        /**
         * Full text explanation of the factor.
         * @return the protocol field value
         */
        @Nullable public String description() {
            return (String) value("description");
        }
        /**
         * The type of mixed content described by the explanation.
         * @return the protocol field value
         */
        @Nullable public String mixedContentType() {
            return (String) value("mixedContentType");
        }
        /**
         * Page certificate.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> certificate() {
            return list(value("certificate"), element0 -> (String) element0);
        }
        /**
         * Recommendations to fix any issues.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> recommendations() {
            return list(value("recommendations"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security state representing the severity of the factor being explained.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityState(@Nullable String value) {
                if (value == null) values.remove("securityState");
                else values.put("securityState", jsonValue(value));
                return this;
            }
            /**
             * Title describing the type of factor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Short phrase describing the type of factor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder summary(@Nullable String value) {
                if (value == null) values.remove("summary");
                else values.put("summary", jsonValue(value));
                return this;
            }
            /**
             * Full text explanation of the factor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable String value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * The type of mixed content described by the explanation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mixedContentType(@Nullable String value) {
                if (value == null) values.remove("mixedContentType");
                else values.put("mixedContentType", jsonValue(value));
                return this;
            }
            /**
             * Page certificate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificate(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("certificate");
                else values.put("certificate", jsonValue(value));
                return this;
            }
            /**
             * Recommendations to fix any issues.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder recommendations(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("recommendations");
                else values.put("recommendations", jsonValue(value));
                return this;
            }
            public SecurityStateExplanation build() {
                if (!values.containsKey("securityState")) throw new IllegalStateException("Missing required CDP field: securityState");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                if (!values.containsKey("summary")) throw new IllegalStateException("Missing required CDP field: summary");
                if (!values.containsKey("description")) throw new IllegalStateException("Missing required CDP field: description");
                if (!values.containsKey("mixedContentType")) throw new IllegalStateException("Missing required CDP field: mixedContentType");
                if (!values.containsKey("certificate")) throw new IllegalStateException("Missing required CDP field: certificate");
                return new SecurityStateExplanation(values);
            }
        }
    }
    /**
     * Information about insecure content on the page.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class InsecureContentStatus extends CdpObject {
        private InsecureContentStatus(Map<String, Object> values) { super(values); }
        @Nullable public static InsecureContentStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InsecureContentStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Always false.
         * @return the protocol field value
         */
        @Nullable public Boolean ranMixedContent() {
            return (Boolean) value("ranMixedContent");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        @Nullable public Boolean displayedMixedContent() {
            return (Boolean) value("displayedMixedContent");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        @Nullable public Boolean containedMixedForm() {
            return (Boolean) value("containedMixedForm");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        @Nullable public Boolean ranContentWithCertErrors() {
            return (Boolean) value("ranContentWithCertErrors");
        }
        /**
         * Always false.
         * @return the protocol field value
         */
        @Nullable public Boolean displayedContentWithCertErrors() {
            return (Boolean) value("displayedContentWithCertErrors");
        }
        /**
         * Always set to unknown.
         * @return the protocol field value
         */
        @Nullable public String ranInsecureContentStyle() {
            return (String) value("ranInsecureContentStyle");
        }
        /**
         * Always set to unknown.
         * @return the protocol field value
         */
        @Nullable public String displayedInsecureContentStyle() {
            return (String) value("displayedInsecureContentStyle");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Always false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ranMixedContent(@Nullable Boolean value) {
                if (value == null) values.remove("ranMixedContent");
                else values.put("ranMixedContent", jsonValue(value));
                return this;
            }
            /**
             * Always false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayedMixedContent(@Nullable Boolean value) {
                if (value == null) values.remove("displayedMixedContent");
                else values.put("displayedMixedContent", jsonValue(value));
                return this;
            }
            /**
             * Always false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containedMixedForm(@Nullable Boolean value) {
                if (value == null) values.remove("containedMixedForm");
                else values.put("containedMixedForm", jsonValue(value));
                return this;
            }
            /**
             * Always false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ranContentWithCertErrors(@Nullable Boolean value) {
                if (value == null) values.remove("ranContentWithCertErrors");
                else values.put("ranContentWithCertErrors", jsonValue(value));
                return this;
            }
            /**
             * Always false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayedContentWithCertErrors(@Nullable Boolean value) {
                if (value == null) values.remove("displayedContentWithCertErrors");
                else values.put("displayedContentWithCertErrors", jsonValue(value));
                return this;
            }
            /**
             * Always set to unknown.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ranInsecureContentStyle(@Nullable String value) {
                if (value == null) values.remove("ranInsecureContentStyle");
                else values.put("ranInsecureContentStyle", jsonValue(value));
                return this;
            }
            /**
             * Always set to unknown.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder displayedInsecureContentStyle(@Nullable String value) {
                if (value == null) values.remove("displayedInsecureContentStyle");
                else values.put("displayedInsecureContentStyle", jsonValue(value));
                return this;
            }
            public InsecureContentStatus build() {
                if (!values.containsKey("ranMixedContent")) throw new IllegalStateException("Missing required CDP field: ranMixedContent");
                if (!values.containsKey("displayedMixedContent")) throw new IllegalStateException("Missing required CDP field: displayedMixedContent");
                if (!values.containsKey("containedMixedForm")) throw new IllegalStateException("Missing required CDP field: containedMixedForm");
                if (!values.containsKey("ranContentWithCertErrors")) throw new IllegalStateException("Missing required CDP field: ranContentWithCertErrors");
                if (!values.containsKey("displayedContentWithCertErrors")) throw new IllegalStateException("Missing required CDP field: displayedContentWithCertErrors");
                if (!values.containsKey("ranInsecureContentStyle")) throw new IllegalStateException("Missing required CDP field: ranInsecureContentStyle");
                if (!values.containsKey("displayedInsecureContentStyle")) throw new IllegalStateException("Missing required CDP field: displayedInsecureContentStyle");
                return new InsecureContentStatus(values);
            }
        }
    }
    /**
     * The action to take when a certificate error occurs. continue will continue processing the request and cancel will cancel the request.
     */
    public static final class CertificateErrorAction {
        private CertificateErrorAction() {}
        public static final String CONTINUE = "continue";
        public static final String CANCEL = "cancel";
    }
    /**
     * Disables tracking security state changes.
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
     * Disables tracking security state changes.
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
     * Enables tracking security state changes.
     */
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
     * Enables tracking security state changes.
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
     * Enable/disable whether all certificate errors should be ignored.
     */
    public static final class SetIgnoreCertificateErrorsParams extends CdpObject {
        private SetIgnoreCertificateErrorsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetIgnoreCertificateErrorsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetIgnoreCertificateErrorsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true, all certificate errors will be ignored.
         * @return the protocol field value
         */
        @Nullable public Boolean ignore() {
            return (Boolean) value("ignore");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true, all certificate errors will be ignored.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignore(@Nullable Boolean value) {
                if (value == null) values.remove("ignore");
                else values.put("ignore", jsonValue(value));
                return this;
            }
            public SetIgnoreCertificateErrorsParams build() {
                if (!values.containsKey("ignore")) throw new IllegalStateException("Missing required CDP field: ignore");
                return new SetIgnoreCertificateErrorsParams(values);
            }
        }
    }
    /**
     * Enable/disable whether all certificate errors should be ignored.
     */
    public static final class SetIgnoreCertificateErrorsResult extends CdpObject {
        private SetIgnoreCertificateErrorsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetIgnoreCertificateErrorsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetIgnoreCertificateErrorsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetIgnoreCertificateErrorsResult build() {
                return new SetIgnoreCertificateErrorsResult(values);
            }
        }
    }
    /**
     * Handles a certificate error that fired a certificateError event.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class HandleCertificateErrorParams extends CdpObject {
        private HandleCertificateErrorParams(Map<String, Object> values) { super(values); }
        @Nullable public static HandleCertificateErrorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HandleCertificateErrorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The ID of the event.
         * @return the protocol field value
         */
        @Nullable public Long eventId() {
            return numberAsLong(value("eventId"));
        }
        /**
         * The action to take on the certificate error.
         * @return the protocol field value
         */
        @Nullable public String action() {
            return (String) value("action");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The ID of the event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventId(@Nullable Long value) {
                if (value == null) values.remove("eventId");
                else values.put("eventId", jsonValue(value));
                return this;
            }
            /**
             * The action to take on the certificate error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder action(@Nullable String value) {
                if (value == null) values.remove("action");
                else values.put("action", jsonValue(value));
                return this;
            }
            public HandleCertificateErrorParams build() {
                if (!values.containsKey("eventId")) throw new IllegalStateException("Missing required CDP field: eventId");
                if (!values.containsKey("action")) throw new IllegalStateException("Missing required CDP field: action");
                return new HandleCertificateErrorParams(values);
            }
        }
    }
    /**
     * Handles a certificate error that fired a certificateError event.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class HandleCertificateErrorResult extends CdpObject {
        private HandleCertificateErrorResult(Map<String, Object> values) { super(values); }
        @Nullable public static HandleCertificateErrorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HandleCertificateErrorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HandleCertificateErrorResult build() {
                return new HandleCertificateErrorResult(values);
            }
        }
    }
    /**
     * Enable/disable overriding certificate errors. If enabled, all certificate error events need to be handled by the DevTools client and should be answered with {@code handleCertificateError} commands.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetOverrideCertificateErrorsParams extends CdpObject {
        private SetOverrideCertificateErrorsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetOverrideCertificateErrorsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetOverrideCertificateErrorsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true, certificate errors will be overridden.
         * @return the protocol field value
         */
        @Nullable public Boolean override() {
            return (Boolean) value("override");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true, certificate errors will be overridden.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder override(@Nullable Boolean value) {
                if (value == null) values.remove("override");
                else values.put("override", jsonValue(value));
                return this;
            }
            public SetOverrideCertificateErrorsParams build() {
                if (!values.containsKey("override")) throw new IllegalStateException("Missing required CDP field: override");
                return new SetOverrideCertificateErrorsParams(values);
            }
        }
    }
    /**
     * Enable/disable overriding certificate errors. If enabled, all certificate error events need to be handled by the DevTools client and should be answered with {@code handleCertificateError} commands.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetOverrideCertificateErrorsResult extends CdpObject {
        private SetOverrideCertificateErrorsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetOverrideCertificateErrorsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetOverrideCertificateErrorsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetOverrideCertificateErrorsResult build() {
                return new SetOverrideCertificateErrorsResult(values);
            }
        }
    }
    /**
     * There is a certificate error. If overriding certificate errors is enabled, then it should be handled with the {@code handleCertificateError} command. Note: this event does not fire if the certificate error has been allowed internally. Only one client per target should override certificate errors at the same time.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CertificateErrorEvent extends CdpObject {
        private CertificateErrorEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CertificateErrorEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CertificateErrorEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The ID of the event.
         * @return the protocol field value
         */
        @Nullable public Long eventId() {
            return numberAsLong(value("eventId"));
        }
        /**
         * The type of the error.
         * @return the protocol field value
         */
        @Nullable public String errorType() {
            return (String) value("errorType");
        }
        /**
         * The url that was requested.
         * @return the protocol field value
         */
        @Nullable public String requestURL() {
            return (String) value("requestURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The ID of the event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventId(@Nullable Long value) {
                if (value == null) values.remove("eventId");
                else values.put("eventId", jsonValue(value));
                return this;
            }
            /**
             * The type of the error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorType(@Nullable String value) {
                if (value == null) values.remove("errorType");
                else values.put("errorType", jsonValue(value));
                return this;
            }
            /**
             * The url that was requested.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestURL(@Nullable String value) {
                if (value == null) values.remove("requestURL");
                else values.put("requestURL", jsonValue(value));
                return this;
            }
            public CertificateErrorEvent build() {
                if (!values.containsKey("eventId")) throw new IllegalStateException("Missing required CDP field: eventId");
                if (!values.containsKey("errorType")) throw new IllegalStateException("Missing required CDP field: errorType");
                if (!values.containsKey("requestURL")) throw new IllegalStateException("Missing required CDP field: requestURL");
                return new CertificateErrorEvent(values);
            }
        }
    }
    /**
     * The security state of the page changed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class VisibleSecurityStateChangedEvent extends CdpObject {
        private VisibleSecurityStateChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static VisibleSecurityStateChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VisibleSecurityStateChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security state information about the page.
         * @return the protocol field value
         */
        @Nullable public Security.VisibleSecurityState visibleSecurityState() {
            return Security.VisibleSecurityState.fromMap(objectMap(value("visibleSecurityState")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security state information about the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder visibleSecurityState(@Nullable Security.VisibleSecurityState value) {
                if (value == null) values.remove("visibleSecurityState");
                else values.put("visibleSecurityState", jsonValue(value));
                return this;
            }
            public VisibleSecurityStateChangedEvent build() {
                if (!values.containsKey("visibleSecurityState")) throw new IllegalStateException("Missing required CDP field: visibleSecurityState");
                return new VisibleSecurityStateChangedEvent(values);
            }
        }
    }
    /**
     * The security state of the page changed. No longer being sent.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SecurityStateChangedEvent extends CdpObject {
        private SecurityStateChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SecurityStateChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SecurityStateChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Security state.
         * @return the protocol field value
         */
        @Nullable public String securityState() {
            return (String) value("securityState");
        }
        /**
         * True if the page was loaded over cryptographic transport such as HTTPS.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean schemeIsCryptographic() {
            return (Boolean) value("schemeIsCryptographic");
        }
        /**
         * Previously a list of explanations for the security state. Now always empty.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public java.util.List<Security.SecurityStateExplanation> explanations() {
            return list(value("explanations"), element0 -> Security.SecurityStateExplanation.fromMap(objectMap(element0)));
        }
        /**
         * Information about insecure content on the page.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Security.InsecureContentStatus insecureContentStatus() {
            return Security.InsecureContentStatus.fromMap(objectMap(value("insecureContentStatus")));
        }
        /**
         * Overrides user-visible description of the state. Always omitted.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String summary() {
            return (String) value("summary");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Security state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityState(@Nullable String value) {
                if (value == null) values.remove("securityState");
                else values.put("securityState", jsonValue(value));
                return this;
            }
            /**
             * True if the page was loaded over cryptographic transport such as HTTPS.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder schemeIsCryptographic(@Nullable Boolean value) {
                if (value == null) values.remove("schemeIsCryptographic");
                else values.put("schemeIsCryptographic", jsonValue(value));
                return this;
            }
            /**
             * Previously a list of explanations for the security state. Now always empty.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder explanations(@Nullable java.util.List<Security.SecurityStateExplanation> value) {
                if (value == null) values.remove("explanations");
                else values.put("explanations", jsonValue(value));
                return this;
            }
            /**
             * Information about insecure content on the page.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder insecureContentStatus(@Nullable Security.InsecureContentStatus value) {
                if (value == null) values.remove("insecureContentStatus");
                else values.put("insecureContentStatus", jsonValue(value));
                return this;
            }
            /**
             * Overrides user-visible description of the state. Always omitted.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder summary(@Nullable String value) {
                if (value == null) values.remove("summary");
                else values.put("summary", jsonValue(value));
                return this;
            }
            public SecurityStateChangedEvent build() {
                if (!values.containsKey("securityState")) throw new IllegalStateException("Missing required CDP field: securityState");
                if (!values.containsKey("schemeIsCryptographic")) throw new IllegalStateException("Missing required CDP field: schemeIsCryptographic");
                if (!values.containsKey("explanations")) throw new IllegalStateException("Missing required CDP field: explanations");
                if (!values.containsKey("insecureContentStatus")) throw new IllegalStateException("Missing required CDP field: insecureContentStatus");
                return new SecurityStateChangedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables tracking security state changes.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Security.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables tracking security state changes.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Security.enable", null, EnableResult::fromMap);
        }
        /**
         * Enable/disable whether all certificate errors should be ignored.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetIgnoreCertificateErrorsResult> setIgnoreCertificateErrors(SetIgnoreCertificateErrorsParams params) {
            return client.call("Security.setIgnoreCertificateErrors", params, SetIgnoreCertificateErrorsResult::fromMap);
        }
        /**
         * Handles a certificate error that fired a certificateError event.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<HandleCertificateErrorResult> handleCertificateError(HandleCertificateErrorParams params) {
            return client.call("Security.handleCertificateError", params, HandleCertificateErrorResult::fromMap);
        }
        /**
         * Enable/disable overriding certificate errors. If enabled, all certificate error events need to be handled by the DevTools client and should be answered with {@code handleCertificateError} commands.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetOverrideCertificateErrorsResult> setOverrideCertificateErrors(SetOverrideCertificateErrorsParams params) {
            return client.call("Security.setOverrideCertificateErrors", params, SetOverrideCertificateErrorsResult::fromMap);
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
