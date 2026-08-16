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
 * Defines commands and events for Autofill.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Autofill.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Autofill {
    private Autofill() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     */
    public static final class CreditCard extends CdpObject {
        private CreditCard(Map<String, Object> values) { super(values); }
        @Nullable public static CreditCard fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreditCard(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * 16-digit credit card number.
         * @return the protocol field value
         */
        @Nullable public String number() {
            return (String) value("number");
        }
        /**
         * Name of the credit card owner.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * 2-digit expiry month.
         * @return the protocol field value
         */
        @Nullable public String expiryMonth() {
            return (String) value("expiryMonth");
        }
        /**
         * 4-digit expiry year.
         * @return the protocol field value
         */
        @Nullable public String expiryYear() {
            return (String) value("expiryYear");
        }
        /**
         * 3-digit card verification code.
         * @return the protocol field value
         */
        @Nullable public String cvc() {
            return (String) value("cvc");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * 16-digit credit card number.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder number(@Nullable String value) {
                if (value == null) values.remove("number");
                else values.put("number", jsonValue(value));
                return this;
            }
            /**
             * Name of the credit card owner.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * 2-digit expiry month.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expiryMonth(@Nullable String value) {
                if (value == null) values.remove("expiryMonth");
                else values.put("expiryMonth", jsonValue(value));
                return this;
            }
            /**
             * 4-digit expiry year.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expiryYear(@Nullable String value) {
                if (value == null) values.remove("expiryYear");
                else values.put("expiryYear", jsonValue(value));
                return this;
            }
            /**
             * 3-digit card verification code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cvc(@Nullable String value) {
                if (value == null) values.remove("cvc");
                else values.put("cvc", jsonValue(value));
                return this;
            }
            public CreditCard build() {
                if (!values.containsKey("number")) throw new IllegalStateException("Missing required CDP field: number");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("expiryMonth")) throw new IllegalStateException("Missing required CDP field: expiryMonth");
                if (!values.containsKey("expiryYear")) throw new IllegalStateException("Missing required CDP field: expiryYear");
                if (!values.containsKey("cvc")) throw new IllegalStateException("Missing required CDP field: cvc");
                return new CreditCard(values);
            }
        }
    }
    /**
     */
    public static final class AddressField extends CdpObject {
        private AddressField(Map<String, Object> values) { super(values); }
        @Nullable public static AddressField fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddressField(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * address field name, for example GIVEN_NAME. The full list of supported field names: https://source.chromium.org/chromium/chromium/src/+/main:components/autofill/core/browser/field_types.cc;l=38
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * address field value, for example Jon Doe.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * address field name, for example GIVEN_NAME. The full list of supported field names: https://source.chromium.org/chromium/chromium/src/+/main:components/autofill/core/browser/field_types.cc;l=38
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * address field value, for example Jon Doe.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public AddressField build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new AddressField(values);
            }
        }
    }
    /**
     * A list of address fields.
     */
    public static final class AddressFields extends CdpObject {
        private AddressFields(Map<String, Object> values) { super(values); }
        @Nullable public static AddressFields fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddressFields(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the fields field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Autofill.AddressField> fields() {
            return list(value("fields"), element0 -> Autofill.AddressField.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the fields field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fields(@Nullable java.util.List<Autofill.AddressField> value) {
                if (value == null) values.remove("fields");
                else values.put("fields", jsonValue(value));
                return this;
            }
            public AddressFields build() {
                if (!values.containsKey("fields")) throw new IllegalStateException("Missing required CDP field: fields");
                return new AddressFields(values);
            }
        }
    }
    /**
     */
    public static final class Address extends CdpObject {
        private Address(Map<String, Object> values) { super(values); }
        @Nullable public static Address fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Address(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * fields and values defining an address.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Autofill.AddressField> fields() {
            return list(value("fields"), element0 -> Autofill.AddressField.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * fields and values defining an address.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fields(@Nullable java.util.List<Autofill.AddressField> value) {
                if (value == null) values.remove("fields");
                else values.put("fields", jsonValue(value));
                return this;
            }
            public Address build() {
                if (!values.containsKey("fields")) throw new IllegalStateException("Missing required CDP field: fields");
                return new Address(values);
            }
        }
    }
    /**
     * Defines how an address can be displayed like in chrome://settings/addresses. Address UI is a two dimensional array, each inner array is an &quot;address information line&quot;, and when rendered in a UI surface should be displayed as such. The following address UI for instance: [[{name: &quot;GIVE_NAME&quot;, value: &quot;Jon&quot;}, {name: &quot;FAMILY_NAME&quot;, value: &quot;Doe&quot;}], [{name: &quot;CITY&quot;, value: &quot;Munich&quot;}, {name: &quot;ZIP&quot;, value: &quot;81456&quot;}]] should allow the receiver to render: Jon Doe Munich 81456
     */
    public static final class AddressUI extends CdpObject {
        private AddressUI(Map<String, Object> values) { super(values); }
        @Nullable public static AddressUI fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddressUI(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A two dimension array containing the representation of values from an address profile.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Autofill.AddressFields> addressFields() {
            return list(value("addressFields"), element0 -> Autofill.AddressFields.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A two dimension array containing the representation of values from an address profile.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder addressFields(@Nullable java.util.List<Autofill.AddressFields> value) {
                if (value == null) values.remove("addressFields");
                else values.put("addressFields", jsonValue(value));
                return this;
            }
            public AddressUI build() {
                if (!values.containsKey("addressFields")) throw new IllegalStateException("Missing required CDP field: addressFields");
                return new AddressUI(values);
            }
        }
    }
    /**
     * Specified whether a filled field was done so by using the html autocomplete attribute or autofill heuristics.
     */
    public static final class FillingStrategy {
        private FillingStrategy() {}
        public static final String AUTOCOMPLETEATTRIBUTE = "autocompleteAttribute";
        public static final String AUTOFILLINFERRED = "autofillInferred";
    }
    /**
     */
    public static final class FilledField extends CdpObject {
        private FilledField(Map<String, Object> values) { super(values); }
        @Nullable public static FilledField fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FilledField(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The type of the field, e.g text, password etc.
         * @return the protocol field value
         */
        @Nullable public String htmlType() {
            return (String) value("htmlType");
        }
        /**
         * the html id
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * the html name
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * the field value
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * The actual field type, e.g FAMILY_NAME
         * @return the protocol field value
         */
        @Nullable public String autofillType() {
            return (String) value("autofillType");
        }
        /**
         * The filling strategy
         * @return the protocol field value
         */
        @Nullable public String fillingStrategy() {
            return (String) value("fillingStrategy");
        }
        /**
         * The frame the field belongs to
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * The form field&#x27;s DOM node
         * @return the protocol field value
         */
        @Nullable public Long fieldId() {
            return numberAsLong(value("fieldId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The type of the field, e.g text, password etc.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder htmlType(@Nullable String value) {
                if (value == null) values.remove("htmlType");
                else values.put("htmlType", jsonValue(value));
                return this;
            }
            /**
             * the html id
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * the html name
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * the field value
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * The actual field type, e.g FAMILY_NAME
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder autofillType(@Nullable String value) {
                if (value == null) values.remove("autofillType");
                else values.put("autofillType", jsonValue(value));
                return this;
            }
            /**
             * The filling strategy
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fillingStrategy(@Nullable String value) {
                if (value == null) values.remove("fillingStrategy");
                else values.put("fillingStrategy", jsonValue(value));
                return this;
            }
            /**
             * The frame the field belongs to
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * The form field&#x27;s DOM node
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fieldId(@Nullable Long value) {
                if (value == null) values.remove("fieldId");
                else values.put("fieldId", jsonValue(value));
                return this;
            }
            public FilledField build() {
                if (!values.containsKey("htmlType")) throw new IllegalStateException("Missing required CDP field: htmlType");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("autofillType")) throw new IllegalStateException("Missing required CDP field: autofillType");
                if (!values.containsKey("fillingStrategy")) throw new IllegalStateException("Missing required CDP field: fillingStrategy");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("fieldId")) throw new IllegalStateException("Missing required CDP field: fieldId");
                return new FilledField(values);
            }
        }
    }
    /**
     * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
     */
    public static final class TriggerParams extends CdpObject {
        private TriggerParams(Map<String, Object> values) { super(values); }
        @Nullable public static TriggerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TriggerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifies a field that serves as an anchor for autofill.
         * @return the protocol field value
         */
        @Nullable public Long fieldId() {
            return numberAsLong(value("fieldId"));
        }
        /**
         * Identifies the frame that field belongs to.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Credit card information to fill out the form. Credit card data is not saved. Mutually exclusive with {@code address}.
         * @return the protocol field value
         */
        @Nullable public Autofill.CreditCard card() {
            return Autofill.CreditCard.fromMap(objectMap(value("card")));
        }
        /**
         * Address to fill out the form. Address data is not saved. Mutually exclusive with {@code card}.
         * @return the protocol field value
         */
        @Nullable public Autofill.Address address() {
            return Autofill.Address.fromMap(objectMap(value("address")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifies a field that serves as an anchor for autofill.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fieldId(@Nullable Long value) {
                if (value == null) values.remove("fieldId");
                else values.put("fieldId", jsonValue(value));
                return this;
            }
            /**
             * Identifies the frame that field belongs to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Credit card information to fill out the form. Credit card data is not saved. Mutually exclusive with {@code address}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder card(@Nullable Autofill.CreditCard value) {
                if (value == null) values.remove("card");
                else values.put("card", jsonValue(value));
                return this;
            }
            /**
             * Address to fill out the form. Address data is not saved. Mutually exclusive with {@code card}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder address(@Nullable Autofill.Address value) {
                if (value == null) values.remove("address");
                else values.put("address", jsonValue(value));
                return this;
            }
            public TriggerParams build() {
                if (!values.containsKey("fieldId")) throw new IllegalStateException("Missing required CDP field: fieldId");
                return new TriggerParams(values);
            }
        }
    }
    /**
     * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
     */
    public static final class TriggerResult extends CdpObject {
        private TriggerResult(Map<String, Object> values) { super(values); }
        @Nullable public static TriggerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TriggerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TriggerResult build() {
                return new TriggerResult(values);
            }
        }
    }
    /**
     * Set addresses so that developers can verify their forms implementation.
     */
    public static final class SetAddressesParams extends CdpObject {
        private SetAddressesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAddressesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAddressesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the addresses field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Autofill.Address> addresses() {
            return list(value("addresses"), element0 -> Autofill.Address.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the addresses field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder addresses(@Nullable java.util.List<Autofill.Address> value) {
                if (value == null) values.remove("addresses");
                else values.put("addresses", jsonValue(value));
                return this;
            }
            public SetAddressesParams build() {
                if (!values.containsKey("addresses")) throw new IllegalStateException("Missing required CDP field: addresses");
                return new SetAddressesParams(values);
            }
        }
    }
    /**
     * Set addresses so that developers can verify their forms implementation.
     */
    public static final class SetAddressesResult extends CdpObject {
        private SetAddressesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAddressesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAddressesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAddressesResult build() {
                return new SetAddressesResult(values);
            }
        }
    }
    /**
     * Disables autofill domain notifications.
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
     * Disables autofill domain notifications.
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
     * Enables autofill domain notifications.
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
     * Enables autofill domain notifications.
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
     * Emitted when an address form is filled.
     */
    public static final class AddressFormFilledEvent extends CdpObject {
        private AddressFormFilledEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AddressFormFilledEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddressFormFilledEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Information about the fields that were filled
         * @return the protocol field value
         */
        @Nullable public java.util.List<Autofill.FilledField> filledFields() {
            return list(value("filledFields"), element0 -> Autofill.FilledField.fromMap(objectMap(element0)));
        }
        /**
         * An UI representation of the address used to fill the form. Consists of a 2D array where each child represents an address/profile line.
         * @return the protocol field value
         */
        @Nullable public Autofill.AddressUI addressUi() {
            return Autofill.AddressUI.fromMap(objectMap(value("addressUi")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Information about the fields that were filled
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filledFields(@Nullable java.util.List<Autofill.FilledField> value) {
                if (value == null) values.remove("filledFields");
                else values.put("filledFields", jsonValue(value));
                return this;
            }
            /**
             * An UI representation of the address used to fill the form. Consists of a 2D array where each child represents an address/profile line.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder addressUi(@Nullable Autofill.AddressUI value) {
                if (value == null) values.remove("addressUi");
                else values.put("addressUi", jsonValue(value));
                return this;
            }
            public AddressFormFilledEvent build() {
                if (!values.containsKey("filledFields")) throw new IllegalStateException("Missing required CDP field: filledFields");
                if (!values.containsKey("addressUi")) throw new IllegalStateException("Missing required CDP field: addressUi");
                return new AddressFormFilledEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TriggerResult> trigger(TriggerParams params) {
            return client.call("Autofill.trigger", params, TriggerResult::fromMap);
        }
        /**
         * Set addresses so that developers can verify their forms implementation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAddressesResult> setAddresses(SetAddressesParams params) {
            return client.call("Autofill.setAddresses", params, SetAddressesResult::fromMap);
        }
        /**
         * Disables autofill domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Autofill.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables autofill domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Autofill.enable", null, EnableResult::fromMap);
        }
        /**
         * Emitted when an address form is filled.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAddressFormFilled(Consumer<AddressFormFilledEvent> handler) {
            return client.on("Autofill.addressFormFilled", AddressFormFilledEvent::fromMap, handler);
        }
    }
}
