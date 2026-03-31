// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implemented by the client to observe MediaRouter events and registered via CefMediaRouter::AddObserver. The methods
 * of this class will be called on the browser process UI thread.
 */
public interface CefMediaObserver {

    /** The list of available media sinks has changed or CefMediaRouter::NotifyCurrentSinks was called. */
    default void onSinks(long sinksCount, long sinks) {}

    /** The list of available media routes has changed or CefMediaRouter::NotifyCurrentRoutes was called. */
    default void onRoutes(long routesCount, long routes) {}

    /** The connection state of |route| has changed. */
    default void onRouteStateChanged(long route, @Nonnull CefMediaRouteConnectionState state) {}

    /**
     * A message was received over |route|. |message| is only valid for the scope of this callback and should be copied
     * if necessary.
     */
    default void onRouteMessageReceived(long route, long message, long messageSize) {}
}
