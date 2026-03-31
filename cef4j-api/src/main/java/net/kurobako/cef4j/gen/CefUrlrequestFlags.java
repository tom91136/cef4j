// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Flags used to customize the behavior of CefURLRequest. */
public enum CefUrlrequestFlags {

    /** Default behavior. */
    UR_FLAG_NONE(0L),
    /**
     * If set the cache will be skipped when handling the request. Setting this value is equivalent to specifying the
     * "Cache-Control: no-cache" request header. Setting this value in combination with UR_FLAG_ONLY_FROM_CACHE will
     * cause the request to fail.
     */
    UR_FLAG_SKIP_CACHE(1L),
    /**
     * If set the request will fail if it cannot be served from the cache (or some equivalent local store). Setting this
     * value is equivalent to specifying the "Cache-Control: only-if-cached" request header. Setting this value in
     * combination with UR_FLAG_SKIP_CACHE or UR_FLAG_DISABLE_CACHE will cause the request to fail.
     */
    UR_FLAG_ONLY_FROM_CACHE(2L),
    /**
     * If set the cache will not be used at all. Setting this value is equivalent to specifying the "Cache-Control:
     * no-store" request header. Setting this value in combination with UR_FLAG_ONLY_FROM_CACHE will cause the request
     * to fail.
     */
    UR_FLAG_DISABLE_CACHE(4L),
    /**
     * If set user name, password, and cookies may be sent with the request, and cookies may be saved from the response.
     */
    UR_FLAG_ALLOW_STORED_CREDENTIALS(8L),
    /** If set upload progress events will be generated when a request has a body. */
    UR_FLAG_REPORT_UPLOAD_PROGRESS(16L),
    /** If set the CefURLRequestClient::OnDownloadData method will not be called. */
    UR_FLAG_NO_DOWNLOAD_DATA(32L),
    /**
     * If set 5XX redirect errors will be propagated to the observer instead of automatically re-tried. This currently
     * only applies for requests originated in the browser process.
     */
    UR_FLAG_NO_RETRY_ON_5XX(64L),
    /** If set 3XX responses will cause the fetch to halt immediately rather than continue through the redirect. */
    UR_FLAG_STOP_ON_REDIRECT(128L),
    UNKNOWN(-1L);

    public final long value;

    CefUrlrequestFlags(long v) {
        this.value = v;
    }

    public static CefUrlrequestFlags fromLong(long v) {
        for (CefUrlrequestFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
