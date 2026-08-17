// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefButtonState;
import net.kurobako.cef4j.gen.CefHorizontalAlignment;
import net.kurobako.cef4j.gen.CefImage;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefMenuAnchorPosition;
import net.kurobako.cef4j.gen.CefMenuModel;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * MenuButton is a button with optional text, icon and/or menu marker that shows a menu when clicked with the left mouse button. All size and position values are in density independent pixels (DIP) unless otherwise indicated. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_menu_button_capi.h
 * <pre>typedef struct _cef_menu_button_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_menu_button_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__button_8h.html">views/cef_menu_button.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefMenuButton extends CefLabelButton {

    /**
     * Show a menu with contents {@code menu_model}. {@code screen_point} specifies the menu position in screen coordinates. {@code anchor_position} specifies how the menu will be anchored relative to {@code screen_point}. This method should be called from {@link net.kurobako.cef4j.gen.views.CefMenuButtonDelegate#onMenuButtonPressed(CefMenuButton, CefPoint, CefMenuButtonPressedLock)}.
     * <p>Definition generated from views/cef_menu_button_capi.h
     * <pre>void (CEF_CALLBACK* show_menu)(struct _cef_menu_button_t* self, struct _cef_menu_model_t* menu_model, const cef_point_t* screen_point, cef_menu_anchor_position_t anchor_position);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__button_8h.html">views/cef_menu_button.h:69</a>
     */
    void showMenu(@Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint, @Nonnull CefMenuAnchorPosition anchorPosition);

    /**
     * Show the menu for this button. Results in a call to {@link net.kurobako.cef4j.gen.views.CefMenuButtonDelegate#onMenuButtonPressed(CefMenuButton, CefPoint, CefMenuButtonPressedLock)}.
     * <p>Definition generated from views/cef_menu_button_capi.h
     * <pre>void (CEF_CALLBACK* trigger_menu)(struct _cef_menu_button_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__button_8h.html">views/cef_menu_button.h:80</a>
     */
    void triggerMenu();
    /**
     * Create a new MenuButton. A {@code delegate} must be provided to call ShowMenu() when the button is clicked. {@code text} will be shown on the MenuButton and used as the default accessible name. If {@code with_frame} is {@code true} the button will have a visible frame at all times, center alignment, additional padding and a default minimum size of 70x33 DIP. If {@code with_frame} is {@code false} the button will only have a visible frame on hover/press, left alignment, less padding and no default minimum size.
     * <p>Definition generated from views/cef_menu_button_capi.h
     * <pre>CEF_EXPORT cef_menu_button_t* cef_menu_button_create(struct _cef_menu_button_delegate_t* delegate, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__button_8h.html">views/cef_menu_button.h:55</a>
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
      public void showMenu(@Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint, @Nonnull CefMenuAnchorPosition anchorPosition) {
          checkNotClosed();
            CefLibraryObject.requireOpen(menuModel, "CefMenuModel");
          showMenu0(nativePtr, menuModel, screenPoint, anchorPosition);
      }

        @Override
      public void triggerMenu() {
          checkNotClosed();
          triggerMenu0(nativePtr);
      }

        @Override
      public Optional<CefMenuButton> asMenuButton() {
          checkNotClosed();
          return Optional.ofNullable(CefLabelButton.NativePeer.asMenuButton0(nativePtr));
      }

        @Override
      public void setText(@Nullable String text) {
          checkNotClosed();
          CefLabelButton.NativePeer.setText0(nativePtr, text);
      }

        @Override
      public Optional<String> getText() {
          checkNotClosed();
          return Optional.ofNullable(CefLabelButton.NativePeer.getText0(nativePtr));
      }

        @Override
      public void setImage(@Nonnull CefButtonState buttonState, @Nullable CefImage image) {
          checkNotClosed();
            CefLibraryObject.requireOpen(image, "CefImage");
          CefLabelButton.NativePeer.setImage0(nativePtr, buttonState, image);
      }

        @Override
      public Optional<CefImage> getImage(@Nonnull CefButtonState buttonState) {
          checkNotClosed();
          return Optional.ofNullable(CefLabelButton.NativePeer.getImage0(nativePtr, buttonState));
      }

        @Override
      public void setTextColor(@Nonnull CefButtonState forState, int color) {
          checkNotClosed();
          CefLabelButton.NativePeer.setTextColor0(nativePtr, forState, color);
      }

        @Override
      public void setEnabledTextColors(int color) {
          checkNotClosed();
          CefLabelButton.NativePeer.setEnabledTextColors0(nativePtr, color);
      }

        @Override
      public void setFontList(@Nullable String fontList) {
          checkNotClosed();
          CefLabelButton.NativePeer.setFontList0(nativePtr, fontList);
      }

        @Override
      public void setHorizontalAlignment(@Nonnull CefHorizontalAlignment alignment) {
          checkNotClosed();
          CefLabelButton.NativePeer.setHorizontalAlignment0(nativePtr, alignment);
      }

        @Override
      public void setMinimumSize(@Nonnull CefSize size) {
          checkNotClosed();
          CefLabelButton.NativePeer.setMinimumSize0(nativePtr, size);
      }

        @Override
      public void setMaximumSize(@Nonnull CefSize size) {
          checkNotClosed();
          CefLabelButton.NativePeer.setMaximumSize0(nativePtr, size);
      }

        @Override
      public Optional<CefLabelButton> asLabelButton() {
          checkNotClosed();
          return Optional.ofNullable(CefButton.NativePeer.asLabelButton0(nativePtr));
      }

        @Override
      public void setState(@Nonnull CefButtonState state) {
          checkNotClosed();
          CefButton.NativePeer.setState0(nativePtr, state);
      }

        @Override
      public CefButtonState getState() {
          checkNotClosed();
          return CefButton.NativePeer.getState0(nativePtr);
      }

        @Override
      public void setInkDropEnabled(boolean enabled) {
          checkNotClosed();
          CefButton.NativePeer.setInkDropEnabled0(nativePtr, enabled);
      }

        @Override
      public void setTooltipText(@Nullable String tooltipText) {
          checkNotClosed();
          CefButton.NativePeer.setTooltipText0(nativePtr, tooltipText);
      }

        @Override
      public void setAccessibleName(@Nullable String name) {
          checkNotClosed();
          CefButton.NativePeer.setAccessibleName0(nativePtr, name);
      }

        @Override
      public Optional<CefBrowserView> asBrowserView() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asBrowserView0(nativePtr));
      }

        @Override
      public Optional<CefButton> asButton() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asButton0(nativePtr));
      }

        @Override
      public Optional<CefPanel> asPanel() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asPanel0(nativePtr));
      }

        @Override
      public Optional<CefScrollView> asScrollView() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asScrollView0(nativePtr));
      }

        @Override
      public Optional<CefTextfield> asTextfield() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.asTextfield0(nativePtr));
      }

        @Override
      public Optional<String> getTypeString() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getTypeString0(nativePtr));
      }

        @Override
      public Optional<String> cefToString(boolean includeChildren) {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.cefToString0(nativePtr, includeChildren));
      }

        @Override
      public boolean isValid() {
          checkNotClosed();
          return CefView.NativePeer.isValid0(nativePtr);
      }

        @Override
      public boolean isAttached() {
          checkNotClosed();
          return CefView.NativePeer.isAttached0(nativePtr);
      }

        @Override
      public boolean isSame(@Nullable CefView that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefView");
          return CefView.NativePeer.isSame0(nativePtr, that);
      }

        @Override
      public Optional<CefViewDelegate> getDelegate() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getDelegate0(nativePtr));
      }

        @Override
      public Optional<CefWindow> getWindow() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getWindow0(nativePtr));
      }

        @Override
      public int getId() {
          checkNotClosed();
          return CefView.NativePeer.getId0(nativePtr);
      }

        @Override
      public void setId(int id) {
          checkNotClosed();
          CefView.NativePeer.setId0(nativePtr, id);
      }

        @Override
      public int getGroupId() {
          checkNotClosed();
          return CefView.NativePeer.getGroupId0(nativePtr);
      }

        @Override
      public void setGroupId(int groupId) {
          checkNotClosed();
          CefView.NativePeer.setGroupId0(nativePtr, groupId);
      }

        @Override
      public Optional<CefView> getParentView() {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getParentView0(nativePtr));
      }

        @Override
      public Optional<CefView> getViewForId(int id) {
          checkNotClosed();
          return Optional.ofNullable(CefView.NativePeer.getViewForId0(nativePtr, id));
      }

        @Override
      public void setBounds(@Nonnull CefRect bounds) {
          checkNotClosed();
          CefView.NativePeer.setBounds0(nativePtr, bounds);
      }

        @Override
      public CefRect getBounds() {
          checkNotClosed();
          return CefView.NativePeer.getBounds0(nativePtr);
      }

        @Override
      public CefRect getBoundsInScreen() {
          checkNotClosed();
          return CefView.NativePeer.getBoundsInScreen0(nativePtr);
      }

        @Override
      public void setSize(@Nonnull CefSize size) {
          checkNotClosed();
          CefView.NativePeer.setSize0(nativePtr, size);
      }

        @Override
      public CefSize getSize() {
          checkNotClosed();
          return CefView.NativePeer.getSize0(nativePtr);
      }

        @Override
      public void setPosition(@Nonnull CefPoint position) {
          checkNotClosed();
          CefView.NativePeer.setPosition0(nativePtr, position);
      }

        @Override
      public CefPoint getPosition() {
          checkNotClosed();
          return CefView.NativePeer.getPosition0(nativePtr);
      }

        @Override
      public void setInsets(@Nonnull CefInsets insets) {
          checkNotClosed();
          CefView.NativePeer.setInsets0(nativePtr, insets);
      }

        @Override
      public CefInsets getInsets() {
          checkNotClosed();
          return CefView.NativePeer.getInsets0(nativePtr);
      }

        @Override
      public CefSize getPreferredSize() {
          checkNotClosed();
          return CefView.NativePeer.getPreferredSize0(nativePtr);
      }

        @Override
      public void sizeToPreferredSize() {
          checkNotClosed();
          CefView.NativePeer.sizeToPreferredSize0(nativePtr);
      }

        @Override
      public CefSize getMinimumSize() {
          checkNotClosed();
          return CefView.NativePeer.getMinimumSize0(nativePtr);
      }

        @Override
      public CefSize getMaximumSize() {
          checkNotClosed();
          return CefView.NativePeer.getMaximumSize0(nativePtr);
      }

        @Override
      public int getHeightForWidth(int width) {
          checkNotClosed();
          return CefView.NativePeer.getHeightForWidth0(nativePtr, width);
      }

        @Override
      public void invalidateLayout() {
          checkNotClosed();
          CefView.NativePeer.invalidateLayout0(nativePtr);
      }

        @Override
      public void setVisible(boolean visible) {
          checkNotClosed();
          CefView.NativePeer.setVisible0(nativePtr, visible);
      }

        @Override
      public boolean isVisible() {
          checkNotClosed();
          return CefView.NativePeer.isVisible0(nativePtr);
      }

        @Override
      public boolean isDrawn() {
          checkNotClosed();
          return CefView.NativePeer.isDrawn0(nativePtr);
      }

        @Override
      public void setEnabled(boolean enabled) {
          checkNotClosed();
          CefView.NativePeer.setEnabled0(nativePtr, enabled);
      }

        @Override
      public boolean isEnabled() {
          checkNotClosed();
          return CefView.NativePeer.isEnabled0(nativePtr);
      }

        @Override
      public void setFocusable(boolean focusable) {
          checkNotClosed();
          CefView.NativePeer.setFocusable0(nativePtr, focusable);
      }

        @Override
      public boolean isFocusable() {
          checkNotClosed();
          return CefView.NativePeer.isFocusable0(nativePtr);
      }

        @Override
      public boolean isAccessibilityFocusable() {
          checkNotClosed();
          return CefView.NativePeer.isAccessibilityFocusable0(nativePtr);
      }

        @Override
      public boolean hasFocus() {
          checkNotClosed();
          return CefView.NativePeer.hasFocus0(nativePtr);
      }

        @Override
      public void requestFocus() {
          checkNotClosed();
          CefView.NativePeer.requestFocus0(nativePtr);
      }

        @Override
      public void setBackgroundColor(int color) {
          checkNotClosed();
          CefView.NativePeer.setBackgroundColor0(nativePtr, color);
      }

        @Override
      public int getBackgroundColor() {
          checkNotClosed();
          return CefView.NativePeer.getBackgroundColor0(nativePtr);
      }

        @Override
      public int getThemeColor(int colorId) {
          checkNotClosed();
          return CefView.NativePeer.getThemeColor0(nativePtr, colorId);
      }

        @Override
      public boolean convertPointToScreen(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointToScreen0(nativePtr, point);
      }

        @Override
      public boolean convertPointFromScreen(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointFromScreen0(nativePtr, point);
      }

        @Override
      public boolean convertPointToWindow(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointToWindow0(nativePtr, point);
      }

        @Override
      public boolean convertPointFromWindow(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return CefView.NativePeer.convertPointFromWindow0(nativePtr, point);
      }

        @Override
      public boolean convertPointToView(@Nullable CefView view, @Nonnull CefPoint.Mutable point) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return CefView.NativePeer.convertPointToView0(nativePtr, view, point);
      }

        @Override
      public boolean convertPointFromView(@Nullable CefView view, @Nonnull CefPoint.Mutable point) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return CefView.NativePeer.convertPointFromView0(nativePtr, view, point);
      }

        static native void showMenu0(long self, @Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint, @Nonnull CefMenuAnchorPosition anchorPosition);

        static native void triggerMenu0(long self);

        static native CefMenuButton create0(@Nullable CefMenuButtonDelegate delegate, @Nullable String text);

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
