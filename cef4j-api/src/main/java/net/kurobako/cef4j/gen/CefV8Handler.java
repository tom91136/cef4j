// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

/**
 * Structure that should be implemented to handle V8 function calls. The functions of this structure will be called on
 * the thread associated with the V8 function. NOTE: This struct is allocated client-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:167</a>
 */
public interface CefV8Handler extends CefLibraryObject {

    /**
     * Handle execution of the function identified by {@code name}. {@code object} is the receiver ('this' object) of
     * the function. {@code arguments} is the list of arguments passed to the function. If execution succeeds set
     * {@code retval} to the function return value. If execution fails set {@code exception} to the exception that will
     * be thrown. Return {@code true} if execution was handled.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* execute)(struct _cef_v8_handler_t* self, const cef_string_t* name, struct _cef_v8_value_t* object, size_t argumentsCount, struct _cef_v8_value_t* const* arguments, struct _cef_v8_value_t** retval, cef_string_t* exception);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:234</a>
     */
    boolean execute(
            @Nonnull String name,
            @Nonnull CefV8Value object,
            long argumentscount,
            @Nonnull CefV8Value[] arguments,
            @Nonnull AtomicReference<CefV8Value> retval,
            @Nonnull String exception);

    final class NativePeer implements CefV8Handler, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8Handler.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8Handler 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean execute(
                @Nonnull String name,
                @Nonnull CefV8Value object,
                long argumentscount,
                @Nonnull CefV8Value[] arguments,
                @Nonnull AtomicReference<CefV8Value> retval,
                @Nonnull String exception) {
            return N_Execute(nativePtr, name, object, argumentscount, arguments, retval, exception);
        }

        private static native boolean N_Execute(
                long self,
                String name,
                CefV8Value object,
                long argumentscount,
                CefV8Value[] arguments,
                AtomicReference<CefV8Value> retval,
                String exception);

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
            return "CefV8Handler{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
