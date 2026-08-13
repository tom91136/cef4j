package net.kurobako.cef4j.webdriver.inprocess;

import javax.annotation.Nonnull;
import net.kurobako.cef4j.gen.CefBrowser;

/** One owned in-process CEF browser. Closing it must close the browser and release its hosting resources. */
public interface InProcessBrowserRuntime extends AutoCloseable {
    @Nonnull
    CefBrowser browser();

    @Override
    void close();
}
