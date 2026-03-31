// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Manage access to preferences. Many built-in preferences are registered by Chromium. Custom preferences can be
 * registered in CefBrowserProcessHandler::OnRegisterCustomPreferences.
 */
public interface CefPreferenceManager {

    /**
     * Returns true if a preference with the specified |name| exists. This method must be called on the browser process
     * UI thread.
     */
    boolean hasPreference(@Nonnull String name);

    /**
     * Returns the value for the preference with the specified |name|. Returns NULL if the preference does not exist.
     * The returned object contains a copy of the underlying preference value and modifications to the returned object
     * will not modify the underlying preference value. This method must be called on the browser process UI thread.
     */
    long getPreference(@Nonnull String name);

    /**
     * Returns all preferences as a dictionary. If |include_defaults| is true then preferences currently at their
     * default value will be included. The returned object contains a copy of the underlying preference values and
     * modifications to the returned object will not modify the underlying preference values. This method must be called
     * on the browser process UI thread.
     */
    long getAllPreferences(boolean includeDefaults);

    /**
     * Returns true if the preference with the specified |name| can be modified using SetPreference. As one example
     * preferences set via the command-line usually cannot be modified. This method must be called on the browser
     * process UI thread.
     */
    boolean canSetPreference(@Nonnull String name);

    /**
     * Set the |value| associated with preference |name|. Returns true if the value is set successfully and false
     * otherwise. If |value| is NULL the preference will be restored to its default value. If setting the preference
     * fails then |error| will be populated with a detailed description of the problem. This method must be called on
     * the browser process UI thread.
     *
     * @param value may be null
     */
    boolean setPreference(@Nonnull String name, long value, @Nonnull String error);

    /**
     * Add an observer for preference changes. |name| is the name of the preference to observe. If |name| is empty then
     * all preferences will be observed. Observing all preferences has performance consequences and is not recommended
     * outside of testing scenarios. The observer will remain registered until the returned Registration object is
     * destroyed. This method must be called on the browser process UI thread.
     *
     * <p>Added in CEF API version 13401.
     *
     * @param name may be null
     */
    long addPreferenceObserver(@Nullable String name, long observer);

    static class NativePeer implements CefPreferenceManager {
        private volatile long nativePtr;

        @Override
        public boolean hasPreference(String name) {
            return N_HasPreference(nativePtr, name);
        }

        @Override
        public long getPreference(String name) {
            return N_GetPreference(nativePtr, name);
        }

        @Override
        public long getAllPreferences(boolean includeDefaults) {
            return N_GetAllPreferences(nativePtr, includeDefaults);
        }

        @Override
        public boolean canSetPreference(String name) {
            return N_CanSetPreference(nativePtr, name);
        }

        @Override
        public boolean setPreference(String name, long value, String error) {
            return N_SetPreference(nativePtr, name, value, error);
        }

        @Override
        public long addPreferenceObserver(String name, long observer) {
            return N_AddPreferenceObserver(nativePtr, name, observer);
        }

        private native boolean N_HasPreference(long self, String name);

        private native long N_GetPreference(long self, String name);

        private native long N_GetAllPreferences(long self, boolean includeDefaults);

        private native boolean N_CanSetPreference(long self, String name);

        private native boolean N_SetPreference(long self, String name, long value, String error);

        private native long N_AddPreferenceObserver(long self, String name, long observer);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefPreferenceManager{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
