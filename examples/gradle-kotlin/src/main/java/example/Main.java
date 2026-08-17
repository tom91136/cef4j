package example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.cdp.CdpSchema;

public final class Main {
    private Main() {}

    public static void main(String[] args) throws Exception {
        String platform = OS.platform();
        String manifest = "cef-runtime/" + platform + "/file-list.txt";
        try (InputStream stream = Main.class.getClassLoader().getResourceAsStream(manifest)) {
            if (stream == null) throw new IllegalStateException("missing packaged CEF manifest " + manifest);
            long files = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .lines()
                    .filter(line -> !line.isBlank())
                    .count();
            if (files == 0) throw new IllegalStateException("empty packaged CEF manifest " + manifest);
            System.out.printf(
                    "cef4j packaged %d CEF files for %s; generated CDP targets Chromium %s%n",
                    files, platform, CdpSchema.chromiumVersion());
        }
    }
}
