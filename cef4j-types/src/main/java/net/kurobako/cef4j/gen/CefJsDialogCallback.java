// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Callback interface used for asynchronous continuation of JavaScript dialog requests.
 *
 * <p>Definition generated from cef_jsdialog_handler_capi.h
 *
 * <pre>typedef struct _cef_jsdialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_jsdialog_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:44</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefJsDialogCallback extends CefLibraryObject {

    /**
     * Continue the JS dialog request. Set {@code success} to {@code true} if the OK button was pressed. The
     * {@code user_input} value should be specified for prompt dialogs.
     *
     * <p>Definition generated from cef_jsdialog_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_jsdialog_callback_t* self, int success, const cef_string_t* user_input);
     * </pre>
     *
     * @param userInput may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:51</a>
     */
    void cont(int success, @Nullable String userInput);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefJsDialogCallback {
        NativePeer(long ptr) {
            super("CefJsDialogCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(int success, @Nullable String userInput) {
            checkNotClosed();
            cont0(nativePtr, success, userInput);
        }

        static native void cont0(long self, int success, @Nullable String userInput);
    }
}
