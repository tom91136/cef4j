// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class used to represent post data for a web request. The methods of this class may be called on any thread.
 *
 * <p>Definition generated from cef_request_capi.h
 *
 * <pre>typedef struct _cef_post_data_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_post_data_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:224</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefPostData extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is read-only.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_post_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:239</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if the underlying POST data includes elements that are not represented by this CefPostData
     * object (for example, multi-part file upload data). Modifying CefPostData objects with excluded elements may
     * result in the request failing.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_excluded_elements)(struct _cef_post_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:245</a>
     */
    boolean hasExcludedElements();

    /**
     * Returns the number of existing post data elements.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_element_count)(struct _cef_post_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:254</a>
     */
    long getElementCount();

    /**
     * Retrieve the post data elements.
     *
     * <p><b>The C API exposes this as a two-pass pattern: first call {@link #getElementCount()} to obtain the count,
     * then allocate and populate the array/collection. This method performs both steps and returns the result
     * directly.</b>
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_post_data_element_t** (CEF_CALLBACK* get_elements)(struct _cef_post_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:260</a>
     */
    List<CefPostDataElement> getElements();

    /**
     * Remove the specified post data element. Returns {@code true} if the removal succeeds.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* remove_element)(struct _cef_post_data_t* self, struct _cef_post_data_element_t* element);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:266</a>
     */
    boolean removeElement(@Nullable CefPostDataElement element);

    /**
     * Add the specified post data element. Returns {@code true} if the add succeeds.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* add_element)(struct _cef_post_data_t* self, struct _cef_post_data_element_t* element);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:273</a>
     */
    boolean addElement(@Nullable CefPostDataElement element);

    /**
     * Remove all existing post data elements.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* remove_elements)(struct _cef_post_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:279</a>
     */
    void removeElements();
    /**
     * Create a new CefPostData object.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>CEF_EXPORT cef_post_data_t* cef_post_data_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:233</a>
     */
    static Optional<CefPostData> create() {
        return Optional.ofNullable(NativePeer.create0());
    }

    final class NativePeer implements CefPostData, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPostData has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPostData.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPostData 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return isReadOnly0(nativePtr);
        }

        @Override
        public boolean hasExcludedElements() {
            checkNotClosed();
            return hasExcludedElements0(nativePtr);
        }

        @Override
        public long getElementCount() {
            checkNotClosed();
            return getElementCount0(nativePtr);
        }

        @Override
        public List<CefPostDataElement> getElements() {
            checkNotClosed();
            return Arrays.asList(getElements0(nativePtr));
        }

        @Override
        public boolean removeElement(@Nullable CefPostDataElement element) {
            checkNotClosed();
            CefLibraryObject.requireOpen(element, "CefPostDataElement");
            return removeElement0(nativePtr, element);
        }

        @Override
        public boolean addElement(@Nullable CefPostDataElement element) {
            checkNotClosed();
            CefLibraryObject.requireOpen(element, "CefPostDataElement");
            return addElement0(nativePtr, element);
        }

        @Override
        public void removeElements() {
            checkNotClosed();
            removeElements0(nativePtr);
        }

        static native boolean isReadOnly0(long self);

        static native boolean hasExcludedElements0(long self);

        static native long getElementCount0(long self);

        static native CefPostDataElement[] getElements0(long self);

        static native boolean removeElement0(long self, @Nullable CefPostDataElement element);

        static native boolean addElement0(long self, @Nullable CefPostDataElement element);

        static native void removeElements0(long self);

        static native CefPostData create0();

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
            return "CefPostData{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
