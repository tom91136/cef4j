package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;
import java.util.ServiceLoader;
import org.junit.jupiter.api.Test;

class CefStreamHandlerProviderTest {

    @Test
    void createsHandlerForHttp() {
        URLStreamHandler h = new CefStreamHandlerProvider().createURLStreamHandler("http");
        assertThat(h).isInstanceOf(CefStreamHandler.class);
    }

    @Test
    void createsHandlerForHttps() {
        URLStreamHandler h = new CefStreamHandlerProvider().createURLStreamHandler("https");
        assertThat(h).isInstanceOf(CefStreamHandler.class);
    }

    @Test
    void returnsNullForUnsupportedProtocol() {
        assertThat(new CefStreamHandlerProvider().createURLStreamHandler("ftp")).isNull();
        assertThat(new CefStreamHandlerProvider().createURLStreamHandler("file"))
                .isNull();
        assertThat(new CefStreamHandlerProvider().createURLStreamHandler("")).isNull();
    }

    @Test
    void isDiscoverableViaServiceLoader() {
        // Verifies @AutoService wired META-INF/services correctly; if this fails,
        // the JAR will not be picked up by URL.openConnection() in real apps.
        boolean found = false;
        for (URLStreamHandlerProvider p : ServiceLoader.load(URLStreamHandlerProvider.class)) {
            if (p instanceof CefStreamHandlerProvider) {
                found = true;
                break;
            }
        }
        assertThat(found)
                .as("CefStreamHandlerProvider registered via ServiceLoader")
                .isTrue();
    }
}
