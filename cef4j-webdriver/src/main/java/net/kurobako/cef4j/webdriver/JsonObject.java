package net.kurobako.cef4j.webdriver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/** Mutable insertion-ordered JSON object independent of a concrete JSON library. */
public final class JsonObject extends JsonElement {
    private final Map<String, JsonElement> values = new LinkedHashMap<>();

    public void add(String name, JsonElement value) {
        values.put(name, value == null ? JsonNull.INSTANCE : value);
    }

    public void addProperty(String name, String value) {
        add(name, value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public void addProperty(String name, Number value) {
        add(name, value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public void addProperty(String name, Boolean value) {
        add(name, value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    @SuppressWarnings("NullAway")
    public JsonElement get(String name) {
        return values.get(name);
    }

    @SuppressWarnings("NullAway")
    public JsonElement remove(String name) {
        return values.remove(name);
    }

    public boolean has(String name) {
        return values.containsKey(name);
    }

    public int size() {
        return values.size();
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return values.entrySet();
    }

    public Set<String> keySet() {
        return values.keySet();
    }

    public JsonObject object(String name) {
        return Objects.requireNonNull(values.get(name), name).asObject();
    }

    public JsonArray array(String name) {
        return Objects.requireNonNull(values.get(name), name).asArray();
    }

    @Override
    public JsonObject deepCopy() {
        JsonObject copy = new JsonObject();
        values.forEach((name, value) -> copy.add(name, value.deepCopy()));
        return copy;
    }
}
