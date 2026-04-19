// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Cookie information.
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:846</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefCookie {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * The cookie name. 
         */    public final @Nullable String name;
        /**
         * The cookie value. 
         */    public final @Nullable String value;
        /**
         * If {@code domain} is empty a host cookie will be created instead of a domain cookie. Domain cookies are stored with a leading "." and are visible to sub-domains whereas host cookies are not. 
         */    public final @Nullable String domain;
        /**
         * If {@code path} is non-empty only URLs at or below the path will get the cookie value. 
         */    public final @Nullable String path;
        /**
         * If {@code secure} is {@code true} the cookie will only be sent for HTTPS requests. 
         */    public final int secure;
        /**
         * If {@code httponly} is {@code true} the cookie will only be sent for HTTP requests. 
         */    public final int httponly;
        /**
         * The cookie creation date. This is automatically populated by the system on cookie creation. 
         */    public final @Nullable CefBasetime creation;
        /**
         * The cookie last access date. This is automatically populated by the system on access. 
         */    public final @Nullable CefBasetime lastAccess;
        /**
         * The cookie expiration date is only valid if {@code has_expires} is {@code true}. 
         */    public final int hasExpires;
    public final @Nullable CefBasetime expires;
        /**
         * Same site. 
         */    public final @Nullable CefCookieSameSite sameSite;
        /**
         * Priority. 
         */    public final @Nullable CefCookiePriority priority;

    public CefCookie(@Nullable String name, @Nullable String value, @Nullable String domain, @Nullable String path, int secure, int httponly, @Nullable CefBasetime creation, @Nullable CefBasetime lastAccess, int hasExpires, @Nullable CefBasetime expires, @Nullable CefCookieSameSite sameSite, @Nullable CefCookiePriority priority) {
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

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.name, this.value, this.domain, this.path, this.secure, this.httponly, this.creation, this.lastAccess, this.hasExpires, this.expires, this.sameSite, this.priority);
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
        return java.util.Objects.hash(name, value, domain, path, secure, httponly, creation, lastAccess, hasExpires, expires, sameSite, priority);
    }

    @Override
    public String toString() {
        return "CefCookie{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "name=" + name + ", " + "value=" + value + ", " + "domain=" + domain + ", " + "path=" + path + ", " + "secure=" + secure + ", " + "httponly=" + httponly + ", " + "creation=" + creation + ", " + "lastAccess=" + lastAccess + ", " + "hasExpires=" + hasExpires + ", " + "expires=" + expires + ", " + "sameSite=" + sameSite + ", " + "priority=" + priority + "}";
    }

    /**
     * Mutable variant of {@link CefCookie}. Cookie information.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:846</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * The cookie name. 
             */        public @Nullable String name;
            /**
             * The cookie value. 
             */        public @Nullable String value;
            /**
             * If {@code domain} is empty a host cookie will be created instead of a domain cookie. Domain cookies are stored with a leading "." and are visible to sub-domains whereas host cookies are not. 
             */        public @Nullable String domain;
            /**
             * If {@code path} is non-empty only URLs at or below the path will get the cookie value. 
             */        public @Nullable String path;
            /**
             * If {@code secure} is {@code true} the cookie will only be sent for HTTPS requests. 
             */        public int secure;
            /**
             * If {@code httponly} is {@code true} the cookie will only be sent for HTTP requests. 
             */        public int httponly;
            /**
             * The cookie creation date. This is automatically populated by the system on cookie creation. 
             */        public @Nullable CefBasetime creation;
            /**
             * The cookie last access date. This is automatically populated by the system on access. 
             */        public @Nullable CefBasetime lastAccess;
            /**
             * The cookie expiration date is only valid if {@code has_expires} is {@code true}. 
             */        public int hasExpires;
        public @Nullable CefBasetime expires;
            /**
             * Same site. 
             */        public @Nullable CefCookieSameSite sameSite;
            /**
             * Priority. 
             */        public @Nullable CefCookiePriority priority;

        public Mutable() {}

        public Mutable(@Nullable String name, @Nullable String value, @Nullable String domain, @Nullable String path, int secure, int httponly, @Nullable CefBasetime creation, @Nullable CefBasetime lastAccess, int hasExpires, @Nullable CefBasetime expires, @Nullable CefCookieSameSite sameSite, @Nullable CefCookiePriority priority) {
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

        /** Create an immutable snapshot of this instance. */
        public CefCookie toImmutable() {
            return new CefCookie(this.name, this.value, this.domain, this.path, this.secure, this.httponly, this.creation, this.lastAccess, this.hasExpires, this.expires, this.sameSite, this.priority);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return java.util.Objects.hash(name, value, domain, path, secure, httponly, creation, lastAccess, hasExpires, expires, sameSite, priority);
        }

        @Override
        public String toString() {
            return "CefCookie.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "name=" + name + ", " + "value=" + value + ", " + "domain=" + domain + ", " + "path=" + path + ", " + "secure=" + secure + ", " + "httponly=" + httponly + ", " + "creation=" + creation + ", " + "lastAccess=" + lastAccess + ", " + "hasExpires=" + hasExpires + ", " + "expires=" + expires + ", " + "sameSite=" + sameSite + ", " + "priority=" + priority + "}";
        }
    }
}
