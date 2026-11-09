package net.kurobako.cef4j.cdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.function.Function;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Mutable, codec-independent CDP value object. Setters store wire-form values. */
public abstract class CdpObject {
    protected final Map<String, Object> values = new LinkedHashMap<>();

    protected CdpObject() {}

    protected CdpObject(Map<String, Object> values) {
        this.values.putAll(values);
    }

    public final Map<String, Object> toMap() {
        return values;
    }

    @NullableBoundary("CDP wire objects use null for absent optional fields")
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

    protected final <T> T require(CdpField<T> field) {
        return field.require(values);
    }

    protected final <T> Optional<T> optional(CdpField<T> field) {
        return field.optional(values);
    }

    protected final String requireString(String name) {
        return (String) require(name);
    }

    protected final boolean requireBoolean(String name) {
        return (Boolean) require(name);
    }

    protected final long requireLong(String name) {
        return ((Number) require(name)).longValue();
    }

    protected final double requireDouble(String name) {
        return ((Number) require(name)).doubleValue();
    }

    protected final Map<String, Object> requireObject(String name) {
        return requireObjectMap(require(name));
    }

    protected final Optional<String> optionalString(String name) {
        return raw(name).map(value -> (String) value);
    }

    protected final Optional<Boolean> optionalBoolean(String name) {
        return raw(name).map(value -> (Boolean) value);
    }

    protected final OptionalLong optionalLong(String name) {
        return numberAsLong(values.get(name)).map(OptionalLong::of).orElseGet(OptionalLong::empty);
    }

    protected final OptionalDouble optionalDouble(String name) {
        return numberAsDouble(values.get(name)).map(OptionalDouble::of).orElseGet(OptionalDouble::empty);
    }

    protected final Optional<Map<String, Object>> optionalObject(String name) {
        return objectMap(values.get(name));
    }

    @SuppressWarnings("unchecked")
    @NullableBoundary("CDP wire objects use null for absent optional fields")
    public static Optional<Map<String, Object>> objectMap(@Nullable Object value) {
        return Optional.ofNullable((Map<String, Object>) value);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> requireObjectMap(Object value) {
        return (Map<String, Object>) Objects.requireNonNull(value);
    }

    @NullableBoundary("CDP wire objects use null for absent optional fields")
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

    @NullableBoundary("CDP wire objects use null for absent optional fields")
    public static Optional<Long> numberAsLong(@Nullable Object value) {
        return Optional.ofNullable(value).map(number -> ((Number) number).longValue());
    }

    @NullableBoundary("CDP wire objects use null for absent optional fields")
    public static Optional<Double> numberAsDouble(@Nullable Object value) {
        return Optional.ofNullable(value).map(number -> ((Number) number).doubleValue());
    }

    @Override
    @SuppressWarnings("EqualsGetClass")
    @NullableBoundary("CDP wire objects use null for absent optional fields")
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
