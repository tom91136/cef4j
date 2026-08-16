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
 * This domain allows configuring virtual authenticators to test the WebAuthn API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/WebAuthn.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class WebAuthn {
    private WebAuthn() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Wire values for AuthenticatorProtocol.
     */
    public static final class AuthenticatorProtocol {
        private AuthenticatorProtocol() {}
        public static final String U2F = "u2f";
        public static final String CTAP2 = "ctap2";
    }
    /**
     * Wire values for Ctap2Version.
     */
    public static final class Ctap2Version {
        private Ctap2Version() {}
        public static final String CTAP2_0 = "ctap2_0";
        public static final String CTAP2_1 = "ctap2_1";
    }
    /**
     * Wire values for AuthenticatorTransport.
     */
    public static final class AuthenticatorTransport {
        private AuthenticatorTransport() {}
        public static final String USB = "usb";
        public static final String NFC = "nfc";
        public static final String BLE = "ble";
        public static final String CABLE = "cable";
        public static final String INTERNAL = "internal";
    }
    /**
     */
    public static final class VirtualAuthenticatorOptions extends CdpObject {
        private VirtualAuthenticatorOptions(Map<String, Object> values) { super(values); }
        @Nullable public static VirtualAuthenticatorOptions fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new VirtualAuthenticatorOptions(values);
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
         * Defaults to ctap2_0. Ignored if |protocol| == u2f.
         * @return the protocol field value
         */
        @Nullable public String ctap2Version() {
            return (String) value("ctap2Version");
        }
        /**
         * Returns the transport field.
         * @return the protocol field value
         */
        @Nullable public String transport() {
            return (String) value("transport");
        }
        /**
         * Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasResidentKey() {
            return (Boolean) value("hasResidentKey");
        }
        /**
         * Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasUserVerification() {
            return (Boolean) value("hasUserVerification");
        }
        /**
         * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasLargeBlob() {
            return (Boolean) value("hasLargeBlob");
        }
        /**
         * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasCredBlob() {
            return (Boolean) value("hasCredBlob");
        }
        /**
         * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasMinPinLength() {
            return (Boolean) value("hasMinPinLength");
        }
        /**
         * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasPrf() {
            return (Boolean) value("hasPrf");
        }
        /**
         * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
         * @return the protocol field value
         */
        @Nullable public Boolean automaticPresenceSimulation() {
            return (Boolean) value("automaticPresenceSimulation");
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean isUserVerified() {
            return (Boolean) value("isUserVerified");
        }
        /**
         * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @return the protocol field value
         */
        @Nullable public Boolean defaultBackupEligibility() {
            return (Boolean) value("defaultBackupEligibility");
        }
        /**
         * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @return the protocol field value
         */
        @Nullable public Boolean defaultBackupState() {
            return (Boolean) value("defaultBackupState");
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
             * Defaults to ctap2_0. Ignored if |protocol| == u2f.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ctap2Version(@Nullable String value) {
                if (value == null) values.remove("ctap2Version");
                else values.put("ctap2Version", jsonValue(value));
                return this;
            }
            /**
             * Sets the transport field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transport(@Nullable String value) {
                if (value == null) values.remove("transport");
                else values.put("transport", jsonValue(value));
                return this;
            }
            /**
             * Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasResidentKey(@Nullable Boolean value) {
                if (value == null) values.remove("hasResidentKey");
                else values.put("hasResidentKey", jsonValue(value));
                return this;
            }
            /**
             * Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasUserVerification(@Nullable Boolean value) {
                if (value == null) values.remove("hasUserVerification");
                else values.put("hasUserVerification", jsonValue(value));
                return this;
            }
            /**
             * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasLargeBlob(@Nullable Boolean value) {
                if (value == null) values.remove("hasLargeBlob");
                else values.put("hasLargeBlob", jsonValue(value));
                return this;
            }
            /**
             * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasCredBlob(@Nullable Boolean value) {
                if (value == null) values.remove("hasCredBlob");
                else values.put("hasCredBlob", jsonValue(value));
                return this;
            }
            /**
             * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasMinPinLength(@Nullable Boolean value) {
                if (value == null) values.remove("hasMinPinLength");
                else values.put("hasMinPinLength", jsonValue(value));
                return this;
            }
            /**
             * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasPrf(@Nullable Boolean value) {
                if (value == null) values.remove("hasPrf");
                else values.put("hasPrf", jsonValue(value));
                return this;
            }
            /**
             * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder automaticPresenceSimulation(@Nullable Boolean value) {
                if (value == null) values.remove("automaticPresenceSimulation");
                else values.put("automaticPresenceSimulation", jsonValue(value));
                return this;
            }
            /**
             * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isUserVerified(@Nullable Boolean value) {
                if (value == null) values.remove("isUserVerified");
                else values.put("isUserVerified", jsonValue(value));
                return this;
            }
            /**
             * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder defaultBackupEligibility(@Nullable Boolean value) {
                if (value == null) values.remove("defaultBackupEligibility");
                else values.put("defaultBackupEligibility", jsonValue(value));
                return this;
            }
            /**
             * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder defaultBackupState(@Nullable Boolean value) {
                if (value == null) values.remove("defaultBackupState");
                else values.put("defaultBackupState", jsonValue(value));
                return this;
            }
            public VirtualAuthenticatorOptions build() {
                if (!values.containsKey("protocol")) throw new IllegalStateException("Missing required CDP field: protocol");
                if (!values.containsKey("transport")) throw new IllegalStateException("Missing required CDP field: transport");
                return new VirtualAuthenticatorOptions(values);
            }
        }
    }
    /**
     */
    public static final class Credential extends CdpObject {
        private Credential(Map<String, Object> values) { super(values); }
        @Nullable public static Credential fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Credential(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        @Nullable public String credentialId() {
            return (String) value("credentialId");
        }
        /**
         * Returns the isResidentCredential field.
         * @return the protocol field value
         */
        @Nullable public Boolean isResidentCredential() {
            return (Boolean) value("isResidentCredential");
        }
        /**
         * Relying Party ID the credential is scoped to. Must be set when adding a credential.
         * @return the protocol field value
         */
        @Nullable public String rpId() {
            return (String) value("rpId");
        }
        /**
         * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String privateKey() {
            return (String) value("privateKey");
        }
        /**
         * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific user. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String userHandle() {
            return (String) value("userHandle");
        }
        /**
         * Signature counter. This is incremented by one for each successful assertion. See https://w3c.github.io/webauthn/#signature-counter
         * @return the protocol field value
         */
        @Nullable public Long signCount() {
            return numberAsLong(value("signCount"));
        }
        /**
         * The large blob associated with the credential. See https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String largeBlob() {
            return (String) value("largeBlob");
        }
        /**
         * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
         * @return the protocol field value
         */
        @Nullable public Boolean backupEligibility() {
            return (Boolean) value("backupEligibility");
        }
        /**
         * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
         * @return the protocol field value
         */
        @Nullable public Boolean backupState() {
            return (Boolean) value("backupState");
        }
        /**
         * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
         * @return the protocol field value
         */
        @Nullable public String userName() {
            return (String) value("userName");
        }
        /**
         * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
         * @return the protocol field value
         */
        @Nullable public String userDisplayName() {
            return (String) value("userDisplayName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the credentialId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credentialId(@Nullable String value) {
                if (value == null) values.remove("credentialId");
                else values.put("credentialId", jsonValue(value));
                return this;
            }
            /**
             * Sets the isResidentCredential field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isResidentCredential(@Nullable Boolean value) {
                if (value == null) values.remove("isResidentCredential");
                else values.put("isResidentCredential", jsonValue(value));
                return this;
            }
            /**
             * Relying Party ID the credential is scoped to. Must be set when adding a credential.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rpId(@Nullable String value) {
                if (value == null) values.remove("rpId");
                else values.put("rpId", jsonValue(value));
                return this;
            }
            /**
             * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder privateKey(@Nullable String value) {
                if (value == null) values.remove("privateKey");
                else values.put("privateKey", jsonValue(value));
                return this;
            }
            /**
             * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific user. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userHandle(@Nullable String value) {
                if (value == null) values.remove("userHandle");
                else values.put("userHandle", jsonValue(value));
                return this;
            }
            /**
             * Signature counter. This is incremented by one for each successful assertion. See https://w3c.github.io/webauthn/#signature-counter
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signCount(@Nullable Long value) {
                if (value == null) values.remove("signCount");
                else values.put("signCount", jsonValue(value));
                return this;
            }
            /**
             * The large blob associated with the credential. See https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder largeBlob(@Nullable String value) {
                if (value == null) values.remove("largeBlob");
                else values.put("largeBlob", jsonValue(value));
                return this;
            }
            /**
             * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backupEligibility(@Nullable Boolean value) {
                if (value == null) values.remove("backupEligibility");
                else values.put("backupEligibility", jsonValue(value));
                return this;
            }
            /**
             * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backupState(@Nullable Boolean value) {
                if (value == null) values.remove("backupState");
                else values.put("backupState", jsonValue(value));
                return this;
            }
            /**
             * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userName(@Nullable String value) {
                if (value == null) values.remove("userName");
                else values.put("userName", jsonValue(value));
                return this;
            }
            /**
             * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userDisplayName(@Nullable String value) {
                if (value == null) values.remove("userDisplayName");
                else values.put("userDisplayName", jsonValue(value));
                return this;
            }
            public Credential build() {
                if (!values.containsKey("credentialId")) throw new IllegalStateException("Missing required CDP field: credentialId");
                if (!values.containsKey("isResidentCredential")) throw new IllegalStateException("Missing required CDP field: isResidentCredential");
                if (!values.containsKey("privateKey")) throw new IllegalStateException("Missing required CDP field: privateKey");
                if (!values.containsKey("signCount")) throw new IllegalStateException("Missing required CDP field: signCount");
                return new Credential(values);
            }
        }
    }
    /**
     * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to enable the WebAuthn user interface. Enabling the UI is recommended for debugging and demo purposes, as it is closer to the real experience. Disabling the UI is recommended for automated testing. Supported at the embedder&#x27;s discretion if UI is available. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean enableUI() {
            return (Boolean) value("enableUI");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to enable the WebAuthn user interface. Enabling the UI is recommended for debugging and demo purposes, as it is closer to the real experience. Disabling the UI is recommended for automated testing. Supported at the embedder&#x27;s discretion if UI is available. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableUI(@Nullable Boolean value) {
                if (value == null) values.remove("enableUI");
                else values.put("enableUI", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
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
     * Disable the WebAuthn domain.
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
     * Disable the WebAuthn domain.
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
     * Creates and adds a virtual authenticator.
     */
    public static final class AddVirtualAuthenticatorParams extends CdpObject {
        private AddVirtualAuthenticatorParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddVirtualAuthenticatorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddVirtualAuthenticatorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        @Nullable public WebAuthn.VirtualAuthenticatorOptions options() {
            return WebAuthn.VirtualAuthenticatorOptions.fromMap(objectMap(value("options")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the options field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder options(@Nullable WebAuthn.VirtualAuthenticatorOptions value) {
                if (value == null) values.remove("options");
                else values.put("options", jsonValue(value));
                return this;
            }
            public AddVirtualAuthenticatorParams build() {
                if (!values.containsKey("options")) throw new IllegalStateException("Missing required CDP field: options");
                return new AddVirtualAuthenticatorParams(values);
            }
        }
    }
    /**
     * Creates and adds a virtual authenticator.
     */
    public static final class AddVirtualAuthenticatorResult extends CdpObject {
        private AddVirtualAuthenticatorResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddVirtualAuthenticatorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddVirtualAuthenticatorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            public AddVirtualAuthenticatorResult build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                return new AddVirtualAuthenticatorResult(values);
            }
        }
    }
    /**
     * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
     */
    public static final class SetResponseOverrideBitsParams extends CdpObject {
        private SetResponseOverrideBitsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetResponseOverrideBitsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetResponseOverrideBitsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * If isBogusSignature is set, overrides the signature in the authenticator response to be zero. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean isBogusSignature() {
            return (Boolean) value("isBogusSignature");
        }
        /**
         * If isBadUV is set, overrides the UV bit in the flags in the authenticator response to be zero. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean isBadUV() {
            return (Boolean) value("isBadUV");
        }
        /**
         * If isBadUP is set, overrides the UP bit in the flags in the authenticator response to be zero. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean isBadUP() {
            return (Boolean) value("isBadUP");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * If isBogusSignature is set, overrides the signature in the authenticator response to be zero. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isBogusSignature(@Nullable Boolean value) {
                if (value == null) values.remove("isBogusSignature");
                else values.put("isBogusSignature", jsonValue(value));
                return this;
            }
            /**
             * If isBadUV is set, overrides the UV bit in the flags in the authenticator response to be zero. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isBadUV(@Nullable Boolean value) {
                if (value == null) values.remove("isBadUV");
                else values.put("isBadUV", jsonValue(value));
                return this;
            }
            /**
             * If isBadUP is set, overrides the UP bit in the flags in the authenticator response to be zero. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isBadUP(@Nullable Boolean value) {
                if (value == null) values.remove("isBadUP");
                else values.put("isBadUP", jsonValue(value));
                return this;
            }
            public SetResponseOverrideBitsParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                return new SetResponseOverrideBitsParams(values);
            }
        }
    }
    /**
     * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
     */
    public static final class SetResponseOverrideBitsResult extends CdpObject {
        private SetResponseOverrideBitsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetResponseOverrideBitsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetResponseOverrideBitsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetResponseOverrideBitsResult build() {
                return new SetResponseOverrideBitsResult(values);
            }
        }
    }
    /**
     * Removes the given authenticator.
     */
    public static final class RemoveVirtualAuthenticatorParams extends CdpObject {
        private RemoveVirtualAuthenticatorParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveVirtualAuthenticatorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveVirtualAuthenticatorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            public RemoveVirtualAuthenticatorParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                return new RemoveVirtualAuthenticatorParams(values);
            }
        }
    }
    /**
     * Removes the given authenticator.
     */
    public static final class RemoveVirtualAuthenticatorResult extends CdpObject {
        private RemoveVirtualAuthenticatorResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveVirtualAuthenticatorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveVirtualAuthenticatorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveVirtualAuthenticatorResult build() {
                return new RemoveVirtualAuthenticatorResult(values);
            }
        }
    }
    /**
     * Adds the credential to the specified authenticator.
     */
    public static final class AddCredentialParams extends CdpObject {
        private AddCredentialParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddCredentialParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddCredentialParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        @Nullable public WebAuthn.Credential credential() {
            return WebAuthn.Credential.fromMap(objectMap(value("credential")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credential field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credential(@Nullable WebAuthn.Credential value) {
                if (value == null) values.remove("credential");
                else values.put("credential", jsonValue(value));
                return this;
            }
            public AddCredentialParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credential")) throw new IllegalStateException("Missing required CDP field: credential");
                return new AddCredentialParams(values);
            }
        }
    }
    /**
     * Adds the credential to the specified authenticator.
     */
    public static final class AddCredentialResult extends CdpObject {
        private AddCredentialResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddCredentialResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddCredentialResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AddCredentialResult build() {
                return new AddCredentialResult(values);
            }
        }
    }
    /**
     * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
     */
    public static final class GetCredentialParams extends CdpObject {
        private GetCredentialParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCredentialParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCredentialParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        @Nullable public String credentialId() {
            return (String) value("credentialId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credentialId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credentialId(@Nullable String value) {
                if (value == null) values.remove("credentialId");
                else values.put("credentialId", jsonValue(value));
                return this;
            }
            public GetCredentialParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credentialId")) throw new IllegalStateException("Missing required CDP field: credentialId");
                return new GetCredentialParams(values);
            }
        }
    }
    /**
     * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
     */
    public static final class GetCredentialResult extends CdpObject {
        private GetCredentialResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetCredentialResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCredentialResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        @Nullable public WebAuthn.Credential credential() {
            return WebAuthn.Credential.fromMap(objectMap(value("credential")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the credential field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credential(@Nullable WebAuthn.Credential value) {
                if (value == null) values.remove("credential");
                else values.put("credential", jsonValue(value));
                return this;
            }
            public GetCredentialResult build() {
                if (!values.containsKey("credential")) throw new IllegalStateException("Missing required CDP field: credential");
                return new GetCredentialResult(values);
            }
        }
    }
    /**
     * Returns all the credentials stored in the given virtual authenticator.
     */
    public static final class GetCredentialsParams extends CdpObject {
        private GetCredentialsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCredentialsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCredentialsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            public GetCredentialsParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                return new GetCredentialsParams(values);
            }
        }
    }
    /**
     * Returns all the credentials stored in the given virtual authenticator.
     */
    public static final class GetCredentialsResult extends CdpObject {
        private GetCredentialsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetCredentialsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCredentialsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the credentials field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<WebAuthn.Credential> credentials() {
            return list(value("credentials"), element0 -> WebAuthn.Credential.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the credentials field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credentials(@Nullable java.util.List<WebAuthn.Credential> value) {
                if (value == null) values.remove("credentials");
                else values.put("credentials", jsonValue(value));
                return this;
            }
            public GetCredentialsResult build() {
                if (!values.containsKey("credentials")) throw new IllegalStateException("Missing required CDP field: credentials");
                return new GetCredentialsResult(values);
            }
        }
    }
    /**
     * Removes a credential from the authenticator.
     */
    public static final class RemoveCredentialParams extends CdpObject {
        private RemoveCredentialParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveCredentialParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveCredentialParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        @Nullable public String credentialId() {
            return (String) value("credentialId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credentialId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credentialId(@Nullable String value) {
                if (value == null) values.remove("credentialId");
                else values.put("credentialId", jsonValue(value));
                return this;
            }
            public RemoveCredentialParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credentialId")) throw new IllegalStateException("Missing required CDP field: credentialId");
                return new RemoveCredentialParams(values);
            }
        }
    }
    /**
     * Removes a credential from the authenticator.
     */
    public static final class RemoveCredentialResult extends CdpObject {
        private RemoveCredentialResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveCredentialResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveCredentialResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveCredentialResult build() {
                return new RemoveCredentialResult(values);
            }
        }
    }
    /**
     * Clears all the credentials from the specified device.
     */
    public static final class ClearCredentialsParams extends CdpObject {
        private ClearCredentialsParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearCredentialsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearCredentialsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            public ClearCredentialsParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                return new ClearCredentialsParams(values);
            }
        }
    }
    /**
     * Clears all the credentials from the specified device.
     */
    public static final class ClearCredentialsResult extends CdpObject {
        private ClearCredentialsResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearCredentialsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearCredentialsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearCredentialsResult build() {
                return new ClearCredentialsResult(values);
            }
        }
    }
    /**
     * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
     */
    public static final class SetUserVerifiedParams extends CdpObject {
        private SetUserVerifiedParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetUserVerifiedParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetUserVerifiedParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the isUserVerified field.
         * @return the protocol field value
         */
        @Nullable public Boolean isUserVerified() {
            return (Boolean) value("isUserVerified");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the isUserVerified field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isUserVerified(@Nullable Boolean value) {
                if (value == null) values.remove("isUserVerified");
                else values.put("isUserVerified", jsonValue(value));
                return this;
            }
            public SetUserVerifiedParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("isUserVerified")) throw new IllegalStateException("Missing required CDP field: isUserVerified");
                return new SetUserVerifiedParams(values);
            }
        }
    }
    /**
     * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
     */
    public static final class SetUserVerifiedResult extends CdpObject {
        private SetUserVerifiedResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetUserVerifiedResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetUserVerifiedResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetUserVerifiedResult build() {
                return new SetUserVerifiedResult(values);
            }
        }
    }
    /**
     * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
     */
    public static final class SetAutomaticPresenceSimulationParams extends CdpObject {
        private SetAutomaticPresenceSimulationParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutomaticPresenceSimulationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutomaticPresenceSimulationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
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
            public SetAutomaticPresenceSimulationParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetAutomaticPresenceSimulationParams(values);
            }
        }
    }
    /**
     * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
     */
    public static final class SetAutomaticPresenceSimulationResult extends CdpObject {
        private SetAutomaticPresenceSimulationResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutomaticPresenceSimulationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutomaticPresenceSimulationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAutomaticPresenceSimulationResult build() {
                return new SetAutomaticPresenceSimulationResult(values);
            }
        }
    }
    /**
     * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
     */
    public static final class SetCredentialPropertiesParams extends CdpObject {
        private SetCredentialPropertiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCredentialPropertiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCredentialPropertiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        @Nullable public String credentialId() {
            return (String) value("credentialId");
        }
        /**
         * Returns the backupEligibility field.
         * @return the protocol field value
         */
        @Nullable public Boolean backupEligibility() {
            return (Boolean) value("backupEligibility");
        }
        /**
         * Returns the backupState field.
         * @return the protocol field value
         */
        @Nullable public Boolean backupState() {
            return (Boolean) value("backupState");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credentialId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credentialId(@Nullable String value) {
                if (value == null) values.remove("credentialId");
                else values.put("credentialId", jsonValue(value));
                return this;
            }
            /**
             * Sets the backupEligibility field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backupEligibility(@Nullable Boolean value) {
                if (value == null) values.remove("backupEligibility");
                else values.put("backupEligibility", jsonValue(value));
                return this;
            }
            /**
             * Sets the backupState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backupState(@Nullable Boolean value) {
                if (value == null) values.remove("backupState");
                else values.put("backupState", jsonValue(value));
                return this;
            }
            public SetCredentialPropertiesParams build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credentialId")) throw new IllegalStateException("Missing required CDP field: credentialId");
                return new SetCredentialPropertiesParams(values);
            }
        }
    }
    /**
     * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
     */
    public static final class SetCredentialPropertiesResult extends CdpObject {
        private SetCredentialPropertiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCredentialPropertiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCredentialPropertiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetCredentialPropertiesResult build() {
                return new SetCredentialPropertiesResult(values);
            }
        }
    }
    /**
     * Triggered when a credential is added to an authenticator.
     */
    public static final class CredentialAddedEvent extends CdpObject {
        private CredentialAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CredentialAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CredentialAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        @Nullable public WebAuthn.Credential credential() {
            return WebAuthn.Credential.fromMap(objectMap(value("credential")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credential field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credential(@Nullable WebAuthn.Credential value) {
                if (value == null) values.remove("credential");
                else values.put("credential", jsonValue(value));
                return this;
            }
            public CredentialAddedEvent build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credential")) throw new IllegalStateException("Missing required CDP field: credential");
                return new CredentialAddedEvent(values);
            }
        }
    }
    /**
     * Triggered when a credential is deleted, e.g. through PublicKeyCredential.signalUnknownCredential().
     */
    public static final class CredentialDeletedEvent extends CdpObject {
        private CredentialDeletedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CredentialDeletedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CredentialDeletedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        @Nullable public String credentialId() {
            return (String) value("credentialId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credentialId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credentialId(@Nullable String value) {
                if (value == null) values.remove("credentialId");
                else values.put("credentialId", jsonValue(value));
                return this;
            }
            public CredentialDeletedEvent build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credentialId")) throw new IllegalStateException("Missing required CDP field: credentialId");
                return new CredentialDeletedEvent(values);
            }
        }
    }
    /**
     * Triggered when a credential is updated, e.g. through PublicKeyCredential.signalCurrentUserDetails().
     */
    public static final class CredentialUpdatedEvent extends CdpObject {
        private CredentialUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CredentialUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CredentialUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        @Nullable public WebAuthn.Credential credential() {
            return WebAuthn.Credential.fromMap(objectMap(value("credential")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credential field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credential(@Nullable WebAuthn.Credential value) {
                if (value == null) values.remove("credential");
                else values.put("credential", jsonValue(value));
                return this;
            }
            public CredentialUpdatedEvent build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credential")) throw new IllegalStateException("Missing required CDP field: credential");
                return new CredentialUpdatedEvent(values);
            }
        }
    }
    /**
     * Triggered when a credential is used in a webauthn assertion.
     */
    public static final class CredentialAssertedEvent extends CdpObject {
        private CredentialAssertedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CredentialAssertedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CredentialAssertedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        @Nullable public String authenticatorId() {
            return (String) value("authenticatorId");
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        @Nullable public WebAuthn.Credential credential() {
            return WebAuthn.Credential.fromMap(objectMap(value("credential")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the authenticatorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatorId(@Nullable String value) {
                if (value == null) values.remove("authenticatorId");
                else values.put("authenticatorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the credential field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder credential(@Nullable WebAuthn.Credential value) {
                if (value == null) values.remove("credential");
                else values.put("credential", jsonValue(value));
                return this;
            }
            public CredentialAssertedEvent build() {
                if (!values.containsKey("authenticatorId")) throw new IllegalStateException("Missing required CDP field: authenticatorId");
                if (!values.containsKey("credential")) throw new IllegalStateException("Missing required CDP field: credential");
                return new CredentialAssertedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("WebAuthn.enable", params, EnableResult::fromMap);
        }
        /**
         * Disable the WebAuthn domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("WebAuthn.disable", null, DisableResult::fromMap);
        }
        /**
         * Creates and adds a virtual authenticator.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddVirtualAuthenticatorResult> addVirtualAuthenticator(AddVirtualAuthenticatorParams params) {
            return client.call("WebAuthn.addVirtualAuthenticator", params, AddVirtualAuthenticatorResult::fromMap);
        }
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetResponseOverrideBitsResult> setResponseOverrideBits(SetResponseOverrideBitsParams params) {
            return client.call("WebAuthn.setResponseOverrideBits", params, SetResponseOverrideBitsResult::fromMap);
        }
        /**
         * Removes the given authenticator.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveVirtualAuthenticatorResult> removeVirtualAuthenticator(RemoveVirtualAuthenticatorParams params) {
            return client.call("WebAuthn.removeVirtualAuthenticator", params, RemoveVirtualAuthenticatorResult::fromMap);
        }
        /**
         * Adds the credential to the specified authenticator.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddCredentialResult> addCredential(AddCredentialParams params) {
            return client.call("WebAuthn.addCredential", params, AddCredentialResult::fromMap);
        }
        /**
         * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCredentialResult> getCredential(GetCredentialParams params) {
            return client.call("WebAuthn.getCredential", params, GetCredentialResult::fromMap);
        }
        /**
         * Returns all the credentials stored in the given virtual authenticator.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCredentialsResult> getCredentials(GetCredentialsParams params) {
            return client.call("WebAuthn.getCredentials", params, GetCredentialsResult::fromMap);
        }
        /**
         * Removes a credential from the authenticator.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveCredentialResult> removeCredential(RemoveCredentialParams params) {
            return client.call("WebAuthn.removeCredential", params, RemoveCredentialResult::fromMap);
        }
        /**
         * Clears all the credentials from the specified device.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearCredentialsResult> clearCredentials(ClearCredentialsParams params) {
            return client.call("WebAuthn.clearCredentials", params, ClearCredentialsResult::fromMap);
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetUserVerifiedResult> setUserVerified(SetUserVerifiedParams params) {
            return client.call("WebAuthn.setUserVerified", params, SetUserVerifiedResult::fromMap);
        }
        /**
         * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAutomaticPresenceSimulationResult> setAutomaticPresenceSimulation(SetAutomaticPresenceSimulationParams params) {
            return client.call("WebAuthn.setAutomaticPresenceSimulation", params, SetAutomaticPresenceSimulationResult::fromMap);
        }
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCredentialPropertiesResult> setCredentialProperties(SetCredentialPropertiesParams params) {
            return client.call("WebAuthn.setCredentialProperties", params, SetCredentialPropertiesResult::fromMap);
        }
        /**
         * Triggered when a credential is added to an authenticator.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCredentialAdded(Consumer<CredentialAddedEvent> handler) {
            return client.on("WebAuthn.credentialAdded", CredentialAddedEvent::fromMap, handler);
        }
        /**
         * Triggered when a credential is deleted, e.g. through PublicKeyCredential.signalUnknownCredential().
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCredentialDeleted(Consumer<CredentialDeletedEvent> handler) {
            return client.on("WebAuthn.credentialDeleted", CredentialDeletedEvent::fromMap, handler);
        }
        /**
         * Triggered when a credential is updated, e.g. through PublicKeyCredential.signalCurrentUserDetails().
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCredentialUpdated(Consumer<CredentialUpdatedEvent> handler) {
            return client.on("WebAuthn.credentialUpdated", CredentialUpdatedEvent::fromMap, handler);
        }
        /**
         * Triggered when a credential is used in a webauthn assertion.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCredentialAsserted(Consumer<CredentialAssertedEvent> handler) {
            return client.on("WebAuthn.credentialAsserted", CredentialAssertedEvent::fromMap, handler);
        }
    }
}
