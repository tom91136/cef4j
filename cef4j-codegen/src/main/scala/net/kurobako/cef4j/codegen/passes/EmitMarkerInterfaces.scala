package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.Banners

object EmitMarkerInterfaces {
  def apply(outJava: Path, javaPackage: String)(using Banners): Unit = {
    val banner     = Banners.java
    val importLine = s"import ${Banners.javaAnnotationClass};"
    val annotation = Banners.javaAnnotation
    Files.writeString(
      outJava.resolve("CefLibraryObject.java"),
      s"""$banner
package $javaPackage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
$importLine

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
$annotation
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
"""
    )
    Files.writeString(
      outJava.resolve("CefClientHandler.java"),
      s"""$banner
package $javaPackage;

$importLine

/**
 * Marker interface for CEF structs allocated on the client side.
 *
 * <p>Client code implements these interfaces and passes instances to the
 * CEF library (e.g. handler callbacks). The library calls back into the
 * client implementation.
 *
 * <p>Unlike {@link CefLibraryObject}, this is not itself a native resource.
 * The JNI layer creates and manages the corresponding native wrapper at CEF
 * use sites.
 *
 * @see CefLibraryObject
 */
$annotation
public interface CefClientHandler {}
"""
    )
    Files.writeString(
      outJava.resolve("CefEnum.java"),
      s"""$banner
package $javaPackage;

$importLine

/**
 * Common interface for generated wrappers around CEF enum values.
 *
 * <p>Each generated enum wrapper preserves the original numeric C value while
 * exposing known constants through a nested {@code Kind} enum. Unknown or
 * composite values are still represented losslessly.
 *
 * <p>This allows flag sets and forward-compatible values to round-trip through
 * the Java API without collapsing them to only the known named constants.
 *
 * @param <T> the concrete generated enum wrapper type
 */
$annotation
public interface CefEnum<T extends CefEnum<T>> {

    /** The underlying C enum numeric value. */
    long value();

    /** The original C expression (e.g. {@code "1 << 3"}), or the numeric string for unknown values. */
    String expr();

    /** The constant name (e.g. {@code "CEF_FOO"}), or a descriptive name for unknown values. */
    String name();
}
"""
    )
  }
}
