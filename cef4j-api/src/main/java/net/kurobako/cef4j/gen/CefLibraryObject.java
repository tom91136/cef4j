// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Marker interface for CEF structs allocated on the library (DLL) side.
 *
 * <p>Instances are created by the CEF library and returned to client code as {@code NativePeer} wrappers. Client code
 * calls methods on them but never instantiates them directly.
 *
 * @see CefClientHandler
 */
public interface CefLibraryObject extends AutoCloseable {
    @Override
    void close();
}
