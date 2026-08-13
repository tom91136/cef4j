package net.kurobako.cef4j.remote;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Build-time Remote CEF protocol compatibility metadata. */
public final class RemoteApiCompatibility {
    private static final int CEF_API_VERSION = loadCefApiVersion();

    private RemoteApiCompatibility() {}

    public static int cefApiVersion() {
        return CEF_API_VERSION;
    }

    private static int loadCefApiVersion() {
        Properties properties = new Properties();
        try (InputStream input = RemoteApiCompatibility.class.getResourceAsStream("version.properties")) {
            if (input == null) throw new IllegalStateException("missing Remote CEF version metadata");
            properties.load(input);
            return Integer.parseInt(properties.getProperty("cef.api.version"));
        } catch (IOException | NumberFormatException failure) {
            throw new IllegalStateException("invalid Remote CEF version metadata", failure);
        }
    }
}
