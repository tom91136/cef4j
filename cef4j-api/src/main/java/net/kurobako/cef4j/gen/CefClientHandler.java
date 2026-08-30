// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Marker interface for CEF structs allocated on the client side.
 *
 * <p>Client code implements these interfaces and passes instances to the CEF library (e.g. handler callbacks). The
 * library calls back into the client implementation.
 *
 * <p>Unlike {@link CefLibraryObject}, this is not itself a native resource. The JNI layer creates and manages the
 * corresponding native wrapper at CEF use sites.
 *
 * @see CefLibraryObject
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefClientHandler {}
