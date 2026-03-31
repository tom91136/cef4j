// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Specifies the task type variants supported by CefTaskManager. Should be kept in sync with Chromium's
 * task_manager::Task::Type type.
 */
public enum CefTaskType {
    CEF_TASK_TYPE_UNKNOWN(0L),
    /** The main browser process. */
    CEF_TASK_TYPE_BROWSER(1L),
    /** A graphics process. */
    CEF_TASK_TYPE_GPU(2L),
    /** A Linux zygote process. */
    CEF_TASK_TYPE_ZYGOTE(3L),
    /** A browser utility process. */
    CEF_TASK_TYPE_UTILITY(4L),
    /** A normal WebContents renderer process. */
    CEF_TASK_TYPE_RENDERER(5L),
    /** An extension or app process. */
    CEF_TASK_TYPE_EXTENSION(6L),
    /** A browser plugin guest process. */
    CEF_TASK_TYPE_GUEST(7L),
    CEF_TASK_TYPE_PLUGIN_DEPRECATED(8L),
    /** A sandbox helper process */
    CEF_TASK_TYPE_SANDBOX_HELPER(9L),
    /** A dedicated worker running on the renderer process. */
    CEF_TASK_TYPE_DEDICATED_WORKER(10L),
    /** A shared worker running on the renderer process. */
    CEF_TASK_TYPE_SHARED_WORKER(11L),
    /** A service worker running on the renderer process. */
    CEF_TASK_TYPE_SERVICE_WORKER(12L),
    CEF_TASK_TYPE_NUM_VALUES(13L),
    UNKNOWN(-1L);

    public final long value;

    CefTaskType(long v) {
        this.value = v;
    }

    public static CefTaskType fromLong(long v) {
        for (CefTaskType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
