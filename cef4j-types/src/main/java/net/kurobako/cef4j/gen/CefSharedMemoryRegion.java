// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__memory__region_8h.html">cef_shared_memory_region.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefSharedMemoryRegion extends CefLibraryObject {

    /**
     * Returns {@code true} if the mapping is valid.
     *
     * <p>Definition generated from cef_shared_memory_region_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_shared_memory_region_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__memory__region_8h.html">cef_shared_memory_region.h:49</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__memory__region_8h.html">cef_shared_memory_region.h:55</a>
     */
    long size();

    NativePointer memory();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefSharedMemoryRegion {
        NativePeer(long ptr) {
            super("CefSharedMemoryRegion", ptr, NativePeer::release0);
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

        static native boolean isValid0(long self);

        static native long size0(long self);

        static native NativePointer memory0(long self);
    }
}
