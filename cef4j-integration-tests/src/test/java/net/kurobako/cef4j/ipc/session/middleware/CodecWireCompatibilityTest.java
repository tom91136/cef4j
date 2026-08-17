package net.kurobako.cef4j.ipc.session.middleware;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CodecWireCompatibilityTest {
    @Test
    void gsonAndJacksonReadEachOthersTraces(@TempDir Path directory) throws Exception {
        Path gsonTrace = directory.resolve("gson.jsonl");
        try (SessionTraceWriter writer =
                SessionTrace.writer(gsonTrace, Map.of("codec", "gson"), GsonNdjsonSessionTraceCodec.INSTANCE)) {
            writer.append(SessionTrace.Kind.REQUEST, 7, 11, new byte[] {1, 2, 3});
        }
        SessionTrace.Recording fromGson = SessionTrace.read(gsonTrace, JacksonNdjsonSessionTraceCodec.INSTANCE);
        assertThat(fromGson.entries())
                .singleElement()
                .satisfies(entry -> assertThat(entry.payload()).containsExactly(1, 2, 3));

        Path jacksonTrace = directory.resolve("jackson.jsonl");
        try (SessionTraceWriter writer = SessionTrace.writer(
                jacksonTrace, Map.of("codec", "jackson"), JacksonNdjsonSessionTraceCodec.INSTANCE)) {
            writer.append(SessionTrace.Kind.EVENT, 9, 13, new byte[] {4, 5});
        }
        SessionTrace.Recording fromJackson = SessionTrace.read(jacksonTrace, GsonNdjsonSessionTraceCodec.INSTANCE);
        assertThat(fromJackson.entries())
                .singleElement()
                .satisfies(entry -> assertThat(entry.payload()).containsExactly(4, 5));
    }
}
