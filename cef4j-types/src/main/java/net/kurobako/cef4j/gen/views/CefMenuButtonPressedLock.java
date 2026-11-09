// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * MenuButton pressed lock is released when this object is destroyed.
 *
 * <p>Definition generated from views/cef_menu_button_delegate_capi.h
 *
 * <pre>typedef struct _cef_menu_button_pressed_lock_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_menu_button_pressed_lock_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__button__delegate_8h.html">views/cef_menu_button_delegate.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefMenuButtonPressedLock extends CefLibraryObject {

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefMenuButtonPressedLock {
        NativePeer(long ptr) {
            super("CefMenuButtonPressedLock", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);
    }
}
