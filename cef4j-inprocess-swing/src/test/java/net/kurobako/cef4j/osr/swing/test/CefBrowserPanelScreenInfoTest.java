package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.onSwingThread;
import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.waitUntil;
import static org.assertj.core.api.Assertions.assertThat;

import java.awt.BorderLayout;
import java.lang.reflect.Proxy;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelScreenInfoTest extends SwingBrowserPanelTestBase {

    @Test
    void movingThePanelNotifiesCefThatScreenInfoMayHaveChanged() throws Exception {
        AtomicInteger screenInfoNotifications = new AtomicInteger();
        AtomicInteger resizeNotifications = new AtomicInteger();
        CefBrowserHost host = proxyHost(screenInfoNotifications, resizeNotifications);
        CefBrowser browser = proxyBrowser(host);
        JFrame[] frameRef = new JFrame[1];

        try {
            onSwingThread(() -> {
                CefBrowserPanel panel = new CefBrowserPanel();
                panel.setLayout(null);
                panel.browser(browser);

                JFrame frame = new JFrame("screen-info-test");
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(panel, BorderLayout.CENTER);
                frame.setSize(400, 300);
                frame.setVisible(true);
                frameRef[0] = frame;

                panel.setLocation(10, 10);
                panel.setSize(200, 150);
            });

            screenInfoNotifications.set(0);
            resizeNotifications.set(0);

            onSwingThread(() -> {
                CefBrowserPanel panel =
                        (CefBrowserPanel) frameRef[0].getContentPane().getComponent(0);
                panel.setLocation(40, 10);
            });

            assertThat(waitUntil(() -> screenInfoNotifications.get() > 0, 1_000))
                    .isTrue();
            assertThat(resizeNotifications.get()).isGreaterThan(0);
        } finally {
            if (frameRef[0] != null) {
                onSwingThread(() -> frameRef[0].dispose());
            }
        }
    }

    @SuppressWarnings("NullAway")
    private static CefBrowser proxyBrowser(CefBrowserHost host) {
        return (CefBrowser) Proxy.newProxyInstance(
                CefBrowser.class.getClassLoader(), new Class<?>[] {CefBrowser.class}, (proxy, method, args) -> {
                    if ("getHost".equals(method.getName())) {
                        return Optional.of(host);
                    }
                    return defaultValue(method.getReturnType());
                });
    }

    @SuppressWarnings("NullAway")
    private static CefBrowserHost proxyHost(AtomicInteger screenInfoNotifications, AtomicInteger resizeNotifications) {
        return (CefBrowserHost) Proxy.newProxyInstance(
                CefBrowserHost.class.getClassLoader(), new Class<?>[] {CefBrowserHost.class}, (proxy, method, args) -> {
                    switch (method.getName()) {
                        case "notifyScreenInfoChanged":
                            screenInfoNotifications.incrementAndGet();
                            return null;
                        case "wasResized":
                            resizeNotifications.incrementAndGet();
                            return null;
                        case "getBrowser":
                            return Optional.empty();
                        default:
                            return defaultValue(method.getReturnType());
                    }
                });
    }

    @Nullable
    private static Object defaultValue(Class<?> type) {
        if (type == Void.TYPE) return null;
        if (type == Boolean.TYPE) return false;
        if (type == Integer.TYPE) return 0;
        if (type == Long.TYPE) return 0L;
        if (type == Float.TYPE) return 0f;
        if (type == Double.TYPE) return 0d;
        if (type == Optional.class) return Optional.empty();
        return null;
    }
}
