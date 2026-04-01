// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Cookie information.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_cookie_t {
 *   size_t size;
 *   cef_string_t* name;
 *   cef_string_t* value;
 *   cef_string_t* domain;
 *   cef_string_t* path;
 *   int secure;
 *   int httponly;
 *   cef_basetime_t* creation;
 *   cef_basetime_t* last_access;
 *   int has_expires;
 *   cef_basetime_t* expires;
 *   cef_cookie_same_site_t same_site;
 *   cef_cookie_priority_t priority;
 * } cef_cookie_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:846</a>
 */
public final class CefCookie {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final String name;
    public final String value;
    public final String domain;
    public final String path;
    public final int secure;
    public final int httponly;
    public final CefBasetime creation;
    public final CefBasetime lastAccess;
    public final int hasExpires;
    public final CefBasetime expires;
    public final CefCookieSameSite sameSite;
    public final CefCookiePriority priority;

    public CefCookie(
            String name,
            String value,
            String domain,
            String path,
            int secure,
            int httponly,
            CefBasetime creation,
            CefBasetime lastAccess,
            int hasExpires,
            CefBasetime expires,
            CefCookieSameSite sameSite,
            CefCookiePriority priority) {
        this.name = name;
        this.value = value;
        this.domain = domain;
        this.path = path;
        this.secure = secure;
        this.httponly = httponly;
        this.creation = creation;
        this.lastAccess = lastAccess;
        this.hasExpires = hasExpires;
        this.expires = expires;
        this.sameSite = sameSite;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCookie)) return false;
        CefCookie other = (CefCookie) obj;
        return java.util.Objects.equals(this.name, other.name)
                && java.util.Objects.equals(this.value, other.value)
                && java.util.Objects.equals(this.domain, other.domain)
                && java.util.Objects.equals(this.path, other.path)
                && this.secure == other.secure
                && this.httponly == other.httponly
                && java.util.Objects.equals(this.creation, other.creation)
                && java.util.Objects.equals(this.lastAccess, other.lastAccess)
                && this.hasExpires == other.hasExpires
                && java.util.Objects.equals(this.expires, other.expires)
                && java.util.Objects.equals(this.sameSite, other.sameSite)
                && java.util.Objects.equals(this.priority, other.priority);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                name,
                value,
                domain,
                path,
                secure,
                httponly,
                creation,
                lastAccess,
                hasExpires,
                expires,
                sameSite,
                priority);
    }

    @Override
    public String toString() {
        return "CefCookie{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "name=" + name + ", "
                + "value=" + value + ", " + "domain=" + domain + ", " + "path=" + path + ", " + "secure=" + secure
                + ", " + "httponly=" + httponly + ", " + "creation=" + creation + ", " + "lastAccess=" + lastAccess
                + ", " + "hasExpires=" + hasExpires + ", " + "expires=" + expires + ", " + "sameSite=" + sameSite + ", "
                + "priority=" + priority + "}";
    }
}
