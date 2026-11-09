// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Callback interface for asynchronous continuation of print dialog requests.
 *
 * <p>Definition generated from cef_print_handler_capi.h
 *
 * <pre>typedef struct _cef_print_dialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_print_dialog_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__handler_8h.html">cef_print_handler.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefPrintDialogCallback extends CefLibraryObject {

    /**
     * Continue printing with the specified {@code settings}.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_print_dialog_callback_t* self, struct _cef_print_settings_t* settings);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__handler_8h.html">cef_print_handler.h:51</a>
     */
    void cont(@Nullable CefPrintSettings settings);

    /**
     * Cancel the printing.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_print_dialog_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__print__handler_8h.html">cef_print_handler.h:57</a>
     */
    void cancel();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefPrintDialogCallback {
        NativePeer(long ptr) {
            super("CefPrintDialogCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(@Nullable CefPrintSettings settings) {
            checkNotClosed();
            CefLibraryObject.requireOpen(settings, "CefPrintSettings");
            cont0(nativePtr, settings);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        static native void cont0(long self, @Nullable CefPrintSettings settings);

        static native void cancel0(long self);
    }
}
