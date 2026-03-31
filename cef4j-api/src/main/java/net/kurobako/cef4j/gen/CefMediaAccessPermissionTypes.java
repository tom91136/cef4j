// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Media access permissions used by OnRequestMediaAccessPermission. */
public enum CefMediaAccessPermissionTypes {

    /** No permission. */
    CEF_MEDIA_PERMISSION_NONE(0L),
    /** Device audio capture permission. */
    CEF_MEDIA_PERMISSION_DEVICE_AUDIO_CAPTURE(1L),
    /** Device video capture permission. */
    CEF_MEDIA_PERMISSION_DEVICE_VIDEO_CAPTURE(2L),
    /** Desktop audio capture permission. */
    CEF_MEDIA_PERMISSION_DESKTOP_AUDIO_CAPTURE(4L),
    /** Desktop video capture permission. */
    CEF_MEDIA_PERMISSION_DESKTOP_VIDEO_CAPTURE(8L),
    UNKNOWN(-1L);

    public final long value;

    CefMediaAccessPermissionTypes(long v) {
        this.value = v;
    }

    public static CefMediaAccessPermissionTypes fromLong(long v) {
        for (CefMediaAccessPermissionTypes e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
