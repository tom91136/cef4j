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
 * This domain allows configuring virtual authenticators to test the WebAuthn API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/WebAuthn.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class WebAuthn {
    private WebAuthn() {}
    /**
     * Tagged String wire value for AuthenticatorId.
     */
    public static final class AuthenticatorId implements CdpValue<String> {
        public final String value;
        public AuthenticatorId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static AuthenticatorProtocol of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static Ctap2Version of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static AuthenticatorTransport of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("ctap2Version") == null ? null : WebAuthn.Ctap2Version.of((String) raw("ctap2Version")));
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
            return Optional.ofNullable((Boolean) raw("hasResidentKey"));
        }
        /**
         * Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasUserVerification() {
            return Optional.ofNullable((Boolean) raw("hasUserVerification"));
        }
        /**
         * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasLargeBlob() {
            return Optional.ofNullable((Boolean) raw("hasLargeBlob"));
        }
        /**
         * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasCredBlob() {
            return Optional.ofNullable((Boolean) raw("hasCredBlob"));
        }
        /**
         * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasMinPinLength() {
            return Optional.ofNullable((Boolean) raw("hasMinPinLength"));
        }
        /**
         * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasPrf() {
            return Optional.ofNullable((Boolean) raw("hasPrf"));
        }
        /**
         * If set to true, the authenticator will support the hmac-secret extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-hmac-secret-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasHmacSecret() {
            return Optional.ofNullable((Boolean) raw("hasHmacSecret"));
        }
        /**
         * If set to true, the authenticator will support the hmac-secret-mc extension. https://fidoalliance.org/specs/fido-v2.2-rd-20241003/fido-client-to-authenticator-protocol-v2.2-rd-20241003.html#sctn-hmac-secret-make-cred-extension Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasHmacSecretMc() {
            return Optional.ofNullable((Boolean) raw("hasHmacSecretMc"));
        }
        /**
         * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> automaticPresenceSimulation() {
            return Optional.ofNullable((Boolean) raw("automaticPresenceSimulation"));
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isUserVerified() {
            return Optional.ofNullable((Boolean) raw("isUserVerified"));
        }
        /**
         * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> defaultBackupEligibility() {
            return Optional.ofNullable((Boolean) raw("defaultBackupEligibility"));
        }
        /**
         * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> defaultBackupState() {
            return Optional.ofNullable((Boolean) raw("defaultBackupState"));
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
         * @param ctap2Version field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions ctap2Version(Optional<WebAuthn.Ctap2Version> ctap2Version) {
            set("ctap2Version", ctap2Version.orElse(null));
            return this;
        }
        /**
         * Defaults to ctap2_0. Ignored if |protocol| == u2f.
         * @param ctap2Version field value; null removes the value
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
         * @param hasResidentKey field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasResidentKey(Optional<Boolean> hasResidentKey) {
            set("hasResidentKey", hasResidentKey.orElse(null));
            return this;
        }
        /**
         * Defaults to false.
         * @param hasResidentKey field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasResidentKey(Boolean hasResidentKey) {
            set("hasResidentKey", hasResidentKey);
            return this;
        }
        /**
         * Defaults to false.
         * @param hasUserVerification field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasUserVerification(Optional<Boolean> hasUserVerification) {
            set("hasUserVerification", hasUserVerification.orElse(null));
            return this;
        }
        /**
         * Defaults to false.
         * @param hasUserVerification field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasUserVerification(Boolean hasUserVerification) {
            set("hasUserVerification", hasUserVerification);
            return this;
        }
        /**
         * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
         * @param hasLargeBlob field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasLargeBlob(Optional<Boolean> hasLargeBlob) {
            set("hasLargeBlob", hasLargeBlob.orElse(null));
            return this;
        }
        /**
         * If set to true, the authenticator will support the largeBlob extension. https://w3c.github.io/webauthn#largeBlob Defaults to false.
         * @param hasLargeBlob field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasLargeBlob(Boolean hasLargeBlob) {
            set("hasLargeBlob", hasLargeBlob);
            return this;
        }
        /**
         * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
         * @param hasCredBlob field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasCredBlob(Optional<Boolean> hasCredBlob) {
            set("hasCredBlob", hasCredBlob.orElse(null));
            return this;
        }
        /**
         * If set to true, the authenticator will support the credBlob extension. https://fidoalliance.org/specs/fido-v2.1-rd-20201208/fido-client-to-authenticator-protocol-v2.1-rd-20201208.html#sctn-credBlob-extension Defaults to false.
         * @param hasCredBlob field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasCredBlob(Boolean hasCredBlob) {
            set("hasCredBlob", hasCredBlob);
            return this;
        }
        /**
         * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
         * @param hasMinPinLength field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasMinPinLength(Optional<Boolean> hasMinPinLength) {
            set("hasMinPinLength", hasMinPinLength.orElse(null));
            return this;
        }
        /**
         * If set to true, the authenticator will support the minPinLength extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-minpinlength-extension Defaults to false.
         * @param hasMinPinLength field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasMinPinLength(Boolean hasMinPinLength) {
            set("hasMinPinLength", hasMinPinLength);
            return this;
        }
        /**
         * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
         * @param hasPrf field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasPrf(Optional<Boolean> hasPrf) {
            set("hasPrf", hasPrf.orElse(null));
            return this;
        }
        /**
         * If set to true, the authenticator will support the prf extension. https://w3c.github.io/webauthn/#prf-extension Defaults to false.
         * @param hasPrf field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasPrf(Boolean hasPrf) {
            set("hasPrf", hasPrf);
            return this;
        }
        /**
         * If set to true, the authenticator will support the hmac-secret extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-hmac-secret-extension Defaults to false.
         * @param hasHmacSecret field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasHmacSecret(Optional<Boolean> hasHmacSecret) {
            set("hasHmacSecret", hasHmacSecret.orElse(null));
            return this;
        }
        /**
         * If set to true, the authenticator will support the hmac-secret extension. https://fidoalliance.org/specs/fido-v2.1-ps-20210615/fido-client-to-authenticator-protocol-v2.1-ps-20210615.html#sctn-hmac-secret-extension Defaults to false.
         * @param hasHmacSecret field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasHmacSecret(Boolean hasHmacSecret) {
            set("hasHmacSecret", hasHmacSecret);
            return this;
        }
        /**
         * If set to true, the authenticator will support the hmac-secret-mc extension. https://fidoalliance.org/specs/fido-v2.2-rd-20241003/fido-client-to-authenticator-protocol-v2.2-rd-20241003.html#sctn-hmac-secret-make-cred-extension Defaults to false.
         * @param hasHmacSecretMc field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasHmacSecretMc(Optional<Boolean> hasHmacSecretMc) {
            set("hasHmacSecretMc", hasHmacSecretMc.orElse(null));
            return this;
        }
        /**
         * If set to true, the authenticator will support the hmac-secret-mc extension. https://fidoalliance.org/specs/fido-v2.2-rd-20241003/fido-client-to-authenticator-protocol-v2.2-rd-20241003.html#sctn-hmac-secret-make-cred-extension Defaults to false.
         * @param hasHmacSecretMc field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions hasHmacSecretMc(Boolean hasHmacSecretMc) {
            set("hasHmacSecretMc", hasHmacSecretMc);
            return this;
        }
        /**
         * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
         * @param automaticPresenceSimulation field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions automaticPresenceSimulation(Optional<Boolean> automaticPresenceSimulation) {
            set("automaticPresenceSimulation", automaticPresenceSimulation.orElse(null));
            return this;
        }
        /**
         * If set to true, tests of user presence will succeed immediately. Otherwise, they will not be resolved. Defaults to true.
         * @param automaticPresenceSimulation field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions automaticPresenceSimulation(Boolean automaticPresenceSimulation) {
            set("automaticPresenceSimulation", automaticPresenceSimulation);
            return this;
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
         * @param isUserVerified field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions isUserVerified(Optional<Boolean> isUserVerified) {
            set("isUserVerified", isUserVerified.orElse(null));
            return this;
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. Defaults to false.
         * @param isUserVerified field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions isUserVerified(Boolean isUserVerified) {
            set("isUserVerified", isUserVerified);
            return this;
        }
        /**
         * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @param defaultBackupEligibility field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions defaultBackupEligibility(Optional<Boolean> defaultBackupEligibility) {
            set("defaultBackupEligibility", defaultBackupEligibility.orElse(null));
            return this;
        }
        /**
         * Credentials created by this authenticator will have the backup eligibility (BE) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @param defaultBackupEligibility field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions defaultBackupEligibility(Boolean defaultBackupEligibility) {
            set("defaultBackupEligibility", defaultBackupEligibility);
            return this;
        }
        /**
         * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @param defaultBackupState field value; empty omits the value
         * @return this model
         */
        public VirtualAuthenticatorOptions defaultBackupState(Optional<Boolean> defaultBackupState) {
            set("defaultBackupState", defaultBackupState.orElse(null));
            return this;
        }
        /**
         * Credentials created by this authenticator will have the backup state (BS) flag set to this value. Defaults to false. https://w3c.github.io/webauthn/#sctn-credential-backup
         * @param defaultBackupState field value; null removes the value
         * @return this model
         */
        public VirtualAuthenticatorOptions defaultBackupState(Boolean defaultBackupState) {
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
            return Optional.ofNullable((String) raw("rpId"));
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
            return Optional.ofNullable((String) raw("userHandle"));
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
            return Optional.ofNullable((String) raw("largeBlob"));
        }
        /**
         * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> backupEligibility() {
            return Optional.ofNullable((Boolean) raw("backupEligibility"));
        }
        /**
         * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> backupState() {
            return Optional.ofNullable((Boolean) raw("backupState"));
        }
        /**
         * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
         * @return the protocol field value, empty when absent
         */
        public Optional<String> userName() {
            return Optional.ofNullable((String) raw("userName"));
        }
        /**
         * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
         * @return the protocol field value, empty when absent
         */
        public Optional<String> userDisplayName() {
            return Optional.ofNullable((String) raw("userDisplayName"));
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
         * @param rpId field value; empty omits the value
         * @return this model
         */
        public Credential rpId(Optional<String> rpId) {
            set("rpId", rpId.orElse(null));
            return this;
        }
        /**
         * Relying Party ID the credential is scoped to. Must be set when adding a credential.
         * @param rpId field value; null removes the value
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
         * @param userHandle field value; empty omits the value
         * @return this model
         */
        public Credential userHandle(Optional<String> userHandle) {
            set("userHandle", userHandle.orElse(null));
            return this;
        }
        /**
         * An opaque byte sequence with a maximum size of 64 bytes mapping the credential to a specific user. (Encoded as a base64 string when passed over JSON)
         * @param userHandle field value; null removes the value
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
         * @param largeBlob field value; empty omits the value
         * @return this model
         */
        public Credential largeBlob(Optional<String> largeBlob) {
            set("largeBlob", largeBlob.orElse(null));
            return this;
        }
        /**
         * The large blob associated with the credential. See https://w3c.github.io/webauthn/#sctn-large-blob-extension (Encoded as a base64 string when passed over JSON)
         * @param largeBlob field value; null removes the value
         * @return this model
         */
        public Credential largeBlob(String largeBlob) {
            set("largeBlob", largeBlob);
            return this;
        }
        /**
         * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
         * @param backupEligibility field value; empty omits the value
         * @return this model
         */
        public Credential backupEligibility(Optional<Boolean> backupEligibility) {
            set("backupEligibility", backupEligibility.orElse(null));
            return this;
        }
        /**
         * Assertions returned by this credential will have the backup eligibility (BE) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupEligibility value.
         * @param backupEligibility field value; null removes the value
         * @return this model
         */
        public Credential backupEligibility(Boolean backupEligibility) {
            set("backupEligibility", backupEligibility);
            return this;
        }
        /**
         * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
         * @param backupState field value; empty omits the value
         * @return this model
         */
        public Credential backupState(Optional<Boolean> backupState) {
            set("backupState", backupState.orElse(null));
            return this;
        }
        /**
         * Assertions returned by this credential will have the backup state (BS) flag set to this value. Defaults to the authenticator&#x27;s defaultBackupState value.
         * @param backupState field value; null removes the value
         * @return this model
         */
        public Credential backupState(Boolean backupState) {
            set("backupState", backupState);
            return this;
        }
        /**
         * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
         * @param userName field value; empty omits the value
         * @return this model
         */
        public Credential userName(Optional<String> userName) {
            set("userName", userName.orElse(null));
            return this;
        }
        /**
         * The credential&#x27;s user.name property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialentity-name
         * @param userName field value; null removes the value
         * @return this model
         */
        public Credential userName(String userName) {
            set("userName", userName);
            return this;
        }
        /**
         * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
         * @param userDisplayName field value; empty omits the value
         * @return this model
         */
        public Credential userDisplayName(Optional<String> userDisplayName) {
            set("userDisplayName", userDisplayName.orElse(null));
            return this;
        }
        /**
         * The credential&#x27;s user.displayName property. Equivalent to empty if not set. https://w3c.github.io/webauthn/#dom-publickeycredentialuserentity-displayname
         * @param userDisplayName field value; null removes the value
         * @return this model
         */
        public Credential userDisplayName(String userDisplayName) {
            set("userDisplayName", userDisplayName);
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
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("credential")))));
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
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("credential")))));
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
            return java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("credential")))));
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
        public CompletionStage<Void> enable(Optional<Boolean> enableUI) {
            Map<String, Object> params = new LinkedHashMap<>();
            enableUI.ifPresent(value_ -> params.put("enableUI", value_));
            return client.call("WebAuthn.enable", params, result_ -> null);
        }
        /**
         * Enable the WebAuthn domain and start intercepting credential storage and retrieval with a virtual authenticator.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return enable(Optional.empty());
        }
        /**
         * Disable the WebAuthn domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("WebAuthn.disable", null, result_ -> null);
        }
        /**
         * Creates and adds a virtual authenticator.
         * @param options protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAuthn.AuthenticatorId> addVirtualAuthenticator(WebAuthn.VirtualAuthenticatorOptions options) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("options", CdpObject.json(options));
            return client.call("WebAuthn.addVirtualAuthenticator", params, result_ -> new WebAuthn.AuthenticatorId((String) java.util.Objects.requireNonNull(result_.get("authenticatorId"))));
        }
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param authenticatorId protocol value
         * @param isBogusSignature protocol value
         * @param isBadUV protocol value
         * @param isBadUP protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setResponseOverrideBits(WebAuthn.AuthenticatorId authenticatorId, Optional<Boolean> isBogusSignature, Optional<Boolean> isBadUV, Optional<Boolean> isBadUP) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            isBogusSignature.ifPresent(value_ -> params.put("isBogusSignature", value_));
            isBadUV.ifPresent(value_ -> params.put("isBadUV", value_));
            isBadUP.ifPresent(value_ -> params.put("isBadUP", value_));
            return client.call("WebAuthn.setResponseOverrideBits", params, result_ -> null);
        }
        /**
         * Resets parameters isBogusSignature, isBadUV, isBadUP to false if they are not present.
         * @param authenticatorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setResponseOverrideBits(WebAuthn.AuthenticatorId authenticatorId) {
            return setResponseOverrideBits(authenticatorId, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Removes the given authenticator.
         * @param authenticatorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeVirtualAuthenticator(WebAuthn.AuthenticatorId authenticatorId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.removeVirtualAuthenticator", params, result_ -> null);
        }
        /**
         * Adds the credential to the specified authenticator.
         * @param authenticatorId protocol value
         * @param credential protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addCredential(WebAuthn.AuthenticatorId authenticatorId, WebAuthn.Credential credential) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credential", CdpObject.json(credential));
            return client.call("WebAuthn.addCredential", params, result_ -> null);
        }
        /**
         * Returns a single credential stored in the given virtual authenticator that matches the credential ID.
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAuthn.Credential> getCredential(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            return client.call("WebAuthn.getCredential", params, result_ -> java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("credential")))))));
        }
        /**
         * Returns all the credentials stored in the given virtual authenticator.
         * @param authenticatorId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<WebAuthn.Credential>> getCredentials(WebAuthn.AuthenticatorId authenticatorId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.getCredentials", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("credentials")), element0 -> java.util.Objects.requireNonNull(WebAuthn.Credential.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Removes a credential from the authenticator.
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeCredential(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            return client.call("WebAuthn.removeCredential", params, result_ -> null);
        }
        /**
         * Clears all the credentials from the specified device.
         * @param authenticatorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearCredentials(WebAuthn.AuthenticatorId authenticatorId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            return client.call("WebAuthn.clearCredentials", params, result_ -> null);
        }
        /**
         * Sets whether User Verification succeeds or fails for an authenticator. The default is true.
         * @param authenticatorId protocol value
         * @param isUserVerified protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserVerified(WebAuthn.AuthenticatorId authenticatorId, boolean isUserVerified) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("isUserVerified", CdpObject.json(isUserVerified));
            return client.call("WebAuthn.setUserVerified", params, result_ -> null);
        }
        /**
         * Sets whether tests of user presence will succeed immediately (if true) or fail to resolve (if false) for an authenticator. The default is true.
         * @param authenticatorId protocol value
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutomaticPresenceSimulation(WebAuthn.AuthenticatorId authenticatorId, boolean enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("enabled", CdpObject.json(enabled));
            return client.call("WebAuthn.setAutomaticPresenceSimulation", params, result_ -> null);
        }
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @param backupEligibility protocol value
         * @param backupState protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCredentialProperties(WebAuthn.AuthenticatorId authenticatorId, String credentialId, Optional<Boolean> backupEligibility, Optional<Boolean> backupState) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("authenticatorId", CdpObject.json(authenticatorId));
            params.put("credentialId", CdpObject.json(credentialId));
            backupEligibility.ifPresent(value_ -> params.put("backupEligibility", value_));
            backupState.ifPresent(value_ -> params.put("backupState", value_));
            return client.call("WebAuthn.setCredentialProperties", params, result_ -> null);
        }
        /**
         * Allows setting credential properties. https://w3c.github.io/webauthn/#sctn-automation-set-credential-properties
         * @param authenticatorId protocol value
         * @param credentialId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCredentialProperties(WebAuthn.AuthenticatorId authenticatorId, String credentialId) {
            return setCredentialProperties(authenticatorId, credentialId, Optional.empty(), Optional.empty());
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
