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
    void panelCreateAndQueryChildCount() {
        Optional<CefPanel> optPanel = CefPanel.create(null);
        assertThat(optPanel).as("CefPanel.create(null)").isPresent();

        try (CefPanel panel = optPanel.get()) {
            assertThat(panel.getChildViewCount()).as("empty panel child count").isEqualTo(0);
            assertThat(panel.asWindow()).as("panel is not a window").isEmpty();
        }
    }

    @Test
    @Order(2)
    void panelSetFillLayout() {
        try (CefPanel panel = CefPanel.create(null).orElseThrow()) {
            try (CefFillLayout layout = panel.setToFillLayout().orElseThrow()) {
                assertThat(layout).isNotNull();
            }
        }
    }

    @Test
    @Order(3)
    void panelSetBoxLayout() {
        try (CefPanel panel = CefPanel.create(null).orElseThrow()) {
            try (CefBoxLayout layout = panel.setToBoxLayout(new CefBoxLayoutSettings.Mutable().toImmutable())
                    .orElseThrow()) {
                assertThat(layout).isNotNull();
                assertThat(panel.getLayout())
                        .as("getLayout after setToBoxLayout")
                        .isPresent();
            }
        }
    }

    @Test
    @Order(20)
    void browserViewCreateAndGetBrowser() {
        CefBrowserSettings.Mutable bs = new CefBrowserSettings.Mutable();
        bs.windowlessFrameRate = 30;

        CefClient client = new CefClient() {};

        Optional<CefBrowserView> optBv =
                CefBrowserView.create(client, "about:blank", bs.toImmutable(), null, null, null);
        assertThat(optBv).as("CefBrowserView.create").isPresent();

        try (CefBrowserView bv = optBv.get()) {
            assertThat(bv.getBrowser())
                    .as("browser before the view is attached to a window")
                    .isEmpty();
            assertThat(bv).as("browser view instance").isNotNull();
        }
    }

    @Test
    @Order(30)
    void windowCreateTopLevelAndSetTitle() throws Exception {
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
            assertThat(pumpUntil(createdLatch, 5_000)).as("window creation").isTrue();

            assertThat(windowCreated.get()).as("onWindowCreated fired").isTrue();

            window.setTitle("cef4j-views-test");
            Optional<String> title = window.getTitle();
            assertThat(title).as("window title").hasValue("cef4j-views-test");

            window.cefClose();
        }
    }

    @Test
    @Order(31)
    void windowStateQueries() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(@Nullable CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            assertThat(pumpUntil(createdLatch, 5_000)).as("window creation").isTrue();

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
    void windowActivateDeactivate() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(@Nullable CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            assertThat(pumpUntil(createdLatch, 5_000)).as("window creation").isTrue();

            window.activate();
            window.deactivate();

            window.cefClose();
        }
    }

    @Test
    @Order(33)
    void windowDelegateCanResizeFires() throws Exception {
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
            assertThat(pumpUntil(createdLatch, 5_000)).as("window creation").isTrue();

            window.cefClose();
        }
        assertThat(canResizeCalled).as("canResize callback").isTrue();
    }

    @Test
    @Order(34)
    void windowGetDisplay() throws Exception {
        CountDownLatch createdLatch = new CountDownLatch(1);
        CefWindowDelegate delegate = new CefWindowDelegate() {
            @Override
            public void onWindowCreated(@Nullable CefWindow window) {
                createdLatch.countDown();
            }
        };

        try (CefWindow window = CefWindow.createTopLevel(delegate).orElseThrow()) {
            assertThat(pumpUntil(createdLatch, 5_000)).as("window creation").isTrue();

            try (CefDisplay display = window.getDisplay().orElseThrow()) {
                assertThat(display.getDeviceScaleFactor()).as("scale factor").isGreaterThan(0f);
            }

            window.cefClose();
        }
    }

    @Test
    @Order(50)
    void labelButtonCreateAndGetText() {
        try (CefLabelButton btn =
                CefLabelButton.create(new CefButtonDelegate() {}, "Click me").orElseThrow()) {
            Optional<String> text = btn.getText();
            assertThat(text).as("button text").hasValue("Click me");

            btn.setText("Updated");
            assertThat(btn.getText()).as("updated text").hasValue("Updated");
        }
    }

    @Test
    @Order(51)
    void labelButtonAsMenuButton() {
        try (CefLabelButton btn =
                CefLabelButton.create(new CefButtonDelegate() {}, "Test").orElseThrow()) {
            assertThat(btn.asMenuButton()).as("labelButton.asMenuButton()").isEmpty();
        }
    }

    @Test
    @Order(55)
    void textfieldCreateAndSetValue() {
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
    void textfieldReadOnlyAndPasswordInput() {
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
    void textfieldSelectionOperations() {
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
    void displayGetCountDoesNotCrash() {
        long count = CefDisplay.getCount();
        assertThat(count).as("display count").isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(61)
    void displayGetPrimary() {
        try (CefDisplay display = CefDisplay.getPrimary().orElseThrow()) {
            assertThat(display.getDeviceScaleFactor()).as("scale factor").isGreaterThan(0f);
            CefRect bounds = display.getBounds();
            assertThat(bounds).as("display bounds").isNotNull();
        }
    }

    @Test
    @Order(70)
    void menuButtonCreateDoesNotCrash() {
        try (CefMenuButton btn =
                CefMenuButton.create(new CefMenuButtonDelegate() {}, "Menu").orElseThrow()) {
            assertThat(btn).isNotNull();
        }
    }
}
