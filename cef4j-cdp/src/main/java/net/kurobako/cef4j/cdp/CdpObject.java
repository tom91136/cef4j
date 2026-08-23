package net.kurobako.cef4j.cdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Mutable, codec-independent CDP value object. Setters store wire-form values. */
public abstract class CdpObject {
    protected final Map<String, Object> values = new LinkedHashMap<>();

    protected CdpObject() {}

    protected CdpObject(Map<String, Object> values) {
        this.values.putAll(values);
    }

    @Nonnull
    public final Map<String, Object> toMap() {
        return values;
    }

    @SuppressWarnings("NullableForbidden")
    protected final void set(String name, @Nullable Object value) {
        if (value == null) values.remove(name);
        else values.put(name, json(value));
    }

    @SuppressWarnings("NullableForbidden")
    @Nullable
    protected final Object raw(String name) {
        return values.get(name);
    }

    @Nonnull
    protected final Object require(String name) {
        Object value = values.get(name);
        if (value == null) throw new IllegalStateException("Missing required protocol field: " + name);
        return value;
    }

    @SuppressWarnings({"unchecked", "NullableForbidden"})
    @Nullable
    public static Map<String, Object> objectMap(@Nullable Object value) {
        return value == null ? null : (Map<String, Object>) value;
    }

    @SuppressWarnings("NullableForbidden")
    @Nullable
    public static <T> List<T> list(@Nullable Object value, Function<Object, T> mapper) {
        if (value == null) return null;
        List<?> source = (List<?>) value;
        List<T> result = new ArrayList<>(source.size());
        for (Object element : source) result.add(mapper.apply(element));
        return Collections.unmodifiableList(result);
    }

    @Nonnull
    public static <T> List<T> requireList(@Nonnull Object value, Function<Object, T> mapper) {
        List<?> source = (List<?>) value;
        List<T> result = new ArrayList<>(source.size());
        for (Object element : source) result.add(mapper.apply(element));
        return Collections.unmodifiableList(result);
    }

    @Nonnull
    public static Object json(@Nonnull Object value) {
        if (value instanceof CdpObject) return ((CdpObject) value).toMap();
        if (value instanceof CdpValue<?>) return ((CdpValue<?>) value).value();
        if (value instanceof List<?>) {
            List<Object> result = new ArrayList<>(((List<?>) value).size());
            for (Object element : (List<?>) value) result.add(json(element));
            return result;
        }
        return value;
    }

    @SuppressWarnings("NullableForbidden")
    @Nullable
    public static Long numberAsLong(@Nullable Object value) {
        return value == null ? null : ((Number) value).longValue();
    }

    @SuppressWarnings("NullableForbidden")
    @Nullable
    public static Double numberAsDouble(@Nullable Object value) {
        return value == null ? null : ((Number) value).doubleValue();
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
}
