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
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects. Evaluation results are returned as mirror object that expose object type, string representation and unique identifier that can be used for further object reference. Original objects are maintained in memory unless they are either explicitly released or are released along with the other objects in their object group.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Runtime {
    private Runtime() {}
    /**
     * Unique script identifier.
     */
    public static final class ScriptId implements CdpValue<String> {
        public final String value;
        public ScriptId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            @Nonnull public String value() { return value; }
            public static SerializationValues of(@Nonnull String value) {
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
            Long value = CdpObject.numberAsLong(raw("maxDepth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> additionalParameters() {
            return Optional.ofNullable(objectMap(raw("additionalParameters")));
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
         * @param maxDepth field value; empty omits the value
         * @return this model
         */
        public SerializationOptions maxDepth(OptionalLong maxDepth) {
            set("maxDepth", maxDepth.isPresent() ? maxDepth.getAsLong() : null);
            return this;
        }
        /**
         * Deep serialization depth. Default is full depth. Respected only in {@code deep} serialization mode.
         * @param maxDepth field value; null removes the value
         * @return this model
         */
        public SerializationOptions maxDepth(Long maxDepth) {
            set("maxDepth", maxDepth);
            return this;
        }
        /**
         * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
         * @param additionalParameters field value; empty omits the value
         * @return this model
         */
        public SerializationOptions additionalParameters(Optional<java.util.Map<String, Object>> additionalParameters) {
            set("additionalParameters", additionalParameters.orElse(null));
            return this;
        }
        /**
         * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
         * @param additionalParameters field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("value"));
        }
        /**
         * Returns the objectId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectId() {
            return Optional.ofNullable((String) raw("objectId"));
        }
        /**
         * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong weakLocalObjectReference() {
            Long value = CdpObject.numberAsLong(raw("weakLocalObjectReference"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public DeepSerializedValue value(Optional<Object> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value; null removes the value
         * @return this model
         */
        public DeepSerializedValue value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * Sets the objectId field.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public DeepSerializedValue objectId(Optional<String> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * Sets the objectId field.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public DeepSerializedValue objectId(String objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
         * @param weakLocalObjectReference field value; empty omits the value
         * @return this model
         */
        public DeepSerializedValue weakLocalObjectReference(OptionalLong weakLocalObjectReference) {
            set("weakLocalObjectReference", weakLocalObjectReference.isPresent() ? weakLocalObjectReference.getAsLong() : null);
            return this;
        }
        /**
         * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
         * @param weakLocalObjectReference field value; null removes the value
         * @return this model
         */
        public DeepSerializedValue weakLocalObjectReference(Long weakLocalObjectReference) {
            set("weakLocalObjectReference", weakLocalObjectReference);
            return this;
        }
    }
    /**
     * Unique object identifier.
     */
    public static final class RemoteObjectId implements CdpValue<String> {
        public final String value;
        public RemoteObjectId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        public UnserializableValue(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static SubtypeValues of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("subtype") == null ? null : RemoteObject.SubtypeValues.of((String) raw("subtype")));
        }
        /**
         * Object class (constructor) name. Specified for {@code object} type values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> className() {
            return Optional.ofNullable((String) raw("className"));
        }
        /**
         * Remote object value in case of primitive values or JSON values (if it was requested).
         * @return the protocol field value, empty when absent
         */
        public Optional<Object> value() {
            return Optional.ofNullable(raw("value"));
        }
        /**
         * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.UnserializableValue> unserializableValue() {
            return Optional.ofNullable(raw("unserializableValue") == null ? null : new Runtime.UnserializableValue((String) raw("unserializableValue")));
        }
        /**
         * String representation of the object.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> description() {
            return Optional.ofNullable((String) raw("description"));
        }
        /**
         * Deep serialized value.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.DeepSerializedValue> deepSerializedValue() {
            return Optional.ofNullable(raw("deepSerializedValue") == null ? null : Runtime.DeepSerializedValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("deepSerializedValue")))));
        }
        /**
         * Unique object identifier (for non-primitive values).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Preview containing abbreviated property values. Specified for {@code object} type values only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ObjectPreview> preview() {
            return Optional.ofNullable(raw("preview") == null ? null : Runtime.ObjectPreview.fromMap(java.util.Objects.requireNonNull(objectMap(raw("preview")))));
        }
        /**
         * Returns the customPreview field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.CustomPreview> customPreview() {
            return Optional.ofNullable(raw("customPreview") == null ? null : Runtime.CustomPreview.fromMap(java.util.Objects.requireNonNull(objectMap(raw("customPreview")))));
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
         * @param subtype field value; empty omits the value
         * @return this model
         */
        public RemoteObject subtype(Optional<RemoteObject.SubtypeValues> subtype) {
            set("subtype", subtype.orElse(null));
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
         * @param subtype field value; null removes the value
         * @return this model
         */
        public RemoteObject subtype(RemoteObject.SubtypeValues subtype) {
            set("subtype", subtype);
            return this;
        }
        /**
         * Object class (constructor) name. Specified for {@code object} type values only.
         * @param className field value; empty omits the value
         * @return this model
         */
        public RemoteObject className(Optional<String> className) {
            set("className", className.orElse(null));
            return this;
        }
        /**
         * Object class (constructor) name. Specified for {@code object} type values only.
         * @param className field value; null removes the value
         * @return this model
         */
        public RemoteObject className(String className) {
            set("className", className);
            return this;
        }
        /**
         * Remote object value in case of primitive values or JSON values (if it was requested).
         * @param value field value; empty omits the value
         * @return this model
         */
        public RemoteObject value(Optional<Object> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * Remote object value in case of primitive values or JSON values (if it was requested).
         * @param value field value; null removes the value
         * @return this model
         */
        public RemoteObject value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
         * @param unserializableValue field value; empty omits the value
         * @return this model
         */
        public RemoteObject unserializableValue(Optional<Runtime.UnserializableValue> unserializableValue) {
            set("unserializableValue", unserializableValue.orElse(null));
            return this;
        }
        /**
         * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
         * @param unserializableValue field value; null removes the value
         * @return this model
         */
        public RemoteObject unserializableValue(Runtime.UnserializableValue unserializableValue) {
            set("unserializableValue", unserializableValue);
            return this;
        }
        /**
         * String representation of the object.
         * @param description field value; empty omits the value
         * @return this model
         */
        public RemoteObject description(Optional<String> description) {
            set("description", description.orElse(null));
            return this;
        }
        /**
         * String representation of the object.
         * @param description field value; null removes the value
         * @return this model
         */
        public RemoteObject description(String description) {
            set("description", description);
            return this;
        }
        /**
         * Deep serialized value.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param deepSerializedValue field value; empty omits the value
         * @return this model
         */
        public RemoteObject deepSerializedValue(Optional<Runtime.DeepSerializedValue> deepSerializedValue) {
            set("deepSerializedValue", deepSerializedValue.orElse(null));
            return this;
        }
        /**
         * Deep serialized value.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param deepSerializedValue field value; null removes the value
         * @return this model
         */
        public RemoteObject deepSerializedValue(Runtime.DeepSerializedValue deepSerializedValue) {
            set("deepSerializedValue", deepSerializedValue);
            return this;
        }
        /**
         * Unique object identifier (for non-primitive values).
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public RemoteObject objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * Unique object identifier (for non-primitive values).
         * @param objectId field value; null removes the value
         * @return this model
         */
        public RemoteObject objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Preview containing abbreviated property values. Specified for {@code object} type values only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param preview field value; empty omits the value
         * @return this model
         */
        public RemoteObject preview(Optional<Runtime.ObjectPreview> preview) {
            set("preview", preview.orElse(null));
            return this;
        }
        /**
         * Preview containing abbreviated property values. Specified for {@code object} type values only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param preview field value; null removes the value
         * @return this model
         */
        public RemoteObject preview(Runtime.ObjectPreview preview) {
            set("preview", preview);
            return this;
        }
        /**
         * Sets the customPreview field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param customPreview field value; empty omits the value
         * @return this model
         */
        public RemoteObject customPreview(Optional<Runtime.CustomPreview> customPreview) {
            set("customPreview", customPreview.orElse(null));
            return this;
        }
        /**
         * Sets the customPreview field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param customPreview field value; null removes the value
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
            return Optional.ofNullable(raw("bodyGetterId") == null ? null : new Runtime.RemoteObjectId((String) raw("bodyGetterId")));
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
         * @param bodyGetterId field value; empty omits the value
         * @return this model
         */
        public CustomPreview bodyGetterId(Optional<Runtime.RemoteObjectId> bodyGetterId) {
            set("bodyGetterId", bodyGetterId.orElse(null));
            return this;
        }
        /**
         * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will contain RemoteObjectId for the function that returns result of formatter.body(object, config) call. The result value is json ML array.
         * @param bodyGetterId field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static SubtypeValues of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("subtype") == null ? null : ObjectPreview.SubtypeValues.of((String) raw("subtype")));
        }
        /**
         * String representation of the object.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> description() {
            return Optional.ofNullable((String) raw("description"));
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
            return CdpObject.requireList(require("properties"), element0 -> java.util.Objects.requireNonNull(Runtime.PropertyPreview.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * List of the entries. Specified for {@code map} and {@code set} subtype values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.EntryPreview>> entries() {
            return Optional.ofNullable(list(raw("entries"), element0 -> java.util.Objects.requireNonNull(Runtime.EntryPreview.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param subtype field value; empty omits the value
         * @return this model
         */
        public ObjectPreview subtype(Optional<ObjectPreview.SubtypeValues> subtype) {
            set("subtype", subtype.orElse(null));
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @param subtype field value; null removes the value
         * @return this model
         */
        public ObjectPreview subtype(ObjectPreview.SubtypeValues subtype) {
            set("subtype", subtype);
            return this;
        }
        /**
         * String representation of the object.
         * @param description field value; empty omits the value
         * @return this model
         */
        public ObjectPreview description(Optional<String> description) {
            set("description", description.orElse(null));
            return this;
        }
        /**
         * String representation of the object.
         * @param description field value; null removes the value
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
         * @param entries field value; empty omits the value
         * @return this model
         */
        public ObjectPreview entries(Optional<java.util.List<Runtime.EntryPreview>> entries) {
            set("entries", entries.orElse(null));
            return this;
        }
        /**
         * List of the entries. Specified for {@code map} and {@code set} subtype values only.
         * @param entries field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static SubtypeValues of(@Nonnull String value) {
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
            return Optional.ofNullable((String) raw("value"));
        }
        /**
         * Nested value preview.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ObjectPreview> valuePreview() {
            return Optional.ofNullable(raw("valuePreview") == null ? null : Runtime.ObjectPreview.fromMap(java.util.Objects.requireNonNull(objectMap(raw("valuePreview")))));
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @return the protocol field value, empty when absent
         */
        public Optional<PropertyPreview.SubtypeValues> subtype() {
            return Optional.ofNullable(raw("subtype") == null ? null : PropertyPreview.SubtypeValues.of((String) raw("subtype")));
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public PropertyPreview value(Optional<String> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * User-friendly property value string.
         * @param value field value; null removes the value
         * @return this model
         */
        public PropertyPreview value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Nested value preview.
         * @param valuePreview field value; empty omits the value
         * @return this model
         */
        public PropertyPreview valuePreview(Optional<Runtime.ObjectPreview> valuePreview) {
            set("valuePreview", valuePreview.orElse(null));
            return this;
        }
        /**
         * Nested value preview.
         * @param valuePreview field value; null removes the value
         * @return this model
         */
        public PropertyPreview valuePreview(Runtime.ObjectPreview valuePreview) {
            set("valuePreview", valuePreview);
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @param subtype field value; empty omits the value
         * @return this model
         */
        public PropertyPreview subtype(Optional<PropertyPreview.SubtypeValues> subtype) {
            set("subtype", subtype.orElse(null));
            return this;
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @param subtype field value; null removes the value
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
            return Optional.ofNullable(raw("key") == null ? null : Runtime.ObjectPreview.fromMap(java.util.Objects.requireNonNull(objectMap(raw("key")))));
        }
        /**
         * Preview of the value.
         * @return the protocol field value
         */
        public Runtime.ObjectPreview value() {
            return java.util.Objects.requireNonNull(Runtime.ObjectPreview.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("value")))));
        }
        /**
         * Preview of the key. Specified for map-like collection entries.
         * @param key field value; empty omits the value
         * @return this model
         */
        public EntryPreview key(Optional<Runtime.ObjectPreview> key) {
            set("key", key.orElse(null));
            return this;
        }
        /**
         * Preview of the key. Specified for map-like collection entries.
         * @param key field value; null removes the value
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
            return Optional.ofNullable(raw("value") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("value")))));
        }
        /**
         * True if the value associated with the property may be changed (data descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> writable() {
            return Optional.ofNullable((Boolean) raw("writable"));
        }
        /**
         * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> get() {
            return Optional.ofNullable(raw("get") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("get")))));
        }
        /**
         * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> set() {
            return Optional.ofNullable(raw("set") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("set")))));
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
            return Optional.ofNullable((Boolean) raw("wasThrown"));
        }
        /**
         * True if the property is owned for the object.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isOwn() {
            return Optional.ofNullable((Boolean) raw("isOwn"));
        }
        /**
         * Property symbol object, if the property is of the {@code symbol} type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> symbol() {
            return Optional.ofNullable(raw("symbol") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("symbol")))));
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor value(Optional<Runtime.RemoteObject> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * The value associated with the property.
         * @param value field value; null removes the value
         * @return this model
         */
        public PropertyDescriptor value(Runtime.RemoteObject value) {
            set("value", value);
            return this;
        }
        /**
         * True if the value associated with the property may be changed (data descriptors only).
         * @param writable field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor writable(Optional<Boolean> writable) {
            set("writable", writable.orElse(null));
            return this;
        }
        /**
         * True if the value associated with the property may be changed (data descriptors only).
         * @param writable field value; null removes the value
         * @return this model
         */
        public PropertyDescriptor writable(Boolean writable) {
            set("writable", writable);
            return this;
        }
        /**
         * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @param get field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor get(Optional<Runtime.RemoteObject> get) {
            set("get", get.orElse(null));
            return this;
        }
        /**
         * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @param get field value; null removes the value
         * @return this model
         */
        public PropertyDescriptor get(Runtime.RemoteObject get) {
            set("get", get);
            return this;
        }
        /**
         * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @param set field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor set(Optional<Runtime.RemoteObject> set) {
            set("set", set.orElse(null));
            return this;
        }
        /**
         * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @param set field value; null removes the value
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
         * @param wasThrown field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor wasThrown(Optional<Boolean> wasThrown) {
            set("wasThrown", wasThrown.orElse(null));
            return this;
        }
        /**
         * True if the result was thrown during the evaluation.
         * @param wasThrown field value; null removes the value
         * @return this model
         */
        public PropertyDescriptor wasThrown(Boolean wasThrown) {
            set("wasThrown", wasThrown);
            return this;
        }
        /**
         * True if the property is owned for the object.
         * @param isOwn field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor isOwn(Optional<Boolean> isOwn) {
            set("isOwn", isOwn.orElse(null));
            return this;
        }
        /**
         * True if the property is owned for the object.
         * @param isOwn field value; null removes the value
         * @return this model
         */
        public PropertyDescriptor isOwn(Boolean isOwn) {
            set("isOwn", isOwn);
            return this;
        }
        /**
         * Property symbol object, if the property is of the {@code symbol} type.
         * @param symbol field value; empty omits the value
         * @return this model
         */
        public PropertyDescriptor symbol(Optional<Runtime.RemoteObject> symbol) {
            set("symbol", symbol.orElse(null));
            return this;
        }
        /**
         * Property symbol object, if the property is of the {@code symbol} type.
         * @param symbol field value; null removes the value
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
            return Optional.ofNullable(raw("value") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("value")))));
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public InternalPropertyDescriptor value(Optional<Runtime.RemoteObject> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * The value associated with the property.
         * @param value field value; null removes the value
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
            return Optional.ofNullable(raw("value") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("value")))));
        }
        /**
         * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> get() {
            return Optional.ofNullable(raw("get") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("get")))));
        }
        /**
         * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> set() {
            return Optional.ofNullable(raw("set") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("set")))));
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public PrivatePropertyDescriptor value(Optional<Runtime.RemoteObject> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * The value associated with the private property.
         * @param value field value; null removes the value
         * @return this model
         */
        public PrivatePropertyDescriptor value(Runtime.RemoteObject value) {
            set("value", value);
            return this;
        }
        /**
         * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @param get field value; empty omits the value
         * @return this model
         */
        public PrivatePropertyDescriptor get(Optional<Runtime.RemoteObject> get) {
            set("get", get.orElse(null));
            return this;
        }
        /**
         * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @param get field value; null removes the value
         * @return this model
         */
        public PrivatePropertyDescriptor get(Runtime.RemoteObject get) {
            set("get", get);
            return this;
        }
        /**
         * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @param set field value; empty omits the value
         * @return this model
         */
        public PrivatePropertyDescriptor set(Optional<Runtime.RemoteObject> set) {
            set("set", set.orElse(null));
            return this;
        }
        /**
         * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @param set field value; null removes the value
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
            return Optional.ofNullable(raw("value"));
        }
        /**
         * Primitive value which can not be JSON-stringified.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.UnserializableValue> unserializableValue() {
            return Optional.ofNullable(raw("unserializableValue") == null ? null : new Runtime.UnserializableValue((String) raw("unserializableValue")));
        }
        /**
         * Remote object handle.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Primitive value or serializable javascript object.
         * @param value field value; empty omits the value
         * @return this model
         */
        public CallArgument value(Optional<Object> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * Primitive value or serializable javascript object.
         * @param value field value; null removes the value
         * @return this model
         */
        public CallArgument value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * Primitive value which can not be JSON-stringified.
         * @param unserializableValue field value; empty omits the value
         * @return this model
         */
        public CallArgument unserializableValue(Optional<Runtime.UnserializableValue> unserializableValue) {
            set("unserializableValue", unserializableValue.orElse(null));
            return this;
        }
        /**
         * Primitive value which can not be JSON-stringified.
         * @param unserializableValue field value; null removes the value
         * @return this model
         */
        public CallArgument unserializableValue(Runtime.UnserializableValue unserializableValue) {
            set("unserializableValue", unserializableValue);
            return this;
        }
        /**
         * Remote object handle.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public CallArgument objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * Remote object handle.
         * @param objectId field value; null removes the value
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
        @Nonnull public Long value() { return value; }
        @Override public boolean equals(Object other) {
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
            return Optional.ofNullable(objectMap(raw("auxData")));
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
         * @param auxData field value; empty omits the value
         * @return this model
         */
        public ExecutionContextDescription auxData(Optional<java.util.Map<String, Object>> auxData) {
            set("auxData", auxData.orElse(null));
            return this;
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @param auxData field value; null removes the value
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
            return Optional.ofNullable(raw("scriptId") == null ? null : new Runtime.ScriptId((String) raw("scriptId")));
        }
        /**
         * URL of the exception location, to be used when the script was not reported.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return Optional.ofNullable((String) raw("url"));
        }
        /**
         * JavaScript stack trace if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return Optional.ofNullable(raw("stackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("stackTrace")))));
        }
        /**
         * Exception object if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> exception() {
            return Optional.ofNullable(raw("exception") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exception")))));
        }
        /**
         * Identifier of the context where exception happened.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return Optional.ofNullable(raw("executionContextId") == null ? null : new Runtime.ExecutionContextId(((Number) raw("executionContextId")).longValue()));
        }
        /**
         * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> exceptionMetaData() {
            return Optional.ofNullable(objectMap(raw("exceptionMetaData")));
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
         * @param scriptId field value; empty omits the value
         * @return this model
         */
        public ExceptionDetails scriptId(Optional<Runtime.ScriptId> scriptId) {
            set("scriptId", scriptId.orElse(null));
            return this;
        }
        /**
         * Script ID of the exception location.
         * @param scriptId field value; null removes the value
         * @return this model
         */
        public ExceptionDetails scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * URL of the exception location, to be used when the script was not reported.
         * @param url field value; empty omits the value
         * @return this model
         */
        public ExceptionDetails url(Optional<String> url) {
            set("url", url.orElse(null));
            return this;
        }
        /**
         * URL of the exception location, to be used when the script was not reported.
         * @param url field value; null removes the value
         * @return this model
         */
        public ExceptionDetails url(String url) {
            set("url", url);
            return this;
        }
        /**
         * JavaScript stack trace if available.
         * @param stackTrace field value; empty omits the value
         * @return this model
         */
        public ExceptionDetails stackTrace(Optional<Runtime.StackTrace> stackTrace) {
            set("stackTrace", stackTrace.orElse(null));
            return this;
        }
        /**
         * JavaScript stack trace if available.
         * @param stackTrace field value; null removes the value
         * @return this model
         */
        public ExceptionDetails stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * Exception object if available.
         * @param exception field value; empty omits the value
         * @return this model
         */
        public ExceptionDetails exception(Optional<Runtime.RemoteObject> exception) {
            set("exception", exception.orElse(null));
            return this;
        }
        /**
         * Exception object if available.
         * @param exception field value; null removes the value
         * @return this model
         */
        public ExceptionDetails exception(Runtime.RemoteObject exception) {
            set("exception", exception);
            return this;
        }
        /**
         * Identifier of the context where exception happened.
         * @param executionContextId field value; empty omits the value
         * @return this model
         */
        public ExceptionDetails executionContextId(Optional<Runtime.ExecutionContextId> executionContextId) {
            set("executionContextId", executionContextId.orElse(null));
            return this;
        }
        /**
         * Identifier of the context where exception happened.
         * @param executionContextId field value; null removes the value
         * @return this model
         */
        public ExceptionDetails executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param exceptionMetaData field value; empty omits the value
         * @return this model
         */
        public ExceptionDetails exceptionMetaData(Optional<java.util.Map<String, Object>> exceptionMetaData) {
            set("exceptionMetaData", exceptionMetaData.orElse(null));
            return this;
        }
        /**
         * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param exceptionMetaData field value; null removes the value
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
        @Nonnull public Double value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public Double value() { return value; }
        @Override public boolean equals(Object other) {
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
            return Optional.ofNullable((String) raw("description"));
        }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        public java.util.List<Runtime.CallFrame> callFrames() {
            return CdpObject.requireList(require("callFrames"), element0 -> java.util.Objects.requireNonNull(Runtime.CallFrame.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> parent() {
            return Optional.ofNullable(raw("parent") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("parent")))));
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTraceId> parentId() {
            return Optional.ofNullable(raw("parentId") == null ? null : Runtime.StackTraceId.fromMap(java.util.Objects.requireNonNull(objectMap(raw("parentId")))));
        }
        /**
         * String label of this stack trace. For async traces this may be a name of the function that initiated the async call.
         * @param description field value; empty omits the value
         * @return this model
         */
        public StackTrace description(Optional<String> description) {
            set("description", description.orElse(null));
            return this;
        }
        /**
         * String label of this stack trace. For async traces this may be a name of the function that initiated the async call.
         * @param description field value; null removes the value
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
         * @param parent field value; empty omits the value
         * @return this model
         */
        public StackTrace parent(Optional<Runtime.StackTrace> parent) {
            set("parent", parent.orElse(null));
            return this;
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * @param parent field value; null removes the value
         * @return this model
         */
        public StackTrace parent(Runtime.StackTrace parent) {
            set("parent", parent);
            return this;
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentId field value; empty omits the value
         * @return this model
         */
        public StackTrace parentId(Optional<Runtime.StackTraceId> parentId) {
            set("parentId", parentId.orElse(null));
            return this;
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentId field value; null removes the value
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
        public UniqueDebuggerId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            return Optional.ofNullable(raw("debuggerId") == null ? null : new Runtime.UniqueDebuggerId((String) raw("debuggerId")));
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
         * @param debuggerId field value; empty omits the value
         * @return this model
         */
        public StackTraceId debuggerId(Optional<Runtime.UniqueDebuggerId> debuggerId) {
            set("debuggerId", debuggerId.orElse(null));
            return this;
        }
        /**
         * Sets the debuggerId field.
         * @param debuggerId field value; null removes the value
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("result")))));
        }
        /**
         * Exception details if stack strace is available.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
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
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public AwaitPromiseResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details if stack strace is available.
         * @param exceptionDetails field value; null removes the value
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("result")))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
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
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public CallFunctionOnResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; null removes the value
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
            return Optional.ofNullable(raw("scriptId") == null ? null : new Runtime.ScriptId((String) raw("scriptId")));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
        }
        /**
         * Id of the script.
         * @param scriptId field value; empty omits the value
         * @return this model
         */
        public CompileScriptResult scriptId(Optional<Runtime.ScriptId> scriptId) {
            set("scriptId", scriptId.orElse(null));
            return this;
        }
        /**
         * Id of the script.
         * @param scriptId field value; null removes the value
         * @return this model
         */
        public CompileScriptResult scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public CompileScriptResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; null removes the value
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("result")))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
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
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public EvaluateResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; null removes the value
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
            return CdpObject.requireList(require("result"), element0 -> java.util.Objects.requireNonNull(Runtime.PropertyDescriptor.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Internal object properties (only of the element itself).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.InternalPropertyDescriptor>> internalProperties() {
            return Optional.ofNullable(list(raw("internalProperties"), element0 -> java.util.Objects.requireNonNull(Runtime.InternalPropertyDescriptor.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Object private properties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.PrivatePropertyDescriptor>> privateProperties() {
            return Optional.ofNullable(list(raw("privateProperties"), element0 -> java.util.Objects.requireNonNull(Runtime.PrivatePropertyDescriptor.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
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
         * @param internalProperties field value; empty omits the value
         * @return this model
         */
        public GetPropertiesResult internalProperties(Optional<java.util.List<Runtime.InternalPropertyDescriptor>> internalProperties) {
            set("internalProperties", internalProperties.orElse(null));
            return this;
        }
        /**
         * Internal object properties (only of the element itself).
         * @param internalProperties field value; null removes the value
         * @return this model
         */
        public GetPropertiesResult internalProperties(java.util.List<Runtime.InternalPropertyDescriptor> internalProperties) {
            set("internalProperties", internalProperties);
            return this;
        }
        /**
         * Object private properties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param privateProperties field value; empty omits the value
         * @return this model
         */
        public GetPropertiesResult privateProperties(Optional<java.util.List<Runtime.PrivatePropertyDescriptor>> privateProperties) {
            set("privateProperties", privateProperties.orElse(null));
            return this;
        }
        /**
         * Object private properties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param privateProperties field value; null removes the value
         * @return this model
         */
        public GetPropertiesResult privateProperties(java.util.List<Runtime.PrivatePropertyDescriptor> privateProperties) {
            set("privateProperties", privateProperties);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public GetPropertiesResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; null removes the value
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("result")))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
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
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public RunScriptResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            return CdpObject.requireList(require("args"), element0 -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return Optional.ofNullable(raw("stackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("stackTrace")))));
        }
        /**
         * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> context() {
            return Optional.ofNullable((String) raw("context"));
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
         * @param stackTrace field value; empty omits the value
         * @return this model
         */
        public ConsoleAPICalledEvent stackTrace(Optional<Runtime.StackTrace> stackTrace) {
            set("stackTrace", stackTrace.orElse(null));
            return this;
        }
        /**
         * Stack trace captured when the call was made. The async stack chain is automatically reported for the following call types: {@code assert}, {@code error}, {@code trace}, {@code warning}. For other types the async call chain can be retrieved using {@code Debugger.getStackTrace} and {@code stackTrace.parentId} field.
         * @param stackTrace field value; null removes the value
         * @return this model
         */
        public ConsoleAPICalledEvent stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param context field value; empty omits the value
         * @return this model
         */
        public ConsoleAPICalledEvent context(Optional<String> context) {
            set("context", context.orElse(null));
            return this;
        }
        /**
         * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param context field value; null removes the value
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
            return java.util.Objects.requireNonNull(Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("exceptionDetails")))));
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
            return java.util.Objects.requireNonNull(Runtime.ExecutionContextDescription.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("context")))));
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("object")))));
        }
        /**
         * Returns the hints field.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> hints() {
            return java.util.Objects.requireNonNull(CdpObject.objectMap(require("hints")));
        }
        /**
         * Identifier of the context where the call was made.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return Optional.ofNullable(raw("executionContextId") == null ? null : new Runtime.ExecutionContextId(((Number) raw("executionContextId")).longValue()));
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
         * @param executionContextId field value; empty omits the value
         * @return this model
         */
        public InspectRequestedEvent executionContextId(Optional<Runtime.ExecutionContextId> executionContextId) {
            set("executionContextId", executionContextId.orElse(null));
            return this;
        }
        /**
         * Identifier of the context where the call was made.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param executionContextId field value; null removes the value
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
        public CompletionStage<AwaitPromiseResult> awaitPromise(Runtime.RemoteObjectId promiseObjectId, Optional<Boolean> returnByValue, Optional<Boolean> generatePreview) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("promiseObjectId", CdpObject.json(promiseObjectId));
            returnByValue.ifPresent(value_ -> params.put("returnByValue", value_));
            generatePreview.ifPresent(value_ -> params.put("generatePreview", value_));
            return client.call("Runtime.awaitPromise", params, result_ -> new AwaitPromiseResult(result_));
        }
        /**
         * Add handler to promise with given promise object id.
         * @param promiseObjectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<AwaitPromiseResult> awaitPromise(Runtime.RemoteObjectId promiseObjectId) {
            return awaitPromise(promiseObjectId, Optional.empty(), Optional.empty());
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
        public CompletionStage<CallFunctionOnResult> callFunctionOn(String functionDeclaration, Optional<Runtime.RemoteObjectId> objectId, Optional<java.util.List<Runtime.CallArgument>> arguments, Optional<Boolean> silent, Optional<Boolean> returnByValue, Optional<Boolean> generatePreview, Optional<Boolean> userGesture, Optional<Boolean> awaitPromise, Optional<Runtime.ExecutionContextId> executionContextId, Optional<String> objectGroup, Optional<Boolean> throwOnSideEffect, Optional<String> uniqueContextId, Optional<Runtime.SerializationOptions> serializationOptions) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("functionDeclaration", CdpObject.json(functionDeclaration));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            arguments.ifPresent(value_ -> params.put("arguments", CdpObject.json(value_)));
            silent.ifPresent(value_ -> params.put("silent", value_));
            returnByValue.ifPresent(value_ -> params.put("returnByValue", value_));
            generatePreview.ifPresent(value_ -> params.put("generatePreview", value_));
            userGesture.ifPresent(value_ -> params.put("userGesture", value_));
            awaitPromise.ifPresent(value_ -> params.put("awaitPromise", value_));
            executionContextId.ifPresent(value_ -> params.put("executionContextId", CdpObject.json(value_)));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            throwOnSideEffect.ifPresent(value_ -> params.put("throwOnSideEffect", value_));
            uniqueContextId.ifPresent(value_ -> params.put("uniqueContextId", CdpObject.json(value_)));
            serializationOptions.ifPresent(value_ -> params.put("serializationOptions", CdpObject.json(value_)));
            return client.call("Runtime.callFunctionOn", params, result_ -> new CallFunctionOnResult(result_));
        }
        /**
         * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
         * @param functionDeclaration protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CallFunctionOnResult> callFunctionOn(String functionDeclaration) {
            return callFunctionOn(functionDeclaration, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Compiles expression.
         * @param expression protocol value
         * @param sourceURL protocol value
         * @param persistScript protocol value
         * @param executionContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CompileScriptResult> compileScript(String expression, String sourceURL, boolean persistScript, Optional<Runtime.ExecutionContextId> executionContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("expression", CdpObject.json(expression));
            params.put("sourceURL", CdpObject.json(sourceURL));
            params.put("persistScript", CdpObject.json(persistScript));
            executionContextId.ifPresent(value_ -> params.put("executionContextId", CdpObject.json(value_)));
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
            return compileScript(expression, sourceURL, persistScript, Optional.empty());
        }
        /**
         * Disables reporting of execution contexts creation.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Runtime.disable", null, result_ -> null);
        }
        /**
         * Discards collected exceptions and console API calls.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> discardConsoleEntries() {
            return client.call("Runtime.discardConsoleEntries", null, result_ -> null);
        }
        /**
         * Enables reporting of execution contexts creation by means of {@code executionContextCreated} event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Runtime.enable", null, result_ -> null);
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
        public CompletionStage<EvaluateResult> evaluate(String expression, Optional<String> objectGroup, Optional<Boolean> includeCommandLineAPI, Optional<Boolean> silent, Optional<Runtime.ExecutionContextId> contextId, Optional<Boolean> returnByValue, Optional<Boolean> generatePreview, Optional<Boolean> userGesture, Optional<Boolean> awaitPromise, Optional<Boolean> throwOnSideEffect, Optional<Runtime.TimeDelta> timeout, Optional<Boolean> disableBreaks, Optional<Boolean> replMode, Optional<Boolean> allowUnsafeEvalBlockedByCSP, Optional<String> uniqueContextId, Optional<Runtime.SerializationOptions> serializationOptions) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("expression", CdpObject.json(expression));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            includeCommandLineAPI.ifPresent(value_ -> params.put("includeCommandLineAPI", value_));
            silent.ifPresent(value_ -> params.put("silent", value_));
            contextId.ifPresent(value_ -> params.put("contextId", CdpObject.json(value_)));
            returnByValue.ifPresent(value_ -> params.put("returnByValue", value_));
            generatePreview.ifPresent(value_ -> params.put("generatePreview", value_));
            userGesture.ifPresent(value_ -> params.put("userGesture", value_));
            awaitPromise.ifPresent(value_ -> params.put("awaitPromise", value_));
            throwOnSideEffect.ifPresent(value_ -> params.put("throwOnSideEffect", value_));
            timeout.ifPresent(value_ -> params.put("timeout", CdpObject.json(value_)));
            disableBreaks.ifPresent(value_ -> params.put("disableBreaks", value_));
            replMode.ifPresent(value_ -> params.put("replMode", value_));
            allowUnsafeEvalBlockedByCSP.ifPresent(value_ -> params.put("allowUnsafeEvalBlockedByCSP", value_));
            uniqueContextId.ifPresent(value_ -> params.put("uniqueContextId", CdpObject.json(value_)));
            serializationOptions.ifPresent(value_ -> params.put("serializationOptions", CdpObject.json(value_)));
            return client.call("Runtime.evaluate", params, result_ -> new EvaluateResult(result_));
        }
        /**
         * Evaluates expression on global object.
         * @param expression protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateResult> evaluate(String expression) {
            return evaluate(expression, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
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
        public CompletionStage<GetPropertiesResult> getProperties(Runtime.RemoteObjectId objectId, Optional<Boolean> ownProperties, Optional<Boolean> accessorPropertiesOnly, Optional<Boolean> generatePreview, Optional<Boolean> nonIndexedPropertiesOnly) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            ownProperties.ifPresent(value_ -> params.put("ownProperties", value_));
            accessorPropertiesOnly.ifPresent(value_ -> params.put("accessorPropertiesOnly", value_));
            generatePreview.ifPresent(value_ -> params.put("generatePreview", value_));
            nonIndexedPropertiesOnly.ifPresent(value_ -> params.put("nonIndexedPropertiesOnly", value_));
            return client.call("Runtime.getProperties", params, result_ -> new GetPropertiesResult(result_));
        }
        /**
         * Returns properties of a given object. Object group of the result is inherited from the target object.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPropertiesResult> getProperties(Runtime.RemoteObjectId objectId) {
            return getProperties(objectId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns all let, const and class variables from global scope.
         * @param executionContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> globalLexicalScopeNames(Optional<Runtime.ExecutionContextId> executionContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            executionContextId.ifPresent(value_ -> params.put("executionContextId", CdpObject.json(value_)));
            return client.call("Runtime.globalLexicalScopeNames", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("names")), element0 -> (String) element0));
        }
        /**
         * Returns all let, const and class variables from global scope.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> globalLexicalScopeNames() {
            return globalLexicalScopeNames(Optional.empty());
        }
        /**
         * Invokes Runtime.queryObjects.
         * @param prototypeObjectId protocol value
         * @param objectGroup protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> queryObjects(Runtime.RemoteObjectId prototypeObjectId, Optional<String> objectGroup) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("prototypeObjectId", CdpObject.json(prototypeObjectId));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            return client.call("Runtime.queryObjects", params, result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("objects")))))));
        }
        /**
         * Invokes Runtime.queryObjects with the required parameters.
         * @param prototypeObjectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> queryObjects(Runtime.RemoteObjectId prototypeObjectId) {
            return queryObjects(prototypeObjectId, Optional.empty());
        }
        /**
         * Releases remote object with given id.
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseObject(Runtime.RemoteObjectId objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("Runtime.releaseObject", params, result_ -> null);
        }
        /**
         * Releases all remote objects that belong to a given group.
         * @param objectGroup protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseObjectGroup(String objectGroup) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectGroup", CdpObject.json(objectGroup));
            return client.call("Runtime.releaseObjectGroup", params, result_ -> null);
        }
        /**
         * Tells inspected instance to run if it was waiting for debugger to attach.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> runIfWaitingForDebugger() {
            return client.call("Runtime.runIfWaitingForDebugger", null, result_ -> null);
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
        public CompletionStage<RunScriptResult> runScript(Runtime.ScriptId scriptId, Optional<Runtime.ExecutionContextId> executionContextId, Optional<String> objectGroup, Optional<Boolean> silent, Optional<Boolean> includeCommandLineAPI, Optional<Boolean> returnByValue, Optional<Boolean> generatePreview, Optional<Boolean> awaitPromise) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            executionContextId.ifPresent(value_ -> params.put("executionContextId", CdpObject.json(value_)));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            silent.ifPresent(value_ -> params.put("silent", value_));
            includeCommandLineAPI.ifPresent(value_ -> params.put("includeCommandLineAPI", value_));
            returnByValue.ifPresent(value_ -> params.put("returnByValue", value_));
            generatePreview.ifPresent(value_ -> params.put("generatePreview", value_));
            awaitPromise.ifPresent(value_ -> params.put("awaitPromise", value_));
            return client.call("Runtime.runScript", params, result_ -> new RunScriptResult(result_));
        }
        /**
         * Runs script with given id in a given context.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RunScriptResult> runScript(Runtime.ScriptId scriptId) {
            return runScript(scriptId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param maxDepth protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAsyncCallStackDepth(long maxDepth) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("maxDepth", CdpObject.json(maxDepth));
            return client.call("Runtime.setAsyncCallStackDepth", params, result_ -> null);
        }
        /**
         * Invokes Runtime.setCustomObjectFormatterEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCustomObjectFormatterEnabled(boolean enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Runtime.setCustomObjectFormatterEnabled", params, result_ -> null);
        }
        /**
         * Invokes Runtime.setMaxCallStackSizeToCapture.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param size protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setMaxCallStackSizeToCapture(long size) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("size", CdpObject.json(size));
            return client.call("Runtime.setMaxCallStackSizeToCapture", params, result_ -> null);
        }
        /**
         * Terminate current or next JavaScript execution. Will cancel the termination when the outer-most script execution ends.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> terminateExecution() {
            return client.call("Runtime.terminateExecution", null, result_ -> null);
        }
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param name protocol value
         * @param executionContextId protocol value
         * @param executionContextName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addBinding(String name, Optional<Runtime.ExecutionContextId> executionContextId, Optional<String> executionContextName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            executionContextId.ifPresent(value_ -> params.put("executionContextId", CdpObject.json(value_)));
            executionContextName.ifPresent(value_ -> params.put("executionContextName", CdpObject.json(value_)));
            return client.call("Runtime.addBinding", params, result_ -> null);
        }
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addBinding(String name) {
            return addBinding(name, Optional.empty(), Optional.empty());
        }
        /**
         * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeBinding(String name) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", CdpObject.json(name));
            return client.call("Runtime.removeBinding", params, result_ -> null);
        }
        /**
         * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param errorObjectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.ExceptionDetails>> getExceptionDetails(Runtime.RemoteObjectId errorObjectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("errorObjectId", CdpObject.json(errorObjectId));
            return client.call("Runtime.getExceptionDetails", params, result_ -> Optional.ofNullable(result_.get("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(result_.get("exceptionDetails"))))));
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
