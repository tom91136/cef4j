// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/** Class used to represent a download item. */
public interface CefDownloadItem {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns true if the download is in progress. */
    boolean isInProgress();

    /** Returns true if the download is complete. */
    boolean isComplete();

    /** Returns true if the download has been canceled. */
    boolean isCanceled();

    /** Returns true if the download has been interrupted. */
    boolean isInterrupted();

    /**
     * Returns the most recent interrupt reason.
     *
     * @return the result, or {@code CEF_DOWNLOAD_INTERRUPT_REASON_NONE} for default handling
     */
    CefDownloadInterruptReason getInterruptReason();

    /** Returns a simple speed estimate in bytes/s. */
    long getCurrentSpeed();

    /** Returns the rough percent complete or -1 if the receive total size is unknown. */
    int getPercentComplete();

    /** Returns the total number of bytes. */
    long getTotalBytes();

    /** Returns the number of received bytes. */
    long getReceivedBytes();

    /** Returns the time that the download started. */
    CefBasetime getStartTime();

    /** Returns the time that the download ended. */
    CefBasetime getEndTime();

    /** Returns the full path to the downloaded or downloading file. */
    Optional<String> getFullPath();

    /** Returns the unique identifier for this download. */
    int getId();

    Optional<String> getUrl();

    /** Returns the original URL before any redirections. */
    Optional<String> getOriginalUrl();

    /** Returns the suggested file name. */
    Optional<String> getSuggestedFileName();

    /** Returns the content disposition. */
    Optional<String> getContentDisposition();

    /** Returns the mime type. */
    Optional<String> getMimeType();

    /**
     * Returns true if the download has been paused.
     *
     * <p>Added in CEF API version 14400.
     */
    boolean isPaused();

    static class NativePeer implements CefDownloadItem {
        private volatile long nativePtr;

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

        private native boolean N_IsValid(long self);

        private native boolean N_IsInProgress(long self);

        private native boolean N_IsComplete(long self);

        private native boolean N_IsCanceled(long self);

        private native boolean N_IsInterrupted(long self);

        private native CefDownloadInterruptReason N_GetInterruptReason(long self);

        private native long N_GetCurrentSpeed(long self);

        private native int N_GetPercentComplete(long self);

        private native long N_GetTotalBytes(long self);

        private native long N_GetReceivedBytes(long self);

        private native CefBasetime N_GetStartTime(long self);

        private native CefBasetime N_GetEndTime(long self);

        private native String N_GetFullPath(long self);

        private native int N_GetId(long self);

        private native String N_GetUrl(long self);

        private native String N_GetOriginalUrl(long self);

        private native String N_GetSuggestedFileName(long self);

        private native String N_GetContentDisposition(long self);

        private native String N_GetMimeType(long self);

        private native boolean N_IsPaused(long self);

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
