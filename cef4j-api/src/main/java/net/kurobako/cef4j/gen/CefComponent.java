// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;

/**
 * Class representing a snapshot of a component's state at the time of retrieval. To get updated information, retrieve a
 * new CefComponent object via {@link CefComponentUpdater#getComponentById(String)} or GetComponents. The methods of
 * this class may be called on any thread.
 *
 * <p>Definition generated from cef_component_updater_capi.h
 *
 * <pre>typedef struct _cef_component_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_component_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__component__updater_8h.html">cef_component_updater.h:65</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefComponent extends CefLibraryObject {

    /**
     * Returns the ID (media source URN or URL) for this source.
     *
     * <p>Definition generated from cef_component_updater_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_id)(struct _cef_component_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:299</a>
     */
    Optional<String> getId();

    /**
     * Returns the human-readable name of this component. Returns an empty string if the component is not installed.
     *
     * <p>Definition generated from cef_component_updater_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_component_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__component__updater_8h.html">cef_component_updater.h:80</a>
     */
    Optional<String> getName();

    /**
     * Returns the version of this component as a string (e.g., "1.2.3.4"). Returns an empty string if the component is
     * not installed.
     *
     * <p>Definition generated from cef_component_updater_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_version)(struct _cef_component_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__component__updater_8h.html">cef_component_updater.h:87</a>
     */
    Optional<String> getVersion();

    /**
     * Returns the state of this component at the time this object was created. A component is considered installed when
     * its state is one of: {@link CefComponentState.Kind#UPDATED}, {@link CefComponentState.Kind#UP_TO_DATE}, or
     * {@link CefComponentState.Kind#RUN}.
     *
     * <p>Definition generated from cef_component_updater_capi.h
     *
     * <pre>cef_component_state_t (CEF_CALLBACK* get_state)(struct _cef_component_t* self);</pre>
     *
     * @return the result, or {@code CEF_COMPONENT_STATE_NEW} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__component__updater_8h.html">cef_component_updater.h:94</a>
     */
    CefComponentState getState();

    final class NativePeer implements CefComponent, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefComponent has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefComponent.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefComponent 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<String> getId() {
            checkNotClosed();
            return Optional.ofNullable(getId0(nativePtr));
        }

        @Override
        public Optional<String> getName() {
            checkNotClosed();
            return Optional.ofNullable(getName0(nativePtr));
        }

        @Override
        public Optional<String> getVersion() {
            checkNotClosed();
            return Optional.ofNullable(getVersion0(nativePtr));
        }

        @Override
        public CefComponentState getState() {
            checkNotClosed();
            return getState0(nativePtr);
        }

        private static native String getId0(long self);

        private static native String getName0(long self);

        private static native String getVersion0(long self);

        private static native CefComponentState getState0(long self);

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
            return "CefComponent{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
