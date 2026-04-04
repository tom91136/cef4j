// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import java.util.Optional;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefRect;

/**
 * A ScrollView will show horizontal and/or vertical scrollbars when necessary based on the size of the attached content
 * view. Methods must be called on the browser process UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_scroll_view_capi.h
 *
 * <pre>typedef struct _cef_scroll_view_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_scroll_view_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefScrollView extends CefLibraryObject {

    /**
     * Set the content View. The content View must have a specified size (e.g. via
     * {@link net.kurobako.cef4j.gen.views.CefView#setBounds(CefRect)} or
     * {@link net.kurobako.cef4j.gen.views.CefViewDelegate#getPreferredSize(CefView)}).
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_content_view)(struct _cef_scroll_view_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:58</a>
     */
    void setContentView(@Nullable CefView view);

    /**
     * Returns the content View.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>cef_view_t* (CEF_CALLBACK* get_content_view)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:65</a>
     */
    Optional<CefView> getContentView();

    /**
     * Returns the visible region of the content View.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>cef_rect_t* (CEF_CALLBACK* get_visible_content_rect)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:71</a>
     */
    CefRect getVisibleContentRect();

    /**
     * Returns {@code true} if the horizontal scrollbar is currently showing.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_horizontal_scrollbar)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:77</a>
     */
    boolean hasHorizontalScrollbar();

    /**
     * Returns the height of the horizontal scrollbar.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_horizontal_scrollbar_height)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:83</a>
     */
    int getHorizontalScrollbarHeight();

    /**
     * Returns {@code true} if the vertical scrollbar is currently showing.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_vertical_scrollbar)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:89</a>
     */
    boolean hasVerticalScrollbar();

    /**
     * Returns the width of the vertical scrollbar.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_vertical_scrollbar_width)(struct _cef_scroll_view_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:95</a>
     */
    int getVerticalScrollbarWidth();
    /**
     * Create a new ScrollView.
     *
     * <p>Definition generated from views/cef_scroll_view_capi.h
     *
     * <pre>CEF_EXPORT cef_scroll_view_t* cef_scroll_view_create(struct _cef_view_delegate_t* delegate);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scroll__view_8h.html">views/cef_scroll_view.h:51</a>
     */
    static Optional<CefScrollView> create(@Nullable CefViewDelegate delegate) {
        return Optional.ofNullable(NativePeer.create0(delegate));
    }

    final class NativePeer implements CefScrollView, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefScrollView has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefScrollView.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefScrollView 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void setContentView(@Nullable CefView view) {
            checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
            setContentView0(nativePtr, view);
        }

        @Override
        public Optional<CefView> getContentView() {
            checkNotClosed();
            return Optional.ofNullable(getContentView0(nativePtr));
        }

        @Override
        public CefRect getVisibleContentRect() {
            checkNotClosed();
            return getVisibleContentRect0(nativePtr);
        }

        @Override
        public boolean hasHorizontalScrollbar() {
            checkNotClosed();
            return hasHorizontalScrollbar0(nativePtr);
        }

        @Override
        public int getHorizontalScrollbarHeight() {
            checkNotClosed();
            return getHorizontalScrollbarHeight0(nativePtr);
        }

        @Override
        public boolean hasVerticalScrollbar() {
            checkNotClosed();
            return hasVerticalScrollbar0(nativePtr);
        }

        @Override
        public int getVerticalScrollbarWidth() {
            checkNotClosed();
            return getVerticalScrollbarWidth0(nativePtr);
        }

        private static native void setContentView0(long self, CefView view);

        private static native CefView getContentView0(long self);

        private static native CefRect getVisibleContentRect0(long self);

        private static native boolean hasHorizontalScrollbar0(long self);

        private static native int getHorizontalScrollbarHeight0(long self);

        private static native boolean hasVerticalScrollbar0(long self);

        private static native int getVerticalScrollbarWidth0(long self);

        static native CefScrollView create0(CefViewDelegate delegate);

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
            return "CefScrollView{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
