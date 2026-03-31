// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Supports creation and modification of menus. See cef_menu_id_t for the command ids that have default implementations.
 * All user-defined command ids should be between MENU_ID_USER_FIRST and MENU_ID_USER_LAST. The methods of this class
 * can only be accessed on the browser process the UI thread.
 */
public interface CefMenuModel {

    /** Returns true if this menu is a submenu. */
    boolean isSubMenu();

    /** Clears the menu. Returns true on success. */
    boolean clear();

    /** Returns the number of items in this menu. */
    long getCount();

    /** Add a separator to the menu. Returns true on success. */
    boolean addSeparator();

    /** Add an item to the menu. Returns true on success. */
    boolean addItem(int commandId, @Nonnull String label);

    /** Add a check item to the menu. Returns true on success. */
    boolean addCheckItem(int commandId, @Nonnull String label);

    /**
     * Add a radio item to the menu. Only a single item with the specified |group_id| can be checked at a time. Returns
     * true on success.
     */
    boolean addRadioItem(int commandId, @Nonnull String label, int groupId);

    /** Add a sub-menu to the menu. The new sub-menu is returned. */
    long addSubMenu(int commandId, @Nonnull String label);

    /** Insert a separator in the menu at the specified |index|. Returns true on success. */
    boolean insertSeparatorAt(long index);

    /** Insert an item in the menu at the specified |index|. Returns true on success. */
    boolean insertItemAt(long index, int commandId, @Nonnull String label);

    /** Insert a check item in the menu at the specified |index|. Returns true on success. */
    boolean insertCheckItemAt(long index, int commandId, @Nonnull String label);

    /**
     * Insert a radio item in the menu at the specified |index|. Only a single item with the specified |group_id| can be
     * checked at a time. Returns true on success.
     */
    boolean insertRadioItemAt(long index, int commandId, @Nonnull String label, int groupId);

    /** Insert a sub-menu in the menu at the specified |index|. The new sub-menu is returned. */
    long insertSubMenuAt(long index, int commandId, @Nonnull String label);

    /** Removes the item with the specified |command_id|. Returns true on success. */
    boolean remove(int commandId);

    /** Removes the item at the specified |index|. Returns true on success. */
    boolean removeAt(long index);

    /**
     * Returns the index associated with the specified |command_id| or -1 if not found due to the command id not
     * existing in the menu.
     */
    int getIndexOf(int commandId);

    /**
     * Returns the command id at the specified |index| or -1 if not found due to invalid range or the index being a
     * separator.
     */
    int getCommandIdAt(long index);

    /** Sets the command id at the specified |index|. Returns true on success. */
    boolean setCommandIdAt(long index, int commandId);

    /** Returns the label for the specified |command_id| or empty if not found. */
    Optional<String> getLabel(int commandId);

    /** Sets the label for the specified |command_id|. Returns true on success. */
    boolean setLabel(int commandId, @Nonnull String label);

    /** Set the label at the specified |index|. Returns true on success. */
    boolean setLabelAt(long index, @Nonnull String label);

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefMenuItemType getType(int commandId);

    /** Returns the group id for the specified |command_id| or -1 if invalid. */
    int getGroupId(int commandId);

    /** Returns the group id at the specified |index| or -1 if invalid. */
    int getGroupIdAt(long index);

    /** Sets the group id for the specified |command_id|. Returns true on success. */
    boolean setGroupId(int commandId, int groupId);

    /** Sets the group id at the specified |index|. Returns true on success. */
    boolean setGroupIdAt(long index, int groupId);

    /** Returns the submenu for the specified |command_id| or empty if invalid. */
    long getSubMenu(int commandId);

    /** Returns the submenu at the specified |index| or empty if invalid. */
    long getSubMenuAt(long index);

    /** Returns true if the specified |command_id| is visible. */
    boolean isVisible(int commandId);

    /** Returns true if the specified |index| is visible. */
    boolean isVisibleAt(long index);

    /** Change the visibility of the specified |command_id|. Returns true on success. */
    boolean setVisible(int commandId, boolean visible);

    /** Change the visibility at the specified |index|. Returns true on success. */
    boolean setVisibleAt(long index, boolean visible);

    /** Returns true if the specified |command_id| is enabled. */
    boolean isEnabled(int commandId);

    /** Returns true if the specified |index| is enabled. */
    boolean isEnabledAt(long index);

    /** Change the enabled status of the specified |command_id|. Returns true on success. */
    boolean setEnabled(int commandId, boolean enabled);

    /** Change the enabled status at the specified |index|. Returns true on success. */
    boolean setEnabledAt(long index, boolean enabled);

    /** Returns true if the specified |command_id| is checked. Only applies to check and radio items. */
    boolean isChecked(int commandId);

    /** Returns true if the specified |index| is checked. Only applies to check and radio items. */
    boolean isCheckedAt(long index);

    /** Check the specified |command_id|. Only applies to check and radio items. Returns true on success. */
    boolean setChecked(int commandId, boolean checked);

    /** Check the specified |index|. Only applies to check and radio items. Returns true on success. */
    boolean setCheckedAt(long index, boolean checked);

    /** Returns true if the specified |command_id| has a keyboard accelerator assigned. */
    boolean hasAccelerator(int commandId);

    /** Returns true if the specified |index| has a keyboard accelerator assigned. */
    boolean hasAcceleratorAt(long index);

    /**
     * Set the keyboard accelerator for the specified |command_id|. |key_code| can be any virtual key or character
     * value. Returns true on success.
     */
    boolean setAccelerator(int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

    /**
     * Set the keyboard accelerator at the specified |index|. |key_code| can be any virtual key or character value.
     * Returns true on success.
     */
    boolean setAcceleratorAt(long index, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

    /** Remove the keyboard accelerator for the specified |command_id|. Returns true on success. */
    boolean removeAccelerator(int commandId);

    /** Remove the keyboard accelerator at the specified |index|. Returns true on success. */
    boolean removeAcceleratorAt(long index);

    /** Retrieves the keyboard accelerator for the specified |command_id|. Returns true on success. */
    boolean getAccelerator(int commandId, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

    /** Retrieves the keyboard accelerator for the specified |index|. Returns true on success. */
    boolean getAcceleratorAt(long index, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

    /**
     * Set the explicit color for |command_id| and |color_type| to |color|. Specify a |color| value of 0 to remove the
     * explicit color. If no explicit color or default color is set for |color_type| then the system color will be used.
     * Returns true on success.
     */
    boolean setColor(int commandId, @Nonnull CefMenuColorType colorType, int color);

    /**
     * Set the explicit color for |command_id| and |index| to |color|. Specify a |color| value of 0 to remove the
     * explicit color. Specify an |index| value of -1 to set the default color for items that do not have an explicit
     * color set. If no explicit color or default color is set for |color_type| then the system color will be used.
     * Returns true on success.
     */
    boolean setColorAt(int index, @Nonnull CefMenuColorType colorType, int color);

    /**
     * Returns in |color| the color that was explicitly set for |command_id| and |color_type|. If a color was not set
     * then 0 will be returned in |color|. Returns true on success.
     */
    boolean getColor(int commandId, @Nonnull CefMenuColorType colorType, long color);

    /**
     * Returns in |color| the color that was explicitly set for |command_id| and |color_type|. Specify an |index| value
     * of -1 to return the default color in |color|. If a color was not set then 0 will be returned in |color|. Returns
     * true on success.
     */
    boolean getColorAt(int index, @Nonnull CefMenuColorType colorType, long color);

    /**
     * Sets the font list for the specified |command_id|. If |font_list| is empty the system font will be used. Returns
     * true on success. The format is "<FONT_FAMILY_LIST>,[STYLES] <SIZE>", where: - FONT_FAMILY_LIST is a
     * comma-separated list of font family names, - STYLES is an optional space-separated list of style names
     * (case-sensitive "Bold" and "Italic" are supported), and - SIZE is an integer font size in pixels with the suffix
     * "px". Here are examples of valid font description strings: - "Arial, Helvetica, Bold Italic 14px" - "Arial, 14px"
     *
     * @param fontList may be null
     */
    boolean setFontList(int commandId, @Nullable String fontList);

    /**
     * Sets the font list for the specified |index|. Specify an |index| value of -1 to set the default font. If
     * |font_list| is empty the system font will be used. Returns true on success. The format is
     * "<FONT_FAMILY_LIST>,[STYLES] <SIZE>", where: - FONT_FAMILY_LIST is a comma-separated list of font family names, -
     * STYLES is an optional space-separated list of style names (case-sensitive "Bold" and "Italic" are supported), and
     * - SIZE is an integer font size in pixels with the suffix "px". Here are examples of valid font description
     * strings: - "Arial, Helvetica, Bold Italic 14px" - "Arial, 14px"
     *
     * @param fontList may be null
     */
    boolean setFontListAt(int index, @Nullable String fontList);

    static class NativePeer implements CefMenuModel {
        private volatile long nativePtr;

        @Override
        public boolean isSubMenu() {
            return N_IsSubMenu(nativePtr);
        }

        @Override
        public boolean clear() {
            return N_Clear(nativePtr);
        }

        @Override
        public long getCount() {
            return N_GetCount(nativePtr);
        }

        @Override
        public boolean addSeparator() {
            return N_AddSeparator(nativePtr);
        }

        @Override
        public boolean addItem(int commandId, String label) {
            return N_AddItem(nativePtr, commandId, label);
        }

        @Override
        public boolean addCheckItem(int commandId, String label) {
            return N_AddCheckItem(nativePtr, commandId, label);
        }

        @Override
        public boolean addRadioItem(int commandId, String label, int groupId) {
            return N_AddRadioItem(nativePtr, commandId, label, groupId);
        }

        @Override
        public long addSubMenu(int commandId, String label) {
            return N_AddSubMenu(nativePtr, commandId, label);
        }

        @Override
        public boolean insertSeparatorAt(long index) {
            return N_InsertSeparatorAt(nativePtr, index);
        }

        @Override
        public boolean insertItemAt(long index, int commandId, String label) {
            return N_InsertItemAt(nativePtr, index, commandId, label);
        }

        @Override
        public boolean insertCheckItemAt(long index, int commandId, String label) {
            return N_InsertCheckItemAt(nativePtr, index, commandId, label);
        }

        @Override
        public boolean insertRadioItemAt(long index, int commandId, String label, int groupId) {
            return N_InsertRadioItemAt(nativePtr, index, commandId, label, groupId);
        }

        @Override
        public long insertSubMenuAt(long index, int commandId, String label) {
            return N_InsertSubMenuAt(nativePtr, index, commandId, label);
        }

        @Override
        public boolean remove(int commandId) {
            return N_Remove(nativePtr, commandId);
        }

        @Override
        public boolean removeAt(long index) {
            return N_RemoveAt(nativePtr, index);
        }

        @Override
        public int getIndexOf(int commandId) {
            return N_GetIndexOf(nativePtr, commandId);
        }

        @Override
        public int getCommandIdAt(long index) {
            return N_GetCommandIdAt(nativePtr, index);
        }

        @Override
        public boolean setCommandIdAt(long index, int commandId) {
            return N_SetCommandIdAt(nativePtr, index, commandId);
        }

        @Override
        public Optional<String> getLabel(int commandId) {
            return Optional.ofNullable(N_GetLabel(nativePtr, commandId));
        }

        @Override
        public boolean setLabel(int commandId, String label) {
            return N_SetLabel(nativePtr, commandId, label);
        }

        @Override
        public boolean setLabelAt(long index, String label) {
            return N_SetLabelAt(nativePtr, index, label);
        }

        @Override
        public CefMenuItemType getType(int commandId) {
            return N_GetType(nativePtr, commandId);
        }

        @Override
        public int getGroupId(int commandId) {
            return N_GetGroupId(nativePtr, commandId);
        }

        @Override
        public int getGroupIdAt(long index) {
            return N_GetGroupIdAt(nativePtr, index);
        }

        @Override
        public boolean setGroupId(int commandId, int groupId) {
            return N_SetGroupId(nativePtr, commandId, groupId);
        }

        @Override
        public boolean setGroupIdAt(long index, int groupId) {
            return N_SetGroupIdAt(nativePtr, index, groupId);
        }

        @Override
        public long getSubMenu(int commandId) {
            return N_GetSubMenu(nativePtr, commandId);
        }

        @Override
        public long getSubMenuAt(long index) {
            return N_GetSubMenuAt(nativePtr, index);
        }

        @Override
        public boolean isVisible(int commandId) {
            return N_IsVisible(nativePtr, commandId);
        }

        @Override
        public boolean isVisibleAt(long index) {
            return N_IsVisibleAt(nativePtr, index);
        }

        @Override
        public boolean setVisible(int commandId, boolean visible) {
            return N_SetVisible(nativePtr, commandId, visible);
        }

        @Override
        public boolean setVisibleAt(long index, boolean visible) {
            return N_SetVisibleAt(nativePtr, index, visible);
        }

        @Override
        public boolean isEnabled(int commandId) {
            return N_IsEnabled(nativePtr, commandId);
        }

        @Override
        public boolean isEnabledAt(long index) {
            return N_IsEnabledAt(nativePtr, index);
        }

        @Override
        public boolean setEnabled(int commandId, boolean enabled) {
            return N_SetEnabled(nativePtr, commandId, enabled);
        }

        @Override
        public boolean setEnabledAt(long index, boolean enabled) {
            return N_SetEnabledAt(nativePtr, index, enabled);
        }

        @Override
        public boolean isChecked(int commandId) {
            return N_IsChecked(nativePtr, commandId);
        }

        @Override
        public boolean isCheckedAt(long index) {
            return N_IsCheckedAt(nativePtr, index);
        }

        @Override
        public boolean setChecked(int commandId, boolean checked) {
            return N_SetChecked(nativePtr, commandId, checked);
        }

        @Override
        public boolean setCheckedAt(long index, boolean checked) {
            return N_SetCheckedAt(nativePtr, index, checked);
        }

        @Override
        public boolean hasAccelerator(int commandId) {
            return N_HasAccelerator(nativePtr, commandId);
        }

        @Override
        public boolean hasAcceleratorAt(long index) {
            return N_HasAcceleratorAt(nativePtr, index);
        }

        @Override
        public boolean setAccelerator(
                int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed) {
            return N_SetAccelerator(nativePtr, commandId, keyCode, shiftPressed, ctrlPressed, altPressed);
        }

        @Override
        public boolean setAcceleratorAt(
                long index, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed) {
            return N_SetAcceleratorAt(nativePtr, index, keyCode, shiftPressed, ctrlPressed, altPressed);
        }

        @Override
        public boolean removeAccelerator(int commandId) {
            return N_RemoveAccelerator(nativePtr, commandId);
        }

        @Override
        public boolean removeAcceleratorAt(long index) {
            return N_RemoveAcceleratorAt(nativePtr, index);
        }

        @Override
        public boolean getAccelerator(
                int commandId, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed) {
            return N_GetAccelerator(nativePtr, commandId, keyCode, shiftPressed, ctrlPressed, altPressed);
        }

        @Override
        public boolean getAcceleratorAt(
                long index, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed) {
            return N_GetAcceleratorAt(nativePtr, index, keyCode, shiftPressed, ctrlPressed, altPressed);
        }

        @Override
        public boolean setColor(int commandId, CefMenuColorType colorType, int color) {
            return N_SetColor(nativePtr, commandId, colorType, color);
        }

        @Override
        public boolean setColorAt(int index, CefMenuColorType colorType, int color) {
            return N_SetColorAt(nativePtr, index, colorType, color);
        }

        @Override
        public boolean getColor(int commandId, CefMenuColorType colorType, long color) {
            return N_GetColor(nativePtr, commandId, colorType, color);
        }

        @Override
        public boolean getColorAt(int index, CefMenuColorType colorType, long color) {
            return N_GetColorAt(nativePtr, index, colorType, color);
        }

        @Override
        public boolean setFontList(int commandId, String fontList) {
            return N_SetFontList(nativePtr, commandId, fontList);
        }

        @Override
        public boolean setFontListAt(int index, String fontList) {
            return N_SetFontListAt(nativePtr, index, fontList);
        }

        private native boolean N_IsSubMenu(long self);

        private native boolean N_Clear(long self);

        private native long N_GetCount(long self);

        private native boolean N_AddSeparator(long self);

        private native boolean N_AddItem(long self, int commandId, String label);

        private native boolean N_AddCheckItem(long self, int commandId, String label);

        private native boolean N_AddRadioItem(long self, int commandId, String label, int groupId);

        private native long N_AddSubMenu(long self, int commandId, String label);

        private native boolean N_InsertSeparatorAt(long self, long index);

        private native boolean N_InsertItemAt(long self, long index, int commandId, String label);

        private native boolean N_InsertCheckItemAt(long self, long index, int commandId, String label);

        private native boolean N_InsertRadioItemAt(long self, long index, int commandId, String label, int groupId);

        private native long N_InsertSubMenuAt(long self, long index, int commandId, String label);

        private native boolean N_Remove(long self, int commandId);

        private native boolean N_RemoveAt(long self, long index);

        private native int N_GetIndexOf(long self, int commandId);

        private native int N_GetCommandIdAt(long self, long index);

        private native boolean N_SetCommandIdAt(long self, long index, int commandId);

        private native String N_GetLabel(long self, int commandId);

        private native boolean N_SetLabel(long self, int commandId, String label);

        private native boolean N_SetLabelAt(long self, long index, String label);

        private native CefMenuItemType N_GetType(long self, int commandId);

        private native int N_GetGroupId(long self, int commandId);

        private native int N_GetGroupIdAt(long self, long index);

        private native boolean N_SetGroupId(long self, int commandId, int groupId);

        private native boolean N_SetGroupIdAt(long self, long index, int groupId);

        private native long N_GetSubMenu(long self, int commandId);

        private native long N_GetSubMenuAt(long self, long index);

        private native boolean N_IsVisible(long self, int commandId);

        private native boolean N_IsVisibleAt(long self, long index);

        private native boolean N_SetVisible(long self, int commandId, boolean visible);

        private native boolean N_SetVisibleAt(long self, long index, boolean visible);

        private native boolean N_IsEnabled(long self, int commandId);

        private native boolean N_IsEnabledAt(long self, long index);

        private native boolean N_SetEnabled(long self, int commandId, boolean enabled);

        private native boolean N_SetEnabledAt(long self, long index, boolean enabled);

        private native boolean N_IsChecked(long self, int commandId);

        private native boolean N_IsCheckedAt(long self, long index);

        private native boolean N_SetChecked(long self, int commandId, boolean checked);

        private native boolean N_SetCheckedAt(long self, long index, boolean checked);

        private native boolean N_HasAccelerator(long self, int commandId);

        private native boolean N_HasAcceleratorAt(long self, long index);

        private native boolean N_SetAccelerator(
                long self, int commandId, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

        private native boolean N_SetAcceleratorAt(
                long self, long index, int keyCode, boolean shiftPressed, boolean ctrlPressed, boolean altPressed);

        private native boolean N_RemoveAccelerator(long self, int commandId);

        private native boolean N_RemoveAcceleratorAt(long self, long index);

        private native boolean N_GetAccelerator(
                long self, int commandId, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

        private native boolean N_GetAcceleratorAt(
                long self, long index, int[] keyCode, int[] shiftPressed, int[] ctrlPressed, int[] altPressed);

        private native boolean N_SetColor(long self, int commandId, CefMenuColorType colorType, int color);

        private native boolean N_SetColorAt(long self, int index, CefMenuColorType colorType, int color);

        private native boolean N_GetColor(long self, int commandId, CefMenuColorType colorType, long color);

        private native boolean N_GetColorAt(long self, int index, CefMenuColorType colorType, long color);

        private native boolean N_SetFontList(long self, int commandId, String fontList);

        private native boolean N_SetFontListAt(long self, int index, String fontList);

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
