// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Class that manages custom scheme registrations.
 * <p>Definition generated from cef_scheme_capi.h
 * <pre>typedef struct _cef_scheme_registrar_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_scheme_registrar_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__scheme_8h.html">cef_scheme.h:79</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefSchemeRegistrar extends CefLibraryObject {

    /**
     * Register a custom scheme. This method should not be called for the built-in HTTP, HTTPS, FILE, FTP, ABOUT and DATA schemes.
     * <p>
     * See cef_scheme_options_t for possible values for {@code options}.
     * <p>
     * This function may be called on any thread. It should only be called once per unique {@code scheme_name} value. If {@code scheme_name} is already registered or if an error occurs this method will return {@code false}.
     * <p>Definition generated from cef_scheme_capi.h
     * <pre>int (CEF_CALLBACK* add_custom_scheme)(struct _cef_scheme_registrar_t* self, const cef_string_t* scheme_name, int options);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__scheme_8h.html">cef_scheme.h:85</a>
     */
    boolean addCustomScheme(@Nullable String schemeName, int options);
    final class NativePeer implements CefSchemeRegistrar, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefSchemeRegistrar has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefSchemeRegistrar.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefSchemeRegistrar 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean addCustomScheme(@Nullable String schemeName, int options) {
          checkNotClosed();
          return addCustomScheme0(nativePtr, schemeName, options);
      }


        static native boolean addCustomScheme0(long self, @Nullable String schemeName, int options);


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
            return "CefSchemeRegistrar{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
