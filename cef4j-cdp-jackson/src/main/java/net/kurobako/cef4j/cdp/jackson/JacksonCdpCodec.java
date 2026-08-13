package net.kurobako.cef4j.cdp.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.kurobako.cef4j.cdp.CdpCodec;

/** Jackson-core implementation of the codec-neutral {@link CdpCodec} contract. */
public final class JacksonCdpCodec implements CdpCodec {
    private static final JsonFactory JSON = JsonFactory.builder().build();

    @Override
    public byte[] encode(Object value) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (JsonGenerator generator = JSON.createGenerator(output)) {
                write(generator, value);
            }
            return output.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("could not encode CDP JSON", e);
        }
    }

    @Override
    public Object decode(byte[] json) {
        try (JsonParser parser = JSON.createParser(json)) {
            JsonToken token = parser.nextToken();
            if (token == null) throw new IllegalArgumentException("CDP JSON is empty");
            Object result = read(parser, token);
            if (parser.nextToken() != null) throw new IllegalArgumentException("data follows CDP JSON value");
            return result;
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid CDP JSON", e);
        }
    }

    @SuppressWarnings("NullAway")
    private static Object read(JsonParser parser, JsonToken token) throws IOException {
        switch (token) {
            case START_OBJECT:
                Map<String, Object> object = new LinkedHashMap<>();
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String name = parser.currentName();
                    object.put(name, read(parser, parser.nextToken()));
                }
                return object;
            case START_ARRAY:
                List<Object> array = new ArrayList<>();
                JsonToken item;
                while ((item = parser.nextToken()) != JsonToken.END_ARRAY) array.add(read(parser, item));
                return array;
            case VALUE_STRING:
                return parser.getText();
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NUMBER_INT:
                return parser.getNumberValue();
            case VALUE_NUMBER_FLOAT:
                return new BigDecimal(parser.getText());
            case VALUE_NULL:
                return null;
            default:
                throw new IllegalArgumentException("unsupported CDP JSON token " + token);
        }
    }

    @SuppressWarnings("unchecked")
    private static void write(JsonGenerator generator, Object value) throws IOException {
        if (value == null) {
            generator.writeNull();
            return;
        }
        if (value instanceof Map) {
            generator.writeStartObject();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                generator.writeFieldName((String) entry.getKey());
                write(generator, entry.getValue());
            }
            generator.writeEndObject();
            return;
        }
        if (value instanceof Iterable) {
            generator.writeStartArray();
            for (Object item : (Iterable<Object>) value) write(generator, item);
            generator.writeEndArray();
            return;
        }
        if (value instanceof Boolean) generator.writeBoolean((Boolean) value);
        else if (value instanceof Integer) generator.writeNumber((Integer) value);
        else if (value instanceof Long) generator.writeNumber((Long) value);
        else if (value instanceof BigDecimal) generator.writeNumber((BigDecimal) value);
        else if (value instanceof Number) generator.writeNumber(value.toString());
        else generator.writeString((String) value);
    }
}
