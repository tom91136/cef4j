package net.kurobako.cef4j.cdp.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

final class JacksonCdpCodecTest {
    @Test
    void returnsOnlyJsonCompatibleJdkValues() {
        JacksonCdpCodec codec = new JacksonCdpCodec();
        Object decoded = codec.decode(codec.encode(Map.of("name", "cef4j", "values", List.of(1, true))));
        assertThat(decoded).isInstanceOf(Map.class);
        assertThat(((Map<?, ?>) decoded).get("values")).isInstanceOf(List.class);
    }
}
