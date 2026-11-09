package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public final class WireEncoder {

    private WireEncoder() {}

    /** Runs {@code body} with the buffer in little-endian order and restores the original order afterwards. */
    public static void littleEndian(ByteBuffer dst, Consumer<ByteBuffer> body) {
        final ByteOrder originalOrder = dst.order();
        try {
            dst.order(ByteOrder.LITTLE_ENDIAN);
            body.accept(dst);
        } finally {
            dst.order(originalOrder);
        }
    }

    public static void bool(ByteBuffer dst, boolean value) {
        dst.put((byte) (value ? 1 : 0));
    }

    public static void bytes(ByteBuffer dst, byte[] value) {
        dst.putInt(value.length);
        dst.put(value);
    }

    public static void utf8List(ByteBuffer dst, String[] values) {
        dst.putInt(values.length);
        for (String value : values) {
            bytes(dst, value.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static int utf8ListSize(String[] values) {
        int size = 0;
        for (String value : values) {
            size += Integer.BYTES + value.getBytes(StandardCharsets.UTF_8).length;
        }
        return size;
    }
}
