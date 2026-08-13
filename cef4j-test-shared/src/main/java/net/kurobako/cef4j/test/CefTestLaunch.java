package net.kurobako.cef4j.test;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

/** Shared hosted-test launch argument handling for native GUI backends. */
public final class CefTestLaunch {
    @Nonnull
    public static List<String> extraArgs() {
        List<String> args = new ArrayList<>();
        String ozone = System.getProperty("cef4j.test.ozonePlatform");
        if (ozone != null && !ozone.isBlank()) args.add("--ozone-platform=" + ozone.trim());
        String extra = System.getProperty("cef4j.test.extraArgs");
        if (extra != null && !extra.isBlank()) {
            java.util.regex.Pattern.compile(",")
                    .splitAsStream(extra)
                    .map(String::trim)
                    .filter(value -> !value.isEmpty())
                    .forEach(args::add);
        }
        return args;
    }

    private CefTestLaunch() {}
}
