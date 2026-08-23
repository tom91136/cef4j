package net.kurobako.cef4j.cdp;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import org.junit.jupiter.api.Test;

final class CdpRequestTrackerTest {
    @Test
    void cancellationRemovesPendingRequest() {
        CdpRequestTracker<byte[]> tracker = new CdpRequestTracker<>();
        CdpRequestTracker.Request<byte[]> request = tracker.register();

        request.cancel(false);

        assertThat(tracker.pendingCount()).isZero();
    }

    @Test
    void wrapSkipsAnOccupiedIdentifier() throws Exception {
        CdpRequestTracker<byte[]> tracker = new CdpRequestTracker<>();
        CdpRequestTracker.Request<byte[]> occupied = tracker.register();
        assertThat(occupied.id()).isEqualTo(1);
        nextId(tracker).set(Integer.MAX_VALUE);

        CdpRequestTracker.Request<byte[]> wrapped = tracker.register();

        assertThat(wrapped.id()).isEqualTo(2);
        assertThat(tracker.pendingCount()).isEqualTo(2);
    }

    @Test
    void typedCallCancellationPropagatesToTransportResult() {
        AtomicReference<CompletableFuture<byte[]>> source = new AtomicReference<>();
        CdpTransport transport = new CdpTransport() {
            @Override
            public CompletableFuture<byte[]> execute(String method, @Nullable byte[] params) {
                CompletableFuture<byte[]> result = new CompletableFuture<>();
                source.set(result);
                return result;
            }

            @Override
            public CdpSubscription subscribe(String method, java.util.function.Consumer<byte[]> handler) {
                return () -> {};
            }
        };
        CdpCodec codec = new CdpCodec() {
            @Override
            public byte[] encode(Object value) {
                return new byte[0];
            }

            @Override
            public Object decode(byte[] json) {
                return Map.of();
            }
        };
        CompletableFuture<String> call = new CdpClient(transport, codec)
                .call("Runtime.evaluate", null, ignored -> "done")
                .toCompletableFuture();

        call.cancel(false);

        assertThat(source.get()).isCancelled();
    }

    private static AtomicInteger nextId(CdpRequestTracker<?> tracker) throws Exception {
        Field field = CdpRequestTracker.class.getDeclaredField("nextId");
        field.setAccessible(true);
        return (AtomicInteger) field.get(tracker);
    }
}
