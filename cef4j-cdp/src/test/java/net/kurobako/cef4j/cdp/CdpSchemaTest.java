package net.kurobako.cef4j.cdp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.security.MessageDigest;
import org.junit.jupiter.api.Test;

final class CdpSchemaTest {
    @Test
    void bundledSchemaMatchesRecordedFingerprint() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("META-INF/cef4j/cdp/protocol.json")) {
            assertThat(stream).isNotNull();
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(stream.readAllBytes());
            assertThat(hex(digest)).isEqualTo(CdpSchema.sha256());
        }
        assertThat(CdpSchema.chromiumVersion()).isEqualTo("146.0.7680.165");
    }

    private static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder(bytes.length * 2);
        for (byte value : bytes) result.append(String.format("%02x", value & 0xff));
        return result.toString();
    }
}
