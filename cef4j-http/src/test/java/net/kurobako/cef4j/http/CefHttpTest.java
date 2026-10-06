package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.net.spi.URLStreamHandlerProvider;
import java.util.ServiceLoader;
import org.junit.jupiter.api.Test;

class CefHttpTest {

    @Test
    void createsHandlerForHttp() {
        URLStreamHandler h = CefHttp.handler("http");
        assertThat(h).isInstanceOf(CefStreamHandler.class);
        assertThat(((CefStreamHandler) h).getDefaultPort()).isEqualTo(80);
    }

    @Test
    void createsHandlerForHttps() {
        URLStreamHandler h = CefHttp.handler("https");
        assertThat(h).isInstanceOf(CefStreamHandler.class);
        assertThat(((CefStreamHandler) h).getDefaultPort()).isEqualTo(443);
    }

    @Test
    void rejectsUnsupportedProtocol() {
        assertThatThrownBy(() -> CefHttp.handler("ftp")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void factoryDefersOtherProtocolsToTheJdk() {
        URLStreamHandlerFactory factory = CefHttp.factory();
        assertThat(factory.createURLStreamHandler("http")).isInstanceOf(CefStreamHandler.class);
        assertThat(factory.createURLStreamHandler("https")).isInstanceOf(CefStreamHandler.class);
        assertThat(factory.createURLStreamHandler("ftp")).isNull();
        assertThat(factory.createURLStreamHandler("file")).isNull();
    }

    @Test
    void isNotRegisteredImplicitly() {
        for (URLStreamHandlerProvider provider : ServiceLoader.load(URLStreamHandlerProvider.class)) {
            assertThat(provider.getClass().getPackageName()).isNotEqualTo(CefHttp.class.getPackageName());
        }
    }
}
