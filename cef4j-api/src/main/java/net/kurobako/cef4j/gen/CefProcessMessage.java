// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class representing a message. Can be used on any process and thread.
 *
 * <p>Definition generated from cef_process_message_capi.h
 *
 * <pre>typedef struct _cef_process_message_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_process_message_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:47</a>
 */
public interface CefProcessMessage extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:68</a>
     */
    boolean isReadOnly();

    /**
     * Returns a writable copy of this object.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>cef_process_message_t* (CEF_CALLBACK* copy)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:90</a>
     */
    Optional<CefProcessMessage> copy();

    /**
     * Returns the name of this node.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:215</a>
     */
    Optional<String> getName();

    /**
     * Returns the list of arguments. Returns {@code null} when message contains a shared memory region.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_argument_list)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:86</a>
     */
    Optional<CefListValue> getArgumentList();

    /**
     * Returns the shared memory region. Returns {@code null} when message contains an argument list.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>cef_shared_memory_region_t* (CEF_CALLBACK* get_shared_memory_region)(struct _cef_process_message_t* self);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:93</a>
     */
    Optional<CefSharedMemoryRegion> getSharedMemoryRegion();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_process_message_capi.h
     *
     * <pre>CEF_EXPORT cef_process_message_t* cef_process_message_create(const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefProcessMessage> create(@Nonnull String name) {
        return Optional.ofNullable(NativePeer.N_Create(name));
    }

    final class NativePeer implements CefProcessMessage, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefProcessMessage.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefProcessMessage 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public Optional<CefProcessMessage> copy() {
            return Optional.ofNullable(N_Copy(nativePtr));
        }

        @Override
        public Optional<String> getName() {
            return Optional.ofNullable(N_GetName(nativePtr));
        }

        @Override
        public Optional<CefListValue> getArgumentList() {
            return Optional.ofNullable(N_GetArgumentList(nativePtr));
        }

        @Override
        public Optional<CefSharedMemoryRegion> getSharedMemoryRegion() {
            return Optional.ofNullable(N_GetSharedMemoryRegion(nativePtr));
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsReadOnly(long self);

        private static native CefProcessMessage N_Copy(long self);

        private static native String N_GetName(long self);

        private static native CefListValue N_GetArgumentList(long self);

        private static native CefSharedMemoryRegion N_GetSharedMemoryRegion(long self);

        static native CefProcessMessage N_Create(String name);

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
            return "CefProcessMessage{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
