package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MessageLogTest {

    @Test
    void writerThenReaderRoundTrip(@TempDir Path tmp) throws IOException {
        Path file = tmp.resolve("log.bin");
        try (MessageLog.Writer w = MessageLog.writer(file)) {
            w.append(MessageLog.Direction.OUTBOUND, 100L, new byte[] {1, 2, 3});
            w.append(MessageLog.Direction.INBOUND, 200L, new byte[] {4, 5});
            w.append(MessageLog.Direction.OUTBOUND, 300L, new byte[] {});
        }

        List<MessageLog.Entry> entries = new ArrayList<>();
        try (MessageLog.Reader r = MessageLog.reader(file)) {
            MessageLog.Entry e;
            while ((e = r.next().orElse(null)) != null) entries.add(e);
        }
        assertThat(entries).hasSize(3);
        assertThat(entries.get(0).direction).isEqualTo(MessageLog.Direction.OUTBOUND);
        assertThat(entries.get(0).timestampNanos).isEqualTo(100L);
        assertThat(entries.get(0).payload).containsExactly(1, 2, 3);
        assertThat(entries.get(1).direction).isEqualTo(MessageLog.Direction.INBOUND);
        assertThat(entries.get(1).payload).containsExactly(4, 5);
        assertThat(entries.get(2).payload).isEmpty();
    }

    @Test
    void readerRejectsBadMagic(@TempDir Path tmp) throws IOException {
        Path file = tmp.resolve("bad.bin");
        Files.write(file, new byte[] {'B', 'A', 'D', 'M', 'A', 'G', 'I', 'C', 0, 0, 0, 1, 0, 0, 0, 0});
        assertThatIOException().isThrownBy(() -> MessageLog.reader(file)).withMessageContaining("bad magic");
    }

    @Test
    void readerRejectsUnknownVersion(@TempDir Path tmp) throws IOException {
        Path file = tmp.resolve("bad.bin");
        byte[] bytes = new byte[16];
        System.arraycopy(MessageLog.MAGIC, 0, bytes, 0, MessageLog.MAGIC.length);
        // version = 99 in big-endian
        bytes[8] = 0;
        bytes[9] = 0;
        bytes[10] = 0;
        bytes[11] = 99;
        Files.write(file, bytes);
        assertThatIOException().isThrownBy(() -> MessageLog.reader(file)).withMessageContaining("version");
    }

    @Test
    void writerIsThreadSafe(@TempDir Path tmp) throws Exception {
        Path file = tmp.resolve("concurrent.bin");
        int threads = 4;
        int per = 100;
        try (MessageLog.Writer w = MessageLog.writer(file)) {
            List<Thread> ts = new ArrayList<>();
            for (int t = 0; t < threads; t++) {
                final int tid = t;
                Thread th = new Thread(() -> {
                    for (int i = 0; i < per; i++) {
                        try {
                            w.append(MessageLog.Direction.OUTBOUND, tid * 1000L + i, new byte[] {(byte) tid, (byte) i});
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                ts.add(th);
                th.start();
            }
            for (Thread th : ts) th.join();
        }
        int count = 0;
        try (MessageLog.Reader r = MessageLog.reader(file)) {
            while (r.next().isPresent()) count++;
        }
        assertThat(count).isEqualTo(threads * per);
    }
}
