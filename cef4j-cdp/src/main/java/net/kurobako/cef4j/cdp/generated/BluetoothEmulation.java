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
 * This domain allows configuring virtual Bluetooth devices to test the web-bluetooth API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/BluetoothEmulation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class BluetoothEmulation {
    private BluetoothEmulation() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Indicates the various states of Central.
     */
    public static final class CentralState {
        private CentralState() {}
        public static final String ABSENT = "absent";
        public static final String POWERED_OFF = "powered-off";
        public static final String POWERED_ON = "powered-on";
    }
    /**
     * Indicates the various types of GATT event.
     */
    public static final class GATTOperationType {
        private GATTOperationType() {}
        public static final String CONNECTION = "connection";
        public static final String DISCOVERY = "discovery";
    }
    /**
     * Indicates the various types of characteristic write.
     */
    public static final class CharacteristicWriteType {
        private CharacteristicWriteType() {}
        public static final String WRITE_DEFAULT_DEPRECATED = "write-default-deprecated";
        public static final String WRITE_WITH_RESPONSE = "write-with-response";
        public static final String WRITE_WITHOUT_RESPONSE = "write-without-response";
    }
    /**
     * Indicates the various types of characteristic operation.
     */
    public static final class CharacteristicOperationType {
        private CharacteristicOperationType() {}
        public static final String READ = "read";
        public static final String WRITE = "write";
        public static final String SUBSCRIBE_TO_NOTIFICATIONS = "subscribe-to-notifications";
        public static final String UNSUBSCRIBE_FROM_NOTIFICATIONS = "unsubscribe-from-notifications";
    }
    /**
     * Indicates the various types of descriptor operation.
     */
    public static final class DescriptorOperationType {
        private DescriptorOperationType() {}
        public static final String READ = "read";
        public static final String WRITE = "write";
    }
    /**
     * Stores the manufacturer data
     */
    public static final class ManufacturerData extends CdpObject {
        private ManufacturerData(Map<String, Object> values) { super(values); }
        @Nullable public static ManufacturerData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ManufacturerData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Company identifier https://bitbucket.org/bluetooth-SIG/public/src/main/assigned_numbers/company_identifiers/company_identifiers.yaml https://usb.org/developers
         * @return the protocol field value
         */
        @Nullable public Long key() {
            return numberAsLong(value("key"));
        }
        /**
         * Manufacturer-specific data (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Company identifier https://bitbucket.org/bluetooth-SIG/public/src/main/assigned_numbers/company_identifiers/company_identifiers.yaml https://usb.org/developers
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Long value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Manufacturer-specific data (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public ManufacturerData build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new ManufacturerData(values);
            }
        }
    }
    /**
     * Stores the byte data of the advertisement packet sent by a Bluetooth device.
     */
    public static final class ScanRecord extends CdpObject {
        private ScanRecord(Map<String, Object> values) { super(values); }
        @Nullable public static ScanRecord fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScanRecord(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the uuids field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> uuids() {
            return list(value("uuids"), element0 -> (String) element0);
        }
        /**
         * Stores the external appearance description of the device.
         * @return the protocol field value
         */
        @Nullable public Long appearance() {
            return numberAsLong(value("appearance"));
        }
        /**
         * Stores the transmission power of a broadcasting device.
         * @return the protocol field value
         */
        @Nullable public Long txPower() {
            return numberAsLong(value("txPower"));
        }
        /**
         * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
         * @return the protocol field value
         */
        @Nullable public java.util.List<BluetoothEmulation.ManufacturerData> manufacturerData() {
            return list(value("manufacturerData"), element0 -> BluetoothEmulation.ManufacturerData.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
             * Sets the uuids field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uuids(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("uuids");
                else values.put("uuids", jsonValue(value));
                return this;
            }
            /**
             * Stores the external appearance description of the device.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder appearance(@Nullable Long value) {
                if (value == null) values.remove("appearance");
                else values.put("appearance", jsonValue(value));
                return this;
            }
            /**
             * Stores the transmission power of a broadcasting device.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder txPower(@Nullable Long value) {
                if (value == null) values.remove("txPower");
                else values.put("txPower", jsonValue(value));
                return this;
            }
            /**
             * Key is the company identifier and the value is an array of bytes of manufacturer specific data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manufacturerData(@Nullable java.util.List<BluetoothEmulation.ManufacturerData> value) {
                if (value == null) values.remove("manufacturerData");
                else values.put("manufacturerData", jsonValue(value));
                return this;
            }
            public ScanRecord build() {
                return new ScanRecord(values);
            }
        }
    }
    /**
     * Stores the advertisement packet information that is sent by a Bluetooth device.
     */
    public static final class ScanEntry extends CdpObject {
        private ScanEntry(Map<String, Object> values) { super(values); }
        @Nullable public static ScanEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScanEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the deviceAddress field.
         * @return the protocol field value
         */
        @Nullable public String deviceAddress() {
            return (String) value("deviceAddress");
        }
        /**
         * Returns the rssi field.
         * @return the protocol field value
         */
        @Nullable public Long rssi() {
            return numberAsLong(value("rssi"));
        }
        /**
         * Returns the scanRecord field.
         * @return the protocol field value
         */
        @Nullable public BluetoothEmulation.ScanRecord scanRecord() {
            return BluetoothEmulation.ScanRecord.fromMap(objectMap(value("scanRecord")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the deviceAddress field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceAddress(@Nullable String value) {
                if (value == null) values.remove("deviceAddress");
                else values.put("deviceAddress", jsonValue(value));
                return this;
            }
            /**
             * Sets the rssi field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rssi(@Nullable Long value) {
                if (value == null) values.remove("rssi");
                else values.put("rssi", jsonValue(value));
                return this;
            }
            /**
             * Sets the scanRecord field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scanRecord(@Nullable BluetoothEmulation.ScanRecord value) {
                if (value == null) values.remove("scanRecord");
                else values.put("scanRecord", jsonValue(value));
                return this;
            }
            public ScanEntry build() {
                if (!values.containsKey("deviceAddress")) throw new IllegalStateException("Missing required CDP field: deviceAddress");
                if (!values.containsKey("rssi")) throw new IllegalStateException("Missing required CDP field: rssi");
                if (!values.containsKey("scanRecord")) throw new IllegalStateException("Missing required CDP field: scanRecord");
                return new ScanEntry(values);
            }
        }
    }
    /**
     * Describes the properties of a characteristic. This follows Bluetooth Core Specification BT 4.2 Vol 3 Part G 3.3.1. Characteristic Properties.
     */
    public static final class CharacteristicProperties extends CdpObject {
        private CharacteristicProperties(Map<String, Object> values) { super(values); }
        @Nullable public static CharacteristicProperties fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CharacteristicProperties(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the broadcast field.
         * @return the protocol field value
         */
        @Nullable public Boolean broadcast() {
            return (Boolean) value("broadcast");
        }
        /**
         * Returns the read field.
         * @return the protocol field value
         */
        @Nullable public Boolean read() {
            return (Boolean) value("read");
        }
        /**
         * Returns the writeWithoutResponse field.
         * @return the protocol field value
         */
        @Nullable public Boolean writeWithoutResponse() {
            return (Boolean) value("writeWithoutResponse");
        }
        /**
         * Returns the write field.
         * @return the protocol field value
         */
        @Nullable public Boolean write() {
            return (Boolean) value("write");
        }
        /**
         * Returns the notify field.
         * @return the protocol field value
         */
        @Nullable public Boolean notifyValue() {
            return (Boolean) value("notify");
        }
        /**
         * Returns the indicate field.
         * @return the protocol field value
         */
        @Nullable public Boolean indicate() {
            return (Boolean) value("indicate");
        }
        /**
         * Returns the authenticatedSignedWrites field.
         * @return the protocol field value
         */
        @Nullable public Boolean authenticatedSignedWrites() {
            return (Boolean) value("authenticatedSignedWrites");
        }
        /**
         * Returns the extendedProperties field.
         * @return the protocol field value
         */
        @Nullable public Boolean extendedProperties() {
            return (Boolean) value("extendedProperties");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the broadcast field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder broadcast(@Nullable Boolean value) {
                if (value == null) values.remove("broadcast");
                else values.put("broadcast", jsonValue(value));
                return this;
            }
            /**
             * Sets the read field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder read(@Nullable Boolean value) {
                if (value == null) values.remove("read");
                else values.put("read", jsonValue(value));
                return this;
            }
            /**
             * Sets the writeWithoutResponse field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder writeWithoutResponse(@Nullable Boolean value) {
                if (value == null) values.remove("writeWithoutResponse");
                else values.put("writeWithoutResponse", jsonValue(value));
                return this;
            }
            /**
             * Sets the write field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder write(@Nullable Boolean value) {
                if (value == null) values.remove("write");
                else values.put("write", jsonValue(value));
                return this;
            }
            /**
             * Sets the notify field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder notifyValue(@Nullable Boolean value) {
                if (value == null) values.remove("notify");
                else values.put("notify", jsonValue(value));
                return this;
            }
            /**
             * Sets the indicate field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder indicate(@Nullable Boolean value) {
                if (value == null) values.remove("indicate");
                else values.put("indicate", jsonValue(value));
                return this;
            }
            /**
             * Sets the authenticatedSignedWrites field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authenticatedSignedWrites(@Nullable Boolean value) {
                if (value == null) values.remove("authenticatedSignedWrites");
                else values.put("authenticatedSignedWrites", jsonValue(value));
                return this;
            }
            /**
             * Sets the extendedProperties field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder extendedProperties(@Nullable Boolean value) {
                if (value == null) values.remove("extendedProperties");
                else values.put("extendedProperties", jsonValue(value));
                return this;
            }
            public CharacteristicProperties build() {
                return new CharacteristicProperties(values);
            }
        }
    }
    /**
     * Enable the BluetoothEmulation domain.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * State of the simulated central.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        /**
         * If the simulated central supports low-energy.
         * @return the protocol field value
         */
        @Nullable public Boolean leSupported() {
            return (Boolean) value("leSupported");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * State of the simulated central.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            /**
             * If the simulated central supports low-energy.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder leSupported(@Nullable Boolean value) {
                if (value == null) values.remove("leSupported");
                else values.put("leSupported", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                if (!values.containsKey("leSupported")) throw new IllegalStateException("Missing required CDP field: leSupported");
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enable the BluetoothEmulation domain.
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
     * Set the state of the simulated central.
     */
    public static final class SetSimulatedCentralStateParams extends CdpObject {
        private SetSimulatedCentralStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSimulatedCentralStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSimulatedCentralStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * State of the simulated central.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * State of the simulated central.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            public SetSimulatedCentralStateParams build() {
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                return new SetSimulatedCentralStateParams(values);
            }
        }
    }
    /**
     * Set the state of the simulated central.
     */
    public static final class SetSimulatedCentralStateResult extends CdpObject {
        private SetSimulatedCentralStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSimulatedCentralStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSimulatedCentralStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSimulatedCentralStateResult build() {
                return new SetSimulatedCentralStateResult(values);
            }
        }
    }
    /**
     * Disable the BluetoothEmulation domain.
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
     * Disable the BluetoothEmulation domain.
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
     * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been connected to the system.
     */
    public static final class SimulatePreconnectedPeripheralParams extends CdpObject {
        private SimulatePreconnectedPeripheralParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulatePreconnectedPeripheralParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulatePreconnectedPeripheralParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        @Nullable public String address() {
            return (String) value("address");
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the manufacturerData field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<BluetoothEmulation.ManufacturerData> manufacturerData() {
            return list(value("manufacturerData"), element0 -> BluetoothEmulation.ManufacturerData.fromMap(objectMap(element0)));
        }
        /**
         * Returns the knownServiceUuids field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> knownServiceUuids() {
            return list(value("knownServiceUuids"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the address field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder address(@Nullable String value) {
                if (value == null) values.remove("address");
                else values.put("address", jsonValue(value));
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
             * Sets the manufacturerData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder manufacturerData(@Nullable java.util.List<BluetoothEmulation.ManufacturerData> value) {
                if (value == null) values.remove("manufacturerData");
                else values.put("manufacturerData", jsonValue(value));
                return this;
            }
            /**
             * Sets the knownServiceUuids field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder knownServiceUuids(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("knownServiceUuids");
                else values.put("knownServiceUuids", jsonValue(value));
                return this;
            }
            public SimulatePreconnectedPeripheralParams build() {
                if (!values.containsKey("address")) throw new IllegalStateException("Missing required CDP field: address");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("manufacturerData")) throw new IllegalStateException("Missing required CDP field: manufacturerData");
                if (!values.containsKey("knownServiceUuids")) throw new IllegalStateException("Missing required CDP field: knownServiceUuids");
                return new SimulatePreconnectedPeripheralParams(values);
            }
        }
    }
    /**
     * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been connected to the system.
     */
    public static final class SimulatePreconnectedPeripheralResult extends CdpObject {
        private SimulatePreconnectedPeripheralResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulatePreconnectedPeripheralResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulatePreconnectedPeripheralResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulatePreconnectedPeripheralResult build() {
                return new SimulatePreconnectedPeripheralResult(values);
            }
        }
    }
    /**
     * Simulates an advertisement packet described in |entry| being received by the central.
     */
    public static final class SimulateAdvertisementParams extends CdpObject {
        private SimulateAdvertisementParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateAdvertisementParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateAdvertisementParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the entry field.
         * @return the protocol field value
         */
        @Nullable public BluetoothEmulation.ScanEntry entry() {
            return BluetoothEmulation.ScanEntry.fromMap(objectMap(value("entry")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the entry field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entry(@Nullable BluetoothEmulation.ScanEntry value) {
                if (value == null) values.remove("entry");
                else values.put("entry", jsonValue(value));
                return this;
            }
            public SimulateAdvertisementParams build() {
                if (!values.containsKey("entry")) throw new IllegalStateException("Missing required CDP field: entry");
                return new SimulateAdvertisementParams(values);
            }
        }
    }
    /**
     * Simulates an advertisement packet described in |entry| being received by the central.
     */
    public static final class SimulateAdvertisementResult extends CdpObject {
        private SimulateAdvertisementResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateAdvertisementResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateAdvertisementResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulateAdvertisementResult build() {
                return new SimulateAdvertisementResult(values);
            }
        }
    }
    /**
     * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
     */
    public static final class SimulateGATTOperationResponseParams extends CdpObject {
        private SimulateGATTOperationResponseParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateGATTOperationResponseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateGATTOperationResponseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        @Nullable public String address() {
            return (String) value("address");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        @Nullable public Long code() {
            return numberAsLong(value("code"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the address field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder address(@Nullable String value) {
                if (value == null) values.remove("address");
                else values.put("address", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the code field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder code(@Nullable Long value) {
                if (value == null) values.remove("code");
                else values.put("code", jsonValue(value));
                return this;
            }
            public SimulateGATTOperationResponseParams build() {
                if (!values.containsKey("address")) throw new IllegalStateException("Missing required CDP field: address");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("code")) throw new IllegalStateException("Missing required CDP field: code");
                return new SimulateGATTOperationResponseParams(values);
            }
        }
    }
    /**
     * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
     */
    public static final class SimulateGATTOperationResponseResult extends CdpObject {
        private SimulateGATTOperationResponseResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateGATTOperationResponseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateGATTOperationResponseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulateGATTOperationResponseResult build() {
                return new SimulateGATTOperationResponseResult(values);
            }
        }
    }
    /**
     * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
     */
    public static final class SimulateCharacteristicOperationResponseParams extends CdpObject {
        private SimulateCharacteristicOperationResponseParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateCharacteristicOperationResponseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateCharacteristicOperationResponseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        @Nullable public String characteristicId() {
            return (String) value("characteristicId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        @Nullable public Long code() {
            return numberAsLong(value("code"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the characteristicId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characteristicId(@Nullable String value) {
                if (value == null) values.remove("characteristicId");
                else values.put("characteristicId", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the code field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder code(@Nullable Long value) {
                if (value == null) values.remove("code");
                else values.put("code", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public SimulateCharacteristicOperationResponseParams build() {
                if (!values.containsKey("characteristicId")) throw new IllegalStateException("Missing required CDP field: characteristicId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("code")) throw new IllegalStateException("Missing required CDP field: code");
                return new SimulateCharacteristicOperationResponseParams(values);
            }
        }
    }
    /**
     * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
     */
    public static final class SimulateCharacteristicOperationResponseResult extends CdpObject {
        private SimulateCharacteristicOperationResponseResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateCharacteristicOperationResponseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateCharacteristicOperationResponseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulateCharacteristicOperationResponseResult build() {
                return new SimulateCharacteristicOperationResponseResult(values);
            }
        }
    }
    /**
     * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
     */
    public static final class SimulateDescriptorOperationResponseParams extends CdpObject {
        private SimulateDescriptorOperationResponseParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateDescriptorOperationResponseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateDescriptorOperationResponseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the descriptorId field.
         * @return the protocol field value
         */
        @Nullable public String descriptorId() {
            return (String) value("descriptorId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        @Nullable public Long code() {
            return numberAsLong(value("code"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the descriptorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descriptorId(@Nullable String value) {
                if (value == null) values.remove("descriptorId");
                else values.put("descriptorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the code field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder code(@Nullable Long value) {
                if (value == null) values.remove("code");
                else values.put("code", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public SimulateDescriptorOperationResponseParams build() {
                if (!values.containsKey("descriptorId")) throw new IllegalStateException("Missing required CDP field: descriptorId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("code")) throw new IllegalStateException("Missing required CDP field: code");
                return new SimulateDescriptorOperationResponseParams(values);
            }
        }
    }
    /**
     * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
     */
    public static final class SimulateDescriptorOperationResponseResult extends CdpObject {
        private SimulateDescriptorOperationResponseResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateDescriptorOperationResponseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateDescriptorOperationResponseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulateDescriptorOperationResponseResult build() {
                return new SimulateDescriptorOperationResponseResult(values);
            }
        }
    }
    /**
     * Adds a service with |serviceUuid| to the peripheral with |address|.
     */
    public static final class AddServiceParams extends CdpObject {
        private AddServiceParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddServiceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddServiceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        @Nullable public String address() {
            return (String) value("address");
        }
        /**
         * Returns the serviceUuid field.
         * @return the protocol field value
         */
        @Nullable public String serviceUuid() {
            return (String) value("serviceUuid");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the address field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder address(@Nullable String value) {
                if (value == null) values.remove("address");
                else values.put("address", jsonValue(value));
                return this;
            }
            /**
             * Sets the serviceUuid field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceUuid(@Nullable String value) {
                if (value == null) values.remove("serviceUuid");
                else values.put("serviceUuid", jsonValue(value));
                return this;
            }
            public AddServiceParams build() {
                if (!values.containsKey("address")) throw new IllegalStateException("Missing required CDP field: address");
                if (!values.containsKey("serviceUuid")) throw new IllegalStateException("Missing required CDP field: serviceUuid");
                return new AddServiceParams(values);
            }
        }
    }
    /**
     * Adds a service with |serviceUuid| to the peripheral with |address|.
     */
    public static final class AddServiceResult extends CdpObject {
        private AddServiceResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddServiceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddServiceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An identifier that uniquely represents this service.
         * @return the protocol field value
         */
        @Nullable public String serviceId() {
            return (String) value("serviceId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An identifier that uniquely represents this service.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceId(@Nullable String value) {
                if (value == null) values.remove("serviceId");
                else values.put("serviceId", jsonValue(value));
                return this;
            }
            public AddServiceResult build() {
                if (!values.containsKey("serviceId")) throw new IllegalStateException("Missing required CDP field: serviceId");
                return new AddServiceResult(values);
            }
        }
    }
    /**
     * Removes the service respresented by |serviceId| from the simulated central.
     */
    public static final class RemoveServiceParams extends CdpObject {
        private RemoveServiceParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveServiceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveServiceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the serviceId field.
         * @return the protocol field value
         */
        @Nullable public String serviceId() {
            return (String) value("serviceId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the serviceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceId(@Nullable String value) {
                if (value == null) values.remove("serviceId");
                else values.put("serviceId", jsonValue(value));
                return this;
            }
            public RemoveServiceParams build() {
                if (!values.containsKey("serviceId")) throw new IllegalStateException("Missing required CDP field: serviceId");
                return new RemoveServiceParams(values);
            }
        }
    }
    /**
     * Removes the service respresented by |serviceId| from the simulated central.
     */
    public static final class RemoveServiceResult extends CdpObject {
        private RemoveServiceResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveServiceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveServiceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveServiceResult build() {
                return new RemoveServiceResult(values);
            }
        }
    }
    /**
     * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
     */
    public static final class AddCharacteristicParams extends CdpObject {
        private AddCharacteristicParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddCharacteristicParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddCharacteristicParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the serviceId field.
         * @return the protocol field value
         */
        @Nullable public String serviceId() {
            return (String) value("serviceId");
        }
        /**
         * Returns the characteristicUuid field.
         * @return the protocol field value
         */
        @Nullable public String characteristicUuid() {
            return (String) value("characteristicUuid");
        }
        /**
         * Returns the properties field.
         * @return the protocol field value
         */
        @Nullable public BluetoothEmulation.CharacteristicProperties properties() {
            return BluetoothEmulation.CharacteristicProperties.fromMap(objectMap(value("properties")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the serviceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceId(@Nullable String value) {
                if (value == null) values.remove("serviceId");
                else values.put("serviceId", jsonValue(value));
                return this;
            }
            /**
             * Sets the characteristicUuid field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characteristicUuid(@Nullable String value) {
                if (value == null) values.remove("characteristicUuid");
                else values.put("characteristicUuid", jsonValue(value));
                return this;
            }
            /**
             * Sets the properties field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder properties(@Nullable BluetoothEmulation.CharacteristicProperties value) {
                if (value == null) values.remove("properties");
                else values.put("properties", jsonValue(value));
                return this;
            }
            public AddCharacteristicParams build() {
                if (!values.containsKey("serviceId")) throw new IllegalStateException("Missing required CDP field: serviceId");
                if (!values.containsKey("characteristicUuid")) throw new IllegalStateException("Missing required CDP field: characteristicUuid");
                if (!values.containsKey("properties")) throw new IllegalStateException("Missing required CDP field: properties");
                return new AddCharacteristicParams(values);
            }
        }
    }
    /**
     * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
     */
    public static final class AddCharacteristicResult extends CdpObject {
        private AddCharacteristicResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddCharacteristicResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddCharacteristicResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An identifier that uniquely represents this characteristic.
         * @return the protocol field value
         */
        @Nullable public String characteristicId() {
            return (String) value("characteristicId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An identifier that uniquely represents this characteristic.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characteristicId(@Nullable String value) {
                if (value == null) values.remove("characteristicId");
                else values.put("characteristicId", jsonValue(value));
                return this;
            }
            public AddCharacteristicResult build() {
                if (!values.containsKey("characteristicId")) throw new IllegalStateException("Missing required CDP field: characteristicId");
                return new AddCharacteristicResult(values);
            }
        }
    }
    /**
     * Removes the characteristic respresented by |characteristicId| from the simulated central.
     */
    public static final class RemoveCharacteristicParams extends CdpObject {
        private RemoveCharacteristicParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveCharacteristicParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveCharacteristicParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        @Nullable public String characteristicId() {
            return (String) value("characteristicId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the characteristicId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characteristicId(@Nullable String value) {
                if (value == null) values.remove("characteristicId");
                else values.put("characteristicId", jsonValue(value));
                return this;
            }
            public RemoveCharacteristicParams build() {
                if (!values.containsKey("characteristicId")) throw new IllegalStateException("Missing required CDP field: characteristicId");
                return new RemoveCharacteristicParams(values);
            }
        }
    }
    /**
     * Removes the characteristic respresented by |characteristicId| from the simulated central.
     */
    public static final class RemoveCharacteristicResult extends CdpObject {
        private RemoveCharacteristicResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveCharacteristicResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveCharacteristicResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveCharacteristicResult build() {
                return new RemoveCharacteristicResult(values);
            }
        }
    }
    /**
     * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
     */
    public static final class AddDescriptorParams extends CdpObject {
        private AddDescriptorParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddDescriptorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddDescriptorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        @Nullable public String characteristicId() {
            return (String) value("characteristicId");
        }
        /**
         * Returns the descriptorUuid field.
         * @return the protocol field value
         */
        @Nullable public String descriptorUuid() {
            return (String) value("descriptorUuid");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the characteristicId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characteristicId(@Nullable String value) {
                if (value == null) values.remove("characteristicId");
                else values.put("characteristicId", jsonValue(value));
                return this;
            }
            /**
             * Sets the descriptorUuid field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descriptorUuid(@Nullable String value) {
                if (value == null) values.remove("descriptorUuid");
                else values.put("descriptorUuid", jsonValue(value));
                return this;
            }
            public AddDescriptorParams build() {
                if (!values.containsKey("characteristicId")) throw new IllegalStateException("Missing required CDP field: characteristicId");
                if (!values.containsKey("descriptorUuid")) throw new IllegalStateException("Missing required CDP field: descriptorUuid");
                return new AddDescriptorParams(values);
            }
        }
    }
    /**
     * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
     */
    public static final class AddDescriptorResult extends CdpObject {
        private AddDescriptorResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddDescriptorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddDescriptorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An identifier that uniquely represents this descriptor.
         * @return the protocol field value
         */
        @Nullable public String descriptorId() {
            return (String) value("descriptorId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An identifier that uniquely represents this descriptor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descriptorId(@Nullable String value) {
                if (value == null) values.remove("descriptorId");
                else values.put("descriptorId", jsonValue(value));
                return this;
            }
            public AddDescriptorResult build() {
                if (!values.containsKey("descriptorId")) throw new IllegalStateException("Missing required CDP field: descriptorId");
                return new AddDescriptorResult(values);
            }
        }
    }
    /**
     * Removes the descriptor with |descriptorId| from the simulated central.
     */
    public static final class RemoveDescriptorParams extends CdpObject {
        private RemoveDescriptorParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveDescriptorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveDescriptorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the descriptorId field.
         * @return the protocol field value
         */
        @Nullable public String descriptorId() {
            return (String) value("descriptorId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the descriptorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descriptorId(@Nullable String value) {
                if (value == null) values.remove("descriptorId");
                else values.put("descriptorId", jsonValue(value));
                return this;
            }
            public RemoveDescriptorParams build() {
                if (!values.containsKey("descriptorId")) throw new IllegalStateException("Missing required CDP field: descriptorId");
                return new RemoveDescriptorParams(values);
            }
        }
    }
    /**
     * Removes the descriptor with |descriptorId| from the simulated central.
     */
    public static final class RemoveDescriptorResult extends CdpObject {
        private RemoveDescriptorResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveDescriptorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveDescriptorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveDescriptorResult build() {
                return new RemoveDescriptorResult(values);
            }
        }
    }
    /**
     * Simulates a GATT disconnection from the peripheral with |address|.
     */
    public static final class SimulateGATTDisconnectionParams extends CdpObject {
        private SimulateGATTDisconnectionParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateGATTDisconnectionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateGATTDisconnectionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        @Nullable public String address() {
            return (String) value("address");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the address field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder address(@Nullable String value) {
                if (value == null) values.remove("address");
                else values.put("address", jsonValue(value));
                return this;
            }
            public SimulateGATTDisconnectionParams build() {
                if (!values.containsKey("address")) throw new IllegalStateException("Missing required CDP field: address");
                return new SimulateGATTDisconnectionParams(values);
            }
        }
    }
    /**
     * Simulates a GATT disconnection from the peripheral with |address|.
     */
    public static final class SimulateGATTDisconnectionResult extends CdpObject {
        private SimulateGATTDisconnectionResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulateGATTDisconnectionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulateGATTDisconnectionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulateGATTDisconnectionResult build() {
                return new SimulateGATTDisconnectionResult(values);
            }
        }
    }
    /**
     * Event for when a GATT operation of |type| to the peripheral with |address| happened.
     */
    public static final class GattOperationReceivedEvent extends CdpObject {
        private GattOperationReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static GattOperationReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GattOperationReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the address field.
         * @return the protocol field value
         */
        @Nullable public String address() {
            return (String) value("address");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the address field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder address(@Nullable String value) {
                if (value == null) values.remove("address");
                else values.put("address", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public GattOperationReceivedEvent build() {
                if (!values.containsKey("address")) throw new IllegalStateException("Missing required CDP field: address");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new GattOperationReceivedEvent(values);
            }
        }
    }
    /**
     * Event for when a characteristic operation of |type| to the characteristic respresented by |characteristicId| happened. |data| and |writeType| is expected to exist when |type| is write.
     */
    public static final class CharacteristicOperationReceivedEvent extends CdpObject {
        private CharacteristicOperationReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CharacteristicOperationReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CharacteristicOperationReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the characteristicId field.
         * @return the protocol field value
         */
        @Nullable public String characteristicId() {
            return (String) value("characteristicId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Returns the writeType field.
         * @return the protocol field value
         */
        @Nullable public String writeType() {
            return (String) value("writeType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the characteristicId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characteristicId(@Nullable String value) {
                if (value == null) values.remove("characteristicId");
                else values.put("characteristicId", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Sets the writeType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder writeType(@Nullable String value) {
                if (value == null) values.remove("writeType");
                else values.put("writeType", jsonValue(value));
                return this;
            }
            public CharacteristicOperationReceivedEvent build() {
                if (!values.containsKey("characteristicId")) throw new IllegalStateException("Missing required CDP field: characteristicId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new CharacteristicOperationReceivedEvent(values);
            }
        }
    }
    /**
     * Event for when a descriptor operation of |type| to the descriptor respresented by |descriptorId| happened. |data| is expected to exist when |type| is write.
     */
    public static final class DescriptorOperationReceivedEvent extends CdpObject {
        private DescriptorOperationReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DescriptorOperationReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DescriptorOperationReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the descriptorId field.
         * @return the protocol field value
         */
        @Nullable public String descriptorId() {
            return (String) value("descriptorId");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the descriptorId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descriptorId(@Nullable String value) {
                if (value == null) values.remove("descriptorId");
                else values.put("descriptorId", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public DescriptorOperationReceivedEvent build() {
                if (!values.containsKey("descriptorId")) throw new IllegalStateException("Missing required CDP field: descriptorId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new DescriptorOperationReceivedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enable the BluetoothEmulation domain.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("BluetoothEmulation.enable", params, EnableResult::fromMap);
        }
        /**
         * Set the state of the simulated central.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSimulatedCentralStateResult> setSimulatedCentralState(SetSimulatedCentralStateParams params) {
            return client.call("BluetoothEmulation.setSimulatedCentralState", params, SetSimulatedCentralStateResult::fromMap);
        }
        /**
         * Disable the BluetoothEmulation domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("BluetoothEmulation.disable", null, DisableResult::fromMap);
        }
        /**
         * Simulates a peripheral with |address|, |name| and |knownServiceUuids| that has already been connected to the system.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulatePreconnectedPeripheralResult> simulatePreconnectedPeripheral(SimulatePreconnectedPeripheralParams params) {
            return client.call("BluetoothEmulation.simulatePreconnectedPeripheral", params, SimulatePreconnectedPeripheralResult::fromMap);
        }
        /**
         * Simulates an advertisement packet described in |entry| being received by the central.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulateAdvertisementResult> simulateAdvertisement(SimulateAdvertisementParams params) {
            return client.call("BluetoothEmulation.simulateAdvertisement", params, SimulateAdvertisementResult::fromMap);
        }
        /**
         * Simulates the response code from the peripheral with |address| for a GATT operation of |type|. The |code| value follows the HCI Error Codes from Bluetooth Core Specification Vol 2 Part D 1.3 List Of Error Codes.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulateGATTOperationResponseResult> simulateGATTOperationResponse(SimulateGATTOperationResponseParams params) {
            return client.call("BluetoothEmulation.simulateGATTOperationResponse", params, SimulateGATTOperationResponseResult::fromMap);
        }
        /**
         * Simulates the response from the characteristic with |characteristicId| for a characteristic operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulateCharacteristicOperationResponseResult> simulateCharacteristicOperationResponse(SimulateCharacteristicOperationResponseParams params) {
            return client.call("BluetoothEmulation.simulateCharacteristicOperationResponse", params, SimulateCharacteristicOperationResponseResult::fromMap);
        }
        /**
         * Simulates the response from the descriptor with |descriptorId| for a descriptor operation of |type|. The |code| value follows the Error Codes from Bluetooth Core Specification Vol 3 Part F 3.4.1.1 Error Response. The |data| is expected to exist when simulating a successful read operation response.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulateDescriptorOperationResponseResult> simulateDescriptorOperationResponse(SimulateDescriptorOperationResponseParams params) {
            return client.call("BluetoothEmulation.simulateDescriptorOperationResponse", params, SimulateDescriptorOperationResponseResult::fromMap);
        }
        /**
         * Adds a service with |serviceUuid| to the peripheral with |address|.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddServiceResult> addService(AddServiceParams params) {
            return client.call("BluetoothEmulation.addService", params, AddServiceResult::fromMap);
        }
        /**
         * Removes the service respresented by |serviceId| from the simulated central.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveServiceResult> removeService(RemoveServiceParams params) {
            return client.call("BluetoothEmulation.removeService", params, RemoveServiceResult::fromMap);
        }
        /**
         * Adds a characteristic with |characteristicUuid| and |properties| to the service represented by |serviceId|.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddCharacteristicResult> addCharacteristic(AddCharacteristicParams params) {
            return client.call("BluetoothEmulation.addCharacteristic", params, AddCharacteristicResult::fromMap);
        }
        /**
         * Removes the characteristic respresented by |characteristicId| from the simulated central.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveCharacteristicResult> removeCharacteristic(RemoveCharacteristicParams params) {
            return client.call("BluetoothEmulation.removeCharacteristic", params, RemoveCharacteristicResult::fromMap);
        }
        /**
         * Adds a descriptor with |descriptorUuid| to the characteristic respresented by |characteristicId|.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddDescriptorResult> addDescriptor(AddDescriptorParams params) {
            return client.call("BluetoothEmulation.addDescriptor", params, AddDescriptorResult::fromMap);
        }
        /**
         * Removes the descriptor with |descriptorId| from the simulated central.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveDescriptorResult> removeDescriptor(RemoveDescriptorParams params) {
            return client.call("BluetoothEmulation.removeDescriptor", params, RemoveDescriptorResult::fromMap);
        }
        /**
         * Simulates a GATT disconnection from the peripheral with |address|.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulateGATTDisconnectionResult> simulateGATTDisconnection(SimulateGATTDisconnectionParams params) {
            return client.call("BluetoothEmulation.simulateGATTDisconnection", params, SimulateGATTDisconnectionResult::fromMap);
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
