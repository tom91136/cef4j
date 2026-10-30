// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
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
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects. Evaluation results are returned as mirror object that expose object type, string representation and unique identifier that can be used for further object reference. Original objects are maintained in memory unless they are either explicitly released or are released along with the other objects in their object group.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Runtime {
    private Runtime() {}
    /**
     * Unique script identifier.
     */
    public static final class ScriptId implements CdpValue<String> {
        public final String value;
        public ScriptId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof ScriptId)) return false;
            return value.equals(((ScriptId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "ScriptId(" + value + ")"; }
    }
    /**
     * Represents options for serialization. Overrides {@code generatePreview} and {@code returnByValue}.
     */
    public static final class SerializationOptions extends CdpObject {
        public SerializationOptions() {}
        private SerializationOptions(Map<String, Object> values) { super(values); }
        public static SerializationOptions fromMap(Map<String, Object> values) {
            return new SerializationOptions(values);
        }
        /**
         * Wire values for SerializationValues.
         */
        public enum SerializationValues implements CdpValue<String> {
            DEEP("deep"),
            JSON("json"),
            IDONLY("idOnly");
            public final String value;
            SerializationValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static SerializationValues of(String value) {
                for (SerializationValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SerializationValues value: " + value);
            }
        }
        /**
         * Returns the serialization field.
         * @return the protocol field value
         */
        public SerializationOptions.SerializationValues serialization() {
            return SerializationOptions.SerializationValues.of((String) require("serialization"));
        }
        /**
         * Deep serialization depth. Default is full depth. Respected only in {@code deep} serialization mode.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxDepth() {
            return raw("maxDepth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> additionalParameters() {
            return raw("additionalParameters").flatMap(CdpObject::objectMap);
        }
        /**
         * Sets the serialization field.
         * @param serialization field value
         * @return this model
         */
        public SerializationOptions serialization(SerializationOptions.SerializationValues serialization) {
            set("serialization", serialization);
            return this;
        }
        /**
         * Deep serialization depth. Default is full depth. Respected only in {@code deep} serialization mode.
         * @param maxDepth field value
         * @return this model
         */
        public SerializationOptions maxDepth(long maxDepth) {
            set("maxDepth", maxDepth);
            return this;
        }
        /**
         * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
         * @param additionalParameters field value
         * @return this model
         */
        public SerializationOptions additionalParameters(java.util.Map<String, Object> additionalParameters) {
            set("additionalParameters", additionalParameters);
            return this;
        }
    }
    /**
     * Represents deep serialized value.
     */
    public static final class DeepSerializedValue extends CdpObject {
        public DeepSerializedValue() {}
        private DeepSerializedValue(Map<String, Object> values) { super(values); }
        public static DeepSerializedValue fromMap(Map<String, Object> values) {
            return new DeepSerializedValue(values);
        }
        /**
         * Wire values for TypeValues.
         */
        public enum TypeValues implements CdpValue<String> {
            UNDEFINED("undefined"),
            NULL("null"),
            STRING("string"),
            NUMBER("number"),
            BOOLEAN("boolean"),
            BIGINT("bigint"),
            REGEXP("regexp"),
            DATE("date"),
            SYMBOL("symbol"),
            ARRAY("array"),
            OBJECT("object"),
            FUNCTION("function"),
            MAP("map"),
            SET("set"),
            WEAKMAP("weakmap"),
            WEAKSET("weakset"),
            ERROR("error"),
            PROXY("proxy"),
            PROMISE("promise"),
            TYPEDARRAY("typedarray"),
            ARRAYBUFFER("arraybuffer"),
            NODE("node"),
            WINDOW("window"),
            GENERATOR("generator");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public DeepSerializedValue.TypeValues type() {
            return DeepSerializedValue.TypeValues.of((String) require("type"));
        }
        /**
         * Returns the value field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Object> value() {
            return raw("value");
        }
        /**
         * Returns the objectId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectId() {
            return raw("objectId").map(value -> (String) value);
        }
        /**
         * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong weakLocalObjectReference() {
            return raw("weakLocalObjectReference").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public DeepSerializedValue type(DeepSerializedValue.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public DeepSerializedValue value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * Sets the objectId field.
         * @param objectId field value
         * @return this model
         */
        public DeepSerializedValue objectId(String objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
         * @param weakLocalObjectReference field value
         * @return this model
         */
        public DeepSerializedValue weakLocalObjectReference(long weakLocalObjectReference) {
            set("weakLocalObjectReference", weakLocalObjectReference);
            return this;
        }
    }
    /**
     * Unique object identifier.
     */
    public static final class RemoteObjectId implements CdpValue<String> {
        public final String value;
        public RemoteObjectId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof RemoteObjectId)) return false;
            return value.equals(((RemoteObjectId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "RemoteObjectId(" + value + ")"; }
    }
    /**
     * Primitive value which cannot be JSON-stringified. Includes values {@code -0}, {@code NaN}, {@code Infinity}, {@code -Infinity}, and bigint literals.
     */
    public static final class UnserializableValue implements CdpValue<String> {
        public final String value;
        public UnserializableValue(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof UnserializableValue)) return false;
            return value.equals(((UnserializableValue) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "UnserializableValue(" + value + ")"; }
    }
    /**
     * Mirror object referencing original JavaScript object.
     */
    public static final class RemoteObject extends CdpObject {
        public RemoteObject() {}
        private RemoteObject(Map<String, Object> values) { super(values); }
        public static RemoteObject fromMap(Map<String, Object> values) {
            return new RemoteObject(values);
        }
        /**
         * Object type.
         */
        public enum TypeValues implements CdpValue<String> {
            OBJECT("object"),
            FUNCTION("function"),
            UNDEFINED("undefined"),
            STRING("string"),
            NUMBER("number"),
            BOOLEAN("boolean"),
            SYMBOL("symbol"),
            BIGINT("bigint");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
         */
        public enum SubtypeValues implements CdpValue<String> {
            ARRAY("array"),
            NULL("null"),
            NODE("node"),
            REGEXP("regexp"),
            DATE("date"),
            MAP("map"),
            SET("set"),
            WEAKMAP("weakmap"),
            WEAKSET("weakset"),
            ITERATOR("iterator"),
            GENERATOR("generator"),
            ERROR("error"),
            PROXY("proxy"),
            PROMISE("promise"),
            TYPEDARRAY("typedarray"),
            ARRAYBUFFER("arraybuffer"),
            DATAVIEW("dataview"),
            WEBASSEMBLYMEMORY("webassemblymemory"),
            WASMVALUE("wasmvalue"),
            TRUSTEDTYPE("trustedtype");
            public final String value;
            SubtypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static SubtypeValues of(String value) {
                for (SubtypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SubtypeValues value: " + value);
            }
        }
        /**
         * Object type.
         * @return the protocol field value
         */
        public RemoteObject.TypeValues type() {
            return RemoteObject.TypeValues.of((String) require("type"));
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
         * @return the protocol field value, empty when absent
         */
        public Optional<RemoteObject.SubtypeValues> subtype() {
            return raw("subtype").map(value -> RemoteObject.SubtypeValues.of((String) value));
        }
        /**
         * Object class (constructor) name. Specified for {@code object} type values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> className() {
            return raw("className").map(value -> (String) value);
        }
        /**
         * Remote object value in case of primitive values or JSON values (if it was requested).
         * @return the protocol field value, empty when absent
         */
        public Optional<Object> value() {
            return raw("value");
        }
        /**
         * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.UnserializableValue> unserializableValue() {
            return raw("unserializableValue").map(value -> new Runtime.UnserializableValue((String) value));
        }
        /**
         * String representation of the object.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> description() {
            return raw("description").map(value -> (String) value);
        }
        /**
         * Deep serialized value.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.DeepSerializedValue> deepSerializedValue() {
            return raw("deepSerializedValue").flatMap(CdpObject::objectMap).map(Runtime.DeepSerializedValue::fromMap);
        }
        /**
         * Unique object identifier (for non-primitive values).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Preview containing abbreviated property values. Specified for {@code object} type values only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ObjectPreview> preview() {
            return raw("preview").flatMap(CdpObject::objectMap).map(Runtime.ObjectPreview::fromMap);
        }
        /**
         * Returns the customPreview field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.CustomPreview> customPreview() {
            return raw("customPreview").flatMap(CdpObject::objectMap).map(Runtime.CustomPreview::fromMap);
        }
        /**
         * Object type.
         * @param type field value
         * @return this model
         */
        public RemoteObject type(RemoteObject.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
         * @param subtype field value
         * @return this model
         */
        public RemoteObject subtype(RemoteObject.SubtypeValues subtype) {
            set("subtype", subtype);
            return this;
        }
        /**
         * Object class (constructor) name. Specified for {@code object} type values only.
         * @param className field value
         * @return this model
         */
        public RemoteObject className(String className) {
            set("className", className);
            return this;
        }
        /**
         * Remote object value in case of primitive values or JSON values (if it was requested).
         * @param value field value
         * @return this model
         */
        public RemoteObject value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
         * @param unserializableValue field value
         * @return this model
         */
        public RemoteObject unserializableValue(Runtime.UnserializableValue unserializableValue) {
            set("unserializableValue", unserializableValue);
            return this;
        }
        /**
         * String representation of the object.
         * @param description field value
         * @return this model
         */
        public RemoteObject description(String description) {
            set("description", description);
            return this;
        }
        /**
         * Deep serialized value.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param deepSerializedValue field value
         * @return this model
         */
        public RemoteObject deepSerializedValue(Runtime.DeepSerializedValue deepSerializedValue) {
            set("deepSerializedValue", deepSerializedValue);
            return this;
        }
        /**
         * Unique object identifier (for non-primitive values).
         * @param objectId field value
         * @return this model
         */
        public RemoteObject objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Preview containing abbreviated property values. Specified for {@code object} type values only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param preview field value
         * @return this model
         */
        public RemoteObject preview(Runtime.ObjectPreview preview) {
            set("preview", preview);
            return this;
        }
        /**
         * Sets the customPreview field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param customPreview field value
         * @return this model
         */
        public RemoteObject customPreview(Runtime.CustomPreview customPreview) {
            set("customPreview", customPreview);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CustomPreview extends CdpObject {
        public CustomPreview() {}
        private CustomPreview(Map<String, Object> values) { super(values); }
        public static CustomPreview fromMap(Map<String, Object> values) {
            return new CustomPreview(values);
        }
        /**
         * The JSON-stringified result of formatter.header(object, config) call. It contains json ML array that represents RemoteObject.
         * @return the protocol field value
         */
        public String header() {
            return (String) require("header");
        }
        /**
         * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will contain RemoteObjectId for the function that returns result of formatter.body(object, config) call. The result value is json ML array.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> bodyGetterId() {
            return raw("bodyGetterId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * The JSON-stringified result of formatter.header(object, config) call. It contains json ML array that represents RemoteObject.
         * @param header field value
         * @return this model
         */
        public CustomPreview header(String header) {
            set("header", header);
            return this;
        }
        /**
         * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will contain RemoteObjectId for the function that returns result of formatter.body(object, config) call. The result value is json ML array.
         * @param bodyGetterId field value
         * @return this model
         */
        public CustomPreview bodyGetterId(Runtime.RemoteObjectId bodyGetterId) {
            set("bodyGetterId", bodyGetterId);
            return this;
        }
    }
    /**
     * Object containing abbreviated remote object value.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ObjectPreview extends CdpObject {
        public ObjectPreview() {}
        private ObjectPreview(Map<String, Object> values) { super(values); }
        public static ObjectPreview fromMap(Map<String, Object> values) {
            return new ObjectPreview(values);
        }
        /**
         * Object type.
         */
        public enum TypeValues implements CdpValue<String> {
            OBJECT("object"),
            FUNCTION("function"),
            UNDEFINED("undefined"),
            STRING("string"),
            NUMBER("number"),
            BOOLEAN("boolean"),
            SYMBOL("symbol"),
            BIGINT("bigint");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         */
        public enum SubtypeValues implements CdpValue<String> {
            ARRAY("array"),
            NULL("null"),
            NODE("node"),
            REGEXP("regexp"),
            DATE("date"),
            MAP("map"),
            SET("set"),
            WEAKMAP("weakmap"),
            WEAKSET("weakset"),
            ITERATOR("iterator"),
            GENERATOR("generator"),
            ERROR("error"),
            PROXY("proxy"),
            PROMISE("promise"),
            TYPEDARRAY("typedarray"),
            ARRAYBUFFER("arraybuffer"),
            DATAVIEW("dataview"),
            WEBASSEMBLYMEMORY("webassemblymemory"),
            WASMVALUE("wasmvalue"),
            TRUSTEDTYPE("trustedtype");
            public final String value;
            SubtypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static SubtypeValues of(String value) {
                for (SubtypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SubtypeValues value: " + value);
            }
        }
        /**
         * Object type.
         * @return the protocol field value
         */
        public ObjectPreview.TypeValues type() {
            return ObjectPreview.TypeValues.of((String) require("type"));
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<ObjectPreview.SubtypeValues> subtype() {
            return raw("subtype").map(value -> ObjectPreview.SubtypeValues.of((String) value));
        }
        /**
         * String representation of the object.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> description() {
            return raw("description").map(value -> (String) value);
        }
        /**
         * True iff some of the properties or entries of the original object did not fit.
         * @return the protocol field value
         */
        public boolean overflow() {
            return (Boolean) require("overflow");
        }
        /**
         * List of the properties.
         * @return the protocol field value
         */
        public java.util.List<Runtime.PropertyPreview> properties() {
            return CdpObject.requireList(require("properties"), element0 -> java.util.Objects.requireNonNull(Runtime.PropertyPreview.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * List of the entries. Specified for {@code map} and {@code set} subtype values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.EntryPreview>> entries() {
            return raw("entries").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Runtime.EntryPreview.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Object type.
         * @param type field value
         * @return this model
         */
        public ObjectPreview type(ObjectPreview.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @param subtype field value
         * @return this model
         */
        public ObjectPreview subtype(ObjectPreview.SubtypeValues subtype) {
            set("subtype", subtype);
            return this;
        }
        /**
         * String representation of the object.
         * @param description field value
         * @return this model
         */
        public ObjectPreview description(String description) {
            set("description", description);
            return this;
        }
        /**
         * True iff some of the properties or entries of the original object did not fit.
         * @param overflow field value
         * @return this model
         */
        public ObjectPreview overflow(boolean overflow) {
            set("overflow", overflow);
            return this;
        }
        /**
         * List of the properties.
         * @param properties field value
         * @return this model
         */
        public ObjectPreview properties(java.util.List<Runtime.PropertyPreview> properties) {
            set("properties", properties);
            return this;
        }
        /**
         * List of the entries. Specified for {@code map} and {@code set} subtype values only.
         * @param entries field value
         * @return this model
         */
        public ObjectPreview entries(java.util.List<Runtime.EntryPreview> entries) {
            set("entries", entries);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PropertyPreview extends CdpObject {
        public PropertyPreview() {}
        private PropertyPreview(Map<String, Object> values) { super(values); }
        public static PropertyPreview fromMap(Map<String, Object> values) {
            return new PropertyPreview(values);
        }
        /**
         * Object type. Accessor means that the property itself is an accessor property.
         */
        public enum TypeValues implements CdpValue<String> {
            OBJECT("object"),
            FUNCTION("function"),
            UNDEFINED("undefined"),
            STRING("string"),
            NUMBER("number"),
            BOOLEAN("boolean"),
            SYMBOL("symbol"),
            ACCESSOR("accessor"),
            BIGINT("bigint");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         */
        public enum SubtypeValues implements CdpValue<String> {
            ARRAY("array"),
            NULL("null"),
            NODE("node"),
            REGEXP("regexp"),
            DATE("date"),
            MAP("map"),
            SET("set"),
            WEAKMAP("weakmap"),
            WEAKSET("weakset"),
            ITERATOR("iterator"),
            GENERATOR("generator"),
            ERROR("error"),
            PROXY("proxy"),
            PROMISE("promise"),
            TYPEDARRAY("typedarray"),
            ARRAYBUFFER("arraybuffer"),
            DATAVIEW("dataview"),
            WEBASSEMBLYMEMORY("webassemblymemory"),
            WASMVALUE("wasmvalue"),
            TRUSTEDTYPE("trustedtype");
            public final String value;
            SubtypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static SubtypeValues of(String value) {
                for (SubtypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SubtypeValues value: " + value);
            }
        }
        /**
         * Property name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Object type. Accessor means that the property itself is an accessor property.
         * @return the protocol field value
         */
        public PropertyPreview.TypeValues type() {
            return PropertyPreview.TypeValues.of((String) require("type"));
        }
        /**
         * User-friendly property value string.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> value() {
            return raw("value").map(value -> (String) value);
        }
        /**
         * Nested value preview.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ObjectPreview> valuePreview() {
            return raw("valuePreview").flatMap(CdpObject::objectMap).map(Runtime.ObjectPreview::fromMap);
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<PropertyPreview.SubtypeValues> subtype() {
            return raw("subtype").map(value -> PropertyPreview.SubtypeValues.of((String) value));
        }
        /**
         * Property name.
         * @param name field value
         * @return this model
         */
        public PropertyPreview name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Object type. Accessor means that the property itself is an accessor property.
         * @param type field value
         * @return this model
         */
        public PropertyPreview type(PropertyPreview.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * User-friendly property value string.
         * @param value field value
         * @return this model
         */
        public PropertyPreview value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Nested value preview.
         * @param valuePreview field value
         * @return this model
         */
        public PropertyPreview valuePreview(Runtime.ObjectPreview valuePreview) {
            set("valuePreview", valuePreview);
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @param subtype field value
         * @return this model
         */
        public PropertyPreview subtype(PropertyPreview.SubtypeValues subtype) {
            set("subtype", subtype);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EntryPreview extends CdpObject {
        public EntryPreview() {}
        private EntryPreview(Map<String, Object> values) { super(values); }
        public static EntryPreview fromMap(Map<String, Object> values) {
            return new EntryPreview(values);
        }
        /**
         * Preview of the key. Specified for map-like collection entries.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ObjectPreview> key() {
            return raw("key").flatMap(CdpObject::objectMap).map(Runtime.ObjectPreview::fromMap);
        }
        /**
         * Preview of the value.
         * @return the protocol field value
         */
        public Runtime.ObjectPreview value() {
            return java.util.Objects.requireNonNull(Runtime.ObjectPreview.fromMap(CdpObject.requireObjectMap(require("value"))));
        }
        /**
         * Preview of the key. Specified for map-like collection entries.
         * @param key field value
         * @return this model
         */
        public EntryPreview key(Runtime.ObjectPreview key) {
            set("key", key);
            return this;
        }
        /**
         * Preview of the value.
         * @param value field value
         * @return this model
         */
        public EntryPreview value(Runtime.ObjectPreview value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Object property descriptor.
     */
    public static final class PropertyDescriptor extends CdpObject {
        public PropertyDescriptor() {}
        private PropertyDescriptor(Map<String, Object> values) { super(values); }
        public static PropertyDescriptor fromMap(Map<String, Object> values) {
            return new PropertyDescriptor(values);
        }
        /**
         * Property name or symbol description.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * The value associated with the property.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> value() {
            return raw("value").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * True if the value associated with the property may be changed (data descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> writable() {
            return raw("writable").map(value -> (Boolean) value);
        }
        /**
         * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> get() {
            return raw("get").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> set() {
            return raw("set").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * True if the type of this property descriptor may be changed and if the property may be deleted from the corresponding object.
         * @return the protocol field value
         */
        public boolean configurable() {
            return (Boolean) require("configurable");
        }
        /**
         * True if this property shows up during enumeration of the properties on the corresponding object.
         * @return the protocol field value
         */
        public boolean enumerable() {
            return (Boolean) require("enumerable");
        }
        /**
         * True if the result was thrown during the evaluation.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> wasThrown() {
            return raw("wasThrown").map(value -> (Boolean) value);
        }
        /**
         * True if the property is owned for the object.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isOwn() {
            return raw("isOwn").map(value -> (Boolean) value);
        }
        /**
         * Property symbol object, if the property is of the {@code symbol} type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> symbol() {
            return raw("symbol").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Property name or symbol description.
         * @param name field value
         * @return this model
         */
        public PropertyDescriptor name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The value associated with the property.
         * @param value field value
         * @return this model
         */
        public PropertyDescriptor value(Runtime.RemoteObject value) {
            set("value", value);
            return this;
        }
        /**
         * True if the value associated with the property may be changed (data descriptors only).
         * @param writable field value
         * @return this model
         */
        public PropertyDescriptor writable(boolean writable) {
            set("writable", writable);
            return this;
        }
        /**
         * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @param get field value
         * @return this model
         */
        public PropertyDescriptor get(Runtime.RemoteObject get) {
            set("get", get);
            return this;
        }
        /**
         * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @param set field value
         * @return this model
         */
        public PropertyDescriptor set(Runtime.RemoteObject set) {
            set("set", set);
            return this;
        }
        /**
         * True if the type of this property descriptor may be changed and if the property may be deleted from the corresponding object.
         * @param configurable field value
         * @return this model
         */
        public PropertyDescriptor configurable(boolean configurable) {
            set("configurable", configurable);
            return this;
        }
        /**
         * True if this property shows up during enumeration of the properties on the corresponding object.
         * @param enumerable field value
         * @return this model
         */
        public PropertyDescriptor enumerable(boolean enumerable) {
            set("enumerable", enumerable);
            return this;
        }
        /**
         * True if the result was thrown during the evaluation.
         * @param wasThrown field value
         * @return this model
         */
        public PropertyDescriptor wasThrown(boolean wasThrown) {
            set("wasThrown", wasThrown);
            return this;
        }
        /**
         * True if the property is owned for the object.
         * @param isOwn field value
         * @return this model
         */
        public PropertyDescriptor isOwn(boolean isOwn) {
            set("isOwn", isOwn);
            return this;
        }
        /**
         * Property symbol object, if the property is of the {@code symbol} type.
         * @param symbol field value
         * @return this model
         */
        public PropertyDescriptor symbol(Runtime.RemoteObject symbol) {
            set("symbol", symbol);
            return this;
        }
    }
    /**
     * Object internal property descriptor. This property isn&#x27;t normally visible in JavaScript code.
     */
    public static final class InternalPropertyDescriptor extends CdpObject {
        public InternalPropertyDescriptor() {}
        private InternalPropertyDescriptor(Map<String, Object> values) { super(values); }
        public static InternalPropertyDescriptor fromMap(Map<String, Object> values) {
            return new InternalPropertyDescriptor(values);
        }
        /**
         * Conventional property name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * The value associated with the property.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> value() {
            return raw("value").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Conventional property name.
         * @param name field value
         * @return this model
         */
        public InternalPropertyDescriptor name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The value associated with the property.
         * @param value field value
         * @return this model
         */
        public InternalPropertyDescriptor value(Runtime.RemoteObject value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Object private field descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PrivatePropertyDescriptor extends CdpObject {
        public PrivatePropertyDescriptor() {}
        private PrivatePropertyDescriptor(Map<String, Object> values) { super(values); }
        public static PrivatePropertyDescriptor fromMap(Map<String, Object> values) {
            return new PrivatePropertyDescriptor(values);
        }
        /**
         * Private property name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * The value associated with the private property.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> value() {
            return raw("value").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> get() {
            return raw("get").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> set() {
            return raw("set").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Private property name.
         * @param name field value
         * @return this model
         */
        public PrivatePropertyDescriptor name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The value associated with the private property.
         * @param value field value
         * @return this model
         */
        public PrivatePropertyDescriptor value(Runtime.RemoteObject value) {
            set("value", value);
            return this;
        }
        /**
         * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @param get field value
         * @return this model
         */
        public PrivatePropertyDescriptor get(Runtime.RemoteObject get) {
            set("get", get);
            return this;
        }
        /**
         * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @param set field value
         * @return this model
         */
        public PrivatePropertyDescriptor set(Runtime.RemoteObject set) {
            set("set", set);
            return this;
        }
    }
    /**
     * Represents function call argument. Either remote object id {@code objectId}, primitive {@code value}, unserializable primitive value or neither of (for undefined) them should be specified.
     */
    public static final class CallArgument extends CdpObject {
        public CallArgument() {}
        private CallArgument(Map<String, Object> values) { super(values); }
        public static CallArgument fromMap(Map<String, Object> values) {
            return new CallArgument(values);
        }
        /**
         * Primitive value or serializable javascript object.
         * @return the protocol field value, empty when absent
         */
        public Optional<Object> value() {
            return raw("value");
        }
        /**
         * Primitive value which can not be JSON-stringified.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.UnserializableValue> unserializableValue() {
            return raw("unserializableValue").map(value -> new Runtime.UnserializableValue((String) value));
        }
        /**
         * Remote object handle.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Primitive value or serializable javascript object.
         * @param value field value
         * @return this model
         */
        public CallArgument value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * Primitive value which can not be JSON-stringified.
         * @param unserializableValue field value
         * @return this model
         */
        public CallArgument unserializableValue(Runtime.UnserializableValue unserializableValue) {
            set("unserializableValue", unserializableValue);
            return this;
        }
        /**
         * Remote object handle.
         * @param objectId field value
         * @return this model
         */
        public CallArgument objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Id of an execution context.
     */
    public static final class ExecutionContextId implements CdpValue<Long> {
        public final long value;
        public ExecutionContextId(long value) { this.value = value; }
        @Override public Long value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof ExecutionContextId)) return false;
            return value == ((ExecutionContextId) other).value;
        }
        @Override public int hashCode() { return Long.hashCode(value); }
        @Override public String toString() { return "ExecutionContextId(" + value + ")"; }
    }
    /**
     * Description of an isolated world.
     */
    public static final class ExecutionContextDescription extends CdpObject {
        public ExecutionContextDescription() {}
        private ExecutionContextDescription(Map<String, Object> values) { super(values); }
        public static ExecutionContextDescription fromMap(Map<String, Object> values) {
            return new ExecutionContextDescription(values);
        }
        /**
         * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
         * @return the protocol field value
         */
        public Runtime.ExecutionContextId id() {
            return new Runtime.ExecutionContextId(((Number) require("id")).longValue());
        }
        /**
         * Execution context origin.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Human readable name describing given context.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * A system-unique execution context identifier. Unlike the id, this is unique across multiple processes, so can be reliably used to identify specific context while backend performs a cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public String uniqueId() {
            return (String) require("uniqueId");
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> auxData() {
            return raw("auxData").flatMap(CdpObject::objectMap);
        }
        /**
         * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
         * @param id field value
         * @return this model
         */
        public ExecutionContextDescription id(Runtime.ExecutionContextId id) {
            set("id", id);
            return this;
        }
        /**
         * Execution context origin.
         * @param origin field value
         * @return this model
         */
        public ExecutionContextDescription origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Human readable name describing given context.
         * @param name field value
         * @return this model
         */
        public ExecutionContextDescription name(String name) {
            set("name", name);
            return this;
        }
        /**
         * A system-unique execution context identifier. Unlike the id, this is unique across multiple processes, so can be reliably used to identify specific context while backend performs a cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param uniqueId field value
         * @return this model
         */
        public ExecutionContextDescription uniqueId(String uniqueId) {
            set("uniqueId", uniqueId);
            return this;
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @param auxData field value
         * @return this model
         */
        public ExecutionContextDescription auxData(java.util.Map<String, Object> auxData) {
            set("auxData", auxData);
            return this;
        }
    }
    /**
     * Detailed information about exception (or error) that was thrown during script compilation or execution.
     */
    public static final class ExceptionDetails extends CdpObject {
        public ExceptionDetails() {}
        private ExceptionDetails(Map<String, Object> values) { super(values); }
        public static ExceptionDetails fromMap(Map<String, Object> values) {
            return new ExceptionDetails(values);
        }
        /**
         * Exception id.
         * @return the protocol field value
         */
        public long exceptionId() {
            return ((Number) require("exceptionId")).longValue();
        }
        /**
         * Exception text, which should be used together with exception object when available.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
        }
        /**
         * Line number of the exception location (0-based).
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Column number of the exception location (0-based).
         * @return the protocol field value
         */
        public long columnNumber() {
            return ((Number) require("columnNumber")).longValue();
        }
        /**
         * Script ID of the exception location.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ScriptId> scriptId() {
            return raw("scriptId").map(value -> new Runtime.ScriptId((String) value));
        }
        /**
         * URL of the exception location, to be used when the script was not reported.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * JavaScript stack trace if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return raw("stackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Exception object if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> exception() {
            return raw("exception").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Identifier of the context where exception happened.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> exceptionMetaData() {
            return raw("exceptionMetaData").flatMap(CdpObject::objectMap);
        }
        /**
         * Exception id.
         * @param exceptionId field value
         * @return this model
         */
        public ExceptionDetails exceptionId(long exceptionId) {
            set("exceptionId", exceptionId);
            return this;
        }
        /**
         * Exception text, which should be used together with exception object when available.
         * @param text field value
         * @return this model
         */
        public ExceptionDetails text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Line number of the exception location (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public ExceptionDetails lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Column number of the exception location (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public ExceptionDetails columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
        /**
         * Script ID of the exception location.
         * @param scriptId field value
         * @return this model
         */
        public ExceptionDetails scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * URL of the exception location, to be used when the script was not reported.
         * @param url field value
         * @return this model
         */
        public ExceptionDetails url(String url) {
            set("url", url);
            return this;
        }
        /**
         * JavaScript stack trace if available.
         * @param stackTrace field value
         * @return this model
         */
        public ExceptionDetails stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * Exception object if available.
         * @param exception field value
         * @return this model
         */
        public ExceptionDetails exception(Runtime.RemoteObject exception) {
            set("exception", exception);
            return this;
        }
        /**
         * Identifier of the context where exception happened.
         * @param executionContextId field value
         * @return this model
         */
        public ExceptionDetails executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param exceptionMetaData field value
         * @return this model
         */
        public ExceptionDetails exceptionMetaData(java.util.Map<String, Object> exceptionMetaData) {
            set("exceptionMetaData", exceptionMetaData);
            return this;
        }
    }
    /**
     * Number of milliseconds since epoch.
     */
    public static final class Timestamp implements CdpValue<Double> {
        public final double value;
        public Timestamp(double value) { this.value = value; }
        @Override public Double value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof Timestamp)) return false;
            return Double.compare(value, ((Timestamp) other).value) == 0;
        }
        @Override public int hashCode() { return Double.hashCode(value); }
        @Override public String toString() { return "Timestamp(" + value + ")"; }
    }
    /**
     * Number of milliseconds.
     */
    public static final class TimeDelta implements CdpValue<Double> {
        public final double value;
        public TimeDelta(double value) { this.value = value; }
        @Override public Double value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof TimeDelta)) return false;
            return Double.compare(value, ((TimeDelta) other).value) == 0;
        }
        @Override public int hashCode() { return Double.hashCode(value); }
        @Override public String toString() { return "TimeDelta(" + value + ")"; }
    }
    /**
     * Stack entry for runtime errors and assertions.
     */
    public static final class CallFrame extends CdpObject {
        public CallFrame() {}
        private CallFrame(Map<String, Object> values) { super(values); }
        public static CallFrame fromMap(Map<String, Object> values) {
            return new CallFrame(values);
        }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        public String functionName() {
            return (String) require("functionName");
        }
        /**
         * JavaScript script id.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * JavaScript script name or url.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * JavaScript script line number (0-based).
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * JavaScript script column number (0-based).
         * @return the protocol field value
         */
        public long columnNumber() {
            return ((Number) require("columnNumber")).longValue();
        }
        /**
         * JavaScript function name.
         * @param functionName field value
         * @return this model
         */
        public CallFrame functionName(String functionName) {
            set("functionName", functionName);
            return this;
        }
        /**
         * JavaScript script id.
         * @param scriptId field value
         * @return this model
         */
        public CallFrame scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * JavaScript script name or url.
         * @param url field value
         * @return this model
         */
        public CallFrame url(String url) {
            set("url", url);
            return this;
        }
        /**
         * JavaScript script line number (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public CallFrame lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * JavaScript script column number (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public CallFrame columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
    }
    /**
     * Call frames for assertions or error messages.
     */
    public static final class StackTrace extends CdpObject {
        public StackTrace() {}
        private StackTrace(Map<String, Object> values) { super(values); }
        public static StackTrace fromMap(Map<String, Object> values) {
            return new StackTrace(values);
        }
        /**
         * String label of this stack trace. For async traces this may be a name of the function that initiated the async call.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> description() {
            return raw("description").map(value -> (String) value);
        }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        public java.util.List<Runtime.CallFrame> callFrames() {
            return CdpObject.requireList(require("callFrames"), element0 -> java.util.Objects.requireNonNull(Runtime.CallFrame.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> parent() {
            return raw("parent").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTraceId> parentId() {
            return raw("parentId").flatMap(CdpObject::objectMap).map(Runtime.StackTraceId::fromMap);
        }
        /**
         * String label of this stack trace. For async traces this may be a name of the function that initiated the async call.
         * @param description field value
         * @return this model
         */
        public StackTrace description(String description) {
            set("description", description);
            return this;
        }
        /**
         * JavaScript function name.
         * @param callFrames field value
         * @return this model
         */
        public StackTrace callFrames(java.util.List<Runtime.CallFrame> callFrames) {
            set("callFrames", callFrames);
            return this;
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * @param parent field value
         * @return this model
         */
        public StackTrace parent(Runtime.StackTrace parent) {
            set("parent", parent);
            return this;
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentId field value
         * @return this model
         */
        public StackTrace parentId(Runtime.StackTraceId parentId) {
            set("parentId", parentId);
            return this;
        }
    }
    /**
     * Unique identifier of current debugger.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UniqueDebuggerId implements CdpValue<String> {
        public final String value;
        public UniqueDebuggerId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof UniqueDebuggerId)) return false;
            return value.equals(((UniqueDebuggerId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "UniqueDebuggerId(" + value + ")"; }
    }
    /**
     * If {@code debuggerId} is set stack trace comes from another debugger and can be resolved there. This allows to track cross-debugger calls. See {@code Runtime.StackTrace} and {@code Debugger.paused} for usages.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StackTraceId extends CdpObject {
        public StackTraceId() {}
        private StackTraceId(Map<String, Object> values) { super(values); }
        public static StackTraceId fromMap(Map<String, Object> values) {
            return new StackTraceId(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public String id() {
            return (String) require("id");
        }
        /**
         * Returns the debuggerId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.UniqueDebuggerId> debuggerId() {
            return raw("debuggerId").map(value -> new Runtime.UniqueDebuggerId((String) value));
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public StackTraceId id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Sets the debuggerId field.
         * @param debuggerId field value
         * @return this model
         */
        public StackTraceId debuggerId(Runtime.UniqueDebuggerId debuggerId) {
            set("debuggerId", debuggerId);
            return this;
        }
    }
    /**
     * Add handler to promise with given promise object id.
     */
    public static final class AwaitPromiseRequest extends CdpObject {
        public AwaitPromiseRequest() {}
        /**
         * Add handler to promise with given promise object id.
         * @param promiseObjectId protocol value
         */
        public AwaitPromiseRequest(Runtime.RemoteObjectId promiseObjectId) {
            promiseObjectId(promiseObjectId);
        }
        public static AwaitPromiseRequest fromMap(Map<String, Object> values) {
            final AwaitPromiseRequest instance_ = new AwaitPromiseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the promise.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId promiseObjectId() {
            return new Runtime.RemoteObjectId((String) require("promiseObjectId"));
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> returnByValue() {
            return raw("returnByValue").map(value -> (Boolean) value);
        }
        /**
         * Whether preview should be generated for the result.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generatePreview() {
            return raw("generatePreview").map(value -> (Boolean) value);
        }
        /**
         * Identifier of the promise.
         * @param promiseObjectId field value
         * @return this model
         */
        public AwaitPromiseRequest promiseObjectId(Runtime.RemoteObjectId promiseObjectId) {
            set("promiseObjectId", promiseObjectId);
            return this;
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @param returnByValue field value
         * @return this model
         */
        public AwaitPromiseRequest returnByValue(boolean returnByValue) {
            set("returnByValue", returnByValue);
            return this;
        }
        /**
         * Whether preview should be generated for the result.
         * @param generatePreview field value
         * @return this model
         */
        public AwaitPromiseRequest generatePreview(boolean generatePreview) {
            set("generatePreview", generatePreview);
            return this;
        }
    }
    /**
     * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
     */
    public static final class CallFunctionOnRequest extends CdpObject {
        public CallFunctionOnRequest() {}
        /**
         * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
         * @param functionDeclaration protocol value
         */
        public CallFunctionOnRequest(String functionDeclaration) {
            functionDeclaration(functionDeclaration);
        }
        public static CallFunctionOnRequest fromMap(Map<String, Object> values) {
            final CallFunctionOnRequest instance_ = new CallFunctionOnRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Declaration of the function to call.
         * @return the protocol field value
         */
        public String functionDeclaration() {
            return (String) require("functionDeclaration");
        }
        /**
         * Identifier of the object to call function on. Either objectId or executionContextId should be specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Call arguments. All call arguments must belong to the same JavaScript world as the target object.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.CallArgument>> arguments() {
            return raw("arguments").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Runtime.CallArgument.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> silent() {
            return raw("silent").map(value -> (Boolean) value);
        }
        /**
         * Whether the result is expected to be a JSON object which should be sent by value. Can be overriden by {@code serializationOptions}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> returnByValue() {
            return raw("returnByValue").map(value -> (Boolean) value);
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generatePreview() {
            return raw("generatePreview").map(value -> (Boolean) value);
        }
        /**
         * Whether execution should be treated as initiated by user in the UI.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> userGesture() {
            return raw("userGesture").map(value -> (Boolean) value);
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> awaitPromise() {
            return raw("awaitPromise").map(value -> (Boolean) value);
        }
        /**
         * Specifies execution context which global object will be used to call function on. Either executionContextId or objectId should be specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Symbolic group name that can be used to release multiple objects. If objectGroup is not specified and objectId is, objectGroup will be inherited from object.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return raw("objectGroup").map(value -> (String) value);
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> throwOnSideEffect() {
            return raw("throwOnSideEffect").map(value -> (Boolean) value);
        }
        /**
         * An alternative way to specify the execution context to call function on. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental function call in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code executionContextId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> uniqueContextId() {
            return raw("uniqueContextId").map(value -> (String) value);
        }
        /**
         * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.SerializationOptions> serializationOptions() {
            return raw("serializationOptions").flatMap(CdpObject::objectMap).map(Runtime.SerializationOptions::fromMap);
        }
        /**
         * Declaration of the function to call.
         * @param functionDeclaration field value
         * @return this model
         */
        public CallFunctionOnRequest functionDeclaration(String functionDeclaration) {
            set("functionDeclaration", functionDeclaration);
            return this;
        }
        /**
         * Identifier of the object to call function on. Either objectId or executionContextId should be specified.
         * @param objectId field value
         * @return this model
         */
        public CallFunctionOnRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Call arguments. All call arguments must belong to the same JavaScript world as the target object.
         * @param arguments field value
         * @return this model
         */
        public CallFunctionOnRequest arguments(java.util.List<Runtime.CallArgument> arguments) {
            set("arguments", arguments);
            return this;
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @param silent field value
         * @return this model
         */
        public CallFunctionOnRequest silent(boolean silent) {
            set("silent", silent);
            return this;
        }
        /**
         * Whether the result is expected to be a JSON object which should be sent by value. Can be overriden by {@code serializationOptions}.
         * @param returnByValue field value
         * @return this model
         */
        public CallFunctionOnRequest returnByValue(boolean returnByValue) {
            set("returnByValue", returnByValue);
            return this;
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param generatePreview field value
         * @return this model
         */
        public CallFunctionOnRequest generatePreview(boolean generatePreview) {
            set("generatePreview", generatePreview);
            return this;
        }
        /**
         * Whether execution should be treated as initiated by user in the UI.
         * @param userGesture field value
         * @return this model
         */
        public CallFunctionOnRequest userGesture(boolean userGesture) {
            set("userGesture", userGesture);
            return this;
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @param awaitPromise field value
         * @return this model
         */
        public CallFunctionOnRequest awaitPromise(boolean awaitPromise) {
            set("awaitPromise", awaitPromise);
            return this;
        }
        /**
         * Specifies execution context which global object will be used to call function on. Either executionContextId or objectId should be specified.
         * @param executionContextId field value
         * @return this model
         */
        public CallFunctionOnRequest executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects. If objectGroup is not specified and objectId is, objectGroup will be inherited from object.
         * @param objectGroup field value
         * @return this model
         */
        public CallFunctionOnRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param throwOnSideEffect field value
         * @return this model
         */
        public CallFunctionOnRequest throwOnSideEffect(boolean throwOnSideEffect) {
            set("throwOnSideEffect", throwOnSideEffect);
            return this;
        }
        /**
         * An alternative way to specify the execution context to call function on. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental function call in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code executionContextId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param uniqueContextId field value
         * @return this model
         */
        public CallFunctionOnRequest uniqueContextId(String uniqueContextId) {
            set("uniqueContextId", uniqueContextId);
            return this;
        }
        /**
         * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param serializationOptions field value
         * @return this model
         */
        public CallFunctionOnRequest serializationOptions(Runtime.SerializationOptions serializationOptions) {
            set("serializationOptions", serializationOptions);
            return this;
        }
    }
    /**
     * Compiles expression.
     */
    public static final class CompileScriptRequest extends CdpObject {
        public CompileScriptRequest() {}
        /**
         * Compiles expression.
         * @param expression protocol value
         * @param sourceURL protocol value
         * @param persistScript protocol value
         */
        public CompileScriptRequest(String expression, String sourceURL, boolean persistScript) {
            expression(expression).sourceURL(sourceURL).persistScript(persistScript);
        }
        public static CompileScriptRequest fromMap(Map<String, Object> values) {
            final CompileScriptRequest instance_ = new CompileScriptRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Expression to compile.
         * @return the protocol field value
         */
        public String expression() {
            return (String) require("expression");
        }
        /**
         * Source url to be set for the script.
         * @return the protocol field value
         */
        public String sourceURL() {
            return (String) require("sourceURL");
        }
        /**
         * Specifies whether the compiled script should be persisted.
         * @return the protocol field value
         */
        public boolean persistScript() {
            return (Boolean) require("persistScript");
        }
        /**
         * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Expression to compile.
         * @param expression field value
         * @return this model
         */
        public CompileScriptRequest expression(String expression) {
            set("expression", expression);
            return this;
        }
        /**
         * Source url to be set for the script.
         * @param sourceURL field value
         * @return this model
         */
        public CompileScriptRequest sourceURL(String sourceURL) {
            set("sourceURL", sourceURL);
            return this;
        }
        /**
         * Specifies whether the compiled script should be persisted.
         * @param persistScript field value
         * @return this model
         */
        public CompileScriptRequest persistScript(boolean persistScript) {
            set("persistScript", persistScript);
            return this;
        }
        /**
         * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
         * @param executionContextId field value
         * @return this model
         */
        public CompileScriptRequest executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
    }
    /**
     * Evaluates expression on global object.
     */
    public static final class EvaluateRequest extends CdpObject {
        public EvaluateRequest() {}
        /**
         * Evaluates expression on global object.
         * @param expression protocol value
         */
        public EvaluateRequest(String expression) {
            expression(expression);
        }
        public static EvaluateRequest fromMap(Map<String, Object> values) {
            final EvaluateRequest instance_ = new EvaluateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Expression to evaluate.
         * @return the protocol field value
         */
        public String expression() {
            return (String) require("expression");
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return raw("objectGroup").map(value -> (String) value);
        }
        /**
         * Determines whether Command Line API should be available during the evaluation.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeCommandLineAPI() {
            return raw("includeCommandLineAPI").map(value -> (Boolean) value);
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> silent() {
            return raw("silent").map(value -> (Boolean) value);
        }
        /**
         * Specifies in which execution context to perform evaluation. If the parameter is omitted the evaluation will be performed in the context of the inspected page. This is mutually exclusive with {@code uniqueContextId}, which offers an alternative way to identify the execution context that is more reliable in a multi-process environment.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> contextId() {
            return raw("contextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> returnByValue() {
            return raw("returnByValue").map(value -> (Boolean) value);
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generatePreview() {
            return raw("generatePreview").map(value -> (Boolean) value);
        }
        /**
         * Whether execution should be treated as initiated by user in the UI.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> userGesture() {
            return raw("userGesture").map(value -> (Boolean) value);
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> awaitPromise() {
            return raw("awaitPromise").map(value -> (Boolean) value);
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation. This implies {@code disableBreaks} below.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> throwOnSideEffect() {
            return raw("throwOnSideEffect").map(value -> (Boolean) value);
        }
        /**
         * Terminate execution after timing out (number of milliseconds).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.TimeDelta> timeout() {
            return raw("timeout").map(value -> new Runtime.TimeDelta(((Number) value).doubleValue()));
        }
        /**
         * Disable breakpoints during execution.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> disableBreaks() {
            return raw("disableBreaks").map(value -> (Boolean) value);
        }
        /**
         * Setting this flag to true enables {@code let} re-declaration and top-level {@code await}. Note that {@code let} variables can only be re-declared if they originate from {@code replMode} themselves.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> replMode() {
            return raw("replMode").map(value -> (Boolean) value);
        }
        /**
         * The Content Security Policy (CSP) for the target might block &#x27;unsafe-eval&#x27; which includes eval(), Function(), setTimeout() and setInterval() when called with non-callable arguments. This flag bypasses CSP for this evaluation and allows unsafe-eval. Defaults to true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowUnsafeEvalBlockedByCSP() {
            return raw("allowUnsafeEvalBlockedByCSP").map(value -> (Boolean) value);
        }
        /**
         * An alternative way to specify the execution context to evaluate in. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental evaluation of the expression in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code contextId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> uniqueContextId() {
            return raw("uniqueContextId").map(value -> (String) value);
        }
        /**
         * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.SerializationOptions> serializationOptions() {
            return raw("serializationOptions").flatMap(CdpObject::objectMap).map(Runtime.SerializationOptions::fromMap);
        }
        /**
         * Expression to evaluate.
         * @param expression field value
         * @return this model
         */
        public EvaluateRequest expression(String expression) {
            set("expression", expression);
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @param objectGroup field value
         * @return this model
         */
        public EvaluateRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
        /**
         * Determines whether Command Line API should be available during the evaluation.
         * @param includeCommandLineAPI field value
         * @return this model
         */
        public EvaluateRequest includeCommandLineAPI(boolean includeCommandLineAPI) {
            set("includeCommandLineAPI", includeCommandLineAPI);
            return this;
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @param silent field value
         * @return this model
         */
        public EvaluateRequest silent(boolean silent) {
            set("silent", silent);
            return this;
        }
        /**
         * Specifies in which execution context to perform evaluation. If the parameter is omitted the evaluation will be performed in the context of the inspected page. This is mutually exclusive with {@code uniqueContextId}, which offers an alternative way to identify the execution context that is more reliable in a multi-process environment.
         * @param contextId field value
         * @return this model
         */
        public EvaluateRequest contextId(Runtime.ExecutionContextId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @param returnByValue field value
         * @return this model
         */
        public EvaluateRequest returnByValue(boolean returnByValue) {
            set("returnByValue", returnByValue);
            return this;
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param generatePreview field value
         * @return this model
         */
        public EvaluateRequest generatePreview(boolean generatePreview) {
            set("generatePreview", generatePreview);
            return this;
        }
        /**
         * Whether execution should be treated as initiated by user in the UI.
         * @param userGesture field value
         * @return this model
         */
        public EvaluateRequest userGesture(boolean userGesture) {
            set("userGesture", userGesture);
            return this;
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @param awaitPromise field value
         * @return this model
         */
        public EvaluateRequest awaitPromise(boolean awaitPromise) {
            set("awaitPromise", awaitPromise);
            return this;
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation. This implies {@code disableBreaks} below.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param throwOnSideEffect field value
         * @return this model
         */
        public EvaluateRequest throwOnSideEffect(boolean throwOnSideEffect) {
            set("throwOnSideEffect", throwOnSideEffect);
            return this;
        }
        /**
         * Terminate execution after timing out (number of milliseconds).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param timeout field value
         * @return this model
         */
        public EvaluateRequest timeout(Runtime.TimeDelta timeout) {
            set("timeout", timeout);
            return this;
        }
        /**
         * Disable breakpoints during execution.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param disableBreaks field value
         * @return this model
         */
        public EvaluateRequest disableBreaks(boolean disableBreaks) {
            set("disableBreaks", disableBreaks);
            return this;
        }
        /**
         * Setting this flag to true enables {@code let} re-declaration and top-level {@code await}. Note that {@code let} variables can only be re-declared if they originate from {@code replMode} themselves.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param replMode field value
         * @return this model
         */
        public EvaluateRequest replMode(boolean replMode) {
            set("replMode", replMode);
            return this;
        }
        /**
         * The Content Security Policy (CSP) for the target might block &#x27;unsafe-eval&#x27; which includes eval(), Function(), setTimeout() and setInterval() when called with non-callable arguments. This flag bypasses CSP for this evaluation and allows unsafe-eval. Defaults to true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param allowUnsafeEvalBlockedByCSP field value
         * @return this model
         */
        public EvaluateRequest allowUnsafeEvalBlockedByCSP(boolean allowUnsafeEvalBlockedByCSP) {
            set("allowUnsafeEvalBlockedByCSP", allowUnsafeEvalBlockedByCSP);
            return this;
        }
        /**
         * An alternative way to specify the execution context to evaluate in. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental evaluation of the expression in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code contextId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param uniqueContextId field value
         * @return this model
         */
        public EvaluateRequest uniqueContextId(String uniqueContextId) {
            set("uniqueContextId", uniqueContextId);
            return this;
        }
        /**
         * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param serializationOptions field value
         * @return this model
         */
        public EvaluateRequest serializationOptions(Runtime.SerializationOptions serializationOptions) {
            set("serializationOptions", serializationOptions);
            return this;
        }
    }
    /**
     * Returns properties of a given object. Object group of the result is inherited from the target object.
     */
    public static final class GetPropertiesRequest extends CdpObject {
        public GetPropertiesRequest() {}
        /**
         * Returns properties of a given object. Object group of the result is inherited from the target object.
         * @param objectId protocol value
         */
        public GetPropertiesRequest(Runtime.RemoteObjectId objectId) {
            objectId(objectId);
        }
        public static GetPropertiesRequest fromMap(Map<String, Object> values) {
            final GetPropertiesRequest instance_ = new GetPropertiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the object to return properties for.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * If true, returns properties belonging only to the element itself, not to its prototype chain.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ownProperties() {
            return raw("ownProperties").map(value -> (Boolean) value);
        }
        /**
         * If true, returns accessor properties (with getter/setter) only; internal properties are not returned either.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> accessorPropertiesOnly() {
            return raw("accessorPropertiesOnly").map(value -> (Boolean) value);
        }
        /**
         * Whether preview should be generated for the results.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generatePreview() {
            return raw("generatePreview").map(value -> (Boolean) value);
        }
        /**
         * If true, returns non-indexed properties only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> nonIndexedPropertiesOnly() {
            return raw("nonIndexedPropertiesOnly").map(value -> (Boolean) value);
        }
        /**
         * Identifier of the object to return properties for.
         * @param objectId field value
         * @return this model
         */
        public GetPropertiesRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * If true, returns properties belonging only to the element itself, not to its prototype chain.
         * @param ownProperties field value
         * @return this model
         */
        public GetPropertiesRequest ownProperties(boolean ownProperties) {
            set("ownProperties", ownProperties);
            return this;
        }
        /**
         * If true, returns accessor properties (with getter/setter) only; internal properties are not returned either.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param accessorPropertiesOnly field value
         * @return this model
         */
        public GetPropertiesRequest accessorPropertiesOnly(boolean accessorPropertiesOnly) {
            set("accessorPropertiesOnly", accessorPropertiesOnly);
            return this;
        }
        /**
         * Whether preview should be generated for the results.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param generatePreview field value
         * @return this model
         */
        public GetPropertiesRequest generatePreview(boolean generatePreview) {
            set("generatePreview", generatePreview);
            return this;
        }
        /**
         * If true, returns non-indexed properties only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nonIndexedPropertiesOnly field value
         * @return this model
         */
        public GetPropertiesRequest nonIndexedPropertiesOnly(boolean nonIndexedPropertiesOnly) {
            set("nonIndexedPropertiesOnly", nonIndexedPropertiesOnly);
            return this;
        }
    }
    /**
     * Returns all let, const and class variables from global scope.
     */
    public static final class GlobalLexicalScopeNamesRequest extends CdpObject {
        public GlobalLexicalScopeNamesRequest() {}
        public static GlobalLexicalScopeNamesRequest fromMap(Map<String, Object> values) {
            final GlobalLexicalScopeNamesRequest instance_ = new GlobalLexicalScopeNamesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Specifies in which execution context to lookup global scope variables.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Specifies in which execution context to lookup global scope variables.
         * @param executionContextId field value
         * @return this model
         */
        public GlobalLexicalScopeNamesRequest executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
    }
    /**
     * Request parameters for Runtime.queryObjects.
     */
    public static final class QueryObjectsRequest extends CdpObject {
        public QueryObjectsRequest() {}
        /**
         * Creates a new QueryObjectsRequest with all required parameters.
         * @param prototypeObjectId protocol value
         */
        public QueryObjectsRequest(Runtime.RemoteObjectId prototypeObjectId) {
            prototypeObjectId(prototypeObjectId);
        }
        public static QueryObjectsRequest fromMap(Map<String, Object> values) {
            final QueryObjectsRequest instance_ = new QueryObjectsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the prototype to return objects for.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId prototypeObjectId() {
            return new Runtime.RemoteObjectId((String) require("prototypeObjectId"));
        }
        /**
         * Symbolic group name that can be used to release the results.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return raw("objectGroup").map(value -> (String) value);
        }
        /**
         * Identifier of the prototype to return objects for.
         * @param prototypeObjectId field value
         * @return this model
         */
        public QueryObjectsRequest prototypeObjectId(Runtime.RemoteObjectId prototypeObjectId) {
            set("prototypeObjectId", prototypeObjectId);
            return this;
        }
        /**
         * Symbolic group name that can be used to release the results.
         * @param objectGroup field value
         * @return this model
         */
        public QueryObjectsRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
    }
    /**
     * Releases remote object with given id.
     */
    public static final class ReleaseObjectRequest extends CdpObject {
        public ReleaseObjectRequest() {}
        /**
         * Releases remote object with given id.
         * @param objectId protocol value
         */
        public ReleaseObjectRequest(Runtime.RemoteObjectId objectId) {
            objectId(objectId);
        }
        public static ReleaseObjectRequest fromMap(Map<String, Object> values) {
            final ReleaseObjectRequest instance_ = new ReleaseObjectRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the object to release.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * Identifier of the object to release.
         * @param objectId field value
         * @return this model
         */
        public ReleaseObjectRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Releases all remote objects that belong to a given group.
     */
    public static final class ReleaseObjectGroupRequest extends CdpObject {
        public ReleaseObjectGroupRequest() {}
        /**
         * Releases all remote objects that belong to a given group.
         * @param objectGroup protocol value
         */
        public ReleaseObjectGroupRequest(String objectGroup) {
            objectGroup(objectGroup);
        }
        public static ReleaseObjectGroupRequest fromMap(Map<String, Object> values) {
            final ReleaseObjectGroupRequest instance_ = new ReleaseObjectGroupRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Symbolic object group name.
         * @return the protocol field value
         */
        public String objectGroup() {
            return (String) require("objectGroup");
        }
        /**
         * Symbolic object group name.
         * @param objectGroup field value
         * @return this model
         */
        public ReleaseObjectGroupRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
    }
    /**
     * Runs script with given id in a given context.
     */
    public static final class RunScriptRequest extends CdpObject {
        public RunScriptRequest() {}
        /**
         * Runs script with given id in a given context.
         * @param scriptId protocol value
         */
        public RunScriptRequest(Runtime.ScriptId scriptId) {
            scriptId(scriptId);
        }
        public static RunScriptRequest fromMap(Map<String, Object> values) {
            final RunScriptRequest instance_ = new RunScriptRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the script to run.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return raw("objectGroup").map(value -> (String) value);
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> silent() {
            return raw("silent").map(value -> (Boolean) value);
        }
        /**
         * Determines whether Command Line API should be available during the evaluation.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeCommandLineAPI() {
            return raw("includeCommandLineAPI").map(value -> (Boolean) value);
        }
        /**
         * Whether the result is expected to be a JSON object which should be sent by value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> returnByValue() {
            return raw("returnByValue").map(value -> (Boolean) value);
        }
        /**
         * Whether preview should be generated for the result.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generatePreview() {
            return raw("generatePreview").map(value -> (Boolean) value);
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> awaitPromise() {
            return raw("awaitPromise").map(value -> (Boolean) value);
        }
        /**
         * Id of the script to run.
         * @param scriptId field value
         * @return this model
         */
        public RunScriptRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
         * @param executionContextId field value
         * @return this model
         */
        public RunScriptRequest executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @param objectGroup field value
         * @return this model
         */
        public RunScriptRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @param silent field value
         * @return this model
         */
        public RunScriptRequest silent(boolean silent) {
            set("silent", silent);
            return this;
        }
        /**
         * Determines whether Command Line API should be available during the evaluation.
         * @param includeCommandLineAPI field value
         * @return this model
         */
        public RunScriptRequest includeCommandLineAPI(boolean includeCommandLineAPI) {
            set("includeCommandLineAPI", includeCommandLineAPI);
            return this;
        }
        /**
         * Whether the result is expected to be a JSON object which should be sent by value.
         * @param returnByValue field value
         * @return this model
         */
        public RunScriptRequest returnByValue(boolean returnByValue) {
            set("returnByValue", returnByValue);
            return this;
        }
        /**
         * Whether preview should be generated for the result.
         * @param generatePreview field value
         * @return this model
         */
        public RunScriptRequest generatePreview(boolean generatePreview) {
            set("generatePreview", generatePreview);
            return this;
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @param awaitPromise field value
         * @return this model
         */
        public RunScriptRequest awaitPromise(boolean awaitPromise) {
            set("awaitPromise", awaitPromise);
            return this;
        }
    }
    /**
     * Enables or disables async call stacks tracking.
     */
    public static final class SetAsyncCallStackDepthRequest extends CdpObject {
        public SetAsyncCallStackDepthRequest() {}
        /**
         * Enables or disables async call stacks tracking.
         * @param maxDepth protocol value
         */
        public SetAsyncCallStackDepthRequest(long maxDepth) {
            maxDepth(maxDepth);
        }
        public static SetAsyncCallStackDepthRequest fromMap(Map<String, Object> values) {
            final SetAsyncCallStackDepthRequest instance_ = new SetAsyncCallStackDepthRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
         * @return the protocol field value
         */
        public long maxDepth() {
            return ((Number) require("maxDepth")).longValue();
        }
        /**
         * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
         * @param maxDepth field value
         * @return this model
         */
        public SetAsyncCallStackDepthRequest maxDepth(long maxDepth) {
            set("maxDepth", maxDepth);
            return this;
        }
    }
    /**
     * Request parameters for Runtime.setCustomObjectFormatterEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCustomObjectFormatterEnabledRequest extends CdpObject {
        public SetCustomObjectFormatterEnabledRequest() {}
        /**
         * Creates a new SetCustomObjectFormatterEnabledRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetCustomObjectFormatterEnabledRequest(boolean enabled) {
            enabled(enabled);
        }
        public static SetCustomObjectFormatterEnabledRequest fromMap(Map<String, Object> values) {
            final SetCustomObjectFormatterEnabledRequest instance_ = new SetCustomObjectFormatterEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
        }
        /**
         * Sets the enabled field.
         * @param enabled field value
         * @return this model
         */
        public SetCustomObjectFormatterEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Request parameters for Runtime.setMaxCallStackSizeToCapture.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetMaxCallStackSizeToCaptureRequest extends CdpObject {
        public SetMaxCallStackSizeToCaptureRequest() {}
        /**
         * Creates a new SetMaxCallStackSizeToCaptureRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param size protocol value
         */
        public SetMaxCallStackSizeToCaptureRequest(long size) {
            size(size);
        }
        public static SetMaxCallStackSizeToCaptureRequest fromMap(Map<String, Object> values) {
            final SetMaxCallStackSizeToCaptureRequest instance_ = new SetMaxCallStackSizeToCaptureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the size field.
         * @return the protocol field value
         */
        public long size() {
            return ((Number) require("size")).longValue();
        }
        /**
         * Sets the size field.
         * @param size field value
         * @return this model
         */
        public SetMaxCallStackSizeToCaptureRequest size(long size) {
            set("size", size);
            return this;
        }
    }
    /**
     * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
     */
    public static final class AddBindingRequest extends CdpObject {
        public AddBindingRequest() {}
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param name protocol value
         */
        public AddBindingRequest(String name) {
            name(name);
        }
        public static AddBindingRequest fromMap(Map<String, Object> values) {
            final AddBindingRequest instance_ = new AddBindingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * If specified, the binding would only be exposed to the specified execution context. If omitted and {@code executionContextName} is not set, the binding is exposed to all execution contexts of the target. This parameter is mutually exclusive with {@code executionContextName}. Deprecated in favor of {@code executionContextName} due to an unclear use case and bugs in implementation (crbug.com/1169639). {@code executionContextId} will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * If specified, the binding is exposed to the executionContext with matching name, even for contexts created after the binding is added. See also {@code ExecutionContext.name} and {@code worldName} parameter to {@code Page.addScriptToEvaluateOnNewDocument}. This parameter is mutually exclusive with {@code executionContextId}.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> executionContextName() {
            return raw("executionContextName").map(value -> (String) value);
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public AddBindingRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * If specified, the binding would only be exposed to the specified execution context. If omitted and {@code executionContextName} is not set, the binding is exposed to all execution contexts of the target. This parameter is mutually exclusive with {@code executionContextName}. Deprecated in favor of {@code executionContextName} due to an unclear use case and bugs in implementation (crbug.com/1169639). {@code executionContextId} will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param executionContextId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public AddBindingRequest executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * If specified, the binding is exposed to the executionContext with matching name, even for contexts created after the binding is added. See also {@code ExecutionContext.name} and {@code worldName} parameter to {@code Page.addScriptToEvaluateOnNewDocument}. This parameter is mutually exclusive with {@code executionContextId}.
         * @param executionContextName field value
         * @return this model
         */
        public AddBindingRequest executionContextName(String executionContextName) {
            set("executionContextName", executionContextName);
            return this;
        }
    }
    /**
     * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
     */
    public static final class RemoveBindingRequest extends CdpObject {
        public RemoveBindingRequest() {}
        /**
         * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
         * @param name protocol value
         */
        public RemoveBindingRequest(String name) {
            name(name);
        }
        public static RemoveBindingRequest fromMap(Map<String, Object> values) {
            final RemoveBindingRequest instance_ = new RemoveBindingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public RemoveBindingRequest name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetExceptionDetailsRequest extends CdpObject {
        public GetExceptionDetailsRequest() {}
        /**
         * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param errorObjectId protocol value
         */
        public GetExceptionDetailsRequest(Runtime.RemoteObjectId errorObjectId) {
            errorObjectId(errorObjectId);
        }
        public static GetExceptionDetailsRequest fromMap(Map<String, Object> values) {
            final GetExceptionDetailsRequest instance_ = new GetExceptionDetailsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The error object for which to resolve the exception details.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId errorObjectId() {
            return new Runtime.RemoteObjectId((String) require("errorObjectId"));
        }
        /**
         * The error object for which to resolve the exception details.
         * @param errorObjectId field value
         * @return this model
         */
        public GetExceptionDetailsRequest errorObjectId(Runtime.RemoteObjectId errorObjectId) {
            set("errorObjectId", errorObjectId);
            return this;
        }
    }
    /**
     * Add handler to promise with given promise object id.
     */
    public static final class AwaitPromiseResult extends CdpObject {
        public AwaitPromiseResult() {}
        private AwaitPromiseResult(Map<String, Object> values) { super(values); }
        public static AwaitPromiseResult fromMap(Map<String, Object> values) {
            return new AwaitPromiseResult(values);
        }
        /**
         * Promise result. Will contain rejected value if promise was rejected.
         * @return the protocol field value
         */
        public Runtime.RemoteObject result() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("result"))));
        }
        /**
         * Exception details if stack strace is available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Promise result. Will contain rejected value if promise was rejected.
         * @param result field value
         * @return this model
         */
        public AwaitPromiseResult result(Runtime.RemoteObject result) {
            set("result", result);
            return this;
        }
        /**
         * Exception details if stack strace is available.
         * @param exceptionDetails field value
         * @return this model
         */
        public AwaitPromiseResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
     */
    public static final class CallFunctionOnResult extends CdpObject {
        public CallFunctionOnResult() {}
        private CallFunctionOnResult(Map<String, Object> values) { super(values); }
        public static CallFunctionOnResult fromMap(Map<String, Object> values) {
            return new CallFunctionOnResult(values);
        }
        /**
         * Call result.
         * @return the protocol field value
         */
        public Runtime.RemoteObject result() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("result"))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Call result.
         * @param result field value
         * @return this model
         */
        public CallFunctionOnResult result(Runtime.RemoteObject result) {
            set("result", result);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value
         * @return this model
         */
        public CallFunctionOnResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Compiles expression.
     */
    public static final class CompileScriptResult extends CdpObject {
        public CompileScriptResult() {}
        private CompileScriptResult(Map<String, Object> values) { super(values); }
        public static CompileScriptResult fromMap(Map<String, Object> values) {
            return new CompileScriptResult(values);
        }
        /**
         * Id of the script.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ScriptId> scriptId() {
            return raw("scriptId").map(value -> new Runtime.ScriptId((String) value));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Id of the script.
         * @param scriptId field value
         * @return this model
         */
        public CompileScriptResult scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value
         * @return this model
         */
        public CompileScriptResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Evaluates expression on global object.
     */
    public static final class EvaluateResult extends CdpObject {
        public EvaluateResult() {}
        private EvaluateResult(Map<String, Object> values) { super(values); }
        public static EvaluateResult fromMap(Map<String, Object> values) {
            return new EvaluateResult(values);
        }
        /**
         * Evaluation result.
         * @return the protocol field value
         */
        public Runtime.RemoteObject result() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("result"))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Evaluation result.
         * @param result field value
         * @return this model
         */
        public EvaluateResult result(Runtime.RemoteObject result) {
            set("result", result);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value
         * @return this model
         */
        public EvaluateResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Returns the JavaScript heap usage. It is the total usage of the corresponding isolate not scoped to a particular Runtime.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHeapUsageResult extends CdpObject {
        public GetHeapUsageResult() {}
        private GetHeapUsageResult(Map<String, Object> values) { super(values); }
        public static GetHeapUsageResult fromMap(Map<String, Object> values) {
            return new GetHeapUsageResult(values);
        }
        /**
         * Used JavaScript heap size in bytes.
         * @return the protocol field value
         */
        public double usedSize() {
            return ((Number) require("usedSize")).doubleValue();
        }
        /**
         * Allocated JavaScript heap size in bytes.
         * @return the protocol field value
         */
        public double totalSize() {
            return ((Number) require("totalSize")).doubleValue();
        }
        /**
         * Used size in bytes in the embedder&#x27;s garbage-collected heap.
         * @return the protocol field value
         */
        public double embedderHeapUsedSize() {
            return ((Number) require("embedderHeapUsedSize")).doubleValue();
        }
        /**
         * Size in bytes of backing storage for array buffers and external strings.
         * @return the protocol field value
         */
        public double backingStorageSize() {
            return ((Number) require("backingStorageSize")).doubleValue();
        }
        /**
         * Used JavaScript heap size in bytes.
         * @param usedSize field value
         * @return this model
         */
        public GetHeapUsageResult usedSize(double usedSize) {
            set("usedSize", usedSize);
            return this;
        }
        /**
         * Allocated JavaScript heap size in bytes.
         * @param totalSize field value
         * @return this model
         */
        public GetHeapUsageResult totalSize(double totalSize) {
            set("totalSize", totalSize);
            return this;
        }
        /**
         * Used size in bytes in the embedder&#x27;s garbage-collected heap.
         * @param embedderHeapUsedSize field value
         * @return this model
         */
        public GetHeapUsageResult embedderHeapUsedSize(double embedderHeapUsedSize) {
            set("embedderHeapUsedSize", embedderHeapUsedSize);
            return this;
        }
        /**
         * Size in bytes of backing storage for array buffers and external strings.
         * @param backingStorageSize field value
         * @return this model
         */
        public GetHeapUsageResult backingStorageSize(double backingStorageSize) {
            set("backingStorageSize", backingStorageSize);
            return this;
        }
    }
    /**
     * Returns properties of a given object. Object group of the result is inherited from the target object.
     */
    public static final class GetPropertiesResult extends CdpObject {
        public GetPropertiesResult() {}
        private GetPropertiesResult(Map<String, Object> values) { super(values); }
        public static GetPropertiesResult fromMap(Map<String, Object> values) {
            return new GetPropertiesResult(values);
        }
        /**
         * Object properties.
         * @return the protocol field value
         */
        public java.util.List<Runtime.PropertyDescriptor> result() {
            return CdpObject.requireList(require("result"), element0 -> java.util.Objects.requireNonNull(Runtime.PropertyDescriptor.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Internal object properties (only of the element itself).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.InternalPropertyDescriptor>> internalProperties() {
            return raw("internalProperties").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Runtime.InternalPropertyDescriptor.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Object private properties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.PrivatePropertyDescriptor>> privateProperties() {
            return raw("privateProperties").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Runtime.PrivatePropertyDescriptor.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Object properties.
         * @param result field value
         * @return this model
         */
        public GetPropertiesResult result(java.util.List<Runtime.PropertyDescriptor> result) {
            set("result", result);
            return this;
        }
        /**
         * Internal object properties (only of the element itself).
         * @param internalProperties field value
         * @return this model
         */
        public GetPropertiesResult internalProperties(java.util.List<Runtime.InternalPropertyDescriptor> internalProperties) {
            set("internalProperties", internalProperties);
            return this;
        }
        /**
         * Object private properties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param privateProperties field value
         * @return this model
         */
        public GetPropertiesResult privateProperties(java.util.List<Runtime.PrivatePropertyDescriptor> privateProperties) {
            set("privateProperties", privateProperties);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value
         * @return this model
         */
        public GetPropertiesResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Runs script with given id in a given context.
     */
    public static final class RunScriptResult extends CdpObject {
        public RunScriptResult() {}
        private RunScriptResult(Map<String, Object> values) { super(values); }
        public static RunScriptResult fromMap(Map<String, Object> values) {
            return new RunScriptResult(values);
        }
        /**
         * Run result.
         * @return the protocol field value
         */
        public Runtime.RemoteObject result() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("result"))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Run result.
         * @param result field value
         * @return this model
         */
        public RunScriptResult result(Runtime.RemoteObject result) {
            set("result", result);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value
         * @return this model
         */
        public RunScriptResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Notification is issued every time when binding is called.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BindingCalledEvent extends CdpObject {
        public BindingCalledEvent() {}
        private BindingCalledEvent(Map<String, Object> values) { super(values); }
        public static BindingCalledEvent fromMap(Map<String, Object> values) {
            return new BindingCalledEvent(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the payload field.
         * @return the protocol field value
         */
        public String payload() {
            return (String) require("payload");
        }
        /**
         * Identifier of the context where the call was made.
         * @return the protocol field value
         */
        public Runtime.ExecutionContextId executionContextId() {
            return new Runtime.ExecutionContextId(((Number) require("executionContextId")).longValue());
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public BindingCalledEvent name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the payload field.
         * @param payload field value
         * @return this model
         */
        public BindingCalledEvent payload(String payload) {
            set("payload", payload);
            return this;
        }
        /**
         * Identifier of the context where the call was made.
         * @param executionContextId field value
         * @return this model
         */
        public BindingCalledEvent executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
    }
    /**
     * Issued when console API was called.
     */
    public static final class ConsoleAPICalledEvent extends CdpObject {
        public ConsoleAPICalledEvent() {}
        private ConsoleAPICalledEvent(Map<String, Object> values) { super(values); }
        public static ConsoleAPICalledEvent fromMap(Map<String, Object> values) {
            return new ConsoleAPICalledEvent(values);
        }
        /**
         * Type of the call.
         */
        public enum TypeValues implements CdpValue<String> {
            LOG("log"),
            DEBUG("debug"),
            INFO("info"),
            ERROR("error"),
            WARNING("warning"),
            DIR("dir"),
            DIRXML("dirxml"),
            TABLE("table"),
            TRACE("trace"),
            CLEAR("clear"),
            STARTGROUP("startGroup"),
            STARTGROUPCOLLAPSED("startGroupCollapsed"),
            ENDGROUP("endGroup"),
            ASSERT("assert"),
            PROFILE("profile"),
            PROFILEEND("profileEnd"),
            COUNT("count"),
            TIMEEND("timeEnd");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Type of the call.
         * @return the protocol field value
         */
        public ConsoleAPICalledEvent.TypeValues type() {
            return ConsoleAPICalledEvent.TypeValues.of((String) require("type"));
        }
        /**
         * Call arguments.
         * @return the protocol field value
         */
        public java.util.List<Runtime.RemoteObject> args() {
            return CdpObject.requireList(require("args"), element0 -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Identifier of the context where the call was made.
         * @return the protocol field value
         */
        public Runtime.ExecutionContextId executionContextId() {
            return new Runtime.ExecutionContextId(((Number) require("executionContextId")).longValue());
        }
        /**
         * Call timestamp.
         * @return the protocol field value
         */
        public Runtime.Timestamp timestamp() {
            return new Runtime.Timestamp(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Stack trace captured when the call was made. The async stack chain is automatically reported for the following call types: {@code assert}, {@code error}, {@code trace}, {@code warning}. For other types the async call chain can be retrieved using {@code Debugger.getStackTrace} and {@code stackTrace.parentId} field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return raw("stackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> context() {
            return raw("context").map(value -> (String) value);
        }
        /**
         * Type of the call.
         * @param type field value
         * @return this model
         */
        public ConsoleAPICalledEvent type(ConsoleAPICalledEvent.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Call arguments.
         * @param args field value
         * @return this model
         */
        public ConsoleAPICalledEvent args(java.util.List<Runtime.RemoteObject> args) {
            set("args", args);
            return this;
        }
        /**
         * Identifier of the context where the call was made.
         * @param executionContextId field value
         * @return this model
         */
        public ConsoleAPICalledEvent executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Call timestamp.
         * @param timestamp field value
         * @return this model
         */
        public ConsoleAPICalledEvent timestamp(Runtime.Timestamp timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Stack trace captured when the call was made. The async stack chain is automatically reported for the following call types: {@code assert}, {@code error}, {@code trace}, {@code warning}. For other types the async call chain can be retrieved using {@code Debugger.getStackTrace} and {@code stackTrace.parentId} field.
         * @param stackTrace field value
         * @return this model
         */
        public ConsoleAPICalledEvent stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param context field value
         * @return this model
         */
        public ConsoleAPICalledEvent context(String context) {
            set("context", context);
            return this;
        }
    }
    /**
     * Issued when unhandled exception was revoked.
     */
    public static final class ExceptionRevokedEvent extends CdpObject {
        public ExceptionRevokedEvent() {}
        private ExceptionRevokedEvent(Map<String, Object> values) { super(values); }
        public static ExceptionRevokedEvent fromMap(Map<String, Object> values) {
            return new ExceptionRevokedEvent(values);
        }
        /**
         * Reason describing why exception was revoked.
         * @return the protocol field value
         */
        public String reason() {
            return (String) require("reason");
        }
        /**
         * The id of revoked exception, as reported in {@code exceptionThrown}.
         * @return the protocol field value
         */
        public long exceptionId() {
            return ((Number) require("exceptionId")).longValue();
        }
        /**
         * Reason describing why exception was revoked.
         * @param reason field value
         * @return this model
         */
        public ExceptionRevokedEvent reason(String reason) {
            set("reason", reason);
            return this;
        }
        /**
         * The id of revoked exception, as reported in {@code exceptionThrown}.
         * @param exceptionId field value
         * @return this model
         */
        public ExceptionRevokedEvent exceptionId(long exceptionId) {
            set("exceptionId", exceptionId);
            return this;
        }
    }
    /**
     * Issued when exception was thrown and unhandled.
     */
    public static final class ExceptionThrownEvent extends CdpObject {
        public ExceptionThrownEvent() {}
        private ExceptionThrownEvent(Map<String, Object> values) { super(values); }
        public static ExceptionThrownEvent fromMap(Map<String, Object> values) {
            return new ExceptionThrownEvent(values);
        }
        /**
         * Timestamp of the exception.
         * @return the protocol field value
         */
        public Runtime.Timestamp timestamp() {
            return new Runtime.Timestamp(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Returns the exceptionDetails field.
         * @return the protocol field value
         */
        public Runtime.ExceptionDetails exceptionDetails() {
            return java.util.Objects.requireNonNull(Runtime.ExceptionDetails.fromMap(CdpObject.requireObjectMap(require("exceptionDetails"))));
        }
        /**
         * Timestamp of the exception.
         * @param timestamp field value
         * @return this model
         */
        public ExceptionThrownEvent timestamp(Runtime.Timestamp timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Sets the exceptionDetails field.
         * @param exceptionDetails field value
         * @return this model
         */
        public ExceptionThrownEvent exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Issued when new execution context is created.
     */
    public static final class ExecutionContextCreatedEvent extends CdpObject {
        public ExecutionContextCreatedEvent() {}
        private ExecutionContextCreatedEvent(Map<String, Object> values) { super(values); }
        public static ExecutionContextCreatedEvent fromMap(Map<String, Object> values) {
            return new ExecutionContextCreatedEvent(values);
        }
        /**
         * A newly created execution context.
         * @return the protocol field value
         */
        public Runtime.ExecutionContextDescription context() {
            return java.util.Objects.requireNonNull(Runtime.ExecutionContextDescription.fromMap(CdpObject.requireObjectMap(require("context"))));
        }
        /**
         * A newly created execution context.
         * @param context field value
         * @return this model
         */
        public ExecutionContextCreatedEvent context(Runtime.ExecutionContextDescription context) {
            set("context", context);
            return this;
        }
    }
    /**
     * Issued when execution context is destroyed.
     */
    public static final class ExecutionContextDestroyedEvent extends CdpObject {
        public ExecutionContextDestroyedEvent() {}
        private ExecutionContextDestroyedEvent(Map<String, Object> values) { super(values); }
        public static ExecutionContextDestroyedEvent fromMap(Map<String, Object> values) {
            return new ExecutionContextDestroyedEvent(values);
        }
        /**
         * Id of the destroyed context
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Runtime.ExecutionContextId executionContextId() {
            return new Runtime.ExecutionContextId(((Number) require("executionContextId")).longValue());
        }
        /**
         * Unique Id of the destroyed context
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public String executionContextUniqueId() {
            return (String) require("executionContextUniqueId");
        }
        /**
         * Id of the destroyed context
         * @param executionContextId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public ExecutionContextDestroyedEvent executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Unique Id of the destroyed context
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param executionContextUniqueId field value
         * @return this model
         */
        public ExecutionContextDestroyedEvent executionContextUniqueId(String executionContextUniqueId) {
            set("executionContextUniqueId", executionContextUniqueId);
            return this;
        }
    }
    /**
     * Issued when all executionContexts were cleared in browser
     */
    public static final class ExecutionContextsClearedEvent extends CdpObject {
        public ExecutionContextsClearedEvent() {}
        private ExecutionContextsClearedEvent(Map<String, Object> values) { super(values); }
        public static ExecutionContextsClearedEvent fromMap(Map<String, Object> values) {
            return new ExecutionContextsClearedEvent(values);
        }
    }
    /**
     * Issued when object should be inspected (for example, as a result of inspect() command line API call).
     */
    public static final class InspectRequestedEvent extends CdpObject {
        public InspectRequestedEvent() {}
        private InspectRequestedEvent(Map<String, Object> values) { super(values); }
        public static InspectRequestedEvent fromMap(Map<String, Object> values) {
            return new InspectRequestedEvent(values);
        }
        /**
         * Returns the object field.
         * @return the protocol field value
         */
        public Runtime.RemoteObject object() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("object"))));
        }
        /**
         * Returns the hints field.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> hints() {
            return CdpObject.requireObjectMap(require("hints"));
        }
        /**
         * Identifier of the context where the call was made.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return raw("executionContextId").map(value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
        }
        /**
         * Sets the object field.
         * @param object field value
         * @return this model
         */
        public InspectRequestedEvent object(Runtime.RemoteObject object) {
            set("object", object);
            return this;
        }
        /**
         * Sets the hints field.
         * @param hints field value
         * @return this model
         */
        public InspectRequestedEvent hints(java.util.Map<String, Object> hints) {
            set("hints", hints);
            return this;
        }
        /**
         * Identifier of the context where the call was made.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param executionContextId field value
         * @return this model
         */
        public InspectRequestedEvent executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Add handler to promise with given promise object id.
         * @param promiseObjectId protocol value
         * @param returnByValue protocol value
         * @param generatePreview protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<AwaitPromiseResult> awaitPromise(Runtime.RemoteObjectId promiseObjectId, @Nullable Boolean returnByValue, @Nullable Boolean generatePreview) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("promiseObjectId", CdpObject.json(promiseObjectId));
            if (returnByValue != null) params.put("returnByValue", CdpObject.json(returnByValue));
            if (generatePreview != null) params.put("generatePreview", CdpObject.json(generatePreview));
            return client.call("Runtime.awaitPromise", params, result_ -> new AwaitPromiseResult(result_));
        }
        /**
         * Add handler to promise with given promise object id.
         * @param promiseObjectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<AwaitPromiseResult> awaitPromise(Runtime.RemoteObjectId promiseObjectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("promiseObjectId", CdpObject.json(promiseObjectId));
            return client.call("Runtime.awaitPromise", params, result_ -> new AwaitPromiseResult(result_));
        }
        /**
         * Add handler to promise with given promise object id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AwaitPromiseResult> awaitPromise(AwaitPromiseRequest request) {
            return client.call("Runtime.awaitPromise", request == null ? null : request.toMap(), result_ -> new AwaitPromiseResult(result_));
        }
        /**
         * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
         * @param functionDeclaration protocol value
         * @param objectId protocol value
         * @param arguments protocol value
         * @param silent protocol value
         * @param returnByValue protocol value
         * @param generatePreview protocol value
         * @param userGesture protocol value
         * @param awaitPromise protocol value
         * @param executionContextId protocol value
         * @param objectGroup protocol value
         * @param throwOnSideEffect protocol value
         * @param uniqueContextId protocol value
         * @param serializationOptions protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CallFunctionOnResult> callFunctionOn(String functionDeclaration, Runtime.@Nullable RemoteObjectId objectId, java.util.@Nullable List<Runtime.CallArgument> arguments, @Nullable Boolean silent, @Nullable Boolean returnByValue, @Nullable Boolean generatePreview, @Nullable Boolean userGesture, @Nullable Boolean awaitPromise, Runtime.@Nullable ExecutionContextId executionContextId, @Nullable String objectGroup, @Nullable Boolean throwOnSideEffect, @Nullable String uniqueContextId, Runtime.@Nullable SerializationOptions serializationOptions) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("functionDeclaration", CdpObject.json(functionDeclaration));
            if (objectId != null) params.put("objectId", CdpObject.json(objectId));
            if (arguments != null) params.put("arguments", CdpObject.json(arguments));
            if (silent != null) params.put("silent", CdpObject.json(silent));
            if (returnByValue != null) params.put("returnByValue", CdpObject.json(returnByValue));
            if (generatePreview != null) params.put("generatePreview", CdpObject.json(generatePreview));
            if (userGesture != null) params.put("userGesture", CdpObject.json(userGesture));
            if (awaitPromise != null) params.put("awaitPromise", CdpObject.json(awaitPromise));
            if (executionContextId != null) params.put("executionContextId", CdpObject.json(executionContextId));
            if (objectGroup != null) params.put("objectGroup", CdpObject.json(objectGroup));
            if (throwOnSideEffect != null) params.put("throwOnSideEffect", CdpObject.json(throwOnSideEffect));
            if (uniqueContextId != null) params.put("uniqueContextId", CdpObject.json(uniqueContextId));
            if (serializationOptions != null) params.put("serializationOptions", CdpObject.json(serializationOptions));
            return client.call("Runtime.callFunctionOn", params, result_ -> new CallFunctionOnResult(result_));
        }
        /**
         * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
         * @param functionDeclaration protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CallFunctionOnResult> callFunctionOn(String functionDeclaration) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("functionDeclaration", CdpObject.json(functionDeclaration));
            return client.call("Runtime.callFunctionOn", params, result_ -> new CallFunctionOnResult(result_));
        }
        /**
         * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CallFunctionOnResult> callFunctionOn(CallFunctionOnRequest request) {
            return client.call("Runtime.callFunctionOn", request == null ? null : request.toMap(), result_ -> new CallFunctionOnResult(result_));
        }
        /**
         * Compiles expression.
         * @param expression protocol value
         * @param sourceURL protocol value
         * @param persistScript protocol value
         * @param executionContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CompileScriptResult> compileScript(String expression, String sourceURL, boolean persistScript, Runtime.ExecutionContextId executionContextId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("expression", CdpObject.json(expression));
            params.put("sourceURL", CdpObject.json(sourceURL));
            params.put("persistScript", CdpObject.json(persistScript));
            params.put("executionContextId", CdpObject.json(executionContextId));
            return client.call("Runtime.compileScript", params, result_ -> new CompileScriptResult(result_));
        }
        /**
         * Compiles expression.
         * @param expression protocol value
         * @param sourceURL protocol value
         * @param persistScript protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CompileScriptResult> compileScript(String expression, String sourceURL, boolean persistScript) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("expression", CdpObject.json(expression));
            params.put("sourceURL", CdpObject.json(sourceURL));
            params.put("persistScript", CdpObject.json(persistScript));
            return client.call("Runtime.compileScript", params, result_ -> new CompileScriptResult(result_));
        }
        /**
         * Compiles expression.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CompileScriptResult> compileScript(CompileScriptRequest request) {
            return client.call("Runtime.compileScript", request == null ? null : request.toMap(), result_ -> new CompileScriptResult(result_));
        }
        /**
         * Disables reporting of execution contexts creation.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Runtime.disable", null);
        }
        /**
         * Discards collected exceptions and console API calls.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> discardConsoleEntries() {
            return client.call("Runtime.discardConsoleEntries", null);
        }
        /**
         * Enables reporting of execution contexts creation by means of {@code executionContextCreated} event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Runtime.enable", null);
        }
        /**
         * Evaluates expression on global object.
         * @param expression protocol value
         * @param objectGroup protocol value
         * @param includeCommandLineAPI protocol value
         * @param silent protocol value
         * @param contextId protocol value
         * @param returnByValue protocol value
         * @param generatePreview protocol value
         * @param userGesture protocol value
         * @param awaitPromise protocol value
         * @param throwOnSideEffect protocol value
         * @param timeout protocol value
         * @param disableBreaks protocol value
         * @param replMode protocol value
         * @param allowUnsafeEvalBlockedByCSP protocol value
         * @param uniqueContextId protocol value
         * @param serializationOptions protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateResult> evaluate(String expression, @Nullable String objectGroup, @Nullable Boolean includeCommandLineAPI, @Nullable Boolean silent, Runtime.@Nullable ExecutionContextId contextId, @Nullable Boolean returnByValue, @Nullable Boolean generatePreview, @Nullable Boolean userGesture, @Nullable Boolean awaitPromise, @Nullable Boolean throwOnSideEffect, Runtime.@Nullable TimeDelta timeout, @Nullable Boolean disableBreaks, @Nullable Boolean replMode, @Nullable Boolean allowUnsafeEvalBlockedByCSP, @Nullable String uniqueContextId, Runtime.@Nullable SerializationOptions serializationOptions) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("expression", CdpObject.json(expression));
            if (objectGroup != null) params.put("objectGroup", CdpObject.json(objectGroup));
            if (includeCommandLineAPI != null) params.put("includeCommandLineAPI", CdpObject.json(includeCommandLineAPI));
            if (silent != null) params.put("silent", CdpObject.json(silent));
            if (contextId != null) params.put("contextId", CdpObject.json(contextId));
            if (returnByValue != null) params.put("returnByValue", CdpObject.json(returnByValue));
            if (generatePreview != null) params.put("generatePreview", CdpObject.json(generatePreview));
            if (userGesture != null) params.put("userGesture", CdpObject.json(userGesture));
            if (awaitPromise != null) params.put("awaitPromise", CdpObject.json(awaitPromise));
            if (throwOnSideEffect != null) params.put("throwOnSideEffect", CdpObject.json(throwOnSideEffect));
            if (timeout != null) params.put("timeout", CdpObject.json(timeout));
            if (disableBreaks != null) params.put("disableBreaks", CdpObject.json(disableBreaks));
            if (replMode != null) params.put("replMode", CdpObject.json(replMode));
            if (allowUnsafeEvalBlockedByCSP != null) params.put("allowUnsafeEvalBlockedByCSP", CdpObject.json(allowUnsafeEvalBlockedByCSP));
            if (uniqueContextId != null) params.put("uniqueContextId", CdpObject.json(uniqueContextId));
            if (serializationOptions != null) params.put("serializationOptions", CdpObject.json(serializationOptions));
            return client.call("Runtime.evaluate", params, result_ -> new EvaluateResult(result_));
        }
        /**
         * Evaluates expression on global object.
         * @param expression protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateResult> evaluate(String expression) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("expression", CdpObject.json(expression));
            return client.call("Runtime.evaluate", params, result_ -> new EvaluateResult(result_));
        }
        /**
         * Evaluates expression on global object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateResult> evaluate(EvaluateRequest request) {
            return client.call("Runtime.evaluate", request == null ? null : request.toMap(), result_ -> new EvaluateResult(result_));
        }
        /**
         * Returns the isolate id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getIsolateId() {
            return client.call("Runtime.getIsolateId", null, result_ -> (String) java.util.Objects.requireNonNull(result_.get("id")));
        }
        /**
         * Returns the JavaScript heap usage. It is the total usage of the corresponding isolate not scoped to a particular Runtime.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetHeapUsageResult> getHeapUsage() {
            return client.call("Runtime.getHeapUsage", null, result_ -> new GetHeapUsageResult(result_));
        }
        /**
         * Returns properties of a given object. Object group of the result is inherited from the target object.
         * @param objectId protocol value
         * @param ownProperties protocol value
         * @param accessorPropertiesOnly protocol value
         * @param generatePreview protocol value
         * @param nonIndexedPropertiesOnly protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPropertiesResult> getProperties(Runtime.RemoteObjectId objectId, @Nullable Boolean ownProperties, @Nullable Boolean accessorPropertiesOnly, @Nullable Boolean generatePreview, @Nullable Boolean nonIndexedPropertiesOnly) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            if (ownProperties != null) params.put("ownProperties", CdpObject.json(ownProperties));
            if (accessorPropertiesOnly != null) params.put("accessorPropertiesOnly", CdpObject.json(accessorPropertiesOnly));
            if (generatePreview != null) params.put("generatePreview", CdpObject.json(generatePreview));
            if (nonIndexedPropertiesOnly != null) params.put("nonIndexedPropertiesOnly", CdpObject.json(nonIndexedPropertiesOnly));
            return client.call("Runtime.getProperties", params, result_ -> new GetPropertiesResult(result_));
        }
        /**
         * Returns properties of a given object. Object group of the result is inherited from the target object.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPropertiesResult> getProperties(Runtime.RemoteObjectId objectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("Runtime.getProperties", params, result_ -> new GetPropertiesResult(result_));
        }
        /**
         * Returns properties of a given object. Object group of the result is inherited from the target object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPropertiesResult> getProperties(GetPropertiesRequest request) {
            return client.call("Runtime.getProperties", request == null ? null : request.toMap(), result_ -> new GetPropertiesResult(result_));
        }
        /**
         * Returns all let, const and class variables from global scope.
         * @param executionContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> globalLexicalScopeNames(Runtime.ExecutionContextId executionContextId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("executionContextId", CdpObject.json(executionContextId));
            return client.call("Runtime.globalLexicalScopeNames", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("names")), element0 -> (String) element0));
        }
        /**
         * Returns all let, const and class variables from global scope.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> globalLexicalScopeNames() {
            return client.call("Runtime.globalLexicalScopeNames", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("names")), element0 -> (String) element0));
        }
        /**
         * Returns all let, const and class variables from global scope.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> globalLexicalScopeNames(GlobalLexicalScopeNamesRequest request) {
            return client.call("Runtime.globalLexicalScopeNames", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("names")), element0 -> (String) element0));
        }
        /**
         * Invokes Runtime.queryObjects.
         * @param prototypeObjectId protocol value
         * @param objectGroup protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> queryObjects(Runtime.RemoteObjectId prototypeObjectId, String objectGroup) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("prototypeObjectId", CdpObject.json(prototypeObjectId));
            params.put("objectGroup", CdpObject.json(objectGroup));
            return client.call("Runtime.queryObjects", params, result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("objects"))))));
        }
        /**
         * Invokes Runtime.queryObjects with the required parameters.
         * @param prototypeObjectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> queryObjects(Runtime.RemoteObjectId prototypeObjectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("prototypeObjectId", CdpObject.json(prototypeObjectId));
            return client.call("Runtime.queryObjects", params, result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("objects"))))));
        }
        /**
         * Invokes Runtime.queryObjects with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> queryObjects(QueryObjectsRequest request) {
            return client.call("Runtime.queryObjects", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("objects"))))));
        }
        /**
         * Releases remote object with given id.
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseObject(Runtime.RemoteObjectId objectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("Runtime.releaseObject", params);
        }
        /**
         * Releases remote object with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseObject(ReleaseObjectRequest request) {
            return client.call("Runtime.releaseObject", request == null ? null : request.toMap());
        }
        /**
         * Releases all remote objects that belong to a given group.
         * @param objectGroup protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseObjectGroup(String objectGroup) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectGroup", CdpObject.json(objectGroup));
            return client.call("Runtime.releaseObjectGroup", params);
        }
        /**
         * Releases all remote objects that belong to a given group.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseObjectGroup(ReleaseObjectGroupRequest request) {
            return client.call("Runtime.releaseObjectGroup", request == null ? null : request.toMap());
        }
        /**
         * Tells inspected instance to run if it was waiting for debugger to attach.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> runIfWaitingForDebugger() {
            return client.call("Runtime.runIfWaitingForDebugger", null);
        }
        /**
         * Runs script with given id in a given context.
         * @param scriptId protocol value
         * @param executionContextId protocol value
         * @param objectGroup protocol value
         * @param silent protocol value
         * @param includeCommandLineAPI protocol value
         * @param returnByValue protocol value
         * @param generatePreview protocol value
         * @param awaitPromise protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RunScriptResult> runScript(Runtime.ScriptId scriptId, Runtime.@Nullable ExecutionContextId executionContextId, @Nullable String objectGroup, @Nullable Boolean silent, @Nullable Boolean includeCommandLineAPI, @Nullable Boolean returnByValue, @Nullable Boolean generatePreview, @Nullable Boolean awaitPromise) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            if (executionContextId != null) params.put("executionContextId", CdpObject.json(executionContextId));
            if (objectGroup != null) params.put("objectGroup", CdpObject.json(objectGroup));
            if (silent != null) params.put("silent", CdpObject.json(silent));
            if (includeCommandLineAPI != null) params.put("includeCommandLineAPI", CdpObject.json(includeCommandLineAPI));
            if (returnByValue != null) params.put("returnByValue", CdpObject.json(returnByValue));
            if (generatePreview != null) params.put("generatePreview", CdpObject.json(generatePreview));
            if (awaitPromise != null) params.put("awaitPromise", CdpObject.json(awaitPromise));
            return client.call("Runtime.runScript", params, result_ -> new RunScriptResult(result_));
        }
        /**
         * Runs script with given id in a given context.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RunScriptResult> runScript(Runtime.ScriptId scriptId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Runtime.runScript", params, result_ -> new RunScriptResult(result_));
        }
        /**
         * Runs script with given id in a given context.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RunScriptResult> runScript(RunScriptRequest request) {
            return client.call("Runtime.runScript", request == null ? null : request.toMap(), result_ -> new RunScriptResult(result_));
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param maxDepth protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAsyncCallStackDepth(long maxDepth) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("maxDepth", CdpObject.json(maxDepth));
            return client.call("Runtime.setAsyncCallStackDepth", params);
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAsyncCallStackDepth(SetAsyncCallStackDepthRequest request) {
            return client.call("Runtime.setAsyncCallStackDepth", request == null ? null : request.toMap());
        }
        /**
         * Invokes Runtime.setCustomObjectFormatterEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCustomObjectFormatterEnabled(boolean enabled) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Runtime.setCustomObjectFormatterEnabled", params);
        }
        /**
         * Invokes Runtime.setCustomObjectFormatterEnabled with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCustomObjectFormatterEnabled(SetCustomObjectFormatterEnabledRequest request) {
            return client.call("Runtime.setCustomObjectFormatterEnabled", request == null ? null : request.toMap());
        }
        /**
         * Invokes Runtime.setMaxCallStackSizeToCapture.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param size protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setMaxCallStackSizeToCapture(long size) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("size", CdpObject.json(size));
            return client.call("Runtime.setMaxCallStackSizeToCapture", params);
        }
        /**
         * Invokes Runtime.setMaxCallStackSizeToCapture with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setMaxCallStackSizeToCapture(SetMaxCallStackSizeToCaptureRequest request) {
            return client.call("Runtime.setMaxCallStackSizeToCapture", request == null ? null : request.toMap());
        }
        /**
         * Terminate current or next JavaScript execution. Will cancel the termination when the outer-most script execution ends.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> terminateExecution() {
            return client.call("Runtime.terminateExecution", null);
        }
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param name protocol value
         * @param executionContextId protocol value
         * @param executionContextName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addBinding(String name, Runtime.@Nullable ExecutionContextId executionContextId, @Nullable String executionContextName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            if (executionContextId != null) params.put("executionContextId", CdpObject.json(executionContextId));
            if (executionContextName != null) params.put("executionContextName", CdpObject.json(executionContextName));
            return client.call("Runtime.addBinding", params);
        }
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addBinding(String name) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            return client.call("Runtime.addBinding", params);
        }
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addBinding(AddBindingRequest request) {
            return client.call("Runtime.addBinding", request == null ? null : request.toMap());
        }
        /**
         * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeBinding(String name) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            return client.call("Runtime.removeBinding", params);
        }
        /**
         * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeBinding(RemoveBindingRequest request) {
            return client.call("Runtime.removeBinding", request == null ? null : request.toMap());
        }
        /**
         * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param errorObjectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.ExceptionDetails>> getExceptionDetails(Runtime.RemoteObjectId errorObjectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("errorObjectId", CdpObject.json(errorObjectId));
            return client.call("Runtime.getExceptionDetails", params, result_ -> Optional.ofNullable(result_.get("exceptionDetails")).flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap));
        }
        /**
         * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.ExceptionDetails>> getExceptionDetails(GetExceptionDetailsRequest request) {
            return client.call("Runtime.getExceptionDetails", request == null ? null : request.toMap(), result_ -> Optional.ofNullable(result_.get("exceptionDetails")).flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap));
        }
        /**
         * Notification is issued every time when binding is called.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBindingCalled(Consumer<BindingCalledEvent> handler) {
            return client.on("Runtime.bindingCalled", BindingCalledEvent::fromMap, handler);
        }
        /**
         * Issued when console API was called.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConsoleAPICalled(Consumer<ConsoleAPICalledEvent> handler) {
            return client.on("Runtime.consoleAPICalled", ConsoleAPICalledEvent::fromMap, handler);
        }
        /**
         * Issued when unhandled exception was revoked.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onExceptionRevoked(Consumer<ExceptionRevokedEvent> handler) {
            return client.on("Runtime.exceptionRevoked", ExceptionRevokedEvent::fromMap, handler);
        }
        /**
         * Issued when exception was thrown and unhandled.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onExceptionThrown(Consumer<ExceptionThrownEvent> handler) {
            return client.on("Runtime.exceptionThrown", ExceptionThrownEvent::fromMap, handler);
        }
        /**
         * Issued when new execution context is created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onExecutionContextCreated(Consumer<ExecutionContextCreatedEvent> handler) {
            return client.on("Runtime.executionContextCreated", ExecutionContextCreatedEvent::fromMap, handler);
        }
        /**
         * Issued when execution context is destroyed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onExecutionContextDestroyed(Consumer<ExecutionContextDestroyedEvent> handler) {
            return client.on("Runtime.executionContextDestroyed", ExecutionContextDestroyedEvent::fromMap, handler);
        }
        /**
         * Issued when all executionContexts were cleared in browser
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onExecutionContextsCleared(Consumer<ExecutionContextsClearedEvent> handler) {
            return client.on("Runtime.executionContextsCleared", ExecutionContextsClearedEvent::fromMap, handler);
        }
        /**
         * Issued when object should be inspected (for example, as a result of inspect() command line API call).
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInspectRequested(Consumer<InspectRequestedEvent> handler) {
            return client.on("Runtime.inspectRequested", InspectRequestedEvent::fromMap, handler);
        }
    }
}
