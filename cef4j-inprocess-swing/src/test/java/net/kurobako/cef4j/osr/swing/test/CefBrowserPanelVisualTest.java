package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelVisualTest extends SwingBrowserPanelTestBase {
    private static final CefPaintElementType VIEW = CefPaintElementType.of(CefPaintElementType.Kind.VIEW);
    private static final int SIZE = 4;
    private static final int RED = 0xFFAA0000;
    private static final int GREEN = 0xFF00AA00;

    @Test
    void frameDroppedByBackPressureIsRequestedAgain() throws Exception {
        List<CefPaintElementType> invalidated = new CopyOnWriteArrayList<>();
        CefBrowserPanel panel = Objects.requireNonNull(onSwingThread(() -> {
            CefBrowserPanel created = new CefBrowserPanel();
            created.setSize(SIZE, SIZE);
            created.browser(fakeBrowser(invalidated));
            return created;
        }));
        CefRenderHandler handler = panel.createRenderHandler();
        onSwingThread(() -> {});
        invalidated.clear();

        handler.onPaint(null, VIEW, 1, new CefRect[] {new CefRect(0, 0, SIZE, SIZE)}, frame(RED), SIZE, SIZE);
        handler.onPaint(null, VIEW, 1, new CefRect[] {new CefRect(0, 0, SIZE, SIZE)}, frame(GREEN), SIZE, SIZE);
        assertThat(waitForRenderedColor(panel, 1, 1, RED, 5_000)).isTrue();
        onSwingThread(() -> {});

        assertThat(invalidated)
                .as("CEF only paints on demand, so the dropped frame has to be requested again")
                .contains(VIEW);
        handler.onPaint(null, VIEW, 1, new CefRect[] {new CefRect(0, 0, SIZE, SIZE)}, frame(GREEN), SIZE, SIZE);
        assertThat(waitForRenderedColor(panel, 1, 1, GREEN, 5_000)).isTrue();
    }

    private static ByteBuffer frame(int argb) {
        ByteBuffer frame =
                ByteBuffer.allocateDirect(SIZE * SIZE * Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < SIZE * SIZE; i++) frame.putInt(argb);
        return frame.flip();
    }

    private static CefBrowser fakeBrowser(List<CefPaintElementType> invalidated) {
        CefBrowserHost host = fake(
                CefBrowserHost.class, Map.of("invalidate", args -> invalidated.add((CefPaintElementType) args[0])));
        return fake(CefBrowser.class, Map.of("getHost", args -> Optional.of(host)));
    }

    private static <T> T fake(Class<T> type, Map<String, Function<Object[], Object>> methods) {
        return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] {type}, (proxy, m, args) -> {
            if (m.getDeclaringClass() == Object.class) {
                if (m.getName().equals("equals")) return proxy == args[0];
                if (m.getName().equals("hashCode")) return System.identityHashCode(proxy);
                return type.getSimpleName();
            }
            Function<Object[], Object> method = methods.get(m.getName());
            if (method != null) return method.apply(args);
            if (m.getReturnType() == void.class) return null;
            throw new UnsupportedOperationException(type.getSimpleName() + "." + m.getName());
        }));
    }

    @Test
    void closingFrameAllowsNewPanelToLoad() throws Exception {
        CefBrowserPanel first = createAttachedPanel();
        loadContent(first, "<html><head><title>first</title></head><body>first</body></html>");
        assertThat(waitUntil(() -> "first".equals(getTitle(first)), 5_000)).isTrue();

        closeFrames();

        CefBrowserPanel second = createAttachedPanel();
        loadContent(second, "<html><head><title>second-view</title></head><body>ok</body></html>");

        assertThat(waitUntil(() -> "second-view".equals(getTitle(second)), 5_000))
                .isTrue();
    }

    @Test
    void resizePanelTriggersRepaint() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        long loadBaseline = loadGeneration(panel);
        loadContent(
                panel,
                "<html><body><script>"
                        + "document.title = window.innerWidth + 'x' + window.innerHeight;"
                        + "window.addEventListener('resize', function() {"
                        + "  document.title = window.innerWidth + 'x' + window.innerHeight;"
                        + "});"
                        + "</script></body></html>");

        assertThat(waitForLoadEnd(panel, loadBaseline, 5_000)).isTrue();
        String initialTitle = getTitle(panel);

        onSwingThread(() -> {
            javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(panel);
            if (frame != null) {
                frame.setSize(400, 300);
            }
        });

        assertThat(waitUntil(() -> !initialTitle.equals(getTitle(panel)), 5_000))
                .as("expected title to change after resize; still '%s'", initialTitle)
                .isTrue();
    }
}
