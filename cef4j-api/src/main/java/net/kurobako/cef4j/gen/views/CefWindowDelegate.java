// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefClientHandler;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.gen.CefLinuxWindowProperties;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRuntimeStyle;
import net.kurobako.cef4j.gen.CefShowState;
import net.kurobako.cef4j.gen.CefState;

/**
 * Implement this interface to handle window events. The methods of this class will be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_window_delegate_capi.h
 * <pre>typedef struct _cef_window_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_window_delegate_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefWindowDelegate extends CefClientHandler {

    /**
     * Called when {@code window} is created.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_created)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:52</a>
     */
    default void onWindowCreated(@Nullable CefWindow window) {
    }

    /**
     * Called when {@code window} is closing.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_closing)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:58</a>
     */
    default void onWindowClosing(@Nullable CefWindow window) {
    }

    /**
     * Called when {@code window} is destroyed. Release all references to {@code window} and do not attempt to execute any methods on {@code window} after this callback returns.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_destroyed)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:64</a>
     */
    default void onWindowDestroyed(@Nullable CefWindow window) {
    }

    /**
     * Called when {@code window} is activated or deactivated.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_activation_changed)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, int active);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:72</a>
     */
    default void onWindowActivationChanged(@Nullable CefWindow window, boolean active) {
    }

    /**
     * Called when {@code window} bounds have changed. {@code new_bounds} will be in DIP screen coordinates.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_bounds_changed)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, const cef_rect_t* new_bounds);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:79</a>
     */
    default void onWindowBoundsChanged(@Nullable CefWindow window, @Nonnull CefRect newBounds) {
    }

    /**
     * Called when {@code window} is transitioning to or from fullscreen mode. On MacOS the transition occurs asynchronously with {@code is_completed} set to {@code false} when the transition starts and {@code true} after the transition completes. On other platforms the transition occurs synchronously with {@code is_completed} set to {@code true} after the transition completes. With Alloy style you must also implement {@link net.kurobako.cef4j.gen.CefDisplayHandler#onFullscreenModeChange(CefBrowser, boolean)} to handle fullscreen transitions initiated by browser content.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_window_fullscreen_transition)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, int is_completed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:87</a>
     */
    default void onWindowFullscreenTransition(@Nullable CefWindow window, boolean isCompleted) {
    }

    /**
     * Return the parent for {@code window} or {@code null} if the {@code window} does not have a parent. Windows with parents will not get a taskbar button. Set {@code is_menu} to {@code true} if {@code window} will be displayed as a menu, in which case it will not be clipped to the parent window bounds. Set {@code can_activate_menu} to {@code false} if {@code is_menu} is {@code true} and {@code window} should not be activated (given keyboard focus) when displayed.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>cef_window_t* (CEF_CALLBACK* get_parent_window)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, int* is_menu, int* can_activate_menu);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:100</a>
     */
    default @Nullable CefWindow getParentWindow(@Nullable CefWindow window, int[] isMenu, int[] canActivateMenu) {
        return null;
    }

    /**
     * Return {@code true} if {@code window} should be created as a window modal dialog. Only called when a Window is returned via GetParentWindow() with {@code is_menu} set to {@code false}. All controls in the parent Window will be disabled while {@code window} is visible. This functionality is not supported by all Linux window managers. Alternately, use {@link net.kurobako.cef4j.gen.views.CefWindow#showAsBrowserModalDialog(CefBrowserView)} for a browser modal dialog that works on all platforms.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* is_window_modal_dialog)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:115</a>
     */
    default boolean isWindowModalDialog(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Return the initial bounds for {@code window} in density independent pixel (DIP) coordinates. If this method returns an empty CefRect then GetPreferredSize() will be called to retrieve the size, and the window will be placed on the screen with origin (0,0). This method can be used in combination with {@link net.kurobako.cef4j.gen.views.CefView#getBoundsInScreen()} to restore the previous window bounds.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_initial_bounds)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:128</a>
     */
    default @Nullable CefRect getInitialBounds(@Nullable CefWindow window) {
        return null;
    }

    /**
     * Return the initial show state for {@code window}.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>cef_show_state_t (CEF_CALLBACK* get_initial_show_state)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @return the result, or {@code CEF_SHOW_STATE_NORMAL} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:141</a>
     */
    default @Nullable CefShowState getInitialShowState(@Nullable CefWindow window) {
        return CefShowState.of(net.kurobako.cef4j.gen.CefShowState.Kind.NORMAL);
    }

    /**
     * Return {@code true} if {@code window} should be created without a frame or title bar. The window will be resizable if CanResize() returns {@code true}. Use {@link net.kurobako.cef4j.gen.views.CefWindow#setDraggableRegions(long, CefDraggableRegion[])} to specify draggable regions.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* is_frameless)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:149</a>
     */
    default boolean isFrameless(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Return {@code true} if {@code window} should be created with standard window buttons like close, minimize and zoom. This method is only supported on macOS.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* with_standard_window_buttons)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:157</a>
     */
    default boolean withStandardWindowButtons(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Return whether the titlebar height should be overridden, and sets the height of the titlebar in {@code titlebar_height}. On macOS, it can also be used to adjust the vertical position of the traffic light buttons in frameless windows. The buttons will be positioned halfway down the titlebar at a height of {@code titlebar_height} / 2.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* get_titlebar_height)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, float* titlebar_height);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:166</a>
     */
    default boolean getTitlebarHeight(@Nullable CefWindow window, float[] titlebarHeight) {
        return false;
    }

    /**
     * Return whether the view should accept the initial mouse-down event, allowing it to respond to click-through behavior. If STATE_ENABLED is returned, the view will be sent a mouseDown: message for an initial mouse-down event, activating the view with one click, instead of clicking first to make the window active and then clicking the view.
     * <p>
     * This method is only supported on macOS. For more details, refer to the documentation of acceptsFirstMouse.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>cef_state_t (CEF_CALLBACK* accepts_first_mouse)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @return the result, or {@code STATE_DEFAULT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:179</a>
     */
    default @Nullable CefState acceptsFirstMouse(@Nullable CefWindow window) {
        return CefState.of(net.kurobako.cef4j.gen.CefState.Kind.DEFAULT);
    }

    /**
     * Return {@code true} if {@code window} can be resized.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* can_resize)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:194</a>
     */
    default boolean canResize(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Return {@code true} if {@code window} can be maximized.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* can_maximize)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:200</a>
     */
    default boolean canMaximize(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Return {@code true} if {@code window} can be minimized.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* can_minimize)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:206</a>
     */
    default boolean canMinimize(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Return {@code true} if {@code window} can be closed. This will be called for user-initiated window close actions and when {@link net.kurobako.cef4j.gen.views.CefWindow#cefClose()} is called.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* can_close)(struct _cef_window_delegate_t* self, struct _cef_window_t* window);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:212</a>
     */
    default boolean canClose(@Nullable CefWindow window) {
        return false;
    }

    /**
     * Called when a keyboard accelerator registered with {@link net.kurobako.cef4j.gen.views.CefWindow#setAccelerator(int, int, boolean, boolean, boolean, boolean)} is triggered. Return {@code true} if the accelerator was handled or {@code false} otherwise.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* on_accelerator)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:219</a>
     */
    default boolean onAccelerator(@Nullable CefWindow window, int commandId) {
        return false;
    }

    /**
     * Called after all other controls in the window have had a chance to handle the event. {@code event} contains information about the keyboard event. Return {@code true} if the keyboard event was handled or {@code false} otherwise.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* on_key_event)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, const cef_key_event_t* event);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:229</a>
     */
    default boolean onKeyEvent(@Nullable CefWindow window, @Nonnull CefKeyEvent event) {
        return false;
    }

    /**
     * Called after the native/OS or Chrome theme for {@code window} has changed. {@code chrome_theme} will be {@code true} if the notification is for a Chrome theme.
     * <p>
     * Native/OS theme colors are configured globally and do not need to be customized for each Window individually. An example of a native/OS theme change that triggers this callback is when the user switches between dark and light mode during application lifespan. Native/OS theme changes can be disabled by passing the `--force-dark-mode` or `--force-light-mode` command-line flag.
     * <p>
     * Chrome theme colors will be applied and this callback will be triggered if/when a BrowserView is added to the Window's component hierarchy. Chrome theme colors can be configured on a per-RequestContext basis using {@link net.kurobako.cef4j.gen.CefRequestContext#setChromeColorScheme(CefColorVariant, int)} or (Chrome style only) by visiting chrome://settings/manageProfile. Any theme changes using those mechanisms will also trigger this callback. Chrome theme colors will be persisted and restored from disk cache.
     * <p>
     * This callback is not triggered on Window creation so clients that wish to customize the initial native/OS theme must call {@link net.kurobako.cef4j.gen.views.CefWindow#setThemeColor(int, int)} and {@link net.kurobako.cef4j.gen.views.CefWindow#themeChanged()} before showing the first Window.
     * <p>
     * Theme colors will be reset to standard values before this callback is called for the first affected Window. Call {@link net.kurobako.cef4j.gen.views.CefWindow#setThemeColor(int, int)} from inside this callback to override a standard color or add a custom color. {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)} will be called after this callback for the complete {@code window} component hierarchy.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_theme_colors_changed)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, int chrome_theme);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:240</a>
     */
    default void onThemeColorsChanged(@Nullable CefWindow window, boolean chromeTheme) {
    }

    /**
     * Optionally change the runtime style for this Window. See cef_runtime_style_t documentation for details.
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>cef_runtime_style_t (CEF_CALLBACK* get_window_runtime_style)(struct _cef_window_delegate_t* self);</pre>
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:273</a>
     */
    default @Nullable CefRuntimeStyle getWindowRuntimeStyle() {
        return CefRuntimeStyle.of(net.kurobako.cef4j.gen.CefRuntimeStyle.Kind.DEFAULT);
    }

    /**
     * Return Linux-specific window properties for correctly handling by window managers
     * <p>Definition generated from views/cef_window_delegate_capi.h
     * <pre>int (CEF_CALLBACK* get_linux_window_properties)(struct _cef_window_delegate_t* self, struct _cef_window_t* window, struct _cef_linux_window_properties_t* properties);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window__delegate_8h.html">views/cef_window_delegate.h:282</a>
     */
    default boolean getLinuxWindowProperties(@Nullable CefWindow window, @Nonnull CefLinuxWindowProperties.Mutable properties) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefWindowDelegate {
        private final java.util.List<CefWindowDelegate> delegates;

        public Delegating(java.util.List<CefWindowDelegate> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onWindowCreated(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) d.onWindowCreated(window);
        }

        @Override
        public void onWindowClosing(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) d.onWindowClosing(window);
        }

        @Override
        public void onWindowDestroyed(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) d.onWindowDestroyed(window);
        }

        @Override
        public void onWindowActivationChanged(@Nullable CefWindow window, boolean active) {
            for (CefWindowDelegate d : delegates) d.onWindowActivationChanged(window, active);
        }

        @Override
        public void onWindowBoundsChanged(@Nullable CefWindow window, @Nonnull CefRect newBounds) {
            for (CefWindowDelegate d : delegates) d.onWindowBoundsChanged(window, newBounds);
        }

        @Override
        public void onWindowFullscreenTransition(@Nullable CefWindow window, boolean isCompleted) {
            for (CefWindowDelegate d : delegates) d.onWindowFullscreenTransition(window, isCompleted);
        }

        @Override
        public @Nullable CefWindow getParentWindow(@Nullable CefWindow window, int[] isMenu, int[] canActivateMenu) {
            if (!delegates.isEmpty()) return delegates.get(0).getParentWindow(window, isMenu, canActivateMenu);
            return null;
        }

        @Override
        public boolean isWindowModalDialog(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.isWindowModalDialog(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public @Nullable CefRect getInitialBounds(@Nullable CefWindow window) {
            if (!delegates.isEmpty()) return delegates.get(0).getInitialBounds(window);
            return null;
        }

        @Override
        public @Nullable CefShowState getInitialShowState(@Nullable CefWindow window) {
            if (!delegates.isEmpty()) return delegates.get(0).getInitialShowState(window);
            return CefShowState.of(net.kurobako.cef4j.gen.CefShowState.Kind.NORMAL);
        }

        @Override
        public boolean isFrameless(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.isFrameless(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean withStandardWindowButtons(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.withStandardWindowButtons(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean getTitlebarHeight(@Nullable CefWindow window, float[] titlebarHeight) {
            for (CefWindowDelegate d : delegates) {
                if (d.getTitlebarHeight(window, titlebarHeight)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public @Nullable CefState acceptsFirstMouse(@Nullable CefWindow window) {
            if (!delegates.isEmpty()) return delegates.get(0).acceptsFirstMouse(window);
            return CefState.of(net.kurobako.cef4j.gen.CefState.Kind.DEFAULT);
        }

        @Override
        public boolean canResize(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.canResize(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean canMaximize(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.canMaximize(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean canMinimize(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.canMinimize(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean canClose(@Nullable CefWindow window) {
            for (CefWindowDelegate d : delegates) {
                if (d.canClose(window)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onAccelerator(@Nullable CefWindow window, int commandId) {
            for (CefWindowDelegate d : delegates) {
                if (d.onAccelerator(window, commandId)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onKeyEvent(@Nullable CefWindow window, @Nonnull CefKeyEvent event) {
            for (CefWindowDelegate d : delegates) {
                if (d.onKeyEvent(window, event)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onThemeColorsChanged(@Nullable CefWindow window, boolean chromeTheme) {
            for (CefWindowDelegate d : delegates) d.onThemeColorsChanged(window, chromeTheme);
        }

        @Override
        public @Nullable CefRuntimeStyle getWindowRuntimeStyle() {
            if (!delegates.isEmpty()) return delegates.get(0).getWindowRuntimeStyle();
            return CefRuntimeStyle.of(net.kurobako.cef4j.gen.CefRuntimeStyle.Kind.DEFAULT);
        }

        @Override
        public boolean getLinuxWindowProperties(@Nullable CefWindow window, @Nonnull CefLinuxWindowProperties.Mutable properties) {
            for (CefWindowDelegate d : delegates) {
                if (d.getLinuxWindowProperties(window, properties)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
