package net.kurobako.cef4j.http;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import javax.annotation.Nonnull;

final class CefStreamHandler extends URLStreamHandler {

    private final CefHttpEngine engine;

    CefStreamHandler(@Nonnull CefHttpEngine engine) {
        this.engine = engine;
    }

    @Override
    @Nonnull
    protected URLConnection openConnection(@Nonnull URL url) {
        return new CefHttpURLConnection(url, engine);
    }

    @Override
    public int getDefaultPort() {
        return 80;
    }
}
