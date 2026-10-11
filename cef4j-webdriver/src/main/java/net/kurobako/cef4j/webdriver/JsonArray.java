package net.kurobako.cef4j.webdriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Mutable JSON array independent of a concrete JSON library. */
@NullableBoundary("mirrors Gson: null values are stored as JsonNull")
public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
    private final List<JsonElement> values = new ArrayList<>();

    public void add(@Nullable JsonElement value) {
        values.add(value == null ? JsonNull.INSTANCE : value);
    }

    public void add(@Nullable String value) {
        add(value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public void add(@Nullable Number value) {
        add(value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
    }

    public void add(@Nullable Boolean value) {
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
