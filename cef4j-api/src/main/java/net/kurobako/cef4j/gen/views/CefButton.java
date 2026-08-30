// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefButtonState;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * A View representing a button. Depending on the specific type, the button could be implemented by a native control or
 * custom rendered. Methods must be called on the browser process UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_button_capi.h
 *
 * <pre>typedef struct _cef_button_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_button_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefButton extends CefView {

    /**
     * Returns this Button as a LabelButton or {@code null} if this is not a LabelButton.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>cef_label_button_t* (CEF_CALLBACK* as_label_button)(struct _cef_button_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:53</a>
     */
    Optional<CefLabelButton> asLabelButton();

    /**
     * Sets the current display state of the Button.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_state)(struct _cef_button_t* self, cef_button_state_t state);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:59</a>
     */
    void setState(@Nonnull CefButtonState state);

    /**
     * Returns the current display state of the Button.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>cef_button_state_t (CEF_CALLBACK* get_state)(struct _cef_button_t* self);</pre>
     *
     * @return the result, or {@code CEF_BUTTON_STATE_NORMAL} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:65</a>
     */
    CefButtonState getState();

    /**
     * Sets the Button will use an ink drop effect for displaying state changes.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_ink_drop_enabled)(struct _cef_button_t* self, int enabled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:71</a>
     */
    void setInkDropEnabled(boolean enabled);

    /**
     * Sets the tooltip text that will be displayed when the user hovers the mouse cursor over the Button.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_tooltip_text)(struct _cef_button_t* self, const cef_string_t* tooltip_text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:77</a>
     */
    void setTooltipText(@Nullable String tooltipText);

    /**
     * Sets the accessible name that will be exposed to assistive technology (AT).
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_accessible_name)(struct _cef_button_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__button_8h.html">views/cef_button.h:84</a>
     */
    void setAccessibleName(@Nullable String name);

    final class NativePeer implements CefButton, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefButton has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefButton.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefButton 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefLabelButton> asLabelButton() {
            checkNotClosed();
            return Optional.ofNullable(asLabelButton0(nativePtr));
        }

        @Override
        public void setState(@Nonnull CefButtonState state) {
            checkNotClosed();
            setState0(nativePtr, state);
        }

        @Override
        public CefButtonState getState() {
            checkNotClosed();
            return getState0(nativePtr);
        }

        @Override
        public void setInkDropEnabled(boolean enabled) {
            checkNotClosed();
            setInkDropEnabled0(nativePtr, enabled);
        }

        @Override
        public void setTooltipText(@Nullable String tooltipText) {
            checkNotClosed();
            setTooltipText0(nativePtr, tooltipText);
        }

        @Override
        public void setAccessibleName(@Nullable String name) {
            checkNotClosed();
            setAccessibleName0(nativePtr, name);
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
        public Optional<CefTextField> asTextField() {
            checkNotClosed();
            return Optional.ofNullable(CefView.NativePeer.asTextField0(nativePtr));
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

        static native CefLabelButton asLabelButton0(long self);

        static native void setState0(long self, @Nonnull CefButtonState state);

        static native CefButtonState getState0(long self);

        static native void setInkDropEnabled0(long self, boolean enabled);

        static native void setTooltipText0(long self, @Nullable String tooltipText);

        static native void setAccessibleName0(long self, @Nullable String name);

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
            return "CefButton{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
