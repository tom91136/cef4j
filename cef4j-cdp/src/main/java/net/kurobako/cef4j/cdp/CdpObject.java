package net.kurobako.cef4j.cdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Mutable, codec-independent CDP value object. Setters store wire-form values. */
@NullableBoundary("CDP wire objects use null for absent optional fields")
public abstract class CdpObject {
    protected final Map<String, Object> values = new LinkedHashMap<>();

    protected CdpObject() {}

    protected CdpObject(Map<String, Object> values) {
        this.values.putAll(values);
    }

    public final Map<String, Object> toMap() {
        return values;
    }

    protected final void set(String name, @Nullable Object value) {
        if (value == null) values.remove(name);
        else values.put(name, json(value));
    }

    protected final Optional<Object> raw(String name) {
        return Optional.ofNullable(values.get(name));
    }

    protected final Object require(String name) {
        final Object value = values.get(name);
        if (value == null) throw new IllegalStateException("Missing required protocol field: " + name);
        return value;
    }

    @SuppressWarnings("unchecked")
    public static Optional<Map<String, Object>> objectMap(@Nullable Object value) {
        return Optional.ofNullable((Map<String, Object>) value);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> requireObjectMap(Object value) {
        return (Map<String, Object>) Objects.requireNonNull(value);
    }

    public static <T> Optional<List<T>> list(@Nullable Object value, Function<Object, T> mapper) {
        return Optional.ofNullable(value).map(source -> requireList(source, mapper));
    }

    public static <T> List<T> requireList(Object value, Function<Object, T> mapper) {
        final List<?> source = (List<?>) value;
        final List<T> result = new ArrayList<>(source.size());
        for (Object element : source) result.add(mapper.apply(element));
        return Collections.unmodifiableList(result);
    }

    public static Object json(Object value) {
        if (value instanceof CdpObject) return ((CdpObject) value).toMap();
        if (value instanceof CdpValue<?>) return ((CdpValue<?>) value).value();
        if (value instanceof List<?>) {
            final List<Object> result = new ArrayList<>(((List<?>) value).size());
            for (Object element : (List<?>) value) result.add(json(element));
            return result;
        }
        return value;
    }

    public static Optional<Long> numberAsLong(@Nullable Object value) {
        return Optional.ofNullable(value).map(number -> ((Number) number).longValue());
    }

    public static Optional<Double> numberAsDouble(@Nullable Object value) {
        return Optional.ofNullable(value).map(number -> ((Number) number).doubleValue());
    }

    @Override
    @SuppressWarnings("EqualsGetClass")
    public final boolean equals(@Nullable Object other) {
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
