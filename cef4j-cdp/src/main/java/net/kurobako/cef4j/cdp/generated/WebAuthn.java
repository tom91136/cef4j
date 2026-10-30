// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
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
 * This domain allows configuring virtual authenticators to test the WebAuthn API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/WebAuthn.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class WebAuthn {
    private WebAuthn() {}
    /**
     * Tagged String wire value for AuthenticatorId.
     */
    public static final class AuthenticatorId implements CdpValue<String> {
        public final String value;
        public AuthenticatorId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof AuthenticatorId)) return false;
            return value.equals(((AuthenticatorId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "AuthenticatorId(" + value + ")"; }
    }
    /**
     * Wire values for AuthenticatorProtocol.
     */
    public enum AuthenticatorProtocol implements CdpValue<String> {
        U2F("u2f"),
        CTAP2("ctap2");
        public final String value;
        AuthenticatorProtocol(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AuthenticatorProtocol of(String value) {
            for (AuthenticatorProtocol constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AuthenticatorProtocol value: " + value);
        }
    }
    /**
     * Wire values for Ctap2Version.
     */
    public enum Ctap2Version implements CdpValue<String> {
        CTAP2_0("ctap2_0"),
        CTAP2_1("ctap2_1"),
        CTAP2_2("ctap2_2");
        public final String value;
        Ctap2Version(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static Ctap2Version of(String value) {
            for (Ctap2Version constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown Ctap2Version value: " + value);
        }
    }
    /**
     * Wire values for AuthenticatorTransport.
     */
    public enum AuthenticatorTransport implements CdpValue<String> {
        USB("usb"),
        NFC("nfc"),
        BLE("ble"),
        CABLE("cable"),
        INTERNAL("internal");
        public final String value;
        AuthenticatorTransport(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AuthenticatorTransport of(String value) {
            for (AuthenticatorTransport constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AuthenticatorTransport value: " + value);
        }
    }
    /**
     */
    public static final class VirtualAuthenticatorOptions extends CdpObject {
        public VirtualAuthenticatorOptions() {}
        private VirtualAuthenticatorOptions(Map<String, Object> values) { super(values); }
        public static VirtualAuthenticatorOptions fromMap(Map<String, Object> values) {
            return new VirtualAuthenticatorOptions(values);
        }
        /**
         * Returns the protocol field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorProtocol protocol() {
            return WebAuthn.AuthenticatorProtocol.of((String) require("protocol"));
        }
        /**
         * Defaults to ctap2_0. Ignored if |protocol| == u2f.
         * @return the protocol field value, empty when absent
         */
        public Optional<WebAuthn.Ctap2Version> ctap2Version() {
            return raw("ctap2Version").map(value -> WebAuthn.Ctap2Version.of((String) value));
        }
        /**
         * Returns the transport field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorTransport transport() {
            return WebAuthn.AuthenticatorTransport.of((String) require("transport"));
        }
        /**
         * Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasResidentKey() {
            return raw("hasResidentKey").map(value -> (Boolean) value);
        }
        /**
         * Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasUserVerification() {
            return raw("hasUserVerification").map(value -> (Boolean) value);
        }
        /**
         * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasLargeBlob() {
            return raw("hasLargeBlob").map(value -> (Boolean) value);
        }
        /**
         * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasCredBlob() {
            return raw("hasCredBlob").map(value -> (Boolean) value);
        }
        /**
         * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasMinPinLength() {
            return raw("hasMinPinLength").map(value -> (Boolean) value);
        }
        /**
         * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasPrf() {
            return raw("hasPrf").map(value -> (Boolean) value);
        }
        /**
         * If set to true, the authenticator will support the hmac-secret extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-hmac-secret-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasHmacSecret() {
            return raw("hasHmacSecret").map(value -> (Boolean) value);
        }
        /**
         * If set to true, the authenticator will support the hmac-secret-mc extension. https://fidoalliance.org/specs/fido-v2.2-rd-20241003/fido-client-to-authenticator-protocol-v2.2-rd-20241003.html#sctn-hmac-secret-make-cred-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasHmacSecretMc() {
            return raw("hasHmacSecretMc").map(value -> (Boolean) value);
        }
        /**
         * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> automaticPresenceSimulation() {
            return raw("automaticPresenceSimulation").map(value -> (Boolean) value);
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isUserVerified() {
            return raw("isUserVerified").map(value -> (Boolean) value);
        }
        /**
         * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> defaultBackupEligibility() {
            return raw("defaultBackupEligibility").map(value -> (Boolean) value);
        }
        /**
         * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> defaultBackupState() {
            return raw("defaultBackupState").map(value -> (Boolean) value);
        }
        /**
         * Sets the protocol field.
         * @param protocol field value
         * @return this model
         */
        public VirtualAuthenticatorOptions protocol(WebAuthn.AuthenticatorProtocol protocol) {
            set("protocol", protocol);
            return this;
        }
        /**
         * Defaults to ctap2_0. Ignored if |protocol| == u2f.
         * @param ctap2Version field value
         * @return this model
         */
        public VirtualAuthenticatorOptions ctap2Version(WebAuthn.Ctap2Version ctap2Version) {
            set("ctap2Version", ctap2Version);
            return this;
        }
        /**
         * Sets the transport field.
         * @param transport field value
         * @return this model
         */
        public VirtualAuthenticatorOptions transport(WebAuthn.AuthenticatorTransport transport) {
            set("transport", transport);
            return this;
        }
        /**
         * Defaults to false.
         * @param hasResidentKey field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasResidentKey(boolean hasResidentKey) {
            set("hasResidentKey", hasResidentKey);
            return this;
        }
        /**
         * Defaults to false.
         * @param hasUserVerification field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasUserVerification(boolean hasUserVerification) {
            set("hasUserVerification", hasUserVerification);
            return this;
        }
        /**
         * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
         * @param hasLargeBlob field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasLargeBlob(boolean hasLargeBlob) {
            set("hasLargeBlob", hasLargeBlob);
            return this;
        }
        /**
         * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
         * @param hasCredBlob field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasCredBlob(boolean hasCredBlob) {
            set("hasCredBlob", hasCredBlob);
            return this;
        }
        /**
         * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
         * @param hasMinPinLength field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasMinPinLength(boolean hasMinPinLength) {
            set("hasMinPinLength", hasMinPinLength);
            return this;
        }
        /**
         * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
         * @param hasPrf field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasPrf(boolean hasPrf) {
            set("hasPrf", hasPrf);
            return this;
        }
        /**
         * If set to true, the authenticator will support the hmac-secret extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-hmac-secret-extension Defaults to false.
         * @param hasHmacSecret field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasHmacSecret(boolean hasHmacSecret) {
            set("hasHmacSecret", hasHmacSecret);
            return this;
        }
        /**
         * If set to true, the authenticator will support the hmac-secret-mc extension. https://fidoalliance.org/specs/fido-v2.2-rd-20241003/fido-client-to-authenticator-protocol-v2.2-rd-20241003.html#sctn-hmac-secret-make-cred-extension Defaults to false.
         * @param hasHmacSecretMc field value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasHmacSecretMc(boolean hasHmacSecretMc) {
            set("hasHmacSecretMc", hasHmacSecretMc);
            return this;
        }
        /**
         * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
         * @param automaticPresenceSimulation field value
         * @return this model
         */
        public VirtualAuthenticatorOptions automaticPresenceSimulation(boolean automaticPresenceSimulation) {
            set("automaticPresenceSimulation", automaticPresenceSimulation);
            return this;
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
         * @param isUserVerified field value
         * @return this model
         */
        public VirtualAuthenticatorOptions isUserVerified(boolean isUserVerified) {
            set("isUserVerified", isUserVerified);
            return this;
        }
        /**
         * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @param defaultBackupEligibility field value
         * @return this model
         */
        public VirtualAuthenticatorOptions defaultBackupEligibility(boolean defaultBackupEligibility) {
            set("defaultBackupEligibility", defaultBackupEligibility);
            return this;
        }
        /**
         * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @param defaultBackupState field value
         * @return this model
         */
        public VirtualAuthenticatorOptions defaultBackupState(boolean defaultBackupState) {
            set("defaultBackupState", defaultBackupState);
            return this;
        }
    }
    /**
     */
    public static final class Credential extends CdpObject {
        public Credential() {}
        private Credential(Map<String, Object> values) { super(values); }
        public static Credential fromMap(Map<String, Object> values) {
            return new Credential(values);
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        public String credentialId() {
            return (String) require("credentialId");
        }
        /**
         * Returns the isResidentCredential field.
         * @return the protocol field value
         */
        public boolean isResidentCredential() {
            return (Boolean) require("isResidentCredential");
        }
        /**
         * Relying Party ID the credential is scoped to. Must be set when adding a credential.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> rpId() {
            return raw("rpId").map(value -> (String) value);
        }
        /**
         * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String privateKey() {
            return (String) require("privateKey");
        }
        /**
         * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific user. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> userHandle() {
            return raw("userHandle").map(value -> (String) value);
        }
        /**
         * Signature counter. This is incremented by one for each successful assertion. See https://w3c.github.io/webauthn/#signature-counter
         * @return the protocol field value
         */
        public long signCount() {
            return ((Number) require("signCount")).longValue();
        }
        /**
         * The large blob associated with the credential. See https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> largeBlob() {
            return raw("largeBlob").map(value -> (String) value);
        }
        /**
         * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> backupEligibility() {
            return raw("backupEligibility").map(value -> (Boolean) value);
        }
        /**
         * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> backupState() {
            return raw("backupState").map(value -> (Boolean) value);
        }
        /**
         * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
         * @return the protocol field value, empty when absent
         */
        public Optional<String> userName() {
            return raw("userName").map(value -> (String) value);
        }
        /**
         * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
         * @return the protocol field value, empty when absent
         */
        public Optional<String> userDisplayName() {
            return raw("userDisplayName").map(value -> (String) value);
        }
        /**
         * Sets the credentialId field.
         * @param credentialId field value
         * @return this model
         */
        public Credential credentialId(String credentialId) {
            set("credentialId", credentialId);
            return this;
        }
        /**
         * Sets the isResidentCredential field.
         * @param isResidentCredential field value
         * @return this model
         */
        public Credential isResidentCredential(boolean isResidentCredential) {
            set("isResidentCredential", isResidentCredential);
            return this;
        }
        /**
         * Relying Party ID the credential is scoped to. Must be set when adding a credential.
         * @param rpId field value
         * @return this model
         */
        public Credential rpId(String rpId) {
            set("rpId", rpId);
            return this;
        }
        /**
         * The ECDSA P-256 private key in PKCS#8 format. (Encoded as a base64 string when passed over JSON)
         * @param privateKey field value
         * @return this model
         */
        public Credential privateKey(String privateKey) {
            set("privateKey", privateKey);
            return this;
        }
        /**
         * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific user. (Encoded as a base64 string when passed over JSON)
         * @param userHandle field value
         * @return this model
         */
        public Credential userHandle(String userHandle) {
            set("userHandle", userHandle);
            return this;
        }
        /**
         * Signature counter. This is incremented by one for each successful assertion. See https://w3c.github.io/webauthn/#signature-counter
         * @param signCount field value
         * @return this model
         */
        public Credential signCount(long signCount) {
            set("signCount", signCount);
            return this;
        }
        /**
         * The large blob associated with the credential. See https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when passed over JSON)
         * @param largeBlob field value
         * @return this model
         */
        public Credential largeBlob(String largeBlob) {
            set("largeBlob", largeBlob);
            return this;
        }
        /**
         * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
         * @param backupEligibility field value
         * @return this model
         */
        public Credential backupEligibility(boolean backupEligibility) {
            set("backupEligibility", backupEligibility);
            return this;
        }
        /**
         * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
         * @param backupState field value
         * @return this model
         */
        public Credential backupState(boolean backupState) {
            set("backupState", backupState);
            return this;
        }
        /**
         * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
         * @param userName field value
         * @return this model
         */
        public Credential userName(String userName) {
            set("userName", userName);
            return this;
        }
        /**
         * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
         * @param userDisplayName field value
         * @return this model
         */
        public Credential userDisplayName(String userDisplayName) {
            set("userDisplayName", userDisplayName);
            return this;
        }
    }
    /**
     * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to enable the WebAuthn user interface. Enabling the UI is recommended for debugging and demo purposes, as it is closer to the real experience. Disabling the UI is recommended for automated testing. Supported at the embedder&#x27;s discretion if UI is available. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableUI() {
            return raw("enableUI").map(value -> (Boolean) value);
        }
        /**
         * Whether to enable the WebAuthn user interface. Enabling the UI is recommended for debugging and demo purposes, as it is closer to the real experience. Disabling the UI is recommended for automated testing. Supported at the embedder&#x27;s discretion if UI is available. Defaults to false.
         * @param enableUI field value
         * @return this model
         */
        public EnableRequest enableUI(boolean enableUI) {
            set("enableUI", enableUI);
            return this;
        }
    }
    /**
     * Creates and adds a virtual authenticator.
     */
    public static final class AddVirtualAuthenticatorRequest extends CdpObject {
        public AddVirtualAuthenticatorRequest() {}
        /**
         * Creates and adds a virtual authenticator.
         * @param options protocol value
         */
        public AddVirtualAuthenticatorRequest(WebAuthn.VirtualAuthenticatorOptions options) {
            options(options);
        }
        public static AddVirtualAuthenticatorRequest fromMap(Map<String, Object> values) {
            final AddVirtualAuthenticatorRequest instance_ = new AddVirtualAuthenticatorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        public WebAuthn.VirtualAuthenticatorOptions options() {
            return java.util.Objects.requireNonNull(WebAuthn.VirtualAuthenticatorOptions.fromMap(CdpObject.requireObjectMap(require("options"))));
        }
        /**
         * Sets the options field.
         * @param options field value
         * @return this model
         */
        public AddVirtualAuthenticatorRequest options(WebAuthn.VirtualAuthenticatorOptions options) {
            set("options", options);
            return this;
        }
    }
    /**
     * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
     */
    public static final class SetResponseOverrideBitsRequest extends CdpObject {
        public SetResponseOverrideBitsRequest() {}
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param authenticatorId protocol value
         */
        public SetResponseOverrideBitsRequest(WebAuthn.AuthenticatorId authenticatorId) {
            authenticatorId(authenticatorId);
        }
        public static SetResponseOverrideBitsRequest fromMap(Map<String, Object> values) {
            final SetResponseOverrideBitsRequest instance_ = new SetResponseOverrideBitsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * If isBogusSignature is set, overrides the signature in the authenticator response to be zero. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isBogusSignature() {
            return raw("isBogusSignature").map(value -> (Boolean) value);
        }
        /**
         * If isBadUV is set, overrides the UV bit in the flags in the authenticator response to be zero. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isBadUV() {
            return raw("isBadUV").map(value -> (Boolean) value);
        }
        /**
         * If isBadUP is set, overrides the UP bit in the flags in the authenticator response to be zero. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isBadUP() {
            return raw("isBadUP").map(value -> (Boolean) value);
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public SetResponseOverrideBitsRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * If isBogusSignature is set, overrides the signature in the authenticator response to be zero. Defaults to false.
         * @param isBogusSignature field value
         * @return this model
         */
        public SetResponseOverrideBitsRequest isBogusSignature(boolean isBogusSignature) {
            set("isBogusSignature", isBogusSignature);
            return this;
        }
        /**
         * If isBadUV is set, overrides the UV bit in the flags in the authenticator response to be zero. Defaults to false.
         * @param isBadUV field value
         * @return this model
         */
        public SetResponseOverrideBitsRequest isBadUV(boolean isBadUV) {
            set("isBadUV", isBadUV);
            return this;
        }
        /**
         * If isBadUP is set, overrides the UP bit in the flags in the authenticator response to be zero. Defaults to false.
         * @param isBadUP field value
         * @return this model
         */
        public SetResponseOverrideBitsRequest isBadUP(boolean isBadUP) {
            set("isBadUP", isBadUP);
            return this;
        }
    }
    /**
     * Removes the given authenticator.
     */
    public static final class RemoveVirtualAuthenticatorRequest extends CdpObject {
        public RemoveVirtualAuthenticatorRequest() {}
        /**
         * Removes the given authenticator.
         * @param authenticatorId protocol value
         */
        public RemoveVirtualAuthenticatorRequest(WebAuthn.AuthenticatorId authenticatorId) {
            authenticatorId(authenticatorId);
        }
        public static RemoveVirtualAuthenticatorRequest fromMap(Map<String, Object> values) {
            final RemoveVirtualAuthenticatorRequest instance_ = new RemoveVirtualAuthenticatorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public RemoveVirtualAuthenticatorRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
    }
    /**
     * Adds the credential to the specified authenticator.
     */
    public static final class AddCredentialRequest extends CdpObject {
        public AddCredentialRequest() {}
        /**
         * Adds the credential to the specified authenticator.
         * @param authenticatorId protocol value
         * @param credential protocol value
         */
        public AddCredentialRequest(WebAuthn.AuthenticatorId authenticatorId, WebAuthn.Credential credential) {
            authenticatorId(authenticatorId).credential(credential);
        }
        public static AddCredentialRequest fromMap(Map<String, Object> values) {
            final AddCredentialRequest instance_ = new AddCredentialRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        public WebAuthn.Credential credential() {
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(require("credential"))));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public AddCredentialRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credential field.
         * @param credential field value
         * @return this model
         */
        public AddCredentialRequest credential(WebAuthn.Credential credential) {
            set("credential", credential);
            return this;
        }
    }
    /**
     * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
     */
    public static final class GetCredentialRequest extends CdpObject {
        public GetCredentialRequest() {}
        /**
         * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         */
        public GetCredentialRequest(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            authenticatorId(authenticatorId).credentialId(credentialId);
        }
        public static GetCredentialRequest fromMap(Map<String, Object> values) {
            final GetCredentialRequest instance_ = new GetCredentialRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        public String credentialId() {
            return (String) require("credentialId");
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public GetCredentialRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credentialId field.
         * @param credentialId field value
         * @return this model
         */
        public GetCredentialRequest credentialId(String credentialId) {
            set("credentialId", credentialId);
            return this;
        }
    }
    /**
     * Returns all the credentials stored in the given virtual authenticator.
     */
    public static final class GetCredentialsRequest extends CdpObject {
        public GetCredentialsRequest() {}
        /**
         * Returns all the credentials stored in the given virtual authenticator.
         * @param authenticatorId protocol value
         */
        public GetCredentialsRequest(WebAuthn.AuthenticatorId authenticatorId) {
            authenticatorId(authenticatorId);
        }
        public static GetCredentialsRequest fromMap(Map<String, Object> values) {
            final GetCredentialsRequest instance_ = new GetCredentialsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public GetCredentialsRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
    }
    /**
     * Removes a credential from the authenticator.
     */
    public static final class RemoveCredentialRequest extends CdpObject {
        public RemoveCredentialRequest() {}
        /**
         * Removes a credential from the authenticator.
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         */
        public RemoveCredentialRequest(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            authenticatorId(authenticatorId).credentialId(credentialId);
        }
        public static RemoveCredentialRequest fromMap(Map<String, Object> values) {
            final RemoveCredentialRequest instance_ = new RemoveCredentialRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        public String credentialId() {
            return (String) require("credentialId");
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public RemoveCredentialRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credentialId field.
         * @param credentialId field value
         * @return this model
         */
        public RemoveCredentialRequest credentialId(String credentialId) {
            set("credentialId", credentialId);
            return this;
        }
    }
    /**
     * Clears all the credentials from the specified device.
     */
    public static final class ClearCredentialsRequest extends CdpObject {
        public ClearCredentialsRequest() {}
        /**
         * Clears all the credentials from the specified device.
         * @param authenticatorId protocol value
         */
        public ClearCredentialsRequest(WebAuthn.AuthenticatorId authenticatorId) {
            authenticatorId(authenticatorId);
        }
        public static ClearCredentialsRequest fromMap(Map<String, Object> values) {
            final ClearCredentialsRequest instance_ = new ClearCredentialsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public ClearCredentialsRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
    }
    /**
     * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
     */
    public static final class SetUserVerifiedRequest extends CdpObject {
        public SetUserVerifiedRequest() {}
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
         * @param authenticatorId protocol value
         * @param isUserVerified protocol value
         */
        public SetUserVerifiedRequest(WebAuthn.AuthenticatorId authenticatorId, boolean isUserVerified) {
            authenticatorId(authenticatorId).isUserVerified(isUserVerified);
        }
        public static SetUserVerifiedRequest fromMap(Map<String, Object> values) {
            final SetUserVerifiedRequest instance_ = new SetUserVerifiedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the isUserVerified field.
         * @return the protocol field value
         */
        public boolean isUserVerified() {
            return (Boolean) require("isUserVerified");
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public SetUserVerifiedRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the isUserVerified field.
         * @param isUserVerified field value
         * @return this model
         */
        public SetUserVerifiedRequest isUserVerified(boolean isUserVerified) {
            set("isUserVerified", isUserVerified);
            return this;
        }
    }
    /**
     * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
     */
    public static final class SetAutomaticPresenceSimulationRequest extends CdpObject {
        public SetAutomaticPresenceSimulationRequest() {}
        /**
         * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
         * @param authenticatorId protocol value
         * @param enabled protocol value
         */
        public SetAutomaticPresenceSimulationRequest(WebAuthn.AuthenticatorId authenticatorId, boolean enabled) {
            authenticatorId(authenticatorId).enabled(enabled);
        }
        public static SetAutomaticPresenceSimulationRequest fromMap(Map<String, Object> values) {
            final SetAutomaticPresenceSimulationRequest instance_ = new SetAutomaticPresenceSimulationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public SetAutomaticPresenceSimulationRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the enabled field.
         * @param enabled field value
         * @return this model
         */
        public SetAutomaticPresenceSimulationRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
     */
    public static final class SetCredentialPropertiesRequest extends CdpObject {
        public SetCredentialPropertiesRequest() {}
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         */
        public SetCredentialPropertiesRequest(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            authenticatorId(authenticatorId).credentialId(credentialId);
        }
        public static SetCredentialPropertiesRequest fromMap(Map<String, Object> values) {
            final SetCredentialPropertiesRequest instance_ = new SetCredentialPropertiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        public String credentialId() {
            return (String) require("credentialId");
        }
        /**
         * Returns the backupEligibility field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> backupEligibility() {
            return raw("backupEligibility").map(value -> (Boolean) value);
        }
        /**
         * Returns the backupState field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> backupState() {
            return raw("backupState").map(value -> (Boolean) value);
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public SetCredentialPropertiesRequest authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credentialId field.
         * @param credentialId field value
         * @return this model
         */
        public SetCredentialPropertiesRequest credentialId(String credentialId) {
            set("credentialId", credentialId);
            return this;
        }
        /**
         * Sets the backupEligibility field.
         * @param backupEligibility field value
         * @return this model
         */
        public SetCredentialPropertiesRequest backupEligibility(boolean backupEligibility) {
            set("backupEligibility", backupEligibility);
            return this;
        }
        /**
         * Sets the backupState field.
         * @param backupState field value
         * @return this model
         */
        public SetCredentialPropertiesRequest backupState(boolean backupState) {
            set("backupState", backupState);
            return this;
        }
    }
    /**
     * Triggered when a credential is added to an authenticator.
     */
    public static final class CredentialAddedEvent extends CdpObject {
        public CredentialAddedEvent() {}
        private CredentialAddedEvent(Map<String, Object> values) { super(values); }
        public static CredentialAddedEvent fromMap(Map<String, Object> values) {
            return new CredentialAddedEvent(values);
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        public WebAuthn.Credential credential() {
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(require("credential"))));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public CredentialAddedEvent authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credential field.
         * @param credential field value
         * @return this model
         */
        public CredentialAddedEvent credential(WebAuthn.Credential credential) {
            set("credential", credential);
            return this;
        }
    }
    /**
     * Triggered when a credential is deleted, e.g. through PublicKeyCredential.signalUnknownCredential().
     */
    public static final class CredentialDeletedEvent extends CdpObject {
        public CredentialDeletedEvent() {}
        private CredentialDeletedEvent(Map<String, Object> values) { super(values); }
        public static CredentialDeletedEvent fromMap(Map<String, Object> values) {
            return new CredentialDeletedEvent(values);
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credentialId field.
         * @return the protocol field value
         */
        public String credentialId() {
            return (String) require("credentialId");
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public CredentialDeletedEvent authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credentialId field.
         * @param credentialId field value
         * @return this model
         */
        public CredentialDeletedEvent credentialId(String credentialId) {
            set("credentialId", credentialId);
            return this;
        }
    }
    /**
     * Triggered when a credential is updated, e.g. through PublicKeyCredential.signalCurrentUserDetails().
     */
    public static final class CredentialUpdatedEvent extends CdpObject {
        public CredentialUpdatedEvent() {}
        private CredentialUpdatedEvent(Map<String, Object> values) { super(values); }
        public static CredentialUpdatedEvent fromMap(Map<String, Object> values) {
            return new CredentialUpdatedEvent(values);
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        public WebAuthn.Credential credential() {
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(require("credential"))));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public CredentialUpdatedEvent authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credential field.
         * @param credential field value
         * @return this model
         */
        public CredentialUpdatedEvent credential(WebAuthn.Credential credential) {
            set("credential", credential);
            return this;
        }
    }
    /**
     * Triggered when a credential is used in a webauthn assertion.
     */
    public static final class CredentialAssertedEvent extends CdpObject {
        public CredentialAssertedEvent() {}
        private CredentialAssertedEvent(Map<String, Object> values) { super(values); }
        public static CredentialAssertedEvent fromMap(Map<String, Object> values) {
            return new CredentialAssertedEvent(values);
        }
        /**
         * Returns the authenticatorId field.
         * @return the protocol field value
         */
        public WebAuthn.AuthenticatorId authenticatorId() {
            return new WebAuthn.AuthenticatorId((String) require("authenticatorId"));
        }
        /**
         * Returns the credential field.
         * @return the protocol field value
         */
        public WebAuthn.Credential credential() {
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(require("credential"))));
        }
        /**
         * Sets the authenticatorId field.
         * @param authenticatorId field value
         * @return this model
         */
        public CredentialAssertedEvent authenticatorId(WebAuthn.AuthenticatorId authenticatorId) {
            set("authenticatorId", authenticatorId);
            return this;
        }
        /**
         * Sets the credential field.
         * @param credential field value
         * @return this model
         */
        public CredentialAssertedEvent credential(WebAuthn.Credential credential) {
            set("credential", credential);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
         * @param enableUI protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(boolean enableUI) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enableUI", CdpObject.json(enableUI));
            return client.call("WebAuthn.enable", params);
        }
        /**
         * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("WebAuthn.enable", null);
        }
        /**
         * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("WebAuthn.enable", request == null ? null : request.toMap());
        }
        /**
         * Disable the WebAuthn domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("WebAuthn.disable", null);
        }
        /**
         * Creates and adds a virtual authenticator.
         * @param options protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAuthn.AuthenticatorId> addVirtualAuthenticator(WebAuthn.VirtualAuthenticatorOptions options) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("options", CdpObject.json(options));
            return client.call("WebAuthn.addVirtualAuthenticator", params, result_ -> new WebAuthn.AuthenticatorId((String) java.util.Objects.requireNonNull(result_.get("authenticatorId"))));
        }
        /**
         * Creates and adds a virtual authenticator.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAuthn.AuthenticatorId> addVirtualAuthenticator(AddVirtualAuthenticatorRequest request) {
            return client.call("WebAuthn.addVirtualAuthenticator", request == null ? null : request.toMap(), result_ -> new WebAuthn.AuthenticatorId((String) java.util.Objects.requireNonNull(result_.get("authenticatorId"))));
        }
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param authenticatorId protocol value
         * @param isBogusSignature protocol value
         * @param isBadUV protocol value
         * @param isBadUP protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setResponseOverrideBits(WebAuthn.AuthenticatorId authenticatorId, @Nullable Boolean isBogusSignature, @Nullable Boolean isBadUV, @Nullable Boolean isBadUP) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            if (isBogusSignature != null) params.put("isBogusSignature", CdpObject.json(isBogusSignature));
            if (isBadUV != null) params.put("isBadUV", CdpObject.json(isBadUV));
            if (isBadUP != null) params.put("isBadUP", CdpObject.json(isBadUP));
            return client.call("WebAuthn.setResponseOverrideBits", params);
        }
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param authenticatorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setResponseOverrideBits(WebAuthn.AuthenticatorId authenticatorId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.setResponseOverrideBits", params);
        }
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setResponseOverrideBits(SetResponseOverrideBitsRequest request) {
            return client.call("WebAuthn.setResponseOverrideBits", request == null ? null : request.toMap());
        }
        /**
         * Removes the given authenticator.
         * @param authenticatorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeVirtualAuthenticator(WebAuthn.AuthenticatorId authenticatorId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.removeVirtualAuthenticator", params);
        }
        /**
         * Removes the given authenticator.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeVirtualAuthenticator(RemoveVirtualAuthenticatorRequest request) {
            return client.call("WebAuthn.removeVirtualAuthenticator", request == null ? null : request.toMap());
        }
        /**
         * Adds the credential to the specified authenticator.
         * @param authenticatorId protocol value
         * @param credential protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addCredential(WebAuthn.AuthenticatorId authenticatorId, WebAuthn.Credential credential) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credential", CdpObject.json(credential));
            return client.call("WebAuthn.addCredential", params);
        }
        /**
         * Adds the credential to the specified authenticator.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addCredential(AddCredentialRequest request) {
            return client.call("WebAuthn.addCredential", request == null ? null : request.toMap());
        }
        /**
         * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAuthn.Credential> getCredential(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            return client.call("WebAuthn.getCredential", params, result_ -> java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("credential"))))));
        }
        /**
         * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAuthn.Credential> getCredential(GetCredentialRequest request) {
            return client.call("WebAuthn.getCredential", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("credential"))))));
        }
        /**
         * Returns all the credentials stored in the given virtual authenticator.
         * @param authenticatorId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<WebAuthn.Credential>> getCredentials(WebAuthn.AuthenticatorId authenticatorId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.getCredentials", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("credentials")), element0 -> java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns all the credentials stored in the given virtual authenticator.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<WebAuthn.Credential>> getCredentials(GetCredentialsRequest request) {
            return client.call("WebAuthn.getCredentials", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("credentials")), element0 -> java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Removes a credential from the authenticator.
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeCredential(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            return client.call("WebAuthn.removeCredential", params);
        }
        /**
         * Removes a credential from the authenticator.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeCredential(RemoveCredentialRequest request) {
            return client.call("WebAuthn.removeCredential", request == null ? null : request.toMap());
        }
        /**
         * Clears all the credentials from the specified device.
         * @param authenticatorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCredentials(WebAuthn.AuthenticatorId authenticatorId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.clearCredentials", params);
        }
        /**
         * Clears all the credentials from the specified device.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCredentials(ClearCredentialsRequest request) {
            return client.call("WebAuthn.clearCredentials", request == null ? null : request.toMap());
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
         * @param authenticatorId protocol value
         * @param isUserVerified protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserVerified(WebAuthn.AuthenticatorId authenticatorId, boolean isUserVerified) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("isUserVerified", CdpObject.json(isUserVerified));
            return client.call("WebAuthn.setUserVerified", params);
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserVerified(SetUserVerifiedRequest request) {
            return client.call("WebAuthn.setUserVerified", request == null ? null : request.toMap());
        }
        /**
         * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
         * @param authenticatorId protocol value
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomaticPresenceSimulation(WebAuthn.AuthenticatorId authenticatorId, boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("enabled", CdpObject.json(enabled));
            return client.call("WebAuthn.setAutomaticPresenceSimulation", params);
        }
        /**
         * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomaticPresenceSimulation(SetAutomaticPresenceSimulationRequest request) {
            return client.call("WebAuthn.setAutomaticPresenceSimulation", request == null ? null : request.toMap());
        }
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @param backupEligibility protocol value
         * @param backupState protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCredentialProperties(WebAuthn.AuthenticatorId authenticatorId, String credentialId, @Nullable Boolean backupEligibility, @Nullable Boolean backupState) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            if (backupEligibility != null) params.put("backupEligibility", CdpObject.json(backupEligibility));
            if (backupState != null) params.put("backupState", CdpObject.json(backupState));
            return client.call("WebAuthn.setCredentialProperties", params);
        }
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCredentialProperties(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            return client.call("WebAuthn.setCredentialProperties", params);
        }
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCredentialProperties(SetCredentialPropertiesRequest request) {
            return client.call("WebAuthn.setCredentialProperties", request == null ? null : request.toMap());
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
