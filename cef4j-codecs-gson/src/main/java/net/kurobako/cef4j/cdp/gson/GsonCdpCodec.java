package net.kurobako.cef4j.cdp.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import net.kurobako.cef4j.cdp.CdpCodec;

/**
 * Gson implementation of the codec-neutral {@link CdpCodec} contract. The default configuration keeps explicit
 * {@code null} members and decodes numbers as the Jackson codec does, so either library behaves the same.
 */
public final class GsonCdpCodec implements CdpCodec {
    private final Gson gson;

    public GsonCdpCodec() {
        this(new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setObjectToNumberStrategy(GsonCdpCodec::number)
                .create());
    }

    public GsonCdpCodec(Gson gson) {
        this.gson = Objects.requireNonNull(gson, "gson");
    }

    @Override
    public byte[] encode(Object value) {
        return gson.toJson(value).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object decode(byte[] json) {
        return gson.fromJson(new String(json, StandardCharsets.UTF_8), Object.class);
    }

    private static Number number(JsonReader reader) throws IOException {
        String text = reader.nextString();
        if (text.indexOf('.') >= 0 || text.indexOf('e') >= 0 || text.indexOf('E') >= 0) return new BigDecimal(text);
        BigInteger value = new BigInteger(text);
        if (value.bitLength() < Integer.SIZE) return value.intValue();
        if (value.bitLength() < Long.SIZE) return value.longValue();
        return value;
    }
}
