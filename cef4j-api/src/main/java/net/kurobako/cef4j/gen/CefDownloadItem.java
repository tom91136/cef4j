// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

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

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isInProgress() {
            return N_IsInProgress(nativePtr);
        }

        @Override
        public boolean isComplete() {
            return N_IsComplete(nativePtr);
        }

        @Override
        public boolean isCanceled() {
            return N_IsCanceled(nativePtr);
        }

        @Override
        public boolean isInterrupted() {
            return N_IsInterrupted(nativePtr);
        }

        @Override
        public CefDownloadInterruptReason getInterruptReason() {
            return N_GetInterruptReason(nativePtr);
        }

        @Override
        public long getCurrentSpeed() {
            return N_GetCurrentSpeed(nativePtr);
        }

        @Override
        public int getPercentComplete() {
            return N_GetPercentComplete(nativePtr);
        }

        @Override
        public long getTotalBytes() {
            return N_GetTotalBytes(nativePtr);
        }

        @Override
        public long getReceivedBytes() {
            return N_GetReceivedBytes(nativePtr);
        }

        @Override
        public CefBasetime getStartTime() {
            return N_GetStartTime(nativePtr);
        }

        @Override
        public CefBasetime getEndTime() {
            return N_GetEndTime(nativePtr);
        }

        @Override
        public Optional<String> getFullPath() {
            return Optional.ofNullable(N_GetFullPath(nativePtr));
        }

        @Override
        public int getId() {
            return N_GetId(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public Optional<String> getOriginalUrl() {
            return Optional.ofNullable(N_GetOriginalUrl(nativePtr));
        }

        @Override
        public Optional<String> getSuggestedFileName() {
            return Optional.ofNullable(N_GetSuggestedFileName(nativePtr));
        }

        @Override
        public Optional<String> getContentDisposition() {
            return Optional.ofNullable(N_GetContentDisposition(nativePtr));
        }

        @Override
        public Optional<String> getMimeType() {
            return Optional.ofNullable(N_GetMimeType(nativePtr));
        }

        @Override
        public boolean isPaused() {
            return N_IsPaused(nativePtr);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsInProgress(long self);

        private static native boolean N_IsComplete(long self);

        private static native boolean N_IsCanceled(long self);

        private static native boolean N_IsInterrupted(long self);

        private static native CefDownloadInterruptReason N_GetInterruptReason(long self);

        private static native long N_GetCurrentSpeed(long self);

        private static native int N_GetPercentComplete(long self);

        private static native long N_GetTotalBytes(long self);

        private static native long N_GetReceivedBytes(long self);

        private static native CefBasetime N_GetStartTime(long self);

        private static native CefBasetime N_GetEndTime(long self);

        private static native String N_GetFullPath(long self);

        private static native int N_GetId(long self);

        private static native String N_GetUrl(long self);

        private static native String N_GetOriginalUrl(long self);

        private static native String N_GetSuggestedFileName(long self);

        private static native String N_GetContentDisposition(long self);

        private static native String N_GetMimeType(long self);

        private static native boolean N_IsPaused(long self);

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
