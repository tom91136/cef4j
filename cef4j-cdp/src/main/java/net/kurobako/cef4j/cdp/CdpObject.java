package net.kurobako.cef4j.cdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Immutable, codec-independent CDP value object. */
public abstract class CdpObject {
    private final Map<String, Object> values;

    protected CdpObject(Map<String, Object> values) {
        this.values = immutableMap(values);
    }

    @Nonnull
    public final Map<String, Object> toMap() {
        return values;
    }

    @Override
    @SuppressWarnings("EqualsGetClass")
    public final boolean equals(Object other) {
        return this == other
                || (other != null && getClass() == other.getClass() && values.equals(((CdpObject) other).values));
    }

    @Override
    public final int hashCode() {
        return values.hashCode();
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName() + values;
    }

    @Nullable
    protected final Object value(String name) {
        return values.get(name);
    }

    @Nullable
    protected final String string(String name) {
        return (String) value(name);
    }

    @Nullable
    protected final Boolean bool(String name) {
        return (Boolean) value(name);
    }

    @Nullable
    protected final Long integer(String name) {
        Number number = (Number) value(name);
        return number == null ? null : number.longValue();
    }

    @Nullable
    protected final Double number(String name) {
        Number number = (Number) value(name);
        return number == null ? null : number.doubleValue();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    protected static Map<String, Object> objectMap(@Nullable Object value) {
        return value == null ? null : (Map<String, Object>) value;
    }

    @Nullable
    protected static <T> List<T> list(@Nullable Object value, Function<Object, T> mapper) {
        if (value == null) return null;
        List<?> source = (List<?>) value;
        List<T> result = new ArrayList<>(source.size());
        for (Object element : source) result.add(mapper.apply(element));
        return Collections.unmodifiableList(result);
    }

    @Nullable
    protected static Object jsonValue(@Nullable Object value) {
        if (value instanceof CdpObject) return ((CdpObject) value).toMap();
        if (value instanceof List<?>) {
            List<Object> result = new ArrayList<>(((List<?>) value).size());
            for (Object element : (List<?>) value) result.add(jsonValue(element));
            return result;
        }
        return value;
    }

    private static Map<String, Object> immutableMap(Map<String, Object> source) {
        Map<String, Object> copy = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            copy.put(entry.getKey(), immutableValue(entry.getValue()));
        }
        return Collections.unmodifiableMap(copy);
    }

    private static Object immutableValue(Object value) {
        if (value instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) value;
            return immutableMap(map);
        }
        if (value instanceof List<?>) {
            List<Object> copy = new ArrayList<>(((List<?>) value).size());
            for (Object element : (List<?>) value) copy.add(immutableValue(element));
            return Collections.unmodifiableList(copy);
        }
        return value;
    }
}
