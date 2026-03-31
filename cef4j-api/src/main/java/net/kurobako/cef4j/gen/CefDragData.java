// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Class used to represent drag data. The methods of this class may be called on any thread. */
public interface CefDragData {

    /** Returns a copy of the current object. */
    long cefClone();

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Returns true if the drag data is a link. */
    boolean isLink();

    /** Returns true if the drag data is a text or html fragment. */
    boolean isFragment();

    /** Returns true if the drag data is a file. */
    boolean isFile();

    /** Returns the URL of the link, if any, that encloses the node that the context menu was invoked on. */
    Optional<String> getLinkUrl();

    /** Return the title associated with the link being dragged. */
    Optional<String> getLinkTitle();

    /** Return the metadata, if any, associated with the link being dragged. */
    Optional<String> getLinkMetadata();

    /** Return the plain text fragment that is being dragged. */
    Optional<String> getFragmentText();

    /** Return the text/html fragment that is being dragged. */
    Optional<String> getFragmentHtml();

    Optional<String> getFragmentBaseUrl();

    /** Return the name of the file being dragged out of the browser window. */
    Optional<String> getFileName();

    /**
     * Write the contents of the file being dragged out of the web view into |writer|. Returns the number of bytes sent
     * to |writer|. If |writer| is NULL this method will return the size of the file contents in bytes. Call
     * GetFileName() to get a suggested name for the file.
     *
     * @param writer may be null
     */
    long getFileContents(long writer);

    /** Retrieve the list of file names that are being dragged into the browser window. */
    boolean getFileNames(@Nonnull java.util.List<String> names);

    /** Retrieve the list of file paths that are being dragged into the browser window. */
    boolean getFilePaths(@Nonnull java.util.List<String> paths);

    void setLinkUrl(@Nonnull String url);

    /**
     * Set the title associated with the link being dragged.
     *
     * @param title may be null
     */
    void setLinkTitle(@Nullable String title);

    /**
     * Set the metadata associated with the link being dragged.
     *
     * @param data may be null
     */
    void setLinkMetadata(@Nullable String data);

    /**
     * Set the plain text fragment that is being dragged.
     *
     * @param text may be null
     */
    void setFragmentText(@Nullable String text);

    /**
     * Set the text/html fragment that is being dragged.
     *
     * @param html may be null
     */
    void setFragmentHtml(@Nullable String html);

    void setFragmentBaseUrl(@Nonnull String baseUrl);

    /**
     * Reset the file contents. You should do this before calling CefBrowserHost::DragTargetDragEnter as the web view
     * does not allow us to drag in this kind of data.
     */
    void resetFileContents();

    /**
     * Add a file that is being dragged into the webview.
     *
     * @param displayName may be null
     */
    void addFile(@Nonnull String path, @Nullable String displayName);

    /** Clear list of filenames. */
    void clearFilenames();

    /** Get the image representation of drag data. May return NULL if no image representation is available. */
    long getImage();

    /** Get the image hotspot (drag start location relative to image dimensions). */
    CefPoint getImageHotspot();

    /** Returns true if an image representation of drag data is available. */
    boolean hasImage();

    static class NativePeer implements CefDragData {
        private volatile long nativePtr;

        @Override
        public long cefClone() {
            return N_Clone(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public boolean isLink() {
            return N_IsLink(nativePtr);
        }

        @Override
        public boolean isFragment() {
            return N_IsFragment(nativePtr);
        }

        @Override
        public boolean isFile() {
            return N_IsFile(nativePtr);
        }

        @Override
        public Optional<String> getLinkUrl() {
            return Optional.ofNullable(N_GetLinkUrl(nativePtr));
        }

        @Override
        public Optional<String> getLinkTitle() {
            return Optional.ofNullable(N_GetLinkTitle(nativePtr));
        }

        @Override
        public Optional<String> getLinkMetadata() {
            return Optional.ofNullable(N_GetLinkMetadata(nativePtr));
        }

        @Override
        public Optional<String> getFragmentText() {
            return Optional.ofNullable(N_GetFragmentText(nativePtr));
        }

        @Override
        public Optional<String> getFragmentHtml() {
            return Optional.ofNullable(N_GetFragmentHtml(nativePtr));
        }

        @Override
        public Optional<String> getFragmentBaseUrl() {
            return Optional.ofNullable(N_GetFragmentBaseUrl(nativePtr));
        }

        @Override
        public Optional<String> getFileName() {
            return Optional.ofNullable(N_GetFileName(nativePtr));
        }

        @Override
        public long getFileContents(long writer) {
            return N_GetFileContents(nativePtr, writer);
        }

        @Override
        public boolean getFileNames(java.util.List<String> names) {
            return N_GetFileNames(nativePtr, names);
        }

        @Override
        public boolean getFilePaths(java.util.List<String> paths) {
            return N_GetFilePaths(nativePtr, paths);
        }

        @Override
        public void setLinkUrl(String url) {
            N_SetLinkUrl(nativePtr, url);
        }

        @Override
        public void setLinkTitle(String title) {
            N_SetLinkTitle(nativePtr, title);
        }

        @Override
        public void setLinkMetadata(String data) {
            N_SetLinkMetadata(nativePtr, data);
        }

        @Override
        public void setFragmentText(String text) {
            N_SetFragmentText(nativePtr, text);
        }

        @Override
        public void setFragmentHtml(String html) {
            N_SetFragmentHtml(nativePtr, html);
        }

        @Override
        public void setFragmentBaseUrl(String baseUrl) {
            N_SetFragmentBaseUrl(nativePtr, baseUrl);
        }

        @Override
        public void resetFileContents() {
            N_ResetFileContents(nativePtr);
        }

        @Override
        public void addFile(String path, String displayName) {
            N_AddFile(nativePtr, path, displayName);
        }

        @Override
        public void clearFilenames() {
            N_ClearFilenames(nativePtr);
        }

        @Override
        public long getImage() {
            return N_GetImage(nativePtr);
        }

        @Override
        public CefPoint getImageHotspot() {
            return N_GetImageHotspot(nativePtr);
        }

        @Override
        public boolean hasImage() {
            return N_HasImage(nativePtr);
        }

        private native long N_Clone(long self);

        private native boolean N_IsReadOnly(long self);

        private native boolean N_IsLink(long self);

        private native boolean N_IsFragment(long self);

        private native boolean N_IsFile(long self);

        private native String N_GetLinkUrl(long self);

        private native String N_GetLinkTitle(long self);

        private native String N_GetLinkMetadata(long self);

        private native String N_GetFragmentText(long self);

        private native String N_GetFragmentHtml(long self);

        private native String N_GetFragmentBaseUrl(long self);

        private native String N_GetFileName(long self);

        private native long N_GetFileContents(long self, long writer);

        private native boolean N_GetFileNames(long self, java.util.List<String> names);

        private native boolean N_GetFilePaths(long self, java.util.List<String> paths);

        private native void N_SetLinkUrl(long self, String url);

        private native void N_SetLinkTitle(long self, String title);

        private native void N_SetLinkMetadata(long self, String data);

        private native void N_SetFragmentText(long self, String text);

        private native void N_SetFragmentHtml(long self, String html);

        private native void N_SetFragmentBaseUrl(long self, String baseUrl);

        private native void N_ResetFileContents(long self);

        private native void N_AddFile(long self, String path, String displayName);

        private native void N_ClearFilenames(long self);

        private native long N_GetImage(long self);

        private native CefPoint N_GetImageHotspot(long self);

        private native boolean N_HasImage(long self);

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
            return "CefDragData{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
