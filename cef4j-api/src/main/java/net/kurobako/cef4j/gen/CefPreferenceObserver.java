// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implemented by the client to observe preference changes and registered via
 * CefPreferenceManager::AddPreferenceObserver. The methods of this class will be called on the browser process UI
 * thread.
 */
public interface CefPreferenceObserver {

    /**
     * Called when a preference has changed. The new value can be retrieved using CefPreferenceManager::GetPreference.
     */
    default void onPreferenceChanged(@Nonnull String name) {}
}
