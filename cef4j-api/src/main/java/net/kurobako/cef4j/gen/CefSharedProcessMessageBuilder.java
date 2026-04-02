// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class that builds a CefProcessMessage containing a shared memory region. This class is not thread-safe but may be
 * used exclusively on a different thread from the one which constructed it.
 *
 * <p>Definition generated from cef_shared_process_message_builder_capi.h
 *
 * <pre>typedef struct _cef_shared_process_message_builder_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_shared_process_message_builder_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:43</a>
 */
public interface CefSharedProcessMessageBuilder extends CefLibraryObject {

    /**
     * Returns {@code true} if the builder is valid.
     *
     * <p>Definition generated from cef_shared_process_message_builder_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_shared_process_message_builder_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:58</a>
     */
    boolean isValid();

    /**
     * Returns the size of the shared memory region in bytes. Returns 0 for invalid instances.
     *
     * <p>Definition generated from cef_shared_process_message_builder_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* size)(struct _cef_shared_process_message_builder_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:64</a>
     */
    long size();

    NativePointer memory();

    /**
     * Creates a new CefProcessMessage from the data provided to the builder. Returns {@code null} for invalid
     * instances. Invalidates the builder instance.
     *
     * <p>Definition generated from cef_shared_process_message_builder_capi.h
     *
     * <pre>cef_process_message_t* (CEF_CALLBACK* build)(struct _cef_shared_process_message_builder_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:79</a>
     */
    Optional<CefProcessMessage> build();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_shared_process_message_builder_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_shared_process_message_builder_t* cef_shared_process_message_builder_create(const cef_string_t* name, size_t byte_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefSharedProcessMessageBuilder> create(@Nullable String name, long byteSize) {
        return Optional.ofNullable(NativePeer.N_Create(name, byteSize));
    }

    final class NativePeer implements CefSharedProcessMessageBuilder, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefSharedProcessMessageBuilder has been closed");
        }

        private static final org.slf4j.Logger _log =
                org.slf4j.LoggerFactory.getLogger(CefSharedProcessMessageBuilder.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled())
                    _log.trace("release CefSharedProcessMessageBuilder 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return N_IsValid(nativePtr);
        }

        @Override
        public long size() {
            checkNotClosed();
            return N_Size(nativePtr);
        }

        @Override
        public NativePointer memory() {
            checkNotClosed();
            return N_Memory(nativePtr);
        }

        @Override
        public Optional<CefProcessMessage> build() {
            checkNotClosed();
            return Optional.ofNullable(N_Build(nativePtr));
        }

        private static native boolean N_IsValid(long self);

        private static native long N_Size(long self);

        private static native NativePointer N_Memory(long self);

        private static native CefProcessMessage N_Build(long self);

        static native CefSharedProcessMessageBuilder N_Create(String name, long byteSize);

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
            return "CefSharedProcessMessageBuilder{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
