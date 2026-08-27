package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.kurobako.cef4j.gen.CefAcceleratedPaintInfo;
import net.kurobako.cef4j.gen.CefAcceleratedPaintInfoCommon;
import net.kurobako.cef4j.gen.CefColorType;
import net.kurobako.cef4j.gen.CefGlobals;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefSize;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CefV144PlusInteropTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        initCef(List.of("--disable-popup-blocking"));
    }

    @Test
    void globalsBase64Encode() {
        byte[] data = "Hello, CEF!".getBytes(StandardCharsets.UTF_8);
        ByteBuffer buf = ByteBuffer.allocateDirect(data.length);
        buf.put(data);
        buf.flip();

        assertThat(CefGlobals.base64Encode(buf)).isEqualTo(Optional.of("SGVsbG8sIENFRiE="));
    }

    @Test
    void renderHandlerOnAcceleratedPaintUsesTypedInfo() throws Exception {
        Method acceleratedPaintMethod = Arrays.stream(CefRenderHandler.class.getMethods())
                .filter(method -> method.getName().equals("onAcceleratedPaint"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Missing method CefRenderHandler.onAcceleratedPaint"));

        assertThat(acceleratedPaintMethod.getParameterTypes()[4]).isEqualTo(CefAcceleratedPaintInfo.class);
        assertThat(acceleratedPaintMethod.getParameterTypes()[1]).isEqualTo(CefPaintElementType.class);
        assertThat(acceleratedPaintMethod.getParameterTypes()[3]).isEqualTo(CefRect[].class);
    }

    @Test
    void acceleratedPaintInfoCrossPlatformTypesImplementSharedInterface() {
        CefAcceleratedPaintInfoCommon common = new CefAcceleratedPaintInfoCommon(
                1L,
                new CefSize(100, 100),
                new CefRect(0, 0, 100, 100),
                new CefRect(0, 0, 100, 100),
                new CefSize(100, 100),
                new CefRect(0, 0, 100, 100),
                new CefRect(0, 0, 100, 100),
                2L,
                1,
                1,
                1,
                1);
        CefColorType colorType = CefColorType.of(CefColorType.Kind.BGRA_8888);

        Object linuxInfo = new net.kurobako.cef4j.gen.linux.CefAcceleratedPaintInfo(1, 0L, colorType, common);
        Object macInfo = new net.kurobako.cef4j.gen.mac.CefAcceleratedPaintInfo(123L, colorType, common);
        Object winInfo = new net.kurobako.cef4j.gen.win.CefAcceleratedPaintInfo(456L, colorType, common);

        assertThat(linuxInfo).isInstanceOf(CefAcceleratedPaintInfo.class);
        assertThat(macInfo).isInstanceOf(CefAcceleratedPaintInfo.class);
        assertThat(winInfo).isInstanceOf(CefAcceleratedPaintInfo.class);
        assertThat(linuxInfo.getClass().getName()).contains(".gen.linux.");
        assertThat(macInfo.getClass().getName()).contains(".gen.mac.");
        assertThat(winInfo.getClass().getName()).contains(".gen.win.");
    }
}
