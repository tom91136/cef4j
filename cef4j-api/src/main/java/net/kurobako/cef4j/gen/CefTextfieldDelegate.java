// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Implement this interface to handle Textfield events. The methods of this class will be called on the browser process
 * UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_textfield_delegate_capi.h
 *
 * <pre>typedef struct _cef_textfield_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_textfield_delegate_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield__delegate_8h.html">views/cef_textfield_delegate.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefTextfieldDelegate extends CefClientHandler {

    /**
     * Called when {@code textfield} receives a keyboard event. {@code event} contains information about the keyboard
     * event. Return {@code true} if the keyboard event was handled or {@code false} otherwise for default handling.
     *
     * <p>Definition generated from views/cef_textfield_delegate_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_key_event)(struct _cef_textfield_delegate_t* self, struct _cef_textfield_t* textfield, const cef_key_event_t* event);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield__delegate_8h.html">views/cef_textfield_delegate.h:53</a>
     */
    default boolean onKeyEvent(@Nullable CefTextfield textfield, @Nonnull CefKeyEvent event) {
        return false;
    }

    /**
     * Called after performing a user action that may change {@code textfield}.
     *
     * <p>Definition generated from views/cef_textfield_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_after_user_action)(struct _cef_textfield_delegate_t* self, struct _cef_textfield_t* textfield);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield__delegate_8h.html">views/cef_textfield_delegate.h:64</a>
     */
    default void onAfterUserAction(@Nullable CefTextfield textfield) {}
}
