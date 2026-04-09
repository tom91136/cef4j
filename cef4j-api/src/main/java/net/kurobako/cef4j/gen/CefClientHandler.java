// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefClientHandler {}
