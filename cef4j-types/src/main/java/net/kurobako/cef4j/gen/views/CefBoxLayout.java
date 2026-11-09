// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * A Layout manager that arranges child views vertically or horizontally in a side-by-side fashion with spacing around
 * and between the child views. The child views are always sized according to their preferred size. If the host's bounds
 * provide insufficient space, child views will be clamped. Excess space will not be distributed. Methods must be called
 * on the browser process UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_box_layout_capi.h
 *
 * <pre>typedef struct _cef_box_layout_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_box_layout_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__box__layout_8h.html">views/cef_box_layout.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefBoxLayout extends CefLayout {

    /**
     * Set the flex weight for the given {@code view}. Using the preferred size as the basis, free space along the main
     * axis is distributed to views in the ratio of their flex weights. Similarly, if the views will overflow the
     * parent, space is subtracted in these ratios. A flex of 0 means this view is not resized. Flex values must not be
     * negative.
     *
     * <p>Definition generated from views/cef_box_layout_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_flex_for_view)(struct _cef_box_layout_t* self, struct _cef_view_t* view, int flex);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__box__layout_8h.html">views/cef_box_layout.h:56</a>
     */
    void setFlexForView(@Nullable CefView view, int flex);

    /**
     * Clears the flex for the given {@code view}, causing it to use the default flex specified via
     * CefBoxLayoutSettings.default_flex.
     *
     * <p>Definition generated from views/cef_box_layout_capi.h
     *
     * <pre>void (CEF_CALLBACK* clear_flex_for_view)(struct _cef_box_layout_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__box__layout_8h.html">views/cef_box_layout.h:66</a>
     */
    void clearFlexForView(@Nullable CefView view);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefBoxLayout {
        NativePeer(long ptr) {
            super("CefBoxLayout", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void setFlexForView(@Nullable CefView view, int flex) {
            checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
            setFlexForView0(nativePtr, view, flex);
        }

        @Override
        public void clearFlexForView(@Nullable CefView view) {
            checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
            clearFlexForView0(nativePtr, view);
        }

        @Override
        public Optional<CefBoxLayout> asBoxLayout() {
            checkNotClosed();
            return Optional.ofNullable(CefLayout.NativePeer.asBoxLayout0(nativePtr));
        }

        @Override
        public Optional<CefFillLayout> asFillLayout() {
            checkNotClosed();
            return Optional.ofNullable(CefLayout.NativePeer.asFillLayout0(nativePtr));
        }

        @Override
        public boolean isValid() {
            checkNotClosed();
            return CefLayout.NativePeer.isValid0(nativePtr);
        }

        static native void setFlexForView0(long self, @Nullable CefView view, int flex);

        static native void clearFlexForView0(long self, @Nullable CefView view);
    }
}
