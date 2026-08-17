// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBoxLayoutSettings;
import net.kurobako.cef4j.gen.CefDockingMode;
import net.kurobako.cef4j.gen.CefDraggableRegion;
import net.kurobako.cef4j.gen.CefImage;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefMenuAnchorPosition;
import net.kurobako.cef4j.gen.CefMenuModel;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRuntimeStyle;
import net.kurobako.cef4j.gen.CefSize;

/**
 * A Window is a top-level Window/widget in the Views hierarchy. By default it will have a non-client area with title bar, icon and buttons that supports moving and resizing. All size and position values are in density independent pixels (DIP) unless otherwise indicated. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_window_capi.h
 * <pre>typedef struct _cef_window_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_window_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:51</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefWindow extends CefPanel {

    /**
     * Show the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* show)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:68</a>
     */
    void show();

    /**
     * Show the Window as a browser modal dialog relative to {@code browser_view}. A parent Window must be returned via {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#getParentWindow(CefWindow, int[], int[])} and {@code browser_view} must belong to that parent Window. While this Window is visible, {@code browser_view} will be disabled while other controls in the parent Window remain enabled. Navigating or destroying the {@code browser_view} will close this Window automatically. Alternately, use Show() and return {@code true} from {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#isWindowModalDialog(CefWindow)} for a window modal dialog where all controls in the parent Window are disabled.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* show_as_browser_modal_dialog)(struct _cef_window_t* self, struct _cef_browser_view_t* browser_view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:74</a>
     */
    void showAsBrowserModalDialog(@Nullable CefBrowserView browserView);

    /**
     * Hide the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* hide)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:88</a>
     */
    void hide();

    /**
     * Sizes the Window to {@code size} and centers it in the current display.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* center_window)(struct _cef_window_t* self, const cef_size_t* size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:94</a>
     */
    void centerWindow(@Nonnull CefSize size);

    /**
     * Close the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* close)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:100</a>
     */
    void cefClose();

    /**
     * Returns {@code true} if the Window has been closed.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int (CEF_CALLBACK* is_closed)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:106</a>
     */
    boolean isClosed();

    /**
     * Activate the Window, assuming it already exists and is visible.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* activate)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:112</a>
     */
    void activate();

    /**
     * Deactivate the Window, making the next Window in the Z order the active Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* deactivate)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:118</a>
     */
    void deactivate();

    /**
     * Returns whether the Window is the currently active Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int (CEF_CALLBACK* is_active)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:125</a>
     */
    boolean isActive();

    /**
     * Bring this Window to the top of other Windows in the Windowing system.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* bring_to_top)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:131</a>
     */
    void bringToTop();

    /**
     * Set the Window to be on top of other Windows in the Windowing system.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_always_on_top)(struct _cef_window_t* self, int on_top);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:137</a>
     */
    void setAlwaysOnTop(boolean onTop);

    /**
     * Returns whether the Window has been set to be on top of other Windows in the Windowing system.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int (CEF_CALLBACK* is_always_on_top)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:143</a>
     */
    boolean isAlwaysOnTop();

    /**
     * Maximize the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* maximize)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:150</a>
     */
    void maximize();

    /**
     * Minimize the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* minimize)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:156</a>
     */
    void minimize();

    /**
     * Restore the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* restore)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:162</a>
     */
    void restore();

    /**
     * Set fullscreen Window state. The {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onWindowFullscreenTransition(CefWindow, boolean)} method will be called during the fullscreen transition for notification purposes.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_fullscreen)(struct _cef_window_t* self, int fullscreen);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:168</a>
     */
    void setFullscreen(boolean fullscreen);

    /**
     * Returns {@code true} if the Window is maximized.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int (CEF_CALLBACK* is_maximized)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:176</a>
     */
    boolean isMaximized();

    /**
     * Returns {@code true} if the Window is minimized.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int (CEF_CALLBACK* is_minimized)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:182</a>
     */
    boolean isMinimized();

    /**
     * Returns {@code true} if the Window is fullscreen.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int (CEF_CALLBACK* is_fullscreen)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:188</a>
     */
    boolean isFullscreen();

    /**
     * Returns the View that currently has focus in this Window, or {@code null} if no View currently has focus. A Window may have a focused View even if it is not currently active. Any focus changes while a Window is not active may be applied after that Window next becomes active.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_view_t* (CEF_CALLBACK* get_focused_view)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:194</a>
     */
    Optional<CefView> getFocusedView();

    /**
     * Set the Window title.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_title)(struct _cef_window_t* self, const cef_string_t* title);</pre>
     *
     * @param title may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:203</a>
     */
    void setTitle(@Nullable String title);

    /**
     * Get the Window title.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_title)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:209</a>
     */
    Optional<String> getTitle();

    /**
     * Set the Window icon. This should be a 16x16 icon suitable for use in the Windows's title bar.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_window_icon)(struct _cef_window_t* self, struct _cef_image_t* image);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:215</a>
     */
    void setWindowIcon(@Nullable CefImage image);

    /**
     * Get the Window icon.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_image_t* (CEF_CALLBACK* get_window_icon)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:222</a>
     */
    Optional<CefImage> getWindowIcon();

    /**
     * Set the Window App icon. This should be a larger icon for use in the host environment app switching UI. On Windows, this is the ICON_BIG used in Alt-Tab list and Windows taskbar. The Window icon will be used by default if no Window App icon is specified.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_window_app_icon)(struct _cef_window_t* self, struct _cef_image_t* image);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:228</a>
     */
    void setWindowAppIcon(@Nullable CefImage image);

    /**
     * Get the Window App icon.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_image_t* (CEF_CALLBACK* get_window_app_icon)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:237</a>
     */
    Optional<CefImage> getWindowAppIcon();

    /**
     * Add a View that will be overlayed on the Window contents with absolute positioning and high z-order. Positioning is controlled by {@code docking_mode} as described below. Setting {@code can_activate} to {@code true} will allow the overlay view to receive input focus. The returned CefOverlayController object is used to control the overlay. Overlays are hidden by default.
     * <p>
     * With {@link net.kurobako.cef4j.gen.CefDockingMode.Kind#CUSTOM}: 1. The overlay is initially hidden, sized to {@code view}'s preferred size, and positioned in the top-left corner. 2. Optionally change the overlay position and/or size by calling CefOverlayController methods. 3. Call {@link net.kurobako.cef4j.gen.views.CefOverlayController#setVisible(boolean)}({@code true}) to show the overlay. 4. The overlay will be automatically re-sized if {@code view}'s layout changes. Optionally change the overlay position and/or size when OnLayoutChanged is called on the Window's delegate to indicate a change in Window bounds.
     * <p>
     * With other docking modes: 1. The overlay is initially hidden, sized to {@code view}'s preferred size, and positioned based on {@code docking_mode}. 2. Call {@link net.kurobako.cef4j.gen.views.CefOverlayController#setVisible(boolean)}({@code true}) to show the overlay. 3. The overlay will be automatically re-sized if {@code view}'s layout changes and re-positioned as appropriate when the Window resizes.
     * <p>
     * Overlays created by this method will receive a higher z-order then any child Views added previously. It is therefore recommended to call this method last after all other child Views have been added so that the overlay displays as the top-most child of the Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_overlay_controller_t* (CEF_CALLBACK* add_overlay_view)(struct _cef_window_t* self, struct _cef_view_t* view, cef_docking_mode_t docking_mode, int can_activate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:243</a>
     */
    Optional<CefOverlayController> addOverlayView(@Nullable CefView view, @Nonnull CefDockingMode dockingMode, boolean canActivate);

    /**
     * Show a menu with contents {@code menu_model}. {@code screen_point} specifies the menu position in screen coordinates. {@code anchor_position} specifies how the menu will be anchored relative to {@code screen_point}.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* show_menu)(struct _cef_window_t* self, struct _cef_menu_model_t* menu_model, const cef_point_t* screen_point, cef_menu_anchor_position_t anchor_position);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:279</a>
     */
    void showMenu(@Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint, @Nonnull CefMenuAnchorPosition anchorPosition);

    /**
     * Cancel the menu that is currently showing, if any.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* cancel_menu)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:289</a>
     */
    void cancelMenu();

    /**
     * Returns the Display that most closely intersects the bounds of this Window. May return {@code null} if this Window is not currently displayed.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_display_t* (CEF_CALLBACK* get_display)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:295</a>
     */
    Optional<CefDisplay> getDisplay();

    /**
     * Returns the bounds (size and position) of this Window's client area. Position is in screen coordinates.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_client_area_bounds_in_screen)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:302</a>
     */
    CefRect getClientAreaBoundsInScreen();

    /**
     * Set the regions where mouse events will be intercepted by this Window to support drag operations. Call this method with an empty vector to clear the draggable regions. The draggable region bounds should be in window coordinates.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_draggable_regions)(struct _cef_window_t* self, size_t regionsCount, cef_draggable_region_t const* regions);</pre>
     *
     * @param regions may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:309</a>
     */
    void setDraggableRegions(long regionsCount, @Nullable CefDraggableRegion[] regions);

    /**
     * Retrieve the platform window handle for this Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>int64_t (CEF_CALLBACK* get_window_handle)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:319</a>
     */
    long getWindowHandle();

    /**
     * Simulate a key press. {@code key_code} is the VKEY_* value from Chromium's ui/events/keycodes/keyboard_codes.h header (VK_* values on Windows). {@code event_flags} is some combination of EVENTFLAG_SHIFT_DOWN, EVENTFLAG_CONTROL_DOWN and/or EVENTFLAG_ALT_DOWN. This method is exposed primarily for testing purposes.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* send_key_press)(struct _cef_window_t* self, int key_code, uint32_t event_flags);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:325</a>
     */
    void sendKeyPress(int keyCode, int eventFlags);

    /**
     * Simulate a mouse move. The mouse cursor will be moved to the specified (screen_x, screen_y) position. This method is exposed primarily for testing purposes.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* send_mouse_move)(struct _cef_window_t* self, int screen_x, int screen_y);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:335</a>
     */
    void sendMouseMove(int screenX, int screenY);

    /**
     * Simulate mouse down and/or mouse up events. {@code button} is the mouse button type. If {@code mouse_down} is {@code true} a mouse down event will be sent. If {@code mouse_up} is {@code true} a mouse up event will be sent. If both are {@code true} a mouse down event will be sent followed by a mouse up event (equivalent to clicking the mouse button). The events will be sent using the current cursor position so make sure to call SendMouseMove() first to position the mouse. This method is exposed primarily for testing purposes.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* send_mouse_events)(struct _cef_window_t* self, cef_mouse_button_type_t button, int mouse_down, int mouse_up);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:343</a>
     */
    void sendMouseEvents(@Nonnull CefMouseButtonType button, boolean mouseDown, boolean mouseUp);

    /**
     * Set the keyboard accelerator for the specified {@code command_id}. {@code key_code} can be any virtual key or character value. Required modifier keys are specified by {@code shift_pressed}, {@code ctrl_pressed} and/or {@code alt_pressed}. {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onAccelerator(CefWindow, int)} will be called if the keyboard combination is triggered while this window has focus.
     * <p>
     * The {@code high_priority} value will be considered if a child CefBrowserView has focus when the keyboard combination is triggered. If {@code high_priority} is {@code true} then the key event will not be forwarded to the web content (`keydown` event handler) or CefKeyboardHandler first. If {@code high_priority} is {@code false} then the behavior will depend on the {@link net.kurobako.cef4j.gen.views.CefBrowserView#setPreferAccelerators(boolean)} configuration.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_accelerator)(struct _cef_window_t* self, int command_id, int key_code, int shift_pressed, int ctrl_pressed, int alt_pressed, int high_priority);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:357</a>
     */
    void setAccelerator(int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed, boolean highPriority);

    /**
     * Remove the keyboard accelerator for the specified {@code command_id}.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* remove_accelerator)(struct _cef_window_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:379</a>
     */
    void removeAccelerator(int commandId);

    /**
     * Remove all keyboard accelerators.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* remove_all_accelerators)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:385</a>
     */
    void removeAllAccelerators();

    /**
     * Override a standard theme color or add a custom color associated with {@code color_id}. See cef_color_ids.h for standard ID values. Recommended usage is as follows:
     * <p>
     * 1. Customize the default native/OS theme by calling SetThemeColor before showing the first Window. When done setting colors call {@link net.kurobako.cef4j.gen.views.CefWindow#themeChanged()} to trigger {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)} notifications. 2. Customize the current native/OS or Chrome theme after it changes by calling SetThemeColor from the {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onThemeColorsChanged(CefWindow, boolean)} callback. {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)} notifications will then be triggered automatically.
     * <p>
     * The configured color will be available immediately via {@link net.kurobako.cef4j.gen.views.CefView#getThemeColor(int)} and will be applied to each View in this Window's component hierarchy when {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)} is called. See OnThemeColorsChanged documentation for additional details.
     * <p>
     * Clients wishing to add custom colors should use {@code color_id} values >= CEF_ChromeColorsEnd.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* set_theme_color)(struct _cef_window_t* self, int color_id, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:391</a>
     */
    void setThemeColor(int colorId, int color);

    /**
     * Trigger {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)} callbacks for each View in this Window's component hierarchy. Unlike a native/OS or Chrome theme change this method does not reset theme colors to standard values and does not result in a call to {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onThemeColorsChanged(CefWindow, boolean)}.
     * <p>
     * Do not call this method from {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onThemeColorsChanged(CefWindow, boolean)} or {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)}.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>void (CEF_CALLBACK* theme_changed)(struct _cef_window_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:416</a>
     */
    void themeChanged();

    /**
     * Returns the runtime style for this Window (ALLOY or CHROME). See cef_runtime_style_t documentation for details.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>cef_runtime_style_t (CEF_CALLBACK* get_runtime_style)(struct _cef_window_t* self);</pre>
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:428</a>
     */
    CefRuntimeStyle getRuntimeStyle();
    /**
     * Create a new Window.
     * <p>Definition generated from views/cef_window_capi.h
     * <pre>CEF_EXPORT cef_window_t* cef_window_create_top_level(struct _cef_window_delegate_t* delegate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__window_8h.html">views/cef_window.h:61</a>
     */
    static Optional<CefWindow> createTopLevel(@Nullable CefWindowDelegate delegate) {
      return Optional.ofNullable(NativePeer.createTopLevel0(delegate));
  }

    final class NativePeer implements CefWindow, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefWindow has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefWindow.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefWindow 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void show() {
          checkNotClosed();
          show0(nativePtr);
      }

        @Override
      public void showAsBrowserModalDialog(@Nullable CefBrowserView browserView) {
          checkNotClosed();
            CefLibraryObject.requireOpen(browserView, "CefBrowserView");
          showAsBrowserModalDialog0(nativePtr, browserView);
      }

        @Override
      public void hide() {
          checkNotClosed();
          hide0(nativePtr);
      }

        @Override
      public void centerWindow(@Nonnull CefSize size) {
          checkNotClosed();
          centerWindow0(nativePtr, size);
      }

        @Override
      public void cefClose() {
          checkNotClosed();
          cefClose0(nativePtr);
      }

        @Override
      public boolean isClosed() {
          checkNotClosed();
          return isClosed0(nativePtr);
      }

        @Override
      public void activate() {
          checkNotClosed();
          activate0(nativePtr);
      }

        @Override
      public void deactivate() {
          checkNotClosed();
          deactivate0(nativePtr);
      }

        @Override
      public boolean isActive() {
          checkNotClosed();
          return isActive0(nativePtr);
      }

        @Override
      public void bringToTop() {
          checkNotClosed();
          bringToTop0(nativePtr);
      }

        @Override
      public void setAlwaysOnTop(boolean onTop) {
          checkNotClosed();
          setAlwaysOnTop0(nativePtr, onTop);
      }

        @Override
      public boolean isAlwaysOnTop() {
          checkNotClosed();
          return isAlwaysOnTop0(nativePtr);
      }

        @Override
      public void maximize() {
          checkNotClosed();
          maximize0(nativePtr);
      }

        @Override
      public void minimize() {
          checkNotClosed();
          minimize0(nativePtr);
      }

        @Override
      public void restore() {
          checkNotClosed();
          restore0(nativePtr);
      }

        @Override
      public void setFullscreen(boolean fullscreen) {
          checkNotClosed();
          setFullscreen0(nativePtr, fullscreen);
      }

        @Override
      public boolean isMaximized() {
          checkNotClosed();
          return isMaximized0(nativePtr);
      }

        @Override
      public boolean isMinimized() {
          checkNotClosed();
          return isMinimized0(nativePtr);
      }

        @Override
      public boolean isFullscreen() {
          checkNotClosed();
          return isFullscreen0(nativePtr);
      }

        @Override
      public Optional<CefView> getFocusedView() {
          checkNotClosed();
          return Optional.ofNullable(getFocusedView0(nativePtr));
      }

        @Override
      public void setTitle(@Nullable String title) {
          checkNotClosed();
          setTitle0(nativePtr, title);
      }

        @Override
      public Optional<String> getTitle() {
          checkNotClosed();
          return Optional.ofNullable(getTitle0(nativePtr));
      }

        @Override
      public void setWindowIcon(@Nullable CefImage image) {
          checkNotClosed();
            CefLibraryObject.requireOpen(image, "CefImage");
          setWindowIcon0(nativePtr, image);
      }

        @Override
      public Optional<CefImage> getWindowIcon() {
          checkNotClosed();
          return Optional.ofNullable(getWindowIcon0(nativePtr));
      }

        @Override
      public void setWindowAppIcon(@Nullable CefImage image) {
          checkNotClosed();
            CefLibraryObject.requireOpen(image, "CefImage");
          setWindowAppIcon0(nativePtr, image);
      }

        @Override
      public Optional<CefImage> getWindowAppIcon() {
          checkNotClosed();
          return Optional.ofNullable(getWindowAppIcon0(nativePtr));
      }

        @Override
      public Optional<CefOverlayController> addOverlayView(@Nullable CefView view, @Nonnull CefDockingMode dockingMode, boolean canActivate) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return Optional.ofNullable(addOverlayView0(nativePtr, view, dockingMode, canActivate));
      }

        @Override
      public void showMenu(@Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint, @Nonnull CefMenuAnchorPosition anchorPosition) {
          checkNotClosed();
            CefLibraryObject.requireOpen(menuModel, "CefMenuModel");
          showMenu0(nativePtr, menuModel, screenPoint, anchorPosition);
      }

        @Override
      public void cancelMenu() {
          checkNotClosed();
          cancelMenu0(nativePtr);
      }

        @Override
      public Optional<CefDisplay> getDisplay() {
          checkNotClosed();
          return Optional.ofNullable(getDisplay0(nativePtr));
      }

        @Override
      public CefRect getClientAreaBoundsInScreen() {
          checkNotClosed();
          return getClientAreaBoundsInScreen0(nativePtr);
      }

        @Override
      public void setDraggableRegions(long regionsCount, @Nullable CefDraggableRegion[] regions) {
          checkNotClosed();
          setDraggableRegions0(nativePtr, regionsCount, regions);
      }

        @Override
      public long getWindowHandle() {
          checkNotClosed();
          return getWindowHandle0(nativePtr);
      }

        @Override
      public void sendKeyPress(int keyCode, int eventFlags) {
          checkNotClosed();
          sendKeyPress0(nativePtr, keyCode, eventFlags);
      }

        @Override
      public void sendMouseMove(int screenX, int screenY) {
          checkNotClosed();
          sendMouseMove0(nativePtr, screenX, screenY);
      }

        @Override
      public void sendMouseEvents(@Nonnull CefMouseButtonType button, boolean mouseDown, boolean mouseUp) {
          checkNotClosed();
          sendMouseEvents0(nativePtr, button, mouseDown, mouseUp);
      }

        @Override
      public void setAccelerator(int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed, boolean highPriority) {
          checkNotClosed();
          setAccelerator0(nativePtr, commandId, keyCode, shiftPressed, ctrlPressed, altPressed, highPriority);
      }

        @Override
      public void removeAccelerator(int commandId) {
          checkNotClosed();
          removeAccelerator0(nativePtr, commandId);
      }

        @Override
      public void removeAllAccelerators() {
          checkNotClosed();
          removeAllAccelerators0(nativePtr);
      }

        @Override
      public void setThemeColor(int colorId, int color) {
          checkNotClosed();
          setThemeColor0(nativePtr, colorId, color);
      }

        @Override
      public void themeChanged() {
          checkNotClosed();
          themeChanged0(nativePtr);
      }

        @Override
      public CefRuntimeStyle getRuntimeStyle() {
          checkNotClosed();
          return getRuntimeStyle0(nativePtr);
      }

        @Override
      public Optional<CefWindow> asWindow() {
          checkNotClosed();
          return Optional.ofNullable(CefPanel.NativePeer.asWindow0(nativePtr));
      }

        @Override
      public Optional<CefFillLayout> setToFillLayout() {
          checkNotClosed();
          return Optional.ofNullable(CefPanel.NativePeer.setToFillLayout0(nativePtr));
      }

        @Override
      public Optional<CefBoxLayout> setToBoxLayout(@Nonnull CefBoxLayoutSettings settings) {
          checkNotClosed();
          return Optional.ofNullable(CefPanel.NativePeer.setToBoxLayout0(nativePtr, settings));
      }

        @Override
      public Optional<CefLayout> getLayout() {
          checkNotClosed();
          return Optional.ofNullable(CefPanel.NativePeer.getLayout0(nativePtr));
      }

        @Override
      public void layout() {
          checkNotClosed();
          CefPanel.NativePeer.layout0(nativePtr);
      }

        @Override
      public void addChildView(@Nullable CefView view) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          CefPanel.NativePeer.addChildView0(nativePtr, view);
      }

        @Override
      public void addChildViewAt(@Nullable CefView view, int index) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          CefPanel.NativePeer.addChildViewAt0(nativePtr, view, index);
      }

        @Override
      public void reorderChildView(@Nullable CefView view, int index) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          CefPanel.NativePeer.reorderChildView0(nativePtr, view, index);
      }

        @Override
      public void removeChildView(@Nullable CefView view) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          CefPanel.NativePeer.removeChildView0(nativePtr, view);
      }

        @Override
      public void removeAllChildViews() {
          checkNotClosed();
          CefPanel.NativePeer.removeAllChildViews0(nativePtr);
      }

        @Override
      public long getChildViewCount() {
          checkNotClosed();
          return CefPanel.NativePeer.getChildViewCount0(nativePtr);
      }

        @Override
      public Optional<CefView> getChildViewAt(int index) {
          checkNotClosed();
          return Optional.ofNullable(CefPanel.NativePeer.getChildViewAt0(nativePtr, index));
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

        static native void show0(long self);

        static native void showAsBrowserModalDialog0(long self, @Nullable CefBrowserView browserView);

        static native void hide0(long self);

        static native void centerWindow0(long self, @Nonnull CefSize size);

        static native void cefClose0(long self);

        static native boolean isClosed0(long self);

        static native void activate0(long self);

        static native void deactivate0(long self);

        static native boolean isActive0(long self);

        static native void bringToTop0(long self);

        static native void setAlwaysOnTop0(long self, boolean onTop);

        static native boolean isAlwaysOnTop0(long self);

        static native void maximize0(long self);

        static native void minimize0(long self);

        static native void restore0(long self);

        static native void setFullscreen0(long self, boolean fullscreen);

        static native boolean isMaximized0(long self);

        static native boolean isMinimized0(long self);

        static native boolean isFullscreen0(long self);

        static native CefView getFocusedView0(long self);

        static native void setTitle0(long self, @Nullable String title);

        static native String getTitle0(long self);

        static native void setWindowIcon0(long self, @Nullable CefImage image);

        static native CefImage getWindowIcon0(long self);

        static native void setWindowAppIcon0(long self, @Nullable CefImage image);

        static native CefImage getWindowAppIcon0(long self);

        static native CefOverlayController addOverlayView0(long self, @Nullable CefView view, @Nonnull CefDockingMode dockingMode, boolean canActivate);

        static native void showMenu0(long self, @Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint, @Nonnull CefMenuAnchorPosition anchorPosition);

        static native void cancelMenu0(long self);

        static native CefDisplay getDisplay0(long self);

        static native CefRect getClientAreaBoundsInScreen0(long self);

        static native void setDraggableRegions0(long self, long regionsCount, @Nullable CefDraggableRegion[] regions);

        static native long getWindowHandle0(long self);

        static native void sendKeyPress0(long self, int keyCode, int eventFlags);

        static native void sendMouseMove0(long self, int screenX, int screenY);

        static native void sendMouseEvents0(long self, @Nonnull CefMouseButtonType button, boolean mouseDown, boolean mouseUp);

        static native void setAccelerator0(long self, int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed, boolean highPriority);

        static native void removeAccelerator0(long self, int commandId);

        static native void removeAllAccelerators0(long self);

        static native void setThemeColor0(long self, int colorId, int color);

        static native void themeChanged0(long self);

        static native CefRuntimeStyle getRuntimeStyle0(long self);

        static native CefWindow createTopLevel0(@Nullable CefWindowDelegate delegate);

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
            return "CefWindow{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
