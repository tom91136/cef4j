package net.kurobako.cef4j.ipc.session.middleware;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;

/** The payload of a message, which the encoders of the sessions write. */
final class MessagePayloads {

    private MessagePayloads() {}

    /** The bytes of the message, which the encoder has to write in full. */
    static byte[] encode(CefMessageEncoder encoder) {
        final ByteBuffer payload = ByteBuffer.allocate(encoder.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        encoder.encodeInto(payload);
        if (payload.position() != encoder.encodedSize()) {
            throw new IllegalArgumentException("encoder for message " + encoder.messageId() + " wrote "
                    + payload.position() + " bytes, expected " + encoder.encodedSize());
        }
        return payload.array();
    }
}
