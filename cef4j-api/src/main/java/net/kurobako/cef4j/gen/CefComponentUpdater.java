// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class provides access to Chromium's component updater service, allowing clients to discover registered components and trigger on-demand updates. The methods of this class may only be called on the browser process UI thread. If the CEF context is not initialized or the component updater service is not available, methods will return safe defaults (0, {@code null}, or empty).
 * <p>Definition generated from cef_component_updater_capi.h
 * <pre>typedef struct _cef_component_updater_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_component_updater_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__component__updater_8h.html">cef_component_updater.h:104</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefComponentUpdater extends CefLibraryObject {

    /**
     * Returns the number of registered components, or 0 if the service is not available.
     * <p>Definition generated from cef_component_updater_capi.h
     * <pre>size_t (CEF_CALLBACK* get_component_count)(struct _cef_component_updater_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__component__updater_8h.html">cef_component_updater.h:121</a>
     */
    long getComponentCount();

    /**
     * Populates {@code components} with all registered components. Any existing contents will be cleared first.
     * <p><b>The C API exposes this as a two-pass pattern: first call {@link #getComponentCount()} to obtain
     * the count, then allocate and populate the array/collection. This method performs both steps and returns the
     * result directly.</b>
     * <p>Definition generated from cef_component_updater_capi.h
     * <pre>cef_component_t** (CEF_CALLBACK* get_components)(struct _cef_component_updater_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__component__updater_8h.html">cef_component_updater.h:128</a>
     */
    List<CefComponent> getComponents();

    /**
     * Returns the component with the specified {@code component_id}, or {@code null} if not found or the service is not available.
     * <p>Definition generated from cef_component_updater_capi.h
     * <pre>cef_component_t* (CEF_CALLBACK* get_component_by_id)(struct _cef_component_updater_t* self, const cef_string_t* component_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__component__updater_8h.html">cef_component_updater.h:136</a>
     */
    Optional<CefComponent> getComponentById(@Nullable String componentId);

    /**
     * Triggers an on-demand update for the component with the specified {@code component_id}. {@code priority} specifies whether the update should be processed in the background or foreground. Use {@link net.kurobako.cef4j.gen.CefComponentUpdatePriority.Kind#FOREGROUND} for user-initiated updates.
     * <p>
     * {@code callback} will be called asynchronously on the UI thread when the update operation completes. The callback is always executed, including when the component is already up-to-date (returns {@link net.kurobako.cef4j.gen.CefComponentUpdateError.Kind#NONE}), when the requested component doesn't exist, or when the service is unavailable (returns {@link net.kurobako.cef4j.gen.CefComponentUpdateError.Kind#SERVICE_ERROR}). The callback may be {@code null} if no notification is needed.
     * <p>Definition generated from cef_component_updater_capi.h
     * <pre>void (CEF_CALLBACK* update)(struct _cef_component_updater_t* self, const cef_string_t* component_id, cef_component_update_priority_t priority, struct _cef_component_update_callback_t* callback);</pre>
     *
     * @param callback may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__component__updater_8h.html">cef_component_updater.h:144</a>
     */
    void update(@Nullable String componentId, @Nonnull CefComponentUpdatePriority priority, @Nullable CefComponentUpdateCallback callback);
    /**
     * Returns the global CefComponentUpdater singleton. Returns {@code null} if called from the incorrect thread.
     * <p>Definition generated from cef_component_updater_capi.h
     * <pre>CEF_EXPORT cef_component_updater_t* cef_component_updater_get(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__component__updater_8h.html">cef_component_updater.h:114</a>
     */
    static Optional<CefComponentUpdater> get() {
      return Optional.ofNullable(NativePeer.get0());
  }

    final class NativePeer implements CefComponentUpdater, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefComponentUpdater has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefComponentUpdater.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefComponentUpdater 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public long getComponentCount() {
          checkNotClosed();
          return getComponentCount0(nativePtr);
      }

        @Override
      public List<CefComponent> getComponents() {
          checkNotClosed();
          return Arrays.asList(getComponents0(nativePtr));
      }

        @Override
      public Optional<CefComponent> getComponentById(@Nullable String componentId) {
          checkNotClosed();
          return Optional.ofNullable(getComponentById0(nativePtr, componentId));
      }

        @Override
      public void update(@Nullable String componentId, @Nonnull CefComponentUpdatePriority priority, @Nullable CefComponentUpdateCallback callback) {
          checkNotClosed();
          update0(nativePtr, componentId, priority, callback);
      }


        static native long getComponentCount0(long self);

        static native CefComponent[] getComponents0(long self);

        static native CefComponent getComponentById0(long self, @Nullable String componentId);

        static native void update0(long self, @Nullable String componentId, @Nonnull CefComponentUpdatePriority priority, @Nullable CefComponentUpdateCallback callback);

        static native CefComponentUpdater get0();

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
