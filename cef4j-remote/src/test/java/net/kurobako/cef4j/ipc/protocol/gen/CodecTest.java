package net.kurobako.cef4j.ipc.protocol.gen;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefState;
import org.junit.jupiter.api.Test;

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
        SelfHandleUrlStringMessage original =
                new SelfHandleUrlStringMessage(frameHandle, "https://example.com/path?q=1");
        SelfHandleUrlStringMessage decoded = SelfHandleUrlStringMessage.DECODER.decode(encode(original));
        assertThat(decoded.url()).isEqualTo(original.url());
        assertThat(decoded.self().id()).isEqualTo(frameHandle.id());
    }

    @Test
    void frameLoadUrlEmptyRoundTrips() {
        SelfHandleUrlStringMessage decoded = SelfHandleUrlStringMessage.DECODER.decode(
                encode(new SelfHandleUrlStringMessage(new net.kurobako.cef4j.ipc.session.RemoteHandle(1), "")));
        assertThat(decoded.url()).isEmpty();
    }

    @Test
    void frameLoadUrlUnicodeRoundTrips() {
        SelfHandleUrlStringMessage decoded =
                SelfHandleUrlStringMessage.DECODER.decode(encode(new SelfHandleUrlStringMessage(
                        new net.kurobako.cef4j.ipc.session.RemoteHandle(1), "https://例え.test/路径")));
        assertThat(decoded.url()).isEqualTo("https://例え.test/路径");
    }

    @Test
    void lifeSpanOnAfterCreatedEventRoundTrip() {
        BrowserHandleMessage original = new BrowserHandleMessage(new net.kurobako.cef4j.ipc.session.RemoteHandle(7));
        BrowserHandleMessage decoded = BrowserHandleMessage.DECODER.decode(encode(original));
        assertThat(decoded.browser().id()).isEqualTo(7);
    }

    @Test
    void handWrittenMessageIdsAreStable() {
        assertThat(MessageIds.RELEASE_HANDLE_REQUEST).isEqualTo(6);
    }

    @Test
    void dataStructRoundTripRect() {
        CefRect original = new CefRect(10, 20, 800, 600);
        ByteBuffer buf = ByteBuffer.allocate(RectCodec.encodedSize(original)).order(ByteOrder.LITTLE_ENDIAN);
        RectCodec.encode(buf, original);
        buf.flip();
        assertThat(RectCodec.decode(buf)).isEqualTo(original);
    }

    @Test
    void dataStructRoundTripBrowserSettings() {
        CefBrowserSettings.Mutable settings = new CefBrowserSettings.Mutable();
        settings.windowlessFrameRate = 30;
        settings.fantasyFontFamily = "Times New Roman";
        settings.defaultEncoding = "UTF-8";
        settings.javascript = CefState.of(CefState.Kind.ENABLED);
        settings.defaultFontSize = 16;
        settings.defaultFixedFontSize = 13;
        settings.minimumLogicalFontSize = 6;
        settings.backgroundColor = 0xFFFFFF;

        CefBrowserSettings original = settings.toImmutable();
        ByteBuffer buf =
                ByteBuffer.allocate(BrowserSettingsCodec.encodedSize(original)).order(ByteOrder.LITTLE_ENDIAN);
        BrowserSettingsCodec.encode(buf, original);
        buf.flip();
        CefBrowserSettings decoded = BrowserSettingsCodec.decode(buf);
        assertThat(decoded.windowlessFrameRate).isEqualTo(30);
        assertThat(decoded.fantasyFontFamily).isEqualTo("Times New Roman");
        assertThat(decoded.defaultEncoding).isEqualTo("UTF-8");
        assertThat(decoded.javascript).isEqualTo(CefState.of(CefState.Kind.ENABLED));
        assertThat(decoded.backgroundColor).isEqualTo(0xFFFFFF);
        assertThat(decoded.standardFontFamily).isEmpty();
        assertThat(decoded.remoteFonts).isEqualTo(CefState.of(0));
    }
}
