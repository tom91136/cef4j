// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Flags used to customize the behavior of CefURLRequest.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   UR_FLAG_NONE = 0,
 *   UR_FLAG_SKIP_CACHE = 1 &lt;&lt; 0,
 *   UR_FLAG_ONLY_FROM_CACHE = 1 &lt;&lt; 1,
 *   UR_FLAG_DISABLE_CACHE = 1 &lt;&lt; 2,
 *   UR_FLAG_ALLOW_STORED_CREDENTIALS = 1 &lt;&lt; 3,
 *   ...
 * } cef_urlrequest_flags_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#SKIP_CACHE}, {@link Kind#ONLY_FROM_CACHE},
 * {@link Kind#DISABLE_CACHE}, {@link Kind#ALLOW_STORED_CREDENTIALS}, {@link Kind#REPORT_UPLOAD_PROGRESS},
 * {@link Kind#NO_DOWNLOAD_DATA}, {@link Kind#NO_RETRY_ON_5XX}, {@link Kind#STOP_ON_REDIRECT}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefUrlRequestFlags implements CefEnum<CefUrlRequestFlags> {

    /** Known constants for {@link CefUrlRequestFlags}. */
    public enum Kind {
        /** Default behavior. */
        NONE(0, "0", "UR_FLAG_NONE"),
        /**
         * If set the cache will be skipped when handling the request. Setting this value is equivalent to specifying
         * the "Cache-Control: no-cache" request header. Setting this value in combination with UR_FLAG_ONLY_FROM_CACHE
         * will cause the request to fail.
         */
        SKIP_CACHE(1 << 0, "1 << 0", "UR_FLAG_SKIP_CACHE"),
        /**
         * If set the request will fail if it cannot be served from the cache (or some equivalent local store). Setting
         * this value is equivalent to specifying the "Cache-Control: only-if-cached" request header. Setting this value
         * in combination with UR_FLAG_SKIP_CACHE or UR_FLAG_DISABLE_CACHE will cause the request to fail.
         */
        ONLY_FROM_CACHE(1 << 1, "1 << 1", "UR_FLAG_ONLY_FROM_CACHE"),
        /**
         * If set the cache will not be used at all. Setting this value is equivalent to specifying the "Cache-Control:
         * no-store" request header. Setting this value in combination with UR_FLAG_ONLY_FROM_CACHE will cause the
         * request to fail.
         */
        DISABLE_CACHE(1 << 2, "1 << 2", "UR_FLAG_DISABLE_CACHE"),
        /**
         * If set user name, password, and cookies may be sent with the request, and cookies may be saved from the
         * response.
         */
        ALLOW_STORED_CREDENTIALS(1 << 3, "1 << 3", "UR_FLAG_ALLOW_STORED_CREDENTIALS"),
        /** If set upload progress events will be generated when a request has a body. */
        REPORT_UPLOAD_PROGRESS(1 << 4, "1 << 4", "UR_FLAG_REPORT_UPLOAD_PROGRESS"),
        /** If set the CefURLRequestClient.onDownloadData() method will not be called. */
        NO_DOWNLOAD_DATA(1 << 5, "1 << 5", "UR_FLAG_NO_DOWNLOAD_DATA"),
        /**
         * If set 5XX redirect errors will be propagated to the observer instead of automatically re-tried. This
         * currently only applies for requests originated in the browser process.
         */
        NO_RETRY_ON_5XX(1 << 6, "1 << 6", "UR_FLAG_NO_RETRY_ON_5XX"),
        /** If set 3XX responses will cause the fetch to halt immediately rather than continue through the redirect. */
        STOP_ON_REDIRECT(1 << 7, "1 << 7", "UR_FLAG_STOP_ON_REDIRECT");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_urlrequest_flags_t"}). */
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

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefUrlRequestFlags(long value) {
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
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
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

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefUrlRequestFlags of(long v) {
        return new CefUrlRequestFlags(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefUrlRequestFlags of(Kind k) {
        return new CefUrlRequestFlags(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefUrlRequestFlags)) return false;
        return this.value == ((CefUrlRequestFlags) obj).value;
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
