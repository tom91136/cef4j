package net.kurobako.cef4j.http;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import javax.annotation.Nonnull;

final class CefStreamHandler extends URLStreamHandler {

    private final CefHttpEngine engine;
    private final int defaultPort;

    CefStreamHandler(@Nonnull CefHttpEngine engine) {
        this(engine, 80);
    }

    CefStreamHandler(@Nonnull CefHttpEngine engine, int defaultPort) {
        this.engine = engine;
        this.defaultPort = defaultPort;
    }

    @Override
    @Nonnull
    protected URLConnection openConnection(@Nonnull URL url) {
        return new CefHttpURLConnection(url, engine);
    }

    @Override
    public int getDefaultPort() {
        return defaultPort;
    }
}
