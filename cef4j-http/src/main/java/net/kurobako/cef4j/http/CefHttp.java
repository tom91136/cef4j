package net.kurobako.cef4j.http;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Opt-in routing of {@code http}/{@code https} {@link URL} connections through CEF's network stack.
 *
 * <p>Nothing is registered by having this module on the classpath. Use {@link #handler(String)} to route individual
 * URLs:
 *
 * <pre>{@code
 * URL url = new URL(null, "https://example.com/", CefHttp.handler("https"));
 * }</pre>
 *
 * <p>or {@link #install()} to route every {@code http}/{@code https} URL in the JVM.
 */
public final class CefHttp {

    private CefHttp() {}

    /**
     * Returns a handler for {@code http} or {@code https}.
     *
     * @throws IllegalArgumentException for any other protocol
     */
    @Nonnull
    public static URLStreamHandler handler(@Nonnull String protocol) {
        switch (protocol) {
            case "http":
                return new CefStreamHandler(CefUrlRequestHttpEngine.INSTANCE, 80);
            case "https":
                return new CefStreamHandler(CefUrlRequestHttpEngine.INSTANCE, 443);
            default:
                throw new IllegalArgumentException("unsupported protocol: " + protocol);
        }
    }

    /** Factory that handles {@code http} and {@code https} and defers every other protocol to the JDK. */
    @Nonnull
    public static URLStreamHandlerFactory factory() {
        return new Factory();
    }

    /**
     * Routes every {@code http}/{@code https} URL in this JVM through CEF via {@link URL#setURLStreamHandlerFactory}.
     *
     * <p>The JDK allows one factory per JVM. Call this before any {@code http}/{@code https} URL is opened, because the
     * JDK caches the default handler on first use. Use {@link #factory()} to compose with another factory.
     *
     * @throws Error if a factory has already been installed
     */
    public static void install() {
        URL.setURLStreamHandlerFactory(factory());
    }

    @NullableBoundary("URLStreamHandlerFactory returns null to defer to the JDK handler")
    private static final class Factory implements URLStreamHandlerFactory {
        @Override
        @Nullable
        public URLStreamHandler createURLStreamHandler(@Nullable String protocol) {
            if (protocol == null) return null;
            switch (protocol) {
                case "http":
                case "https":
                    return handler(protocol);
                default:
                    return null;
            }
        }
    }
}
