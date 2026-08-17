// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure containing shared texture information for the OnAcceleratedPaint callback. Resources will be released to the underlying pool for reuse when the callback returns from client code.
 * <p>Definition generated from internal/cef_types_linux.h
 * <pre>typedef struct _cef_accelerated_paint_info_t {
 *   size_t size;
 *   int plane_count;
 *   int64_t modifier;
 *   cef_color_type_t format;
 *   cef_accelerated_paint_info_common_t* extra;
 * } cef_accelerated_paint_info_t;</pre>
 * Platform-specific implementations: {@link net.kurobako.cef4j.gen.linux.CefAcceleratedPaintInfo}, {@link net.kurobako.cef4j.gen.mac.CefAcceleratedPaintInfo}, {@link net.kurobako.cef4j.gen.win.CefAcceleratedPaintInfo}.
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__linux_8h.html">internal/cef_types_linux.h:176</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefAcceleratedPaintInfo {


}
