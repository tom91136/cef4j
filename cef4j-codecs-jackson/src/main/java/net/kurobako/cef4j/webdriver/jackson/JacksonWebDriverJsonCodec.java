package net.kurobako.cef4j.webdriver.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import net.kurobako.cef4j.webdriver.JsonArray;
import net.kurobako.cef4j.webdriver.JsonElement;
import net.kurobako.cef4j.webdriver.JsonNull;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.JsonPrimitive;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;

/** Jackson-core implementation of the WebDriver JSON codec SPI. */
public final class JacksonWebDriverJsonCodec implements WebDriverJsonCodec {
    private static final JsonFactory JSON = JsonFactory.builder().build();

    @Override
    public JsonElement decode(byte[] json) {
        try (JsonParser parser = JSON.createParser(json)) {
            JsonToken first = parser.nextToken();
            if (first == null) throw new IllegalArgumentException("JSON document is empty");
            JsonElement result = read(parser, first);
            if (parser.nextToken() != null) throw new IllegalArgumentException("data follows JSON value");
            return result;
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid JSON", e);
        }
    }

    @Override
    public byte[] encode(JsonElement value) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (JsonGenerator generator = JSON.createGenerator(output)) {
                write(generator, value);
            }
            return output.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("could not encode JSON", e);
        }
    }

    private static JsonElement read(JsonParser parser, JsonToken token) throws IOException {
        switch (token) {
            case START_OBJECT:
                JsonObject object = new JsonObject();
                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    String name = parser.currentName();
                    object.add(name, read(parser, parser.nextToken()));
                }
                return object;
            case START_ARRAY:
                JsonArray array = new JsonArray();
                JsonToken item;
                while ((item = parser.nextToken()) != JsonToken.END_ARRAY) array.add(read(parser, item));
                return array;
            case VALUE_STRING:
                return new JsonPrimitive(parser.getText());
            case VALUE_TRUE:
                return new JsonPrimitive(true);
            case VALUE_FALSE:
                return new JsonPrimitive(false);
            case VALUE_NUMBER_INT:
                return new JsonPrimitive(parser.getNumberValue());
            case VALUE_NUMBER_FLOAT:
                return new JsonPrimitive(new BigDecimal(parser.getText()));
            case VALUE_NULL:
                return JsonNull.INSTANCE;
            default:
                throw new IllegalArgumentException("unsupported JSON token " + token);
        }
    }

    private static void write(JsonGenerator generator, JsonElement value) throws IOException {
        if (value.isNull()) {
            generator.writeNull();
            return;
        }
        if (value.isObject()) {
            generator.writeStartObject();
            for (Map.Entry<String, JsonElement> entry : value.asObject().entrySet()) {
                generator.writeFieldName(entry.getKey());
                write(generator, entry.getValue());
            }
            generator.writeEndObject();
            return;
        }
        if (value.isArray()) {
            generator.writeStartArray();
            for (JsonElement item : value.asArray()) write(generator, item);
            generator.writeEndArray();
            return;
        }
        Object primitive = value.asPrimitive().value();
        if (primitive instanceof Boolean) generator.writeBoolean((Boolean) primitive);
        else if (primitive instanceof Integer) generator.writeNumber((Integer) primitive);
        else if (primitive instanceof Long) generator.writeNumber((Long) primitive);
        else if (primitive instanceof BigDecimal) generator.writeNumber((BigDecimal) primitive);
        else if (primitive instanceof Number) generator.writeNumber(primitive.toString());
        else generator.writeString((String) primitive);
    }
}
