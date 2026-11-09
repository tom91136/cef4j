// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * A Layout handles the sizing of the children of a Panel according to implementation-specific heuristics. Methods must
 * be called on the browser process UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_layout_capi.h
 *
 * <pre>typedef struct _cef_layout_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_layout_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__layout_8h.html">views/cef_layout.h:46</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefLayout extends CefLibraryObject {

    /**
     * Returns this Layout as a BoxLayout or {@code null} if this is not a BoxLayout.
     *
     * <p>Definition generated from views/cef_layout_capi.h
     *
     * <pre>cef_box_layout_t* (CEF_CALLBACK* as_box_layout)(struct _cef_layout_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__layout_8h.html">views/cef_layout.h:54</a>
     */
    Optional<CefBoxLayout> asBoxLayout();

    /**
     * Returns this Layout as a FillLayout or {@code null} if this is not a FillLayout.
     *
     * <p>Definition generated from views/cef_layout_capi.h
     *
     * <pre>cef_fill_layout_t* (CEF_CALLBACK* as_fill_layout)(struct _cef_layout_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__layout_8h.html">views/cef_layout.h:60</a>
     */
    Optional<CefFillLayout> asFillLayout();

    /**
     * Returns {@code true} if this Layout is valid.
     *
     * <p>Definition generated from views/cef_layout_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_layout_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__layout_8h.html">views/cef_layout.h:66</a>
     */
    boolean isValid();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefLayout {
        NativePeer(long ptr) {
            super("CefLayout", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefBoxLayout> asBoxLayout() {
            checkNotClosed();
            return Optional.ofNullable(asBoxLayout0(nativePtr));
        }

        @Override
        public Optional<CefFillLayout> asFillLayout() {
            checkNotClosed();
            return Optional.ofNullable(asFillLayout0(nativePtr));
        }

        @Override
        public boolean isValid() {
            checkNotClosed();
            return isValid0(nativePtr);
        }

        static native CefBoxLayout asBoxLayout0(long self);

        static native CefFillLayout asFillLayout0(long self);

        static native boolean isValid0(long self);
    }
}
