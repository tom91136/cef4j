package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class LoopbackTransportTest extends CefTransportContractTest {
    @Override
    protected Pair newPair() {
        LoopbackTransport.Pair p = LoopbackTransport.create();
        return new Pair(p.a, p.b);
    }

    @Test
    void lateDisconnectRegistrationIsDeliveredExactlyOnce() {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try {
            pair.b.close();
            AtomicInteger calls = new AtomicInteger();

            pair.a.onDisconnect(calls::incrementAndGet);
            pair.a.close();

            assertThat(calls).hasValue(1);
        } finally {
            pair.a.close();
            pair.b.close();
        }
    }
}
