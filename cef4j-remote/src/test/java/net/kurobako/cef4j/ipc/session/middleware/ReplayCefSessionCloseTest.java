package net.kurobako.cef4j.ipc.session.middleware;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(15)
final class ReplayCefSessionCloseTest {
    private static final int REQUEST = 101;

    @Test
    void closeCleansUpEvenWhenTheTraceMismatches() throws Exception {
        List<SessionTrace.Entry> entries = List.of(
                new SessionTrace.Entry(1, 0, SessionTrace.Kind.REQUEST, 1, REQUEST, bytes("first"), null, null),
                new SessionTrace.Entry(2, 0, SessionTrace.Kind.RESPONSE, 1, REQUEST, bytes("answer"), null, null),
                new SessionTrace.Entry(3, 0, SessionTrace.Kind.REQUEST, 2, REQUEST, bytes("second"), null, null));
        ReplayCefSession replay =
                new ReplayCefSession(new SessionTrace.Recording(Collections.emptyMap(), entries), ReplayMode.IMMEDIATE);
        AtomicBoolean closed = new AtomicBoolean();
        replay.onClose(() -> closed.set(true));
        replay.start();
        CompletableFuture<TextView> first = replay.request(REQUEST, encoder("first"), decoder());
        assertThat(first.get(5, TimeUnit.SECONDS).text).isEqualTo("answer");

        assertThatThrownBy(replay::close).isInstanceOf(ReplayMismatchException.class);
        assertThat(closed).isTrue();
        replay.close();
        assertThat(closed).isTrue();
    }

    private static CefMessageEncoder encoder(String text) {
        return new TextEncoder(bytes(text));
    }

    private static CefMessageDecoder<TextView> decoder() {
        return payload -> {
            byte[] bytes = new byte[payload.remaining()];
            payload.get(bytes);
            return new TextView(new String(bytes, StandardCharsets.UTF_8));
        };
    }

    private static byte[] bytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    private static final class TextEncoder implements CefMessageEncoder {
        private final byte[] bytes;

        TextEncoder(byte[] bytes) {
            this.bytes = bytes;
        }

        @Override
        public int encodedSize() {
            return bytes.length;
        }

        @Override
        public void encodeInto(ByteBuffer destination) {
            destination.put(bytes);
        }
    }

    private static final class TextView implements CefMessageView {
        private final String text;

        TextView(String text) {
            this.text = text;
        }
    }
}
