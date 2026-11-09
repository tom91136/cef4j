// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

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
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefSharedProcessMessageBuilder extends CefLibraryObject {

    /**
     * Returns {@code true} if the builder is valid.
     *
     * <p>Definition generated from cef_shared_process_message_builder_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_shared_process_message_builder_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:58</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:64</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:79</a>
     */
    Optional<CefProcessMessage> build();
    /**
     * Creates a new CefSharedProcessMessageBuilder with the specified {@code name} and shared memory region of
     * specified {@code byte_size}.
     *
     * <p>Definition generated from cef_shared_process_message_builder_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_shared_process_message_builder_t* cef_shared_process_message_builder_create(const cef_string_t* name, size_t byte_size);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__shared__process__message__builder_8h.html">cef_shared_process_message_builder.h:51</a>
     */
    static Optional<CefSharedProcessMessageBuilder> create(@Nullable String name, long byteSize) {
        return Optional.ofNullable(NativePeer.create0(name, byteSize));
    }

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefSharedProcessMessageBuilder {
        NativePeer(long ptr) {
            super("CefSharedProcessMessageBuilder", ptr, NativePeer::release0);
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

        @Override
        public Optional<CefProcessMessage> build() {
            checkNotClosed();
            return Optional.ofNullable(build0(nativePtr));
        }

        static native boolean isValid0(long self);

        static native long size0(long self);

        static native NativePointer memory0(long self);

        static native CefProcessMessage build0(long self);

        static native CefSharedProcessMessageBuilder create0(@Nullable String name, long byteSize);
    }
}
