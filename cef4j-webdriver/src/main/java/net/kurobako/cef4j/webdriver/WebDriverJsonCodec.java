package net.kurobako.cef4j.webdriver;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Serialization boundary for WebDriver HTTP and CDP JSON, layered over a {@link CdpCodec}. The codec must keep explicit
 * {@code null} object members, which WebDriver responses rely on.
 */
public final class WebDriverJsonCodec {
    private final CdpCodec codec;

    public WebDriverJsonCodec(@Nonnull CdpCodec codec) {
        this.codec = Objects.requireNonNull(codec, "codec");
    }

    @Nonnull
    public CdpCodec cdpCodec() {
        return codec;
    }

    @Nonnull
    public JsonElement decode(@Nonnull byte[] json) {
        return toJsonElement(codec.decode(json));
    }

    @Nonnull
    public JsonElement decode(@Nonnull String json) {
        return decode(json.getBytes(StandardCharsets.UTF_8));
    }

    @Nonnull
    public byte[] encode(@Nonnull JsonElement value) {
        Object raw = fromJsonElement(value);
        return raw == null ? "null".getBytes(StandardCharsets.UTF_8) : codec.encode(raw);
    }

    /** Wraps the installed {@link CdpCodec} provider. Prefer explicit injection when multiple codecs are present. */
    @Nonnull
    public static WebDriverJsonCodec installed() {
        Iterator<CdpCodec> providers = ServiceLoader.load(CdpCodec.class).iterator();
        if (!providers.hasNext()) {
            throw new IllegalStateException("No JSON codec installed; add cef4j-codecs-gson or cef4j-codecs-jackson");
        }
        CdpCodec provider = providers.next();
        if (providers.hasNext()) {
            throw new IllegalStateException("Multiple JSON codecs installed ("
                    + provider.getClass().getName() + ", "
                    + providers.next().getClass().getName()
                    + "); supply one explicitly");
        }
        return new WebDriverJsonCodec(provider);
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
