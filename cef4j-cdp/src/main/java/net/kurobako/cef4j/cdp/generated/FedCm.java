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
 * This domain allows interacting with the FedCM dialog.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/FedCm.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class FedCm {
    private FedCm() {}
    /**
     * Whether this is a sign-up or sign-in action for this account, i.e. whether this account has ever been used to sign in to this RP before.
     */
    public enum LoginState implements CdpValue<String> {
        SIGNIN("SignIn"),
        SIGNUP("SignUp");
        public final String value;
        LoginState(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static LoginState of(@Nonnull String value) {
            for (LoginState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown LoginState value: " + value);
        }
    }
    /**
     * The types of FedCM dialogs.
     */
    public enum DialogType implements CdpValue<String> {
        ACCOUNTCHOOSER("AccountChooser"),
        AUTOREAUTHN("AutoReauthn"),
        CONFIRMIDPLOGIN("ConfirmIdpLogin"),
        ERROR("Error");
        public final String value;
        DialogType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DialogType of(@Nonnull String value) {
            for (DialogType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DialogType value: " + value);
        }
    }
    /**
     * The buttons on the FedCM dialog.
     */
    public enum DialogButton implements CdpValue<String> {
        CONFIRMIDPLOGINCONTINUE("ConfirmIdpLoginContinue"),
        ERRORGOTIT("ErrorGotIt"),
        ERRORMOREDETAILS("ErrorMoreDetails");
        public final String value;
        DialogButton(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DialogButton of(@Nonnull String value) {
            for (DialogButton constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DialogButton value: " + value);
        }
    }
    /**
     * The URLs that each account has
     */
    public enum AccountUrlType implements CdpValue<String> {
        TERMSOFSERVICE("TermsOfService"),
        PRIVACYPOLICY("PrivacyPolicy");
        public final String value;
        AccountUrlType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static AccountUrlType of(@Nonnull String value) {
            for (AccountUrlType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AccountUrlType value: " + value);
        }
    }
    /**
     * Corresponds to IdentityRequestAccount
     */
    public static final class Account extends CdpObject {
        public Account() {}
        private Account(Map<String, Object> values) { super(values); }
        public static Account fromMap(Map<String, Object> values) {
            return new Account(values);
        }
        /**
         * Returns the accountId field.
         * @return the protocol field value
         */
        public String accountId() {
            return (String) require("accountId");
        }
        /**
         * Returns the email field.
         * @return the protocol field value
         */
        public String email() {
            return (String) require("email");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the givenName field.
         * @return the protocol field value
         */
        public String givenName() {
            return (String) require("givenName");
        }
        /**
         * Returns the pictureUrl field.
         * @return the protocol field value
         */
        public String pictureUrl() {
            return (String) require("pictureUrl");
        }
        /**
         * Returns the idpConfigUrl field.
         * @return the protocol field value
         */
        public String idpConfigUrl() {
            return (String) require("idpConfigUrl");
        }
        /**
         * Returns the idpLoginUrl field.
         * @return the protocol field value
         */
        public String idpLoginUrl() {
            return (String) require("idpLoginUrl");
        }
        /**
         * Returns the loginState field.
         * @return the protocol field value
         */
        public FedCm.LoginState loginState() {
            return FedCm.LoginState.of((String) require("loginState"));
        }
        /**
         * These two are only set if the loginState is signUp
         * @return the protocol field value, empty when absent
         */
        public Optional<String> termsOfServiceUrl() {
            return Optional.ofNullable((String) raw("termsOfServiceUrl"));
        }
        /**
         * Returns the privacyPolicyUrl field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> privacyPolicyUrl() {
            return Optional.ofNullable((String) raw("privacyPolicyUrl"));
        }
        /**
         * Sets the accountId field.
         * @param accountId field value
         * @return this model
         */
        public Account accountId(String accountId) {
            set("accountId", accountId);
            return this;
        }
        /**
         * Sets the email field.
         * @param email field value
         * @return this model
         */
        public Account email(String email) {
            set("email", email);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public Account name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the givenName field.
         * @param givenName field value
         * @return this model
         */
        public Account givenName(String givenName) {
            set("givenName", givenName);
            return this;
        }
        /**
         * Sets the pictureUrl field.
         * @param pictureUrl field value
         * @return this model
         */
        public Account pictureUrl(String pictureUrl) {
            set("pictureUrl", pictureUrl);
            return this;
        }
        /**
         * Sets the idpConfigUrl field.
         * @param idpConfigUrl field value
         * @return this model
         */
        public Account idpConfigUrl(String idpConfigUrl) {
            set("idpConfigUrl", idpConfigUrl);
            return this;
        }
        /**
         * Sets the idpLoginUrl field.
         * @param idpLoginUrl field value
         * @return this model
         */
        public Account idpLoginUrl(String idpLoginUrl) {
            set("idpLoginUrl", idpLoginUrl);
            return this;
        }
        /**
         * Sets the loginState field.
         * @param loginState field value
         * @return this model
         */
        public Account loginState(FedCm.LoginState loginState) {
            set("loginState", loginState);
            return this;
        }
        /**
         * These two are only set if the loginState is signUp
         * @param termsOfServiceUrl field value; empty omits the value
         * @return this model
         */
        public Account termsOfServiceUrl(Optional<String> termsOfServiceUrl) {
            set("termsOfServiceUrl", termsOfServiceUrl.orElse(null));
            return this;
        }
        /**
         * These two are only set if the loginState is signUp
         * @param termsOfServiceUrl field value; null removes the value
         * @return this model
         */
        public Account termsOfServiceUrl(String termsOfServiceUrl) {
            set("termsOfServiceUrl", termsOfServiceUrl);
            return this;
        }
        /**
         * Sets the privacyPolicyUrl field.
         * @param privacyPolicyUrl field value; empty omits the value
         * @return this model
         */
        public Account privacyPolicyUrl(Optional<String> privacyPolicyUrl) {
            set("privacyPolicyUrl", privacyPolicyUrl.orElse(null));
            return this;
        }
        /**
         * Sets the privacyPolicyUrl field.
         * @param privacyPolicyUrl field value; null removes the value
         * @return this model
         */
        public Account privacyPolicyUrl(String privacyPolicyUrl) {
            set("privacyPolicyUrl", privacyPolicyUrl);
            return this;
        }
    }
    /**
     * Request parameters for FedCm.enable.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Allows callers to disable the promise rejection delay that would normally happen, if this is unimportant to what&#x27;s being tested. (step 4 of https://fedidcg.github.io/FedCM/#browser-api-rp-sign-in)
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> disableRejectionDelay() {
            return Optional.ofNullable((Boolean) raw("disableRejectionDelay"));
        }
        /**
         * Allows callers to disable the promise rejection delay that would normally happen, if this is unimportant to what&#x27;s being tested. (step 4 of https://fedidcg.github.io/FedCM/#browser-api-rp-sign-in)
         * @param disableRejectionDelay field value; empty omits the value
         * @return this model
         */
        public EnableRequest disableRejectionDelay(Optional<Boolean> disableRejectionDelay) {
            set("disableRejectionDelay", disableRejectionDelay.orElse(null));
            return this;
        }
        /**
         * Allows callers to disable the promise rejection delay that would normally happen, if this is unimportant to what&#x27;s being tested. (step 4 of https://fedidcg.github.io/FedCM/#browser-api-rp-sign-in)
         * @param disableRejectionDelay field value; null removes the value
         * @return this model
         */
        public EnableRequest disableRejectionDelay(Boolean disableRejectionDelay) {
            set("disableRejectionDelay", disableRejectionDelay);
            return this;
        }
    }
    /**
     * Request parameters for FedCm.selectAccount.
     */
    public static final class SelectAccountRequest extends CdpObject {
        public SelectAccountRequest() {}
        /**
         * Creates a new SelectAccountRequest with all required parameters.
         * @param dialogId protocol value
         * @param accountIndex protocol value
         */
        public SelectAccountRequest(String dialogId, long accountIndex) {
            set("dialogId", dialogId);
            set("accountIndex", accountIndex);
        }
        public static SelectAccountRequest fromMap(Map<String, Object> values) {
            SelectAccountRequest instance_ = new SelectAccountRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        public String dialogId() {
            return (String) require("dialogId");
        }
        /**
         * Returns the accountIndex field.
         * @return the protocol field value
         */
        public long accountIndex() {
            return ((Number) require("accountIndex")).longValue();
        }
        /**
         * Sets the dialogId field.
         * @param dialogId field value
         * @return this model
         */
        public SelectAccountRequest dialogId(String dialogId) {
            set("dialogId", dialogId);
            return this;
        }
        /**
         * Sets the accountIndex field.
         * @param accountIndex field value
         * @return this model
         */
        public SelectAccountRequest accountIndex(long accountIndex) {
            set("accountIndex", accountIndex);
            return this;
        }
    }
    /**
     * Request parameters for FedCm.clickDialogButton.
     */
    public static final class ClickDialogButtonRequest extends CdpObject {
        public ClickDialogButtonRequest() {}
        /**
         * Creates a new ClickDialogButtonRequest with all required parameters.
         * @param dialogId protocol value
         * @param dialogButton protocol value
         */
        public ClickDialogButtonRequest(String dialogId, FedCm.DialogButton dialogButton) {
            set("dialogId", dialogId);
            set("dialogButton", dialogButton);
        }
        public static ClickDialogButtonRequest fromMap(Map<String, Object> values) {
            ClickDialogButtonRequest instance_ = new ClickDialogButtonRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        public String dialogId() {
            return (String) require("dialogId");
        }
        /**
         * Returns the dialogButton field.
         * @return the protocol field value
         */
        public FedCm.DialogButton dialogButton() {
            return FedCm.DialogButton.of((String) require("dialogButton"));
        }
        /**
         * Sets the dialogId field.
         * @param dialogId field value
         * @return this model
         */
        public ClickDialogButtonRequest dialogId(String dialogId) {
            set("dialogId", dialogId);
            return this;
        }
        /**
         * Sets the dialogButton field.
         * @param dialogButton field value
         * @return this model
         */
        public ClickDialogButtonRequest dialogButton(FedCm.DialogButton dialogButton) {
            set("dialogButton", dialogButton);
            return this;
        }
    }
    /**
     * Request parameters for FedCm.openUrl.
     */
    public static final class OpenUrlRequest extends CdpObject {
        public OpenUrlRequest() {}
        /**
         * Creates a new OpenUrlRequest with all required parameters.
         * @param dialogId protocol value
         * @param accountIndex protocol value
         * @param accountUrlType protocol value
         */
        public OpenUrlRequest(String dialogId, long accountIndex, FedCm.AccountUrlType accountUrlType) {
            set("dialogId", dialogId);
            set("accountIndex", accountIndex);
            set("accountUrlType", accountUrlType);
        }
        public static OpenUrlRequest fromMap(Map<String, Object> values) {
            OpenUrlRequest instance_ = new OpenUrlRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        public String dialogId() {
            return (String) require("dialogId");
        }
        /**
         * Returns the accountIndex field.
         * @return the protocol field value
         */
        public long accountIndex() {
            return ((Number) require("accountIndex")).longValue();
        }
        /**
         * Returns the accountUrlType field.
         * @return the protocol field value
         */
        public FedCm.AccountUrlType accountUrlType() {
            return FedCm.AccountUrlType.of((String) require("accountUrlType"));
        }
        /**
         * Sets the dialogId field.
         * @param dialogId field value
         * @return this model
         */
        public OpenUrlRequest dialogId(String dialogId) {
            set("dialogId", dialogId);
            return this;
        }
        /**
         * Sets the accountIndex field.
         * @param accountIndex field value
         * @return this model
         */
        public OpenUrlRequest accountIndex(long accountIndex) {
            set("accountIndex", accountIndex);
            return this;
        }
        /**
         * Sets the accountUrlType field.
         * @param accountUrlType field value
         * @return this model
         */
        public OpenUrlRequest accountUrlType(FedCm.AccountUrlType accountUrlType) {
            set("accountUrlType", accountUrlType);
            return this;
        }
    }
    /**
     * Request parameters for FedCm.dismissDialog.
     */
    public static final class DismissDialogRequest extends CdpObject {
        public DismissDialogRequest() {}
        /**
         * Creates a new DismissDialogRequest with all required parameters.
         * @param dialogId protocol value
         */
        public DismissDialogRequest(String dialogId) {
            set("dialogId", dialogId);
        }
        public static DismissDialogRequest fromMap(Map<String, Object> values) {
            DismissDialogRequest instance_ = new DismissDialogRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        public String dialogId() {
            return (String) require("dialogId");
        }
        /**
         * Returns the triggerCooldown field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> triggerCooldown() {
            return Optional.ofNullable((Boolean) raw("triggerCooldown"));
        }
        /**
         * Sets the dialogId field.
         * @param dialogId field value
         * @return this model
         */
        public DismissDialogRequest dialogId(String dialogId) {
            set("dialogId", dialogId);
            return this;
        }
        /**
         * Sets the triggerCooldown field.
         * @param triggerCooldown field value; empty omits the value
         * @return this model
         */
        public DismissDialogRequest triggerCooldown(Optional<Boolean> triggerCooldown) {
            set("triggerCooldown", triggerCooldown.orElse(null));
            return this;
        }
        /**
         * Sets the triggerCooldown field.
         * @param triggerCooldown field value; null removes the value
         * @return this model
         */
        public DismissDialogRequest triggerCooldown(Boolean triggerCooldown) {
            set("triggerCooldown", triggerCooldown);
            return this;
        }
    }
    /**
     * Payload of the FedCm.dialogShown event.
     */
    public static final class DialogShownEvent extends CdpObject {
        public DialogShownEvent() {}
        private DialogShownEvent(Map<String, Object> values) { super(values); }
        public static DialogShownEvent fromMap(Map<String, Object> values) {
            return new DialogShownEvent(values);
        }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        public String dialogId() {
            return (String) require("dialogId");
        }
        /**
         * Returns the dialogType field.
         * @return the protocol field value
         */
        public FedCm.DialogType dialogType() {
            return FedCm.DialogType.of((String) require("dialogType"));
        }
        /**
         * Returns the accounts field.
         * @return the protocol field value
         */
        public java.util.List<FedCm.Account> accounts() {
            return CdpObject.requireList(require("accounts"), element0 -> java.util.Objects.requireNonNull(FedCm.Account.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * These exist primarily so that the caller can verify the RP context was used appropriately.
         * @return the protocol field value
         */
        public String title() {
            return (String) require("title");
        }
        /**
         * Returns the subtitle field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> subtitle() {
            return Optional.ofNullable((String) raw("subtitle"));
        }
        /**
         * Sets the dialogId field.
         * @param dialogId field value
         * @return this model
         */
        public DialogShownEvent dialogId(String dialogId) {
            set("dialogId", dialogId);
            return this;
        }
        /**
         * Sets the dialogType field.
         * @param dialogType field value
         * @return this model
         */
        public DialogShownEvent dialogType(FedCm.DialogType dialogType) {
            set("dialogType", dialogType);
            return this;
        }
        /**
         * Sets the accounts field.
         * @param accounts field value
         * @return this model
         */
        public DialogShownEvent accounts(java.util.List<FedCm.Account> accounts) {
            set("accounts", accounts);
            return this;
        }
        /**
         * These exist primarily so that the caller can verify the RP context was used appropriately.
         * @param title field value
         * @return this model
         */
        public DialogShownEvent title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Sets the subtitle field.
         * @param subtitle field value; empty omits the value
         * @return this model
         */
        public DialogShownEvent subtitle(Optional<String> subtitle) {
            set("subtitle", subtitle.orElse(null));
            return this;
        }
        /**
         * Sets the subtitle field.
         * @param subtitle field value; null removes the value
         * @return this model
         */
        public DialogShownEvent subtitle(String subtitle) {
            set("subtitle", subtitle);
            return this;
        }
    }
    /**
     * Triggered when a dialog is closed, either by user action, JS abort, or a command below.
     */
    public static final class DialogClosedEvent extends CdpObject {
        public DialogClosedEvent() {}
        private DialogClosedEvent(Map<String, Object> values) { super(values); }
        public static DialogClosedEvent fromMap(Map<String, Object> values) {
            return new DialogClosedEvent(values);
        }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        public String dialogId() {
            return (String) require("dialogId");
        }
        /**
         * Sets the dialogId field.
         * @param dialogId field value
         * @return this model
         */
        public DialogClosedEvent dialogId(String dialogId) {
            set("dialogId", dialogId);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes FedCm.enable.
         * @param disableRejectionDelay protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(Optional<Boolean> disableRejectionDelay) {
            Map<String, Object> params = new LinkedHashMap<>();
            disableRejectionDelay.ifPresent(value_ -> params.put("disableRejectionDelay", value_));
            return client.call("FedCm.enable", params, result_ -> null);
        }
        /**
         * Invokes FedCm.enable with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return enable(Optional.empty());
        }
        /**
         * Invokes FedCm.enable with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("FedCm.enable", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes FedCm.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("FedCm.disable", null, result_ -> null);
        }
        /**
         * Invokes FedCm.selectAccount.
         * @param dialogId protocol value
         * @param accountIndex protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> selectAccount(String dialogId, long accountIndex) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("dialogId", CdpObject.json(dialogId));
            params.put("accountIndex", CdpObject.json(accountIndex));
            return client.call("FedCm.selectAccount", params, result_ -> null);
        }
        /**
         * Invokes FedCm.selectAccount with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> selectAccount(SelectAccountRequest request) {
            return client.call("FedCm.selectAccount", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes FedCm.clickDialogButton.
         * @param dialogId protocol value
         * @param dialogButton protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clickDialogButton(String dialogId, FedCm.DialogButton dialogButton) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("dialogId", CdpObject.json(dialogId));
            params.put("dialogButton", CdpObject.json(dialogButton));
            return client.call("FedCm.clickDialogButton", params, result_ -> null);
        }
        /**
         * Invokes FedCm.clickDialogButton with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clickDialogButton(ClickDialogButtonRequest request) {
            return client.call("FedCm.clickDialogButton", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes FedCm.openUrl.
         * @param dialogId protocol value
         * @param accountIndex protocol value
         * @param accountUrlType protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> openUrl(String dialogId, long accountIndex, FedCm.AccountUrlType accountUrlType) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("dialogId", CdpObject.json(dialogId));
            params.put("accountIndex", CdpObject.json(accountIndex));
            params.put("accountUrlType", CdpObject.json(accountUrlType));
            return client.call("FedCm.openUrl", params, result_ -> null);
        }
        /**
         * Invokes FedCm.openUrl with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> openUrl(OpenUrlRequest request) {
            return client.call("FedCm.openUrl", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes FedCm.dismissDialog.
         * @param dialogId protocol value
         * @param triggerCooldown protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dismissDialog(String dialogId, Optional<Boolean> triggerCooldown) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("dialogId", CdpObject.json(dialogId));
            triggerCooldown.ifPresent(value_ -> params.put("triggerCooldown", value_));
            return client.call("FedCm.dismissDialog", params, result_ -> null);
        }
        /**
         * Invokes FedCm.dismissDialog with the required parameters.
         * @param dialogId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dismissDialog(String dialogId) {
            return dismissDialog(dialogId, Optional.empty());
        }
        /**
         * Invokes FedCm.dismissDialog with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dismissDialog(DismissDialogRequest request) {
            return client.call("FedCm.dismissDialog", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Resets the cooldown time, if any, to allow the next FedCM call to show a dialog even if one was recently dismissed by the user.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resetCooldown() {
            return client.call("FedCm.resetCooldown", null, result_ -> null);
        }
        /**
         * Subscribes to FedCm.dialogShown.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDialogShown(Consumer<DialogShownEvent> handler) {
            return client.on("FedCm.dialogShown", DialogShownEvent::fromMap, handler);
        }
        /**
         * Triggered when a dialog is closed, either by user action, JS abort, or a command below.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDialogClosed(Consumer<DialogClosedEvent> handler) {
            return client.on("FedCm.dialogClosed", DialogClosedEvent::fromMap, handler);
        }
    }
}
