package net.kurobako.cef4j.webdriver.gson;

import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import net.kurobako.cef4j.webdriver.JsonArray;
import net.kurobako.cef4j.webdriver.JsonElement;
import net.kurobako.cef4j.webdriver.JsonNull;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.JsonPrimitive;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;

/** Gson implementation of the WebDriver JSON codec SPI. */
public final class GsonWebDriverJsonCodec implements WebDriverJsonCodec {
    private final Gson gson;

    public GsonWebDriverJsonCodec() {
        this(new Gson());
    }

    public GsonWebDriverJsonCodec(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "gson");
    }

    @Override
    public JsonElement decode(byte[] json) {
        return fromGson(gson.fromJson(new String(json, StandardCharsets.UTF_8), com.google.gson.JsonElement.class));
    }

    @Override
    public byte[] encode(JsonElement value) {
        return toGson(value).toString().getBytes(StandardCharsets.UTF_8);
    }

    private static JsonElement fromGson(com.google.gson.JsonElement value) {
        if (value == null || value.isJsonNull()) return JsonNull.INSTANCE;
        if (value.isJsonObject()) {
            JsonObject result = new JsonObject();
            for (Map.Entry<String, com.google.gson.JsonElement> entry :
                    value.getAsJsonObject().entrySet()) result.add(entry.getKey(), fromGson(entry.getValue()));
            return result;
        }
        if (value.isJsonArray()) {
            JsonArray result = new JsonArray();
            for (com.google.gson.JsonElement item : value.getAsJsonArray()) result.add(fromGson(item));
            return result;
        }
        com.google.gson.JsonPrimitive primitive = value.getAsJsonPrimitive();
        if (primitive.isBoolean()) return new JsonPrimitive(primitive.getAsBoolean());
        if (primitive.isNumber()) return new JsonPrimitive(primitive.getAsNumber());
        return new JsonPrimitive(primitive.getAsString());
    }

    private static com.google.gson.JsonElement toGson(JsonElement value) {
        if (value.isNull()) return com.google.gson.JsonNull.INSTANCE;
        if (value.isObject()) {
            com.google.gson.JsonObject result = new com.google.gson.JsonObject();
            for (Map.Entry<String, JsonElement> entry : value.asObject().entrySet())
                result.add(entry.getKey(), toGson(entry.getValue()));
            return result;
        }
        if (value.isArray()) {
            com.google.gson.JsonArray result = new com.google.gson.JsonArray();
            for (JsonElement item : value.asArray()) result.add(toGson(item));
            return result;
        }
        Object primitive = value.asPrimitive().value();
        if (primitive instanceof Boolean) return new com.google.gson.JsonPrimitive((Boolean) primitive);
        if (primitive instanceof Number) return new com.google.gson.JsonPrimitive((Number) primitive);
        return new com.google.gson.JsonPrimitive((String) primitive);
    }
}
