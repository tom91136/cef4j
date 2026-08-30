package net.kurobako.cef4j.webdriver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.policy.NullableBoundary;

/** Bridges the codec-neutral {@link net.kurobako.cef4j.cdp.CdpClient} onto the installed {@link WebDriverJsonCodec}. */
public final class WebDriverCdpCodec implements CdpCodec {
    private final WebDriverJsonCodec delegate;

    public WebDriverCdpCodec(@Nonnull WebDriverJsonCodec delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    @Override
    public byte[] encode(Object value) {
        return delegate.encode(toJsonElement(value));
    }

    @Override
    @Nullable
    public Object decode(byte[] json) {
        return fromJsonElement(delegate.decode(json));
    }

    @NullableBoundary("JSON null maps to the JDK null wire value")
    @Nonnull
    static JsonElement toJsonElement(@Nullable Object value) {
        if (value == null) return JsonNull.INSTANCE;
        if (value instanceof JsonElement) return (JsonElement) value;
        if (value instanceof Map) {
            JsonObject object = new JsonObject();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                object.add(String.valueOf(entry.getKey()), toJsonElement(entry.getValue()));
            }
            return object;
        }
        if (value instanceof List) {
            JsonArray array = new JsonArray();
            for (Object item : (List<?>) value) array.add(toJsonElement(item));
            return array;
        }
        if (value instanceof String) return new JsonPrimitive((String) value);
        if (value instanceof Boolean) return new JsonPrimitive((Boolean) value);
        if (value instanceof Number) return new JsonPrimitive((Number) value);
        throw new IllegalArgumentException("unsupported JSON value: " + value.getClass());
    }

    @Nullable
    static Object fromJsonElement(@Nonnull JsonElement value) {
        if (value.isNull()) return null;
        if (value.isObject()) {
            Map<String, Object> object = new LinkedHashMap<>();
            for (Map.Entry<String, JsonElement> entry : value.asObject().entrySet()) {
                object.put(entry.getKey(), fromJsonElement(entry.getValue()));
            }
            return object;
        }
        if (value.isArray()) {
            List<Object> array = new ArrayList<>();
            for (JsonElement item : value.asArray()) array.add(fromJsonElement(item));
            return array;
        }
        return value.asPrimitive().value();
    }
}
