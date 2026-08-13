package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

/**
 * Contract that every {@link CefTransport} implementation must honour. Subclasses provide a connected pair via
 * {@link #newPair}; the rest of the assertions are shared.
 *
 * <p>Per-method @Timeout is generous because implementations perform real socket I/O and CI hosts can be heavily
 * loaded. {@code SEPARATE_THREAD} also lets JUnit stop a transport implementation that deadlocks during teardown.
 */
@Timeout(value = 60, threadMode = ThreadMode.SEPARATE_THREAD)
public abstract class CefTransportContractTest {

    /** Returns a freshly connected, isolated transport pair. Each test gets its own. */
    protected abstract Pair newPair() throws Exception;

    protected static class Pair implements AutoCloseable {
        final CefTransport a;
        final CefTransport b;

        protected Pair(CefTransport a, CefTransport b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void close() {
            a.close();
            b.close();
        }
    }

    private static ByteBuffer buf(String s) {
        return ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] drain(ByteBuffer b) {
        byte[] out = new byte[b.remaining()];
        b.get(out);
        return out;
    }

    @Test
    void roundTripsSingleFrame() throws Exception {
        try (Pair p = newPair()) {
            ConcurrentLinkedQueue<byte[]> received = new ConcurrentLinkedQueue<>();
            CountDownLatch arrived = new CountDownLatch(1);
            p.b.onReceive(frame -> {
                received.add(drain(frame));
                arrived.countDown();
            });
            p.a.send(buf("hello"));
            assertThat(arrived.await(30, TimeUnit.SECONDS)).isTrue();
            assertThat(received).containsExactly("hello".getBytes(StandardCharsets.UTF_8));
        }
    }

    @Test
    void bidirectionalTraffic() throws Exception {
        try (Pair p = newPair()) {
            CountDownLatch aGot = new CountDownLatch(1);
            CountDownLatch bGot = new CountDownLatch(1);
            ConcurrentLinkedQueue<byte[]> aRx = new ConcurrentLinkedQueue<>();
            ConcurrentLinkedQueue<byte[]> bRx = new ConcurrentLinkedQueue<>();
            p.a.onReceive(f -> {
                aRx.add(drain(f));
                aGot.countDown();
            });
            p.b.onReceive(f -> {
                bRx.add(drain(f));
                bGot.countDown();
            });
            p.a.send(buf("a->b"));
            p.b.send(buf("b->a"));
            assertThat(aGot.await(30, TimeUnit.SECONDS)).isTrue();
            assertThat(bGot.await(30, TimeUnit.SECONDS)).isTrue();
            assertThat(aRx).containsExactly("b->a".getBytes(StandardCharsets.UTF_8));
            assertThat(bRx).containsExactly("a->b".getBytes(StandardCharsets.UTF_8));
        }
    }

    @Test
    void largeMessageRoundTrips() throws Exception {
        byte[] payload = new byte[2 * 1024 * 1024]; // 2 MiB
        new Random(42).nextBytes(payload);
        try (Pair p = newPair()) {
            CountDownLatch arrived = new CountDownLatch(1);
            byte[][] received = new byte[1][];
            p.b.onReceive(f -> {
                received[0] = drain(f);
                arrived.countDown();
            });
            p.a.send(ByteBuffer.wrap(payload));
            assertThat(arrived.await(30, TimeUnit.SECONDS)).isTrue();
            assertThat(received[0]).isEqualTo(payload);
        }
    }

    @Test
    void manyMessagesPreserveOrder() throws Exception {
        int n = 1000;
        try (Pair p = newPair()) {
            ConcurrentLinkedQueue<Integer> received = new ConcurrentLinkedQueue<>();
            CountDownLatch done = new CountDownLatch(n);
            p.b.onReceive(f -> {
                ByteBuffer view = f.duplicate();
                received.add(view.getInt());
                done.countDown();
            });
            for (int i = 0; i < n; i++) {
                ByteBuffer m = ByteBuffer.allocate(4).putInt(i);
                m.flip();
                p.a.send(m);
            }
            assertThat(done.await(30, TimeUnit.SECONDS))
                    .as("all %d frames received", n)
                    .isTrue();
            List<Integer> got = new ArrayList<>(received);
            for (int i = 0; i < n; i++) {
                assertThat(got.get(i)).as("frame %d", i).isEqualTo(i);
            }
        }
    }

    @Test
    void framesArrivingBeforeReceiverIsRegisteredAreBuffered() throws Exception {
        try (Pair p = newPair()) {
            // A sends three frames before B registers a handler.
            p.a.send(buf("one"));
            p.a.send(buf("two"));
            p.a.send(buf("three"));
            // Give the receiver a moment to actually deliver them to its internal buffer.
            Thread.sleep(200);

            ConcurrentLinkedQueue<String> received = new ConcurrentLinkedQueue<>();
            CountDownLatch done = new CountDownLatch(3);
            p.b.onReceive(f -> {
                received.add(new String(drain(f), StandardCharsets.UTF_8));
                done.countDown();
            });
            assertThat(done.await(30, TimeUnit.SECONDS)).isTrue();
            assertThat(received).containsExactly("one", "two", "three");
        }
    }

    @Test
    void concurrentSendsAreSerialised() throws Exception {
        int threads = 8;
        int perThread = 200;
        try (Pair p = newPair()) {
            ConcurrentLinkedQueue<byte[]> received = new ConcurrentLinkedQueue<>();
            CountDownLatch done = new CountDownLatch(threads * perThread);
            p.b.onReceive(f -> {
                received.add(drain(f));
                done.countDown();
            });
            CountDownLatch start = new CountDownLatch(1);
            List<Thread> ts = new ArrayList<>();
            for (int t = 0; t < threads; t++) {
                final int tid = t;
                Thread th = new Thread(() -> {
                    try {
                        start.await();
                        for (int i = 0; i < perThread; i++) {
                            ByteBuffer m = ByteBuffer.allocate(8).putInt(tid).putInt(i);
                            m.flip();
                            p.a.send(m);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (CefTransportException e) {
                        throw new RuntimeException(e);
                    }
                });
                ts.add(th);
                th.start();
            }
            start.countDown();
            for (Thread th : ts) th.join();
            assertThat(done.await(30, TimeUnit.SECONDS)).isTrue();

            // Per-thread sequences must remain monotonic; cross-thread ordering is not specified.
            int[] lastSeq = new int[threads];
            for (int i = 0; i < threads; i++) lastSeq[i] = -1;
            for (byte[] frame : received) {
                ByteBuffer v = ByteBuffer.wrap(frame);
                int tid = v.getInt();
                int seq = v.getInt();
                assertThat(seq).as("thread %d seq monotonic", tid).isGreaterThan(lastSeq[tid]);
                lastSeq[tid] = seq;
            }
            for (int i = 0; i < threads; i++) {
                assertThat(lastSeq[i]).as("thread %d final seq", i).isEqualTo(perThread - 1);
            }
        }
    }

    @Test
    void closeIsIdempotent() throws Exception {
        Pair p = newPair();
        p.a.close();
        p.a.close();
        p.a.close();
        p.b.close();
    }

    @Test
    void sendAfterCloseThrows() throws Exception {
        Pair p = newPair();
        p.a.close();
        assertThatThrownBy(() -> p.a.send(buf("nope"))).isInstanceOf(CefTransportException.class);
        p.b.close();
    }

    @Test
    void localCloseDoesNotFireOwnDisconnect() throws Exception {
        try (Pair p = newPair()) {
            AtomicBoolean fired = new AtomicBoolean(false);
            p.a.onDisconnect(() -> fired.set(true));
            p.a.close();
            // Give any erroneous async fire a chance to race in.
            Thread.sleep(200);
            assertThat(fired)
                    .as("local close must not fire onDisconnect on the same transport")
                    .isFalse();
        }
    }

    @Test
    void remoteCloseFiresPeerDisconnect() throws Exception {
        try (Pair p = newPair()) {
            CountDownLatch peerSawDisconnect = new CountDownLatch(1);
            p.b.onDisconnect(peerSawDisconnect::countDown);
            p.a.close();
            assertThat(peerSawDisconnect.await(30, TimeUnit.SECONDS))
                    .as("peer should observe a remote disconnect when the other side closes")
                    .isTrue();
            assertThat(p.b.isConnected()).isFalse();
        }
    }
}
