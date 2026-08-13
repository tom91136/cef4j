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
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects. Evaluation results are returned as mirror object that expose object type, string representation and unique identifier that can be used for further object reference. Original objects are maintained in memory unless they are either explicitly released or are released along with the other objects in their object group.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/0e999a528db40a3ef6fa917adf96370a18b87d70/include/js_protocol.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class Runtime {
    private Runtime() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Represents options for serialization. Overrides {@code generatePreview} and {@code returnByValue}.
     */
    public static final class SerializationOptions extends CdpObject {
        private SerializationOptions(Map<String, Object> values) { super(values); }
        @Nullable public static SerializationOptions fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SerializationOptions(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the serialization field.
         * @return the protocol field value
         */
        @Nullable public String serialization() {
            return (String) value("serialization");
        }
        /**
         * Wire values for SerializationValues.
         */
        public static final class SerializationValues {
            private SerializationValues() {}
            public static final String DEEP = "deep";
            public static final String JSON = "json";
            public static final String IDONLY = "idOnly";
        }
        /**
         * Deep serialization depth. Default is full depth. Respected only in {@code deep} serialization mode.
         * @return the protocol field value
         */
        @Nullable public Long maxDepth() {
            return numberAsLong(value("maxDepth"));
        }
        /**
         * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> additionalParameters() {
            return objectMap(value("additionalParameters"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the serialization field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serialization(@Nullable String value) {
                if (value == null) values.remove("serialization");
                else values.put("serialization", jsonValue(value));
                return this;
            }
            /**
             * Deep serialization depth. Default is full depth. Respected only in {@code deep} serialization mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxDepth(@Nullable Long value) {
                if (value == null) values.remove("maxDepth");
                else values.put("maxDepth", jsonValue(value));
                return this;
            }
            /**
             * Embedder-specific parameters. For example if connected to V8 in Chrome these control DOM serialization via {@code maxNodeDepth: integer} and {@code includeShadowTree: &quot;none&quot; | &quot;open&quot; | &quot;all&quot;}. Values can be only of type string or integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder additionalParameters(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("additionalParameters");
                else values.put("additionalParameters", jsonValue(value));
                return this;
            }
            public SerializationOptions build() {
                if (!values.containsKey("serialization")) throw new IllegalStateException("Missing required CDP field: serialization");
                return new SerializationOptions(values);
            }
        }
    }
    /**
     * Represents deep serialized value.
     */
    public static final class DeepSerializedValue extends CdpObject {
        private DeepSerializedValue(Map<String, Object> values) { super(values); }
        @Nullable public static DeepSerializedValue fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeepSerializedValue(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Wire values for TypeValues.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String UNDEFINED = "undefined";
            public static final String NULL = "null";
            public static final String STRING = "string";
            public static final String NUMBER = "number";
            public static final String BOOLEAN = "boolean";
            public static final String BIGINT = "bigint";
            public static final String REGEXP = "regexp";
            public static final String DATE = "date";
            public static final String SYMBOL = "symbol";
            public static final String ARRAY = "array";
            public static final String OBJECT = "object";
            public static final String FUNCTION = "function";
            public static final String MAP = "map";
            public static final String SET = "set";
            public static final String WEAKMAP = "weakmap";
            public static final String WEAKSET = "weakset";
            public static final String ERROR = "error";
            public static final String PROXY = "proxy";
            public static final String PROMISE = "promise";
            public static final String TYPEDARRAY = "typedarray";
            public static final String ARRAYBUFFER = "arraybuffer";
            public static final String NODE = "node";
            public static final String WINDOW = "window";
            public static final String GENERATOR = "generator";
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public Object value() {
            return value("value");
        }
        /**
         * Returns the objectId field.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
         * @return the protocol field value
         */
        @Nullable public Long weakLocalObjectReference() {
            return numberAsLong(value("weakLocalObjectReference"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Object value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Sets the objectId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Set if value reference met more then once during serialization. In such case, value is provided only to one of the serialized values. Unique per value in the scope of one CDP call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder weakLocalObjectReference(@Nullable Long value) {
                if (value == null) values.remove("weakLocalObjectReference");
                else values.put("weakLocalObjectReference", jsonValue(value));
                return this;
            }
            public DeepSerializedValue build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new DeepSerializedValue(values);
            }
        }
    }
    /**
     * Mirror object referencing original JavaScript object.
     */
    public static final class RemoteObject extends CdpObject {
        private RemoteObject(Map<String, Object> values) { super(values); }
        @Nullable public static RemoteObject fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoteObject(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Object type.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String OBJECT = "object";
            public static final String FUNCTION = "function";
            public static final String UNDEFINED = "undefined";
            public static final String STRING = "string";
            public static final String NUMBER = "number";
            public static final String BOOLEAN = "boolean";
            public static final String SYMBOL = "symbol";
            public static final String BIGINT = "bigint";
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
         * @return the protocol field value
         */
        @Nullable public String subtype() {
            return (String) value("subtype");
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
         */
        public static final class SubtypeValues {
            private SubtypeValues() {}
            public static final String ARRAY = "array";
            public static final String NULL = "null";
            public static final String NODE = "node";
            public static final String REGEXP = "regexp";
            public static final String DATE = "date";
            public static final String MAP = "map";
            public static final String SET = "set";
            public static final String WEAKMAP = "weakmap";
            public static final String WEAKSET = "weakset";
            public static final String ITERATOR = "iterator";
            public static final String GENERATOR = "generator";
            public static final String ERROR = "error";
            public static final String PROXY = "proxy";
            public static final String PROMISE = "promise";
            public static final String TYPEDARRAY = "typedarray";
            public static final String ARRAYBUFFER = "arraybuffer";
            public static final String DATAVIEW = "dataview";
            public static final String WEBASSEMBLYMEMORY = "webassemblymemory";
            public static final String WASMVALUE = "wasmvalue";
            public static final String TRUSTEDTYPE = "trustedtype";
        }
        /**
         * Object class (constructor) name. Specified for {@code object} type values only.
         * @return the protocol field value
         */
        @Nullable public String className() {
            return (String) value("className");
        }
        /**
         * Remote object value in case of primitive values or JSON values (if it was requested).
         * @return the protocol field value
         */
        @Nullable public Object value() {
            return value("value");
        }
        /**
         * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
         * @return the protocol field value
         */
        @Nullable public String unserializableValue() {
            return (String) value("unserializableValue");
        }
        /**
         * String representation of the object.
         * @return the protocol field value
         */
        @Nullable public String description() {
            return (String) value("description");
        }
        /**
         * Deep serialized value.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.DeepSerializedValue deepSerializedValue() {
            return Runtime.DeepSerializedValue.fromMap(objectMap(value("deepSerializedValue")));
        }
        /**
         * Unique object identifier (for non-primitive values).
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Preview containing abbreviated property values. Specified for {@code object} type values only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.ObjectPreview preview() {
            return Runtime.ObjectPreview.fromMap(objectMap(value("preview")));
        }
        /**
         * Returns the customPreview field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.CustomPreview customPreview() {
            return Runtime.CustomPreview.fromMap(objectMap(value("customPreview")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Object subtype hint. Specified for {@code object} type values only. NOTE: If you change anything here, make sure to also update {@code subtype} in {@code ObjectPreview} and {@code PropertyPreview} below.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subtype(@Nullable String value) {
                if (value == null) values.remove("subtype");
                else values.put("subtype", jsonValue(value));
                return this;
            }
            /**
             * Object class (constructor) name. Specified for {@code object} type values only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder className(@Nullable String value) {
                if (value == null) values.remove("className");
                else values.put("className", jsonValue(value));
                return this;
            }
            /**
             * Remote object value in case of primitive values or JSON values (if it was requested).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Object value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Primitive value which can not be JSON-stringified does not have {@code value}, but gets this property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unserializableValue(@Nullable String value) {
                if (value == null) values.remove("unserializableValue");
                else values.put("unserializableValue", jsonValue(value));
                return this;
            }
            /**
             * String representation of the object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable String value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * Deep serialized value.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deepSerializedValue(@Nullable Runtime.DeepSerializedValue value) {
                if (value == null) values.remove("deepSerializedValue");
                else values.put("deepSerializedValue", jsonValue(value));
                return this;
            }
            /**
             * Unique object identifier (for non-primitive values).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Preview containing abbreviated property values. Specified for {@code object} type values only.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder preview(@Nullable Runtime.ObjectPreview value) {
                if (value == null) values.remove("preview");
                else values.put("preview", jsonValue(value));
                return this;
            }
            /**
             * Sets the customPreview field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder customPreview(@Nullable Runtime.CustomPreview value) {
                if (value == null) values.remove("customPreview");
                else values.put("customPreview", jsonValue(value));
                return this;
            }
            public RemoteObject build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new RemoteObject(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CustomPreview extends CdpObject {
        private CustomPreview(Map<String, Object> values) { super(values); }
        @Nullable public static CustomPreview fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CustomPreview(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The JSON-stringified result of formatter.header(object, config) call. It contains json ML array that represents RemoteObject.
         * @return the protocol field value
         */
        @Nullable public String header() {
            return (String) value("header");
        }
        /**
         * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will contain RemoteObjectId for the function that returns result of formatter.body(object, config) call. The result value is json ML array.
         * @return the protocol field value
         */
        @Nullable public String bodyGetterId() {
            return (String) value("bodyGetterId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The JSON-stringified result of formatter.header(object, config) call. It contains json ML array that represents RemoteObject.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder header(@Nullable String value) {
                if (value == null) values.remove("header");
                else values.put("header", jsonValue(value));
                return this;
            }
            /**
             * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will contain RemoteObjectId for the function that returns result of formatter.body(object, config) call. The result value is json ML array.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bodyGetterId(@Nullable String value) {
                if (value == null) values.remove("bodyGetterId");
                else values.put("bodyGetterId", jsonValue(value));
                return this;
            }
            public CustomPreview build() {
                if (!values.containsKey("header")) throw new IllegalStateException("Missing required CDP field: header");
                return new CustomPreview(values);
            }
        }
    }
    /**
     * Object containing abbreviated remote object value.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ObjectPreview extends CdpObject {
        private ObjectPreview(Map<String, Object> values) { super(values); }
        @Nullable public static ObjectPreview fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ObjectPreview(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Object type.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String OBJECT = "object";
            public static final String FUNCTION = "function";
            public static final String UNDEFINED = "undefined";
            public static final String STRING = "string";
            public static final String NUMBER = "number";
            public static final String BOOLEAN = "boolean";
            public static final String SYMBOL = "symbol";
            public static final String BIGINT = "bigint";
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @return the protocol field value
         */
        @Nullable public String subtype() {
            return (String) value("subtype");
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         */
        public static final class SubtypeValues {
            private SubtypeValues() {}
            public static final String ARRAY = "array";
            public static final String NULL = "null";
            public static final String NODE = "node";
            public static final String REGEXP = "regexp";
            public static final String DATE = "date";
            public static final String MAP = "map";
            public static final String SET = "set";
            public static final String WEAKMAP = "weakmap";
            public static final String WEAKSET = "weakset";
            public static final String ITERATOR = "iterator";
            public static final String GENERATOR = "generator";
            public static final String ERROR = "error";
            public static final String PROXY = "proxy";
            public static final String PROMISE = "promise";
            public static final String TYPEDARRAY = "typedarray";
            public static final String ARRAYBUFFER = "arraybuffer";
            public static final String DATAVIEW = "dataview";
            public static final String WEBASSEMBLYMEMORY = "webassemblymemory";
            public static final String WASMVALUE = "wasmvalue";
            public static final String TRUSTEDTYPE = "trustedtype";
        }
        /**
         * String representation of the object.
         * @return the protocol field value
         */
        @Nullable public String description() {
            return (String) value("description");
        }
        /**
         * True iff some of the properties or entries of the original object did not fit.
         * @return the protocol field value
         */
        @Nullable public Boolean overflow() {
            return (Boolean) value("overflow");
        }
        /**
         * List of the properties.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.PropertyPreview> properties() {
            return list(value("properties"), element0 -> Runtime.PropertyPreview.fromMap(objectMap(element0)));
        }
        /**
         * List of the entries. Specified for {@code map} and {@code set} subtype values only.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.EntryPreview> entries() {
            return list(value("entries"), element0 -> Runtime.EntryPreview.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Object subtype hint. Specified for {@code object} type values only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subtype(@Nullable String value) {
                if (value == null) values.remove("subtype");
                else values.put("subtype", jsonValue(value));
                return this;
            }
            /**
             * String representation of the object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable String value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * True iff some of the properties or entries of the original object did not fit.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder overflow(@Nullable Boolean value) {
                if (value == null) values.remove("overflow");
                else values.put("overflow", jsonValue(value));
                return this;
            }
            /**
             * List of the properties.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder properties(@Nullable java.util.List<Runtime.PropertyPreview> value) {
                if (value == null) values.remove("properties");
                else values.put("properties", jsonValue(value));
                return this;
            }
            /**
             * List of the entries. Specified for {@code map} and {@code set} subtype values only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entries(@Nullable java.util.List<Runtime.EntryPreview> value) {
                if (value == null) values.remove("entries");
                else values.put("entries", jsonValue(value));
                return this;
            }
            public ObjectPreview build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("overflow")) throw new IllegalStateException("Missing required CDP field: overflow");
                if (!values.containsKey("properties")) throw new IllegalStateException("Missing required CDP field: properties");
                return new ObjectPreview(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PropertyPreview extends CdpObject {
        private PropertyPreview(Map<String, Object> values) { super(values); }
        @Nullable public static PropertyPreview fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PropertyPreview(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Property name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Object type. Accessor means that the property itself is an accessor property.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Object type. Accessor means that the property itself is an accessor property.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String OBJECT = "object";
            public static final String FUNCTION = "function";
            public static final String UNDEFINED = "undefined";
            public static final String STRING = "string";
            public static final String NUMBER = "number";
            public static final String BOOLEAN = "boolean";
            public static final String SYMBOL = "symbol";
            public static final String ACCESSOR = "accessor";
            public static final String BIGINT = "bigint";
        }
        /**
         * User-friendly property value string.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Nested value preview.
         * @return the protocol field value
         */
        @Nullable public Runtime.ObjectPreview valuePreview() {
            return Runtime.ObjectPreview.fromMap(objectMap(value("valuePreview")));
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         * @return the protocol field value
         */
        @Nullable public String subtype() {
            return (String) value("subtype");
        }
        /**
         * Object subtype hint. Specified for {@code object} type values only.
         */
        public static final class SubtypeValues {
            private SubtypeValues() {}
            public static final String ARRAY = "array";
            public static final String NULL = "null";
            public static final String NODE = "node";
            public static final String REGEXP = "regexp";
            public static final String DATE = "date";
            public static final String MAP = "map";
            public static final String SET = "set";
            public static final String WEAKMAP = "weakmap";
            public static final String WEAKSET = "weakset";
            public static final String ITERATOR = "iterator";
            public static final String GENERATOR = "generator";
            public static final String ERROR = "error";
            public static final String PROXY = "proxy";
            public static final String PROMISE = "promise";
            public static final String TYPEDARRAY = "typedarray";
            public static final String ARRAYBUFFER = "arraybuffer";
            public static final String DATAVIEW = "dataview";
            public static final String WEBASSEMBLYMEMORY = "webassemblymemory";
            public static final String WASMVALUE = "wasmvalue";
            public static final String TRUSTEDTYPE = "trustedtype";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Object type. Accessor means that the property itself is an accessor property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * User-friendly property value string.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Nested value preview.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder valuePreview(@Nullable Runtime.ObjectPreview value) {
                if (value == null) values.remove("valuePreview");
                else values.put("valuePreview", jsonValue(value));
                return this;
            }
            /**
             * Object subtype hint. Specified for {@code object} type values only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subtype(@Nullable String value) {
                if (value == null) values.remove("subtype");
                else values.put("subtype", jsonValue(value));
                return this;
            }
            public PropertyPreview build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new PropertyPreview(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EntryPreview extends CdpObject {
        private EntryPreview(Map<String, Object> values) { super(values); }
        @Nullable public static EntryPreview fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EntryPreview(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Preview of the key. Specified for map-like collection entries.
         * @return the protocol field value
         */
        @Nullable public Runtime.ObjectPreview key() {
            return Runtime.ObjectPreview.fromMap(objectMap(value("key")));
        }
        /**
         * Preview of the value.
         * @return the protocol field value
         */
        @Nullable public Runtime.ObjectPreview value() {
            return Runtime.ObjectPreview.fromMap(objectMap(value("value")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Preview of the key. Specified for map-like collection entries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Runtime.ObjectPreview value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Preview of the value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Runtime.ObjectPreview value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public EntryPreview build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new EntryPreview(values);
            }
        }
    }
    /**
     * Object property descriptor.
     */
    public static final class PropertyDescriptor extends CdpObject {
        private PropertyDescriptor(Map<String, Object> values) { super(values); }
        @Nullable public static PropertyDescriptor fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PropertyDescriptor(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Property name or symbol description.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The value associated with the property.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject value() {
            return Runtime.RemoteObject.fromMap(objectMap(value("value")));
        }
        /**
         * True if the value associated with the property may be changed (data descriptors only).
         * @return the protocol field value
         */
        @Nullable public Boolean writable() {
            return (Boolean) value("writable");
        }
        /**
         * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject get() {
            return Runtime.RemoteObject.fromMap(objectMap(value("get")));
        }
        /**
         * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject set() {
            return Runtime.RemoteObject.fromMap(objectMap(value("set")));
        }
        /**
         * True if the type of this property descriptor may be changed and if the property may be deleted from the corresponding object.
         * @return the protocol field value
         */
        @Nullable public Boolean configurable() {
            return (Boolean) value("configurable");
        }
        /**
         * True if this property shows up during enumeration of the properties on the corresponding object.
         * @return the protocol field value
         */
        @Nullable public Boolean enumerable() {
            return (Boolean) value("enumerable");
        }
        /**
         * True if the result was thrown during the evaluation.
         * @return the protocol field value
         */
        @Nullable public Boolean wasThrown() {
            return (Boolean) value("wasThrown");
        }
        /**
         * True if the property is owned for the object.
         * @return the protocol field value
         */
        @Nullable public Boolean isOwn() {
            return (Boolean) value("isOwn");
        }
        /**
         * Property symbol object, if the property is of the {@code symbol} type.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject symbol() {
            return Runtime.RemoteObject.fromMap(objectMap(value("symbol")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Property name or symbol description.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The value associated with the property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * True if the value associated with the property may be changed (data descriptors only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder writable(@Nullable Boolean value) {
                if (value == null) values.remove("writable");
                else values.put("writable", jsonValue(value));
                return this;
            }
            /**
             * A function which serves as a getter for the property, or {@code undefined} if there is no getter (accessor descriptors only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder get(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("get");
                else values.put("get", jsonValue(value));
                return this;
            }
            /**
             * A function which serves as a setter for the property, or {@code undefined} if there is no setter (accessor descriptors only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder set(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("set");
                else values.put("set", jsonValue(value));
                return this;
            }
            /**
             * True if the type of this property descriptor may be changed and if the property may be deleted from the corresponding object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder configurable(@Nullable Boolean value) {
                if (value == null) values.remove("configurable");
                else values.put("configurable", jsonValue(value));
                return this;
            }
            /**
             * True if this property shows up during enumeration of the properties on the corresponding object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enumerable(@Nullable Boolean value) {
                if (value == null) values.remove("enumerable");
                else values.put("enumerable", jsonValue(value));
                return this;
            }
            /**
             * True if the result was thrown during the evaluation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder wasThrown(@Nullable Boolean value) {
                if (value == null) values.remove("wasThrown");
                else values.put("wasThrown", jsonValue(value));
                return this;
            }
            /**
             * True if the property is owned for the object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isOwn(@Nullable Boolean value) {
                if (value == null) values.remove("isOwn");
                else values.put("isOwn", jsonValue(value));
                return this;
            }
            /**
             * Property symbol object, if the property is of the {@code symbol} type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder symbol(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("symbol");
                else values.put("symbol", jsonValue(value));
                return this;
            }
            public PropertyDescriptor build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("configurable")) throw new IllegalStateException("Missing required CDP field: configurable");
                if (!values.containsKey("enumerable")) throw new IllegalStateException("Missing required CDP field: enumerable");
                return new PropertyDescriptor(values);
            }
        }
    }
    /**
     * Object internal property descriptor. This property isn&#x27;t normally visible in JavaScript code.
     */
    public static final class InternalPropertyDescriptor extends CdpObject {
        private InternalPropertyDescriptor(Map<String, Object> values) { super(values); }
        @Nullable public static InternalPropertyDescriptor fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InternalPropertyDescriptor(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Conventional property name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The value associated with the property.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject value() {
            return Runtime.RemoteObject.fromMap(objectMap(value("value")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Conventional property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The value associated with the property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public InternalPropertyDescriptor build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new InternalPropertyDescriptor(values);
            }
        }
    }
    /**
     * Object private field descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PrivatePropertyDescriptor extends CdpObject {
        private PrivatePropertyDescriptor(Map<String, Object> values) { super(values); }
        @Nullable public static PrivatePropertyDescriptor fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrivatePropertyDescriptor(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Private property name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The value associated with the private property.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject value() {
            return Runtime.RemoteObject.fromMap(objectMap(value("value")));
        }
        /**
         * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject get() {
            return Runtime.RemoteObject.fromMap(objectMap(value("get")));
        }
        /**
         * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject set() {
            return Runtime.RemoteObject.fromMap(objectMap(value("set")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Private property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The value associated with the private property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * A function which serves as a getter for the private property, or {@code undefined} if there is no getter (accessor descriptors only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder get(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("get");
                else values.put("get", jsonValue(value));
                return this;
            }
            /**
             * A function which serves as a setter for the private property, or {@code undefined} if there is no setter (accessor descriptors only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder set(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("set");
                else values.put("set", jsonValue(value));
                return this;
            }
            public PrivatePropertyDescriptor build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new PrivatePropertyDescriptor(values);
            }
        }
    }
    /**
     * Represents function call argument. Either remote object id {@code objectId}, primitive {@code value}, unserializable primitive value or neither of (for undefined) them should be specified.
     */
    public static final class CallArgument extends CdpObject {
        private CallArgument(Map<String, Object> values) { super(values); }
        @Nullable public static CallArgument fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CallArgument(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Primitive value or serializable javascript object.
         * @return the protocol field value
         */
        @Nullable public Object value() {
            return value("value");
        }
        /**
         * Primitive value which can not be JSON-stringified.
         * @return the protocol field value
         */
        @Nullable public String unserializableValue() {
            return (String) value("unserializableValue");
        }
        /**
         * Remote object handle.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Primitive value or serializable javascript object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Object value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Primitive value which can not be JSON-stringified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unserializableValue(@Nullable String value) {
                if (value == null) values.remove("unserializableValue");
                else values.put("unserializableValue", jsonValue(value));
                return this;
            }
            /**
             * Remote object handle.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public CallArgument build() {
                return new CallArgument(values);
            }
        }
    }
    /**
     * Description of an isolated world.
     */
    public static final class ExecutionContextDescription extends CdpObject {
        private ExecutionContextDescription(Map<String, Object> values) { super(values); }
        @Nullable public static ExecutionContextDescription fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExecutionContextDescription(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
         * @return the protocol field value
         */
        @Nullable public Long id() {
            return numberAsLong(value("id"));
        }
        /**
         * Execution context origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Human readable name describing given context.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * A system-unique execution context identifier. Unlike the id, this is unique across multiple processes, so can be reliably used to identify specific context while backend performs a cross-process navigation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String uniqueId() {
            return (String) value("uniqueId");
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> auxData() {
            return objectMap(value("auxData"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique id of the execution context. It can be used to specify in which execution context script evaluation should be performed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable Long value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Execution context origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Human readable name describing given context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * A system-unique execution context identifier. Unlike the id, this is unique across multiple processes, so can be reliably used to identify specific context while backend performs a cross-process navigation.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uniqueId(@Nullable String value) {
                if (value == null) values.remove("uniqueId");
                else values.put("uniqueId", jsonValue(value));
                return this;
            }
            /**
             * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder auxData(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("auxData");
                else values.put("auxData", jsonValue(value));
                return this;
            }
            public ExecutionContextDescription build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("uniqueId")) throw new IllegalStateException("Missing required CDP field: uniqueId");
                return new ExecutionContextDescription(values);
            }
        }
    }
    /**
     * Detailed information about exception (or error) that was thrown during script compilation or execution.
     */
    public static final class ExceptionDetails extends CdpObject {
        private ExceptionDetails(Map<String, Object> values) { super(values); }
        @Nullable public static ExceptionDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExceptionDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Exception id.
         * @return the protocol field value
         */
        @Nullable public Long exceptionId() {
            return numberAsLong(value("exceptionId"));
        }
        /**
         * Exception text, which should be used together with exception object when available.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Line number of the exception location (0-based).
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Column number of the exception location (0-based).
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        /**
         * Script ID of the exception location.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * URL of the exception location, to be used when the script was not reported.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * JavaScript stack trace if available.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        /**
         * Exception object if available.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject exception() {
            return Runtime.RemoteObject.fromMap(objectMap(value("exception")));
        }
        /**
         * Identifier of the context where exception happened.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> exceptionMetaData() {
            return objectMap(value("exceptionMetaData"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Exception id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionId(@Nullable Long value) {
                if (value == null) values.remove("exceptionId");
                else values.put("exceptionId", jsonValue(value));
                return this;
            }
            /**
             * Exception text, which should be used together with exception object when available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Line number of the exception location (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Column number of the exception location (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            /**
             * Script ID of the exception location.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * URL of the exception location, to be used when the script was not reported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * JavaScript stack trace if available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            /**
             * Exception object if available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exception(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("exception");
                else values.put("exception", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the context where exception happened.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Dictionary with entries of meta data that the client associated with this exception, such as information about associated network requests, etc.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionMetaData(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("exceptionMetaData");
                else values.put("exceptionMetaData", jsonValue(value));
                return this;
            }
            public ExceptionDetails build() {
                if (!values.containsKey("exceptionId")) throw new IllegalStateException("Missing required CDP field: exceptionId");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new ExceptionDetails(values);
            }
        }
    }
    /**
     * Stack entry for runtime errors and assertions.
     */
    public static final class CallFrame extends CdpObject {
        private CallFrame(Map<String, Object> values) { super(values); }
        @Nullable public static CallFrame fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CallFrame(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        @Nullable public String functionName() {
            return (String) value("functionName");
        }
        /**
         * JavaScript script id.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * JavaScript script name or url.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * JavaScript script line number (0-based).
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * JavaScript script column number (0-based).
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript function name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functionName(@Nullable String value) {
                if (value == null) values.remove("functionName");
                else values.put("functionName", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script name or url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script line number (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script column number (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            public CallFrame build() {
                if (!values.containsKey("functionName")) throw new IllegalStateException("Missing required CDP field: functionName");
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new CallFrame(values);
            }
        }
    }
    /**
     * Call frames for assertions or error messages.
     */
    public static final class StackTrace extends CdpObject {
        private StackTrace(Map<String, Object> values) { super(values); }
        @Nullable public static StackTrace fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StackTrace(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * String label of this stack trace. For async traces this may be a name of the function that initiated the async call.
         * @return the protocol field value
         */
        @Nullable public String description() {
            return (String) value("description");
        }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.CallFrame> callFrames() {
            return list(value("callFrames"), element0 -> Runtime.CallFrame.fromMap(objectMap(element0)));
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace parent() {
            return Runtime.StackTrace.fromMap(objectMap(value("parent")));
        }
        /**
         * Asynchronous JavaScript stack trace that preceded this stack, if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTraceId parentId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("parentId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * String label of this stack trace. For async traces this may be a name of the function that initiated the async call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable String value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * JavaScript function name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrames(@Nullable java.util.List<Runtime.CallFrame> value) {
                if (value == null) values.remove("callFrames");
                else values.put("callFrames", jsonValue(value));
                return this;
            }
            /**
             * Asynchronous JavaScript stack trace that preceded this stack, if available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parent(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("parent");
                else values.put("parent", jsonValue(value));
                return this;
            }
            /**
             * Asynchronous JavaScript stack trace that preceded this stack, if available.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            public StackTrace build() {
                if (!values.containsKey("callFrames")) throw new IllegalStateException("Missing required CDP field: callFrames");
                return new StackTrace(values);
            }
        }
    }
    /**
     * If {@code debuggerId} is set stack trace comes from another debugger and can be resolved there. This allows to track cross-debugger calls. See {@code Runtime.StackTrace} and {@code Debugger.paused} for usages.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StackTraceId extends CdpObject {
        private StackTraceId(Map<String, Object> values) { super(values); }
        @Nullable public static StackTraceId fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StackTraceId(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Returns the debuggerId field.
         * @return the protocol field value
         */
        @Nullable public String debuggerId() {
            return (String) value("debuggerId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Sets the debuggerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debuggerId(@Nullable String value) {
                if (value == null) values.remove("debuggerId");
                else values.put("debuggerId", jsonValue(value));
                return this;
            }
            public StackTraceId build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new StackTraceId(values);
            }
        }
    }
    /**
     * Add handler to promise with given promise object id.
     */
    public static final class AwaitPromiseParams extends CdpObject {
        private AwaitPromiseParams(Map<String, Object> values) { super(values); }
        @Nullable public static AwaitPromiseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AwaitPromiseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the promise.
         * @return the protocol field value
         */
        @Nullable public String promiseObjectId() {
            return (String) value("promiseObjectId");
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @return the protocol field value
         */
        @Nullable public Boolean returnByValue() {
            return (Boolean) value("returnByValue");
        }
        /**
         * Whether preview should be generated for the result.
         * @return the protocol field value
         */
        @Nullable public Boolean generatePreview() {
            return (Boolean) value("generatePreview");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the promise.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder promiseObjectId(@Nullable String value) {
                if (value == null) values.remove("promiseObjectId");
                else values.put("promiseObjectId", jsonValue(value));
                return this;
            }
            /**
             * Whether the result is expected to be a JSON object that should be sent by value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnByValue(@Nullable Boolean value) {
                if (value == null) values.remove("returnByValue");
                else values.put("returnByValue", jsonValue(value));
                return this;
            }
            /**
             * Whether preview should be generated for the result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generatePreview(@Nullable Boolean value) {
                if (value == null) values.remove("generatePreview");
                else values.put("generatePreview", jsonValue(value));
                return this;
            }
            public AwaitPromiseParams build() {
                if (!values.containsKey("promiseObjectId")) throw new IllegalStateException("Missing required CDP field: promiseObjectId");
                return new AwaitPromiseParams(values);
            }
        }
    }
    /**
     * Add handler to promise with given promise object id.
     */
    public static final class AwaitPromiseResult extends CdpObject {
        private AwaitPromiseResult(Map<String, Object> values) { super(values); }
        @Nullable public static AwaitPromiseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AwaitPromiseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Promise result. Will contain rejected value if promise was rejected.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject result() {
            return Runtime.RemoteObject.fromMap(objectMap(value("result")));
        }
        /**
         * Exception details if stack strace is available.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Promise result. Will contain rejected value if promise was rejected.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Exception details if stack strace is available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public AwaitPromiseResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new AwaitPromiseResult(values);
            }
        }
    }
    /**
     * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
     */
    public static final class CallFunctionOnParams extends CdpObject {
        private CallFunctionOnParams(Map<String, Object> values) { super(values); }
        @Nullable public static CallFunctionOnParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CallFunctionOnParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Declaration of the function to call.
         * @return the protocol field value
         */
        @Nullable public String functionDeclaration() {
            return (String) value("functionDeclaration");
        }
        /**
         * Identifier of the object to call function on. Either objectId or executionContextId should be specified.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Call arguments. All call arguments must belong to the same JavaScript world as the target object.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.CallArgument> arguments() {
            return list(value("arguments"), element0 -> Runtime.CallArgument.fromMap(objectMap(element0)));
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value
         */
        @Nullable public Boolean silent() {
            return (Boolean) value("silent");
        }
        /**
         * Whether the result is expected to be a JSON object which should be sent by value. Can be overriden by {@code serializationOptions}.
         * @return the protocol field value
         */
        @Nullable public Boolean returnByValue() {
            return (Boolean) value("returnByValue");
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean generatePreview() {
            return (Boolean) value("generatePreview");
        }
        /**
         * Whether execution should be treated as initiated by user in the UI.
         * @return the protocol field value
         */
        @Nullable public Boolean userGesture() {
            return (Boolean) value("userGesture");
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @return the protocol field value
         */
        @Nullable public Boolean awaitPromise() {
            return (Boolean) value("awaitPromise");
        }
        /**
         * Specifies execution context which global object will be used to call function on. Either executionContextId or objectId should be specified.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Symbolic group name that can be used to release multiple objects. If objectGroup is not specified and objectId is, objectGroup will be inherited from object.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean throwOnSideEffect() {
            return (Boolean) value("throwOnSideEffect");
        }
        /**
         * An alternative way to specify the execution context to call function on. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental function call in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code executionContextId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String uniqueContextId() {
            return (String) value("uniqueContextId");
        }
        /**
         * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.SerializationOptions serializationOptions() {
            return Runtime.SerializationOptions.fromMap(objectMap(value("serializationOptions")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Declaration of the function to call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functionDeclaration(@Nullable String value) {
                if (value == null) values.remove("functionDeclaration");
                else values.put("functionDeclaration", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the object to call function on. Either objectId or executionContextId should be specified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Call arguments. All call arguments must belong to the same JavaScript world as the target object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder arguments(@Nullable java.util.List<Runtime.CallArgument> value) {
                if (value == null) values.remove("arguments");
                else values.put("arguments", jsonValue(value));
                return this;
            }
            /**
             * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder silent(@Nullable Boolean value) {
                if (value == null) values.remove("silent");
                else values.put("silent", jsonValue(value));
                return this;
            }
            /**
             * Whether the result is expected to be a JSON object which should be sent by value. Can be overriden by {@code serializationOptions}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnByValue(@Nullable Boolean value) {
                if (value == null) values.remove("returnByValue");
                else values.put("returnByValue", jsonValue(value));
                return this;
            }
            /**
             * Whether preview should be generated for the result.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generatePreview(@Nullable Boolean value) {
                if (value == null) values.remove("generatePreview");
                else values.put("generatePreview", jsonValue(value));
                return this;
            }
            /**
             * Whether execution should be treated as initiated by user in the UI.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userGesture(@Nullable Boolean value) {
                if (value == null) values.remove("userGesture");
                else values.put("userGesture", jsonValue(value));
                return this;
            }
            /**
             * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder awaitPromise(@Nullable Boolean value) {
                if (value == null) values.remove("awaitPromise");
                else values.put("awaitPromise", jsonValue(value));
                return this;
            }
            /**
             * Specifies execution context which global object will be used to call function on. Either executionContextId or objectId should be specified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Symbolic group name that can be used to release multiple objects. If objectGroup is not specified and objectId is, objectGroup will be inherited from object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            /**
             * Whether to throw an exception if side effect cannot be ruled out during evaluation.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder throwOnSideEffect(@Nullable Boolean value) {
                if (value == null) values.remove("throwOnSideEffect");
                else values.put("throwOnSideEffect", jsonValue(value));
                return this;
            }
            /**
             * An alternative way to specify the execution context to call function on. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental function call in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code executionContextId}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uniqueContextId(@Nullable String value) {
                if (value == null) values.remove("uniqueContextId");
                else values.put("uniqueContextId", jsonValue(value));
                return this;
            }
            /**
             * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serializationOptions(@Nullable Runtime.SerializationOptions value) {
                if (value == null) values.remove("serializationOptions");
                else values.put("serializationOptions", jsonValue(value));
                return this;
            }
            public CallFunctionOnParams build() {
                if (!values.containsKey("functionDeclaration")) throw new IllegalStateException("Missing required CDP field: functionDeclaration");
                return new CallFunctionOnParams(values);
            }
        }
    }
    /**
     * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
     */
    public static final class CallFunctionOnResult extends CdpObject {
        private CallFunctionOnResult(Map<String, Object> values) { super(values); }
        @Nullable public static CallFunctionOnResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CallFunctionOnResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Call result.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject result() {
            return Runtime.RemoteObject.fromMap(objectMap(value("result")));
        }
        /**
         * Exception details.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Call result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public CallFunctionOnResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new CallFunctionOnResult(values);
            }
        }
    }
    /**
     * Compiles expression.
     */
    public static final class CompileScriptParams extends CdpObject {
        private CompileScriptParams(Map<String, Object> values) { super(values); }
        @Nullable public static CompileScriptParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CompileScriptParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Expression to compile.
         * @return the protocol field value
         */
        @Nullable public String expression() {
            return (String) value("expression");
        }
        /**
         * Source url to be set for the script.
         * @return the protocol field value
         */
        @Nullable public String sourceURL() {
            return (String) value("sourceURL");
        }
        /**
         * Specifies whether the compiled script should be persisted.
         * @return the protocol field value
         */
        @Nullable public Boolean persistScript() {
            return (Boolean) value("persistScript");
        }
        /**
         * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Expression to compile.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expression(@Nullable String value) {
                if (value == null) values.remove("expression");
                else values.put("expression", jsonValue(value));
                return this;
            }
            /**
             * Source url to be set for the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceURL(@Nullable String value) {
                if (value == null) values.remove("sourceURL");
                else values.put("sourceURL", jsonValue(value));
                return this;
            }
            /**
             * Specifies whether the compiled script should be persisted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder persistScript(@Nullable Boolean value) {
                if (value == null) values.remove("persistScript");
                else values.put("persistScript", jsonValue(value));
                return this;
            }
            /**
             * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            public CompileScriptParams build() {
                if (!values.containsKey("expression")) throw new IllegalStateException("Missing required CDP field: expression");
                if (!values.containsKey("sourceURL")) throw new IllegalStateException("Missing required CDP field: sourceURL");
                if (!values.containsKey("persistScript")) throw new IllegalStateException("Missing required CDP field: persistScript");
                return new CompileScriptParams(values);
            }
        }
    }
    /**
     * Compiles expression.
     */
    public static final class CompileScriptResult extends CdpObject {
        private CompileScriptResult(Map<String, Object> values) { super(values); }
        @Nullable public static CompileScriptResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CompileScriptResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Exception details.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public CompileScriptResult build() {
                return new CompileScriptResult(values);
            }
        }
    }
    /**
     * Disables reporting of execution contexts creation.
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
     * Disables reporting of execution contexts creation.
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
     * Discards collected exceptions and console API calls.
     */
    public static final class DiscardConsoleEntriesParams extends CdpObject {
        private DiscardConsoleEntriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static DiscardConsoleEntriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DiscardConsoleEntriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DiscardConsoleEntriesParams build() {
                return new DiscardConsoleEntriesParams(values);
            }
        }
    }
    /**
     * Discards collected exceptions and console API calls.
     */
    public static final class DiscardConsoleEntriesResult extends CdpObject {
        private DiscardConsoleEntriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static DiscardConsoleEntriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DiscardConsoleEntriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DiscardConsoleEntriesResult build() {
                return new DiscardConsoleEntriesResult(values);
            }
        }
    }
    /**
     * Enables reporting of execution contexts creation by means of {@code executionContextCreated} event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
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
     * Enables reporting of execution contexts creation by means of {@code executionContextCreated} event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
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
     * Evaluates expression on global object.
     */
    public static final class EvaluateParams extends CdpObject {
        private EvaluateParams(Map<String, Object> values) { super(values); }
        @Nullable public static EvaluateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EvaluateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Expression to evaluate.
         * @return the protocol field value
         */
        @Nullable public String expression() {
            return (String) value("expression");
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        /**
         * Determines whether Command Line API should be available during the evaluation.
         * @return the protocol field value
         */
        @Nullable public Boolean includeCommandLineAPI() {
            return (Boolean) value("includeCommandLineAPI");
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value
         */
        @Nullable public Boolean silent() {
            return (Boolean) value("silent");
        }
        /**
         * Specifies in which execution context to perform evaluation. If the parameter is omitted the evaluation will be performed in the context of the inspected page. This is mutually exclusive with {@code uniqueContextId}, which offers an alternative way to identify the execution context that is more reliable in a multi-process environment.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @return the protocol field value
         */
        @Nullable public Boolean returnByValue() {
            return (Boolean) value("returnByValue");
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean generatePreview() {
            return (Boolean) value("generatePreview");
        }
        /**
         * Whether execution should be treated as initiated by user in the UI.
         * @return the protocol field value
         */
        @Nullable public Boolean userGesture() {
            return (Boolean) value("userGesture");
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @return the protocol field value
         */
        @Nullable public Boolean awaitPromise() {
            return (Boolean) value("awaitPromise");
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation. This implies {@code disableBreaks} below.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean throwOnSideEffect() {
            return (Boolean) value("throwOnSideEffect");
        }
        /**
         * Terminate execution after timing out (number of milliseconds).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double timeout() {
            return numberAsDouble(value("timeout"));
        }
        /**
         * Disable breakpoints during execution.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean disableBreaks() {
            return (Boolean) value("disableBreaks");
        }
        /**
         * Setting this flag to true enables {@code let} re-declaration and top-level {@code await}. Note that {@code let} variables can only be re-declared if they originate from {@code replMode} themselves.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean replMode() {
            return (Boolean) value("replMode");
        }
        /**
         * The Content Security Policy (CSP) for the target might block &#x27;unsafe-eval&#x27; which includes eval(), Function(), setTimeout() and setInterval() when called with non-callable arguments. This flag bypasses CSP for this evaluation and allows unsafe-eval. Defaults to true.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean allowUnsafeEvalBlockedByCSP() {
            return (Boolean) value("allowUnsafeEvalBlockedByCSP");
        }
        /**
         * An alternative way to specify the execution context to evaluate in. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental evaluation of the expression in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code contextId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String uniqueContextId() {
            return (String) value("uniqueContextId");
        }
        /**
         * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.SerializationOptions serializationOptions() {
            return Runtime.SerializationOptions.fromMap(objectMap(value("serializationOptions")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Expression to evaluate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expression(@Nullable String value) {
                if (value == null) values.remove("expression");
                else values.put("expression", jsonValue(value));
                return this;
            }
            /**
             * Symbolic group name that can be used to release multiple objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            /**
             * Determines whether Command Line API should be available during the evaluation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeCommandLineAPI(@Nullable Boolean value) {
                if (value == null) values.remove("includeCommandLineAPI");
                else values.put("includeCommandLineAPI", jsonValue(value));
                return this;
            }
            /**
             * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder silent(@Nullable Boolean value) {
                if (value == null) values.remove("silent");
                else values.put("silent", jsonValue(value));
                return this;
            }
            /**
             * Specifies in which execution context to perform evaluation. If the parameter is omitted the evaluation will be performed in the context of the inspected page. This is mutually exclusive with {@code uniqueContextId}, which offers an alternative way to identify the execution context that is more reliable in a multi-process environment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Whether the result is expected to be a JSON object that should be sent by value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnByValue(@Nullable Boolean value) {
                if (value == null) values.remove("returnByValue");
                else values.put("returnByValue", jsonValue(value));
                return this;
            }
            /**
             * Whether preview should be generated for the result.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generatePreview(@Nullable Boolean value) {
                if (value == null) values.remove("generatePreview");
                else values.put("generatePreview", jsonValue(value));
                return this;
            }
            /**
             * Whether execution should be treated as initiated by user in the UI.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userGesture(@Nullable Boolean value) {
                if (value == null) values.remove("userGesture");
                else values.put("userGesture", jsonValue(value));
                return this;
            }
            /**
             * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder awaitPromise(@Nullable Boolean value) {
                if (value == null) values.remove("awaitPromise");
                else values.put("awaitPromise", jsonValue(value));
                return this;
            }
            /**
             * Whether to throw an exception if side effect cannot be ruled out during evaluation. This implies {@code disableBreaks} below.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder throwOnSideEffect(@Nullable Boolean value) {
                if (value == null) values.remove("throwOnSideEffect");
                else values.put("throwOnSideEffect", jsonValue(value));
                return this;
            }
            /**
             * Terminate execution after timing out (number of milliseconds).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timeout(@Nullable Double value) {
                if (value == null) values.remove("timeout");
                else values.put("timeout", jsonValue(value));
                return this;
            }
            /**
             * Disable breakpoints during execution.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disableBreaks(@Nullable Boolean value) {
                if (value == null) values.remove("disableBreaks");
                else values.put("disableBreaks", jsonValue(value));
                return this;
            }
            /**
             * Setting this flag to true enables {@code let} re-declaration and top-level {@code await}. Note that {@code let} variables can only be re-declared if they originate from {@code replMode} themselves.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder replMode(@Nullable Boolean value) {
                if (value == null) values.remove("replMode");
                else values.put("replMode", jsonValue(value));
                return this;
            }
            /**
             * The Content Security Policy (CSP) for the target might block &#x27;unsafe-eval&#x27; which includes eval(), Function(), setTimeout() and setInterval() when called with non-callable arguments. This flag bypasses CSP for this evaluation and allows unsafe-eval. Defaults to true.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowUnsafeEvalBlockedByCSP(@Nullable Boolean value) {
                if (value == null) values.remove("allowUnsafeEvalBlockedByCSP");
                else values.put("allowUnsafeEvalBlockedByCSP", jsonValue(value));
                return this;
            }
            /**
             * An alternative way to specify the execution context to evaluate in. Compared to contextId that may be reused across processes, this is guaranteed to be system-unique, so it can be used to prevent accidental evaluation of the expression in context different than intended (e.g. as a result of navigation across process boundaries). This is mutually exclusive with {@code contextId}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uniqueContextId(@Nullable String value) {
                if (value == null) values.remove("uniqueContextId");
                else values.put("uniqueContextId", jsonValue(value));
                return this;
            }
            /**
             * Specifies the result serialization. If provided, overrides {@code generatePreview} and {@code returnByValue}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serializationOptions(@Nullable Runtime.SerializationOptions value) {
                if (value == null) values.remove("serializationOptions");
                else values.put("serializationOptions", jsonValue(value));
                return this;
            }
            public EvaluateParams build() {
                if (!values.containsKey("expression")) throw new IllegalStateException("Missing required CDP field: expression");
                return new EvaluateParams(values);
            }
        }
    }
    /**
     * Evaluates expression on global object.
     */
    public static final class EvaluateResult extends CdpObject {
        private EvaluateResult(Map<String, Object> values) { super(values); }
        @Nullable public static EvaluateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EvaluateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Evaluation result.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject result() {
            return Runtime.RemoteObject.fromMap(objectMap(value("result")));
        }
        /**
         * Exception details.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Evaluation result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public EvaluateResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new EvaluateResult(values);
            }
        }
    }
    /**
     * Returns the isolate id.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetIsolateIdParams extends CdpObject {
        private GetIsolateIdParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetIsolateIdParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetIsolateIdParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetIsolateIdParams build() {
                return new GetIsolateIdParams(values);
            }
        }
    }
    /**
     * Returns the isolate id.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetIsolateIdResult extends CdpObject {
        private GetIsolateIdResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetIsolateIdResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetIsolateIdResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The isolate id.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The isolate id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public GetIsolateIdResult build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new GetIsolateIdResult(values);
            }
        }
    }
    /**
     * Returns the JavaScript heap usage. It is the total usage of the corresponding isolate not scoped to a particular Runtime.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHeapUsageParams extends CdpObject {
        private GetHeapUsageParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetHeapUsageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHeapUsageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetHeapUsageParams build() {
                return new GetHeapUsageParams(values);
            }
        }
    }
    /**
     * Returns the JavaScript heap usage. It is the total usage of the corresponding isolate not scoped to a particular Runtime.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetHeapUsageResult extends CdpObject {
        private GetHeapUsageResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetHeapUsageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHeapUsageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Used JavaScript heap size in bytes.
         * @return the protocol field value
         */
        @Nullable public Double usedSize() {
            return numberAsDouble(value("usedSize"));
        }
        /**
         * Allocated JavaScript heap size in bytes.
         * @return the protocol field value
         */
        @Nullable public Double totalSize() {
            return numberAsDouble(value("totalSize"));
        }
        /**
         * Used size in bytes in the embedder&#x27;s garbage-collected heap.
         * @return the protocol field value
         */
        @Nullable public Double embedderHeapUsedSize() {
            return numberAsDouble(value("embedderHeapUsedSize"));
        }
        /**
         * Size in bytes of backing storage for array buffers and external strings.
         * @return the protocol field value
         */
        @Nullable public Double backingStorageSize() {
            return numberAsDouble(value("backingStorageSize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Used JavaScript heap size in bytes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder usedSize(@Nullable Double value) {
                if (value == null) values.remove("usedSize");
                else values.put("usedSize", jsonValue(value));
                return this;
            }
            /**
             * Allocated JavaScript heap size in bytes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder totalSize(@Nullable Double value) {
                if (value == null) values.remove("totalSize");
                else values.put("totalSize", jsonValue(value));
                return this;
            }
            /**
             * Used size in bytes in the embedder&#x27;s garbage-collected heap.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder embedderHeapUsedSize(@Nullable Double value) {
                if (value == null) values.remove("embedderHeapUsedSize");
                else values.put("embedderHeapUsedSize", jsonValue(value));
                return this;
            }
            /**
             * Size in bytes of backing storage for array buffers and external strings.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backingStorageSize(@Nullable Double value) {
                if (value == null) values.remove("backingStorageSize");
                else values.put("backingStorageSize", jsonValue(value));
                return this;
            }
            public GetHeapUsageResult build() {
                if (!values.containsKey("usedSize")) throw new IllegalStateException("Missing required CDP field: usedSize");
                if (!values.containsKey("totalSize")) throw new IllegalStateException("Missing required CDP field: totalSize");
                if (!values.containsKey("embedderHeapUsedSize")) throw new IllegalStateException("Missing required CDP field: embedderHeapUsedSize");
                if (!values.containsKey("backingStorageSize")) throw new IllegalStateException("Missing required CDP field: backingStorageSize");
                return new GetHeapUsageResult(values);
            }
        }
    }
    /**
     * Returns properties of a given object. Object group of the result is inherited from the target object.
     */
    public static final class GetPropertiesParams extends CdpObject {
        private GetPropertiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetPropertiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPropertiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the object to return properties for.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * If true, returns properties belonging only to the element itself, not to its prototype chain.
         * @return the protocol field value
         */
        @Nullable public Boolean ownProperties() {
            return (Boolean) value("ownProperties");
        }
        /**
         * If true, returns accessor properties (with getter/setter) only; internal properties are not returned either.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean accessorPropertiesOnly() {
            return (Boolean) value("accessorPropertiesOnly");
        }
        /**
         * Whether preview should be generated for the results.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean generatePreview() {
            return (Boolean) value("generatePreview");
        }
        /**
         * If true, returns non-indexed properties only.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean nonIndexedPropertiesOnly() {
            return (Boolean) value("nonIndexedPropertiesOnly");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the object to return properties for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * If true, returns properties belonging only to the element itself, not to its prototype chain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownProperties(@Nullable Boolean value) {
                if (value == null) values.remove("ownProperties");
                else values.put("ownProperties", jsonValue(value));
                return this;
            }
            /**
             * If true, returns accessor properties (with getter/setter) only; internal properties are not returned either.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accessorPropertiesOnly(@Nullable Boolean value) {
                if (value == null) values.remove("accessorPropertiesOnly");
                else values.put("accessorPropertiesOnly", jsonValue(value));
                return this;
            }
            /**
             * Whether preview should be generated for the results.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generatePreview(@Nullable Boolean value) {
                if (value == null) values.remove("generatePreview");
                else values.put("generatePreview", jsonValue(value));
                return this;
            }
            /**
             * If true, returns non-indexed properties only.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nonIndexedPropertiesOnly(@Nullable Boolean value) {
                if (value == null) values.remove("nonIndexedPropertiesOnly");
                else values.put("nonIndexedPropertiesOnly", jsonValue(value));
                return this;
            }
            public GetPropertiesParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new GetPropertiesParams(values);
            }
        }
    }
    /**
     * Returns properties of a given object. Object group of the result is inherited from the target object.
     */
    public static final class GetPropertiesResult extends CdpObject {
        private GetPropertiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetPropertiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPropertiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object properties.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.PropertyDescriptor> result() {
            return list(value("result"), element0 -> Runtime.PropertyDescriptor.fromMap(objectMap(element0)));
        }
        /**
         * Internal object properties (only of the element itself).
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.InternalPropertyDescriptor> internalProperties() {
            return list(value("internalProperties"), element0 -> Runtime.InternalPropertyDescriptor.fromMap(objectMap(element0)));
        }
        /**
         * Object private properties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.PrivatePropertyDescriptor> privateProperties() {
            return list(value("privateProperties"), element0 -> Runtime.PrivatePropertyDescriptor.fromMap(objectMap(element0)));
        }
        /**
         * Exception details.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object properties.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Runtime.PropertyDescriptor> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Internal object properties (only of the element itself).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder internalProperties(@Nullable java.util.List<Runtime.InternalPropertyDescriptor> value) {
                if (value == null) values.remove("internalProperties");
                else values.put("internalProperties", jsonValue(value));
                return this;
            }
            /**
             * Object private properties.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder privateProperties(@Nullable java.util.List<Runtime.PrivatePropertyDescriptor> value) {
                if (value == null) values.remove("privateProperties");
                else values.put("privateProperties", jsonValue(value));
                return this;
            }
            /**
             * Exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public GetPropertiesResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new GetPropertiesResult(values);
            }
        }
    }
    /**
     * Returns all let, const and class variables from global scope.
     */
    public static final class GlobalLexicalScopeNamesParams extends CdpObject {
        private GlobalLexicalScopeNamesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GlobalLexicalScopeNamesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GlobalLexicalScopeNamesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Specifies in which execution context to lookup global scope variables.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Specifies in which execution context to lookup global scope variables.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            public GlobalLexicalScopeNamesParams build() {
                return new GlobalLexicalScopeNamesParams(values);
            }
        }
    }
    /**
     * Returns all let, const and class variables from global scope.
     */
    public static final class GlobalLexicalScopeNamesResult extends CdpObject {
        private GlobalLexicalScopeNamesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GlobalLexicalScopeNamesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GlobalLexicalScopeNamesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the names field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> names() {
            return list(value("names"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the names field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder names(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("names");
                else values.put("names", jsonValue(value));
                return this;
            }
            public GlobalLexicalScopeNamesResult build() {
                if (!values.containsKey("names")) throw new IllegalStateException("Missing required CDP field: names");
                return new GlobalLexicalScopeNamesResult(values);
            }
        }
    }
    /**
     * Parameters for Runtime.queryObjects.
     */
    public static final class QueryObjectsParams extends CdpObject {
        private QueryObjectsParams(Map<String, Object> values) { super(values); }
        @Nullable public static QueryObjectsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QueryObjectsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the prototype to return objects for.
         * @return the protocol field value
         */
        @Nullable public String prototypeObjectId() {
            return (String) value("prototypeObjectId");
        }
        /**
         * Symbolic group name that can be used to release the results.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the prototype to return objects for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder prototypeObjectId(@Nullable String value) {
                if (value == null) values.remove("prototypeObjectId");
                else values.put("prototypeObjectId", jsonValue(value));
                return this;
            }
            /**
             * Symbolic group name that can be used to release the results.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            public QueryObjectsParams build() {
                if (!values.containsKey("prototypeObjectId")) throw new IllegalStateException("Missing required CDP field: prototypeObjectId");
                return new QueryObjectsParams(values);
            }
        }
    }
    /**
     * Result of Runtime.queryObjects.
     */
    public static final class QueryObjectsResult extends CdpObject {
        private QueryObjectsResult(Map<String, Object> values) { super(values); }
        @Nullable public static QueryObjectsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QueryObjectsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array with objects.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject objects() {
            return Runtime.RemoteObject.fromMap(objectMap(value("objects")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array with objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objects(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("objects");
                else values.put("objects", jsonValue(value));
                return this;
            }
            public QueryObjectsResult build() {
                if (!values.containsKey("objects")) throw new IllegalStateException("Missing required CDP field: objects");
                return new QueryObjectsResult(values);
            }
        }
    }
    /**
     * Releases remote object with given id.
     */
    public static final class ReleaseObjectParams extends CdpObject {
        private ReleaseObjectParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseObjectParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseObjectParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the object to release.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the object to release.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public ReleaseObjectParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new ReleaseObjectParams(values);
            }
        }
    }
    /**
     * Releases remote object with given id.
     */
    public static final class ReleaseObjectResult extends CdpObject {
        private ReleaseObjectResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseObjectResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseObjectResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReleaseObjectResult build() {
                return new ReleaseObjectResult(values);
            }
        }
    }
    /**
     * Releases all remote objects that belong to a given group.
     */
    public static final class ReleaseObjectGroupParams extends CdpObject {
        private ReleaseObjectGroupParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseObjectGroupParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseObjectGroupParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Symbolic object group name.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Symbolic object group name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            public ReleaseObjectGroupParams build() {
                if (!values.containsKey("objectGroup")) throw new IllegalStateException("Missing required CDP field: objectGroup");
                return new ReleaseObjectGroupParams(values);
            }
        }
    }
    /**
     * Releases all remote objects that belong to a given group.
     */
    public static final class ReleaseObjectGroupResult extends CdpObject {
        private ReleaseObjectGroupResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseObjectGroupResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseObjectGroupResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReleaseObjectGroupResult build() {
                return new ReleaseObjectGroupResult(values);
            }
        }
    }
    /**
     * Tells inspected instance to run if it was waiting for debugger to attach.
     */
    public static final class RunIfWaitingForDebuggerParams extends CdpObject {
        private RunIfWaitingForDebuggerParams(Map<String, Object> values) { super(values); }
        @Nullable public static RunIfWaitingForDebuggerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RunIfWaitingForDebuggerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RunIfWaitingForDebuggerParams build() {
                return new RunIfWaitingForDebuggerParams(values);
            }
        }
    }
    /**
     * Tells inspected instance to run if it was waiting for debugger to attach.
     */
    public static final class RunIfWaitingForDebuggerResult extends CdpObject {
        private RunIfWaitingForDebuggerResult(Map<String, Object> values) { super(values); }
        @Nullable public static RunIfWaitingForDebuggerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RunIfWaitingForDebuggerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RunIfWaitingForDebuggerResult build() {
                return new RunIfWaitingForDebuggerResult(values);
            }
        }
    }
    /**
     * Runs script with given id in a given context.
     */
    public static final class RunScriptParams extends CdpObject {
        private RunScriptParams(Map<String, Object> values) { super(values); }
        @Nullable public static RunScriptParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RunScriptParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script to run.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value
         */
        @Nullable public Boolean silent() {
            return (Boolean) value("silent");
        }
        /**
         * Determines whether Command Line API should be available during the evaluation.
         * @return the protocol field value
         */
        @Nullable public Boolean includeCommandLineAPI() {
            return (Boolean) value("includeCommandLineAPI");
        }
        /**
         * Whether the result is expected to be a JSON object which should be sent by value.
         * @return the protocol field value
         */
        @Nullable public Boolean returnByValue() {
            return (Boolean) value("returnByValue");
        }
        /**
         * Whether preview should be generated for the result.
         * @return the protocol field value
         */
        @Nullable public Boolean generatePreview() {
            return (Boolean) value("generatePreview");
        }
        /**
         * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
         * @return the protocol field value
         */
        @Nullable public Boolean awaitPromise() {
            return (Boolean) value("awaitPromise");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script to run.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Specifies in which execution context to perform script run. If the parameter is omitted the evaluation will be performed in the context of the inspected page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Symbolic group name that can be used to release multiple objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            /**
             * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder silent(@Nullable Boolean value) {
                if (value == null) values.remove("silent");
                else values.put("silent", jsonValue(value));
                return this;
            }
            /**
             * Determines whether Command Line API should be available during the evaluation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeCommandLineAPI(@Nullable Boolean value) {
                if (value == null) values.remove("includeCommandLineAPI");
                else values.put("includeCommandLineAPI", jsonValue(value));
                return this;
            }
            /**
             * Whether the result is expected to be a JSON object which should be sent by value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnByValue(@Nullable Boolean value) {
                if (value == null) values.remove("returnByValue");
                else values.put("returnByValue", jsonValue(value));
                return this;
            }
            /**
             * Whether preview should be generated for the result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generatePreview(@Nullable Boolean value) {
                if (value == null) values.remove("generatePreview");
                else values.put("generatePreview", jsonValue(value));
                return this;
            }
            /**
             * Whether execution should {@code await} for resulting value and return once awaited promise is resolved.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder awaitPromise(@Nullable Boolean value) {
                if (value == null) values.remove("awaitPromise");
                else values.put("awaitPromise", jsonValue(value));
                return this;
            }
            public RunScriptParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                return new RunScriptParams(values);
            }
        }
    }
    /**
     * Runs script with given id in a given context.
     */
    public static final class RunScriptResult extends CdpObject {
        private RunScriptResult(Map<String, Object> values) { super(values); }
        @Nullable public static RunScriptResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RunScriptResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Run result.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject result() {
            return Runtime.RemoteObject.fromMap(objectMap(value("result")));
        }
        /**
         * Exception details.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Run result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public RunScriptResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new RunScriptResult(values);
            }
        }
    }
    /**
     * Enables or disables async call stacks tracking.
     */
    public static final class SetAsyncCallStackDepthParams extends CdpObject {
        private SetAsyncCallStackDepthParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAsyncCallStackDepthParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAsyncCallStackDepthParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
         * @return the protocol field value
         */
        @Nullable public Long maxDepth() {
            return numberAsLong(value("maxDepth"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxDepth(@Nullable Long value) {
                if (value == null) values.remove("maxDepth");
                else values.put("maxDepth", jsonValue(value));
                return this;
            }
            public SetAsyncCallStackDepthParams build() {
                if (!values.containsKey("maxDepth")) throw new IllegalStateException("Missing required CDP field: maxDepth");
                return new SetAsyncCallStackDepthParams(values);
            }
        }
    }
    /**
     * Enables or disables async call stacks tracking.
     */
    public static final class SetAsyncCallStackDepthResult extends CdpObject {
        private SetAsyncCallStackDepthResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAsyncCallStackDepthResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAsyncCallStackDepthResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAsyncCallStackDepthResult build() {
                return new SetAsyncCallStackDepthResult(values);
            }
        }
    }
    /**
     * Parameters for Runtime.setCustomObjectFormatterEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCustomObjectFormatterEnabledParams extends CdpObject {
        private SetCustomObjectFormatterEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCustomObjectFormatterEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCustomObjectFormatterEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enabled field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetCustomObjectFormatterEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetCustomObjectFormatterEnabledParams(values);
            }
        }
    }
    /**
     * Result of Runtime.setCustomObjectFormatterEnabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCustomObjectFormatterEnabledResult extends CdpObject {
        private SetCustomObjectFormatterEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCustomObjectFormatterEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCustomObjectFormatterEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetCustomObjectFormatterEnabledResult build() {
                return new SetCustomObjectFormatterEnabledResult(values);
            }
        }
    }
    /**
     * Parameters for Runtime.setMaxCallStackSizeToCapture.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetMaxCallStackSizeToCaptureParams extends CdpObject {
        private SetMaxCallStackSizeToCaptureParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetMaxCallStackSizeToCaptureParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetMaxCallStackSizeToCaptureParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the size field.
         * @return the protocol field value
         */
        @Nullable public Long size() {
            return numberAsLong(value("size"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the size field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Long value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            public SetMaxCallStackSizeToCaptureParams build() {
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                return new SetMaxCallStackSizeToCaptureParams(values);
            }
        }
    }
    /**
     * Result of Runtime.setMaxCallStackSizeToCapture.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetMaxCallStackSizeToCaptureResult extends CdpObject {
        private SetMaxCallStackSizeToCaptureResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetMaxCallStackSizeToCaptureResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetMaxCallStackSizeToCaptureResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetMaxCallStackSizeToCaptureResult build() {
                return new SetMaxCallStackSizeToCaptureResult(values);
            }
        }
    }
    /**
     * Terminate current or next JavaScript execution. Will cancel the termination when the outer-most script execution ends.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TerminateExecutionParams extends CdpObject {
        private TerminateExecutionParams(Map<String, Object> values) { super(values); }
        @Nullable public static TerminateExecutionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TerminateExecutionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TerminateExecutionParams build() {
                return new TerminateExecutionParams(values);
            }
        }
    }
    /**
     * Terminate current or next JavaScript execution. Will cancel the termination when the outer-most script execution ends.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TerminateExecutionResult extends CdpObject {
        private TerminateExecutionResult(Map<String, Object> values) { super(values); }
        @Nullable public static TerminateExecutionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TerminateExecutionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TerminateExecutionResult build() {
                return new TerminateExecutionResult(values);
            }
        }
    }
    /**
     * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
     */
    public static final class AddBindingParams extends CdpObject {
        private AddBindingParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddBindingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddBindingParams(values);
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
         * If specified, the binding would only be exposed to the specified execution context. If omitted and {@code executionContextName} is not set, the binding is exposed to all execution contexts of the target. This parameter is mutually exclusive with {@code executionContextName}. Deprecated in favor of {@code executionContextName} due to an unclear use case and bugs in implementation (crbug.com/1169639). {@code executionContextId} will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * If specified, the binding is exposed to the executionContext with matching name, even for contexts created after the binding is added. See also {@code ExecutionContext.name} and {@code worldName} parameter to {@code Page.addScriptToEvaluateOnNewDocument}. This parameter is mutually exclusive with {@code executionContextId}.
         * @return the protocol field value
         */
        @Nullable public String executionContextName() {
            return (String) value("executionContextName");
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
             * If specified, the binding would only be exposed to the specified execution context. If omitted and {@code executionContextName} is not set, the binding is exposed to all execution contexts of the target. This parameter is mutually exclusive with {@code executionContextName}. Deprecated in favor of {@code executionContextName} due to an unclear use case and bugs in implementation (crbug.com/1169639). {@code executionContextId} will be removed in the future.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * If specified, the binding is exposed to the executionContext with matching name, even for contexts created after the binding is added. See also {@code ExecutionContext.name} and {@code worldName} parameter to {@code Page.addScriptToEvaluateOnNewDocument}. This parameter is mutually exclusive with {@code executionContextId}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextName(@Nullable String value) {
                if (value == null) values.remove("executionContextName");
                else values.put("executionContextName", jsonValue(value));
                return this;
            }
            public AddBindingParams build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new AddBindingParams(values);
            }
        }
    }
    /**
     * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
     */
    public static final class AddBindingResult extends CdpObject {
        private AddBindingResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddBindingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddBindingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AddBindingResult build() {
                return new AddBindingResult(values);
            }
        }
    }
    /**
     * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
     */
    public static final class RemoveBindingParams extends CdpObject {
        private RemoveBindingParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveBindingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveBindingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
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
            public RemoveBindingParams build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new RemoveBindingParams(values);
            }
        }
    }
    /**
     * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
     */
    public static final class RemoveBindingResult extends CdpObject {
        private RemoveBindingResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveBindingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveBindingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveBindingResult build() {
                return new RemoveBindingResult(values);
            }
        }
    }
    /**
     * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetExceptionDetailsParams extends CdpObject {
        private GetExceptionDetailsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetExceptionDetailsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetExceptionDetailsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The error object for which to resolve the exception details.
         * @return the protocol field value
         */
        @Nullable public String errorObjectId() {
            return (String) value("errorObjectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The error object for which to resolve the exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorObjectId(@Nullable String value) {
                if (value == null) values.remove("errorObjectId");
                else values.put("errorObjectId", jsonValue(value));
                return this;
            }
            public GetExceptionDetailsParams build() {
                if (!values.containsKey("errorObjectId")) throw new IllegalStateException("Missing required CDP field: errorObjectId");
                return new GetExceptionDetailsParams(values);
            }
        }
    }
    /**
     * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetExceptionDetailsResult extends CdpObject {
        private GetExceptionDetailsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetExceptionDetailsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetExceptionDetailsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the exceptionDetails field.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the exceptionDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public GetExceptionDetailsResult build() {
                return new GetExceptionDetailsResult(values);
            }
        }
    }
    /**
     * Notification is issued every time when binding is called.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BindingCalledEvent extends CdpObject {
        private BindingCalledEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BindingCalledEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BindingCalledEvent(values);
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
         * Returns the payload field.
         * @return the protocol field value
         */
        @Nullable public String payload() {
            return (String) value("payload");
        }
        /**
         * Identifier of the context where the call was made.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
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
             * Sets the payload field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder payload(@Nullable String value) {
                if (value == null) values.remove("payload");
                else values.put("payload", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the context where the call was made.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            public BindingCalledEvent build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("payload")) throw new IllegalStateException("Missing required CDP field: payload");
                if (!values.containsKey("executionContextId")) throw new IllegalStateException("Missing required CDP field: executionContextId");
                return new BindingCalledEvent(values);
            }
        }
    }
    /**
     * Issued when console API was called.
     */
    public static final class ConsoleAPICalledEvent extends CdpObject {
        private ConsoleAPICalledEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ConsoleAPICalledEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConsoleAPICalledEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the call.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the call.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String LOG = "log";
            public static final String DEBUG = "debug";
            public static final String INFO = "info";
            public static final String ERROR = "error";
            public static final String WARNING = "warning";
            public static final String DIR = "dir";
            public static final String DIRXML = "dirxml";
            public static final String TABLE = "table";
            public static final String TRACE = "trace";
            public static final String CLEAR = "clear";
            public static final String STARTGROUP = "startGroup";
            public static final String STARTGROUPCOLLAPSED = "startGroupCollapsed";
            public static final String ENDGROUP = "endGroup";
            public static final String ASSERT = "assert";
            public static final String PROFILE = "profile";
            public static final String PROFILEEND = "profileEnd";
            public static final String COUNT = "count";
            public static final String TIMEEND = "timeEnd";
        }
        /**
         * Call arguments.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.RemoteObject> args() {
            return list(value("args"), element0 -> Runtime.RemoteObject.fromMap(objectMap(element0)));
        }
        /**
         * Identifier of the context where the call was made.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Call timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Stack trace captured when the call was made. The async stack chain is automatically reported for the following call types: {@code assert}, {@code error}, {@code trace}, {@code warning}. For other types the async call chain can be retrieved using {@code Debugger.getStackTrace} and {@code stackTrace.parentId} field.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        /**
         * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String context() {
            return (String) value("context");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Call arguments.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder args(@Nullable java.util.List<Runtime.RemoteObject> value) {
                if (value == null) values.remove("args");
                else values.put("args", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the context where the call was made.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Call timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Stack trace captured when the call was made. The async stack chain is automatically reported for the following call types: {@code assert}, {@code error}, {@code trace}, {@code warning}. For other types the async call chain can be retrieved using {@code Debugger.getStackTrace} and {@code stackTrace.parentId} field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            /**
             * Console context descriptor for calls on non-default console context (not console.*): &#x27;anonymous#unique-logger-id&#x27; for call on unnamed context, &#x27;name#unique-logger-id&#x27; for call on named context.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder context(@Nullable String value) {
                if (value == null) values.remove("context");
                else values.put("context", jsonValue(value));
                return this;
            }
            public ConsoleAPICalledEvent build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("args")) throw new IllegalStateException("Missing required CDP field: args");
                if (!values.containsKey("executionContextId")) throw new IllegalStateException("Missing required CDP field: executionContextId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new ConsoleAPICalledEvent(values);
            }
        }
    }
    /**
     * Issued when unhandled exception was revoked.
     */
    public static final class ExceptionRevokedEvent extends CdpObject {
        private ExceptionRevokedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ExceptionRevokedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExceptionRevokedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Reason describing why exception was revoked.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * The id of revoked exception, as reported in {@code exceptionThrown}.
         * @return the protocol field value
         */
        @Nullable public Long exceptionId() {
            return numberAsLong(value("exceptionId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Reason describing why exception was revoked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            /**
             * The id of revoked exception, as reported in {@code exceptionThrown}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionId(@Nullable Long value) {
                if (value == null) values.remove("exceptionId");
                else values.put("exceptionId", jsonValue(value));
                return this;
            }
            public ExceptionRevokedEvent build() {
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                if (!values.containsKey("exceptionId")) throw new IllegalStateException("Missing required CDP field: exceptionId");
                return new ExceptionRevokedEvent(values);
            }
        }
    }
    /**
     * Issued when exception was thrown and unhandled.
     */
    public static final class ExceptionThrownEvent extends CdpObject {
        private ExceptionThrownEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ExceptionThrownEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExceptionThrownEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Timestamp of the exception.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Returns the exceptionDetails field.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Timestamp of the exception.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Sets the exceptionDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public ExceptionThrownEvent build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("exceptionDetails")) throw new IllegalStateException("Missing required CDP field: exceptionDetails");
                return new ExceptionThrownEvent(values);
            }
        }
    }
    /**
     * Issued when new execution context is created.
     */
    public static final class ExecutionContextCreatedEvent extends CdpObject {
        private ExecutionContextCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ExecutionContextCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExecutionContextCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A newly created execution context.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExecutionContextDescription context() {
            return Runtime.ExecutionContextDescription.fromMap(objectMap(value("context")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A newly created execution context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder context(@Nullable Runtime.ExecutionContextDescription value) {
                if (value == null) values.remove("context");
                else values.put("context", jsonValue(value));
                return this;
            }
            public ExecutionContextCreatedEvent build() {
                if (!values.containsKey("context")) throw new IllegalStateException("Missing required CDP field: context");
                return new ExecutionContextCreatedEvent(values);
            }
        }
    }
    /**
     * Issued when execution context is destroyed.
     */
    public static final class ExecutionContextDestroyedEvent extends CdpObject {
        private ExecutionContextDestroyedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ExecutionContextDestroyedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExecutionContextDestroyedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the destroyed context
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Unique Id of the destroyed context
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String executionContextUniqueId() {
            return (String) value("executionContextUniqueId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the destroyed context
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Unique Id of the destroyed context
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextUniqueId(@Nullable String value) {
                if (value == null) values.remove("executionContextUniqueId");
                else values.put("executionContextUniqueId", jsonValue(value));
                return this;
            }
            public ExecutionContextDestroyedEvent build() {
                if (!values.containsKey("executionContextId")) throw new IllegalStateException("Missing required CDP field: executionContextId");
                if (!values.containsKey("executionContextUniqueId")) throw new IllegalStateException("Missing required CDP field: executionContextUniqueId");
                return new ExecutionContextDestroyedEvent(values);
            }
        }
    }
    /**
     * Issued when all executionContexts were cleared in browser
     */
    public static final class ExecutionContextsClearedEvent extends CdpObject {
        private ExecutionContextsClearedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ExecutionContextsClearedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExecutionContextsClearedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ExecutionContextsClearedEvent build() {
                return new ExecutionContextsClearedEvent(values);
            }
        }
    }
    /**
     * Issued when object should be inspected (for example, as a result of inspect() command line API call).
     */
    public static final class InspectRequestedEvent extends CdpObject {
        private InspectRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InspectRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the object field.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject object() {
            return Runtime.RemoteObject.fromMap(objectMap(value("object")));
        }
        /**
         * Returns the hints field.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> hints() {
            return objectMap(value("hints"));
        }
        /**
         * Identifier of the context where the call was made.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the object field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder object(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("object");
                else values.put("object", jsonValue(value));
                return this;
            }
            /**
             * Sets the hints field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hints(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("hints");
                else values.put("hints", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the context where the call was made.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            public InspectRequestedEvent build() {
                if (!values.containsKey("object")) throw new IllegalStateException("Missing required CDP field: object");
                if (!values.containsKey("hints")) throw new IllegalStateException("Missing required CDP field: hints");
                return new InspectRequestedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Add handler to promise with given promise object id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AwaitPromiseResult> awaitPromise(AwaitPromiseParams params) {
            return client.call("Runtime.awaitPromise", params, AwaitPromiseResult::fromMap);
        }
        /**
         * Calls function with given declaration on the given object. Object group of the result is inherited from the target object.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CallFunctionOnResult> callFunctionOn(CallFunctionOnParams params) {
            return client.call("Runtime.callFunctionOn", params, CallFunctionOnResult::fromMap);
        }
        /**
         * Compiles expression.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CompileScriptResult> compileScript(CompileScriptParams params) {
            return client.call("Runtime.compileScript", params, CompileScriptResult::fromMap);
        }
        /**
         * Disables reporting of execution contexts creation.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Runtime.disable", null, DisableResult::fromMap);
        }
        /**
         * Discards collected exceptions and console API calls.
         * @return a stage completing with the command result
         */
        public CompletionStage<DiscardConsoleEntriesResult> discardConsoleEntries() {
            return client.call("Runtime.discardConsoleEntries", null, DiscardConsoleEntriesResult::fromMap);
        }
        /**
         * Enables reporting of execution contexts creation by means of {@code executionContextCreated} event. When the reporting gets enabled the event will be sent immediately for each existing execution context.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Runtime.enable", null, EnableResult::fromMap);
        }
        /**
         * Evaluates expression on global object.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateResult> evaluate(EvaluateParams params) {
            return client.call("Runtime.evaluate", params, EvaluateResult::fromMap);
        }
        /**
         * Returns the isolate id.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetIsolateIdResult> getIsolateId() {
            return client.call("Runtime.getIsolateId", null, GetIsolateIdResult::fromMap);
        }
        /**
         * Returns the JavaScript heap usage. It is the total usage of the corresponding isolate not scoped to a particular Runtime.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetHeapUsageResult> getHeapUsage() {
            return client.call("Runtime.getHeapUsage", null, GetHeapUsageResult::fromMap);
        }
        /**
         * Returns properties of a given object. Object group of the result is inherited from the target object.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPropertiesResult> getProperties(GetPropertiesParams params) {
            return client.call("Runtime.getProperties", params, GetPropertiesResult::fromMap);
        }
        /**
         * Returns all let, const and class variables from global scope.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GlobalLexicalScopeNamesResult> globalLexicalScopeNames(GlobalLexicalScopeNamesParams params) {
            return client.call("Runtime.globalLexicalScopeNames", params, GlobalLexicalScopeNamesResult::fromMap);
        }
        /**
         * Invokes Runtime.queryObjects.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<QueryObjectsResult> queryObjects(QueryObjectsParams params) {
            return client.call("Runtime.queryObjects", params, QueryObjectsResult::fromMap);
        }
        /**
         * Releases remote object with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReleaseObjectResult> releaseObject(ReleaseObjectParams params) {
            return client.call("Runtime.releaseObject", params, ReleaseObjectResult::fromMap);
        }
        /**
         * Releases all remote objects that belong to a given group.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReleaseObjectGroupResult> releaseObjectGroup(ReleaseObjectGroupParams params) {
            return client.call("Runtime.releaseObjectGroup", params, ReleaseObjectGroupResult::fromMap);
        }
        /**
         * Tells inspected instance to run if it was waiting for debugger to attach.
         * @return a stage completing with the command result
         */
        public CompletionStage<RunIfWaitingForDebuggerResult> runIfWaitingForDebugger() {
            return client.call("Runtime.runIfWaitingForDebugger", null, RunIfWaitingForDebuggerResult::fromMap);
        }
        /**
         * Runs script with given id in a given context.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RunScriptResult> runScript(RunScriptParams params) {
            return client.call("Runtime.runScript", params, RunScriptResult::fromMap);
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAsyncCallStackDepthResult> setAsyncCallStackDepth(SetAsyncCallStackDepthParams params) {
            return client.call("Runtime.setAsyncCallStackDepth", params, SetAsyncCallStackDepthResult::fromMap);
        }
        /**
         * Invokes Runtime.setCustomObjectFormatterEnabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCustomObjectFormatterEnabledResult> setCustomObjectFormatterEnabled(SetCustomObjectFormatterEnabledParams params) {
            return client.call("Runtime.setCustomObjectFormatterEnabled", params, SetCustomObjectFormatterEnabledResult::fromMap);
        }
        /**
         * Invokes Runtime.setMaxCallStackSizeToCapture.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetMaxCallStackSizeToCaptureResult> setMaxCallStackSizeToCapture(SetMaxCallStackSizeToCaptureParams params) {
            return client.call("Runtime.setMaxCallStackSizeToCapture", params, SetMaxCallStackSizeToCaptureResult::fromMap);
        }
        /**
         * Terminate current or next JavaScript execution. Will cancel the termination when the outer-most script execution ends.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<TerminateExecutionResult> terminateExecution() {
            return client.call("Runtime.terminateExecution", null, TerminateExecutionResult::fromMap);
        }
        /**
         * If executionContextId is empty, adds binding with the given name on the global objects of all inspected contexts, including those created later, bindings survive reloads. Binding function takes exactly one argument, this argument should be string, in case of any other input, function throws an exception. Each binding function call produces Runtime.bindingCalled notification.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddBindingResult> addBinding(AddBindingParams params) {
            return client.call("Runtime.addBinding", params, AddBindingResult::fromMap);
        }
        /**
         * This method does not remove binding function from global object but unsubscribes current runtime agent from Runtime.bindingCalled notifications.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveBindingResult> removeBinding(RemoveBindingParams params) {
            return client.call("Runtime.removeBinding", params, RemoveBindingResult::fromMap);
        }
        /**
         * This method tries to lookup and populate exception details for a JavaScript Error object. Note that the stackTrace portion of the resulting exceptionDetails will only be populated if the Runtime domain was enabled at the time when the Error was thrown.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetExceptionDetailsResult> getExceptionDetails(GetExceptionDetailsParams params) {
            return client.call("Runtime.getExceptionDetails", params, GetExceptionDetailsResult::fromMap);
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
