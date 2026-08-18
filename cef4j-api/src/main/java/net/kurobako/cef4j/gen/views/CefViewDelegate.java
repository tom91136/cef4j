// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefClientHandler;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * Implement this interface to handle view events. All size and position values are in density independent pixels (DIP) unless otherwise indicated. The methods of this class will be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_view_delegate_capi.h
 * <pre>typedef struct _cef_view_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_view_delegate_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefViewDelegate extends CefClientHandler {

    /**
     * Return the preferred size for {@code view}. The Layout will use this information to determine the display size.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_preferred_size)(struct _cef_view_delegate_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:54</a>
     */
    default @Nullable CefSize getPreferredSize(@Nullable CefView view) {
        return null;
    }

    /**
     * Return the minimum size for {@code view}.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_minimum_size)(struct _cef_view_delegate_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:63</a>
     */
    default @Nullable CefSize getMinimumSize(@Nullable CefView view) {
        return null;
    }

    /**
     * Return the maximum size for {@code view}.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_maximum_size)(struct _cef_view_delegate_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:69</a>
     */
    default @Nullable CefSize getMaximumSize(@Nullable CefView view) {
        return null;
    }

    /**
     * Return the height necessary to display {@code view} with the provided {@code width}. If not specified the result of GetPreferredSize().height will be used by default. Override if {@code view}'s preferred height depends upon the width (for example, with Labels).
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>int (CEF_CALLBACK* get_height_for_width)(struct _cef_view_delegate_t* self, struct _cef_view_t* view, int width);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:75</a>
     */
    default int getHeightForWidth(@Nullable CefView view, int width) {
        return 0;
    }

    /**
     * Called when the parent of {@code view} has changed. If {@code view} is being added to {@code parent} then {@code added} will be {@code true}. If {@code view} is being removed from {@code parent} then {@code added} will be {@code false}. If {@code view} is being reparented the remove notification will be sent before the add notification. Do not modify the view hierarchy in this callback.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_parent_view_changed)(struct _cef_view_delegate_t* self, struct _cef_view_t* view, int added, struct _cef_view_t* parent);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:86</a>
     */
    default void onParentViewChanged(@Nullable CefView view, boolean added, @Nullable CefView parent) {
    }

    /**
     * Called when a child of {@code view} has changed. If {@code child} is being added to {@code view} then {@code added} will be {@code true}. If {@code child} is being removed from {@code view} then {@code added} will be {@code false}. If {@code child} is being reparented the remove notification will be sent to the old parent before the add notification is sent to the new parent. Do not modify the view hierarchy in this callback.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_child_view_changed)(struct _cef_view_delegate_t* self, struct _cef_view_t* view, int added, struct _cef_view_t* child);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:98</a>
     */
    default void onChildViewChanged(@Nullable CefView view, boolean added, @Nullable CefView child) {
    }

    /**
     * Called when {@code view} is added or removed from the CefWindow.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_changed)(struct _cef_view_delegate_t* self, struct _cef_view_t* view, int added);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:110</a>
     */
    default void onWindowChanged(@Nullable CefView view, boolean added) {
    }

    /**
     * Called when the layout of {@code view} has changed.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_layout_changed)(struct _cef_view_delegate_t* self, struct _cef_view_t* view, const cef_rect_t* new_bounds);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:116</a>
     */
    default void onLayoutChanged(@Nullable CefView view, @Nonnull CefRect newBounds) {
    }

    /**
     * Called when {@code view} gains focus.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_focus)(struct _cef_view_delegate_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:123</a>
     */
    default void onFocus(@Nullable CefView view) {
    }

    /**
     * Called when {@code view} loses focus.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_blur)(struct _cef_view_delegate_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:129</a>
     */
    default void onBlur(@Nullable CefView view) {
    }

    /**
     * Called when the theme for {@code view} has changed, after the new theme colors have already been applied. Views are notified via the component hierarchy in depth-first reverse order (children before parents).
     * <p>
     * This will be called in the following cases:
     * <p>
     * 1. When {@code view}, or a parent of {@code view}, is added to a Window. 2. When the native/OS or Chrome theme changes for the Window that contains {@code view}. See {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onThemeColorsChanged(CefWindow, boolean)} documentation. 3. When the client explicitly calls {@link net.kurobako.cef4j.gen.views.CefWindow#themeChanged()} on the Window that contains {@code view}.
     * <p>
     * Optionally use this callback to override the new per-View theme colors by calling {@link net.kurobako.cef4j.gen.views.CefView#setBackgroundColor(int)} or the appropriate component-specific method. See {@link net.kurobako.cef4j.gen.views.CefWindow#setThemeColor(int, int)} documentation for how to customize additional Window theme colors.
     * <p>Definition generated from views/cef_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_theme_changed)(struct _cef_view_delegate_t* self, struct _cef_view_t* view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__view__delegate_8h.html">views/cef_view_delegate.h:135</a>
     */
    default void onThemeChanged(@Nullable CefView view) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefViewDelegate {
        private final java.util.List<CefViewDelegate> delegates;

        public Delegating(java.util.List<CefViewDelegate> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public @Nullable CefSize getPreferredSize(@Nullable CefView view) {
            if (!delegates.isEmpty()) return delegates.get(0).getPreferredSize(view);
            return null;
        }

        @Override
        public @Nullable CefSize getMinimumSize(@Nullable CefView view) {
            if (!delegates.isEmpty()) return delegates.get(0).getMinimumSize(view);
            return null;
        }

        @Override
        public @Nullable CefSize getMaximumSize(@Nullable CefView view) {
            if (!delegates.isEmpty()) return delegates.get(0).getMaximumSize(view);
            return null;
        }

        @Override
        public int getHeightForWidth(@Nullable CefView view, int width) {
            if (!delegates.isEmpty()) return delegates.get(0).getHeightForWidth(view, width);
            return 0;
        }

        @Override
        public void onParentViewChanged(@Nullable CefView view, boolean added, @Nullable CefView parent) {
            for (CefViewDelegate d : delegates) d.onParentViewChanged(view, added, parent);
        }

        @Override
        public void onChildViewChanged(@Nullable CefView view, boolean added, @Nullable CefView child) {
            for (CefViewDelegate d : delegates) d.onChildViewChanged(view, added, child);
        }

        @Override
        public void onWindowChanged(@Nullable CefView view, boolean added) {
            for (CefViewDelegate d : delegates) d.onWindowChanged(view, added);
        }

        @Override
        public void onLayoutChanged(@Nullable CefView view, @Nonnull CefRect newBounds) {
            for (CefViewDelegate d : delegates) d.onLayoutChanged(view, newBounds);
        }

        @Override
        public void onFocus(@Nullable CefView view) {
            for (CefViewDelegate d : delegates) d.onFocus(view);
        }

        @Override
        public void onBlur(@Nullable CefView view) {
            for (CefViewDelegate d : delegates) d.onBlur(view);
        }

        @Override
        public void onThemeChanged(@Nullable CefView view) {
            for (CefViewDelegate d : delegates) d.onThemeChanged(view);
        }
    }

}
