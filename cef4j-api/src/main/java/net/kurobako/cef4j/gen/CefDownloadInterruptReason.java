// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Download interrupt reasons. Should be kept in sync with Chromium's download::DownloadInterruptReason type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_DOWNLOAD_INTERRUPT_REASON_NONE = 0,
 *   CEF_DOWNLOAD_INTERRUPT_REASON_FILE_FAILED = 1,
 *   CEF_DOWNLOAD_INTERRUPT_REASON_FILE_ACCESS_DENIED = 2,
 *   CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NO_SPACE = 3,
 *   CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NAME_TOO_LONG = 5,
 *   ...
 * } cef_download_interrupt_reason_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#FILE_FAILED}, {@link Kind#FILE_ACCESS_DENIED},
 * {@link Kind#FILE_NO_SPACE}, {@link Kind#FILE_NAME_TOO_LONG}, {@link Kind#FILE_TOO_LARGE},
 * {@link Kind#FILE_VIRUS_INFECTED}, {@link Kind#FILE_TRANSIENT_ERROR}, {@link Kind#FILE_BLOCKED},
 * {@link Kind#FILE_SECURITY_CHECK_FAILED}, {@link Kind#FILE_TOO_SHORT}, {@link Kind#FILE_HASH_MISMATCH},
 * {@link Kind#FILE_SAME_AS_SOURCE}, {@link Kind#NETWORK_FAILED}, {@link Kind#NETWORK_TIMEOUT},
 * {@link Kind#NETWORK_DISCONNECTED}, {@link Kind#NETWORK_SERVER_DOWN}, {@link Kind#NETWORK_INVALID_REQUEST},
 * {@link Kind#SERVER_FAILED}, {@link Kind#SERVER_NO_RANGE}, {@link Kind#SERVER_BAD_CONTENT},
 * {@link Kind#SERVER_UNAUTHORIZED}, {@link Kind#SERVER_CERT_PROBLEM}, {@link Kind#SERVER_FORBIDDEN},
 * {@link Kind#SERVER_UNREACHABLE}, {@link Kind#SERVER_CONTENT_LENGTH_MISMATCH},
 * {@link Kind#SERVER_CROSS_ORIGIN_REDIRECT}, {@link Kind#USER_CANCELED}, {@link Kind#USER_SHUTDOWN}, {@link Kind#CRASH}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefDownloadInterruptReason implements CefEnum<CefDownloadInterruptReason> {

    /** Known constants for {@link CefDownloadInterruptReason}. */
    public enum Kind {
        NONE(0, "0", "CEF_DOWNLOAD_INTERRUPT_REASON_NONE"),
        /** Generic file operation failure. */
        FILE_FAILED(1, "1", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_FAILED"),
        /** The file cannot be accessed due to security restrictions. */
        FILE_ACCESS_DENIED(2, "2", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_ACCESS_DENIED"),
        /** There is not enough room on the drive. */
        FILE_NO_SPACE(3, "3", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NO_SPACE"),
        /** The directory or file name is too long. */
        FILE_NAME_TOO_LONG(5, "5", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NAME_TOO_LONG"),
        /** The file is too large for the file system to handle. */
        FILE_TOO_LARGE(6, "6", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TOO_LARGE"),
        /** The file contains a virus. */
        FILE_VIRUS_INFECTED(7, "7", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_VIRUS_INFECTED"),
        /** The file was in use. Too many files are opened at once. We have run out of memory. */
        FILE_TRANSIENT_ERROR(10, "10", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TRANSIENT_ERROR"),
        /** The file was blocked due to local policy. */
        FILE_BLOCKED(11, "11", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_BLOCKED"),
        /**
         * An attempt to check the safety of the download failed due to unexpected reasons. See <a
         * href="http://crbug.com/153212">http://crbug.com/153212</a>
         */
        FILE_SECURITY_CHECK_FAILED(12, "12", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_SECURITY_CHECK_FAILED"),
        /**
         * An attempt was made to seek past the end of a file in opening a file (as part of resuming a previously
         * interrupted download).
         */
        FILE_TOO_SHORT(13, "13", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TOO_SHORT"),
        /** The partial file didn't match the expected hash. */
        FILE_HASH_MISMATCH(14, "14", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_HASH_MISMATCH"),
        /** The source and the target of the download were the same. */
        FILE_SAME_AS_SOURCE(15, "15", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_SAME_AS_SOURCE"),
        /** Generic network failure. */
        NETWORK_FAILED(20, "20", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_FAILED"),
        /** The network operation timed out. */
        NETWORK_TIMEOUT(21, "21", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_TIMEOUT"),
        /** The network connection has been lost. */
        NETWORK_DISCONNECTED(22, "22", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_DISCONNECTED"),
        /** The server has gone down. */
        NETWORK_SERVER_DOWN(23, "23", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_SERVER_DOWN"),
        /**
         * The network request was invalid. This may be due to the original URL or a redirected URL: - Having an
         * unsupported scheme. - Being an invalid URL. - Being disallowed by policy.
         */
        NETWORK_INVALID_REQUEST(24, "24", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_INVALID_REQUEST"),
        /** The server indicates that the operation has failed (generic). */
        SERVER_FAILED(30, "30", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_FAILED"),
        /** The server does not support range requests. Internal use only: must restart from the beginning. */
        SERVER_NO_RANGE(31, "31", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_NO_RANGE"),
        /** The server does not have the requested data. */
        SERVER_BAD_CONTENT(33, "33", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_BAD_CONTENT"),
        /** Server didn't authorize access to resource. */
        SERVER_UNAUTHORIZED(34, "34", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_UNAUTHORIZED"),
        /** Server certificate problem. */
        SERVER_CERT_PROBLEM(35, "35", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CERT_PROBLEM"),
        /** Server access forbidden. */
        SERVER_FORBIDDEN(36, "36", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_FORBIDDEN"),
        /**
         * Unexpected server response. This might indicate that the responding server may not be the intended server.
         */
        SERVER_UNREACHABLE(37, "37", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_UNREACHABLE"),
        /**
         * The server sent fewer bytes than the content-length header. It may indicate that the connection was closed
         * prematurely, or the Content-Length header was invalid. The download is only interrupted if strong validators
         * are present. Otherwise, it is treated as finished.
         */
        SERVER_CONTENT_LENGTH_MISMATCH(38, "38", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CONTENT_LENGTH_MISMATCH"),
        /** An unexpected cross-origin redirect happened. */
        SERVER_CROSS_ORIGIN_REDIRECT(39, "39", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CROSS_ORIGIN_REDIRECT"),
        /** The user canceled the download. */
        USER_CANCELED(40, "40", "CEF_DOWNLOAD_INTERRUPT_REASON_USER_CANCELED"),
        /** The user shut down the browser. Internal use only: resume pending downloads if possible. */
        USER_SHUTDOWN(41, "41", "CEF_DOWNLOAD_INTERRUPT_REASON_USER_SHUTDOWN"),
        /** The browser crashed. Internal use only: resume pending downloads if possible. */
        CRASH(50, "50", "CEF_DOWNLOAD_INTERRUPT_REASON_CRASH");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_download_interrupt_reason_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
    public final long value;

    private CefDownloadInterruptReason(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value. No data is lost — unknown or composite values are preserved. */
    public static CefDownloadInterruptReason of(long v) {
        return new CefDownloadInterruptReason(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDownloadInterruptReason of(Kind k) {
        return new CefDownloadInterruptReason(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDownloadInterruptReason)) return false;
        return this.value == ((CefDownloadInterruptReason) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
