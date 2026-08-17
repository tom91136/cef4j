// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Marker interface for CEF structs allocated on the library (DLL) side.
 *
 * <p>Instances are created by the CEF library and returned to client code
 * as private {@code NativePeer} wrappers. Client code calls methods on them
 * but should never instantiate them directly.
 *
 * <p>Instances are reference-counted resources under CEF ownership rules.
 * They are released automatically during GC cleanup or CEF teardown, but
 * callers are encouraged to close them as soon as they are no longer needed.
 *
 * @see CefClientHandler
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefLibraryObject extends AutoCloseable {
    @Override default void close() { peerClose(); }

    void peerClose();

    default boolean peerIsClosed() { return false; }

    static void requireOpen(@Nullable CefLibraryObject obj, @Nonnull String name) {
        if (obj != null && obj.peerIsClosed()) {
            throw new IllegalStateException(name + " argument has been closed");
        }
    }
}
