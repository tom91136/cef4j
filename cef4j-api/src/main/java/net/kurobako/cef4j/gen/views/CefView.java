// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefInsets;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSize;

/**
 * A View is a rectangle within the views View hierarchy. It is the base class for all Views. All size and position values are in density independent pixels (DIP) unless otherwise indicated. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_view_capi.h
 * <pre>typedef struct _cef_view_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_view_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:50</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefView extends CefLibraryObject {

    /**
     * Returns this View as a BrowserView or {@code null} if this is not a BrowserView.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_browser_view_t* (CEF_CALLBACK* as_browser_view)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:59</a>
     */
    Optional<CefBrowserView> asBrowserView();

    /**
     * Returns this View as a Button or {@code null} if this is not a Button.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_button_t* (CEF_CALLBACK* as_button)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:65</a>
     */
    Optional<CefButton> asButton();

    /**
     * Returns this View as a Panel or {@code null} if this is not a Panel.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_panel_t* (CEF_CALLBACK* as_panel)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:71</a>
     */
    Optional<CefPanel> asPanel();

    /**
     * Returns this View as a ScrollView or {@code null} if this is not a ScrollView.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_scroll_view_t* (CEF_CALLBACK* as_scroll_view)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:77</a>
     */
    Optional<CefScrollView> asScrollView();

    /**
     * Returns this View as a Textfield or {@code null} if this is not a Textfield.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_textfield_t* (CEF_CALLBACK* as_textfield)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:83</a>
     */
    Optional<CefTextfield> asTextfield();

    /**
     * Returns the type of this View as a string. Used primarily for testing purposes.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_type_string)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:89</a>
     */
    Optional<String> getTypeString();

    /**
     * Returns a string representation of this View which includes the type and various type-specific identifying attributes. If {@code include_children} is {@code true} any child Views will also be included. Used primarily for testing purposes.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* to_string)(struct _cef_view_t* self, int include_children);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:96</a>
     */
    Optional<String> cefToString(boolean includeChildren);

    /**
     * Returns {@code true} if this View is valid.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:105</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this View is currently attached to another View. A View can only be attached to one View at a time.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_attached)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:111</a>
     */
    boolean isAttached();

    /**
     * Returns {@code true} if this View is the same as {@code that} View.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_view_t* self, struct _cef_view_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:118</a>
     */
    boolean isSame(@Nullable CefView that);

    /**
     * Returns the delegate associated with this View, if any.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_view_delegate_t* (CEF_CALLBACK* get_delegate)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:124</a>
     */
    Optional<CefViewDelegate> getDelegate();

    /**
     * Returns the top-level Window hosting this View, if any.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_window_t* (CEF_CALLBACK* get_window)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:130</a>
     */
    Optional<CefWindow> getWindow();

    /**
     * Returns the ID (media source URN or URL) for this source.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* get_id)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__media__router_8h.html">cef_media_router.h:299</a>
     */
    int getId();

    /**
     * Sets the ID for this View. ID should be unique within the subtree that you intend to search for it. 0 is the default ID for views.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_id)(struct _cef_view_t* self, int id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:142</a>
     */
    void setId(int id);

    /**
     * Returns the group id for the specified {@code command_id} or -1 if invalid.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* get_group_id)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:227</a>
     */
    int getGroupId();

    /**
     * Sets the group id for the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_group_id)(struct _cef_view_t* self, int group_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:239</a>
     */
    void setGroupId(int groupId);

    /**
     * Returns the View that contains this View, if any.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_view_t* (CEF_CALLBACK* get_parent_view)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:163</a>
     */
    Optional<CefView> getParentView();

    /**
     * Recursively descends the view tree starting at this View, and returns the first child that it encounters with the given ID. Returns {@code null} if no matching child view is found.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_view_t* (CEF_CALLBACK* get_view_for_id)(struct _cef_view_t* self, int id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:169</a>
     */
    Optional<CefView> getViewForId(int id);

    /**
     * Sets the bounds (size and position) of this View. {@code bounds} is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_bounds)(struct _cef_view_t* self, const cef_rect_t* bounds);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:177</a>
     */
    void setBounds(@Nonnull CefRect bounds);

    /**
     * Returns the bounds (size and position) of this View in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_bounds)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:184</a>
     */
    CefRect getBounds();

    /**
     * Returns the bounds (size and position) of this View in DIP screen coordinates.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_bounds_in_screen)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:191</a>
     */
    CefRect getBoundsInScreen();

    /**
     * Sets the size of this View without changing the position. {@code size} in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_size)(struct _cef_view_t* self, const cef_size_t* size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:198</a>
     */
    void setSize(@Nonnull CefSize size);

    /**
     * Returns the size of this View in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_size)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:205</a>
     */
    CefSize getSize();

    /**
     * Sets the position of this View without changing the size. {@code position} is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_position)(struct _cef_view_t* self, const cef_point_t* position);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:212</a>
     */
    void setPosition(@Nonnull CefPoint position);

    /**
     * Returns the position of this View. Position is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_point_t* (CEF_CALLBACK* get_position)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:219</a>
     */
    CefPoint getPosition();

    /**
     * Sets the insets for this View. {@code insets} is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_insets)(struct _cef_view_t* self, const cef_insets_t* insets);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:226</a>
     */
    void setInsets(@Nonnull CefInsets insets);

    /**
     * Returns the insets for this View in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_insets_t* (CEF_CALLBACK* get_insets)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:233</a>
     */
    CefInsets getInsets();

    /**
     * Returns the size this View would like to be if enough space is available. Size is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_preferred_size)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:240</a>
     */
    CefSize getPreferredSize();

    /**
     * Size this View to its preferred size. Size is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* size_to_preferred_size)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:248</a>
     */
    void sizeToPreferredSize();

    /**
     * Returns the minimum size for this View. Size is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_minimum_size)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:255</a>
     */
    CefSize getMinimumSize();

    /**
     * Returns the maximum size for this View. Size is in parent coordinates, or DIP screen coordinates if there is no parent.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>cef_size_t* (CEF_CALLBACK* get_maximum_size)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:262</a>
     */
    CefSize getMaximumSize();

    /**
     * Returns the height necessary to display this View with the provided width.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* get_height_for_width)(struct _cef_view_t* self, int width);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:269</a>
     */
    int getHeightForWidth(int width);

    /**
     * Indicate that this View and all parent Views require a re-layout. This ensures the next call to Layout() will propagate to this View even if the bounds of parent Views do not change.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* invalidate_layout)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:275</a>
     */
    void invalidateLayout();

    /**
     * Sets whether this View is visible. Windows are hidden by default and other views are visible by default. This View and any parent views must be set as visible for this View to be drawn in a Window. If this View is set as hidden then it and any child views will not be drawn and, if any of those views currently have focus, then focus will also be cleared. Painting is scheduled as needed. If this View is a Window then calling this method is equivalent to calling the Window Show() and Hide() methods.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_visible)(struct _cef_view_t* self, int visible);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:283</a>
     */
    void setVisible(boolean visible);

    /**
     * Returns whether this View is visible. A view may be visible but still not drawn in a Window if any parent views are hidden. If this View is a Window then a return value of {@code true} indicates that this Window is currently visible to the user on-screen. If this View is not a Window then call IsDrawn() to determine whether this View and all parent views are visible and will be drawn.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_visible)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:295</a>
     */
    boolean isVisible();

    /**
     * Returns whether this View is visible and drawn in a Window. A view is drawn if it and all parent views are visible. If this View is a Window then calling this method is equivalent to calling IsVisible(). Otherwise, to determine if the containing Window is visible to the user on-screen call IsVisible() on the Window.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_drawn)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:306</a>
     */
    boolean isDrawn();

    /**
     * Set whether this View is enabled. A disabled View does not receive keyboard or mouse inputs. If {@code enabled} differs from the current value the View will be repainted. Also, clears focus if the focused View is disabled.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_enabled)(struct _cef_view_t* self, int enabled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:316</a>
     */
    void setEnabled(boolean enabled);

    /**
     * Returns whether this View is enabled.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_enabled)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:325</a>
     */
    boolean isEnabled();

    /**
     * Sets whether this View is capable of taking focus. It will clear focus if the focused View is set to be non-focusable. This is {@code false} by default so that a View used as a container does not get the focus.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_focusable)(struct _cef_view_t* self, int focusable);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:331</a>
     */
    void setFocusable(boolean focusable);

    /**
     * Returns {@code true} if this View is focusable, enabled and drawn.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_focusable)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:339</a>
     */
    boolean isFocusable();

    /**
     * Return whether this View is focusable when the user requires full keyboard access, even though it may not be normally focusable.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* is_accessibility_focusable)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:345</a>
     */
    boolean isAccessibilityFocusable();

    /**
     * Returns {@code true} if this View has focus in the context of the containing Window. Check both this method and {@link net.kurobako.cef4j.gen.views.CefWindow#isActive()} to determine global keyboard focus.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* has_focus)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:352</a>
     */
    boolean hasFocus();

    /**
     * Request focus for this View in the context of the containing Window. If this View is focusable it will become the focused View. Any focus changes while a Window is not active may be applied after that Window next becomes active.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* request_focus)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:360</a>
     */
    void requestFocus();

    /**
     * Sets the background color for this View. The background color will be automatically reset when {@link net.kurobako.cef4j.gen.views.CefViewDelegate#onThemeChanged(CefView)} is called.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>void (CEF_CALLBACK* set_background_color)(struct _cef_view_t* self, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:369</a>
     */
    void setBackgroundColor(int color);

    /**
     * Returns the background color for this View. If the background color is unset then the current `GetThemeColor(CEF_ColorPrimaryBackground)` value will be returned. If this View belongs to an overlay (created with {@link net.kurobako.cef4j.gen.views.CefWindow#addOverlayView(CefView, CefDockingMode, boolean)}), and the background color is unset, then a value of transparent (0) will be returned.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>unsigned int (CEF_CALLBACK* get_background_color)(struct _cef_view_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:376</a>
     */
    int getBackgroundColor();

    /**
     * Returns the current theme color associated with {@code color_id}, or the placeholder color (red) if unset. See cef_color_ids.h for standard ID values. Standard colors can be overridden and custom colors can be added using {@link net.kurobako.cef4j.gen.views.CefWindow#setThemeColor(int, int)}.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>unsigned int (CEF_CALLBACK* get_theme_color)(struct _cef_view_t* self, int color_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:386</a>
     */
    int getThemeColor(int colorId);

    /**
     * Convert {@code point} from this View's coordinate system to DIP screen coordinates. This View must belong to a Window when calling this method. Returns {@code true} if the conversion is successful or {@code false} otherwise. Use {@link net.kurobako.cef4j.gen.views.CefDisplay#convertPointToPixels(CefPoint.Mutable)} after calling this method if further conversion to display-specific pixel coordinates is desired.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* convert_point_to_screen)(struct _cef_view_t* self, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:395</a>
     */
    boolean convertPointToScreen(@Nonnull CefPoint.Mutable point);

    /**
     * Convert {@code point} to this View's coordinate system from DIP screen coordinates. This View must belong to a Window when calling this method. Returns {@code true} if the conversion is successful or {@code false} otherwise. Use {@link net.kurobako.cef4j.gen.views.CefDisplay#convertPointFromPixels(CefPoint.Mutable)} before calling this method if conversion from display-specific pixel coordinates is necessary.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* convert_point_from_screen)(struct _cef_view_t* self, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:405</a>
     */
    boolean convertPointFromScreen(@Nonnull CefPoint.Mutable point);

    /**
     * Convert {@code point} from this View's coordinate system to that of the Window. This View must belong to a Window when calling this method. Returns {@code true} if the conversion is successful or {@code false} otherwise.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* convert_point_to_window)(struct _cef_view_t* self, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:415</a>
     */
    boolean convertPointToWindow(@Nonnull CefPoint.Mutable point);

    /**
     * Convert {@code point} to this View's coordinate system from that of the Window. This View must belong to a Window when calling this method. Returns {@code true} if the conversion is successful or {@code false} otherwise.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* convert_point_from_window)(struct _cef_view_t* self, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:423</a>
     */
    boolean convertPointFromWindow(@Nonnull CefPoint.Mutable point);

    /**
     * Convert {@code point} from this View's coordinate system to that of {@code view}. {@code view} needs to be in the same Window but not necessarily the same view hierarchy. Returns {@code true} if the conversion is successful or {@code false} otherwise.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* convert_point_to_view)(struct _cef_view_t* self, struct _cef_view_t* view, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:431</a>
     */
    boolean convertPointToView(@Nullable CefView view, @Nonnull CefPoint.Mutable point);

    /**
     * Convert {@code point} to this View's coordinate system from that {@code view}. {@code view} needs to be in the same Window but not necessarily the same view hierarchy. Returns {@code true} if the conversion is successful or {@code false} otherwise.
     * <p>Definition generated from views/cef_view_capi.h
     * <pre>int (CEF_CALLBACK* convert_point_from_view)(struct _cef_view_t* self, struct _cef_view_t* view, cef_point_t* point);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__view_8h.html">views/cef_view.h:440</a>
     */
    boolean convertPointFromView(@Nullable CefView view, @Nonnull CefPoint.Mutable point);
    final class NativePeer implements CefView, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefView has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefView.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefView 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public Optional<CefBrowserView> asBrowserView() {
          checkNotClosed();
          return Optional.ofNullable(asBrowserView0(nativePtr));
      }

        @Override
      public Optional<CefButton> asButton() {
          checkNotClosed();
          return Optional.ofNullable(asButton0(nativePtr));
      }

        @Override
      public Optional<CefPanel> asPanel() {
          checkNotClosed();
          return Optional.ofNullable(asPanel0(nativePtr));
      }

        @Override
      public Optional<CefScrollView> asScrollView() {
          checkNotClosed();
          return Optional.ofNullable(asScrollView0(nativePtr));
      }

        @Override
      public Optional<CefTextfield> asTextfield() {
          checkNotClosed();
          return Optional.ofNullable(asTextfield0(nativePtr));
      }

        @Override
      public Optional<String> getTypeString() {
          checkNotClosed();
          return Optional.ofNullable(getTypeString0(nativePtr));
      }

        @Override
      public Optional<String> cefToString(boolean includeChildren) {
          checkNotClosed();
          return Optional.ofNullable(cefToString0(nativePtr, includeChildren));
      }

        @Override
      public boolean isValid() {
          checkNotClosed();
          return isValid0(nativePtr);
      }

        @Override
      public boolean isAttached() {
          checkNotClosed();
          return isAttached0(nativePtr);
      }

        @Override
      public boolean isSame(@Nullable CefView that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefView");
          return isSame0(nativePtr, that);
      }

        @Override
      public Optional<CefViewDelegate> getDelegate() {
          checkNotClosed();
          return Optional.ofNullable(getDelegate0(nativePtr));
      }

        @Override
      public Optional<CefWindow> getWindow() {
          checkNotClosed();
          return Optional.ofNullable(getWindow0(nativePtr));
      }

        @Override
      public int getId() {
          checkNotClosed();
          return getId0(nativePtr);
      }

        @Override
      public void setId(int id) {
          checkNotClosed();
          setId0(nativePtr, id);
      }

        @Override
      public int getGroupId() {
          checkNotClosed();
          return getGroupId0(nativePtr);
      }

        @Override
      public void setGroupId(int groupId) {
          checkNotClosed();
          setGroupId0(nativePtr, groupId);
      }

        @Override
      public Optional<CefView> getParentView() {
          checkNotClosed();
          return Optional.ofNullable(getParentView0(nativePtr));
      }

        @Override
      public Optional<CefView> getViewForId(int id) {
          checkNotClosed();
          return Optional.ofNullable(getViewForId0(nativePtr, id));
      }

        @Override
      public void setBounds(@Nonnull CefRect bounds) {
          checkNotClosed();
          setBounds0(nativePtr, bounds);
      }

        @Override
      public CefRect getBounds() {
          checkNotClosed();
          return getBounds0(nativePtr);
      }

        @Override
      public CefRect getBoundsInScreen() {
          checkNotClosed();
          return getBoundsInScreen0(nativePtr);
      }

        @Override
      public void setSize(@Nonnull CefSize size) {
          checkNotClosed();
          setSize0(nativePtr, size);
      }

        @Override
      public CefSize getSize() {
          checkNotClosed();
          return getSize0(nativePtr);
      }

        @Override
      public void setPosition(@Nonnull CefPoint position) {
          checkNotClosed();
          setPosition0(nativePtr, position);
      }

        @Override
      public CefPoint getPosition() {
          checkNotClosed();
          return getPosition0(nativePtr);
      }

        @Override
      public void setInsets(@Nonnull CefInsets insets) {
          checkNotClosed();
          setInsets0(nativePtr, insets);
      }

        @Override
      public CefInsets getInsets() {
          checkNotClosed();
          return getInsets0(nativePtr);
      }

        @Override
      public CefSize getPreferredSize() {
          checkNotClosed();
          return getPreferredSize0(nativePtr);
      }

        @Override
      public void sizeToPreferredSize() {
          checkNotClosed();
          sizeToPreferredSize0(nativePtr);
      }

        @Override
      public CefSize getMinimumSize() {
          checkNotClosed();
          return getMinimumSize0(nativePtr);
      }

        @Override
      public CefSize getMaximumSize() {
          checkNotClosed();
          return getMaximumSize0(nativePtr);
      }

        @Override
      public int getHeightForWidth(int width) {
          checkNotClosed();
          return getHeightForWidth0(nativePtr, width);
      }

        @Override
      public void invalidateLayout() {
          checkNotClosed();
          invalidateLayout0(nativePtr);
      }

        @Override
      public void setVisible(boolean visible) {
          checkNotClosed();
          setVisible0(nativePtr, visible);
      }

        @Override
      public boolean isVisible() {
          checkNotClosed();
          return isVisible0(nativePtr);
      }

        @Override
      public boolean isDrawn() {
          checkNotClosed();
          return isDrawn0(nativePtr);
      }

        @Override
      public void setEnabled(boolean enabled) {
          checkNotClosed();
          setEnabled0(nativePtr, enabled);
      }

        @Override
      public boolean isEnabled() {
          checkNotClosed();
          return isEnabled0(nativePtr);
      }

        @Override
      public void setFocusable(boolean focusable) {
          checkNotClosed();
          setFocusable0(nativePtr, focusable);
      }

        @Override
      public boolean isFocusable() {
          checkNotClosed();
          return isFocusable0(nativePtr);
      }

        @Override
      public boolean isAccessibilityFocusable() {
          checkNotClosed();
          return isAccessibilityFocusable0(nativePtr);
      }

        @Override
      public boolean hasFocus() {
          checkNotClosed();
          return hasFocus0(nativePtr);
      }

        @Override
      public void requestFocus() {
          checkNotClosed();
          requestFocus0(nativePtr);
      }

        @Override
      public void setBackgroundColor(int color) {
          checkNotClosed();
          setBackgroundColor0(nativePtr, color);
      }

        @Override
      public int getBackgroundColor() {
          checkNotClosed();
          return getBackgroundColor0(nativePtr);
      }

        @Override
      public int getThemeColor(int colorId) {
          checkNotClosed();
          return getThemeColor0(nativePtr, colorId);
      }

        @Override
      public boolean convertPointToScreen(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return convertPointToScreen0(nativePtr, point);
      }

        @Override
      public boolean convertPointFromScreen(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return convertPointFromScreen0(nativePtr, point);
      }

        @Override
      public boolean convertPointToWindow(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return convertPointToWindow0(nativePtr, point);
      }

        @Override
      public boolean convertPointFromWindow(@Nonnull CefPoint.Mutable point) {
          checkNotClosed();
          return convertPointFromWindow0(nativePtr, point);
      }

        @Override
      public boolean convertPointToView(@Nullable CefView view, @Nonnull CefPoint.Mutable point) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return convertPointToView0(nativePtr, view, point);
      }

        @Override
      public boolean convertPointFromView(@Nullable CefView view, @Nonnull CefPoint.Mutable point) {
          checkNotClosed();
            CefLibraryObject.requireOpen(view, "CefView");
          return convertPointFromView0(nativePtr, view, point);
      }


        static native CefBrowserView asBrowserView0(long self);

        static native CefButton asButton0(long self);

        static native CefPanel asPanel0(long self);

        static native CefScrollView asScrollView0(long self);

        static native CefTextfield asTextfield0(long self);

        static native String getTypeString0(long self);

        static native String cefToString0(long self, boolean includeChildren);

        static native boolean isValid0(long self);

        static native boolean isAttached0(long self);

        static native boolean isSame0(long self, CefView that);

        static native CefViewDelegate getDelegate0(long self);

        static native CefWindow getWindow0(long self);

        static native int getId0(long self);

        static native void setId0(long self, int id);

        static native int getGroupId0(long self);

        static native void setGroupId0(long self, int groupId);

        static native CefView getParentView0(long self);

        static native CefView getViewForId0(long self, int id);

        static native void setBounds0(long self, CefRect bounds);

        static native CefRect getBounds0(long self);

        static native CefRect getBoundsInScreen0(long self);

        static native void setSize0(long self, CefSize size);

        static native CefSize getSize0(long self);

        static native void setPosition0(long self, CefPoint position);

        static native CefPoint getPosition0(long self);

        static native void setInsets0(long self, CefInsets insets);

        static native CefInsets getInsets0(long self);

        static native CefSize getPreferredSize0(long self);

        static native void sizeToPreferredSize0(long self);

        static native CefSize getMinimumSize0(long self);

        static native CefSize getMaximumSize0(long self);

        static native int getHeightForWidth0(long self, int width);

        static native void invalidateLayout0(long self);

        static native void setVisible0(long self, boolean visible);

        static native boolean isVisible0(long self);

        static native boolean isDrawn0(long self);

        static native void setEnabled0(long self, boolean enabled);

        static native boolean isEnabled0(long self);

        static native void setFocusable0(long self, boolean focusable);

        static native boolean isFocusable0(long self);

        static native boolean isAccessibilityFocusable0(long self);

        static native boolean hasFocus0(long self);

        static native void requestFocus0(long self);

        static native void setBackgroundColor0(long self, int color);

        static native int getBackgroundColor0(long self);

        static native int getThemeColor0(long self, int colorId);

        static native boolean convertPointToScreen0(long self, CefPoint.Mutable point);

        static native boolean convertPointFromScreen0(long self, CefPoint.Mutable point);

        static native boolean convertPointToWindow0(long self, CefPoint.Mutable point);

        static native boolean convertPointFromWindow0(long self, CefPoint.Mutable point);

        static native boolean convertPointToView0(long self, CefView view, CefPoint.Mutable point);

        static native boolean convertPointFromView0(long self, CefView view, CefPoint.Mutable point);


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
            return "CefView{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
