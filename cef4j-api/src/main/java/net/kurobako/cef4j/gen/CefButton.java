// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefButton extends CefLibraryObject {

    /**
     * Returns this Button as a LabelButton or {@code null} if this is not a LabelButton.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>cef_label_button_t* (CEF_CALLBACK* as_label_button)(struct _cef_button_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:53</a>
     */
    Optional<CefLabelButton> asLabelButton();

    /**
     * Sets the current display state of the Button.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_state)(struct _cef_button_t* self, cef_button_state_t state);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:59</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:65</a>
     */
    CefButtonState getState();

    /**
     * Sets the Button will use an ink drop effect for displaying state changes.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_ink_drop_enabled)(struct _cef_button_t* self, int enabled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:71</a>
     */
    void setInkDropEnabled(boolean enabled);

    /**
     * Sets the tooltip text that will be displayed when the user hovers the mouse cursor over the Button.
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_tooltip_text)(struct _cef_button_t* self, const cef_string_t* tooltip_text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:77</a>
     */
    void setTooltipText(@Nullable String tooltipText);

    /**
     * Sets the accessible name that will be exposed to assistive technology (AT).
     *
     * <p>Definition generated from views/cef_button_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_accessible_name)(struct _cef_button_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__button_8h.html">views/cef_button.h:84</a>
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

        private static native CefLabelButton asLabelButton0(long self);

        private static native void setState0(long self, CefButtonState state);

        private static native CefButtonState getState0(long self);

        private static native void setInkDropEnabled0(long self, boolean enabled);

        private static native void setTooltipText0(long self, String tooltipText);

        private static native void setAccessibleName0(long self, String name);

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
