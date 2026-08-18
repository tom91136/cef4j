// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Supports creation and modification of menus. See cef_menu_id_t for the command ids that have default implementations. All user-defined command ids should be between MENU_ID_USER_FIRST and MENU_ID_USER_LAST. The methods of this class can only be accessed on the browser process the UI thread.
 * <p>Definition generated from cef_menu_model_capi.h
 * <pre>typedef struct _cef_menu_model_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_menu_model_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefMenuModel extends CefLibraryObject {

    /**
     * Returns {@code true} if this menu is a submenu.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_sub_menu)(struct _cef_menu_model_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:62</a>
     */
    boolean isSubMenu();

    /**
     * Clears the menu. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* clear)(struct _cef_menu_model_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:68</a>
     */
    boolean clear();

    /**
     * Returns the number of items in this menu.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>size_t (CEF_CALLBACK* get_count)(struct _cef_menu_model_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:74</a>
     */
    long getCount();

    /**
     * Add a separator to the menu. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* add_separator)(struct _cef_menu_model_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:80</a>
     */
    boolean addSeparator();

    /**
     * Add an item to the menu. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* add_item)(struct _cef_menu_model_t* self, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:86</a>
     */
    boolean addItem(int commandId, @Nullable String label);

    /**
     * Add a check item to the menu. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* add_check_item)(struct _cef_menu_model_t* self, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:92</a>
     */
    boolean addCheckItem(int commandId, @Nullable String label);

    /**
     * Add a radio item to the menu. Only a single item with the specified {@code group_id} can be checked at a time. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* add_radio_item)(struct _cef_menu_model_t* self, int command_id, const cef_string_t* label, int group_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:97</a>
     */
    boolean addRadioItem(int commandId, @Nullable String label, int groupId);

    /**
     * Add a sub-menu to the menu. The new sub-menu is returned.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>cef_menu_model_t* (CEF_CALLBACK* add_sub_menu)(struct _cef_menu_model_t* self, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:106</a>
     */
    Optional<CefMenuModel> addSubMenu(int commandId, @Nullable String label);

    /**
     * Insert a separator in the menu at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* insert_separator_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:113</a>
     */
    boolean insertSeparatorAt(long index);

    /**
     * Insert an item in the menu at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* insert_item_at)(struct _cef_menu_model_t* self, size_t index, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:120</a>
     */
    boolean insertItemAt(long index, int commandId, @Nullable String label);

    /**
     * Insert a check item in the menu at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* insert_check_item_at)(struct _cef_menu_model_t* self, size_t index, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:129</a>
     */
    boolean insertCheckItemAt(long index, int commandId, @Nullable String label);

    /**
     * Insert a radio item in the menu at the specified {@code index}. Only a single item with the specified {@code group_id} can be checked at a time. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* insert_radio_item_at)(struct _cef_menu_model_t* self, size_t index, int command_id, const cef_string_t* label, int group_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:138</a>
     */
    boolean insertRadioItemAt(long index, int commandId, @Nullable String label, int groupId);

    /**
     * Insert a sub-menu in the menu at the specified {@code index}. The new sub-menu is returned.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>cef_menu_model_t* (CEF_CALLBACK* insert_sub_menu_at)(struct _cef_menu_model_t* self, size_t index, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:149</a>
     */
    Optional<CefMenuModel> insertSubMenuAt(long index, int commandId, @Nullable String label);

    /**
     * Removes the item with the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* remove)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:158</a>
     */
    boolean remove(int commandId);

    /**
     * Removes the item at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* remove_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:164</a>
     */
    boolean removeAt(long index);

    /**
     * Returns the index associated with the specified {@code command_id} or -1 if not found due to the command id not existing in the menu.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_index_of)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:170</a>
     */
    int getIndexOf(int commandId);

    /**
     * Returns the command id at the specified {@code index} or -1 if not found due to invalid range or the index being a separator.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_command_id_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:177</a>
     */
    int getCommandIdAt(long index);

    /**
     * Sets the command id at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_command_id_at)(struct _cef_menu_model_t* self, size_t index, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:184</a>
     */
    boolean setCommandIdAt(long index, int commandId);

    /**
     * Returns the label for the specified {@code command_id} or empty if not found.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_label)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:190</a>
     */
    Optional<String> getLabel(int commandId);

    /**
     * Sets the label for the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_label)(struct _cef_menu_model_t* self, int command_id, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:203</a>
     */
    boolean setLabel(int commandId, @Nullable String label);

    /**
     * Set the label at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_label_at)(struct _cef_menu_model_t* self, size_t index, const cef_string_t* label);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:209</a>
     */
    boolean setLabelAt(long index, @Nullable String label);

    /**
     * Returns the item type for the specified {@code command_id}.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>cef_menu_item_type_t (CEF_CALLBACK* get_type)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:215</a>
     */
    CefMenuItemType getType(int commandId);

    /**
     * Returns the group id for the specified {@code command_id} or -1 if invalid.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_group_id)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:227</a>
     */
    int getGroupId(int commandId);

    /**
     * Returns the group id at the specified {@code index} or -1 if invalid.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_group_id_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:233</a>
     */
    int getGroupIdAt(long index);

    /**
     * Sets the group id for the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_group_id)(struct _cef_menu_model_t* self, int command_id, int group_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:239</a>
     */
    boolean setGroupId(int commandId, int groupId);

    /**
     * Sets the group id at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_group_id_at)(struct _cef_menu_model_t* self, size_t index, int group_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:245</a>
     */
    boolean setGroupIdAt(long index, int groupId);

    /**
     * Returns the submenu for the specified {@code command_id} or empty if invalid.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>cef_menu_model_t* (CEF_CALLBACK* get_sub_menu)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:251</a>
     */
    Optional<CefMenuModel> getSubMenu(int commandId);

    /**
     * Returns the submenu at the specified {@code index} or empty if invalid.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>cef_menu_model_t* (CEF_CALLBACK* get_sub_menu_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:257</a>
     */
    Optional<CefMenuModel> getSubMenuAt(long index);

    /**
     * Returns {@code true} if the specified {@code command_id} is visible.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_visible)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:263</a>
     */
    boolean isVisible(int commandId);

    /**
     * Returns {@code true} if the specified {@code index} is visible.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_visible_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:269</a>
     */
    boolean isVisibleAt(long index);

    /**
     * Change the visibility of the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_visible)(struct _cef_menu_model_t* self, int command_id, int visible);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:275</a>
     */
    boolean setVisible(int commandId, boolean visible);

    /**
     * Change the visibility at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_visible_at)(struct _cef_menu_model_t* self, size_t index, int visible);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:282</a>
     */
    boolean setVisibleAt(long index, boolean visible);

    /**
     * Returns {@code true} if the specified {@code command_id} is enabled.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_enabled)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:288</a>
     */
    boolean isEnabled(int commandId);

    /**
     * Returns {@code true} if the specified {@code index} is enabled.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_enabled_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:294</a>
     */
    boolean isEnabledAt(long index);

    /**
     * Change the enabled status of the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_enabled)(struct _cef_menu_model_t* self, int command_id, int enabled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:300</a>
     */
    boolean setEnabled(int commandId, boolean enabled);

    /**
     * Change the enabled status at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_enabled_at)(struct _cef_menu_model_t* self, size_t index, int enabled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:307</a>
     */
    boolean setEnabledAt(long index, boolean enabled);

    /**
     * Returns {@code true} if the specified {@code command_id} is checked. Only applies to check and radio items.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_checked)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:314</a>
     */
    boolean isChecked(int commandId);

    /**
     * Returns {@code true} if the specified {@code index} is checked. Only applies to check and radio items.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* is_checked_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:321</a>
     */
    boolean isCheckedAt(long index);

    /**
     * Check the specified {@code command_id}. Only applies to check and radio items. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_checked)(struct _cef_menu_model_t* self, int command_id, int checked);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:328</a>
     */
    boolean setChecked(int commandId, boolean checked);

    /**
     * Check the specified {@code index}. Only applies to check and radio items. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_checked_at)(struct _cef_menu_model_t* self, size_t index, int checked);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:335</a>
     */
    boolean setCheckedAt(long index, boolean checked);

    /**
     * Returns {@code true} if the specified {@code command_id} has a keyboard accelerator assigned.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* has_accelerator)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:342</a>
     */
    boolean hasAccelerator(int commandId);

    /**
     * Returns {@code true} if the specified {@code index} has a keyboard accelerator assigned.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* has_accelerator_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:349</a>
     */
    boolean hasAcceleratorAt(long index);

    /**
     * Set the keyboard accelerator for the specified {@code command_id}. {@code key_code} can be any virtual key or character value. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_accelerator)(struct _cef_menu_model_t* self, int command_id, int key_code, int shift_pressed, int ctrl_pressed, int alt_pressed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:355</a>
     */
    boolean setAccelerator(int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

    /**
     * Set the keyboard accelerator at the specified {@code index}. {@code key_code} can be any virtual key or character value. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_accelerator_at)(struct _cef_menu_model_t* self, size_t index, int key_code, int shift_pressed, int ctrl_pressed, int alt_pressed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:366</a>
     */
    boolean setAcceleratorAt(long index, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

    /**
     * Remove the keyboard accelerator for the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* remove_accelerator)(struct _cef_menu_model_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:377</a>
     */
    boolean removeAccelerator(int commandId);

    /**
     * Remove the keyboard accelerator at the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* remove_accelerator_at)(struct _cef_menu_model_t* self, size_t index);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:384</a>
     */
    boolean removeAcceleratorAt(long index);

    /**
     * Retrieves the keyboard accelerator for the specified {@code command_id}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_accelerator)(struct _cef_menu_model_t* self, int command_id, int* key_code, int* shift_pressed, int* ctrl_pressed, int* alt_pressed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:391</a>
     */
    boolean getAccelerator(int commandId, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

    /**
     * Retrieves the keyboard accelerator for the specified {@code index}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_accelerator_at)(struct _cef_menu_model_t* self, size_t index, int* key_code, int* shift_pressed, int* ctrl_pressed, int* alt_pressed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:402</a>
     */
    boolean getAcceleratorAt(long index, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

    /**
     * Set the explicit color for {@code command_id} and {@code color_type} to {@code color}. Specify a {@code color} value of 0 to remove the explicit color. If no explicit color or default color is set for {@code color_type} then the system color will be used. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_color)(struct _cef_menu_model_t* self, int command_id, cef_menu_color_type_t color_type, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:413</a>
     */
    boolean setColor(int commandId, @Nonnull CefMenuColorType colorType, int color);

    /**
     * Set the explicit color for {@code command_id} and {@code index} to {@code color}. Specify a {@code color} value of 0 to remove the explicit color. Specify an {@code index} value of -1 to set the default color for items that do not have an explicit color set. If no explicit color or default color is set for {@code color_type} then the system color will be used. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_color_at)(struct _cef_menu_model_t* self, int index, cef_menu_color_type_t color_type, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:424</a>
     */
    boolean setColorAt(int index, @Nonnull CefMenuColorType colorType, int color);

    /**
     * Returns in {@code color} the color that was explicitly set for {@code command_id} and {@code color_type}. If a color was not set then 0 will be returned in {@code color}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_color)(struct _cef_menu_model_t* self, int command_id, cef_menu_color_type_t color_type, cef_color_t* color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:436</a>
     */
    boolean getColor(int commandId, @Nonnull CefMenuColorType colorType, int[] color);

    /**
     * Returns in {@code color} the color that was explicitly set for {@code command_id} and {@code color_type}. Specify an {@code index} value of -1 to return the default color in {@code color}. If a color was not set then 0 will be returned in {@code color}. Returns {@code true} on success.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* get_color_at)(struct _cef_menu_model_t* self, int index, cef_menu_color_type_t color_type, cef_color_t* color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:446</a>
     */
    boolean getColorAt(int index, @Nonnull CefMenuColorType colorType, int[] color);

    /**
     * Sets the font list for the specified {@code command_id}. If {@code font_list} is empty the system font will be used. Returns {@code true} on success. The format is "&lt;FONT_FAMILY_LIST&gt;,[STYLES] &lt;SIZE&gt;", where:
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
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_font_list)(struct _cef_menu_model_t* self, int command_id, const cef_string_t* font_list);</pre>
     *
     * @param fontList may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:457</a>
     */
    boolean setFontList(int commandId, @Nullable String fontList);

    /**
     * Sets the font list for the specified {@code index}. Specify an {@code index} value of -1 to set the default font. If {@code font_list} is empty the system font will be used. Returns {@code true} on success. The format is "&lt;FONT_FAMILY_LIST&gt;,[STYLES] &lt;SIZE&gt;", where:
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
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>int (CEF_CALLBACK* set_font_list_at)(struct _cef_menu_model_t* self, int index, const cef_string_t* font_list);</pre>
     *
     * @param fontList may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:473</a>
     */
    boolean setFontListAt(int index, @Nullable String fontList);
    /**
     * Create a new MenuModel with the specified {@code delegate}.
     * <p>Definition generated from cef_menu_model_capi.h
     * <pre>CEF_EXPORT cef_menu_model_t* cef_menu_model_create(struct _cef_menu_model_delegate_t* delegate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__menu__model_8h.html">cef_menu_model.h:55</a>
     */
    static Optional<CefMenuModel> create(@Nullable CefMenuModelDelegate delegate) {
      return Optional.ofNullable(NativePeer.create0(delegate));
  }

    final class NativePeer implements CefMenuModel, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefMenuModel has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMenuModel.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMenuModel 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isSubMenu() {
          checkNotClosed();
          return isSubMenu0(nativePtr);
      }

        @Override
      public boolean clear() {
          checkNotClosed();
          return clear0(nativePtr);
      }

        @Override
      public long getCount() {
          checkNotClosed();
          return getCount0(nativePtr);
      }

        @Override
      public boolean addSeparator() {
          checkNotClosed();
          return addSeparator0(nativePtr);
      }

        @Override
      public boolean addItem(int commandId, @Nullable String label) {
          checkNotClosed();
          return addItem0(nativePtr, commandId, label);
      }

        @Override
      public boolean addCheckItem(int commandId, @Nullable String label) {
          checkNotClosed();
          return addCheckItem0(nativePtr, commandId, label);
      }

        @Override
      public boolean addRadioItem(int commandId, @Nullable String label, int groupId) {
          checkNotClosed();
          return addRadioItem0(nativePtr, commandId, label, groupId);
      }

        @Override
      public Optional<CefMenuModel> addSubMenu(int commandId, @Nullable String label) {
          checkNotClosed();
          return Optional.ofNullable(addSubMenu0(nativePtr, commandId, label));
      }

        @Override
      public boolean insertSeparatorAt(long index) {
          checkNotClosed();
          return insertSeparatorAt0(nativePtr, index);
      }

        @Override
      public boolean insertItemAt(long index, int commandId, @Nullable String label) {
          checkNotClosed();
          return insertItemAt0(nativePtr, index, commandId, label);
      }

        @Override
      public boolean insertCheckItemAt(long index, int commandId, @Nullable String label) {
          checkNotClosed();
          return insertCheckItemAt0(nativePtr, index, commandId, label);
      }

        @Override
      public boolean insertRadioItemAt(long index, int commandId, @Nullable String label, int groupId) {
          checkNotClosed();
          return insertRadioItemAt0(nativePtr, index, commandId, label, groupId);
      }

        @Override
      public Optional<CefMenuModel> insertSubMenuAt(long index, int commandId, @Nullable String label) {
          checkNotClosed();
          return Optional.ofNullable(insertSubMenuAt0(nativePtr, index, commandId, label));
      }

        @Override
      public boolean remove(int commandId) {
          checkNotClosed();
          return remove0(nativePtr, commandId);
      }

        @Override
      public boolean removeAt(long index) {
          checkNotClosed();
          return removeAt0(nativePtr, index);
      }

        @Override
      public int getIndexOf(int commandId) {
          checkNotClosed();
          return getIndexOf0(nativePtr, commandId);
      }

        @Override
      public int getCommandIdAt(long index) {
          checkNotClosed();
          return getCommandIdAt0(nativePtr, index);
      }

        @Override
      public boolean setCommandIdAt(long index, int commandId) {
          checkNotClosed();
          return setCommandIdAt0(nativePtr, index, commandId);
      }

        @Override
      public Optional<String> getLabel(int commandId) {
          checkNotClosed();
          return Optional.ofNullable(getLabel0(nativePtr, commandId));
      }

        @Override
      public boolean setLabel(int commandId, @Nullable String label) {
          checkNotClosed();
          return setLabel0(nativePtr, commandId, label);
      }

        @Override
      public boolean setLabelAt(long index, @Nullable String label) {
          checkNotClosed();
          return setLabelAt0(nativePtr, index, label);
      }

        @Override
      public CefMenuItemType getType(int commandId) {
          checkNotClosed();
          return getType0(nativePtr, commandId);
      }

        @Override
      public int getGroupId(int commandId) {
          checkNotClosed();
          return getGroupId0(nativePtr, commandId);
      }

        @Override
      public int getGroupIdAt(long index) {
          checkNotClosed();
          return getGroupIdAt0(nativePtr, index);
      }

        @Override
      public boolean setGroupId(int commandId, int groupId) {
          checkNotClosed();
          return setGroupId0(nativePtr, commandId, groupId);
      }

        @Override
      public boolean setGroupIdAt(long index, int groupId) {
          checkNotClosed();
          return setGroupIdAt0(nativePtr, index, groupId);
      }

        @Override
      public Optional<CefMenuModel> getSubMenu(int commandId) {
          checkNotClosed();
          return Optional.ofNullable(getSubMenu0(nativePtr, commandId));
      }

        @Override
      public Optional<CefMenuModel> getSubMenuAt(long index) {
          checkNotClosed();
          return Optional.ofNullable(getSubMenuAt0(nativePtr, index));
      }

        @Override
      public boolean isVisible(int commandId) {
          checkNotClosed();
          return isVisible0(nativePtr, commandId);
      }

        @Override
      public boolean isVisibleAt(long index) {
          checkNotClosed();
          return isVisibleAt0(nativePtr, index);
      }

        @Override
      public boolean setVisible(int commandId, boolean visible) {
          checkNotClosed();
          return setVisible0(nativePtr, commandId, visible);
      }

        @Override
      public boolean setVisibleAt(long index, boolean visible) {
          checkNotClosed();
          return setVisibleAt0(nativePtr, index, visible);
      }

        @Override
      public boolean isEnabled(int commandId) {
          checkNotClosed();
          return isEnabled0(nativePtr, commandId);
      }

        @Override
      public boolean isEnabledAt(long index) {
          checkNotClosed();
          return isEnabledAt0(nativePtr, index);
      }

        @Override
      public boolean setEnabled(int commandId, boolean enabled) {
          checkNotClosed();
          return setEnabled0(nativePtr, commandId, enabled);
      }

        @Override
      public boolean setEnabledAt(long index, boolean enabled) {
          checkNotClosed();
          return setEnabledAt0(nativePtr, index, enabled);
      }

        @Override
      public boolean isChecked(int commandId) {
          checkNotClosed();
          return isChecked0(nativePtr, commandId);
      }

        @Override
      public boolean isCheckedAt(long index) {
          checkNotClosed();
          return isCheckedAt0(nativePtr, index);
      }

        @Override
      public boolean setChecked(int commandId, boolean checked) {
          checkNotClosed();
          return setChecked0(nativePtr, commandId, checked);
      }

        @Override
      public boolean setCheckedAt(long index, boolean checked) {
          checkNotClosed();
          return setCheckedAt0(nativePtr, index, checked);
      }

        @Override
      public boolean hasAccelerator(int commandId) {
          checkNotClosed();
          return hasAccelerator0(nativePtr, commandId);
      }

        @Override
      public boolean hasAcceleratorAt(long index) {
          checkNotClosed();
          return hasAcceleratorAt0(nativePtr, index);
      }

        @Override
      public boolean setAccelerator(int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed) {
          checkNotClosed();
          return setAccelerator0(nativePtr, commandId, keyCode, shiftPressed, ctrlPressed, altPressed);
      }

        @Override
      public boolean setAcceleratorAt(long index, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed) {
          checkNotClosed();
          return setAcceleratorAt0(nativePtr, index, keyCode, shiftPressed, ctrlPressed, altPressed);
      }

        @Override
      public boolean removeAccelerator(int commandId) {
          checkNotClosed();
          return removeAccelerator0(nativePtr, commandId);
      }

        @Override
      public boolean removeAcceleratorAt(long index) {
          checkNotClosed();
          return removeAcceleratorAt0(nativePtr, index);
      }

        @Override
      public boolean getAccelerator(int commandId, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed) {
          checkNotClosed();
          return getAccelerator0(nativePtr, commandId, keyCode, shiftPressed, ctrlPressed, altPressed);
      }

        @Override
      public boolean getAcceleratorAt(long index, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed) {
          checkNotClosed();
          return getAcceleratorAt0(nativePtr, index, keyCode, shiftPressed, ctrlPressed, altPressed);
      }

        @Override
      public boolean setColor(int commandId, @Nonnull CefMenuColorType colorType, int color) {
          checkNotClosed();
          return setColor0(nativePtr, commandId, colorType, color);
      }

        @Override
      public boolean setColorAt(int index, @Nonnull CefMenuColorType colorType, int color) {
          checkNotClosed();
          return setColorAt0(nativePtr, index, colorType, color);
      }

        @Override
      public boolean getColor(int commandId, @Nonnull CefMenuColorType colorType, int[] color) {
          checkNotClosed();
          return getColor0(nativePtr, commandId, colorType, color);
      }

        @Override
      public boolean getColorAt(int index, @Nonnull CefMenuColorType colorType, int[] color) {
          checkNotClosed();
          return getColorAt0(nativePtr, index, colorType, color);
      }

        @Override
      public boolean setFontList(int commandId, @Nullable String fontList) {
          checkNotClosed();
          return setFontList0(nativePtr, commandId, fontList);
      }

        @Override
      public boolean setFontListAt(int index, @Nullable String fontList) {
          checkNotClosed();
          return setFontListAt0(nativePtr, index, fontList);
      }


        static native boolean isSubMenu0(long self);

        static native boolean clear0(long self);

        static native long getCount0(long self);

        static native boolean addSeparator0(long self);

        static native boolean addItem0(long self, int commandId, @Nullable String label);

        static native boolean addCheckItem0(long self, int commandId, @Nullable String label);

        static native boolean addRadioItem0(long self, int commandId, @Nullable String label, int groupId);

        static native CefMenuModel addSubMenu0(long self, int commandId, @Nullable String label);

        static native boolean insertSeparatorAt0(long self, long index);

        static native boolean insertItemAt0(long self, long index, int commandId, @Nullable String label);

        static native boolean insertCheckItemAt0(long self, long index, int commandId, @Nullable String label);

        static native boolean insertRadioItemAt0(long self, long index, int commandId, @Nullable String label, int groupId);

        static native CefMenuModel insertSubMenuAt0(long self, long index, int commandId, @Nullable String label);

        static native boolean remove0(long self, int commandId);

        static native boolean removeAt0(long self, long index);

        static native int getIndexOf0(long self, int commandId);

        static native int getCommandIdAt0(long self, long index);

        static native boolean setCommandIdAt0(long self, long index, int commandId);

        static native String getLabel0(long self, int commandId);

        static native boolean setLabel0(long self, int commandId, @Nullable String label);

        static native boolean setLabelAt0(long self, long index, @Nullable String label);

        static native CefMenuItemType getType0(long self, int commandId);

        static native int getGroupId0(long self, int commandId);

        static native int getGroupIdAt0(long self, long index);

        static native boolean setGroupId0(long self, int commandId, int groupId);

        static native boolean setGroupIdAt0(long self, long index, int groupId);

        static native CefMenuModel getSubMenu0(long self, int commandId);

        static native CefMenuModel getSubMenuAt0(long self, long index);

        static native boolean isVisible0(long self, int commandId);

        static native boolean isVisibleAt0(long self, long index);

        static native boolean setVisible0(long self, int commandId, boolean visible);

        static native boolean setVisibleAt0(long self, long index, boolean visible);

        static native boolean isEnabled0(long self, int commandId);

        static native boolean isEnabledAt0(long self, long index);

        static native boolean setEnabled0(long self, int commandId, boolean enabled);

        static native boolean setEnabledAt0(long self, long index, boolean enabled);

        static native boolean isChecked0(long self, int commandId);

        static native boolean isCheckedAt0(long self, long index);

        static native boolean setChecked0(long self, int commandId, boolean checked);

        static native boolean setCheckedAt0(long self, long index, boolean checked);

        static native boolean hasAccelerator0(long self, int commandId);

        static native boolean hasAcceleratorAt0(long self, long index);

        static native boolean setAccelerator0(long self, int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

        static native boolean setAcceleratorAt0(long self, long index, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

        static native boolean removeAccelerator0(long self, int commandId);

        static native boolean removeAcceleratorAt0(long self, long index);

        static native boolean getAccelerator0(long self, int commandId, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

        static native boolean getAcceleratorAt0(long self, long index, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

        static native boolean setColor0(long self, int commandId, @Nonnull CefMenuColorType colorType, int color);

        static native boolean setColorAt0(long self, int index, @Nonnull CefMenuColorType colorType, int color);

        static native boolean getColor0(long self, int commandId, @Nonnull CefMenuColorType colorType, int[] color);

        static native boolean getColorAt0(long self, int index, @Nonnull CefMenuColorType colorType, int[] color);

        static native boolean setFontList0(long self, int commandId, @Nullable String fontList);

        static native boolean setFontListAt0(long self, int index, @Nullable String fontList);

        static native CefMenuModel create0(@Nullable CefMenuModelDelegate delegate);

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
            return "CefMenuModel{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
