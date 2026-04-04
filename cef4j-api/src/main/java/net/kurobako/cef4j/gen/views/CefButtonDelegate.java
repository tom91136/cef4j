// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefClientHandler;

/**
 * Implement this interface to handle Button events. The methods of this class will be called on the browser process UI
 * thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_button_delegate_capi.h
 *
 * <pre>typedef struct _cef_button_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_button_delegate_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button__delegate_8h.html">views/cef_button_delegate.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefButtonDelegate extends CefClientHandler {

    /**
     * Called when {@code button} is pressed.
     *
     * <p>Definition generated from views/cef_button_delegate_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_button_pressed)(struct _cef_button_delegate_t* self, struct _cef_button_t* button);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button__delegate_8h.html">views/cef_button_delegate.h:52</a>
     */
    default void onButtonPressed(@Nullable CefButton button) {}

    /**
     * Called when the state of {@code button} changes.
     *
     * <p>Definition generated from views/cef_button_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_button_state_changed)(struct _cef_button_delegate_t* self, struct _cef_button_t* button);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button__delegate_8h.html">views/cef_button_delegate.h:58</a>
     */
    default void onButtonStateChanged(@Nullable CefButton button) {}
}
