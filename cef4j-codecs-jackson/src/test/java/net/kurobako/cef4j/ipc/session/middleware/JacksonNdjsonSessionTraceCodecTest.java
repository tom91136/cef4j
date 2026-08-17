package net.kurobako.cef4j.ipc.session.middleware;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JacksonNdjsonSessionTraceCodecTest {
    @Test
    void writesCanonicalReadableNdjson(@TempDir Path directory) throws Exception {
        Path trace = directory.resolve("trace.cef4japi.jsonl");
        byte[] payload = {0, 1, 2, (byte) 0xff};
        try (SessionTraceWriter writer = SessionTrace.writer(trace, Map.of("application", "unicode-π"))) {
            writer.append(SessionTrace.Kind.REQUEST, 9, 123, payload);
            writer.append(
                    SessionTrace.Kind.FAILURE, 9, 123, null, "java.lang.IllegalStateException", "quote=\" newline=\n");
            writer.append(SessionTrace.Kind.CLOSE, 0, 0, null);
        }

        List<String> lines = Files.readAllLines(trace, StandardCharsets.UTF_8);
        assertThat(lines).hasSize(4);
        assertThat(lines.get(0))
                .startsWith("{\"type\":\"header\",\"format\":\"cef4j-session-api\",\"version\":1")
                .contains("\"application\":\"unicode-π\"");
        assertThat(lines.get(1))
                .contains("\"type\":\"request\"")
                .contains("\"sequence\":\"1\"")
                .contains("\"operationId\":\"9\"")
                .contains("\"payloadBase64\":\"AAEC/w==\"");

        SessionTrace.Recording recording = SessionTrace.read(trace);
        assertThat(recording.metadata()).containsEntry("application", "unicode-π");
        assertThat(recording.entries()).hasSize(3);
        assertThat(recording.entries().get(0).payload()).containsExactly(payload);
        assertThat(recording.entries().get(1).detailMessage).isEqualTo("quote=\" newline=\n");
    }

    @Test
    void ignoresOnlyAnUnterminatedCrashFragment(@TempDir Path directory) throws Exception {
        Path trace = directory.resolve("crashed.cef4japi.jsonl");
        try (SessionTraceWriter writer = SessionTrace.writer(trace)) {
            writer.append(SessionTrace.Kind.FAILURE, 1, 5, null, "failure", "flushed");
        }
        Files.writeString(
                trace, "{\"type\":\"request\",\"sequence\":\"2\"", StandardCharsets.UTF_8, StandardOpenOption.APPEND);

        assertThat(SessionTrace.read(trace).entries())
                .extracting(entry -> entry.kind)
                .containsExactly(SessionTrace.Kind.FAILURE);

        Files.writeString(trace, "}\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        assertThatThrownBy(() -> SessionTrace.read(trace))
                .isInstanceOf(IOException.class)
                .hasMessageContaining("missing a required field");
    }

    @Test
    void explicitCodecIsUsedForWritingAndReading(@TempDir Path directory) throws Exception {
        AtomicBoolean opened = new AtomicBoolean();
        AtomicBoolean read = new AtomicBoolean();
        SessionTraceCodec codec = new SessionTraceCodec() {
            @Override
            @Nonnull
            public String format() {
                return "test-wrapper";
            }

            @Override
            @Nonnull
            public String fileExtension() {
                return ".test";
            }

            @Override
            @Nonnull
            public SessionTraceWriter openWriter(
                    @Nonnull OutputStream destination, @Nonnull Map<String, String> metadata) throws IOException {
                opened.set(true);
                return JacksonNdjsonSessionTraceCodec.INSTANCE.openWriter(destination, metadata);
            }

            @Override
            @Nonnull
            public SessionTrace.Recording read(@Nonnull InputStream source) throws IOException {
                read.set(true);
                return JacksonNdjsonSessionTraceCodec.INSTANCE.read(source);
            }
        };
        Path trace = directory.resolve("trace.test");
        try (SessionTraceWriter writer = SessionTrace.writer(trace, Map.of(), codec)) {
            writer.append(SessionTrace.Kind.CLOSE, 0, 0, null);
        }
        assertThat(SessionTrace.read(trace, codec).entries()).hasSize(1);
        assertThat(opened).isTrue();
        assertThat(read).isTrue();
    }
}
