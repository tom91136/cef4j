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
 * Input/Output operations for streams produced by DevTools.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/IO.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class IO {
    private IO() {}
    /**
     * This is either obtained from another method or specified as {@code blob:&lt;uuid&gt;} where {@code &lt;uuid&gt;} is an UUID of a Blob.
     */
    public static final class StreamHandle implements CdpValue<String> {
        public final String value;
        public StreamHandle(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof StreamHandle)) return false;
            return value.equals(((StreamHandle) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "StreamHandle(" + value + ")"; }
    }
    /**
     * Close the stream, discard any temporary backing storage.
     */
    public static final class CloseRequest extends CdpObject {
        public CloseRequest() {}
        /**
         * Close the stream, discard any temporary backing storage.
         * @param handle protocol value
         */
        public CloseRequest(IO.StreamHandle handle) {
            set("handle", handle);
        }
        public static CloseRequest fromMap(Map<String, Object> values) {
            CloseRequest instance_ = new CloseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Handle of the stream to close.
         * @return the protocol field value
         */
        public IO.StreamHandle handle() {
            return new IO.StreamHandle((String) require("handle"));
        }
        /**
         * Handle of the stream to close.
         * @param handle field value
         * @return this model
         */
        public CloseRequest handle(IO.StreamHandle handle) {
            set("handle", handle);
            return this;
        }
    }
    /**
     * Read a chunk of the stream
     */
    public static final class ReadRequest extends CdpObject {
        public ReadRequest() {}
        /**
         * Read a chunk of the stream
         * @param handle protocol value
         */
        public ReadRequest(IO.StreamHandle handle) {
            set("handle", handle);
        }
        public static ReadRequest fromMap(Map<String, Object> values) {
            ReadRequest instance_ = new ReadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Handle of the stream to read.
         * @return the protocol field value
         */
        public IO.StreamHandle handle() {
            return new IO.StreamHandle((String) require("handle"));
        }
        /**
         * Seek to the specified offset before reading (if not specified, proceed with offset following the last read). Some types of streams may only support sequential reads.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong offset() {
            Long value = CdpObject.numberAsLong(raw("offset"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Maximum number of bytes to read (left upon the agent discretion if not specified).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong size() {
            Long value = CdpObject.numberAsLong(raw("size"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Handle of the stream to read.
         * @param handle field value
         * @return this model
         */
        public ReadRequest handle(IO.StreamHandle handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Seek to the specified offset before reading (if not specified, proceed with offset following the last read). Some types of streams may only support sequential reads.
         * @param offset field value; empty omits the value
         * @return this model
         */
        public ReadRequest offset(OptionalLong offset) {
            set("offset", offset.isPresent() ? offset.getAsLong() : null);
            return this;
        }
        /**
         * Seek to the specified offset before reading (if not specified, proceed with offset following the last read). Some types of streams may only support sequential reads.
         * @param offset field value; null removes the value
         * @return this model
         */
        public ReadRequest offset(Long offset) {
            set("offset", offset);
            return this;
        }
        /**
         * Maximum number of bytes to read (left upon the agent discretion if not specified).
         * @param size field value; empty omits the value
         * @return this model
         */
        public ReadRequest size(OptionalLong size) {
            set("size", size.isPresent() ? size.getAsLong() : null);
            return this;
        }
        /**
         * Maximum number of bytes to read (left upon the agent discretion if not specified).
         * @param size field value; null removes the value
         * @return this model
         */
        public ReadRequest size(Long size) {
            set("size", size);
            return this;
        }
    }
    /**
     * Return UUID of Blob object specified by a remote object id.
     */
    public static final class ResolveBlobRequest extends CdpObject {
        public ResolveBlobRequest() {}
        /**
         * Return UUID of Blob object specified by a remote object id.
         * @param objectId protocol value
         */
        public ResolveBlobRequest(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
        }
        public static ResolveBlobRequest fromMap(Map<String, Object> values) {
            ResolveBlobRequest instance_ = new ResolveBlobRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Object id of a Blob object wrapper.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * Object id of a Blob object wrapper.
         * @param objectId field value
         * @return this model
         */
        public ResolveBlobRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Read a chunk of the stream
     */
    public static final class ReadResult extends CdpObject {
        public ReadResult() {}
        private ReadResult(Map<String, Object> values) { super(values); }
        public static ReadResult fromMap(Map<String, Object> values) {
            return new ReadResult(values);
        }
        /**
         * Set if the data is base64-encoded
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> base64Encoded() {
            return Optional.ofNullable((Boolean) raw("base64Encoded"));
        }
        /**
         * Data that were read.
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Set if the end-of-file condition occurred while reading.
         * @return the protocol field value
         */
        public boolean eof() {
            return (Boolean) require("eof");
        }
        /**
         * Set if the data is base64-encoded
         * @param base64Encoded field value; empty omits the value
         * @return this model
         */
        public ReadResult base64Encoded(Optional<Boolean> base64Encoded) {
            set("base64Encoded", base64Encoded.orElse(null));
            return this;
        }
        /**
         * Set if the data is base64-encoded
         * @param base64Encoded field value; null removes the value
         * @return this model
         */
        public ReadResult base64Encoded(Boolean base64Encoded) {
            set("base64Encoded", base64Encoded);
            return this;
        }
        /**
         * Data that were read.
         * @param data field value
         * @return this model
         */
        public ReadResult data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Set if the end-of-file condition occurred while reading.
         * @param eof field value
         * @return this model
         */
        public ReadResult eof(boolean eof) {
            set("eof", eof);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Close the stream, discard any temporary backing storage.
         * @param handle protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close(IO.StreamHandle handle) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("handle", CdpObject.json(handle));
            return client.call("IO.close", params, result_ -> null);
        }
        /**
         * Close the stream, discard any temporary backing storage.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close(CloseRequest request) {
            return client.call("IO.close", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Read a chunk of the stream
         * @param handle protocol value
         * @param offset protocol value
         * @param size protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(IO.StreamHandle handle, OptionalLong offset, OptionalLong size) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("handle", CdpObject.json(handle));
            offset.ifPresent(value_ -> params.put("offset", value_));
            size.ifPresent(value_ -> params.put("size", value_));
            return client.call("IO.read", params, result_ -> new ReadResult(result_));
        }
        /**
         * Read a chunk of the stream
         * @param handle protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(IO.StreamHandle handle) {
            return read(handle, OptionalLong.empty(), OptionalLong.empty());
        }
        /**
         * Read a chunk of the stream
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(ReadRequest request) {
            return client.call("IO.read", request == null ? null : request.toMap(), result_ -> new ReadResult(result_));
        }
        /**
         * Return UUID of Blob object specified by a remote object id.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> resolveBlob(Runtime.RemoteObjectId objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("IO.resolveBlob", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("uuid")));
        }
        /**
         * Return UUID of Blob object specified by a remote object id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> resolveBlob(ResolveBlobRequest request) {
            return client.call("IO.resolveBlob", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("uuid")));
        }
    }
}
