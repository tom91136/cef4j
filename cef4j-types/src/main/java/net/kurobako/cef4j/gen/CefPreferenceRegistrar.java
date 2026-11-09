// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Class that manages custom preference registrations.
 *
 * <p>Definition generated from cef_preference_capi.h
 *
 * <pre>typedef struct _cef_preference_registrar_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_preference_registrar_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__preference_8h.html">cef_preference.h:47</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefPreferenceRegistrar extends CefLibraryObject {

    /**
     * Register a preference with the specified {@code name} and {@code default_value}. To avoid conflicts with built-in
     * preferences the {@code name} value should contain an application-specific prefix followed by a period (e.g.
     * "myapp.value"). The contents of {@code default_value} will be copied. The data type for the preference will be
     * inferred from {@code default_value}'s type and cannot be changed after registration. Returns {@code true} on
     * success. Returns {@code false} if {@code name} is already registered or if {@code default_value} has an invalid
     * type. This method must be called from within the scope of the
     * {@link net.kurobako.cef4j.gen.CefBrowserProcessHandler#onRegisterCustomPreferences(CefPreferencesType,
     * CefPreferenceRegistrar)} callback.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* add_preference)(struct _cef_preference_registrar_t* self, const cef_string_t* name, struct _cef_value_t* default_value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__preference_8h.html">cef_preference.h:53</a>
     */
    boolean addPreference(@Nullable String name, @Nullable CefValue defaultValue);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefPreferenceRegistrar {
        NativePeer(long ptr) {
            super("CefPreferenceRegistrar", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public boolean addPreference(@Nullable String name, @Nullable CefValue defaultValue) {
            checkNotClosed();
            CefLibraryObject.requireOpen(defaultValue, "CefValue");
            return addPreference0(nativePtr, name, defaultValue);
        }

        static native boolean addPreference0(long self, @Nullable String name, @Nullable CefValue defaultValue);
    }
}
