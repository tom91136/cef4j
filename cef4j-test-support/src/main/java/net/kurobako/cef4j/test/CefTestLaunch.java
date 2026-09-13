package net.kurobako.cef4j.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

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

    /** Sets the cache root on CEF versions that expose the setting (CEF 74+). */
    public static void setRootCachePath(Object settings, String path) {
        try {
            Field field = settings.getClass().getField("rootCachePath");
            field.set(settings, path);
        } catch (NoSuchFieldException ignored) {
            // Older CEF versions use cachePath as the root and do not expose rootCachePath.
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to set CEF root cache path", e);
        }
    }

    private CefTestLaunch() {}
}
