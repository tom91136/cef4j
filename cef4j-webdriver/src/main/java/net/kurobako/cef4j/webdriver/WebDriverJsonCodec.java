package net.kurobako.cef4j.webdriver;

import java.nio.charset.StandardCharsets;
import java.util.ServiceLoader;
import javax.annotation.Nonnull;

/** Serialization boundary for WebDriver HTTP and CDP JSON. */
public interface WebDriverJsonCodec {
    @Nonnull
    JsonElement decode(@Nonnull byte[] json);

    @Nonnull
    byte[] encode(@Nonnull JsonElement value);

    @Nonnull
    default JsonElement decode(@Nonnull String json) {
        return decode(json.getBytes(StandardCharsets.UTF_8));
    }

    /** Returns the first installed provider. Prefer explicit injection when multiple codecs are present. */
    @Nonnull
    static WebDriverJsonCodec installed() {
        return ServiceLoader.load(WebDriverJsonCodec.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No WebDriver JSON codec installed; add cef4j-webdriver-gson or cef4j-webdriver-jackson"));
    }
}
