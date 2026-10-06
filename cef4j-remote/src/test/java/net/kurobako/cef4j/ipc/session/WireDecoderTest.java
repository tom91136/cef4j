package net.kurobako.cef4j.ipc.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;

class WireDecoderTest {
    @Test
    void acceptsBoundedLengthAndAdvancesPastPrefix() {
        ByteBuffer source = ByteBuffer.allocate(7).order(ByteOrder.LITTLE_ENDIAN);
        source.putInt(3).put(new byte[] {1, 2, 3}).flip();

        assertThat(WireDecoder.length(source, "payload")).isEqualTo(3);
        assertThat(source.position()).isEqualTo(Integer.BYTES);
    }

    @Test
    void rejectsNegativeTruncatedAndOversizedLengthsBeforeAllocation() {
        assertThatThrownBy(() -> WireDecoder.length(intBuffer(-1), "payload"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> WireDecoder.length(intBuffer(1), "payload"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> WireDecoder.length(intBuffer(WireDecoder.MAX_FIELD_BYTES + 1), "payload"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsInfeasibleAndOversizedCollectionCounts() {
        assertThatThrownBy(() -> WireDecoder.count(intBuffer(1), "items")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> WireDecoder.count(intBuffer(WireDecoder.MAX_COLLECTION_ITEMS + 1), "items"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void exactPayloadCheckRejectsTrailingBytes() {
        assertThatThrownBy(() -> WireDecoder.requireFullyConsumed(ByteBuffer.wrap(new byte[] {1}), "Message"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("trailing");
    }

    private static ByteBuffer intBuffer(int value) {
        return ByteBuffer.allocate(Integer.BYTES)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putInt(value)
                .flip();
    }
}
