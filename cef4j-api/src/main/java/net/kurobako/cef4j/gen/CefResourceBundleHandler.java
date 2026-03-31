// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Class used to implement a custom resource bundle interface. See CefSettings for additional options related to
 * resource bundle loading. The methods of this class may be called on multiple threads.
 */
public interface CefResourceBundleHandler {

    /**
     * Returns the localized string for the specified |string_id| or an empty string if the value is not found. Use the
     * cef_id_for_pack_string_name() function for version-safe mapping of string IDS names from cef_pack_strings.h to
     * version-specific numerical |string_id| values.
     */
    default int getLocalizedString(int stringId, @Nonnull String string) {
        return 0;
    }

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified scale independent |resource_id| or
     * NULL if not found. Use the cef_id_for_pack_resource_name() function for version-safe mapping of resource IDR
     * names from cef_pack_resources.h to version-specific numerical |resource_id| values.
     */
    default int getDataResource(int resourceId, long data, long dataSize) {
        return 0;
    }

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified |resource_id| nearest the scale
     * factor |scale_factor| or NULL if not found. Use a |scale_factor| value of SCALE_FACTOR_NONE for scale independent
     * resources or call GetDataResource instead. Use the cef_id_for_pack_resource_name() function for version-safe
     * mapping of resource IDR names from cef_pack_resources.h to version-specific numerical |resource_id| values.
     */
    default int getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor, long data, long dataSize) {
        return 0;
    }
}
