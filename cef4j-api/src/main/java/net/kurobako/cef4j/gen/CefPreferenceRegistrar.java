// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:47</a>
 */
public interface CefPreferenceRegistrar extends CefLibraryObject {

    /**
     * Register a preference with the specified {@code name} and {@code default_value}. To avoid conflicts with built-in
     * preferences the {@code name} value should contain an application-specific prefix followed by a period (e.g.
     * "myapp.value"). The contents of {@code default_value} will be copied. The data type for the preference will be
     * inferred from {@code default_value}'s type and cannot be changed after registration. Returns {@code true} on
     * success. Returns {@code false} if {@code name} is already registered or if {@code default_value} has an invalid
     * type. This method must be called from within the scope of the
     * {@link CefBrowserProcessHandler#onRegisterCustomPreferences(CefPreferencesType, CefPreferenceRegistrar)}
     * callback.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* add_preference)(struct _cef_preference_registrar_t* self, const cef_string_t* name, struct _cef_value_t* default_value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:53</a>
     */
    boolean addPreference(@Nullable String name, @Nullable CefValue defaultValue);

    final class NativePeer implements CefPreferenceRegistrar, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPreferenceRegistrar has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPreferenceRegistrar.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPreferenceRegistrar 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean addPreference(@Nullable String name, @Nullable CefValue defaultValue) {
            checkNotClosed();
            CefLibraryObject.requireOpen(defaultValue, "CefValue");
            return N_AddPreference(nativePtr, name, defaultValue);
        }

        private static native boolean N_AddPreference(long self, String name, CefValue defaultValue);

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
            return "CefPreferenceRegistrar{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
