// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefClientHandler;

/**
 * Implement this interface to handle Button events. The methods of this class will be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_button_delegate_capi.h
 * <pre>typedef struct _cef_button_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_button_delegate_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button__delegate_8h.html">views/cef_button_delegate.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefButtonDelegate extends CefClientHandler {

    /**
     * Called when {@code button} is pressed.
     * <p>Definition generated from views/cef_button_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_button_pressed)(struct _cef_button_delegate_t* self, struct _cef_button_t* button);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button__delegate_8h.html">views/cef_button_delegate.h:52</a>
     */
    default void onButtonPressed(@Nullable CefButton button) {
    }

    /**
     * Called when the state of {@code button} changes.
     * <p>Definition generated from views/cef_button_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_button_state_changed)(struct _cef_button_delegate_t* self, struct _cef_button_t* button);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button__delegate_8h.html">views/cef_button_delegate.h:58</a>
     */
    default void onButtonStateChanged(@Nullable CefButton button) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefButtonDelegate {
        private final java.util.List<CefButtonDelegate> delegates;

        public Delegating(java.util.List<CefButtonDelegate> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onButtonPressed(@Nullable CefButton button) {
            for (CefButtonDelegate d : delegates) d.onButtonPressed(button);
        }

        @Override
        public void onButtonStateChanged(@Nullable CefButton button) {
            for (CefButtonDelegate d : delegates) d.onButtonStateChanged(button);
        }
    }

}
