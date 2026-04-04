// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefMenuAnchorPosition;
import net.kurobako.cef4j.gen.CefMenuModel;
import net.kurobako.cef4j.gen.CefPoint;

/**
 * MenuButton is a button with optional text, icon and/or menu marker that shows a menu when clicked with the left mouse
 * button. All size and position values are in density independent pixels (DIP) unless otherwise indicated. Methods must
 * be called on the browser process UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_menu_button_capi.h
 *
 * <pre>typedef struct _cef_menu_button_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_menu_button_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__button_8h.html">views/cef_menu_button.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefMenuButton extends CefLibraryObject {

    /**
     * Show a menu with contents {@code menu_model}. {@code screen_point} specifies the menu position in screen
     * coordinates. {@code anchor_position} specifies how the menu will be anchored relative to {@code screen_point}.
     * This method should be called from
     * {@link net.kurobako.cef4j.gen.views.CefMenuButtonDelegate#onMenuButtonPressed(CefMenuButton, CefPoint,
     * CefMenuButtonPressedLock)}.
     *
     * <p>Definition generated from views/cef_menu_button_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* show_menu)(struct _cef_menu_button_t* self, struct _cef_menu_model_t* menu_model, const cef_point_t* screen_point, cef_menu_anchor_position_t anchor_position);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__button_8h.html">views/cef_menu_button.h:69</a>
     */
    void showMenu(
            @Nullable CefMenuModel menuModel,
            @Nonnull CefPoint screenPoint,
            @Nonnull CefMenuAnchorPosition anchorPosition);

    /**
     * Show the menu for this button. Results in a call to
     * {@link net.kurobako.cef4j.gen.views.CefMenuButtonDelegate#onMenuButtonPressed(CefMenuButton, CefPoint,
     * CefMenuButtonPressedLock)}.
     *
     * <p>Definition generated from views/cef_menu_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* trigger_menu)(struct _cef_menu_button_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__button_8h.html">views/cef_menu_button.h:80</a>
     */
    void triggerMenu();
    /**
     * Create a new MenuButton. A {@code delegate} must be provided to call ShowMenu() when the button is clicked.
     * {@code text} will be shown on the MenuButton and used as the default accessible name. If {@code with_frame} is
     * {@code true} the button will have a visible frame at all times, center alignment, additional padding and a
     * default minimum size of 70x33 DIP. If {@code with_frame} is {@code false} the button will only have a visible
     * frame on hover/press, left alignment, less padding and no default minimum size.
     *
     * <p>Definition generated from views/cef_menu_button_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_menu_button_t* cef_menu_button_create(struct _cef_menu_button_delegate_t* delegate, const cef_string_t* text);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__button_8h.html">views/cef_menu_button.h:55</a>
     */
    static Optional<CefMenuButton> create(@Nullable CefMenuButtonDelegate delegate, @Nullable String text) {
        return Optional.ofNullable(NativePeer.create0(delegate, text));
    }

    final class NativePeer implements CefMenuButton, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefMenuButton has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMenuButton.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMenuButton 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void showMenu(
                @Nullable CefMenuModel menuModel,
                @Nonnull CefPoint screenPoint,
                @Nonnull CefMenuAnchorPosition anchorPosition) {
            checkNotClosed();
            CefLibraryObject.requireOpen(menuModel, "CefMenuModel");
            showMenu0(nativePtr, menuModel, screenPoint, anchorPosition);
        }

        @Override
        public void triggerMenu() {
            checkNotClosed();
            triggerMenu0(nativePtr);
        }

        private static native void showMenu0(
                long self, CefMenuModel menuModel, CefPoint screenPoint, CefMenuAnchorPosition anchorPosition);

        private static native void triggerMenu0(long self);

        static native CefMenuButton create0(CefMenuButtonDelegate delegate, String text);

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
            return "CefMenuButton{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
