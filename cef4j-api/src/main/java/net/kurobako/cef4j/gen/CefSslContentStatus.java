// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported SSL content status flags. See content/public/common/ssl_status.h for more information. */
public enum CefSslContentStatus {
    SSL_CONTENT_NORMAL_CONTENT(0L),
    SSL_CONTENT_DISPLAYED_INSECURE_CONTENT(1L),
    SSL_CONTENT_RAN_INSECURE_CONTENT(2L),
    UNKNOWN(-1L);

    public final long value;

    CefSslContentStatus(long v) {
        this.value = v;
    }

    public static CefSslContentStatus fromLong(long v) {
        for (CefSslContentStatus e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
