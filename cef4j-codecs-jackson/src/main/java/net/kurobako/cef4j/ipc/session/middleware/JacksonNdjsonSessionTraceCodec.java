package net.kurobako.cef4j.ipc.session.middleware;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.StreamReadFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Canonical newline-delimited JSON codec. Protocol payloads are preserved as standard Base64 strings. */
public final class JacksonNdjsonSessionTraceCodec implements SessionTraceCodec {
    public static final JacksonNdjsonSessionTraceCodec INSTANCE = new JacksonNdjsonSessionTraceCodec();

    private static final int MAX_LINE_CHARACTERS = 96 * 1024 * 1024;
    private static final JsonFactory JSON = JsonFactory.builder()
            .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
            .streamReadConstraints(StreamReadConstraints.builder()
                    .maxNestingDepth(32)
                    .maxNumberLength(128)
                    .maxStringLength(MAX_LINE_CHARACTERS)
                    .build())
            .build();

    /** Public for {@link java.util.ServiceLoader}. Prefer {@link #INSTANCE} in direct code. */
    public JacksonNdjsonSessionTraceCodec() {}

    @Override
    @Nonnull
    public String format() {
        return "cef4j-session-api-ndjson";
    }

    @Override
    @Nonnull
    public String fileExtension() {
        return ".cef4japi.jsonl";
    }

    @Override
    @Nonnull
    public SessionTraceWriter openWriter(@Nonnull OutputStream destination, @Nonnull Map<String, String> metadata)
            throws IOException {
        return new Writer(destination, metadata);
    }

    @Override
    @Nonnull
    public SessionTrace.Recording read(@Nonnull InputStream source) throws IOException {
        Objects.requireNonNull(source, "source");
        BufferedReader input = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8));
        String headerLine = readCompleteLine(input);
        if (headerLine == null) throw new IOException("cef4j API trace has no complete header record");
        Map<String, String> metadata = parseHeader(headerLine);
        List<SessionTrace.Entry> entries = new ArrayList<>();
        long expectedSequence = 1;
        long lastElapsedNanos = 0;
        String line;
        while ((line = readCompleteLine(input)) != null) {
            if (line.isBlank()) continue;
            SessionTrace.Entry entry = parseEntry(line);
            if (entry.sequence != expectedSequence) {
                throw new IOException("invalid trace sequence " + entry.sequence + ", expected " + expectedSequence);
            }
            if (entry.elapsedNanos < lastElapsedNanos) {
                throw new IOException("trace elapsedNanos moved backwards at entry " + entry.sequence);
            }
            entries.add(entry);
            expectedSequence++;
            lastElapsedNanos = entry.elapsedNanos;
        }
        return new SessionTrace.Recording(metadata, entries);
    }

    /**
     * Returns only newline-terminated records. The writer terminates and then flushes each recovery boundary, so an
     * unterminated final fragment is safely treated as a crash-torn record rather than guessed at.
     */
    @Nullable
    private static String readCompleteLine(BufferedReader input) throws IOException {
        StringBuilder line = new StringBuilder();
        while (true) {
            int value = input.read();
            if (value < 0) return null;
            if (value == '\n') {
                int length = line.length();
                if (length > 0 && line.charAt(length - 1) == '\r') line.setLength(length - 1);
                return line.toString();
            }
            if (line.length() >= MAX_LINE_CHARACTERS) throw new IOException("NDJSON trace record is too large");
            line.append((char) value);
        }
    }

    private static Map<String, String> parseHeader(String line) throws IOException {
        String type = null;
        String format = null;
        Integer version = null;
        String createdEpochMillis = null;
        Map<String, String> metadata = new LinkedHashMap<>();
        try (JsonParser parser = JSON.createParser(line)) {
            require(parser.nextToken(), JsonToken.START_OBJECT, "trace header must be a JSON object");
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String field = parser.currentName();
                JsonToken value = parser.nextToken();
                switch (field) {
                    case "type":
                        type = requireText(parser, value, field);
                        break;
                    case "format":
                        format = requireText(parser, value, field);
                        break;
                    case "version":
                        if (value != JsonToken.VALUE_NUMBER_INT) throw invalid(field + " must be an integer");
                        version = parser.getIntValue();
                        break;
                    case "createdEpochMillis":
                        createdEpochMillis = requireDecimalString(parser, value, field);
                        break;
                    case "metadata":
                        readMetadata(parser, value, metadata);
                        break;
                    default:
                        parser.skipChildren();
                }
            }
            if (parser.nextToken() != null) throw invalid("data follows trace header object");
        }
        if (!"header".equals(type)) throw invalid("first record is not a header");
        if (!SessionTrace.FORMAT.equals(format)) throw invalid("unsupported trace format " + format);
        if (version == null || version.intValue() != SessionTrace.VERSION) {
            throw invalid("unsupported trace version " + version);
        }
        if (createdEpochMillis == null) throw invalid("header has no createdEpochMillis");
        metadata.put("format", SessionTrace.FORMAT);
        metadata.put("formatVersion", Integer.toString(SessionTrace.VERSION));
        metadata.put("createdEpochMillis", createdEpochMillis);
        return metadata;
    }

    private static void readMetadata(JsonParser parser, JsonToken token, Map<String, String> metadata)
            throws IOException {
        if (token != JsonToken.START_OBJECT) throw invalid("metadata must be an object");
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String key = parser.currentName();
            JsonToken value = parser.nextToken();
            metadata.put(key, requireText(parser, value, "metadata." + key));
        }
    }

    private static SessionTrace.Entry parseEntry(String line) throws IOException {
        String type = null;
        Long sequence = null;
        Long elapsedNanos = null;
        Long operationId = null;
        Integer messageId = null;
        byte[] payload = null;
        String detailType = null;
        String detailMessage = null;
        try (JsonParser parser = JSON.createParser(line)) {
            require(parser.nextToken(), JsonToken.START_OBJECT, "trace entry must be a JSON object");
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String field = parser.currentName();
                JsonToken value = parser.nextToken();
                switch (field) {
                    case "type":
                        type = requireText(parser, value, field);
                        break;
                    case "sequence":
                        sequence = parseLong(requireDecimalString(parser, value, field), field);
                        break;
                    case "elapsedNanos":
                        elapsedNanos = parseLong(requireDecimalString(parser, value, field), field);
                        break;
                    case "operationId":
                        operationId = parseLong(requireDecimalString(parser, value, field), field);
                        break;
                    case "messageId":
                        if (value != JsonToken.VALUE_NUMBER_INT) throw invalid(field + " must be an integer");
                        messageId = parser.getIntValue();
                        break;
                    case "payloadBase64":
                        if (value != JsonToken.VALUE_STRING) throw invalid(field + " must be a string");
                        payload = parser.getBinaryValue(Base64Variants.getDefaultVariant());
                        break;
                    case "detailType":
                        detailType = requireText(parser, value, field);
                        break;
                    case "detailMessage":
                        detailMessage = requireText(parser, value, field);
                        break;
                    default:
                        parser.skipChildren();
                }
            }
            if (parser.nextToken() != null) throw invalid("data follows trace entry object");
        }
        if (type == null || sequence == null || elapsedNanos == null || operationId == null || messageId == null) {
            throw invalid("trace entry is missing a required field");
        }
        SessionTrace.Kind kind;
        try {
            kind = SessionTrace.Kind.valueOf(type.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException failure) {
            throw invalid("unknown trace entry type " + type, failure);
        }
        try {
            return new SessionTrace.Entry(
                    sequence.longValue(),
                    elapsedNanos.longValue(),
                    kind,
                    operationId.longValue(),
                    messageId.intValue(),
                    payload,
                    detailType,
                    detailMessage);
        } catch (IllegalArgumentException failure) {
            throw invalid("invalid trace entry " + sequence, failure);
        }
    }

    private static String requireText(JsonParser parser, JsonToken token, String field) throws IOException {
        if (token != JsonToken.VALUE_STRING) throw invalid(field + " must be a string");
        return parser.getText();
    }

    private static String requireDecimalString(JsonParser parser, JsonToken token, String field) throws IOException {
        String value = requireText(parser, token, field);
        if (!value.matches("-?[0-9]+")) throw invalid(field + " must be a decimal integer string");
        return value;
    }

    private static long parseLong(String value, String field) throws IOException {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException failure) {
            throw invalid(field + " is outside the signed 64-bit range", failure);
        }
    }

    private static void require(@Nullable JsonToken actual, JsonToken expected, String message) throws IOException {
        if (actual != expected) throw invalid(message);
    }

    private static IOException invalid(String message) {
        return new IOException("invalid cef4j NDJSON trace: " + message);
    }

    private static IOException invalid(String message, Exception cause) {
        return new IOException("invalid cef4j NDJSON trace: " + message, cause);
    }

    private static final class Writer implements SessionTraceWriter {
        private final JsonGenerator output;
        private final long startNanos = System.nanoTime();
        private long nextSequence = 1;
        private boolean closed;

        Writer(OutputStream destination, Map<String, String> metadata) throws IOException {
            Objects.requireNonNull(destination, "destination");
            Objects.requireNonNull(metadata, "metadata");
            output = JSON.createGenerator(destination);
            output.writeStartObject();
            output.writeStringField("type", "header");
            output.writeStringField("format", SessionTrace.FORMAT);
            output.writeNumberField("version", SessionTrace.VERSION);
            output.writeStringField("createdEpochMillis", Long.toString(System.currentTimeMillis()));
            output.writeObjectFieldStart("metadata");
            for (Map.Entry<String, String> entry : metadata.entrySet()) {
                output.writeStringField(entry.getKey(), entry.getValue());
            }
            output.writeEndObject();
            output.writeEndObject();
            endLineAndFlush();
        }

        @Override
        public synchronized void append(
                @Nonnull SessionTrace.Kind kind,
                long operationId,
                int messageId,
                @Nullable byte[] payload,
                @Nullable String detailType,
                @Nullable String detailMessage)
                throws IOException {
            Objects.requireNonNull(kind, "kind");
            if (closed) throw new IOException("session trace writer closed");
            output.writeStartObject();
            output.writeStringField("type", kind.name().toLowerCase(Locale.ROOT));
            output.writeStringField("sequence", Long.toString(nextSequence++));
            output.writeStringField("elapsedNanos", Long.toString(Math.max(0, System.nanoTime() - startNanos)));
            output.writeStringField("operationId", Long.toString(operationId));
            output.writeNumberField("messageId", messageId);
            if (payload != null) {
                output.writeFieldName("payloadBase64");
                output.writeBinary(Base64Variants.getDefaultVariant(), payload, 0, payload.length);
            }
            if (detailType != null) output.writeStringField("detailType", detailType);
            if (detailMessage != null) output.writeStringField("detailMessage", detailMessage);
            output.writeEndObject();
            output.writeRaw('\n');
            if (kind == SessionTrace.Kind.FAILURE || kind == SessionTrace.Kind.CLOSE) output.flush();
        }

        @Override
        public synchronized void flush() throws IOException {
            if (closed) throw new IOException("session trace writer closed");
            output.flush();
        }

        @Override
        public synchronized void close() throws IOException {
            if (closed) return;
            closed = true;
            output.close();
        }

        private void endLineAndFlush() throws IOException {
            output.writeRaw('\n');
            output.flush();
        }
    }
}
