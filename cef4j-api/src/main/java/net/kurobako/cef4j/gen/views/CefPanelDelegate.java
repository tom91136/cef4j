// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefClientHandler;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Implement this interface to handle Panel events. The methods of this class will be called on the browser process UI
 * thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_panel_delegate_capi.h
 *
 * <pre>typedef struct _cef_panel_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_panel_delegate_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__panel__delegate_8h.html">views/cef_panel_delegate.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefPanelDelegate extends CefClientHandler {}
