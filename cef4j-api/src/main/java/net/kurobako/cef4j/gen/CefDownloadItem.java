// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;

/**
 * Class used to represent a download item.
 *
 * <p>Definition generated from cef_download_item_capi.h
 *
 * <pre>typedef struct _cef_download_item_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_download_item_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefDownloadItem extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the download is in progress.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_in_progress)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:56</a>
     */
    boolean isInProgress();

    /**
     * Returns {@code true} if the download is complete.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_complete)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:62</a>
     */
    boolean isComplete();

    /**
     * Returns {@code true} if the download has been canceled.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_canceled)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:68</a>
     */
    boolean isCanceled();

    /**
     * Returns {@code true} if the download has been interrupted.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_interrupted)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:74</a>
     */
    boolean isInterrupted();

    /**
     * Returns the most recent interrupt reason.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_download_interrupt_reason_t (CEF_CALLBACK* get_interrupt_reason)(struct _cef_download_item_t* self);
     * </pre>
     *
     * @return the result, or {@code CEF_DOWNLOAD_INTERRUPT_REASON_NONE} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:88</a>
     */
    CefDownloadInterruptReason getInterruptReason();

    /**
     * Returns a simple speed estimate in bytes/s.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_current_speed)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:94</a>
     */
    long getCurrentSpeed();

    /**
     * Returns the rough percent complete or -1 if the receive total size is unknown.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_percent_complete)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:100</a>
     */
    int getPercentComplete();

    /**
     * Returns the total number of bytes.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_total_bytes)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:107</a>
     */
    long getTotalBytes();

    /**
     * Returns the number of received bytes.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_received_bytes)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:113</a>
     */
    long getReceivedBytes();

    /**
     * Returns the time that the download started.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_start_time)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:119</a>
     */
    CefBasetime getStartTime();

    /**
     * Returns the time that the download ended.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_end_time)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:125</a>
     */
    CefBasetime getEndTime();

    /**
     * Returns the full path to the downloaded or downloading file.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_full_path)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:131</a>
     */
    Optional<String> getFullPath();

    /**
     * Returns the unique identifier for this download.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_id)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:137</a>
     */
    int getId();

    /**
     * Returns the URL.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:143</a>
     */
    Optional<String> getUrl();

    /**
     * Returns the original URL before any redirections.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_original_url)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:149</a>
     */
    Optional<String> getOriginalUrl();

    /**
     * Returns the suggested file name.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_suggested_file_name)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:155</a>
     */
    Optional<String> getSuggestedFileName();

    /**
     * Returns the content disposition.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_content_disposition)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:161</a>
     */
    Optional<String> getContentDisposition();

    /**
     * Returns the mime type.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_mime_type)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:167</a>
     */
    Optional<String> getMimeType();

    /**
     * Returns {@code true} if the download has been paused.
     *
     * <p>Added in CEF API version 14400.
     *
     * <p>Definition generated from cef_download_item_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_paused)(struct _cef_download_item_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:81</a>
     */
    boolean isPaused();

    final class NativePeer implements CefDownloadItem, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefDownloadItem has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDownloadItem.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDownloadItem 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return isValid0(nativePtr);
        }

        @Override
        public boolean isInProgress() {
            checkNotClosed();
            return isInProgress0(nativePtr);
        }

        @Override
        public boolean isComplete() {
            checkNotClosed();
            return isComplete0(nativePtr);
        }

        @Override
        public boolean isCanceled() {
            checkNotClosed();
            return isCanceled0(nativePtr);
        }

        @Override
        public boolean isInterrupted() {
            checkNotClosed();
            return isInterrupted0(nativePtr);
        }

        @Override
        public CefDownloadInterruptReason getInterruptReason() {
            checkNotClosed();
            return getInterruptReason0(nativePtr);
        }

        @Override
        public long getCurrentSpeed() {
            checkNotClosed();
            return getCurrentSpeed0(nativePtr);
        }

        @Override
        public int getPercentComplete() {
            checkNotClosed();
            return getPercentComplete0(nativePtr);
        }

        @Override
        public long getTotalBytes() {
            checkNotClosed();
            return getTotalBytes0(nativePtr);
        }

        @Override
        public long getReceivedBytes() {
            checkNotClosed();
            return getReceivedBytes0(nativePtr);
        }

        @Override
        public CefBasetime getStartTime() {
            checkNotClosed();
            return getStartTime0(nativePtr);
        }

        @Override
        public CefBasetime getEndTime() {
            checkNotClosed();
            return getEndTime0(nativePtr);
        }

        @Override
        public Optional<String> getFullPath() {
            checkNotClosed();
            return Optional.ofNullable(getFullPath0(nativePtr));
        }

        @Override
        public int getId() {
            checkNotClosed();
            return getId0(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            checkNotClosed();
            return Optional.ofNullable(getUrl0(nativePtr));
        }

        @Override
        public Optional<String> getOriginalUrl() {
            checkNotClosed();
            return Optional.ofNullable(getOriginalUrl0(nativePtr));
        }

        @Override
        public Optional<String> getSuggestedFileName() {
            checkNotClosed();
            return Optional.ofNullable(getSuggestedFileName0(nativePtr));
        }

        @Override
        public Optional<String> getContentDisposition() {
            checkNotClosed();
            return Optional.ofNullable(getContentDisposition0(nativePtr));
        }

        @Override
        public Optional<String> getMimeType() {
            checkNotClosed();
            return Optional.ofNullable(getMimeType0(nativePtr));
        }

        @Override
        public boolean isPaused() {
            checkNotClosed();
            return isPaused0(nativePtr);
        }

        private static native boolean isValid0(long self);

        private static native boolean isInProgress0(long self);

        private static native boolean isComplete0(long self);

        private static native boolean isCanceled0(long self);

        private static native boolean isInterrupted0(long self);

        private static native CefDownloadInterruptReason getInterruptReason0(long self);

        private static native long getCurrentSpeed0(long self);

        private static native int getPercentComplete0(long self);

        private static native long getTotalBytes0(long self);

        private static native long getReceivedBytes0(long self);

        private static native CefBasetime getStartTime0(long self);

        private static native CefBasetime getEndTime0(long self);

        private static native String getFullPath0(long self);

        private static native int getId0(long self);

        private static native String getUrl0(long self);

        private static native String getOriginalUrl0(long self);

        private static native String getSuggestedFileName0(long self);

        private static native String getContentDisposition0(long self);

        private static native String getMimeType0(long self);

        private static native boolean isPaused0(long self);

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
            return "CefDownloadItem{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
