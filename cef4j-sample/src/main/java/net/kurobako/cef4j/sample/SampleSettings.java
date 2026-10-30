package net.kurobako.cef4j.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.kurobako.cef4j.gen.CefSettings;

final class SampleSettings {

    private SampleSettings() {}

    static CefSettings.Mutable newSettings(String cacheDirPrefix) throws IOException {
        final Path cacheDir = Files.createTempDirectory(cacheDirPrefix);
        cacheDir.toFile().deleteOnExit();

        final CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        return settings;
    }
}
