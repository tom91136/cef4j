package net.kurobako.cef4j.test.backend;

/** Known native-CEF compatibility boundaries shared by every contract adapter. */
public final class CefTestCompatibility {
    private CefTestCompatibility() {}

    public static int cefApiVersion() {
        String value = System.getProperty("cef.api.version", System.getProperty("cef4j.test.cefApiVersion"));
        if (value == null || value.isBlank()) value = System.getProperty("cef.version");
        if (value == null || value.isBlank()) return Integer.MAX_VALUE;
        int separator = value.indexOf('.');
        return Integer.parseInt((separator < 0 ? value : value.substring(0, separator)).trim());
    }

    public static boolean hasReliableNativeBrowserInfoHandshake(int apiVersion) {
        return apiVersion < 138 || apiVersion >= 142;
    }

    public static boolean supports(BrowserBackend backend) {
        boolean nativeBackend =
                BrowserBackend.NATIVE_NAME.equals(backend.name()) || "native-swing".equals(backend.name());
        return !nativeBackend || hasReliableNativeBrowserInfoHandshake(cefApiVersion());
    }
}
