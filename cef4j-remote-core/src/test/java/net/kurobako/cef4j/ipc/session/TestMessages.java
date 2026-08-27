package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Nonnull;

final class TestMessages {

    private TestMessages() {}

    static final class BytesView implements CefMessageView {
        final int messageId;
        final byte[] bytes;

        BytesView(int messageId, byte[] bytes) {
            this.messageId = messageId;
            this.bytes = bytes;
        }

        @Override
        public int messageId() {
            return messageId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BytesView)) return false;
            BytesView that = (BytesView) o;
            return messageId == that.messageId && Arrays.equals(bytes, that.bytes);
        }

        @Override
        public int hashCode() {
            return 31 * messageId + Arrays.hashCode(bytes);
        }

        @Override
        public String toString() {
            return "BytesView(msgId=" + messageId + ", bytes=" + Arrays.toString(bytes) + ")";
        }
    }

    static final class BytesEncoder implements CefMessageEncoder {
        private final int messageId;
        private final byte[] bytes;

        BytesEncoder(int messageId, byte[] bytes) {
            this.messageId = messageId;
            this.bytes = bytes;
        }

        @Override
        public int messageId() {
            return messageId;
        }

        @Override
        public int encodedSize() {
            return bytes.length;
        }

        @Override
        public void encodeInto(@Nonnull ByteBuffer dst) {
            dst.put(bytes);
        }
    }

    static CefMessageDecoder<BytesView> bytesDecoder(int messageId) {
        return payload -> {
            byte[] copy = new byte[payload.remaining()];
            payload.get(copy);
            return new BytesView(messageId, copy);
        };
    }
}
