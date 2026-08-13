package net.kurobako.cef4j.webdriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Mutable JSON array independent of a concrete JSON library. */
public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
    private final List<JsonElement> values = new ArrayList<>();

    public void add(JsonElement value) {
        values.add(value == null ? JsonNull.INSTANCE : value);
    }

    public void add(String value) {
        add(value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public void add(Number value) {
        add(value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public void add(Boolean value) {
        add(value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public JsonElement get(int index) {
        return values.get(index);
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public Iterator<JsonElement> iterator() {
        return values.iterator();
    }

    @Override
    public JsonArray deepCopy() {
        JsonArray copy = new JsonArray();
        values.forEach(value -> copy.add(value.deepCopy()));
        return copy;
    }
}
