package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
        final int value = source.getInt();
        if (value < 0 || value > MAX_FIELD_BYTES || value > source.remaining()) {
            throw new IllegalArgumentException("invalid length " + value + " for field " + field);
        }
        return value;
    }

    public static int count(ByteBuffer source, String field) {
        requireRemaining(source, Integer.BYTES, field);
        final int value = source.getInt();
        if (value < 0 || value > MAX_COLLECTION_ITEMS || value > source.remaining() / Integer.BYTES) {
            throw new IllegalArgumentException("invalid count " + value + " for field " + field);
        }
        return value;
    }

    public static int int32(ByteBuffer source, String field) {
        requireRemaining(source, Integer.BYTES, field);
        return source.getInt();
    }

    public static long int64(ByteBuffer source, String field) {
        requireRemaining(source, Long.BYTES, field);
        return source.getLong();
    }

    public static boolean bool(ByteBuffer source, String field) {
        requireRemaining(source, 1, field);
        return source.get() != 0;
    }

    public static RemoteHandle handle(ByteBuffer source, String field) {
        return new RemoteHandle(int32(source, field));
    }

    public static byte[] bytes(ByteBuffer source, String field) {
        final byte[] value = new byte[length(source, field)];
        source.get(value);
        return value;
    }

    public static String utf8(ByteBuffer source, String field) {
        return new String(bytes(source, field), StandardCharsets.UTF_8);
    }

    public static String[] utf8List(ByteBuffer source, String field) {
        final String[] values = new String[count(source, field)];
        for (int i = 0; i < values.length; i++) {
            values[i] = utf8(source, field + "[" + i + "]");
        }
        return values;
    }

    public static void requireFullyConsumed(ByteBuffer source, String type) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(type, "type");
        if (source.hasRemaining()) {
            throw new IllegalArgumentException("trailing bytes in " + type + " payload");
        }
    }
}
