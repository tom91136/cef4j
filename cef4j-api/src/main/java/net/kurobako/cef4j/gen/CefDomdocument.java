// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used to represent a DOM document. The methods of this class should only be called on the render process main
 * thread thread.
 */
public interface CefDomdocument {

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefDomDocumentType getType();

    /** Returns the document associated with this node. */
    long getDocument();

    /** Returns the BODY node of an HTML document. */
    long getBody();

    /** Returns the HEAD node of an HTML document. */
    long getHead();

    /** Returns the title of an HTML document. */
    Optional<String> getTitle();

    /** Returns the document element with the specified ID value. */
    long getElementById(@Nonnull String id);

    /** Returns the node that currently has keyboard focus. */
    long getFocusedNode();

    /** Returns true if a portion of the document is selected. */
    boolean hasSelection();

    /** Returns the selection offset within the start node. */
    int getSelectionStartOffset();

    /** Returns the selection offset within the end node. */
    int getSelectionEndOffset();

    /** Returns the contents of this selection as markup. */
    Optional<String> getSelectionAsMarkup();

    /** Returns the contents of this selection as text. */
    Optional<String> getSelectionAsText();

    Optional<String> getBaseUrl();

    Optional<String> getCompleteUrl(@Nonnull String partialURL);

    static class NativePeer implements CefDomdocument {
        private volatile long nativePtr;

        @Override
        public CefDomDocumentType getType() {
            return N_GetType(nativePtr);
        }

        @Override
        public long getDocument() {
            return N_GetDocument(nativePtr);
        }

        @Override
        public long getBody() {
            return N_GetBody(nativePtr);
        }

        @Override
        public long getHead() {
            return N_GetHead(nativePtr);
        }

        @Override
        public Optional<String> getTitle() {
            return Optional.ofNullable(N_GetTitle(nativePtr));
        }

        @Override
        public long getElementById(String id) {
            return N_GetElementById(nativePtr, id);
        }

        @Override
        public long getFocusedNode() {
            return N_GetFocusedNode(nativePtr);
        }

        @Override
        public boolean hasSelection() {
            return N_HasSelection(nativePtr);
        }

        @Override
        public int getSelectionStartOffset() {
            return N_GetSelectionStartOffset(nativePtr);
        }

        @Override
        public int getSelectionEndOffset() {
            return N_GetSelectionEndOffset(nativePtr);
        }

        @Override
        public Optional<String> getSelectionAsMarkup() {
            return Optional.ofNullable(N_GetSelectionAsMarkup(nativePtr));
        }

        @Override
        public Optional<String> getSelectionAsText() {
            return Optional.ofNullable(N_GetSelectionAsText(nativePtr));
        }

        @Override
        public Optional<String> getBaseUrl() {
            return Optional.ofNullable(N_GetBaseUrl(nativePtr));
        }

        @Override
        public Optional<String> getCompleteUrl(String partialURL) {
            return Optional.ofNullable(N_GetCompleteUrl(nativePtr, partialURL));
        }

        private native CefDomDocumentType N_GetType(long self);

        private native long N_GetDocument(long self);

        private native long N_GetBody(long self);

        private native long N_GetHead(long self);

        private native String N_GetTitle(long self);

        private native long N_GetElementById(long self, String id);

        private native long N_GetFocusedNode(long self);

        private native boolean N_HasSelection(long self);

        private native int N_GetSelectionStartOffset(long self);

        private native int N_GetSelectionEndOffset(long self);

        private native String N_GetSelectionAsMarkup(long self);

        private native String N_GetSelectionAsText(long self);

        private native String N_GetBaseUrl(long self);

        private native String N_GetCompleteUrl(long self, String partialURL);

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
            return "CefDomdocument{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
