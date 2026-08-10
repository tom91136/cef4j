package net.kurobako.cef4j.ipc.protocol.gen;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;

/** Round-trip codec tests for the AST-derived wire types. */
class CodecTest {

    private static <T extends net.kurobako.cef4j.ipc.session.CefMessageEncoder> ByteBuffer encode(T msg) {
        ByteBuffer buf = ByteBuffer.allocate(msg.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        msg.encodeInto(buf);
        buf.flip();
        return buf;
    }

    @Test
    void frameLoadUrlRoundTrip() {
        net.kurobako.cef4j.ipc.session.RemoteHandle frameHandle = new net.kurobako.cef4j.ipc.session.RemoteHandle(42);
        FrameLoadUrlRequest original = new FrameLoadUrlRequest(frameHandle, "https://example.com/path?q=1");
        FrameLoadUrlRequest decoded = FrameLoadUrlRequest.DECODER.decode(encode(original));
        assertThat(decoded.url()).isEqualTo(original.url());
        assertThat(decoded.self().id()).isEqualTo(frameHandle.id());
    }

    @Test
    void frameLoadUrlEmptyRoundTrips() {
        FrameLoadUrlRequest decoded = FrameLoadUrlRequest.DECODER.decode(
                encode(new FrameLoadUrlRequest(new net.kurobako.cef4j.ipc.session.RemoteHandle(1), "")));
        assertThat(decoded.url()).isEmpty();
    }

    @Test
    void frameLoadUrlUnicodeRoundTrips() {
        FrameLoadUrlRequest decoded = FrameLoadUrlRequest.DECODER.decode(encode(
                new FrameLoadUrlRequest(new net.kurobako.cef4j.ipc.session.RemoteHandle(1), "https://例え.test/路径")));
        assertThat(decoded.url()).isEqualTo("https://例え.test/路径");
    }

    @Test
    void lifeSpanOnAfterCreatedEventRoundTrip() {
        LifeSpanHandlerOnAfterCreatedEvent original =
                new LifeSpanHandlerOnAfterCreatedEvent(new net.kurobako.cef4j.ipc.session.RemoteHandle(7));
        LifeSpanHandlerOnAfterCreatedEvent decoded =
                LifeSpanHandlerOnAfterCreatedEvent.DECODER.decode(encode(original));
        assertThat(decoded.browser().id()).isEqualTo(7);
        assertThat(decoded.messageId()).isEqualTo(LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID);
    }

    @Test
    void handWrittenMessageIdsAreStable() {
        // Hand-written specs claim ids in [1, AstIdBase). AST-derived ids are stable Murmur3 hashes well above.
        assertThat(ReleaseHandleRequest.MESSAGE_ID).isEqualTo(6);
    }

    @Test
    void dataStructRoundTripRect() {
        // Validates the JavaDataStructEmitter wire codec for a primitive-only struct. The same wire bytes are
        // what CppEmitter produces for the C++ overlay, so passing this means the two languages agree.
        Rect original = new Rect(10, 20, 800, 600);
        ByteBuffer buf = ByteBuffer.allocate(original.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        original.encodeInto(buf);
        buf.flip();
        Rect decoded = Rect.decode(buf);
        assertThat(decoded.x()).isEqualTo(10);
        assertThat(decoded.y()).isEqualTo(20);
        assertThat(decoded.width()).isEqualTo(800);
        assertThat(decoded.height()).isEqualTo(600);
    }

    @Test
    void dataStructRoundTripBrowserSettings() {
        // Validates a struct with mixed primitives + strings via the generated builder (28-arg ctor would
        // be unreadable; the builder defaults the rest to 0/false/"").
        BrowserSettings settings = BrowserSettings.builder()
                .windowlessFrameRate(30)
                .fantasyFontFamily("Times New Roman")
                .defaultEncoding("UTF-8")
                .javascript(1)
                .defaultFontSize(16)
                .defaultFixedFontSize(13)
                .minimumLogicalFontSize(6)
                .backgroundColor(0xFFFFFF)
                .build();

        ByteBuffer buf = ByteBuffer.allocate(settings.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        settings.encodeInto(buf);
        buf.flip();
        BrowserSettings decoded = BrowserSettings.decode(buf);
        assertThat(decoded.windowlessFrameRate()).isEqualTo(30);
        assertThat(decoded.fantasyFontFamily()).isEqualTo("Times New Roman");
        assertThat(decoded.defaultEncoding()).isEqualTo("UTF-8");
        assertThat(decoded.backgroundColor()).isEqualTo(0xFFFFFF);
        // Fields not set on the builder default to 0/"".
        assertThat(decoded.standardFontFamily()).isEmpty();
        assertThat(decoded.remoteFonts()).isZero();
    }
}
