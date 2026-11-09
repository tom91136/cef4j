package net.kurobako.cef4j.cdp;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.jspecify.annotations.Nullable;

/** Typed name of a CDP wire field together with the conversion from its wire form. */
public final class CdpField<T> {
    private final String name;
    private final Function<Object, T> decoder;

    private CdpField(String name, Function<Object, T> decoder) {
        this.name = name;
        this.decoder = decoder;
    }

    public static <T> CdpField<T> of(String name, Function<Object, T> decoder) {
        return new CdpField<>(name, decoder);
    }

    public T require(Map<String, Object> values) {
        final @Nullable Object value = values.get(name);
        if (value == null) throw new IllegalStateException("Missing required protocol field: " + name);
        return Objects.requireNonNull(decoder.apply(value));
    }

    public Optional<T> optional(Map<String, Object> values) {
        return Optional.ofNullable(values.get(name)).map(decoder);
    }
}
