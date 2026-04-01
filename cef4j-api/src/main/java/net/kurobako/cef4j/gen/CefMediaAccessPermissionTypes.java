// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Media access permissions used by OnRequestMediaAccessPermission.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_MEDIA_PERMISSION_NONE = 0,
 *   CEF_MEDIA_PERMISSION_DEVICE_AUDIO_CAPTURE = 1 << 0,
 *   CEF_MEDIA_PERMISSION_DEVICE_VIDEO_CAPTURE = 1 << 1,
 *   CEF_MEDIA_PERMISSION_DESKTOP_AUDIO_CAPTURE = 1 << 2,
 *   CEF_MEDIA_PERMISSION_DESKTOP_VIDEO_CAPTURE = 1 << 3
 * } cef_media_access_permission_types_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#DEVICE_AUDIO_CAPTURE}, {@link Kind#DEVICE_VIDEO_CAPTURE},
 * {@link Kind#DESKTOP_AUDIO_CAPTURE}, {@link Kind#DESKTOP_VIDEO_CAPTURE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefMediaAccessPermissionTypes implements CefEnum<CefMediaAccessPermissionTypes> {

    /** Known constants for {@link CefMediaAccessPermissionTypes}. */
    public enum Kind {
        /** No permission. */
        NONE(0, "0", "CEF_MEDIA_PERMISSION_NONE"),
        /** Device audio capture permission. */
        DEVICE_AUDIO_CAPTURE(1 << 0, "1 << 0", "CEF_MEDIA_PERMISSION_DEVICE_AUDIO_CAPTURE"),
        /** Device video capture permission. */
        DEVICE_VIDEO_CAPTURE(1 << 1, "1 << 1", "CEF_MEDIA_PERMISSION_DEVICE_VIDEO_CAPTURE"),
        /** Desktop audio capture permission. */
        DESKTOP_AUDIO_CAPTURE(1 << 2, "1 << 2", "CEF_MEDIA_PERMISSION_DESKTOP_AUDIO_CAPTURE"),
        /** Desktop video capture permission. */
        DESKTOP_VIDEO_CAPTURE(1 << 3, "1 << 3", "CEF_MEDIA_PERMISSION_DESKTOP_VIDEO_CAPTURE");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_media_access_permission_types_t"}). */
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

    private CefMediaAccessPermissionTypes(long value) {
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
    public static CefMediaAccessPermissionTypes of(long v) {
        return new CefMediaAccessPermissionTypes(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMediaAccessPermissionTypes of(Kind k) {
        return new CefMediaAccessPermissionTypes(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMediaAccessPermissionTypes)) return false;
        return this.value == ((CefMediaAccessPermissionTypes) obj).value;
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
