package net.kurobako.cef4j.cdp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.CompletionStage;
import net.kurobako.cef4j.cdp.generated.Browser;

/** Metadata and opt-in compatibility checks for the bundled protocol schema. */
public final class CdpSchema {
    private static final String RESOURCE = "META-INF/cef4j/cdp/schema.properties";
    private static final Properties PROPERTIES = load();

    private CdpSchema() {}

    public static String chromiumVersion() {
        return PROPERTIES.getProperty("chromium.version");
    }

    public static String v8Revision() {
        return PROPERTIES.getProperty("v8.revision");
    }

    /**
     * Queries {@code Browser.getVersion} and rejects a Chromium build different from the generated schema. This uses
     * the existing CDP channel; it never launches CEF or enables a debugging port.
     */
    public static CompletionStage<Browser.GetVersionResult> requireExactVersion(CdpClient client) {
        Objects.requireNonNull(client, "client");
        return client.domains().browser().getVersion().thenApply(result -> {
            String product = result.product();
            if (!product.endsWith("/" + chromiumVersion())) {
                throw new CdpVersionMismatchException(chromiumVersion(), product);
            }
            return result;
        });
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream stream = CdpSchema.class.getClassLoader().getResourceAsStream(RESOURCE)) {
            if (stream == null) throw new IllegalStateException("Missing bundled CDP schema metadata");
            properties.load(stream);
            return properties;
        } catch (IOException failure) {
            throw new ExceptionInInitializerError(failure);
        }
    }
}
