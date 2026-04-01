// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Class that wraps platform-dependent share memory region mapping.
 *
 * <p>Definition generated from cef_shared_memory_region_capi.h
 *
 * <pre>typedef struct _cef_shared_memory_region_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_shared_memory_region_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__memory__region_8h.html">cef_shared_memory_region.h:43</a>
 */
public interface CefSharedMemoryRegion extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_shared_memory_region_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_shared_memory_region_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns the size of the shared memory region in bytes. Returns 0 for invalid instances.
     *
     * <p>Definition generated from cef_shared_memory_region_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* size)(struct _cef_shared_memory_region_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:64</a>
     */
    long size();

    NativePointer memory();

    final class NativePeer implements CefSharedMemoryRegion, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefSharedMemoryRegion.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefSharedMemoryRegion 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public long size() {
            return N_Size(nativePtr);
        }

        @Override
        public NativePointer memory() {
            return N_Memory(nativePtr);
        }

        private static native boolean N_IsValid(long self);

        private static native long N_Size(long self);

        private static native NativePointer N_Memory(long self);

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
            return "CefSharedMemoryRegion{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
