// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used for retrieving resources from the resource bundle (*.pak) files loaded by CEF during startup or via the
 * CefResourceBundleHandler returned from CefApp::GetResourceBundleHandler. See CefSettings for additional options
 * related to resource bundle loading. The methods of this class may be called on any thread unless otherwise indicated.
 */
public interface CefResourceBundle {

    /**
     * Returns the localized string for the specified |string_id| or an empty string if the value is not found. Use the
     * cef_id_for_pack_string_name() function for version-safe mapping of string IDS names from cef_pack_strings.h to
     * version-specific numerical |string_id| values.
     */
    Optional<String> getLocalizedString(int stringId);

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified scale independent |resource_id| or
     * NULL if not found. Use the cef_id_for_pack_resource_name() function for version-safe mapping of resource IDR
     * names from cef_pack_resources.h to version-specific numerical |resource_id| values.
     */
    long getDataResource(int resourceId);

    /**
     * Returns a CefBinaryValue containing the decompressed contents of the specified |resource_id| nearest the scale
     * factor |scale_factor| or NULL if not found. Use a |scale_factor| value of SCALE_FACTOR_NONE for scale independent
     * resources or call GetDataResource instead. Use the cef_id_for_pack_resource_name() function for version-safe
     * mapping of resource IDR names from cef_pack_resources.h to version-specific numerical |resource_id| values.
     */
    long getDataResourceForScale(int resourceId, @Nonnull CefScaleFactor scaleFactor);

    static class NativePeer implements CefResourceBundle {
        private volatile long nativePtr;

        @Override
        public Optional<String> getLocalizedString(int stringId) {
            return Optional.ofNullable(N_GetLocalizedString(nativePtr, stringId));
        }

        @Override
        public long getDataResource(int resourceId) {
            return N_GetDataResource(nativePtr, resourceId);
        }

        @Override
        public long getDataResourceForScale(int resourceId, CefScaleFactor scaleFactor) {
            return N_GetDataResourceForScale(nativePtr, resourceId, scaleFactor);
        }

        private native String N_GetLocalizedString(long self, int stringId);

        private native long N_GetDataResource(long self, int resourceId);

        private native long N_GetDataResourceForScale(long self, int resourceId, CefScaleFactor scaleFactor);

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
