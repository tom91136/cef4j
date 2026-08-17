package net.kurobako.cef4j.ipc.session.middleware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/** Gson implementation of the canonical crash-tolerant NDJSON trace format. */
public final class GsonNdjsonSessionTraceCodec implements SessionTraceCodec {
    public static final GsonNdjsonSessionTraceCodec INSTANCE = new GsonNdjsonSessionTraceCodec();
    private static final int MAX_LINE_CHARACTERS = 96 * 1024 * 1024;
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    public GsonNdjsonSessionTraceCodec() {}

    @Override
    public String format() {
        return "cef4j-session-api-ndjson";
    }

    @Override
    public String fileExtension() {
        return ".cef4japi.jsonl";
    }

    @Override
    public SessionTraceWriter openWriter(OutputStream destination, Map<String, String> metadata) throws IOException {
        return new TraceWriter(destination, metadata);
    }

    @Override
    public SessionTrace.Recording read(InputStream source) throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(source, "source"), StandardCharsets.UTF_8));
        String headerLine = readCompleteLine(input);
        if (headerLine == null) throw invalid("trace has no complete header record");
        Map<String, String> metadata = parseHeader(object(headerLine));
        List<SessionTrace.Entry> entries = new ArrayList<>();
        long expected = 1, lastElapsed = 0;
        String line;
        while ((line = readCompleteLine(input)) != null) {
            if (line.isBlank()) continue;
            SessionTrace.Entry entry = parseEntry(object(line));
            if (entry.sequence != expected)
                throw invalid("invalid trace sequence " + entry.sequence + ", expected " + expected);
            if (entry.elapsedNanos < lastElapsed)
                throw invalid("trace elapsedNanos moved backwards at entry " + entry.sequence);
            entries.add(entry);
            expected++;
            lastElapsed = entry.elapsedNanos;
        }
        return new SessionTrace.Recording(metadata, entries);
    }

    @Nullable
    private static String readCompleteLine(BufferedReader input) throws IOException {
        StringBuilder line = new StringBuilder();
        while (true) {
            int value = input.read();
            if (value < 0) return null;
            if (value == '\n') {
                int n = line.length();
                if (n > 0 && line.charAt(n - 1) == '\r') line.setLength(n - 1);
                return line.toString();
            }
            if (line.length() >= MAX_LINE_CHARACTERS) throw invalid("trace record is too large");
            line.append((char) value);
        }
    }

    private static JsonObject object(String line) throws IOException {
        try {
            validateJson(line);
            JsonElement value = JsonParser.parseString(line);
            if (!value.isJsonObject()) throw invalid("record must be an object");
            return value.getAsJsonObject();
        } catch (JsonParseException | IllegalStateException e) {
            throw invalid("record is not valid JSON", e);
        }
    }

    private static void validateJson(String line) throws IOException {
        try (JsonReader reader = new JsonReader(new StringReader(line))) {
            validateValue(reader, 0);
            if (reader.peek() != JsonToken.END_DOCUMENT) throw invalid("data follows record object");
        } catch (JsonParseException | IllegalStateException e) {
            throw invalid("record is not valid JSON", e);
        }
    }

    private static void validateValue(JsonReader reader, int depth) throws IOException {
        if (depth > 32) throw invalid("record nesting exceeds 32 levels");
        switch (reader.peek()) {
            case BEGIN_OBJECT:
                reader.beginObject();
                java.util.Set<String> names = new HashSet<>();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (!names.add(name)) throw invalid("duplicate field " + name);
                    validateValue(reader, depth + 1);
                }
                reader.endObject();
                return;
            case BEGIN_ARRAY:
                reader.beginArray();
                while (reader.hasNext()) validateValue(reader, depth + 1);
                reader.endArray();
                return;
            case STRING:
                reader.skipValue();
                return;
            case NUMBER:
                String number = reader.nextString();
                if (number.length() > 128) throw invalid("number exceeds 128 characters");
                return;
            case BOOLEAN:
                reader.skipValue();
                return;
            case NULL:
                reader.skipValue();
                return;
            default:
                throw invalid("unexpected JSON token " + reader.peek());
        }
    }

    private static Map<String, String> parseHeader(JsonObject value) throws IOException {
        if (!"header".equals(text(value, "type"))) throw invalid("first record is not a header");
        if (!SessionTrace.FORMAT.equals(text(value, "format"))) throw invalid("unsupported trace format");
        if (integer(value, "version") != SessionTrace.VERSION) throw invalid("unsupported trace version");
        String created = decimal(value, "createdEpochMillis");
        Map<String, String> metadata = new LinkedHashMap<>();
        JsonElement raw = value.get("metadata");
        if (raw == null || !raw.isJsonObject()) throw invalid("metadata must be an object");
        for (Map.Entry<String, JsonElement> entry : raw.getAsJsonObject().entrySet())
            metadata.put(entry.getKey(), text(entry.getValue(), "metadata." + entry.getKey()));
        metadata.put("format", SessionTrace.FORMAT);
        metadata.put("formatVersion", Integer.toString(SessionTrace.VERSION));
        metadata.put("createdEpochMillis", created);
        return metadata;
    }

    private static SessionTrace.Entry parseEntry(JsonObject value) throws IOException {
        if (!value.has("type")
                || !value.has("sequence")
                || !value.has("elapsedNanos")
                || !value.has("operationId")
                || !value.has("messageId")) {
            throw invalid("trace entry is missing a required field");
        }
        String type = text(value, "type");
        long sequence = number(decimal(value, "sequence"), "sequence");
        long elapsed = number(decimal(value, "elapsedNanos"), "elapsedNanos");
        long operation = number(decimal(value, "operationId"), "operationId");
        int message = integer(value, "messageId");
        byte[] payload = null;
        JsonElement encoded = value.get("payloadBase64");
        if (encoded != null)
            try {
                payload = Base64.getDecoder().decode(text(encoded, "payloadBase64"));
            } catch (IllegalArgumentException e) {
                throw invalid("invalid payloadBase64", e);
            }
        String detailType = optionalText(value, "detailType"), detailMessage = optionalText(value, "detailMessage");
        SessionTrace.Kind kind;
        try {
            kind = SessionTrace.Kind.valueOf(type.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw invalid("unknown trace entry type " + type, e);
        }
        try {
            return new SessionTrace.Entry(
                    sequence, elapsed, kind, operation, message, payload, detailType, detailMessage);
        } catch (IllegalArgumentException e) {
            throw invalid("invalid trace entry " + sequence, e);
        }
    }

    private static String text(JsonObject value, String name) throws IOException {
        JsonElement element = value.get(name);
        if (element == null) return missing(name);
        return text(element, name);
    }

    private static String text(JsonElement value, String name) throws IOException {
        if (!value.isJsonPrimitive() || !value.getAsJsonPrimitive().isString())
            throw invalid(name + " must be a string");
        return value.getAsString();
    }

    @Nullable
    private static String optionalText(JsonObject value, String name) throws IOException {
        JsonElement element = value.get(name);
        return element == null ? null : text(element, name);
    }

    private static int integer(JsonObject value, String name) throws IOException {
        JsonElement element = value.get(name);
        if (element == null
                || !element.isJsonPrimitive()
                || !element.getAsJsonPrimitive().isNumber()) throw invalid(name + " must be an integer");
        try {
            java.math.BigDecimal exact = element.getAsBigDecimal();
            return exact.intValueExact();
        } catch (NumberFormatException | ArithmeticException e) {
            throw invalid(name + " must be an integer", e);
        }
    }

    private static String decimal(JsonObject value, String name) throws IOException {
        String result = text(value, name);
        if (!result.matches("-?[0-9]+")) throw invalid(name + " must be a decimal integer string");
        return result;
    }

    private static long number(String value, String name) throws IOException {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw invalid(name + " is outside the signed 64-bit range", e);
        }
    }

    private static String missing(String name) throws IOException {
        throw invalid(name + " is required");
    }

    private static IOException invalid(String message) {
        return new IOException("invalid cef4j NDJSON trace: " + message);
    }

    private static IOException invalid(String message, Exception cause) {
        return new IOException("invalid cef4j NDJSON trace: " + message, cause);
    }

    private static final class TraceWriter implements SessionTraceWriter {
        private final Writer output;
        private final long startNanos = System.nanoTime();
        private long nextSequence = 1;
        private boolean closed;

        TraceWriter(OutputStream destination, Map<String, String> metadata) throws IOException {
            output = new OutputStreamWriter(Objects.requireNonNull(destination, "destination"), StandardCharsets.UTF_8);
            JsonObject header = new JsonObject();
            header.addProperty("type", "header");
            header.addProperty("format", SessionTrace.FORMAT);
            header.addProperty("version", SessionTrace.VERSION);
            header.addProperty("createdEpochMillis", Long.toString(System.currentTimeMillis()));
            JsonObject values = new JsonObject();
            metadata.forEach(values::addProperty);
            header.add("metadata", values);
            line(header, true);
        }

        @Override
        public synchronized void append(
                SessionTrace.Kind kind,
                long operationId,
                int messageId,
                @Nullable byte[] payload,
                @Nullable String detailType,
                @Nullable String detailMessage)
                throws IOException {
            if (closed) throw new IOException("session trace writer closed");
            JsonObject value = new JsonObject();
            value.addProperty("type", kind.name().toLowerCase(Locale.ROOT));
            value.addProperty("sequence", Long.toString(nextSequence++));
            value.addProperty("elapsedNanos", Long.toString(Math.max(0, System.nanoTime() - startNanos)));
            value.addProperty("operationId", Long.toString(operationId));
            value.addProperty("messageId", messageId);
            if (payload != null)
                value.addProperty("payloadBase64", Base64.getEncoder().encodeToString(payload));
            if (detailType != null) value.addProperty("detailType", detailType);
            if (detailMessage != null) value.addProperty("detailMessage", detailMessage);
            line(value, kind == SessionTrace.Kind.FAILURE || kind == SessionTrace.Kind.CLOSE);
        }

        private void line(JsonObject value, boolean flush) throws IOException {
            GSON.toJson(value, output);
            output.write('\n');
            if (flush) output.flush();
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
    }
}
