// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Callback interface used for continuation of custom quick menu display.
 *
 * <p>Definition generated from cef_context_menu_handler_capi.h
 *
 * <pre>typedef struct _cef_run_quick_menu_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_run_quick_menu_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:68</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefRunQuickMenuCallback extends CefLibraryObject {

    /**
     * Complete quick menu display by selecting the specified {@code command_id} and {@code event_flags}.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_run_quick_menu_callback_t* self, int command_id, cef_event_flags_t event_flags);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:74</a>
     */
    void cont(int commandId, CefEventFlags eventFlags);

    /**
     * Cancel quick menu display.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_run_quick_menu_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:81</a>
     */
    void cancel();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefRunQuickMenuCallback {
        NativePeer(long ptr) {
            super("CefRunQuickMenuCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(int commandId, CefEventFlags eventFlags) {
            checkNotClosed();
            cont0(nativePtr, commandId, eventFlags);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        static native void cont0(long self, int commandId, CefEventFlags eventFlags);

        static native void cancel0(long self);
    }
}
