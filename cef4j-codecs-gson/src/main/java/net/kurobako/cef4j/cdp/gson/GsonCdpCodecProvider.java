package net.kurobako.cef4j.cdp.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpCodec;

/**
 * Installed Gson {@link CdpCodec} provider. Unlike the default {@link GsonCdpCodec}, it keeps explicit {@code null}
 * members and decodes numbers exactly as the Jackson codec does, so WebDriver and Remote CEF behave the same with
 * either library.
 */
public final class GsonCdpCodecProvider implements CdpCodec {
    private static final CdpCodec CODEC = new GsonCdpCodec(new GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .setObjectToNumberStrategy(GsonCdpCodecProvider::number)
            .create());

    @Override
    public byte[] encode(Object value) {
        return CODEC.encode(value);
    }

    @Override
    @Nullable
    public Object decode(byte[] json) {
        return CODEC.decode(json);
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
