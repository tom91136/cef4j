package net.kurobako.cef4j.webdriver;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
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

    /** Returns the installed provider. Prefer explicit injection when multiple codecs are present. */
    @Nonnull
    static WebDriverJsonCodec installed() {
        Iterator<WebDriverJsonCodec> providers =
                ServiceLoader.load(WebDriverJsonCodec.class).iterator();
        if (!providers.hasNext()) {
            throw new IllegalStateException(
                    "No WebDriver JSON codec installed; add cef4j-codecs-gson or cef4j-codecs-jackson");
        }
        WebDriverJsonCodec provider = providers.next();
        if (providers.hasNext()) {
            throw new IllegalStateException("Multiple WebDriver JSON codecs installed ("
                    + provider.getClass().getName() + ", "
                    + providers.next().getClass().getName()
                    + "); supply one explicitly");
        }
        return provider;
    }
}
