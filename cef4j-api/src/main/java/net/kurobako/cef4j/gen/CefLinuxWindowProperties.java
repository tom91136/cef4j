// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Linux window properties, such as X11's WM_CLASS or Wayland's app_id. Those are passed to CefWindowDelegate, so the
 * client can set them for the CefWindow's top-level. Thus, allowing window managers to correctly display the
 * application's information (e.g., icons).
 */
public final class CefLinuxWindowProperties {

    public final long size;
    public final int waylandAppId;
    public final int wmClassClass;
    public final int wmClassName;
    public final int wmRoleName;

    public CefLinuxWindowProperties(long size, int waylandAppId, int wmClassClass, int wmClassName, int wmRoleName) {
        this.size = size;
        this.waylandAppId = waylandAppId;
        this.wmClassClass = wmClassClass;
        this.wmClassName = wmClassName;
        this.wmRoleName = wmRoleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefLinuxWindowProperties)) return false;
        CefLinuxWindowProperties other = (CefLinuxWindowProperties) obj;
        return this.size == other.size
                && this.waylandAppId == other.waylandAppId
                && this.wmClassClass == other.wmClassClass
                && this.wmClassName == other.wmClassName
                && this.wmRoleName == other.wmRoleName;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, waylandAppId, wmClassClass, wmClassName, wmRoleName);
    }

    @Override
    public String toString() {
        return "CefLinuxWindowProperties{" + "size=" + size + ", " + "waylandAppId=" + waylandAppId + ", "
                + "wmClassClass=" + wmClassClass + ", " + "wmClassName=" + wmClassName + ", " + "wmRoleName="
                + wmRoleName + "}";
    }
}
