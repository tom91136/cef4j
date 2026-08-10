package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;

/**
 * Codec for the 14-byte fixed envelope that prefixes every IPC frame.
 *
 * <p>Layout (little-endian, regardless of the caller's {@link ByteBuffer#order}):
 *
 * <pre>
 *   +0   4  payloadLength
 *   +4   1  kind
 *   +5   1  flags
 *   +6   4  corrId       (-1 if none)
 *   +10  4  messageId
 *   +14  N  payload
 * </pre>
 *
 * @see <a href="../../../../../../cef4j-ipc-architecture.md">cef4j-ipc-architecture.md, Decision 3</a>
 */
public final class Envelope {

    /** Fixed header byte count. */
    public static final int HEADER_SIZE = 14;

    /** corrId sentinel for messages that do not correlate (e.g. EVENT). */
    public static final int NO_CORR_ID = -1;

    public static final int FLAG_HAS_CONTINUATION = 0x01;
    public static final int FLAG_IS_CONTINUATION = 0x02;

    /** Logical kind of the message; the first byte after {@code payloadLength}. */
    public enum Kind {
        REQUEST((byte) 1),
        RESPONSE((byte) 2),
        EVENT((byte) 3),
        INTERCEPT((byte) 4),
        INTERCEPT_RESPONSE((byte) 5),
        ERROR((byte) 6);

        final byte code;

        Kind(byte code) {
            this.code = code;
        }

        static Kind of(byte b) {
            for (Kind k : values()) if (k.code == b) return k;
            throw new IllegalArgumentException("unknown envelope kind: " + (b & 0xff));
        }
    }

    /** Decoded header. */
    public static final class Header {
        public final int payloadLength;
        public final Kind kind;
        public final int flags;
        public final int corrId;
        public final int messageId;

        public Header(int payloadLength, Kind kind, int flags, int corrId, int messageId) {
            this.payloadLength = payloadLength;
            this.kind = kind;
            this.flags = flags;
            this.corrId = corrId;
            this.messageId = messageId;
        }
    }

    private Envelope() {}

    /**
     * Write a header into {@code dst} starting at its current position. Advances position by {@link #HEADER_SIZE}. The
     * buffer's byte order is preserved on return; bytes are always written in little-endian regardless of the caller's
     * setting.
     */
    public static void writeHeader(
            @Nonnull ByteBuffer dst, @Nonnull Kind kind, int flags, int corrId, int messageId, int payloadLength) {
        if (payloadLength < 0) throw new IllegalArgumentException("payloadLength must be >= 0, got " + payloadLength);
        if ((flags & ~0xFF) != 0)
            throw new IllegalArgumentException("flags must fit in a byte, got 0x" + Integer.toHexString(flags));
        if (dst.remaining() < HEADER_SIZE) {
            throw new IllegalArgumentException(
                    "buffer has " + dst.remaining() + " bytes remaining, need " + HEADER_SIZE);
        }
        ByteOrder originalOrder = dst.order();
        try {
            dst.order(ByteOrder.LITTLE_ENDIAN);
            dst.putInt(payloadLength);
            dst.put(kind.code);
            dst.put((byte) (flags & 0xFF));
            dst.putInt(corrId);
            dst.putInt(messageId);
        } finally {
            dst.order(originalOrder);
        }
    }

    /**
     * Read a header from {@code src} starting at its current position. Advances position by {@link #HEADER_SIZE}. The
     * buffer's byte order is preserved on return.
     */
    @Nonnull
    public static Header readHeader(@Nonnull ByteBuffer src) {
        if (src.remaining() < HEADER_SIZE) {
            throw new IllegalArgumentException(
                    "buffer has " + src.remaining() + " bytes remaining, need " + HEADER_SIZE);
        }
        ByteOrder originalOrder = src.order();
        try {
            src.order(ByteOrder.LITTLE_ENDIAN);
            int len = src.getInt();
            byte kindByte = src.get();
            int flags = src.get() & 0xFF;
            int corrId = src.getInt();
            int messageId = src.getInt();
            Kind kind = Kind.of(kindByte);
            return new Header(len, kind, flags, corrId, messageId);
        } finally {
            src.order(originalOrder);
        }
    }
}
