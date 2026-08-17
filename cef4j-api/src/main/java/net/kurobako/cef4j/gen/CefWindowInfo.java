// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Class representing window information.
 * <p>Definition generated from internal/cef_types_linux.h
 * <pre>typedef struct _cef_window_info_t {
 *   size_t size;
 *   cef_string_t* window_name;
 *   cef_rect_t* bounds;
 *   int64_t parent_window;
 *   int windowless_rendering_enabled;
 *   int shared_texture_enabled;
 *   int external_begin_frame_enabled;
 *   int64_t window;
 *   cef_runtime_style_t runtime_style;
 * } cef_window_info_t;</pre>
 * Platform-specific implementations: {@link net.kurobako.cef4j.gen.linux.CefWindowInfo}, {@link net.kurobako.cef4j.gen.mac.CefWindowInfo}, {@link net.kurobako.cef4j.gen.win.CefWindowInfo}.
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__linux_8h.html">internal/cef_types_linux.h:85</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefWindowInfo {


    public interface Mutable {}

}
