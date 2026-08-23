package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import net.kurobako.cef4j.gen.CefRequest;
import org.junit.jupiter.api.Test;

class UrlResourceHandlerTest {

    @Test
    void closesStreamAtEof() throws Exception {
        CloseTrackingStream stream = new CloseTrackingStream(new byte[] {1});
        UrlResourceHandler handler = handler(stream);

        open(handler);
        assertThat(handler.read(ByteBuffer.allocate(1), new int[1], null)).isTrue();
        assertThat(handler.read(ByteBuffer.allocate(1), new int[1], null)).isFalse();

        assertThat(stream.closeCount()).isOne();
    }

    @Test
    void closesStreamAfterReadFailure() throws Exception {
        CloseTrackingStream stream = new CloseTrackingStream(new byte[] {1}) {
            @Override
            public int read(byte[] bytes) throws IOException {
                throw new IOException("failed");
            }
        };
        UrlResourceHandler handler = handler(stream);

        open(handler);
        assertThat(handler.read(ByteBuffer.allocate(1), new int[1], null)).isFalse();

        assertThat(stream.closeCount()).isOne();
    }

    @Test
    void closesPreviousStreamBeforeReopenAndClosesOnCancel() throws Exception {
        CloseTrackingStream first = new CloseTrackingStream(new byte[] {1});
        CloseTrackingStream second = new CloseTrackingStream(new byte[] {2});
        AtomicInteger opens = new AtomicInteger();
        UrlResourceHandler handler =
                new UrlResourceHandler(url -> connection(opens.getAndIncrement() == 0 ? first : second));

        open(handler);
        open(handler);
        handler.cancel();

        assertThat(first.closeCount()).isOne();
        assertThat(second.closeCount()).isOne();
    }

    private static UrlResourceHandler handler(InputStream stream) {
        return new UrlResourceHandler(url -> connection(stream));
    }

    private static void open(UrlResourceHandler handler) {
        CefRequest request = mock(CefRequest.class);
        when(request.getUrl()).thenReturn(Optional.of("test:data"));
        assertThat(handler.open(request, new int[1], null)).isTrue();
    }

    private static URLConnection connection(InputStream stream) throws IOException {
        return new URLConnection(new URL("file:/test")) {
            @Override
            public void connect() {}

            @Override
            public String getContentType() {
                return "application/octet-stream";
            }

            @Override
            public InputStream getInputStream() {
                return stream;
            }
        };
    }

    private static class CloseTrackingStream extends ByteArrayInputStream {
        private int closeCount;

        private CloseTrackingStream(byte[] bytes) {
            super(bytes);
        }

        @Override
        public void close() throws IOException {
            closeCount++;
            super.close();
        }

        private int closeCount() {
            return closeCount;
        }
    }
}
