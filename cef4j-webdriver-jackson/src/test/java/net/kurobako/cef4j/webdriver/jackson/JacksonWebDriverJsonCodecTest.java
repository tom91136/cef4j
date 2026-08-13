package net.kurobako.cef4j.webdriver.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import net.kurobako.cef4j.webdriver.JsonElement;
import org.junit.jupiter.api.Test;

final class JacksonWebDriverJsonCodecTest {
    @Test
    void roundTripsWebDriverValues() {
        JacksonWebDriverJsonCodec codec = new JacksonWebDriverJsonCodec();
        JsonElement value =
                codec.decode("{\"text\":\"hello\",\"n\":42,\"items\":[true,null]}".getBytes(StandardCharsets.UTF_8));
        JsonElement decoded = codec.decode(codec.encode(value));
        assertThat(decoded.asObject().get("text").string()).isEqualTo("hello");
        assertThat(decoded.asObject().get("n").intValue()).isEqualTo(42);
        assertThat(decoded.asObject().array("items").get(1).isNull()).isTrue();
    }
}
