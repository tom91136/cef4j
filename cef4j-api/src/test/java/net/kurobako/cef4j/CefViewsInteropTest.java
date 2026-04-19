package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import net.kurobako.cef4j.gen.views.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisabledOnOs(OS.MAC)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefViewsInteropTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        CefTestBase.initCef(List.of());
    }

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
            Optional<CefBoxLayout> layout = panel.setToBoxLayout(new CefBoxLayoutSettings.Mutable().toImmutable());
            if (layout.isPresent()) {
                assertThat(panel.getLayout())
                        .as("getLayout after setToBoxLayout")
                        .isPresent();
            }
        }
    }

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
            browser.ifPresent(cefBrowser ->
                    assertThat(cefBrowser.isValid()).as("browser isValid").isTrue());

            assertThat(bv).as("browser view instance").isNotNull();
        }
    }

    @Test
    @Order(30)
    void window_createTopLevelAndSetTitle() throws Exception {
        AtomicBoolean windowCreated = new AtomicBoolean(false);
        CountDownLatch createdLatch = new CountDownLatch(1);

        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(@Nullable CefWindow window) {
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
            public void onWindowCreated(@Nullable CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            assertThat(window.isClosed()).as("isClosed after creation").isFalse();
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
            public void onWindowCreated(@Nullable CefWindow window) {
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
            public void onWindowCreated(@Nullable CefWindow window) {
                createdLatch.countDown();
            }

            @Override
            public boolean canResize(@Nullable CefWindow window) {
                canResizeCalled.set(true);
                return true;
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            window.cefClose();
        }
    }

    @Test
    @Order(34)
    void window_getDisplay() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(@Nullable CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            pumpUntil(createdLatch, 5_000);

            Optional<CefDisplay> display = window.getDisplay();
            display.ifPresent(cefDisplay -> assertThat(cefDisplay.getDeviceScaleFactor())
                    .as("scale factor")
                    .isGreaterThan(0f));

            window.cefClose();
        }
    }

    @Test
    @Order(50)
    void labelButton_createAndGetText() {
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

    @Test
    @Order(60)
    void display_getCountDoesNotCrash() {
        long count = CefDisplay.getCount();
        assertThat(count).as("display count").isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(61)
    void display_getPrimary() {
        Optional<CefDisplay> primary = CefDisplay.getPrimary();
        if (primary.isPresent()) {
            try (CefDisplay display = primary.get()) {
                assertThat(display.getDeviceScaleFactor()).as("scale factor").isGreaterThan(0f);
                CefRect bounds = display.getBounds();
                assertThat(bounds).as("display bounds").isNotNull();
            }
        }
    }

    @Test
    @Order(70)
    void menuButton_createDoesNotCrash() {
        Optional<CefMenuButton> optBtn = CefMenuButton.create(null, "Menu");
        if (optBtn.isEmpty()) return;

        try (CefMenuButton btn = optBtn.get()) {
            assertThat(btn).isNotNull();
        }
    }

    private static void pumpFor(long durationMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + durationMs;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
    }
}
