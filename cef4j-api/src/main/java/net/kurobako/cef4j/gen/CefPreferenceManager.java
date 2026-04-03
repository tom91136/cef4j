// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Manage access to preferences. Many built-in preferences are registered by Chromium. Custom preferences can be
 * registered in {@link CefBrowserProcessHandler#onRegisterCustomPreferences(CefPreferencesType,
 * CefPreferenceRegistrar)}.
 *
 * <p>Definition generated from cef_preference_capi.h
 *
 * <pre>typedef struct _cef_preference_manager_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_preference_manager_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:87</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefPreferenceManager extends CefLibraryObject {

    /**
     * Returns {@code true} if a preference with the specified {@code name} exists. This method must be called on the
     * browser process UI thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_preference)(struct _cef_preference_manager_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:131</a>
     */
    boolean hasPreference(@Nullable String name);

    /**
     * Returns the value for the preference with the specified {@code name}. Returns {@code null} if the preference does
     * not exist. The returned object contains a copy of the underlying preference value and modifications to the
     * returned object will not modify the underlying preference value. This method must be called on the browser
     * process UI thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * cef_value_t* (CEF_CALLBACK* get_preference)(struct _cef_preference_manager_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:138</a>
     */
    Optional<CefValue> getPreference(@Nullable String name);

    /**
     * Returns all preferences as a dictionary. If {@code include_defaults} is {@code true} then preferences currently
     * at their default value will be included. The returned object contains a copy of the underlying preference values
     * and modifications to the returned object will not modify the underlying preference values. This method must be
     * called on the browser process UI thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * cef_dictionary_value_t* (CEF_CALLBACK* get_all_preferences)(struct _cef_preference_manager_t* self, int include_defaults);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:148</a>
     */
    Optional<CefDictionaryValue> getAllPreferences(boolean includeDefaults);

    /**
     * Returns {@code true} if the preference with the specified {@code name} can be modified using SetPreference. As
     * one example preferences set via the command-line usually cannot be modified. This method must be called on the
     * browser process UI thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>int (CEF_CALLBACK* can_set_preference)(struct _cef_preference_manager_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:160</a>
     */
    boolean canSetPreference(@Nullable String name);

    /**
     * Set the {@code value} associated with preference {@code name}. Returns {@code true} if the value is set
     * successfully and {@code false} otherwise. If {@code value} is {@code null} the preference will be restored to its
     * default value. If setting the preference fails then {@code error} will be populated with a detailed description
     * of the problem. This method must be called on the browser process UI thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_preference)(struct _cef_preference_manager_t* self, const cef_string_t* name, struct _cef_value_t* value, cef_string_t* error);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:169</a>
     */
    boolean setPreference(@Nullable String name, @Nullable CefValue value, @Nullable String error);

    /**
     * Add an observer for preference changes. {@code name} is the name of the preference to observe. If {@code name} is
     * empty then all preferences will be observed. Observing all preferences has performance consequences and is not
     * recommended outside of testing scenarios. The observer will remain registered until the returned Registration
     * object is destroyed. This method must be called on the browser process UI thread.
     *
     * <p>Added in CEF API version 13401.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * cef_registration_t* (CEF_CALLBACK* add_preference_observer)(struct _cef_preference_manager_t* self, const cef_string_t* name, struct _cef_preference_observer_t* observer);
     * </pre>
     *
     * @param name may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:183</a>
     */
    Optional<CefRegistration> addPreferenceObserver(@Nullable String name, @Nullable CefPreferenceObserver observer);
    /**
     * Returns the current Chrome Variations configuration (combination of field trials and chrome://flags) as
     * equivalent command-line switches (`--[enable|disable]-features=XXXX`, etc). These switches can be used to apply
     * the same configuration when launching a CEF-based application. See <a
     * href="https://developer.chrome.com/docs/web-platform/chrome-variations">https://developer.chrome.com/docs/web-platform/chrome-variations</a>
     * for background and details. Note that field trial tests are disabled by default in Official CEF builds (via the
     * `disable_fieldtrial_testing_config={@code true}` GN flag). This method must be called on the browser process UI
     * thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>CEF_EXPORT void cef_preference_manager_get_chrome_variations_as_switches(cef_string_list_t switches);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:96</a>
     */
    static void getChromeVariationsAsSwitches(@Nonnull List<String> switches) {
        NativePeer.getChromeVariationsAsSwitches0(switches);
    }

    /**
     * Returns the current Chrome Variations configuration (combination of field trials and chrome://flags) as
     * human-readable strings. This is the human-readable equivalent of the "Active Variations" section of
     * chrome://version. See <a
     * href="https://developer.chrome.com/docs/web-platform/chrome-variations">https://developer.chrome.com/docs/web-platform/chrome-variations</a>
     * for background and details. Note that field trial tests are disabled by default in Official CEF builds (via the
     * `disable_fieldtrial_testing_config={@code true}` GN flag). This method must be called on the browser process UI
     * thread.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>CEF_EXPORT void cef_preference_manager_get_chrome_variations_as_strings(cef_string_list_t strings);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:110</a>
     */
    static void getChromeVariationsAsStrings(@Nonnull List<String> strings) {
        NativePeer.getChromeVariationsAsStrings0(strings);
    }

    /**
     * Returns the global object for this context. The context must be entered before calling this method.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>CEF_EXPORT cef_preference_manager_t* cef_preference_manager_get_global(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:177</a>
     */
    static Optional<CefPreferenceManager> getGlobal() {
        return Optional.ofNullable(NativePeer.getGlobal0());
    }

    final class NativePeer implements CefPreferenceManager, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefPreferenceManager has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPreferenceManager.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPreferenceManager 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean hasPreference(@Nullable String name) {
            checkNotClosed();
            return hasPreference0(nativePtr, name);
        }

        @Override
        public Optional<CefValue> getPreference(@Nullable String name) {
            checkNotClosed();
            return Optional.ofNullable(getPreference0(nativePtr, name));
        }

        @Override
        public Optional<CefDictionaryValue> getAllPreferences(boolean includeDefaults) {
            checkNotClosed();
            return Optional.ofNullable(getAllPreferences0(nativePtr, includeDefaults));
        }

        @Override
        public boolean canSetPreference(@Nullable String name) {
            checkNotClosed();
            return canSetPreference0(nativePtr, name);
        }

        @Override
        public boolean setPreference(@Nullable String name, @Nullable CefValue value, @Nullable String error) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefValue");
            return setPreference0(nativePtr, name, value, error);
        }

        @Override
        public Optional<CefRegistration> addPreferenceObserver(
                @Nullable String name, @Nullable CefPreferenceObserver observer) {
            checkNotClosed();
            return Optional.ofNullable(addPreferenceObserver0(nativePtr, name, observer));
        }

        private static native boolean hasPreference0(long self, String name);

        private static native CefValue getPreference0(long self, String name);

        private static native CefDictionaryValue getAllPreferences0(long self, boolean includeDefaults);

        private static native boolean canSetPreference0(long self, String name);

        private static native boolean setPreference0(long self, String name, CefValue value, String error);

        private static native CefRegistration addPreferenceObserver0(
                long self, String name, CefPreferenceObserver observer);

        static native void getChromeVariationsAsSwitches0(List<String> switches);

        static native void getChromeVariationsAsStrings0(List<String> strings);

        static native CefPreferenceManager getGlobal0();

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
