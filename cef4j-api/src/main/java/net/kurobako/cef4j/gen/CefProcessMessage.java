// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class representing a message. Can be used on any process and thread.
 * <p>Definition generated from cef_process_message_capi.h
 * <pre>typedef struct _cef_process_message_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_process_message_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:47</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefProcessMessage extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns {@code false}.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:59</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:66</a>
     */
    boolean isReadOnly();

    /**
     * Returns a writable copy of this object. Returns {@code null} when message contains a shared memory region.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>cef_process_message_t* (CEF_CALLBACK* copy)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:73</a>
     */
    Optional<CefProcessMessage> copy();

    /**
     * Returns the message name.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:80</a>
     */
    Optional<String> getName();

    /**
     * Returns the list of arguments. Returns {@code null} when message contains a shared memory region.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>cef_list_value_t* (CEF_CALLBACK* get_argument_list)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:86</a>
     */
    Optional<CefListValue> getArgumentList();

    /**
     * Returns the shared memory region. Returns {@code null} when message contains an argument list.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>cef_shared_memory_region_t* (CEF_CALLBACK* get_shared_memory_region)(struct _cef_process_message_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:93</a>
     */
    Optional<CefSharedMemoryRegion> getSharedMemoryRegion();
    /**
     * Create a new CefProcessMessage object with the specified name.
     * <p>Definition generated from cef_process_message_capi.h
     * <pre>CEF_EXPORT cef_process_message_t* cef_process_message_create(const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__process__message_8h.html">cef_process_message.h:53</a>
     */
    static Optional<CefProcessMessage> create(@Nullable String name) {
      return Optional.ofNullable(NativePeer.create0(name));
  }

    final class NativePeer implements CefProcessMessage, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefProcessMessage has been closed");
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
      public boolean isReadOnly() {
          checkNotClosed();
          return isReadOnly0(nativePtr);
      }

        @Override
      public Optional<CefProcessMessage> copy() {
          checkNotClosed();
          return Optional.ofNullable(copy0(nativePtr));
      }

        @Override
      public Optional<String> getName() {
          checkNotClosed();
          return Optional.ofNullable(getName0(nativePtr));
      }

        @Override
      public Optional<CefListValue> getArgumentList() {
          checkNotClosed();
          return Optional.ofNullable(getArgumentList0(nativePtr));
      }

        @Override
      public Optional<CefSharedMemoryRegion> getSharedMemoryRegion() {
          checkNotClosed();
          return Optional.ofNullable(getSharedMemoryRegion0(nativePtr));
      }


        static native boolean isValid0(long self);

        static native boolean isReadOnly0(long self);

        static native CefProcessMessage copy0(long self);

        static native String getName0(long self);

        static native CefListValue getArgumentList0(long self);

        static native CefSharedMemoryRegion getSharedMemoryRegion0(long self);

        static native CefProcessMessage create0(String name);

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
