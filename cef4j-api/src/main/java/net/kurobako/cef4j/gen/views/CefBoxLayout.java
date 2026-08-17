// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefLibraryObject;

/**
 * A Layout manager that arranges child views vertically or horizontally in a side-by-side fashion with spacing around and between the child views. The child views are always sized according to their preferred size. If the host's bounds provide insufficient space, child views will be clamped. Excess space will not be distributed. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_box_layout_capi.h
 * <pre>typedef struct _cef_box_layout_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_box_layout_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__box__layout_8h.html">views/cef_box_layout.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefBoxLayout extends CefLayout {

    /**
     * Set the flex weight for the given {@code view}. Using the preferred size as the basis, free space along the main axis is distributed to views in the ratio of their flex weights. Similarly, if the views will overflow the parent, space is subtracted in these ratios. A flex of 0 means this view is not resized. Flex values must not be negative.
     * <p>Definition generated from views/cef_box_layout_capi.h
     * <pre>void (CEF_CALLBACK* set_flex_for_view)(struct _cef_box_layout_t* self, struct _cef_view_t* view, int flex);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__box__layout_8h.html">views/cef_box_layout.h:56</a>
     */
    void setFlexForView(@Nullable CefView view, int flex);

    /**
     * Clears the flex for the given {@code view}, causing it to use the default flex specified via CefBoxLayoutSettings.default_flex.
     * <p>Definition generated from views/cef_box_layout_capi.h
     * <pre>void (CEF_CALLBACK* clear_flex_for_view)(struct _cef_box_layout_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__box__layout_8h.html">views/cef_box_layout.h:66</a>
     */
    void clearFlexForView(@Nullable CefView view);
    final class NativePeer implements CefBoxLayout, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefBoxLayout has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefBoxLayout.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefBoxLayout 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
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


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefBoxLayout{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
