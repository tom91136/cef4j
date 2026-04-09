// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
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
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * LabelButton is a button with optional text and/or icon. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_label_button_capi.h
 * <pre>typedef struct _cef_label_button_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_label_button_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:47</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefLabelButton extends CefButton {

    /**
     * Returns this LabelButton as a MenuButton or {@code null} if this is not a MenuButton.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>cef_menu_button_t* (CEF_CALLBACK* as_menu_button)(struct _cef_label_button_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:64</a>
     */
    Optional<CefMenuButton> asMenuButton();

    /**
     * Sets the text shown on the LabelButton. By default {@code text} will also be used as the accessible name.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_text)(struct _cef_label_button_t* self, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:71</a>
     */
    void setText(@Nullable String text);

    /**
     * Returns the text shown on the LabelButton.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_text)(struct _cef_label_button_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:78</a>
     */
    Optional<String> getText();

    /**
     * Sets the image shown for {@code button_state}. When this Button is drawn if no image exists for the current state then the image for {@link net.kurobako.cef4j.gen.CefButtonState.Kind#NORMAL}, if any, will be shown.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_image)(struct _cef_label_button_t* self, cef_button_state_t button_state, struct _cef_image_t* image);</pre>
     *
     * @param image may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:84</a>
     */
    void setImage(@Nonnull CefButtonState buttonState, @Nullable CefImage image);

    /**
     * Returns the image shown for {@code button_state}. If no image exists for that state then the image for {@link net.kurobako.cef4j.gen.CefButtonState.Kind#NORMAL} will be returned.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>cef_image_t* (CEF_CALLBACK* get_image)(struct _cef_label_button_t* self, cef_button_state_t button_state);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:93</a>
     */
    Optional<CefImage> getImage(@Nonnull CefButtonState buttonState);

    /**
     * Sets the text color shown for the specified button {@code for_state} to {@code color}.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_text_color)(struct _cef_label_button_t* self, cef_button_state_t for_state, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:100</a>
     */
    void setTextColor(@Nonnull CefButtonState forState, int color);

    /**
     * Sets the text colors shown for the non-disabled states to {@code color}.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_enabled_text_colors)(struct _cef_label_button_t* self, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:107</a>
     */
    void setEnabledTextColors(int color);

    /**
     * Sets the font list. The format is "&lt;FONT_FAMILY_LIST&gt;,[STYLES] &lt;SIZE&gt;", where:
     * <ul>
     * <li>FONT_FAMILY_LIST is a comma-separated list of font family names,</li>
     * <li>STYLES is an optional space-separated list of style names</li>
     * </ul>
     * (case-sensitive "Bold" and "Italic" are supported), and
     * <ul>
     * <li>SIZE is an integer font size in pixels with the suffix "px".</li>
     * </ul>
     * <p>
     * Here are examples of valid font description strings:
     * <ul>
     * <li>"Arial, Helvetica, Bold Italic 14px"</li>
     * <li>"Arial, 14px"</li>
     * </ul>
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_font_list)(struct _cef_label_button_t* self, const cef_string_t* font_list);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:113</a>
     */
    void setFontList(@Nullable String fontList);

    /**
     * Sets the horizontal alignment; reversed in RTL. Default is {@link net.kurobako.cef4j.gen.CefHorizontalAlignment.Kind#CENTER}.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_horizontal_alignment)(struct _cef_label_button_t* self, cef_horizontal_alignment_t alignment);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:128</a>
     */
    void setHorizontalAlignment(@Nonnull CefHorizontalAlignment alignment);

    /**
     * Reset the minimum size of this LabelButton to {@code size}.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_minimum_size)(struct _cef_label_button_t* self, const cef_size_t* size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:135</a>
     */
    void setMinimumSize(@Nonnull CefSize size);

    /**
     * Reset the maximum size of this LabelButton to {@code size}.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>void (CEF_CALLBACK* set_maximum_size)(struct _cef_label_button_t* self, const cef_size_t* size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:141</a>
     */
    void setMaximumSize(@Nonnull CefSize size);
    /**
     * Create a new LabelButton. A {@code delegate} must be provided to handle the button click. {@code text} will be shown on the LabelButton and used as the default accessible name.
     * <p>Definition generated from views/cef_label_button_capi.h
     * <pre>CEF_EXPORT cef_label_button_t* cef_label_button_create(struct _cef_button_delegate_t* delegate, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__label__button_8h.html">views/cef_label_button.h:54</a>
     */
    static Optional<CefLabelButton> create(@Nullable CefButtonDelegate delegate, @Nullable String text) {
      return Optional.ofNullable(NativePeer.create0(delegate, text));
  }

    final class NativePeer implements CefLabelButton, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefLabelButton has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefLabelButton.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefLabelButton 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public Optional<CefMenuButton> asMenuButton() {
          checkNotClosed();
          return Optional.ofNullable(asMenuButton0(nativePtr));
      }

        @Override
      public void setText(@Nullable String text) {
          checkNotClosed();
          setText0(nativePtr, text);
      }

        @Override
      public Optional<String> getText() {
          checkNotClosed();
          return Optional.ofNullable(getText0(nativePtr));
      }

        @Override
      public void setImage(@Nonnull CefButtonState buttonState, @Nullable CefImage image) {
          checkNotClosed();
            CefLibraryObject.requireOpen(image, "CefImage");
          setImage0(nativePtr, buttonState, image);
      }

        @Override
      public Optional<CefImage> getImage(@Nonnull CefButtonState buttonState) {
          checkNotClosed();
          return Optional.ofNullable(getImage0(nativePtr, buttonState));
      }

        @Override
      public void setTextColor(@Nonnull CefButtonState forState, int color) {
          checkNotClosed();
          setTextColor0(nativePtr, forState, color);
      }

        @Override
      public void setEnabledTextColors(int color) {
          checkNotClosed();
          setEnabledTextColors0(nativePtr, color);
      }

        @Override
      public void setFontList(@Nullable String fontList) {
          checkNotClosed();
          setFontList0(nativePtr, fontList);
      }

        @Override
      public void setHorizontalAlignment(@Nonnull CefHorizontalAlignment alignment) {
          checkNotClosed();
          setHorizontalAlignment0(nativePtr, alignment);
      }

        @Override
      public void setMinimumSize(@Nonnull CefSize size) {
          checkNotClosed();
          setMinimumSize0(nativePtr, size);
      }

        @Override
      public void setMaximumSize(@Nonnull CefSize size) {
          checkNotClosed();
          setMaximumSize0(nativePtr, size);
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

        static native CefMenuButton asMenuButton0(long self);

        static native void setText0(long self, String text);

        static native String getText0(long self);

        static native void setImage0(long self, CefButtonState buttonState, CefImage image);

        static native CefImage getImage0(long self, CefButtonState buttonState);

        static native void setTextColor0(long self, CefButtonState forState, int color);

        static native void setEnabledTextColors0(long self, int color);

        static native void setFontList0(long self, String fontList);

        static native void setHorizontalAlignment0(long self, CefHorizontalAlignment alignment);

        static native void setMinimumSize0(long self, CefSize size);

        static native void setMaximumSize0(long self, CefSize size);

        static native CefLabelButton create0(CefButtonDelegate delegate, String text);

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
            return "CefLabelButton{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
