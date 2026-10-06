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
        // XXX: CEF 150 shutdown can hang the Windows message loop or post late AppKit work after AWT starts on macOS;
        // enable explicit shutdown when the minimum CEF is above 150 and both isolated native contract forks return.
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
