// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent drag data. The methods of this class may be called on any thread.
 * <p>Definition generated from cef_drag_data_capi.h
 * <pre>typedef struct _cef_drag_data_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_drag_data_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:47</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDragData extends CefLibraryObject {

    /**
     * Returns a copy of the current object.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_drag_data_t* (CEF_CALLBACK* clone)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:60</a>
     */
    Optional<CefDragData> cefClone();

    /**
     * Returns {@code true} if this object is read-only.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:66</a>
     */
    boolean isReadOnly();

    /**
     * Returns {@code true} if the drag data is a link.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* is_link)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:72</a>
     */
    boolean isLink();

    /**
     * Returns {@code true} if the drag data is a text or html fragment.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* is_fragment)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:78</a>
     */
    boolean isFragment();

    /**
     * Returns {@code true} if the drag data is a file.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* is_file)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:84</a>
     */
    boolean isFile();

    /**
     * Returns the URL of the link, if any, that encloses the node that the context menu was invoked on.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_link_url)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:228</a>
     */
    Optional<String> getLinkUrl();

    /**
     * Return the title associated with the link being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_link_title)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:96</a>
     */
    Optional<String> getLinkTitle();

    /**
     * Return the metadata, if any, associated with the link being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_link_metadata)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:102</a>
     */
    Optional<String> getLinkMetadata();

    /**
     * Return the plain text fragment that is being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_fragment_text)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:108</a>
     */
    Optional<String> getFragmentText();

    /**
     * Return the text/html fragment that is being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_fragment_html)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:114</a>
     */
    Optional<String> getFragmentHtml();

    /**
     * Return the base URL that the fragment came from. This value is used for resolving relative URLs and may be empty.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_fragment_base_url)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:120</a>
     */
    Optional<String> getFragmentBaseUrl();

    /**
     * Return the name of the file being dragged out of the browser window.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_file_name)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:127</a>
     */
    Optional<String> getFileName();

    /**
     * Write the contents of the file being dragged out of the web view into {@code writer}. Returns the number of bytes sent to {@code writer}. If {@code writer} is {@code null} this method will return the size of the file contents in bytes. Call GetFileName() to get a suggested name for the file.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>size_t (CEF_CALLBACK* get_file_contents)(struct _cef_drag_data_t* self, struct _cef_stream_writer_t* writer);</pre>
     *
     * @param writer may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:133</a>
     */
    long getFileContents(@Nullable CefStreamWriter writer);

    /**
     * Retrieve the list of file names that are being dragged into the browser window.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* get_file_names)(struct _cef_drag_data_t* self, cef_string_list_t names);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:142</a>
     */
    boolean getFileNames(@Nonnull List<String> names);

    /**
     * Retrieve the list of file paths that are being dragged into the browser window.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* get_file_paths)(struct _cef_drag_data_t* self, cef_string_list_t paths);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:149</a>
     */
    boolean getFilePaths(@Nonnull List<String> paths);

    /**
     * Set the link URL that is being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* set_link_url)(struct _cef_drag_data_t* self, const cef_string_t* url);</pre>
     *
     * @param url may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:156</a>
     */
    void setLinkUrl(@Nullable String url);

    /**
     * Set the title associated with the link being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* set_link_title)(struct _cef_drag_data_t* self, const cef_string_t* title);</pre>
     *
     * @param title may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:162</a>
     */
    void setLinkTitle(@Nullable String title);

    /**
     * Set the metadata associated with the link being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* set_link_metadata)(struct _cef_drag_data_t* self, const cef_string_t* data);</pre>
     *
     * @param data may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:168</a>
     */
    void setLinkMetadata(@Nullable String data);

    /**
     * Set the plain text fragment that is being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* set_fragment_text)(struct _cef_drag_data_t* self, const cef_string_t* text);</pre>
     *
     * @param text may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:174</a>
     */
    void setFragmentText(@Nullable String text);

    /**
     * Set the text/html fragment that is being dragged.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* set_fragment_html)(struct _cef_drag_data_t* self, const cef_string_t* html);</pre>
     *
     * @param html may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:180</a>
     */
    void setFragmentHtml(@Nullable String html);

    /**
     * Set the base URL that the fragment came from.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* set_fragment_base_url)(struct _cef_drag_data_t* self, const cef_string_t* base_url);</pre>
     *
     * @param baseUrl may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:186</a>
     */
    void setFragmentBaseUrl(@Nullable String baseUrl);

    /**
     * Reset the file contents. You should do this before calling {@link net.kurobako.cef4j.gen.CefBrowserHost#dragTargetDragEnter(CefDragData, CefMouseEvent, CefDragOperationsMask)} as the web view does not allow us to drag in this kind of data.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* reset_file_contents)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:192</a>
     */
    void resetFileContents();

    /**
     * Add a file that is being dragged into the webview.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* add_file)(struct _cef_drag_data_t* self, const cef_string_t* path, const cef_string_t* display_name);</pre>
     *
     * @param displayName may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:200</a>
     */
    void addFile(@Nullable String path, @Nullable String displayName);

    /**
     * Clear list of filenames.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>void (CEF_CALLBACK* clear_filenames)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:207</a>
     */
    void clearFilenames();

    /**
     * Get the image representation of drag data. May return {@code null} if no image representation is available.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_image_t* (CEF_CALLBACK* get_image)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:213</a>
     */
    Optional<CefImage> getImage();

    /**
     * Get the image hotspot (drag start location relative to image dimensions).
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>cef_point_t* (CEF_CALLBACK* get_image_hotspot)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:220</a>
     */
    CefPoint getImageHotspot();

    /**
     * Returns {@code true} if an image representation of drag data is available.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>int (CEF_CALLBACK* has_image)(struct _cef_drag_data_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:226</a>
     */
    boolean hasImage();
    /**
     * Create a new CefDragData object.
     * <p>Definition generated from cef_drag_data_capi.h
     * <pre>CEF_EXPORT cef_drag_data_t* cef_drag_data_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__data_8h.html">cef_drag_data.h:54</a>
     */
    static Optional<CefDragData> create() {
      return Optional.ofNullable(NativePeer.create0());
  }

    final class NativePeer implements CefDragData, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefDragData has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDragData.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDragData 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public Optional<CefDragData> cefClone() {
          checkNotClosed();
          return Optional.ofNullable(cefClone0(nativePtr));
      }

        @Override
      public boolean isReadOnly() {
          checkNotClosed();
          return isReadOnly0(nativePtr);
      }

        @Override
      public boolean isLink() {
          checkNotClosed();
          return isLink0(nativePtr);
      }

        @Override
      public boolean isFragment() {
          checkNotClosed();
          return isFragment0(nativePtr);
      }

        @Override
      public boolean isFile() {
          checkNotClosed();
          return isFile0(nativePtr);
      }

        @Override
      public Optional<String> getLinkUrl() {
          checkNotClosed();
          return Optional.ofNullable(getLinkUrl0(nativePtr));
      }

        @Override
      public Optional<String> getLinkTitle() {
          checkNotClosed();
          return Optional.ofNullable(getLinkTitle0(nativePtr));
      }

        @Override
      public Optional<String> getLinkMetadata() {
          checkNotClosed();
          return Optional.ofNullable(getLinkMetadata0(nativePtr));
      }

        @Override
      public Optional<String> getFragmentText() {
          checkNotClosed();
          return Optional.ofNullable(getFragmentText0(nativePtr));
      }

        @Override
      public Optional<String> getFragmentHtml() {
          checkNotClosed();
          return Optional.ofNullable(getFragmentHtml0(nativePtr));
      }

        @Override
      public Optional<String> getFragmentBaseUrl() {
          checkNotClosed();
          return Optional.ofNullable(getFragmentBaseUrl0(nativePtr));
      }

        @Override
      public Optional<String> getFileName() {
          checkNotClosed();
          return Optional.ofNullable(getFileName0(nativePtr));
      }

        @Override
      public long getFileContents(@Nullable CefStreamWriter writer) {
          checkNotClosed();
            CefLibraryObject.requireOpen(writer, "CefStreamWriter");
          return getFileContents0(nativePtr, writer);
      }

        @Override
      public boolean getFileNames(@Nonnull List<String> names) {
          checkNotClosed();
          return getFileNames0(nativePtr, names);
      }

        @Override
      public boolean getFilePaths(@Nonnull List<String> paths) {
          checkNotClosed();
          return getFilePaths0(nativePtr, paths);
      }

        @Override
      public void setLinkUrl(@Nullable String url) {
          checkNotClosed();
          setLinkUrl0(nativePtr, url);
      }

        @Override
      public void setLinkTitle(@Nullable String title) {
          checkNotClosed();
          setLinkTitle0(nativePtr, title);
      }

        @Override
      public void setLinkMetadata(@Nullable String data) {
          checkNotClosed();
          setLinkMetadata0(nativePtr, data);
      }

        @Override
      public void setFragmentText(@Nullable String text) {
          checkNotClosed();
          setFragmentText0(nativePtr, text);
      }

        @Override
      public void setFragmentHtml(@Nullable String html) {
          checkNotClosed();
          setFragmentHtml0(nativePtr, html);
      }

        @Override
      public void setFragmentBaseUrl(@Nullable String baseUrl) {
          checkNotClosed();
          setFragmentBaseUrl0(nativePtr, baseUrl);
      }

        @Override
      public void resetFileContents() {
          checkNotClosed();
          resetFileContents0(nativePtr);
      }

        @Override
      public void addFile(@Nullable String path, @Nullable String displayName) {
          checkNotClosed();
          addFile0(nativePtr, path, displayName);
      }

        @Override
      public void clearFilenames() {
          checkNotClosed();
          clearFilenames0(nativePtr);
      }

        @Override
      public Optional<CefImage> getImage() {
          checkNotClosed();
          return Optional.ofNullable(getImage0(nativePtr));
      }

        @Override
      public CefPoint getImageHotspot() {
          checkNotClosed();
          return getImageHotspot0(nativePtr);
      }

        @Override
      public boolean hasImage() {
          checkNotClosed();
          return hasImage0(nativePtr);
      }


        static native CefDragData cefClone0(long self);

        static native boolean isReadOnly0(long self);

        static native boolean isLink0(long self);

        static native boolean isFragment0(long self);

        static native boolean isFile0(long self);

        static native String getLinkUrl0(long self);

        static native String getLinkTitle0(long self);

        static native String getLinkMetadata0(long self);

        static native String getFragmentText0(long self);

        static native String getFragmentHtml0(long self);

        static native String getFragmentBaseUrl0(long self);

        static native String getFileName0(long self);

        static native long getFileContents0(long self, CefStreamWriter writer);

        static native boolean getFileNames0(long self, List<String> names);

        static native boolean getFilePaths0(long self, List<String> paths);

        static native void setLinkUrl0(long self, String url);

        static native void setLinkTitle0(long self, String title);

        static native void setLinkMetadata0(long self, String data);

        static native void setFragmentText0(long self, String text);

        static native void setFragmentHtml0(long self, String html);

        static native void setFragmentBaseUrl0(long self, String baseUrl);

        static native void resetFileContents0(long self);

        static native void addFile0(long self, String path, String displayName);

        static native void clearFilenames0(long self);

        static native CefImage getImage0(long self);

        static native CefPoint getImageHotspot0(long self);

        static native boolean hasImage0(long self);

        static native CefDragData create0();

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
