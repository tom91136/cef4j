// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefClientHandler;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__textfield__delegate_8h.html">views/cef_textfield_delegate.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefTextFieldDelegate extends CefClientHandler {

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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__textfield__delegate_8h.html">views/cef_textfield_delegate.h:53</a>
     */
    default boolean onKeyEvent(@Nullable CefTextField textfield, @Nonnull CefKeyEvent event) {
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__textfield__delegate_8h.html">views/cef_textfield_delegate.h:64</a>
     */
    default void onAfterUserAction(@Nullable CefTextField textfield) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefTextFieldDelegate {
        private final java.util.List<CefTextFieldDelegate> delegates;

        public Delegating(java.util.List<CefTextFieldDelegate> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean onKeyEvent(@Nullable CefTextField textfield, @Nonnull CefKeyEvent event) {
            for (CefTextFieldDelegate d : delegates) {
                if (d.onKeyEvent(textfield, event)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onAfterUserAction(@Nullable CefTextField textfield) {
            for (CefTextFieldDelegate d : delegates) d.onAfterUserAction(textfield);
        }
    }
}
