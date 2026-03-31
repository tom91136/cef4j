// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Callback interface for CefMediaRouter::CreateRoute. The methods of this class will be called on the browser process
 * UI thread.
 */
public interface CefMediaRouteCreateCallback {

    /**
     * Method that will be executed when the route creation has finished. |result| will be CEF_MRCR_OK if the route
     * creation succeeded. |error| will be a description of the error if the route creation failed. |route| is the
     * resulting route, or empty if the route creation failed.
     *
     * @param error may be null
     * @param route may be null
     */
    default void onMediaRouteCreateFinished(
            @Nonnull CefMediaRouteCreateResult result, @Nullable String error, long route) {}
}
