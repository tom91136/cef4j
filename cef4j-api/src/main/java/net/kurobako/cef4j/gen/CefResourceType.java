// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Resource type for a request. These constants match their equivalents in Chromium's ResourceType and should not be
 * renumbered.
 */
public enum CefResourceType {

    /** Top level page. */
    RT_MAIN_FRAME(0L),
    /** Frame or iframe. */
    RT_SUB_FRAME(1L),
    /** CSS stylesheet. */
    RT_STYLESHEET(2L),
    /** External script. */
    RT_SCRIPT(3L),
    /** Image (jpg/gif/png/etc). */
    RT_IMAGE(4L),
    /** Font. */
    RT_FONT_RESOURCE(5L),
    /** Some other subresource. This is the default type if the actual type is unknown. */
    RT_SUB_RESOURCE(6L),
    /** Object (or embed) tag for a plugin, or a resource that a plugin requested. */
    RT_OBJECT(7L),
    /** Media resource. */
    RT_MEDIA(8L),
    /** Main resource of a dedicated worker. */
    RT_WORKER(9L),
    /** Main resource of a shared worker. */
    RT_SHARED_WORKER(10L),
    /** Explicitly requested prefetch. */
    RT_PREFETCH(11L),
    /** Favicon. */
    RT_FAVICON(12L),
    /** XMLHttpRequest. */
    RT_XHR(13L),
    /** A request for a "<ping>". */
    RT_PING(14L),
    /** Main resource of a service worker. */
    RT_SERVICE_WORKER(15L),
    /** A report of Content Security Policy violations. */
    RT_CSP_REPORT(16L),
    /** A resource that a plugin requested. */
    RT_PLUGIN_RESOURCE(17L),
    /** A main-frame service worker navigation preload request. */
    RT_NAVIGATION_PRELOAD_MAIN_FRAME(19L),
    /** A sub-frame service worker navigation preload request. */
    RT_NAVIGATION_PRELOAD_SUB_FRAME(20L),
    RT_NUM_VALUES(21L),
    UNKNOWN(-1L);

    public final long value;

    CefResourceType(long v) {
        this.value = v;
    }

    public static CefResourceType fromLong(long v) {
        for (CefResourceType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
