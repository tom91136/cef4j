package net.kurobako.cef4j.osr.swing.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;

class CefBrowserPanelCompileTest {

    @Test
    void panelApiSurfaceCompilesCorrectly() {
        Supplier<CefBrowserPanel> ctor = CefBrowserPanel::new;
        Function<CefBrowserPanel, CefRenderHandler> createRenderHandler = CefBrowserPanel::createRenderHandler;
        Function<CefBrowserPanel, CefBrowser> browser = CefBrowserPanel::browser;
        Consumer<CefBrowserPanel> dispose = CefBrowserPanel::release;
        Consumer<CefBrowserPanel> close = CefBrowserPanel::close;

        assertThat(ctor).isNotNull();
        assertThat(createRenderHandler).isNotNull();
        assertThat(browser).isNotNull();
        assertThat(dispose).isNotNull();
        assertThat(close).isNotNull();
    }
}
