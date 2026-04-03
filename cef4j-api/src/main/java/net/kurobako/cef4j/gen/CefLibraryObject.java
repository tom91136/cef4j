// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Marker interface for CEF structs allocated on the library (DLL) side.
 *
 * <p>Instances are created by the CEF library and returned to client code as private {@code NativePeer} wrappers.
 * Client code calls methods on them but should never instantiate them directly.
 *
 * <p>Instances are reference-counted resources under CEF ownership rules. They are released automatically during GC
 * cleanup or CEF teardown, but callers are encouraged to close them as soon as they are no longer needed.
 *
 * @see CefClientHandler
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefLibraryObject extends AutoCloseable {
    @Override
    default void close() {
        peerClose();
    }

    void peerClose();

    default boolean peerIsClosed() {
        return false;
    }

    static void requireOpen(CefLibraryObject obj, String name) {
        if (obj != null && obj.peerIsClosed()) {
            throw new IllegalStateException(name + " argument has been closed");
        }
    }
}
