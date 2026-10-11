package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.util.Objects;

public final class WireDecoder {
    public static final int MAX_FIELD_BYTES = 64 * 1024 * 1024;
    public static final int MAX_COLLECTION_ITEMS = 1_000_000;

    private WireDecoder() {}

    public static void requireRemaining(ByteBuffer source, int count, String field) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(field, "field");
        if (count < 0 || count > source.remaining()) {
            throw new IllegalArgumentException("truncated field " + field);
        }
    }

    public static int length(ByteBuffer source, String field) {
        requireRemaining(source, Integer.BYTES, field);
        int value = source.getInt();
        if (value < 0 || value > MAX_FIELD_BYTES || value > source.remaining()) {
            throw new IllegalArgumentException("invalid length " + value + " for field " + field);
        }
        return value;
    }

    public static int count(ByteBuffer source, String field) {
        requireRemaining(source, Integer.BYTES, field);
        int value = source.getInt();
        if (value < 0 || value > MAX_COLLECTION_ITEMS || value > source.remaining() / Integer.BYTES) {
            throw new IllegalArgumentException("invalid count " + value + " for field " + field);
        }
        return value;
    }

    public static void requireFullyConsumed(ByteBuffer source, String type) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(type, "type");
        if (source.hasRemaining()) {
            throw new IllegalArgumentException("trailing bytes in " + type + " payload");
        }
    }
}
