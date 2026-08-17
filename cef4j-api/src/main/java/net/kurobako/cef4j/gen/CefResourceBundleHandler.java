// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to implement a custom resource bundle interface. See CefSettings for additional options related to resource bundle loading. The methods of this class may be called on multiple threads.
 * <p>Definition generated from cef_resource_bundle_handler_capi.h
 * <pre>typedef struct _cef_resource_bundle_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_bundle_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle__handler_8h.html">cef_resource_bundle_handler.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefResourceBundleHandler extends CefClientHandler {

    /**
     * Called to retrieve a localized translation for the specified {@code string_id}. To provide the translation set {@code string} to the translation string and return {@code true}. To use the default translation return {@code false}. Use the cef_id_for_pack_string_name() function for version-safe mapping of string IDS names from cef_pack_strings.h to version-specific numerical {@code string_id} values.
     * <p>Definition generated from cef_resource_bundle_handler_capi.h
     * <pre>int (CEF_CALLBACK* get_localized_string)(struct _cef_resource_bundle_handler_t* self, int string_id, cef_string_t* string);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle__handler_8h.html">cef_resource_bundle_handler.h:53</a>
     */
    default boolean getLocalizedString(int stringId, @Nullable String string) {
        return false;
    }

    /**
     * Called to retrieve data for the specified scale independent {@code resource_id}. To provide the resource data set {@code data} and {@code data_size} to the data pointer and size respectively and return {@code true}. To use the default resource data return {@code false}. The resource data will not be copied and must remain resident in memory. Use the cef_id_for_pack_resource_name() function for version-safe mapping of resource IDR names from cef_pack_resources.h to version-specific numerical {@code resource_id} values.
     * <p>Definition generated from cef_resource_bundle_handler_capi.h
     * <pre>int (CEF_CALLBACK* get_data_resource)(struct _cef_resource_bundle_handler_t* self, int resource_id, void** data, size_t* data_size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle__handler_8h.html">cef_resource_bundle_handler.h:64</a>
     */
    default boolean getDataResource(int resourceId, @Nonnull AtomicReference<NativePointer> data, long[] dataSize) {
        return false;
    }

    /**
     * Called to retrieve data for the specified {@code resource_id} nearest the scale factor {@code scale_factor}. To provide the resource data set {@code data} and {@code data_size} to the data pointer and size respectively and return {@code true}. To use the default resource data return {@code false}. The resource data will not be copied and must remain resident in memory. Use the cef_id_for_pack_resource_name() function for version-safe mapping of resource IDR names from cef_pack_resources.h to version-specific numerical {@code resource_id} values.
     * <p>Definition generated from cef_resource_bundle_handler_capi.h
     * <pre>int (CEF_CALLBACK* get_data_resource_for_scale)(struct _cef_resource_bundle_handler_t* self, int resource_id, cef_scale_factor_t scale_factor, void** data, size_t* data_size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__bundle__handler_8h.html">cef_resource_bundle_handler.h:78</a>
     */
    default boolean getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor, @Nonnull AtomicReference<NativePointer> data, long[] dataSize) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefResourceBundleHandler {
        private final java.util.List<CefResourceBundleHandler> delegates;

        public Delegating(java.util.List<CefResourceBundleHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean getLocalizedString(int stringId, @Nullable String string) {
            for (CefResourceBundleHandler d : delegates) {
                if (d.getLocalizedString(stringId, string)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean getDataResource(int resourceId, @Nonnull AtomicReference<NativePointer> data, long[] dataSize) {
            for (CefResourceBundleHandler d : delegates) {
                if (d.getDataResource(resourceId, data, dataSize)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor, @Nonnull AtomicReference<NativePointer> data, long[] dataSize) {
            for (CefResourceBundleHandler d : delegates) {
                if (d.getDataResourceForScale(resourceId, scaleFactor, data, dataSize)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
