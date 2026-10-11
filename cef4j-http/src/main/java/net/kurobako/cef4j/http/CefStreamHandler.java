package net.kurobako.cef4j.http;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

final class CefStreamHandler extends URLStreamHandler {

    private final CefHttpEngine engine;
    private final int defaultPort;

    CefStreamHandler(CefHttpEngine engine) {
        this(engine, 80);
    }

    CefStreamHandler(CefHttpEngine engine, int defaultPort) {
        this.engine = engine;
        this.defaultPort = defaultPort;
    }

    @Override
    protected URLConnection openConnection(URL url) {
        return new CefHttpURLConnection(url, engine);
    }

    @Override
    public int getDefaultPort() {
        return defaultPort;
    }
}
