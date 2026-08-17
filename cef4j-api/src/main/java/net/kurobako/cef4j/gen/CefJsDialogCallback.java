// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Callback interface used for asynchronous continuation of JavaScript dialog requests.
 * <p>Definition generated from cef_jsdialog_handler_capi.h
 * <pre>typedef struct _cef_jsdialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_jsdialog_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefJsDialogCallback extends CefLibraryObject {

    /**
     * Continue the JS dialog request. Set {@code success} to {@code true} if the OK button was pressed. The {@code user_input} value should be specified for prompt dialogs.
     * <p>Definition generated from cef_jsdialog_handler_capi.h
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_jsdialog_callback_t* self, int success, const cef_string_t* user_input);</pre>
     *
     * @param userInput may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:51</a>
     */
    void cont(int success, @Nullable String userInput);
    final class NativePeer implements CefJsDialogCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefJsDialogCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefJsDialogCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefJsDialogCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void cont(int success, @Nullable String userInput) {
          checkNotClosed();
          cont0(nativePtr, success, userInput);
      }


        static native void cont0(long self, int success, @Nullable String userInput);


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
            return "CefJsDialogCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
