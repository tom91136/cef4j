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
 * This domain allows interacting with the FedCM dialog.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/FedCm.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class FedCm {
    private FedCm() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Whether this is a sign-up or sign-in action for this account, i.e. whether this account has ever been used to sign in to this RP before.
     */
    public static final class LoginState {
        private LoginState() {}
        public static final String SIGNIN = "SignIn";
        public static final String SIGNUP = "SignUp";
    }
    /**
     * The types of FedCM dialogs.
     */
    public static final class DialogType {
        private DialogType() {}
        public static final String ACCOUNTCHOOSER = "AccountChooser";
        public static final String AUTOREAUTHN = "AutoReauthn";
        public static final String CONFIRMIDPLOGIN = "ConfirmIdpLogin";
        public static final String ERROR = "Error";
    }
    /**
     * The buttons on the FedCM dialog.
     */
    public static final class DialogButton {
        private DialogButton() {}
        public static final String CONFIRMIDPLOGINCONTINUE = "ConfirmIdpLoginContinue";
        public static final String ERRORGOTIT = "ErrorGotIt";
        public static final String ERRORMOREDETAILS = "ErrorMoreDetails";
    }
    /**
     * The URLs that each account has
     */
    public static final class AccountUrlType {
        private AccountUrlType() {}
        public static final String TERMSOFSERVICE = "TermsOfService";
        public static final String PRIVACYPOLICY = "PrivacyPolicy";
    }
    /**
     * Corresponds to IdentityRequestAccount
     */
    public static final class Account extends CdpObject {
        private Account(Map<String, Object> values) { super(values); }
        @Nullable public static Account fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Account(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the accountId field.
         * @return the protocol field value
         */
        @Nullable public String accountId() {
            return (String) value("accountId");
        }
        /**
         * Returns the email field.
         * @return the protocol field value
         */
        @Nullable public String email() {
            return (String) value("email");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the givenName field.
         * @return the protocol field value
         */
        @Nullable public String givenName() {
            return (String) value("givenName");
        }
        /**
         * Returns the pictureUrl field.
         * @return the protocol field value
         */
        @Nullable public String pictureUrl() {
            return (String) value("pictureUrl");
        }
        /**
         * Returns the idpConfigUrl field.
         * @return the protocol field value
         */
        @Nullable public String idpConfigUrl() {
            return (String) value("idpConfigUrl");
        }
        /**
         * Returns the idpLoginUrl field.
         * @return the protocol field value
         */
        @Nullable public String idpLoginUrl() {
            return (String) value("idpLoginUrl");
        }
        /**
         * Returns the loginState field.
         * @return the protocol field value
         */
        @Nullable public String loginState() {
            return (String) value("loginState");
        }
        /**
         * These two are only set if the loginState is signUp
         * @return the protocol field value
         */
        @Nullable public String termsOfServiceUrl() {
            return (String) value("termsOfServiceUrl");
        }
        /**
         * Returns the privacyPolicyUrl field.
         * @return the protocol field value
         */
        @Nullable public String privacyPolicyUrl() {
            return (String) value("privacyPolicyUrl");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the accountId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accountId(@Nullable String value) {
                if (value == null) values.remove("accountId");
                else values.put("accountId", jsonValue(value));
                return this;
            }
            /**
             * Sets the email field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder email(@Nullable String value) {
                if (value == null) values.remove("email");
                else values.put("email", jsonValue(value));
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
             * Sets the givenName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder givenName(@Nullable String value) {
                if (value == null) values.remove("givenName");
                else values.put("givenName", jsonValue(value));
                return this;
            }
            /**
             * Sets the pictureUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pictureUrl(@Nullable String value) {
                if (value == null) values.remove("pictureUrl");
                else values.put("pictureUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the idpConfigUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder idpConfigUrl(@Nullable String value) {
                if (value == null) values.remove("idpConfigUrl");
                else values.put("idpConfigUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the idpLoginUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder idpLoginUrl(@Nullable String value) {
                if (value == null) values.remove("idpLoginUrl");
                else values.put("idpLoginUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the loginState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loginState(@Nullable String value) {
                if (value == null) values.remove("loginState");
                else values.put("loginState", jsonValue(value));
                return this;
            }
            /**
             * These two are only set if the loginState is signUp
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder termsOfServiceUrl(@Nullable String value) {
                if (value == null) values.remove("termsOfServiceUrl");
                else values.put("termsOfServiceUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the privacyPolicyUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder privacyPolicyUrl(@Nullable String value) {
                if (value == null) values.remove("privacyPolicyUrl");
                else values.put("privacyPolicyUrl", jsonValue(value));
                return this;
            }
            public Account build() {
                if (!values.containsKey("accountId")) throw new IllegalStateException("Missing required CDP field: accountId");
                if (!values.containsKey("email")) throw new IllegalStateException("Missing required CDP field: email");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("givenName")) throw new IllegalStateException("Missing required CDP field: givenName");
                if (!values.containsKey("pictureUrl")) throw new IllegalStateException("Missing required CDP field: pictureUrl");
                if (!values.containsKey("idpConfigUrl")) throw new IllegalStateException("Missing required CDP field: idpConfigUrl");
                if (!values.containsKey("idpLoginUrl")) throw new IllegalStateException("Missing required CDP field: idpLoginUrl");
                if (!values.containsKey("loginState")) throw new IllegalStateException("Missing required CDP field: loginState");
                return new Account(values);
            }
        }
    }
    /**
     * Parameters for FedCm.enable.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Allows callers to disable the promise rejection delay that would normally happen, if this is unimportant to what&#x27;s being tested. (step 4 of https://fedidcg.github.io/FedCM/#browser-api-rp-sign-in)
         * @return the protocol field value
         */
        @Nullable public Boolean disableRejectionDelay() {
            return (Boolean) value("disableRejectionDelay");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Allows callers to disable the promise rejection delay that would normally happen, if this is unimportant to what&#x27;s being tested. (step 4 of https://fedidcg.github.io/FedCM/#browser-api-rp-sign-in)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disableRejectionDelay(@Nullable Boolean value) {
                if (value == null) values.remove("disableRejectionDelay");
                else values.put("disableRejectionDelay", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Result of FedCm.enable.
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
     * Parameters for FedCm.disable.
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
     * Result of FedCm.disable.
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
     * Parameters for FedCm.selectAccount.
     */
    public static final class SelectAccountParams extends CdpObject {
        private SelectAccountParams(Map<String, Object> values) { super(values); }
        @Nullable public static SelectAccountParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SelectAccountParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        @Nullable public String dialogId() {
            return (String) value("dialogId");
        }
        /**
         * Returns the accountIndex field.
         * @return the protocol field value
         */
        @Nullable public Long accountIndex() {
            return numberAsLong(value("accountIndex"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dialogId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogId(@Nullable String value) {
                if (value == null) values.remove("dialogId");
                else values.put("dialogId", jsonValue(value));
                return this;
            }
            /**
             * Sets the accountIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accountIndex(@Nullable Long value) {
                if (value == null) values.remove("accountIndex");
                else values.put("accountIndex", jsonValue(value));
                return this;
            }
            public SelectAccountParams build() {
                if (!values.containsKey("dialogId")) throw new IllegalStateException("Missing required CDP field: dialogId");
                if (!values.containsKey("accountIndex")) throw new IllegalStateException("Missing required CDP field: accountIndex");
                return new SelectAccountParams(values);
            }
        }
    }
    /**
     * Result of FedCm.selectAccount.
     */
    public static final class SelectAccountResult extends CdpObject {
        private SelectAccountResult(Map<String, Object> values) { super(values); }
        @Nullable public static SelectAccountResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SelectAccountResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SelectAccountResult build() {
                return new SelectAccountResult(values);
            }
        }
    }
    /**
     * Parameters for FedCm.clickDialogButton.
     */
    public static final class ClickDialogButtonParams extends CdpObject {
        private ClickDialogButtonParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClickDialogButtonParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClickDialogButtonParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        @Nullable public String dialogId() {
            return (String) value("dialogId");
        }
        /**
         * Returns the dialogButton field.
         * @return the protocol field value
         */
        @Nullable public String dialogButton() {
            return (String) value("dialogButton");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dialogId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogId(@Nullable String value) {
                if (value == null) values.remove("dialogId");
                else values.put("dialogId", jsonValue(value));
                return this;
            }
            /**
             * Sets the dialogButton field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogButton(@Nullable String value) {
                if (value == null) values.remove("dialogButton");
                else values.put("dialogButton", jsonValue(value));
                return this;
            }
            public ClickDialogButtonParams build() {
                if (!values.containsKey("dialogId")) throw new IllegalStateException("Missing required CDP field: dialogId");
                if (!values.containsKey("dialogButton")) throw new IllegalStateException("Missing required CDP field: dialogButton");
                return new ClickDialogButtonParams(values);
            }
        }
    }
    /**
     * Result of FedCm.clickDialogButton.
     */
    public static final class ClickDialogButtonResult extends CdpObject {
        private ClickDialogButtonResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClickDialogButtonResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClickDialogButtonResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClickDialogButtonResult build() {
                return new ClickDialogButtonResult(values);
            }
        }
    }
    /**
     * Parameters for FedCm.openUrl.
     */
    public static final class OpenUrlParams extends CdpObject {
        private OpenUrlParams(Map<String, Object> values) { super(values); }
        @Nullable public static OpenUrlParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OpenUrlParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        @Nullable public String dialogId() {
            return (String) value("dialogId");
        }
        /**
         * Returns the accountIndex field.
         * @return the protocol field value
         */
        @Nullable public Long accountIndex() {
            return numberAsLong(value("accountIndex"));
        }
        /**
         * Returns the accountUrlType field.
         * @return the protocol field value
         */
        @Nullable public String accountUrlType() {
            return (String) value("accountUrlType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dialogId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogId(@Nullable String value) {
                if (value == null) values.remove("dialogId");
                else values.put("dialogId", jsonValue(value));
                return this;
            }
            /**
             * Sets the accountIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accountIndex(@Nullable Long value) {
                if (value == null) values.remove("accountIndex");
                else values.put("accountIndex", jsonValue(value));
                return this;
            }
            /**
             * Sets the accountUrlType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accountUrlType(@Nullable String value) {
                if (value == null) values.remove("accountUrlType");
                else values.put("accountUrlType", jsonValue(value));
                return this;
            }
            public OpenUrlParams build() {
                if (!values.containsKey("dialogId")) throw new IllegalStateException("Missing required CDP field: dialogId");
                if (!values.containsKey("accountIndex")) throw new IllegalStateException("Missing required CDP field: accountIndex");
                if (!values.containsKey("accountUrlType")) throw new IllegalStateException("Missing required CDP field: accountUrlType");
                return new OpenUrlParams(values);
            }
        }
    }
    /**
     * Result of FedCm.openUrl.
     */
    public static final class OpenUrlResult extends CdpObject {
        private OpenUrlResult(Map<String, Object> values) { super(values); }
        @Nullable public static OpenUrlResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OpenUrlResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public OpenUrlResult build() {
                return new OpenUrlResult(values);
            }
        }
    }
    /**
     * Parameters for FedCm.dismissDialog.
     */
    public static final class DismissDialogParams extends CdpObject {
        private DismissDialogParams(Map<String, Object> values) { super(values); }
        @Nullable public static DismissDialogParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DismissDialogParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        @Nullable public String dialogId() {
            return (String) value("dialogId");
        }
        /**
         * Returns the triggerCooldown field.
         * @return the protocol field value
         */
        @Nullable public Boolean triggerCooldown() {
            return (Boolean) value("triggerCooldown");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dialogId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogId(@Nullable String value) {
                if (value == null) values.remove("dialogId");
                else values.put("dialogId", jsonValue(value));
                return this;
            }
            /**
             * Sets the triggerCooldown field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder triggerCooldown(@Nullable Boolean value) {
                if (value == null) values.remove("triggerCooldown");
                else values.put("triggerCooldown", jsonValue(value));
                return this;
            }
            public DismissDialogParams build() {
                if (!values.containsKey("dialogId")) throw new IllegalStateException("Missing required CDP field: dialogId");
                return new DismissDialogParams(values);
            }
        }
    }
    /**
     * Result of FedCm.dismissDialog.
     */
    public static final class DismissDialogResult extends CdpObject {
        private DismissDialogResult(Map<String, Object> values) { super(values); }
        @Nullable public static DismissDialogResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DismissDialogResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DismissDialogResult build() {
                return new DismissDialogResult(values);
            }
        }
    }
    /**
     * Resets the cooldown time, if any, to allow the next FedCM call to show a dialog even if one was recently dismissed by the user.
     */
    public static final class ResetCooldownParams extends CdpObject {
        private ResetCooldownParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResetCooldownParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetCooldownParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetCooldownParams build() {
                return new ResetCooldownParams(values);
            }
        }
    }
    /**
     * Resets the cooldown time, if any, to allow the next FedCM call to show a dialog even if one was recently dismissed by the user.
     */
    public static final class ResetCooldownResult extends CdpObject {
        private ResetCooldownResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResetCooldownResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetCooldownResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetCooldownResult build() {
                return new ResetCooldownResult(values);
            }
        }
    }
    /**
     * Payload of the FedCm.dialogShown event.
     */
    public static final class DialogShownEvent extends CdpObject {
        private DialogShownEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DialogShownEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DialogShownEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        @Nullable public String dialogId() {
            return (String) value("dialogId");
        }
        /**
         * Returns the dialogType field.
         * @return the protocol field value
         */
        @Nullable public String dialogType() {
            return (String) value("dialogType");
        }
        /**
         * Returns the accounts field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<FedCm.Account> accounts() {
            return list(value("accounts"), element0 -> FedCm.Account.fromMap(objectMap(element0)));
        }
        /**
         * These exist primarily so that the caller can verify the RP context was used appropriately.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * Returns the subtitle field.
         * @return the protocol field value
         */
        @Nullable public String subtitle() {
            return (String) value("subtitle");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dialogId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogId(@Nullable String value) {
                if (value == null) values.remove("dialogId");
                else values.put("dialogId", jsonValue(value));
                return this;
            }
            /**
             * Sets the dialogType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogType(@Nullable String value) {
                if (value == null) values.remove("dialogType");
                else values.put("dialogType", jsonValue(value));
                return this;
            }
            /**
             * Sets the accounts field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accounts(@Nullable java.util.List<FedCm.Account> value) {
                if (value == null) values.remove("accounts");
                else values.put("accounts", jsonValue(value));
                return this;
            }
            /**
             * These exist primarily so that the caller can verify the RP context was used appropriately.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Sets the subtitle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subtitle(@Nullable String value) {
                if (value == null) values.remove("subtitle");
                else values.put("subtitle", jsonValue(value));
                return this;
            }
            public DialogShownEvent build() {
                if (!values.containsKey("dialogId")) throw new IllegalStateException("Missing required CDP field: dialogId");
                if (!values.containsKey("dialogType")) throw new IllegalStateException("Missing required CDP field: dialogType");
                if (!values.containsKey("accounts")) throw new IllegalStateException("Missing required CDP field: accounts");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                return new DialogShownEvent(values);
            }
        }
    }
    /**
     * Triggered when a dialog is closed, either by user action, JS abort, or a command below.
     */
    public static final class DialogClosedEvent extends CdpObject {
        private DialogClosedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DialogClosedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DialogClosedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the dialogId field.
         * @return the protocol field value
         */
        @Nullable public String dialogId() {
            return (String) value("dialogId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the dialogId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dialogId(@Nullable String value) {
                if (value == null) values.remove("dialogId");
                else values.put("dialogId", jsonValue(value));
                return this;
            }
            public DialogClosedEvent build() {
                if (!values.containsKey("dialogId")) throw new IllegalStateException("Missing required CDP field: dialogId");
                return new DialogClosedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes FedCm.enable.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("FedCm.enable", params, EnableResult::fromMap);
        }
        /**
         * Invokes FedCm.enable with default parameters.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return enable(EnableParams.builder().build());
        }
        /**
         * Invokes FedCm.disable.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("FedCm.disable", null, DisableResult::fromMap);
        }
        /**
         * Invokes FedCm.selectAccount.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SelectAccountResult> selectAccount(SelectAccountParams params) {
            return client.call("FedCm.selectAccount", params, SelectAccountResult::fromMap);
        }
        /**
         * Invokes FedCm.clickDialogButton.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClickDialogButtonResult> clickDialogButton(ClickDialogButtonParams params) {
            return client.call("FedCm.clickDialogButton", params, ClickDialogButtonResult::fromMap);
        }
        /**
         * Invokes FedCm.openUrl.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<OpenUrlResult> openUrl(OpenUrlParams params) {
            return client.call("FedCm.openUrl", params, OpenUrlResult::fromMap);
        }
        /**
         * Invokes FedCm.dismissDialog.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DismissDialogResult> dismissDialog(DismissDialogParams params) {
            return client.call("FedCm.dismissDialog", params, DismissDialogResult::fromMap);
        }
        /**
         * Resets the cooldown time, if any, to allow the next FedCM call to show a dialog even if one was recently dismissed by the user.
         * @return a stage completing with the command result
         */
        public CompletionStage<ResetCooldownResult> resetCooldown() {
            return client.call("FedCm.resetCooldown", null, ResetCooldownResult::fromMap);
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
