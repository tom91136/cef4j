// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Cookie information. */
public final class CefCookie {

    public final long size;
    public final int name;
    public final int value;
    public final int domain;
    public final int path;
    public final int secure;
    public final int httponly;
    public final CefBasetime creation;
    public final CefBasetime lastAccess;
    public final int hasExpires;
    public final CefBasetime expires;
    public final CefCookieSameSite sameSite;
    public final CefCookiePriority priority;

    public CefCookie(
            long size,
            int name,
            int value,
            int domain,
            int path,
            int secure,
            int httponly,
            CefBasetime creation,
            CefBasetime lastAccess,
            int hasExpires,
            CefBasetime expires,
            CefCookieSameSite sameSite,
            CefCookiePriority priority) {
        this.size = size;
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
        return this.size == other.size
                && this.name == other.name
                && this.value == other.value
                && this.domain == other.domain
                && this.path == other.path
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
                size,
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
        return "CefCookie{" + "size=" + size + ", " + "name=" + name + ", " + "value=" + value + ", " + "domain="
                + domain + ", " + "path=" + path + ", " + "secure=" + secure + ", " + "httponly=" + httponly + ", "
                + "creation=" + creation + ", " + "lastAccess=" + lastAccess + ", " + "hasExpires=" + hasExpires + ", "
                + "expires=" + expires + ", " + "sameSite=" + sameSite + ", " + "priority=" + priority + "}";
    }
}
