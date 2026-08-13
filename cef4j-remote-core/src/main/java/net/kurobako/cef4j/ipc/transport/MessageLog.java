package net.kurobako.cef4j.ipc.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * On-disk recording format used by {@link RecordingTransport} / {@link ReplayTransport}.
 *
 * <h2>Format</h2>
 *
 * Big-endian framing (Java native), payload bytes are opaque.
 *
 * <pre>
 * file header (16 bytes):
 *   +0   8 bytes  magic "CEF4JLOG"
 *   +8   4 bytes  version (currently 1)
 *   +12  4 bytes  flags (reserved, 0)
 *
 * entry (13 + payload bytes, repeated):
 *   +0   1 byte   direction (0 = OUTBOUND, 1 = INBOUND)
 *   +1   8 bytes  timestamp_nanos (System.nanoTime at record time)
 *   +9   4 bytes  payload length
 *   +13  N bytes  payload
 * </pre>
 */
public final class MessageLog {

    static final byte[] MAGIC = {'C', 'E', 'F', '4', 'J', 'L', 'O', 'G'};
    static final int VERSION = 1;

    /** Direction of a recorded frame, from the recording side's perspective. */
    public enum Direction {
        OUTBOUND((byte) 0),
        INBOUND((byte) 1);

        final byte code;

        Direction(byte code) {
            this.code = code;
        }

        static Direction of(byte b) {
            if (b == 0) return OUTBOUND;
            if (b == 1) return INBOUND;
            throw new IllegalArgumentException("unknown direction: " + b);
        }
    }

    /** A single recorded frame. */
    public static final class Entry {
        public final Direction direction;
        public final long timestampNanos;
        public final byte[] payload;

        public Entry(Direction direction, long timestampNanos, byte[] payload) {
            this.direction = direction;
            this.timestampNanos = timestampNanos;
            this.payload = payload;
        }
    }

    private MessageLog() {}

    /** Open a log for writing. Truncates if the file exists. */
    public static Writer writer(@Nonnull Path file) throws IOException {
        OutputStream raw = Files.newOutputStream(
                file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(raw));
        out.write(MAGIC);
        out.writeInt(VERSION);
        out.writeInt(0); // flags
        return new Writer(out);
    }

    /** Open a log for reading. */
    public static Reader reader(@Nonnull Path file) throws IOException {
        InputStream raw = Files.newInputStream(file, StandardOpenOption.READ);
        DataInputStream in = new DataInputStream(new BufferedInputStream(raw));
        byte[] magic = new byte[MAGIC.length];
        in.readFully(magic);
        for (int i = 0; i < MAGIC.length; i++) {
            if (magic[i] != MAGIC[i]) throw new IOException("bad magic in " + file);
        }
        int version = in.readInt();
        if (version != VERSION) throw new IOException("unsupported MessageLog version " + version);
        int flags = in.readInt();
        if (flags != 0) throw new IOException("unsupported MessageLog flags 0x" + Integer.toHexString(flags));
        return new Reader(in);
    }

    /** Append-only writer. Thread-safe via internal synchronisation. */
    public static final class Writer implements Closeable {
        private final DataOutputStream out;
        private final Object lock = new Object();
        private boolean closed = false;

        Writer(DataOutputStream out) {
            this.out = out;
        }

        public void append(@Nonnull Direction direction, long timestampNanos, @Nonnull byte[] payload)
                throws IOException {
            synchronized (lock) {
                if (closed) throw new IOException("MessageLog.Writer closed");
                out.writeByte(direction.code);
                out.writeLong(timestampNanos);
                out.writeInt(payload.length);
                out.write(payload);
            }
        }

        @Override
        public void close() throws IOException {
            synchronized (lock) {
                if (closed) return;
                closed = true;
                out.flush();
                out.close();
            }
        }
    }

    /** Streaming reader. Returns one entry per call to {@link #next}; {@code null} on EOF. */
    public static final class Reader implements Closeable {
        private final DataInputStream in;
        private boolean closed = false;

        Reader(DataInputStream in) {
            this.in = in;
        }

        @Nullable
        public Entry next() throws IOException {
            if (closed) return null;
            int dirByte;
            try {
                dirByte = in.read();
            } catch (EOFException eof) {
                return null;
            }
            if (dirByte < 0) return null;
            Direction dir = Direction.of((byte) dirByte);
            long ts = in.readLong();
            int len = in.readInt();
            if (len < 0) throw new IOException("negative payload length: " + len);
            byte[] payload = new byte[len];
            in.readFully(payload);
            return new Entry(dir, ts, payload);
        }

        @Override
        public void close() throws IOException {
            if (closed) return;
            closed = true;
            in.close();
        }
    }
}
