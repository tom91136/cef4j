package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.junit.jupiter.api.Test;

class CefStreamHandlerTest {

    @Test
    void openConnectionReturnsCefHttpURLConnection() throws Exception {
        CefStreamHandler h = new CefStreamHandler(FakeCefHttpEngine.empty());
        URL url = new URL("http", "example.com", -1, "/", h);
        URLConnection conn = url.openConnection();
        assertThat(conn).isInstanceOf(HttpURLConnection.class);
        assertThat(conn).isInstanceOf(CefHttpURLConnection.class);
        assertThat(conn.getURL()).isEqualTo(url);
    }

    @Test
    void defaultPortIsEighty() {
        assertThat(new CefStreamHandler(FakeCefHttpEngine.empty()).getDefaultPort())
                .isEqualTo(80);
    }
}
