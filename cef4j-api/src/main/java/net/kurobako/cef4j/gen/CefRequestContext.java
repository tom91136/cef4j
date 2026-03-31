// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * A request context provides request handling for a set of related browser or URL request objects. A request context
 * can be specified when creating a new browser via the CefBrowserHost static factory methods or when creating a new URL
 * request via the CefURLRequest static factory methods. Browser objects with different request contexts will never be
 * hosted in the same render process. Browser objects with the same request context may or may not be hosted in the same
 * render process depending on the process model. Browser objects created indirectly via the JavaScript window.open
 * function or targeted links will share the same render process and the same request context as the source browser.
 * When running in single-process mode there is only a single render process (the main process) and so all browsers
 * created in single-process mode will share the same request context. This will be the first request context passed
 * into a CefBrowserHost static factory method and all other request context objects will be ignored.
 */
public final class CefRequestContext {

    public final CefPreferenceManager base;

    public CefRequestContext(CefPreferenceManager base) {
        this.base = base;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRequestContext)) return false;
        CefRequestContext other = (CefRequestContext) obj;
        return java.util.Objects.equals(this.base, other.base);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(base);
    }

    @Override
    public String toString() {
        return "CefRequestContext{" + "base=" + base + "}";
    }
}
