// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefSharedMemoryRegion extends CefLibraryObject {

    /**
     * Returns {@code true} if the mapping is valid.
     *
     * <p>Definition generated from cef_shared_memory_region_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_shared_memory_region_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__memory__region_8h.html">cef_shared_memory_region.h:49</a>
     */
    boolean isValid();

    /**
     * Returns the size of the mapping in bytes. Returns 0 for invalid instances.
     *
     * <p>Definition generated from cef_shared_memory_region_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* size)(struct _cef_shared_memory_region_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__memory__region_8h.html">cef_shared_memory_region.h:55</a>
     */
    long size();

    NativePointer memory();

    final class NativePeer implements CefSharedMemoryRegion, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefSharedMemoryRegion has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return isValid0(nativePtr);
        }

        @Override
        public long size() {
            checkNotClosed();
            return size0(nativePtr);
        }

        @Override
        public NativePointer memory() {
            checkNotClosed();
            return memory0(nativePtr);
        }

        private static native boolean isValid0(long self);

        private static native long size0(long self);

        private static native NativePointer memory0(long self);

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
