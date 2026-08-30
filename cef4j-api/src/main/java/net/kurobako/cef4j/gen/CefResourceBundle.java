// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class used for retrieving resources from the resource bundle (*.pak) files loaded by CEF during startup or via the
 * CefResourceBundleHandler returned from {@link net.kurobako.cef4j.gen.CefApp#getResourceBundleHandler()}. See
 * CefSettings for additional options related to resource bundle loading. The methods of this class may be called on any
 * thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_resource_bundle_capi.h
 *
 * <pre>typedef struct _cef_resource_bundle_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_bundle_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:44</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefResourceBundle extends CefLibraryObject {

    /**
     * Returns the localized string for the specified {@code string_id} or an empty string if the value is not found.
     * Use the cef_id_for_pack_string_name() function for version-safe mapping of string IDS names from
     * cef_pack_strings.h to version-specific numerical {@code string_id} values.
     *
     * <p>Definition generated from cef_resource_bundle_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_localized_string)(struct _cef_resource_bundle_t* self, int string_id);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:62</a>
     */
    Optional<String> getLocalizedString(int stringId);

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified scale independent
     * {@code resource_id} or {@code null} if not found. Use the cef_id_for_pack_resource_name() function for
     * version-safe mapping of resource IDR names from cef_pack_resources.h to version-specific numerical
     * {@code resource_id} values.
     *
     * <p>Definition generated from cef_resource_bundle_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_data_resource)(struct _cef_resource_bundle_t* self, int resource_id);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:71</a>
     */
    Optional<CefBinaryValue> getDataResource(int resourceId);

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified {@code resource_id} nearest the
     * scale factor {@code scale_factor} or {@code null} if not found. Use a {@code scale_factor} value of
     * SCALE_FACTOR_NONE for scale independent resources or call GetDataResource instead. Use the
     * cef_id_for_pack_resource_name() function for version-safe mapping of resource IDR names from cef_pack_resources.h
     * to version-specific numerical {@code resource_id} values.
     *
     * <p>Definition generated from cef_resource_bundle_capi.h
     *
     * <pre>
     * cef_binary_value_t* (CEF_CALLBACK* get_data_resource_for_scale)(struct _cef_resource_bundle_t* self, int resource_id, cef_scale_factor_t scale_factor);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:81</a>
     */
    Optional<CefBinaryValue> getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor);
    /**
     * Returns the global resource bundle instance.
     *
     * <p>Definition generated from cef_resource_bundle_capi.h
     *
     * <pre>CEF_EXPORT cef_resource_bundle_t* cef_resource_bundle_get_global(void);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:56</a>
     */
    static Optional<CefResourceBundle> getGlobal() {
        return Optional.ofNullable(NativePeer.getGlobal0());
    }

    final class NativePeer implements CefResourceBundle, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefResourceBundle has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefResourceBundle.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefResourceBundle 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<String> getLocalizedString(int stringId) {
            checkNotClosed();
            return Optional.ofNullable(getLocalizedString0(nativePtr, stringId));
        }

        @Override
        public Optional<CefBinaryValue> getDataResource(int resourceId) {
            checkNotClosed();
            return Optional.ofNullable(getDataResource0(nativePtr, resourceId));
        }

        @Override
        public Optional<CefBinaryValue> getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor) {
            checkNotClosed();
            return Optional.ofNullable(getDataResourceForScale0(nativePtr, resourceId, scaleFactor));
        }

        static native String getLocalizedString0(long self, int stringId);

        static native CefBinaryValue getDataResource0(long self, int resourceId);

        static native CefBinaryValue getDataResourceForScale0(
                long self, int resourceId, @Nonnull CefScaleFactor scaleFactor);

        static native CefResourceBundle getGlobal0();

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
            return "CefResourceBundle{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
