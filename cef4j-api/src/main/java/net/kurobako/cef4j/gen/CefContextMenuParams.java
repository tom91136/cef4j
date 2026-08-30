// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Provides information about the context menu state. The methods of this class can only be accessed on browser process
 * the UI thread.
 *
 * <p>Definition generated from cef_context_menu_handler_capi.h
 *
 * <pre>typedef struct _cef_context_menu_params_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_context_menu_params_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:195</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefContextMenuParams extends CefLibraryObject {

    /**
     * Returns the X coordinate of the mouse where the context menu was invoked. Coords are relative to the associated
     * RenderView's origin.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_xcoord)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:207</a>
     */
    int getXCoord();

    /**
     * Returns the Y coordinate of the mouse where the context menu was invoked. Coords are relative to the associated
     * RenderView's origin.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_ycoord)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:214</a>
     */
    int getYCoord();

    /**
     * Returns flags representing the type of node that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_context_menu_type_flags_t (CEF_CALLBACK* get_type_flags)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @return the result, or {@code CM_TYPEFLAG_NONE} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:221</a>
     */
    CefContextMenuTypeFlags getTypeFlags();

    /**
     * Returns the URL of the link, if any, that encloses the node that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_link_url)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:228</a>
     */
    Optional<String> getLinkUrl();

    /**
     * Returns the link URL, if any, to be used ONLY for "copy link address". We don't validate this field in the
     * frontend process.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_unfiltered_link_url)(struct _cef_context_menu_params_t* self);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:235</a>
     */
    Optional<String> getUnfilteredLinkUrl();

    /**
     * Returns the source URL, if any, for the element that the context menu was invoked on. Example of elements with
     * source URLs are img, audio, and video.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_source_url)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:242</a>
     */
    Optional<String> getSourceUrl();

    /**
     * Returns {@code true} if the context menu was invoked on an image which has non-empty contents.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_image_contents)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:250</a>
     */
    boolean hasImageContents();

    /**
     * Returns the title text or the alt text if the context menu was invoked on an image.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_title_text)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:257</a>
     */
    Optional<String> getTitleText();

    /**
     * Returns the URL of the top level page that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_page_url)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:264</a>
     */
    Optional<String> getPageUrl();

    /**
     * Returns the URL of the subframe that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_frame_url)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:271</a>
     */
    Optional<String> getFrameUrl();

    /**
     * Returns the character encoding of the subframe that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_frame_charset)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:277</a>
     */
    Optional<String> getFrameCharset();

    /**
     * Returns the type of context node that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_context_menu_media_type_t (CEF_CALLBACK* get_media_type)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @return the result, or {@code CM_MEDIATYPE_NONE} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:284</a>
     */
    CefContextMenuMediaType getMediaType();

    /**
     * Returns flags representing the actions supported by the media element, if any, that the context menu was invoked
     * on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>
     * cef_context_menu_media_state_flags_t (CEF_CALLBACK* get_media_state_flags)(struct _cef_context_menu_params_t* self);
     * </pre>
     *
     * @return the result, or {@code CM_MEDIAFLAG_NONE} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:290</a>
     */
    CefContextMenuMediaStateFlags getMediaStateFlags();

    /**
     * Returns the text of the selection, if any, that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_selection_text)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:297</a>
     */
    Optional<String> getSelectionText();

    /**
     * Returns the text of the misspelled word, if any, that the context menu was invoked on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_misspelled_word)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:304</a>
     */
    Optional<String> getMisspelledWord();

    /**
     * Returns {@code true} if suggestions exist, {@code false} otherwise. Fills in {@code suggestions} from the spell
     * check service for the misspelled word if there is one.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_dictionary_suggestions)(struct _cef_context_menu_params_t* self, cef_string_list_t suggestions);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:311</a>
     */
    boolean getDictionarySuggestions(@Nonnull List<String> suggestions);

    /**
     * Returns {@code true} if the context menu was invoked on an editable node.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_editable)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:319</a>
     */
    boolean isEditable();

    /**
     * Returns {@code true} if the context menu was invoked on an editable node where spell-check is enabled.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_spell_check_enabled)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:325</a>
     */
    boolean isSpellCheckEnabled();

    /**
     * Returns flags representing the actions supported by the editable node, if any, that the context menu was invoked
     * on.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>
     * cef_context_menu_edit_state_flags_t (CEF_CALLBACK* get_edit_state_flags)(struct _cef_context_menu_params_t* self);
     * </pre>
     *
     * @return the result, or {@code CM_EDITFLAG_NONE} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:332</a>
     */
    CefContextMenuEditStateFlags getEditStateFlags();

    /**
     * Returns {@code true} if the context menu contains items specified by the renderer process.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_custom_menu)(struct _cef_context_menu_params_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:339</a>
     */
    boolean isCustomMenu();

    final class NativePeer implements CefContextMenuParams, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefContextMenuParams has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefContextMenuParams.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefContextMenuParams 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public int getXCoord() {
            checkNotClosed();
            return getXCoord0(nativePtr);
        }

        @Override
        public int getYCoord() {
            checkNotClosed();
            return getYCoord0(nativePtr);
        }

        @Override
        public CefContextMenuTypeFlags getTypeFlags() {
            checkNotClosed();
            return getTypeFlags0(nativePtr);
        }

        @Override
        public Optional<String> getLinkUrl() {
            checkNotClosed();
            return Optional.ofNullable(getLinkUrl0(nativePtr));
        }

        @Override
        public Optional<String> getUnfilteredLinkUrl() {
            checkNotClosed();
            return Optional.ofNullable(getUnfilteredLinkUrl0(nativePtr));
        }

        @Override
        public Optional<String> getSourceUrl() {
            checkNotClosed();
            return Optional.ofNullable(getSourceUrl0(nativePtr));
        }

        @Override
        public boolean hasImageContents() {
            checkNotClosed();
            return hasImageContents0(nativePtr);
        }

        @Override
        public Optional<String> getTitleText() {
            checkNotClosed();
            return Optional.ofNullable(getTitleText0(nativePtr));
        }

        @Override
        public Optional<String> getPageUrl() {
            checkNotClosed();
            return Optional.ofNullable(getPageUrl0(nativePtr));
        }

        @Override
        public Optional<String> getFrameUrl() {
            checkNotClosed();
            return Optional.ofNullable(getFrameUrl0(nativePtr));
        }

        @Override
        public Optional<String> getFrameCharset() {
            checkNotClosed();
            return Optional.ofNullable(getFrameCharset0(nativePtr));
        }

        @Override
        public CefContextMenuMediaType getMediaType() {
            checkNotClosed();
            return getMediaType0(nativePtr);
        }

        @Override
        public CefContextMenuMediaStateFlags getMediaStateFlags() {
            checkNotClosed();
            return getMediaStateFlags0(nativePtr);
        }

        @Override
        public Optional<String> getSelectionText() {
            checkNotClosed();
            return Optional.ofNullable(getSelectionText0(nativePtr));
        }

        @Override
        public Optional<String> getMisspelledWord() {
            checkNotClosed();
            return Optional.ofNullable(getMisspelledWord0(nativePtr));
        }

        @Override
        public boolean getDictionarySuggestions(@Nonnull List<String> suggestions) {
            checkNotClosed();
            return getDictionarySuggestions0(nativePtr, suggestions);
        }

        @Override
        public boolean isEditable() {
            checkNotClosed();
            return isEditable0(nativePtr);
        }

        @Override
        public boolean isSpellCheckEnabled() {
            checkNotClosed();
            return isSpellCheckEnabled0(nativePtr);
        }

        @Override
        public CefContextMenuEditStateFlags getEditStateFlags() {
            checkNotClosed();
            return getEditStateFlags0(nativePtr);
        }

        @Override
        public boolean isCustomMenu() {
            checkNotClosed();
            return isCustomMenu0(nativePtr);
        }

        static native int getXCoord0(long self);

        static native int getYCoord0(long self);

        static native CefContextMenuTypeFlags getTypeFlags0(long self);

        static native String getLinkUrl0(long self);

        static native String getUnfilteredLinkUrl0(long self);

        static native String getSourceUrl0(long self);

        static native boolean hasImageContents0(long self);

        static native String getTitleText0(long self);

        static native String getPageUrl0(long self);

        static native String getFrameUrl0(long self);

        static native String getFrameCharset0(long self);

        static native CefContextMenuMediaType getMediaType0(long self);

        static native CefContextMenuMediaStateFlags getMediaStateFlags0(long self);

        static native String getSelectionText0(long self);

        static native String getMisspelledWord0(long self);

        static native boolean getDictionarySuggestions0(long self, @Nonnull List<String> suggestions);

        static native boolean isEditable0(long self);

        static native boolean isSpellCheckEnabled0(long self);

        static native CefContextMenuEditStateFlags getEditStateFlags0(long self);

        static native boolean isCustomMenu0(long self);

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
            return "CefContextMenuParams{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
