// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Download interrupt reasons. Should be kept in sync with Chromium's download::DownloadInterruptReason type. */
public enum CefDownloadInterruptReason {
    CEF_DOWNLOAD_INTERRUPT_REASON_NONE(0L),
    /** Generic file operation failure. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_FAILED(1L),
    /** The file cannot be accessed due to security restrictions. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_ACCESS_DENIED(2L),
    /** There is not enough room on the drive. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NO_SPACE(3L),
    /** The directory or file name is too long. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NAME_TOO_LONG(5L),
    /** The file is too large for the file system to handle. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TOO_LARGE(6L),
    /** The file contains a virus. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_VIRUS_INFECTED(7L),
    /** The file was in use. Too many files are opened at once. We have run out of memory. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TRANSIENT_ERROR(10L),
    /** The file was blocked due to local policy. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_BLOCKED(11L),
    /** An attempt to check the safety of the download failed due to unexpected reasons. See http://crbug.com/153212. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_SECURITY_CHECK_FAILED(12L),
    /**
     * An attempt was made to seek past the end of a file in opening a file (as part of resuming a previously
     * interrupted download).
     */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TOO_SHORT(13L),
    /** The partial file didn't match the expected hash. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_HASH_MISMATCH(14L),
    /** The source and the target of the download were the same. */
    CEF_DOWNLOAD_INTERRUPT_REASON_FILE_SAME_AS_SOURCE(15L),
    /** Generic network failure. */
    CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_FAILED(20L),
    /** The network operation timed out. */
    CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_TIMEOUT(21L),
    /** The network connection has been lost. */
    CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_DISCONNECTED(22L),
    /** The server has gone down. */
    CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_SERVER_DOWN(23L),
    /**
     * The network request was invalid. This may be due to the original URL or a redirected URL: - Having an unsupported
     * scheme. - Being an invalid URL. - Being disallowed by policy.
     */
    CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_INVALID_REQUEST(24L),
    /** The server indicates that the operation has failed (generic). */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_FAILED(30L),
    /** The server does not support range requests. Internal use only: must restart from the beginning. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_NO_RANGE(31L),
    /** The server does not have the requested data. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_BAD_CONTENT(33L),
    /** Server didn't authorize access to resource. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_UNAUTHORIZED(34L),
    /** Server certificate problem. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CERT_PROBLEM(35L),
    /** Server access forbidden. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_FORBIDDEN(36L),
    /** Unexpected server response. This might indicate that the responding server may not be the intended server. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_UNREACHABLE(37L),
    /**
     * The server sent fewer bytes than the content-length header. It may indicate that the connection was closed
     * prematurely, or the Content-Length header was invalid. The download is only interrupted if strong validators are
     * present. Otherwise, it is treated as finished.
     */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CONTENT_LENGTH_MISMATCH(38L),
    /** An unexpected cross-origin redirect happened. */
    CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CROSS_ORIGIN_REDIRECT(39L),
    /** The user canceled the download. */
    CEF_DOWNLOAD_INTERRUPT_REASON_USER_CANCELED(40L),
    /** The user shut down the browser. Internal use only: resume pending downloads if possible. */
    CEF_DOWNLOAD_INTERRUPT_REASON_USER_SHUTDOWN(41L),
    /** The browser crashed. Internal use only: resume pending downloads if possible. */
    CEF_DOWNLOAD_INTERRUPT_REASON_CRASH(50L),
    UNKNOWN(-1L);

    public final long value;

    CefDownloadInterruptReason(long v) {
        this.value = v;
    }

    public static CefDownloadInterruptReason fromLong(long v) {
        for (CefDownloadInterruptReason e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
