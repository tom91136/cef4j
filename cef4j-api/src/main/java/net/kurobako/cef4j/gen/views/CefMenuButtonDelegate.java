// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefClientHandler;
import net.kurobako.cef4j.gen.CefPoint;

/**
 * Implement this interface to handle MenuButton events. The methods of this class will be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_menu_button_delegate_capi.h
 * <pre>typedef struct _cef_menu_button_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_menu_button_delegate_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__button__delegate_8h.html">views/cef_menu_button_delegate.h:51</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefMenuButtonDelegate extends CefClientHandler {

    /**
     * Called when {@code button} is pressed. Call {@link net.kurobako.cef4j.gen.views.CefMenuButton#showMenu(CefMenuModel, CefPoint, CefMenuAnchorPosition)} to show a popup menu at {@code screen_point}. When showing a custom popup such as a window keep a reference to {@code button_pressed_lock} until the popup is hidden to maintain the pressed button state.
     * <p>Definition generated from views/cef_menu_button_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_menu_button_pressed)(struct _cef_menu_button_delegate_t* self, struct _cef_menu_button_t* menu_button, const cef_point_t* screen_point, struct _cef_menu_button_pressed_lock_t* button_pressed_lock);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__button__delegate_8h.html">views/cef_menu_button_delegate.h:59</a>
     */
    default void onMenuButtonPressed(@Nullable CefMenuButton menuButton, @Nonnull CefPoint screenPoint, @Nullable CefMenuButtonPressedLock buttonPressedLock) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefMenuButtonDelegate {
        private final java.util.List<CefMenuButtonDelegate> delegates;

        public Delegating(java.util.List<CefMenuButtonDelegate> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onMenuButtonPressed(@Nullable CefMenuButton menuButton, @Nonnull CefPoint screenPoint, @Nullable CefMenuButtonPressedLock buttonPressedLock) {
            for (CefMenuButtonDelegate d : delegates) d.onMenuButtonPressed(menuButton, screenPoint, buttonPressedLock);
        }
    }

}
