package net.kurobako.cef4j.osr.swing.test;

import static org.assertj.core.api.Assertions.assertThat;

import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@Timeout(180)
@ExtendWith(DisplayLock.class)
class NativeSwingContractTest {
    @Test
    void isolatedUiForkOnlyTerminatesCefExplicitlyOnLinux() {
        assertThat(shouldTerminateCef(false, false)).isTrue();
        assertThat(shouldTerminateCef(true, false)).isFalse();
        assertThat(shouldTerminateCef(false, true)).isFalse();
    }

    @AfterAll
    static void shutdown() throws Exception {
        // Windows can hang during message-loop shutdown. With AWT active on macOS, older CEF releases can post an
        // AppKit notification after cef_shutdown and trap an otherwise successful Surefire fork. The isolated test
        // process owns native teardown on both platforms; Linux retains explicit shutdown coverage here.
        if (shouldTerminateCef(OS.isWindows(), OS.isMacOS()) && Cef.INSTANCE.state() == Cef.State.INITIALISED) {
            CefBrowserPanel.terminate();
        } else if (OS.isWindows() || OS.isMacOS()) {
            javax.swing.SwingUtilities.invokeAndWait(() -> {
                for (java.awt.Frame frame : java.awt.Frame.getFrames()) frame.dispose();
            });
        }
    }

    static boolean shouldTerminateCef(boolean windows, boolean macOs) {
        return !windows && !macOs;
    }

    @Test
    void nativeBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new NativeSwingBrowserBackend());
    }
}
