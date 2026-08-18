// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Implement this interface to receive accessibility notification when accessibility events have been registered. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_accessibility_handler_capi.h
 * <pre>typedef struct _cef_accessibility_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_accessibility_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__accessibility__handler_8h.html">cef_accessibility_handler.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefAccessibilityHandler extends CefClientHandler {

    /**
     * Called after renderer process sends accessibility tree changes to the browser process.
     * <p>Definition generated from cef_accessibility_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_accessibility_tree_change)(struct _cef_accessibility_handler_t* self, struct _cef_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__accessibility__handler_8h.html">cef_accessibility_handler.h:51</a>
     */
    default void onAccessibilityTreeChange(@Nullable CefValue value) {
    }

    /**
     * Called after renderer process sends accessibility location changes to the browser process.
     * <p>Definition generated from cef_accessibility_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_accessibility_location_change)(struct _cef_accessibility_handler_t* self, struct _cef_value_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__accessibility__handler_8h.html">cef_accessibility_handler.h:58</a>
     */
    default void onAccessibilityLocationChange(@Nullable CefValue value) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefAccessibilityHandler {
        private final java.util.List<CefAccessibilityHandler> delegates;

        public Delegating(java.util.List<CefAccessibilityHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onAccessibilityTreeChange(@Nullable CefValue value) {
            for (CefAccessibilityHandler d : delegates) d.onAccessibilityTreeChange(value);
        }

        @Override
        public void onAccessibilityLocationChange(@Nullable CefValue value) {
            for (CefAccessibilityHandler d : delegates) d.onAccessibilityLocationChange(value);
        }
    }

}
