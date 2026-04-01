// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Class used to implement a custom resource bundle interface. See CefSettings for additional options related to
 * resource bundle loading. The methods of this class may be called on multiple threads.
 *
 * <p>Definition generated from cef_resource_bundle_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_bundle_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_bundle_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle__handler_8h.html">cef_resource_bundle_handler.h:43</a>
 */
public interface CefResourceBundleHandler extends CefClientHandler {

    /**
     * Returns the localized string for the specified {@code string_id} or an empty string if the value is not found.
     * Use the cef_id_for_pack_string_name() function for version-safe mapping of string IDS names from
     * cef_pack_strings.h to version-specific numerical {@code string_id} values.
     *
     * <p>Definition generated from cef_resource_bundle_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_localized_string)(struct _cef_resource_bundle_handler_t* self, int string_id, cef_string_t* string);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:62</a>
     */
    default int getLocalizedString(int stringId, @Nonnull String string) {
        return 0;
    }

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified scale independent
     * {@code resource_id} or {@code null} if not found. Use the cef_id_for_pack_resource_name() function for
     * version-safe mapping of resource IDR names from cef_pack_resources.h to version-specific numerical
     * {@code resource_id} values.
     *
     * <p>Definition generated from cef_resource_bundle_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_data_resource)(struct _cef_resource_bundle_handler_t* self, int resource_id, void** data, size_t* data_size);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:71</a>
     */
    default int getDataResource(int resourceId, @Nonnull NativePointer data, long[] dataSize) {
        return 0;
    }

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified {@code resource_id} nearest the
     * scale factor {@code scale_factor} or {@code null} if not found. Use a {@code scale_factor} value of
     * SCALE_FACTOR_NONE for scale independent resources or call GetDataResource instead. Use the
     * cef_id_for_pack_resource_name() function for version-safe mapping of resource IDR names from cef_pack_resources.h
     * to version-specific numerical {@code resource_id} values.
     *
     * <p>Definition generated from cef_resource_bundle_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_data_resource_for_scale)(struct _cef_resource_bundle_handler_t* self, int resource_id, cef_scale_factor_t scale_factor, void** data, size_t* data_size);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__bundle_8h.html">cef_resource_bundle.h:81</a>
     */
    default int getDataResourceForScale(
            int resourceId, @Nonnull CefScaleFactor scaleFactor, @Nonnull NativePointer data, long[] dataSize) {
        return 0;
    }
}
