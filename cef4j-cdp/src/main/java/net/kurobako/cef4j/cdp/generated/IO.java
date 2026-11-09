// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Input/Output operations for streams produced by DevTools.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/IO.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class IO {
    private IO() {}
    private static final CdpField<IO.StreamHandle> HANDLE = CdpField.of("handle", value -> new IO.StreamHandle((String) value));
    private static final CdpField<Runtime.RemoteObjectId> OBJECT_ID = CdpField.of("objectId", value -> new Runtime.RemoteObjectId((String) value));
    private static final CdpField<String> UUID = CdpField.of("uuid", value -> (String) value);
    /**
     * This is either obtained from another method or specified as {@code blob:&lt;uuid&gt;} where {@code &lt;uuid&gt;} is an UUID of a Blob.
     */
    public static final class StreamHandle implements CdpValue<String> {
        public final String value;
        public StreamHandle(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
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
        public static CloseRequest fromMap(Map<String, Object> values) {
            final CloseRequest instance_ = new CloseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Handle of the stream to close.
         * @return the protocol field value
         */
        public IO.StreamHandle handle() {
            return require(HANDLE);
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
        public static ReadRequest fromMap(Map<String, Object> values) {
            final ReadRequest instance_ = new ReadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Handle of the stream to read.
         * @return the protocol field value
         */
        public IO.StreamHandle handle() {
            return require(HANDLE);
        }
        /**
         * Seek to the specified offset before reading (if not specified, proceed with offset following the last read). Some types of streams may only support sequential reads.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong offset() {
            return optionalLong("offset");
        }
        /**
         * Maximum number of bytes to read (left upon the agent discretion if not specified).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong size() {
            return optionalLong("size");
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
         * @param offset field value
         * @return this model
         */
        public ReadRequest offset(long offset) {
            set("offset", offset);
            return this;
        }
        /**
         * Maximum number of bytes to read (left upon the agent discretion if not specified).
         * @param size field value
         * @return this model
         */
        public ReadRequest size(long size) {
            set("size", size);
            return this;
        }
    }
    /**
     * Return UUID of Blob object specified by a remote object id.
     */
    public static final class ResolveBlobRequest extends CdpObject {
        public ResolveBlobRequest() {}
        public static ResolveBlobRequest fromMap(Map<String, Object> values) {
            final ResolveBlobRequest instance_ = new ResolveBlobRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Object id of a Blob object wrapper.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return require(OBJECT_ID);
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
            return optionalBoolean("base64Encoded");
        }
        /**
         * Data that were read.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Set if the end-of-file condition occurred while reading.
         * @return the protocol field value
         */
        public boolean eof() {
            return requireBoolean("eof");
        }
        /**
         * Set if the data is base64-encoded
         * @param base64Encoded field value
         * @return this model
         */
        public ReadResult base64Encoded(boolean base64Encoded) {
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Close the stream, discard any temporary backing storage.
         * @param handle protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close(IO.StreamHandle handle) {
            return call("IO.close", params("handle", handle));
        }
        /**
         * Close the stream, discard any temporary backing storage.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> close(CloseRequest request) {
            return call("IO.close", request);
        }
        /**
         * Read a chunk of the stream
         * @param handle protocol value
         * @param offset protocol value
         * @param size protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(IO.StreamHandle handle, @Nullable Long offset, @Nullable Long size) {
            return call("IO.read", params("handle", handle, "offset", offset, "size", size), ReadResult::fromMap);
        }
        /**
         * Read a chunk of the stream
         * @param handle protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(IO.StreamHandle handle) {
            return call("IO.read", params("handle", handle), ReadResult::fromMap);
        }
        /**
         * Read a chunk of the stream
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(ReadRequest request) {
            return call("IO.read", request, ReadResult::fromMap);
        }
        /**
         * Return UUID of Blob object specified by a remote object id.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> resolveBlob(Runtime.RemoteObjectId objectId) {
            return call("IO.resolveBlob", params("objectId", objectId), UUID::require);
        }
        /**
         * Return UUID of Blob object specified by a remote object id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> resolveBlob(ResolveBlobRequest request) {
            return call("IO.resolveBlob", request, UUID::require);
        }
    }
}
