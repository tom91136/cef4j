// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefLibraryObject;
import net.kurobako.cef4j.gen.CefRange;
import net.kurobako.cef4j.gen.CefTextFieldCommands;
import net.kurobako.cef4j.gen.CefTextStyle;

/**
 * A Textfield supports editing of text. This control is custom rendered with no platform-specific code. Methods must be
 * called on the browser process UI thread unless otherwise indicated.
 *
 * <p>Definition generated from views/cef_textfield_capi.h
 *
 * <pre>typedef struct _cef_textfield_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_textfield_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefTextfield extends CefLibraryObject {

    /**
     * Sets whether the text will be displayed as asterisks.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_password_input)(struct _cef_textfield_t* self, int password_input);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:59</a>
     */
    void setPasswordInput(boolean passwordInput);

    /**
     * Returns {@code true} if the text will be displayed as asterisks.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_password_input)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:65</a>
     */
    boolean isPasswordInput();

    /**
     * Sets whether the text will read-only.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_read_only)(struct _cef_textfield_t* self, int read_only);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:71</a>
     */
    void setReadOnly(boolean readOnly);

    /**
     * Returns {@code true} if the text is read-only.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:77</a>
     */
    boolean isReadOnly();

    /**
     * Returns the currently displayed text.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_text)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:83</a>
     */
    Optional<String> getText();

    /**
     * Sets the contents to {@code text}. The cursor will be moved to end of the text if the current position is outside
     * of the text range.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_text)(struct _cef_textfield_t* self, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:89</a>
     */
    void setText(@Nullable String text);

    /**
     * Appends {@code text} to the previously-existing text.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* append_text)(struct _cef_textfield_t* self, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:96</a>
     */
    void appendText(@Nullable String text);

    /**
     * Inserts {@code text} at the current cursor position replacing any selected text.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* insert_or_replace_text)(struct _cef_textfield_t* self, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:102</a>
     */
    void insertOrReplaceText(@Nullable String text);

    /**
     * Returns {@code true} if there is any selected text.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_selection)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:108</a>
     */
    boolean hasSelection();

    /**
     * Returns the currently selected text.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_selected_text)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:114</a>
     */
    Optional<String> getSelectedText();

    /**
     * Selects all text. If {@code reversed} is {@code true} the range will end at the logical beginning of the text;
     * this generally shows the leading portion of text that overflows its display area.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* select_all)(struct _cef_textfield_t* self, int reversed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:120</a>
     */
    void selectAll(boolean reversed);

    /**
     * Clears the text selection and sets the caret to the end.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* clear_selection)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:128</a>
     */
    void clearSelection();

    /**
     * Returns the selected logical text range.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>cef_range_t* (CEF_CALLBACK* get_selected_range)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:134</a>
     */
    CefRange getSelectedRange();

    /**
     * Selects the specified logical text range.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* select_range)(struct _cef_textfield_t* self, const cef_range_t* range);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:140</a>
     */
    void selectRange(@Nonnull CefRange range);

    /**
     * Returns the current cursor position.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_cursor_position)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:146</a>
     */
    long getCursorPosition();

    /**
     * Sets the text color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_text_color)(struct _cef_textfield_t* self, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:152</a>
     */
    void setTextColor(int color);

    /**
     * Returns the text color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_text_color)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:158</a>
     */
    int getTextColor();

    /**
     * Sets the selection text color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_selection_text_color)(struct _cef_textfield_t* self, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:164</a>
     */
    void setSelectionTextColor(int color);

    /**
     * Returns the selection text color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_selection_text_color)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:170</a>
     */
    int getSelectionTextColor();

    /**
     * Sets the selection background color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_selection_background_color)(struct _cef_textfield_t* self, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:176</a>
     */
    void setSelectionBackgroundColor(int color);

    /**
     * Returns the selection background color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_selection_background_color)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:182</a>
     */
    int getSelectionBackgroundColor();

    /**
     * Sets the font list. The format is "&lt;FONT_FAMILY_LIST&gt;,[STYLES] &lt;SIZE&gt;", where:
     *
     * <ul>
     *   <li>FONT_FAMILY_LIST is a comma-separated list of font family names,
     *   <li>STYLES is an optional space-separated list of style names
     * </ul>
     *
     * (case-sensitive "Bold" and "Italic" are supported), and
     *
     * <ul>
     *   <li>SIZE is an integer font size in pixels with the suffix "px".
     * </ul>
     *
     * <p>Here are examples of valid font description strings:
     *
     * <ul>
     *   <li>"Arial, Helvetica, Bold Italic 14px"
     *   <li>"Arial, 14px"
     * </ul>
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_font_list)(struct _cef_textfield_t* self, const cef_string_t* font_list);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:188</a>
     */
    void setFontList(@Nullable String fontList);

    /**
     * Applies {@code color} to the specified {@code range} without changing the default color. If {@code range} is
     * empty the color will be set on the complete text contents.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* apply_text_color)(struct _cef_textfield_t* self, cef_color_t color, const cef_range_t* range);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:203</a>
     */
    void applyTextColor(int color, @Nonnull CefRange range);

    /**
     * Applies {@code style} to the specified {@code range} without changing the default style. If {@code add} is
     * {@code true} the style will be added, otherwise the style will be removed. If {@code range} is empty the style
     * will be set on the complete text contents.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* apply_text_style)(struct _cef_textfield_t* self, cef_text_style_t style, int add, const cef_range_t* range);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:211</a>
     */
    void applyTextStyle(@Nonnull CefTextStyle style, boolean add, @Nonnull CefRange range);

    /**
     * Returns {@code true} if the action associated with the specified command id is enabled. See additional comments
     * on ExecuteCommand().
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_command_enabled)(struct _cef_textfield_t* self, cef_text_field_commands_t command_id);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:222</a>
     */
    boolean isCommandEnabled(@Nonnull CefTextFieldCommands commandId);

    /**
     * Performs the action associated with the specified command id.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* execute_command)(struct _cef_textfield_t* self, cef_text_field_commands_t command_id);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:229</a>
     */
    void executeCommand(@Nonnull CefTextFieldCommands commandId);

    /**
     * Clears Edit history.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* clear_edit_history)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:235</a>
     */
    void clearEditHistory();

    /**
     * Sets the placeholder text that will be displayed when the Textfield is empty.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_placeholder_text)(struct _cef_textfield_t* self, const cef_string_t* text);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:241</a>
     */
    void setPlaceholderText(@Nullable String text);

    /**
     * Returns the placeholder text that will be displayed when the Textfield is empty.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_placeholder_text)(struct _cef_textfield_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:248</a>
     */
    Optional<String> getPlaceholderText();

    /**
     * Sets the placeholder text color.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_placeholder_text_color)(struct _cef_textfield_t* self, cef_color_t color);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:255</a>
     */
    void setPlaceholderTextColor(int color);

    /**
     * Set the accessible name that will be exposed to assistive technology (AT).
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_accessible_name)(struct _cef_textfield_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:261</a>
     */
    void setAccessibleName(@Nullable String name);
    /**
     * Create a new Textfield.
     *
     * <p>Definition generated from views/cef_textfield_capi.h
     *
     * <pre>CEF_EXPORT cef_textfield_t* cef_textfield_create(struct _cef_textfield_delegate_t* delegate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__textfield_8h.html">views/cef_textfield.h:52</a>
     */
    static Optional<CefTextfield> create(@Nullable CefTextfieldDelegate delegate) {
        return Optional.ofNullable(NativePeer.create0(delegate));
    }

    final class NativePeer implements CefTextfield, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefTextfield has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefTextfield.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefTextfield 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void setPasswordInput(boolean passwordInput) {
            checkNotClosed();
            setPasswordInput0(nativePtr, passwordInput);
        }

        @Override
        public boolean isPasswordInput() {
            checkNotClosed();
            return isPasswordInput0(nativePtr);
        }

        @Override
        public void setReadOnly(boolean readOnly) {
            checkNotClosed();
            setReadOnly0(nativePtr, readOnly);
        }

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return isReadOnly0(nativePtr);
        }

        @Override
        public Optional<String> getText() {
            checkNotClosed();
            return Optional.ofNullable(getText0(nativePtr));
        }

        @Override
        public void setText(@Nullable String text) {
            checkNotClosed();
            setText0(nativePtr, text);
        }

        @Override
        public void appendText(@Nullable String text) {
            checkNotClosed();
            appendText0(nativePtr, text);
        }

        @Override
        public void insertOrReplaceText(@Nullable String text) {
            checkNotClosed();
            insertOrReplaceText0(nativePtr, text);
        }

        @Override
        public boolean hasSelection() {
            checkNotClosed();
            return hasSelection0(nativePtr);
        }

        @Override
        public Optional<String> getSelectedText() {
            checkNotClosed();
            return Optional.ofNullable(getSelectedText0(nativePtr));
        }

        @Override
        public void selectAll(boolean reversed) {
            checkNotClosed();
            selectAll0(nativePtr, reversed);
        }

        @Override
        public void clearSelection() {
            checkNotClosed();
            clearSelection0(nativePtr);
        }

        @Override
        public CefRange getSelectedRange() {
            checkNotClosed();
            return getSelectedRange0(nativePtr);
        }

        @Override
        public void selectRange(@Nonnull CefRange range) {
            checkNotClosed();
            selectRange0(nativePtr, range);
        }

        @Override
        public long getCursorPosition() {
            checkNotClosed();
            return getCursorPosition0(nativePtr);
        }

        @Override
        public void setTextColor(int color) {
            checkNotClosed();
            setTextColor0(nativePtr, color);
        }

        @Override
        public int getTextColor() {
            checkNotClosed();
            return getTextColor0(nativePtr);
        }

        @Override
        public void setSelectionTextColor(int color) {
            checkNotClosed();
            setSelectionTextColor0(nativePtr, color);
        }

        @Override
        public int getSelectionTextColor() {
            checkNotClosed();
            return getSelectionTextColor0(nativePtr);
        }

        @Override
        public void setSelectionBackgroundColor(int color) {
            checkNotClosed();
            setSelectionBackgroundColor0(nativePtr, color);
        }

        @Override
        public int getSelectionBackgroundColor() {
            checkNotClosed();
            return getSelectionBackgroundColor0(nativePtr);
        }

        @Override
        public void setFontList(@Nullable String fontList) {
            checkNotClosed();
            setFontList0(nativePtr, fontList);
        }

        @Override
        public void applyTextColor(int color, @Nonnull CefRange range) {
            checkNotClosed();
            applyTextColor0(nativePtr, color, range);
        }

        @Override
        public void applyTextStyle(@Nonnull CefTextStyle style, boolean add, @Nonnull CefRange range) {
            checkNotClosed();
            applyTextStyle0(nativePtr, style, add, range);
        }

        @Override
        public boolean isCommandEnabled(@Nonnull CefTextFieldCommands commandId) {
            checkNotClosed();
            return isCommandEnabled0(nativePtr, commandId);
        }

        @Override
        public void executeCommand(@Nonnull CefTextFieldCommands commandId) {
            checkNotClosed();
            executeCommand0(nativePtr, commandId);
        }

        @Override
        public void clearEditHistory() {
            checkNotClosed();
            clearEditHistory0(nativePtr);
        }

        @Override
        public void setPlaceholderText(@Nullable String text) {
            checkNotClosed();
            setPlaceholderText0(nativePtr, text);
        }

        @Override
        public Optional<String> getPlaceholderText() {
            checkNotClosed();
            return Optional.ofNullable(getPlaceholderText0(nativePtr));
        }

        @Override
        public void setPlaceholderTextColor(int color) {
            checkNotClosed();
            setPlaceholderTextColor0(nativePtr, color);
        }

        @Override
        public void setAccessibleName(@Nullable String name) {
            checkNotClosed();
            setAccessibleName0(nativePtr, name);
        }

        private static native void setPasswordInput0(long self, boolean passwordInput);

        private static native boolean isPasswordInput0(long self);

        private static native void setReadOnly0(long self, boolean readOnly);

        private static native boolean isReadOnly0(long self);

        private static native String getText0(long self);

        private static native void setText0(long self, String text);

        private static native void appendText0(long self, String text);

        private static native void insertOrReplaceText0(long self, String text);

        private static native boolean hasSelection0(long self);

        private static native String getSelectedText0(long self);

        private static native void selectAll0(long self, boolean reversed);

        private static native void clearSelection0(long self);

        private static native CefRange getSelectedRange0(long self);

        private static native void selectRange0(long self, CefRange range);

        private static native long getCursorPosition0(long self);

        private static native void setTextColor0(long self, int color);

        private static native int getTextColor0(long self);

        private static native void setSelectionTextColor0(long self, int color);

        private static native int getSelectionTextColor0(long self);

        private static native void setSelectionBackgroundColor0(long self, int color);

        private static native int getSelectionBackgroundColor0(long self);

        private static native void setFontList0(long self, String fontList);

        private static native void applyTextColor0(long self, int color, CefRange range);

        private static native void applyTextStyle0(long self, CefTextStyle style, boolean add, CefRange range);

        private static native boolean isCommandEnabled0(long self, CefTextFieldCommands commandId);

        private static native void executeCommand0(long self, CefTextFieldCommands commandId);

        private static native void clearEditHistory0(long self);

        private static native void setPlaceholderText0(long self, String text);

        private static native String getPlaceholderText0(long self);

        private static native void setPlaceholderTextColor0(long self, int color);

        private static native void setAccessibleName0(long self, String name);

        static native CefTextfield create0(CefTextfieldDelegate delegate);

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
            return "CefTextfield{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
