package net.kurobako.cef4j.http;

import com.google.auto.service.AutoService;
import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;
import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Routes {@code http}/{@code https} URLs through CEF's network stack. Registered via
 * {@code java.net.spi.URLStreamHandlerProvider} (ServiceLoader). Picked up automatically once this jar is on the
 * classpath - no explicit wiring required from consumers.
 */
@AutoService(URLStreamHandlerProvider.class)
@NullableBoundary("URLStreamHandlerProvider returns null for unsupported protocols")
public final class CefStreamHandlerProvider extends URLStreamHandlerProvider {

    @Override
    @Nullable
    public URLStreamHandler createURLStreamHandler(@Nullable String protocol) {
        if (protocol == null) return null;
        switch (protocol) {
            case "http":
                return new CefStreamHandler(CefUrlRequestHttpEngine.INSTANCE, 80);
            case "https":
                return new CefStreamHandler(CefUrlRequestHttpEngine.INSTANCE, 443);
            default:
                return null;
        }
    }
}
