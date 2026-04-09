package net.kurobako.cef4j;

import java.util.Optional;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefRequest;
import net.kurobako.cef4j.gen.CefResourceHandler;
import net.kurobako.cef4j.gen.CefSchemeHandlerFactory;

/**
 * A {@link CefSchemeHandlerFactory} that creates {@link UrlResourceHandler} instances. Each request gets its own
 * handler that bridges to Java's {@link java.net.URL} system.
 */
@SuppressWarnings("unused")
public final class UrlSchemeHandlerFactory implements CefSchemeHandlerFactory {

    @Override
    public Optional<CefResourceHandler> create(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nullable String schemeName,
            @Nullable CefRequest request) {
        return Optional.of(new UrlResourceHandler());
    }
}
