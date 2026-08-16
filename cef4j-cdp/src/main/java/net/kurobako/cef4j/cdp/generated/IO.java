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
 * Input/Output operations for streams produced by DevTools.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/IO.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class IO {
    private IO() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Close the stream, discard any temporary backing storage.
     */
    public static final class CloseParams extends CdpObject {
        private CloseParams(Map<String, Object> values) { super(values); }
        @Nullable public static CloseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Handle of the stream to close.
         * @return the protocol field value
         */
        @Nullable public String handle() {
            return (String) value("handle");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Handle of the stream to close.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable String value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            public CloseParams build() {
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                return new CloseParams(values);
            }
        }
    }
    /**
     * Close the stream, discard any temporary backing storage.
     */
    public static final class CloseResult extends CdpObject {
        private CloseResult(Map<String, Object> values) { super(values); }
        @Nullable public static CloseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CloseResult build() {
                return new CloseResult(values);
            }
        }
    }
    /**
     * Read a chunk of the stream
     */
    public static final class ReadParams extends CdpObject {
        private ReadParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Handle of the stream to read.
         * @return the protocol field value
         */
        @Nullable public String handle() {
            return (String) value("handle");
        }
        /**
         * Seek to the specified offset before reading (if not specified, proceed with offset following the last read). Some types of streams may only support sequential reads.
         * @return the protocol field value
         */
        @Nullable public Long offset() {
            return numberAsLong(value("offset"));
        }
        /**
         * Maximum number of bytes to read (left upon the agent discretion if not specified).
         * @return the protocol field value
         */
        @Nullable public Long size() {
            return numberAsLong(value("size"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Handle of the stream to read.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable String value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Seek to the specified offset before reading (if not specified, proceed with offset following the last read). Some types of streams may only support sequential reads.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offset(@Nullable Long value) {
                if (value == null) values.remove("offset");
                else values.put("offset", jsonValue(value));
                return this;
            }
            /**
             * Maximum number of bytes to read (left upon the agent discretion if not specified).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Long value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            public ReadParams build() {
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                return new ReadParams(values);
            }
        }
    }
    /**
     * Read a chunk of the stream
     */
    public static final class ReadResult extends CdpObject {
        private ReadResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Set if the data is base64-encoded
         * @return the protocol field value
         */
        @Nullable public Boolean base64Encoded() {
            return (Boolean) value("base64Encoded");
        }
        /**
         * Data that were read.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Set if the end-of-file condition occurred while reading.
         * @return the protocol field value
         */
        @Nullable public Boolean eof() {
            return (Boolean) value("eof");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Set if the data is base64-encoded
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder base64Encoded(@Nullable Boolean value) {
                if (value == null) values.remove("base64Encoded");
                else values.put("base64Encoded", jsonValue(value));
                return this;
            }
            /**
             * Data that were read.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Set if the end-of-file condition occurred while reading.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eof(@Nullable Boolean value) {
                if (value == null) values.remove("eof");
                else values.put("eof", jsonValue(value));
                return this;
            }
            public ReadResult build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                if (!values.containsKey("eof")) throw new IllegalStateException("Missing required CDP field: eof");
                return new ReadResult(values);
            }
        }
    }
    /**
     * Return UUID of Blob object specified by a remote object id.
     */
    public static final class ResolveBlobParams extends CdpObject {
        private ResolveBlobParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveBlobParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveBlobParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object id of a Blob object wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object id of a Blob object wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public ResolveBlobParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new ResolveBlobParams(values);
            }
        }
    }
    /**
     * Return UUID of Blob object specified by a remote object id.
     */
    public static final class ResolveBlobResult extends CdpObject {
        private ResolveBlobResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveBlobResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveBlobResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * UUID of the specified Blob.
         * @return the protocol field value
         */
        @Nullable public String uuid() {
            return (String) value("uuid");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * UUID of the specified Blob.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uuid(@Nullable String value) {
                if (value == null) values.remove("uuid");
                else values.put("uuid", jsonValue(value));
                return this;
            }
            public ResolveBlobResult build() {
                if (!values.containsKey("uuid")) throw new IllegalStateException("Missing required CDP field: uuid");
                return new ResolveBlobResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Close the stream, discard any temporary backing storage.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CloseResult> close(CloseParams params) {
            return client.call("IO.close", params, CloseResult::fromMap);
        }
        /**
         * Read a chunk of the stream
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReadResult> read(ReadParams params) {
            return client.call("IO.read", params, ReadResult::fromMap);
        }
        /**
         * Return UUID of Blob object specified by a remote object id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResolveBlobResult> resolveBlob(ResolveBlobParams params) {
            return client.call("IO.resolveBlob", params, ResolveBlobResult::fromMap);
        }
    }
}
