package net.kurobako.cef4j.ipc.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class EnvelopeTest {

    @Test
    void headerSizeIsFourteen() {
        assertThat(Envelope.HEADER_SIZE).isEqualTo(14);
    }

    @Test
    void writeThenReadRoundTrips() {
        byte[] payload = "hello".getBytes(StandardCharsets.UTF_8);
        ByteBuffer buf =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + payload.length).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(buf, Envelope.Kind.REQUEST, 0, 7, 42, payload.length);
        buf.put(payload);
        buf.flip();

        Envelope.Header h = Envelope.readHeader(buf);
        assertThat(h.kind).isEqualTo(Envelope.Kind.REQUEST);
        assertThat(h.flags).isEqualTo(0);
        assertThat(h.corrId).isEqualTo(7);
        assertThat(h.messageId).isEqualTo(42);
        assertThat(h.payloadLength).isEqualTo(payload.length);

        byte[] got = new byte[buf.remaining()];
        buf.get(got);
        assertThat(got).isEqualTo(payload);
    }

    @Test
    void allKindsRoundTrip() {
        for (Envelope.Kind k : Envelope.Kind.values()) {
            ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(buf, k, 0xFF, -1, 99, 0);
            buf.flip();
            Envelope.Header h = Envelope.readHeader(buf);
            assertThat(h.kind).isEqualTo(k);
            assertThat(h.flags).isEqualTo(0xFF);
            assertThat(h.corrId).isEqualTo(-1);
            assertThat(h.messageId).isEqualTo(99);
            assertThat(h.payloadLength).isEqualTo(0);
        }
    }

    @Test
    void byteOrderIsLittleEndianRegardlessOfBufferOrder() {
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.BIG_ENDIAN);
        Envelope.writeHeader(buf, Envelope.Kind.EVENT, 0, -1, 0x01020304, 0);
        buf.flip();
        assertThat(buf.get(10)).isEqualTo((byte) 0x04);
        assertThat(buf.get(11)).isEqualTo((byte) 0x03);
        assertThat(buf.get(12)).isEqualTo((byte) 0x02);
        assertThat(buf.get(13)).isEqualTo((byte) 0x01);
    }

    @Test
    void writeRejectsNegativePayloadLength() {
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        assertThatThrownBy(() -> Envelope.writeHeader(buf, Envelope.Kind.REQUEST, 0, 1, 2, -5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void readRejectsUnknownKind() {
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        buf.putInt(0);
        buf.put((byte) 99);
        buf.put((byte) 0);
        buf.putInt(0);
        buf.putInt(0);
        buf.flip();
        assertThatThrownBy(() -> Envelope.readHeader(buf)).hasMessageContaining("kind");
    }

    @Test
    void readRejectsTruncatedHeader() {
        ByteBuffer buf = ByteBuffer.allocate(7);
        assertThatThrownBy(() -> Envelope.readHeader(buf)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void readRejectsNegativePayloadLength() {
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        buf.putInt(-1);
        buf.put((byte) 1);
        buf.put((byte) 0);
        buf.putInt(1);
        buf.putInt(2);
        buf.flip();
        assertThatThrownBy(() -> Envelope.readHeader(buf)).hasMessageContaining("payloadLength");
    }

    @Test
    void readRejectsPayloadLengthDifferentFromRemainingFrame() {
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE + 3).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(buf, Envelope.Kind.RESPONSE, 0, 1, 2, 2);
        buf.put(new byte[] {1, 2, 3}).flip();
        assertThatThrownBy(() -> Envelope.readHeader(buf)).hasMessageContaining("remaining");
    }
}
