// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * This domain allows configuring virtual Bluetooth devices to test the web-bluetooth API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/BluetoothEmulation.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class BluetoothEmulation {
    private BluetoothEmulation() {}
    private static final CdpField<String> CHARACTERISTIC_ID = CdpField.of("characteristicId", value -> (String) value);
    private static final CdpField<String> DESCRIPTOR_ID = CdpField.of("descriptorId", value -> (String) value);
    private static final CdpField<BluetoothEmulation.ScanEntry> ENTRY = CdpField.of("entry", value -> java.util.Objects.requireNonNull(BluetoothEmulation.ScanEntry.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> KNOWN_SERVICE_UUIDS = CdpField.of("knownServiceUuids", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<BluetoothEmulation.ManufacturerData>> MANUFACTURER_DATA = CdpField.of("manufacturerData", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(BluetoothEmulation.ManufacturerData.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<BluetoothEmulation.CharacteristicProperties> PROPERTIES = CdpField.of("properties", value -> java.util.Objects.requireNonNull(BluetoothEmulation.CharacteristicProperties.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<BluetoothEmulation.ScanRecord> SCAN_RECORD = CdpField.of("scanRecord", value -> java.util.Objects.requireNonNull(BluetoothEmulation.ScanRecord.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<String> SERVICE_ID = CdpField.of("serviceId", value -> (String) value);
    private static final CdpField<BluetoothEmulation.CentralState> STATE = CdpField.of("state", value -> BluetoothEmulation.CentralState.of((String) value));
    private static final CdpField<BluetoothEmulation.GATTOperationType> TYPE = CdpField.of("type", value -> BluetoothEmulation.GATTOperationType.of((String) value));
    private static final CdpField<BluetoothEmulation.CharacteristicOperationType> TYPE_2 = CdpField.of("type", value -> BluetoothEmulation.CharacteristicOperationType.of((String) value));
    private static final CdpField<BluetoothEmulation.DescriptorOperationType> TYPE_3 = CdpField.of("type", value -> BluetoothEmulation.DescriptorOperationType.of((String) value));
    private static final CdpField<java.util.List<String>> UUIDS = CdpField.of("uuids", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<BluetoothEmulation.CharacteristicWriteType> WRITE_TYPE = CdpField.of("writeType", value -> BluetoothEmulation.CharacteristicWriteType.of((String) value));
    /**
     * Indicates the various states of Central.
     */
    public enum CentralState implements CdpValue<String> {
        ABSENT("absent"),
        POWERED_OFF("powered-off"),
        POWERED_ON("powered-on");
        public final String value;
        CentralState(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CentralState of(String value) {
            for (CentralState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CentralState value: " + value);
        }
    }
    /**
     * Indicates the various types of GATT event.
     */
    public enum GATTOperationType implements CdpValue<String> {
        CONNECTION("connection"),
        DISCOVERY("discovery");
        public final String value;
        GATTOperationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static GATTOperationType of(String value) {
            for (GATTOperationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GATTOperationType value: " + value);
        }
    }
    /**
     * Indicates the various types of characteristic write.
     */
    public enum CharacteristicWriteType implements CdpValue<String> {
        WRITE_DEFAULT_DEPRECATED("write-default-deprecated"),
        WRITE_WITH_RESPONSE("write-with-response"),
        WRITE_WITHOUT_RESPONSE("write-without-response");
        public final String value;
        CharacteristicWriteType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CharacteristicWriteType of(String value) {
            for (CharacteristicWriteType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CharacteristicWriteType value: " + value);
        }
    }
    /**
     * Indicates the various types of characteristic operation.
     */
    public enum CharacteristicOperationType implements CdpValue<String> {
        READ("read"),
        WRITE("write"),
        SUBSCRIBE_TO_NOTIFICATIONS("subscribe-to-notifications"),
        UNSUBSCRIBE_FROM_NOTIFICATIONS("unsubscribe-from-notifications");
        public final String value;
        CharacteristicOperationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CharacteristicOperationType of(String value) {
            for (CharacteristicOperationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CharacteristicOperationType value: " + value);
        }
    }
    /**
     * Indicates the various types of descriptor operation.
     */
    public enum DescriptorOperationType implements CdpValue<String> {
        READ("read"),
        WRITE("write");
        public final String value;
        DescriptorOperationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static DescriptorOperationType of(String value) {
            for (DescriptorOperationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DescriptorOperationType value: " + value);
        }
    }
    /**
     * Stores the manufacturer data
     */
    public static final class ManufacturerData extends CdpObject {
        public ManufacturerData() {}
        private ManufacturerData(Map<String, Object> values) { super(values); }
        public static ManufacturerData fromMap(Map<String, Object> values) {
            return new ManufacturerData(values);
        }
        /**
         * Company identifier https://bitbucket.org/bluetooth-SIG/public/src/main/assigned_numbers/company_identifiers/company_identifiers.yaml https://usb.org/developers
         * @return the protocol field value
         */
        public long key() {
            return requireLong("key");
        }
        /**
         * Manufacturer-specific data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Company identifier https://bitbucket.org/bluetooth-SIG/public/src/main/assigned_numbers/company_identifiers/company_identifiers.yaml https://usb.org/developers
         * @param key field value
         * @return this model
         */
        public ManufacturerData key(long key) {
            set("key", key);
            return this;
        }
        /**
         * Manufacturer-specific data (Encoded as a base64 string when passed over JSON)
         * @param data field value
         * @return this model
         */
        public ManufacturerData data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Stores the byte data of the advertisement packet sent by a Bluetooth device.
     */
    public static final class ScanRecord extends CdpObject {
        public ScanRecord() {}
        private ScanRecord(Map<String, Object> values) { super(values); }
        public static ScanRecord fromMap(Map<String, Object> values) {
            return new ScanRecord(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
        }
        /**
         * Returns the uuids field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> uuids() {
            return optional(UUIDS);
        }
        /**
         * Stores the external appearance description of the device.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong appearance() {
            return optionalLong("appearance");
        }
        /**
         * Stores the transmission power of a broadcasting device.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong txPower() {
            return optionalLong("txPower");
        }
        /**
         * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<BluetoothEmulation.ManufacturerData>> manufacturerData() {
            return optional(MANUFACTURER_DATA);
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public ScanRecord name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the uuids field.
         * @param uuids field value
         * @return this model
         */
        public ScanRecord uuids(java.util.List<String> uuids) {
            set("uuids", uuids);
            return this;
        }
        /**
         * Stores the external appearance description of the device.
         * @param appearance field value
         * @return this model
         */
        public ScanRecord appearance(long appearance) {
            set("appearance", appearance);
            return this;
        }
        /**
         * Stores the transmission power of a broadcasting device.
         * @param txPower field value
         * @return this model
         */
        public ScanRecord txPower(long txPower) {
            set("txPower", txPower);
            return this;
        }
        /**
         * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
         * @param manufacturerData field value
         * @return this model
         */
        public ScanRecord manufacturerData(java.util.List<BluetoothEmulation.ManufacturerData> manufacturerData) {
            set("manufacturerData", manufacturerData);
            return this;
        }
    }
    /**
     * Stores the advertisement packet information that is sent by a Bluetooth device.
     */
    public static final class ScanEntry extends CdpObject {
        public ScanEntry() {}
        private ScanEntry(Map<String, Object> values) { super(values); }
        public static ScanEntry fromMap(Map<String, Object> values) {
            return new ScanEntry(values);
        }
        /**
         * Returns the deviceAddress field.
         * @return the protocol field value
         */
        public String deviceAddress() {
            return requireString("deviceAddress");
        }
        /**
         * Returns the rssi field.
         * @return the protocol field value
         */
        public long rssi() {
            return requireLong("rssi");
        }
        /**
         * Returns the scanRecord field.
         * @return the protocol field value
         */
        public BluetoothEmulation.ScanRecord scanRecord() {
            return require(SCAN_RECORD);
        }
        /**
         * Sets the deviceAddress field.
         * @param deviceAddress field value
         * @return this model
         */
        public ScanEntry deviceAddress(String deviceAddress) {
            set("deviceAddress", deviceAddress);
            return this;
        }
        /**
         * Sets the rssi field.
         * @param rssi field value
         * @return this model
         */
        public ScanEntry rssi(long rssi) {
            set("rssi", rssi);
            return this;
        }
        /**
         * Sets the scanRecord field.
         * @param scanRecord field value
         * @return this model
         */
        public ScanEntry scanRecord(BluetoothEmulation.ScanRecord scanRecord) {
            set("scanRecord", scanRecord);
            return this;
        }
    }
    /**
     * Describes the properties of a characteristic. This follows Bluetooth Core Specification BT 4.2 Vol 3 Part G 3.3.1. Characteristic Properties.
     */
    public static final class CharacteristicProperties extends CdpObject {
        public CharacteristicProperties() {}
        private CharacteristicProperties(Map<String, Object> values) { super(values); }
        public static CharacteristicProperties fromMap(Map<String, Object> values) {
            return new CharacteristicProperties(values);
        }
        /**
         * Returns the broadcast field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> broadcast() {
            return optionalBoolean("broadcast");
        }
        /**
         * Returns the read field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> read() {
            return optionalBoolean("read");
        }
        /**
         * Returns the writeWithoutResponse field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> writeWithoutResponse() {
            return optionalBoolean("writeWithoutResponse");
        }
        /**
         * Returns the write field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> write() {
            return optionalBoolean("write");
        }
        /**
         * Returns the notify field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> notifyValue() {
            return optionalBoolean("notify");
        }
        /**
         * Returns the indicate field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> indicate() {
            return optionalBoolean("indicate");
        }
        /**
         * Returns the authenticatedSignedWrites field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> authenticatedSignedWrites() {
            return optionalBoolean("authenticatedSignedWrites");
        }
        /**
         * Returns the extendedProperties field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> extendedProperties() {
            return optionalBoolean("extendedProperties");
        }
        /**
         * Sets the broadcast field.
         * @param broadcast field value
         * @return this model
         */
        public CharacteristicProperties broadcast(boolean broadcast) {
            set("broadcast", broadcast);
            return this;
        }
        /**
         * Sets the read field.
         * @param read field value
         * @return this model
         */
        public CharacteristicProperties read(boolean read) {
            set("read", read);
            return this;
        }
        /**
         * Sets the writeWithoutResponse field.
         * @param writeWithoutResponse field value
         * @return this model
         */
        public CharacteristicProperties writeWithoutResponse(boolean writeWithoutResponse) {
            set("writeWithoutResponse", writeWithoutResponse);
            return this;
        }
        /**
         * Sets the write field.
         * @param write field value
         * @return this model
         */
        public CharacteristicProperties write(boolean write) {
            set("write", write);
            return this;
        }
        /**
         * Sets the notify field.
         * @param notifyValue field value
         * @return this model
         */
        public CharacteristicProperties notifyValue(boolean notifyValue) {
            set("notify", notifyValue);
            return this;
        }
        /**
         * Sets the indicate field.
         * @param indicate field value
         * @return this model
         */
        public CharacteristicProperties indicate(boolean indicate) {
            set("indicate", indicate);
            return this;
        }
        /**
         * Sets the authenticatedSignedWrites field.
         * @param authenticatedSignedWrites field value
         * @return this model
         */
        public CharacteristicProperties authenticatedSignedWrites(boolean authenticatedSignedWrites) {
            set("authenticatedSignedWrites", authenticatedSignedWrites);
            return this;
        }
        /**
         * Sets the extendedProperties field.
         * @param extendedProperties field value
         * @return this model
         */
        public CharacteristicProperties extendedProperties(boolean extendedProperties) {
            set("extendedProperties", extendedProperties);
            return this;
        }
    }
    /**
     * Enable the BluetoothEmulation domain.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * State of the simulated central.
         * @return the protocol field value
         */
        public BluetoothEmulation.CentralState state() {
            return require(STATE);
        }
        /**
         * If the simulated central supports low-energy.
         * @return the protocol field value
         */
        public boolean leSupported() {
            return requireBoolean("leSupported");
        }
        /**
         * State of the simulated central.
         * @param state field value
         * @return this model
         */
        public EnableRequest state(BluetoothEmulation.CentralState state) {
            set("state", state);
            return this;
        }
        /**
         * If the simulated central supports low-energy.
         * @param leSupported field value
         * @return this model
         */
        public EnableRequest leSupported(boolean leSupported) {
            set("leSupported", leSupported);
            return this;
        }
    }
    /**
     * Set the state of the simulated central.
     */
    public static final class SetSimulatedCentralStateRequest extends CdpObject {
        public SetSimulatedCentralStateRequest() {}
        public static SetSimulatedCentralStateRequest fromMap(Map<String, Object> values) {
            final SetSimulatedCentralStateRequest instance_ = new SetSimulatedCentralStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * State of the simulated central.
         * @return the protocol field value
         */
        public BluetoothEmulation.CentralState state() {
            return require(STATE);
        }
        /**
         * State of the simulated central.
         * @param state field value
         * @return this model
         */
        public SetSimulatedCentralStateRequest state(BluetoothEmulation.CentralState state) {
            set("state", state);
            return this;
        }
    }
    /**
     * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been connected to the system.
     */
    public static final class SimulatePreconnectedPeripheralRequest extends CdpObject {
        public SimulatePreconnectedPeripheralRequest() {}
        public static SimulatePreconnectedPeripheralRequest fromMap(Map<String, Object> values) {
            final SimulatePreconnectedPeripheralRequest instance_ = new SimulatePreconnectedPeripheralRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        public String address() {
            return requireString("address");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Returns the manufacturerData field.
         * @return the protocol field value
         */
        public java.util.List<BluetoothEmulation.ManufacturerData> manufacturerData() {
            return require(MANUFACTURER_DATA);
        }
        /**
         * Returns the knownServiceUuids field.
         * @return the protocol field value
         */
        public java.util.List<String> knownServiceUuids() {
            return require(KNOWN_SERVICE_UUIDS);
        }
        /**
         * Sets the address field.
         * @param address field value
         * @return this model
         */
        public SimulatePreconnectedPeripheralRequest address(String address) {
            set("address", address);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public SimulatePreconnectedPeripheralRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the manufacturerData field.
         * @param manufacturerData field value
         * @return this model
         */
        public SimulatePreconnectedPeripheralRequest manufacturerData(java.util.List<BluetoothEmulation.ManufacturerData> manufacturerData) {
            set("manufacturerData", manufacturerData);
            return this;
        }
        /**
         * Sets the knownServiceUuids field.
         * @param knownServiceUuids field value
         * @return this model
         */
        public SimulatePreconnectedPeripheralRequest knownServiceUuids(java.util.List<String> knownServiceUuids) {
            set("knownServiceUuids", knownServiceUuids);
            return this;
        }
    }
    /**
     * Simulates an advertisement packet described in |entry| being received by the central.
     */
    public static final class SimulateAdvertisementRequest extends CdpObject {
        public SimulateAdvertisementRequest() {}
        public static SimulateAdvertisementRequest fromMap(Map<String, Object> values) {
            final SimulateAdvertisementRequest instance_ = new SimulateAdvertisementRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the entry field.
         * @return the protocol field value
         */
        public BluetoothEmulation.ScanEntry entry() {
            return require(ENTRY);
        }
        /**
         * Sets the entry field.
         * @param entry field value
         * @return this model
         */
        public SimulateAdvertisementRequest entry(BluetoothEmulation.ScanEntry entry) {
            set("entry", entry);
            return this;
        }
    }
    /**
     * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
     */
    public static final class SimulateGATTOperationResponseRequest extends CdpObject {
        public SimulateGATTOperationResponseRequest() {}
        public static SimulateGATTOperationResponseRequest fromMap(Map<String, Object> values) {
            final SimulateGATTOperationResponseRequest instance_ = new SimulateGATTOperationResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        public String address() {
            return requireString("address");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.GATTOperationType type() {
            return require(TYPE);
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        public long code() {
            return requireLong("code");
        }
        /**
         * Sets the address field.
         * @param address field value
         * @return this model
         */
        public SimulateGATTOperationResponseRequest address(String address) {
            set("address", address);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public SimulateGATTOperationResponseRequest type(BluetoothEmulation.GATTOperationType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the code field.
         * @param code field value
         * @return this model
         */
        public SimulateGATTOperationResponseRequest code(long code) {
            set("code", code);
            return this;
        }
    }
    /**
     * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
     */
    public static final class SimulateCharacteristicOperationResponseRequest extends CdpObject {
        public SimulateCharacteristicOperationResponseRequest() {}
        public static SimulateCharacteristicOperationResponseRequest fromMap(Map<String, Object> values) {
            final SimulateCharacteristicOperationResponseRequest instance_ = new SimulateCharacteristicOperationResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        public String characteristicId() {
            return requireString("characteristicId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.CharacteristicOperationType type() {
            return require(TYPE_2);
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        public long code() {
            return requireLong("code");
        }
        /**
         * Returns the data field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return optionalString("data");
        }
        /**
         * Sets the characteristicId field.
         * @param characteristicId field value
         * @return this model
         */
        public SimulateCharacteristicOperationResponseRequest characteristicId(String characteristicId) {
            set("characteristicId", characteristicId);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public SimulateCharacteristicOperationResponseRequest type(BluetoothEmulation.CharacteristicOperationType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the code field.
         * @param code field value
         * @return this model
         */
        public SimulateCharacteristicOperationResponseRequest code(long code) {
            set("code", code);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public SimulateCharacteristicOperationResponseRequest data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
     */
    public static final class SimulateDescriptorOperationResponseRequest extends CdpObject {
        public SimulateDescriptorOperationResponseRequest() {}
        public static SimulateDescriptorOperationResponseRequest fromMap(Map<String, Object> values) {
            final SimulateDescriptorOperationResponseRequest instance_ = new SimulateDescriptorOperationResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the descriptorId field.
         * @return the protocol field value
         */
        public String descriptorId() {
            return requireString("descriptorId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.DescriptorOperationType type() {
            return require(TYPE_3);
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        public long code() {
            return requireLong("code");
        }
        /**
         * Returns the data field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return optionalString("data");
        }
        /**
         * Sets the descriptorId field.
         * @param descriptorId field value
         * @return this model
         */
        public SimulateDescriptorOperationResponseRequest descriptorId(String descriptorId) {
            set("descriptorId", descriptorId);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public SimulateDescriptorOperationResponseRequest type(BluetoothEmulation.DescriptorOperationType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the code field.
         * @param code field value
         * @return this model
         */
        public SimulateDescriptorOperationResponseRequest code(long code) {
            set("code", code);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public SimulateDescriptorOperationResponseRequest data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Adds a service with |serviceUuid| to the peripheral with |address|.
     */
    public static final class AddServiceRequest extends CdpObject {
        public AddServiceRequest() {}
        public static AddServiceRequest fromMap(Map<String, Object> values) {
            final AddServiceRequest instance_ = new AddServiceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        public String address() {
            return requireString("address");
        }
        /**
         * Returns the serviceUuid field.
         * @return the protocol field value
         */
        public String serviceUuid() {
            return requireString("serviceUuid");
        }
        /**
         * Sets the address field.
         * @param address field value
         * @return this model
         */
        public AddServiceRequest address(String address) {
            set("address", address);
            return this;
        }
        /**
         * Sets the serviceUuid field.
         * @param serviceUuid field value
         * @return this model
         */
        public AddServiceRequest serviceUuid(String serviceUuid) {
            set("serviceUuid", serviceUuid);
            return this;
        }
    }
    /**
     * Removes the service respresented by |serviceId| from the simulated central.
     */
    public static final class RemoveServiceRequest extends CdpObject {
        public RemoveServiceRequest() {}
        public static RemoveServiceRequest fromMap(Map<String, Object> values) {
            final RemoveServiceRequest instance_ = new RemoveServiceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the serviceId field.
         * @return the protocol field value
         */
        public String serviceId() {
            return requireString("serviceId");
        }
        /**
         * Sets the serviceId field.
         * @param serviceId field value
         * @return this model
         */
        public RemoveServiceRequest serviceId(String serviceId) {
            set("serviceId", serviceId);
            return this;
        }
    }
    /**
     * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
     */
    public static final class AddCharacteristicRequest extends CdpObject {
        public AddCharacteristicRequest() {}
        public static AddCharacteristicRequest fromMap(Map<String, Object> values) {
            final AddCharacteristicRequest instance_ = new AddCharacteristicRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the serviceId field.
         * @return the protocol field value
         */
        public String serviceId() {
            return requireString("serviceId");
        }
        /**
         * Returns the characteristicUuid field.
         * @return the protocol field value
         */
        public String characteristicUuid() {
            return requireString("characteristicUuid");
        }
        /**
         * Returns the properties field.
         * @return the protocol field value
         */
        public BluetoothEmulation.CharacteristicProperties properties() {
            return require(PROPERTIES);
        }
        /**
         * Sets the serviceId field.
         * @param serviceId field value
         * @return this model
         */
        public AddCharacteristicRequest serviceId(String serviceId) {
            set("serviceId", serviceId);
            return this;
        }
        /**
         * Sets the characteristicUuid field.
         * @param characteristicUuid field value
         * @return this model
         */
        public AddCharacteristicRequest characteristicUuid(String characteristicUuid) {
            set("characteristicUuid", characteristicUuid);
            return this;
        }
        /**
         * Sets the properties field.
         * @param properties field value
         * @return this model
         */
        public AddCharacteristicRequest properties(BluetoothEmulation.CharacteristicProperties properties) {
            set("properties", properties);
            return this;
        }
    }
    /**
     * Removes the characteristic respresented by |characteristicId| from the simulated central.
     */
    public static final class RemoveCharacteristicRequest extends CdpObject {
        public RemoveCharacteristicRequest() {}
        public static RemoveCharacteristicRequest fromMap(Map<String, Object> values) {
            final RemoveCharacteristicRequest instance_ = new RemoveCharacteristicRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        public String characteristicId() {
            return requireString("characteristicId");
        }
        /**
         * Sets the characteristicId field.
         * @param characteristicId field value
         * @return this model
         */
        public RemoveCharacteristicRequest characteristicId(String characteristicId) {
            set("characteristicId", characteristicId);
            return this;
        }
    }
    /**
     * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
     */
    public static final class AddDescriptorRequest extends CdpObject {
        public AddDescriptorRequest() {}
        public static AddDescriptorRequest fromMap(Map<String, Object> values) {
            final AddDescriptorRequest instance_ = new AddDescriptorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        public String characteristicId() {
            return requireString("characteristicId");
        }
        /**
         * Returns the descriptorUuid field.
         * @return the protocol field value
         */
        public String descriptorUuid() {
            return requireString("descriptorUuid");
        }
        /**
         * Sets the characteristicId field.
         * @param characteristicId field value
         * @return this model
         */
        public AddDescriptorRequest characteristicId(String characteristicId) {
            set("characteristicId", characteristicId);
            return this;
        }
        /**
         * Sets the descriptorUuid field.
         * @param descriptorUuid field value
         * @return this model
         */
        public AddDescriptorRequest descriptorUuid(String descriptorUuid) {
            set("descriptorUuid", descriptorUuid);
            return this;
        }
    }
    /**
     * Removes the descriptor with |descriptorId| from the simulated central.
     */
    public static final class RemoveDescriptorRequest extends CdpObject {
        public RemoveDescriptorRequest() {}
        public static RemoveDescriptorRequest fromMap(Map<String, Object> values) {
            final RemoveDescriptorRequest instance_ = new RemoveDescriptorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the descriptorId field.
         * @return the protocol field value
         */
        public String descriptorId() {
            return requireString("descriptorId");
        }
        /**
         * Sets the descriptorId field.
         * @param descriptorId field value
         * @return this model
         */
        public RemoveDescriptorRequest descriptorId(String descriptorId) {
            set("descriptorId", descriptorId);
            return this;
        }
    }
    /**
     * Simulates a GATT disconnection from the peripheral with |address|.
     */
    public static final class SimulateGATTDisconnectionRequest extends CdpObject {
        public SimulateGATTDisconnectionRequest() {}
        public static SimulateGATTDisconnectionRequest fromMap(Map<String, Object> values) {
            final SimulateGATTDisconnectionRequest instance_ = new SimulateGATTDisconnectionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        public String address() {
            return requireString("address");
        }
        /**
         * Sets the address field.
         * @param address field value
         * @return this model
         */
        public SimulateGATTDisconnectionRequest address(String address) {
            set("address", address);
            return this;
        }
    }
    /**
     * Event for when a GATT operation of |type| to the peripheral with |address| happened.
     */
    public static final class GattOperationReceivedEvent extends CdpObject {
        public GattOperationReceivedEvent() {}
        private GattOperationReceivedEvent(Map<String, Object> values) { super(values); }
        public static GattOperationReceivedEvent fromMap(Map<String, Object> values) {
            return new GattOperationReceivedEvent(values);
        }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        public String address() {
            return requireString("address");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.GATTOperationType type() {
            return require(TYPE);
        }
        /**
         * Sets the address field.
         * @param address field value
         * @return this model
         */
        public GattOperationReceivedEvent address(String address) {
            set("address", address);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public GattOperationReceivedEvent type(BluetoothEmulation.GATTOperationType type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Event for when a characteristic operation of |type| to the characteristic respresented by |characteristicId| happened. |data| and |writeType| is expected to exist when |type| is write.
     */
    public static final class CharacteristicOperationReceivedEvent extends CdpObject {
        public CharacteristicOperationReceivedEvent() {}
        private CharacteristicOperationReceivedEvent(Map<String, Object> values) { super(values); }
        public static CharacteristicOperationReceivedEvent fromMap(Map<String, Object> values) {
            return new CharacteristicOperationReceivedEvent(values);
        }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        public String characteristicId() {
            return requireString("characteristicId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.CharacteristicOperationType type() {
            return require(TYPE_2);
        }
        /**
         * Returns the data field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return optionalString("data");
        }
        /**
         * Returns the writeType field.
         * @return the protocol field value, empty when absent
         */
        public Optional<BluetoothEmulation.CharacteristicWriteType> writeType() {
            return optional(WRITE_TYPE);
        }
        /**
         * Sets the characteristicId field.
         * @param characteristicId field value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent characteristicId(String characteristicId) {
            set("characteristicId", characteristicId);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent type(BluetoothEmulation.CharacteristicOperationType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Sets the writeType field.
         * @param writeType field value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent writeType(BluetoothEmulation.CharacteristicWriteType writeType) {
            set("writeType", writeType);
            return this;
        }
    }
    /**
     * Event for when a descriptor operation of |type| to the descriptor respresented by |descriptorId| happened. |data| is expected to exist when |type| is write.
     */
    public static final class DescriptorOperationReceivedEvent extends CdpObject {
        public DescriptorOperationReceivedEvent() {}
        private DescriptorOperationReceivedEvent(Map<String, Object> values) { super(values); }
        public static DescriptorOperationReceivedEvent fromMap(Map<String, Object> values) {
            return new DescriptorOperationReceivedEvent(values);
        }
        /**
         * Returns the descriptorId field.
         * @return the protocol field value
         */
        public String descriptorId() {
            return requireString("descriptorId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.DescriptorOperationType type() {
            return require(TYPE_3);
        }
        /**
         * Returns the data field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return optionalString("data");
        }
        /**
         * Sets the descriptorId field.
         * @param descriptorId field value
         * @return this model
         */
        public DescriptorOperationReceivedEvent descriptorId(String descriptorId) {
            set("descriptorId", descriptorId);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public DescriptorOperationReceivedEvent type(BluetoothEmulation.DescriptorOperationType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DescriptorOperationReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Enable the BluetoothEmulation domain.
         * @param state protocol value
         * @param leSupported protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(BluetoothEmulation.CentralState state, boolean leSupported) {
            return call("BluetoothEmulation.enable", params("state", state, "leSupported", leSupported));
        }
        /**
         * Enable the BluetoothEmulation domain.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return call("BluetoothEmulation.enable", request);
        }
        /**
         * Set the state of the simulated central.
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSimulatedCentralState(BluetoothEmulation.CentralState state) {
            return call("BluetoothEmulation.setSimulatedCentralState", params("state", state));
        }
        /**
         * Set the state of the simulated central.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSimulatedCentralState(SetSimulatedCentralStateRequest request) {
            return call("BluetoothEmulation.setSimulatedCentralState", request);
        }
        /**
         * Disable the BluetoothEmulation domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("BluetoothEmulation.disable");
        }
        /**
         * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been connected to the system.
         * @param address protocol value
         * @param name protocol value
         * @param manufacturerData protocol value
         * @param knownServiceUuids protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulatePreconnectedPeripheral(String address, String name, java.util.List<BluetoothEmulation.ManufacturerData> manufacturerData, java.util.List<String> knownServiceUuids) {
            return call("BluetoothEmulation.simulatePreconnectedPeripheral", params("address", address, "name", name, "manufacturerData", manufacturerData, "knownServiceUuids", knownServiceUuids));
        }
        /**
         * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been connected to the system.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulatePreconnectedPeripheral(SimulatePreconnectedPeripheralRequest request) {
            return call("BluetoothEmulation.simulatePreconnectedPeripheral", request);
        }
        /**
         * Simulates an advertisement packet described in |entry| being received by the central.
         * @param entry protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateAdvertisement(BluetoothEmulation.ScanEntry entry) {
            return call("BluetoothEmulation.simulateAdvertisement", params("entry", entry));
        }
        /**
         * Simulates an advertisement packet described in |entry| being received by the central.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateAdvertisement(SimulateAdvertisementRequest request) {
            return call("BluetoothEmulation.simulateAdvertisement", request);
        }
        /**
         * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
         * @param address protocol value
         * @param type protocol value
         * @param code protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateGATTOperationResponse(String address, BluetoothEmulation.GATTOperationType type, long code) {
            return call("BluetoothEmulation.simulateGATTOperationResponse", params("address", address, "type", type, "code", code));
        }
        /**
         * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateGATTOperationResponse(SimulateGATTOperationResponseRequest request) {
            return call("BluetoothEmulation.simulateGATTOperationResponse", request);
        }
        /**
         * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param characteristicId protocol value
         * @param type protocol value
         * @param code protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateCharacteristicOperationResponse(String characteristicId, BluetoothEmulation.CharacteristicOperationType type, long code, String data) {
            return call("BluetoothEmulation.simulateCharacteristicOperationResponse", params("characteristicId", characteristicId, "type", type, "code", code, "data", data));
        }
        /**
         * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param characteristicId protocol value
         * @param type protocol value
         * @param code protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateCharacteristicOperationResponse(String characteristicId, BluetoothEmulation.CharacteristicOperationType type, long code) {
            return call("BluetoothEmulation.simulateCharacteristicOperationResponse", params("characteristicId", characteristicId, "type", type, "code", code));
        }
        /**
         * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateCharacteristicOperationResponse(SimulateCharacteristicOperationResponseRequest request) {
            return call("BluetoothEmulation.simulateCharacteristicOperationResponse", request);
        }
        /**
         * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param descriptorId protocol value
         * @param type protocol value
         * @param code protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateDescriptorOperationResponse(String descriptorId, BluetoothEmulation.DescriptorOperationType type, long code, String data) {
            return call("BluetoothEmulation.simulateDescriptorOperationResponse", params("descriptorId", descriptorId, "type", type, "code", code, "data", data));
        }
        /**
         * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param descriptorId protocol value
         * @param type protocol value
         * @param code protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateDescriptorOperationResponse(String descriptorId, BluetoothEmulation.DescriptorOperationType type, long code) {
            return call("BluetoothEmulation.simulateDescriptorOperationResponse", params("descriptorId", descriptorId, "type", type, "code", code));
        }
        /**
         * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateDescriptorOperationResponse(SimulateDescriptorOperationResponseRequest request) {
            return call("BluetoothEmulation.simulateDescriptorOperationResponse", request);
        }
        /**
         * Adds a service with |serviceUuid| to the peripheral with |address|.
         * @param address protocol value
         * @param serviceUuid protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addService(String address, String serviceUuid) {
            return call("BluetoothEmulation.addService", params("address", address, "serviceUuid", serviceUuid), SERVICE_ID::require);
        }
        /**
         * Adds a service with |serviceUuid| to the peripheral with |address|.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addService(AddServiceRequest request) {
            return call("BluetoothEmulation.addService", request, SERVICE_ID::require);
        }
        /**
         * Removes the service respresented by |serviceId| from the simulated central.
         * @param serviceId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeService(String serviceId) {
            return call("BluetoothEmulation.removeService", params("serviceId", serviceId));
        }
        /**
         * Removes the service respresented by |serviceId| from the simulated central.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeService(RemoveServiceRequest request) {
            return call("BluetoothEmulation.removeService", request);
        }
        /**
         * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
         * @param serviceId protocol value
         * @param characteristicUuid protocol value
         * @param properties protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addCharacteristic(String serviceId, String characteristicUuid, BluetoothEmulation.CharacteristicProperties properties) {
            return call("BluetoothEmulation.addCharacteristic", params("serviceId", serviceId, "characteristicUuid", characteristicUuid, "properties", properties), CHARACTERISTIC_ID::require);
        }
        /**
         * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addCharacteristic(AddCharacteristicRequest request) {
            return call("BluetoothEmulation.addCharacteristic", request, CHARACTERISTIC_ID::require);
        }
        /**
         * Removes the characteristic respresented by |characteristicId| from the simulated central.
         * @param characteristicId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeCharacteristic(String characteristicId) {
            return call("BluetoothEmulation.removeCharacteristic", params("characteristicId", characteristicId));
        }
        /**
         * Removes the characteristic respresented by |characteristicId| from the simulated central.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeCharacteristic(RemoveCharacteristicRequest request) {
            return call("BluetoothEmulation.removeCharacteristic", request);
        }
        /**
         * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
         * @param characteristicId protocol value
         * @param descriptorUuid protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addDescriptor(String characteristicId, String descriptorUuid) {
            return call("BluetoothEmulation.addDescriptor", params("characteristicId", characteristicId, "descriptorUuid", descriptorUuid), DESCRIPTOR_ID::require);
        }
        /**
         * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addDescriptor(AddDescriptorRequest request) {
            return call("BluetoothEmulation.addDescriptor", request, DESCRIPTOR_ID::require);
        }
        /**
         * Removes the descriptor with |descriptorId| from the simulated central.
         * @param descriptorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDescriptor(String descriptorId) {
            return call("BluetoothEmulation.removeDescriptor", params("descriptorId", descriptorId));
        }
        /**
         * Removes the descriptor with |descriptorId| from the simulated central.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDescriptor(RemoveDescriptorRequest request) {
            return call("BluetoothEmulation.removeDescriptor", request);
        }
        /**
         * Simulates a GATT disconnection from the peripheral with |address|.
         * @param address protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateGATTDisconnection(String address) {
            return call("BluetoothEmulation.simulateGATTDisconnection", params("address", address));
        }
        /**
         * Simulates a GATT disconnection from the peripheral with |address|.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateGATTDisconnection(SimulateGATTDisconnectionRequest request) {
            return call("BluetoothEmulation.simulateGATTDisconnection", request);
        }
        /**
         * Event for when a GATT operation of |type| to the peripheral with |address| happened.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onGattOperationReceived(Consumer<GattOperationReceivedEvent> handler) {
            return on("BluetoothEmulation.gattOperationReceived", GattOperationReceivedEvent::fromMap, handler);
        }
        /**
         * Event for when a characteristic operation of |type| to the characteristic respresented by |characteristicId| happened. |data| and |writeType| is expected to exist when |type| is write.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCharacteristicOperationReceived(Consumer<CharacteristicOperationReceivedEvent> handler) {
            return on("BluetoothEmulation.characteristicOperationReceived", CharacteristicOperationReceivedEvent::fromMap, handler);
        }
        /**
         * Event for when a descriptor operation of |type| to the descriptor respresented by |descriptorId| happened. |data| is expected to exist when |type| is write.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDescriptorOperationReceived(Consumer<DescriptorOperationReceivedEvent> handler) {
            return on("BluetoothEmulation.descriptorOperationReceived", DescriptorOperationReceivedEvent::fromMap, handler);
        }
    }
}
