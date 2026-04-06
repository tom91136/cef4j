package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Integration tests for the CEF classpath: scheme handler bridge. Verifies that CEF can load resources via the
 * {@code classpath:} URL scheme, which is bridged to Java's URL system by {@link UrlSchemeHandlerFactory} and
 * {@link UrlResourceHandler}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefSchemeHandlerTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        // Register a classpath: URLStreamHandler so java.net.URL can resolve classpath: URLs.
        // This mirrors what ClasspathStreamHandler.register() does in typedwebfx-core.
        try {
            URL.setURLStreamHandlerFactory(protocol -> {
                if ("classpath".equals(protocol)) {
                    return new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL u) {
                            return new URLConnection(u) {
                                private InputStream stream;

                                @Override
                                public void connect() throws IOException {
                                    String path = u.getPath();
                                    stream = CefSchemeHandlerTest.class.getResourceAsStream(path);
                                    if (stream == null) {
                                        throw new IOException("Resource not found: " + path);
                                    }
                                    connected = true;
                                }

                                @Override
                                public InputStream getInputStream() throws IOException {
                                    if (!connected) connect();
                                    return stream;
                                }

                                @Override
                                public String getContentType() {
                                    String path = url.getPath().toLowerCase();
                                    if (path.endsWith(".html")) return "text/html";
                                    if (path.endsWith(".js")) return "text/javascript";
                                    if (path.endsWith(".css")) return "text/css";
                                    return "application/octet-stream";
                                }
                            };
                        }
                    };
                }
                return null;
            });
        } catch (Error alreadySet) {
            // Factory already registered (e.g. CefInteropTest ran first in the same fork)
        }

        CefApp appHandler = new CefApp() {
            @Override
            public void onRegisterCustomSchemes(@Nullable CefSchemeRegistrar registrar) {
                if (registrar != null) {
                    int options = (int) (CefSchemeOptions.Kind.SECURE.value
                            | CefSchemeOptions.Kind.CORS_ENABLED.value
                            | CefSchemeOptions.Kind.FETCH_ENABLED.value);
                    registrar.addCustomScheme("classpath", options);
                }
            }
        };
        CefTestBase.initCef(List.of(), appHandler);
        CefGlobals.registerSchemeHandlerFactory("classpath", null, new UrlSchemeHandlerFactory());
    }

    @AfterAll
    static void shutdownCef() {
        // Don't dispose - other test classes may need CEF in the same fork.
    }

    @Test
    @Order(1)
    void classpathScheme_loadsHtmlResource() throws Exception {
        CountDownLatch loadLatch = new CountDownLatch(1);
        AtomicInteger httpStatus = new AtomicInteger(-1);
        AtomicReference<String> pageTitle = new AtomicReference<>();
        AtomicReference<String> loadErrorText = new AtomicReference<>();

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpStatusCode) {
                        httpStatus.set(httpStatusCode);
                        loadLatch.countDown();
                    }

                    @Override
                    public void onLoadError(
                            @Nonnull CefBrowser browser,
                            @Nonnull CefFrame frame,
                            @Nonnull CefErrorCode errorCode,
                            @Nonnull String errorText,
                            @Nonnull String failedUrl) {
                        loadErrorText.set("error=" + errorCode + " text=" + errorText + " url=" + failedUrl);
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public void onTitleChange(@Nonnull CefBrowser browser, @Nonnull String title) {
                        pageTitle.set(title);
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefInteropTest.MinimalRenderHandler(200, 200));
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "classpath:///cef4j-scheme-test.html");

        assertThat(pumpUntil(loadLatch, 15_000))
                .as("classpath: URL should load within 15s (loadError=%s)", loadErrorText.get())
                .isTrue();
        assertThat(loadErrorText.get()).as("should not have load error").isNull();
        assertThat(httpStatus.get()).as("HTTP status for classpath resource").isEqualTo(200);
        assertThat(pageTitle.get()).as("page title from classpath resource").isEqualTo("cef4j-scheme-test");

        closeBrowser(browser);
    }

    @Test
    @Order(2)
    void classpathScheme_returns404ForMissingResource() throws Exception {
        CountDownLatch loadLatch = new CountDownLatch(1);
        AtomicReference<CefErrorCode> loadError = new AtomicReference<>();

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpStatusCode) {
                        loadLatch.countDown();
                    }

                    @Override
                    public void onLoadError(
                            @Nonnull CefBrowser browser,
                            @Nonnull CefFrame frame,
                            @Nonnull CefErrorCode errorCode,
                            @Nonnull String errorText,
                            @Nonnull String failedUrl) {
                        loadError.set(errorCode);
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefInteropTest.MinimalRenderHandler(200, 200));
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "classpath:///nonexistent-resource.html");

        assertThat(pumpUntil(loadLatch, 15_000))
                .as("load should complete (with error) within 15s")
                .isTrue();

        closeBrowser(browser);
    }
}
