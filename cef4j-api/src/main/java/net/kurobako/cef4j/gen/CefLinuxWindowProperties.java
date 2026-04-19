// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Linux window properties, such as X11's WM_CLASS or Wayland's app_id. Those are passed to CefWindowDelegate, so the client can set them for the CefWindow's top-level. Thus, allowing window managers to correctly display the application's information (e.g., icons).
 * <p>Definition generated from internal/cef_types.h
 * <pre>typedef struct _cef_linux_window_properties_t {
 *   size_t size;
 *   cef_string_t* wayland_app_id;
 *   cef_string_t* wm_class_class;
 *   cef_string_t* wm_class_name;
 *   cef_string_t* wm_role_name;
 * } cef_linux_window_properties_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:1964</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefLinuxWindowProperties {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * Main window's Wayland's app_id 
         */    public final @Nullable String waylandAppId;
        /**
         * Main window's WM_CLASS_CLASS in X11 
         */    public final @Nullable String wmClassClass;
        /**
         * Main window's WM_CLASS_NAME in X11 
         */    public final @Nullable String wmClassName;
        /**
         * Main window's WM_WINDOW_ROLE in X11 
         */    public final @Nullable String wmRoleName;

    public CefLinuxWindowProperties(@Nullable String waylandAppId, @Nullable String wmClassClass, @Nullable String wmClassName, @Nullable String wmRoleName) {
        this.waylandAppId = waylandAppId;
        this.wmClassClass = wmClassClass;
        this.wmClassName = wmClassName;
        this.wmRoleName = wmRoleName;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.waylandAppId, this.wmClassClass, this.wmClassName, this.wmRoleName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefLinuxWindowProperties)) return false;
        CefLinuxWindowProperties other = (CefLinuxWindowProperties) obj;
        return java.util.Objects.equals(this.waylandAppId, other.waylandAppId)
                    && java.util.Objects.equals(this.wmClassClass, other.wmClassClass)
                    && java.util.Objects.equals(this.wmClassName, other.wmClassName)
                    && java.util.Objects.equals(this.wmRoleName, other.wmRoleName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(waylandAppId, wmClassClass, wmClassName, wmRoleName);
    }

    @Override
    public String toString() {
        return "CefLinuxWindowProperties{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "waylandAppId=" + waylandAppId + ", " + "wmClassClass=" + wmClassClass + ", " + "wmClassName=" + wmClassName + ", " + "wmRoleName=" + wmRoleName + "}";
    }

    /**
     * Mutable variant of {@link CefLinuxWindowProperties}. Linux window properties, such as X11's WM_CLASS or Wayland's app_id. Those are passed to CefWindowDelegate, so the client can set them for the CefWindow's top-level. Thus, allowing window managers to correctly display the application's information (e.g., icons).
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:1964</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * Main window's Wayland's app_id 
             */        public @Nullable String waylandAppId;
            /**
             * Main window's WM_CLASS_CLASS in X11 
             */        public @Nullable String wmClassClass;
            /**
             * Main window's WM_CLASS_NAME in X11 
             */        public @Nullable String wmClassName;
            /**
             * Main window's WM_WINDOW_ROLE in X11 
             */        public @Nullable String wmRoleName;

        public Mutable() {}

        public Mutable(@Nullable String waylandAppId, @Nullable String wmClassClass, @Nullable String wmClassName, @Nullable String wmRoleName) {
            this.waylandAppId = waylandAppId;
            this.wmClassClass = wmClassClass;
            this.wmClassName = wmClassName;
            this.wmRoleName = wmRoleName;
        }

        /** Create an immutable snapshot of this instance. */
        public CefLinuxWindowProperties toImmutable() {
            return new CefLinuxWindowProperties(this.waylandAppId, this.wmClassClass, this.wmClassName, this.wmRoleName);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return java.util.Objects.equals(this.waylandAppId, other.waylandAppId)
                        && java.util.Objects.equals(this.wmClassClass, other.wmClassClass)
                        && java.util.Objects.equals(this.wmClassName, other.wmClassName)
                        && java.util.Objects.equals(this.wmRoleName, other.wmRoleName);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(waylandAppId, wmClassClass, wmClassName, wmRoleName);
        }

        @Override
        public String toString() {
            return "CefLinuxWindowProperties.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "waylandAppId=" + waylandAppId + ", " + "wmClassClass=" + wmClassClass + ", " + "wmClassName=" + wmClassName + ", " + "wmRoleName=" + wmRoleName + "}";
        }
    }
}
