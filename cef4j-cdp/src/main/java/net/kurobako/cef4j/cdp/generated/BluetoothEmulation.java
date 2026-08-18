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
 * This domain allows configuring virtual Bluetooth devices to test the web-bluetooth API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/BluetoothEmulation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class BluetoothEmulation {
    private BluetoothEmulation() {}
    /**
     * Indicates the various states of Central.
     */
    public enum CentralState implements CdpValue<String> {
        ABSENT("absent"),
        POWERED_OFF("powered-off"),
        POWERED_ON("powered-on");
        public final String value;
        CentralState(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static CentralState of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static GATTOperationType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static CharacteristicWriteType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static CharacteristicOperationType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static DescriptorOperationType of(@Nonnull String value) {
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
            return ((Number) require("key")).longValue();
        }
        /**
         * Manufacturer-specific data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
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
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * Returns the uuids field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> uuids() {
            return Optional.ofNullable(list(raw("uuids"), element0 -> (String) element0));
        }
        /**
         * Stores the external appearance description of the device.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong appearance() {
            Long value = CdpObject.numberAsLong(raw("appearance"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Stores the transmission power of a broadcasting device.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong txPower() {
            Long value = CdpObject.numberAsLong(raw("txPower"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<BluetoothEmulation.ManufacturerData>> manufacturerData() {
            return Optional.ofNullable(list(raw("manufacturerData"), element0 -> java.util.Objects.requireNonNull(BluetoothEmulation.ManufacturerData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Sets the name field.
         * @param name field value; empty omits the value
         * @return this model
         */
        public ScanRecord name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value; null removes the value
         * @return this model
         */
        public ScanRecord name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the uuids field.
         * @param uuids field value; empty omits the value
         * @return this model
         */
        public ScanRecord uuids(Optional<java.util.List<String>> uuids) {
            set("uuids", uuids.orElse(null));
            return this;
        }
        /**
         * Sets the uuids field.
         * @param uuids field value; null removes the value
         * @return this model
         */
        public ScanRecord uuids(java.util.List<String> uuids) {
            set("uuids", uuids);
            return this;
        }
        /**
         * Stores the external appearance description of the device.
         * @param appearance field value; empty omits the value
         * @return this model
         */
        public ScanRecord appearance(OptionalLong appearance) {
            set("appearance", appearance.isPresent() ? appearance.getAsLong() : null);
            return this;
        }
        /**
         * Stores the external appearance description of the device.
         * @param appearance field value; null removes the value
         * @return this model
         */
        public ScanRecord appearance(Long appearance) {
            set("appearance", appearance);
            return this;
        }
        /**
         * Stores the transmission power of a broadcasting device.
         * @param txPower field value; empty omits the value
         * @return this model
         */
        public ScanRecord txPower(OptionalLong txPower) {
            set("txPower", txPower.isPresent() ? txPower.getAsLong() : null);
            return this;
        }
        /**
         * Stores the transmission power of a broadcasting device.
         * @param txPower field value; null removes the value
         * @return this model
         */
        public ScanRecord txPower(Long txPower) {
            set("txPower", txPower);
            return this;
        }
        /**
         * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
         * @param manufacturerData field value; empty omits the value
         * @return this model
         */
        public ScanRecord manufacturerData(Optional<java.util.List<BluetoothEmulation.ManufacturerData>> manufacturerData) {
            set("manufacturerData", manufacturerData.orElse(null));
            return this;
        }
        /**
         * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
         * @param manufacturerData field value; null removes the value
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
            return (String) require("deviceAddress");
        }
        /**
         * Returns the rssi field.
         * @return the protocol field value
         */
        public long rssi() {
            return ((Number) require("rssi")).longValue();
        }
        /**
         * Returns the scanRecord field.
         * @return the protocol field value
         */
        public BluetoothEmulation.ScanRecord scanRecord() {
            return java.util.Objects.requireNonNull(BluetoothEmulation.ScanRecord.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("scanRecord")))));
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
            return Optional.ofNullable((Boolean) raw("broadcast"));
        }
        /**
         * Returns the read field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> read() {
            return Optional.ofNullable((Boolean) raw("read"));
        }
        /**
         * Returns the writeWithoutResponse field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> writeWithoutResponse() {
            return Optional.ofNullable((Boolean) raw("writeWithoutResponse"));
        }
        /**
         * Returns the write field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> write() {
            return Optional.ofNullable((Boolean) raw("write"));
        }
        /**
         * Returns the notify field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> notifyValue() {
            return Optional.ofNullable((Boolean) raw("notify"));
        }
        /**
         * Returns the indicate field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> indicate() {
            return Optional.ofNullable((Boolean) raw("indicate"));
        }
        /**
         * Returns the authenticatedSignedWrites field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> authenticatedSignedWrites() {
            return Optional.ofNullable((Boolean) raw("authenticatedSignedWrites"));
        }
        /**
         * Returns the extendedProperties field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> extendedProperties() {
            return Optional.ofNullable((Boolean) raw("extendedProperties"));
        }
        /**
         * Sets the broadcast field.
         * @param broadcast field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties broadcast(Optional<Boolean> broadcast) {
            set("broadcast", broadcast.orElse(null));
            return this;
        }
        /**
         * Sets the broadcast field.
         * @param broadcast field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties broadcast(Boolean broadcast) {
            set("broadcast", broadcast);
            return this;
        }
        /**
         * Sets the read field.
         * @param read field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties read(Optional<Boolean> read) {
            set("read", read.orElse(null));
            return this;
        }
        /**
         * Sets the read field.
         * @param read field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties read(Boolean read) {
            set("read", read);
            return this;
        }
        /**
         * Sets the writeWithoutResponse field.
         * @param writeWithoutResponse field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties writeWithoutResponse(Optional<Boolean> writeWithoutResponse) {
            set("writeWithoutResponse", writeWithoutResponse.orElse(null));
            return this;
        }
        /**
         * Sets the writeWithoutResponse field.
         * @param writeWithoutResponse field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties writeWithoutResponse(Boolean writeWithoutResponse) {
            set("writeWithoutResponse", writeWithoutResponse);
            return this;
        }
        /**
         * Sets the write field.
         * @param write field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties write(Optional<Boolean> write) {
            set("write", write.orElse(null));
            return this;
        }
        /**
         * Sets the write field.
         * @param write field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties write(Boolean write) {
            set("write", write);
            return this;
        }
        /**
         * Sets the notify field.
         * @param notifyValue field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties notifyValue(Optional<Boolean> notifyValue) {
            set("notify", notifyValue.orElse(null));
            return this;
        }
        /**
         * Sets the notify field.
         * @param notifyValue field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties notifyValue(Boolean notifyValue) {
            set("notify", notifyValue);
            return this;
        }
        /**
         * Sets the indicate field.
         * @param indicate field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties indicate(Optional<Boolean> indicate) {
            set("indicate", indicate.orElse(null));
            return this;
        }
        /**
         * Sets the indicate field.
         * @param indicate field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties indicate(Boolean indicate) {
            set("indicate", indicate);
            return this;
        }
        /**
         * Sets the authenticatedSignedWrites field.
         * @param authenticatedSignedWrites field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties authenticatedSignedWrites(Optional<Boolean> authenticatedSignedWrites) {
            set("authenticatedSignedWrites", authenticatedSignedWrites.orElse(null));
            return this;
        }
        /**
         * Sets the authenticatedSignedWrites field.
         * @param authenticatedSignedWrites field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties authenticatedSignedWrites(Boolean authenticatedSignedWrites) {
            set("authenticatedSignedWrites", authenticatedSignedWrites);
            return this;
        }
        /**
         * Sets the extendedProperties field.
         * @param extendedProperties field value; empty omits the value
         * @return this model
         */
        public CharacteristicProperties extendedProperties(Optional<Boolean> extendedProperties) {
            set("extendedProperties", extendedProperties.orElse(null));
            return this;
        }
        /**
         * Sets the extendedProperties field.
         * @param extendedProperties field value; null removes the value
         * @return this model
         */
        public CharacteristicProperties extendedProperties(Boolean extendedProperties) {
            set("extendedProperties", extendedProperties);
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
            return (String) require("address");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.GATTOperationType type() {
            return BluetoothEmulation.GATTOperationType.of((String) require("type"));
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
            return (String) require("characteristicId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.CharacteristicOperationType type() {
            return BluetoothEmulation.CharacteristicOperationType.of((String) require("type"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return Optional.ofNullable((String) raw("data"));
        }
        /**
         * Returns the writeType field.
         * @return the protocol field value, empty when absent
         */
        public Optional<BluetoothEmulation.CharacteristicWriteType> writeType() {
            return Optional.ofNullable(raw("writeType") == null ? null : BluetoothEmulation.CharacteristicWriteType.of((String) raw("writeType")));
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
         * @param data field value; empty omits the value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent data(Optional<String> data) {
            set("data", data.orElse(null));
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value; null removes the value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Sets the writeType field.
         * @param writeType field value; empty omits the value
         * @return this model
         */
        public CharacteristicOperationReceivedEvent writeType(Optional<BluetoothEmulation.CharacteristicWriteType> writeType) {
            set("writeType", writeType.orElse(null));
            return this;
        }
        /**
         * Sets the writeType field.
         * @param writeType field value; null removes the value
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
            return (String) require("descriptorId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public BluetoothEmulation.DescriptorOperationType type() {
            return BluetoothEmulation.DescriptorOperationType.of((String) require("type"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return Optional.ofNullable((String) raw("data"));
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
         * @param data field value; empty omits the value
         * @return this model
         */
        public DescriptorOperationReceivedEvent data(Optional<String> data) {
            set("data", data.orElse(null));
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value; null removes the value
         * @return this model
         */
        public DescriptorOperationReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enable the BluetoothEmulation domain.
         * @param state protocol value
         * @param leSupported protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(BluetoothEmulation.CentralState state, boolean leSupported) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("state", CdpObject.json(state));
            params.put("leSupported", CdpObject.json(leSupported));
            return client.call("BluetoothEmulation.enable", params, result_ -> null);
        }
        /**
         * Set the state of the simulated central.
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSimulatedCentralState(BluetoothEmulation.CentralState state) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("state", CdpObject.json(state));
            return client.call("BluetoothEmulation.setSimulatedCentralState", params, result_ -> null);
        }
        /**
         * Disable the BluetoothEmulation domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("BluetoothEmulation.disable", null, result_ -> null);
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("address", CdpObject.json(address));
            params.put("name", CdpObject.json(name));
            params.put("manufacturerData", CdpObject.json(manufacturerData));
            params.put("knownServiceUuids", CdpObject.json(knownServiceUuids));
            return client.call("BluetoothEmulation.simulatePreconnectedPeripheral", params, result_ -> null);
        }
        /**
         * Simulates an advertisement packet described in |entry| being received by the central.
         * @param entry protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateAdvertisement(BluetoothEmulation.ScanEntry entry) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("entry", CdpObject.json(entry));
            return client.call("BluetoothEmulation.simulateAdvertisement", params, result_ -> null);
        }
        /**
         * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
         * @param address protocol value
         * @param type protocol value
         * @param code protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateGATTOperationResponse(String address, BluetoothEmulation.GATTOperationType type, long code) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("address", CdpObject.json(address));
            params.put("type", CdpObject.json(type));
            params.put("code", CdpObject.json(code));
            return client.call("BluetoothEmulation.simulateGATTOperationResponse", params, result_ -> null);
        }
        /**
         * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param characteristicId protocol value
         * @param type protocol value
         * @param code protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateCharacteristicOperationResponse(String characteristicId, BluetoothEmulation.CharacteristicOperationType type, long code, Optional<String> data) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("characteristicId", CdpObject.json(characteristicId));
            params.put("type", CdpObject.json(type));
            params.put("code", CdpObject.json(code));
            data.ifPresent(value_ -> params.put("data", CdpObject.json(value_)));
            return client.call("BluetoothEmulation.simulateCharacteristicOperationResponse", params, result_ -> null);
        }
        /**
         * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param characteristicId protocol value
         * @param type protocol value
         * @param code protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateCharacteristicOperationResponse(String characteristicId, BluetoothEmulation.CharacteristicOperationType type, long code) {
            return simulateCharacteristicOperationResponse(characteristicId, type, code, Optional.empty());
        }
        /**
         * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param descriptorId protocol value
         * @param type protocol value
         * @param code protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateDescriptorOperationResponse(String descriptorId, BluetoothEmulation.DescriptorOperationType type, long code, Optional<String> data) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("descriptorId", CdpObject.json(descriptorId));
            params.put("type", CdpObject.json(type));
            params.put("code", CdpObject.json(code));
            data.ifPresent(value_ -> params.put("data", CdpObject.json(value_)));
            return client.call("BluetoothEmulation.simulateDescriptorOperationResponse", params, result_ -> null);
        }
        /**
         * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param descriptorId protocol value
         * @param type protocol value
         * @param code protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateDescriptorOperationResponse(String descriptorId, BluetoothEmulation.DescriptorOperationType type, long code) {
            return simulateDescriptorOperationResponse(descriptorId, type, code, Optional.empty());
        }
        /**
         * Adds a service with |serviceUuid| to the peripheral with |address|.
         * @param address protocol value
         * @param serviceUuid protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addService(String address, String serviceUuid) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("address", CdpObject.json(address));
            params.put("serviceUuid", CdpObject.json(serviceUuid));
            return client.call("BluetoothEmulation.addService", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("serviceId")));
        }
        /**
         * Removes the service respresented by |serviceId| from the simulated central.
         * @param serviceId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeService(String serviceId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("serviceId", CdpObject.json(serviceId));
            return client.call("BluetoothEmulation.removeService", params, result_ -> null);
        }
        /**
         * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
         * @param serviceId protocol value
         * @param characteristicUuid protocol value
         * @param properties protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addCharacteristic(String serviceId, String characteristicUuid, BluetoothEmulation.CharacteristicProperties properties) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("serviceId", CdpObject.json(serviceId));
            params.put("characteristicUuid", CdpObject.json(characteristicUuid));
            params.put("properties", CdpObject.json(properties));
            return client.call("BluetoothEmulation.addCharacteristic", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("characteristicId")));
        }
        /**
         * Removes the characteristic respresented by |characteristicId| from the simulated central.
         * @param characteristicId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeCharacteristic(String characteristicId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("characteristicId", CdpObject.json(characteristicId));
            return client.call("BluetoothEmulation.removeCharacteristic", params, result_ -> null);
        }
        /**
         * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
         * @param characteristicId protocol value
         * @param descriptorUuid protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> addDescriptor(String characteristicId, String descriptorUuid) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("characteristicId", CdpObject.json(characteristicId));
            params.put("descriptorUuid", CdpObject.json(descriptorUuid));
            return client.call("BluetoothEmulation.addDescriptor", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("descriptorId")));
        }
        /**
         * Removes the descriptor with |descriptorId| from the simulated central.
         * @param descriptorId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDescriptor(String descriptorId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("descriptorId", CdpObject.json(descriptorId));
            return client.call("BluetoothEmulation.removeDescriptor", params, result_ -> null);
        }
        /**
         * Simulates a GATT disconnection from the peripheral with |address|.
         * @param address protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulateGATTDisconnection(String address) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("address", CdpObject.json(address));
            return client.call("BluetoothEmulation.simulateGATTDisconnection", params, result_ -> null);
        }
        /**
         * Event for when a GATT operation of |type| to the peripheral with |address| happened.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onGattOperationReceived(Consumer<GattOperationReceivedEvent> handler) {
            return client.on("BluetoothEmulation.gattOperationReceived", GattOperationReceivedEvent::fromMap, handler);
        }
        /**
         * Event for when a characteristic operation of |type| to the characteristic respresented by |characteristicId| happened. |data| and |writeType| is expected to exist when |type| is write.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCharacteristicOperationReceived(Consumer<CharacteristicOperationReceivedEvent> handler) {
            return client.on("BluetoothEmulation.characteristicOperationReceived", CharacteristicOperationReceivedEvent::fromMap, handler);
        }
        /**
         * Event for when a descriptor operation of |type| to the descriptor respresented by |descriptorId| happened. |data| is expected to exist when |type| is write.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDescriptorOperationReceived(Consumer<DescriptorOperationReceivedEvent> handler) {
            return client.on("BluetoothEmulation.descriptorOperationReceived", DescriptorOperationReceivedEvent::fromMap, handler);
        }
    }
}
