// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used for retrieving resources from the resource bundle (*.pak) files loaded by CEF during startup or via the
 * CefResourceBundleHandler returned from CefApp.getResourceBundleHandler(). See CefSettings for additional options
 * related to resource bundle loading. The methods of this class may be called on any thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_resource_bundle_capi.h
 *
 * <pre>typedef struct _cef_resource_bundle_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_bundle_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:44</a>
 */
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:62</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:71</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:81</a>
     */
    Optional<CefBinaryValue> getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor);
    /**
     * Returns the global object for this context. The context must be entered before calling this method.
     *
     * <p>Definition generated from cef_resource_bundle_capi.h
     *
     * <pre>CEF_EXPORT cef_resource_bundle_t* cef_resource_bundle_get_global(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:177</a>
     */
    static Optional<CefResourceBundle> getGlobal() {
        return Optional.ofNullable(NativePeer.N_GetGlobal());
    }

    final class NativePeer implements CefResourceBundle, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<String> getLocalizedString(int stringId) {
            return Optional.ofNullable(N_GetLocalizedString(nativePtr, stringId));
        }

        @Override
        public Optional<CefBinaryValue> getDataResource(int resourceId) {
            return Optional.ofNullable(N_GetDataResource(nativePtr, resourceId));
        }

        @Override
        public Optional<CefBinaryValue> getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor) {
            return Optional.ofNullable(N_GetDataResourceForScale(nativePtr, resourceId, scaleFactor));
        }

        private static native String N_GetLocalizedString(long self, int stringId);

        private static native CefBinaryValue N_GetDataResource(long self, int resourceId);

        private static native CefBinaryValue N_GetDataResourceForScale(
                long self, int resourceId, CefScaleFactor scaleFactor);

        static native CefResourceBundle N_GetGlobal();

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
