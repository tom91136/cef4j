package net.kurobako.cef4j.http;

import com.google.auto.service.AutoService;
import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;
import javax.annotation.Nullable;

/**
 * Routes {@code http}/{@code https} URLs through CEF's network stack. Registered via
 * {@code java.net.spi.URLStreamHandlerProvider} (ServiceLoader). Picked up automatically once this jar is on the
 * classpath - no explicit wiring required from consumers.
 */
@AutoService(URLStreamHandlerProvider.class)
public final class CefStreamHandlerProvider extends URLStreamHandlerProvider {

    @Override
    // JDK contract: null protocol/result
    @SuppressWarnings("NullableForbidden")
    @Nullable
    public URLStreamHandler createURLStreamHandler(@Nullable String protocol) {
        if (protocol == null) return null;
        switch (protocol) {
            case "http":
            case "https":
                return new CefStreamHandler(CefUrlRequestHttpEngine.INSTANCE);
            default:
                return null;
        }
    }
}
