// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Structure that should be implemented to handle V8 accessor calls. Accessor identifiers are registered by calling
 * CefV8Value.setValue(). The functions of this structure will be called on the thread associated with the V8 accessor.
 * NOTE: This struct is allocated client-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_accessor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_accessor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:196</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefV8Accessor extends CefLibraryObject {

    /**
     * Handle retrieval of the interceptor value identified by {@code index}. {@code object} is the receiver ('this'
     * object) of the interceptor. If retrieval succeeds, set {@code retval} to the return value. If the requested value
     * does not exist, don't set either {@code retval} or {@code exception}. If retrieval fails, set {@code exception}
     * to the exception that will be thrown. Return {@code true} if interceptor retrieval was handled, {@code false}
     * otherwise.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get)(struct _cef_v8_accessor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t** retval, cef_string_t* exception);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:310</a>
     */
    boolean get(
            @Nullable String name,
            @Nullable CefV8Value object,
            @Nullable AtomicReference<CefV8Value> retval,
            @Nullable String exception);

    /**
     * Handle assignment of the interceptor value identified by {@code index}. {@code object} is the receiver ('this'
     * object) of the interceptor. {@code value} is the new value being assigned to the interceptor. If assignment
     * fails, set {@code exception} to the exception that will be thrown. Return {@code true} if interceptor assignment
     * was handled, {@code false} otherwise.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set)(struct _cef_v8_accessor_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, struct _cef_v8_value_t* value, cef_string_t* exception);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:338</a>
     */
    boolean set(
            @Nullable String name, @Nullable CefV8Value object, @Nullable CefV8Value value, @Nullable String exception);

    final class NativePeer implements CefV8Accessor, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8Accessor has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8Accessor.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8Accessor 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean get(
                @Nullable String name,
                @Nullable CefV8Value object,
                @Nullable AtomicReference<CefV8Value> retval,
                @Nullable String exception) {
            checkNotClosed();
            CefLibraryObject.requireOpen(object, "CefV8Value");
            return get0(nativePtr, name, object, retval, exception);
        }

        @Override
        public boolean set(
                @Nullable String name,
                @Nullable CefV8Value object,
                @Nullable CefV8Value value,
                @Nullable String exception) {
            checkNotClosed();
            CefLibraryObject.requireOpen(object, "CefV8Value");
            CefLibraryObject.requireOpen(value, "CefV8Value");
            return set0(nativePtr, name, object, value, exception);
        }

        private static native boolean get0(
                long self, String name, CefV8Value object, AtomicReference<CefV8Value> retval, String exception);

        private static native boolean set0(
                long self, String name, CefV8Value object, CefV8Value value, String exception);

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
            return "CefV8Accessor{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
