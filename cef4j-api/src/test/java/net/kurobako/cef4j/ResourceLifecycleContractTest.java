package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefV8Value;
import org.junit.jupiter.api.Test;

class ResourceLifecycleContractTest {
    @Test
    void generatedCefResourcesAreAutoCloseable() {
        assertThat(AutoCloseable.class).isAssignableFrom(CefLibraryObject.class);
        assertThat(CefLibraryObject.class).isAssignableFrom(CefBrowser.class);
        assertThat(CefLibraryObject.class).isAssignableFrom(CefFrame.class);
        assertThat(CefLibraryObject.class).isAssignableFrom(CefV8Value.class);
    }

    @Test
    void generatedCloseReleasesItsPeer() {
        AtomicInteger closes = new AtomicInteger();
        CefLibraryObject resource = closes::incrementAndGet;

        resource.close();

        assertThat(closes).hasValue(1);
    }

    @Test
    void scriptEngineIsAutoCloseable() {
        assertThat(AutoCloseable.class).isAssignableFrom(CefScriptEngine.class);
    }

    @Test
    void cefRuntimeIsAutoCloseable() {
        assertThat(AutoCloseable.class).isAssignableFrom(Cef.class);
    }
}
