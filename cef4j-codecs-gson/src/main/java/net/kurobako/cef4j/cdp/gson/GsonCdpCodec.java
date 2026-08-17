package net.kurobako.cef4j.cdp.gson;

import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import net.kurobako.cef4j.cdp.CdpCodec;

/** Gson implementation of the codec-neutral {@link CdpCodec} contract. */
public final class GsonCdpCodec implements CdpCodec {
    private final Gson gson;

    public GsonCdpCodec() {
        this(new Gson());
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
}
