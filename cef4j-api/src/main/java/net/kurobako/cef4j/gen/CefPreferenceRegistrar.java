// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
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

    final class NativePeer implements CefPreferenceRegistrar, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean addPreference(@Nullable String name, @Nullable CefValue defaultValue) {
            checkNotClosed();
            CefLibraryObject.requireOpen(defaultValue, "CefValue");
            return addPreference0(nativePtr, name, defaultValue);
        }

        static native boolean addPreference0(long self, @Nullable String name, @Nullable CefValue defaultValue);

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
