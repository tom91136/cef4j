// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * This class provides access to Chromium's component updater service, allowing clients to discover registered
 * components and trigger on-demand updates. The methods of this class may only be called on the browser process UI
 * thread. If the CEF context is not initialized or the component updater service is not available, methods will return
 * safe defaults (0, nullptr, or empty).
 */
public interface CefComponentUpdater {

    /** Returns the number of registered components, or 0 if the service is not available. */
    long getComponentCount();

    /**
     * Populates |components| with all registered components. Any existing contents will be cleared first.
     *
     * <p>The size of {@code components} is determined by {@code GetComponentCount()}.
     */
    void getComponents(long componentsCount, long components);

    long getComponentById(@Nonnull String componentId);

    /**
     * Triggers an on-demand update for the component with the specified |component_id|. |priority| specifies whether
     * the update should be processed in the background or foreground. Use CEF_COMPONENT_UPDATE_PRIORITY_FOREGROUND for
     * user-initiated updates. |callback| will be called asynchronously on the UI thread when the update operation
     * completes. The callback is always executed, including when the component is already up-to-date (returns
     * CEF_COMPONENT_UPDATE_ERROR_NONE), when the requested component doesn't exist, or when the service is unavailable
     * (returns CEF_COMPONENT_UPDATE_ERROR_SERVICE_ERROR). The callback may be nullptr if no notification is needed.
     *
     * @param callback may be null
     */
    void update(@Nonnull String componentId, @Nonnull CefComponentUpdatePriority priority, long callback);

    static class NativePeer implements CefComponentUpdater {
        private volatile long nativePtr;

        @Override
        public long getComponentCount() {
            return N_GetComponentCount(nativePtr);
        }

        @Override
        public void getComponents(long componentsCount, long components) {
            N_GetComponents(nativePtr, componentsCount, components);
        }

        @Override
        public long getComponentById(String componentId) {
            return N_GetComponentById(nativePtr, componentId);
        }

        @Override
        public void update(String componentId, CefComponentUpdatePriority priority, long callback) {
            N_Update(nativePtr, componentId, priority, callback);
        }

        private native long N_GetComponentCount(long self);

        private native void N_GetComponents(long self, long componentsCount, long components);

        private native long N_GetComponentById(long self, String componentId);

        private native void N_Update(long self, String componentId, CefComponentUpdatePriority priority, long callback);

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
            return "CefComponentUpdater{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
