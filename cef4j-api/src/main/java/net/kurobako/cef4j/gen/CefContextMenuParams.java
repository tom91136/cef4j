// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Provides information about the context menu state. The methods of this class can only be accessed on browser process
 * the UI thread.
 */
public interface CefContextMenuParams {

    int getXcoord();

    int getYcoord();

    /**
     * Returns flags representing the type of node that the context menu was invoked on.
     *
     * @return the result, or {@code CM_TYPEFLAG_NONE} for default handling
     */
    CefContextMenuTypeFlags getTypeFlags();

    /** Returns the URL of the link, if any, that encloses the node that the context menu was invoked on. */
    Optional<String> getLinkUrl();

    /**
     * Returns the link URL, if any, to be used ONLY for "copy link address". We don't validate this field in the
     * frontend process.
     */
    Optional<String> getUnfilteredLinkUrl();

    /**
     * Returns the source URL, if any, for the element that the context menu was invoked on. Example of elements with
     * source URLs are img, audio, and video.
     */
    Optional<String> getSourceUrl();

    /** Returns true if the context menu was invoked on an image which has non-empty contents. */
    boolean hasImageContents();

    /** Returns the title text or the alt text if the context menu was invoked on an image. */
    Optional<String> getTitleText();

    /** Returns the URL of the top level page that the context menu was invoked on. */
    Optional<String> getPageUrl();

    /** Returns the URL of the subframe that the context menu was invoked on. */
    Optional<String> getFrameUrl();

    /** Returns the character encoding of the subframe that the context menu was invoked on. */
    Optional<String> getFrameCharset();

    /**
     * Returns the type of context node that the context menu was invoked on.
     *
     * @return the result, or {@code CM_MEDIATYPE_NONE} for default handling
     */
    CefContextMenuMediaType getMediaType();

    /**
     * Returns flags representing the actions supported by the media element, if any, that the context menu was invoked
     * on.
     *
     * @return the result, or {@code CM_MEDIAFLAG_NONE} for default handling
     */
    CefContextMenuMediaStateFlags getMediaStateFlags();

    /** Returns the text of the selection, if any, that the context menu was invoked on. */
    Optional<String> getSelectionText();

    /** Returns the text of the misspelled word, if any, that the context menu was invoked on. */
    Optional<String> getMisspelledWord();

    /**
     * Returns true if suggestions exist, false otherwise. Fills in |suggestions| from the spell check service for the
     * misspelled word if there is one.
     */
    boolean getDictionarySuggestions(@Nonnull java.util.List<String> suggestions);

    /** Returns true if this is an editable node. */
    boolean isEditable();

    /** Returns true if the context menu was invoked on an editable node where spell-check is enabled. */
    boolean isSpellCheckEnabled();

    /**
     * Returns flags representing the actions supported by the editable node, if any, that the context menu was invoked
     * on.
     *
     * @return the result, or {@code CM_EDITFLAG_NONE} for default handling
     */
    CefContextMenuEditStateFlags getEditStateFlags();

    /** Returns true if the context menu contains items specified by the renderer process. */
    boolean isCustomMenu();

    static class NativePeer implements CefContextMenuParams {
        private volatile long nativePtr;

        @Override
        public int getXcoord() {
            return N_GetXcoord(nativePtr);
        }

        @Override
        public int getYcoord() {
            return N_GetYcoord(nativePtr);
        }

        @Override
        public CefContextMenuTypeFlags getTypeFlags() {
            return N_GetTypeFlags(nativePtr);
        }

        @Override
        public Optional<String> getLinkUrl() {
            return Optional.ofNullable(N_GetLinkUrl(nativePtr));
        }

        @Override
        public Optional<String> getUnfilteredLinkUrl() {
            return Optional.ofNullable(N_GetUnfilteredLinkUrl(nativePtr));
        }

        @Override
        public Optional<String> getSourceUrl() {
            return Optional.ofNullable(N_GetSourceUrl(nativePtr));
        }

        @Override
        public boolean hasImageContents() {
            return N_HasImageContents(nativePtr);
        }

        @Override
        public Optional<String> getTitleText() {
            return Optional.ofNullable(N_GetTitleText(nativePtr));
        }

        @Override
        public Optional<String> getPageUrl() {
            return Optional.ofNullable(N_GetPageUrl(nativePtr));
        }

        @Override
        public Optional<String> getFrameUrl() {
            return Optional.ofNullable(N_GetFrameUrl(nativePtr));
        }

        @Override
        public Optional<String> getFrameCharset() {
            return Optional.ofNullable(N_GetFrameCharset(nativePtr));
        }

        @Override
        public CefContextMenuMediaType getMediaType() {
            return N_GetMediaType(nativePtr);
        }

        @Override
        public CefContextMenuMediaStateFlags getMediaStateFlags() {
            return N_GetMediaStateFlags(nativePtr);
        }

        @Override
        public Optional<String> getSelectionText() {
            return Optional.ofNullable(N_GetSelectionText(nativePtr));
        }

        @Override
        public Optional<String> getMisspelledWord() {
            return Optional.ofNullable(N_GetMisspelledWord(nativePtr));
        }

        @Override
        public boolean getDictionarySuggestions(java.util.List<String> suggestions) {
            return N_GetDictionarySuggestions(nativePtr, suggestions);
        }

        @Override
        public boolean isEditable() {
            return N_IsEditable(nativePtr);
        }

        @Override
        public boolean isSpellCheckEnabled() {
            return N_IsSpellCheckEnabled(nativePtr);
        }

        @Override
        public CefContextMenuEditStateFlags getEditStateFlags() {
            return N_GetEditStateFlags(nativePtr);
        }

        @Override
        public boolean isCustomMenu() {
            return N_IsCustomMenu(nativePtr);
        }

        private native int N_GetXcoord(long self);

        private native int N_GetYcoord(long self);

        private native CefContextMenuTypeFlags N_GetTypeFlags(long self);

        private native String N_GetLinkUrl(long self);

        private native String N_GetUnfilteredLinkUrl(long self);

        private native String N_GetSourceUrl(long self);

        private native boolean N_HasImageContents(long self);

        private native String N_GetTitleText(long self);

        private native String N_GetPageUrl(long self);

        private native String N_GetFrameUrl(long self);

        private native String N_GetFrameCharset(long self);

        private native CefContextMenuMediaType N_GetMediaType(long self);

        private native CefContextMenuMediaStateFlags N_GetMediaStateFlags(long self);

        private native String N_GetSelectionText(long self);

        private native String N_GetMisspelledWord(long self);

        private native boolean N_GetDictionarySuggestions(long self, java.util.List<String> suggestions);

        private native boolean N_IsEditable(long self);

        private native boolean N_IsSpellCheckEnabled(long self);

        private native CefContextMenuEditStateFlags N_GetEditStateFlags(long self);

        private native boolean N_IsCustomMenu(long self);

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
