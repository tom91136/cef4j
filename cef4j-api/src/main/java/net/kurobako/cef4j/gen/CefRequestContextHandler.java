// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface to provide handler implementations. The handler instance will not be released until all
 * objects related to the context have been destroyed.
 */
public interface CefRequestContextHandler {

    /** Called on the browser process UI thread immediately after the request context has been initialized. */
    default void onRequestContextInitialized(long requestContext) {}
}
