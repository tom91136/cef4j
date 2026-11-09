// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefDownloadInterruptReason implements CefEnum<CefDownloadInterruptReason> {

    /** Known constants for {@link CefDownloadInterruptReason}. */
    public enum Kind {
        NONE(new EnumConstant(0, "0", "CEF_DOWNLOAD_INTERRUPT_REASON_NONE")),
        /** Generic file operation failure. */
        FILE_FAILED(new EnumConstant(1, "1", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_FAILED")),
        /** The file cannot be accessed due to security restrictions. */
        FILE_ACCESS_DENIED(new EnumConstant(2, "2", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_ACCESS_DENIED")),
        /** There is not enough room on the drive. */
        FILE_NO_SPACE(new EnumConstant(3, "3", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NO_SPACE")),
        /** The directory or file name is too long. */
        FILE_NAME_TOO_LONG(new EnumConstant(5, "5", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_NAME_TOO_LONG")),
        /** The file is too large for the file system to handle. */
        FILE_TOO_LARGE(new EnumConstant(6, "6", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TOO_LARGE")),
        /** The file contains a virus. */
        FILE_VIRUS_INFECTED(new EnumConstant(7, "7", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_VIRUS_INFECTED")),
        /** The file was in use. Too many files are opened at once. We have run out of memory. */
        FILE_TRANSIENT_ERROR(new EnumConstant(10, "10", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TRANSIENT_ERROR")),
        /** The file was blocked due to local policy. */
        FILE_BLOCKED(new EnumConstant(11, "11", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_BLOCKED")),
        /**
         * An attempt to check the safety of the download failed due to unexpected reasons. See <a
         * href="http://crbug.com/153212">http://crbug.com/153212</a>.
         */
        FILE_SECURITY_CHECK_FAILED(
                new EnumConstant(12, "12", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_SECURITY_CHECK_FAILED")),
        /**
         * An attempt was made to seek past the end of a file in opening a file (as part of resuming a previously
         * interrupted download).
         */
        FILE_TOO_SHORT(new EnumConstant(13, "13", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_TOO_SHORT")),
        /** The partial file didn't match the expected hash. */
        FILE_HASH_MISMATCH(new EnumConstant(14, "14", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_HASH_MISMATCH")),
        /** The source and the target of the download were the same. */
        FILE_SAME_AS_SOURCE(new EnumConstant(15, "15", "CEF_DOWNLOAD_INTERRUPT_REASON_FILE_SAME_AS_SOURCE")),
        /** Generic network failure. */
        NETWORK_FAILED(new EnumConstant(20, "20", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_FAILED")),
        /** The network operation timed out. */
        NETWORK_TIMEOUT(new EnumConstant(21, "21", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_TIMEOUT")),
        /** The network connection has been lost. */
        NETWORK_DISCONNECTED(new EnumConstant(22, "22", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_DISCONNECTED")),
        /** The server has gone down. */
        NETWORK_SERVER_DOWN(new EnumConstant(23, "23", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_SERVER_DOWN")),
        /**
         * The network request was invalid. This may be due to the original URL or a redirected URL: - Having an
         * unsupported scheme. - Being an invalid URL. - Being disallowed by policy.
         */
        NETWORK_INVALID_REQUEST(new EnumConstant(24, "24", "CEF_DOWNLOAD_INTERRUPT_REASON_NETWORK_INVALID_REQUEST")),
        /** The server indicates that the operation has failed (generic). */
        SERVER_FAILED(new EnumConstant(30, "30", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_FAILED")),
        /** The server does not support range requests. Internal use only: must restart from the beginning. */
        SERVER_NO_RANGE(new EnumConstant(31, "31", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_NO_RANGE")),
        /** The server does not have the requested data. */
        SERVER_BAD_CONTENT(new EnumConstant(33, "33", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_BAD_CONTENT")),
        /** Server didn't authorize access to resource. */
        SERVER_UNAUTHORIZED(new EnumConstant(34, "34", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_UNAUTHORIZED")),
        /** Server certificate problem. */
        SERVER_CERT_PROBLEM(new EnumConstant(35, "35", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CERT_PROBLEM")),
        /** Server access forbidden. */
        SERVER_FORBIDDEN(new EnumConstant(36, "36", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_FORBIDDEN")),
        /**
         * Unexpected server response. This might indicate that the responding server may not be the intended server.
         */
        SERVER_UNREACHABLE(new EnumConstant(37, "37", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_UNREACHABLE")),
        /**
         * The server sent fewer bytes than the content-length header. It may indicate that the connection was closed
         * prematurely, or the Content-Length header was invalid. The download is only interrupted if strong validators
         * are present. Otherwise, it is treated as finished.
         */
        SERVER_CONTENT_LENGTH_MISMATCH(
                new EnumConstant(38, "38", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CONTENT_LENGTH_MISMATCH")),
        /** An unexpected cross-origin redirect happened. */
        SERVER_CROSS_ORIGIN_REDIRECT(
                new EnumConstant(39, "39", "CEF_DOWNLOAD_INTERRUPT_REASON_SERVER_CROSS_ORIGIN_REDIRECT")),
        /** The user canceled the download. */
        USER_CANCELED(new EnumConstant(40, "40", "CEF_DOWNLOAD_INTERRUPT_REASON_USER_CANCELED")),
        /** The user shut down the browser. Internal use only: resume pending downloads if possible. */
        USER_SHUTDOWN(new EnumConstant(41, "41", "CEF_DOWNLOAD_INTERRUPT_REASON_USER_SHUTDOWN")),
        /** The browser crashed. Internal use only: resume pending downloads if possible. */
        CRASH(new EnumConstant(50, "50", "CEF_DOWNLOAD_INTERRUPT_REASON_CRASH"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_download_interrupt_reason_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefDownloadInterruptReason(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefDownloadInterruptReason of(long v) {
        return new CefDownloadInterruptReason(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDownloadInterruptReason of(Kind k) {
        return new CefDownloadInterruptReason(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
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
