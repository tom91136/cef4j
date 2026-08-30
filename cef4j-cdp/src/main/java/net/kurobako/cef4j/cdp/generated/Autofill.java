// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * Defines commands and events for Autofill.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Autofill.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Autofill {
    private Autofill() {}
    /**
     */
    public static final class CreditCard extends CdpObject {
        public CreditCard() {}
        private CreditCard(Map<String, Object> values) { super(values); }
        public static CreditCard fromMap(Map<String, Object> values) {
            return new CreditCard(values);
        }
        /**
         * 16-digit credit card number.
         * @return the protocol field value
         */
        public String number() {
            return (String) require("number");
        }
        /**
         * Name of the credit card owner.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * 2-digit expiry month.
         * @return the protocol field value
         */
        public String expiryMonth() {
            return (String) require("expiryMonth");
        }
        /**
         * 4-digit expiry year.
         * @return the protocol field value
         */
        public String expiryYear() {
            return (String) require("expiryYear");
        }
        /**
         * 3-digit card verification code.
         * @return the protocol field value
         */
        public String cvc() {
            return (String) require("cvc");
        }
        /**
         * 16-digit credit card number.
         * @param number field value
         * @return this model
         */
        public CreditCard number(String number) {
            set("number", number);
            return this;
        }
        /**
         * Name of the credit card owner.
         * @param name field value
         * @return this model
         */
        public CreditCard name(String name) {
            set("name", name);
            return this;
        }
        /**
         * 2-digit expiry month.
         * @param expiryMonth field value
         * @return this model
         */
        public CreditCard expiryMonth(String expiryMonth) {
            set("expiryMonth", expiryMonth);
            return this;
        }
        /**
         * 4-digit expiry year.
         * @param expiryYear field value
         * @return this model
         */
        public CreditCard expiryYear(String expiryYear) {
            set("expiryYear", expiryYear);
            return this;
        }
        /**
         * 3-digit card verification code.
         * @param cvc field value
         * @return this model
         */
        public CreditCard cvc(String cvc) {
            set("cvc", cvc);
            return this;
        }
    }
    /**
     */
    public static final class AddressField extends CdpObject {
        public AddressField() {}
        private AddressField(Map<String, Object> values) { super(values); }
        public static AddressField fromMap(Map<String, Object> values) {
            return new AddressField(values);
        }
        /**
         * address field name, for example GIVEN_NAME. The full list of supported field names: https://source.chromium.org/chromium/chromium/src/+/main:components/autofill/core/browser/field_types.cc;l=38
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * address field value, for example Jon Doe.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * address field name, for example GIVEN_NAME. The full list of supported field names: https://source.chromium.org/chromium/chromium/src/+/main:components/autofill/core/browser/field_types.cc;l=38
         * @param name field value
         * @return this model
         */
        public AddressField name(String name) {
            set("name", name);
            return this;
        }
        /**
         * address field value, for example Jon Doe.
         * @param value field value
         * @return this model
         */
        public AddressField value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * A list of address fields.
     */
    public static final class AddressFields extends CdpObject {
        public AddressFields() {}
        private AddressFields(Map<String, Object> values) { super(values); }
        public static AddressFields fromMap(Map<String, Object> values) {
            return new AddressFields(values);
        }
        /**
         * Returns the fields field.
         * @return the protocol field value
         */
        public java.util.List<Autofill.AddressField> fields() {
            return CdpObject.requireList(require("fields"), element0 -> java.util.Objects.requireNonNull(Autofill.AddressField.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the fields field.
         * @param fields field value
         * @return this model
         */
        public AddressFields fields(java.util.List<Autofill.AddressField> fields) {
            set("fields", fields);
            return this;
        }
    }
    /**
     */
    public static final class Address extends CdpObject {
        public Address() {}
        private Address(Map<String, Object> values) { super(values); }
        public static Address fromMap(Map<String, Object> values) {
            return new Address(values);
        }
        /**
         * fields and values defining an address.
         * @return the protocol field value
         */
        public java.util.List<Autofill.AddressField> fields() {
            return CdpObject.requireList(require("fields"), element0 -> java.util.Objects.requireNonNull(Autofill.AddressField.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * fields and values defining an address.
         * @param fields field value
         * @return this model
         */
        public Address fields(java.util.List<Autofill.AddressField> fields) {
            set("fields", fields);
            return this;
        }
    }
    /**
     * Defines how an address can be displayed like in chrome://settings/addresses. Address UI is a two dimensional array, each inner array is an &quot;address information line&quot;, and when rendered in a UI surface should be displayed as such. The following address UI for instance: [[{name: &quot;GIVE_NAME&quot;, value: &quot;Jon&quot;}, {name: &quot;FAMILY_NAME&quot;, value: &quot;Doe&quot;}], [{name: &quot;CITY&quot;, value: &quot;Munich&quot;}, {name: &quot;ZIP&quot;, value: &quot;81456&quot;}]] should allow the receiver to render: Jon Doe Munich 81456
     */
    public static final class AddressUI extends CdpObject {
        public AddressUI() {}
        private AddressUI(Map<String, Object> values) { super(values); }
        public static AddressUI fromMap(Map<String, Object> values) {
            return new AddressUI(values);
        }
        /**
         * A two dimension array containing the representation of values from an address profile.
         * @return the protocol field value
         */
        public java.util.List<Autofill.AddressFields> addressFields() {
            return CdpObject.requireList(require("addressFields"), element0 -> java.util.Objects.requireNonNull(Autofill.AddressFields.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * A two dimension array containing the representation of values from an address profile.
         * @param addressFields field value
         * @return this model
         */
        public AddressUI addressFields(java.util.List<Autofill.AddressFields> addressFields) {
            set("addressFields", addressFields);
            return this;
        }
    }
    /**
     * Specified whether a filled field was done so by using the html autocomplete attribute or autofill heuristics.
     */
    public enum FillingStrategy implements CdpValue<String> {
        AUTOCOMPLETEATTRIBUTE("autocompleteAttribute"),
        AUTOFILLINFERRED("autofillInferred");
        public final String value;
        FillingStrategy(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static FillingStrategy of(@Nonnull String value) {
            for (FillingStrategy constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown FillingStrategy value: " + value);
        }
    }
    /**
     */
    public static final class FilledField extends CdpObject {
        public FilledField() {}
        private FilledField(Map<String, Object> values) { super(values); }
        public static FilledField fromMap(Map<String, Object> values) {
            return new FilledField(values);
        }
        /**
         * The type of the field, e.g text, password etc.
         * @return the protocol field value
         */
        public String htmlType() {
            return (String) require("htmlType");
        }
        /**
         * the html id
         * @return the protocol field value
         */
        public String id() {
            return (String) require("id");
        }
        /**
         * the html name
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * the field value
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * The actual field type, e.g FAMILY_NAME
         * @return the protocol field value
         */
        public String autofillType() {
            return (String) require("autofillType");
        }
        /**
         * The filling strategy
         * @return the protocol field value
         */
        public Autofill.FillingStrategy fillingStrategy() {
            return Autofill.FillingStrategy.of((String) require("fillingStrategy"));
        }
        /**
         * The frame the field belongs to
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * The form field&#x27;s DOM node
         * @return the protocol field value
         */
        public DOM.BackendNodeId fieldId() {
            return new DOM.BackendNodeId(((Number) require("fieldId")).longValue());
        }
        /**
         * The type of the field, e.g text, password etc.
         * @param htmlType field value
         * @return this model
         */
        public FilledField htmlType(String htmlType) {
            set("htmlType", htmlType);
            return this;
        }
        /**
         * the html id
         * @param id field value
         * @return this model
         */
        public FilledField id(String id) {
            set("id", id);
            return this;
        }
        /**
         * the html name
         * @param name field value
         * @return this model
         */
        public FilledField name(String name) {
            set("name", name);
            return this;
        }
        /**
         * the field value
         * @param value field value
         * @return this model
         */
        public FilledField value(String value) {
            set("value", value);
            return this;
        }
        /**
         * The actual field type, e.g FAMILY_NAME
         * @param autofillType field value
         * @return this model
         */
        public FilledField autofillType(String autofillType) {
            set("autofillType", autofillType);
            return this;
        }
        /**
         * The filling strategy
         * @param fillingStrategy field value
         * @return this model
         */
        public FilledField fillingStrategy(Autofill.FillingStrategy fillingStrategy) {
            set("fillingStrategy", fillingStrategy);
            return this;
        }
        /**
         * The frame the field belongs to
         * @param frameId field value
         * @return this model
         */
        public FilledField frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The form field&#x27;s DOM node
         * @param fieldId field value
         * @return this model
         */
        public FilledField fieldId(DOM.BackendNodeId fieldId) {
            set("fieldId", fieldId);
            return this;
        }
    }
    /**
     * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
     */
    public static final class TriggerRequest extends CdpObject {
        public TriggerRequest() {}
        /**
         * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
         * @param fieldId protocol value
         */
        public TriggerRequest(DOM.BackendNodeId fieldId) {
            set("fieldId", fieldId);
        }
        public static TriggerRequest fromMap(Map<String, Object> values) {
            TriggerRequest instance_ = new TriggerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifies a field that serves as an anchor for autofill.
         * @return the protocol field value
         */
        public DOM.BackendNodeId fieldId() {
            return new DOM.BackendNodeId(((Number) require("fieldId")).longValue());
        }
        /**
         * Identifies the frame that field belongs to.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return Optional.ofNullable(raw("frameId") == null ? null : new Page.FrameId((String) raw("frameId")));
        }
        /**
         * Credit card information to fill out the form. Credit card data is not saved. Mutually exclusive with {@code address}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Autofill.CreditCard> card() {
            return Optional.ofNullable(raw("card") == null ? null : Autofill.CreditCard.fromMap(java.util.Objects.requireNonNull(objectMap(raw("card")))));
        }
        /**
         * Address to fill out the form. Address data is not saved. Mutually exclusive with {@code card}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Autofill.Address> address() {
            return Optional.ofNullable(raw("address") == null ? null : Autofill.Address.fromMap(java.util.Objects.requireNonNull(objectMap(raw("address")))));
        }
        /**
         * Identifies a field that serves as an anchor for autofill.
         * @param fieldId field value
         * @return this model
         */
        public TriggerRequest fieldId(DOM.BackendNodeId fieldId) {
            set("fieldId", fieldId);
            return this;
        }
        /**
         * Identifies the frame that field belongs to.
         * @param frameId field value; empty omits the value
         * @return this model
         */
        public TriggerRequest frameId(Optional<Page.FrameId> frameId) {
            set("frameId", frameId.orElse(null));
            return this;
        }
        /**
         * Identifies the frame that field belongs to.
         * @param frameId field value; null removes the value
         * @return this model
         */
        public TriggerRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Credit card information to fill out the form. Credit card data is not saved. Mutually exclusive with {@code address}.
         * @param card field value; empty omits the value
         * @return this model
         */
        public TriggerRequest card(Optional<Autofill.CreditCard> card) {
            set("card", card.orElse(null));
            return this;
        }
        /**
         * Credit card information to fill out the form. Credit card data is not saved. Mutually exclusive with {@code address}.
         * @param card field value; null removes the value
         * @return this model
         */
        public TriggerRequest card(Autofill.CreditCard card) {
            set("card", card);
            return this;
        }
        /**
         * Address to fill out the form. Address data is not saved. Mutually exclusive with {@code card}.
         * @param address field value; empty omits the value
         * @return this model
         */
        public TriggerRequest address(Optional<Autofill.Address> address) {
            set("address", address.orElse(null));
            return this;
        }
        /**
         * Address to fill out the form. Address data is not saved. Mutually exclusive with {@code card}.
         * @param address field value; null removes the value
         * @return this model
         */
        public TriggerRequest address(Autofill.Address address) {
            set("address", address);
            return this;
        }
    }
    /**
     * Set addresses so that developers can verify their forms implementation.
     */
    public static final class SetAddressesRequest extends CdpObject {
        public SetAddressesRequest() {}
        /**
         * Set addresses so that developers can verify their forms implementation.
         * @param addresses protocol value
         */
        public SetAddressesRequest(java.util.List<Autofill.Address> addresses) {
            set("addresses", addresses);
        }
        public static SetAddressesRequest fromMap(Map<String, Object> values) {
            SetAddressesRequest instance_ = new SetAddressesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the addresses field.
         * @return the protocol field value
         */
        public java.util.List<Autofill.Address> addresses() {
            return CdpObject.requireList(require("addresses"), element0 -> java.util.Objects.requireNonNull(Autofill.Address.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the addresses field.
         * @param addresses field value
         * @return this model
         */
        public SetAddressesRequest addresses(java.util.List<Autofill.Address> addresses) {
            set("addresses", addresses);
            return this;
        }
    }
    /**
     * Emitted when an address form is filled.
     */
    public static final class AddressFormFilledEvent extends CdpObject {
        public AddressFormFilledEvent() {}
        private AddressFormFilledEvent(Map<String, Object> values) { super(values); }
        public static AddressFormFilledEvent fromMap(Map<String, Object> values) {
            return new AddressFormFilledEvent(values);
        }
        /**
         * Information about the fields that were filled
         * @return the protocol field value
         */
        public java.util.List<Autofill.FilledField> filledFields() {
            return CdpObject.requireList(require("filledFields"), element0 -> java.util.Objects.requireNonNull(Autofill.FilledField.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * An UI representation of the address used to fill the form. Consists of a 2D array where each child represents an address/profile line.
         * @return the protocol field value
         */
        public Autofill.AddressUI addressUi() {
            return java.util.Objects.requireNonNull(Autofill.AddressUI.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("addressUi")))));
        }
        /**
         * Information about the fields that were filled
         * @param filledFields field value
         * @return this model
         */
        public AddressFormFilledEvent filledFields(java.util.List<Autofill.FilledField> filledFields) {
            set("filledFields", filledFields);
            return this;
        }
        /**
         * An UI representation of the address used to fill the form. Consists of a 2D array where each child represents an address/profile line.
         * @param addressUi field value
         * @return this model
         */
        public AddressFormFilledEvent addressUi(Autofill.AddressUI addressUi) {
            set("addressUi", addressUi);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
         * @param fieldId protocol value
         * @param frameId protocol value
         * @param card protocol value
         * @param address protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trigger(DOM.BackendNodeId fieldId, Optional<Page.FrameId> frameId, Optional<Autofill.CreditCard> card, Optional<Autofill.Address> address) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("fieldId", CdpObject.json(fieldId));
            frameId.ifPresent(value_ -> params.put("frameId", CdpObject.json(value_)));
            card.ifPresent(value_ -> params.put("card", CdpObject.json(value_)));
            address.ifPresent(value_ -> params.put("address", CdpObject.json(value_)));
            return client.call("Autofill.trigger", params, result_ -> null);
        }
        /**
         * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
         * @param fieldId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trigger(DOM.BackendNodeId fieldId) {
            return trigger(fieldId, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Trigger autofill on a form identified by the fieldId. If the field and related form cannot be autofilled, returns an error.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trigger(TriggerRequest request) {
            return client.call("Autofill.trigger", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Set addresses so that developers can verify their forms implementation.
         * @param addresses protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAddresses(java.util.List<Autofill.Address> addresses) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("addresses", CdpObject.json(addresses));
            return client.call("Autofill.setAddresses", params, result_ -> null);
        }
        /**
         * Set addresses so that developers can verify their forms implementation.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAddresses(SetAddressesRequest request) {
            return client.call("Autofill.setAddresses", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Disables autofill domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Autofill.disable", null, result_ -> null);
        }
        /**
         * Enables autofill domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Autofill.enable", null, result_ -> null);
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
