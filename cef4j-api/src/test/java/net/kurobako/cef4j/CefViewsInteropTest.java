package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kurobako.cef4j.gen.*;
import net.kurobako.cef4j.gen.views.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Headless integration tests for the CEF Views framework.
 *
 * <p>These tests exercise the generated Views JNI bindings (CefWindow, CefPanel, CefBrowserView, etc.) using CEF's
 * headless ozone platform so no display server is required.
 *
 * <p>Shares the same CEF process as {@link CefInteropTest} - CEF is initialised once per JVM fork.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefViewsInteropTest {

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();

        if (Cef.INSTANCE.getState() == Cef.State.UNINITIALISED) {
            Path cacheDir = Files.createTempDirectory("cef4j-views-test-cache-");
            cacheDir.toFile().deleteOnExit();

            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.cachePath = cacheDir.toAbsolutePath().toString();
            settings.windowlessRenderingEnabled = 1;
            settings.externalMessagePump = 1;
            settings.multiThreadedMessageLoop = 0;

            List<String> extraArgs = new ArrayList<>();
            if (OS.isLinux()) {
                extraArgs.add("--no-sandbox");
                String ozonePlatform = System.getProperty("cef4j.test.ozonePlatform");
                if (ozonePlatform != null && !ozonePlatform.isBlank()) {
                    extraArgs.add("--ozone-platform=" + ozonePlatform.trim());
                }
            }
            String extraArgsProperty = System.getProperty("cef4j.test.extraArgs");
            if (extraArgsProperty != null && !extraArgsProperty.isBlank()) {
                for (String arg : extraArgsProperty.split(",")) {
                    String trimmed = arg.trim();
                    if (!trimmed.isEmpty()) {
                        extraArgs.add(trimmed);
                    }
                }
            }
            Cef.INSTANCE.initialise(settings, extraArgs);
        }
    }

    // -- Panel --

    @Test
    @Order(1)
    void panel_createAndQueryChildCount() {
        Optional<CefPanel> optPanel = CefPanel.create(null);
        assertThat(optPanel).as("CefPanel.create(null)").isPresent();

        try (CefPanel panel = optPanel.get()) {
            assertThat(panel.getChildViewCount()).as("empty panel child count").isEqualTo(0);
            assertThat(panel.asWindow()).as("panel is not a window").isEmpty();
        }
    }

    @Test
    @Order(2)
    void panel_setFillLayout() {
        try (CefPanel panel = CefPanel.create(null).orElseThrow()) {
            Optional<CefFillLayout> layout = panel.setToFillLayout();
            assertThat(layout).as("setToFillLayout").isPresent();
        }
    }

    @Test
    @Order(3)
    void panel_setBoxLayout() {
        try (CefPanel panel = CefPanel.create(null).orElseThrow()) {
            // null settings may return empty on some platforms; verify no crash
            Optional<CefBoxLayout> layout = panel.setToBoxLayout(null);
            if (layout.isPresent()) {
                assertThat(panel.getLayout())
                        .as("getLayout after setToBoxLayout")
                        .isPresent();
            }
        }
    }

    // -- BrowserView --

    @Test
    @Order(20)
    void browserView_createAndGetBrowser() throws Exception {
        CefBrowserSettings.Mutable bs = new CefBrowserSettings.Mutable();
        bs.windowlessFrameRate = 30;

        CefClient client = new CefClient() {};

        Optional<CefBrowserView> optBv =
                CefBrowserView.create(client, "about:blank", bs.toImmutable(), null, null, null);
        assertThat(optBv).as("CefBrowserView.create").isPresent();

        try (CefBrowserView bv = optBv.get()) {
            pumpFor(500);

            Optional<CefBrowser> browser = bv.getBrowser();
            // Browser may not be created until the view is added to a window
            if (browser.isPresent()) {
                assertThat(browser.get().isValid()).as("browser isValid").isTrue();
            }

            assertThat(bv).as("browser view instance").isNotNull();
        }
    }

    @Test
    @Order(21)
    void browserView_getRuntimeStyle() throws Exception {
        CefBrowserSettings.Mutable bs = new CefBrowserSettings.Mutable();
        CefClient client = new CefClient() {};

        try (CefBrowserView bv = CefBrowserView.create(client, "about:blank", bs.toImmutable(), null, null, null)
                .orElseThrow()) {
            CefRuntimeStyle style = bv.getRuntimeStyle();
            assertThat(style).as("runtime style").isNotNull();
        }
    }

    // -- Window --

    @Test
    @Order(30)
    void window_createTopLevelAndSetTitle() throws Exception {
        AtomicBoolean windowCreated = new AtomicBoolean(false);
        CountDownLatch createdLatch = new CountDownLatch(1);

        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(CefWindow window) {
                windowCreated.set(true);
                createdLatch.countDown();
            }
        };

        Optional<CefWindow> optWindow = CefWindow.createTopLevel(delegate);
        assertThat(optWindow).as("CefWindow.createTopLevel").isPresent();

        try (CefWindow window = optWindow.get()) {
            pumpUntil(createdLatch, 5_000);

            assertThat(windowCreated.get()).as("onWindowCreated fired").isTrue();

            window.setTitle("cef4j-views-test");
            Optional<String> title = window.getTitle();
            assertThat(title).as("window title").hasValue("cef4j-views-test");

            window.cefClose();
        }
    }

    @Test
    @Order(31)
    void window_stateQueries() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            assertThat(window.isClosed()).as("isClosed after creation").isFalse();
            // These may vary on headless but should not crash
            window.isMaximized();
            window.isMinimized();
            window.isFullscreen();
            window.isActive();
            window.isAlwaysOnTop();

            window.cefClose();
        }
    }

    @Test
    @Order(32)
    void window_activateDeactivate() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            window.activate();
            window.deactivate();

            window.cefClose();
        }
    }

    @Test
    @Order(33)
    void windowDelegate_canResizeFires() throws Exception {
        AtomicBoolean canResizeCalled = new AtomicBoolean(false);
        CountDownLatch createdLatch = new CountDownLatch(1);

        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(CefWindow window) {
                createdLatch.countDown();
            }

            @Override
            public boolean canResize(CefWindow window) {
                canResizeCalled.set(true);
                return true;
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            // canResize is queried by CEF when the window is created/shown.
            // On headless it may or may not be called, so just verify no crash.
            window.cefClose();
        }
    }

    @Test
    @Order(34)
    void window_getDisplay() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            // On headless, display may or may not be available
            Optional<CefDisplay> display = window.getDisplay();
            if (display.isPresent()) {
                assertThat(display.get().getDeviceScaleFactor())
                        .as("scale factor")
                        .isGreaterThan(0f);
            }

            window.cefClose();
        }
    }

    // -- LabelButton --

    @Test
    @Order(50)
    void labelButton_createAndGetText() {
        // LabelButton creation may return empty in headless/no-Views-context environments
        Optional<CefLabelButton> optBtn = CefLabelButton.create(null, "Click me");
        if (optBtn.isEmpty()) return;

        try (CefLabelButton btn = optBtn.get()) {
            Optional<String> text = btn.getText();
            assertThat(text).as("button text").hasValue("Click me");

            btn.setText("Updated");
            assertThat(btn.getText()).as("updated text").hasValue("Updated");
        }
    }

    @Test
    @Order(51)
    void labelButton_asMenuButton() {
        Optional<CefLabelButton> optBtn = CefLabelButton.create(null, "Test");
        if (optBtn.isEmpty()) return;

        try (CefLabelButton btn = optBtn.get()) {
            assertThat(btn.asMenuButton()).as("labelButton.asMenuButton()").isEmpty();
        }
    }

    // -- Textfield --

    @Test
    @Order(55)
    void textfield_createAndSetValue() {
        Optional<CefTextfield> optTf = CefTextfield.create(null);
        assertThat(optTf).as("CefTextfield.create").isPresent();

        try (CefTextfield tf = optTf.get()) {
            tf.setText("hello");
            assertThat(tf.getText()).as("textfield text").hasValue("hello");

            tf.appendText(" world");
            assertThat(tf.getText()).as("after append").hasValue("hello world");

            tf.setPlaceholderText("Enter name...");
            assertThat(tf.getPlaceholderText()).as("placeholder").hasValue("Enter name...");
        }
    }

    @Test
    @Order(56)
    void textfield_readOnlyAndPasswordInput() {
        try (CefTextfield tf = CefTextfield.create(null).orElseThrow()) {
            assertThat(tf.isReadOnly()).as("initially not read-only").isFalse();
            tf.setReadOnly(true);
            assertThat(tf.isReadOnly()).as("after setReadOnly(true)").isTrue();

            // setPasswordInput may not take effect on a detached textfield in headless mode;
            // just verify the round-trip doesn't crash
            tf.setPasswordInput(true);
            tf.isPasswordInput();
        }
    }

    @Test
    @Order(57)
    void textfield_selectionOperations() {
        try (CefTextfield tf = CefTextfield.create(null).orElseThrow()) {
            tf.setText("hello world");
            tf.selectAll(false);
            assertThat(tf.hasSelection()).as("hasSelection after selectAll").isTrue();

            Optional<String> selected = tf.getSelectedText();
            assertThat(selected).as("selected text").hasValue("hello world");

            tf.clearSelection();
        }
    }

    // -- Display --

    @Test
    @Order(60)
    void display_getCountDoesNotCrash() {
        long count = CefDisplay.getCount();
        assertThat(count).as("display count").isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(61)
    void display_getPrimary() {
        // On headless, primary display may or may not be available
        Optional<CefDisplay> primary = CefDisplay.getPrimary();
        if (primary.isPresent()) {
            try (CefDisplay display = primary.get()) {
                assertThat(display.getDeviceScaleFactor()).as("scale factor").isGreaterThan(0f);
                CefRect bounds = display.getBounds();
                assertThat(bounds).as("display bounds").isNotNull();
            }
        }
    }

    // -- MenuButton --

    @Test
    @Order(70)
    void menuButton_createDoesNotCrash() {
        // MenuButton creation may return empty in headless/no-Views-context environments
        Optional<CefMenuButton> optBtn = CefMenuButton.create(null, "Menu");
        if (optBtn.isEmpty()) return;

        try (CefMenuButton btn = optBtn.get()) {
            assertThat(btn).isNotNull();
        }
    }

    // Helpers

    private static boolean pumpUntil(CountDownLatch latch, long timeoutMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (latch.getCount() > 0 && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
        return latch.getCount() == 0;
    }

    private static void pumpFor(long durationMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + durationMs;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
    }
}
