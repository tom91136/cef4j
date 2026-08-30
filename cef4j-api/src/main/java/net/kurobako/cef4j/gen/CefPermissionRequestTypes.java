// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Permission types used with OnShowPermissionPrompt. Some types are platform-specific or only supported with Chrome
 * style. Should be kept in sync with Chromium's permissions::RequestType type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_PERMISSION_TYPE_NONE = 0,
 *   CEF_PERMISSION_TYPE_AR_SESSION = 1 &lt;&lt; 0,
 *   CEF_PERMISSION_TYPE_CAMERA_PAN_TILT_ZOOM = 1 &lt;&lt; 1,
 *   CEF_PERMISSION_TYPE_CAMERA_STREAM = 1 &lt;&lt; 2,
 *   CEF_PERMISSION_TYPE_CAPTURED_SURFACE_CONTROL = 1 &lt;&lt; 3,
 *   ...
 * } cef_permission_request_types_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#AR_SESSION}, {@link Kind#CAMERA_PAN_TILT_ZOOM},
 * {@link Kind#CAMERA_STREAM}, {@link Kind#CAPTURED_SURFACE_CONTROL}, {@link Kind#CLIPBOARD},
 * {@link Kind#TOP_LEVEL_STORAGE_ACCESS}, {@link Kind#DISK_QUOTA}, {@link Kind#LOCAL_FONTS}, {@link Kind#GEOLOCATION},
 * {@link Kind#HAND_TRACKING}, {@link Kind#IDENTITY_PROVIDER}, {@link Kind#IDLE_DETECTION}, {@link Kind#MIC_STREAM},
 * {@link Kind#MIDI_SYSEX}, {@link Kind#MULTIPLE_DOWNLOADS}, {@link Kind#NOTIFICATIONS}, {@link Kind#KEYBOARD_LOCK},
 * {@link Kind#POINTER_LOCK}, {@link Kind#PROTECTED_MEDIA_IDENTIFIER}, {@link Kind#REGISTER_PROTOCOL_HANDLER},
 * {@link Kind#STORAGE_ACCESS}, {@link Kind#VR_SESSION}, {@link Kind#WEB_APP_INSTALLATION},
 * {@link Kind#WINDOW_MANAGEMENT}, {@link Kind#FILE_SYSTEM_ACCESS}, {@link Kind#LOCAL_NETWORK_ACCESS_DEPRECATED},
 * {@link Kind#LOCAL_NETWORK}, {@link Kind#LOOPBACK_NETWORK}, {@link Kind#SENSORS}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefPermissionRequestTypes implements CefEnum<CefPermissionRequestTypes> {

    /** Known constants for {@link CefPermissionRequestTypes}. */
    public enum Kind {
        NONE(0, "0", "CEF_PERMISSION_TYPE_NONE"),
        AR_SESSION(1 << 0, "1 << 0", "CEF_PERMISSION_TYPE_AR_SESSION"),
        CAMERA_PAN_TILT_ZOOM(1 << 1, "1 << 1", "CEF_PERMISSION_TYPE_CAMERA_PAN_TILT_ZOOM"),
        CAMERA_STREAM(1 << 2, "1 << 2", "CEF_PERMISSION_TYPE_CAMERA_STREAM"),
        CAPTURED_SURFACE_CONTROL(1 << 3, "1 << 3", "CEF_PERMISSION_TYPE_CAPTURED_SURFACE_CONTROL"),
        CLIPBOARD(1 << 4, "1 << 4", "CEF_PERMISSION_TYPE_CLIPBOARD"),
        TOP_LEVEL_STORAGE_ACCESS(1 << 5, "1 << 5", "CEF_PERMISSION_TYPE_TOP_LEVEL_STORAGE_ACCESS"),
        DISK_QUOTA(1 << 6, "1 << 6", "CEF_PERMISSION_TYPE_DISK_QUOTA"),
        LOCAL_FONTS(1 << 7, "1 << 7", "CEF_PERMISSION_TYPE_LOCAL_FONTS"),
        GEOLOCATION(1 << 8, "1 << 8", "CEF_PERMISSION_TYPE_GEOLOCATION"),
        HAND_TRACKING(1 << 9, "1 << 9", "CEF_PERMISSION_TYPE_HAND_TRACKING"),
        IDENTITY_PROVIDER(1 << 10, "1 << 10", "CEF_PERMISSION_TYPE_IDENTITY_PROVIDER"),
        IDLE_DETECTION(1 << 11, "1 << 11", "CEF_PERMISSION_TYPE_IDLE_DETECTION"),
        MIC_STREAM(1 << 12, "1 << 12", "CEF_PERMISSION_TYPE_MIC_STREAM"),
        MIDI_SYSEX(1 << 13, "1 << 13", "CEF_PERMISSION_TYPE_MIDI_SYSEX"),
        MULTIPLE_DOWNLOADS(1 << 14, "1 << 14", "CEF_PERMISSION_TYPE_MULTIPLE_DOWNLOADS"),
        NOTIFICATIONS(1 << 15, "1 << 15", "CEF_PERMISSION_TYPE_NOTIFICATIONS"),
        KEYBOARD_LOCK(1 << 16, "1 << 16", "CEF_PERMISSION_TYPE_KEYBOARD_LOCK"),
        POINTER_LOCK(1 << 17, "1 << 17", "CEF_PERMISSION_TYPE_POINTER_LOCK"),
        PROTECTED_MEDIA_IDENTIFIER(1 << 18, "1 << 18", "CEF_PERMISSION_TYPE_PROTECTED_MEDIA_IDENTIFIER"),
        REGISTER_PROTOCOL_HANDLER(1 << 19, "1 << 19", "CEF_PERMISSION_TYPE_REGISTER_PROTOCOL_HANDLER"),
        STORAGE_ACCESS(1 << 20, "1 << 20", "CEF_PERMISSION_TYPE_STORAGE_ACCESS"),
        VR_SESSION(1 << 21, "1 << 21", "CEF_PERMISSION_TYPE_VR_SESSION"),
        WEB_APP_INSTALLATION(1 << 22, "1 << 22", "CEF_PERMISSION_TYPE_WEB_APP_INSTALLATION"),
        WINDOW_MANAGEMENT(1 << 23, "1 << 23", "CEF_PERMISSION_TYPE_WINDOW_MANAGEMENT"),
        FILE_SYSTEM_ACCESS(1 << 24, "1 << 24", "CEF_PERMISSION_TYPE_FILE_SYSTEM_ACCESS"),
        LOCAL_NETWORK_ACCESS_DEPRECATED(1 << 25, "1 << 25", "CEF_PERMISSION_TYPE_LOCAL_NETWORK_ACCESS_DEPRECATED"),
        LOCAL_NETWORK(1 << 26, "1 << 26", "CEF_PERMISSION_TYPE_LOCAL_NETWORK"),
        LOOPBACK_NETWORK(1 << 27, "1 << 27", "CEF_PERMISSION_TYPE_LOOPBACK_NETWORK"),
        SENSORS(1 << 28, "1 << 28", "CEF_PERMISSION_TYPE_SENSORS");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_permission_request_types_t"}). */
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

    private CefPermissionRequestTypes(long value) {
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
    public static CefPermissionRequestTypes of(long v) {
        return new CefPermissionRequestTypes(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPermissionRequestTypes of(Kind k) {
        return new CefPermissionRequestTypes(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPermissionRequestTypes)) return false;
        return this.value == ((CefPermissionRequestTypes) obj).value;
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
