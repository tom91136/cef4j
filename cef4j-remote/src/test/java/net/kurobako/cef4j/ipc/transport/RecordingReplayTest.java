package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(15)
class RecordingReplayTest {

    private static ByteBuffer buf(String s) {
        return ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void recordingThroughLoopbackProducesReplayableLog(@TempDir Path tmp) throws Exception {
        Path logFile = tmp.resolve("session.log");
        LoopbackTransport.Pair pair = LoopbackTransport.create();

        List<String> liveReceived = new ArrayList<>();
        CountDownLatch threeArrived = new CountDownLatch(3);

        try (RecordingTransport recA = new RecordingTransport(pair.a, MessageLog.writer(logFile))) {
            recA.onReceive(f -> {
                byte[] bytes = new byte[f.remaining()];
                f.get(bytes);
                liveReceived.add(new String(bytes, StandardCharsets.UTF_8));
                threeArrived.countDown();
            });

            recA.send(buf("a-out-1"));
            recA.send(buf("a-out-2"));
            pair.b.send(buf("b-in-1"));
            pair.b.send(buf("b-in-2"));
            pair.b.send(buf("b-in-3"));

            assertThat(threeArrived.await(5, TimeUnit.SECONDS)).isTrue();
            assertThat(liveReceived).containsExactly("b-in-1", "b-in-2", "b-in-3");
        }
        pair.b.close();

        try (ReplayTransport replay = ReplayTransport.fromFile(logFile)) {
            List<String> replayed = new ArrayList<>();
            replay.onReceive(f -> {
                byte[] bytes = new byte[f.remaining()];
                f.get(bytes);
                replayed.add(new String(bytes, StandardCharsets.UTF_8));
            });
            replay.start();
            assertThat(replayed).containsExactly("b-in-1", "b-in-2", "b-in-3");

            assertThat(replay.recordedOutbound())
                    .extracting(b -> new String(b, StandardCharsets.UTF_8))
                    .containsExactly("a-out-1", "a-out-2");
        }
    }

    @Test
    void replaySendsAreCapturedForAssertion(@TempDir Path tmp) throws Exception {
        Path logFile = tmp.resolve("empty.log");
        MessageLog.writer(logFile).close();
        try (ReplayTransport replay = ReplayTransport.fromFile(logFile)) {
            replay.onReceive(f -> {});
            replay.start();
            replay.send(buf("x"));
            replay.send(buf("yy"));
            assertThat(replay.actualOutbound())
                    .extracting(b -> new String(b, StandardCharsets.UTF_8))
                    .containsExactly("x", "yy");
        }
    }

    @Test
    void recordedOutboundExposesOriginalSendOrder(@TempDir Path tmp) throws Exception {
        Path logFile = tmp.resolve("only-out.log");
        try (MessageLog.Writer w = MessageLog.writer(logFile)) {
            w.append(MessageLog.Direction.OUTBOUND, 1, "first".getBytes(StandardCharsets.UTF_8));
            w.append(MessageLog.Direction.INBOUND, 2, "ignored".getBytes(StandardCharsets.UTF_8));
            w.append(MessageLog.Direction.OUTBOUND, 3, "second".getBytes(StandardCharsets.UTF_8));
        }
        try (ReplayTransport replay = ReplayTransport.fromFile(logFile)) {
            assertThat(replay.recordedOutbound())
                    .extracting(b -> new String(b, StandardCharsets.UTF_8))
                    .containsExactly("first", "second");
        }
    }

    @Test
    void recordingPreservesRuntimeServerClientCapability(@TempDir Path tmp) throws Exception {
        Path logFile = tmp.resolve("runtime-client.log");
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        CefTransport runtimeClient = new CefTransport() {
            @Override
            public void send(ByteBuffer frame) throws CefTransportException {
                pair.a.send(frame);
            }

            @Override
            public void onReceive(java.util.function.Consumer<ByteBuffer> handler) {
                pair.a.onReceive(handler);
            }

            @Override
            public void onDisconnect(Runnable handler) {
                pair.a.onDisconnect(handler);
            }

            @Override
            public boolean isConnected() {
                return pair.a.isConnected();
            }

            @Override
            public boolean isRuntimeServerClient() {
                return true;
            }

            @Override
            public void close() {
                pair.a.close();
            }
        };
        try (RecordingTransport recording = new RecordingTransport(runtimeClient, MessageLog.writer(logFile))) {
            assertThat(recording.isRuntimeServerClient()).isTrue();
        } finally {
            pair.b.close();
        }
    }

    @Test
    void inboundFrameForwardedEvenWhenRecordingFails() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        MessageLog.Writer broken = new MessageLog.Writer(new DataOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException("recording medium full");
            }
        }));
        try (RecordingTransport recording = new RecordingTransport(pair.a, broken)) {
            CompletableFuture<String> arrived = new CompletableFuture<>();
            recording.onReceive(frame -> {
                byte[] bytes = new byte[frame.remaining()];
                frame.get(bytes);
                arrived.complete(new String(bytes, StandardCharsets.UTF_8));
            });
            pair.b.send(buf("live"));
            assertThat(arrived.get(5, TimeUnit.SECONDS)).isEqualTo("live");
        }
        pair.b.close();
    }

    @Test
    void sendFailsLoudlyWhenRecordingFails() {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        MessageLog.Writer broken = new MessageLog.Writer(new DataOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException("recording medium full");
            }
        }));
        try (RecordingTransport recording = new RecordingTransport(pair.a, broken)) {
            assertThatThrownBy(() -> recording.send(buf("x"))).isInstanceOf(CefTransportException.class);
        }
        pair.b.close();
    }
}
